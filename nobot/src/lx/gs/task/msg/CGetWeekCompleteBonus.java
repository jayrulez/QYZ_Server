
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetWeekCompleteBonus__ extends xio.Protocol { }

/** 每周完成一定小环环任务数后领奖，目前完成10，20，40，70环数后会有奖励
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetWeekCompleteBonus extends __CGetWeekCompleteBonus__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581935;

	public int getType() {
		return 6581935;
	}

	public int bonuslvl; // 要领取的档位,用0，1，2，3...表示

	public CGetWeekCompleteBonus() {
	}

	public CGetWeekCompleteBonus(int _bonuslvl_) {
		this.bonuslvl = _bonuslvl_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bonuslvl);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bonuslvl = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetWeekCompleteBonus) {
			CGetWeekCompleteBonus _o_ = (CGetWeekCompleteBonus)_o1_;
			if (bonuslvl != _o_.bonuslvl) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bonuslvl;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bonuslvl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetWeekCompleteBonus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bonuslvl - _o_.bonuslvl;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

