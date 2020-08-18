package com.example.test.mapper;

import com.example.test.bean.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface TeamMapper {

    //创建团队
    @Options(useGeneratedKeys = true,keyProperty = "TeamID")
    @Insert("insert into Team(UserID,TeamName,DateTime) values(#{UserID},#{TeamName},sysdate())")
    int newTeam(Team team);

    //修改团队名称
    @Update("update Team set TeamName=#{TeamName} where TeamID=#{TeamID}")
    int updateTeamName(Team team);

    //添加团队成员
    @Options(useGeneratedKeys = true,keyProperty = "MemberID")
    @Insert("insert into Member(TeamID,UserID) values(#{TeamID},#{UserID})")
    int insertMember(Integer TeamID,Integer UserID);

    //修改团队人数+1
    @Update("update Team set MemberNumber=MemberNumber+1 where TeamID=#{TeamID}")
    int upMemberNum(Integer TeamID);

    //删除团队成员
    @Delete("delete from Member where UserID=#{UserID} and TeamID=#{TeamID}")
    int deleteMember(Integer TeamID,Integer UserID);

    //修改团队人数-1
    @Update("update Team set MemberNumber=MemberNumber-1 where TeamID=#{TeamID}")
    int downMemberNum(Integer TeamID);

    //修改团队权限
    @Update("update Team set Privilege=#{Privilege} where TeamID=#{TeamID}")
    int changePrivilege(Team team);

    //修改团队简介
    @Update("update Team set TeamInfo=#{TeamInfo} where TeamID=#{TeamID}")
    int changeTeamInfo(Team team);

    //解散团队
    @Delete("delete from Team where TeamID=#{TeamID}")
    int disbandTeam(Team team);

    //根据团队id选择团队
    @Select("select * from Team where TeamID=#{id}")
    Team getTeamById(Integer id);

    //根据团队名称选择团队
    @Select("select * from Team where TeamName=#{name}")
    Team getTeamByName(String name);

}
