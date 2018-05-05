
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBedivorcedNotify__ extends xio.Protocol { }

/** 使用休书后通知被离婚者
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBedivorcedNotify extends __SBedivorcedNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576040;

	public int getType() {
		return 6576040;
	}

	public long divorceroleid; // 发起离婚的id
	public java.lang.String divorcerolename; // 发起离婚的姓名
	public long divorcetime; // 离婚的时间
	public java.lang.String bookcontent; // 休书内容

	public SBedivorcedNotify() {
		divorcerolename = "";
		bookcontent = "";
	}

	public SBedivorcedNotify(long _divorceroleid_, java.lang.String _divorcerolename_, long _divorcetime_, java.lang.String _bookcontent_) {
		this.divorceroleid = _divorceroleid_;
		this.divorcerolename = _divorcerolename_;
		this.divorcetime = _divorcetime_;
		this.bookcontent = _bookcontent_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(divorceroleid);
		_os_.marshal(divorcerolename, "UTF-16LE");
		_os_.marshal(divorcetime);
		_os_.marshal(bookcontent, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		divorceroleid = _os_.unmarshal_long();
		divorcerolename = _os_.unmarshal_String("UTF-16LE");
		divorcetime = _os_.unmarshal_long();
		bookcontent = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBedivorcedNotify) {
			SBedivorcedNotify _o_ = (SBedivorcedNotify)_o1_;
			if (divorceroleid != _o_.divorceroleid) return false;
			if (!divorcerolename.equals(_o_.divorcerolename)) return false;
			if (divorcetime != _o_.divorcetime) return false;
			if (!bookcontent.equals(_o_.bookcontent)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)divorceroleid;
		_h_ += divorcerolename.hashCode();
		_h_ += (int)divorcetime;
		_h_ += bookcontent.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(divorceroleid).append(",");
		_sb_.append("T").append(divorcerolename.length()).append(",");
		_sb_.append(divorcetime).append(",");
		_sb_.append("T").append(bookcontent.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

