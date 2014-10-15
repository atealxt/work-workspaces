package jbpmdemo.test;

import java.util.List;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.RepositoryService;
import org.junit.Test;

public class HelloworldTest extends TestBase {

    @Override
    @Test
    public void excute() {

        //加载新流程文件
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String deployId = repositoryService.createDeployment().addResourceFromClasspath("helloworld.jpdl.xml").deploy();

        //取得已加载的流程定义
        List<ProcessDefinition> processDefinition = repositoryService.createProcessDefinitionQuery().list();
        logger.debug(processDefinition);

        //删除流程定义
        // repositoryService.deleteDeployment(deployId);
        repositoryService.deleteDeploymentCascade(deployId);
        logger.debug(repositoryService.createProcessDefinitionQuery().list());

    }

}
