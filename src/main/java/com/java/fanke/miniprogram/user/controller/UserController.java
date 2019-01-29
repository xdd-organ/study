package com.java.fanke.miniprogram.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.java.fanke.common.service.RedisService;
import com.java.fanke.common.utils.httpclient.HttpClientUtil;
import com.java.fanke.common.utils.httpclient.HttpResult;
import com.java.fanke.common.vo.Result;
import com.java.fanke.common.weixin.WeixinDecrypt;
import com.java.fanke.miniprogram.pay.constant.PayConstant;
import com.java.fanke.miniprogram.user.service.TransFlowInfoService;
import com.java.fanke.miniprogram.user.service.UserFollowService;
import com.java.fanke.miniprogram.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xdd
 * @date 2018/8/21
 */
@RequestMapping("user")
@RestController
@CrossOrigin
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TransFlowInfoService transFlowInfoService;
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private HttpClientUtil httpClientUtil;
    @Autowired
    private UserFollowService userFollowService;
    @Value("${appid:}")
    private String appid;
    @Value("${appSecret:}")
    private String appSecret;

    @RequestMapping("pageByTransFlowInfo")
    public Result pageByTransFlowInfo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        logger.info("分页查询流水参数：{},userId:{}", JSONObject.toJSONString(params), userId);
        params.put("user_id", userId);
        PageInfo pageInfo = transFlowInfoService.pageByTransFlowInfo(params);
        logger.info("分页查询流水返回：{},userId:{}", JSONObject.toJSONString(pageInfo));
        return new Result(100, pageInfo);
    }

    @RequestMapping("getUserInfo")
    public Result getUserInfo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        logger.info("获取用户信息参数：{},userId:{}", JSONObject.toJSONString(params), userId);
        params.put("user_id", userId);
        Map<String, Object> user = userService.getByUserId(userId.toString());
        logger.info("获取用户信息返回：{}", JSONObject.toJSONString(user));
        return new Result(100, user);
    }

    @RequestMapping("getUserByUserId")//
    public Result getUserByUserId(@RequestBody Map<String, Object> params) {
        logger.info("获取用户信息参数：{}", JSONObject.toJSONString(params));
        Object userId = params.get("user_id");
        Map<String, Object> user = userService.getByUserId(userId.toString());
        logger.info("获取用户信息返回：{}", JSONObject.toJSONString(user));
        return new Result(100, user);
    }

    @RequestMapping("bindPhone")//绑定手机
    public Result bindPhone(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        logger.info("绑定手机参数：{},userId:{}", JSONObject.toJSONString(params), userId);
        String userVerifyCode = params.remove("verifyCode").toString();
        String verifyCode = redisService.get(params.remove("randomKey").toString());
        logger.info("用户验证码：{}，redis验证码:{}", userVerifyCode, verifyCode);
        if (!userVerifyCode.equals(verifyCode)) return new Result(500, "验证码错误", null);
        params.put("user_id", userId);
        int i = userService.updateByUserId(params);
        logger.info("绑定手机返回：{}", i);
        return new Result(100);
    }

    @RequestMapping("update")//更新用户
    public Result update(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        logger.info("更新用户信息参数：{},userId:{}", params, userId);
        int i = userService.updateByUserId(params);
        logger.info("更新用户信息返回：{}", i);
        return new Result(100);
    }

    @RequestMapping("bindWeixinInfo")//绑定微信信息
    public Result bindWeixinInfo(@RequestBody Map<String, Object> params, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        logger.info("绑定微信信息参数：{},userId:{}", JSONObject.toJSONString(params), userId);
        String code = params.get("code").toString();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + appSecret + "&js_code=" + code + "&grant_type=authorization_code";
        try {
            logger.info("发送到微信获取openid参数：{}", url);
            HttpResult httpResult = httpClientUtil.doGet(url, null, null);
            String body = httpResult.getBody();
            logger.info("调用接口jscode2session，返回code：{}：body:{}", httpResult.getCode(),body);
            if (body != null && body.contains(PayConstant.OPENID)) {
                Map<String, String> bodyMap = JSONObject.parseObject(body, Map.class);
                String encrypteData = String.valueOf(params.get("encryptedData"));
                String iv = String.valueOf(params.get("iv"));
                String openId = String.valueOf(params.get("openId"));
                String sessionKey = bodyMap.get("session_key");
                String decrypt = WeixinDecrypt.decrypt(appid, encrypteData, sessionKey, iv);
                logger.info("解密encrypteData结果：{}", decrypt);
                Map<String, String> decryptMap = JSONObject.parseObject(decrypt, Map.class);

                Map<String, Object> userParams = new HashMap<>();
                userParams.put("user_id", userId);
                userParams.put("openid", bodyMap.get("openid"));
                userParams.put("unionid", bodyMap.get("unionid"));
                userParams.put("nickname", decryptMap.get("nickName"));
                userParams.put("gender", decryptMap.get("gender"));
                userParams.put("avatar", decryptMap.get("avatarUrl"));
                userParams.put("phone", decryptMap.get("phoneNumber"));
                logger.info("更新用户信息参数：{}", JSONObject.toJSONString(userParams));
                int i = userService.updateByUserId(userParams);
                logger.info("更新用户信息结果：{}", i);
                return new Result(100, userService.getByUserId(userId.toString()));
            }
        } catch (Exception e) {
            logger.error("异常：" + e.getMessage(), e);
        }
        return new Result(500);
    }

    @RequestMapping("pageByUser")
    public Result pageByUser(@RequestBody Map<String, Object> params) {
        logger.info("分页查询用户参数：{}", params);
        PageInfo pageInfo = userService.pageByUser(params);
        logger.info("分页查询用户返回：{}", pageInfo);
        return new Result(100, pageInfo);
    }

    @RequestMapping("login")
    public Result login(@RequestBody Map<String, String> params) {
        String username = params.get("username");
        String password = params.get("password");
        logger.info("账号：{}，密码：{}", username, password);
        Map<String, Object> user = userService.getByUsername(username);
        if (user == null) {
            return new Result(500, "用户名或密码错误");
        } else {
            String pwd = String.valueOf(user.remove("password"));
            if (pwd.equalsIgnoreCase(password)) {
                return new Result(100, user);
            } else {
                return new Result(500, "用户名或密码错误");
            }
        }
    }

    @RequestMapping("follow")
    public Result follow(@RequestBody Map<String, Object> params) {
        logger.info("用户关注参数：{}", params);
        int res = 0;
        if ("1".equals(String.valueOf(params.get("is_follow")))) {
            userFollowService.insert(params);
            res = 1;
        } else {
            res = userFollowService.unFollow(params);
        }
        logger.info("用户关注返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("pageByFollow")
    public Result pageByFollow(@RequestBody Map<String, Object> params) {
        logger.info("分页查询用户关注参数：{}", params);
        PageInfo res = userFollowService.pageByFollow(params);
        logger.info("分页查询用户关注返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("signIn")
    public Result signIn(@RequestBody Map<String, Object> params) {
        logger.info("用户签到参数：{}", params);
        long res = userService.signIn(params);
        logger.info("用户签到返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("listByUserSignIn")
    public Result getSignInByDate(@RequestBody Map<String, Object> params) {
        logger.info("查询用户签到信息参数：{}", params);
        List<Map<String, Object>> res = userService.listByUserSignIn(params);
        logger.info("查询用户签到信息返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("pageBySignIn")
    public Result pageBySignIn(@RequestBody Map<String, Object> params) {
        logger.info("分页查询用户签到信息参数：{}", params);
        PageInfo res = userService.pageBySignIn(params);
        logger.info("分页查询用户签到信息返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("getUserSig")
    public Result getUserSig(@RequestBody Map<String, Object> params) {
        logger.info("获取腾讯UserSig参数：{}", params);
        String res = userService.getUserSig(params);
        logger.info("获取腾讯UserSig返回：{}", res);
        return new Result(100, res);
    }

}
