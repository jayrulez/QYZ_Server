
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBeproposed__ extends xio.Protocol { }

/** 求婚者开始求婚后，服务器通知被求婚者有人向你求婚了
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBeproposed extends __SBeproposed__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574784;

	public int getType() {
		return 6574784;
	}

	public long proposeroleid; // 求婚者的id
	public java.lang.String proposerolename; // 求婚者的姓名
	public int proposetype; // 求婚类型，0表示普通，1表示豪华
	public java.lang.String proposeoath; // 求婚宣言

	public SBeproposed() {
		proposerolename = "";
		proposeoath = "";
	}

	public SBeproposed(long _proposeroleid_, java.lang.String _proposerolename_, int _proposetype_, java.lang.String _proposeoath_) {
		this.proposeroleid = _proposeroleid_;
		this.proposerolename = _proposerolename_;
		this.proposetype = _proposetype_;
		this.proposeoath = _proposeoath_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeroleid);
		_os_.marshal(proposerolename, "UTF-16LE");
		_os_.marshal(proposetype);
		_os_.marshal(proposeoath, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeroleid = _os_.unmarshal_long();
		proposerolename = _os_.unmarshal_String("UTF-16LE");
		proposetype = _os_.unmarshal_int();
		proposeoath = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBeproposed) {
			SBeproposed _o_ = (SBeproposed)_o1_;
			if (proposeroleid != _o_.proposeroleid) return false;
			if (!proposerolename.equals(_o_.proposerolename)) return false;
			if (proposetype != _o_.proposetype) return false;
			if (!proposeoath.equals(_o_.proposeoath)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeroleid;
		_h_ += proposerolename.hashCode();
		_h_ += proposetype;
		_h_ += proposeoath.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeroleid).append(",");
		_sb_.append("T").append(proposerolename.length()).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append("T").append(proposeoath.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

