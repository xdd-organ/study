package com.java.fanke.miniprogram.user.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author xdd
 * @date 2018/8/21
 */
public interface UserService {

    Map<String, Object> insert(Map<String, Object> params);

    Map<String, Object> getByUsername(String username);

    Map<String, Object> getByUserId(String userId);

    Map<String, Object> getByPrimaryKey(Map<String, Object> params);

    int updateByOpenid(Map<String,Object> params);

    int updateByUserId(Map<String,Object> params);

    PageInfo pageByUser(Map<String,Object> params);

    long signIn(Map<String,Object> params);

    List<Map<String, Object>> listByUserSignIn(Map<String,Object> params);

    PageInfo pageBySignIn(Map<String,Object> params);

    String getUserSig(Map<String, Object> params);
}
