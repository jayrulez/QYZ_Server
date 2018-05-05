
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;

import common.ErrorCode;
import gs.AProcedure;
import lx.gs.friend.FFriend;
import lx.gs.marriage.FMarriage;
import xbean.RoleMarriage;
import xdb.Lockeys;

import com.goldhuman.Common.Marshal.MarshalException;
import xdb.Xdb;
import xtable.Locks;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBedivorceWithDiscuss__ extends xio.Protocol { }

/** 被离婚者的答复信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

    public class CBedivorceWithDiscuss extends __CBedivorceWithDiscuss__ {
	@Override
	protected void process() {
		new AProcedure<CBedivorceWithDiscuss>(this) {

			@Override
			protected boolean doProcess() throws Exception { 
				if(divorceresult == 1){//如果同意离婚，那么要清空伴侣id信息
					lock(Lockeys.get(xtable.Locks.ROLELOCK, roleid, divorceroleid)); //都先进行加锁操作
					RoleMarriage divorceInfo = FMarriage.getMarriageInfo(divorceroleid);
                    if(divorceInfo.getCoupleroleid() != roleid){
                        return error(ErrorCode.NOT_YOUR_WIFE_OR_HUSBAND);
                    }
					xbean.RoleMarriage bedivorceInfo = FMarriage.getMarriageInfo(roleid);
					divorceInfo.setCoupleroleid(0L);
					bedivorceInfo.setCoupleroleid(0L);
                    FFriend.clearBanlvInfo(divorceroleid, roleid);
                    xdb.Trace.info("role1 = {} and role2 = {} has successed divorce", divorceroleid, roleid);
				}
				SDivorceWithDiscuss notify = new SDivorceWithDiscuss();
				notify.bedivorceroleid = roleid;
				notify.divorceresult = divorceresult;
                notify.bedivorcerolename = Roleinfos.selectName(roleid);
				tsendWhileCommit(divorceroleid, notify);//通知发起离婚者结果。
                response(notify);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582336;

	public int getType() {
		return 6582336;
	}

	public long divorceroleid; // 发起离婚的id
	public int divorceresult; // 0表示拒绝离婚，1表示同意离婚

	public CBedivorceWithDiscuss() {
	}

	public CBedivorceWithDiscuss(long _divorceroleid_, int _divorceresult_) {
		this.divorceroleid = _divorceroleid_;
		this.divorceresult = _divorceresult_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(divorceroleid);
		_os_.marshal(divorceresult);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		divorceroleid = _os_.unmarshal_long();
		divorceresult = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBedivorceWithDiscuss) {
			CBedivorceWithDiscuss _o_ = (CBedivorceWithDiscuss)_o1_;
			if (divorceroleid != _o_.divorceroleid) return false;
			if (divorceresult != _o_.divorceresult) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)divorceroleid;
		_h_ += divorceresult;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(divorceroleid).append(",");
		_sb_.append(divorceresult).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CBedivorceWithDiscuss _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(divorceroleid - _o_.divorceroleid);
		if (0 != _c_) return _c_;
		_c_ = divorceresult - _o_.divorceresult;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

