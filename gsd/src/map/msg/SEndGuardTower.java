
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndGuardTower__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndGuardTower extends __SEndGuardTower__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6702857;

	public int getType() {
		return 6702857;
	}

	public int errcode;
	public map.msg.Bonus bonus;
	public map.msg.Bonus maxkill;

	public SEndGuardTower() {
		bonus = new map.msg.Bonus();
		maxkill = new map.msg.Bonus();
	}

	public SEndGuardTower(int _errcode_, map.msg.Bonus _bonus_, map.msg.Bonus _maxkill_) {
		this.errcode = _errcode_;
		this.bonus = _bonus_;
		this.maxkill = _maxkill_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		if (!maxkill._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(bonus);
		_os_.marshal(maxkill);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		maxkill.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndGuardTower) {
			SEndGuardTower _o_ = (SEndGuardTower)_o1_;
			if (errcode != _o_.errcode) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!maxkill.equals(_o_.maxkill)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += bonus.hashCode();
		_h_ += maxkill.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(maxkill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

