
package lx.gs.activity.worldmonster.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __ExpMonsterNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class ExpMonsterNotify extends __ExpMonsterNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583127;

	public int getType() {
		return 6583127;
	}

	public final static int START = 1;
	public final static int END = 2;

	public int eventtype;
	public java.lang.String msg;

	public ExpMonsterNotify() {
		msg = "";
	}

	public ExpMonsterNotify(int _eventtype_, java.lang.String _msg_) {
		this.eventtype = _eventtype_;
		this.msg = _msg_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(eventtype);
		_os_.marshal(msg, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		eventtype = _os_.unmarshal_int();
		msg = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ExpMonsterNotify) {
			ExpMonsterNotify _o_ = (ExpMonsterNotify)_o1_;
			if (eventtype != _o_.eventtype) return false;
			if (!msg.equals(_o_.msg)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += eventtype;
		_h_ += msg.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(eventtype).append(",");
		_sb_.append("T").append(msg.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

