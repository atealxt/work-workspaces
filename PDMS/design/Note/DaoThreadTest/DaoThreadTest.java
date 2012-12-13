package pdms.test;

import pdms.components.dao.*;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import pdms.components.pojo.Group;
import pdms.components.pojo.User;
import pdms.platform.configeration.DIManager;
import pdms.platform.configeration.DaoManager;
import pdms.platform.constant.CommonConstant;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DaoThreadTest {

    public static void main(String[] args) {

        DIManager.getBean("UserDao");//init program

        Read r = new Read();
        r.start();

        Update u = new Update();
        u.start();

        Read r2= new Read();
        r2.start();

    }
}

class Read extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {

            while (Update.RUNNING) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

//            DaoThreadTest.RUNNING = true;
//            System.out.println("aaa start");
//            A0500UserMService srv = (A0500UserMService) DIManager.getBean("A0500UserMService");
            UserDao dao = (UserDao) DIManager.getBean("UserDao");
            try {
//                List<A0500UserMVo> vos = srv.MakeVo(10, 0, null, null);
//                System.out.println("read: " + i + " " + vos.get(8).getULoginId() + " " + vos.get(8).getUGroup());//蒋续邀                
                User user = dao.findByLoginId("蒋续邀");
                Set<Group> g = user.getGroups();
                System.out.println("read: " + i + " " + user.getLoginid() + " " + g);
            } catch (Exception ex) {
                Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
            }
            DaoManager.closeSession();
//            System.out.println("aaa end");
//            DaoThreadTest.RUNNING = false;

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

class Update extends Thread {

    public static Boolean RUNNING = false;
    //TODO 1.把判断flg改为同步锁性质的 2.重复读如何不加锁？
    //读的时候判断是否更新中；写的时候独占锁

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

//            while (DaoThreadTest.RUNNING) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }

            RUNNING = true;
//            System.out.println("bbb start");

            UserDao dao = (UserDao) DIManager.getBean("UserDao");
            GroupDao gdao = (GroupDao) DIManager.getBean("GroupDao");
            User user = dao.findByLoginId("蒋续邀");
            if (i % 2 == 0) {
                Set set = new HashSet(1);
                user.setGroups(set);
            } else {
                Set set = new HashSet(1);
                set.add(gdao.findById(1));
                user.setGroups(set);
            }
            int istatus = dao.Update(user);
            if (istatus == CommonConstant.DB_STATUS_NG) {
                System.out.println("update error " + i);
                break;
            }
            Set<Group> g = user.getGroups();
            System.out.println("update: " + i + " " + user.getLoginid() + " " + g);

//            DaoManager.closeSession();

//            System.out.println("bbb end");
            RUNNING = false;

            try {
                Thread.sleep(100);
            } catch (Exception ex) {
                Logger.getLogger(Read.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
}