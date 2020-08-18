package com.example.test.service;


import com.example.test.bean.Team;

public interface TeamService {
    //创建团队
    Team createTeam(Team team);

    //修改团队名称
    //int updateTeamName(Team team,Integer userID);

    //添加团队成员
    int addMember(Integer TeamID,Integer memberID,Integer userID);

    //删除团队成员
    int deleteMember(Integer TeamID,Integer memberID,Integer userID);

    //修改团队权限
    int changePrivilege(Team team,Integer userID);

    //修改团队简介
    int changeTeamInfo(Team team,Integer userID);

    //解散团队
    int removeTeam(Team team,Integer userID);

    //根据团队id查找团队
    Team getTeamById(Integer id);

    //根据团队名称选择团队
    Team getTeamByName(String name);

    //根据用户id查询所在团队
    Team getTeamByUser(Integer userID);

    int quitTeam(Integer TeamID,Integer UserID);
}
