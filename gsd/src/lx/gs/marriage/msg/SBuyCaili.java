
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBuyCaili__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBuyCaili extends __SBuyCaili__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565696;

	public int getType() {
		return 6565696;
	}

	public int cailitypeid; // 购买的彩礼类型

	public SBuyCaili() {
	}

	public SBuyCaili(int _cailitypeid_) {
		this.cailitypeid = _cailitypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(cailitypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		cailitypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBuyCaili) {
			SBuyCaili _o_ = (SBuyCaili)_o1_;
			if (cailitypeid != _o_.cailitypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += cailitypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(cailitypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBuyCaili _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = cailitypeid - _o_.cailitypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

