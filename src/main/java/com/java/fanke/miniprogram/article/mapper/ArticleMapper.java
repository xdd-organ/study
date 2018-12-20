package com.java.fanke.miniprogram.article.mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    int insert(Map<String, Object> params);

    List<Map<String, Object>> listByArticle(Map<String,Object> params);

    int update(Map<String,Object> params);

    int like(Map<String,Object> params);

    int favorite(Map<String,Object> params);

    int unLike(Map<String,Object> params);

    int unFavorite(Map<String,Object> params);

    int comment(Map<String,Object> params);

    int delComment(Map<String,Object> params);

    Map<String,Object> getByArticleId(Map<String,Object> params);
}
