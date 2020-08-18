package com.example.test.service;

import com.example.test.bean.Member;
import com.example.test.bean.Team;
import com.example.test.mapper.MemberMapper;
import com.example.test.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamMapper teamMapper;

    @Autowired
    MemberMapper memberMapper;

    /**
     * 创建团队
     * @param team
     * @return
     */
    @Override
    public Team createTeam(Team team){
        teamMapper.newTeam(team);
        return teamMapper.getTeamById(team.getTeamID());
    }

//    /**
//     * 修改团队名称,只有队长有权限
//     * @param team
//     * @return
//     */
//    @Override
//    public int updateTeamName(Team team,Integer userID){
//        Team team1 = teamMapper.getTeamById(team.getTeamID());
//        if(team1.getUserID() == userID){
//            teamMapper.updateTeamName(team);
//            return 1;
//        }
//        return 0;
//    }


    /**
     * 添加团队成员,只有队长有权限
     * 第3个参数是队长id，第2个参数是加入的成员id
     * 所以第50行需不需要加个if条件判断呢
     */
    @Override
    public int addMember(Integer TeamID,Integer memberID,Integer userID){
        Team team1 = teamMapper.getTeamById(TeamID);
        if(team1.getUserID() == userID){
            teamMapper.insertMember(TeamID,memberID);
            teamMapper.upMemberNum(TeamID);
            return 1;
        }
        return 0;
    }

    /**
     * 删除团队成员,只有队长有权限
     * 第三个参数是队长id，第二个是要删除的成员
     */
    @Override
    public int deleteMember(Integer TeamID,Integer memberID,Integer userID){
        Team team1 = teamMapper.getTeamById(TeamID);
        if(team1.getUserID() == userID){
            teamMapper.deleteMember(TeamID,memberID);
            teamMapper.downMemberNum(TeamID);
            return 1;
        }
        return 0;
    }

    /**
     * 修改团队权限,只有队长有权限
     */
    @Override
    public int changePrivilege(Team team,Integer userID){
        Team team1 = teamMapper.getTeamById(team.getTeamID());
        if(team1.getUserID() == userID){
            teamMapper.changePrivilege(team);
            return 1;
        }
        return 0;
    }

    /**
     * 修改团队简介,只有队长有权限
     * @param team
     * @return
     */
    @Override
    public int changeTeamInfo(Team team,Integer userID){
        Team team1 = teamMapper.getTeamById(team.getTeamID());
        if(team1.getUserID() == userID){
            teamMapper.changeTeamInfo(team);
            return 1;
        }
        return 0;
    }

    /**
     * 解散团队,只有队长有权限
     */
    @Override
    public int removeTeam(Team team,Integer userID){
        Team team1 = teamMapper.getTeamById(team.getTeamID());
        if(team1.getUserID() == userID){
            teamMapper.disbandTeam(team);
            return 1;
        }
        return 0;
    }

    @Override
    public Team getTeamById(Integer id){
        return teamMapper.getTeamById(id);
    }

    @Override
    public Team getTeamByName(String name){
        return teamMapper.getTeamByName(name);
    }

    @Override
    public Team getTeamByUser(Integer userID){
        Member member = memberMapper.getMemberByUser(userID);
        return teamMapper.getTeamById(member.getTeamID());
    }

    /**
     * 主动退出团队
     */
    @Override
    public int quitTeam(Integer TeamID,Integer UserID){
        teamMapper.deleteMember(TeamID,UserID);
        teamMapper.downMemberNum(TeamID);
        return 1;
    }
}
