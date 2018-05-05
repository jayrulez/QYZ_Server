
package lx.gs;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPing__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPing extends __SPing__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572349;

	public int getType() {
		return 6572349;
	}

	public long sendclienttime;
	public long sendservertime;
	public long recvclienttime;
	public long sendmessagecount;

	public SPing() {
	}

	public SPing(long _sendclienttime_, long _sendservertime_, long _recvclienttime_, long _sendmessagecount_) {
		this.sendclienttime = _sendclienttime_;
		this.sendservertime = _sendservertime_;
		this.recvclienttime = _recvclienttime_;
		this.sendmessagecount = _sendmessagecount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(sendclienttime);
		_os_.marshal(sendservertime);
		_os_.marshal(recvclienttime);
		_os_.marshal(sendmessagecount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		sendclienttime = _os_.unmarshal_long();
		sendservertime = _os_.unmarshal_long();
		recvclienttime = _os_.unmarshal_long();
		sendmessagecount = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPing) {
			SPing _o_ = (SPing)_o1_;
			if (sendclienttime != _o_.sendclienttime) return false;
			if (sendservertime != _o_.sendservertime) return false;
			if (recvclienttime != _o_.recvclienttime) return false;
			if (sendmessagecount != _o_.sendmessagecount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)sendclienttime;
		_h_ += (int)sendservertime;
		_h_ += (int)recvclienttime;
		_h_ += (int)sendmessagecount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sendclienttime).append(",");
		_sb_.append(sendservertime).append(",");
		_sb_.append(recvclienttime).append(",");
		_sb_.append(sendmessagecount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SPing _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(sendclienttime - _o_.sendclienttime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(sendservertime - _o_.sendservertime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(recvclienttime - _o_.recvclienttime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(sendmessagecount - _o_.sendmessagecount);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

