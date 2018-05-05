
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.friend.FFriend;
import lx.gs.friend.msg.SGetMaimaiInfo;

import java.util.Arrays;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetMaimaiInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetMaimaiInfo extends __CGetMaimaiInfo__ {
	@Override
	protected void process() {
		new AProcedure<CGetMaimaiInfo>(this) {

			@Override
			protected boolean doProcess() throws Exception {
			    final long myid = roleid;
                final long toid = param.roleid;
                SGetMaimaiInfo result = new SGetMaimaiInfo();

				xbean.RoleFriendsInfo toinfo = xtable.Rolefriendsinfo.select(toid);
                if(toinfo != null) {
                    boolean allow = false;
                    if (toinfo.getFriends().containsKey(myid)) {
                        if (toinfo.getIsallowfriendgetmm() == 1) {
                            allow = true;
                        }
                    } else {
                        if (toinfo.getIsallowstrangergetmm() == 1) {
                            allow = true;
                        }
                    }
                    if (allow) {
                        result.result = ErrorCode.OK.getErrorId();
                        result.roleid = toid;
                        result.mminfo = FFriend.makeProtocolMMInfo(toinfo);
                    } else {
                        return error(ErrorCode.FRIEND_NOT_ALLOW_GETMM);
                    }
                } else {
                    return error(ErrorCode.FRIEND_NOT_ALLOW_GETMM);
                }
				
				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576304;

	public int getType() {
		return 6576304;
	}

	public long roleid;

	public CGetMaimaiInfo() {
	}

	public CGetMaimaiInfo(long _roleid_) {
		this.roleid = _roleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetMaimaiInfo) {
			CGetMaimaiInfo _o_ = (CGetMaimaiInfo)_o1_;
			if (roleid != _o_.roleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetMaimaiInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
