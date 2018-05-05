package lx.gs.logger;

import cfg.CfgMgr;
import cfg.bonus.RankType;
import cfg.currency.CurrencyType;
import common.Time;
import gnet.link.Onlines;
import gs.Logger;
import lx.gs.bonus.FBonus;
import lx.gs.family.FFamily;
import lx.gs.item.ItemModule;
import lx.gs.pay.FPay;
import lx.gs.pay.PayModule;
import xbean.RoleInfo;
import xdb.Procedure;
import xtable.Roleinfos;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FLogger {

    /**
     * 3.1 创建角色(即注册日志)
     * @param userid
     * @param roleid
     */
    public static void createrole(long userid, long roleid){
        StringBuilder sb = createroleAfterCommit(userid, roleid);
        log(sb.toString());
    }

    private static StringBuilder createroleAfterCommit(long userid, long roleid){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(userid);

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"createrole\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"mac\":").append("\"").append(getMac(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(userid).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(getRoleName(roleid)).append("\",");
        sb.append("\"occupation\":").append("\"").append(getRolePro(roleid)).append("\"");
        return sb;
    }

	/**
	 * 3.2角色登陆日志
	 * @param roleid
	 * @param role
	 */
	public static void rolelogin(long roleid, xbean.RoleInfo role){
        StringBuilder sb = roleloginAfterCommit(roleid, role);
        log(sb.toString());
	}

    private static StringBuilder roleloginAfterCommit(long roleid, xbean.RoleInfo role){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"rolelogin\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"ip\":").append("\"").append(getPeer(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\"");
        return sb;
    }
	
	/**
	 * 3.3角色登出日志
	 * @param roleid
	 * @param role
	 */

    public static void rolelogout(long roleid, xbean.RoleInfo role, long onlinetime){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"rolelogout\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        final Map<Integer, Long> currencys = role.getCurrencys();
        sb.append("\"xunibileft\":").append("\"").append(currencys.getOrDefault(CurrencyType.XuNiBi, 0L)).append("\",");
        sb.append("\"cashyuanbaoleft\":").append("\"").append(currencys.getOrDefault(CurrencyType.YuanBao, 0L)).append("\",");
        sb.append("\"bindyuanbaoleft\":").append("\"").append(currencys.getOrDefault(CurrencyType.BindYuanBao, 0L)).append("\",");
        long totaltime = TimeUnit.MILLISECONDS.toSeconds(onlinetime);
        sb.append("\"time\":").append("\"").append(totaltime).append("\"");

        Logger.trace(sb.toString());
    }

    /**
     * 3.4 在线人数日志
     */
    public static void onlineuser(){
        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"onlineuser\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append("0").append("\",");
        sb.append("\"platform\":").append("\"").append("0").append("\",");
        sb.append("\"currentuser\":").append("\"").append(Onlines.getInstance().roles().length).append("\"");

        Logger.trace(sb.toString());
    }

    /**
     * 3.5 角色升级日志
     * @param roleid
     * @param role
     */
    public static void levelup(long roleid, xbean.RoleInfo role,int newlevel, long totalLevelupTime){
        StringBuilder sb = levelupAfterCommit(roleid, role, newlevel, totalLevelupTime);
        log(sb.toString());
    }
    
    private static StringBuilder levelupAfterCommit(long roleid, xbean.RoleInfo role,int newlevel, long totalLevelupTime){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"levelup\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(newlevel).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        long totaltime = TimeUnit.MILLISECONDS.toSeconds(totalLevelupTime);//从上一级到本级用时总时长，现在不包括离线时间
        sb.append("\"leveluptime\":").append("\"").append(totaltime).append("\"");
        return sb;
    }

    /**
     * 3.6 角色充值日志（元宝充值）
     */
    public static void addcash(long roleid, xbean.RoleInfo role, int rmb, int realPay, String platorderid, String gsorderid, String addType){
        StringBuilder sb = addcashAfterCommit(roleid, role, rmb, realPay, platorderid, gsorderid, addType);
        log(sb.toString());
    }

    private static StringBuilder addcashAfterCommit(long roleid, xbean.RoleInfo role, int rmb, int realPay, String platorderid, String gsorderid, String addType){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"addcash\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"cash\":").append("\"").append(rmb).append("\",");
        sb.append("\"gameorder\":").append("\"").append(gsorderid).append("\",");
        sb.append("\"platformorder\":").append("\"").append(platorderid).append("\",");
        sb.append("\"iaptype\":").append("\"").append(addType.toLowerCase()).append("\",");
        sb.append("\"realcash\":").append("\"").append(realPay).append("\"");
        return sb;
    }

    /**
     * 3.7 货币增加日志
     * @param roleid
     * @param role

     */
    public static void addyuanbao(long roleid, xbean.RoleInfo role, int currencytype, long add, By addBy){
        StringBuilder sb = addyuanbaoAfterCommit(roleid, role, currencytype, add, addBy);
        log(sb.toString());
    }

    private static StringBuilder addyuanbaoAfterCommit(long roleid, xbean.RoleInfo role, int currencytype, long add, By addBy){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"addyuanbao\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"yuanbaotype\":").append("\"").append(currencytype).append("\",");
        sb.append("\"yuanbaopath\":").append("\"").append(addBy.getType()).append("\",");
        sb.append("\"yuanbao\":").append("\"").append(add).append("\"");
        return sb;
    }

    /**
     * 3.8 货币消耗日志
     * @param roleid
     * @param role
     * @param costMoney
     */
    public static void costYuanbao(long roleid, xbean.RoleInfo role, int type, long costMoney, By costBy){
        StringBuilder sb = costYuanbaoAfterCommit(roleid, role, type, costMoney, costBy);
        log(sb.toString());
    }

    private static StringBuilder costYuanbaoAfterCommit(long roleid, xbean.RoleInfo role, int type, long costMoney, By costBy){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"costyuanbao\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"yuanbaotype\":").append("\"").append(type).append("\",");
        sb.append("\"yuanbaopath\":").append("\"").append(costBy.getType()).append("\",");
        sb.append("\"yuanbao\":").append("\"").append(costMoney).append("\"");
        return sb;
    }

    /**
     *
     * 3.9 游戏商城销售日志
     */
    public static void shop_trade(long roleid, xbean.RoleInfo role, int itemCfgid, int itemcount, int cashtype, int cashneed){
        StringBuilder sb = shop_tradeAfterCommit(roleid, role, itemCfgid, itemcount, cashtype, cashneed);
        log(sb.toString());
    }

    private static StringBuilder shop_tradeAfterCommit(long roleid, xbean.RoleInfo role, int itemCfgid, int itemcount, int cashtype, int cashneed){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"shoptrade\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(getRoleName(roleid)).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"itemtype\":").append("\"").append(getItemType(itemCfgid)).append("\",");
        sb.append("\"itemid\":").append("\"").append(itemCfgid).append("\",");
        sb.append("\"itemcount\":").append("\"").append(itemcount).append("\",");
        sb.append("\"yuanbaotype\":").append("\"").append(cashtype).append("\",");
        sb.append("\"yuanbao\":").append("\"").append(cashneed).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\"");
        return sb;
    }

    /**
     * 3.10 物品获得日志
     * @param roleid
     * @param role
     * @param itemCfgid
     * @param count 本次获得或消耗的数量

     */
    public static void gainitem(long roleid, xbean.RoleInfo role, int itemCfgid, int count, By addBy){
        StringBuilder sb = gainitemAfterCommit(roleid, role, itemCfgid, count, addBy);
        log(sb.toString());
    }
    private static StringBuilder gainitemAfterCommit(long roleid, xbean.RoleInfo role, int itemCfgid, int count, By addBy){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"gainitem\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(getRoleName(roleid)).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"itemtype\":").append("\"").append(getItemType(itemCfgid)).append("\",");
        sb.append("\"itemid\":").append("\"").append(itemCfgid).append("\",");
        sb.append("\"itemcount\":").append("\"").append(count).append("\",");
        sb.append("\"itempath\":").append("\"").append(addBy.getType()).append("\"");
        return sb;
    }

    public static void costitem(long roleid, Map<Integer, Integer> logItem, By by) {
        RoleInfo role = Roleinfos.get(roleid);
        if(logItem != null && !logItem.isEmpty())
            logItem.forEach((itemid, num) -> costitem(roleid, role, itemid, num, by));
    }

    /**
     *
     * 3.11 物品消耗日志
     * @param roleid
     * @param role
     * @param itemCfgid
     * @param count
     */
    public static void costitem(long roleid, xbean.RoleInfo role, int itemCfgid, int count, By costBy){
        StringBuilder sb = costitemAfterCommit(roleid, role, itemCfgid, count, costBy);
        log(sb.toString());
    }

    private static StringBuilder costitemAfterCommit(long roleid, xbean.RoleInfo role, int itemCfgid, int count, By costBy){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"loseitem\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(getRoleName(roleid)).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"itemtype\":").append("\"").append(getItemType(itemCfgid)).append("\",");
        sb.append("\"itemid\":").append("\"").append(itemCfgid).append("\",");
        sb.append("\"itemcount\":").append("\"").append(count).append("\",");
        if(costBy == null){
            sb.append("\"itempath\":").append("\"").append(0).append("\"");//测试时预防为空
            xdb.Trace.warn("loseitem logger lack of by, detail = {}", sb.toString());
        }else {
            sb.append("\"itempath\":").append("\"").append(costBy.getType()).append("\"");
        }
        return sb;
    }

    /**
     * 3.12 角色信息快照日志
     */
    public static void chardata(long roleid, xbean.RoleInfo role){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());
        xbean.RoleFamily familyInfo = FFamily.getRoleFamilyInfo(roleid);
        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"chardata\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"createtime\":").append("\"").append(Time.toFormatStr(role.getCreatetime())).append("\",");
        sb.append("\"occupation\":").append("\"").append(getRolePro(roleid)).append("\",");
        sb.append("\"faction\":").append("\"").append(familyInfo.getCurrentfid()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"viplev\":").append("\"").append(role.getViplevel()).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\"");

        //TODO 可能还需要其他属性
        Logger.trace(sb.toString());
    }


    public static void startInstanceInProcedure(long roleid, xbean.RoleInfo role, int ectypeid){
       new Procedure(){
            @Override
            protected boolean process() throws Exception {
                startinstance(roleid, role, 0, ectypeid);
                return true;
            }
        }.execute();
    }
    /**
     * 开启副本日志
     * @param roleid
     * @param role
     * @param chapterid
     * @param ectypeid
     */
    private static void startinstance(long roleid, xbean.RoleInfo role, int chapterid, int ectypeid){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"startinstance\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"chapterid\":").append("\"").append(chapterid).append("\",");
        sb.append("\"ectypeid\":").append("\"").append(ectypeid).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\"");
        Logger.trace(sb.toString());

    }

    public static void endInstanceInProcedure(long roleid, xbean.RoleInfo role, int ectypeid, int time, int result){
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                endinstance(roleid, role, 0, ectypeid, time, result);
                return true;
            }
        }.execute();
    }
    /**
     * 副本结束信息
     * @param roleid
     * @param role
     * @param chapterid
     * @param ectypeid
     * @param time
     * @param result
     */
    private static void endinstance(long roleid, xbean.RoleInfo role, int chapterid, int ectypeid, int time, int result){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"endinstance\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"chapterid\":").append("\"").append(chapterid).append("\",");
        sb.append("\"ectypeid\":").append("\"").append(ectypeid).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\",");
        sb.append("\"time\":").append("\"").append(Time.toSeconds(time)).append("\",");
        sb.append("\"result\":").append("\"").append(result).append("\"");

        Logger.trace(sb.toString());

    }

    /**
     * 领取任务日志
     * @param roleid
     * @param role
     * @param tasktype
     * @param taskid
     */
    public static void starttask(long roleid, xbean.RoleInfo role, int tasktype, int taskid){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"starttask\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"tasktypeid\":").append("\"").append(tasktype).append("\",");
        sb.append("\"taskid\":").append("\"").append(taskid).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\"");

        Logger.trace(sb.toString());
    }


    /**
     * 结束任务日志,主线任务和环任务是分别处理的
     * @param roleid
     * @param role
     * @param tasktype
     * @param taskid
     */
    public static void endtask(long roleid, xbean.RoleInfo role, int tasktype, int taskid, long time){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"endtask\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"tasktypeid\":").append("\"").append(tasktype).append("\",");
        sb.append("\"taskid\":").append("\"").append(taskid).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\",");
        sb.append("\"time\":").append("\"").append(Time.toSeconds(time)).append("\",");
        sb.append("\"result\":").append("\"").append(0).append("\"");

        Logger.trace(sb.toString());
    }

    public static void startArenaInProcedure(long roleid, xbean.RoleInfo role, int arenaid, long enemyroleid){
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                startarena(roleid, role, arenaid, enemyroleid);
                return true;
            }
        }.execute();
    }
    /**
     * 开启竞技场日志
     * @param roleid
     * @param role
     * @param arenaid
     */
    private static void startarena(long roleid, xbean.RoleInfo role, int arenaid, long enemyroleid){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"startarena\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"enemyroleid\":").append("\"").append(enemyroleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"arenaid\":").append("\"").append(arenaid).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\"");

        Logger.trace(sb.toString());
    }

    public static void endArenaInProcedure(long roleid, xbean.RoleInfo role, int arenaid, int time, int result, long enemyroleid){
        new Procedure(){
            @Override
            protected boolean process() throws Exception {
                endtarena(roleid, role, arenaid, time, result, enemyroleid);
                return true;
            }
        }.execute();
    }
    /**
     * 结束竞技场日志
     * @param roleid
     * @param role
     * @param arenaid
     */
    private static void endtarena(long roleid, xbean.RoleInfo role, int arenaid, int time, int result, long enemyroleid){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"endtarena\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"enemyroleid\":").append("\"").append(enemyroleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"arenaid\":").append("\"").append(arenaid).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\",");
        sb.append("\"time\":").append("\"").append(Time.toSeconds(time)).append("\",");
        sb.append("\"result\":").append("\"").append(result).append("\"");
        Logger.trace(sb.toString());
    }



    public static void chat(long roleid, xbean.RoleInfo role, String content, int chatChannel){
        StringBuilder sb = chatAfterCommit(roleid, role, content, chatChannel);
        log(sb.toString());
    }
    /**
     * 聊天日志
     * @param roleid
     * @param role
     * @param content
     * @return
     */
    private static StringBuilder chatAfterCommit(long roleid, xbean.RoleInfo role, String content, int chatChannel){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"chatlog\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"viplev\":").append("\"").append(role.getViplevel()).append("\",");
        sb.append("\"channel\":").append("\"").append(chatChannel).append("\",");
        sb.append("\"msg\":").append("\"").append(content).append("\"");
        return sb;
    }

    public static void nomarlRank(long roleid, xbean.RoleInfo role, int ranktype, int rank, long value){
        StringBuilder sb = rankInfoAftercommit(roleid, role, ranktype, rank, value);
        log(sb.toString());
    }

    /*
     *排行榜信息,每天记录一次
     */
    private static StringBuilder rankInfoAftercommit(long roleid, xbean.RoleInfo role, int ranktype, int rank, long value){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        String rankname = getRankName(ranktype);
        sb.append("\"logname\":").append("\"").append(rankname).append("\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"occupation\":").append("\"").append(role.getProfession()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"familyname\":").append("\"").append(getFamilyName(roleid)).append("\",");
        sb.append("\"rank\":").append("\"").append(rank).append("\",");
        sb.append("\"score\":").append("\"").append(value).append("\"");
        return sb;
    }


    public static void familyRank(long familyid, int rank){
        StringBuilder sb = familyRankAfterCommit(familyid, rank);
        log(sb.toString());
    }

    /*
     *家族排行榜信息，字段比较特殊
     */
    private static StringBuilder familyRankAfterCommit(long familyid, int rank){
        StringBuilder sb = new StringBuilder();
        xbean.Family familyInfo = xtable.Family.select(familyid);
        sb.append("\"logname\":").append("\"jiazu_rank\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"familyid\":").append("\"").append(familyid).append("\",");
        sb.append("\"familyname\":").append("\"").append(familyInfo.getFamilyname()).append("\",");
        sb.append("\"leaderroleid\":").append("\"").append(familyInfo.getChiefid()).append("\",");
        sb.append("\"leaderrolename\":").append("\"").append(xtable.Roleinfos.selectName(familyInfo.getChiefid())).append("\",");
        sb.append("\"familylev\":").append("\"").append(familyInfo.getFlevel()).append("\",");
        sb.append("\"membercount\":").append("\"").append(familyInfo.getMembers().size()).append("\",");
        sb.append("\"rank\":").append("\"").append(rank).append("\",");
        sb.append("\"score\":").append("\"").append(familyInfo.getTotalbuilddegree()).append("\"");
        return sb;
    }

    /**
     * 抽卡兑换
     * @param roleid
     * @param role
     * @param type
     */
    public static void exchange(long roleid, xbean.RoleInfo role, int type, int subtype){
        StringBuilder sb = exchangeAfterCommit(roleid, role, type, subtype);
        log(sb.toString());
    }

    private static StringBuilder exchangeAfterCommit(long roleid, xbean.RoleInfo role, int type, int subtype){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"exchange\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\",");
        sb.append("\"type\":").append("\"").append(type).append("\",");
        sb.append("\"subtype\":").append("\"").append(subtype).append("\"");
        return sb;
    }

    /**
     * 抽卡
     * @param roleid
     * @param role
     * @param type
     */
    public static void recruit(long roleid, xbean.RoleInfo role, int type){
        StringBuilder sb = recruitAfterCommit(roleid, role, type);
        log(sb.toString());
    }

    private static StringBuilder recruitAfterCommit(long roleid, xbean.RoleInfo role, int type){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"recruit\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\",");
        sb.append("\"type\":").append("\"").append(type).append("\"");
        return sb;
    }

    /**
     * 福利
     * @param roleid
     * @param role
     * @param type
     * @param subtype
     */
    public static void welfare(long roleid, xbean.RoleInfo role, int type, int subtype){
        StringBuilder sb = welfareAfterCommit(roleid, role, type, subtype);
        log(sb.toString());
    }

    private static StringBuilder welfareAfterCommit(long roleid, xbean.RoleInfo role, int type, int subtype){
        xbean.OnlineUser onlineuser = xtable.Onlineusers.select(role.getUserid());

        StringBuilder sb = new StringBuilder();
        sb.append("\"logname\":").append("\"welfare\",");
        sb.append("\"serverid\":").append("\"").append(getServerid()).append("\",");
        sb.append("\"os\":").append("\"").append(getOs(onlineuser)).append("\",");
        sb.append("\"platform\":").append("\"").append(getPlatform(onlineuser)).append("\",");
        sb.append("\"userid\":").append("\"").append(role.getUserid()).append("\",");
        sb.append("\"account\":").append("\"").append(getAccount(onlineuser)).append("\",");
        sb.append("\"roleid\":").append("\"").append(roleid).append("\",");
        sb.append("\"rolename\":").append("\"").append(role.getName()).append("\",");
        sb.append("\"lev\":").append("\"").append(role.getLevel()).append("\",");
        sb.append("\"totalcash\":").append("\"").append(getRolePay(roleid)).append("\",");
        sb.append("\"fight\":").append("\"").append(getFight(roleid)).append("\",");
        sb.append("\"type\":").append("\"").append(type).append("\",");
        sb.append("\"subtype\":").append("\"").append(subtype).append("\"");
        return sb;
    }

	
	private static int getServerid(){
		return Logger.getServerid();
	}
	
	private static String getAccount(xbean.OnlineUser onlineuser){
		return onlineuser == null ? "" : onlineuser.getUsername();
	}
	
	private static String getMac(xbean.OnlineUser onlineuser){
		return onlineuser == null ? "" : onlineuser.getDeviceid();
	}

	private static String getPeer(xbean.OnlineUser onlineuser){
		return onlineuser == null ? "" : onlineuser.getPeer(); 
	}
	
	private static String getPlatform(xbean.OnlineUser onlineUser){ //根据渠道id，给出渠道简称
        String username = getAccount(onlineUser);
        String platstring = username.split("_")[0];
        String empty = "0";
        try{
            int plattype = Integer.parseInt(platstring);
            cfg.role.PlatIdToDetail conf = CfgMgr.platidtodetail.get(plattype);
            if(conf == null)
                return empty;
            String result;
            if(getOs(onlineUser) == 2){//安卓的话，选安卓平台
                result = conf.android.trim();
            }else{
                result = conf.ios.trim();
            }
            return result;
        }catch (NumberFormatException e){
            return empty;
        }
	}
	
	private static int getOs(xbean.OnlineUser onlineuser){
		if(onlineuser == null){
			return 0;
		}
		//1 IOS越狱
		//2 安卓国内
		//3 IOS官服简体
		//4 IOS官服繁体
		String os = onlineuser.getOs();
		try{
			return Integer.parseInt(os);
		}catch(Exception e){
		}
		return 0;
	}

    private static String getRoleName(long roleid){
        return xtable.Roleinfos.selectName(roleid);
    }

    private static int getRolePro(long roleid){
        return xtable.Roleinfos.selectProfession(roleid);
    }

    private static long getRolePay(long roleid){
        return  FPay.getRolePay(roleid).getRoleallpay();
//        return FPay.getRolePay(roleid).getTotalpay() + getMonthCardAndGrowPlan(roleid);
    }

    /**
     * @param roleid
     * @return
     */
    private static long getMonthCardAndGrowPlan(long roleid){
        xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
        long cash = 0L;
        cash += roleInfo.getBuymonthcardtimes() * PayModule.MONTH_CARD_PRICE;
        switch (roleInfo.getBuygrowplan()){
            case 1: return cash + PayModule.GROWPLAN_1;
            case 2: return cash + PayModule.GROWPLAN_1 + PayModule.GROWPLAN_2;
            case 3: return cash + PayModule.GROWPLAN_1 + PayModule.GROWPLAN_2 + PayModule.GROWPLAN_3;
        }
        return cash;
    }

	private static int getItemType(int itemid){
        return ItemModule.getItemType(itemid);
    }

	private static int getFight(long roleid){
        return xtable.Roleattrs.selectTotalcombatpower(roleid);
    }

    /**
     * 事务提交成功后再打印
     * @param string
     */
	private static void log(String string){
        xdb.Transaction.texecuteWhileCommit(()->
                Logger.trace(string));
    }


    private static String getRankName(int rankType){
        switch (rankType){
            case RankType.COMBAT_POWER: return "zhanli_rank";
            case RankType.LEVEL: return "dengji_rank";
            case RankType.PET: return "huoban_rank";
            case RankType.FABAO: return "fabao_rank";
            case RankType.FAMILY: return "jiazu_rank";
            case RankType.XUNIBI: return "jinbi_rank";
            case RankType.CLIMB_TOWER: return "huanyue_rank";
            case RankType.FLOWER: return "xianhua_rank";
            default:return "unknow_rank_type";
        }
    }

    private static String getFamilyName(long roleid){
        xbean.RoleFamily roleFamily = FFamily.getRoleFamilyInfo(roleid);
        if(roleFamily.getCurrentfid() > 0){
            return xtable.Family.selectFamilyname(roleFamily.getCurrentfid());
        }else {
            return "";
        }
    }

	

	

}
