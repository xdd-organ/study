package com.java.study.miniprogram.article.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ArticleInfoMapper {
    int insert(Map<String, Object> params);

    Map<String,Object> getByUserIdAndArticleId(@Param("user_id") String userId, @Param("article_id") String articleId);
}
