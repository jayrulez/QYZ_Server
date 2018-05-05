
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __ServicedRefs__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class ServicedRefs extends __ServicedRefs__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6703897;

	public int getType() {
		return 6703897;
	}

	public map.msg.Bonus bonus;
	public map.msg.Vector3 vector3;

	public ServicedRefs() {
		bonus = new map.msg.Bonus();
		vector3 = new map.msg.Vector3();
	}

	public ServicedRefs(map.msg.Bonus _bonus_, map.msg.Vector3 _vector3_) {
		this.bonus = _bonus_;
		this.vector3 = _vector3_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		if (!vector3._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bonus);
		_os_.marshal(vector3);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bonus.unmarshal(_os_);
		vector3.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ServicedRefs) {
			ServicedRefs _o_ = (ServicedRefs)_o1_;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!vector3.equals(_o_.vector3)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bonus.hashCode();
		_h_ += vector3.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bonus).append(",");
		_sb_.append(vector3).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

