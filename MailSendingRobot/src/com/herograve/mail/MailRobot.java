package com.herograve.mail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Properties;  
  
import javax.mail.internet.MimeMessage;  

import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;  
import org.springframework.mail.javamail.MimeMessageHelper; 

/**
 *
 * @author Atea
 * @param MAIL_TO The address email to.
 */
public class MailRobot {
    
    private final String HOST = "smtp.gmail.com";
    private final String USERNAME = "atealxt";
    private final String PASSWORD = "Atealxt82616104";
    private final int PORT = 587;
    
    private final String MAIL_FROM = "atealxt@gmail.com";
    private final String MAIL_TO;
    private final String SUBJECT = "您好，卢梭(LUSUO)应聘Java工程师。";
    private final String BODY_FILE = "BODY.html";

    public MailRobot(String MAIL_TO) {
        this.MAIL_TO = MAIL_TO;
    }        

    /**
     * Sending mail.
     */
    public boolean sending(){
        System.out.println("Mail init...");
        
        try{
            JavaMailSenderImpl javaMail = new JavaMailSenderImpl();
            javaMail.setHost(HOST);
            javaMail.setUsername(USERNAME);  
            javaMail.setPassword(PASSWORD);   
            javaMail.setPort(PORT);

            Properties prop = new Properties();  
            prop.setProperty("mail.smtp.auth", "true"); //set validator  
            prop.put("mail.smtp.starttls.enable","true");   //set ssl if the mail need.for example gmail
            prop.put("mail.smtp.timeout","20000");
            javaMail.setJavaMailProperties(prop);  
            
            MimeMessage message = javaMail.createMimeMessage();  
            MimeMessageHelper messageHelp = new MimeMessageHelper(message,true,"GBK");//UTF-8 GB2312            
            messageHelp.setFrom(MAIL_FROM);  
            messageHelp.setTo(MAIL_TO);  
            messageHelp.setSubject(SUBJECT); 
            messageHelp.setText(getMailBody(), true);            

            System.out.println("Mail sending...");
            javaMail.send(message);             
        }
        catch(MailSendException ex){
            ex.printStackTrace();
            return false;
        }        
        catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
        
        System.out.println("Mail sent seccess: " + this.MAIL_TO);
        return true;
    }
    
    private String getMailBody() throws Exception{        
        StringBuilder body = new StringBuilder();        

        //File f = new File(Thread.currentThread().getContextClassLoader().getResource(BODY_FILE).toURI());
        File f = new File(BODY_FILE);
        if(!f.exists()){
            throw new FileNotFoundException(BODY_FILE);
        }
//        long fileLength = f.length();
//        MappedByteBuffer fileMapped = new RandomAccessFile(f, "r").getChannel()
//                .map(FileChannel.MapMode.READ_ONLY, 0, fileLength);
//        for(int i = 0; i < fileLength; i++){
//            body.append((char)fileMapped.get(i));
//        }
//        
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "GB2312"));
        String strTemp = br.readLine();
        while(strTemp!=null){
            if(strTemp.trim().startsWith("<")){
                body.append(strTemp);
            }
            else{
                body.append(strTemp + "<br/>");
            }
            
            strTemp = br.readLine();
        }
        br.close();
        
        String strRet = body.toString();
        //strRet = new String(strRet.getBytes("UTF-8"),"GB2312");
        //System.out.println(strRet);
        
//        File fff= new File("D:/Work/NetBeans 6.1/TEMP/MailSendingRobot/dist/aaa.html");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fff));
//        bw.write(strRet);
//        bw.close();
        
        return strRet;
    }
    
    
    
    
    
    
}
