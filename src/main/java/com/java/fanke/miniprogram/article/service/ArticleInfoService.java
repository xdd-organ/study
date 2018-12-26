package com.java.fanke.miniprogram.article.service;

import java.util.List;
import java.util.Map;

public interface ArticleInfoService {

    Map<String, Object> getByUserIdAndArticleId(String userId, String articleId);

    long insert(Map<String, Object> params);

    long insertInfo(String userId, String articleId);

    List<Map<String, Object>> listArticleInfoByParams(Object articleId, Object like, Object favorite);
}
