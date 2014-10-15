package com.hg.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.hg.dto.CommentInfo;
import com.hg.pojo.Comment;
import com.hg.util.CacheUtil;

@Aspect
@Component
public class CommentAspect {

    private static Log logger = LogFactory.getLog(CommentAspect.class);

    @Around("execution(* com.hg.service.CommentService.genCommentHTML(..))")
    public Object genCommentHTML(final ProceedingJoinPoint point) throws Throwable {
        final String articleId = (String) point.getArgs()[0];
        final String key = genKey(articleId);
        final Object value = CacheUtil.get(key);
        if (value != null) {
            return value;
        }
        final Object ret = point.proceed(point.getArgs());
        CacheUtil.put(key, ret);
        return ret;
    }

    @Around("execution(* com.hg.service.ArticleService.addComment(..))")
    public Object addComment(final ProceedingJoinPoint point) throws Throwable {
        final Object ret = point.proceed(point.getArgs());
        final CommentInfo comment = (CommentInfo) point.getArgs()[0];
        final String key = genKey(comment.getArticleId());
        CacheUtil.remove(key);
        return ret;
    }

    //注意不要多次在底层拦截相同的AOP，多次的话需要重构成一个。另：TODO 参数泛型的情况无法指定具体类型，待研究
    @Around("execution(* com.hg.core.dao.BaseDao.delete(..))")
    public Object deleteById(final ProceedingJoinPoint point) throws Throwable {
        final Object ret = point.proceed(point.getArgs());
        if (!(point.getArgs()[0] instanceof Comment)) {
            return ret;
        }
        final Comment comment = (Comment) point.getArgs()[0];
        final String key = genKey(comment.getArticle().getId());
        final boolean b = CacheUtil.remove(key);
        logger.info(b);
        return ret;
    }

    // 此方法在service层，也可以实现注销缓存，但无法接收到其他函数delete comment的事件。
    // @Around("execution(* com.hg.service.CommentService.remove(..))")
    // public Object remove(final ProceedingJoinPoint point) throws Throwable {
    // final Object ret = point.proceed(point.getArgs());
    // final Comment comment = (Comment) ret;
    // final String key = genKey(comment.getArticle().getId());
    // final boolean b = CacheUtil.remove(key);
    // logger.info(b);
    // logger.info(key);
    // return ret;
    // }

    private String genKey(final String articleId) {
        return CommentAspect.class.getSimpleName() + " " + articleId;
    }
}
