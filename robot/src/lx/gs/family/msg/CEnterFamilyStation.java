
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEnterFamilyStation__ extends xio.Protocol { }

/** 帮众进入家族仙府驻地
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEnterFamilyStation extends __CEnterFamilyStation__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581103;

	public int getType() {
		return 6581103;
	}

	public final static int NORMAL_ENTER = 0;
	public final static int OPEN_PARTY = 1;
	public final static int BLACK_MARKET = 2;
	public final static int FAMILY_MANAGER = 3;
	public final static int GOD_ANIMAL = 4;

	public int isopenparty; // 是否是去开启家族仙府聚宴的,0 否；1 是

	public CEnterFamilyStation() {
	}

	public CEnterFamilyStation(int _isopenparty_) {
		this.isopenparty = _isopenparty_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(isopenparty);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		isopenparty = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEnterFamilyStation) {
			CEnterFamilyStation _o_ = (CEnterFamilyStation)_o1_;
			if (isopenparty != _o_.isopenparty) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += isopenparty;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(isopenparty).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEnterFamilyStation _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = isopenparty - _o_.isopenparty;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

