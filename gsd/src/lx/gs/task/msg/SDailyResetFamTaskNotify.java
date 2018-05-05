
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SDailyResetFamTaskNotify__ extends xio.Protocol { }

/** 每天重置家族环任务信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SDailyResetFamTaskNotify extends __SDailyResetFamTaskNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578381;

	public int getType() {
		return 6578381;
	}

	public int comdaycycle; // 已经完成的日环任务组数
	public int comweeksmallcycle; // 已经完成的周环任务组数

	public SDailyResetFamTaskNotify() {
	}

	public SDailyResetFamTaskNotify(int _comdaycycle_, int _comweeksmallcycle_) {
		this.comdaycycle = _comdaycycle_;
		this.comweeksmallcycle = _comweeksmallcycle_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(comdaycycle);
		_os_.marshal(comweeksmallcycle);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		comdaycycle = _os_.unmarshal_int();
		comweeksmallcycle = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SDailyResetFamTaskNotify) {
			SDailyResetFamTaskNotify _o_ = (SDailyResetFamTaskNotify)_o1_;
			if (comdaycycle != _o_.comdaycycle) return false;
			if (comweeksmallcycle != _o_.comweeksmallcycle) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += comdaycycle;
		_h_ += comweeksmallcycle;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(comdaycycle).append(",");
		_sb_.append(comweeksmallcycle).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SDailyResetFamTaskNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = comdaycycle - _o_.comdaycycle;
		if (0 != _c_) return _c_;
		_c_ = comweeksmallcycle - _o_.comweeksmallcycle;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

