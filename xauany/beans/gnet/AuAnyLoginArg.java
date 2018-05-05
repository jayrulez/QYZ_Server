
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 登录
*/
public class AuAnyLoginArg implements Marshal {
	public com.goldhuman.Common.Octets user_identity;
	public com.goldhuman.Common.Octets token;
	public gnet.PlatType plattype; // 在混合平台时plattype用来区分具体平台
	public int loginip;

	public AuAnyLoginArg() {
		user_identity = new com.goldhuman.Common.Octets();
		token = new com.goldhuman.Common.Octets();
		plattype = new gnet.PlatType();
	}

	public AuAnyLoginArg(com.goldhuman.Common.Octets _user_identity_, com.goldhuman.Common.Octets _token_, gnet.PlatType _plattype_, int _loginip_) {
		this.user_identity = _user_identity_;
		this.token = _token_;
		this.plattype = _plattype_;
		this.loginip = _loginip_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(user_identity);
		_os_.marshal(token);
		_os_.marshal(plattype);
		_os_.marshal(loginip);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		user_identity = _os_.unmarshal_Octets();
		token = _os_.unmarshal_Octets();
		plattype.unmarshal(_os_);
		loginip = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AuAnyLoginArg) {
			AuAnyLoginArg _o_ = (AuAnyLoginArg)_o1_;
			if (!user_identity.equals(_o_.user_identity)) return false;
			if (!token.equals(_o_.token)) return false;
			if (!plattype.equals(_o_.plattype)) return false;
			if (loginip != _o_.loginip) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += user_identity.hashCode();
		_h_ += token.hashCode();
		_h_ += plattype.hashCode();
		_h_ += loginip;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("B").append(user_identity.size()).append(",");
		_sb_.append("B").append(token.size()).append(",");
		_sb_.append(plattype).append(",");
		_sb_.append(loginip).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

