package com.java.fanke.miniprogram.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.article.mapper.ArticleCommentMapper;
import com.java.fanke.miniprogram.article.service.ArticleCommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleCommentServiceImpl.class);

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Transactional
    @Override
    public long insert(Map<String, Object> params) {
        LOGGER.info("保存评论参数：{}", params);
        return articleCommentMapper.insert(params);
    }

    @Transactional
    @Override
    public int update(Map<String, Object> params) {
        LOGGER.info("修改评论参数：{}", params);
        return articleCommentMapper.update(params);
    }

    @Override
    public PageInfo pageByArticleComment(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        return new PageInfo(articleCommentMapper.listByArticleComment(params));
    }
}
