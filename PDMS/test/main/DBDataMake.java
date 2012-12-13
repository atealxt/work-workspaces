/*
 * 初始化数据库数据。
 * 执行之前请做到：
 *     1.备份原数据。
 *     2.已建立数据库PDMS。
 */
package main;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pdms.components.dao.MissionDao;
import pdms.components.pojo.Example;
import pdms.components.pojo.Function;
import pdms.components.pojo.Group;
import pdms.components.pojo.Identity;
import pdms.components.pojo.Mission;
import pdms.components.pojo.MissionSubmit;
import pdms.components.pojo.Operate;
import pdms.components.pojo.Project;
import pdms.components.pojo.Reply;
import pdms.components.pojo.Role;
import pdms.components.pojo.Topic;
import pdms.components.pojo.UploadFile;
import pdms.components.pojo.User;
import pdms.components.pojo.UserProjectRel;
import pdms.platform.configeration.DIManager;
import pdms.platform.core.PdmsException;
import pdms.platform.service.impl.A0900FileServiceImpl;
import pdms.platform.util.StringUtil;

/**
 *
 * @author LUSuo(atealxt@gmail.com)
 */
public class DBDataMake {

    private Configuration conf;
    private SessionFactory sf;

    public DBDataMake() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        conf = new Configuration().configure();
        sf = conf.buildSessionFactory();
    }

    @After
    public void tearDown() {
        sf.close();
    }

    @Test
    public void createTable() {

        //make file saving path
        String path = this.getClass().getResource("../").getPath();
        System.out.println(path);
        for (int i = 0; i < 5; i++) {
            path = path.substring(0, path.lastIndexOf("/"));
        }
        path = path + "/design/Database/PDMS.sql";
        System.out.println(path);

        SchemaExport export = new SchemaExport(conf);
        export.setOutputFile(path);
        export.create(true, true);

        //执行順序不能变
        createData_Example();
        createData_Operate();
        createData_Function();
        createData_Role();
        createData_Group();
        createData_Identity();
        createData_User();
        createData_Project();
        createData_Mission();
        createData_Topic();
        createData_Reply();
        createData_UploadFile();
    }

    public void createData_UploadFile() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        UploadFile uf = new UploadFile();
        uf.setFileName("详细设计(保留测试别删).xls");
        User u = (User) sess.createQuery("from User where loginid='atea'").uniqueResult();
        uf.setUser(u);
        try {
            uf.setAddress(A0900FileServiceImpl.getFileSaveAdd(u, "xls"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        uf.setUploadDate(new java.util.Date());
        sess.save(uf);

        sess.flush();
        sess.clear();

        for (int i = 1; i < 41; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
            }

            uf = new UploadFile();
            uf.setFileName("详细设计" + i);
            u = (User) sess.createQuery("from User where loginid='atea'").uniqueResult();
            uf.setUser(u);
            try {
                uf.setAddress(A0900FileServiceImpl.getFileSaveAdd(u, "xls"));
            } catch (PdmsException ex) {
                Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
            }
            uf.setUploadDate(new java.util.Date());
            sess.save(uf);
            if (i % 4 == 0) {
                sess.flush();
                sess.clear();
            }
        }

        trans.commit();
    }

    public void createData_Reply() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Reply r = new Reply();
        r.setContent(Hibernate.createClob("工具所在地址：172.16.98.1"));
        r.setCreatetime(new java.util.Date());
        r.setCreateuser((User) sess.createQuery("from User where loginid='atea'").uniqueResult());
        r.setTopic((Topic) sess.createQuery("from Topic where name='本项目的开发工具'").uniqueResult());
        sess.save(r);

        r = new Reply();
        r.setContent(Hibernate.createClob("Server"));
        r.setCreatetime(new java.util.Date());
        r.setCreateuser((User) sess.createQuery("from User where loginid='atea'").uniqueResult());
        r.setTopic((Topic) sess.createQuery("from Topic where name='SqlServer安装选择Server端还是Client端？'").uniqueResult());
        sess.save(r);

        trans.commit();
    }

    public void createData_Topic() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Topic t = new Topic();

        for (int i = 1; i < 51; i++) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
            }

            t = new Topic();
            t.setName("帖子测试数据！第" + i + "条");
            t.setContent(Hibernate.createClob("Test<span style=\"color: rgb(255, 0, 0);\">D</span>ata<span style=\"font-style: italic; font-weight: bold;\">测试数据</span>Test<span style=\"background-color: rgb(255, 0, 0);\">D</span>ata测试数据<br>Test<span style=\"color: rgb(255, 0, 0);\">D</span>ata测试数据Test<span style=\"background-color: rgb(255, 0, 0);\">D</span>ata<span style=\"font-style: italic; font-weight: bold;\">测试数据</span><br>Test<span style=\"color: rgb(255, 0, 0);\">D</span>ata<span style=\"font-style: italic; font-weight: bold;\">测试数据</span>Test<span style=\"background-color: rgb(255, 0, 0);\">D</span>ata测试数据<br>"));
            t.setCreatetime(new java.util.Date());
            if (i % 2 == 0) {
                t.setTopiclevel(2);
            } else {
                t.setTopiclevel(1);
            }
            if (i % 3 == 0) {
                t.setTopictype(1);
            } else {
                t.setTopictype(0);
            }
            if (i % 5 == 0) {
                t.setStatus(0);
            } else {
                t.setStatus(1);
            }
            if (i % 7 == 0) {
                t.setStatus(-1);
            }
            t.setCreateuser((User) sess.createQuery("from User where loginid='atea'").uniqueResult());
            t.setProject((Project) sess.createQuery("from Project where id=2").uniqueResult());
            sess.save(t);
            if (i % 4 == 0) {
                sess.flush();
                sess.clear();
            }
        }
        sess.flush();
        sess.clear();

        t.setName("本项目的开发工具");
        t.setContent(Hibernate.createClob("1.VB6.0<br>2.WindowsXP<br>3.SQLServer2000"));
        t.setCreatetime(new java.util.Date());
        t.setStatus(1);
        t.setTopictype(1);
        t.setTopiclevel(1);
        t.setCreateuser((User) sess.createQuery("from User where loginid='atea'").uniqueResult());
        t.setProject((Project) sess.createQuery("from Project where id=2").uniqueResult());
        sess.save(t);

        t = new Topic();
        t.setName("SqlServer安装选择Server端还是Client端？");
        t.setContent(Hibernate.createClob("如题"));
        t.setCreatetime(new java.util.Date());
        t.setStatus(1);
        t.setTopictype(0);
        t.setTopiclevel(2);
        t.setCreateuser((User) sess.createQuery("from User where loginid='wangqiang'").uniqueResult());
        t.setProject((Project) sess.createQuery("from Project where id=2").uniqueResult());
        sess.save(t);

        t = new Topic();
        t.setName("站务区第一帖");
        t.setContent(Hibernate.createClob("有问题欢迎大家讨论:)"));
        t.setCreatetime(new java.util.Date());
        t.setStatus(1);
        t.setTopictype(0);
        t.setTopiclevel(1);
        t.setCreateuser((User) sess.createQuery("from User where loginid='admin'").uniqueResult());
        t.setProject((Project) sess.createQuery("from Project where id=1").uniqueResult());
        sess.save(t);

        trans.commit();
    }

    public void createData_Mission() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        User atea = (User) sess.createQuery("from User where loginid='atea'").uniqueResult();
        User wangqiang = (User) sess.createQuery("from User where loginid='wangqiang'").uniqueResult();
        User userNo7 = (User) sess.createQuery("from User where id=7").uniqueResult();

        Mission m = new Mission();
        m.setContent("1.为数据库的图书表建立访问控制，<br>2.设计借阅图书的用户界面。<br>" +
            "具体资料请参见详细设计：\\172.16.98.120\\图书管理系统\\control.xls");
        m.setCreatetime(new Date(new java.util.Date().getTime()));
        m.setCompletetimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 20 * 1000));
        m.setConfirmtimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 10 * 1000));
        m.setDistributor(atea);
        m.setReceiver(wangqiang);
        m.setDistributionConfirm(1);
        m.setCompleteStatus(false);
        m.setInspectStatus(1);
        Project prj = (Project) sess.createQuery("from Project where id=2").uniqueResult();
        m.setProject(prj);
        sess.save(m);

        m = new Mission();
        m.setContent("填写设计文档。");
        m.setCreatetime(new Date(new java.util.Date().getTime()));
        m.setCompletetimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 20 * 1000));
        m.setConfirmtimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 10 * 1000));
        m.setDistributor(atea);
        m.setReceiver(wangqiang);
        m.setDistributionConfirm(0);
        m.setCompleteStatus(false);
        m.setInspectStatus(1);
        sess.save(m);

        sess.flush();
        sess.clear();

        //为柱状统计图建立倒数12个月的任务数据
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i <= 12; i++) {
            cal.add(Calendar.MONTH, -1);
            Random random = new Random();
            int count = random.nextInt(20);
            while (count < 4) {
                count = random.nextInt(20);
            }
            count++;
            for (int j = 1; j <= count; j++) {
                m = new Mission();
                m.setContent("报表任务测试数据");
                m.setCreatetime(new java.sql.Date(cal.getTime().getTime()));
                m.setCompletetimeLimit(new java.sql.Date(cal.getTime().getTime() + 3600 * 24 * 20 * 1000));
                m.setDistributor(atea);
                m.setReceiver(userNo7);
                m.setDistributionConfirm(1);
                m.setCompleteStatus(false);
                m.setInspectStatus(1);
                //m.setProject(prj);
                if (j % 3 == 0) {
                    m.setDistributionConfirm(0);
                } else {
                    m.setDistributionConfirm(1);
                }

                sess.save(m);
                if (j % 4 == 0) {
                    sess.flush();
                    sess.clear();
                }
            }

        }

        for (int i = 1; i < 21; i++) {
            m = new Mission();
            m.setContent("任务" + i);
            m.setCreatetime(new Date(new java.util.Date().getTime()));
            m.setCompletetimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 20 * 1000));
            m.setConfirmtimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 5 * 1000));
            m.setDistributor(atea);
            m.setReceiver(wangqiang);
            m.setDistributionConfirm(1);
            m.setCompleteStatus(false);
            m.setInspectStatus(1);

            if (i % 2 == 0) {
                m.setProject(prj);
            }
            if (i % 3 == 0) {
                m.setDistributionConfirm(0);
            } else {
                m.setDistributionConfirm(1);
            }

            sess.save(m);

            if (i % 4 == 0) {
                sess.flush();
                sess.clear();
            }
        }

        prj = (Project) sess.createQuery("from Project where id=3").uniqueResult();
        for (int i = 21; i < 41; i++) {
            m = new Mission();
            m.setContent("任务" + i);
            m.setCreatetime(new Date(new java.util.Date().getTime()));
            m.setCompletetimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 20 * 1000));
            m.setConfirmtimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 5 * 1000));
            m.setDistributor(atea);
            m.setReceiver(wangqiang);
            m.setDistributionConfirm(1);
            //m.setCompleteStatus(false);
            m.setInspectStatus(1);
            m.setProject(prj);

            if (i % 3 == 0) {
                m.setDistributionConfirm(1);
            } else {
                m.setDistributionConfirm(0);
            }

            sess.save(m);
            if (i % 4 == 0) {
                sess.flush();
                sess.clear();
            }
        }
        prj = (Project) sess.createQuery("from Project where id=4").uniqueResult();
        for (int i = 1; i < 11; i++) {
            m = new Mission();
            m.setContent("任务" + i);
            m.setCreatetime(new Date(new java.util.Date().getTime()));
            m.setCompletetimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 20 * 1000));
            m.setConfirmtimeLimit(new Date(new java.util.Date().getTime() + 3600 * 24 * 5 * 1000));
            m.setDistributor(atea);
            m.setReceiver(wangqiang);
            m.setDistributionConfirm(1);
            //m.setCompleteStatus(false);
            m.setInspectStatus(1);
            m.setProject(prj);

            if (i % 3 == 0) {
                m.setDistributionConfirm(1);
            } else {
                m.setDistributionConfirm(0);
            }

            sess.save(m);
            if (i % 4 == 0) {
                sess.flush();
                sess.clear();
            }
        }

        trans.commit();

        updateData_AfterCreateMission();
    }

    public void updateData_AfterCreateMission() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        MissionDao missionDao = (MissionDao) DIManager.getBean("MissionDao");
        List<Mission> missions = missionDao.findByProjectId(2, 5, 5);
        java.util.Date date = new java.util.Date();
        for (Mission m : missions) {
            MissionSubmit ms = new MissionSubmit();
            ms.setSubmitInfo("test data");
            ms.setMission(m);
            ms.setSubmitDate(new java.util.Date(date.getTime() + 1 * 24 * 60 * 60 * 1000));
            missionDao.Insert(ms);

            m.setSubmitInfo(ms);
            m.setDistributionConfirm(0);
            m.setCompleteStatus(true);
            missionDao.Update(m);
        }

        missions = missionDao.findByProjectId(3, 10, 0);
        for (Mission m : missions) {
            MissionSubmit ms = new MissionSubmit();
            ms.setSubmitInfo("test data");
            ms.setMission(m);
            ms.setSubmitDate(new java.util.Date(date.getTime() + 2 * 24 * 60 * 60 * 1000));
            missionDao.Insert(ms);

            m.setSubmitInfo(ms);
            m.setDistributionConfirm(0);
            m.setCompleteStatus(true);
            missionDao.Update(m);
        }

        missions = missionDao.findByProjectId(4, 5, 0);
        for (Mission m : missions) {
            MissionSubmit ms = new MissionSubmit();
            ms.setSubmitInfo("test data");
            ms.setMission(m);
            ms.setSubmitDate(new java.util.Date(date.getTime() + 3 * 24 * 60 * 60 * 1000));
            missionDao.Insert(ms);

            m.setSubmitInfo(ms);
            m.setDistributionConfirm(0);
            m.setCompleteStatus(true);
            missionDao.Update(m);
        }

        //为柱状统计图建立倒数12个月的任务提交数据
        Calendar cal = Calendar.getInstance();
        Random random = new Random();
        for (int i = 1; i <= 12; i++) {
            Date end = new Date(cal.getTime().getTime());
            cal.add(Calendar.MONTH, -1);
            Date start = new Date(cal.getTime().getTime());

            String sql = "from Mission as m where m.createtime>=:start and m.createtime<:end order by id";
            Query query = sess.createQuery(sql);
            query.setParameter("start", start);
            query.setParameter("end", end);
            List<Mission> msTemp = query.list();
            for (Mission m : msTemp) {
                if (random.nextInt(3) <= 1) {
                    m.setCompleteStatus(true);
                } else {
                    m.setCompleteStatus(random.nextBoolean());
                }
                if (m.isCompleteStatus()) {
                    MissionSubmit ms = new MissionSubmit();
                    ms.setSubmitInfo("test data");
                    ms.setMission(m);
                    ms.setSubmitDate(new java.util.Date(m.getCreatetime().getTime() + 3600 * 24 * 2 * 1000));
                    missionDao.Insert(ms);

                    m.setSubmitInfo(ms);
                    m.setDistributionConfirm(0);
                    //m.setCompleteStatus(true);
                    missionDao.Update(m);
                }
            }

        }

        trans.commit();
    }

    public void updateData_AfterCreateProject() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

//        User u = (User) sess.createQuery("from User where loginid='atea'").uniqueResult();
//        u.setProjects(new HashSet<Project>(sess.createQuery("from Project").list()));
//        sess.update(u);
//
//        u = (User) sess.createQuery("from User where loginid='wangqiang'").uniqueResult();
//        Set<Project> set = new HashSet<Project>();
//        set.add((Project) sess.createQuery("from Project where id=2").uniqueResult());
//        u.setProjects(set);
//        sess.update(u);
        UserProjectRel upr = null;
        User u = (User) sess.createQuery("from User where loginid='atea'").uniqueResult();
        List<Project> list = sess.createQuery("from Project where id!=1").list();
        for (Project p : list) {
            upr = new UserProjectRel();
            upr.setCanManage(true);
            upr.setProject(p);
            upr.setUser(u);
            sess.save(upr);
        }

        u = (User) sess.createQuery("from User where loginid='wangqiang'").uniqueResult();
        Project p = (Project) sess.createQuery("from Project where id=2").uniqueResult();
        upr = new UserProjectRel();
        upr.setCanManage(false);
        upr.setProject(p);
        upr.setUser(u);
        sess.save(upr);

        Identity identity = (Identity) sess.createQuery("from Identity where id=3").uniqueResult();
        p = (Project) sess.createQuery("from Project where id=3").uniqueResult();
        Set<User> users = identity.getUsers();
        for (User uTemp : users) {
            upr = new UserProjectRel();
            upr.setCanManage(false);
            upr.setProject(p);
            upr.setUser(uTemp);
            sess.save(upr);
        }
        p = (Project) sess.createQuery("from Project where id=4").uniqueResult();
        for (User uTemp : users) {
            upr = new UserProjectRel();
            upr.setCanManage(false);
            upr.setProject(p);
            upr.setUser(uTemp);
            sess.save(upr);
        }

        trans.commit();
    }

    public void createData_Project() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Project p = new Project();
        p.setName("站务服务区");
        p.setSummary("交流意见或建议、提出网站BUG、留言的地方");
        p.setAnnouncement("<div style=\"text-align: center;\"><div style=\"text-align: left;\"><font size=\"4\">这里是站务服务区，专门用于交流意见或建议、提交BUG、留言等。<br></font></div><div style=\"text-align: left;\"><font size=\"4\">发帖前请先阅读<a target=\"_blank\" title=\"\" href=\"../pages/pdms.html\">系统帮助</a></font><br></div></div>");
        p.setLogo("project/p-1.PNG");
        p.setStatus(1);
        sess.save(p);

        p = new Project();
        p.setName("图书管理系统");
        p.setSummary("使用VB为学校图书馆开发的图书管理系统");
        p.setAnnouncement("适用于单位图书馆，学校图书馆，图书租借机构的超级图书管理软件，是您管理图书的最佳帮手。<br>方便借书、还书、查找等操作。可以自定义图书，方便添加图书、管理图书、管理用户。<br>提供完善的借书和还书操作，完全独立的数据库系统，数据管理为您提供：借阅管理，学号管理，书号管理。<br>数据查询为您提供：借阅情况，书库查询，学号查询。<br>报表为您提供简单报表和详细报表，并提供方便的打印机设置和打印，还有数据导出功能，能够导出HTML和TXT文本文件供您选择。<br>提供的报表只有简单报表，报表内容是借出书的资料，系统设置提供默认借阅时间和默认续借时间。<br>本系统具有安全的数据库备份方案，简单易用，仿WINDOWS 操作方式，可以使您很快上手，打印机设置为您打印提供解决方案根据需要自动设置图书类别和相应的借出时间、借出册数，支持条形码。<br>");
        p.setLogo("project/p-2.PNG");
        p.setStatus(1);
        //注意：日期不能为周末!
        p.setStarttime(new Date(new java.util.Date().getTime()));
        p.setEndtime(new Date(new java.util.Date().getTime() + 3600 * 24 * 120 * 1000));
        sess.save(p);

        p = new Project();
        p.setName("学生管理系统");
        p.setSummary("使用C#为学校开发的学生管理系统");
        p.setAnnouncement("储存学生、老师及员工资料及照片,可以增加,修改及删除,其他功能包括搜索,查询,打印签到表,自动计算老师薪金,佣金等酬劳,学生付款结单,未付清学费的学生名单.<br>也可以打印学生证,毕业生名单,正式收据,信件,通告,报表,课程表,活动表,业务报告,邮寄标签,课程记录,个别学生,老师及员工详细资料,发送E-mail致学生,家长,老师及员工等.<br>SMS学生管理系统是一套人工智慧极高的软件,日常手抄工作,都可以快捷,有效的处理,省时,省力,省钱,是您业务上不可多得的好帮手.");
        p.setLogo("project/p-3.PNG");
        p.setStatus(1);
        p.setStarttime(new Date(new java.util.Date().getTime()));
        p.setEndtime(new Date(new java.util.Date().getTime() + 3600 * 24 * 120 * 1000));
        sess.save(p);

        p = new Project();
        p.setName("人事管理系统");
        p.setSummary("使用Java为企事业单位开发的人事管理系统");
        p.setAnnouncement("《人事管理系统》是一套对人事档案、考勤、人员变动、奖励与惩罚、员工培训等人事管理进行全面管理的系统。 <br>&gt;该系统通过采集在日常的人事管理中所产生的各种原始数据，根据现代人事管理的要求，对数据进行分类、统计、汇总、分析，建立程序化、制度化、规范化的人事信息计算机软件管理系统，对各项人事信息进行分类维护与综合查询，及时准确地报出各种报表，为人事信息管理提供了一个现代化的管理手段，保持了各种数据的完整性、及时性和正确性，完善了管理体系提高了管理水平。 <br>&gt;该系统集信息采集、分类、汇总、分析、查询、统计等各种处理为一体，各种操作既可以通过菜单进行，又可以通过系统的管理树导航进行。信息录入智能化，极大提高工作效率。");
        p.setLogo("project/p-4.PNG");
        p.setStatus(1);
        p.setStarttime(new Date(new java.util.Date().getTime()));
        p.setEndtime(new Date(new java.util.Date().getTime() + 3600 * 24 * 120 * 1000));
        sess.save(p);

        trans.commit();

        updateData_AfterCreateProject();
    }

    public void createData_User() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        User user = new User();
        user.setLoginid("pdms");
        user.setName("PDMS");
        try {
            user.setPassword(StringUtil.getMD5Code("pdms"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setIp("172.0.0.1");
        user.setStatus(0);
        List<Identity> identities = sess.createQuery("from Identity where name='PDMS'").list();
        sess.save(user);

        user = new User();
        user.setLoginid("guest");
        user.setName("访客");
        try {
            user.setPassword(StringUtil.getMD5Code("guest"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setIp("-");
        user.setStatus(0);
        List<Group> groups = sess.createQuery("from Group where name='访客'").list();
        user.setGroups(new HashSet<Group>(groups));
        sess.save(user);

        user = new User();
        user.setLoginid("admin");
        user.setName("Ashurei Luis");
        try {
            user.setPassword(StringUtil.getMD5Code("admin"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setIp("172.16.98.193");
        user.setStatus(0);
        identities = sess.createQuery("from Identity where name='系统管理员'").list();
        user.setIdentities(new HashSet<Identity>(identities));
        sess.save(user);

        user = new User();
        user.setLoginid("boss");
        user.setName("THE BOSS");
        try {
            user.setPassword(StringUtil.getMD5Code("boss"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setIp("172.16.98.1");
        user.setStatus(0);
        identities = sess.createQuery("from Identity where name='系统管理员'").list();
        user.setIdentities(new HashSet<Identity>(identities));
        sess.save(user);

        user = new User();
        user.setLoginid("atea");
        user.setName("阿T");
        try {
            user.setPassword(StringUtil.getMD5Code("atea"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setIp("172.16.98.2");
        user.setStatus(1);
        identities = sess.createQuery("from Identity where name='项目负责人'").list();
        user.setIdentities(new HashSet<Identity>(identities));
        sess.save(user);

        user = new User();
        user.setLoginid("wangqiang");
        user.setName("王强");
        try {
            user.setPassword(StringUtil.getMD5Code("wangqiang"));
        } catch (PdmsException ex) {
            Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
        }
        user.setIp("172.16.98.3");
        user.setStatus(1);
        identities = sess.createQuery("from Identity where name='职员'").list();
        user.setIdentities(new HashSet<Identity>(identities));
        List<Role> roles = sess.createQuery("from Role where id=4").list();
        user.setRoles(new HashSet<Role>(roles));
        groups = sess.createQuery("from Group where name='技术顾问'").list();
        user.setGroups(new HashSet<Group>(groups));
        sess.save(user);

        sess.flush();
        sess.clear();
        for (int i = 0; i < 20; i++) {
            user = new User();
            String strTemp = Naming.createName();
            System.out.println("strTemp: " + strTemp);
            user.setLoginid(strTemp);
            user.setName(strTemp);
            try {
                user.setPassword(StringUtil.getMD5Code(strTemp));
            } catch (PdmsException ex) {
                Logger.getLogger(DBDataMake.class.getName()).log(Level.SEVERE, null, ex);
            }
            user.setIp("172.16.99." + i);
            user.setStatus(1);

            if (i % 3 == 0) {
                user.setIdentities(new HashSet<Identity>(sess.createQuery("from Identity where name='项目负责人'").list()));
            } else {
                user.setIdentities(new HashSet<Identity>(identities));
            }
            sess.save(user);
            if (i % 4 == 0) {
                sess.flush();
                sess.clear();
            }
        }

        trans.commit();
    }

    public void createData_Identity() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        List<Role> roles = sess.createQuery("from Role").list();
        System.out.println(roles.size());

        //真正环境，把角色PDMS去掉(还有用户PDMS)
        Identity identity = new Identity();
        identity.setName("PDMS");
        Set set = new HashSet(roles);
        identity.setRoles(set);
        sess.save(identity);

        identity = new Identity();
        identity.setName("系统管理员");
        set = new HashSet(roles);
        identity.setRoles(set);
        sess.save(identity);

        set = new HashSet();
        for (Role r : roles) {
            if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Personal")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("CRU") &&
                //if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Topic")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("CRU") &&
                //if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Reply")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("R") &&
                r.getFunction().getName().equals("Mission")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("R") &&
                r.getFunction().getName().equals("Project")) {
                set.add(r);
            }
            //if (r.getOperate().getName().equals("CRUD") &&//C和D为文件管理权限
            if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("File")) {
                set.add(r);
            }
//            if (r.getOperate().getName().equals("R") &&
//                r.getFunction().getName().equals("Report")) {
//                set.add(r);
//            }
        }
        identity = new Identity();
        identity.setName("职员");
        identity.setRoles(set);
        sess.save(identity);

        set = new HashSet();
        for (Role r : roles) {
            if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Personal")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("CRU") &&
                //if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Topic")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("CRU") &&
                //if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Reply")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("CRUD") &&
                r.getFunction().getName().equals("Mission")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Project")) {
                set.add(r);
            }
            //if (r.getOperate().getName().equals("CRUD") &&//C和D为文件管理权限
            if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("File")) {
                set.add(r);
            }
            if (r.getOperate().getName().equals("RU") &&
                r.getFunction().getName().equals("Report")) {
                set.add(r);
            }
        }
        identity = new Identity();
        identity.setName("项目负责人");
        identity.setRoles(set);
        sess.save(identity);

        trans.commit();
    }

    public void createData_Example() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();
        Example exp = new Example();
        exp.setName("firstname");
        sess.save(exp);
        trans.commit();
    }

    public void createData_Operate() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Operate op = new Operate();
        op.setName("CRUD");
        sess.save(op);
        op = new Operate();
        op.setName("CRU");
        sess.save(op);
        op = new Operate();
        op.setName("RU");
        sess.save(op);
        op = new Operate();
        op.setName("R");
        sess.save(op);
        op = new Operate();
        op.setName("D");
        sess.save(op);

        trans.commit();
    }

    public void createData_Function() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Function f = new Function();
        f.setName("Personal");
        sess.save(f);
        f = new Function();
        f.setName("User");
        sess.save(f);
        f = new Function();
        f.setName("Topic");
        sess.save(f);
        f = new Function();
        f.setName("Reply");
        sess.save(f);
        f = new Function();
        f.setName("Mission");
        sess.save(f);
        f = new Function();
        f.setName("Project");
        sess.save(f);
        f = new Function();
        f.setName("File");
        sess.save(f);
        f = new Function();
        f.setName("Group");
        sess.save(f);
        f = new Function();
        f.setName("Report");
        sess.save(f);

        trans.commit();
    }

    public void createData_Role() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Role r = null;
        List<Operate> o = sess.createQuery("from Operate").list();
        List<Function> f = sess.createQuery("from Function").list();
        int i = 0;
        for (Operate oTemp : o) {
            for (Function fTemp : f) {
                i++;
                r = new Role();
                r.setFunction(fTemp);
                r.setOperate(oTemp);

                if (oTemp.getName().contains("R") && fTemp.getName().equals("Mission")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(我的任务)");
                } else if (oTemp.getName().contains("R") &&
                    oTemp.getName().contains("U") &&
                    fTemp.getName().equals("File")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(我的文件)");
                } else if (oTemp.getName().contains("R") &&
                    oTemp.getName().contains("U") &&
                    fTemp.getName().equals("Report")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(报表统计)");
                } else if (oTemp.getName().contains("C") &&
                    oTemp.getName().contains("D") &&
                    fTemp.getName().equals("User")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(会员管理)");
                } else if (oTemp.getName().contains("C") &&
                    oTemp.getName().contains("D") &&
                    fTemp.getName().equals("Group")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(用户组管理)");
                } else if (oTemp.getName().contains("C") &&
                    oTemp.getName().contains("D") &&
                    fTemp.getName().equals("File")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(文件管理)");
                } else if (oTemp.getName().contains("C") &&
                    oTemp.getName().contains("D") &&
                    fTemp.getName().equals("Project")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(项目管理)");
                } else if (oTemp.getName().contains("U") &&
                    fTemp.getName().equals("Project")) {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName() + "(所属项目管理)");
                } else {
                    r.setName("权限" + i + ": " + fTemp.getName() + " " + oTemp.getName());
                }

                sess.save(r);
            }
        }

        trans.commit();
    }

    public void createData_Group() {
        Session sess = sf.getCurrentSession();
        Transaction trans = sess.beginTransaction();

        Group g = new Group();
        g.setName("技术顾问");
        List<Role> roles = sess.createQuery("from Role where id=11 or id=12").list();
        g.setRoles(new HashSet<Role>(roles));
        sess.save(g);

        g = new Group();
        g.setName("访客");
        roles = sess.createQuery("from Role where id=12 or id=13").list();//赋予发帖权限(站务服务区)
        g.setRoles(new HashSet<Role>(roles));
        sess.save(g);

        trans.commit();
    }
}