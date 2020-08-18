package com.example.test.service;

import com.example.test.bean.News;
import com.example.test.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    NewsMapper newsMapper;

    public News getNewsByNewsId(Integer NewsID){
        News news = newsMapper.SelectNewsByNewsId(NewsID);
        return news;
    }

    public List<News> getNewsByUserId(Integer UserID){
        List<News> news = newsMapper.SelectNewsByUserId(UserID);
        return news;
    }

    public News readNews(Integer NewsID){
        newsMapper.updateRead(NewsID);
        return newsMapper.SelectNewsByNewsId(NewsID);
    }

    public int deleteById(News news){
        if(news == null || news.getNewsID() == null){
            return 0;
        }
        return newsMapper.deleteById(news);
    }

}
