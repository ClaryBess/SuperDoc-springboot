package com.example.test.controller;

import com.example.test.bean.Team;
import com.example.test.mapper.TeamMapper;
import com.example.test.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @PostMapping("/team/insertTeam")
    public Team insertTeam(@RequestBody Team team){
        return teamService.insertTeam(team);
    }

    @PostMapping("/team/getTeam")
    public Team getTeam(@RequestBody Integer TeamID){
        return teamService.getTeamById(TeamID);
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
//        teamService.deleteMember(TeamId,memberId,userId);
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
