
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeClimbTower__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeClimbTower extends __SChangeClimbTower__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584220;

	public int getType() {
		return 6584220;
	}

	public int ectypeid;
	public lx.gs.map.msg.ClimbTowerInfo info;

	public SChangeClimbTower() {
		info = new lx.gs.map.msg.ClimbTowerInfo();
	}

	public SChangeClimbTower(int _ectypeid_, lx.gs.map.msg.ClimbTowerInfo _info_) {
		this.ectypeid = _ectypeid_;
		this.info = _info_;
	}

	public final boolean _validator_() {
		if (!info._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(info);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		info.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeClimbTower) {
			SChangeClimbTower _o_ = (SChangeClimbTower)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (!info.equals(_o_.info)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += info.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(info).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SChangeClimbTower _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = info.compareTo(_o_.info);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

