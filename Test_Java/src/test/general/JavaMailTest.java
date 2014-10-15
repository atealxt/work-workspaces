package test.general;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {

    public static void main(final String args[]) throws Exception {

        final String host = "smtp.126.com";

        final String to = "yangyupeng521@sina.com";

        final String subject = "title:test";
        final String messageText = "contents";

        final String from = "lu2yang@126.com";
        final String m_user = "lu2yang";
        final String m_pass = "lu2yanglu2yang";

        final boolean sessionDebug = false;

        // Create some properties and get the default Session.
        final Properties props = System.getProperties();
        props.put("mail.host", host);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");

        final Session mailSession = Session.getDefaultInstance(props, null);

        // Set debug on the Session so we can see what is going on
        // Passing false will not echo debug info, and passing true
        // will.
        mailSession.setDebug(sessionDebug);

        // Instantiate a new MimeMessage and fill it with the
        // required information.
        final Message msg = new MimeMessage(mailSession);

        msg.setFrom(new InternetAddress(from));
        final InternetAddress[] address = { new InternetAddress(to) };
        msg.setRecipients(Message.RecipientType.TO, address);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(messageText);

        // Hand the message to the default transport service
        // for delivery.

        // Transport.send(msg);
        final Transport trans = mailSession.getTransport();
        trans.connect(host, m_user, m_pass);
        trans.sendMessage(msg, InternetAddress.parse(to));
        trans.close();

        System.out.println("send end!");

    }
}
