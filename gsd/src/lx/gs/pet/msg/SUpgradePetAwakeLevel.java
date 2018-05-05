
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUpgradePetAwakeLevel__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUpgradePetAwakeLevel extends __SUpgradePetAwakeLevel__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564962;

	public int getType() {
		return 6564962;
	}

	public long petid;
	public int awakelevel;

	public SUpgradePetAwakeLevel() {
	}

	public SUpgradePetAwakeLevel(long _petid_, int _awakelevel_) {
		this.petid = _petid_;
		this.awakelevel = _awakelevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petid);
		_os_.marshal(awakelevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petid = _os_.unmarshal_long();
		awakelevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUpgradePetAwakeLevel) {
			SUpgradePetAwakeLevel _o_ = (SUpgradePetAwakeLevel)_o1_;
			if (petid != _o_.petid) return false;
            return awakelevel == _o_.awakelevel;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)petid;
		_h_ += awakelevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petid).append(",");
		_sb_.append(awakelevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUpgradePetAwakeLevel _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(petid - _o_.petid);
		if (0 != _c_) return _c_;
		_c_ = awakelevel - _o_.awakelevel;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

