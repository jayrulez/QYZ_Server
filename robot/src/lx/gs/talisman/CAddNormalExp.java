
package lx.gs.talisman;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAddNormalExp__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAddNormalExp extends __CAddNormalExp__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579019;

	public int getType() {
		return 6579019;
	}

	public int bagtype;
	public int pos;
	public int materialpos;
	public int materialnum;

	public CAddNormalExp() {
	}

	public CAddNormalExp(int _bagtype_, int _pos_, int _materialpos_, int _materialnum_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.materialpos = _materialpos_;
		this.materialnum = _materialnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(materialpos);
		_os_.marshal(materialnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		materialpos = _os_.unmarshal_int();
		materialnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAddNormalExp) {
			CAddNormalExp _o_ = (CAddNormalExp)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (materialpos != _o_.materialpos) return false;
			if (materialnum != _o_.materialnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += materialpos;
		_h_ += materialnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(materialpos).append(",");
		_sb_.append(materialnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CAddNormalExp _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = materialpos - _o_.materialpos;
		if (0 != _c_) return _c_;
		_c_ = materialnum - _o_.materialnum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

