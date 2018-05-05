
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDivorceWithBook__ extends xio.Protocol { }

/** 使用休书与对象离婚
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDivorceWithBook extends __CDivorceWithBook__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6582102;

	public int getType() {
		return 6582102;
	}

	public long bedivorceroleid; // 被离婚对象的id
	public java.lang.String bookcontent; // 休书内容

	public CDivorceWithBook() {
		bookcontent = "";
	}

	public CDivorceWithBook(long _bedivorceroleid_, java.lang.String _bookcontent_) {
		this.bedivorceroleid = _bedivorceroleid_;
		this.bookcontent = _bookcontent_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bedivorceroleid);
		_os_.marshal(bookcontent, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bedivorceroleid = _os_.unmarshal_long();
		bookcontent = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CDivorceWithBook) {
			CDivorceWithBook _o_ = (CDivorceWithBook)_o1_;
			if (bedivorceroleid != _o_.bedivorceroleid) return false;
			if (!bookcontent.equals(_o_.bookcontent)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)bedivorceroleid;
		_h_ += bookcontent.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bedivorceroleid).append(",");
		_sb_.append("T").append(bookcontent.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

