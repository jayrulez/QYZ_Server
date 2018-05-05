
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFamilyPartyOpenNotify__ extends xio.Protocol { }

/** 通知帮众家族仙府开启了
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFamilyPartyOpenNotify extends __SFamilyPartyOpenNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569920;

	public int getType() {
		return 6569920;
	}

	public long openid; // 召集人的id，客户端过滤用

	public SFamilyPartyOpenNotify() {
	}

	public SFamilyPartyOpenNotify(long _openid_) {
		this.openid = _openid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(openid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		openid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFamilyPartyOpenNotify) {
			SFamilyPartyOpenNotify _o_ = (SFamilyPartyOpenNotify)_o1_;
			if (openid != _o_.openid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)openid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(openid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SFamilyPartyOpenNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(openid - _o_.openid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

