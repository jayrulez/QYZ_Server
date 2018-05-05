
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetConfigures__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetConfigures extends __SGetConfigures__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575283;

	public int getType() {
		return 6575283;
	}

	public java.util.HashMap<java.lang.String,java.lang.String> datas;

	public SGetConfigures() {
		datas = new java.util.HashMap<java.lang.String,java.lang.String>();
	}

	public SGetConfigures(java.util.HashMap<java.lang.String,java.lang.String> _datas_) {
		this.datas = _datas_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(datas.size());
		for (java.util.Map.Entry<java.lang.String, java.lang.String> _e_ : datas.entrySet()) {
			_os_.marshal(_e_.getKey(), "UTF-16LE");
			_os_.marshal(_e_.getValue(), "UTF-16LE");
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			java.lang.String _k_;
			_k_ = _os_.unmarshal_String("UTF-16LE");
			java.lang.String _v_;
			_v_ = _os_.unmarshal_String("UTF-16LE");
			datas.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetConfigures) {
			SGetConfigures _o_ = (SGetConfigures)_o1_;
			if (!datas.equals(_o_.datas)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += datas.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(datas).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

