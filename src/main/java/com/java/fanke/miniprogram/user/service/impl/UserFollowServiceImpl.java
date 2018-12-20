package com.java.fanke.miniprogram.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.user.mapper.UserFollowMapper;
import com.java.fanke.miniprogram.user.service.UserFollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserFollowServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowMapper userFollowMapper;

    @Override
    public int unFollow(Map<String, Object> params) {
        return userFollowMapper.unFollow(params);
    }

    @Override
    public long insert(Map<String, Object> params) {
        return userFollowMapper.insert(params);
    }

    @Override
    public PageInfo pageByFollow(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        PageInfo pageInfo = new PageInfo(userFollowMapper.listByFollow(params));
        return pageInfo;
    }
}
