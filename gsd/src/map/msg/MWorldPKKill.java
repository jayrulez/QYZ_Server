
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MWorldPKKill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MWorldPKKill extends __MWorldPKKill__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6695256;

	public int getType() {
		return 6695256;
	}

	public long attacker;
	public long defencer;

	public MWorldPKKill() {
	}

	public MWorldPKKill(long _attacker_, long _defencer_) {
		this.attacker = _attacker_;
		this.defencer = _defencer_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(attacker);
		_os_.marshal(defencer);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		attacker = _os_.unmarshal_long();
		defencer = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MWorldPKKill) {
			MWorldPKKill _o_ = (MWorldPKKill)_o1_;
			if (attacker != _o_.attacker) return false;
			if (defencer != _o_.defencer) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)attacker;
		_h_ += (int)defencer;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(attacker).append(",");
		_sb_.append(defencer).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(MWorldPKKill _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(attacker - _o_.attacker);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(defencer - _o_.defencer);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

