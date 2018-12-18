package com.java.fanke.miniprogram.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.article.mapper.ArticleMapper;
import com.java.fanke.miniprogram.article.service.ArticleCommentService;
import com.java.fanke.miniprogram.article.service.ArticleInfoService;
import com.java.fanke.miniprogram.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public int update(Map<String, Object> params) {
        return articleMapper.update(params);
    }

    @Override
    public int like(Map<String, Object> params) {
        String userId = String.valueOf(params.get("user_id"));
        String articleId = String.valueOf(params.get("article_id"));
        long id = articleInfoService.insertInfo(userId, articleId);
        params.put("article_info_id", id);
        return articleMapper.like(params);
    }

    @Override
    public int favorite(Map<String, Object> params) {
        String userId = String.valueOf(params.get("user_id"));
        String articleId = String.valueOf(params.get("article_id"));
        long id = articleInfoService.insertInfo(userId, articleId);
        params.put("article_info_id", id);
        return articleMapper.favorite(params);
    }

    @Override
    public int unLike(Map<String, Object> params) {
        return articleMapper.unLike(params);
    }

    @Override
    public int unFavorite(Map<String, Object> params) {
        return articleMapper.unFavorite(params);
    }

    @Override
    public long comment(Map<String, Object> params) {
        return 0;
    }

    @Override
    public int delComment(Map<String, Object> params) {
        return 0;
    }
}
