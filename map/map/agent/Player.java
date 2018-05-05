package map.agent;

import cfg.CfgMgr;
import cfg.Const;
import cfg.ectype.EctypeType;
import cfg.equip.RideType;
import cfg.fight.AgentType;
import cfg.fight.AttrId;
import cfg.fight.PKState;
import cfg.fight.StateType;
import cfg.map.PolygonRegion;
import cfg.map.Reason;
import cfg.pet.FollowInfo;
import cfg.role.GenderType;
import common.ErrorCode;
import gs.Config;
import map.MapUtils;
import map.buff.Buff;
import map.buff.effect.AttrEffect;
import map.buff.effect.Effect;
import map.buff.effect.InternEffcteIds;
import map.map.*;
import map.map.story.AbstractStory;
import map.map.story.Prologue;
import map.msg.*;
import map.skill.Skill;
import pathfinding.NavGraph;
import pathfinding.Vector3;
import xdb.Trace;
import xio.Protocol;

import java.util.*;
import java.util.stream.Collectors;

public class Player extends Fighter {
    public Player(GameMap map, PlayerBuilder b) {
        super(map, b.basic);
        this.pcfg = CfgMgr.profession.get(b.profession);

        this.last = b.last;

        this.serverid = b.serverid;
        this.profession = b.profession;
        this.gender = b.gender;
        this.name = b.name;
        this.familyName = b.familyname;
        this.vipLevel = b.viplevel;
        this.level = b.level;
        this.isTakeParty = b.iscantakeparty;//0没参加，1已参加
        this.taskMonsters = b.taskmonsters;

        this.attrMgr.init(b.attrs);
        this.dressid = b.dressid;

        if (map.canRide()) {
            this.rideid = b.rideid;
            this.ridestatus = b.ridestatus;
            installRide();
        } else {
            this.rideid = 0;
            this.ridestatus = RideType.NONE;
        }

        this.titleid = b.titleid;

        this.fabaoid = b.fabaoid;
        for (int buffid : b.fabaobuffs) {
            this.fabaoBuffs.put(buffid, Buff.installFabaoBuff(this, buffid));
        }

        this.equips = b.equips;
        this.skillMgr.init(b.gender == GenderType.MALE ? pcfg.modelname : pcfg.modelname2, pcfg.talismanactionname,
                b.roleskills, b.fabaoskills, b.amulets, b.cur.info2.skills);

        this.pets = b.pets;
        this.petCtxs = b.cur.info2.pets;

        this.team = b.team;
        this.pkstate = b.pkstate;

        this.combatPower = b.combatpower;
        this.ready = b.ready != 0;
        if (!this.ready)
            this.stateMgr.setStateForever(InternEffcteIds.ENTER_PROTECT, StateType.ENTER_PROTECT);

        this.offline = false;

        this.lastRecvMsgTime = map.getNow();

        this.totalBonus = new ArrayList<>();

        this.attrMgr.initHpMp(b.cur.info2.hp == 0 && b.cur.info2.deathtime == 0 ? Const.NULL : b.cur.info2.hp,
                b.cur.info2.mp == 0 && b.cur.info2.deathtime == 0 ? Const.NULL : b.cur.info2.mp);
        if (this.attrMgr.getHPValue() <= 0) {
            setDead();
        }
        this.totalRemainSend = CfgMgr.roleconfig.broadcastlimit.maxtotalremain;
    }

    private final int serverid;
    private final int profession;
    private final int gender;
    private final cfg.role.Profession pcfg;

    private final ArrayList<RoleMapContext> last;

    private String name;
    private String familyName;
    private int vipLevel;
    private int level;
    private int isTakeParty;
    private Set<Integer> taskMonsters = new HashSet<>();

    private int dressid;

    private int rideid;
    private int ridestatus;
    private Effect rideSpeedEffect;
    private Effect rideFlyEffect;


    private int titleid;
    private int fabaoid;
    private Map<Integer, Buff> fabaoBuffs = new HashMap<>();
    private ArrayList<EquipBrief> equips;

    private ArrayList<PetBuilder> pets;
    private Map<Integer, APet> activePets = new HashMap<>();
    private Map<Integer, PetMapContext> petCtxs = new HashMap<>();


    private int combatPower;

    private map.msg.Team team;

    private int pkstate;

    private boolean ready;
    private boolean offline;

    private Vector3 lastClientPosition;
    private long lastResetStatisticMoveTime;
    private double totalServerMoveDistance;
    private double totalClientMoveDistance;


    final public long getRoleid() {
        return getAid();
    }

    public final int getGender() {
        return gender;
    }

    public final int getServerid() {
        return serverid;
    }

    public final int getProfession() {
        return profession;
    }

    public int getVipLevel() {
        return vipLevel;
    }

    @Override
    public final int getLevel() {
        return level;
    }

    public final String getName() {
        return name;
    }

    public final map.msg.Team getTeam() {
        return team;
    }

    public final boolean isSameTeam(Player other) {
        return team.teamid != 0 && team.members.contains(other.getRoleid());
    }

    public final String getFamilyName() {
        return familyName;
    }

    public final boolean isSameFamily(Player other) {
        return !familyName.isEmpty() && familyName.equals(other.familyName);
    }

    public final Map<Integer, APet> getActivePets() {
        return activePets;
    }


    public int getPkstate() {
        return pkstate;
    }

    public void setPkstate(int pkstate) {
        this.pkstate = pkstate;
    }

    @Override
    public final boolean isMyTeamMate(Fighter other) {
        if(this.team.members.isEmpty()) {
            return other.getOwner() == this;
        } else {
            return this.team.members.contains(other.getOwner().getAid());
        }
    }

    public boolean isReady() {
        return ready;
    }

    public boolean isOnline() {
        return !offline;
    }

    public boolean canTakeParty(){
        return isTakeParty == 0;
    }

    public void setHasTakeParty(){
        isTakeParty = 1;
    }

    public boolean isTaskMonsters(int monsterid){
        return taskMonsters.contains(monsterid);
    }

    public map.msg.RoleMapContext getLastRoleMapContext() {
        return last.isEmpty() ? null : last.get(0);
    }

    public void setReady() {
        if (!this.ready) {
            this.ready = true;
            getStateMgr().clearState(InternEffcteIds.ENTER_PROTECT);
            activePets.values().forEach(pet -> pet.getStateMgr().clearState(InternEffcteIds.ENTER_PROTECT));
        }
    }

    public void fillMapContext(RoleMapContext ctx) {
        ctx.isnew = 0;
        final RoleMapInfo1 info1 = ctx.info1;
        info1.mapid = map.getMapid();
        info1.position = MapUtils.p2m(getPosition());
        info1.orient = MapUtils.p2m(getOrient());
        info1.ridestatus = ridestatus;

        final RoleMapInfo2 info2 = ctx.info2;
        info2.deathtime = deathTime;
        info2.hp = (int)attrMgr.getHPValue();
        info2.mp = (int)attrMgr.getMPValue();

        skillMgr.fillContext(info2.skills);

        info2.pets.putAll(petCtxs);
        for(APet pet : activePets.values()) {
            info2.pets.put(pet.getPetkey(), pet.fillMapContext(new PetMapContext()));
        }
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //
    // 			视野更新相关
    //
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    // 这两个变量定义在这里纯粹因为 foreachNearbyInAgentMap闭包无法访问栈变量
    public HashMap<Agent, MySubscriptInfo> mySubscriptAgents = new HashMap<>();
    private int newNearbyAgentNum;

    public final void updateNearbyPlayer() {
        assert (isPlayer());
        mySubscriptAgents.values().forEach(a -> a.stillSubscript = false);
        newNearbyAgentNum = 0;
        final int outerSightRadius = map.getOuterSightRadius();
        final int squareInnerSightRadius = map.getInnerSightRadius() * map.getInnerSightRadius();
        getMap().foreachNearbyInAgentMap(this, outerSightRadius, a -> {
            MySubscriptInfo si = mySubscriptAgents.get(a);
            if (si != null) {
                ++newNearbyAgentNum;
                si.stillSubscript = true;
            } else if (a.getPosition().getSubXZSquare(this.getPosition()) < squareInnerSightRadius) {
                ++newNearbyAgentNum;
                a.addSubscriptMePlayer(this);
                mySubscriptAgents.put(a, new MySubscriptInfo(a, true));
                onAgentEnter(a);
            }
        });

        // 说明有agent离开玩家视野
        if (newNearbyAgentNum < mySubscriptAgents.size()) {
            final List<Agent> leaveAgents = new ArrayList<Agent>();
            for (MySubscriptInfo info : mySubscriptAgents.values()) {
                if (!info.stillSubscript) {
                    leaveAgents.add(info.agent);
                    info.agent.removeSubscriptMePlayer(this);
                }
            }
            mySubscriptAgents.keySet().removeAll(leaveAgents);
            onAgentsLeave(leaveAgents);
        }
    }

    public void updateKnownHp(Fighter other, int hp) {
        final MySubscriptInfo si = mySubscriptAgents.get(other);
        if(si != null)
            si.lastHp = hp;
    }

    public boolean isKnownHp(Fighter other, int hp) {
        final MySubscriptInfo si = mySubscriptAgents.get(other);
        return si == null || si.lastHp == hp;
    }

    public void updateAttackRecords(long now) {
        final long expireTime = now - GameMap.ATTACK_EXPIRE_TIME;
        recentAttackMe.values().removeIf(t -> t < expireTime);
        recentMeAttack.values().removeIf(t -> t < expireTime);
    }

    private final HashMap<Agent, Long> recentAttackMe = new HashMap<>();
    private final HashMap<Agent, Long> recentMeAttack = new HashMap<>();

    public void onBeAttackByOther(Fighter other) {
        recentAttackMe.put(other, getNow());
    }

    public void onAttackOther(Fighter other) {
        recentMeAttack.put(other, getNow());
    }

    public boolean isRecentAttackByOther(Agent other, int inRecentSeconds) {
        return recentAttackMe.getOrDefault(other, 0L) > getNow() - inRecentSeconds * 1000;
    }

    public boolean isRecentMeAttack(Agent other, int inRecentSeconds) {
        return recentMeAttack.getOrDefault(other, 0L) > getNow() - inRecentSeconds * 1000;
    }

    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////
    //
    // 			消息发送相关
    //
    //////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////


    private long totalRemainSend;
    private long necessarySend;
    private long unnecessarySend;

    private final static long maxSendPerSecond = CfgMgr.roleconfig.broadcastlimit.maxsendpersecond;

    public final boolean checkSend(Agent from, xio.Protocol msg) {
        if(from.getOwner() == this) {
           sendNecessary();
            return true;
        } else if(necessarySend + unnecessarySend < maxSendPerSecond && totalRemainSend > 0) {
            sendUnnecessary();
            return true;
        }
        return false;
    }

    private void sendNecessary() {
        ++necessarySend;
        --totalRemainSend;
    }

    private void sendUnnecessary() {
        ++unnecessarySend;
        --totalRemainSend;
    }

    public void updateSend() {
        final cfg.role.BroadcastLimit b = CfgMgr.roleconfig.broadcastlimit;
//        if(getLevel() == 36) {
//            System.out.printf("roleid:%10s totalne:%10s totalun:%10s necessarysend:%10s unnecessarysend:%10s totalremainsend:%10s curremainsend:%10s \n"
//                    , getRoleid(), totalNecessarySend, totalUnnecessarySend, necessarySend, unnecessarySend,
//                    totalRemainSend, (maxSendPerSecond - necessarySend - unnecessarySend));
//        }
        totalRemainSend = Math.min(totalRemainSend + b.addremainpersecond, b.maxtotalremain);
        unnecessarySend = 0;
        necessarySend = 0;
    }

    public final void send(long roleid, Protocol proto) {
        Trace.debug("Player.send roleid:{} {}{}", roleid, proto.getClass().getName(), proto);
        if (offline || !isActive()) {
            Trace.debug("Player.send. player isn't active or is offline. ignore");
            return;
        }
        MapMgr.dispatcher.sendClient(getRoleid(), GameMap.makeRoleProtocol(roleid, proto));

        sendNecessary();
    }


    public final void sendNotRoleMsg(xio.Protocol proto) {
        MapMgr.dispatcher.sendClient(getRoleid(), proto);
        Trace.debug("player:{} sendNotRoleMsg {}{}", this, proto.getClass().getName(), proto);
        sendNecessary();
    }

    public final void sendError(ErrorCode err) {
        sendNotRoleMsg(MapMgr.dispatcher.createSError(err));
    }

    public final void onAgentEnter(Agent agent) {
        assert (isPlayer());
        send(agent.getAid(), agent.createSelfEnter());
    }

    public final void onAgentsLeave(List<Agent> agents) {
        assert (isPlayer());
        final SNearbyAgentLeave re = new SNearbyAgentLeave();
        for (Agent agent : agents) {
            re.agentids.add(agent.getAid());
        }
        this.sendNotRoleMsg(re);
    }

    @Override
    protected void onDead(Fighter attacker) {
        super.onDead(attacker);
        if(this.rideSpeedEffect != null) {
            buffMgr.removeEffect(this.rideSpeedEffect);
            this.rideSpeedEffect = null;
        }
    }

    @Override
    protected void onRevive(pathfinding.Vector3 position, pathfinding.Vector3 orient) {
        super.onRevive(position, orient);
        installRide();
    }

    @Override
    public xio.Protocol createSelfEnter() {
        final SNearbyPlayerEnter re = new SNearbyPlayerEnter();
        re.playerid = getRoleid();
        re.serverid = serverid;
        re.profession = profession;
        re.gender = gender;

        re.name = name;
        re.level = level;
        re.viplevel = vipLevel;
        re.familyname = familyName;

        re.dressid = dressid;
        re.rideid = rideid;
        re.ridestatus = ridestatus;
        re.titleid = titleid;
        re.fabaoid = fabaoid;

        re.equips = equips;
        this.buffMgr.createEffectInfos(re.effects);

        re.pkstate = pkstate;

        fillFighterCommon(re.fightercommon);

        return re;
    }

    public pathfinding.Vector3 calcPositionByOrientAndRelatePosition(Vector3 relatePosition) {
        return getPosition().plus(relatePosition.rotateForword(getOrient()));
    }

    // index in [0, petNum)
    public pathfinding.Vector3 getPetDefaultRelateOwnerPosition(int petNum, int index) {
        final List<FollowInfo> followInfos = CfgMgr.petconfig.follow_petamount.get(petNum).followlist;
        final FollowInfo pcfg = followInfos.get(index);
        return new Vector3(0, 0, pcfg.distance).rotateXZAngle(-pcfg.degree);
    }

    private APet createPet(PetBuilder pb, pathfinding.Vector3 defaultRelateOwnerPosition) {
        pb.basic.camp = getCamp();
        final AgentBuilder b = pb.basic.basic;
        b.orient = MapUtils.p2m(getOrient());
        pb.defaultrelateownerposition = MapUtils.p2m(defaultRelateOwnerPosition);
        b.position = MapUtils.p2m(calcPositionByOrientAndRelatePosition(defaultRelateOwnerPosition));
        final APet pet = map.createPet(this, pb);
        if (!isReady())
            pet.getStateMgr().setStateForever(InternEffcteIds.ENTER_PROTECT, StateType.ENTER_PROTECT);
        pet.setActive(ridestatus != RideType.FLY);
        activePets.put(pet.getPetkey(), pet);
        return pet;
    }

    @Override
    public void onEnterDone() {
        super.onEnterDone();
        int i = 0;
        for (PetBuilder pb : pets) {
            createPet(pb, getPetDefaultRelateOwnerPosition(pets.size(), i++));
        }
        this.lastClientPosition = getPosition();
        this.lastResetStatisticMoveTime = getNow();
    }

    @Override
    public void onLeave() {
        activePets.values().forEach(pet -> {
            map.leave(pet, Reason.FOLLOW_OWNER);
        });
        activePets.clear();
    }

    public final void sendXdb(xio.Protocol msg) {
        MapMgr.dispatcher.sendXdbRole(getRoleid(), msg);
    }


    public final static void sendXdb(long roleid, xio.Protocol msg) {
        MapMgr.dispatcher.sendXdbRole(roleid, msg);
    }

    public void addBonus(Bonus bonus) {
        sendXdb(new MAddBonus(bonus));
    }

    public static void addBonus(long roleid, Bonus bonus) {
        sendXdb(roleid, new MAddBonus(bonus));
    }

    private boolean needSync = false;
    private HashMap<Integer, Integer> killMonsters = new HashMap<>();

    public final void onKillMonster(int monsterid) {
        checkSync();
        common.Utils.addValue(killMonsters, monsterid, 1);
    }

    private long lastSyncTime = 0;
    private void checkSync() {
        if (!needSync) {
            needSync = true;
            scheduleAlwaysExecute(() -> {
                needSync = false;
                lastSyncTime = getNow();

                final MPlayerKillMonster msg = new MPlayerKillMonster();
                if (!killMonsters.isEmpty()) {
                    final HashMap<Integer, Integer> temp = msg.monsters;
                    msg.monsters = killMonsters;
                    killMonsters = temp;
                }
                msg.inworld = common.Utils.toByte(map.isWorldMap());
                if (!totalKillMonsterExp.isEmpty()) {
                    msg.baseexp = totalKillMonsterExp;
                    totalKillMonsterExp = new ArrayList<>();
                }
                msg.expbonusrate = map.getExpBonusRate();
                if (!totalKillMonsterCurrency.isEmpty()) {
                    msg.currency = totalKillMonsterCurrency;
                    totalKillMonsterCurrency = new ArrayList<>();
                }
                msg.pets.addAll(activePets.keySet());

                if(!totalBonus.isEmpty()) {
                    msg.bonuss = totalBonus;
                    totalBonus = new ArrayList<>();
                }

                sendXdb(msg);
            }, lastSyncTime + 20000 < getNow() ? 500 : (long) (CfgMgr.roleconfig.batchsynckillmonsterdelay * 1000));
        }
    }

    private ArrayList<Integer> totalKillMonsterCurrency = new ArrayList<>();
    private ArrayList<Integer> totalKillMonsterExp = new ArrayList<>();

    public final void onAsistKillMonster(int exp, int currency) {
        checkSync();
        if (exp > 0) {
            totalKillMonsterExp.add(exp);
        }
        if (currency > 0) {
            totalKillMonsterCurrency.add(currency);
        }
    }

    private ArrayList<map.msg.Bonus> totalBonus;

    public final void addMonsterDropBonus(Bonus bonus) {
        checkSync();
        totalBonus.add(bonus);
    }

    private long lastRecvMsgTime;

    public void refreshIdle() {
        this.lastRecvMsgTime = getNow();
    }

    public void reenter() {
        map.sendPlayerEnter(this);
        this.offline = false;

        for (Agent a : mySubscriptAgents.keySet()) {
            a.removeSubscriptMePlayer(this);
        }
        mySubscriptAgents.clear();
        updateNearbyPlayer();
//        for(MySubscriptInfo info : mySubscriptAgents.values()) {
//            send(info.agent.getAid(), info.agent.createSelfEnter());
//        }
    }

    public void addServerMoveDistance(double distance) {
        this.totalServerMoveDistance += distance;
    }

    protected void updateWhileAlive(long now) {
        this.totalServerMoveDistance += moveMgr.calcDeltaMoveDistance();
        //Trace.info("roleid:{} now:{} totalservermovedistance:{} totaltime:{}", getRoleid(), now, totalServerMoveDistance, lastResetStatisticMoveTime);
        super.updateWhileAlive(now);
    }

    public boolean checkIdleExpire(long now) {
        return lastRecvMsgTime + 30 * 60 * 1000L < now;
    }

//    private long lastResetStatisticTime;
//    private double totalDeltaDistance;
//    private void resetDiffMovement() {
//        Trace.info("Player:{} resetDiffMovement totalDeltaDistance:{}", this, totalDeltaDistance);
//        this.lastResetStatisticTime = System.currentTimeMillis();
//        this.totalDeltaDistance = 0;
//    }
//
//
//
//    public boolean checkClientPositionDelta(map.msg.Vector3 pos) {
//        double delta = getPosition().getSubXZMagnitude(MapUtils.m2p(pos));
//        totalDeltaDistance += delta;
//        return totalDeltaDistance < 30;
//    }

    ///////////////////////////////////////////////////////////////////////////////
    //
    //  		来自其他系统的变化通知到map
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void process(CTestNetwork msg) {
        if(Config.getInstance().isDebug()) {
            final STestNetWork re = new STestNetWork();
            for(int i = 0 ; i < 5 ;i++)
                re.datas.add(i);
            broadcastToNearby(re);
        }
    }

    public void process(XGetPlayerLocation msg) {
        final MGetPlayerLocation re = new MGetPlayerLocation();
        re.queryroleid = msg.queryroleid;
        re.maptype = map.getType();
        if (re.maptype == EctypeType.WORLD) {
            World world = (World) map;
            re.worldid = world.getWorldid();
            re.lineid = world.getLineid();
            re.position = MapUtils.p2m(getPosition());
        }
        sendXdb(re);
    }

    public void process(XOffline msg) {
        this.offline = true;
        for (Agent a : mySubscriptAgents.keySet()) {
            a.removeSubscriptMePlayer(this);
        }
        mySubscriptAgents.clear();
    }

    public void process(CSetMessgeMode msg) {
        //setMode(msg.mode);
    }

    public void process(XUseItem msg) {
        if (canBeheal()) {
            sendXdb(new MUseItem(msg.pos, msg.usenumber));
        } else {
            Trace.error("player:{} can't use item now", this);
        }
    }

//	public void process(XUseItemByItemKey msg) {
//		if(stateMgr.isEnable(AbilityType.USE_ITEM)) {
//			sendXdb(new MUseItemByItemKey(msg.itemkey, msg.num));
//		} else {
//			Trace.error("player:{} can't use item:{} now", this, msg);
//		}
//	}

    public void process(XUseMedicine msg) {
        final cfg.item.ItemMedicine itemcfg = (cfg.item.ItemMedicine) CfgMgr.itembasic.get(msg.itemid);
        if (itemcfg != null) {
            if (itemcfg.hp > 0)
                attrMgr.addHPValue(itemcfg.hp);
            if (itemcfg.mp > 0)
                attrMgr.addMPValue(itemcfg.mp);
            if (itemcfg.buffid > 0)
                Buff.installNotSkillHitPointBuff(this, itemcfg.buffid);
        }
    }

    public final void process(XTransfer msg) {
        final Vector3 newPos = MapUtils.m2p(msg.position);
        addServerMoveDistance(newPos.getSubXZMagnitude(getPosition()));
        moveMgr.stopAtPosition(newPos, MapUtils.m2p(msg.orient));
    }

    public final void process(XResetRoleParty msg){
        isTakeParty = 0;
    }

    public final void process(SChangeRawAttrs msg) {
        if(map instanceof Prologue) return;
        combatPower = msg.combatpower;
        attrMgr.changeAttrs(msg.attrs, msg.resethpmp != 0);
    }

    public final void process(SChangeName msg) {
        this.name = msg.name;
        broadcastToNearby(msg);
    }

    public final void process(SChangeLevel msg) {
        this.level = msg.level;
        broadcastToNearby(msg);
    }

    public final void process(SChangeVipLevel msg) {
        this.vipLevel = msg.level;
        broadcastToNearby(msg);
    }

    public final void process(SChangeFamily msg) {
        if (!this.familyName.equals(msg.name)) {
            this.familyName = msg.name;
            broadcastToNearby(msg);
        }
    }

    public final void process(SStartFamilyParty msg) {//开启家族聚宴，设置时间
        FamilyStation familyMap = (FamilyStation) map;
        familyMap.reSetTime(msg.remaintime);
    }

    public void process(SChangeDress msg) {
        if (this.dressid != msg.dressid) {
            this.dressid = msg.dressid;
            broadcastToNearby(msg);
        }
    }

    private void uninstallRide() {
        if (this.rideSpeedEffect != null) {
            this.buffMgr.removeEffect(this.rideSpeedEffect);
            this.rideSpeedEffect = null;
            this.stateMgr.clearState(InternEffcteIds.RIDE);
        }
    }

    private void installRide() {
        if (this.rideid > 0) {
            final cfg.equip.Riding rcfg = CfgMgr.riding.get(this.rideid);
            if (rcfg == null) return;
            switch (this.ridestatus) {
                case RideType.FLY: {
                    this.rideSpeedEffect = new AttrEffect(InternEffcteIds.RIDE, this, AttrId.MOVE_SPEED, rcfg.flyspeed - attrMgr.getRawMoveSpeed());
                    this.stateMgr.setStateForever(InternEffcteIds.RIDE, StateType.RIDE_FLY);
                    buffMgr.addEffect(this.rideSpeedEffect);
                    break;
                }
                case RideType.WALK: {
                    this.rideSpeedEffect = new AttrEffect(InternEffcteIds.RIDE, this, AttrId.MOVE_SPEED, rcfg.ridespeed - attrMgr.getRawMoveSpeed());
                    this.stateMgr.setStateForever(InternEffcteIds.RIDE, StateType.RIDE_WALK);
                    buffMgr.addEffect(this.rideSpeedEffect);
                    break;
                }
                default: {
                    this.rideSpeedEffect = null;
                }
            }
        }

        final boolean active = this.ridestatus != RideType.FLY;
        activePets.values().forEach(pet -> pet.setActive(active));
    }

    public void process(SChangeTitle msg) {
        if (this.titleid != msg.titleid) {
            this.titleid = msg.titleid;
            broadcastToNearby(msg);
        }
    }

    public void process(SChangeEquip msg) {
        this.equips = msg.equips;
        broadcastToNearby(msg);
    }

    public void process(XPlayCGSetState msg) {
        getStateMgr().setState(InternEffcteIds.PLAY_CG, StateType.PLAY_CG, 30 * 1000L);//播放cg保护状态时间30s
        xdb.Trace.debug("Add protect state when playing taskCG, taskid = {}", msg.taskid);
    }

    public void process(XChangeTalisman msg) {
        if (this.fabaoid != msg.talismanmodelid) {
            this.fabaoid = msg.talismanmodelid;
            broadcastToNearby(new SChangeFabao(msg.talismanmodelid));
        }
        skillMgr.changeFabaoSkills(msg.skills);

        fabaoBuffs.keySet().stream().filter(b -> !msg.buffs.contains(b)).collect(Collectors.toList())
                .forEach(b -> {
                    fabaoBuffs.remove(b).uninstall();
                });
        msg.buffs.stream().filter(b -> !fabaoBuffs.containsKey(b)).forEach(b -> fabaoBuffs.put(b, Buff.installFabaoBuff(this, b)));
    }

    public final void process(SEquipPet msg) {
        final long petid = msg.pet.basic.basic.agentid;
        final APet pet = activePets.get(petid);
        if (pet != null) {
            xdb.Trace.error("SEquipPet. petid:{} has been in gamemap:{}", petid, map);
            return;
        }

        assert (activePets.isEmpty());
        createPet(msg.pet, getPetDefaultRelateOwnerPosition(1, 0));
    }

    public final void process(SUnequipPet msg) {
        Trace.info("SUnequipPet. petid:{}", msg.petmodelid);
        final APet pet = activePets.remove(msg.petmodelid);
        if (pet != null) {
            map.leave(pet, Reason.UNEQIUP);
            petCtxs.put(msg.petmodelid, pet.fillMapContext(new PetMapContext()));
        }
    }

    public final void process(XChangeAmulet msg) {
        Trace.info("SChangeAmulet player:{} amulets:{}", this, msg.amulets);
        skillMgr.changeAmulets(msg.amulets);
    }

    public final void process(XCreateMonsterAtPlayerPosition msg) {
        for (int i = 0; i < msg.num; i++) {
            map.createMonster(msg.monsterid, getPosition(), getOrient());
        }
    }

    public final void process(XChangePKState msg) {
        if (map.isWorldMap()) {
            final World world = (World) map;
            if (world.getWorldid() == msg.worldid && world.getWorldmapCfg().allowpk
                    && PKState.enums.contains(msg.pkstate) && this.pkstate != msg.pkstate) {
                setPkstate(msg.pkstate);
                broadcastToNearby(new SChangePKState(msg.pkstate));
            }
        }
    }

    public final void process(XCheckWorldRevive msg) {
        if (isActive() && isDead() && map.isWorldMap()) {
            reviveAtCurPosition();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////
    //
    //  		来自玩家的协议通知到map
    //
    ///////////////////////////////////////////////////////////////////////////////

    public void process(CFindPath msg) {
        if (!validPos(msg.src) || !validPos(msg.dst)) {
            Trace.error("invalid src or dst");
            return;
        }

        SFindPath re = new SFindPath();
        final NavGraph.Path path = map.findPath(MapUtils.m2p(msg.src), MapUtils.m2p(msg.dst));
        if (path != null) {
            path.vertexs.stream().map(MapUtils::p2m).forEach(re.path::add);
        }
        sendNotRoleMsg(re);
    }

    public boolean validOrient(map.msg.Vector3 pos) {
        return Float.isFinite(pos.x) && Float.isFinite(pos.y) && Float.isFinite(pos.z);
    }

    public boolean validPos(map.msg.Vector3 pos) {
        return Float.isFinite(pos.x) && Float.isFinite(pos.y) && Float.isFinite(pos.z);
    }

    private final static double initMoveDelta = CfgMgr.roleconfig.initmovedelta;
    private final static double addMoveDetalRate = CfgMgr.roleconfig.addmovedeltapersecond * 0.001;
    private final static long checkMoveInterval = CfgMgr.roleconfig.checkmoveinterval * 1000;
    private final static double maxMoveDelta = initMoveDelta + addMoveDetalRate * checkMoveInterval;
    private final static double maxPosDelta = CfgMgr.roleconfig.maxpositiondelta;

    private boolean checkClientMove(Vector3 newPos) {
        final long now = getNow();
        final double newTotalClientMoveDistance = totalClientMoveDistance + newPos.getSubXZMagnitude(lastClientPosition);
        final boolean result;
        final double deltaDistance = newTotalClientMoveDistance - (totalServerMoveDistance + initMoveDelta + (now - lastResetStatisticMoveTime) * addMoveDetalRate);
        if(deltaDistance > 0) {
            result = false;
        } else if(deltaDistance > - maxMoveDelta) {
            result = true;
            totalClientMoveDistance = newTotalClientMoveDistance;
        } else {
            final double deltaPos = newPos.getSubXZMagnitude(getPosition());
            if(deltaPos < maxPosDelta) {
                result = true;
                totalClientMoveDistance = totalServerMoveDistance;
            } else {
                result = false;
            }
        }
//        if(gs.Utils.isTest() && getLevel() == 36) {
//            System.out.printf("[%s] roleid:%s client:%8.2f new client:%8.2f service:%8.2f time since last reset:%s\n", result ? "====" : "####", getRoleid(),
//                    totalClientMoveDistance, newTotalClientMoveDistance, totalServerMoveDistance, now - lastResetStatisticMoveTime);
//            Trace.debug("[{}] roleid:{} client:{} new client:{} service:{} last reset:{}", result ? "====" : "####", getRoleid(),
//                    totalClientMoveDistance, newTotalClientMoveDistance, totalServerMoveDistance, now - lastResetStatisticMoveTime);
//        }
        if(lastResetStatisticMoveTime + checkMoveInterval < now) {
            lastResetStatisticMoveTime = getNow();
            totalClientMoveDistance = 0;
            totalServerMoveDistance = 0;
        }
        lastClientPosition = result ? newPos : getPosition();
        return result;
    }

    public void process(CStop msg) {
        if(!(validPos(msg.position) && validPos(msg.orient))) {
            Trace.debug("invalid position or orient");
            return;
        }
        if(!canMove()) {
            Trace.debug("player:{} can't move now", this);
            return;
        }

        final Vector3 newPos = MapUtils.m2p(msg.position);
        if(!checkClientMove(newPos)) {
            Trace.debug("checkClientPositionDelta fail.");
            moveMgr.stopAtCurPosition();
            return;
        }

        moveMgr.stopAtPosition(newPos, MapUtils.m2p(msg.orient), true);
    }

    public void process(CMove msg) {
        if(!(validPos(msg.position) && validPos(msg.target)) || !canMove())
            return;
        final Vector3 newPos = MapUtils.m2p(msg.position);
        if(!checkClientMove(newPos)) {
            moveMgr.setTarget(MapUtils.m2p(msg.target));
            return;
        }
        moveMgr.setTarget(MapUtils.m2p(msg.target), true);
    }

    public void process(CMoveToDefaultPosition msg) {
        if(!canMove() || isFighting()) return;
        final Vector3 newPos = getMap().getInitPosition();
        addServerMoveDistance(newPos.getSubXZMagnitude(getPosition()));
        moveMgr.stopAtPosition(newPos, getMap().getInitOrient());
    }

    public void process(COrient msg) {
        if(!validOrient(msg.orient)) {
            return;
        }
        setOrient(MapUtils.m2p(msg.orient));
        broadcastToNearby(new SOrient(msg.orient));
    }

    public void process(RolePetProtocol msg) {
        final APet pet = activePets.get(msg.petkey);
        if(pet != null) {
            common.Utils.dynamicProcess(pet, MapMgr.dispatcher.decode(msg.proto.ptype, msg.proto.pdata));
        }
    }

    public void process(CJump msg) {
        broadcastToNearby(new SJump());
    }

    private CCurveFlyBegin lastCurveFly;
    public void process(CCurveFlyBegin msg) {
        if(getMap().isWorldMap()) {
            if(!canMove()) return;
            final World world = (World)getMap();
            final cfg.map.WorldMap wcfg = world.getWorldmapCfg();
            final cfg.map.Portal pcfg = wcfg.portals_srcregionid.get(msg.portalid);
            if(pcfg == null || pcfg.dstworldmapid != wcfg.id) return;
//            final cfg.map.EctypeRegionSet rscfg = world.getRegionSetCfg();
//            final cfg.map.IndexedPolygonRegion region = rscfg.regions_id.get(msg.portalid);
//            if (!nearbyRegion(region.polygon, getPosition())) return;
            if(!MapUtils.c2p(pcfg.dstregion).isSubXZMagnitudeInRadius(MapUtils.m2p(msg.dstposition), 0.1))
                return;
        }
        lastCurveFly = msg;
        broadcastToNearby(new SCurveFlyBegin(msg.portalid, msg.curposition, msg.dstposition, msg.curveid));
    }

    public void process(CCurveFlyEnd msg) {
        if(lastCurveFly != null) {
            moveMgr.stopAtPosition(MapUtils.m2p(lastCurveFly.dstposition), MapUtils.m2p(msg.orient));
            broadcastToNearby(new SCurveFlyEnd());
            lastCurveFly = null;
        }
    }

    public void process(CFindAgent msg) {
        final Agent agent = map.findAgentBySubType(this, msg.agenttemplateid);
        final SFindAgent re = new SFindAgent();
        re.agenttemplateid = msg.agenttemplateid;
        if (agent != null) {
            re.errcode = ErrorCode.OK.getErrorId();
            re.position = MapUtils.p2m(agent.getPosition());
        } else {
            re.errcode = ErrorCode.AGENT_NOT_FIND.getErrorId();
        }
        sendNotRoleMsg(re);
    }

    public void process(CFindAgentByType msg) {
        final Agent agent = map.findAgentByType(this, msg.agenttype);
        final SFindAgentByType re = new SFindAgentByType();
        re.agenttype = msg.agenttype;
        if (agent != null) {
            re.errcode = ErrorCode.OK.getErrorId();
            re.position = MapUtils.p2m(agent.getPosition());
        } else {
            re.errcode = ErrorCode.AGENT_NOT_FIND.getErrorId();
        }
        sendNotRoleMsg(re);
    }

    public void process(CChangeRide msg) {
        if (!map.canRide() || this.rideid <= 0 || !RideType.enums.contains(msg.ridetype) || msg.ridetype == this.ridestatus) return;
        uninstallRide();
        ridestatus = msg.ridetype;
        broadcastToNearby(new SChangeRide(this.rideid, this.ridestatus));
        installRide();
    }

    public void process(XChangeRide msg) {
        if (!map.canRide() || this.rideid == msg.rideid) return;
        uninstallRide();
        this.rideid = msg.rideid;
        if (this.rideid <= 0) {
            this.ridestatus = RideType.NONE;
        } else if (this.ridestatus == RideType.NONE || this.ridestatus == RideType.NULL) {
            this.ridestatus = RideType.WALK;
        }
        broadcastToNearby(new SChangeRide(this.rideid, this.ridestatus));
        installRide();
    }

    public void process(CDigMineBegin msg) {
        final AMine mine = (AMine) map.getAgent(msg.mineagentid);
        if (mine == null)
            return;
        final ErrorCode err = mine.beginDig(this);
        if (err.err()) {
            sendError(err);
        } else {
            sendNotRoleMsg(new SDigMineBegin(msg.mineagentid));
        }
    }

    public void process(CDigMineEnd msg) {
        final AMine mine = (AMine) map.getAgent(msg.mineagentid);
        if (mine == null)
            return;
        final ErrorCode err = mine.endDig(this);
        if (err.err()) {
            sendError(err);
        } else {
            sendNotRoleMsg(new SDigMineEnd(msg.mineagentid));
        }
    }


    public void process(CDigMineCancel msg) {
        final AMine mine = (AMine) map.getAgent(msg.mineagentid);
        if (mine == null)
            return;
        final ErrorCode err = mine.cancelDig(this);
        if (err.err()) {
            sendError(err);
        } else {
            sendNotRoleMsg(new SDigMineCancel(msg.mineagentid));
        }
    }

    public void process(CEatRune msg) {
        final ARune rune = (ARune) map.getAgent(msg.runeagentid);
        if (rune == null) {
            xdb.Trace.error("rune not found" + msg.runeagentid);
            sendError(ErrorCode.YOU_COME_LATER);
            return;
        }
        map.eatRune(this, rune);
    }

    private boolean nearbyRegion(PolygonRegion region, Vector3 pos) {
        return MapUtils.inside(pos, region.vertices) || region.vertices.stream().anyMatch(v -> MapUtils.c2p(v).getSubXZMagnitude(pos) < 10);
    }

    public void process(CTransferWorld msg) {
        final int regionid = msg.portalid;
        if(!getMap().isWorldMap()) return;
        final World gamemap = (World) getMap();
        final cfg.map.WorldMap mcfg = gamemap.getWorldmapCfg();
        if (getLevel() < mcfg.openlevel) {
            sendError(ErrorCode.LEVEL_NOT_ENOUGH_CAN_NOT_ENTER_WORLD);
            return;
        }
        final cfg.map.Portal portal = mcfg.portals_srcregionid.get(regionid);
        if (portal == null) {
            xdb.Trace.error("CTransferWorld. invalid regionid:{}", regionid);
            return;
        }
//        final cfg.map.EctypeRegionSet rscfg = gamemap.getRegionSetCfg();
//        final cfg.map.IndexedPolygonRegion region = rscfg.regions_id.get(regionid);
//        if (!nearbyRegion(region.polygon, getPosition())) {
//            xdb.Trace.error("CTransferWorld. out of range");
//            return;
//        }
        final int dstmapid = portal.dstworldmapid;
        final pathfinding.Vector3 dstPosition = MapUtils.c2p(portal.dstregion);
        final pathfinding.Vector3 dstOrient = getOrient();
        if (gamemap.getWorldid() == dstmapid) {
            addServerMoveDistance(dstPosition.getSubXZMagnitude(getPosition()));
            moveMgr.stopAtPosition(dstPosition, dstOrient);
            return;
        }
        MapMgr.dispatcher.sendXdbRole(getRoleid(), new MTransferWorld(dstmapid, MapUtils.p2m(dstPosition), MapUtils.p2m(dstOrient)));
    }

    public void process(CRevive msg) {
        map.revivePlayer(this, msg.revivetype);
    }

    public void process(CClientActionEnd msg) {
        final GameMap gameMap = getMap();
        if (gameMap instanceof AbstractStory) {
            final AbstractStory map = (AbstractStory)gameMap;
            map.endClientAction(msg.actionid);
        } else if(gameMap instanceof CurrencyEctype) {
            final CurrencyEctype map = (CurrencyEctype)gameMap;
//            map.onActionEnd(msg.actionid);
        } else if(gameMap instanceof ExpEctype) {
            final ExpEctype map = (ExpEctype)gameMap;
            map.onActionEnd(msg.actionid);
        }
    }

    public void process(CCloseLayout msg) {
        if (getMap() instanceof AbstractStory) {
            final AbstractStory map = (AbstractStory) getMap();
            map.closeLayout(msg.layoutid);
        }
    }

    public void process(COpenLayout msg) {
        if (getMap() instanceof AbstractStory) {
            final AbstractStory map = (AbstractStory) getMap();
            map.openLayout(msg.layoutid);
        }
    }

    public void process(CBuyBuff msg) {
        ((ClimbTower) getMap()).process(msg);
    }

    public void process(CSkillPerform msg) {
        // 挂起时不能发技能
        if (getMap().isSuspend()) return;
        final Fighter defencer = msg.targetid != 0 ? getMap().getFighter(msg.targetid) : null;

        Skill.Builder param = new Skill.Builder();
        param.skillid = msg.skillid;
        param.defencer = defencer;
        param.direction = MapUtils.m2p(msg.direction);

        performSkillAndNotify(param);
    }

    public void process(CEndClimbTowerEctype msg) {
        if(!(getMap() instanceof  ClimbTower)) return;
        final ClimbTower cmap = (ClimbTower) getMap();
        cmap.terminate();
    }

    public void process(CReady msg) {
        setReady();
        sendNotRoleMsg(new SReady());
        map.onPlayerReady(this);
    }

    public void process(XChangeTeam msg) {
        this.team = msg.team;
    }

    public void process(CFindNearbyRole msg) {
        final SFindNearbyRole re = new SFindNearbyRole();
        map.getAgentMap().foreachNearbyAgents(this, AgentType.PLAYER, CfgMgr.team.findradius, agent -> {
            final Player player = (Player) agent;
            if (agent != this && player.team.members.isEmpty() && re.rolelist.size() < CfgMgr.team.findnearbyplayermaxcount) {
                final NearbyRoleShowInfo si = new NearbyRoleShowInfo();
                si.roleid = player.getRoleid();
                si.serverid = player.getServerid();
                si.name = player.getName();
                si.profession = player.getProfession();
                si.gender = player.getGender();
                si.level = player.getLevel();
                si.viplevel = player.vipLevel;
                si.combatpower = player.combatPower;
                re.rolelist.add(si);
            }
        });
        sendNotRoleMsg(re);
    }

    public void process(CFindNearbyTeam msg) {
        final MFindNearbyTeam re = new MFindNearbyTeam();
        final HashSet<Long> findTeams = new HashSet<>();
        map.getAgentMap().foreachNearbyAgents(this, AgentType.PLAYER, CfgMgr.team.findradius, agent -> {
            if (re.teamlist.size() < CfgMgr.team.findnearbyteammaxcount) {
                final Player player = (Player) agent;
                final Team team = player.team;
                if (team.teamid > 0 && team.members.size() < CfgMgr.team.teammembermaxcount && !findTeams.contains(team.teamid)) {
                    findTeams.add(team.teamid);
                    re.teamlist.add(team.teamid);
                }
            }
        });
        sendXdb(re);
    }

    public void process(CEctypeStatistic msg) {
        if (map instanceof Ectype) {
            sendNotRoleMsg(((Ectype) map).createSEctypeStatistic());
        }
    }

    public void process(CEctypeDamageStatistic msg) {
        if (map instanceof Ectype) {
            sendNotRoleMsg(((Ectype) map).createSEctypeDamageStatistic());
        }
    }

    public void process(CPlayCGOver msg) {//播放cg结束后取消保护状态
        getStateMgr().clearState(InternEffcteIds.PLAY_CG);
    }

    public void process(CPraiseMember msg) {
        if (map instanceof Ectype) {
            ((Ectype) map).playerPraise(getRoleid(), msg);
        }
    }

    public void process(XPlayerChangeTask msg){
        this.taskMonsters = msg.taskmonsters;
        Trace.debug("XPlayerChangeTask:{} roleid:{}", msg, getRoleid());
    }

}
