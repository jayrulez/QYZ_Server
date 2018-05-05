
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBekillByOther__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBekillByOther extends __SBekillByOther__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579082;

	public int getType() {
		return 6579082;
	}

	public java.lang.String attackername;
	public long defencer;

	public SBekillByOther() {
		attackername = "";
	}

	public SBekillByOther(java.lang.String _attackername_, long _defencer_) {
		this.attackername = _attackername_;
		this.defencer = _defencer_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(attackername, "UTF-16LE");
		_os_.marshal(defencer);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		attackername = _os_.unmarshal_String("UTF-16LE");
		defencer = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBekillByOther) {
			SBekillByOther _o_ = (SBekillByOther)_o1_;
			if (!attackername.equals(_o_.attackername)) return false;
			if (defencer != _o_.defencer) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += attackername.hashCode();
		_h_ += (int)defencer;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(attackername.length()).append(",");
		_sb_.append(defencer).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

