package com.java.fanke.miniprogram.user.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserFollowService {

    int unFollow(Map<String,Object> params);

    long insert(Map<String,Object> params);

    PageInfo pageByFollow(Map<String,Object> params);
}
