package com.java.fanke.miniprogram.institute.mapper;

import java.util.List;
import java.util.Map;

public interface InstituteMapper {
    int insert(Map<String, Object> params);

    List<Map<String, Object>> listByInstitute(Map<String, Object> params);

    Map<String,Object> getByInstitute(Map<String,Object> params);

    int update(Map<String,Object> params);
}
