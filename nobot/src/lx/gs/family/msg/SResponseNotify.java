
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SResponseNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SResponseNotify extends __SResponseNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570501;

	public int getType() {
		return 6570501;
	}

	public long responseroleid;
	public java.lang.String responserolename;
	public int result;

	public SResponseNotify() {
		responserolename = "";
	}

	public SResponseNotify(long _responseroleid_, java.lang.String _responserolename_, int _result_) {
		this.responseroleid = _responseroleid_;
		this.responserolename = _responserolename_;
		this.result = _result_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(responseroleid);
		_os_.marshal(responserolename, "UTF-16LE");
		_os_.marshal(result);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		responseroleid = _os_.unmarshal_long();
		responserolename = _os_.unmarshal_String("UTF-16LE");
		result = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SResponseNotify) {
			SResponseNotify _o_ = (SResponseNotify)_o1_;
			if (responseroleid != _o_.responseroleid) return false;
			if (!responserolename.equals(_o_.responserolename)) return false;
			if (result != _o_.result) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)responseroleid;
		_h_ += responserolename.hashCode();
		_h_ += result;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(responseroleid).append(",");
		_sb_.append("T").append(responserolename.length()).append(",");
		_sb_.append(result).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

