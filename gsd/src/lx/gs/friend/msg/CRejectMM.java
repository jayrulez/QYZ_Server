
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.msg.CRejectMM;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SRejectMM;
import lx.gs.friend.msg.SRejectMMNotify;

import com.goldhuman.Common.Marshal.MarshalException;

import java.util.Arrays;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRejectMM__ extends xio.Protocol { }

/** 拒绝脉脉关系
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRejectMM extends __CRejectMM__ {
	@Override
	protected void process() {
		new AProcedure<CRejectMM>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    final long myid = roleid;
                final long toid = param.roleid;
			    lock(xtable.Rolefriendsinfo.getTable(), Arrays.asList(myid, toid));
				SRejectMM result = new SRejectMM();
				xbean.RoleFriendsInfo myinfo = FFriend.getRoleFriendsInfo(myid);

				if (!myinfo.getFriends().containsKey(toid)) {
					return error(ErrorCode.FRIEND_NOT_FRIEND);
				}

				result.result = ErrorCode.OK.getErrorId();
				result.mmtype = param.mmtype;
				tsend(myid, result);

				SRejectMMNotify notify = new SRejectMMNotify();
				notify.mmtype = param.mmtype;
				notify.roleid = myid;
				notify.rolename = xtable.Roleinfos.selectName(myid);
				tsend(toid, notify);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575075;

	public int getType() {
		return 6575075;
	}

	public long roleid;
	public int mmtype;

	public CRejectMM() {
	}

	public CRejectMM(long _roleid_, int _mmtype_) {
		this.roleid = _roleid_;
		this.mmtype = _mmtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(mmtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		mmtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRejectMM) {
			CRejectMM _o_ = (CRejectMM)_o1_;
			if (roleid != _o_.roleid) return false;
			if (mmtype != _o_.mmtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += mmtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(mmtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRejectMM _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = mmtype - _o_.mmtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
