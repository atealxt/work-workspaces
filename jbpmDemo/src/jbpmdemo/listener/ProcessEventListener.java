package jbpmdemo.listener;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;

/** 配置写在jbpm的xml里 */
public class ProcessEventListener implements EventListener {

    private String msg;

    public void notify(final EventListenerExecution execution) {

        String str = msg;

        str += " [id = " + execution.getId();
        str += " name = " + execution.getName();
        str += " key = " + execution.getKey();
        str += " state = " + execution.getState();
        str += " isEnded = " + execution.isEnded();
        str += " activeActivityNames = " + execution.findActiveActivityNames();
        str += "]";

        System.out.println(str);

        // context value pass:
        // execution.getVariable
        // execution.setVariable
    }
}
