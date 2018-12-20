package com.java.fanke.miniprogram.article.service.impl;

import com.java.fanke.miniprogram.article.mapper.ArticleInfoMapper;
import com.java.fanke.miniprogram.article.service.ArticleInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleInfoServiceImpl.class);

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Override
    public Map<String, Object> getByUserIdAndArticleId(String userId, String articleId) {
        Map<String, Object> article = articleInfoMapper.getByUserIdAndArticleId(userId, articleId);
        LOGGER.info("根据用户id和文章id查询文字信息：{}，{}, {}", userId, articleId, article);
        return article;
    }

    @Override
    public long insert(Map<String, Object> params) {
        return articleInfoMapper.insert(params);
    }

    @Override
    public long insertInfo(String userId, String articleId) {
        LOGGER.info("插入文章信息参数：{}，{}", userId, articleId);
        Map<String, Object> info = this.getByUserIdAndArticleId(userId, articleId);
        if (CollectionUtils.isEmpty(info)) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", userId);
            params.put("article_id", articleId);
            this.insert(params);
            return Long.valueOf(params.get("id").toString());
        } else {
            return Long.valueOf(info.get("id").toString());
        }
    }
}
