package lx.gs.activity.worldmonster;

import cfg.CfgMgr;
import cfg.ectype.ExpMonsterRef;
import gnet.MapClient;
import lx.gs.activity.Activity;
import lx.gs.activity.DailyTimeCaculator;
import lx.gs.activity.worldmonster.msg.ExpMonsterNotify;
import lx.gs.chat.FChat;
import lx.gs.map.MapModule;
import map.msg.XCloseExpMonster;
import map.msg.XCreatExpMonster;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by xiong on 2016/8/17.
 */
public class WorldMonster extends Activity {

    public static Map<Integer, ExpMonsterToMapInfo> minLevelToMapid = new HashMap<>();
    public static class ExpMonsterToMapInfo{
        public final int levelindex;
        public final int positionindex;
        public final int mapid;
        public final int minLevel;
        public final int maxLevel;
        public ExpMonsterToMapInfo(int mapid, int levelindex, int positionindex, int minLevel, int maxLevel){
            this.mapid = mapid;
            this.levelindex = levelindex;
            this.positionindex = positionindex;
            this.minLevel = minLevel;
            this.maxLevel = maxLevel;
        }
    }
    private cfg.ectype.ExpMonster mcfg;
    public WorldMonster(cfg.ectype.ExpMonster mcfg){
        super(mcfg.id, 0);
        this.mcfg = mcfg;
        for(cfg.ectype.TimeControler tc : mcfg.opentimes) {
            this.intervalOpenTimeCalculators.add(new DailyTimeCaculator(tc.hour, tc.minute, tc.second, mcfg.lasttime));
        }
        this.beforeOpenWorks.add(new Work(mcfg.beforeopenbroadcasttime * 1000L, () -> {
            sendNotice(ExpMonsterNotify.START, mcfg.openbroadcast);
        }));

        this.afterOpenWorks.add(new Work(0L, () -> {
            sendNotice(0, mcfg.ongoingbroadcast);
        }));

        for(int t = mcfg.afteropenbroadcasttime, interval = Math.max(mcfg.afteropenbroadcastinterval, 1) ; t < mcfg.lasttime ; t += interval) {
            this.afterOpenWorks.add(new Work(t * 1000L, () -> sendNotice(0, mcfg.ongoingbroadcast)));
        }

    }
    @Override
    protected void onOpen() {
        for(int i = 0 ; i < mcfg.monstermsg.size(); i++){
            cfg.ectype.ExpMonsterMsg eMsg = mcfg.monstermsg.get(i);
            int randIndex = common.Utils.randomRange(0, eMsg.monsterref.size());
            ExpMonsterRef mapAndPosition = eMsg.monsterref.get(randIndex);
            ExpMonsterToMapInfo detail = new ExpMonsterToMapInfo(mapAndPosition.mapid, i, randIndex, eMsg.minlevel, eMsg.maxlevel);
            minLevelToMapid.put(eMsg.minlevel, detail);
            final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(mapAndPosition.mapid);
            XCreatExpMonster msg = new XCreatExpMonster(detail.levelindex, detail.positionindex);
            lines.values().forEach(mInfo -> {
                MapClient.sendToMap(mInfo.mapid, msg);
            });
//            System.out.println("地图" + mapAndPosition.mapid + " 刷新了经验怪");
        }
    }

    @Override
    protected void onClose() {
        minLevelToMapid.values().forEach(detail -> {
            final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(detail.mapid);
            int monsterid = CfgMgr.expmonster.monstermsg.get(detail.levelindex).monsterid;
            lines.values().forEach(mInfo -> {
                MapClient.sendToMap(mInfo.mapid, new XCloseExpMonster(monsterid));
            });

        });
        minLevelToMapid.clear();
        sendNotice(ExpMonsterNotify.END, mcfg.endbroadcast);
    }

    @Override
    protected void onLoad() {

    }
    public static void sendNotice(int type, cfg.ectype.Broadcast broadcast) {
        FChat.sendSystemMessage(new ExpMonsterNotify(type, broadcast.broadcastdec));
    }
}
