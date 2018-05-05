
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __ConfirmUseActivationCode__ extends xio.Protocol { }

/** gs 确认使用激活码
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class ConfirmUseActivationCode extends __ConfirmUseActivationCode__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 146;

	public int getType() {
		return 146;
	}

	public java.lang.String code;
	public byte isconfirm;

	public ConfirmUseActivationCode() {
		code = "";
	}

	public ConfirmUseActivationCode(java.lang.String _code_, byte _isconfirm_) {
		this.code = _code_;
		this.isconfirm = _isconfirm_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(code, "UTF-16LE");
		_os_.marshal(isconfirm);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		code = _os_.unmarshal_String("UTF-16LE");
		isconfirm = _os_.unmarshal_byte();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ConfirmUseActivationCode) {
			ConfirmUseActivationCode _o_ = (ConfirmUseActivationCode)_o1_;
			if (!code.equals(_o_.code)) return false;
			if (isconfirm != _o_.isconfirm) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += code.hashCode();
		_h_ += (int)isconfirm;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(code.length()).append(",");
		_sb_.append(isconfirm).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

