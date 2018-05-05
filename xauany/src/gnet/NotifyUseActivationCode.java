
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __NotifyUseActivationCode__ extends xio.Protocol { }

/** auany - deliver - gs
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class NotifyUseActivationCode extends __NotifyUseActivationCode__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 145;

	public int getType() {
		return 145;
	}

	public gnet.ActivationCodeErr err;
	public long userid;
	public long roleid;
	public java.lang.String code;
	public int codetype;

	public NotifyUseActivationCode() {
		err = new gnet.ActivationCodeErr();
		code = "";
	}

	public NotifyUseActivationCode(gnet.ActivationCodeErr _err_, long _userid_, long _roleid_, java.lang.String _code_, int _codetype_) {
		this.err = _err_;
		this.userid = _userid_;
		this.roleid = _roleid_;
		this.code = _code_;
		this.codetype = _codetype_;
	}

	public final boolean _validator_() {
		if (!err._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(userid);
		_os_.marshal(roleid);
		_os_.marshal(code, "UTF-16LE");
		_os_.marshal(codetype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err.unmarshal(_os_);
		userid = _os_.unmarshal_long();
		roleid = _os_.unmarshal_long();
		code = _os_.unmarshal_String("UTF-16LE");
		codetype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NotifyUseActivationCode) {
			NotifyUseActivationCode _o_ = (NotifyUseActivationCode)_o1_;
			if (!err.equals(_o_.err)) return false;
			if (userid != _o_.userid) return false;
			if (roleid != _o_.roleid) return false;
			if (!code.equals(_o_.code)) return false;
			if (codetype != _o_.codetype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err.hashCode();
		_h_ += (int)userid;
		_h_ += (int)roleid;
		_h_ += code.hashCode();
		_h_ += codetype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(code.length()).append(",");
		_sb_.append(codetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

