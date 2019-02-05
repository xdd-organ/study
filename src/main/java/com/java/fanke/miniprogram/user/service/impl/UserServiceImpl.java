package com.java.fanke.miniprogram.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.common.utils.SerialNumber;
import com.java.fanke.common.utils.httpclient.HttpClientUtil;
import com.java.fanke.common.utils.httpclient.HttpResult;
import com.java.fanke.common.video.VideoScreenshot;
import com.java.fanke.miniprogram.user.mapper.UserMapper;
import com.java.fanke.miniprogram.user.mapper.UserSignInMapper;
import com.java.fanke.miniprogram.user.service.UserService;
import com.tls.tls_sigature.tls_sigature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
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
    @Autowired
    private HttpClientUtil httpClientUtil;

    @Override
    public Map<String, Object> insert(Map<String, Object> params) {
        params.put("ticket", UUID.randomUUID().toString().replaceAll("-", ""));
        userMapper.insert(params);
        this.registerIdentifier(params);
        return params;
    }

    private static final String url = "https://console.tim.qq.com/v4/im_open_login_svc/account_import?contenttype=json";
    private void registerIdentifier(final Map<String, Object> params) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                try {
                    String userSig = getUserSig(null);
                    String random = SerialNumber.getRandomNum(32);
                    String reqUrl = url + "&sdkappid=" + sdkAppid + "&identifier=" + sdkAppAdmin + "&usersig=" + userSig + "&random=" + random;

                    String body = "";

                    Map<String, Object> bodyMap = new HashMap<>();
                    bodyMap.put("Identifier", params.get("ticket"));
                    if (!StringUtils.isEmpty(params.get("nickname"))) bodyMap.put("Nick", params.get("nickname"));
                    if (!StringUtils.isEmpty(params.get("avatar")))bodyMap.put("FaceUrl", params.get("avatar"));
                    bodyMap.put("Type", 0);

                    body = JSONObject.toJSONString(bodyMap);

                    logger.info("注册腾讯云讯地址【{}】，参数【{}】", reqUrl, body);
                    HttpResult httpResult = httpClientUtil.doPost(reqUrl, body, null);
                    logger.info("注册腾讯云讯返回【{}】", JSONObject.toJSONString(httpResult));
                    boolean ok = httpResult.getBody().contains("OK");
                    if (!ok) {
                        throw new RuntimeException("注册腾讯云讯返回失败");
                    }
                } catch (Exception e) {
                    logger.error("注册腾讯云讯异常", e);
                }
            }
        }).start();
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


    private static final String priKeyContent = "-----BEGIN PRIVATE KEY-----\n" +
            "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgvu4Y/prmz2xFQZ08\n" +
            "w+tlqt/Knz3YHoTblRESZMh9MSahRANCAASHk2mD+f7bwTNTKLIQVrOQiZwQoLML\n" +
            "vf2x8CTKxCmXtc8ZrItkTVRw530BHARgW7T4uzaONgKJiaBEpAtVlBKN\n" +
            "-----END PRIVATE KEY-----";
    @Value("${sdkAppid:1400181648}")
    private long sdkAppid;
    @Value("${sdkAppAdmin:admin}")
    private String sdkAppAdmin;

    @Override
    public String getUserSig(Map<String, Object> params) {
        String ticket = null;
        if (StringUtils.isEmpty(params.get("ticket"))) {
            ticket = "admin";
        } else {
            ticket = String.valueOf(params.get("ticket"));
        }
        tls_sigature.GenTLSSignatureResult result = tls_sigature.GenTLSSignatureEx(sdkAppid, ticket, priKeyContent);
        return result.urlSig;
    }
}
