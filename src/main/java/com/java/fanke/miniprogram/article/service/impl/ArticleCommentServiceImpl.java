package com.java.fanke.miniprogram.article.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.fanke.miniprogram.article.mapper.ArticleCommentMapper;
import com.java.fanke.miniprogram.article.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Transactional
    @Override
    public long insert(Map<String, Object> params) {
        return articleCommentMapper.insert(params);
    }

    @Transactional
    @Override
    public int update(Map<String, Object> params) {
        return articleCommentMapper.update(params);
    }

    @Override
    public PageInfo pageByArticleComment(Map<String, Object> params) {
        PageHelper.startPage(Integer.valueOf(params.get("pageNum").toString()), Integer.valueOf(params.get("pageSize").toString()));
        return new PageInfo(articleCommentMapper.listByArticleComment(params));
    }
}
