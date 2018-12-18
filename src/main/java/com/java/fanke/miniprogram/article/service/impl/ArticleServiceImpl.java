package com.java.fanke.miniprogram.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.article.mapper.ArticleMapper;
import com.java.fanke.miniprogram.article.service.ArticleCommentService;
import com.java.fanke.miniprogram.article.service.ArticleInfoService;
import com.java.fanke.miniprogram.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleInfoService articleInfoService;
    @Autowired
    private ArticleCommentService articleCommentService;

    @Override
    public long insert(Map<String, Object> params) {
        int res = articleMapper.insert(params);
        return Long.valueOf(params.get("id").toString());
    }

    @Override
    public PageInfo pageByArticle(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        return new PageInfo(articleMapper.listByArticle(params));
    }

    @Transactional
    @Override
    public int update(Map<String, Object> params) {
        return articleMapper.update(params);
    }

    @Transactional
    @Override
    public int like(Map<String, Object> params) {
        String userId = String.valueOf(params.get("user_id"));
        String articleId = String.valueOf(params.get("article_id"));
        long id = articleInfoService.insertInfo(userId, articleId);
        params.put("article_info_id", id);
        return articleMapper.like(params);
    }

    @Transactional
    @Override
    public int favorite(Map<String, Object> params) {
        String userId = String.valueOf(params.get("user_id"));
        String articleId = String.valueOf(params.get("article_id"));
        long id = articleInfoService.insertInfo(userId, articleId);
        params.put("article_info_id", id);
        return articleMapper.favorite(params);
    }

    @Transactional
    @Override
    public int unLike(Map<String, Object> params) {
        return articleMapper.unLike(params);
    }

    @Transactional
    @Override
    public int unFavorite(Map<String, Object> params) {
        return articleMapper.unFavorite(params);
    }

    @Transactional
    @Override
    public long comment(Map<String, Object> params) {
        articleCommentService.insert(params);
        return articleMapper.comment(params);
    }

    @Transactional
    @Override
    public int delComment(Map<String, Object> params) {
        Map<String, Object> commentParams = new HashMap<>();
        commentParams.put("id", params.get("id"));
        commentParams.put("state", "1");
        articleCommentService.update(commentParams);
        return articleMapper.delComment(params);
    }
}
