package lx.gs.map;

import cfg.CfgMgr;
import cfg.Const;
import cfg.map.Reason;
import common.ErrorCode;
import common.TaskQueue;
import gnet.MapClient;
import map.MapUtils;
import map.msg.*;
import xdb.Trace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by HuangQiang on 2016/8/1.
 */
public class RoleContext {
    private final long roleid;
    private final ArrayList<map.msg.RoleMapInfo1> prevs = new ArrayList<>();
    private RoleMapInfo2 worldOrFamilyInfo;
    private volatile map.msg.RoleMapContext cur;
    private map.msg.RoleMapContext last;
    private boolean finishPrologue;
    private boolean isOffline;

    private int curCtxId;

    private LockOper curOper;
    private final LinkedList<LockOper> nextOpers = new LinkedList<>();

    public RoleContext(long roleid) {
        this.roleid = roleid;
        this.worldOrFamilyInfo = new RoleMapInfo2();
        this.cur = null;
        this.last = null;
        this.finishPrologue = false;

        this.isOffline = false;

        this.curCtxId = 0;
        this.curOper = null;
    }

    @Override
    public String toString() {
        return String.format("RoleContex. roleid:%s prevs:%s worldOrfamily:%s cur:%s last:%s isoffline:%s",
                roleid, prevs, worldOrFamilyInfo, cur, last, isOffline);
    }

    RoleMapContext getCur() {
        return cur;
    }

    public void setFinishPrologue() {
        this.finishPrologue = true;
    }

    public void setOffline(boolean offline) {
        this.isOffline = offline;
    }

    private void initDefault() {
        prevs.clear();

        final cfg.role.RoleConfig rcfg = CfgMgr.roleconfig;
        final map.msg.RoleMapContext ctx = new map.msg.RoleMapContext();
        ctx.isnew = 1;
        ctx.info1.mapid = FMap.makeWorldMapid(rcfg.bornmap, 0);
        ctx.info1.position = common.Utils.c2m(rcfg.bornposition);
        ctx.info1.orient = FMap.createOrient(rcfg.bornorientrotation);
        ctx.info2.hp = ctx.info2.mp = Const.NULL;
        cur = ctx;
        worldOrFamilyInfo = ctx.info2;
        Trace.debug("init default:{}", this);
    }

    public RoleContext load(xbean.RoleMap info) {
        this.finishPrologue = info.getFinishprologue();
        final List<xbean.RoleMapInfo> xprevs = info.getPrevs();
        if(!xprevs.isEmpty()) {
            for (int i = 0; i < xprevs.size(); i++) {
                final xbean.RoleMapInfo xmi = xprevs.get(i);
                final RoleMapInfo1 rmi = new RoleMapInfo1();
                rmi.mapid = xmi.getMapid();
                rmi.position = FMap.x2m(xmi.getPosition());
                rmi.orient = FMap.x2m(xmi.getOrient());
                rmi.ridestatus = xmi.getRidestatus();
                prevs.add(rmi);
            }
            cur = new RoleMapContext();
            cur.isnew = common.Utils.toByte(info.getIsnew());
            cur.info1 = prevs.remove(prevs.size() - 1);
            cur.info2.hp = info.getHp();
            cur.info2.mp = info.getMp();
            worldOrFamilyInfo = cur.info2;
        } else {
            cur = null;
        }
        last = null;
        Trace.info("load. {}", this);
        return this;
    }

    public void save() {
        Trace.debug("RoleContext.save {}", this);
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                final RoleMapInfo2 pWorld;
                final RoleMapContext pCur;
                final List<RoleMapInfo1> pPrevs;
                synchronized (RoleContext.this) {
                    pWorld = worldOrFamilyInfo;
                    pCur = cur;
                    pPrevs = new ArrayList<>(prevs);
                    if (pCur != null)
                        pPrevs.add(pCur.info1);
                }
                final xbean.RoleMap info = FMap.get(roleid);
                final List<xbean.RoleMapInfo> xms = info.getPrevs();
                xms.clear();
                for (RoleMapInfo1 m : pPrevs) {
                    final xbean.RoleMapInfo x = xbean.Pod.newRoleMapInfo();
                    x.setMapid(m.mapid);
                    FMap.m2x(x.getPosition(), m.position);
                    FMap.m2x(x.getOrient(), m.orient);
                    x.setRidestatus(m.ridestatus);
                    xms.add(x);
                }

                info.setHp(pWorld.hp);
                info.setMp(pWorld.mp);
                info.setIsnew(pCur != null && pCur.isnew != 0);
                return true;
            }
        }.execute();
    }

    public void addOper(Work work, Action onDone, Action onTimeout) {
        synchronized (this) {
            final LockOper op = new LockOper(work, onDone, onTimeout);
            nextOpers.add(op);
            if(curOper == null) {
                next(null);
            }
        }
    }

    private void next(Object param) {
        if(!nextOpers.isEmpty()) {
            final LockOper op = nextOpers.removeFirst();
            this.curOper = op;
            if(op.run(param) != Result.END) {
                    TaskQueue.getScheduleExecutor().schedule(() -> {
                        synchronized (this) {
                            if(this.curOper == op) {
                                try {
                                    if(op.onTimeout != null)
                                        op.onTimeout.process(op);
                                } catch (Exception e) {
                                    Trace.error("RoleContext.addOper", e);
                                }
                                next(null);
                            }
                        }
                    }, 5, TimeUnit.SECONDS);
            } else {
                next(null);
            }
        } else {
            this.curOper = null;
        }
    }

    public void resume(int ctxid, Object param) {
        synchronized (this) {
            if(curOper != null && ctxid == curOper.ctxid) {
                if(curOper.run(param) == Result.END) {
                    curOper.done();
                    next(null);
                }
            }
        }
    }

    private void optimize() {
        if(prevs.size() <= 1) return;
        Trace.debug("before optimis:{}", this);
        final RoleMapInfo1 last = common.Utils.getLast(prevs);
        if(common.Utils.isWorld(last.mapid)) {
            prevs.clear();
            prevs.add(last);
        } else {
            while(prevs.size() >= 2 && common.Utils.isFamily(prevs.get(prevs.size() - 2).mapid)) {
                prevs.remove(prevs.size() - 2);
            }
        }
        Trace.debug("after optimize:{}", this);
    }

    public boolean isInWorldOrFamily() {
        final map.msg.RoleMapContext cur = this.cur;
        return cur != null && common.Utils.isWorldOrFamily(cur.info1.mapid);
    }

    public boolean isInWorld() {
        final map.msg.RoleMapContext cur = this.cur;
        return cur != null && common.Utils.isWorld(cur.info1.mapid);
    }

    public boolean isInFamily() {
        final map.msg.RoleMapContext cur = this.cur;
        return cur != null && common.Utils.isFamily(cur.info1.mapid);
    }

    public boolean isInEctype() {
        final map.msg.RoleMapContext cur = this.cur;
        return cur != null && common.Utils.isEctype(cur.info1.mapid);
    }

    public long getCurMapid() {
        final map.msg.RoleMapContext cur = this.cur;
        return cur != null ? cur.info1.mapid : 0L;
    }

    enum Result {
        CONTINUE,
        END,
    }

    public interface Work {
        Result process(LockOper op, Object param);
    }

    public interface Action {
        void process(LockOper op);
    }


    public class LockOper {
        final int ctxid;
        final Action onDone;
        final Action onTimeout;

        private Work work;

        public LockOper(Work work, Action onDone, Action onTimeout) {
            this.ctxid = ++curCtxId;
            this.onDone = onDone;
            this.onTimeout = onTimeout;
            this.work = work;
        }

        public Result run(Object param) {
            try {
                Trace.debug("run. {}", this);
                return this.work != null ? this.work.process(this, param) : Result.END;
            } catch (Exception e) {
                return Result.END;
            }
        }

        public void done() {
            Trace.debug("done. {}", this);
            if(this.onDone != null) {
                try {
                    this.onDone.process(this);
                } catch (Exception e) {
                    Trace.error("roleid:" + roleid + ", LockOper.done", e);
                }
            }
        }

        public RoleContext getRoleMapInfo() {
            return RoleContext.this;
        }

        public Result fallback() {
            Trace.error("fallback. {}", this);
            if(prevs.isEmpty()) {
                if(finishPrologue) {
                    last = null;
                    initDefault();
                } else {
                    new xdb.Procedure() {
                        @Override
                        protected boolean process() {
                            Trace.debug("reenterCurMap. 5");
                            FMap.openOnePlayerEctypeInProcedure(roleid, CfgMgr.prologue.id);
                            return true;
                        }
                    }.execute();
                    return Result.END;
                }
            } else {
                last = null;
                cur = new RoleMapContext((byte) 0, prevs.remove(prevs.size() - 1), worldOrFamilyInfo);
            }
            return enterMap(this::defaultMEnterMapHandler);
        }

        public Result leaveMap(int reason, boolean reserve, Function<MLeaveMap, Result> onResult) {
            if(cur != null) {
                Trace.debug("leavemap. not null {}", this);
                final XLeaveMap msg = new XLeaveMap();
                msg.ctxid = ctxid;
                msg.roleid = roleid;
                msg.mapid = cur.info1.mapid;
                msg.reserve = common.Utils.toByte(reserve);
                msg.reason = reason;
                this.work = (op, param) -> {
                    Trace.debug("leavemap. work 1{}", this);
                    final MLeaveMap re = (MLeaveMap)param;
                    if(re.retcode == 0 && common.Utils.isWorldOrFamily(re.cur.info1.mapid)) {
                        worldOrFamilyInfo = re.cur.info2;
                        save();
                        Trace.debug("leavemap. work2 {}", this);
                    }
                    Trace.debug("leavemap. work 3 {}", this);
                    if(!re.news.isEmpty()) {
                        last = cur;
                        cur = re.news.get(0);
                        cur.isnew = 1;
                        Trace.debug("leavemap. work 4{}", this);
                        return enterMap(this::defaultMEnterMapHandler);
                    }
                    Trace.debug("leavemap. work 5{}", this);
                    if(!reserve) {
                        last = re.cur;
                        cur = null;
                        Trace.debug("leavemap. work 6 {}", this);
                    }
                    return onResult.apply(re);
                };
                MapClient.send(common.Utils.getHolderserveridById(cur.info1.mapid), msg);
                return Result.CONTINUE;
            } else {
                Trace.debug("leavemap. null 1 {}", this);
                final MLeaveMap re = new MLeaveMap();
                re.retcode = ErrorCode.MAP_NOT_EXIST.getErrorId();
                re.reason = reason;
                re.cur.info1.mapid = 0;
                if(!reserve) {
                    last = cur;
                    cur = null;
                    Trace.debug("leavemap. null 3 {}", this);
                }
                Trace.debug("leavemap. null 2 {}", this);
                return onResult.apply(re);
            }
        }

        public Result enterMap(Function<MEnterMap, Result> onResult) {
            Trace.debug("enterMap. 1{}", this);
            if(isOffline)
                return Result.END;
            Trace.debug("enterMap.2 {}", this);
            if(cur == null)
                return fallback();
            Trace.debug("enterMap. 3 {}", this);
            long mapid = cur.info1.mapid;
            if(common.Utils.isWorld(mapid) && common.Utils.getLineididById(mapid) == 0) {
                Trace.debug("enterMap.4 {}", this);
                final int worldid = common.Utils.getWorldidById(mapid);
                mapid = FMap.getAlternativeWorldMapid(worldid, mapid);
                if(mapid == 0) {
                    Trace.debug("enterMap.5 {}", this);
                    FMap.createWorldMapAndEnterNotInProcedure(worldid, roleid);
                    return Result.END;
                }
            }
            Trace.debug("enterMap.6 {}", this);
            final long finalMapid = mapid;
            final XEnterMap msg = new XEnterMap();
            msg.ctxid = ctxid;
            msg.mapid = mapid;
            this.work = (op, param) -> {
                Trace.debug("enterMap. 7{}", this);
                final MEnterMap re = (MEnterMap)param;
                if(re.retcode == 0) {
                    Trace.debug("enterMap. 8{}", this);
                    if(common.Utils.isWorldOrFamily(re.cur.info1.mapid)) {
                        Trace.debug("enterMap.9 {}", this);
                        worldOrFamilyInfo = re.cur.info2;
                    }
                    Trace.debug("enterMap. 10 {}", this);
                    return onResult.apply(re);
                } else {
                    Trace.debug("enterMap. 11 {}", this);
                    last = null;
                    cur = null;
                    return fallback();
                }
            };
            new xdb.Procedure() {
                @Override
                protected boolean process() {
                    final RoleMapContext pLast;
                    final RoleMapContext pCur;
                    final RoleMapInfo2 pWorld;
                    synchronized (RoleContext.this) {
                        pLast = last;
                        pCur = cur;
                        pWorld = worldOrFamilyInfo;
                    }
                    Trace.debug("enterMap. 12 {}", this);
                    if(ctxid == msg.ctxid)
                        FMap.initPlayerBuilder(msg.player, roleid, FMap.isOneActivePet(common.Utils.getTypeById(finalMapid)), pLast, pCur, pWorld);
                    MapClient.send(common.Utils.getHolderserveridById(finalMapid), msg);
                    return true;
                }
            }.execute();
            Trace.debug("enterMap. 13 {}", this);
            return Result.CONTINUE;
        }

        private Result defaultMEnterMapHandler(MEnterMap re) {
            Trace.debug("defaultMEnterMapHandler. 1 {}", this);
            cur = re.cur;
            cur.isnew = 0;
            if(common.Utils.isWorldOrFamily(cur.info1.mapid)) {
                worldOrFamilyInfo = cur.info2;
                save();
                Trace.debug("defaultMEnterMapHandler. 2 {}", this);
            }
            return Result.END;
        }


        public Result leaveThenEnterPrevMap() {
            Trace.debug("leaveThenEnterPrevMap. 1 {}", this);
            if(cur != null && common.Utils.isWorld(cur.info1.mapid))
                return Result.END;
            return leaveMap(Reason.ENTER_PREV_MAP, false, this::enterPrevMap);
        }

        public Result leaveThenEnterNewMap(long mapid, pathfinding.Vector3 position, pathfinding.Vector3 orient) {
            Trace.debug("leaveThenEnterNewMap. 1 {}", this);
            return leaveMap(Reason.ENTER_NEW_MAP, false, msg -> {
                Trace.debug("leaveThenEnterNewMap. 2 {}", this);
                if(last != null && common.Utils.isWorldOrFamily(last.info1.mapid)) {
                    prevs.add(last.info1);
                    optimize();
                    Trace.debug("leaveThenEnterNewMap. 3 {}", this);
                }
                cur = new map.msg.RoleMapContext();
                // 如果换线.位置与前地图相同
                if(last != null && position == null && common.Utils.isSameWorld(mapid, last.info1.mapid)) {
                    Trace.debug("leaveThenEnterNewMap. 4 {}", this);
                    cur.info1 = last.info1;
                } else if(finishPrologue){
                    Trace.debug("leaveThenEnterNewMap. 5 {}", this);
                    cur.info1.position = MapUtils.p2m(position);
                    cur.info1.orient = MapUtils.p2m(orient);
                }
                Trace.debug("leaveThenEnterNewMap. 6 {}", this);
                cur.info1.mapid = mapid;
                cur.isnew = 1;
                if(common.Utils.isWorldOrFamily(mapid) && worldOrFamilyInfo != null) {
                    Trace.debug("leaveThenEnterNewMap. 7 {}", this);
                    cur.info2 = worldOrFamilyInfo;
                }
                Trace.debug("leaveThenEnterNewMap. 8 {}", this);
                return enterMap(this::defaultMEnterMapHandler);
            });
        }

        private Result enterPrevMap(MLeaveMap msg) {
            Trace.debug("enterPrevMap. 1 {}", this);
            if(!prevs.isEmpty()) {
                cur = new map.msg.RoleMapContext((byte)0, prevs.remove(prevs.size() - 1), worldOrFamilyInfo);
                Trace.debug("enterPrevMap. 2 {}", this);
            } else if(msg.retcode == 0 || finishPrologue){
                last = null;
                initDefault();
                Trace.debug("enterPrevMap. 3 {}", this);
//            } else {
//                Trace.debug("enterPrevMap. 4 {}", this);
//                return Result.END;
            } else {
                return Result.END;
            }
            return enterMap(this::defaultMEnterMapHandler);
        }

        public Result doneLeaveThenEnterPrevMap(MLeaveMap msg) {
            Trace.debug("doneLeaveThenEnterPrevMap. 1 {}", this);
            if(msg.retcode == 0 && common.Utils.isWorldOrFamily(msg.cur.info1.mapid)) {
                worldOrFamilyInfo = msg.cur.info2;
                save();
                Trace.debug("doneLeaveThenEnterPrevMap. 2 {}", this);
            }
            if(!msg.news.isEmpty()) {
                Trace.debug("doneLeaveThenEnterPrevMap. 3 {}", this);
                last = cur;
                cur = msg.news.get(0);
                cur.isnew = 1;
                Trace.debug("doneLeaveThenEnterPrevMap. 4 {}", this);
                return enterMap(this::defaultMEnterMapHandler);
            }
            last = msg.cur;
            cur = null;
            Trace.debug("doneLeaveThenEnterPrevMap. 5 {}", this);
            return finishPrologue || msg.reason != Reason.MAP_CLOSE_KICKOUT || !prevs.isEmpty() ? enterPrevMap(msg) : Result.END;
        }

        public Result reserveCurMap() {
            Trace.debug("reserveCurMap. 1 {}", this);
            setOffline(true);
            return leaveMap(Reason.LOGOUT, true , msg -> {
                Trace.debug("reserveCurMap. 2 {}", this);
                if(msg.retcode == 0) {
                    cur = msg.cur;
                    cur.isnew = 0;
                    Trace.debug("reserveCurMap. 3 {}", this);
                }
                return Result.END;
            });
        }

        public Result reenterCurMap() {
            Trace.debug("reenterCurMap. 1 {}", this);
            setOffline(false);
            if(cur == null) {
                Trace.debug("reenterCurMap. 2 {}", this);
                if(!prevs.isEmpty()) {
                    last = null;
                    cur = new map.msg.RoleMapContext((byte)0, prevs.remove(prevs.size() - 1), worldOrFamilyInfo);
                    Trace.debug("reenterCurMap. 3 {}", this);
                } else if(finishPrologue) {
                    last = null;
                    initDefault();
                    Trace.debug("reenterCurMap. 4 {}", this);
                } else {
                    Trace.debug("reenterCurMap. 6 {}", this);
                    return fallback();
                }
            }
            return enterMap(this::defaultMEnterMapHandler);
        }
    }
}
