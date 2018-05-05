
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CClaimFamilyBonus__ extends xio.Protocol { }

/** 领取家族奖励，领取过的级别不能再领，每周重置
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CClaimFamilyBonus extends __CClaimFamilyBonus__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579117;

	public int getType() {
		return 6579117;
	}

	public int claimlevel; // 要领取的级别

	public CClaimFamilyBonus() {
	}

	public CClaimFamilyBonus(int _claimlevel_) {
		this.claimlevel = _claimlevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(claimlevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		claimlevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CClaimFamilyBonus) {
			CClaimFamilyBonus _o_ = (CClaimFamilyBonus)_o1_;
			if (claimlevel != _o_.claimlevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += claimlevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(claimlevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CClaimFamilyBonus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = claimlevel - _o_.claimlevel;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

