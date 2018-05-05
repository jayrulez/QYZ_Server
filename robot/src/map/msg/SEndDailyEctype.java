
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndDailyEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndDailyEctype extends __SEndDailyEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699555;

	public int getType() {
		return 6699555;
	}

	public int errcode;
	public map.msg.Bonus totalbonus;
	public java.util.ArrayList<map.msg.Bonus> bonuss;

	public SEndDailyEctype() {
		totalbonus = new map.msg.Bonus();
		bonuss = new java.util.ArrayList<map.msg.Bonus>();
	}

	public SEndDailyEctype(int _errcode_, map.msg.Bonus _totalbonus_, java.util.ArrayList<map.msg.Bonus> _bonuss_) {
		this.errcode = _errcode_;
		this.totalbonus = _totalbonus_;
		this.bonuss = _bonuss_;
	}

	public final boolean _validator_() {
		if (!totalbonus._validator_()) return false;
		for (map.msg.Bonus _v_ : bonuss)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(totalbonus);
		_os_.compact_uint32(bonuss.size());
		for (map.msg.Bonus _v_ : bonuss) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		totalbonus.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.Bonus _v_ = new map.msg.Bonus();
			_v_.unmarshal(_os_);
			bonuss.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndDailyEctype) {
			SEndDailyEctype _o_ = (SEndDailyEctype)_o1_;
			if (errcode != _o_.errcode) return false;
			if (!totalbonus.equals(_o_.totalbonus)) return false;
			if (!bonuss.equals(_o_.bonuss)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += totalbonus.hashCode();
		_h_ += bonuss.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(totalbonus).append(",");
		_sb_.append(bonuss).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

