package com.hg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hg.constant.PagingConstant;
import com.hg.core.dao.BaseDao;
import com.hg.core.service.BaseServiceImpl;
import com.hg.dao.CommentDao;
import com.hg.dto.CommentInfo;
import com.hg.pojo.Comment;
import com.hg.service.CommentService;
import com.hg.util.DateUtil;
import com.hg.util.GaeUtil;
import com.hg.util.StringUtil;

@Service("CommentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    protected BaseDao<Comment, String> getDao() {
        return commentDao;
    }

    @Override
    public List<CommentInfo> getLatestCmts() {
        final List<Comment> comments = commentDao.findLatast(PagingConstant.LATEST_COMMENT);
        final List<CommentInfo> vos = new ArrayList<CommentInfo>(comments.size());
        for (final Comment cmt : comments) {
            final CommentInfo vo = new CommentInfo();
            BeanUtils.copyProperties(cmt, vo);
            vo.setName(cmt.getFounder().getName());
            vo.setArticleId(cmt.getArticle().getId());
            vo.setAlias(cmt.getArticle().getAlias());
            vo.setCreateTime(cmt.getArticle().getCreateTime());
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public int getMaxPage() {
        final int count = (int) commentDao.count();
        int maxP = count / PagingConstant.COMMENT_MANAGE;
        if (count % PagingConstant.COMMENT_MANAGE == 0) {
            maxP--;
        }
        return maxP;
    }

    @Override
    public List<CommentInfo> getList(final int pageNo) {
        final List<Comment> cmts = commentDao.findAll(pageNo * PagingConstant.COMMENT_MANAGE,
                                                      PagingConstant.COMMENT_MANAGE);
        final List<CommentInfo> vos = new ArrayList<CommentInfo>();
        for (final Comment c : cmts) {
            final CommentInfo vo = new CommentInfo();
            BeanUtils.copyProperties(c, vo);
            vo.setContent(StringUtil.escape(c.getContent()));
            vo.setArticleId(c.getArticle().getId());
            vo.setIp(c.getFounder().getIp());
            vo.setName(c.getFounder().getName());
            vo.setContact(StringUtil.getContactUrl(c.getFounder().getContact()));
            vo.setTime(DateUtil.format(c.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public Comment remove(final String id) {
        final Comment comment = get(id);
        remove(comment);
        return comment;
    }

    @Override
    public String genCommentHTML(final String articleId, final List<Comment> comments) {
        final StringBuilder sb = new StringBuilder("<h3 class=\"title\">");
        if (comments.isEmpty()) {
            sb.append("No Comment.</h3>");
            return sb.toString();
        }
        sb.append("Comments:</h3>");
        for (int i = 0; i < comments.size(); i++) {
            final Comment cmt = comments.get(i);
            sb.append("<a name=\"");
            sb.append(cmt.getId());
            sb.append("\"></a>");
            sb.append("<div class=\"reply\"><p class=\"byline\">");
            sb.append(i + 1 + "# ");
            sb.append(StringUtil.escape(cmt.getTitle()));
            sb.append(" ");
            sb.append(DateUtil.format(cmt.getCreateTime(), "yyyy-MM-dd HH:mm"));
            sb.append(" | ");
            if (GaeUtil.getCurrentUser() != null && !StringUtil.isEmpty(cmt.getFounder().getContact(), true)) {
                sb.append("<a target=\"_blank\" href=\"");
                sb.append(StringUtil.escape(StringUtil.getContactUrl(cmt.getFounder().getContact())));
                sb.append("\">");
                sb.append(StringUtil.escape(cmt.getFounder().getName()));
                sb.append("</a>");
            } else {
                sb.append(StringUtil.escape(cmt.getFounder().getName()));
            }
            sb.append("</p><p class=\"byline\">");
            sb.append(StringUtil.escape(cmt.getContent()));
            sb.append(" <a onclick=\"setReplyTitle('");
            sb.append(StringUtil.escape(cmt.getFounder().getName()));
            sb.append("')\">Reply</a></p> </div>");
        }
        return sb.toString();
    }
}
