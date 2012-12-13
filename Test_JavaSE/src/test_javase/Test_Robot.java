/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test_javase;

import java.awt.AWTException;
//import java.awt.MouseInfo;
//import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author Administrator
 */
public class Test_Robot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, AWTException {

        Robot robot = new Robot();

        //win+r
        Thread.sleep(2500);
        robot.keyPress(KeyEvent.VK_WINDOWS);
        robot.keyPress(KeyEvent.VK_R);
        robot.keyRelease(KeyEvent.VK_WINDOWS);
        robot.keyRelease(KeyEvent.VK_R);

        //open notepad
        doKey(robot, KeyEvent.VK_N, 2000);
        doKey(robot, KeyEvent.VK_O, 250);
        doKey(robot, KeyEvent.VK_T, 250);
        doKey(robot, KeyEvent.VK_E, 250);
        doKey(robot, KeyEvent.VK_P, 250);
        doKey(robot, KeyEvent.VK_A, 250);
        doKey(robot, KeyEvent.VK_D, 250);
        doKey(robot, KeyEvent.VK_ENTER, 300);

        //write
        doKey(robot, KeyEvent.VK_N, 2000);
        doKey(robot, KeyEvent.VK_I, 150);
        doKey(robot, KeyEvent.VK_SPACE, 150);
        doKey(robot, KeyEvent.VK_S, 150);
        doKey(robot, KeyEvent.VK_H, 150);
        doKey(robot, KeyEvent.VK_I, 150);
        doKey(robot, KeyEvent.VK_SPACE, 150);
        doKey(robot, KeyEvent.VK_G, 150);
        doKey(robot, KeyEvent.VK_O, 150);
        doKey(robot, KeyEvent.VK_U, 150);
        doKey(robot, KeyEvent.VK_SPACE, 150);
        doKey(robot, KeyEvent.VK_S, 150);
        doKey(robot, KeyEvent.VK_A, 150);
        doKey(robot, KeyEvent.VK_I, 150);
        doKey(robot, KeyEvent.VK_PERIOD, 150);
        doKey(robot, KeyEvent.VK_H, 150);
        doKey(robot, KeyEvent.VK_A, 150);
        doKey(robot, KeyEvent.VK_H, 150);
        doKey(robot, KeyEvent.VK_A, 150);
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.keyPress(KeyEvent.VK_1);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.keyRelease(KeyEvent.VK_1);

        //close
        Thread.sleep(2000);
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
        doKey(robot, KeyEvent.VK_N, 300);


//        //max size
//        doKey(robot, KeyEvent.VK_ALT, 500);
//        doKey(robot, KeyEvent.VK_SPACE, 0);
//        doKey(robot, KeyEvent.VK_X, 0);
//
//        PointerInfo pointer = MouseInfo.getPointerInfo();
//        int x = pointer.getLocation().x;
//        int y = pointer.getLocation().y;
//        robot.mouseMove(300, 200);


    }

    private static void doKey(Robot robot, int cmd, int sleeptime) throws InterruptedException, AWTException {
        Thread.sleep(sleeptime);
        robot.keyPress(cmd);
        robot.keyRelease(cmd);
    }
}
