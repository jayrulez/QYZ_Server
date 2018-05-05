
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyMembersInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyMembersInfo extends __SGetFamilyMembersInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566349;

	public int getType() {
		return 6566349;
	}

	public java.util.HashMap<Integer,lx.gs.family.msg.FamilyJobStaffList> jobinfo; // 家族职位信息，key为职位id，参考配置文件value为该职位对应的角色列表
	public java.util.HashMap<Long,lx.gs.family.msg.FamilyMember> members; // 家族成员信息

	public SGetFamilyMembersInfo() {
		jobinfo = new java.util.HashMap<Integer,lx.gs.family.msg.FamilyJobStaffList>();
		members = new java.util.HashMap<Long,lx.gs.family.msg.FamilyMember>();
	}

	public SGetFamilyMembersInfo(java.util.HashMap<Integer,lx.gs.family.msg.FamilyJobStaffList> _jobinfo_, java.util.HashMap<Long,lx.gs.family.msg.FamilyMember> _members_) {
		this.jobinfo = _jobinfo_;
		this.members = _members_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.family.msg.FamilyJobStaffList> _e_ : jobinfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (java.util.Map.Entry<Long, lx.gs.family.msg.FamilyMember> _e_ : members.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(jobinfo.size());
		for (java.util.Map.Entry<Integer, lx.gs.family.msg.FamilyJobStaffList> _e_ : jobinfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(members.size());
		for (java.util.Map.Entry<Long, lx.gs.family.msg.FamilyMember> _e_ : members.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.family.msg.FamilyJobStaffList _v_ = new lx.gs.family.msg.FamilyJobStaffList();
			_v_.unmarshal(_os_);
			jobinfo.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.family.msg.FamilyMember _v_ = new lx.gs.family.msg.FamilyMember();
			_v_.unmarshal(_os_);
			members.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyMembersInfo) {
			SGetFamilyMembersInfo _o_ = (SGetFamilyMembersInfo)_o1_;
			if (!jobinfo.equals(_o_.jobinfo)) return false;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += jobinfo.hashCode();
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(jobinfo).append(",");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

