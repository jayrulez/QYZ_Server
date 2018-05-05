
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CMove__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CMove extends __CMove__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684775;

	public int getType() {
		return 6684775;
	}

	public map.msg.Vector3 position;
	public map.msg.Vector3 target;

	public CMove() {
		position = new map.msg.Vector3();
		target = new map.msg.Vector3();
	}

	public CMove(map.msg.Vector3 _position_, map.msg.Vector3 _target_) {
		this.position = _position_;
		this.target = _target_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!target._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		_os_.marshal(target);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position.unmarshal(_os_);
		target.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CMove) {
			CMove _o_ = (CMove)_o1_;
			if (!position.equals(_o_.position)) return false;
			if (!target.equals(_o_.target)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position.hashCode();
		_h_ += target.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(target).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

