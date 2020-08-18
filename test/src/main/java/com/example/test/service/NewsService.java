package com.example.test.service;

import com.example.test.bean.News;

import java.util.List;

public interface NewsService {


    //根据id查看消息
    News ViewNewsByNewsId(Integer NewsID);

    //根据用户查看消息
    List<News> ViewNewsByUserId(Integer UserID);

    //修改已读状态
    News ReadNews(News news);

    //查看所有信息
    //List<News> ViewAllNews(int UserID);
}
