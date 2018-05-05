
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class MultiStoryInfo implements Marshal , Comparable<MultiStoryInfo>{
	public int beststar;

	public MultiStoryInfo() {
	}

	public MultiStoryInfo(int _beststar_) {
		this.beststar = _beststar_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(beststar);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		beststar = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MultiStoryInfo) {
			MultiStoryInfo _o_ = (MultiStoryInfo)_o1_;
			if (beststar != _o_.beststar) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += beststar;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(beststar).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(MultiStoryInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = beststar - _o_.beststar;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

