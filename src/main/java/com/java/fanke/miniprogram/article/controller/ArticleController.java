package com.java.fanke.miniprogram.article.controller;

import com.github.pagehelper.PageInfo;
import com.java.fanke.common.vo.Result;
import com.java.fanke.miniprogram.article.service.ArticleCommentService;
import com.java.fanke.miniprogram.article.service.ArticleService;
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
    @Autowired
    private ArticleCommentService articleCommentService;

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

    @RequestMapping("like")
    public Result like(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("文章点赞参数：{}", params);
        int res;
        if ("1".equals(String.valueOf(params.get("is_like")))) {
            res = articleService.like(params);
        } else {
            res = articleService.unLike(params);
        }
        LOGGER.info("文章点赞返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("favorite")
    public Result favorite(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("文章收藏参数：{}", params);
        int res;
        if ("1".equals(String.valueOf(params.get("is_favorite")))) {
            res = articleService.favorite(params);
        } else {
            res = articleService.unFavorite(params);
        }
        LOGGER.info("文章收藏返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("comment")
    public Result comment(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("文章评论参数：{}", params);
        long res = articleService.comment(params);
        LOGGER.info("文章评论返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("delComment")
    public Result delComment(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("文章删除评论参数：{}", params);
        int res = articleService.delComment(params);
        LOGGER.info("文章删除评论返回：{}", res);
        return new Result(100, res);
    }

    @RequestMapping("pageByArticleComment")
    public Result pageByComment(@RequestBody Map<String, Object> params, HttpSession session) {
        Object userId = session.getAttribute("userId");
        params.put("user_id", userId);
        LOGGER.info("分页查询文章评论参数：{}", params);
        PageInfo res = articleCommentService.pageByArticleComment(params);
        LOGGER.info("分页查询文章评论返回：{}", res);
        return new Result(100, res);
    }

}
