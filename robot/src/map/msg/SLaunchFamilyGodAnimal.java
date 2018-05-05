
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SLaunchFamilyGodAnimal__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SLaunchFamilyGodAnimal extends __SLaunchFamilyGodAnimal__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6705015;

	public int getType() {
		return 6705015;
	}

	public long familymapid; // 家族的地图id
	public long starttime; // 开始时间
	public long endtime; // 结束时间

	public SLaunchFamilyGodAnimal() {
	}

	public SLaunchFamilyGodAnimal(long _familymapid_, long _starttime_, long _endtime_) {
		this.familymapid = _familymapid_;
		this.starttime = _starttime_;
		this.endtime = _endtime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familymapid);
		_os_.marshal(starttime);
		_os_.marshal(endtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familymapid = _os_.unmarshal_long();
		starttime = _os_.unmarshal_long();
		endtime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SLaunchFamilyGodAnimal) {
			SLaunchFamilyGodAnimal _o_ = (SLaunchFamilyGodAnimal)_o1_;
			if (familymapid != _o_.familymapid) return false;
			if (starttime != _o_.starttime) return false;
			if (endtime != _o_.endtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familymapid;
		_h_ += (int)starttime;
		_h_ += (int)endtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familymapid).append(",");
		_sb_.append(starttime).append(",");
		_sb_.append(endtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SLaunchFamilyGodAnimal _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(familymapid - _o_.familymapid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(starttime - _o_.starttime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(endtime - _o_.endtime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

