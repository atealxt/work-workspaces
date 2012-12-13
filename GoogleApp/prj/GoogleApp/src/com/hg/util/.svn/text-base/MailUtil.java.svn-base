package com.hg.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.hg.dto.Mail;

public class MailUtil {

    private static Log logger = LogFactory.getLog(MailUtil.class);

    private MailUtil() {}

    public static boolean sendMail(final Mail mail) {
        if (mail == null) {
            throw new IllegalArgumentException();
        }

        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
//            logger.info("Mail sending from: " + mail.getFrom() + " to: " + mail.getTo());
            msg.setFrom(new InternetAddress(mail.getFrom()));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(mail.getTo()));
            msg.setSubject(mail.getSubject());

            if (mail.isMultiPart()) {
                setContentMultiPart(msg, mail);
            } else {
                setContentBase(msg, mail);
            }
            Transport.send(msg);
        } catch (AddressException e) {
            logger.error(e.getMessage(), e);
            return false;
        } catch (MessagingException e) {
            logger.error(e.getMessage(), e);
            return false;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

    private static void setContentBase(final Message msg, final Mail mail) throws MessagingException {
        msg.setText(mail.getText());
    }

    private static void setContentMultiPart(final Message msg, final Mail mail) throws MessagingException {
        Multipart mp = new MimeMultipart();
        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(mail.getText(), "text/html");
        mp.addBodyPart(htmlPart);
        msg.setContent(mp);
    }
}
