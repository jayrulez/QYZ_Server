
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AGetPayReturnInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AGetPayReturnInfo extends __AGetPayReturnInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 153;

	public int getType() {
		return 153;
	}

	public byte hasgotpayreturn;
	public long getreturnroleid;
	public long totalpay;
	public long totalyuanbao;
	public long totalbindyuanbao;
	public long totalvipexp;

	public AGetPayReturnInfo() {
	}

	public AGetPayReturnInfo(byte _hasgotpayreturn_, long _getreturnroleid_, long _totalpay_, long _totalyuanbao_, long _totalbindyuanbao_, long _totalvipexp_) {
		this.hasgotpayreturn = _hasgotpayreturn_;
		this.getreturnroleid = _getreturnroleid_;
		this.totalpay = _totalpay_;
		this.totalyuanbao = _totalyuanbao_;
		this.totalbindyuanbao = _totalbindyuanbao_;
		this.totalvipexp = _totalvipexp_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(hasgotpayreturn);
		_os_.marshal(getreturnroleid);
		_os_.marshal(totalpay);
		_os_.marshal(totalyuanbao);
		_os_.marshal(totalbindyuanbao);
		_os_.marshal(totalvipexp);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		hasgotpayreturn = _os_.unmarshal_byte();
		getreturnroleid = _os_.unmarshal_long();
		totalpay = _os_.unmarshal_long();
		totalyuanbao = _os_.unmarshal_long();
		totalbindyuanbao = _os_.unmarshal_long();
		totalvipexp = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AGetPayReturnInfo) {
			AGetPayReturnInfo _o_ = (AGetPayReturnInfo)_o1_;
			if (hasgotpayreturn != _o_.hasgotpayreturn) return false;
			if (getreturnroleid != _o_.getreturnroleid) return false;
			if (totalpay != _o_.totalpay) return false;
			if (totalyuanbao != _o_.totalyuanbao) return false;
			if (totalbindyuanbao != _o_.totalbindyuanbao) return false;
			if (totalvipexp != _o_.totalvipexp) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)hasgotpayreturn;
		_h_ += (int)getreturnroleid;
		_h_ += (int)totalpay;
		_h_ += (int)totalyuanbao;
		_h_ += (int)totalbindyuanbao;
		_h_ += (int)totalvipexp;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(hasgotpayreturn).append(",");
		_sb_.append(getreturnroleid).append(",");
		_sb_.append(totalpay).append(",");
		_sb_.append(totalyuanbao).append(",");
		_sb_.append(totalbindyuanbao).append(",");
		_sb_.append(totalvipexp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AGetPayReturnInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = hasgotpayreturn - _o_.hasgotpayreturn;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(getreturnroleid - _o_.getreturnroleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(totalpay - _o_.totalpay);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(totalyuanbao - _o_.totalyuanbao);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(totalbindyuanbao - _o_.totalbindyuanbao);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(totalvipexp - _o_.totalvipexp);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

