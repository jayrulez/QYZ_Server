
package lx.gs.activity.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ActivityInfo implements Marshal , Comparable<ActivityInfo>{
	public int id;
	public byte isopen;
	public long opentime;
	public long closetime;
	public int status; // 当前活动状态，完成或者已领取，如果是未完成，客户端自己就能判断了

	public ActivityInfo() {
	}

	public ActivityInfo(int _id_, byte _isopen_, long _opentime_, long _closetime_, int _status_) {
		this.id = _id_;
		this.isopen = _isopen_;
		this.opentime = _opentime_;
		this.closetime = _closetime_;
		this.status = _status_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(isopen);
		_os_.marshal(opentime);
		_os_.marshal(closetime);
		_os_.marshal(status);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_int();
		isopen = _os_.unmarshal_byte();
		opentime = _os_.unmarshal_long();
		closetime = _os_.unmarshal_long();
		status = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ActivityInfo) {
			ActivityInfo _o_ = (ActivityInfo)_o1_;
			if (id != _o_.id) return false;
			if (isopen != _o_.isopen) return false;
			if (opentime != _o_.opentime) return false;
			if (closetime != _o_.closetime) return false;
			if (status != _o_.status) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += id;
		_h_ += (int)isopen;
		_h_ += (int)opentime;
		_h_ += (int)closetime;
		_h_ += status;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(isopen).append(",");
		_sb_.append(opentime).append(",");
		_sb_.append(closetime).append(",");
		_sb_.append(status).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(ActivityInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = id - _o_.id;
		if (0 != _c_) return _c_;
		_c_ = isopen - _o_.isopen;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(opentime - _o_.opentime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(closetime - _o_.closetime);
		if (0 != _c_) return _c_;
		_c_ = status - _o_.status;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

