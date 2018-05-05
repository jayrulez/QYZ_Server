
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bag.StorageBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnlockGrid__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnlockGrid extends __CUnlockGrid__ {
	@Override
	protected void process() {
		new AProcedure<CUnlockGrid>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				StorageBag bag = FBag.getStorageBag(roleid, param.bagtype);
				if(bag == null || param.unlocknum <= 0){
					return error(ErrorCode.PARAM_ERROR);
				}
				ErrorCode errorCode = bag.unlockGrid(param.unlocknum);
				if(errorCode.err()){
					return error(errorCode);
				}
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565022;

	public int getType() {
		return 6565022;
	}

	public int bagtype;
	public int unlocknum; // 解锁的格子数

	public CUnlockGrid() {
	}

	public CUnlockGrid(int _bagtype_, int _unlocknum_) {
		this.bagtype = _bagtype_;
		this.unlocknum = _unlocknum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(unlocknum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		unlocknum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnlockGrid) {
			CUnlockGrid _o_ = (CUnlockGrid)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (unlocknum != _o_.unlocknum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += unlocknum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(unlocknum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUnlockGrid _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = unlocknum - _o_.unlocknum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

