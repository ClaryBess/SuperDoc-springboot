package com.example.test.mapper;

import com.example.test.bean.News;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface NewsMapper {

    //创建消息
    @Options(useGeneratedKeys = true,keyProperty = "NewsID")
    @Insert("insert into News(NewsID,UserID,DateTime,Type,Content) values(#{NewsID},#{UserID},#{DateTime},#{Type},#{Content})")
    News CreateNews(News news);

    //发送系统消息
    News SendNews(News news);

    //修改消息类型
    @Update("update News set Type=#{Type} where NewsID=#{NewsID}")
    void changeNewsType(News news);

    //修改已读状态
    @Update("update News set IsRead=1 where NewsID=#{NewsID}")
    void changeNewsIsRead(Integer NewsID);

    //根据NewsID查看消息
    @Select("select * from News where NewsID=#{NewsID}")
    News SelectNewsByNewsId(Integer NewsID);

    //根据UserID查看消息
    @Select("select * from News where UserID=#{UserID}")
    List<News> SelectNewsByUserId(Integer UserID);
}