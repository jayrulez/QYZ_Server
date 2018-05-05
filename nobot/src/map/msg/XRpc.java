
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XRpc__ extends xio.Protocol { }

/** 只在服务器内部使用的地图协议 开始

				以 X开头的是    xdb -> gamemap 的协议
				以 M开头的是	gamemap -> xdb 的协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XRpc extends __XRpc__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6709628;

	public int getType() {
		return 6709628;
	}

	public long ctxid;
	public int ptype;
	public com.goldhuman.Common.Octets argument;

	public XRpc() {
		argument = new com.goldhuman.Common.Octets();
	}

	public XRpc(long _ctxid_, int _ptype_, com.goldhuman.Common.Octets _argument_) {
		this.ctxid = _ctxid_;
		this.ptype = _ptype_;
		this.argument = _argument_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctxid);
		_os_.marshal(ptype);
		_os_.marshal(argument);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctxid = _os_.unmarshal_long();
		ptype = _os_.unmarshal_int();
		argument = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XRpc) {
			XRpc _o_ = (XRpc)_o1_;
			if (ctxid != _o_.ctxid) return false;
			if (ptype != _o_.ptype) return false;
			if (!argument.equals(_o_.argument)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)ctxid;
		_h_ += ptype;
		_h_ += argument.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctxid).append(",");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(argument.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

