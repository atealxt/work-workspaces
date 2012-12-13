/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.herograve.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Administrator
 */
public class SpringUitl {    
    public static ApplicationContext getContext(String[] paths){
        return new ClassPathXmlApplicationContext(paths);
    }
}
