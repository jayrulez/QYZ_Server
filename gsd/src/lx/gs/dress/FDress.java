package lx.gs.dress;

import common.ErrorCode;
import lx.gs.map.FMap;
import lx.gs.role.FRoleAttr;
import map.msg.SChangeDress;
import xdb.Transaction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FDress {

	public static Map<Integer, Float> calcAttrs(int dressKey) {
		if(dressKey <= 0)
			return Collections.emptyMap();
		final Map<Integer, Float> result = new HashMap<>();
		common.AttrUtils.addAttrs(result, cfg.CfgMgr.dress.get(dressKey).propertylist);
		return result;
	}
	
	public static void updateRoleAttr(long roleid){
		FRoleAttr.updateGroup(roleid, "dress", calcAttrs(getActived(roleid)));
	}

	public static int getActived(long roleId){
		return xtable.Dress.selectActivedress(roleId);
	}
	
	/**
	 * 时装激活或者卸载后通知地图
	 * @param roleid
	 */
	public static void onActiveDressChange(long roleid){
		updateRoleAttr(roleid);
		FMap.dispatchMessageInProcedure(roleid, new SChangeDress(getActived(roleid)));
	}
	/**
	 * 获取时装信息
	 * @param roleid
	 * @return
	 */
	public static xbean.RoleDress get(long roleid){
		return xtable.Dress.get(roleid);
	}
	
	/**
	 * 合成时装或者使用时装物品蛋的时候调用该方法把时装变为解锁状态
	 * 如果再次使用该时装的宠物蛋。则提示“时装已获得，不可重复获得。”
	 * @param roleid
	 * @param dresskey
	 * @return
	 */
	public static ErrorCode unlockDress(long roleid, int dresskey, int effectiveTime){
		long now = System.currentTimeMillis();
		long expireTime = effectiveTime <= 0 ? 0 : now + TimeUnit.SECONDS.toMillis(effectiveTime);
		Map<Integer, xbean.Dress> dresses = get(roleid).getDresses();
		xbean.Dress dress = dresses.get(dresskey);
		if(dress != null){
			if(dress.getExpiretime() == 0){
				return ErrorCode.DRESS_ALREADY_HAVE;
			}
		} else {
			assert(cfg.CfgMgr.dress.containsKey(dresskey));
			dress = xbean.Pod.newDress();
			dress.setModelid(dresskey);
			dresses.put(dresskey, dress);
		}
		dress.setExpiretime(expireTime);

		xdb.Transaction.tsendWhileCommit(roleid, new SDressGetNotify(convert(dress)));
		return ErrorCode.OK;
	}

	public static lx.gs.dress.Dress convert(xbean.Dress d){
		return new lx.gs.dress.Dress(d.getModelid(), d.getExpiretime());
	}

	public static void onRoleLogin(long roleid) {
		SGetDressInfo result = new SGetDressInfo();
		get(roleid).getDresses().values().forEach(dress -> result.dresslist.add(convert(dress)));
		result.activedress = getActived(roleid);
		Transaction.tsendWhileCommit(roleid, result);
	}

	public static void checkExpire(long roleid) {
		long now = System.currentTimeMillis();
		xbean.RoleDress roleDress = FDress.get(roleid);
		int active = roleDress.getActivedress();
		Iterator<xbean.Dress> iterator = roleDress.getDresses().values().iterator();
		iterator.forEachRemaining(dress -> {
			long exp = dress.getExpiretime();
			if(exp > 0 && exp < now){
				int modelId = dress.getModelid();
				iterator.remove();
				Transaction.tsendWhileCommit(roleid, new SDressExpired(modelId));
				if(active == modelId) roleDress.setActivedress(0);
			}
		});
	}
}
