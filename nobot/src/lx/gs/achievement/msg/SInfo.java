
package lx.gs.achievement.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInfo extends __SInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6569195;

	public int getType() {
		return 6569195;
	}

	public java.util.HashMap<Integer,Integer> achievementstates; // cfg.achievement.State
	public java.util.HashMap<Integer,Long> counters;

	public SInfo() {
		achievementstates = new java.util.HashMap<Integer,Integer>();
		counters = new java.util.HashMap<Integer,Long>();
	}

	public SInfo(java.util.HashMap<Integer,Integer> _achievementstates_, java.util.HashMap<Integer,Long> _counters_) {
		this.achievementstates = _achievementstates_;
		this.counters = _counters_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(achievementstates.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : achievementstates.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(counters.size());
		for (java.util.Map.Entry<Integer, Long> _e_ : counters.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			achievementstates.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			long _v_;
			_v_ = _os_.unmarshal_long();
			counters.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInfo) {
			SInfo _o_ = (SInfo)_o1_;
			if (!achievementstates.equals(_o_.achievementstates)) return false;
			if (!counters.equals(_o_.counters)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += achievementstates.hashCode();
		_h_ += counters.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(achievementstates).append(",");
		_sb_.append(counters).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

