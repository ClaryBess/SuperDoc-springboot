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

    /**
     * 将消息设置为已读
     * @param NewsID
     */
    @Override
    public void ReadNews(Integer NewsID){
        newsMapper.changeNewsIsRead(NewsID);
    }
    /**
     * 查看所有信息
     * @param UserID
     * @return
     */
   //public List<News> ViewAllNews(int UserID)


}
