package lx.gs.jade;

import cfg.fight.AttrId;
import lx.gs.role.FRoleAttr;
import xdb.Xdb;

import java.util.HashMap;
import java.util.Map;

public class FJade {
	/**
	 * 获取玉佩信息
	 * @param roleid
	 * @return
	 */
	public static xbean.RoleJadeInfo getRoleJadeInfo(long roleid){
		xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
		if(rjade==null){
			rjade = xbean.Pod.newRoleJadeInfo();
			xtable.Jade.add(roleid, rjade);
			//初始化玉佩
			rjade.setLevel(1);
			rjade.setJewelrygetlevel(1);
			rjade.setBonus(0);
		}
		return rjade;
	}
	
	public static int addJadeBonus(long roleid, int add){
		xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
		int old = rjade.getBonus();
		int real = old + add;
		int max = cfg.CfgMgr.jade.get(rjade.getLevel()).maxbonus;
		if(real>max){
			real = max;
		}
		rjade.setBonus(real);
		return real;
	}
	
	public static xbean.Jewelry generateJewelry(long roleid, int jewelryid){
		xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
		xbean.Jewelry jewelry = xbean.Pod.newJewelry();
		jewelry.setExp(0);
		jewelry.setLevel(1);
		jewelry.setId(jewelryid);
		rjade.getJewelrybag().add(jewelry);
		return jewelry;
	}
	
	public static int getRandomTrans(float up, float down){
		float rand = Xdb.random().nextFloat();
		if(rand<up){
			return 1;
		}else{
			rand -= up;
		}
			
		if(rand<down){
			return -1;
		}else{
			return 0;
		}
	}
	
	public static int getRandomQuality(java.util.List<Float> probability){
		float rand = Xdb.random().nextFloat();
		for(int i=0; i<probability.size(); i++){
			if(rand<probability.get(i)){
				return i;
			}else{
				rand-=probability.get(i);
			}
		}
		return probability.size()-1;
	}
	
	public static int setJadeLevel(long roleid, int level){
		xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
		rjade.setLevel(level);
		return level;
	}
	
	public static int calRandomBonus(cfg.equip.JadeEnhanceData enhancedata){
		int bonus = common.Utils.randomRange(enhancedata.minbonus, enhancedata.maxbonus);
		double rand = common.Utils.random01();
		if(rand<enhancedata.criticalrate){
			return bonus*enhancedata.criticalvalue;
		}else{
			return bonus;
		}
	}
	
	public static void onRoleLevelUp(long roleid, int oldlevel, int newlevel){
		xbean.RoleJadeInfo info = FJade.getRoleJadeInfo(roleid);
		cfg.equip.JadeEnhance conf = cfg.CfgMgr.jadeenhance;
		if(newlevel<conf.openlevel)
			return;
		for(int i = 0; i < conf.holeopenlevel.size(); i++){
			if(newlevel<conf.holeopenlevel.get(i)){
				if(oldlevel<conf.holeopenlevel.get(i-1)){
					info.setHolenum(i);
					SJadeUnLockNotify notify = new SJadeUnLockNotify();
					notify.holenum = info.getHolenum();
					notify.jade.level = info.getLevel();
					notify.jade.bonus = info.getBonus();
					xdb.Transaction.tsendWhileCommit(roleid, notify);
				}
				break;
			}
		}

	}

	public static lx.gs.jade.Jewelry makeProtocolJewelry(xbean.Jewelry p){
		lx.gs.jade.Jewelry result = new lx.gs.jade.Jewelry();
		if(p == null) return result;
		result.id = p.getId();
		result.level = p.getLevel();
		result.exp = p.getExp();
		return result;
	}

	public static lx.gs.jade.RoleJadeInfo makeProtocolRoleJadeInfo(xbean.RoleJadeInfo p){
		lx.gs.jade.RoleJadeInfo result = new lx.gs.jade.RoleJadeInfo();
		result.holenum = p.getHolenum();
		result.jewelrygetlevel = p.getJewelrygetlevel();
		result.jade.level = p.getLevel();
		result.jade.bonus = p.getBonus();
		for(xbean.Jewelry j: p.getJewelrybag()){
			result.jewelrybag.add(makeProtocolJewelry(j));
		}
		for(Map.Entry<Integer, xbean.Jewelry> e: p.getJewelry().entrySet()){
			result.jewelry.put(e.getKey(), makeProtocolJewelry(e.getValue()));
		}
		return result;
	}

	public static void sendJadeInfo(long roleid){
		cfg.equip.JadeEnhance conf = cfg.CfgMgr.jadeenhance;
		xbean.RoleInfo  roleinfo = xtable.Roleinfos.get(roleid);
		if(roleinfo.getLevel()>=conf.openlevel){
			SGetJadeInfo result = new SGetJadeInfo();
			xbean.RoleJadeInfo jadeinfo = FJade.getRoleJadeInfo(roleid);
			result.jadeinfo = FJade.makeProtocolRoleJadeInfo(jadeinfo);
			xdb.Transaction.tsendWhileCommit(roleid,result);
		}
	}
	
	
	
	public static void updateRoleAttr(long roleid){
		xbean.RoleJadeInfo rjade = getRoleJadeInfo(roleid);
		updateJadeRoleAttr(roleid, rjade);
		updateJewelryRoleAttr(roleid, rjade);
	}

	public static void updateJadeRoleAttr(long roleid, xbean.RoleJadeInfo rjade) {
		cfg.equip.Jade conf = cfg.CfgMgr.jade.get(rjade.getLevel());
		final Map<Integer,Float> attrs = new HashMap<Integer,Float>();
		common.Utils.addValue(attrs, AttrId.HP_FULL_VALUE, rjade.getBonus());
		common.Utils.addValue(attrs, AttrId.HP_FULL_VALUE_PERCENT, conf.percent);
		FRoleAttr.updateGroup(roleid, "jade", attrs);
	}

	public static void updateJewelryRoleAttr(long roleid, xbean.RoleJadeInfo rjade) {
		final Map<Integer,Float> attrs = new HashMap<Integer,Float>();
		for(xbean.Jewelry e : rjade.getJewelry().values()){
			cfg.equip.Jewelry jewelryconf = cfg.CfgMgr.jewelry.get(e.getId());
			cfg.equip.JewelryLvlUp jewelrylvlupconf = cfg.CfgMgr.jewelrylvlup.get(e.getLevel());
			//宝珠属性值计算
			float realvalue = jewelryconf.maturerate*jewelrylvlupconf.basicvalue + jewelryconf.basicvalue;
			common.Utils.addValue(attrs, jewelryconf.propertytype, realvalue);
		}
		FRoleAttr.updateGroup(roleid, "jewelry", attrs);
	}
	
	public static void onJadeChange(long roleid) {
		xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
		updateJadeRoleAttr(roleid, rjade);
	}

	public static void onJewelryChange(long roleid) {
		xbean.RoleJadeInfo rjade = xtable.Jade.get(roleid);
		updateJewelryRoleAttr(roleid, rjade);
	}
}
