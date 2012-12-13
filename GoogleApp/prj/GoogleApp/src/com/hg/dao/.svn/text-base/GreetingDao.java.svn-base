package com.hg.dao;

import java.util.List;

import com.hg.core.dao.BaseDao;
import com.hg.pojo.Greeting;

public interface GreetingDao extends BaseDao<Greeting, String> {

    List<Greeting> findAll(int start, int length);

    Greeting insert(Greeting g);

    void deleteById(String id);
}
