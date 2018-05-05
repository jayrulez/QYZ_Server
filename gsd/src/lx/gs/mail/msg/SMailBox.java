
package lx.gs.mail.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SMailBox__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SMailBox extends __SMailBox__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554400;

	public int getType() {
		return 6554400;
	}

	public java.util.ArrayList<lx.gs.mail.msg.Mail> mails;

	public SMailBox() {
		mails = new java.util.ArrayList<lx.gs.mail.msg.Mail>();
	}

	public SMailBox(java.util.ArrayList<lx.gs.mail.msg.Mail> _mails_) {
		this.mails = _mails_;
	}

	public final boolean _validator_() {
		for (lx.gs.mail.msg.Mail _v_ : mails)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(mails.size());
		for (lx.gs.mail.msg.Mail _v_ : mails) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.mail.msg.Mail _v_ = new lx.gs.mail.msg.Mail();
			_v_.unmarshal(_os_);
			mails.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SMailBox) {
			SMailBox _o_ = (SMailBox)_o1_;
			if (!mails.equals(_o_.mails)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mails.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mails).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

