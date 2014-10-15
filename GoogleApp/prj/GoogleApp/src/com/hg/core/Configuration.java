package com.hg.core;

import org.springframework.beans.factory.annotation.Autowired;

import com.hg.constant.TypeConstant;
import com.hg.dao.TypeDao;
import com.hg.pojo.Type;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private TypeDao typeDao;

    public void config() {
        long cnt = typeDao.count();
        if (cnt == 0) {
            Type t = new Type(TypeConstant.DEFAULT);
            typeDao.insert(t);
        }
    }
}
