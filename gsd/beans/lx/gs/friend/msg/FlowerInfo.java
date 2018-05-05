
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 花的信息
*/
public class FlowerInfo implements Marshal , Comparable<FlowerInfo>{
	public int flowerid; // 花的Id
	public int flowernum; // 花的数量

	public FlowerInfo() {
	}

	public FlowerInfo(int _flowerid_, int _flowernum_) {
		this.flowerid = _flowerid_;
		this.flowernum = _flowernum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(flowerid);
		_os_.marshal(flowernum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		flowerid = _os_.unmarshal_int();
		flowernum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FlowerInfo) {
			FlowerInfo _o_ = (FlowerInfo)_o1_;
			if (flowerid != _o_.flowerid) return false;
			if (flowernum != _o_.flowernum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += flowerid;
		_h_ += flowernum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(flowerid).append(",");
		_sb_.append(flowernum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(FlowerInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = flowerid - _o_.flowerid;
		if (0 != _c_) return _c_;
		_c_ = flowernum - _o_.flowernum;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

