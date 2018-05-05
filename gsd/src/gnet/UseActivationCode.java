
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __UseActivationCode__ extends xio.Protocol { }

/** gs - deliver - auany
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class UseActivationCode extends __UseActivationCode__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 144;

	public int getType() {
		return 144;
	}

	public long userid;
	public long roleid;
	public java.lang.String code;

	public UseActivationCode() {
		code = "";
	}

	public UseActivationCode(long _userid_, long _roleid_, java.lang.String _code_) {
		this.userid = _userid_;
		this.roleid = _roleid_;
		this.code = _code_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(userid);
		_os_.marshal(roleid);
		_os_.marshal(code, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		userid = _os_.unmarshal_long();
		roleid = _os_.unmarshal_long();
		code = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof UseActivationCode) {
			UseActivationCode _o_ = (UseActivationCode)_o1_;
			if (userid != _o_.userid) return false;
			if (roleid != _o_.roleid) return false;
			if (!code.equals(_o_.code)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)userid;
		_h_ += (int)roleid;
		_h_ += code.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(code.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

