
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEctypeDamageStatistic__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEctypeDamageStatistic extends __SEctypeDamageStatistic__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6713705;

	public int getType() {
		return 6713705;
	}

	public java.util.HashMap<Long,Long> agentdamages;

	public SEctypeDamageStatistic() {
		agentdamages = new java.util.HashMap<Long,Long>();
	}

	public SEctypeDamageStatistic(java.util.HashMap<Long,Long> _agentdamages_) {
		this.agentdamages = _agentdamages_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(agentdamages.size());
		for (java.util.Map.Entry<Long, Long> _e_ : agentdamages.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			long _v_;
			_v_ = _os_.unmarshal_long();
			agentdamages.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEctypeDamageStatistic) {
			SEctypeDamageStatistic _o_ = (SEctypeDamageStatistic)_o1_;
			if (!agentdamages.equals(_o_.agentdamages)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += agentdamages.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agentdamages).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

