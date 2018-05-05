
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class IdolAwardClaim implements Marshal {
	public java.util.HashSet<Integer> claiminfo; // key为奖励id，value为领取状态，0为未领取，1为领取过

	public IdolAwardClaim() {
		claiminfo = new java.util.HashSet<Integer>();
	}

	public IdolAwardClaim(java.util.HashSet<Integer> _claiminfo_) {
		this.claiminfo = _claiminfo_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(claiminfo.size());
		for (Integer _v_ : claiminfo) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			claiminfo.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof IdolAwardClaim) {
			IdolAwardClaim _o_ = (IdolAwardClaim)_o1_;
			if (!claiminfo.equals(_o_.claiminfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += claiminfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(claiminfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

