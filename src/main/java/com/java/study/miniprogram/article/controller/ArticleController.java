package com.java.study.miniprogram.article.controller;

import com.github.pagehelper.PageInfo;
import com.java.study.common.vo.Result;
import com.java.study.miniprogram.article.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("article")
@CrossOrigin
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @RequestMapping("insert")
    public Result insert(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("保存文章信息参数：{}", params);
        long res = articleService.insert(params);
        LOGGER.info("保存文章信息返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("pageByArticle")
    public Result pageByArticle(@RequestBody Map<String, Object> params, HttpSession session) {
        LOGGER.info("分页查询文章信息参数：{}", params);
        PageInfo res = articleService.pageByArticle(params);
        LOGGER.info("分页查询文章信息返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("update")
    public Result update(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("update_author", userId);
        LOGGER.info("修改文章信息参数：{}", params);
        int res = articleService.update(params);
        LOGGER.info("修改文章信息返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("star")
    public Result star(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("文章点赞参数：{}", params);
        int res = articleService.star(params);
        LOGGER.info("文章点赞返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("unStar")
    public Result unStar(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("文章取消点赞参数：{}", params);
        int res = articleService.unStar(params);
        LOGGER.info("文章取消点赞返回：{}", res);
        return new Result(100, res);
    }


    @RequestMapping("collect")
    public Result collect(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("update_author", userId);
        LOGGER.info("文章关注参数：{}", params);
        int res = articleService.collect(params);
        LOGGER.info("文章关注返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("unWatch")
    public Result unWatch(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("update_author", userId);
        LOGGER.info("文章取消收藏参数：{}", params);
        int res = articleService.unCollect(params);
        LOGGER.info("文章取消收藏返回：{}", res);
        return new Result(100, res);
    }

}
