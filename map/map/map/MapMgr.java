package map.map;

import common.TaskQueue;
import common.Utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by huangqiang on 2016/4/20.
 */
public final class MapMgr {
    public final static MapMgr Ins = new MapMgr();
    public static ProtocolDispatcher dispatcher;
    private int localServerid;
    private MapMgr() {

    }

    public void start() {
        TaskQueue.getScheduleExecutor().scheduleAtFixedRate(this::update, 100, 100, TimeUnit.MILLISECONDS);
    }

    @SuppressWarnings("unchecked")
    private final ConcurrentHashMap<Integer, ConcurrentHashMap<Long, GameMap>> mapsByServeridAndType = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, GameMap> roleid2Map = new ConcurrentHashMap<>();

    public int getLocalServerid() {
        return localServerid;
    }

    public void setLocalServerid(int localServerid) {
        this.localServerid = localServerid;
    }

    public ConcurrentHashMap<Long, GameMap> getMapsByServeridAndType(long id) {
        final int cid = Utils.getLowBits(id, Utils.BIT_MAP_TYPE + Utils.BIT_SERVERID_HOLDER);
        ConcurrentHashMap<Long, GameMap> maps = mapsByServeridAndType.get(cid);
        if(maps != null) {
            return maps;
        } else {
            mapsByServeridAndType.putIfAbsent(cid, new ConcurrentHashMap<>());
            return mapsByServeridAndType.get(cid);
        }
    }

    public GameMap getMap(long id) {
        return getMapsByServeridAndType(id).get(id);
    }

    public boolean addMap(GameMap map) {
        final long id = map.getMapid();
        return getMapsByServeridAndType(id).putIfAbsent(id, map) == null;
    }

    public GameMap removeMap(long id) {
        return getMapsByServeridAndType(id).remove(id);
    }

    public boolean removeMap(GameMap map) {
        final long id = map.getMapid();
        return getMapsByServeridAndType(id).remove(id, map);
    }

    public GameMap getRoleInGame(long roleid) {
        return roleid2Map.get(roleid);
    }

    public boolean playerEnterMap(long roleid, GameMap map) {
        return roleid2Map.putIfAbsent(roleid, map) == null;
    }

    public boolean playerLeaveMap(long roleid, GameMap map) {
        return roleid2Map.remove(roleid, map);
    }

    protected void update() {
        final long now = System.currentTimeMillis();
        for(Map<Long, GameMap> maps : mapsByServeridAndType.values()) {
            for(GameMap m : maps.values()) {
                m.externUpdate(now);
            }
        }
    }
}
