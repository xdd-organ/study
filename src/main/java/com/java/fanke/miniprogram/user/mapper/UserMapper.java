package com.java.fanke.miniprogram.user.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xdd
 * @date 2018/8/21
 */
public interface UserMapper {

    int insert(Map<String, Object> params);

    Map<String, Object> getByUsername(@Param("username") String username);

    Map<String,Object> getByUserId(@Param("user_id") String userId);

    Map<String,Object> getByPrimaryKey(Map<String,Object> params);

    int updateByOpenid(Map<String,Object> params);

    int updateByUserId(Map<String,Object> params);

    List<Map<String,Object>> listByUser(Map<String,Object> params);

    int updateUserScore(@Param("user_id") Object userId, @Param("score") Object score);

}
