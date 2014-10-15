package jbpmdemo.servlet.ask4leave;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jbpmdemo.servlet.BaseServlet;

import org.jbpm.api.TaskService;

public class ServletAudit extends BaseServlet {

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {

        if (auditIfNeed(req, resp)) {
            return;
        }

        String taskId = req.getParameter("id");
        TaskService taskService = processEngine.getTaskService();

        // 在用户组机制下可以多人竞争接受任务(Task节点的type要设为candidate-groups)
        // 但不建议直接使用jbpm的权限控制，自己写权限控制为好(把自己的权限模型实现IdentityService接口)
        // taskService.takeTask(taskId, (String) req.getSession().getAttribute("USERNAME"));

        req.setAttribute("applyer", taskService.getVariable(taskId, "applyer"));
        req.setAttribute("day", taskService.getVariable(taskId, "day"));
        req.setAttribute("reason", taskService.getVariable(taskId, "reason"));

        req.getRequestDispatcher("/ask4leave/manager.ftl").forward(req, resp);
    }

    /**
     * 审核
     *
     * @throws IOException
     */
    private boolean auditIfNeed(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        if ("audit".equals(req.getParameter("action"))) {
            TaskService taskService = processEngine.getTaskService();

            String taskId = req.getParameter("taskId");
            String result = req.getParameter("result");
            result = new String(result.getBytes("ISO-8859-1"), "UTF-8");

            if ("驳回".equals(result)) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("reject", req.getSession().getAttribute("USERNAME"));// param.put可继续添加任意参数
                taskService.completeTask(taskId, result, param);
            } else {
                taskService.completeTask(taskId, result);
            }
            resp.sendRedirect("ask4leave");
            return true;
        }
        return false;
    }

}
