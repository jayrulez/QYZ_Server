package map.map;

import cfg.CfgMgr;
import cfg.ectype.*;
import cfg.fight.StateType;
import cfg.map.PatrolType;
import cfg.map.Vector2;
import common.ErrorCode;
import common.Time;
import common.Utils;
import map.MapUtils;
import map.agent.*;
import map.buff.effect.InternEffcteIds;
import map.msg.*;
import xdb.Trace;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 血战青云
 *
 * Created by huangqiang on 2016/3/16.
 */
public class GuardTower extends Ectype {

    private int state;
    private int curWaveId;

    public static GuardTower create(XCreateGuardTowerEctype builder) {
        final GuardTower.Builder b = new GuardTower.Builder();
        initCommon(b, builder.ectypeid, false, builder.serverid);
        b.roles.putAll(builder.roles);
        b.zoneid = builder.zoneid;
        b.failOnLeave = false;
        b.useBroadcastPolicy = false;

        return new GuardTower(b);
    }

    public static class Builder extends Ectype.Builder {
        public Map<Long, Integer> roles = new HashMap<>();
        public boolean failOnLeave;
        public int zoneid;
    }

    private final Map<Long, Integer> roles = new HashMap<>(4);
    private final cfg.ectype.GuardTowerZone gzcfg;

    private Monster base;

    private final List<Vector2> bornpositions = new LinkedList<>();

    public GuardTower(Builder b) {
        super(b);
        this.roles.putAll(b.roles);
        this.gzcfg = CfgMgr.guardtower.zones_zoneid.get(b.zoneid);
        this.curWaveId = this.gzcfg.monsterwaves.get(0).id;
        this.state = EctypeState.PREPARE;
        this.bornpositions.addAll(CfgMgr.guardtower.bornpositions);
        long prepareTime = CfgMgr.guardtower.preparetime * 1000L;
        this.remainTime += prepareTime;
    }

    @Override
    public boolean admit(long roleid) {
        return roles.containsKey(roleid);
    }

    @Override
    public void init() {
        super.init();

        this.base = createMonster(gzcfg.baseid, MapUtils.c2p(gzcfg.baseposition), MapUtils.createOrient(0));
        this.base.addListener(Listener.DEATH, (go, evtid, param) -> {
            Trace.debug("GuardTower : base is destroyed, check end.");
            checkEnd(ErrorCode.BASE_DESTROY);
        });
        base.getAttrMgr().setAlwaysBroadcast(true);

        long prepareTime = CfgMgr.guardtower.preparetime * 1000L;
        schedule(this::refreshSpell, prepareTime);
        schedule(this::beginFight, prepareTime);
        sendContextMsg(new SCountDown(getNow() + Time.toMilliseconds(CfgMgr.guardtower.preparetime)));
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
        roles.keySet().stream().filter(aLong -> aLong != player.getRoleid())
                .forEach(aLong -> sendToEachother(player, getPlayer(aLong)));
    }

    @Override
    protected void initPlayerPositionAndOrient(Player player) {
        if (this.bornpositions.size() == 0) {
            super.initPlayerPositionAndOrient(player);
        } else {
            player.initPosition(MapUtils.c2p(this.bornpositions.remove(0)));
            player.initOrient(getInitOrient());
        }
    }

    private void beginFight() {
        this.state = EctypeState.START;
        sendContextMsg(new SBeginFight(this.state));
        openWave(curWaveId);
        players.values().forEach(player -> player.getStateMgr().clearState(InternEffcteIds.NOT_FIGHT));
        startRemainTime();
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {

    }

    private void openWave(int waveId) {
        Trace.info("GuardTower.openWave roles:{} ectypeid:{} waveid:{}", roles.keySet(), ectypeid, waveId);
        GuardTowerMonsterInfo wave = gzcfg.monsterwaves_id.get(waveId);
        createMonsters(wave.monsters, wave.regionid, gzcfg.mainregionid, PatrolType.NULL, null, this::addRewardAndOpenNextWave, null);
    }

    private void addRewardAndOpenNextWave() {
        // add wave bonus
        GuardTowerMonsterInfo lastWave = gzcfg.monsterwaves_id.get(curWaveId);
        players.values().stream().forEach(p -> {
            final Bonus dst = new Bonus();
            common.Bonus.genBonusByProfession(p.getProfession(), lastWave.clearaward, 1, dst);
            p.addBonus(dst);
        });
        // open next wave
        ++curWaveId;
        if (curWaveId > gzcfg.monsterwaves.size()) {
            xdb.Trace.info("GuardTower.addRewardAndOpenNextWave reach setting max Wave:{}. end", curWaveId);
            checkEnd(ErrorCode.GD_MAX_WAVE);
            return;
        }
        xdb.Trace.info("GuardTower.addRewardAndOpenNextWave roles:{} ectypeid:{} newWaveid:{}", roles.keySet(), ectypeid, curWaveId);
        GuardTowerMonsterInfo wave = gzcfg.monsterwaves_id.get(curWaveId);
        if (wave == null) {
            xdb.Trace.info("GuardTower.addRewardAndOpenNextWave reach data max Wave:{}. end", curWaveId);
            checkEnd(ErrorCode.GD_MAX_WAVE);
            return;
        }
        openWave(curWaveId);
        broadcastNotContextMsg(new SNewWaveOpen(curWaveId));
    }

    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
        if (inPrepare()) {
            player.getStateMgr().setStateForever(InternEffcteIds.NOT_FIGHT, StateType.NOT_FIGHT);
        }
    }

    @Override
    protected void normalUpdate(long now) {
    }

    private void refreshSpell() {
        // random spell and position
        Spell spellmsg = CfgMgr.guardtower.spellmsg;
        Vector2 position = Utils.randomChoose(spellmsg.positions);
        int randomIndex = Utils.getRandomIndex(spellmsg.runeinfo.stream().map(info -> info.weight).collect(Collectors.toList()));
        final RuneInfo runeInfo = spellmsg.runeinfo.get(randomIndex);
        createRune(runeInfo.runeid, MapUtils.c2p(position), arune -> arune.addListener(Listener.LEAVE, (go, evtid, param) -> schedule(this::refreshSpell, CfgMgr.guardtower.spellmsg.refreshtime * 1000L)));
    }

    @Override
    protected void onFail(ErrorCode err) {
        final MEndGuardTower endMsg = new MEndGuardTower();
        boolean win = err.ok() || err == ErrorCode.GD_MAX_WAVE;
        endMsg.ectypeid = ectypeid;
        endMsg.errcode = win ? ErrorCode.OK.getErrorId() : err.getErrorId();
        this.players.values().forEach(p -> p.sendXdb(endMsg));
        if (win) {
            final long maxDamageRoleid = playerStatInfo.values().stream().collect(Collectors.groupingBy(s -> s.ownerid, Collectors.summingLong(s -> s.damage)))
                    .entrySet().stream().max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue())).get().getKey();
            this.roles.entrySet().forEach(role -> {
                final long roleid = role.getKey();
                final Player player = (Player) getAgent(roleid);
                //逃跑玩家没有奖励
                if (player == null) {
                    return;
                }
                final SEndGuardTower endGuardTower = new SEndGuardTower();
                endGuardTower.errcode = err.getErrorId();
                // reward for passing
                endGuardTower.bonus = new Bonus();
                this.gzcfg.winaward.bonuss.forEach(bonus -> common.Bonus.genBonusByProfession(role.getValue(), bonus, endGuardTower.bonus));
                // additional reward for the 1st one
                if (roleid == maxDamageRoleid) {
                    this.gzcfg.maxkillaward.bonuss.forEach(bonus -> common.Bonus.genBonusByProfession(role.getValue(), bonus, endGuardTower.bonus));
                }
                this.gzcfg.maxkillaward.bonuss.forEach(bonus -> common.Bonus.genBonusByProfession(role.getValue(), bonus, endGuardTower.maxkill));
                player.addBonus(endGuardTower.bonus);
                player.sendNotRoleMsg(endGuardTower);
            });
        } else {
            final SEndGuardTower endGuardTower = new SEndGuardTower();
            endGuardTower.errcode = err.getErrorId();
            broadcastNotContextMsg(endGuardTower);
        }
    }

    @Override
    public void sendPlayerEnter(Player player) {
        SEnterGuardTower enterGuardTower = new SEnterGuardTower();
        enterGuardTower.id = getMapid();
        enterGuardTower.ectypeid = ectypeid;
        enterGuardTower.curwaveid = curWaveId;
        enterGuardTower.remaintime = remainTime;
        player.sendNotRoleMsg(enterGuardTower);
    }

    public boolean inPrepare() {
        return state == EctypeState.PREPARE;
    }

}
