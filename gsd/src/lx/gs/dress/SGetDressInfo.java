
package lx.gs.dress;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetDressInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetDressInfo extends lx.gs.dress.__SGetDressInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555771;

	public int getType() {
		return 6555771;
	}

	public java.util.ArrayList<lx.gs.dress.Dress> dresslist;
	public int activedress;

	public SGetDressInfo() {
		dresslist = new java.util.ArrayList<lx.gs.dress.Dress>();
	}

	public SGetDressInfo(java.util.ArrayList<lx.gs.dress.Dress> _dresslist_, int _activedress_) {
		this.dresslist = _dresslist_;
		this.activedress = _activedress_;
	}

	public final boolean _validator_() {
		for (lx.gs.dress.Dress _v_ : dresslist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(dresslist.size());
		for (lx.gs.dress.Dress _v_ : dresslist) {
			_os_.marshal(_v_);
		}
		_os_.marshal(activedress);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.dress.Dress _v_ = new lx.gs.dress.Dress();
			_v_.unmarshal(_os_);
			dresslist.add(_v_);
		}
		activedress = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetDressInfo) {
			SGetDressInfo _o_ = (SGetDressInfo)_o1_;
			if (!dresslist.equals(_o_.dresslist)) return false;
			if (activedress != _o_.activedress) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += dresslist.hashCode();
		_h_ += activedress;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(dresslist).append(",");
		_sb_.append(activedress).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

