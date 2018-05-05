
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CFriendWish__ extends xio.Protocol { }

/** 好友祝福
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CFriendWish extends __CFriendWish__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577940;

	public int getType() {
		return 6577940;
	}

	public long proposeroleid;
	public long beproposeroleid;
	public int proposetype;

	public CFriendWish() {
	}

	public CFriendWish(long _proposeroleid_, long _beproposeroleid_, int _proposetype_) {
		this.proposeroleid = _proposeroleid_;
		this.beproposeroleid = _beproposeroleid_;
		this.proposetype = _proposetype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(proposeroleid);
		_os_.marshal(beproposeroleid);
		_os_.marshal(proposetype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		proposeroleid = _os_.unmarshal_long();
		beproposeroleid = _os_.unmarshal_long();
		proposetype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CFriendWish) {
			CFriendWish _o_ = (CFriendWish)_o1_;
			if (proposeroleid != _o_.proposeroleid) return false;
			if (beproposeroleid != _o_.beproposeroleid) return false;
			if (proposetype != _o_.proposetype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)proposeroleid;
		_h_ += (int)beproposeroleid;
		_h_ += proposetype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(proposeroleid).append(",");
		_sb_.append(beproposeroleid).append(",");
		_sb_.append(proposetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CFriendWish _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(proposeroleid - _o_.proposeroleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(beproposeroleid - _o_.beproposeroleid);
		if (0 != _c_) return _c_;
		_c_ = proposetype - _o_.proposetype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

