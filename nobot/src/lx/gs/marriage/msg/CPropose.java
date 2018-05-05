
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPropose__ extends xio.Protocol { }

/** 开始求婚
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPropose extends __CPropose__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581170;

	public int getType() {
		return 6581170;
	}

	public long beproposedroleid; // 被求婚的id
	public java.lang.String proposeoath; // 求婚宣言，只有豪华彩礼才能自定义誓词

	public CPropose() {
		proposeoath = "";
	}

	public CPropose(long _beproposedroleid_, java.lang.String _proposeoath_) {
		this.beproposedroleid = _beproposedroleid_;
		this.proposeoath = _proposeoath_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(beproposedroleid);
		_os_.marshal(proposeoath, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		beproposedroleid = _os_.unmarshal_long();
		proposeoath = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPropose) {
			CPropose _o_ = (CPropose)_o1_;
			if (beproposedroleid != _o_.beproposedroleid) return false;
			if (!proposeoath.equals(_o_.proposeoath)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)beproposedroleid;
		_h_ += proposeoath.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(beproposedroleid).append(",");
		_sb_.append("T").append(proposeoath.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

