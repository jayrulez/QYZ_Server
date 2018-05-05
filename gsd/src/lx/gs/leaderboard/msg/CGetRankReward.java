
package lx.gs.leaderboard.msg;

import cfg.CfgMgr;
import cfg.bonus.RankBonus;
import cfg.bonus.RankBonusList;
import cfg.cmd.ConfigId;
import cfg.cmd.action.Bonus;
import cfg.cmd.condition.LimitType;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.leaderboard.FLeaderBoard;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.rank.msg.SGetReward;

import java.util.*;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetRankReward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetRankReward extends __CGetRankReward__ {
	@Override
	protected void process() {
		new AProcedure<CGetRankReward>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final RankBonus rb = CfgMgr.rankbonus.get(ranktype);
				final int ranking = FLeaderBoard.getYesterdayRanking(roleid, ranktype);
				if(ranking <= 0)
					return error(ErrorCode.PARAM_ERROR);
				if(!FLimit.checkLimitAndAddNum(roleid, ConfigId.RANK, ranktype, 1, LimitType.DAY, 1)) {
					return error(ErrorCode.HAS_GOT_ALL_REWARD);
				}

				Map<Integer, Bonus> bonusMap = new HashMap<>();
				List<Integer> rankList = new ArrayList<>();
				for(RankBonusList bonus : rb.bonuslist) {
					bonusMap.put(bonus.requirerank, bonus.bonuslist);
					rankList.add(bonus.requirerank);
				}
				Collections.sort(rankList);
				int lastRanking = rankList.get(rankList.size() - 1);
				if(ranking > lastRanking){
					return error(ErrorCode.PARAM_ERROR);
				}
				int targetIndex = lastRanking;
				for (int rank : rankList) {
					if(ranking <= rank){
						targetIndex = rank;
						break;
					}
				}
				boolean result = FBonus.addBonus(roleid, bonusMap.get(targetIndex), 1, common.Bonus.BindType.BIND, By.Rank);
				if(result) response(new SGetReward(ranktype));
				return result;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577524;

	public int getType() {
		return 6577524;
	}

	public int ranktype;

	public CGetRankReward() {
	}

	public CGetRankReward(int _ranktype_) {
		this.ranktype = _ranktype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ranktype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ranktype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetRankReward) {
			CGetRankReward _o_ = (CGetRankReward)_o1_;
			if (ranktype != _o_.ranktype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ranktype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ranktype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetRankReward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ranktype - _o_.ranktype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

