package com.java.fanke.miniprogram.user.mapper;

import java.util.List;
import java.util.Map;

/**
 * @author xdd
 * @date 2018/8/21
 */
public interface UserSignInMapper {

    int insert(Map<String, Object> params);

    List<Map<String, Object>> listByUserSignIn(Map<String, Object> params);
}
