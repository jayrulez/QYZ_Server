
package lx.gs.storynote.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SInfo extends __SInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568931;

	public int getType() {
		return 6568931;
	}

	public java.util.HashMap<Integer,lx.gs.storynote.msg.Chapter> chapters;

	public SInfo() {
		chapters = new java.util.HashMap<Integer,lx.gs.storynote.msg.Chapter>();
	}

	public SInfo(java.util.HashMap<Integer,lx.gs.storynote.msg.Chapter> _chapters_) {
		this.chapters = _chapters_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.storynote.msg.Chapter> _e_ : chapters.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(chapters.size());
		for (java.util.Map.Entry<Integer, lx.gs.storynote.msg.Chapter> _e_ : chapters.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.storynote.msg.Chapter _v_ = new lx.gs.storynote.msg.Chapter();
			_v_.unmarshal(_os_);
			chapters.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SInfo) {
			SInfo _o_ = (SInfo)_o1_;
			if (!chapters.equals(_o_.chapters)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += chapters.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chapters).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

