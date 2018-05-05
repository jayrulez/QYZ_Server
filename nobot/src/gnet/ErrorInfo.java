
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import robot.Ctx;
import xdb.Trace;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __ErrorInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class ErrorInfo extends __ErrorInfo__ {
	@Override
	protected void process() {
		Ctx ctx = (Ctx)getContext();
        Trace.error("account:{} userid:{} ErrorInfo:{}", ctx.account, ctx.userid, errcode.code);
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 102;

	public int getType() {
		return 102;
	}

	public gnet.ErrCode errcode;
	public com.goldhuman.Common.Octets info;

	public ErrorInfo() {
		errcode = new gnet.ErrCode();
		info = new com.goldhuman.Common.Octets();
	}

	public ErrorInfo(gnet.ErrCode _errcode_, com.goldhuman.Common.Octets _info_) {
		this.errcode = _errcode_;
		this.info = _info_;
	}

	public final boolean _validator_() {
		if (!errcode._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(info);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode.unmarshal(_os_);
		info = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ErrorInfo) {
			ErrorInfo _o_ = (ErrorInfo)_o1_;
			if (!errcode.equals(_o_.errcode)) return false;
			if (!info.equals(_o_.info)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode.hashCode();
		_h_ += info.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append("B").append(info.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

