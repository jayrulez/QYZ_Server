package map.map;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.ectype.GodMonsterInfo;
import cfg.ectype.RuneInfo;
import cfg.ectype.Spell;
import cfg.fight.PKState;
import cfg.fight.Relation;
import cfg.map.Reason;
import cfg.map.ReviveType;
import common.*;
import map.MapModule;
import map.MapUtils;
import map.agent.*;
import map.controller.Controller;
import map.msg.*;
import map.msg.Bonus;
import map.skill.DamageParam;
import map.skill.DeathParam;
import pathfinding.Vector3;

import java.util.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * Created by xiong on 2016/5/13.
 */
public class FamilyStation extends GameMap {

    public static FamilyStation create(XCreateFamilyStation msg) {
        familyBuilder b = new familyBuilder();
        b.mapid = Utils.makeFamilyId(msg.serverid, MapMgr.Ins.getLocalServerid(), msg.familyid);
        b.serverid = msg.serverid;
        b.familyId = msg.familyid;
        b.familyMembersId.addAll(msg.members);
        b.godAnimalLvl.putAll(msg.godanimallvl);

        final cfg.ectype.EctypeBasic basicCfg = CfgMgr.ectypebasic.get(CfgMgr.familyparty.familyectypeid);
        b.landscapeid = basicCfg.landscapeid;
        b.regionsetid = basicCfg.regionsetid;
        b.scene = basicCfg.scenename;

        b.xcellNum = 0;
        b.zcellNum = 0;

        b.baseUpdateInterval = 100;
        b.nearbyUpdateInterval = 1000;
        b.normalUpdateInterval = 100;
        b.controllerUpdateInterval = 1000;

        b.maxDefencerBodyHeight = 10;
        b.maxDefencerBodyRadius = 4;

        b.innerSightRadius = CfgMgr.roleconfig.oneplayermapinnersightradius;
        b.outerSightRadius = CfgMgr.roleconfig.oneplayermapoutersightradius;

        b.initOrient = MapUtils.createOrient(0);
        b.initPosition = new Vector3(CfgMgr.familystation.bornpositions.x, 0, CfgMgr.familystation.bornpositions.y);
        b.revivePosition = MapUtils.c2p(basicCfg.reviveinfo.position);

        b.useBroadcastPolicy = true;
        return new FamilyStation(b);
    }

    public static class familyBuilder extends Builder {
        public long familyId;
        public Set<Long> familyMembersId = new HashSet<>();
        public Map<Integer, Integer> godAnimalLvl = new HashMap<>();
        public Vector3 revivePosition;
    }

    private cfg.ectype.FamilyStation conf;

    private long familyId;
    //纪录玩家吃的buff数量
    private Map<Long, Map<Integer, Integer>> playerGetRunes = new HashMap<>();
    private Map<Long, Integer> playerEatExps = new HashMap<>();
    //神兽伤害分开统计
    private Map<Integer, Map<Long, Integer>> playerOrPetDamage = new HashMap<>();
    // 随机产生的符咒,用来加buff，加经验，加物品；
    private final List<Long> runes = new LinkedList<>();
    //存放神兽的aid
    private final Set<Long> godMonsters = new HashSet<>();
    private final Set<Long> takePartyRoles = new HashSet<>();
    private final Map<Integer, Integer> godAnimalLvl = new HashMap<>();
    //宴会持续的时间
    private int partyLastTime;
    //刷新时间
    private long refreshTime;
    //神兽下次刷新时间
    private long nextGodAnimalRefreshTime;

    private Map<Long, Integer> roleTarget  = new HashMap<>();

    private Vector3 revivePosition;


    public FamilyStation(familyBuilder b) {
        super(b);

        this.familyId = b.familyId;
        this.godAnimalLvl.putAll(b.godAnimalLvl);
        this.partyLastTime = 0;
        conf = CfgMgr.familystation;
        revivePosition = b.revivePosition;
        long godAnimalStartTime = common.Time.todayZeroTime() + 20 * Time.HOUR_MILLISECONDS;
        long godAnimalEndTime = godAnimalStartTime + CfgMgr.bossconfig.battletime * 1000;
//        System.out.println("开始时间: " + Time.toFormatStr(godAnimalStartTime) + " 结束时间：" + Time.toFormatStr(godAnimalEndTime));
        long now = System.currentTimeMillis();
        if(now < godAnimalStartTime){
            nextGodAnimalRefreshTime = godAnimalStartTime;
        }else if (now > godAnimalEndTime){
            nextGodAnimalRefreshTime = godAnimalStartTime + Time.DAY_MILLISECONDS;
        }else {
            //如果创建家族时，在刷新神兽的时间，那么要直接创建神兽，并将下次刷新时间置为明天
            nextGodAnimalRefreshTime = godAnimalStartTime + Time.DAY_MILLISECONDS;
            refreshGodAnimal(godAnimalEndTime);
        }
    }

    @Override
    public void init() {
        super.init();
        openAllControllers();
    }

    //家族族长和副组长开启仙府聚宴时重置时间
    public void reSetTime(int remainTime) {
        xdb.Trace.debug("family id = {} start the part, remaintime = {}", getFamilyId(), remainTime);
        partyLastTime = remainTime;
        playerGetRunes.clear();
        takePartyRoles.clear();
        playerEatExps.clear();
        refreshTime = System.currentTimeMillis() + conf.refreshinterval * 1000L;
        schedule(() -> partyEnd(), remainTime);
    }

    public void refreshGodAnimal(long endTime) {
        playerOrPetDamage.clear();
        godMonsters.clear();
        int dayInWeek = Time.getWeekDay(getNow());
        if(CfgMgr.bossconfig.monsterinfo.containsKey(dayInWeek + 1)) {
            int bossid = CfgMgr.bossconfig.monsterinfo.get(dayInWeek + 1);
            creatGodAnimalById(bossid);
            schedule(() -> clearGodAnimals(), endTime - getNow());
        }
    }

    public void godAnimalLvlup(int bossid, int lvl) {
        godAnimalLvl.put(bossid, lvl);
    }

    public long getFamilyId() {
        return this.familyId;
    }

    @Override
    protected void update(long now) {
        super.update(now);
        if (partyLastTime > 0) {
            partyLastTime -= getDeltaTime();
            if (now >= refreshTime) {
                refreshSpell(); //到刷新时间后就刷新符咒
                addExp();//经验雨
            }
        } else {
            clearSpell();//最后还要清空残余的符咒
        }
        if(now > nextGodAnimalRefreshTime){
            nextGodAnimalRefreshTime += Time.DAY_MILLISECONDS; //下次刷新在明天此时
            refreshGodAnimal(now + CfgMgr.bossconfig.battletime * 1000L);
        }
    }

    private void refreshSpell() {
        updateRefreshTime();
        clearSpell();
        int runeNums = common.Utils.randomRange(conf.minspellnum, conf.maxspellnum);
        xdb.Trace.info("Creat runes num = {}", runeNums);
        Spell spellmsg = conf.spellmsg;
        List<Integer> runeWeight = spellmsg.runeinfo.stream().map(info -> info.weight).collect(Collectors.toList());
        List<Integer> positionIndexs = new ArrayList<>();
        positionIndexs.addAll(common.Utils.getMutiRandom(0, spellmsg.positions.size(), runeNums));
        for (int i : positionIndexs) {
            int runeIndex = common.Utils.getRandomIndex(runeWeight);
            RuneInfo runeInfo = spellmsg.runeinfo.get(runeIndex);
            createRune(runeInfo.runeid, MapUtils.c2p(spellmsg.positions.get(i)), (rune) -> this.runes.add(rune.getAid()));
        }
    }

    private void addExp(){//增加经验
        players.values().forEach(p -> {
            if((p.isActive() && p.canTakeParty()) || playerEatExps.containsKey(p.getRoleid())){
                int eatTime = playerEatExps.getOrDefault(p.getRoleid(), 0);
                if(eatTime < CfgMgr.familyparty.upperbound) {
                    takePartyRoles.add(p.getRoleid());
                    int exp = CfgMgr.exptable.get(p.getLevel()).partyexp;
                    Bonus bonus = new Bonus();
                    bonus.items.put(CurrencyType.JingYan, exp);
                    p.addBonus(bonus);
                    if(eatTime == 0){
                        p.sendXdb(p.getRoleid(), new MEatFamilyParty());
                    }
                    playerEatExps.put(p.getRoleid(), eatTime + 1);
                }
            }
        });
    }

    /**
     * 通知xdb，所有参加过本次仙府聚宴的玩家今天不能再参加仙府聚宴了
     */
    private void partyEnd(){
        players.values().forEach(p -> {
            if(takePartyRoles.contains(p.getRoleid())){
                p.setHasTakeParty();
            }
        });
        sendXdbServer(new MEndFamilyParty(new ArrayList<>(takePartyRoles)));
    }

    private void clearSpell() {
        if (!runes.isEmpty()) {
            runes.forEach(aid -> {
                Agent agent = getAgent(aid);
                if (agent != null) {
                    leave(agent, Reason.ALIVE_EXPIRE);
                }
            });
            runes.clear();
        }
    }

    private void updateRefreshTime() {
        refreshTime += conf.refreshinterval * 1000L;
    }


    private void creatGodAnimalById(int bossid) {
        xdb.Trace.debug("family = {},Creat boss, id = {}",familyId, bossid);
        //根据不同bossid取到不同等级神兽对应的怪物id
        int lvl = godAnimalLvl.get(bossid);
        cfg.family.BossInfo bossConf = CfgMgr.boss.get(bossid).bossinfo.get(lvl - 1);
        GodMonsterInfo positionInfo = conf.monsters.get(bossid - 1);//下标从0开始
        Monster monster = createMonster(bossConf.monsterid, MapUtils.c2p(positionInfo.position), MapUtils.fixRotation(positionInfo.orientangle));
        monster.addListener(Listener.DEATH, (go, eventid, param) -> {
            DeathParam p = (DeathParam) param;
            Fighter fighter = p.attacker;
            long lastAttackId = fighter.isPlayer() ? ((Player) fighter).getRoleid() : fighter.isPet() ? ((APet) fighter).getOwner().getRoleid() : 0;
            godMonsters.remove(monster.getAid());
            statisticDamage(bossid, lvl, lastAttackId);
//            endGodAnimals();//如果是最后一只神兽死亡，那么活动直接结束
        });
        monster.addListener(Listener.BE_DAMAGE, (go, evtid, param) -> {
            final DamageParam dp = (DamageParam) param;
            if (dp.attacker.isPlayerOrFakePlayer() || dp.attacker.isPet()) {
                long aid = dp.attacker.getAid();
                Map<Long, Integer> roledamages = playerOrPetDamage.getOrDefault(bossid, new HashMap<>());
                int damage = roledamages.getOrDefault(aid, 0);
                roledamages.put(aid, damage + dp.damage);
                playerOrPetDamage.putIfAbsent(bossid, roledamages);
            }
        });
        godMonsters.add(monster.getAid());
    }

    private void clearGodAnimals() {
        if (!godMonsters.isEmpty()) { //如果到点后还有神兽没有杀死,直接移除，并没有奖励
            this.godMonsters.forEach(aid -> {
                Agent agent = getAgent(aid);
                if (agent != null) {
                    leave(agent, Reason.ALIVE_EXPIRE);
                }
            });
            this.godMonsters.clear();
//            endGodAnimals();//超时的话要发送结束消息
        }
    }

    /**
     * 结束后通知服务器,重置结束时间
     */
//    private void endGodAnimals() {
//        sendXdbRole(0, new MGodAnimalActEnd(getFamilyId()));
//    }

    /**
     * 对玩家来说，每种符咒有一个获取上限
     *
     * @param player
     * @param arune
     * @return
     */
    @Override
    public boolean eatRune(Player player, ARune arune) {
        if(!player.canTakeParty() && !playerEatExps.containsKey(player.getRoleid())){
            return false;
        }
        Map<Integer, Integer> eatRunes = playerGetRunes.getOrDefault(player.getRoleid(), new HashMap<>());
        int num = eatRunes.getOrDefault(arune.runeid, 0);
        if (num >= conf.maxbonustimes) {
            player.sendNotRoleMsg(MapMgr.dispatcher.createSError(ErrorCode.HAS_EAT_ENOUGH_RUNE));
            return false;
        } else {
            eatRunes.put(arune.runeid, ++num);
            playerGetRunes.put(player.getRoleid(), eatRunes);
        }
        return super.eatRune(player, arune);
    }

    /**
     * 按角色id统计对神兽造成的伤害，并给前几名发放奖励,并发放最后一击奖励，幸运一击奖励
     */
    private void statisticDamage(int bossid, int bosslvl, long lasthit) {
        Map<Long, Integer> damageByRoleid = new HashMap<>();
        Map<Long, Integer> playerOrPetDamageByBossid = playerOrPetDamage.get(bossid);
        playerOrPetDamageByBossid.forEach((aid, damage) -> {
            final Agent agent = getAgent(aid);
            if(agent != null) {
                final long roleid = agent.isPet() ? ((APet) agent).getOwner().getRoleid() : agent.getAid();
                int stas = damageByRoleid.getOrDefault(roleid, 0);
                damageByRoleid.put(roleid, stas + damage);
            }
        });
        List<Long> maxFourDamage = new ArrayList<>();
        int num = 3;//暂定前三得奖励
        while (num > 0 && !damageByRoleid.isEmpty()) {
            long temproleid = 0;
            int maxdamage = 0;
            for (Map.Entry<Long, Integer> e : damageByRoleid.entrySet()) {
                if (e.getValue() > maxdamage) {
                    maxdamage = e.getValue();
                    temproleid = e.getKey();
                }
            }
            maxFourDamage.add(temproleid);
            damageByRoleid.remove(temproleid);
            num--;
        }

        List<map.msg.SingleRoleBonus> membersGetGiftNum = new ArrayList<>();//记录每个玩家获得的奖励
        //先发放前三的
        cfg.family.BossInfo conf = CfgMgr.boss.get(bossid).bossinfo.get(bosslvl - 1);
        for (int i = 0; i < maxFourDamage.size(); i++) {
            long roleid = maxFourDamage.get(i);
            Player player = players.get(roleid);
            if(player != null) {
                Bonus toXdb = new Bonus();
                common.Bonus.genBonusByProfession(player.getProfession(), conf.dropitem, conf.dropamount.get(i), toXdb);
                sendKillAnimalToPlayer(player, toXdb, bossid);
                membersGetGiftNum.add(new SingleRoleBonus(roleid, player.getName(), toXdb));
            }
        }

        //伤害在前三之后的玩家
        int minNum = conf.dropamount.get(conf.dropamount.size() - 1);
        for (long i : damageByRoleid.keySet()) {
            Player player = players.get(i);
            if(player != null) {
                Bonus toXdb = new Bonus();
                common.Bonus.genBonusByProfession(player.getProfession(), conf.dropitem, minNum, toXdb);
                sendKillAnimalToPlayer(player, toXdb, bossid);
                membersGetGiftNum.add(new SingleRoleBonus(i, player.getName(), toXdb));
            }
        }
        MKillGodAnimal msg = new MKillGodAnimal();
        msg.familyid = this.familyId;
        msg.bossid = bossid;
        msg.bosslvl = bosslvl;
        msg.membersbonus.addAll(membersGetGiftNum);
        Player lastHitPlayer = players.get(lasthit);
        if(lastHitPlayer != null){
            Bonus lasthitBonus = new Bonus();
            common.Bonus.genBonusByProfession(lastHitPlayer.getProfession(), conf.lasthitbonus, 1, lasthitBonus);
            msg.lasthitbonus.add(new SingleRoleBonus(lasthit, lastHitPlayer.getName(), lasthitBonus));
        }
        int randomIndex = common.Utils.randomRange(0, msg.membersbonus.size());
        long luckRoleid = msg.membersbonus.get(randomIndex).roleid;
        Player luckPlayer = players.get(luckRoleid);
        if(luckPlayer != null){
            Bonus luckBonus = new Bonus();
            common.Bonus.genBonusByProfession(luckPlayer.getProfession(), conf.luckybonus, 1, luckBonus);
            msg.luckybonus.add(new SingleRoleBonus(luckRoleid, luckPlayer.getName(), luckBonus));
        }
        sendXdbServer(msg);
        playerOrPetDamage.remove(bossid);
    }

    private void sendKillAnimalToPlayer(Player player, Bonus bonus, int bossid){
        SKillGodAnimal response = new SKillGodAnimal();
        response.bossid = bossid;
        response.bonus = bonus;
        response.isover = 1;
//        if(bossid == 3 || bossid == 4 ){//击杀的最后一只,活动结束
//            response.isover = 1;
//        }else {
//            response.isover = 0;
//        }
        player.sendNotRoleMsg(response);
    }


    @Override
    public boolean admit(long roleid) {
        return true;
    }

    @Override
    public void onAgentEnter(Agent agent) {
    }

    @Override
    public void onAgentLeave(Agent agent) {

    }

    @Override
    public void onPlayerEnter(Player player) {
        player.setPkstate(PKState.PEACE);
    }

    @Override
    public void onPlayerLeave(Player player) {

    }

    @Override
    public void sendPlayerEnter(Player player) {
        player.sendNotRoleMsg(new SAfterEnterFamilyStation(partyLastTime, 0));
    }

    @Override
    public void revivePlayer(Player player, int reviveType) {
        if (!player.isActive() || !player.isDead())
            return;
        switch (reviveType) {
            case ReviveType.CUR_POSITION: {
                player.reviveAtCurPosition();
                break;
            }
            case ReviveType.REVIVE_POSITION: {
                player.revive(revivePosition, getInitOrient());//专门设了一个复活点 和出生点不在一个地方
                break;
            }
            default: {
                xdb.Trace.debug("unknown revivetype:" + reviveType);
            }
        }
    }

    @Override
    public void onPlayerReady(Player player) {

    }

    public void setPlayerEntertoOpen(long roleid, int eventid) {
        roleTarget.put(roleid, eventid);
    }

    @Override
    protected void initPlayerPositionAndOrient(Player player) {
        int eventId = roleTarget.getOrDefault(player.getRoleid(), 0);
        if (eventId == 0) {
            super.initPlayerPositionAndOrient(player);
        } else if (eventId == 1) {//聚宴
            player.initPosition(new Vector3(CfgMgr.familystation.partyposition.x, 0, CfgMgr.familystation.partyposition.y + 4));
            player.initOrient(MapUtils.createOrient(158));
        } else if (eventId == 2) {//黑市商人
            player.initPosition(new Vector3(CfgMgr.familystation.blackmarkeposition.x, 0, CfgMgr.familystation.blackmarkeposition.y));
            player.initOrient(getInitOrient());
        } else if (eventId == 3) {//家族管理员
            player.initPosition(getInitPosition());
            player.initOrient(MapUtils.createOrient(180));
        } else if(eventId == 4){//神兽挑战
            player.initPosition(new Vector3(CfgMgr.familystation.godanimalposition.x, 0, CfgMgr.familystation.godanimalposition.y));
            player.initOrient(getInitOrient());
        }
        roleTarget.remove(player.getRoleid());
    }

    //gm命令强制开启神兽挑战
    public void process(SLaunchFamilyGodAnimal msg) {
        if(godMonsters.size() != 0){ //不能同时有多只
            return;
        }
        refreshGodAnimal(getNow() + CfgMgr.bossconfig.battletime);
    }

    public void process(SGodAnimalLvlup msg) {
        godAnimalLvlup(msg.bossid, msg.bosslvl);
    }

    public void process(XToOpenFamilyParty msg) {
        setPlayerEntertoOpen(msg.roleid, msg.eventid);
    }

    public void process(XLeaveOrKickFamily msg){ //玩家退出家族或者被踢出后，从家族仙府中移除
        if(msg.familyid == this.familyId){
            Player player = players.get(msg.roleid);
            if(player != null){
                internPlayerLeave(player, msg.reason);
            }
        }
    }

    public final void process(SAutoStartParty msg) {//开启家族聚宴，设置时间
        reSetTime(msg.remaintime);
    }

    @Override
    public boolean canRide() {
        return true;
    }
}
