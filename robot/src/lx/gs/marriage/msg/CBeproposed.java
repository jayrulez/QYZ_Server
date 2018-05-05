
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CBeproposed__ extends xio.Protocol { }

/** 被求婚者的答复信息,该结果要通知给求婚方
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CBeproposed extends __CBeproposed__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586200;

	public int getType() {
		return 6586200;
	}

	public long proposeroleid; // 求婚者的id
	public int proposeresult; // 0表示拒绝求婚，1表示同意求婚
	public int proposetype; // 求婚类型，0表示普通，1表示豪华
	public java.lang.String proposeoath; // 由于没有存储求婚宣言，这里再上传一次

	public CBeproposed() {
		proposeoath = "";
	}

	public CBeproposed(long _proposeroleid_, int _proposeresult_, int _proposetype_, java.lang.String _proposeoath_) {
		this.proposeroleid = _proposeroleid_;
		this.proposeresult = _proposeresult_;
		this.proposetype = _proposetype_;
		this.proposeoath = _proposeoath_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeroleid);
		_os_.marshal(proposeresult);
		_os_.marshal(proposetype);
		_os_.marshal(proposeoath, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeroleid = _os_.unmarshal_long();
		proposeresult = _os_.unmarshal_int();
		proposetype = _os_.unmarshal_int();
		proposeoath = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CBeproposed) {
			CBeproposed _o_ = (CBeproposed)_o1_;
			if (proposeroleid != _o_.proposeroleid) return false;
			if (proposeresult != _o_.proposeresult) return false;
			if (proposetype != _o_.proposetype) return false;
			if (!proposeoath.equals(_o_.proposeoath)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeroleid;
		_h_ += proposeresult;
		_h_ += proposetype;
		_h_ += proposeoath.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeroleid).append(",");
		_sb_.append(proposeresult).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append("T").append(proposeoath.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

