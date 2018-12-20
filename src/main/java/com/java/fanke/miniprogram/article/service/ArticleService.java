package com.java.fanke.miniprogram.article.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ArticleService {
    long insert(Map<String,Object> params);

    PageInfo pageByArticle(Map<String,Object> params);

    int update(Map<String,Object> params);

    int like(Map<String,Object> params);

    int favorite(Map<String,Object> params);

    int unLike(Map<String,Object> params);

    int unFavorite(Map<String,Object> params);

    long comment(Map<String, Object> params);

    int delComment(Map<String, Object> params);

    Map<String,Object> getByArticleId(Map<String,Object> params);
}
