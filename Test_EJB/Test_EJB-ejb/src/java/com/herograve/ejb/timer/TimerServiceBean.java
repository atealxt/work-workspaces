package com.herograve.ejb.timer;

import java.util.Date;
import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

/**
 *
 * @author Administrator
 */
@Stateless
@Remote({TimerObject.class})
public class TimerServiceBean implements TimerObject {

    private int count = 1;
    @Resource
    private SessionContext ctx;

    public void scheduleTimer(long milliseconds) {
        count = 1;
        TimerService service = ctx.getTimerService();

        //single-event timers
//        service.createTimer(milliseconds, "This is a timer");

        //interval timers
        service.createTimer(new Date(new Date().getTime() + 2000), milliseconds, "This is a timer");

    }

    @Timeout
    private void timeoutHandler(Timer timer) {
        System.out.println("Timer.getInfo(): " + timer.getInfo());
        System.out.println("Timer.getNextTimeout(): " + timer.getNextTimeout());
        System.out.println("Timer.getTimeRemaining(): " + timer.getTimeRemaining());
        if (count++ > 2) {
            System.out.println("Timer cancelled.");
            timer.cancel();
        }
    }
}
