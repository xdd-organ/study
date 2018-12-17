package com.java.study.miniprogram.article.mapper;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    int insert(Map<String, Object> params);

    List<Map<String, Object>> listByArticle(Map<String,Object> params);

    int update(Map<String,Object> params);

    int star(Map<String,Object> params);

    int watch(Map<String,Object> params);

    int unStar(Map<String,Object> params);

    int unWatch(Map<String,Object> params);

    int comment(Map<String,Object> params);

    int unComment(Map<String,Object> params);
}
