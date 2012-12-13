package com.hg.dao;

import java.util.List;

import com.hg.core.dao.BaseDao;
import com.hg.pojo.Comment;

public interface CommentDao extends BaseDao<Comment, String> {

    Comment insert(Comment g);

    List<Comment> findLatast(int size);

    List<Comment> findAll(int start, int length);
}
