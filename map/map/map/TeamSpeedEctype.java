package map.map;


import cfg.CfgMgr;
import cfg.Const;
import cfg.cmd.action.MultiBonus;
import cfg.ectype.GradeBonus;
import cfg.ectype.RuneInfo;
import cfg.ectype.SpeedRegionInfo;
import cfg.ectype.Spell;
import cfg.fight.CampType;
import cfg.map.Vector2;
import common.ErrorCode;
import common.Utils;
import map.MapUtils;
import map.agent.*;
import map.msg.*;
import map.skill.DamageParam;
import map.skill.DeathParam;
import pathfinding.Vector3;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 鸿蒙争霸
 * @author Jin Shuai
 */
public class TeamSpeedEctype extends Ectype {

    public static TeamSpeedEctype create(XCreateTeamSpeed msg) {
        final Ectype.Builder b = new Ectype.Builder();
        initCommon(b, msg.ectypeid, false, msg.serverid);

        return new TeamSpeedEctype(b, msg.levelgroupid, msg.team1, msg.team2, msg.rewardrole);
    }

    private static class Member {
        private long roleId;
        private int camp;
        private pathfinding.Vector3 bornPosition;

        public Member(long roleId, int camp, Vector3 bornPosition) {
            this.roleId = roleId;
            this.camp = camp;
            this.bornPosition = bornPosition;
        }
    }

    private final HashMap<Long, Member> members = new HashMap<>();

    private final Set<Long> rewardRoles = new HashSet<>();

    private final static int TEAM1_CAMP = CampType.PLAYER_RED;
    private final static int TEAM2_CAMP = CampType.PLAYER_BLUE;
    private final cfg.ectype.SpeedLvMsg lvlCfg;

    private Map<Integer,Integer> teamscore = new HashMap<>();
    private Map<Integer,Long> bossDamage = new HashMap<>();

    private Monster boss = null;
    private final int bossRefreshScore;
    private long bossFullHp = 0;

    public TeamSpeedEctype(Builder b, int groupId, List<Long> team1, List<Long> team2, List<Long> rewardRoles) {
        super(b);
        this.rewardRoles.addAll(rewardRoles);

        this.lvlCfg = CfgMgr.teamspeed.lvmsg_id.get(groupId);
        Vector3 born1 = MapUtils.c2p(lvlCfg.team1.bornposition);
        Vector3 born2 = MapUtils.c2p(lvlCfg.team2.bornposition);

        team1.forEach(roleId -> members.put(roleId, new Member(roleId, TEAM1_CAMP, born1)));

        team2.forEach(roleId -> members.put(roleId, new Member(roleId, TEAM2_CAMP, born2)));

        int max = 0;
        for (SpeedRegionInfo speedRegionInfo : lvlCfg.team1.speedregioninfo) {
            if(speedRegionInfo.needgrade > 0){
                max = Math.max(max, speedRegionInfo.needgrade);
            }
        }
        for (SpeedRegionInfo speedRegionInfo : lvlCfg.team2.speedregioninfo) {
            if(speedRegionInfo.needgrade > 0){
                max = Math.max(max, speedRegionInfo.needgrade);
            }
        }
        bossRefreshScore = max;
    }

    @Override
    public void init() {
        super.init();
        setSuspend();
        onCompeteBegin();
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
        SEctypeMemberEnter ectypeMemberEnter = getEnterInfo(player);
        List<Long> sameCamp = new ArrayList<>();
        int camp = getCamp(player);
        members.forEach((aLong, member) -> {
            if(member.camp == camp){
                sameCamp.add(aLong);
            }
        });
        sameCamp.stream().filter(aLong -> aLong != player.getRoleid())
                .forEach(aLong -> sendToEachother(player, getPlayer(aLong)));
    }

    public boolean admit(long roleid) {
        return notEnd() && members.containsKey(roleid);
    }

    private void onCompeteBegin() {
        clearSuspend();
        startRemainTime();
        refreshMonster();
        refreshRune();
        addListener(Listener.BE_DAMAGE, (host, evtid, param) -> {
            if(host == boss){
                final DamageParam dp = (DamageParam)param;
                if(dp.attacker.isPlayerOrFakePlayerOrPet() && dp.damage > 0) {
                    int camp = getCamp(dp.attacker);
                    long curr = bossDamage.getOrDefault(camp, 0l);
                    long currTotal = bossDamage.getOrDefault(TEAM1_CAMP, 0l) + bossDamage.getOrDefault(TEAM2_CAMP, 0l);
                    if(currTotal + dp.damage > bossFullHp){
                        curr += bossFullHp - currTotal;
                    } else {
                        curr += dp.damage;
                    }
                    bossDamage.put(camp, curr);
                    syncBossDamage();
                }
            }
        });
    }

    private void refreshMonster(){
        lvlCfg.monsters.forEach(speedMonsterRefInfo -> {
            long refreshCd = TimeUnit.SECONDS.toMillis((long) speedMonsterRefInfo.monsters.regeneratecd);
            int monsterId = speedMonsterRefInfo.monsters.monsterid;
            int initNum = speedMonsterRefInfo.monsters.count;
            int totalNum = speedMonsterRefInfo.monsters.regeneratecount;
            int regionId = speedMonsterRefInfo.regionid;
            int killScore = speedMonsterRefInfo.grade;
            refreshMonster0(monsterId, initNum,  regionId, totalNum, refreshCd, killScore);
        });
    }

    private void refreshBoss() {
        int monsterId = lvlCfg.bossref.monsterid;
        int regionId = lvlCfg.bossregion;
        boss = createMonster(monsterId, regionId);
        bossFullHp = boss.getAttrMgr().getHPFullValue();
        boss.addListener(Listener.DEATH, (go, evtid, param) -> {
            checkEnd(ErrorCode.OK);
        });
    }

    private static class Counter{
        private int num;
    }

    private void refreshMonster0(int monsterId, int initNum, int regionId, int totalNum, long refreshDelay, int killScore){
        Counter counter = new Counter();
        counter.num = totalNum;
        for (int i = 0; i < initNum; i++) {
            refreshMonster1(monsterId, regionId, counter, refreshDelay, killScore);
            counter.num--;
        }
    }

    private Monster refreshMonster1(int monsterId, int regionId, Counter counter, long refreshDelay, int killScore){
        Monster monster = createMonster(monsterId, regionId);
        monster.addListener(Listener.DEATH, (host, evtid, p) -> {
            if(counter.num > 0){
                counter.num--;
                schedule(() -> refreshMonster1(monsterId, regionId, counter, refreshDelay, killScore), refreshDelay);
            }
            if(killScore > 0){
                final DeathParam param = (DeathParam) p;
                Player caster = param.getFinalAttackerAsPlayer();
                if (caster != null) {
                    final int casterTeam = getCamp(caster);
                    int newScore = teamscore.getOrDefault(casterTeam, 0) + killScore;
                    teamscore.put(casterTeam, newScore);
                    if( boss == null && newScore >= bossRefreshScore){
                        refreshBoss();
                    }
                    syncScore();
                }
            }
        });
        return monster;
    }

    @Override
    protected int getCamp(Fighter fighter){
        return members.get(fighter.getOwner().getAid()).camp;
    }

    private void syncScore() {
        SSyncTeamSpeedScore score = new SSyncTeamSpeedScore();
        score.teamscore.put(TEAM1_CAMP, teamscore.getOrDefault(TEAM1_CAMP, 0)) ;
        score.teamscore.put(TEAM2_CAMP, teamscore.getOrDefault(TEAM2_CAMP, 0)) ;

        broadcastNotContextMsg(score);
    }

    private void syncBossDamage() {
        SSyncTeamSpeedBossDamage damage = new SSyncTeamSpeedBossDamage();
        if(bossFullHp == 0){
            damage.bossdamage.put(TEAM1_CAMP, 0f);
            damage.bossdamage.put(TEAM2_CAMP, 0f);
            broadcastNotContextMsg(damage);
            return;
        }
        BigDecimal damage1 = new BigDecimal(bossDamage.getOrDefault(TEAM1_CAMP, 0l) * 100);
        BigDecimal damage2 = new BigDecimal(bossDamage.getOrDefault(TEAM2_CAMP, 0l) * 100);
        BigDecimal bossHpBd = new BigDecimal(bossFullHp);
        float rate1 = damage1.divide(bossHpBd, 2).floatValue();
        float rate2 = damage2.divide(bossHpBd, 2).floatValue();
        damage.bossdamage.put(TEAM1_CAMP, rate1);
        damage.bossdamage.put(TEAM2_CAMP, rate2);

        broadcastNotContextMsg(damage);
    }

    private void refreshRune() {
        final Spell runecfg = lvlCfg.spellmsg;
        Vector2 position = Utils.randomChoose(runecfg.positions);
        int randomIndex = Utils.getRandomIndex(runecfg.runeinfo.stream().map(info -> info.weight).collect(Collectors.toList()));
        final RuneInfo runeInfo = runecfg.runeinfo.get(randomIndex);
        final ARune rune = createRune(runeInfo.runeid, MapUtils.c2p(position), null);
        rune.addListener(Listener.LEAVE, (go ,evtid, param) -> {
            schedule(this::refreshRune, runecfg.refreshtime * 1000L);
        });
    }

    @Override
    protected void initPlayerPositionAndOrient(Player player) {
        player.initPosition(members.get(player.getRoleid()).bornPosition);
        player.initOrient(getInitOrient());
    }

    @Override
    protected void doRevive(Player player) {
        pathfinding.Vector3 revivcePos = getRevivePos(getCamp(player));
        if(revivcePos == null){
            player.revive(members.get(player.getRoleid()).bornPosition, player.getOrient());
        } else {
            player.revive(revivcePos, player.getOrient());
        }
    }

    private pathfinding.Vector3 getRevivePos(int camp){
        List<SpeedRegionInfo> speedRegionInfos = new ArrayList<>();
        if(camp == TEAM1_CAMP){
            speedRegionInfos.addAll(lvlCfg.team1.speedregioninfo);
        } else if(camp == TEAM2_CAMP){
            speedRegionInfos.addAll(lvlCfg.team2.speedregioninfo);
        }
        int currScore = teamscore.getOrDefault(camp, 0);
        SpeedRegionInfo target = null;
        for (SpeedRegionInfo speedRegionInfo : speedRegionInfos) {
            if(currScore < speedRegionInfo.needgrade)
                break;
            target = speedRegionInfo;
        }
        return target == null ? null : MapUtils.c2p(target.rebornposition);
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {}

    @Override
    protected void normalUpdate(long now) {
    }

    @Override
    protected void onFail(ErrorCode err) {
        Map<Integer, Integer> resultMap = new HashMap<>();
        resultMap.put(TEAM1_CAMP, Const.FALSE);
        resultMap.put(TEAM2_CAMP, Const.FALSE);

        if(err.ok()) {
            long team1Damage = bossDamage.getOrDefault(TEAM1_CAMP, 0l);
            long team2Damage = bossDamage.getOrDefault(TEAM2_CAMP, 0l);

            int result1 = team1Damage == 0 ? Const.FALSE : team1Damage >= team2Damage ? Const.TRUE : Const.FALSE;
            int result2 = team2Damage == 0 ? Const.FALSE : team2Damage >= team1Damage ? Const.TRUE : Const.FALSE;

            resultMap.put(TEAM1_CAMP, result1);
            resultMap.put(TEAM2_CAMP, result2);
        }

        players.values().forEach(player -> {
            int camp = getCamp(player);
            long roleId = player.getRoleid();

            int teamScore = teamscore.getOrDefault(camp, 0);
            MultiBonus bonus = getBonusByScore(teamScore);
            map.msg.Bonus bonusProto = new Bonus();
            if(bonus != null && rewardRoles.contains(Long.valueOf(roleId))){
                common.Bonus.genBonusByProfession(Const.NULL, bonus, 1, bonusProto);
            }

            int result = resultMap.getOrDefault(camp, Const.FALSE);
            player.sendNotRoleMsg(new SEndTeamSpeed(result, bonusProto));
            player.sendXdb(new MEndTeamSpeed(result, ectypeid, bonusProto));
        });
    }

    private MultiBonus getBonusByScore(int score){
        GradeBonus last = null;
        for (GradeBonus gradebonus : lvlCfg.gradebonus) {
            if(score < gradebonus.grade && last != null){
                return last.bonus;
            }
            last = gradebonus;
        }
        return last != null ? last.bonus : null;
    }

    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
        player.setCamp(CampType.PLAYER);
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterTeamSpeed msg = new SEnterTeamSpeed();
        msg.levelindex = lvlCfg.id;
        msg.teamid = getCamp(player);
        msg.remaintime = remainTime;
        msg.teamscore.putAll(this.teamscore);
        player.sendNotRoleMsg(msg);
    }
}
