package com.java.fanke.miniprogram.article.mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    int insert(Map<String, Object> params);

    List<Map<String, Object>> listByArticle(Map<String,Object> params);

    int update(Map<String,Object> params);

    int star(Map<String,Object> params);

    int collect(Map<String,Object> params);

    int unStar(Map<String,Object> params);

    int unCollect(Map<String,Object> params);

    int comment(Map<String,Object> params);

    int unComment(Map<String,Object> params);
}
