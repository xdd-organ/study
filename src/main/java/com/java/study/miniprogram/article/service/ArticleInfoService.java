package com.java.study.miniprogram.article.service;

import java.util.Map;

public interface ArticleInfoService {

    Map<String, Object> getByUserIdAndArticleId(String userId, String articleId);

    long insert(Map<String, Object> params);

    long insertInfo(String userId, String articleId);

}
