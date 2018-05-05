
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpdateDeclarationOrPublic__ extends xio.Protocol { }

/** 修改家族宣言或者公告信息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpdateDeclarationOrPublic extends __CUpdateDeclarationOrPublic__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580368;

	public int getType() {
		return 6580368;
	}

	public final static int UPDATE_DECLARATION = 1;
	public final static int UPDATE_PUBLIC = 2;

	public int updatetype; // 修改类型，0为修改公告，1为修改宣言
	public java.lang.String newtext; // 修改后的文字

	public CUpdateDeclarationOrPublic() {
		newtext = "";
	}

	public CUpdateDeclarationOrPublic(int _updatetype_, java.lang.String _newtext_) {
		this.updatetype = _updatetype_;
		this.newtext = _newtext_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(updatetype);
		_os_.marshal(newtext, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		updatetype = _os_.unmarshal_int();
		newtext = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUpdateDeclarationOrPublic) {
			CUpdateDeclarationOrPublic _o_ = (CUpdateDeclarationOrPublic)_o1_;
			if (updatetype != _o_.updatetype) return false;
			if (!newtext.equals(_o_.newtext)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += updatetype;
		_h_ += newtext.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(updatetype).append(",");
		_sb_.append("T").append(newtext.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

