
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAddNewBranchTaskNotify__ extends xio.Protocol { }

/** 新增的支线
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAddNewBranchTaskNotify extends __SAddNewBranchTaskNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577009;

	public int getType() {
		return 6577009;
	}

	public java.util.ArrayList<Integer> newtask;

	public SAddNewBranchTaskNotify() {
		newtask = new java.util.ArrayList<Integer>();
	}

	public SAddNewBranchTaskNotify(java.util.ArrayList<Integer> _newtask_) {
		this.newtask = _newtask_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(newtask.size());
		for (Integer _v_ : newtask) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			newtask.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAddNewBranchTaskNotify) {
			SAddNewBranchTaskNotify _o_ = (SAddNewBranchTaskNotify)_o1_;
			if (!newtask.equals(_o_.newtask)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += newtask.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(newtask).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

