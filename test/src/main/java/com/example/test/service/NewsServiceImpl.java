package com.example.test.service;

import com.example.test.bean.News;
import com.example.test.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;



    /**
     * 根据NewsID查找信息
     * @param NewsID
     * @return
     */
    @Override
    public News ViewNewsByNewsId(Integer NewsID){
        News news = newsMapper.SelectNewsByNewsId(NewsID);
        return news;
    }

    /**
     * 根据UserID查找信息
     * @param UserID
     * @return
     */
    @Override
    public List<News> ViewNewsByUserId(Integer UserID){
        List<News> news = newsMapper.SelectNewsByUserId(UserID);
        return news;
    }

    @Override
    public News ReadNews(News news){
        if(news == null || news.getNewsID() == null || news.getUserID() == null){
            return null;
        }
        newsMapper.changeNewsIsRead(news);
        return newsMapper.SelectNewsByNewsId(news.getNewsID());
    }

    public int deleteById(News news){
        if(news == null || news.getNewsID() == null){
            return 0;
        }
        return newsMapper.deleteById(news);
    }

}
