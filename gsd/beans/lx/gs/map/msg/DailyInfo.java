
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class DailyInfo implements Marshal , Comparable<DailyInfo>{
	public int maxvalue; // 日常副本里最好成绩

	public DailyInfo() {
	}

	public DailyInfo(int _maxvalue_) {
		this.maxvalue = _maxvalue_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(maxvalue);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		maxvalue = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof DailyInfo) {
			DailyInfo _o_ = (DailyInfo)_o1_;
			if (maxvalue != _o_.maxvalue) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += maxvalue;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(maxvalue).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(DailyInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = maxvalue - _o_.maxvalue;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

