package map.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import common.ErrorCode;
import map.agent.AMine;
import map.agent.Player;
import map.msg.MEndDailyEctype;
import map.msg.SEndDailyEctype;
import map.msg.SEnterDailyEctype;
import map.msg.SNewMonsterWave;
import pathfinding.Vector3;

import java.util.HashMap;
import java.util.List;

/**
 * Created by huangqiang on 2016/1/22.
 */
public class Daily extends AbstraceDaily {

    public Daily(Builder b) {
        this(b, CfgMgr.dailyectype.get(b.subid));
    }

    private Daily(Builder b, cfg.ectype.DailyEctype dcfg) {
        super(b, dcfg.doublebonustime, dcfg.ectyperandomdrop);
        this.dailyEctypeCfg = dcfg;
    }

    private final cfg.ectype.DailyEctype dailyEctypeCfg;
    public void init() {
        super.init();
        waveIndex = -1;
        beginTime = 0;

        createMultiBatchMonsters2(dailyEctypeCfg.monsters, dailyEctypeCfg.monsterreftime, Const.NULL,
                () -> broadcastNotContextMsg(new SNewMonsterWave(++waveIndex)), null, null, this::success);
    }

    @Override
    public void onReady() {
        super.onReady();
        startRemainTime();
        this.beginTime = getNow();
    }
}
