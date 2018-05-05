
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.msg.CSetMMAuthorization;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SSetMMAuthorization;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSetMMAuthorization__ extends xio.Protocol { }

/** 设置mm权限
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSetMMAuthorization extends __CSetMMAuthorization__ {
	@Override
	protected void process() {
		new AProcedure<CSetMMAuthorization>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleFriendsInfo info = FFriend.getRoleFriendsInfo(roleid);
				if (null == info) {
					return error(ErrorCode.FRIEND_NOT_FOUND);
				}
				FFriend.setMMAuthorization(roleid, param.allowfriendgetmm, param.allowstrangergetmm);
				SSetMMAuthorization result = new SSetMMAuthorization();
				result.result = ErrorCode.OK.getErrorId();
				result.allowfriendgetmm = param.allowfriendgetmm;
				result.allowstrangergetmm = param.allowstrangergetmm;

				tsend(roleid, result);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572361;

	public int getType() {
		return 6572361;
	}

	public int allowfriendgetmm;
	public int allowstrangergetmm;

	public CSetMMAuthorization() {
	}

	public CSetMMAuthorization(int _allowfriendgetmm_, int _allowstrangergetmm_) {
		this.allowfriendgetmm = _allowfriendgetmm_;
		this.allowstrangergetmm = _allowstrangergetmm_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(allowfriendgetmm);
		_os_.marshal(allowstrangergetmm);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		allowfriendgetmm = _os_.unmarshal_int();
		allowstrangergetmm = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSetMMAuthorization) {
			CSetMMAuthorization _o_ = (CSetMMAuthorization)_o1_;
			if (allowfriendgetmm != _o_.allowfriendgetmm) return false;
			if (allowstrangergetmm != _o_.allowstrangergetmm) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += allowfriendgetmm;
		_h_ += allowstrangergetmm;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(allowfriendgetmm).append(",");
		_sb_.append(allowstrangergetmm).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSetMMAuthorization _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = allowfriendgetmm - _o_.allowfriendgetmm;
		if (0 != _c_) return _c_;
		_c_ = allowstrangergetmm - _o_.allowstrangergetmm;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
