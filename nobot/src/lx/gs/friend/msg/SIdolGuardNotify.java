
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SIdolGuardNotify__ extends xio.Protocol { }

/** 通知偶像守护者变化
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SIdolGuardNotify extends __SIdolGuardNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565154;

	public int getType() {
		return 6565154;
	}

	public long idolid;
	public long oldguardid;
	public long guardid;
	public java.lang.String guardname;
	public long guardtime;

	public SIdolGuardNotify() {
		guardname = "";
	}

	public SIdolGuardNotify(long _idolid_, long _oldguardid_, long _guardid_, java.lang.String _guardname_, long _guardtime_) {
		this.idolid = _idolid_;
		this.oldguardid = _oldguardid_;
		this.guardid = _guardid_;
		this.guardname = _guardname_;
		this.guardtime = _guardtime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(idolid);
		_os_.marshal(oldguardid);
		_os_.marshal(guardid);
		_os_.marshal(guardname, "UTF-16LE");
		_os_.marshal(guardtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		idolid = _os_.unmarshal_long();
		oldguardid = _os_.unmarshal_long();
		guardid = _os_.unmarshal_long();
		guardname = _os_.unmarshal_String("UTF-16LE");
		guardtime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SIdolGuardNotify) {
			SIdolGuardNotify _o_ = (SIdolGuardNotify)_o1_;
			if (idolid != _o_.idolid) return false;
			if (oldguardid != _o_.oldguardid) return false;
			if (guardid != _o_.guardid) return false;
			if (!guardname.equals(_o_.guardname)) return false;
			if (guardtime != _o_.guardtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)idolid;
		_h_ += (int)oldguardid;
		_h_ += (int)guardid;
		_h_ += guardname.hashCode();
		_h_ += (int)guardtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(idolid).append(",");
		_sb_.append(oldguardid).append(",");
		_sb_.append(guardid).append(",");
		_sb_.append("T").append(guardname.length()).append(",");
		_sb_.append(guardtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

