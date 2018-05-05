
package lx.gs.bonus.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetOnlineGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetOnlineGift extends __SGetOnlineGift__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556117;

	public int getType() {
		return 6556117;
	}

	public java.util.HashMap<Integer,map.msg.Bonus> onlinegift;

	public SGetOnlineGift() {
		onlinegift = new java.util.HashMap<Integer,map.msg.Bonus>();
	}

	public SGetOnlineGift(java.util.HashMap<Integer,map.msg.Bonus> _onlinegift_) {
		this.onlinegift = _onlinegift_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, map.msg.Bonus> _e_ : onlinegift.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(onlinegift.size());
		for (java.util.Map.Entry<Integer, map.msg.Bonus> _e_ : onlinegift.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			map.msg.Bonus _v_ = new map.msg.Bonus();
			_v_.unmarshal(_os_);
			onlinegift.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetOnlineGift) {
			SGetOnlineGift _o_ = (SGetOnlineGift)_o1_;
			if (!onlinegift.equals(_o_.onlinegift)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += onlinegift.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(onlinegift).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

