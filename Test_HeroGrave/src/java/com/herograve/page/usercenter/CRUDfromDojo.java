/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.usercenter;

import com.herograve.user.User;
import com.herograve.user.Userinfo;
import com.herograve.util.ContentUtil;
import com.herograve.util.HibernateUtil;
import com.herograve.util.HttpSessionUtil;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class CRUDfromDojo {
    
    private Session session;
    private HttpServletRequest request;
    private User user;
    private Userinfo userinfo;
    
    CRUDfromDojo(HttpServletRequest arg0) {
        this.request = arg0;
        this.user = (User)request.getSession().getAttribute(HttpSessionUtil.USER_KEY);
    }

    public Userinfo getUserinfo() {
        return userinfo;
    }
    
    public User getUser() {
        return user;
    }        
    
    public void getInfo(){
        session = HibernateUtil.getSession();
        
        userinfo = (Userinfo)session.createCriteria(Userinfo.class).setFetchMode("userlevelshow", FetchMode.JOIN)
                .add(Restrictions.eq("id", user.getId())).uniqueResult();

        session.close();
    }

    public void updateInfo(){
        session = HibernateUtil.getCurrentSession();
        Transaction tran = session.beginTransaction();
        
        User userForm = (User)request.getSession().getAttribute(HttpSessionUtil.USER_KEY);
        User userDB = (User)session.createCriteria(User.class).add(Restrictions.eq("id", userForm.getId())).uniqueResult();
        Userinfo userinfoDB = (Userinfo)session.createCriteria(Userinfo.class).
                add(Restrictions.eq("id", userDB.getId())).uniqueResult();        
        
        String hiddenIMG = request.getParameter("hiddenIMG");
        if(hiddenIMG != null && !"".equals(hiddenIMG)){
            hiddenIMG = hiddenIMG.substring(hiddenIMG.indexOf("picture"));
            userinfoDB.setIconpath(hiddenIMG);
        }
        
        String sexsel = request.getParameter("sexsel");
        if(sexsel!=null && !"".equals(sexsel)){
            userinfoDB.setSex(Integer.valueOf(sexsel));
        }
        
        String txtEmail = request.getParameter("txtEmail");
        if(txtEmail!=null && !"".equals(txtEmail)){
            userDB.setEmail(txtEmail);
            userForm.setEmail(txtEmail);
        }

        String pswRep = request.getParameter("pswRep");
        if(pswRep!=null && !"".equals(pswRep)){
            String md5 = ContentUtil.EncoderByMd5(pswRep);
            userDB.setPwd(md5);
            userForm.setPwd(md5);
        }

        tran.commit();       
        request.getSession().setAttribute(HttpSessionUtil.USER_KEY, userForm);
    }
    
    
    
}
