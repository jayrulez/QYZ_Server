
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SQuitFamilyNotify__ extends xio.Protocol { }

/** 退出家族通知，该通知发给家族所有人
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SQuitFamilyNotify extends __SQuitFamilyNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580009;

	public int getType() {
		return 6580009;
	}

	public long memberid; // 退出家族的角色id
	public java.lang.String membername; // 退出家族的角色名称

	public SQuitFamilyNotify() {
		membername = "";
	}

	public SQuitFamilyNotify(long _memberid_, java.lang.String _membername_) {
		this.memberid = _memberid_;
		this.membername = _membername_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(memberid);
		_os_.marshal(membername, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		memberid = _os_.unmarshal_long();
		membername = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SQuitFamilyNotify) {
			SQuitFamilyNotify _o_ = (SQuitFamilyNotify)_o1_;
			if (memberid != _o_.memberid) return false;
			if (!membername.equals(_o_.membername)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)memberid;
		_h_ += membername.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(memberid).append(",");
		_sb_.append("T").append(membername.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

