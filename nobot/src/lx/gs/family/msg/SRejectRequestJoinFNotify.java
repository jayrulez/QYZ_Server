
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRejectRequestJoinFNotify__ extends xio.Protocol { }

/** 拒绝申请的通知，拒绝申请的通知，该通知会发给族长，副族长以及被拒绝申请的玩家
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRejectRequestJoinFNotify extends __SRejectRequestJoinFNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564607;

	public int getType() {
		return 6564607;
	}

	public lx.gs.family.msg.FamilyBasicInfo family; // 同意申请的家族信息
	public long memberid; // 拒绝申请的角色id

	public SRejectRequestJoinFNotify() {
		family = new lx.gs.family.msg.FamilyBasicInfo();
	}

	public SRejectRequestJoinFNotify(lx.gs.family.msg.FamilyBasicInfo _family_, long _memberid_) {
		this.family = _family_;
		this.memberid = _memberid_;
	}

	public final boolean _validator_() {
		if (!family._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(family);
		_os_.marshal(memberid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		family.unmarshal(_os_);
		memberid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRejectRequestJoinFNotify) {
			SRejectRequestJoinFNotify _o_ = (SRejectRequestJoinFNotify)_o1_;
			if (!family.equals(_o_.family)) return false;
			if (memberid != _o_.memberid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += family.hashCode();
		_h_ += (int)memberid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(family).append(",");
		_sb_.append(memberid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

