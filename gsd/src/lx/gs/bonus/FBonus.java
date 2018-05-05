package lx.gs.bonus;

import cfg.CfgMgr;
import cfg.bonus.BaoZi;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import cfg.item.ItemTitle;
import cfg.tips.LocationType;
import common.Bonus;
import common.ErrorCode;
import common.Time;
import gnet.link.Onlines;
import lx.gs.SError;
import lx.gs.bag.FBag;
import lx.gs.bonus.msg.SBonusInfo;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.login.FLogin;
import lx.gs.mail.FMail;
import lx.gs.pay.FPay;
import lx.gs.pet.FPet;
import lx.gs.role.FRole;
import lx.gs.role.title.FTitle;
import lx.gs.tips.FTips;
import cfg.tips.TipsCode;
import xdb.Transaction;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class FBonus {

    public static void genBonusByProfession(int profession, cfg.cmd.action.Bonus src, int num, map.msg.Bonus dst) {
        common.Bonus.genBonusByProfession(profession, src, num, dst);
    }

    public static void genBonusByProfession(int profession, cfg.cmd.action.Bonus src, int num, Bonus.BindType bindType, map.msg.Bonus dst) {
        dst.bindtype = bindType.toInt();
        common.Bonus.genBonusByProfession(profession, src, num, dst);
    }

    public static void genBonus(long roleid, cfg.cmd.action.Bonus src, map.msg.Bonus dst) {
        common.Bonus.genBonusByProfession(xtable.Roleinfos.selectProfession(roleid), src, 1, dst);
    }

    public static void genBonus(long roleid, cfg.cmd.action.Bonus src, int num, map.msg.Bonus dst) {
        common.Bonus.genBonusByProfession(xtable.Roleinfos.selectProfession(roleid), src, num, dst);
    }

    public static void genBonus(long roleid, cfg.cmd.action.Bonus src, Bonus.BindType bindType, map.msg.Bonus dst) {
        dst.bindtype = bindType.toInt();
        common.Bonus.genBonusByProfession(xtable.Roleinfos.selectProfession(roleid), src, 1, dst);
    }

    public static void genBonus(long roleid, cfg.cmd.action.Bonus src, int num, Bonus.BindType bindType, map.msg.Bonus dst) {
        dst.bindtype = bindType.toInt();
        common.Bonus.genBonusByProfession(xtable.Roleinfos.selectProfession(roleid), src, num, dst);
    }

    /**
     * 无法回滚的操作调用此方法，失败后奖励会通过邮件发放
     *
     * 注意：尽量不要调用此方法，邮箱容量有限
     * @param roleid
     * @param src
     * @param num
     * @param bindType
     * @param by
     */
    public static void addBonusAlwaysSucc(long roleid, cfg.cmd.action.Bonus src, int num, Bonus.BindType bindType, By by) {
        final map.msg.Bonus dst = new map.msg.Bonus();
        genBonus(roleid, src, num, bindType, dst);
        addBonusAlwaysSucc(roleid, dst, by);
    }

    public static void addBonusAlwaysSucc(long roleid, Bonus.BindType bindType, HashMap<Integer, Integer> items, By by) {
        addBonusAlwaysSucc(roleid, new map.msg.Bonus(bindType.toInt(), items), by);
    }

    public static void addBonusAlwaysSucc(long roleid, map.msg.Bonus bonus, By by) {
        if(!addBonus0(roleid, bonus, by)){
            Onlines.getInstance().send(roleid, FTips.create(LocationType.CENTER, TipsCode.BAG_FULL_TRANSFER_2_MAIL));
            FMail.addMail(roleid, 13, bonus);
        }
    }

    public static void addBonus(long roleid, map.msg.Bonus bonus,BonusStrategy strategy, By by) {
        if(!addBonus0(roleid, bonus, by)){
            switch (strategy){
                case TIPS_DISCARD:{
                    Onlines.getInstance().send(roleid, FTips.create(LocationType.CENTER, TipsCode.BAG_FULL));
                    break;
                }
                case TIPS_MAIL:{
                    Onlines.getInstance().send(roleid, FTips.create(LocationType.CENTER, TipsCode.BAG_FULL_TRANSFER_2_MAIL));
                    FMail.addMail(roleid, 13, bonus);
                    break;
                }
                case ALERT:{
                    Onlines.getInstance().send(roleid, FTips.create(LocationType.ALERT, TipsCode.BAG_FULL));
                    break;
                }
                default:break;
            }
        }
    }


    public static boolean addBonus(long roleid, cfg.cmd.action.Bonus src, Bonus.BindType bindType, By by) {
        final map.msg.Bonus dst = new map.msg.Bonus();
        genBonus(roleid, src, 1, bindType, dst);
        return addBonus(roleid, dst, by);
    }

    public static boolean addBonus(long roleid, cfg.cmd.action.Bonus src, int num, Bonus.BindType bindType, By by) {
        final map.msg.Bonus dst = new map.msg.Bonus();
        genBonus(roleid, src, num, bindType, dst);
        return addBonus(roleid, dst, by);
    }

    public static boolean addBonus(long roleid, Bonus.BindType bindType, HashMap<Integer, Integer> items, By by) {
        return addBonus(roleid, new map.msg.Bonus(bindType.toInt(), items), by);
    }

    public static boolean addBonus(long roleid, map.msg.Bonus bonus, By by) {
        boolean isSucc = addBonus0(roleid, bonus, by);
        if(!isSucc) {
            Transaction.tsend(roleid, new SError(ErrorCode.BAG_FULL.getErrorId()));
        }
        return isSucc;
    }

    private static boolean addBonus0(long roleid, map.msg.Bonus bonus, By by){
        final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
        final int bindType = EItemBindType.enums.contains(bonus.bindtype) ? bonus.bindtype : EItemBindType.BOUND;
        for (Map.Entry<Integer, Integer> bo : bonus.items.entrySet()) {
            final int itemid = bo.getKey();
            final int num = bo.getValue();
            if (CurrencyType.enums.contains(itemid)) {
                FRole.addCurrency(roleid, info, itemid, num, by);
            } else if (CfgMgr.itembasic.containsKey(itemid) && CfgMgr.itembasic.get(itemid).getTypeId() == ItemTitle.TYPEID) {
                ItemTitle it = (ItemTitle) CfgMgr.itembasic.get(itemid);
                if (CfgMgr.title.containsKey(it.id)) {
                    FTitle.unlockTitle(roleid, it.id);
                }
                FLogger.gainitem(roleid, info, itemid, num, by);
            } else if (CfgMgr.petfragment.containsKey(itemid)) {
                FPet.addPetFragment(roleid, itemid, num, by);
            } else if (CfgMgr.petbasicstatus.containsKey(itemid)) {
                FPet.addPet(roleid, itemid, num, by);
            } else if (CfgMgr.itembasic.containsKey(itemid)
                    || CfgMgr.fragment.containsKey(itemid)
                    || CfgMgr.equip.containsKey(itemid)
                    || CfgMgr.talismanbasic.containsKey(itemid)) {
                if(!FBag.addItemToBag(roleid, itemid, num, bindType != EItemBindType.NOT_BOUND, by)){
                    return false;
                }
            } else {
                throw new RuntimeException("addToBag. unknown itemid:" + itemid);
            }
        }
        return true;
    }

    public static boolean genAndAddBonus(long roleid, cfg.cmd.action.Bonus src, Bonus.BindType bindType, map.msg.Bonus dst, By by) {
        genBonus(roleid, src, 1, bindType, dst);
        return addBonus(roleid, dst, by);
    }

    public static void genAndAddBonusAlwaysSucc(long roleid, cfg.cmd.action.Bonus src, Bonus.BindType bindType, map.msg.Bonus dst, By by) {
        genBonus(roleid, src, 1, bindType, dst);
        if(!addBonus(roleid, dst, by)){
            FMail.addMail(roleid, 1, dst);
        }
    }

    public static void genBonusByReflection(long roleid, Object cmd, int num, Bonus.BindType bindType, map.msg.Bonus dst) {
        dst.bindtype = bindType.toInt();
        Class<?> cls = cmd.getClass();
        for (Field f : cls.getFields()) {
            if (cfg.cmd.action.Action.class.isAssignableFrom(f.getType())) {
                try {
                    genBonus(roleid, (cfg.cmd.action.Bonus) f.get(cmd), num, dst);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // impossible
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean addBonusByReflection(long roleid, Object cmd, int num, Bonus.BindType bindType, By by) {
        final map.msg.Bonus dst = new map.msg.Bonus();
        genBonusByReflection(roleid, cmd, num, bindType, dst);
        return addBonus(roleid, dst, by);
    }

    public static boolean genAndAddBonusByReflection(long roleid, Object cmd, int num, Bonus.BindType bindType, map.msg.Bonus dst, By by) {
        genBonusByReflection(roleid, cmd, num, bindType, dst);
        return addBonus(roleid, dst, by);
    }

    public boolean tryAddBonus(long roleid, map.msg.Bonus dst, By by) {
        return addBonus(roleid, dst, by);
    }

    public static xbean.RoleInfo getRoleInfo(long roleid) {
        return xtable.Roleinfos.get(roleid);
    }

    public static xbean.RoleWelfareInfo get(long roleid) {
        xbean.RoleWelfareInfo welfare = xtable.Rolewelfareinfo.get(roleid);
        if (welfare == null) {
            welfare = xbean.Pod.newRoleWelfareInfo();
            xtable.Rolewelfareinfo.insert(roleid, welfare);
        }
        return welfare;
    }

    public static void mutiBonus(map.msg.Bonus gift, int mutiple) { //按制定倍数增加奖励
        for (Map.Entry<Integer, Integer> entry : gift.items.entrySet()) {
            entry.setValue(Math.multiplyExact(entry.getValue(), mutiple));
        }
    }

    /**
     * 判断月卡是否有效
     *
     * @param roleid
     * @param date
     * @return
     */
    public static boolean isCurrentMonthCardDay(long roleid, int date) {
        xbean.RoleInfo roleinfo = getRoleInfo(roleid);
        int leftDays = roleinfo.getMonthcardlefttimes();
        if (leftDays <= 0) {
            return false;
        }
        if (leftDays % 30 == 0) { //只要剩下天数是30的倍数是，就将月卡当前天置为1
            return date == 1;
        } else {
            return date == (30 - leftDays % 30 + 1);
        }
    }

    public static void clearWelFareRecords(long roleid) {
        xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
        if(Time.isSameDay(roleinfo.getLastaddlogindaytime(), System.currentTimeMillis())){
            return;
        }
        xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
        roleinfo.setLastaddlogindaytime(System.currentTimeMillis());
        roleinfo.setLogindaycount(roleinfo.getLogindaycount() + 1); //总的登录天数加一天
//        if (roleinfo.getLastlogintime() < common.Time.getTimeInfo().monthZeroTime) { //每月清空
//            welfare.getSigndate().clear();
//            welfare.setAddsigncount(0);
//        }
        if (welfare.getSigndate().size() >= 30) {//现在是签到30天一个循环
            welfare.getSigndate().clear();
        }
        welfare.setHastodaysign(0);
        final int newLoginDay = roleinfo.getContinuesloginday() + 1;
        roleinfo.setContinuesloginday(newLoginDay);
        welfare.getReceiveconlogingift().clear();
        FLogin.onContinueLoginDayChange(roleid, newLoginDay);
//		roleinfo.setLeftgifttimes(roleinfo.getContinuesloginday());//领奖次数最多为7次
        roleinfo.setLeftgifttimes(Math.min(7, newLoginDay));
        roleinfo.setTotaldayonlinetime(0L); //重置在线时长
        welfare.getReceivedonlinegift().clear(); //清空领取的在线时长奖励类型
        if (roleinfo.getMonthcardlefttimes() > 0) {
            roleinfo.setMonthcardlefttimes(roleinfo.getMonthcardlefttimes() - 1);//月卡剩余天数减一天
        }
        if (roleinfo.getMonthcardlefttimes() % 30 == 0) {
            welfare.getReceivedmonthcarddate().clear(); //月卡30天为一个周期，每过30天清空一次领奖情况
        }
        welfare.setWishtimes(0); //清空已许愿次数
        welfare.setIseatdinner(0);
        welfare.setIseatlunch(0);
    }

    public static void convert(map.msg.Bonus from, xbean.Bonus to) {
        to.setBindtype(from.bindtype);
        to.getItems().putAll(from.items);
    }

    public static map.msg.Bonus convert(xbean.Bonus from) {
        map.msg.Bonus to = new map.msg.Bonus();
        convert(from, to);
        return to;
    }

    public static void convert(xbean.Bonus from, map.msg.Bonus to) {
        to.bindtype = from.getBindtype();
        to.items.putAll(from.getItems());
    }

    public static xio.Protocol creatInfo(long roleid) {
        xbean.RoleWelfareInfo welfare = get(roleid);
        xbean.RoleInfo roleinfo = getRoleInfo(roleid);
        xbean.RolePay rolepay = FPay.getRolePay(roleid);
        SBonusInfo response = new SBonusInfo();

        response.receivednewgift.addAll(welfare.getReceivednewgift());
        response.totallogindays = roleinfo.getLogindaycount();

        response.signedlist.addAll(welfare.getSigndate());
        response.totaldays = common.Time.getCurrentMonthDays();
//        response.addsigntimes = welfare.getAddsigncount();
        response.hastodaysign = welfare.getHastodaysign();

        response.continuedays = Math.min(7, roleinfo.getContinuesloginday());
        response.lefttimes = roleinfo.getLeftgifttimes();
        response.receivedconlogingift.addAll(welfare.getReceiveconlogingift());

        response.receivedtype.addAll(welfare.getReceivedonlinegift());
        response.dailyonlinetime = FLogin.calcTodayOnlineTime(roleinfo);

        if (roleinfo.getBuymonthcardtimes() > 0) {
            response.monthcardplayer = 1;
        } else {
            response.monthcardplayer = 0;
        }
        response.dates.addAll(welfare.getReceivedmonthcarddate());
        response.monthcardleftday = roleinfo.getMonthcardlefttimes();

        response.buygrowplantype = roleinfo.getBuygrowplan();
        response.buygrowplantypetwo = roleinfo.getBuygrowplantwo();
        response.buygrowplantypethr = roleinfo.getBuygrowplanthree();
        response.growplanonetime = roleinfo.getGrowonetime();
        response.growplantwotime = roleinfo.getGrowtwotime();
        response.growplanthreetime = roleinfo.getGrowthreetime();
        response.threegifts.addAll(welfare.getThreegrowplan());
        response.fivegifts.addAll(welfare.getFivegrowplan());
        response.sevengifts.addAll(welfare.getSevengrowplan());

        response.isfirstpayused = common.Utils.toByte(rolepay.getIsfirstpayused());//是否领取过首冲奖励
        response.dailytotalcharge = rolepay.getDailytotalpay();
        response.totalcharge = rolepay.getTotalpay();
        response.totalcost = roleinfo.getTotalcostyuanbao();
        response.hasbuyproduct.addAll(rolepay.getIsentryfirstpay().keySet());
        response.receivedchargegifts.addAll(welfare.getReceivedpaycharge());
        response.isactivepay = rolepay.getDailyactivepay();

        response.lastwishtime = welfare.getLastwishtime();
        response.wishtimes = welfare.getWishtimes();
        response.lastwishpetid = welfare.getLastwishpetid();

        response.iseatlunch = welfare.getIseatlunch();
        response.iseatdinner = welfare.getIseatdinner();
        return response;
    }

    /**
     * 判断当前时间是否在吃包子的时间段
     *
     * @param conf
     * @return
     */
    public static boolean isInTimeInterval(BaoZi conf) {
        long cur = System.currentTimeMillis();
        long todayPass = common.Time.getDayMilliseconds(cur);
        return todayPass >= common.Time.getTodayMillisecondByHourAndMin(conf.starthour, conf.startminute) &&
                todayPass <= common.Time.getTodayMillisecondByHourAndMin(conf.endhour, conf.endminute);
    }

    /**
     * 判断是否已经过了吃包子的时间
     *
     * @param conf
     * @return
     */
    public static boolean isAfterEatTime(BaoZi conf) {
        long cur = System.currentTimeMillis();
        long todayPass = common.Time.getDayMilliseconds(cur);
        return todayPass > common.Time.getTodayMillisecondByHourAndMin(conf.endhour, conf.endminute);
    }
}
