
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.BagModule;
import lx.gs.bag.FBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSortBag__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSortBag extends __CSortBag__ {
	@Override
	protected void process() {
		new AProcedure<CSortBag>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!BagModule.INSTANCE.onSort(roleid, bagtype)){
					return error(ErrorCode.OPERATION_LATER);
				}
				FBag.getStorageBag(roleid, param.bagtype).sort();
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578091;

	public int getType() {
		return 6578091;
	}

	public int bagtype;

	public CSortBag() {
	}

	public CSortBag(int _bagtype_) {
		this.bagtype = _bagtype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSortBag) {
			CSortBag _o_ = (CSortBag)_o1_;
			if (bagtype != _o_.bagtype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSortBag _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

