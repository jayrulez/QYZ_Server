
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLaunchGodAnimalActivityNotify__ extends xio.Protocol { }

/** 开始神兽挑战,每周两次，通知全族人员
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLaunchGodAnimalActivityNotify extends __SLaunchGodAnimalActivityNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570215;

	public int getType() {
		return 6570215;
	}

	public long launchroleid; // 开启的角色id
	public long starttime; // 下一次神兽活动开始时间

	public SLaunchGodAnimalActivityNotify() {
	}

	public SLaunchGodAnimalActivityNotify(long _launchroleid_, long _starttime_) {
		this.launchroleid = _launchroleid_;
		this.starttime = _starttime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(launchroleid);
		_os_.marshal(starttime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		launchroleid = _os_.unmarshal_long();
		starttime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLaunchGodAnimalActivityNotify) {
			SLaunchGodAnimalActivityNotify _o_ = (SLaunchGodAnimalActivityNotify)_o1_;
			if (launchroleid != _o_.launchroleid) return false;
			if (starttime != _o_.starttime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)launchroleid;
		_h_ += (int)starttime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(launchroleid).append(",");
		_sb_.append(starttime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SLaunchGodAnimalActivityNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(launchroleid - _o_.launchroleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(starttime - _o_.starttime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

