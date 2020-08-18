package com.example.test.controller;

import com.example.test.bean.Team;
import com.example.test.mapper.TeamMapper;
import com.example.test.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {

    @Autowired
    TeamService teamService;

    @Autowired
    TeamMapper teamMapper;

    //@GetMapping("/team")
    //public Team createTeam()

    @GetMapping("/team/{TeamID}")
    public Team getTeam(@PathVariable("TeamID") Integer TeamID){
        return teamService.getTeamById(TeamID);
    }

    @GetMapping("/team/1/update/info")
    public Team updateTeamInfo(String TeamInfo, Integer userId, Integer TeamId){
        Team team1 = teamService.getTeamById(TeamId);
        team1.setTeamInfo(TeamInfo);
        teamService.changeTeamInfo(team1,userId);
        return team1;
    }

    @GetMapping("/team/1/add")
    public Team addMember(Integer userId, Integer memberId,Integer TeamId){
        Team team1 = teamService.getTeamById(TeamId);
        teamService.addMember(TeamId,memberId,userId);
        return team1;
    }

    @GetMapping("/team/1/disband")
    public int disbandTeam(Integer userId,Integer TeamId){
        Team team = teamService.getTeamById(TeamId);
        return teamService.removeTeam(team,userId);
    }

    @GetMapping("/team/2/quit")
    public int quitTeam(Integer userId,Integer TeamId){
        return teamService.quitTeam(TeamId,userId);
    }

}
