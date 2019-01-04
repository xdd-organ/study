package com.java.fanke.miniprogram.institute.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;
public interface InstituteService {

    Map<String,Object> insert(Map<String, Object> params);

    PageInfo pageByInstitute(Map<String, Object> params);

    Map<String,Object> getByInstitute(Map<String,Object> params);
}
