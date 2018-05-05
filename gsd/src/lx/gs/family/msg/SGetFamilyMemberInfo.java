
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyMemberInfo__ extends xio.Protocol { }

/** 获取家族成员详细信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyMemberInfo extends __SGetFamilyMemberInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564135;

	public int getType() {
		return 6564135;
	}

	public long memberid;
	public lx.gs.family.msg.FamilyMember baseinfo;
	public lx.gs.role.msg.RoleShowInfo3 publicinfo;

	public SGetFamilyMemberInfo() {
		baseinfo = new lx.gs.family.msg.FamilyMember();
		publicinfo = new lx.gs.role.msg.RoleShowInfo3();
	}

	public SGetFamilyMemberInfo(long _memberid_, lx.gs.family.msg.FamilyMember _baseinfo_, lx.gs.role.msg.RoleShowInfo3 _publicinfo_) {
		this.memberid = _memberid_;
		this.baseinfo = _baseinfo_;
		this.publicinfo = _publicinfo_;
	}

	public final boolean _validator_() {
		if (!baseinfo._validator_()) return false;
		if (!publicinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(memberid);
		_os_.marshal(baseinfo);
		_os_.marshal(publicinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		memberid = _os_.unmarshal_long();
		baseinfo.unmarshal(_os_);
		publicinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyMemberInfo) {
			SGetFamilyMemberInfo _o_ = (SGetFamilyMemberInfo)_o1_;
			if (memberid != _o_.memberid) return false;
			if (!baseinfo.equals(_o_.baseinfo)) return false;
			if (!publicinfo.equals(_o_.publicinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)memberid;
		_h_ += baseinfo.hashCode();
		_h_ += publicinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(memberid).append(",");
		_sb_.append(baseinfo).append(",");
		_sb_.append(publicinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

