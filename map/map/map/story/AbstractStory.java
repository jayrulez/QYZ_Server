package map.map.story;

import cfg.CfgMgr;
import cfg.ectype.LayoutType;
import common.ErrorCode;
import common.Utils;
import map.MapUtils;
import map.agent.Player;
import map.map.Ectype;
import map.map.MapMgr;
import map.msg.*;
import xdb.Trace;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HuangQiang on 2016/5/30.
 */
public abstract class AbstractStory extends Ectype {
    public AbstractStory(Builder b) {
        super(b);

        this.ectypeCfg = CfgMgr.storylayout.get(b.subid);
    }

    private final cfg.ectype.StoryLayout ectypeCfg;

    public static class Layout {
        public boolean showGlobalTips;
        public cfg.ectype.Layout layoutCfg;
        public boolean completed;
        public boolean start;
        public Script script;
        public HashSet<Integer> openEntryids = new HashSet<>();
        public HashSet<Integer> openExitids = new HashSet<>();
    }

    public static class ActionInfo {
        public Object action;
        public boolean isClientAction;
        public boolean suspendMap;
    }

    protected Story.Layout curLayout = null;
    protected final Map<Integer, Story.Layout> layouts = new HashMap<>();
    protected final Map<Integer, Integer> enviroments = new HashMap<>();
    protected final HashMap<Integer, Story.ActionInfo> actions = new LinkedHashMap<>();
    protected int lastPromptAction = -1;

    private boolean ready;

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    @Override
    public pathfinding.Vector3 getInitPosition() {
        return MapUtils.c2p(curLayout.layoutCfg.startposition);
    }

    @Override
    public pathfinding.Vector3 getInitOrient() {
        return  MapUtils.createOrient(curLayout.layoutCfg.startrotation);
    }

    @Override
    protected void onPrecreatePlayer(PlayerBuilder b) {
        super.onPrecreatePlayer(b);
        final map.msg.RoleMapContext last = b.last.isEmpty() ? null : b.last.get(0);
        final map.msg.AgentBuilder ab = b.basic.basic;
        if(last != null && isTaskEctype() && common.Utils.isWorld(last.info1.mapid) && basiccfg.scenename.equals(CfgMgr.worldmap.get(Utils.getWorldidById(last.info1.mapid)).scenename)) {
            ab.position = last.info1.position;
            ab.orient = last.info1.orient;
        } else {
            ab.position = MapUtils.p2m(getInitPosition());
            ab.orient = MapUtils.p2m(getInitOrient());
        }
    }

    @Override
    protected MLeaveMap createMLeaveMap(int ctxid, Player player, int reason) {
        final MLeaveMap msg = super.createMLeaveMap(ctxid, player, reason);
        final int newWorldid = ectypeCfg.storyexitscene;
        map.msg.RoleMapContext last = player.getLastRoleMapContext();
        if(last != null && isTaskEctype() && isSuccCompleted() && newWorldid > 0) {
            final RoleMapContext ctx = new RoleMapContext((byte)1, new RoleMapInfo1(), last.info2);
            ctx.info1.mapid = common.Utils.makeId(getServerid(), MapMgr.Ins.getLocalServerid(), newWorldid, 0);
            if(basiccfg.scenename.equals(CfgMgr.worldmap.get(newWorldid).scenename)) {
                ctx.info1.position = MapUtils.p2m(player.getPosition());
                ctx.info1.orient = MapUtils.p2m(player.getOrient());
            } else {
                ctx.info1.position = common.Utils.c2m(ectypeCfg.storyexitposition);
                ctx.info1.orient = MapUtils.p2m(MapUtils.createOrient(ectypeCfg.exitrotation));
            }
            msg.news.add(ctx);
        }
        return msg;
    }

    @Override
    protected void broadcastToSameCamp(Player player) {
    }

    public void beginNotEndClientAction(int actionid) {
        broadcastNotContextMsg(new SActionBegin(actionid));
    }

    public void beginAction(int actionid, Object action, boolean isClientAction, boolean suspendMap) {
        Trace.debug("beginAction. actionid:{} action:{} isclientaction:{} suspendmap:{}", actionid, action, isClientAction, suspendMap);
        final Story.ActionInfo a = new Story.ActionInfo();
        a.action = action;
        a.isClientAction = isClientAction;
        a.suspendMap = suspendMap;
        actions.put(actionid, a);
        broadcastNotContextMsg(new SActionBegin(actionid));
        if(suspendMap) {
            setSuspend();
        }
        if(isClientAction) {
            schedule(() -> {
                if(actions.containsKey(actionid)) {
                    Trace.error("story:{} not confirm client actionid:{}", this, actionid);
                    endClientAction(actionid);
                }
            }, CfgMgr.ectypesingle.clientactiontimeout * 1000);
        }
    }

    public void beginPromptAction(int actionid) {
        endPromptAction();
        lastPromptAction = actionid;
        beginAction(actionid, null, false, false);
    }

    public void endPromptAction() {
        if(lastPromptAction > 0) {
            endAction(lastPromptAction);
            lastPromptAction = -1;
        }
    }

    public void endAction(int actionid) {
        final Story.ActionInfo ai = actions.remove(actionid);
        if(ai != null) {
            broadcastNotContextMsg(new SActionEnd(actionid));
            if(ai.suspendMap)
                clearSuspend();
            assert(!ai.isClientAction);
        }
    }

    public void endClientAction(int actionid) {
        final Story.ActionInfo a = actions.get(actionid);
        if(a != null && a.isClientAction) {
            actions.remove(actionid);
            if(a.suspendMap)
                clearSuspend();
        }
    }

    public boolean isClientActionEnd(int actionid) {
        return !actions.containsKey(actionid);
    }


    public void init() {
        super.init();

        for(cfg.ectype.Enviroment ev : ectypeCfg.enviroments) {
            this.enviroments.put(ev.name, ev.value);
        }

        ectypeCfg.layouts.stream().filter(layoutCfg -> layoutCfg.type == LayoutType.ENTER).forEach(layoutCfg -> {
            Layout layout = createLayout(layoutCfg);
            layout.start = true;
            layouts.put(layoutCfg.id,  layout);
        });
        curLayout = layouts.values().iterator().next();

        ready = false;
    }

    LayoutInfo genLayoutInfo(Story.Layout layout) {
        final LayoutInfo info = new LayoutInfo();
        info.id = layout.layoutCfg.id;
        info.completed = layout.completed ? 1 : 0;
        info.openentryids.addAll(layout.openEntryids);
        info.openexitids.addAll(layout.openExitids);
        info.showglobaltips = common.Utils.toByte(layout.showGlobalTips);
        return info;
    }

    Story.Layout createLayout(cfg.ectype.Layout layoutCfg) {
        final Story.Layout layout = new Story.Layout();
        layout.script = new Script(this, layoutCfg);
        layout.layoutCfg = layoutCfg;
        layout.start = false;
        layout.showGlobalTips = false;
        //layout.openEntryids
        return layout;
    }

    public void openLayout(int layoutid) {
        Trace.debug("Story.openLayout {} layoutid:{}", this, layoutid);
        if(layouts.containsKey(layoutid)) {
            xdb.Trace.error("Story.openlayout fail. layout has opened! {} layoutid:{}", this, layoutid);
            broadcastNotContextMsg(new SOpenLayout(genLayoutInfo(layouts.get(layoutid))));
            return;
        }

//		if(!curLayout.completed) {
//			xdb.xdb.Trace.error("Story.openLayout fail! curLayout:{} not completed", curLayout.layoutCfg.mapid);
//			return;
//		}
//        if(layouts.values().stream().allMatch(layout -> layout.openExitids.stream().allMatch(e -> layout.layoutCfg.exits_id.get(e).linkedlayout != layoutid)))  {
//            xdb.Trace.error("layout:{} not in any linkedlayout. ", layoutid);
//            return;
//        }
        if(!layouts.values().stream().filter(layout -> layout.completed).anyMatch(layout -> layout.layoutCfg.exits.stream().anyMatch(p -> p.linkedlayout == layoutid))) {
            xdb.Trace.error("this:{} layout:{} not in any linkedlayout. ", this, layoutid);
            return;
        }

        // 打开下一个版面后,关闭当前版面所有出口
        final Story.Layout newLayout = createLayout(ectypeCfg.layouts_id.get(layoutid));
        layouts.put(layoutid, newLayout);

        broadcastNotContextMsg(new SOpenLayout(genLayoutInfo(newLayout)));
    }

    public void closeLayout(int layoutid) {
        final Story.Layout layout = layouts.remove(layoutid);
        if(layout == null) {
            xdb.Trace.error("Story.closeLayout  ectypeid:{} layoutid not open!", ectypeid);
            return;
        }
        layout.script.end();
        if(curLayout == layout) {
            curLayout = layouts.values().iterator().next();
        }
        layouts.values().forEach(y -> y.start = true);
        broadcastNotContextMsg(new SCloseLayout(layoutid));
    }

    public void showCurLayoutGlobalTips() {
        if(curLayout != null) {
            curLayout.showGlobalTips = true;
        }
    }

    public int setEnvVar(int var, int op, int value) {
        final int oldValue = this.enviroments.getOrDefault(var, 0);
        final int newValue;
        if(op == cfg.ectype.MathOperator.SET) {
            newValue = value;
        } else {
            switch(op) {
                case cfg.ectype.MathOperator.ADD: {
                    newValue = oldValue + value;
                    break;
                }
                case cfg.ectype.MathOperator.SUB: {
                    newValue = oldValue + value;
                    break;
                }
                case cfg.ectype.MathOperator.MUL: {
                    newValue = oldValue * value;
                    break;
                }
                default :
                    throw new RuntimeException("unknown MathOperator:" + op);
            }
        }

        this.enviroments.put(var, newValue);
        broadcastNotContextMsg(new SChangeEnviroment(var, newValue));
        Trace.debug("Story.setEnvVar var:{} op:{} value:{} oldvalue:{} newvalue:{}",
                var, op, value, oldValue, newValue);
        return newValue;
    }

    public int getEnvVar(int var) {
        return this.enviroments.getOrDefault(var, 0);
    }

    public Story.Layout getLayout(int layoutid) {
        return layouts.get(layoutid);
    }

    public void openEntry(int layoutid, int entryid) {
        Trace.debug("Story.openEntry mapid:{} layoutid:{} entryid:{}", getMapid(), layoutid, entryid);
        final Story.Layout layout = getLayout(layoutid);
        if(layout == null)
            return;
        if(layout.openEntryids.add(entryid))
            broadcastNotContextMsg(new SChangeEntry(layoutid, entryid, 1));
    }

    public void closeEntry(int layoutid, int entryid) {
        Trace.debug("Story.closeEntry mapid:{} layoutid:{} entryid:{}", getMapid(), layoutid, entryid);
        final Story.Layout layout = getLayout(layoutid);
        if(layout == null)
            return;
        if(layout.openEntryids.remove(entryid))
            broadcastNotContextMsg(new SChangeEntry(layoutid, entryid, 0));
    }

    public void openExit(int layoutid, int exitid) {
        Trace.debug("Story.openExit mapid:{} layoutid:{} entryid:{}", getMapid(), layoutid, exitid);
        final Story.Layout layout = getLayout(layoutid);
        if(layout == null)
            return;
        if(layout.openExitids.add(exitid))
            broadcastNotContextMsg(new SChangeExit(layoutid, exitid, 1));
    }

    public void closeExit(int layoutid, int exitid) {
        Trace.debug("Story.closeExit mapid:{} layoutid:{} entryid:{}", getMapid(), layoutid, exitid);
        final Story.Layout layout = getLayout(layoutid);
        if(layout == null)
            return;
        if(layout.openExitids.remove(exitid))
            broadcastNotContextMsg(new SChangeExit(layoutid, exitid, 0));
    }

    public void completeCurLayout(int layoutid) {
        onCompleteCurLayout(layoutid);
    }

    private void onCompleteCurLayout(int layoutid) {
        final Story.Layout layout = layouts.get(layoutid);
        if(layout.completed) {
            xdb.Trace.error("Story.onCompleteCurLayout. mapid:{} curlayout:{} has completed", getMapid(), layoutid);
            return;
        }
        Trace.debug("Story.onCompleteCurLayout. mapid:{} curlayout:{}", getMapid(), layoutid);
        layout.completed = true;
        broadcastNotContextMsg(new SCompleteLayout(layoutid));
        // 没有出口说明是最后一个版面
        if(layout.layoutCfg.exits.isEmpty()) {
            checkEnd(ErrorCode.OK);
        }
    }

    @Override
    protected void onPlayerExceedMaxDeadCount(Player player) {
        checkEnd(ErrorCode.ECTYPE_MAX_DEAD_COUNT);
    }

    protected void updateScripts(long now) {
        layouts.values().stream().filter(layout -> layout.start).forEach(layout -> layout.script.update(now));
    }

    @Override
    protected void normalUpdate(long now) {

    }

    @Override
    public void onPlayerReady(Player player) {
        super.onPlayerReady(player);
        if(notEnd() && !this.isReady()) {
            setReady(true);
            startRemainTime();
            clearSuspend();
        }
    }

    @Override
    public void update(long now) {
        super.update(now);
        if(this.ready) {
            updateScripts(now);
        }
    }
}
