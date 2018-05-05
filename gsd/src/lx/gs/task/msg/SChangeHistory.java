
package lx.gs.task.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeHistory__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeHistory extends __SChangeHistory__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554301;

	public int getType() {
		return 6554301;
	}

	public java.util.HashMap<Integer,Integer> changes;
	public java.util.ArrayList<Integer> removes;

	public SChangeHistory() {
		changes = new java.util.HashMap<Integer,Integer>();
		removes = new java.util.ArrayList<Integer>();
	}

	public SChangeHistory(java.util.HashMap<Integer,Integer> _changes_, java.util.ArrayList<Integer> _removes_) {
		this.changes = _changes_;
		this.removes = _removes_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(changes.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : changes.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(removes.size());
		for (Integer _v_ : removes) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			changes.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			removes.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeHistory) {
			SChangeHistory _o_ = (SChangeHistory)_o1_;
			if (!changes.equals(_o_.changes)) return false;
			if (!removes.equals(_o_.removes)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += changes.hashCode();
		_h_ += removes.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(changes).append(",");
		_sb_.append(removes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

