
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class VerifyLoginActivationCodeRes implements Marshal , Comparable<VerifyLoginActivationCodeRes>{
	public gnet.ActivationCodeErr err;
	public long userid;

	public VerifyLoginActivationCodeRes() {
		err = new gnet.ActivationCodeErr();
	}

	public VerifyLoginActivationCodeRes(gnet.ActivationCodeErr _err_, long _userid_) {
		this.err = _err_;
		this.userid = _userid_;
	}

	public final boolean _validator_() {
		if (!err._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(err);
		_os_.marshal(userid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		err.unmarshal(_os_);
		userid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof VerifyLoginActivationCodeRes) {
			VerifyLoginActivationCodeRes _o_ = (VerifyLoginActivationCodeRes)_o1_;
			if (!err.equals(_o_.err)) return false;
			if (userid != _o_.userid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += err.hashCode();
		_h_ += (int)userid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(err).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(VerifyLoginActivationCodeRes _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = err.compareTo(_o_.err);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(userid - _o_.userid);
		if (0 != _c_) return _c_;
		return _c_;
	}

}

