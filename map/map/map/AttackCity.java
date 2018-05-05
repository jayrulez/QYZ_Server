package map.map;

import cfg.CfgMgr;
import common.ErrorCode;
import common.Utils;
import map.MapUtils;
import map.agent.Player;
import map.msg.*;

/**
 * Created by HuangQiang on 2016/5/25.
 */
public class AttackCity extends MultiPlayerEctype {

    public static AttackCity create(XCreateAttackCity msg) {
        final Builder b = new Builder();
        initCommon(b, msg.ectypeid, false, msg.serverid);
        b.countDownDurationTime = 0;
        b.levelSectionid = msg.levelsectionid;
        b.roles = msg.roleids;

        b.xcellNum = 0;
        b.zcellNum = 0;
        return new AttackCity(b);
    }


    public static class Builder extends MultiPlayerEctype.Builder {
        public int levelSectionid;
    }

    public AttackCity(Builder b) {
        super(b);
        this.levelSectionid = b.levelSectionid;
        this.attackCityCfg = CfgMgr.attackcity;
        this.levelSectionCfg = this.attackCityCfg.sections.get(levelSectionid);

    }

    private final int levelSectionid;
    private final cfg.ectype.AttackCity attackCityCfg;
    private final cfg.ectype.AttackCityLevelSection levelSectionCfg;

    @Override
    public void init() {
        super.init();

        int i = 0;
        for(cfg.ectype.AttackCityRefreshMonster info : levelSectionCfg.monsterinfos) {
            final int index = i++;
            schedule(() -> {
                if(hasEnd()) return;
                for(cfg.ectype.MonsterRefreshInfo monsterRefreshInfo : info.monsters) {
                    createMonsters(monsterRefreshInfo.monsters, monsterRefreshInfo.regionid, monsterRefreshInfo.patrol);
                }
                broadcastNotContextMsg(new SNewAttackCityMonster(levelSectionid, index));
            }, info.refreshtime * 1000L);
        }
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
    }

    @Override
    protected void initPlayerPositionAndOrient(Player player) {
        MapUtils.Location loc = MapUtils.randomPolygonsPositionAndOrient(getRegionSetCfg().regions_id.get(Utils.randomChoose(levelSectionCfg.bornregion)).polygon);
        player.initPosition(loc.position);
        player.initOrient(loc.orient);
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {

    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    protected void onFail(ErrorCode err) {
        sendContextMsg(new SEndAttackCity());
    }

    @Override
    public void sendPlayerEnter(Player player) {
        final SEnterAttackCity msg = new SEnterAttackCity();
        msg.ectypeid = ectypeid;
        msg.id = getMapid();
        msg.state = state;
        msg.remaintime = remainTime;
        player.sendNotRoleMsg(msg);
    }

    @Override
    protected void onBattleBegin() {

    }
}
