package com.hg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.constant.TypeConstant;
import com.hg.core.dao.BaseDao;
import com.hg.core.service.BaseServiceImpl;
import com.hg.dao.ArticleDao;
import com.hg.dao.TypeDao;
import com.hg.dto.TypeInfo;
import com.hg.pojo.Article;
import com.hg.pojo.Type;
import com.hg.service.TypeService;

@Service("TypeService")
public class TypeServiceImpl extends BaseServiceImpl<Type, String> implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private ArticleDao articleDao;

    @Override
    protected BaseDao<Type, String> getDao() {
        return typeDao;
    }

    @Override
    public boolean updateType(final TypeInfo info) {
        final Type type = get(info.getId());
        final List<Article> arts = articleDao.findAllByType(type.getName());
        for (final Article a : arts) {
            a.setType(info.getName());
        }
        type.setName(info.getName());
        save(type);
        return true;
    }

    @Override
    public List<TypeInfo> getTypes() {
        return getTypes(true);
    }

    @Override
    public List<TypeInfo> getTypes(final boolean containsDefault) {
        final List<Type> types = typeDao.findAll();
        final List<TypeInfo> vos = new ArrayList<TypeInfo>(types.size());
        for (final Type t : types) {
            if (!containsDefault && TypeConstant.DEFAULT.equals(t.getName())) {
                continue;
            }
            final TypeInfo vo = new TypeInfo();
            BeanUtils.copyProperties(t, vo);
            vo.setCount(t.getCountArticle());
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public boolean postType(final TypeInfo info) {
        final Type t = new Type(info.getName());
        return typeDao.insert(t) != null;
    }

    @Override
    public boolean removeType(final String typeId) {
        typeDao.deleteById(typeId);
        return true;
    }

}
