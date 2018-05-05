
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetWorldLines__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetWorldLines extends __SGetWorldLines__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582648;

	public int getType() {
		return 6582648;
	}

	public int worldid;
	public java.util.ArrayList<lx.gs.map.msg.LineInfo> lines;

	public SGetWorldLines() {
		lines = new java.util.ArrayList<lx.gs.map.msg.LineInfo>();
	}

	public SGetWorldLines(int _worldid_, java.util.ArrayList<lx.gs.map.msg.LineInfo> _lines_) {
		this.worldid = _worldid_;
		this.lines = _lines_;
	}

	public final boolean _validator_() {
		for (lx.gs.map.msg.LineInfo _v_ : lines)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(worldid);
		_os_.compact_uint32(lines.size());
		for (lx.gs.map.msg.LineInfo _v_ : lines) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		worldid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.map.msg.LineInfo _v_ = new lx.gs.map.msg.LineInfo();
			_v_.unmarshal(_os_);
			lines.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetWorldLines) {
			SGetWorldLines _o_ = (SGetWorldLines)_o1_;
			if (worldid != _o_.worldid) return false;
			if (!lines.equals(_o_.lines)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += worldid;
		_h_ += lines.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(worldid).append(",");
		_sb_.append(lines).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

