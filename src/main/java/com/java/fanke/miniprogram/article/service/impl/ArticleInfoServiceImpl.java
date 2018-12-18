package com.java.fanke.miniprogram.article.service.impl;

import com.java.fanke.miniprogram.article.mapper.ArticleInfoMapper;
import com.java.fanke.miniprogram.article.service.ArticleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleInfoServiceImpl implements ArticleInfoService {

    @Autowired
    private ArticleInfoMapper articleInfoMapper;

    @Override
    public Map<String, Object> getByUserIdAndArticleId(String userId, String articleId) {
        return articleInfoMapper.getByUserIdAndArticleId(userId, articleId);
    }

    @Override
    public long insert(Map<String, Object> params) {
        return articleInfoMapper.insert(params);
    }

    @Override
    public long insertInfo(String userId, String articleId) {
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
