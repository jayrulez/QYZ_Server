
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SKickoutFamilyMemberNotify__ extends xio.Protocol { }

/** 踢出家族通知，通知发给被踢的人
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SKickoutFamilyMemberNotify extends __SKickoutFamilyMemberNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569346;

	public int getType() {
		return 6569346;
	}

	public long memberid; // 要踢出家族的角色id
	public lx.gs.family.msg.FamilyBasicInfo family; // 家族基本信息

	public SKickoutFamilyMemberNotify() {
		family = new lx.gs.family.msg.FamilyBasicInfo();
	}

	public SKickoutFamilyMemberNotify(long _memberid_, lx.gs.family.msg.FamilyBasicInfo _family_) {
		this.memberid = _memberid_;
		this.family = _family_;
	}

	public final boolean _validator_() {
		if (!family._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(memberid);
		_os_.marshal(family);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		memberid = _os_.unmarshal_long();
		family.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SKickoutFamilyMemberNotify) {
			SKickoutFamilyMemberNotify _o_ = (SKickoutFamilyMemberNotify)_o1_;
			if (memberid != _o_.memberid) return false;
			if (!family.equals(_o_.family)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)memberid;
		_h_ += family.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(memberid).append(",");
		_sb_.append(family).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

