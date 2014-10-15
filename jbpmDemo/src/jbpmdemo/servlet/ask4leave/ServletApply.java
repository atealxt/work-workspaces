package jbpmdemo.servlet.ask4leave;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jbpmdemo.servlet.BaseServlet;

import org.jbpm.api.TaskService;

public class ServletApply extends BaseServlet {

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {

        if (applyIfNeed(req, resp)) {
            return;
        }

        String taskId = req.getParameter("id");
        TaskService taskService = processEngine.getTaskService();
        req.setAttribute("reject", taskService.getVariable(taskId, "reject"));

        req.getRequestDispatcher("/ask4leave/apply.ftl").forward(req, resp);
    }

    /**
     * 请假申请
     *
     * @throws IOException
     */
    private boolean applyIfNeed(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {

        if ("apply".equals(req.getParameter("action"))) {

            String taskId = req.getParameter("taskId");
            String applyer = req.getParameter("applyer");
            int day = Integer.parseInt(req.getParameter("day"));
            String reason = req.getParameter("reason");

            Map<String, Object> param = new HashMap<String, Object>();
            param.put("applyer", applyer);
            param.put("day", day);
            param.put("reason", reason);

            TaskService taskService = processEngine.getTaskService();
            taskService.completeTask(taskId, param);

            resp.sendRedirect("ask4leave");
            return true;
        }
        return false;
    }
}
