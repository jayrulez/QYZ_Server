
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFindPath__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFindPath extends __SFindPath__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6708970;

	public int getType() {
		return 6708970;
	}

	public java.util.ArrayList<map.msg.Vector3> path;

	public SFindPath() {
		path = new java.util.ArrayList<map.msg.Vector3>();
	}

	public SFindPath(java.util.ArrayList<map.msg.Vector3> _path_) {
		this.path = _path_;
	}

	public final boolean _validator_() {
		for (map.msg.Vector3 _v_ : path)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(path.size());
		for (map.msg.Vector3 _v_ : path) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.Vector3 _v_ = new map.msg.Vector3();
			_v_.unmarshal(_os_);
			path.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFindPath) {
			SFindPath _o_ = (SFindPath)_o1_;
			if (!path.equals(_o_.path)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += path.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(path).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

