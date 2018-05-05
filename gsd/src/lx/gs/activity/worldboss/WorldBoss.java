package lx.gs.activity.worldboss;

import cfg.ectype.WorldBossStateType;
import gnet.MapClient;
import gnet.link.Onlines;
import lx.gs.activity.Activity;
import lx.gs.activity.ActivityModule;
import lx.gs.activity.DailyTimeCaculator;
import lx.gs.activity.worldboss.msg.*;
import lx.gs.chat.FChat;
import lx.gs.event.EventModule;
import lx.gs.event.KillWorldBossEvent;
import lx.gs.map.MapModule;
import map.msg.Vector3;
import map.msg.XCreateWorldBoss;
import map.msg.XEndWorldBoss;
import xdb.Trace;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by huangqiang on 2016/3/19.
 */
public class WorldBoss extends Activity {
    private static final Map<Integer, WorldBoss> worldBosses = new HashMap<>();

    public static WorldBoss create(cfg.ectype.WorldBoss wcfg) {
        final WorldBoss worldBoss = new WorldBoss(wcfg);
        worldBosses.put(wcfg.id, worldBoss);
        return worldBoss;
    }

    public static WorldBoss getWorldBoss(int bossid) {
        return worldBosses.get(bossid);
    }

    private final cfg.ectype.WorldBoss wcfg;
    private final HashMap<Integer, Integer> lineBossStatus = new HashMap<>();

    private WorldBoss(cfg.ectype.WorldBoss wcfg) {
        super(wcfg.id, (wcfg.endtime - 30) * 1000L);
        this.wcfg = wcfg;
        for(cfg.ectype.TimeControler tc : wcfg.opentimes) {
            this.intervalOpenTimeCalculators.add(new DailyTimeCaculator(tc.hour, tc.minute, tc.second, wcfg.endtime));
        }

        this.beforeOpenWorks.add(new Work(wcfg.beforeopenbroadcasttime * 1000L, () -> {
            sendNotice(wcfg.prebroadcast);
        }));

        this.afterOpenWorks.add(new Work(0L, () -> {
            sendNotice(wcfg.openbroadcast);
        }));

        for(int t = wcfg.afteropenbroadcasttime, interval = Math.max(wcfg.afteropenbroadcastinterval, 1) ; t < wcfg.endtime ; t += interval) {
            this.afterOpenWorks.add(new Work(t * 1000L, () -> sendNotice(wcfg.ongoingbroadcast)));
        }
    }

    @Override
    public void onLoad() {
        scheduleNext();
    }

    @Override
    protected void onOpen() {
        final Map<Integer, MapModule.MapInfo> worldLines = MapModule.worldsByLines.get(wcfg.mapid);
        final List<Integer> lines = new ArrayList<>(worldLines.keySet());
        final Collection<Integer> lineids = common.Utils.getMutiRandom(0, lines.size(), (int)(lines.size() * cfg.ectype.WorldBoss.LINE_PARA))
                .stream().map(index -> lines.get(index)).collect(Collectors.toList());

        lineBossStatus.clear();
        for(Integer lineid : lineids) {
            lineBossStatus.put(lineid, WorldBossStateType.ALIVE);
        }

        lineids.forEach(lineid -> MapClient.sendToMap(worldLines.get(lineid).mapid,
                new XCreateWorldBoss(wcfg.id, wcfg.monsterid, common.Utils.c2m(wcfg.position), new Vector3(0, 0, 1))));
    }

    @Override
    protected void onClose() {
        final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(wcfg.mapid);
        lineBossStatus.entrySet().stream().filter(e -> e.getValue() == WorldBossStateType.ALIVE)
                .forEach(e -> {
                    MapClient.sendToMap(lines.get(e.getKey()).mapid, new XEndWorldBoss(wcfg.id));
                });
        lineBossStatus.clear();
        sendNotice(wcfg.failbroadcast);
    }

    private void onBossKill(int lineid, long killer, String killerName) {
        EventModule.INSTANCE.broadcastEvent(new KillWorldBossEvent(killer));
        FChat.sendSystemMessage(new SWorldBossKillNotice(killerName, wcfg.killbroadcast.broadcastdec));
        lineBossStatus.put(lineid, WorldBossStateType.KILLED);
    }

    public static void sendNotice(cfg.ectype.Broadcast broadcast) {
        FChat.sendSystemMessage(new SWorldBossNotice(broadcast.broadcastdec));
    }

    public static void onBossKill(int lineid, long killer, String killerName, int bossid) {
        ActivityModule.INSTACNE.addTask(() -> {
            final WorldBoss worldBoss = getWorldBoss(bossid);
            worldBoss.onBossKill(lineid, killer, killerName);
        });
    }

    public static void onLineRemove(int worldid, int lineid) {
        ActivityModule.INSTACNE.addTask(() -> {
            for(WorldBoss worldBoss : worldBosses.values()) {
                if(worldid == worldBoss.wcfg.mapid) {
                    if(worldBoss.lineBossStatus.remove(lineid) != null) {
                        Trace.info("WorldBoss.onLineRemove worldboss.id:{} worlid:{} lineid:{}", worldBoss.wcfg.id, worldid, lineid);
                    };
                }
            }
        });
    }

    private SGetWorldBossLineStatus createSGetWorldBossLineStatus() {
        final SGetWorldBossLineStatus re = new SGetWorldBossLineStatus();
        re.bossid = wcfg.id;
        final Map<Integer, MapModule.MapInfo> lines = MapModule.worldsByLines.get(wcfg.mapid);
        for (Integer lineid : lines.keySet()) {
            re.lines.put(lineid, lineBossStatus.getOrDefault(lineid, WorldBossStateType.NOT_EXIED));
        }
        return re;
    }

    public static void sendSGetWorldBossLineStatus(long roleid, int bossid) {
        ActivityModule.INSTACNE.addTask(() -> {
            final WorldBoss worldBoss = getWorldBoss(bossid);
            Onlines.getInstance().send(roleid, worldBoss.createSGetWorldBossLineStatus());
        });
    }

    public static void sendSGetWorldBossList(long roleid) {
        ActivityModule.INSTACNE.addTask(() -> {
            final SGetWorldBossList re = new SGetWorldBossList();
            for(WorldBoss boss : worldBosses.values()) {
                re.bosses.add(new WorldBossInfo(boss.getId(), boss.getOpenTime(), boss.isOpen() ? 1 : 0));
            }
            Onlines.getInstance().send(roleid, re);
        });

    }
}
