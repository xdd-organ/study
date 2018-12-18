package com.java.fanke.miniprogram.article.service.impl;

import com.java.fanke.miniprogram.article.mapper.ArticleCommentMapper;
import com.java.fanke.miniprogram.article.service.ArticleCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleCommentServiceImpl implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;
}
