
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CStop__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CStop extends __CStop__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684782;

	public int getType() {
		return 6684782;
	}

	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;

	public CStop() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public CStop(map.msg.Vector3 _position_, map.msg.Vector3 _orient_) {
		this.position = _position_;
		this.orient = _orient_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		_os_.marshal(orient);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CStop) {
			CStop _o_ = (CStop)_o1_;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

