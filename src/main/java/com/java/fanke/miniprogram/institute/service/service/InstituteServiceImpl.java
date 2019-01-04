package com.java.fanke.miniprogram.institute.service.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.institute.mapper.InstituteMapper;
import com.java.fanke.miniprogram.institute.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteMapper instituteMapper;

    @Override
    public Map<String, Object> insert(Map<String, Object> params) {
        instituteMapper.insert(params);
        return params;
    }

    @Override
    public PageInfo pageByInstitute(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        return new PageInfo(instituteMapper.listByInstitute(params));
    }

    @Override
    public Map<String, Object> getByInstitute(Map<String, Object> params) {
        return instituteMapper.getByInstitute(params);
    }
}
