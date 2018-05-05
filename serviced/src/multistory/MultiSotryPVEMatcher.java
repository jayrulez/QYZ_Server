package multistory;

import common.ErrorCode;
import common.Rpc;
import gnet.ServiceServer;
import lx.gs.map.msg.EnrollBriefInfo;
import lx.matcher.*;
import map.msg.MCreateMap;
import map.msg.XCreateEctypeMultiStory;
import match.Member;
import match.PVEMatcher;
import match.Team;
import serviced.Config;
import xdb.Trace;
import xio.Protocol;

import java.util.*;


/**
 * Created by xiong on 2016/5/10.
 */
public class MultiSotryPVEMatcher extends PVEMatcher {
    public static MultiSotryPVEMatcher creat(int maxNum, long roundInterval, long matchTimeOut){
        Builder b = new Builder();
        b.matchTimeout = matchTimeOut;
        b.roundInterval = roundInterval;
        b.teamMemberNum = maxNum;
        return new MultiSotryPVEMatcher(b);
    }

    public MultiSotryPVEMatcher(Builder b){
        super(b);
    }

    @Override
    protected void onMatchStart(Team team) {
        MAddMultiMatch notify = new MAddMultiMatch();
        notify.err = ErrorCode.OK.getErrorId();
        notify.ectypeid = (int)team.gid;
        team.members.forEach(e-> notify.members.add(e.roleid));
        ServiceServer.getInstance().sendGsdByRoleid(notify.members.get(0), notify);
    }

    @Override
    protected void onMatchError(Team team, ErrorCode err) {
        MAddMultiMatch notify = new MAddMultiMatch();
        notify.err = err.getErrorId();
        team.members.forEach(e -> notify.members.add(e.roleid));
        ServiceServer.getInstance().sendGsdByRoleid(notify.members.get(0), notify);
    }

    @Override
    protected void onMatchSucc(List<Team> teams) {
        Trace.info("MultiStory match succ. teams = {}", teams);
        final int serverid = ServiceServer.getInstance().getRandomServerid();
        Set<Long> roles = new HashSet<>();
        final XCreateEctypeMultiStory msg = new XCreateEctypeMultiStory();
        msg.ectypeid = (int)teams.get(0).gid;
        msg.serverid = Config.getInstance().getServerid();
        msg.istaskectype = common.Utils.toByte(false);
        teams.forEach(team -> {
            team.members.forEach(member ->{
                MultiSotryTeam.MultiMember m = (MultiSotryTeam.MultiMember)member;
                msg.roles.put(m.roleid, m.info.level);
                msg.rolesstar.put(m.roleid, m.info.star);
                msg.roleusedtimes.put(m.roleid, m.info.usedtimes);
                roles.add(m.roleid);
            });
        });

        Rpc.sendRemote(serverid, msg, new Rpc.Callback<XCreateEctypeMultiStory>() {
            @Override
            public void onResult(XCreateEctypeMultiStory argument, Protocol result) {
                final MCreateMap res = (MCreateMap)result;
                if(res.retcode == 0) {
                    final MMatchMultiSuccess re = new MMatchMultiSuccess();
                    re.mapid = res.mapid;
                    re.ectypeid = msg.ectypeid;
                    teams.forEach(team -> {
                        team.members.forEach(member ->{
                            MultiSotryTeam.MultiMember m = (MultiSotryTeam.MultiMember)member;
                            re.members.add(new EnrollBriefInfo(m.roleid, m.info.gender, m.info.name, m.info.profession));
                        });
                    });
                    Set<Integer> gsds = new HashSet<>();
                    roles.forEach(l -> gsds.add(common.Utils.getServeridByRoleid(l)));
                    gsds.forEach(serverid -> ServiceServer.getInstance().sendGsd(serverid, re));
                } else {
                    onTimeout(msg);
                }
            }

            @Override
            public void onTimeout(XCreateEctypeMultiStory argument) {
                for(Team team : teams) {
                    onMatchError(team, ErrorCode.INTERNAL_ERR);
                }
            }
        });
//        FMap.openMultiStoryEctype(roleids, (int)teams.get(0).gid);//尝试开始创建副本
    }

    @Override
    protected void onUnmatchError(long roleid, ErrorCode err) {
        final MAddMultiMatch re = new MAddMultiMatch();
        re.err = err.getErrorId();
        re.members.add(roleid);
        ServiceServer.getInstance().sendGsdByRoleid(roleid, re);
    }


    //取消成功后通知玩家
    @Override
    protected void onTeamUnmatchSucc(Team team) {
        final MCancelMultiMatch re = new MCancelMultiMatch();
        re.err = ErrorCode.OK.getErrorId();
        for(Member member : team.members)
            re.members.add(member.roleid);
        ServiceServer.getInstance().sendGsdByRoleid(team.members.get(0).roleid, re);
    }
}
