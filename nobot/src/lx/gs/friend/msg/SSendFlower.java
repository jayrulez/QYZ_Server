
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSendFlower__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSendFlower extends __SSendFlower__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578792;

	public int getType() {
		return 6578792;
	}

	public int sendtype; // 发送类型，给普通好友还是给偶像，通过enum值设定
	public long reveiverid; // 接受者的角色id
	public java.util.ArrayList<lx.gs.friend.msg.FlowerInfo> flowers;

	public SSendFlower() {
		flowers = new java.util.ArrayList<lx.gs.friend.msg.FlowerInfo>();
	}

	public SSendFlower(int _sendtype_, long _reveiverid_, java.util.ArrayList<lx.gs.friend.msg.FlowerInfo> _flowers_) {
		this.sendtype = _sendtype_;
		this.reveiverid = _reveiverid_;
		this.flowers = _flowers_;
	}

	public final boolean _validator_() {
		for (lx.gs.friend.msg.FlowerInfo _v_ : flowers)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(sendtype);
		_os_.marshal(reveiverid);
		_os_.compact_uint32(flowers.size());
		for (lx.gs.friend.msg.FlowerInfo _v_ : flowers) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		sendtype = _os_.unmarshal_int();
		reveiverid = _os_.unmarshal_long();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.friend.msg.FlowerInfo _v_ = new lx.gs.friend.msg.FlowerInfo();
			_v_.unmarshal(_os_);
			flowers.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSendFlower) {
			SSendFlower _o_ = (SSendFlower)_o1_;
			if (sendtype != _o_.sendtype) return false;
			if (reveiverid != _o_.reveiverid) return false;
			if (!flowers.equals(_o_.flowers)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += sendtype;
		_h_ += (int)reveiverid;
		_h_ += flowers.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(sendtype).append(",");
		_sb_.append(reveiverid).append(",");
		_sb_.append(flowers).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

