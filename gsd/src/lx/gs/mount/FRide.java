package lx.gs.mount;

import cfg.CfgMgr;
import cfg.equip.Riding;
import common.ErrorCode;
import lx.gs.map.FMap;
import map.msg.XChangeRide;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FRide {
	
	public static void onActiveRideChange(long roleid){
		FMap.dispatchMessageInProcedure(roleid, new XChangeRide(xtable.Ride.select(roleid).getActiveride()));
	}

	public static xbean.RoleRide getRoleRide(long roleid){
		return xtable.Ride.get(roleid);
	}
	
	public static boolean isRideModuleUnlock(long roleid){
		return Roleinfos.selectLevel(roleid) >= Riding.OPEN_LEVEL;
	}

	/**
	 * effectiveTime小于等于0表示永久有效
	 *
	 * @param roleid
	 * @param rideKey
	 * @param effectiveTime
     * @return
     */
	public static ErrorCode unlockRide(long roleid, int rideKey, int effectiveTime){
		long now = System.currentTimeMillis();
		long expireTime = effectiveTime <= 0 ? 0 : now + TimeUnit.SECONDS.toMillis(effectiveTime);
		Map<Integer, xbean.Ride> rideMap = getRoleRide(roleid).getRides();
		xbean.Ride ride = rideMap.get(rideKey);
		if(ride != null){
			if(ride.getExpiretime() == 0){
				return ErrorCode.RIDE_ALREADY_GET;
			}
		} else {
			assert(CfgMgr.riding.containsKey(rideKey));
			ride = xbean.Pod.newRide();
			ride.setModelid(rideKey);
			rideMap.put(rideKey, ride);
		}
		ride.setExpiretime(expireTime);

		xdb.Transaction.tsendWhileCommit(roleid, new SRideGetNotify(FRide.convert(ride)));
		return ErrorCode.OK;
	}

	public static lx.gs.mount.Ride convert(xbean.Ride d){
		return new lx.gs.mount.Ride(d.getModelid(), d.getExpiretime());
	}

	public static void checkExpire(long roleid) {
		long now = System.currentTimeMillis();
		xbean.RoleRide roleRide = FRide.getRoleRide(roleid);
		int activeRide = roleRide.getActiveride();
		Iterator<xbean.Ride> iterator = roleRide.getRides().values().iterator();
		iterator.forEachRemaining(ride -> {
			long exp = ride.getExpiretime();
			if(exp > 0 && exp < now){
				int modelId = ride.getModelid();
				iterator.remove();
				Transaction.tsendWhileCommit(roleid, new SRideExpired(modelId));
				if(activeRide == modelId) roleRide.setActiveride(0);
			}
		});
	}
}
