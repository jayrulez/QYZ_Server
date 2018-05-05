package lx.gs.map.teamspeed;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import map.msg.XCreateTeamSpeed;
import xdb.Trace;
import xtable.Roleinfos;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Jin Shuai
 */
public class GroupWaitingList {

    private Queue<List<Long>> waitingPlayers1 = new ConcurrentLinkedQueue<>();
    private Queue<List<Long>> waitingPlayers2 = new ConcurrentLinkedQueue<>();
    private Queue<List<Long>> waitingPlayers3 = new ConcurrentLinkedQueue<>();

    private final Map<Long, List<Long>> teamMap = new ConcurrentHashMap<>();

    public void cancelApply(long roleId){
        if(teamMap.containsKey(roleId)){
            teamMap.get(roleId).remove(Long.valueOf(roleId));
        }
    }

    public void matchSucc(List<Long> team1, List<Long> team2){
        List<Long> filterTeam1 = new ArrayList<>();
        List<Long> filterTeam2 = new ArrayList<>();

        team1.forEach(aLong -> {
            if(aLong != null){
                filterTeam1.add(aLong);
            }
        });

        team2.forEach(aLong -> {
            if(aLong != null){
                filterTeam2.add(aLong);
            }
        });
        if(!TeamSpeedModule.INSTANCE.isOpen()){
            Trace.warn("team speed is closed!");
            return;
        }

        if(filterTeam1.isEmpty() && filterTeam2.isEmpty()){
            return;
        }

        List<Long> roleids = new ArrayList<>(filterTeam1);
        roleids.addAll(filterTeam2);

        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final XCreateTeamSpeed msg = new XCreateTeamSpeed();
                msg.levelgroupid = TeamSpeedModule.INSTANCE.levelGroupMap.get(Roleinfos.selectLevel(roleids.get(0)));
                msg.ectypeid = TeamSpeedModule.INSTANCE.cfg.lvmsg_id.get(msg.levelgroupid).ectypeid;
                msg.serverid = gs.Utils.getServerId();
                msg.team1.addAll(filterTeam1);
                msg.team2.addAll(filterTeam2);

                roleids.forEach(roleId -> {
                    FMap.getEctype(roleId).setMatchtype(0);
                    if(FCondition.checkA(roleId, CfgMgr.teamspeed.dailylimit, 1, By.Team_Speed_Reward, ConfigId.TEAM_SPEED, 0).ok()){
                        msg.rewardrole.add(roleId);
                    }
                });
                FMap.openEctypeInProcedure(roleids, msg.serverid, msg);
                return true;
            }
        }.execute();
    }

    public void doMatch(){
        while (waitingPlayers3.size() >= 2){
            matchSucc(waitingPlayers3.poll(), waitingPlayers3.poll());
        }

        while (waitingPlayers1.size() >= 2 && waitingPlayers2.size() >= 2){
            List<Long> team1 = waitingPlayers2.poll();
            List<Long> team2 = waitingPlayers2.poll();
            team1.addAll(waitingPlayers1.poll());
            team2.addAll(waitingPlayers1.poll());
            matchSucc(team1, team2);
        }

        while (waitingPlayers2.size() >= 2){
            List<Long> team1 = waitingPlayers2.poll();
            List<Long> team2 = waitingPlayers2.poll();
            matchSucc(team1, team2);
        }

        List<Long> player3 = waitingPlayers3.poll();
        List<Long> player2 = waitingPlayers2.poll();

        List<Long> team1 = player3 == null ? new ArrayList<>() : new ArrayList<>(player3);
        List<Long> team2 = player2 == null ? new ArrayList<>() : new ArrayList<>(player2);

        while (!waitingPlayers1.isEmpty()){
            while (!waitingPlayers1.isEmpty()){
                if(team1.size() == 3 && team2.size() == 3){
                    break;
                }
                List<Long> addTeam = team1.size() > team2.size() ? team2 : team1;
                List<Long> newAdd = waitingPlayers1.poll();
                if(newAdd != null){
                    addTeam.addAll(newAdd);
                }
            }
            if(!team1.isEmpty() || !team2.isEmpty()){
                matchSucc(team1, team2);
            }
            team1.clear();
            team2.clear();
        }

        if(!team1.isEmpty() || !team2.isEmpty()){
            matchSucc(team1, team2);
        }

    }

    public boolean offer(List<Long> roleIdList) {
        List<Long> add = new ArrayList<>(roleIdList);
        add.forEach(aLong -> teamMap.put(aLong, add));
        switch (add.size()){
            case 1 : return waitingPlayers1.offer(add);
            case 2 : return waitingPlayers2.offer(add);
            case 3 : return waitingPlayers3.offer(add);
            default: return false;
        }
    }

    public void clear() {
        waitingPlayers1.clear();
        waitingPlayers2.clear();
        waitingPlayers3.clear();
        teamMap.clear();
    }
}
