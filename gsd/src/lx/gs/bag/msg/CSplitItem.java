
package lx.gs.bag.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bag.StorageBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSplitItem__ extends xio.Protocol { }

/** 拆分物品
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSplitItem extends __CSplitItem__ {
	@Override
	protected void process() {
		new AProcedure<CSplitItem>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				StorageBag storageBag = FBag.getStorageBag(roleid, param.bagtype);
				ErrorCode errorCode = storageBag.split(param.pos, param.splitnumber);
				if(errorCode.err()){
					return error(errorCode);
				}
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573279;

	public int getType() {
		return 6573279;
	}

	public int bagtype;
	public int pos; // 位置
	public int splitnumber;

	public CSplitItem() {
	}

	public CSplitItem(int _bagtype_, int _pos_, int _splitnumber_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.splitnumber = _splitnumber_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(splitnumber);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		splitnumber = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSplitItem) {
			CSplitItem _o_ = (CSplitItem)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (splitnumber != _o_.splitnumber) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += splitnumber;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(splitnumber).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSplitItem _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = splitnumber - _o_.splitnumber;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

