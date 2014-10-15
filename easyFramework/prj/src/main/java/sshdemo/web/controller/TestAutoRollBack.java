package sshdemo.web.controller;

import static org.junit.Assert.assertNull;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sshdemo.dao.ChildDao;
import sshdemo.dao.FatherDao;
import sshdemo.service.FatherAndChildService;
import sshdemo.service.ServiceA;

@Controller
public class TestAutoRollBack {

    @Autowired
    @Qualifier("FatherAndChildService")
    protected FatherAndChildService fatherAndChildService;
    @Autowired
    @Qualifier("ServiceA")
    protected ServiceA serviceA;
    @Autowired
    @Qualifier("ChildDao")
    protected ChildDao childDao;
    @Autowired
    @Qualifier("FatherDao")
    protected FatherDao fatherDao;

    @RequestMapping("/testAutoRollBack.action")
    public void hello(HttpServletResponse resp) {
        try {
            serviceA.testTransaction();
        } catch (Exception e) {
        }
        assertNull(childDao.findByName("ff"));
        try {
            fatherAndChildService.testTransaction();
        } catch (Exception e) {
        }
        assertNull(childDao.findByName("ff"));

        try {
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().write("test success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
