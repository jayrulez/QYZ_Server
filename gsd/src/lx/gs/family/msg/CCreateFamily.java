
package lx.gs.family.msg;

import lx.gs.family.PCreateFamily;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCreateFamily__ extends xio.Protocol { }

/** 创建家族
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCreateFamily extends __CCreateFamily__ {
	@Override
	protected void process() {
		new PCreateFamily(this).validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581106;

	public int getType() {
		return 6581106;
	}

	public java.lang.String familyname; // 家族名称，需要进行敏感词匹配

	public CCreateFamily() {
		familyname = "";
	}

	public CCreateFamily(java.lang.String _familyname_) {
		this.familyname = _familyname_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyname, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyname = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CCreateFamily) {
			CCreateFamily _o_ = (CCreateFamily)_o1_;
			if (!familyname.equals(_o_.familyname)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += familyname.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(familyname.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

