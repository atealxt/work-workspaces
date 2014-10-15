package com.hg.util;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.hg.dto.Mail;

import core.old.TestUtil;

public class MailUtilTest extends TestUtil {

    private static final String TARGET_CLASS_PATH = "com.hg.util.MailUtil";

    @DataProvider(name = "sendBaseMail")
    public Object[][] testSendBaseMailDp() throws Exception {
        Mail mail = new Mail();
        mail.setFrom("a@a.com");
        mail.setTo("atealxt@gmail.com");
        mail.setSubject("test!");
        mail.setText("hello world!");

        Object[][] testCase = new Object[1][1];
        testCase[0][0] = mail;
        return testCase;
    }

    @SuppressWarnings("unchecked")
    @Test(dataProvider = "sendBaseMail", groups = "sendBaseMail")
    public void testSendBaseMail(Mail mail) throws Exception {
        Class[] param = { Mail.class };
        Method method = getMethod(TARGET_CLASS_PATH, "sendMail", param);
        method.setAccessible(true);

        Assert.assertTrue(Boolean.class.cast(MailUtil.sendMail(mail)));
    }
}
