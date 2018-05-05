
package lx.gs.arena.msg;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import common.ErrorCode;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;

import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CObtainDailySuccReward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CObtainDailySuccReward extends __CObtainDailySuccReward__ {
	@Override
	protected void process() {
		new AProcedure<CObtainDailySuccReward>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final xbean.DailyArena info = FLimit.getDaily(roleid).getArena();
				final List<Integer> obtainRewards = info.getObtainrewards();
				if(obtainRewards.contains(rewardid))
					return error(ErrorCode.ARENA_OBTAIN_REWARD);
				final cfg.arena.ArenaSpecialAward acfg = CfgMgr.arenaconfig.specialawardlist_times.get(rewardid);
				if(info.getChallengesuccnum() < acfg.times)
					return error(ErrorCode.ARENA_CAN_NOT_OBTAIN_REWARD);
				obtainRewards.add(rewardid);

				final SObtainDailySuccReward re = new SObtainDailySuccReward();
				re.rewardid = rewardid;
				response(re);
				return FBonus.genAndAddBonus(roleid, acfg.award, common.Bonus.BindType.BIND, re.bonus, By.Arena_DayChallenge_Count);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571529;

	public int getType() {
		return 6571529;
	}

	public int rewardid;

	public CObtainDailySuccReward() {
	}

	public CObtainDailySuccReward(int _rewardid_) {
		this.rewardid = _rewardid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(rewardid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		rewardid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CObtainDailySuccReward) {
			CObtainDailySuccReward _o_ = (CObtainDailySuccReward)_o1_;
			if (rewardid != _o_.rewardid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += rewardid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rewardid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CObtainDailySuccReward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = rewardid - _o_.rewardid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

