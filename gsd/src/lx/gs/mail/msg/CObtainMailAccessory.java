
package lx.gs.mail.msg;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.mail.FMail;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CObtainMailAccessory__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CObtainMailAccessory extends __CObtainMailAccessory__ {
	@Override
	protected void process() {
		new AProcedure<CObtainMailAccessory>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final SObtainMailAccessory re = new SObtainMailAccessory();
				final ErrorCode ret = FMail.obtainMailAccessory(roleid, mailids, re.bonus);
				if(ret.err())
					return error(ret);
				re.mailids = mailids;
				response(re);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554405;

	public int getType() {
		return 6554405;
	}

	public java.util.ArrayList<Integer> mailids;

	public CObtainMailAccessory() {
		mailids = new java.util.ArrayList<Integer>();
	}

	public CObtainMailAccessory(java.util.ArrayList<Integer> _mailids_) {
		this.mailids = _mailids_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(mailids.size());
		for (Integer _v_ : mailids) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			mailids.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CObtainMailAccessory) {
			CObtainMailAccessory _o_ = (CObtainMailAccessory)_o1_;
			if (!mailids.equals(_o_.mailids)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mailids.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mailids).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

