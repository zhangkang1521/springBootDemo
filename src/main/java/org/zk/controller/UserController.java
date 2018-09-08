package org.zk.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zk.domain.Article;
import org.zk.repository.ArticleSearchRepository;

/**
 * Created by zhangkang on 2017/7/31.
 */
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ArticleSearchRepository repository;

    int i = 0;

    @RequestMapping("/")
    public Article index(){
        Article article = new Article();
        String threadName = Thread.currentThread().getName();
        article.setTitle(threadName);
        repository.save(article);
        logger.info("{}, {}", threadName, i++);
        return article;
    }
}
