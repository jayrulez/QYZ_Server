package lx.gs.gm;

import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.item.EItemBindType;
import cfg.pay.GrowPlan;
import cfg.pay.GrowPlanType;
import cfg.pay.MonthCard;
import cfg.pay.NormalCharge;
import cfg.tips.LocationType;
import gm.GmException;
import gm.annotation.Cmd;
import gm.annotation.Csi;
import gm.annotation.Module;
import gm.annotation.Param;
import gnet.link.Onlines;
import gnet.link.Role;
import lx.gs.family.FFamily;
import lx.gs.logger.By;
import lx.gs.login.FLogin;
import lx.gs.login.SKickRole;
import lx.gs.mail.FMail;
import lx.gs.pay.FPay;
import lx.gs.pay.SBuyGrowPlanNotify;
import lx.gs.pay.SBuyMonthCardNotify;
import lx.gs.pay.SPaySuccessNotify;
import lx.gs.role.FForbid;
import lx.gs.role.FRole;
import lx.gs.system.FSystem;
import lx.gs.tips.FTips;
import xbean.ForbidItem;
import xbean.SystemMail;
import xdb.Procedure;
import xdb.Trace;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Csi
@Module(comment="运维及客服接口.CustomerService")
public class Cs {
	
	private Map<String, Object> getResult(){
		return new LinkedHashMap<>();
	}
	
	@Cmd(comment="1.1	角色列表查询")
	public Map<String, Object> GetAccCharList(
			@Param(name="accid", comment="用户id：userid") long accid){
		Map<String, Object> result = getResult();
		result.put("accid", accid);
		
		xbean.User user = xtable.Users.select(accid);
		if(user == null){
			throw new GmException("用户不存在，accid=" + accid);
		}
		for (int i = 0; i < 5; i++) {
			String charStr = "";
			if(user.getRoleids().size() > i){
				long roleid = user.getRoleids().get(i);
				xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
				charStr = roleid + "|" + roleInfo.getName() + "|" + roleInfo.getLevel();
			}
			
			result.put("char" + (i + 1), charStr);
		}
		
		return result;
	}
	
	@Cmd(comment="1.2	角色信息查询")
	public Map<String, Object> GetChar(
			@Param(name="charid", comment="角色id：roleid") long charid,
			@Param(name="charname", comment="角色名称") String charname){
		Map<String, Object> result = getResult();
		
		long roleid = charid;
		if(charname != null && !charname.isEmpty()){
			Long id = xtable.Rolename2ids.select(FLogin.makeFullName(charname, gs.Utils.getServerId()));
			if(id != null){
				roleid = id;
			}
		}
		/**
		 * <cmd_command  cmd_data="GetChar" return="true" charid="12345"
		 *  name="角色名" lineid="100" level="200" sex="1" 
		 *  profession="3" isdel="1" registertime="2008-2-3 2:50:50" 
		 *  lastlogintime="2008-2-3 2:50:50" deltime="2008-2-3 2:50:50" 
		 *  ip="127.0.0.1" exp="100" familyname="dddd" gold="100" money="100"/>
		 * */
		
		xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
		if(roleInfo == null){
			throw new GmException("角色未找到");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		result.put("charid", roleid);
		result.put("name", roleInfo.getName());
		result.put("lineid", 1);
		result.put("level", roleInfo.getLevel());
		result.put("sex", roleInfo.getGender());
		result.put("profession", roleInfo.getProfession());
		result.put("isdel", 0);
		result.put("registertime", sdf.format(new Date(roleInfo.getCreatetime())));
		result.put("lastlogintime", sdf.format(new Date(roleInfo.getLastlogintime())));
		result.put("deltime", "");
		result.put("ip", "");
		result.put("exp", roleInfo.getCurrencys().getOrDefault(CurrencyType.JingYan, 0L));
		xbean.Family family = FFamily.getFamilyByRoleId(roleid);
		result.put("familyname", family != null ? family.getFamilyname() : "");
		result.put("gold", FRole.getCurrency(roleid, CurrencyType.XuNiBi));
		result.put("money", FRole.getCurrency(roleid, CurrencyType.YuanBao) + FRole.getCurrency(roleid, CurrencyType.BindYuanBao));
		
		return result;
	}

	@Cmd(comment="1.3	角色禁止登陆")
	public Map<String, Object> AddForbidLogin(
			@Param(name="roleid", comment="roleid") long roleid,
			@Param(name="forbidtime", comment="禁止登陆时间，单位分钟") int forbidtime,
			@Param(name="desc", comment="desc") String desc,
			@Param(name="notifytouser", comment="notifytouser") String notifytouser
			){
		
		FForbid.forbid(roleid, xbean.Forbid.LOGIN, TimeUnit.MINUTES.toMillis(forbidtime), desc, notifytouser);
		//将角色踢下线
		gnet.link.Role role = Onlines.getInstance().find(roleid);
		if(role != null){
			role.send(new SKickRole(notifytouser));
			role.doCompleteLogout();
		}

		return getResult();
	}
	
	@Cmd(comment="1.4	解除角色禁止登陆")
	public Map<String, Object> DelForbidLogin(
			@Param(name="roleid", comment="roleid") long roleid){
		
		FForbid.delete(roleid, xbean.Forbid.LOGIN);
		
		return getResult();
	}
	
	@Cmd(comment="1.5	角色禁止发言")
	public Map<String, Object> AddForbidTalk(
			@Param(name="roleid", comment="roleid") long roleid,
			@Param(name="forbidtime", comment="forbidtime") int forbidtime,
			@Param(name="desc", comment="desc") String desc,
			@Param(name="notifytouser", comment="notifytouser") String notifytouser
			){
		
		FForbid.forbid(roleid, xbean.Forbid.TALK, TimeUnit.MINUTES.toMillis(forbidtime), desc, notifytouser);
		
		return getResult();
	}
	
	@Cmd(comment="1.6	解除禁止发言")
	public Map<String, Object> DelForbidTalk(
			@Param(name="roleid", comment="roleid") long roleid){
		
		FForbid.delete(roleid, xbean.Forbid.TALK);
		
		Map<String, Object> result = getResult();
		return result;
	}
	
	@Cmd(comment="1.7	名称与ID互查")
	public Map<String, Object> GetAccID(
			@Param(name="charid", comment="角色id：roleid") long charid,
			@Param(name="charname", comment="角色名称") String charname){
		Map<String, Object> result = getResult();
		
		long roleid = charid;
		if(charname != null && !charname.isEmpty()){
			Long id = xtable.Rolename2ids.select(FLogin.makeFullName(charname, gs.Utils.getServerId()));
			if(id != null){
				roleid = id;
			}
		}
		
		xbean.RoleInfo roleInfo = xtable.Roleinfos.select(roleid);
		if(roleInfo == null){
			throw new GmException("角色未找到");
		}

		result.put("accid", roleInfo.getUserid());
		result.put("charid", roleid);
		result.put("charname", roleInfo.getName());

		return result;
	}

	@Cmd(comment="1.8  公告发布")
	public Map<String, Object> Bull(
			@Param(name="text", comment="发送公告内容") String text){
		FTips.broadcast(LocationType.CENTER_SCROLL, text);
		return getResult();
	}

	/**
	 当为一个角色发送多个物品时，itemnum为“-1”，itemid的格式如下itemid="物品ID@物品数目@物品ID@物品数目…" itemnum="-1"
	 当为一个角色发送纯文本邮件时，itemnum为“0”，itemid的格式如下itemid=" "  itemnum="0"
	 物品id请通过服务端slk目录下的item.slk查看
	 */
	@Cmd(comment="1.9  邮件发奖/公告")
	public Map<String, Object> MailItemToPlayer(
			@Param(name="playerid", comment="角色id：roleid") long playerid,
			@Param(name="itemid", comment="物品id") String itemid,
			@Param(name="itemnum", comment="物品数量") int itemnum,
			@Param(name="mailtitle", comment="邮件标题") String mailtitle,
			@Param(name="mailcontent", comment="邮件内容") String mailcontent){
		long roleid = playerid;
		final map.msg.Bonus bonus = new map.msg.Bonus();
		bonus.bindtype = EItemBindType.BOUND;

		try {
			if(itemnum == -1){
                String[] items = itemid.split("@");
                for (int i = 0; i < items.length; i += 2) {
                    int itemModelId = Integer.parseInt(items[i]);
                    int num = Integer.parseInt(items[i + 1]);
                    bonus.items.put(itemModelId, num);
                }
            } else if(itemnum != 0){
                bonus.items.put(Integer.parseInt(itemid), itemnum);
            }
		} catch (Exception e) {
			new GmException("itemid错误");
		}

		if(!bonus.items.isEmpty()){
			Trace.info("gm command add mail item, playerid = {}, itemid = {}, itemnum = {}, mailtitle = {}, mailcontent = {}",
					playerid, itemid, itemnum, mailtitle, mailcontent);
		}
		FMail.addMail(roleid, 1, mailtitle, mailcontent, bonus);
		return getResult();
	}

	/**
	 当为全服发送多个物品时，itemnum为“-1”，itemid的格式如下itemid="物品ID@物品数目@物品ID@物品数目…"  itemnum="-1"
	 当为全服发送纯文本邮件时，itemnum为“0”，itemid的格式如下itemid=" "  itemnum="0"
	 根据服务器当前在线人数邮件发送会有延时
     */
	@Cmd(comment="1.10 全服发奖")
	public Map<String, Object> MailToAllPlayer(
			@Param(name="itemid", comment="物品id") String itemid,
			@Param(name="itemnum", comment="物品数量") int itemnum,
			@Param(name="mailtitle", comment="邮件标题") String mailtitle,
			@Param(name="mailcontent", comment="邮件内容") String mailcontent){
		final map.msg.Bonus bonus = new map.msg.Bonus();
		bonus.bindtype = EItemBindType.BOUND;

		try {
			if(itemnum == -1){
				String[] items = itemid.split("@");
				for (int i = 0; i < items.length; i += 2) {
					int itemModelId = Integer.parseInt(items[i]);
					int num = Integer.parseInt(items[i + 1]);
					bonus.items.put(itemModelId, num);
				}
			} else if(itemnum != 0){
				bonus.items.put(Integer.parseInt(itemid), itemnum);
			}
		} catch (Exception e) {
			new GmException("itemid错误");
		}

		if(!bonus.items.isEmpty()){
			Trace.info("gm command add mail item, itemid = {}, itemnum = {}, mailtitle = {}, mailcontent = {}",
					itemid, itemnum, mailtitle, mailcontent);
		}

		int mailId = 1;
		SystemMail systemMail = FMail.addSystemMail(mailId, mailtitle, mailcontent, bonus, Collections.emptyList());
		for (Role role : Onlines.getInstance().roles()) {
			new Procedure(){
				@Override
				protected boolean process() throws Exception {
					FMail.addMail(role.getRoleid(), systemMail);
					return true;
				}
			}.call();
		}
		return getResult();
	}

	@Cmd(comment="1.11  查询角色禁言信息")
	public Map<String, Object> GetRoleForbidChat(
			@Param(name="roleid", comment="角色id：roleid") long roleid){
		ForbidItem talkForbid = FForbid.getTalkItem(roleid);
		if(talkForbid == null){
			throw new GmException("没有封禁记录");
		}
		long begintime = talkForbid.getForbidrealsetime() - talkForbid.getForbidtimeinterval();

		Map<String, Object> result = getResult();
		result.put("begintime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(begintime)));
		result.put("lasttime", TimeUnit.MILLISECONDS.toMinutes(talkForbid.getForbidtimeinterval()));
		result.put("desc", talkForbid.getDesc());
		return result;
	}
	
//	@Cmd(comment="1.12	查询角色禁止登陆信息")
//	public Map<String, Object> GetRoleForbidLogin(
//			@Param(name="roleid", comment="角色id：roleid") long roleid){
//		xbean.ForbidItem loginForbid = FForbid.getLoginItem(roleid);
//		if(loginForbid == null){
//			throw new GmException("没有封禁记录");
//		}
//		long begintime = loginForbid.getForbidrealsetime() - loginForbid.getForbidtimeinterval();
//
//		Map<String, Object> result = getResult();
//		result.put("begintime", new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(begintime)));
//		result.put("lasttime", TimeUnit.MILLISECONDS.toMinutes(loginForbid.getForbidtimeinterval()));
//		result.put("desc", loginForbid.getDesc());
//		return result;
//	}
//
//	@Cmd(comment="1.13 根据平台账号查询游戏账号")
//	public Map<String, Object> GetAccIDByPlatformInfo(
//			@Param(name="platformid", comment="平台id") int platformid,
//			@Param(name="accid", comment="平台账号：platformacctid") long accid){
//		long platformacctid = accid;
//		Map<String, Object> result = getResult();
//		result.put("accid", 0);
//		return result;
//	}
//
//	@Cmd(comment="1.14 查询充值订单")
//	public Map<String, Object> SelectBill(
//			@Param(name="roleid", comment="角色id：roleid") long roleid,
//			@Param(name="chargesn", comment="订单号") String chargesn){
//		Map<String, Object> result = getResult();
//		result.put("desc", "");
//		return result;
//	}
//
//	@Cmd(comment="1.15 充值补单")
//	public Map<String, Object> ConfirmBill(
//			@Param(name="roleid", comment="角色id：roleid") long roleid,
//			@Param(name="chargesn", comment="订单号") String chargesn){
//		Map<String, Object> result = getResult();
//		result.put("desc", "");
//		return result;
//	}
//
//	@Cmd(comment="1.16 活动开关")
//	public Map<String, Object> SwitchModule(
//			@Param(name="open", comment="表示活动的开启以及关闭，0：关闭 1：开启") int open,
//			@Param(name="moduleid", comment="活动id") int moduleid){
//
//		Map<String, Object> result = getResult();
//		result.put("desc", "配置成功");
//		return result;
//	}

	@Cmd(comment="1.17 添加服务器屏蔽字")
	public Map<String, Object> AddForbidWord(
			@Param(name="words", comment="屏蔽字,若要一次性添加多个，请以空格隔开，如'屏蔽字 屏蔽字'") String words){
		List<String> add = Arrays.asList(words.split(" "));
		FSystem.getGMSenseWords().getAddwords().addAll(add);
		FSystem.getGMSenseWords().getRemovewords().removeAll(add);
		return getResult();
	}

	@Cmd(comment="1.18 解除服务器屏蔽字")
	public Map<String, Object> DelForbidWord(
			@Param(name="words", comment="屏蔽字,若要一次性添加多个，请以空格隔开，如'屏蔽字 屏蔽字'") String words){
		FSystem.getGMSenseWords().getRemovewords().addAll(Arrays.asList(words.split(" ")));
		return getResult();
	}

    @Cmd(comment="1.19 模拟充值，不包含成长计划")
    public Object gmCharge(
            @Param(name="roleid", comment="角色id") long roleid,
            @Param(name="productid", comment = "产品id") String productids,
            @Param(name="num", comment = "产品数量") int num){
        Map<Integer, Integer> toCharges = new HashMap<>();
        try {
            if(num == -1){
                String[] products = productids.split("@");
                for (int i = 0; i < products.length; i += 2) {
                    int productid = Integer.parseInt(products[i]);
                    int productnums = Integer.parseInt(products[i + 1]);
                    toCharges.put(productid, productnums);
                }
            } else if(num != 0){
                toCharges.put(Integer.parseInt(productids), num);
            }
        } catch (Exception e) {
            new GmException("输入参数错误");
        }

        xbean.RoleInfo roleInfo = xtable.Roleinfos.get(roleid);
        for(Map.Entry<Integer, Integer> entry : toCharges.entrySet()) {
            int productid = entry.getKey();
            cfg.pay.Charge payCfg = CfgMgr.charge.get(productid);
            if (payCfg.getTypeId() == GrowPlan.TYPEID) {
                continue;
            }
            for (int i = 0; i < entry.getValue(); i++) {
                xbean.RolePay pay = FPay.getRolePay(roleid);
                SPaySuccessNotify payNotify = new SPaySuccessNotify();

                if (payCfg.getTypeId() == NormalCharge.TYPEID) {//如果是正常充值
                    pay.setCangetfirstbonus(true);//表示能领取首冲奖励了,月卡和成长计划不算在首冲里面
                    cfg.pay.NormalCharge nCfg = (NormalCharge) payCfg;
                    payNotify.yuanbao = nCfg.getyuanbao.amount;
                    if (!pay.getIsentryfirstpay().getOrDefault(productid, false)) {
                        payNotify.bindyuanbao = nCfg.firstgetbindyuanbao.amount;
                        pay.getIsentryfirstpay().put(productid, true);
                    } else {
                        payNotify.bindyuanbao = nCfg.getbindyuanbao.amount;
                    }
                } else if (payCfg.getTypeId() == MonthCard.TYPEID) {//如果是月卡充值,现在给元宝
                    cfg.pay.MonthCard mCfg = (MonthCard) payCfg;
                    payNotify.yuanbao = mCfg.getyuanbao.amount;
                    roleInfo.setMonthcardlefttimes(roleInfo.getMonthcardlefttimes() + mCfg.days);
                    roleInfo.setBuymonthcardtimes(roleInfo.getBuymonthcardtimes() + 1);
                }
                if (payNotify.yuanbao > 0) {
                    FRole.addYuanBao(roleid, roleInfo, payNotify.yuanbao, By.Gm);
                }
                if (payNotify.bindyuanbao > 0) {
                    FRole.addCurrency(roleid, roleInfo, CurrencyType.BindYuanBao, payNotify.bindyuanbao, By.Gm);
                }
                //增加充值积分,月卡不算充值积分,积分是元和积分的比例
                if (payCfg.getTypeId() == NormalCharge.TYPEID) {
                    FRole.addCurrency(roleid, roleInfo, CurrencyType.ChongZhiJiFen, Math.multiplyExact(payCfg.price, CfgMgr.firstcharge.rmbtojifen) / 100, By.Gm);
                }

                pay.setRoleallpay(pay.getRoleallpay() + payCfg.price);//记录任何充值记录
                if (payCfg.getTypeId() == NormalCharge.TYPEID) {//只有正常充值才算vip等级
                    FPay.pay(roleid, pay, payCfg);//记录总的充值
                    FRole.addVipLevel(roleid, roleInfo, pay);//记录vip等级改变
                } else if (payCfg.getTypeId() == MonthCard.TYPEID) {
                    SBuyMonthCardNotify monthCardNotify = new SBuyMonthCardNotify();
                    monthCardNotify.buymonthcardtime = roleInfo.getBuymonthcardtimes();
                    monthCardNotify.monthcardleftdays = roleInfo.getMonthcardlefttimes();
                    gnet.link.Onlines.getInstance().send(roleid, monthCardNotify);
                }

                payNotify.productid = productid;
                payNotify.dailypaystatus = pay.getDailypaystatus();
                payNotify.dailytotalpaystatus = pay.getDailytotalpaystatus();
                gnet.link.Onlines.getInstance().send(roleid, payNotify);
            }
        }
        return "succ!";
    }

}
