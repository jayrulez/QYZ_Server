
package lx.gs.mail.msg;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.mail.FMail;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDelMail__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDelMail extends __CDelMail__ {
	@Override
	protected void process() {
		new AProcedure<CDelMail>(this) {
			@Override
			protected boolean doProcess() throws Exception {			
				final SDelMail re = new SDelMail();
				FMail.delMails(roleid, delmailids);
				re.delmailids = delmailids;
				response(re);			
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6554402;

	public int getType() {
		return 6554402;
	}

	public java.util.ArrayList<Integer> delmailids;

	public CDelMail() {
		delmailids = new java.util.ArrayList<Integer>();
	}

	public CDelMail(java.util.ArrayList<Integer> _delmailids_) {
		this.delmailids = _delmailids_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(delmailids.size());
		for (Integer _v_ : delmailids) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			delmailids.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CDelMail) {
			CDelMail _o_ = (CDelMail)_o1_;
			if (!delmailids.equals(_o_.delmailids)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += delmailids.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(delmailids).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

