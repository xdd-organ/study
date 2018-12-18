package com.java.fanke.miniprogram.article.mapper;

import java.util.List;
import java.util.Map;

public interface ArticleCommentMapper {

    int insert(Map<String, Object> params);

    int update(Map<String, Object> params);

    List<Map<String, Object>> listByArticleComment(Map<String,Object> params);
}
