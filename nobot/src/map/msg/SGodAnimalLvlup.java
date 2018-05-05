
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGodAnimalLvlup__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGodAnimalLvlup extends __SGodAnimalLvlup__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6701585;

	public int getType() {
		return 6701585;
	}

	public long familymapid; // 家族的地图id
	public int bossid;
	public int bosslvl;

	public SGodAnimalLvlup() {
	}

	public SGodAnimalLvlup(long _familymapid_, int _bossid_, int _bosslvl_) {
		this.familymapid = _familymapid_;
		this.bossid = _bossid_;
		this.bosslvl = _bosslvl_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familymapid);
		_os_.marshal(bossid);
		_os_.marshal(bosslvl);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familymapid = _os_.unmarshal_long();
		bossid = _os_.unmarshal_int();
		bosslvl = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGodAnimalLvlup) {
			SGodAnimalLvlup _o_ = (SGodAnimalLvlup)_o1_;
			if (familymapid != _o_.familymapid) return false;
			if (bossid != _o_.bossid) return false;
			if (bosslvl != _o_.bosslvl) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familymapid;
		_h_ += bossid;
		_h_ += bosslvl;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familymapid).append(",");
		_sb_.append(bossid).append(",");
		_sb_.append(bosslvl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SGodAnimalLvlup _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(familymapid - _o_.familymapid);
		if (0 != _c_) return _c_;
		_c_ = bossid - _o_.bossid;
		if (0 != _c_) return _c_;
		_c_ = bosslvl - _o_.bosslvl;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

