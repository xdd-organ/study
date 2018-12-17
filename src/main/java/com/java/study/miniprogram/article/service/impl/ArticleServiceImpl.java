package com.java.study.miniprogram.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.study.miniprogram.article.mapper.ArticleMapper;
import com.java.study.miniprogram.article.service.ArticleInfoService;
import com.java.study.miniprogram.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleInfoService articleInfoService;

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
    public int star(Map<String, Object> params) {
        String userId = String.valueOf(params.get("user_id"));
        String articleId = String.valueOf(params.get("article_id"));
        long id = articleInfoService.insertInfo(userId, articleId);
        params.put("article_info_id", id);
        return articleMapper.star(params);
    }

    @Override
    public int watch(Map<String, Object> params) {
        String userId = String.valueOf(params.get("user_id"));
        String articleId = String.valueOf(params.get("article_id"));
        long id = articleInfoService.insertInfo(userId, articleId);
        params.put("article_info_id", id);
        return articleMapper.watch(params);
    }

    @Override
    public int unStar(Map<String, Object> params) {
        return articleMapper.unStar(params);
    }

    @Override
    public int unWatch(Map<String, Object> params) {
        return articleMapper.unWatch(params);
    }
}
