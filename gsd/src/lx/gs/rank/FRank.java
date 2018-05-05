package lx.gs.rank;

import cfg.CfgMgr;
import cfg.bonus.RankType;
import cfg.bonus.UpdateType;
import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import lx.gs.rank.msg.*;
import xbean.RankRecord;
import xdb.Transaction;

import java.util.ArrayList;
import java.util.Map;

public final class FRank {
	public final static class XOrderRank extends SimpleOrderRank<xbean.RankRecord> {
		public XOrderRank(Map<Long, Integer> id2rank, Map<Integer, RankRecord> records, int capacity) {
			super(id2rank, records, capacity);
		}

		@Override
		protected long getId(RankRecord r) {
			return r.getId();
		}

		@Override
		protected long getValue(RankRecord r) {
			return r.getValue();
		}

		@Override
		protected void setValue(RankRecord r, long value) {
			r.setValue(value);
		}

		@Override
		protected RankRecord create(long id, long value) {
			final xbean.RankRecord r = xbean.Pod.newRankRecord();
			r.setId(id);
			r.setValue(value);
			return r;
		}
	}

	public final static class XSwapRank extends SwapRank<xbean.RankRecord> {
		public XSwapRank(Map<Long, Integer> id2rank, Map<Integer, xbean.RankRecord> records, int capacity) {
			super(id2rank, records, capacity);
		}

		@Override
		protected long getId(RankRecord r) {
			return r.getId();
		}

		@Override
		protected RankRecord create(long id) {
			final xbean.RankRecord r = xbean.Pod.newRankRecord();
			r.setId(id);
			return r;
		}
	}
	
	public static xbean.RoleRank get(long roleid) {
		xbean.RoleRank info = xtable.Rolerank.get(roleid);
		if(info == null) {
			info = xbean.Pod.newRoleRank();
			xtable.Rolerank.insert(roleid, info);
		}
		return info;
	}


	public static SInfo createSInfo(long roleid) {
		final SInfo re = new SInfo();
		for(cfg.bonus.Rank rcfg : CfgMgr.rank.values()) {
			if(rcfg.isrolerank) {
				re.ranks.add(new RankInfo(rcfg.ranktype, getSnapRankById(rcfg.ranktype, roleid)));
			}
		}
		return re;
	}

	private static int calcCurRankId(int ranktype, int serverid) {
		return (ranktype << 14) + serverid;
	}

	private static int calcSnapRankId(int ranktype, int serverid) {
		return (ranktype << 14) + serverid + (1 << 13);
	}

	private static int calcCurRankId(int ranktype) {
		return calcCurRankId(ranktype, gs.Utils.getServerId());
	}

	private static int calcSnapRankId(int ranktype) {
		return calcSnapRankId(ranktype, gs.Utils.getServerId());
	}

	public static boolean isExpire(long createTime, long now) {
		return !common.Time.isSameDay(createTime, now);
	}

	public static void refreshRanks(boolean forceRefresh) {
		new xdb.Procedure() {
			@Override
			protected boolean process() {
				final long now = System.currentTimeMillis();
				for (cfg.bonus.Rank rcfg : CfgMgr.rank.values()) {
					final int rankType = rcfg.ranktype;
					if(rankType != RankType.ARENA){
						continue;
					}
					final int rankId = calcCurRankId(rankType);
					final int snapRankId = calcSnapRankId(rankType);
					xbean.ARank rank = xtable.Ranks.get(rankId);
					if (rank == null) {
						rank = xbean.Pod.newARank();
						rank.setCreatetime(now);
						xtable.Ranks.insert(rankId, rank);
						final xbean.ARank snapRank = rank.copy();
						xtable.Ranks.getTable().put(snapRankId, snapRank);
					} else if (forceRefresh || isExpire(rank.getCreatetime(), now)) {
						rank.setCreatetime(now);
						final xbean.ARank snapRank = rank.copy();
						xtable.Ranks.getTable().put(snapRankId, snapRank);
						xdb.Trace.info("refreshRank. ranktype:{} size:{}", rankType, snapRank.getId2rank().size());
					}
				}
				return true;
			}
		}.call();
	}

	// 每加一个榜都要同时处理buildRank和genRankShowData
//	private static xbean.ARank buildRank(cfg.bonus.Rank rcfg) {
//		final int rankType = rcfg.ranktype;
//		final xbean.ARank rank = xbean.Pod.newARankData();
//		final XOrderRank orderRank = new XOrderRank(rank.getId2rank(), rank.getRecords(), rcfg.ranksize);
//		final XSwapRank swapRank = new XSwapRank(rank.getId2rank(), rank.getRecords(), rcfg.ranksize);
//		switch (rankType) {
//			case RankType.COMBAT_POWER:
//			case RankType.LEVEL:
//			case RankType.FAMILY:
//			case RankType.ARENA:
//			case RankType.CLIMB_TOWER:
//			case RankType.FLOWER:
//			case RankType.PET:
//			case RankType.FABAO: {
//				break;
//			}
//			default: {
//				throw new RuntimeException("unknown ranktype:" + rankType);
//			}
//		}
//
//		xdb.Trace.info("buildRank. ranktype:{} size:{}", rankType, rank.getId2rank().size());
//		return rank.toBean();
//	}

	// 非事务环境
	private static Marshal genRankShowData(int rankType, long id, int rank, long value) {
		switch (rankType) {
//			case RankType.COMBAT_POWER:
//			case RankType.LEVEL:
//			case RankType.CLIMB_TOWER: {
//				final GeneralRankInfo ri = new GeneralRankInfo();
//				xtable.Roleinfos.getTable().select(id,  info -> {
//					ri.roleid = id;
//					ri.name = info.getName();
//					ri.level = info.getLevel();
//					ri.value = value;
//					return info;
//				});
//				return ri;
//			}
//			case RankType.FAMILY: {
//				final FamilyRankInfo ri = new FamilyRankInfo();
//				ri.id = id;
//				xtable.Family.getTable().select(id, family -> {
//					ri.name = family.getFamilyname();
//					ri.level = family.getFlevel();
//					ri.value = family.getTotalbuilddegree();
//					ri.chiefid = family.getChiefid();
//					return family;
//				});
//				return ri;
//
//			}
			case RankType.ARENA: {
				final GeneralRankInfo ri = new GeneralRankInfo();
				xtable.Roleinfos.getTable().select(id, info -> {
					ri.roleid = id;
					ri.name = info.getName();
					ri.level = info.getLevel();
					ri.value = xtable.Roleattrs.selectTotalcombatpower(id);
					return info;
				});
				return ri;
			}
//            case RankType.XUNIBI:
//			case RankType.FLOWER:
//			case RankType.PET:
//			case RankType.FABAO: {
//				final GeneralRankInfo ri = new GeneralRankInfo();
//				xtable.Roleinfos.getTable().select(id,  info -> {
//					ri.roleid = id;
//					ri.name = info.getName();
//					ri.level = info.getLevel();
//					ri.value = value;
//					return info;
//				});
//				return ri;
//			}
			default: {
				throw new RuntimeException("unknown ranktype:" + rankType);
			}
		}
	}

	private final static int MAX_GET_SIZE = 50;
	public static SGetRank createSGetRank(long roleid, int rankType, int rankStart, int rankEnd) {
		final SGetRank re = new SGetRank();
		re.ranktype = rankType;
		final int finalRankEnd = Math.min(rankEnd, rankStart + MAX_GET_SIZE);

		final ArrayList<xbean.RankRecord> rankRecords = new ArrayList<>();
		new xdb.Procedure() {
			@Override
			protected boolean process() {
				final cfg.bonus.Rank rcfg = CfgMgr.rank.get(rankType);
				final xbean.ARank curRank = getARank(rankType, false);//!rcfg.realtime);
				final xbean.ARank snapRank = getARank(rankType, true);
				final Map<Integer, xbean.RankRecord> allRecords = curRank.getRecords();
				re.ranksize = allRecords.size();
				rankRecords.clear();
				for(int i = rankStart ; i <= finalRankEnd ; i++) {
					final xbean.RankRecord r = allRecords.get(i);
					if(r == null)
						break;
					rankRecords.add(r.toData());
				}
				if(rcfg.isrolerank) {
					re.mycurrank = curRank.getId2rank().getOrDefault(roleid, 0);
				}
				return true;
			}
		}.call();

		final OctetsStream os = new OctetsStream();
		os.compact_uint32(rankRecords.size());

		int curRank = rankStart;
		for(xbean.RankRecord record : rankRecords) {
			os.marshal(genRankShowData(rankType, record.getId(), curRank++, record.getValue()));
		}
		re.data = os;
		return re;
	}


	public static xbean.ARank getARank(int rankType, boolean snap) {
		return xtable.Ranks.get(snap ? calcSnapRankId(rankType) : calcCurRankId(rankType));
	}

	public static XOrderRank getOrderRank(int rankType, boolean snap) {
		assert(CfgMgr.rank.get(rankType).updatetype == UpdateType.ORDER);
		final xbean.ARank rank = xtable.Ranks.get(snap ? calcSnapRankId(rankType) : calcCurRankId(rankType));
		return new XOrderRank(rank.getId2rank(), rank.getRecords(), CfgMgr.rank.get(rankType).ranksize);
	}

	public static XSwapRank getSwapRank(int rankType, boolean snap) {
		assert(CfgMgr.rank.get(rankType).updatetype == UpdateType.SWAP);
		final xbean.ARank rank = xtable.Ranks.get(snap ? calcSnapRankId(rankType) : calcCurRankId(rankType));
		return new XSwapRank(rank.getId2rank(), rank.getRecords(), CfgMgr.rank.get(rankType).ranksize);
	}


	public static int syncUpdateOrderRank(int rankType, long id, long value) {
		final XOrderRank rank = getOrderRank(rankType, false);
		return rank.greaterUpdate(id, value);
	}


    public static void asyncUpdateOrderRank(int rankType, long id, long value) {
        Transaction.texecuteWhileCommit(new xdb.Procedure() {
            @Override
            protected boolean process() {
                final XOrderRank rank = getOrderRank(rankType, false);
                rank.greaterUpdate(id, value);
                return true;
            }
        });
    }

	public static void swapSwapRank(int rankType, long id1, long id2) {
		final XSwapRank rank = getSwapRank(rankType, false);
		rank.swap(id1, id2);
	}

	public static boolean addSwapRank(int rankType, long id) {
		final XSwapRank rank = getSwapRank(rankType, false);
		return rank.add(rank.create(id));
	}

	public static void removeOrderRankById(int rankType, long id) {
		final XOrderRank rank = getOrderRank(rankType, false);
		rank.removeById(id);
	}

	public static int getRankById(int rankType, long id) {
		final cfg.bonus.Rank rcfg = CfgMgr.rank.get(rankType);
		final xbean.ARank arank = getARank(rankType, !rcfg.realtime);
		return (arank == null) ? 0 : arank.getId2rank().getOrDefault(id, 0);
	}

	public static int getRealtimeRankById(int rankType, long id) {
		final xbean.ARank arank = getARank(rankType, false);
		return (arank == null) ? 0 : arank.getId2rank().getOrDefault(id, 0);
	}

	public static int getSnapRankById(int rankType, long id) {
		final xbean.ARank arank = getARank(rankType, true);
		return (arank == null) ? 0 : arank.getId2rank().getOrDefault(id, 0);
	}

	public static xbean.RankRecord getRecordById(int rankType, long id) {
		final cfg.bonus.Rank rcfg = CfgMgr.rank.get(rankType);
		final xbean.ARank arank = getARank(rankType, !rcfg.realtime);
		if(arank == null)
			return null;
		final Integer rank = arank.getId2rank().get(id);
		if(rank == null)
			return null;
		return arank.getRecords().get(rank);
	}

	public static xbean.RankRecord getRecordByRank(int rankType, int rank) {
		final cfg.bonus.Rank rcfg = CfgMgr.rank.get(rankType);
		final xbean.ARank arank = getARank(rankType, !rcfg.realtime);
		return arank != null ? arank.getRecords().get(rank) : null;
	}

}
