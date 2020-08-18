package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.News;
import com.example.test.mapper.NewsMapper;
import com.example.test.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    NewsMapper newsMapper;

    @PostMapping("/news/getNews")
    public List<News> getNews(@RequestBody Integer UserID){
        return newsService.getNewsByUserId(UserID);
    }

    @PostMapping("/news/readNews")
    public CommonResult readNews(@RequestBody News news){
        News news1 = newsService.readNews(news.NewsID);
        return new CommonResult(200,"success",news1);
    }

    //这是后端测试用的
    @GetMapping("/news/read")
    public News read(News news){
        Integer NewsID = news.getNewsID();
        return newsService.readNews(NewsID);
//        newsMapper.updateRead(NewsID);
//        return newsMapper.SelectNewsByNewsId(NewsID);
    }

    @PostMapping("/news/deleteNews")
    public CommonResult deleteNews(@RequestBody News news){
        int flag = newsService.deleteById(news);
        if(flag == 0){
            return new CommonResult(400,"cannot find the news",null);
        }
        else{
            return new CommonResult(200,"success",null);
        }
    }

}
