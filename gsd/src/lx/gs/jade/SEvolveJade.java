
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEvolveJade__ extends xio.Protocol { }

/** 玉佩进阶
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEvolveJade extends lx.gs.jade.__SEvolveJade__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571921;

	public int getType() {
		return 6571921;
	}

	public lx.gs.jade.Jade jade; // 玉佩等级

	public SEvolveJade() {
		jade = new lx.gs.jade.Jade();
	}

	public SEvolveJade(lx.gs.jade.Jade _jade_) {
		this.jade = _jade_;
	}

	public final boolean _validator_() {
		if (!jade._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(jade);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		jade.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEvolveJade) {
			SEvolveJade _o_ = (SEvolveJade)_o1_;
			if (!jade.equals(_o_.jade)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += jade.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(jade).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEvolveJade _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = jade.compareTo(_o_.jade);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

