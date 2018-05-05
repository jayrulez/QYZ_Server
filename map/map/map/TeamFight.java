package map.map;

import cfg.CfgMgr;
import cfg.ectype.EvaluateCondition;
import cfg.ectype.RuneInfo;
import cfg.ectype.Spell;
import cfg.fight.CampType;
import cfg.map.Vector2;
import common.ErrorCode;
import common.Utils;
import map.MapUtils;
import map.agent.*;
import map.buff.effect.InternEffcteIds;
import map.msg.*;
import map.skill.DeathParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/11.
 */
public class TeamFight extends MultiPlayerEctype {

    public static TeamFight create(XCreateTeamFight msg) {
        final Builder b = new Builder();
        initCommon(b, msg.ectypeid, false, msg.serverid);

        b.countDownDurationTime = CfgMgr.teamfight.preparetime * 1000L;
        msg.team1.forEach(m -> b.roles.add(m.roleid));
        msg.team2.forEach(m -> b.roles.add(m.roleid));

        b.levelGroupid = msg.levelgroupid;
        b.team1 = msg.team1;
        b.team2 = msg.team2;

        return new TeamFight(b);
    }

    public static class Builder extends MultiPlayerEctype.Builder {
        public int levelGroupid;
        public List<TeamFightMember> team1, team2;
    }

    private static class Member {
        public TeamFightMember info;
        public int camp;
        public pathfinding.Vector3 bornPosition;
    }

    private final HashMap<Long, Member> members = new HashMap<>();
    private final HashMap<Integer, Integer> teamKillNums = new HashMap<>();

    private final int levelGroupid;
    private final List<TeamFightMember> team1, team2;

    private final static int TEAM1_CAMP = CampType.PLAYER_RED;
    private final static int TEAM2_CAMP = CampType.PLAYER_BLUE;

    private final cfg.ectype.TeamFight tfcfg;
    public TeamFight(Builder b) {
        super(b);

        this.tfcfg = CfgMgr.teamfight;

        this.levelGroupid = b.levelGroupid;
        this.team1 = b.team1;
        this.team2 = b.team2;

        int index = 0;
        for(map.msg.TeamFightMember m : b.team1) {
            final Member member = new Member();
            member.info = m;
            member.camp = TEAM1_CAMP;
            member.bornPosition = MapUtils.c2p(tfcfg.bornpositions.get(index++));
            members.put(m.roleid, member);
        }

        index = 3;
        for(map.msg.TeamFightMember m : b.team2) {
            final Member member = new Member();
            member.info = m;
            member.camp = TEAM2_CAMP;
            member.bornPosition = MapUtils.c2p(tfcfg.bornpositions.get(index++));
            members.put(m.roleid, member);
        }
    }

    @Override
    public void init() {
        super.init();

        addListener(Listener.DEATH, (go, evtid, param) -> {
            final DeathParam dp = (DeathParam)param;
            if(notEnd())
                onPlayerDeath(dp.attacker, dp.defencer);
        });


        addListener(Listener.LEAVE, (go, evtid, param) -> checkOneTeamAllPlayerLeave());

    }

    @Override
    protected void broadcastToSameCamp(Player player) {
        SEctypeMemberEnter ectypeMemberEnter = getEnterInfo(player);
        int camp = getCamp(player);
        List<Long> members = new ArrayList<>();
        if(camp == TEAM1_CAMP){
            team1.forEach(teamFightMember -> members.add(teamFightMember.roleid));
        } else {
            team2.forEach(teamFightMember -> members.add(teamFightMember.roleid));
        }
        members.stream().filter(aLong -> aLong != player.getRoleid())
                .forEach(aLong -> sendToEachother(player, getPlayer(aLong)));
    }

    @Override
    protected void onBattleBegin() {
        schedule(this::refreshRune, CfgMgr.teamfight.runefirstrefreshtime * 1000L);
        players.values().forEach(player -> player.getStateMgr().clearState(InternEffcteIds.NOT_FIGHT));
        checkOneTeamAllPlayerLeave();
    }

    @Override
    protected void initPlayerPositionAndOrient(Player player) {
        player.initPosition(members.get(player.getRoleid()).bornPosition);
        player.initOrient(getInitOrient());
    }

    @Override
    protected void doRevive(Player player) {
        player.revive(MapUtils.c2p(common.Utils.randomChoose(tfcfg.revivepositions)), player.getOrient());
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {

    }

    private void refreshRune() {
        final Spell runecfg = CfgMgr.teamfight.runeinfo;
        Vector2 position = Utils.randomChoose(runecfg.positions);
        int randomIndex = Utils.getRandomIndex(runecfg.runeinfo.stream().map(info -> info.weight).collect(Collectors.toList()));
        final RuneInfo runeInfo = runecfg.runeinfo.get(randomIndex);
        final ARune rune = createRune(runeInfo.runeid, MapUtils.c2p(position), null);
        rune.addListener(Listener.LEAVE, (go ,evtid, param) -> {
            schedule(this::refreshRune, runecfg.refreshtime * 1000L);
        });
    }

    private void onPlayerDeath(Fighter attacker, Fighter defencer) {
        final int killerCamp = defencer.getCamp() != TEAM1_CAMP ? TEAM1_CAMP : TEAM2_CAMP;
        final int killNum = common.Utils.addValue(teamKillNums, killerCamp, 1);
        broadcastNotContextMsg(new SChangeTeamKillNum(killerCamp, killNum));
        if(killNum >= CfgMgr.teamfight.completeneedkillnum) {
            addDeferTask(() -> checkEnd(ErrorCode.OK));
        }
        broadcastNotContextMsg(new SKillEvent(attacker.getOwner().getName(), attacker.getCamp(), defencer.getOwner().getName(), defencer.getCamp(), defencer.isPet() ? ((APet)defencer).getPetkey() : 0));
    }

    @Override
    protected void normalUpdate(long now) {

    }

    private int calcResult() {
        final int deltaKillNum = teamKillNums.getOrDefault(TEAM1_CAMP, 0) - teamKillNums.getOrDefault(TEAM2_CAMP, 0);
        if(deltaKillNum != 0)
            return deltaKillNum;
        long totalDamage1 = 0;
        long totalDamage2 = 0;
        for(Map.Entry<Long, PlayerOrPetStatisticInfo> e : playerStatInfo.entrySet()) {
            final Fighter agent = (Fighter)cacheAgents.get(e.getKey());
            switch (agent.getCamp()) {
                case TEAM1_CAMP: totalDamage1 += e.getValue().damage; break;
                case TEAM2_CAMP: totalDamage2 += e.getValue().damage; break;
            }
        }
        return Long.signum(totalDamage1 - totalDamage2);
    }

    @Override
    protected void onFail(ErrorCode err) {
        final int fightResult = calcResult();
        final int winCamp = fightResult > 0 ? TEAM1_CAMP : (fightResult < 0 ? TEAM2_CAMP : 0);
        int minKillCount = Integer.MAX_VALUE;
        int maxKillCount = Integer.MIN_VALUE;
        int minDamage = Integer.MAX_VALUE;
        int maxDamage = Integer.MIN_VALUE;
        int minDeadCount = Integer.MAX_VALUE;
        int maxDeadCount = Integer.MIN_VALUE;
        int maxContinueKillCount = Integer.MIN_VALUE;

        final Map<Long, PlayerOrPetStatisticInfo> stats = statisticByRoleid();
        for(PlayerOrPetStatisticInfo psi : stats.values()) {
            minKillCount = Math.min(minKillCount, psi.killCount);
            maxKillCount = Math.max(maxKillCount, psi.killCount);
            maxContinueKillCount = Math.max(maxContinueKillCount, psi.maxContinueKillCount);
            minDamage = Math.min(minDamage, psi.damage);
            maxDamage = Math.max(maxDamage, psi.damage);
            minDeadCount = Math.min(minDeadCount, psi.deadCount);
            maxDeadCount = Math.max(maxDeadCount, psi.deadCount);
        }

        final ArrayList<PlayerEvaluate> pes = new ArrayList<>();
        final PlayerEvaluate[] awardPlayers = new PlayerEvaluate[EvaluateCondition.enums.size() + 1];
        for(Member member : members.values()) {
            final long roleid = member.info.roleid;
            final Player player = (Player)cacheAgents.get(roleid);
            if(player == null) continue;
            final PlayerOrPetStatisticInfo psi = stats.get(roleid);
            final PlayerEvaluate pe = new PlayerEvaluate();
            pe.name = player.getName();
            pe.camp = player.getCamp();
            pe.damage = psi.damage;
            pe.continuekill = psi.maxContinueKillCount;
            pe.kill = psi.killCount;

            final boolean[] evalues = new boolean[EvaluateCondition.enums.size() + 1];
            final boolean isMaxDamage = psi.damage > 0 && psi.damage == maxDamage;
            evalues[EvaluateCondition.MAX_KILL] = psi.killCount > 0 && psi.killCount == maxKillCount;
            evalues[EvaluateCondition.MAX_CONTINUE_KILL] = psi.maxContinueKillCount > 0 && psi.maxContinueKillCount == maxContinueKillCount;
            evalues[EvaluateCondition.MAX_DAMAGE_KILL_CONTINUE_KILL] = isMaxDamage
                    && evalues[EvaluateCondition.MAX_KILL] && evalues[EvaluateCondition.MAX_CONTINUE_KILL];
            evalues[EvaluateCondition.MAX_DAMAGE_MIN_KILL] = isMaxDamage && (psi.killCount == minKillCount);
            evalues[EvaluateCondition.MIN_DAMAGE_MAX_KILL] = evalues[EvaluateCondition.MAX_KILL] && psi.damage == minDamage;
            evalues[EvaluateCondition.MVP] = member.camp == winCamp && (awardPlayers[EvaluateCondition.MVP] == null || awardPlayers[EvaluateCondition.MVP].damage < pe.damage);
            for(int i = 1 ; i < evalues.length ; i++) {
                if(evalues[i]) {
                    if(awardPlayers[i] == null) {
                        pe.evaluates.add(i);
                        awardPlayers[i] = pe;
                    } else if(awardPlayers[i].damage < pe.damage) {
                        pe.evaluates.add(i);
                        awardPlayers[i].evaluates.remove((Integer)i);
                        awardPlayers[i] = pe;
                    }
                }
            }
            pes.add(pe);
        }

        for (TeamFightMember member : team1) {
            final long roleid = member.roleid;
            final Player player = getPlayer(roleid);
            if(player != null) {
                onResult(player, member.profession, fightResult, pes);
            } else {
                onQuit(roleid);
            }
        }

        for (TeamFightMember member : team2) {
            final long roleid = member.roleid;
            final Player player = getPlayer(roleid);
            if(player != null) {
                onResult(player, member.profession, -fightResult, pes);
            } else {
                onQuit(roleid);
            }
        }
    }

    protected void onResult(Player player, int profession, int result, ArrayList<PlayerEvaluate> evaluates) {
        final cfg.ectype.TeamFightLevelGroup gcfg = common.Utils.getOrLast(CfgMgr.teamfight.levelgroups, levelGroupid);
        final MEndTeamFight msg = new MEndTeamFight();
        for(PlayerEvaluate pe : evaluates) {
            if(pe.name.equals(player.getName())) {
                msg.evaluates = pe.evaluates;
                break;
            }
        }
        msg.result = result;
        common.Bonus.genBonusByProfession(profession, result > 0 ? gcfg.winbonus : (result < 0 ? gcfg.losebonus : gcfg.drawbonus),
                1, common.Bonus.BindType.BIND, msg.bonus);
        player.sendXdb(msg);
        player.sendNotRoleMsg(new SEndTeamFight(result, msg.bonus, evaluates));
    }

    protected void onQuit(long roleid) {
        final MEndTeamFight msg = new MEndTeamFight();
        msg.quit = 1;
        Player.sendXdb(roleid, msg);
    }

    public void onPrecreatePlayer(PlayerBuilder p) {
        p.basic.camp = members.get(p.basic.basic.agentid).camp;
    }

    private final static int MAX_KILL_NUM = 1000;
    // 如果有一队玩家都离开了副本,结束战斗
    private void checkOneTeamAllPlayerLeave() {
        if(hasEnd()) return;

        boolean existCamp1 = false;
        boolean existCamp2 = false;
        for(Player player : players.values()) {
            switch (player.getCamp()) {
                case TEAM1_CAMP: existCamp1 = true; break;
                case TEAM2_CAMP: existCamp2 = true; break;
            }
        }
        if(!existCamp1) {
            teamKillNums.put(TEAM2_CAMP, MAX_KILL_NUM);
        }
        if(!existCamp2) {
            teamKillNums.put(TEAM1_CAMP, MAX_KILL_NUM);
        }
        if(!existCamp1 || !existCamp2)
            checkEnd(ErrorCode.ONE_TEAM_PLAYERS_ALL_LEAVE);
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterTeamFight msg = new SEnterTeamFight();
        msg.id = getMapid();
        msg.ectypeid = ectypeid;
        msg.state = state;
        msg.remaintime = remainTime;
        msg.mycamp = player.getCamp();
        msg.teamkillnums.putAll(this.teamKillNums);
        player.sendNotRoleMsg(msg);
    }

}
