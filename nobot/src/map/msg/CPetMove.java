
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPetMove__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPetMove extends __CPetMove__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6707676;

	public int getType() {
		return 6707676;
	}

	public long petid;
	public map.msg.Vector3 position;
	public map.msg.Vector3 target;

	public CPetMove() {
		position = new map.msg.Vector3();
		target = new map.msg.Vector3();
	}

	public CPetMove(long _petid_, map.msg.Vector3 _position_, map.msg.Vector3 _target_) {
		this.petid = _petid_;
		this.position = _position_;
		this.target = _target_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!target._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petid);
		_os_.marshal(position);
		_os_.marshal(target);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petid = _os_.unmarshal_long();
		position.unmarshal(_os_);
		target.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPetMove) {
			CPetMove _o_ = (CPetMove)_o1_;
			if (petid != _o_.petid) return false;
			if (!position.equals(_o_.position)) return false;
			if (!target.equals(_o_.target)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)petid;
		_h_ += position.hashCode();
		_h_ += target.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(target).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

