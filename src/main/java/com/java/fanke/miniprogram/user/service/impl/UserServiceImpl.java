package com.java.fanke.miniprogram.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.user.mapper.UserMapper;
import com.java.fanke.miniprogram.user.mapper.UserSignInMapper;
import com.java.fanke.miniprogram.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author xdd
 * @date 2018/8/21
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSignInMapper userSignInMapper;

    @Override
    public Map<String, Object> insert(Map<String, Object> params) {
        params.put("ticket", UUID.randomUUID().toString().replaceAll("-", ""));
        userMapper.insert(params);
        return params;
    }

    @Override
    public Map<String, Object> getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public Map<String, Object> getByUserId(String userId) {
        Map<String, Object> user = userMapper.getByUserId(userId);
        if (user != null) user.remove("password");
        return user;
    }

    @Override
    public Map<String, Object> getByPrimaryKey(Map<String, Object> params) {
        Map<String, Object> user = userMapper.getByPrimaryKey(params);
        if (user != null) user.remove("password");
        return user;
    }

    @Override
    public int updateByOpenid(Map<String, Object> params) {
        logger.info("根据openid更新用户参数：{}", JSONObject.toJSONString(params));
        int i = userMapper.updateByOpenid(params);
        logger.info("根据openid更新用户结果：", i);
        return i;
    }

    @Override
    public int updateByUserId(Map<String, Object> params) {
        logger.info("根据userId更新用户参数:{}", JSONObject.toJSONString(params));
        int i = userMapper.updateByUserId(params);
        logger.info("根据userId更新用户参数结果：", i);
        return i;
    }

    @Override
    public PageInfo pageByUser(Map<String, Object> params) {
        logger.info("分页查询用户参数：{}", params);
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        PageInfo pageInfo = new PageInfo(userMapper.listByUser(params));
        logger.info("分页查询用户返回：{}", pageInfo);
        return pageInfo;
    }

    @Override
    public long signIn(Map<String, Object> params) {
        userMapper.updateUserScore(params.get("user_id"), 1);
        int insert = userSignInMapper.insert(params);
        return Long.valueOf(params.get("id").toString());
    }

    @Override
    public List<Map<String, Object>> listByUserSignIn(Map<String, Object> params) {
        return userSignInMapper.listByUserSignIn(params);
    }

    @Override
    public PageInfo pageBySignIn(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        return new PageInfo(userSignInMapper.listByUserSignIn(params));
    }
}
