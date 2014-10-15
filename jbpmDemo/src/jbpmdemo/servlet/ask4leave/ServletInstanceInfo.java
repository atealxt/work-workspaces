package jbpmdemo.servlet.ask4leave;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jbpmdemo.servlet.BaseServlet;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;

public class ServletInstanceInfo extends BaseServlet {

    @Override
    protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException,
            IOException {

        // 取得流程实例
        ProcessInstance processInstance = executionService.findProcessInstanceById(req.getParameter("id"));

        // 取得源jBPM的流程PNG图片，并添加当前活动信息
        BufferedImage image = ImageIO.read(getProcessPic(processInstance));
        Graphics2D graphic = image.createGraphics();
        graphic.setColor(Color.RED);
        for (ActivityCoordinates coordinates : getActivityCoordinates(processInstance)) {
            graphic.drawRect(coordinates.getX(), coordinates.getY(), coordinates.getWidth(), coordinates.getHeight());
        }
        graphic.dispose();

        // 输出至流
        ServletOutputStream outputStream = resp.getOutputStream();
        ImageIO.write(image, "PNG", outputStream);
        outputStream.close();
    }

    /** 取得源jBPM的流程PNG图片 */
    private InputStream getProcessPic(final ProcessInstance processInstance) {

        String processDefinitionId = processInstance.getProcessDefinitionId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(processDefinitionId).uniqueResult();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
                                                                        processDefinition.getImageResourceName());
        return inputStream;
    }

    /** 取得所有活动节点信息 */
    private List<ActivityCoordinates> getActivityCoordinates(final ProcessInstance processInstance) {

        Set<String> activityNames = processInstance.findActiveActivityNames();
        List<ActivityCoordinates> coordinatesList = new ArrayList<ActivityCoordinates>(activityNames.size());
        for (String name : activityNames) {
            ActivityCoordinates coordinates = repositoryService//
                    .getActivityCoordinates(processInstance.getProcessDefinitionId(), name);
            coordinatesList.add(coordinates);
        }
        return coordinatesList;
    }

}
