package lx.gs.role;


import cfg.CfgMgr;
import cfg.achievement.CounterType;
import cfg.currency.CurrencyType;
import cfg.family.FamilyInfo;
import common.AttrUtils;
import common.ErrorCode;
import common.Utils;
import gs.Listeners;
import lx.gs.achievement.FAchievement;
import lx.gs.activity.FActivity;
import lx.gs.bonus.FBonus;
import lx.gs.equip.FEquip;
import lx.gs.event.EventModule;
import lx.gs.event.LevelUpEvent;
import lx.gs.event.XunibiAddEvent;
import lx.gs.family.FFamily;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.map.FMap;
import lx.gs.pay.SVipLevelNotify;
import lx.gs.role.msg.RoleShowInfo3;
import lx.gs.role.msg.SLevelChange;
import lx.gs.system.FSystem;
import map.msg.MemberInfo;
import map.msg.SChangeLevel;
import map.msg.SChangeVipLevel;
import xbean.Pod;
import xbean.RoleMemInfo;
import xtable.Rolefamily;
import xtable.Rolememinfos;

import java.util.List;
import java.util.Map;

public final class FRole {
//
//    protected static Font nameFont;
//
//    public static void initFont(String nameFontFile) throws Exception {
//        nameFont = Font.createFont(Font.TRUETYPE_FONT, new File(nameFontFile));
//    }

    public static boolean containsWhiteSpaceChar(String word) {
        return word.chars().anyMatch(Character::isWhitespace);
        //return word.chars().anyMatch(ch -> Character.isWhitespace(ch) || !nameFont.canDisplay(ch));
    }

    public static boolean isSenseWord(String word) {
        return RoleModule.senseWordPattern.matcher(word).find() ||
                RoleModule.fuzzySenseWord.matcher(word).find();
    }
    public static String replaceFuzzySenseWord(String word){
        return RoleModule.fuzzySenseWord.matcher(word).replaceAll("***");
    }

    public static String replaceSenseWordWithStar(String word) {
        return RoleModule.senseWordPattern.matcher(word).replaceAll("***");
    }

	public static void addCurrency(long roleid, int type, long add, By by) {
		addCurrency(roleid, xtable.Roleinfos.get(roleid), type, add, by);
	}
	
	public static void addCurrency(long roleid, xbean.RoleInfo info, int type, long add, By by) {
		xdb.Trace.debug("role.addCurrency roleid:{} type:{} add:{} addby:{}", roleid, type, add, by);
		if(add == 0)
			return;
		if(add < 0) 
			throw new RuntimeException(String.format("role.addCurrency roleid:%d type:%d add:%d < 0", roleid, type, add));

		switch(type) {
			case CurrencyType.XuNiBi:
			case CurrencyType.YuanBao:
			case CurrencyType.BindYuanBao:
			case CurrencyType.LingJing:
			case CurrencyType.ZaoHua:
			case CurrencyType.WuXing:
			case CurrencyType.BangPai:
			case CurrencyType.ShiMen:
			case CurrencyType.ZhanChang:
			case CurrencyType.ShengWang:
			case CurrencyType.HuoBanJiFen:
			case CurrencyType.FaBaoJiFen:
			case CurrencyType.ChengJiu:
			case CurrencyType.BangGong:
			case CurrencyType.TianFu:
            case CurrencyType.ChongZhiJiFen:
            case CurrencyType.TiLi:{
				addNormalCurrency(roleid, info, type, add, by);
				break;
			}
			case CurrencyType.JingYan: {
				addExp(roleid, info, add, by);
				break;
			}
			default :
				throw new RuntimeException("unknown currency type:" + type);
		}
		RoleModule.INSTANCE.recordCurrencyAdd(roleid, type, add);
	}

	private static void addNormalCurrency(long roleid, xbean.RoleInfo info, int type, long add, By by) {
		final Map<Integer, Long> currencys = info.getCurrencys();
		final long newValue = currencys.getOrDefault(type, 0L) + add;
		currencys.put(type, newValue);
		switch(type) {
			case CurrencyType.XuNiBi: {
				FAchievement.addCounter(roleid, CounterType.XUNIBI_NUM_OBTAIN, add);
				EventModule.INSTANCE.broadcastEvent(new XunibiAddEvent(roleid, newValue));
				break;
			}
			case CurrencyType.ShengWang: {
				FAchievement.updateCounter(roleid, CounterType.SHENGWANG, newValue);
				break;
			}
            case CurrencyType.ChengJiu:{
                FActivity.checkAchievementPoints(roleid);//运营活动，成就点数
                break;
            }
		}
		FLogger.addyuanbao(roleid, info, type, add, by);//货币增加日志
	}

    public static boolean  addTiLiToLimit(long roleid, xbean.RoleInfo info, long add, By by) {
        if(add <= 0) return true;
        final Map<Integer, Long> currencys = info.getCurrencys();
        final long oldValue = currencys.getOrDefault(CurrencyType.TiLi, 0L);
        final long maxTili = CfgMgr.roleconfig.maxtili;
        if(oldValue < maxTili) {
            addNormalCurrency(roleid, info, CurrencyType.TiLi, Math.min(add, maxTili - oldValue), by);
            return true;
        }
        return false;
    }

    public static ErrorCode costCurrency(long roleid, int type, long cost, By by) {
        if(checkCostCurrency(roleid, xtable.Roleinfos.get(roleid), type, cost, by)){
            return ErrorCode.OK;
        }
        switch (type){
            case CurrencyType.XuNiBi: return ErrorCode.XUNIBI_NOT_ENOUGH;
            case CurrencyType.YuanBao: return ErrorCode.YUANBAO_NOT_ENOUGH;
            case CurrencyType.BindYuanBao: return ErrorCode.BINDYUANBAO_NOT_ENOUGH;
            case CurrencyType.LingJing: return ErrorCode.LINGJING_NOT_ENOUGH;
            case CurrencyType.JingYan: return ErrorCode.JINGYAN_NOT_ENOUGH;
            case CurrencyType.ZaoHua: return ErrorCode.ZAOHUA_NOT_ENOUGH;
            case CurrencyType.WuXing: return ErrorCode.WUXING_NOT_ENOUGH;
            case CurrencyType.BangPai: return ErrorCode.BANGPAI_NOT_ENOUGH;
            case CurrencyType.ShiMen: return ErrorCode.SHIMEN_NOT_ENOUGH;
            case CurrencyType.ZhanChang: return ErrorCode.ZHANCHANG_NOT_ENOUGH;
            case CurrencyType.ShengWang: return ErrorCode.SHENGWANG_NOT_ENOUGH;
            case CurrencyType.HuoBanJiFen: return ErrorCode.HUOBANJIFEN_NOT_ENOUGH;
            case CurrencyType.FaBaoJiFen: return ErrorCode.FABAOJIFEN_NOT_ENOUGH;
            case CurrencyType.ChengJiu: return ErrorCode.CHENGJIU_NOT_ENOUGH;
            case CurrencyType.BangGong: return ErrorCode.BANGGONG_NOT_ENOUGH;
            case CurrencyType.TianFu: return ErrorCode.TIANFU_NOT_ENOUGH;
            case CurrencyType.TiLi: return ErrorCode.TILI_NOT_ENOUGH;
            case CurrencyType.ChongZhiJiFen: return ErrorCode.CHONGZHIJIFEN_NOT_ENOUGH;
            default: return ErrorCode.CURRENCY_IS_NOT_ENOUGH;
        }
    }

	public static boolean checkCostCurrency(long roleid, int type, long cost, By by) {
		return checkCostCurrency(roleid, xtable.Roleinfos.get(roleid), type, cost, by);
	}
	
	public static boolean checkCostCurrency(long roleid, xbean.RoleInfo info, int type, long cost, By by) {
		xdb.Trace.debug("role.costCurrency roleid:{} type:{} cost:{} costby:{}", roleid, type, cost, by);
		if(cost == 0)
			return true;
		if(cost < 0) 
			throw new RuntimeException(String.format("role.costCurrency roleid:%s type:%s cost:%s < 0", roleid, type, cost));
		final Map<Integer, Long> currencys = info.getCurrencys();
		long newValue = currencys.getOrDefault(type, 0L) - cost;
		if(newValue < 0) {
			if(type != CurrencyType.BindYuanBao || !checkCostCurrency(roleid, info, CurrencyType.YuanBao, -newValue, by))
				return false;
			newValue = 0;
		}
		switch(type) {
			case CurrencyType.ChengJiu:
				// 这两种只检查不扣
				break;
			default:
				currencys.put(type, newValue);
		}

		if(type == CurrencyType.YuanBao){
			info.setTotalcostyuanbao(info.getTotalcostyuanbao() + (int)cost); //总计花费的元宝做一个记录
            FActivity.checkTotalCost(roleid, cost);//运营活动，花费元宝
		} else if (type == CurrencyType.XuNiBi) {
			FAchievement.addCounter(roleid, CounterType.XUNIBI_NUM_CONSUME, cost);
		} else if (type == CurrencyType.TiLi){
            FMap.checkMultiStoryTili(roleid, currencys.get(type));
        }
        FLogger.costYuanbao(roleid, info, type, cost, by);
		return true;
	}
	
	public static void costCurrency(long roleid, xbean.RoleInfo info, int type, long cost, By by) {
		if(!checkCostCurrency(roleid, info, type, cost, by))
			throw new RuntimeException(String.format("role.costCurrency not enought. roleid:%s type:%s cost:%s costby:%s", roleid, type, cost, by));
	}
	
	public static long getCurrency(long roleid, int type) {
		return getCurrency(xtable.Roleinfos.get(roleid), type);
	}
	
	public static long getCurrency(xbean.RoleInfo info, int type) {
		switch(type) {
			default:
				return info.getCurrencys().getOrDefault(type, 0L);
		}
	}

    private static void addExp(long roleid, xbean.RoleInfo info, long add, By by) {
        final Map<Integer, Long> currencys = info.getCurrencys();
        final long oldExp = currencys.getOrDefault(CurrencyType.JingYan, 0L);
        long newExp = oldExp + add;
        final int oldLevel = info.getLevel();
        int newLevel = oldLevel;
        cfg.role.ExpTable curet = CfgMgr.exptable.get(newLevel);
        while(newExp >= Utils.selfOrMax(curet.exp)) {
            final cfg.role.ExpTable nextet = CfgMgr.exptable.get(newLevel + 1);
            if(nextet != null) {
                newLevel++;
                newExp -= Utils.selfOrMax(curet.exp);
                curet = nextet;
            } else {
                break;
            }
        }

        currencys.put(CurrencyType.JingYan, newExp);
        if(newLevel > oldLevel) {
            xdb.Transaction.tsendWhileCommit(roleid, new SLevelChange(newLevel));
            info.setLevel(newLevel);
//            long lastlvluptime = info.getLastlvluptime();
            long now = System.currentTimeMillis();
            info.setLastlvluptime(now);
            long lastlvluptotaltime = info.getLastlvluptotalonlinetime();
            long interval = now - info.getLastlogintime() + info.getTotalonlinetime() - lastlvluptotaltime;
            info.setLastlvluptotalonlinetime(lastlvluptotaltime + interval);
            FLogger.levelup(roleid, info, newLevel,  interval);
            FBonus.addBonusAlwaysSucc(roleid, CfgMgr.roleconfig.levelupgaintili, newLevel - oldLevel, common.Bonus.BindType.BIND, By.Role_Level_Up);
            onChangeLevel(roleid, oldLevel, newLevel);
            if(newLevel == FamilyInfo.OPEN_LEVEL){
                xbean.System system = FSystem.get();
                system.setRolenumsreach20(system.getRolenumsreach20() + 1);
            }
        }
    }

    public static void addBatchExp(long roleid, xbean.RoleInfo info, long totalAdd, List<Integer> addExps, By by) {
        addExp(roleid, info, totalAdd, by);
        for(int add : addExps) {
            RoleModule.INSTANCE.recordCurrencyAdd(roleid, CurrencyType.JingYan, add);
        }
    }


	private static void onChangeLevel(long roleid, int oldLevel, int newLevel) {
		Listeners.INSTANCE.levelUp(roleid, oldLevel, newLevel);
		EventModule.INSTANCE.broadcastEvent(new LevelUpEvent(roleid, newLevel));
		FMap.dispatchMessageInProcedure(roleid, new SChangeLevel(newLevel));
		RoleModule.INSTANCE.updateRoleAttr(roleid);
		xdb.Trace.info("role.addExp. level up. roleid:{} oldLevel:{} newLevel:{}", roleid, oldLevel, newLevel);
	}

	public static ErrorCode addLevel(long roleid, int num) {
		final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
		final int oldLevel = info.getLevel();
		final int newLevel = Math.min(oldLevel + num, CfgMgr.exptable.size());
		if(oldLevel >= newLevel)
			return ErrorCode.MAX_ROLE_LEVEL;
		info.setLevel(newLevel);
		onChangeLevel(roleid, oldLevel, newLevel);
        xdb.Transaction.tsendWhileCommit(roleid, new SLevelChange(info.getLevel()));
		return ErrorCode.OK;
	}

    public static void addVipLevel(long roleid, xbean.RoleInfo info, xbean.RolePay pay) {
        final int oldVipLevel = info.getViplevel();
        final long totalPay = pay.getTotalpay();
//        final long totalPayyuan = totalPay;
        final long totalPayyuan = totalPay/100;
        final int newLevel = CfgMgr.vipbonus.values().stream().filter(p -> p.needcharge <= totalPayyuan).mapToInt(p -> p.viplevel).max().orElse(0);
        if(newLevel > oldVipLevel){
            info.setViplevel(newLevel);
            FMap.dispatchMessageInProcedure(roleid, new SChangeVipLevel(info.getViplevel()));
        }
        SVipLevelNotify notify = new SVipLevelNotify();
        notify.newlevel = info.getViplevel();
        notify.totalcharge = totalPay;
        xdb.Transaction.tsendWhileCommit(roleid, notify);
    }

	public static void addYuanBao(long roleid, xbean.RoleInfo info, long add, By by){
		addCurrency(roleid, info, CurrencyType.YuanBao, add, by);
	}

	public static void costYuanBao(long roleid, xbean.RoleInfo info, long cost, By by){
		costCurrency(roleid, info, CurrencyType.YuanBao, cost, by);
	}

	public static boolean checkCostYuanBao(long roleid, xbean.RoleInfo info, long cost, By by){
		return checkCostCurrency(roleid, info, CurrencyType.YuanBao, cost, by);
	}

	public static boolean checkCostYuanBao(long roleid, long cost, By by){
		return checkCostCurrency(roleid, xtable.Roleinfos.get(roleid), CurrencyType.YuanBao, cost, by);
	}

	public static void addXuNiBi(long roleid, xbean.RoleInfo info, long add, By by){
		addCurrency(roleid, info, CurrencyType.XuNiBi, add, by);
	}

	public static void costXuNiBi(long roleid, xbean.RoleInfo role, long costXuNiBi, By costBy){
		costCurrency(roleid, role, CurrencyType.XuNiBi, costXuNiBi, costBy);
	}

	public static boolean checkCostXuNiBi(long roleid, xbean.RoleInfo info, long cost, By by) {
		return checkCostCurrency(roleid, info, CurrencyType.XuNiBi, cost, by);
	}

	// 此处不必改为Roleinfos.select xxx, 因为只在登陆时才取.
    // 而且取的顺序已经排序
    public static void genRoleBrief(long roleid, xbean.RoleInfo role, lx.gs.role.msg.RoleBrief info){
    	info.roleid = roleid;
		info.serverid = role.getServerid();
    	info.rolename = role.getName();
    	info.profession = role.getProfession();
    	info.level = role.getLevel();
        info.viplevel = role.getViplevel();
    	info.gender = role.getGender();
        xtable.Dress.getTable().select(roleid, roleDress -> {
            info.dressid = roleDress.getActivedress();
            return roleDress;
        });
        FEquip.getEquipBrief(roleid, info.equips);
    }

    // 此处也不需 Roleinfos.select,因为只有登陆时才取,而且只涉及自己
    public static void genRoleDetail(long roleid, xbean.RoleInfo role, xbean.RoleAttr roleAttr, lx.gs.login.RoleDetail proto){
    	proto.roleid = roleid;
		proto.serverid = role.getServerid();
    	proto.rolename = role.getName();
    	proto.profession = role.getProfession();
    	proto.gender = role.getGender();
    	proto.level = role.getLevel();
    	proto.viplevel = role.getViplevel();
    	proto.vipexp = role.getVipexp();
    	proto.currencys.putAll(role.getCurrencys());
    	proto.combatpower = roleAttr.getTotalcombatpower();
        common.AttrUtils.convert(roleAttr.getFinalattrs(), proto.attrs);

		final xbean.DailyMonsterExp dme = FLimit.getDaily(roleid).getMonsterexp();
		proto.todaytotaladdmonsterexp = dme.getTodaytotaladdmonsterexp();
		
		xbean.RoleFamily rf = FFamily.getRoleFamilyInfo(roleid);
		proto.familyid = rf.getCurrentfid();
        proto.changenametimes = role.getChangenametime();
        proto.creatroletime = role.getCreatetime();
   }

    public static lx.gs.role.msg.RoleShowInfo3 genRoleShowInfo(long roleid, RoleShowInfo3 info){
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            info.roleid = roleid;
            info.serverid = roleinfo.getServerid();
            info.name = roleinfo.getName();
            info.profession = roleinfo.getProfession();
            info.gender = roleinfo.getGender();
            info.level = roleinfo.getLevel();
            info.viplevel = roleinfo.getViplevel();
            info.lastonlinetime = roleinfo.getLastlogouttime();
            xtable.Roleattrs.getTable().select(roleid, roleAttr -> {
                info.combatpower = roleAttr.getTotalcombatpower();
                AttrUtils.convert(roleAttr.getFinalattrs(), info.fightattrs);
                return roleAttr;
            });

            xtable.Title.getTable().select(roleid, roleTitle -> {
                info.title = roleTitle.getActivekey();
                return roleTitle;
            });

            xtable.Dress.getTable().select(roleid, roleDress -> {
                info.dressid = roleDress.getActivedress();
                return roleDress;
            });

            Long familyid = xtable.Rolefamily.selectCurrentfid(roleid);
            if(familyid != null) {
                xtable.Family.getTable().select(familyid, family -> {
                   info.familyname = family.getFamilyname();
                    info.familylevel = family.getFlevel();
                    info.familyjob = family.getMembers().get(roleid).getFamilyjob();
                    return family;
                });
            }


            FEquip.getEquipBrief(roleid, info.equips);
            return true;
        });
		return info;
    }

    public static lx.gs.role.msg.RoleShowInfo4 genRoleShowInfo(long roleid, lx.gs.role.msg.RoleShowInfo4 info) {
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            info.roleid = roleid;
            info.serverid = roleinfo.getServerid();
            info.name = roleinfo.getName();
            info.profession = roleinfo.getProfession();
            info.gender = roleinfo.getGender();
            info.level = roleinfo.getLevel();
            info.viplevel = roleinfo.getViplevel();
            info.combatpower = xtable.Roleattrs.selectTotalcombatpower(roleid);
            info.lastonlinetime = roleinfo.getLastlogouttime();
            info.familyid = Rolefamily.selectCurrentfid(roleid);
            return roleinfo;
        });
        return info;
    }

    public static void fillMemberInfo(long roleid, MemberInfo info) {
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            info.roleid = roleid;
            info.serverid = roleinfo.getServerid();
            info.name = roleinfo.getName();
            info.profession = roleinfo.getProfession();
            info.gender = roleinfo.getGender();
            info.level = roleinfo.getLevel();
            info.viplevel = roleinfo.getViplevel();
            info.combatpower = xtable.Roleattrs.selectTotalcombatpower(roleid);
            return info;
        });
    }

    public static lx.gs.role.msg.RoleShowInfo5 genRoleShowInfo(long roleid, lx.gs.role.msg.RoleShowInfo5 info){
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            info.roleid = roleid;
            info.serverid = roleinfo.getServerid();
            info.name = roleinfo.getName();
            info.profession = roleinfo.getProfession();
            info.gender = roleinfo.getGender();
            info.level = roleinfo.getLevel();
            info.viplevel = roleinfo.getViplevel();
            info.lastonlinetime = roleinfo.getLastlogouttime();
            info.combatpower = xtable.Roleattrs.selectTotalcombatpower(roleid);
            return roleinfo;
        });
        xtable.Roleattrs.getTable().select(roleid, roleAttr -> {
            info.combatpower = roleAttr.getTotalcombatpower();
            AttrUtils.convert(roleAttr.getFinalattrs(), info.fightattrs);
            return roleAttr;
        });

        xtable.Title.getTable().select(roleid, roleTitle -> {
            info.title = roleTitle.getActivekey();
            return roleTitle;
        });

        xtable.Dress.getTable().select(roleid, roleDress -> {
            info.dressid = roleDress.getActivedress();
            return roleDress;
        });

        Long familyid = xtable.Rolefamily.selectCurrentfid(roleid);
        if(familyid != null) {
            xtable.Family.getTable().select(familyid, family -> {
                info.familyname = family.getFamilyname();
                info.familylevel = family.getFlevel();
                info.familyjob = family.getMembers().get(roleid).getFamilyjob();
                return family;
            });
        }

        Long coupleRoleid = xtable.Rolemarriageinfo.selectCoupleroleid(roleid);
        if (coupleRoleid != null && coupleRoleid != 0) {
            info.lovername = xtable.Roleinfos.selectName(coupleRoleid);
        }

        FEquip.genEquipInfo(roleid, info.equips);
        return info;
    }

	public static lx.gs.role.msg.RoleShowInfo1 genRoleShowInfo(long roleid, lx.gs.role.msg.RoleShowInfo1 info) {
        xtable.Roleinfos.getTable().select(roleid, roleinfo -> {
            info.roleid = roleid;
            info.serverid = roleinfo.getServerid();
            info.name = roleinfo.getName();
            info.profession = roleinfo.getProfession();
            info.gender = roleinfo.getGender();
            info.level = roleinfo.getLevel();
            info.viplevel = roleinfo.getViplevel();
            info.combatpower = xtable.Roleattrs.selectTotalcombatpower(roleid);
            return roleinfo;
        });


        xtable.Dress.getTable().select(roleid, roleDress -> {
            info.dressid = roleDress.getActivedress();
            return roleDress;
        });

		FEquip.getEquipBrief(roleid, info.equips);
        return info;
	}
    
    public static xbean.RoleConfigure getConfigure(long roleid) {
    	xbean.RoleConfigure info = xtable.Roleconfigure.get(roleid);
    	if(info == null) {
    		info = xbean.Pod.newRoleConfigure();
    		xtable.Roleconfigure.insert(roleid, info);
    	}
    	return info;
    }

	static void refreshTili(long roleid) {
		final xbean.RoleInfo info = xtable.Roleinfos.get(roleid);
		final long now = System.currentTimeMillis();
		final long add = (now - info.getLastaddtilitime()) / CfgMgr.roleconfig.addonetiliinterval / 1000;
		addTiLiToLimit(roleid, info, add, By.TiLi_Interval_Add);
		info.setLastaddtilitime(now);
	}

	public static RoleMemInfo getRoleMemInfo(long roleid) {
		RoleMemInfo info = Rolememinfos.get(roleid);
		if (info == null) {
			info = Pod.newRoleMemInfo();
			xtable.Rolememinfos.insert(roleid, info);
		}
		return info;
	}
}
