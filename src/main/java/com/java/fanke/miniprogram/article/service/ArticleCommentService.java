package com.java.fanke.miniprogram.article.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface ArticleCommentService {

    long insert(Map<String,Object> params);

    int update(Map<String,Object> params);

    PageInfo pageByArticleComment(Map<String,Object> params);
}
