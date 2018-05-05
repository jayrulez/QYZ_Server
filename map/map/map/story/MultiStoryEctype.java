package map.map.story;


import cfg.CfgMgr;
import cfg.ectype.StarCondition;
import common.ErrorCode;
import map.agent.Player;
import map.map.MultiPlayerEctype;
import map.msg.*;

import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Created by xiong on 2016/5/5.
 */
public class MultiStoryEctype extends AbstractStory {

    public static MultiStoryEctype create(XCreateEctypeMultiStory builder){
        final Builder b = new Builder();
        initCommon(b, builder);
        return new MultiStoryEctype(b);
    }

    protected static void initCommon(Builder b, XCreateEctypeMultiStory builder){
        initCommon(b, builder.ectypeid, false, builder.serverid);
        b.roles.addAll(builder.roles.keySet());
        b.rolesStar = builder.rolesstar;
        b.rolesUsedTimes = builder.roleusedtimes;
    }

    public static class Builder extends MultiPlayerEctype.Builder{
        public HashMap<Long, Integer> rolesStar; //roleid-star
        public HashMap<Long, Integer> rolesUsedTimes;
    }

    private final HashMap<Long, Integer> rolesStar;
    private final HashMap<Long, Integer> rolesUsedTimes;

    private final cfg.ectype.TeamStoryEctype teamStoryCfg;

    @Override
    public boolean admit(long roleid) {
        return notEnd() && rolesStar.containsKey(roleid);
    }

    public MultiStoryEctype(Builder b) {
        super(b);
        this.rolesStar = b.rolesStar;
        this.rolesUsedTimes = b.rolesUsedTimes;
        this.teamStoryCfg = CfgMgr.teamstoryectype.get(ectypeid);
    }


    public void init() {
        super.init();
        setSuspend();
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
        rolesStar.keySet().stream()
                .filter(aLong -> aLong != player.getRoleid())
                .forEach(aLong -> sendToEachother(player, getPlayer(aLong)));
    }

    @Override
    public void onPlayerLeave(Player player) {
        super.onPlayerLeave(player);
        if(players.size() == 1) {
            addDeferTask(() -> {
                checkEnd(ErrorCode.ALL_PLAYER_HAS_LEAVE);
            });
        }
    }

    @Override
    public void sendPlayerEnter(Player player) {
        SEnterMultiStoryEctype re = new SEnterMultiStoryEctype();
        re.ectypeid = ectypeid;
        re.id = player.getRoleid();
        re.remainrevivecount = basiccfg.reviveinfo.maxcount;
        re.remaintime = remainTime;
        re.enviroments.putAll(this.enviroments);
        re.openlayouts.addAll(layouts.values().stream().map(this::genLayoutInfo).collect(Collectors.toList()));
        re.activeactions.addAll(actions.keySet());
        player.sendNotRoleMsg(re);
    }


    public int getTotalDeadCount(){
        return playerStatInfo.entrySet().stream().filter(e -> cacheAgents.get(e.getKey()).isPlayerOrFakePlayer())
                .mapToInt(e -> e.getValue().deadCount).sum();
    }

    @Override
    protected boolean checkNotExceedMaxDeadCount(Player player, int deadCount) {
        return getTotalDeadCount() <= basiccfg.reviveinfo.maxcount;
    }

    private int calcStar() {
        int star = 0;
        for(cfg.ectype.StarConditionInfo sc : teamStoryCfg.starcondition) {
            switch (sc.condition) {
                case StarCondition.CLEAR: {
                    star++;
                    break;
                }
                case StarCondition.CLEAR_IN_SECONDS: {
                    if(basiccfg.totaltime - (int)(remainTime / 1000) <= sc.value) {
                        star++;
                    }
                    break;
                }
                case StarCondition.DEAD_TIMES_LOWER_THAN: {
                    if(getTotalDeadCount() <= sc.value) {
                        star++;
                    }
                    break;
                }
                default: {
                    throw new RuntimeException("unknown StarCondition:" + sc.condition);
                }

            }
        }
        return star;
    }


    @Override
    protected void onFail(ErrorCode err) {//奖励结算给副本内每个玩家都分别发送消息
        if (err.ok()) {// 如果成功
            final int star = calcStar();
            players.forEach((roleid, player) -> {//正常情况下，玩家不允许离开副本，所以player应该包含所有信息
                final SEndMultiStoryEctype re = new SEndMultiStoryEctype();
                re.errcode = ErrorCode.OK.getErrorId();
                re.star = star;
                re.ectypeid = getEctypeid();
                int profession = player.getProfession();
                if (star == 3 && rolesStar.get(roleid) < 3) {
                    common.Bonus.genBonusByProfession(profession, teamStoryCfg.starbonus, 1, re.bonus);
                }
                //小于限制次数的时候才能获取奖励
                if(rolesUsedTimes.get(roleid) < teamStoryCfg.daylimit.num) {
                    common.Bonus.genBonusByProfession(profession, teamStoryCfg.ectypedrop, 1, re.bonus);
                }
                player.sendNotRoleMsg(re);
                final MEndMultiStoryEctype msg = new MEndMultiStoryEctype();
                msg.result = 1;//成功
                msg.ectypeid = getEctypeid();
                msg.star = star;
                msg.bonus = re.bonus;
                player.sendXdb(msg);
            });
        } else {
            final SEndMultiStoryEctype re = new SEndMultiStoryEctype();
            re.errcode = err.getErrorId();
            re.ectypeid = getEctypeid();
            sendContextMsg(re);
            for (long roleid : rolesStar.keySet()) {
                final MEndMultiStoryEctype msg = new MEndMultiStoryEctype();
                msg.result = 0;//失败也发送是为了清除其匹配状态
                Player.sendXdb(roleid, msg);
            }
        }

    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {
        for(Player p : players.values()){
            if(!p.isDead()){
                return;
            }
        }
        checkEnd(ErrorCode.ECTYPE_MAX_DEAD_COUNT);//都死完了才结束
    }
}
