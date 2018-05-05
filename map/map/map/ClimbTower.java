package map.map;

import cfg.CfgMgr;
import cfg.ectype.ClimbTowerEctype;
import cfg.ectype.FloorInfo;
import cfg.ectype.TargetType;
import cfg.fight.AttrId;
import cfg.item.EItemBindType;
import cfg.map.PatrolType;
import common.ErrorCode;
import map.agent.AttrMgr;
import xdb.Trace;
import map.MapUtils;
import map.agent.Listener;
import map.agent.Monster;
import map.agent.Player;
import map.msg.*;
import pathfinding.Vector3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangqiang on 2016/1/5.
 */
public class ClimbTower extends OnePlayerEctype {

    public ClimbTower(Builder b) {
        super(b);
        this.ecCfg = CfgMgr.climbtowerectype.get(ectypeid);
        this.lastMaxClimbFloorId = b.param1;
        this.startFloorId = Math.max(b.param1 - ClimbTowerEctype.OFFSET_FLOOR_FROM_MAX_FLOOR, 1);
        this.totalScore = 0;

        this.baseDeathListener = (go, evtid, param) -> {
            checkEnd(ErrorCode.BASE_DESTROY);
        };
    }

    private final cfg.ectype.ClimbTowerEctype ecCfg;
    private final int lastMaxClimbFloorId;
    private final int startFloorId;
    private Monster base;
    private int curFloorId;
    private int totalScore;
    private HashMap<Integer, Integer> buffBuyNums = new HashMap<>();

    private final Listener baseDeathListener;
    private long lastFloorCostTime;
    private long currFloorOpenTime;

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterClimbTower re = new SEnterClimbTower();
        re.id = getMapid();
        re.ectypeid = ectypeid;
        re.totalscore = totalScore;
        re.curfloorid = curFloorId;
        re.buffbuynums.putAll(buffBuyNums);
        re.remaintime = remainTime;
        player.sendNotRoleMsg(re);
    }

    public void init() {
        super.init();

        lastFloorCostTime = 0;
        curFloorId = startFloorId;
        totalScore = 0;
        for(FloorInfo fi : ecCfg.floors) {
            if(fi.id >= startFloorId) break;
            totalScore += fi.score * fi.monsters.values().stream().mapToInt(v -> v).sum();
        }
        Trace.debug("ClimbTower.init roleid:{} startfloorid:{} totalscore:{}", roleid, startFloorId, totalScore);
        base = createMonster(ecCfg.baseid, new Vector3(ecCfg.baseposition.x, 0, ecCfg.baseposition.y), MapUtils.createOrient(0));
        base.addListener(Listener.DEATH, baseDeathListener);
        base.getAttrMgr().setAlwaysBroadcast(true);
        openFloor(curFloorId);
    }

    private void openFloor(int floorId) {
        currFloorOpenTime = System.currentTimeMillis();
        Trace.info("ClimbTower.openFloor roleid:{} ectypeid:{} floorid:{}", roleid, ectypeid, floorId);
        final cfg.ectype.FloorInfo fi = ecCfg.floors_id.get(floorId);
        final AttrMgr attrMgr = base.getAttrMgr();
        final float hppercent = attrMgr.getHPValue() / attrMgr.getHPFullValue();
        //每级属性得到增加.
        //其中血条比例保持不变
        attrMgr.setRawAttr(AttrId.HP_FULL_VALUE, fi.basehp);
        attrMgr.setRawAttr(AttrId.DEFENCE, fi.basedefence);
        final float addhp = hppercent * attrMgr.getHPFullValue() - attrMgr.getHPValue();
        if(addhp > 0)
            attrMgr.addHPValue(addhp);

        final Listener listener = (go, evt, param) -> addScore(fi.score);

        createMonsters(fi.monsters, fi.regionid, ecCfg.mainregionid, null, this::openNextFloor, monster ->
            monster.addListener(Listener.DEATH, listener)
        );
    }

    private void openNextFloor() {
        lastFloorCostTime = System.currentTimeMillis() - currFloorOpenTime;
        ++curFloorId;
        final cfg.ectype.FloorInfo fi = ecCfg.floors_id.get(curFloorId);

        if(fi == null) {
            checkEnd(ErrorCode.MAX_TOWER_FLOOR);
            return;
        }
        final int level = player != null ? player.getLevel() : 0;
        if(fi.requirelevel > level) {
            checkEnd(ErrorCode.MAX_CUR_LEVEL_TOWER_FLOOR);
            return;
        }
        openFloor(curFloorId);
        broadcastNotContextMsg(new SNewFloorOpen(curFloorId));
    }

    private void addScore(int add) {
        this.totalScore += add;
        broadcastNotContextMsg(new SScoreChange(totalScore));
    }

    public void process(CBuyBuff msg) {
        final int buffid = msg.buffid;
        final cfg.ectype.Buff b = ecCfg.buffs_buffid.get(buffid);
        final int buyNum = buffBuyNums.getOrDefault(buffid, 0) + 1;
        final int costScore = b.price.get(Math.min(buyNum, b.price.size()) - 1);
        if(totalScore < costScore) {
            xdb.Trace.error("ClimbTower.BuyBuff score not enough. ectypeid:{} buffid:{} totalscore:{} costscore:{}", ectypeid, buffid, totalScore, costScore);
            return;
        }
        totalScore -= costScore;
        install(b);

        buffBuyNums.put(buffid, buyNum);
        broadcastNotContextMsg(new SBuyBuff(buffid, buyNum, totalScore));
        Trace.debug("ClimbTower.buyBuff roleid:{} ectypeid:{} buffid:{} newbuynum:{} costscore:{} remaintotalscore:{}",
                roleid, ectypeid, buffid, buyNum, costScore, totalScore);
    }

    private void install(cfg.ectype.Buff buff) {
        switch (buff.target) {
            case TargetType.BASE: {
                if(base.isActive() && !base.isDead()) {
                    map.buff.Buff.installNotSkillHitPointBuff(base, buff.buffid);
                }
                break;
            }
            case TargetType.SELF: {
                final Player player = (Player)getAgent(roleid);
                if(player.isActive() && !player.isDead()) {
                    map.buff.Buff.installNotSkillHitPointBuff(player, buff.buffid);
                }
                break;
            }
            default:
                throw new RuntimeException("unknown buff target:" + buff.target);
        }
    }
    @Override
    public void onPlayerEnter(Player player) {
        super.onPlayerEnter(player);
        //player.addListener(Listener.REVIVE, (go, evtid, param) -> {
            for(Map.Entry<Integer, Integer> e : buffBuyNums.entrySet()) {
                final cfg.ectype.Buff buff = ecCfg.buffs_buffid.get(e.getKey());
                for(int n = e.getValue() ; n > 0 ; n--) {
                    install(buff);
                }
            }
        //});
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    protected void onFail(ErrorCode err) {
        final SEndClimbTowerEctype re = new SEndClimbTowerEctype();
        re.errcode = err.getErrorId();
        re.oldmaxfloorid = lastMaxClimbFloorId;
        re.newmaxfloorid = curFloorId - 1;
        re.lastfloorcosttime = lastFloorCostTime < 1000 ? 1 : (int) TimeUnit.MILLISECONDS.toSeconds(lastFloorCostTime);

        for(int floorid = 1 ; floorid < curFloorId ; floorid++) {
            final cfg.ectype.FloorInfo fi = ecCfg.floors_id.get(floorid);
            common.Bonus.genBonusByProfession(profession, fi.normalbonus, 1,
                    common.Bonus.BindType.BIND, re.normalbonus);
            if(floorid > lastMaxClimbFloorId) {
                common.Bonus.genBonusByProfession(profession, fi.firstbonus, 1,
                        common.Bonus.BindType.BIND, re.firstbonus);
            }
        }
        sendContextMsg(re);

        final MEndClimbTowerEctype msg = new MEndClimbTowerEctype();
        msg.retcode = err.getErrorId();
        msg.ectypeid = ectypeid;
        msg.newfloorid = re.newmaxfloorid;
        msg.lastfloorcosttime = re.lastfloorcosttime;
        msg.firstbonus = re.firstbonus;
        msg.normalbonus = re.normalbonus;
        Player.sendXdb(roleid, msg);
    }

    public void terminate() {
        checkEnd(ErrorCode.ECTYPE_PLAYER_EXIT);
    }

    @Override
    public void onReady() {
        super.onReady();
        startRemainTime();
    }
}
