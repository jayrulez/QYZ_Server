
package lx.gs.rank.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RankInfo implements Marshal , Comparable<RankInfo>{
	public int ranktype;
	public int snaprank;

	public RankInfo() {
	}

	public RankInfo(int _ranktype_, int _snaprank_) {
		this.ranktype = _ranktype_;
		this.snaprank = _snaprank_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ranktype);
		_os_.marshal(snaprank);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ranktype = _os_.unmarshal_int();
		snaprank = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RankInfo) {
			RankInfo _o_ = (RankInfo)_o1_;
			if (ranktype != _o_.ranktype) return false;
			if (snaprank != _o_.snaprank) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ranktype;
		_h_ += snaprank;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ranktype).append(",");
		_sb_.append(snaprank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(RankInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ranktype - _o_.ranktype;
		if (0 != _c_) return _c_;
		_c_ = snaprank - _o_.snaprank;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

