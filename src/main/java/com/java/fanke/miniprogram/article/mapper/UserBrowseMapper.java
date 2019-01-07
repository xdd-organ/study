package com.java.fanke.miniprogram.article.mapper;

import java.util.List;
import java.util.Map;

public interface UserBrowseMapper {

    int insert(Map<String, Object> params);

    List<Map<String, Object>> listByArticle(Map<String, Object> params);

}
