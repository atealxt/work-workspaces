package jbpmdemo.servlet;

import javax.servlet.http.HttpServlet;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;

public abstract class BaseServlet extends HttpServlet {

    // TODO 注意，不支持多线程！！！
    protected ProcessEngine processEngine = Configuration.getProcessEngine();
    protected RepositoryService repositoryService = processEngine.getRepositoryService();
    protected ExecutionService executionService = processEngine.getExecutionService();
}
