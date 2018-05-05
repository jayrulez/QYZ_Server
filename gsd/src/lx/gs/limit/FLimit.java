package lx.gs.limit;

import cfg.Const;
import cfg.cmd.condition.CoolDown;
import cfg.cmd.condition.GroupCoolDown;
import cfg.cmd.condition.LimitType;
import common.ErrorCode;
import lx.gs.cmd.FCondition;
import lx.gs.limit.msg.SCoolDownChange;
import lx.gs.limit.msg.SLimit;
import lx.gs.logger.By;
import lx.gs.role.FRole;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class FLimit {
	
	public static xbean.RoleLimit get(long roleid) {
		xbean.RoleLimit info = xtable.Rolelimit.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleLimit();
			xtable.Rolelimit.insert(roleid, info);
		}
		return info;
	}

	public static xbean.DailyResetData getDaily(long roleid) {
		xbean.DailyResetData info = xtable.Roledailyresetdata.get(roleid);
		if(info == null) {
			info = xbean.Pod.newDailyResetData();
			info.setLastupdatedailytime(System.currentTimeMillis());
			xtable.Roledailyresetdata.insert(roleid, info);
		}
		return info;
	}
	
	private static long makeId(int configId, int cmdId) {
		return ((long)configId << 32) + cmdId;
	}

	public static boolean checkCoolDown(long roleid, int configId, int cmdId, CoolDown cooldown) {
		return checkCoolDownById(roleid, makeId(configId, cmdId), cooldown.time);
	}

	public static boolean checkCoolDown(long roleid, int configId, int cmdId, GroupCoolDown cooldown) {
		return checkCoolDownById(roleid, makeId(configId, cooldown.groupid != Const.NULL ? cooldown.groupid : cmdId), cooldown.time);
	}

	private static boolean checkCoolDownById(long roleid, long id, float cooldownTime) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.CoolDown> cooldowns = info.getCooldowns();
		final long now = System.currentTimeMillis();
		final long expireTime = now + (long)(cooldownTime * 1000);
		xbean.CoolDown cd = cooldowns.get(id);
		if(cd == null) {
			cd = xbean.Pod.newCoolDown();
			cd.setId(id);
			cd.setExpiretime(expireTime);
			cooldowns.put(id, cd);
		} else {
			if(cd.getExpiretime() > now)
				return false;
			cd.setExpiretime(expireTime);
		}
		xdb.Transaction.tsendWhileCommit(roleid, new SCoolDownChange(id, expireTime));
		return true;
	}

	public static boolean checkLimitAndAddNum(long roleid, int configId, int cmdId, int num, int limittype, int limitnum) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.Limit> alllimits = info.getLimits();
		final long id = makeId(configId, cmdId);
		xbean.Limit limit = alllimits.get(id);
		final Map<Integer, Integer> typelimits;
		if(limit == null) {
			limit = xbean.Pod.newLimit();
			if(num > limitnum)
				return false;
			limit.setId(id);
			typelimits = limit.getTypenums();
			typelimits.put(limittype, num);
			alllimits.put(id, limit);
		} else {
			typelimits = limit.getTypenums();
			final int newnum = typelimits.getOrDefault(limittype, 0) + num;
			if (newnum > limitnum) {
				return false;
			} else {
				typelimits.put(limittype, newnum);
			}
		}
		limit.setLastbuytime((int)(System.currentTimeMillis() / 1000));
		return true;
	}

	public static boolean checkLimitAndAddNum(long roleid, int configId, int cmdId, int num, List<cfg.cmd.condition.Limit> limits) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.Limit> alllimits = info.getLimits();
		final long id = makeId(configId, cmdId);
		xbean.Limit limit = alllimits.get(id);
		final Map<Integer, Integer> typelimits;
		if(limit == null) {
			limit = xbean.Pod.newLimit();
			if(limits.stream().anyMatch(l -> l.num < num))
				return false;
			limit.setId(id);
			typelimits = limit.getTypenums();
			for(cfg.cmd.condition.Limit li : limits) {
				typelimits.put(li.type, num);
			}
			alllimits.put(id, limit);
		} else {
			typelimits = limit.getTypenums();
			for(cfg.cmd.condition.Limit li : limits) {
				final int newnum = typelimits.getOrDefault(li.type, 0) + num;
				if(newnum > li.num) {
					return false;
				}
				else {
					typelimits.put(li.type, newnum);
				}
			}
		}
		limit.setLastbuytime((int)(System.currentTimeMillis() / 1000));
		return true;
	}

	public static ErrorCode checkVipLimitAndAddNum(long roleid, int configId, int cmdId, int costnum, cfg.cmd.condition.VipLimits c, By costby) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.Limit> alllimits = info.getLimits();
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		final int vipLevel = role.getViplevel();
		final int idx = Math.min(vipLevel, c.entertimes.size() - 1);
		final int maxnum = c.entertimes.get(idx);
		final long id = makeId(configId, cmdId);
		xbean.Limit limit = alllimits.get(id);
		if(limit == null) {
			limit = xbean.Pod.newLimit();
			limit.setId(id);
			alllimits.put(id, limit);
		}
		final Map<Integer, Integer> typelimits = limit.getTypenums();
		int curnum = typelimits.getOrDefault(LimitType.DAY, 0);
		final int newnum = curnum + costnum;
		if(maxnum != Const.NULL && newnum > maxnum)
			return ErrorCode.EXCEED_LIMIT;
		for(int i = curnum ; i < newnum ; i++) {
			final int cost = c.amout.get(Math.min(i, c.amout.size()-1));
			if(cost > 0) {
				ErrorCode err = FRole.costCurrency(roleid, c.currencytype, cost, costby);
				if(err.err()){
					return err;
				}
			}
		}
		typelimits.put(LimitType.DAY, newnum);
		limit.setLastbuytime((int)(System.currentTimeMillis() / 1000));
		return ErrorCode.OK;
	}

	public static ErrorCode checkVipLimitAndAddNum(long roleid, int configId, int cmdId, int costnum, cfg.cmd.condition.VipLimits2 c, By costby) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.Limit> alllimits = info.getLimits();
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		final int vipLevel = role.getViplevel();
		final int idx = Math.min(vipLevel, c.entertimes.size() - 1);
		final int maxnum = c.entertimes.get(idx);
		final long id = makeId(configId, cmdId);
		xbean.Limit limit = alllimits.get(id);
		if(limit == null) {
			limit = xbean.Pod.newLimit();
			limit.setId(id);
			alllimits.put(id, limit);
		}
		final Map<Integer, Integer> typelimits = limit.getTypenums();
		int curnum = typelimits.getOrDefault(LimitType.DAY, 0);
		final int newnum = curnum + costnum;
		if(maxnum != Const.NULL && newnum > maxnum)
			return ErrorCode.EXCEED_LIMIT;
		for(int i = curnum ; i < newnum ; i++) {
			final ErrorCode err = FCondition.checkA(roleid, c.costs.get(Math.min(i, c.costs.size()-1)), 1, costby, configId, cmdId);
			if(err.err())
				return err;
		}
		typelimits.put(LimitType.DAY, newnum);
		limit.setLastbuytime((int)(System.currentTimeMillis() / 1000));
		return ErrorCode.OK;
	}

	public static boolean checkVipLimitAndAddNum(long roleid, int configId, int cmdId, int costnum, cfg.cmd.condition.VipLimitsLite c, By costby) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.Limit> alllimits = info.getLimits();
		final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
		final int vipLevel = role.getViplevel();
		final int idx = Math.min(vipLevel, c.entertimes.size() - 1);
		final int maxnum = c.entertimes.get(idx);
		final long id = makeId(configId, cmdId);
		xbean.Limit limit = alllimits.get(id);
		if(limit == null) {
			limit = xbean.Pod.newLimit();
			limit.setId(id);
			alllimits.put(id, limit);
		}
		final Map<Integer, Integer> typelimits = limit.getTypenums();
		final int newnum = typelimits.getOrDefault(LimitType.DAY, 0) + costnum;
		if(maxnum != Const.NULL && newnum > maxnum)
			return false;
		typelimits.put(LimitType.DAY, newnum);
		limit.setLastbuytime((int)(System.currentTimeMillis() / 1000));
		return true;
	}

	public static boolean clearLimit(long roleid, int configId, int cmdId) {
		return get(roleid).getLimits().remove(makeId(configId, cmdId)) != null;
	}

	static void clearExpireLimits(long roleid) {
		final xbean.RoleLimit info = get(roleid);
		final Map<Long, xbean.Limit> limits = info.getLimits();
		final common.Time.TimeInfo expireInfo = common.Time.getTimeInfo();
		for(Iterator<xbean.Limit> it = limits.values().iterator() ; it.hasNext() ;) {
			final xbean.Limit limit = it.next();
			// 减2秒为了避免0点附近买东西的玩家被判定为过了0点才买,而未被重置次数
			final long lastBuyTime = limit.getLastbuytime() * 1000L - 2000;
			// 肯定未过期
			if(lastBuyTime >= expireInfo.dayZeroTime)
				continue;
			final Map<Integer, Integer> typenums = limit.getTypenums();
			for(Iterator<Integer> it2 = typenums.keySet().iterator() ; it2.hasNext() ;) {
				final int type = it2.next();
				switch(type) {
					case cfg.cmd.condition.LimitType.DAY : {
						it2.remove();
						break;
					}
					case cfg.cmd.condition.LimitType.WEEK: {
						if(lastBuyTime < expireInfo.weekZeroTime)
							it2.remove();
						break;
					}
					case cfg.cmd.condition.LimitType.MONTH: {
						if(lastBuyTime < expireInfo.monthZeroTime)
							it2.remove();
						break;
					}
				}
			}
			if(typenums.isEmpty())
				it.remove();
		}
	}

	static void clearExpireCoolDown(long roleid) {
		final xbean.RoleLimit info = get(roleid);
		final long now = System.currentTimeMillis();
		for(Iterator<xbean.CoolDown> it = info.getCooldowns().values().iterator() ; it.hasNext() ;) {
			final xbean.CoolDown cd = it.next();
			if(cd.getExpiretime() <= now) {
				xdb.Transaction.tsendWhileCommit(roleid, new SCoolDownChange(cd.getId(), 0));
				it.remove();
			}
		}
	}

	public static void resetLimitAndCoolDown(long roleid) {
		final xbean.RoleLimit info = get(roleid);
		info.getLimits().clear();
		info.getCooldowns().values().forEach(c -> xdb.Transaction.tsendWhileCommit(roleid, new SCoolDownChange(c.getId(), 0)));
		info.getCooldowns().clear();
	}

	static void clearExpireDailyData(long roleid) {
		xbean.DailyResetData info = xtable.Roledailyresetdata.get(roleid);
		if(info == null) return;
		final common.Time.TimeInfo expireInfo = common.Time.getTimeInfo();
		if(info.getLastupdatedailytime() < expireInfo.dayZeroTime) {
			info = xbean.Pod.newDailyResetData();
			info.setLastupdatedailytime(expireInfo.dayZeroTime);
			xtable.Roledailyresetdata.getTable().put(roleid, info);
		}
	}
	
	public static lx.gs.limit.msg.Limit convert(xbean.Limit limit) {
		final lx.gs.limit.msg.Limit l = new lx.gs.limit.msg.Limit();
		l.id = limit.getId();
		l.typenums.putAll(limit.getTypenums());
		return l;
	}

	public static lx.gs.limit.msg.CoolDown convert(xbean.CoolDown cooldown) {
		final lx.gs.limit.msg.CoolDown cd = new lx.gs.limit.msg.CoolDown();
		cd.id = cooldown.getId();
		cd.expiretime = cooldown.getExpiretime();
		return cd;
	}
	
	static SLimit createSLimit(long roleid) {
		final SLimit re = new SLimit();
		final xbean.RoleLimit info = get(roleid);
		re.limits.addAll(info.getLimits().values().stream().map(FLimit::convert).collect(Collectors.toList()));
		re.cooldowns.addAll(info.getCooldowns().values().stream().map(FLimit::convert).collect(Collectors.toList()));
		return re;
	}

    public static int getLimitTimes(long roleid, int configId, int cmdId, int limittype){
        xbean.RoleLimit info = get(roleid);
        long id = makeId(configId,cmdId);
        xbean.Limit limit = info.getLimits().get(id);
        if(limit == null){
            return 0;
        }
        return limit.getTypenums().getOrDefault(limittype,0);
    }

    public static void setLimitTimes(long roleid, int configId, int cmdId, int limittype, int times){
        xbean.RoleLimit info = get(roleid);
        long id = makeId(configId,cmdId);
        xbean.Limit limit = info.getLimits().get(id);
        limit.getTypenums().put(limittype, times);
    }

}
