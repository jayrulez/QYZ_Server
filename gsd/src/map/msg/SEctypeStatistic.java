
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEctypeStatistic__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEctypeStatistic extends __SEctypeStatistic__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6702843;

	public int getType() {
		return 6702843;
	}

	public java.util.ArrayList<map.msg.TeamStatistic> teams;

	public SEctypeStatistic() {
		teams = new java.util.ArrayList<map.msg.TeamStatistic>();
	}

	public SEctypeStatistic(java.util.ArrayList<map.msg.TeamStatistic> _teams_) {
		this.teams = _teams_;
	}

	public final boolean _validator_() {
		for (map.msg.TeamStatistic _v_ : teams)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(teams.size());
		for (map.msg.TeamStatistic _v_ : teams) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.TeamStatistic _v_ = new map.msg.TeamStatistic();
			_v_.unmarshal(_os_);
			teams.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEctypeStatistic) {
			SEctypeStatistic _o_ = (SEctypeStatistic)_o1_;
			if (!teams.equals(_o_.teams)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += teams.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(teams).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

