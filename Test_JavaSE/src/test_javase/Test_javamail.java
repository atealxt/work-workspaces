package test_javase;

import java.io.File;  
import java.util.Properties;  
  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeUtility; 

import org.springframework.mail.MailSendException;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;  
import org.springframework.mail.javamail.MimeMessageHelper; 

public class Test_javamail {
    
    public boolean sendTest(){
        try{
            JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
            javaMail.setHost("smtp.gmail.com");//set send host
            javaMail.setUsername("atealxt");  
            javaMail.setPassword("*******");   
            javaMail.setPort(587);//gmail's port

            Properties prop = new Properties();  
            prop.setProperty("mail.smtp.auth", "true"); //set validator  
            prop.put("mail.smtp.starttls.enable","true");   //set ssl if the mail need.for example gmail
            prop.put("mail.smtp.timeout","20000");
            javaMail.setJavaMailProperties(prop);  
            
            MimeMessage message = javaMail.createMimeMessage();  
            MimeMessageHelper messageHelp = new MimeMessageHelper(message,true,"GBK");
            
            messageHelp.setFrom("atealxt@gmail.com");  
            messageHelp.setTo("atealxt@gmail.com");  
            messageHelp.setSubject("这是标题"); 
            String body = "<html><head><title>test</title></head><body>这是内容</body></html>";
            messageHelp.setText(body, true);
            
            //messageHelp.addInline("a", new File("D:/a.txt"));  
            File file=new File("D:/测试.txt");
            messageHelp.addAttachment(MimeUtility.encodeWord(file.getName()), file);
            javaMail.send(message); 
            
            /* the test SimpleMailMessage is fail..
            SimpleMailMessage message2 = new SimpleMailMessage();
            message2.setFrom("atealxt@gmail.com");
            message2.setReplyTo("atealxt@gmail.com");
            message2.setSubject("这是标题");
            message2.setText(body);
            javaMail.send(message2);
             * */
            
        }
        catch(MailSendException ex){
            ex.printStackTrace();
            return false;
        }        
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        
        System.out.println("Send completed!");
        return true;
    }

}