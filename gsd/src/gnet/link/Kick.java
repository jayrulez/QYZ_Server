
package gnet.link;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Kick__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Kick extends __Kick__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65539;

	public int getType() {
		return 65539;
	}

	public final static int E_PROTOCOL_UNKOWN = 1;
	public final static int E_MARSHAL_EXCEPTION = 2;
	public final static int E_PROTOCOL_EXCEPTION = 3;
	public final static int A_QUICK_CLOSE = 1;
	public final static int A_DELAY_CLOSE = 2;
	public final static int A_ACKICKOUT = 3;

	public int linksid;
	public int action;
	public int error;

	public Kick() {
	}

	public Kick(int _linksid_, int _action_, int _error_) {
		this.linksid = _linksid_;
		this.action = _action_;
		this.error = _error_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(linksid);
		_os_.marshal(action);
		_os_.marshal(error);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		linksid = _os_.unmarshal_int();
		action = _os_.unmarshal_int();
		error = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Kick) {
			Kick _o_ = (Kick)_o1_;
			if (linksid != _o_.linksid) return false;
			if (action != _o_.action) return false;
			if (error != _o_.error) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += linksid;
		_h_ += action;
		_h_ += error;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(linksid).append(",");
		_sb_.append(action).append(",");
		_sb_.append(error).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Kick _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = linksid - _o_.linksid;
		if (0 != _c_) return _c_;
		_c_ = action - _o_.action;
		if (0 != _c_) return _c_;
		_c_ = error - _o_.error;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

