
package lx.gs;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gnet.link.Onlines;
import gnet.link.Role;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPing__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPing extends __CPing__ {
	@Override
	protected void process() {
        final Role role = Onlines.getInstance().find(this);
        if(role != null) {
            role.onPing(this);
        }
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571796;

	public int getType() {
		return 6571796;
	}

	public long sendclienttime;
	public long receivedmessagecount;

	public CPing() {
	}

	public CPing(long _sendclienttime_, long _receivedmessagecount_) {
		this.sendclienttime = _sendclienttime_;
		this.receivedmessagecount = _receivedmessagecount_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(sendclienttime);
		_os_.marshal(receivedmessagecount);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		sendclienttime = _os_.unmarshal_long();
		receivedmessagecount = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPing) {
			CPing _o_ = (CPing)_o1_;
			if (sendclienttime != _o_.sendclienttime) return false;
			if (receivedmessagecount != _o_.receivedmessagecount) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)sendclienttime;
		_h_ += (int)receivedmessagecount;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sendclienttime).append(",");
		_sb_.append(receivedmessagecount).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CPing _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(sendclienttime - _o_.sendclienttime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(receivedmessagecount - _o_.receivedmessagecount);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

