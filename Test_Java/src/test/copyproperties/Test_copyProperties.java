/*
 * To change this template, choose Tools | Templates and open the template in the editor.
 */
package test.copyproperties;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.cglib.beans.BeanCopier;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

/**
 *
 * @author Administrator
 */
public class Test_copyProperties {

    @Test
    public void copyProperties() {

        CopyFrom from1 = new CopyFrom();
        from1.setIntCopy(10);
        from1.setIntegerCopy(20);
        from1.setStringCopy("haha");

        System.out.println("Copy properties test: BeanUtils");
        CopyTo to1 = new CopyTo();
        try {
            /*
             * support type: java.lang.BigDecimal java.lang.BigInteger boolean and java.lang.Boolean byte and
             * java.lang.Byte char and java.lang.Character java.lang.Class double and java.lang.Double float and
             * java.lang.Float int and java.lang.Integer long and java.lang.Long short and java.lang.Short
             * java.lang.String java.sql.Date java.sql.Time java.sql.Timestamp
             */
            long beginTime = System.currentTimeMillis();
            BeanUtils.copyProperties(to1, from1);
            System.out.println("Cost time: " + (System.currentTimeMillis() - beginTime));
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Test_copyProperties.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Test_copyProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(to1.getIntCopy());
        System.out.println(to1.getIntegerCopy());
        System.out.println(to1.getStringCopy());
        System.out.println();

        System.out.println("Copy properties test: Cglib");
        CopyTo to2 = new CopyTo();
        long beginTime = System.currentTimeMillis();
        // 避免每次进行BeanCopier.create创建对象，一般建议是通过static BeanCopier copier = BeanCopier.create()
        BeanCopier copier = BeanCopier.create(CopyFrom.class, CopyTo.class, false);
        copier.copy(from1, to2, null);
        System.out.println("Cost time: " + (System.currentTimeMillis() - beginTime));
        System.out.println(to2.getIntCopy());
        System.out.println(to2.getIntegerCopy());
        System.out.println(to2.getStringCopy());
    }
}
