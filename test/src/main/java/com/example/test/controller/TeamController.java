package com.example.test.controller;

import com.example.test.bean.CommonResult;
import com.example.test.bean.Member;
import com.example.test.bean.Team;
import com.example.test.bean.TeamShow;
import com.example.test.mapper.TeamMapper;
import com.example.test.service.TeamService;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class TeamController {

    @Autowired
    TeamService teamService;
    @Autowired
    UserService userService;


    @PostMapping("/team/addTeam")
    public CommonResult addTeam(@RequestBody Team team){
        System.out.println(team.getUserID());
        System.out.println(team.getTeamName());
        Team result=teamService.insertTeam(team);
        return new CommonResult(200,null,result);
    }


    @PostMapping("/team/getTeam")
    public Team getTeam(@RequestBody Integer TeamID){
        return teamService.getTeamById(TeamID);
    }

    @PostMapping("/team/inTeams")
    public List<TeamShow> inTeams(@RequestBody Integer UserID){
        List<Team> teams=teamService.getTeamByUser(UserID);
        if(teams==null)
            return null;
        List<TeamShow> teamShows=new ArrayList<>();
        for(Team team:teams){
            teamShows.add(new TeamShow(team.getTeamID(),UserID,team.getTeamName(),userService.getUserById(UserID).getUserName()));
        }
        return teamShows;
    }

    @PostMapping("/team/quit/{TeamID}")
    public CommonResult quit(@PathVariable("TeamID") Integer TeamID,@RequestBody Integer UserID){
        teamService.deleteByTeamAndUser(TeamID,UserID);
        return new CommonResult(200, null,null);
    }

    @PostMapping("/team/delete/{TeamID}")
    public CommonResult delete(@PathVariable("TeamID")Integer TeamID){
        teamService.deleteTeam(TeamID);
        return new CommonResult(200,null,null);
    }

    @PostMapping("/team/updateInfo/{TeamID}")
    public CommonResult updateInfo(@PathVariable("TeamID")Integer TeamID,@RequestBody String TeamInfo){
        teamService.updateInfo(teamService.getTeamById(TeamID));
        return new CommonResult(200,null,null);
    }

    @PostMapping("/team/addMember")
    public CommonResult addMember(@RequestBody Member member){
        if(member.getUserID()==teamService.getTeamById(member.getTeamID()).getUserID()){
            return new CommonResult(500,"Leader can't be added as a member!",null);
        }
        Member result=teamService.insertMember(member);
        return new CommonResult(200,null,result);
    }

//    @PostMapping("/team/1/update/info")
//    public Team updateTeamInfo(@RequestBody String TeamInfo, Integer userId, Integer TeamId){
//        Team team1 = teamService.getTeamById(TeamId);
//        team1.setTeamInfo(TeamInfo);
//        teamService.changeTeamInfo(team1,userId);
//        return team1;
//    }

//    @PostMapping("/team/1/add")
//    public Team addMember(@RequestBody Integer userId, Integer memberId,Integer TeamId){
//        Team team1 = teamService.getTeamById(TeamId);
//        teamService.addMember(TeamId,memberId,userId);
//        return team1;
//    }

//    @PostMapping("/team/1/delete/{TeamID}")
//    public Team deleteMember(@PathVariable Integer TeamID,@RequestBody Integer UserID){
//        Team team1 = teamService.getTeamById(TeamID);
//        teamService.deleteMember();
//        return team1;
//    }

//    @PostMapping("/team/1/disband")
//    public int disbandTeam(Integer userId,Integer TeamId){
//        Team team = teamService.getTeamById(TeamId);
//        return teamService.removeTeam(team,userId);
//    }

//    @PostMapping("/team/2/quit")
//    public int quitTeam(Integer userId,Integer TeamId){
//        return teamService.quitTeam(TeamId,userId);
//    }

}
