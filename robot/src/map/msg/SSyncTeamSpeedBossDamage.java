
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSyncTeamSpeedBossDamage__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSyncTeamSpeedBossDamage extends __SSyncTeamSpeedBossDamage__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6707949;

	public int getType() {
		return 6707949;
	}

	public java.util.HashMap<Integer,Float> bossdamage;

	public SSyncTeamSpeedBossDamage() {
		bossdamage = new java.util.HashMap<Integer,Float>();
	}

	public SSyncTeamSpeedBossDamage(java.util.HashMap<Integer,Float> _bossdamage_) {
		this.bossdamage = _bossdamage_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(bossdamage.size());
		for (java.util.Map.Entry<Integer, Float> _e_ : bossdamage.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			float _v_;
			_v_ = _os_.unmarshal_float();
			bossdamage.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSyncTeamSpeedBossDamage) {
			SSyncTeamSpeedBossDamage _o_ = (SSyncTeamSpeedBossDamage)_o1_;
			if (!bossdamage.equals(_o_.bossdamage)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bossdamage.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bossdamage).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

