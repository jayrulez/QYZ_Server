package lx.gs.gm;


import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.bonus.FBonus;
import lx.gs.bonus.msg.CGetGrowPlanGift;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.pay.FPay;

@Module(comment="福利模块GM命令")
public class Welfare {
	@Cmd(comment="清空已签到日期.")
	public void clearSignInfo(@Param(name="roleid",comment="角色id：roleid")long roleid){
		xbean.RoleWelfareInfo welfare = xtable.Rolewelfareinfo.get(roleid);
		if(!welfare.getSigndate().isEmpty()){
			welfare.getSigndate().clear();
		}
		welfare.setHastodaysign(0);
	}
	
	@Cmd(comment="设置月卡剩余天数")
	public Object setMonthCardDays(@Param(name="roleid",comment="角色id：roleid")long roleid,
			@Param(name="MonthCardDays",comment="月卡剩余天数：MonthCardDays")int days){
		xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
		roleinfo.setMonthcardlefttimes(days);
		return "用户 " + roleid + "当前月卡剩余天数 " + days;
	}
	
	@Cmd(comment="设置是否购买过成长计划")
	public Object setBuyGrowPlan(@Param(name="roleid",comment="角色id：roleid")long roleid,
			@Param(name="isbuygrowplan",comment="是否购买过成长计划，1为买过,0为没买过")int buygrowplan, @Param(name="isbuyMonthCard",comment="是否购买过月卡，1为买过")int buymonthcard){
		xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
		roleinfo.setBuygrowplan(buygrowplan);
		roleinfo.setBuymonthcardtimes(buymonthcard);
		if(buygrowplan == 0){
			xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
			welfare.getReceivedgrowplangift().clear();//清空领取过的成长计划信息
		}
		return "用户 " + roleid + "购买成长计划 " + buygrowplan + " 购买月卡" + buymonthcard;
	}
	
	@Cmd(comment="设置物品数量")
	public Object setBaodieNums(@Param(name="roleid",comment="角色id")long roleid,@Param(name="itemkey",comment="物品id") int itemkey,@Param(name="num",comment="物品数量") int num){
		FItem.addItemToBag(roleid, itemkey, num, true, By.Gm);
		int curNums = FItem.getItemNum(roleid, itemkey, false);
		return "当前编号为 " + itemkey + " 的物品数量为 " + curNums;
	}
	
	@Cmd(comment="设置许愿次数")
	public Object setWishTime(@Param(name="roleid",comment="角色id")long roleid,@Param(name="wishtimes",comment="许愿次数") int wishtimes,@Param(name="lastwishtime",comment="上次许愿时间,只有为0时有效，其余值不改变") long lastwishtime){
		xbean.RoleWelfareInfo rolewel = FBonus.get(roleid);
		rolewel.setWishtimes(wishtimes);
		if(lastwishtime == 0){
			rolewel.setLastwishtime(lastwishtime);
		}
		return "当期许愿次数为" + wishtimes + ", 上次许愿时间为" + rolewel.getLastwishtime();
	}
	
	@Cmd(comment="设置总的登录天数")
	public Object setTotalLogin(@Param(name="roleid",comment="角色id")long roleid, @Param(name="days",comment="总计登录天数")int days){
		xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
		roleinfo.setLogindaycount(days);
		return "当前总计登录天数为 " + roleinfo.getLogindaycount();
	}
	
	@Cmd(comment="设置连续登录天数")
	public Object setContinueLogin(@Param(name="roleid",comment="角色id")long roleid, @Param(name="days",comment="连续登录天数")int days){
		xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
		roleinfo.setContinuesloginday(days);
		return "当前连续登录天数为 " + roleinfo.getContinuesloginday();
	}
	
	@Cmd(comment="设置连续登录剩余领奖次数")
	public Object setLeftLoginGift(@Param(name="leftTimes",comment="剩余次数")int leftTimes){
        final long roleid = GmSession.current().getRoleid();
        xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
		roleinfo.setLeftgifttimes(leftTimes);
		return "当前剩余领奖次数为 " + roleinfo.getLeftgifttimes();
	}
	
	
	@Cmd(comment="设置充值消费情况")
	public Object setPayAndCost(@Param(name="roleid",comment="角色id")long roleid,@Param(name="dailyPay",comment="当日总计充值，为0时不变化") int dailyPay, 
			@Param(name="total",comment="总计充值，为0时不变化")int totalPay, @Param(name="dailyPay",comment="总计消费，为0时不变化")int totalCost){
		xbean.RolePay rolePay = FPay.getRolePay(roleid);
		xbean.RoleInfo roleInfo = FBonus.getRoleInfo(roleid);
		if(dailyPay != 0){
			rolePay.setDailytotalpay(dailyPay);
		}
		if(totalPay != 0){
			rolePay.setTotalpay(totalPay);
		}
		if(totalCost != 0){
			roleInfo.setTotalcostyuanbao(totalCost);
		}
		return "每日总计充值为" + rolePay.getDailytotalpay() + ", 总计充值为" + rolePay.getTotalpay() + ", 总计花费为" + roleInfo.getTotalcostyuanbao();
	}
	
	@Cmd(comment="清空已领取的新手礼包")
	public Object clearNewGift(@Param(name="roleid",comment="角色id")long roleid){
		xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
		welfare.getReceivednewgift().clear();
		return "当前已领取的新手礼包数为 " + welfare.getReceivednewgift().size();
	}

    @Cmd(comment="查询福利信息")
    public Object queryWelfFareInfo(@Param(name="roleid",comment="角色id")long roleid){
        return FBonus.get(roleid);
    }

    @Cmd(comment = "模拟领取成长计划礼包")
    public Object getGrowPlanGift(@Param(name="type",comment="成长计划档位，有1,2,3档")int type, @Param(name="index",comment="具体领取哪一条")int index){
        gs.Utils.call(new CGetGrowPlanGift(type, index));
        return "succ";
    }

}
