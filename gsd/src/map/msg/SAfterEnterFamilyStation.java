
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAfterEnterFamilyStation__ extends xio.Protocol { }

/** 家族仙府聚会，刷怪，吃符咒的方式
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAfterEnterFamilyStation extends __SAfterEnterFamilyStation__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6697426;

	public int getType() {
		return 6697426;
	}

	public int remaintime;
	public int godanimalremaintime;

	public SAfterEnterFamilyStation() {
	}

	public SAfterEnterFamilyStation(int _remaintime_, int _godanimalremaintime_) {
		this.remaintime = _remaintime_;
		this.godanimalremaintime = _godanimalremaintime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(remaintime);
		_os_.marshal(godanimalremaintime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		remaintime = _os_.unmarshal_int();
		godanimalremaintime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAfterEnterFamilyStation) {
			SAfterEnterFamilyStation _o_ = (SAfterEnterFamilyStation)_o1_;
			if (remaintime != _o_.remaintime) return false;
			if (godanimalremaintime != _o_.godanimalremaintime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += remaintime;
		_h_ += godanimalremaintime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(remaintime).append(",");
		_sb_.append(godanimalremaintime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SAfterEnterFamilyStation _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = remaintime - _o_.remaintime;
		if (0 != _c_) return _c_;
		_c_ = godanimalremaintime - _o_.godanimalremaintime;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

