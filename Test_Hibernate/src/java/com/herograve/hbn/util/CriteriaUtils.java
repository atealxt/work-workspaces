package com.herograve.hbn.util;

import java.util.Map;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * @author Atea
 * @see http://www.***.net/
 */
public class CriteriaUtils {

    /**<h4>Use example:</h4>
     * <code>
     * Map&lt;RestrictionsType, Object[]&gt; restrictions = new HashMap&lt;RestrictionsType, Object[]&gt;();<br/>
     * restrictions.put(RestrictionsType.eq, new String[]{"name", "Luis"});<br/>
     * CriteriaUtils.getDetachedCriteria("com.herograve.POJONAME", restrictions);<br/>
     * </code>
     * @param CLASSNAME pojo class name
     * @param restrictions Map&lt;RestrictionsType, Object[]&gt;<br/>
     * <ul>
     * <li>RestrictionsType - Restrictions method name</li>
     * <li>Object[] - Restrictions method parameters</li>
     * </ul>
     * @return org.hibernate.criterion.DetachedCriteria
     * @exception ClassNotFoundException
     */
    public static DetachedCriteria getDetachedCriteria(String CLASSNAME,
        Map<RestrictionsType, Object[]> restrictions)
        throws ClassNotFoundException {
        DetachedCriteria dCriteria =
            DetachedCriteria.forClass(Thread.currentThread().getContextClassLoader().loadClass(CLASSNAME));
        for (RestrictionsType key : restrictions.keySet()) {
            Object[] objArr = restrictions.get(key);
            switch (key) {
                case allEq:
                    dCriteria.add(Restrictions.allEq((Map) objArr[0]));
                    break;
                case between:
                    dCriteria.add(Restrictions.between((String) objArr[0], objArr[1], objArr[2]));
                    break;
                case eq:
                    dCriteria.add(Restrictions.eq((String) objArr[0], objArr[1]));
                    break;
                case ge:
                    dCriteria.add(Restrictions.ge((String) objArr[0], objArr[1]));
                    break;
                case gt:
                    dCriteria.add(Restrictions.gt((String) objArr[0], objArr[1]));
                    break;
                case idEq:
                    dCriteria.add(Restrictions.idEq(objArr[0]));
                    break;
                case ilike:
                    if (objArr.length == 3) {
                        dCriteria.add(Restrictions.ilike((String) objArr[0], (String) objArr[1], (MatchMode) objArr[2]));
                    } else {
                        dCriteria.add(Restrictions.ilike((String) objArr[0], objArr[1]));
                    }
                    break;                    
                case le:
                    dCriteria.add(Restrictions.le((String) objArr[0], objArr[1]));
                    break;
                case like:
                    if (objArr.length == 3) {
                        dCriteria.add(Restrictions.like((String) objArr[0], (String) objArr[1], (MatchMode) objArr[2]));
                    } else {
                        dCriteria.add(Restrictions.like((String) objArr[0], objArr[1]));
                    }
                    break;
                default:
                    break;
            }
        }
        return dCriteria;
    }
}
