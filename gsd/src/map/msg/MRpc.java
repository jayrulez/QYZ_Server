
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MRpc__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MRpc extends __MRpc__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6695824;

	public int getType() {
		return 6695824;
	}

	public long ctxid;
	public int ptype;
	public com.goldhuman.Common.Octets result;

	public MRpc() {
		result = new com.goldhuman.Common.Octets();
	}

	public MRpc(long _ctxid_, int _ptype_, com.goldhuman.Common.Octets _result_) {
		this.ctxid = _ctxid_;
		this.ptype = _ptype_;
		this.result = _result_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctxid);
		_os_.marshal(ptype);
		_os_.marshal(result);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctxid = _os_.unmarshal_long();
		ptype = _os_.unmarshal_int();
		result = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MRpc) {
			MRpc _o_ = (MRpc)_o1_;
			if (ctxid != _o_.ctxid) return false;
			if (ptype != _o_.ptype) return false;
			if (!result.equals(_o_.result)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)ctxid;
		_h_ += ptype;
		_h_ += result.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctxid).append(",");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(result.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

