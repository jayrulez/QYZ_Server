
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SRejectMMNotify__ extends xio.Protocol { }

/** 别人拒绝我的脉脉申请的通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SRejectMMNotify extends __SRejectMMNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583789;

	public int getType() {
		return 6583789;
	}

	public int mmtype;
	public long roleid;
	public java.lang.String rolename;

	public SRejectMMNotify() {
		rolename = "";
	}

	public SRejectMMNotify(int _mmtype_, long _roleid_, java.lang.String _rolename_) {
		this.mmtype = _mmtype_;
		this.roleid = _roleid_;
		this.rolename = _rolename_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mmtype);
		_os_.marshal(roleid);
		_os_.marshal(rolename, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mmtype = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		rolename = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SRejectMMNotify) {
			SRejectMMNotify _o_ = (SRejectMMNotify)_o1_;
			if (mmtype != _o_.mmtype) return false;
			if (roleid != _o_.roleid) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mmtype;
		_h_ += (int)roleid;
		_h_ += rolename.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mmtype).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

