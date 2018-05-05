
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.marriage.FMarriage;

import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDivorceWithDiscuss__ extends xio.Protocol { }

/** 与对象协商离婚
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDivorceWithDiscuss extends __CDivorceWithDiscuss__ {
	@Override
	protected void process() {
		new AProcedure<CDivorceWithDiscuss>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleMarriage marryInfo = FMarriage.getMarriageInfo(roleid);
				if(marryInfo.getCoupleroleid() != bedivorceroleid){//如果传的id和服务器存的伴侣id不匹配
					return error(ErrorCode.NOT_YOUR_WIFE_OR_HUSBAND);
				}
				SBedivorceWithDiscuss notify = new SBedivorceWithDiscuss();
				notify.divorceroleid = roleid;
				tsendWhileCommit(bedivorceroleid, notify);//通知被离婚的人
                response(notify);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568993;

	public int getType() {
		return 6568993;
	}

	public long bedivorceroleid; // 被离婚对象的id

	public CDivorceWithDiscuss() {
	}

	public CDivorceWithDiscuss(long _bedivorceroleid_) {
		this.bedivorceroleid = _bedivorceroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bedivorceroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bedivorceroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CDivorceWithDiscuss) {
			CDivorceWithDiscuss _o_ = (CDivorceWithDiscuss)_o1_;
			if (bedivorceroleid != _o_.bedivorceroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)bedivorceroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bedivorceroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CDivorceWithDiscuss _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(bedivorceroleid - _o_.bedivorceroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

