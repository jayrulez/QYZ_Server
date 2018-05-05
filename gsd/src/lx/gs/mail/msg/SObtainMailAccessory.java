
package lx.gs.mail.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SObtainMailAccessory__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SObtainMailAccessory extends __SObtainMailAccessory__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554406;

	public int getType() {
		return 6554406;
	}

	public java.util.ArrayList<Integer> mailids;
	public map.msg.Bonus bonus;

	public SObtainMailAccessory() {
		mailids = new java.util.ArrayList<Integer>();
		bonus = new map.msg.Bonus();
	}

	public SObtainMailAccessory(java.util.ArrayList<Integer> _mailids_, map.msg.Bonus _bonus_) {
		this.mailids = _mailids_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(mailids.size());
		for (Integer _v_ : mailids) {
			_os_.marshal(_v_);
		}
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			mailids.add(_v_);
		}
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SObtainMailAccessory) {
			SObtainMailAccessory _o_ = (SObtainMailAccessory)_o1_;
			if (!mailids.equals(_o_.mailids)) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mailids.hashCode();
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mailids).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

