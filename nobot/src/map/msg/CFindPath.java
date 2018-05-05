
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFindPath__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFindPath extends __CFindPath__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6703967;

	public int getType() {
		return 6703967;
	}

	public map.msg.Vector3 src;
	public map.msg.Vector3 dst;

	public CFindPath() {
		src = new map.msg.Vector3();
		dst = new map.msg.Vector3();
	}

	public CFindPath(map.msg.Vector3 _src_, map.msg.Vector3 _dst_) {
		this.src = _src_;
		this.dst = _dst_;
	}

	public final boolean _validator_() {
		if (!src._validator_()) return false;
		if (!dst._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(src);
		_os_.marshal(dst);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		src.unmarshal(_os_);
		dst.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFindPath) {
			CFindPath _o_ = (CFindPath)_o1_;
			if (!src.equals(_o_.src)) return false;
			if (!dst.equals(_o_.dst)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += src.hashCode();
		_h_ += dst.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(src).append(",");
		_sb_.append(dst).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

