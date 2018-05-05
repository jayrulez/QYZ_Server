
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetFamilyRequestingInfo__ extends xio.Protocol { }

/** 获取请求加入家族的信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetFamilyRequestingInfo extends __SGetFamilyRequestingInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564905;

	public int getType() {
		return 6564905;
	}

	public java.util.HashMap<Long,lx.gs.role.msg.RoleShowInfo4> requestinglist; // 申请成员列表,key为角色id

	public SGetFamilyRequestingInfo() {
		requestinglist = new java.util.HashMap<Long,lx.gs.role.msg.RoleShowInfo4>();
	}

	public SGetFamilyRequestingInfo(java.util.HashMap<Long,lx.gs.role.msg.RoleShowInfo4> _requestinglist_) {
		this.requestinglist = _requestinglist_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Long, lx.gs.role.msg.RoleShowInfo4> _e_ : requestinglist.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(requestinglist.size());
		for (java.util.Map.Entry<Long, lx.gs.role.msg.RoleShowInfo4> _e_ : requestinglist.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			lx.gs.role.msg.RoleShowInfo4 _v_ = new lx.gs.role.msg.RoleShowInfo4();
			_v_.unmarshal(_os_);
			requestinglist.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetFamilyRequestingInfo) {
			SGetFamilyRequestingInfo _o_ = (SGetFamilyRequestingInfo)_o1_;
			if (!requestinglist.equals(_o_.requestinglist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += requestinglist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(requestinglist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

