
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ClimbTowerInfo implements Marshal , Comparable<ClimbTowerInfo>{
	public int maxfloorid;
	public int costtime; // 秒数

	public ClimbTowerInfo() {
	}

	public ClimbTowerInfo(int _maxfloorid_, int _costtime_) {
		this.maxfloorid = _maxfloorid_;
		this.costtime = _costtime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(maxfloorid);
		_os_.marshal(costtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		maxfloorid = _os_.unmarshal_int();
		costtime = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ClimbTowerInfo) {
			ClimbTowerInfo _o_ = (ClimbTowerInfo)_o1_;
			if (maxfloorid != _o_.maxfloorid) return false;
			if (costtime != _o_.costtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += maxfloorid;
		_h_ += costtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(maxfloorid).append(",");
		_sb_.append(costtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(ClimbTowerInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = maxfloorid - _o_.maxfloorid;
		if (0 != _c_) return _c_;
		_c_ = costtime - _o_.costtime;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

