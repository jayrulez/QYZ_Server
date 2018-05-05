
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCancelHideMinesNotify__ extends xio.Protocol { }

/** 取消隐藏矿物通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCancelHideMinesNotify extends __SCancelHideMinesNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577073;

	public int getType() {
		return 6577073;
	}

	public java.util.HashSet<Integer> unhideminse;

	public SCancelHideMinesNotify() {
		unhideminse = new java.util.HashSet<Integer>();
	}

	public SCancelHideMinesNotify(java.util.HashSet<Integer> _unhideminse_) {
		this.unhideminse = _unhideminse_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(unhideminse.size());
		for (Integer _v_ : unhideminse) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			unhideminse.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCancelHideMinesNotify) {
			SCancelHideMinesNotify _o_ = (SCancelHideMinesNotify)_o1_;
			if (!unhideminse.equals(_o_.unhideminse)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += unhideminse.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(unhideminse).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

