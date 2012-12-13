/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.page.login2out;

import com.herograve.user.User;
import com.herograve.util.HibernateUtil;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Administrator
 */
public class CRUDfromDojo {
    
    private Session session;
    private User user;
    
    CRUDfromDojo() {
        //getInfo();
    }

    public User getUser() {
        return user;
    }
    
    public void getInfo(String user_name,String user_pwd){
        session = HibernateUtil.getSession();        
        
        user = (User)session.createCriteria(User.class).setFetchMode("userlevel", FetchMode.JOIN)
                .add(Restrictions.eq("name", user_name)).add(Restrictions.eq("pwd", user_pwd)).uniqueResult();
        
        session.close();
    }
    
    
    
    
    
    
    
    
    
}
