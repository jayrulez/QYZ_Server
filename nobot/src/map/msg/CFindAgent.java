
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFindAgent__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFindAgent extends __CFindAgent__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684804;

	public int getType() {
		return 6684804;
	}

	public int agenttemplateid;

	public CFindAgent() {
	}

	public CFindAgent(int _agenttemplateid_) {
		this.agenttemplateid = _agenttemplateid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agenttemplateid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agenttemplateid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFindAgent) {
			CFindAgent _o_ = (CFindAgent)_o1_;
			if (agenttemplateid != _o_.agenttemplateid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += agenttemplateid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agenttemplateid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CFindAgent _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = agenttemplateid - _o_.agenttemplateid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

