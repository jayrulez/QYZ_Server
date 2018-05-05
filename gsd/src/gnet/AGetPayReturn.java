
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import lx.gs.pay.PGotPayReturn;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AGetPayReturn__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AGetPayReturn extends __AGetPayReturn__ {
	@Override
	protected void process() {
		new PGotPayReturn(this).execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 150;

	public int getType() {
		return 150;
	}

	public final static int OK = 0;
	public final static int HAS_GOT_RETURN = 1;
	public final static int HAS_NOT_RETURN = 2;

	public int err;
	public long getreturnroleid;
	public long totalpay;
	public long totalyuanbao;
	public long totalbindyuanbao;
	public long totalvipexp;

	public AGetPayReturn() {
	}

	public AGetPayReturn(int _err_, long _getreturnroleid_, long _totalpay_, long _totalyuanbao_, long _totalbindyuanbao_, long _totalvipexp_) {
		this.err = _err_;
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
		_os_.marshal(err);
		_os_.marshal(getreturnroleid);
		_os_.marshal(totalpay);
		_os_.marshal(totalyuanbao);
		_os_.marshal(totalbindyuanbao);
		_os_.marshal(totalvipexp);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err = _os_.unmarshal_int();
		getreturnroleid = _os_.unmarshal_long();
		totalpay = _os_.unmarshal_long();
		totalyuanbao = _os_.unmarshal_long();
		totalbindyuanbao = _os_.unmarshal_long();
		totalvipexp = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AGetPayReturn) {
			AGetPayReturn _o_ = (AGetPayReturn)_o1_;
			if (err != _o_.err) return false;
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
		_h_ += err;
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
		_sb_.append(err).append(",");
		_sb_.append(getreturnroleid).append(",");
		_sb_.append(totalpay).append(",");
		_sb_.append(totalyuanbao).append(",");
		_sb_.append(totalbindyuanbao).append(",");
		_sb_.append(totalvipexp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AGetPayReturn _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = err - _o_.err;
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

