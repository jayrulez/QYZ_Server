
package gnet.link;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __LinkServerControl__ extends xio.Protocol { }

/** 正式服link启动时默认对client的端口未监听，由gs通知其开始监听，gs关闭时通知link关闭端口监听
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class LinkServerControl extends __LinkServerControl__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65544;

	public int getType() {
		return 65544;
	}

	public final static int E_START_LISTEN = 0;
	public final static int E_STOP_LISTEN = 1;

	public int ptype;

	public LinkServerControl() {
	}

	public LinkServerControl(int _ptype_) {
		this.ptype = _ptype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ptype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ptype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof LinkServerControl) {
			LinkServerControl _o_ = (LinkServerControl)_o1_;
			if (ptype != _o_.ptype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ptype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ptype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(LinkServerControl _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ptype - _o_.ptype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

