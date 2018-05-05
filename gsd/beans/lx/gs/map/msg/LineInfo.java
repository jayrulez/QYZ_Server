
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 地图分线相关协议　
*/
public class LineInfo implements Marshal , Comparable<LineInfo>{
	public int lineid;
	public int rolenum;

	public LineInfo() {
	}

	public LineInfo(int _lineid_, int _rolenum_) {
		this.lineid = _lineid_;
		this.rolenum = _rolenum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(lineid);
		_os_.marshal(rolenum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		lineid = _os_.unmarshal_int();
		rolenum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LineInfo) {
			LineInfo _o_ = (LineInfo)_o1_;
			if (lineid != _o_.lineid) return false;
			if (rolenum != _o_.rolenum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += lineid;
		_h_ += rolenum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lineid).append(",");
		_sb_.append(rolenum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(LineInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = lineid - _o_.lineid;
		if (0 != _c_) return _c_;
		_c_ = rolenum - _o_.rolenum;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

