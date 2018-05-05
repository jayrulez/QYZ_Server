
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSendFlowerNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSendFlowerNotify extends __SSendFlowerNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573071;

	public int getType() {
		return 6573071;
	}

	public lx.gs.friend.msg.FriendInfo senderinfo; // 赠送者的信息
	public java.util.ArrayList<lx.gs.friend.msg.FlowerInfo> flowers;

	public SSendFlowerNotify() {
		senderinfo = new lx.gs.friend.msg.FriendInfo();
		flowers = new java.util.ArrayList<lx.gs.friend.msg.FlowerInfo>();
	}

	public SSendFlowerNotify(lx.gs.friend.msg.FriendInfo _senderinfo_, java.util.ArrayList<lx.gs.friend.msg.FlowerInfo> _flowers_) {
		this.senderinfo = _senderinfo_;
		this.flowers = _flowers_;
	}

	public final boolean _validator_() {
		if (!senderinfo._validator_()) return false;
		for (lx.gs.friend.msg.FlowerInfo _v_ : flowers)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(senderinfo);
		_os_.compact_uint32(flowers.size());
		for (lx.gs.friend.msg.FlowerInfo _v_ : flowers) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		senderinfo.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.FlowerInfo _v_ = new lx.gs.friend.msg.FlowerInfo();
			_v_.unmarshal(_os_);
			flowers.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSendFlowerNotify) {
			SSendFlowerNotify _o_ = (SSendFlowerNotify)_o1_;
			if (!senderinfo.equals(_o_.senderinfo)) return false;
			if (!flowers.equals(_o_.flowers)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += senderinfo.hashCode();
		_h_ += flowers.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(senderinfo).append(",");
		_sb_.append(flowers).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

