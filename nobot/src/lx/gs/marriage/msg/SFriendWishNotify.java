
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFriendWishNotify__ extends xio.Protocol { }

/** 朋友祝福后通知结婚双方得到了祝福
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFriendWishNotify extends __SFriendWishNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6580306;

	public int getType() {
		return 6580306;
	}

	public java.lang.String friendname; // 祝福的朋友的名字
	public map.msg.Bonus wishgift;

	public SFriendWishNotify() {
		friendname = "";
		wishgift = new map.msg.Bonus();
	}

	public SFriendWishNotify(java.lang.String _friendname_, map.msg.Bonus _wishgift_) {
		this.friendname = _friendname_;
		this.wishgift = _wishgift_;
	}

	public final boolean _validator_() {
		if (!wishgift._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(friendname, "UTF-16LE");
		_os_.marshal(wishgift);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		friendname = _os_.unmarshal_String("UTF-16LE");
		wishgift.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFriendWishNotify) {
			SFriendWishNotify _o_ = (SFriendWishNotify)_o1_;
			if (!friendname.equals(_o_.friendname)) return false;
			if (!wishgift.equals(_o_.wishgift)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += friendname.hashCode();
		_h_ += wishgift.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(friendname.length()).append(",");
		_sb_.append(wishgift).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

