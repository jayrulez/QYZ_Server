
package lx.gs.pickcard.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPickCardByType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPickCardByType extends __SPickCardByType__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568736;

	public int getType() {
		return 6568736;
	}

	public int picktype;
	public java.util.ArrayList<lx.gs.pickcard.msg.PickBonusInfo> pickbonus;

	public SPickCardByType() {
		pickbonus = new java.util.ArrayList<lx.gs.pickcard.msg.PickBonusInfo>();
	}

	public SPickCardByType(int _picktype_, java.util.ArrayList<lx.gs.pickcard.msg.PickBonusInfo> _pickbonus_) {
		this.picktype = _picktype_;
		this.pickbonus = _pickbonus_;
	}

	public final boolean _validator_() {
		for (lx.gs.pickcard.msg.PickBonusInfo _v_ : pickbonus)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(picktype);
		_os_.compact_uint32(pickbonus.size());
		for (lx.gs.pickcard.msg.PickBonusInfo _v_ : pickbonus) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		picktype = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.pickcard.msg.PickBonusInfo _v_ = new lx.gs.pickcard.msg.PickBonusInfo();
			_v_.unmarshal(_os_);
			pickbonus.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPickCardByType) {
			SPickCardByType _o_ = (SPickCardByType)_o1_;
			if (picktype != _o_.picktype) return false;
			if (!pickbonus.equals(_o_.pickbonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += picktype;
		_h_ += pickbonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(picktype).append(",");
		_sb_.append(pickbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

