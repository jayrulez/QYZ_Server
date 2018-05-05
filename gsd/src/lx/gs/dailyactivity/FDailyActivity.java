package lx.gs.dailyactivity;

import cfg.CfgMgr;
import cfg.active.Activebonus;
import cfg.active.AddType;
import cfg.currency.CurrencyType;
import cfg.family.FamilyInfo;
import common.ErrorCode;
import lx.gs.bonus.FBonus;
import lx.gs.dailyactivity.msg.*;
import lx.gs.family.FFamily;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import lx.gs.task.FTask;
import map.msg.Bonus;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public final class FDailyActivity {
    public static xbean.DailyActive getDailyBean(long roleid) {
        xbean.DailyActive info = xtable.Dailyactives.get(roleid);
        if (info == null) {
            info = xbean.Pod.newDailyActive();
            xtable.Dailyactives.insert(roleid, info);
        }
        return info;
    }
    /**
     * 获取系统完成情况
     * @param roleid
     * @param response
     * @return
     */
    public static ErrorCode getActiveInfo(long roleid, SGetActiveInfo response) {
//        if(FTask.getCompleteCount(FTask.getTask(roleid), Activebonus.requiretask) <= 0){
//            return ErrorCode.TASK_NOT_COMPLETED;
//        }
        xbean.DailyActive info = getDailyBean(roleid);
        response.scores = info.getActivescores();
        response.receivedbonus.addAll(info.getReceivedbonus());
        response.activetimes.putAll(info.getActivetimes());
        return ErrorCode.OK;
    }
    
    public static int getEventTime(long roleid, int event){
    	xbean.DailyActive info = getDailyBean(roleid);
    	int times = info.getActivetimes().getOrDefault(event,0);
    	return times;	
    }
    /**
     * 获取活跃奖励
     * 
     * @param roleid
     * @param type
     * @param response
     * @return
     */
    public static ErrorCode getActiveBonus(long roleid, int type, SGetActiveBonus response) {
        xbean.DailyActive info = getDailyBean(roleid);
//        if(FTask.getCompleteCount(FTask.getTask(roleid), Activebonus.requiretask) <= 0){
//            return ErrorCode.TASK_NOT_COMPLETED;
//        }
        if (info.getReceivedbonus().contains(type)) {
            return ErrorCode.HAS_RECEIVED;
        }
        if (info.getActivescores() < type) {
            return ErrorCode.NOT_ENOUGH_SCORES;
        }
        info.getReceivedbonus().add(type);
        cfg.active.Activebonus bonus = cfg.CfgMgr.activebonus.get(type);
        if(!FBonus.genAndAddBonus(roleid, bonus.award, common.Bonus.BindType.BIND, response.bonus, By.Active_Award)){
            return ErrorCode.BAG_FULL;
        }
        response.bonustype = type;
        return ErrorCode.OK;
    }

    /**
	 * 获取未完成活动的次数和找回能得到的奖励;
	 * 
	 * @param roleid
	 * @param response
	 */
	public static void getUndoActiveInfo(long roleid, Undoactiveinfos response) {
	    xbean.DailyActive activeInfo = getDailyBean(roleid);
	    int key = getFindbackCfgKey(activeInfo);
        if(key == 0)
            return;
	    cfg.active.Findback findbackcfg = cfg.CfgMgr.findback.get(key);// 配置中配置的是按找回一次配置的
	    getUndoActiveBonusDatail(roleid, activeInfo, response, findbackcfg, -1, -1);
	}
	/**
     * 完成日常活动后增加积分接口
     * 
     * @param roleid
     * @param eventType
     *            为cfg.active.EVENTNUM枚举值中的某一种
     */
    public static void addActiveScores(long roleid, int eventType) {// 每次完成活动后调用判断是否增加积分
        addActiveScores(roleid, eventType, 1);
    }

    public static void addActiveScores(long roleid, int eventType, int eventTimes){
        xbean.DailyActive info = getDailyBean(roleid);
        cfg.active.Activeevent event = cfg.CfgMgr.activeevent.get(eventType);
        Map<Integer, Integer> activeTimes = info.getActivetimes();
        if (activeTimes.get(eventType) == null) { // 如果是第一次做该任务
            int finalNum = Math.min(eventTimes, event.times);
            activeTimes.put(eventType, finalNum);
            if (event.addtype == cfg.active.AddType.ADDPERTIME) {
                info.setActivescores(info.getActivescores() + event.addnum * finalNum);
            }else if (event.addtype == cfg.active.AddType.ADDTOTAL) {
                if(eventTimes >= event.times){ //如果直接超过了需要做的次数
                    info.setActivescores(info.getActivescores() + event.addnum);
                }
            }
        } else { // 如果已经做过该任务
            int doneTimes = activeTimes.get(eventType);
            int need = event.times - doneTimes;
            if(need == 0){//如果已经做满了，不做处理
                return;
            }else if (eventTimes >= need) { // 加上当前刚好做满时，不管是单次还是全部都增加积分
                activeTimes.put(eventType, event.times);
                if(event.addtype == AddType.ADDTOTAL) {
                    info.setActivescores(info.getActivescores() + event.addnum);
                }else if(event.addtype == AddType.ADDPERTIME){
                    info.setActivescores(info.getActivescores() + event.addnum * need);
                }
            } else if(eventTimes < need) {
                activeTimes.put(eventType, doneTimes + eventTimes);
                if (event.addtype == cfg.active.AddType.ADDPERTIME) {
                    info.setActivescores(info.getActivescores() + event.addnum * eventTimes);
                }
            }
        }
        SActiveInfoChangeNotify notify = new SActiveInfoChangeNotify();
        notify.changeactivetimes.put(eventType, activeTimes.get(eventType));
        notify.scores = info.getActivescores();
        gnet.link.Onlines.getInstance().send(roleid, notify);//发送活动完成次数改变的信息
    }

    /**
     * 给找回系统中每种行为记次数,和活跃度中的事件略有重复
     * @param roleid
     * @param eventType
     */
    public static void addFindBackTimes(long roleid, int eventType){
        xbean.DailyActive info = getDailyBean(roleid);
        Map<Integer, Integer> doneTimes = info.getEventdonetimes();
        int eventTime = doneTimes.getOrDefault(eventType, 0);
        doneTimes.put(eventType, eventTime + 1);
    }
    
    /**
     * 每天0点和当天第一次登录的时候清空用户昨日的活跃信息
     * @param roleid
     */
    public static void clearActiveInfo(long roleid) { 
        xbean.DailyActive info = getDailyBean(roleid);
        info.getUndoactive().clear();//先清空,再统计新的
        int vipLevel = xtable.Roleinfos.selectViplevel(roleid);
        for (cfg.active.FindBackCandoTimes e : CfgMgr.findbackcandotimes.values()) { // 统计每种活动未完成的情况
            int doneTimes = info.getEventdonetimes().getOrDefault(e.eventtype, 0);
            int allTimes = e.candotimes.entertimes.get(vipLevel);
            int undoTimes = allTimes - doneTimes;
            if(undoTimes > 0){
                info.getUndoactive().put(e.eventtype, undoTimes);
            }
        }
        int activeScores = (int)(info.getActivescores() * FamilyInfo.ACTIVE_TRANSFER_RATE);
        info.setActivescores(0);
        info.getReceivedbonus().clear();
        info.getActivetimes().clear();
        info.getEventdonetimes().clear();
        xbean.Family family = FFamily.getFamilyByRoleId(roleid);
        if(family != null && activeScores > 0){
            FFamily.addBuildGrade(roleid, family, activeScores);
        }
        info.setLastactivelvl(xtable.Roleinfos.selectLevel(roleid));
    }

    public static int getFindbackCfgKey(xbean.DailyActive info) { // 找到符合当前等的找回level
        Set<Integer> keySet = cfg.CfgMgr.findback.keySet();
        List<Integer> a = keySet.stream().filter(i -> i <= info.getLastactivelvl()).collect(Collectors.toList());
        if(a.size() == 0){
            return 0;
        }
        return Collections.max(a);
    }

    /**
     * 
     * @param activeInfo
     * @param response
     * @param config
     * @param eventType
     *            事件类型，-1时生成所有的
     * @param findType
     *            找回方式,分为金币找回和元宝找回,-1时都生成
     */
    public static void getUndoActiveBonusDatail(long roleid, xbean.DailyActive activeInfo, Undoactiveinfos response,
            cfg.active.Findback config, int eventType, int findType) {
    	Map<Integer, Integer> undoTimes = activeInfo.getUndoactive();
        if (eventType == -1) { // 找回所有系统未完成的活动
            for (cfg.active.SystemLookBack lookback : config.findsystemlist) {// 配置中配置的是按找回一次配置的
                Undoactiveinfo undoInfo = new Undoactiveinfo();
                undoInfo.eventtype = lookback.eventtype;
                undoInfo.undotimes = undoTimes.getOrDefault(lookback.eventtype, 0);
                if (undoInfo.undotimes > 0) { // 只有在未完成次数大于0时，才生成相应信息
                    genBonusByType(roleid, lookback, findType, undoInfo);
                    response.undoactive.put(lookback.eventtype, undoInfo);
                }
            }
        } else {
            config.findsystemlist.stream().filter(i -> i.eventtype == eventType)
                    .findFirst().ifPresent(lookback -> { // 具体的找回类型
                Undoactiveinfo undoInfo = new Undoactiveinfo();
                undoInfo.eventtype = lookback.eventtype;
                undoInfo.undotimes = undoTimes.getOrDefault(lookback.eventtype, 0);
                if (undoInfo.undotimes > 0) {
                    genBonusByType(roleid, lookback, findType, undoInfo);
                    response.undoactive.put(lookback.eventtype, undoInfo);
                }
            });
        }
    }

    /**
     * @param lookback
     * @param findType
     * @param undoInfo
     */
    public static void genBonusByType(long roleid, cfg.active.SystemLookBack lookback, int findType, Undoactiveinfo undoInfo) {
        switch (findType) {
        case -1: { // 这个-1纯粹是为了生成所有找回信息，具体找回时还是使用的下面两种
            undoInfo.costjinbi = lookback.xunibicost.amount * undoInfo.undotimes;
            undoInfo.costyuanbao = lookback.yuanbaocost.amount * undoInfo.undotimes;
            FBonus.genBonus(roleid, lookback.xunibiaward, undoInfo.undotimes, common.Bonus.BindType.BIND, undoInfo.jinbifindbackbonus);
            FBonus.genBonus(roleid, lookback.yuanbaoaward, undoInfo.undotimes, common.Bonus.BindType.BIND, undoInfo.yuanbaofindbackbonus);
            break;
        }
        case Findbacktype.JINBI_FIND: {
            undoInfo.costjinbi = lookback.xunibicost.amount * undoInfo.undotimes;
            FBonus.genBonus(roleid, lookback.xunibiaward, undoInfo.undotimes, common.Bonus.BindType.BIND, undoInfo.jinbifindbackbonus);
            break;
        }
        case Findbacktype.YUANBAO_FIND: {
            undoInfo.costyuanbao = lookback.yuanbaocost.amount * undoInfo.undotimes;
            FBonus.genBonus(roleid, lookback.yuanbaoaward, undoInfo.undotimes, common.Bonus.BindType.BIND, undoInfo.yuanbaofindbackbonus);
            break;
        }
        default:
            break;
        }
    }

    /**
     * 找回不同系统中为完成的活动
     * @param roleid
     * @param findType 
     * @param eventType
     * @return
     */
    public static ErrorCode findBack(long roleid, int findType, int eventType) {
        xbean.DailyActive activeInfo = getDailyBean(roleid);
        xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
//        if(roleinfo.getLevel() < Activebonus.openlevel){
//            return ErrorCode.NOT_ENOUGH_LEVEL;
//        }
        Undoactiveinfos response = new Undoactiveinfos();
        int key = getFindbackCfgKey(activeInfo);
        if(key == 0){
            return ErrorCode.NO_FIND_CONTENT;
        }
        cfg.active.Findback findbackcfg = cfg.CfgMgr.findback.get(key);
        switch (findType) {
        case Findbacktype.JINBI_FIND: {
            getUndoActiveBonusDatail(roleid, activeInfo, response, findbackcfg, eventType, findType);
            break;
        }
        case Findbacktype.YUANBAO_FIND: {
            getUndoActiveBonusDatail(roleid, activeInfo, response, findbackcfg, eventType, findType);
            break;
        }
        case Findbacktype.JINBI_FINDALL: {
            getUndoActiveBonusDatail(roleid, activeInfo, response, findbackcfg, -1, Findbacktype.JINBI_FIND);
            break;
        }
        case Findbacktype.YUANBAO_FINDALL: {
            getUndoActiveBonusDatail(roleid, activeInfo, response, findbackcfg, -1, Findbacktype.YUANBAO_FIND);
            break;
        }
        default:
            throw new RuntimeException("unknown findType" + findType);
        }
        if (response.undoactive.isEmpty()) {
            return ErrorCode.HAS_FIND_BACK;
        }
        for (Entry<Integer, Undoactiveinfo> entry : response.undoactive.entrySet()) {
            activeInfo.getUndoactive().put(entry.getKey(), 0); // 将已找回的系统未完成活动数重置为0；同时将奖励放入背包
            if (entry.getValue().costjinbi > 0) {
                if (!FRole.checkCostCurrency(roleid, roleinfo, CurrencyType.XuNiBi, entry.getValue().costjinbi, By.Find_Back)) {
                    return ErrorCode.XUNIBI_NOT_ENOUGH;
                }
                FBonus.addBonus(roleid, entry.getValue().jinbifindbackbonus, By.Find_Back);
            }
            if (entry.getValue().costyuanbao > 0) {
                if (!FRole.checkCostCurrency(roleid, roleinfo, CurrencyType.YuanBao, entry.getValue().costyuanbao, By.Find_Back)) {
                    return ErrorCode.YUANBAO_NOT_ENOUGH;
                }
                if(!FBonus.addBonus(roleid, entry.getValue().yuanbaofindbackbonus, By.Find_Back)){
                    return ErrorCode.BAG_FULL;
                }
            }
        }
        return ErrorCode.OK;
    }
    
    /**
     * 一键找回时生成所有的找回物品,但是这里没有定义次数
     */
    public static void genTotalFindBackBonus(Undoactiveinfos response){ 
    	Undoactiveinfo total = new Undoactiveinfo();
    	total.eventtype = cfg.active.EventNum.TOTAL;
    	for(Undoactiveinfo undo : response.undoactive.values()){
    		total.costjinbi += undo.costjinbi;
    		total.costyuanbao += undo.costyuanbao;
    		addBonus(total.jinbifindbackbonus, undo.jinbifindbackbonus);
    		addBonus(total.yuanbaofindbackbonus, undo.yuanbaofindbackbonus);
    	}
    	response.undoactive.put(total.eventtype, total);
    }
    
    public static void addBonus(Bonus dest, Bonus source){
    	for(Entry<Integer, Integer> entry : source.items.entrySet()){
    		common.Utils.addValue(dest.items, entry.getKey(), entry.getValue());
    	}
    }
}
