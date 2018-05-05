
package lx.gs.skill.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEvolveSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEvolveSkill extends __SEvolveSkill__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557707;

	public int getType() {
		return 6557707;
	}

	public int oldskillid;
	public int newskillid;

	public SEvolveSkill() {
	}

	public SEvolveSkill(int _oldskillid_, int _newskillid_) {
		this.oldskillid = _oldskillid_;
		this.newskillid = _newskillid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(oldskillid);
		_os_.marshal(newskillid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		oldskillid = _os_.unmarshal_int();
		newskillid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEvolveSkill) {
			SEvolveSkill _o_ = (SEvolveSkill)_o1_;
			if (oldskillid != _o_.oldskillid) return false;
			if (newskillid != _o_.newskillid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += oldskillid;
		_h_ += newskillid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(oldskillid).append(",");
		_sb_.append(newskillid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEvolveSkill _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = oldskillid - _o_.oldskillid;
		if (0 != _c_) return _c_;
		_c_ = newskillid - _o_.newskillid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

