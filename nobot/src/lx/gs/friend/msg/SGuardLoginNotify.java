
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGuardLoginNotify__ extends xio.Protocol { }

/** 偶像守护者上线通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGuardLoginNotify extends __SGuardLoginNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584263;

	public int getType() {
		return 6584263;
	}

	public long idolid;
	public long guardid;
	public java.lang.String guardname;

	public SGuardLoginNotify() {
		guardname = "";
	}

	public SGuardLoginNotify(long _idolid_, long _guardid_, java.lang.String _guardname_) {
		this.idolid = _idolid_;
		this.guardid = _guardid_;
		this.guardname = _guardname_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(idolid);
		_os_.marshal(guardid);
		_os_.marshal(guardname, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		idolid = _os_.unmarshal_long();
		guardid = _os_.unmarshal_long();
		guardname = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGuardLoginNotify) {
			SGuardLoginNotify _o_ = (SGuardLoginNotify)_o1_;
			if (idolid != _o_.idolid) return false;
			if (guardid != _o_.guardid) return false;
			if (!guardname.equals(_o_.guardname)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)idolid;
		_h_ += (int)guardid;
		_h_ += guardname.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(idolid).append(",");
		_sb_.append(guardid).append(",");
		_sb_.append("T").append(guardname.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

