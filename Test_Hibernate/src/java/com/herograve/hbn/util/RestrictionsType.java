package com.herograve.hbn.util;

/**
 * @author Atea
 * @see http://www.***.net/
 */
public enum RestrictionsType {

    /**
     * Restrictions.allEq(Map propertyNameValues)
     * <br/><br/>Apply an "equals" constraint to each property in the key set of a Map
     */
    allEq,
    /**
     * Restrictions.and(Criterion lhs, Criterion rhs)
     * <br/><br/>Return the conjuction of two expressions
     */
    and,    
    /**
     * Restrictions.between(String propertyName, Object lo, Object hi)
     * <br/><br/>Apply a "between" constraint to the named property 
     */
    between,
    /**
     * Restrictions.eq(String propertyName,Object value)
     * <br/><br/>Apply an "equal" constraint to the named property
     */
    eq,
    /**
     * Restrictions.ge(String propertyName,Object value)
     * <br/><br/>Apply a "greater than or equal" constraint to the named property 
     */
    ge,
    /**
     * Restrictions.gt(String propertyName,Object value)
     * <br/><br/>Apply a "greater than" constraint to the named property
     */
    gt,
    /**
     * Restrictions.idEq(Object value) 
     * <br/><br/>Apply an "equal" constraint to the identifier property
     */
    idEq,
    /**
     * Restrictions.ilike(String propertyName,Object value)
     * <br/>or
     * <br/>Restrictions.ilike(String propertyName,Object value,MatchMode matchMode)
     * <br/><br/>A case-insensitive "like", similar to Postgres ilike operator
     */
    ilike,    
    /**
     * Restrictions.le(String propertyName,Object value)
     * <br/><br/>Apply a "less than or equal" constraint to the named property 
     */
    le,
    /**
     * Restrictions.like(String propertyName,Object value)
     * <br/>or
     * <br/>Restrictions.like(String propertyName,Object value,MatchMode matchMode)
     * <br/><br/>Apply a "like" constraint to the named property 
     */
    like,
}
