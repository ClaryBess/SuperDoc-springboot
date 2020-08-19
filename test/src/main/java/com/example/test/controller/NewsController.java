package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.News;
import com.example.test.mapper.NewsMapper;
import com.example.test.mapper.TeamMapper;
import com.example.test.mapper.UserMapper;
import com.example.test.service.NewsService;
import com.example.test.service.TeamService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    NewsService newsService;
    @Autowired
    NewsMapper newsMapper;
    @Autowired
    UserService userService;
    @Autowired
    TeamService teamService;


    @PostMapping("/news/getNews")
    public List<News> getNews(@RequestBody Integer UserID){
        return newsService.getNewsByUserId(UserID);
    }

    @PostMapping("/news/readNews")
    public CommonResult readNews(@RequestBody News news){
        int flag = newsService.updateRead(news);
        if(flag == 0){
            return new CommonResult(400,"no influence",null);
        }
        else{
            return new CommonResult(200,"success",null);
        }
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

    @PostMapping("/news/apply/{TeamID}")
    public CommonResult applyNews(@PathVariable("TeamID")Integer TeamID,@RequestBody Integer UserID){
        News news=new News();
        news.setUserID(teamService.getTeamById(TeamID).getUserID());
        news.setContent(userService.getUserById(UserID).getUserName());
        news.setType(1);
        int result=newsMapper.CreateNews(news);
        return new CommonResult(200,null,result);
    }
}
