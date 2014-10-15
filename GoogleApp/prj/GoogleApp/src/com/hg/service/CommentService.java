package com.hg.service;

import java.util.List;

import com.hg.dto.CommentInfo;
import com.hg.pojo.Comment;

public interface CommentService {

    List<CommentInfo> getLatestCmts();

    String genCommentHTML(String articleId, List<Comment> comments);

    int getMaxPage();

    List<CommentInfo> getList(int pageNo);

    Comment remove(String id);
}
