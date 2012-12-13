/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.util;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 *
 * @author Administrator
 */
public class ContentUtil {

    //only number and english words. input length:6-16
    private final static String pswPattern = "[0-9a-zA-Z]{6,16}";        
    
    /**
     * filter text for web page show.
     */
    public static String pagefilter(String content){
        //String p = System.getProperty("line.separator") ; //\r\n in windows jp

        content = content.replaceAll("&","&amp;");        
        content = content.replaceAll("<","&lt;");
        content = content.replaceAll(">","&gt;");        
        content = content.replaceAll("\n","<br/>");
        content = content.replaceAll(" ","&nbsp;");
        
        
        return content;
    }
    
    /**
     * get the MD5 code
     */
    public static String EncoderByMd5(String source){
        String sCode = null;
        try{
            MessageDigest md5=MessageDigest.getInstance("MD5");          
            sCode = Base64.encode(md5.digest(source.getBytes("UTF-8")));  
        }
        catch(UnsupportedEncodingException e1){}
        catch(NoSuchAlgorithmException e2){}
        
        return sCode;
    }
    
    public static boolean isPswInputStyleOk(String source){
        
        return Pattern.matches(pswPattern, source);
    }
    
    
    
    
    
    
    
    
}
