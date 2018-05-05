
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBedivorceWithDiscuss__ extends xio.Protocol { }

/** 服务器通知被离婚者
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBedivorceWithDiscuss extends __SBedivorceWithDiscuss__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568518;

	public int getType() {
		return 6568518;
	}

	public long divorceroleid; // 发起离婚的id

	public SBedivorceWithDiscuss() {
	}

	public SBedivorceWithDiscuss(long _divorceroleid_) {
		this.divorceroleid = _divorceroleid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(divorceroleid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		divorceroleid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBedivorceWithDiscuss) {
			SBedivorceWithDiscuss _o_ = (SBedivorceWithDiscuss)_o1_;
			if (divorceroleid != _o_.divorceroleid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)divorceroleid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(divorceroleid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SBedivorceWithDiscuss _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(divorceroleid - _o_.divorceroleid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

