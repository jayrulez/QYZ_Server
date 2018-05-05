
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bag.StorageBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSell__ extends xio.Protocol { }

/** 出售物品
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSell extends __CSell__ {
	@Override
	protected void process() {
		new AProcedure<CSell>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				StorageBag bag = FBag.getStorageBag(roleid, param.bagtype);
				if(bag == null || param.sellnum <= 0){
					return error(ErrorCode.PARAM_ERROR);
				}
				ErrorCode errorCode = bag.sell(param.pos, param.sellnum);
				if(errorCode.err()){
					return error(errorCode);
				}
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575552;

	public int getType() {
		return 6575552;
	}

	public int bagtype;
	public int pos; // 位置
	public int sellnum; // 售出数量

	public CSell() {
	}

	public CSell(int _bagtype_, int _pos_, int _sellnum_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.sellnum = _sellnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(sellnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		sellnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSell) {
			CSell _o_ = (CSell)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (sellnum != _o_.sellnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += sellnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(sellnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSell _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = sellnum - _o_.sellnum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

