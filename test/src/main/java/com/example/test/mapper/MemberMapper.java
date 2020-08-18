package com.example.test.mapper;

import com.example.test.bean.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface MemberMapper {

    //根据用户id返回加入团队
    @Select("select * from Member where UserID=#{userID}")
    public Member getMemberByUser(Integer userID);

    //添加成员
    @Options(useGeneratedKeys = true,keyProperty = "MemberID")
    @Insert("insert into Member(TeamID,UserID) values(#{TeamID},#{UserID})")
    int insertMember(Integer TeamID,Integer UserID);

    //删除成员
    @Delete("delete from Member where UserID=#{UserID} and TeamID=#{TeamID}")
    int deleteMember(Integer TeamID,Integer UserID);
}
