
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAcceptRequestJoinFNotify__ extends xio.Protocol { }

/** 同意申请的通知，该通知会发给族长，副族长以及被同意申请的玩家
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAcceptRequestJoinFNotify extends __SAcceptRequestJoinFNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575273;

	public int getType() {
		return 6575273;
	}

	public lx.gs.family.msg.FamilyBasicInfo family; // 同意申请的家族信息
	public lx.gs.family.msg.FamilyMember member; // 同意申请的角色id

	public SAcceptRequestJoinFNotify() {
		family = new lx.gs.family.msg.FamilyBasicInfo();
		member = new lx.gs.family.msg.FamilyMember();
	}

	public SAcceptRequestJoinFNotify(lx.gs.family.msg.FamilyBasicInfo _family_, lx.gs.family.msg.FamilyMember _member_) {
		this.family = _family_;
		this.member = _member_;
	}

	public final boolean _validator_() {
		if (!family._validator_()) return false;
		if (!member._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(family);
		_os_.marshal(member);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		family.unmarshal(_os_);
		member.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAcceptRequestJoinFNotify) {
			SAcceptRequestJoinFNotify _o_ = (SAcceptRequestJoinFNotify)_o1_;
			if (!family.equals(_o_.family)) return false;
			if (!member.equals(_o_.member)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += family.hashCode();
		_h_ += member.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(family).append(",");
		_sb_.append(member).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

