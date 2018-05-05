
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ActivationCodeErr implements Marshal , Comparable<ActivationCodeErr>{
	public final static int ERR_SUCCESS = 0; // 成功
	public final static int ERR_FORMATE_INVALID = 1; // 激活码格式错误
	public final static int ERR_INVALID = 2; // 激活码无效
	public final static int ERR_TYPE_NOT_MATCH = 3; // 激活码类型不匹配
	public final static int ERR_CODE_IS_USED = 4; // 激活码已使用
	public final static int ERR_CODE_IS_EXPIRATED = 5; // 激活码已过期
	public final static int ERR_CODE_IS_NOT_OPEN = 6; // 激活码未到使用时间
	public final static int ERR_FUNCTION_IS_CLOSED = 7; // 激活码功能已关闭
	public final static int ERR_PLATFORM_NOT_MATCH = 8; // 激活码平台不匹配
	public final static int ERR_HAS_ALEADY_ACTIVATED = 9; // 已经使用过同一类型的激活码
	public final static int ERR_NETWORK = 10; // deliver和au通讯异常
	public final static int ERR_EXCEED_DAY_USENUM = 11; // 超出每日使用次数
	public final static int ERR_EXCEED_ALL_USENUM = 12; // 超出累计使用次数
	public final static int ERR_INTERNAL = 13; // 服务器内部错误
	public final static int ERR_LEVEL_TOO_LOWE = 15; // 等级太低,无法使用
	public final static int ERR_LEVEL_TOO_HIGH = 16; // 等级太高,无法使用

	public int code;

	public ActivationCodeErr() {
	}

	public ActivationCodeErr(int _code_) {
		this.code = _code_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(code);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		code = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActivationCodeErr) {
			ActivationCodeErr _o_ = (ActivationCodeErr)_o1_;
			if (code != _o_.code) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += code;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(code).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(ActivationCodeErr _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = code - _o_.code;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

