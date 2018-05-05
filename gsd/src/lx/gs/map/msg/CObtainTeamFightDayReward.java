
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.map.FMap;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CObtainTeamFightDayReward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CObtainTeamFightDayReward extends __CObtainTeamFightDayReward__ {
	@Override
	protected void process() {
		new AProcedure<CObtainTeamFightDayReward>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final xbean.TeamFightInfo info = FMap.getEctype(roleid).getTeamfight();
                if(info.getObtaintodaywinreward())
                    return error(ErrorCode.HAS_OBTAIN_TEAM_FIGHT_DAY_REWARD);
                if(info.getTodaywinnum() < CfgMgr.teamfight.dailywintimes)
                    return error(ErrorCode.TODAY_WIN_NUM_NOT_ENOUGH);
                boolean result = FBonus.addBonus(roleid, CfgMgr.teamfight.dailywinbonus, common.Bonus.BindType.BIND, By.Team_Fight_Day_Reward);
                info.setObtaintodaywinreward(true);
                if(result) response(new SObtainTeamFightDayReward());
                return result;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578760;

	public int getType() {
		return 6578760;
	}


	public CObtainTeamFightDayReward() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CObtainTeamFightDayReward) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CObtainTeamFightDayReward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

