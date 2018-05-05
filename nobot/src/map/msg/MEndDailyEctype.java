
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEndDailyEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEndDailyEctype extends __MEndDailyEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6713050;

	public int getType() {
		return 6713050;
	}

	public int ectypeid;
	public int costtime; // seconds
	public java.util.ArrayList<map.msg.Bonus> bonuss;

	public MEndDailyEctype() {
		bonuss = new java.util.ArrayList<map.msg.Bonus>();
	}

	public MEndDailyEctype(int _ectypeid_, int _costtime_, java.util.ArrayList<map.msg.Bonus> _bonuss_) {
		this.ectypeid = _ectypeid_;
		this.costtime = _costtime_;
		this.bonuss = _bonuss_;
	}

	public final boolean _validator_() {
		for (map.msg.Bonus _v_ : bonuss)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(costtime);
		_os_.compact_uint32(bonuss.size());
		for (map.msg.Bonus _v_ : bonuss) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		costtime = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.Bonus _v_ = new map.msg.Bonus();
			_v_.unmarshal(_os_);
			bonuss.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEndDailyEctype) {
			MEndDailyEctype _o_ = (MEndDailyEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (costtime != _o_.costtime) return false;
			if (!bonuss.equals(_o_.bonuss)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += costtime;
		_h_ += bonuss.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(costtime).append(",");
		_sb_.append(bonuss).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

