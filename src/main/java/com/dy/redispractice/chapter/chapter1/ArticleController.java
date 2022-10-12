package com.dy.redispractice.chapter.chapter1;

import com.dy.redispractice.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.BoundZSetOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dongyang
 * @date 2022/10/11 19:56
 */
//@Api(tags = "文章")
@RestController
@RequestMapping("/article")
public class ArticleController {

    private static final int SCORE = 453;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/add")
    public void add(Article article){
        article.setTime(LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")));
        String articleId = "article:" + article.getId();
        //1、将文章信息保存到redis
        BoundHashOperations articleOps = redisTemplate.boundHashOps(articleId);
        articleOps.put("id", articleId);
        //2、将文章保存到时间排序列表
        BoundZSetOperations time = redisTemplate.boundZSetOps("time");
        time.add(articleId, article.getTime());
        //3、将作者加入已经投屏序列
        BoundZSetOperations score = redisTemplate.boundZSetOps("score");
        score.add(articleId, article.getTime() + SCORE);
    }

    @GetMapping("/vote")
    public void vote(Article article){
        String articleId = "article:" + article.getId();
        //1、修改分数
        BoundZSetOperations score = redisTemplate.boundZSetOps("score");
        double newScore = score.score(articleId) + SCORE;
        score.add(articleId, newScore);
        //2、将用户加入已经投票里面
        BoundSetOperations voted = redisTemplate.boundSetOps("voted:" + article.getId());
        voted.add(SystemUtil.getCurrentUserCode());
    }

    @GetMapping("/list")
    public List<Article> list(){
        List<Article> articleList = new ArrayList<>();
        BoundZSetOperations score = redisTemplate.boundZSetOps("score");
        Set range = score.range(0, -1);
        for (Object o : range) {
            String articleId = o.toString();
            Double score1 = score.score(articleId);
            BoundHashOperations article = redisTemplate.boundHashOps("article:" + o.toString());
            articleList.add(Article.builder().id(o.toString()).score(score1).build());
//            article.entries()
//            articleList.add();
        }
        return articleList;
    }
}
