
package lx.gs.rank.msg;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.rank.FRank;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetReward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetReward extends __CGetReward__ {
	@Override
	protected void process() {
		new AProcedure<CGetReward>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final cfg.bonus.RankBonus rb = CfgMgr.rankbonus.get(ranktype);
				final int snapRank = FRank.getSnapRankById(ranktype, roleid);
				if(snapRank <= 0)
					return error("未上榜没有奖励");
				if(!FLimit.checkLimitAndAddNum(roleid, ConfigId.RANK, ranktype, 1, LimitType.DAY, 1)) {
					return error(ErrorCode.HAS_GOT_ALL_REWARD);
				}
				for(cfg.bonus.RankBonusList bonus : rb.bonuslist) {
					if(snapRank >= bonus.requirerank) {
						if(!FBonus.addBonus(roleid, bonus.bonuslist, 1, common.Bonus.BindType.BIND, By.Rank)){
							return false;
						}
						response(new SGetReward(ranktype));
						return true;
					}
				}
				return error("当前名次没有奖励");
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6558621;

	public int getType() {
		return 6558621;
	}

	public int ranktype;

	public CGetReward() {
	}

	public CGetReward(int _ranktype_) {
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
		if (_o1_ instanceof CGetReward) {
			CGetReward _o_ = (CGetReward)_o1_;
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

	public int compareTo(CGetReward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ranktype - _o_.ranktype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

