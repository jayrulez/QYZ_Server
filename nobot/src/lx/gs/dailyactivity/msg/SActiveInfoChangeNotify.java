
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SActiveInfoChangeNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SActiveInfoChangeNotify extends __SActiveInfoChangeNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574271;

	public int getType() {
		return 6574271;
	}

	public int scores;
	public java.util.HashMap<Integer,Integer> changeactivetimes;

	public SActiveInfoChangeNotify() {
		changeactivetimes = new java.util.HashMap<Integer,Integer>();
	}

	public SActiveInfoChangeNotify(int _scores_, java.util.HashMap<Integer,Integer> _changeactivetimes_) {
		this.scores = _scores_;
		this.changeactivetimes = _changeactivetimes_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(scores);
		_os_.compact_uint32(changeactivetimes.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : changeactivetimes.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		scores = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			changeactivetimes.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SActiveInfoChangeNotify) {
			SActiveInfoChangeNotify _o_ = (SActiveInfoChangeNotify)_o1_;
			if (scores != _o_.scores) return false;
			if (!changeactivetimes.equals(_o_.changeactivetimes)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += scores;
		_h_ += changeactivetimes.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(scores).append(",");
		_sb_.append(changeactivetimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

