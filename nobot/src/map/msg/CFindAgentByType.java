
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFindAgentByType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFindAgentByType extends __CFindAgentByType__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6715711;

	public int getType() {
		return 6715711;
	}

	public int agenttype; // 见cfg.fight.AgentType

	public CFindAgentByType() {
	}

	public CFindAgentByType(int _agenttype_) {
		this.agenttype = _agenttype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agenttype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agenttype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFindAgentByType) {
			CFindAgentByType _o_ = (CFindAgentByType)_o1_;
			if (agenttype != _o_.agenttype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += agenttype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agenttype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CFindAgentByType _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = agenttype - _o_.agenttype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

