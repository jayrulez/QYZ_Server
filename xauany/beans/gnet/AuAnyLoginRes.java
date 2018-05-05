
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class AuAnyLoginRes implements Marshal , Comparable<AuAnyLoginRes>{
	public final static int ERR_SUCCEED = 0;
	public final static int ERR_INVALID = 1; // 失败，token/sessionid无效
	public final static int ERR_PLAT_EXCEPTION = 2; // 与平台相关的访问异常
	public final static int ERR_STORE = 3; // xdb存储异常
	public final static int ERR_NEED_ACTIVATE = 4; // 需要激活码激活才能登陆

	public int errcode;
	public long userid;

	public AuAnyLoginRes() {
		userid = -1;
	}

	public AuAnyLoginRes(int _errcode_, long _userid_) {
		this.errcode = _errcode_;
		this.userid = _userid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(userid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		userid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AuAnyLoginRes) {
			AuAnyLoginRes _o_ = (AuAnyLoginRes)_o1_;
			if (errcode != _o_.errcode) return false;
			if (userid != _o_.userid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += (int)userid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AuAnyLoginRes _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = errcode - _o_.errcode;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(userid - _o_.userid);
		if (0 != _c_) return _c_;
		return _c_;
	}

}

