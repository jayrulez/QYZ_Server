
package lx.gs.notice.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNotice__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNotice extends __SNotice__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556600;

	public int getType() {
		return 6556600;
	}

	public java.util.ArrayList<lx.gs.notice.msg.Notice> notices;

	public SNotice() {
		notices = new java.util.ArrayList<lx.gs.notice.msg.Notice>();
	}

	public SNotice(java.util.ArrayList<lx.gs.notice.msg.Notice> _notices_) {
		this.notices = _notices_;
	}

	public final boolean _validator_() {
		for (lx.gs.notice.msg.Notice _v_ : notices)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(notices.size());
		for (lx.gs.notice.msg.Notice _v_ : notices) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.notice.msg.Notice _v_ = new lx.gs.notice.msg.Notice();
			_v_.unmarshal(_os_);
			notices.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNotice) {
			SNotice _o_ = (SNotice)_o1_;
			if (!notices.equals(_o_.notices)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += notices.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(notices).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

