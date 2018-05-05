
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class VerifyLoginActivationCodeArg implements Marshal {
	public long userid;
	public com.goldhuman.Common.Octets code;

	public VerifyLoginActivationCodeArg() {
		code = new com.goldhuman.Common.Octets();
	}

	public VerifyLoginActivationCodeArg(long _userid_, com.goldhuman.Common.Octets _code_) {
		this.userid = _userid_;
		this.code = _code_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(userid);
		_os_.marshal(code);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		userid = _os_.unmarshal_long();
		code = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof VerifyLoginActivationCodeArg) {
			VerifyLoginActivationCodeArg _o_ = (VerifyLoginActivationCodeArg)_o1_;
			if (userid != _o_.userid) return false;
			if (!code.equals(_o_.code)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)userid;
		_h_ += code.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid).append(",");
		_sb_.append("B").append(code.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

