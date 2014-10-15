package jbpmdemo.servlet.ask4leave;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jbpmdemo.servlet.BaseServlet;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.task.Task;

/** 最好不要拿jbpm的类来当VO，特别是给freeMarker当VO... */
public class ServletAsk4Leave extends BaseServlet {

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {

        if (!chkloggedIn(req, resp)) {
            return;
        }
        deployIfNeed(req);
        delIfNeed(req);
        startIfNeed(req);

        // 流程定义
        List<ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery().list();
        req.setAttribute("processDefinitions", processDefinitions);

        // 流程实例
        List<ProcessInstance> processInstances = executionService.createProcessInstanceQuery().list();
        req.setAttribute("processInstances", processInstances);

        // 待办任务
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.findPersonalTasks((String) req.getSession().getAttribute("USERNAME"));
        req.setAttribute("tasks", tasks);

        // 历史流程
        List<HistoryProcessInstance> historyProcess = processEngine.getHistoryService()
                .createHistoryProcessInstanceQuery().list();
        req.setAttribute("historyProcess", historyProcess);
        req.setAttribute("duration", calcDuration(historyProcess));
        req.setAttribute("endActivityName", calcEndActivityName(historyProcess));

        req.getRequestDispatcher("/ask4leave/ask4leave.ftl").forward(req, resp);
    }

    private boolean chkloggedIn(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        loginIfNeed(req);
        if (req.getSession().getAttribute("USERNAME") == null
                || ((String) req.getSession().getAttribute("USERNAME")).trim().isEmpty()) {
            resp.sendRedirect("login.ftl");
            return false;
        }
        return true;
    }

    private void loginIfNeed(final HttpServletRequest req) {
        if (req.getParameter("username") != null) {
            req.getSession().setAttribute("USERNAME", req.getParameter("username"));
        }
    }

    /** 部署新的流程定义(这里固定加载请假的流程) */
    private void deployIfNeed(final HttpServletRequest req) {
        if ("deploy".equals(req.getParameter("action"))) {
            // repositoryService.createDeployment().addResourceFromClasspath("askforleave.jpdl.xml").deploy();
            // 要想看图的话，必须用zip方式
            ZipInputStream inputStream = new ZipInputStream(this.getClass().getResourceAsStream("/askforleave.zip"));
            repositoryService.createDeployment().addResourcesFromZipInputStream(inputStream).deploy();
        }
    }

    /** 启动流程 */
    private void startIfNeed(final HttpServletRequest req) {
        if ("start".equals(req.getParameter("action"))) {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("applyer", req.getSession().getAttribute("USERNAME"));
            executionService.startProcessInstanceById(req.getParameter("id"), param);
        }
    }

    /** 删除流程(流程定义) */
    private void delIfNeed(final HttpServletRequest req) {
        if ("del".equals(req.getParameter("action"))) {
            repositoryService.deleteDeploymentCascade(req.getParameter("pid"));
        }
    }

    private Object calcDuration(final List<HistoryProcessInstance> historyProcess) {
        List<String> duration = new ArrayList<String>();
        for (HistoryProcessInstance i : historyProcess) {
            if (i.getDuration() == null) {
                duration.add("NaN");
            } else {
                duration.add(i.getDuration() + " milliseconds.");
            }
        }
        return duration;
    }

    private Object calcEndActivityName(final List<HistoryProcessInstance> historyProcess) {
        List<String> endActivityName = new ArrayList<String>();
        for (HistoryProcessInstance i : historyProcess) {
            if (i.getEndActivityName() == null) {
                endActivityName.add("NaN");
            } else {
                endActivityName.add(i.getEndActivityName());
            }
        }
        return endActivityName;
    }
}
