
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 鸿蒙争霸 3v3竞速副本 开始
*/
public class TimeRange implements Marshal {
	public java.lang.String start; // 格式 HH:mm:ss
	public java.lang.String end; // 格式 HH:mm:ss

	public TimeRange() {
		start = "";
		end = "";
	}

	public TimeRange(java.lang.String _start_, java.lang.String _end_) {
		this.start = _start_;
		this.end = _end_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(start, "UTF-16LE");
		_os_.marshal(end, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		start = _os_.unmarshal_String("UTF-16LE");
		end = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TimeRange) {
			TimeRange _o_ = (TimeRange)_o1_;
			if (!start.equals(_o_.start)) return false;
			if (!end.equals(_o_.end)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += start.hashCode();
		_h_ += end.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(start.length()).append(",");
		_sb_.append("T").append(end.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

