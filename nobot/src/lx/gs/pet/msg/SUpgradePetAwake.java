
package lx.gs.pet.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SUpgradePetAwake__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SUpgradePetAwake extends __SUpgradePetAwake__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572346;

	public int getType() {
		return 6572346;
	}

	public int modelid;
	public int awakelevel;

	public SUpgradePetAwake() {
	}

	public SUpgradePetAwake(int _modelid_, int _awakelevel_) {
		this.modelid = _modelid_;
		this.awakelevel = _awakelevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(awakelevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		awakelevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SUpgradePetAwake) {
			SUpgradePetAwake _o_ = (SUpgradePetAwake)_o1_;
			if (modelid != _o_.modelid) return false;
			if (awakelevel != _o_.awakelevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += awakelevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(awakelevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SUpgradePetAwake _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = awakelevel - _o_.awakelevel;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

