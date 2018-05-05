
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFriendMarryNotify__ extends xio.Protocol { }

/** 通知双方所有好友结婚消息
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFriendMarryNotify extends __SFriendMarryNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570538;

	public int getType() {
		return 6570538;
	}

	public long proposeroleid;
	public java.lang.String proposename; // 求婚者的姓名
	public long beproposeroleid;
	public java.lang.String beproposedname; // 被求婚者的姓名
	public int proposetype; // 求婚类型

	public SFriendMarryNotify() {
		proposename = "";
		beproposedname = "";
	}

	public SFriendMarryNotify(long _proposeroleid_, java.lang.String _proposename_, long _beproposeroleid_, java.lang.String _beproposedname_, int _proposetype_) {
		this.proposeroleid = _proposeroleid_;
		this.proposename = _proposename_;
		this.beproposeroleid = _beproposeroleid_;
		this.beproposedname = _beproposedname_;
		this.proposetype = _proposetype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeroleid);
		_os_.marshal(proposename, "UTF-16LE");
		_os_.marshal(beproposeroleid);
		_os_.marshal(beproposedname, "UTF-16LE");
		_os_.marshal(proposetype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeroleid = _os_.unmarshal_long();
		proposename = _os_.unmarshal_String("UTF-16LE");
		beproposeroleid = _os_.unmarshal_long();
		beproposedname = _os_.unmarshal_String("UTF-16LE");
		proposetype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFriendMarryNotify) {
			SFriendMarryNotify _o_ = (SFriendMarryNotify)_o1_;
			if (proposeroleid != _o_.proposeroleid) return false;
			if (!proposename.equals(_o_.proposename)) return false;
			if (beproposeroleid != _o_.beproposeroleid) return false;
			if (!beproposedname.equals(_o_.beproposedname)) return false;
			if (proposetype != _o_.proposetype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeroleid;
		_h_ += proposename.hashCode();
		_h_ += (int)beproposeroleid;
		_h_ += beproposedname.hashCode();
		_h_ += proposetype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeroleid).append(",");
		_sb_.append("T").append(proposename.length()).append(",");
		_sb_.append(beproposeroleid).append(",");
		_sb_.append("T").append(beproposedname.length()).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

