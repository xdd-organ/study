package com.java.fanke.miniprogram.article.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ArticleInfoMapper {
    int insert(Map<String, Object> params);

    Map<String,Object> getByUserIdAndArticleId(@Param("user_id") String userId, @Param("article_id") String articleId);

    List<Map<String,Object>> listArticleInfoByParams(@Param("article_id") Object articleId, @Param("like") Object like, @Param("favorite") Object favorite);
}
