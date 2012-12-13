package com.hg.dao;

import java.util.List;

import com.hg.core.dao.BaseDao;
import com.hg.pojo.Type;

public interface TypeDao extends BaseDao<Type, String> {

    Type insert(Type t);

    Type findByName(String name);

    List<Type> findAll();

    void deleteById(String id);
}
