
package lx.gs.achievement.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.achievement.FAchievement;
import common.ErrorCode;

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
				final ErrorCode err = FAchievement.getReward(roleid, achievementid);
				if(err.err())
					return error(err);
				response(new SGetReward(achievementid));
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575020;

	public int getType() {
		return 6575020;
	}

	public int achievementid;

	public CGetReward() {
	}

	public CGetReward(int _achievementid_) {
		this.achievementid = _achievementid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(achievementid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		achievementid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetReward) {
			CGetReward _o_ = (CGetReward)_o1_;
			if (achievementid != _o_.achievementid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += achievementid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(achievementid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetReward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = achievementid - _o_.achievementid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

