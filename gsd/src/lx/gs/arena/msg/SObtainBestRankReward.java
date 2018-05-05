
package lx.gs.arena.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SObtainBestRankReward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SObtainBestRankReward extends __SObtainBestRankReward__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573831;

	public int getType() {
		return 6573831;
	}

	public int bestrank;
	public int lastrewardrank;

	public SObtainBestRankReward() {
	}

	public SObtainBestRankReward(int _bestrank_, int _lastrewardrank_) {
		this.bestrank = _bestrank_;
		this.lastrewardrank = _lastrewardrank_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bestrank);
		_os_.marshal(lastrewardrank);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bestrank = _os_.unmarshal_int();
		lastrewardrank = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SObtainBestRankReward) {
			SObtainBestRankReward _o_ = (SObtainBestRankReward)_o1_;
			if (bestrank != _o_.bestrank) return false;
            return lastrewardrank == _o_.lastrewardrank;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bestrank;
		_h_ += lastrewardrank;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bestrank).append(",");
		_sb_.append(lastrewardrank).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SObtainBestRankReward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bestrank - _o_.bestrank;
		if (0 != _c_) return _c_;
		_c_ = lastrewardrank - _o_.lastrewardrank;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

