package com.java.fanke.miniprogram.article.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ArticleService {
    long insert(Map<String,Object> params);

    PageInfo pageByArticle(Map<String,Object> params);

    int update(Map<String,Object> params);

    int star(Map<String,Object> params);

    int collect(Map<String,Object> params);

    int unStar(Map<String,Object> params);

    int unCollect(Map<String,Object> params);

    long comment(Map<String, Object> params);

    int delComment(Map<String, Object> params);
}
