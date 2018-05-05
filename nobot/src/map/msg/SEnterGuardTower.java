
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterGuardTower__ extends xio.Protocol { }

/** 血战青云
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterGuardTower extends __SEnterGuardTower__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6715338;

	public int getType() {
		return 6715338;
	}

	public long id;
	public int ectypeid;
	public int curwaveid;
	public long remaintime;

	public SEnterGuardTower() {
	}

	public SEnterGuardTower(long _id_, int _ectypeid_, int _curwaveid_, long _remaintime_) {
		this.id = _id_;
		this.ectypeid = _ectypeid_;
		this.curwaveid = _curwaveid_;
		this.remaintime = _remaintime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(ectypeid);
		_os_.marshal(curwaveid);
		_os_.marshal(remaintime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		ectypeid = _os_.unmarshal_int();
		curwaveid = _os_.unmarshal_int();
		remaintime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterGuardTower) {
			SEnterGuardTower _o_ = (SEnterGuardTower)_o1_;
			if (id != _o_.id) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (curwaveid != _o_.curwaveid) return false;
			if (remaintime != _o_.remaintime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += ectypeid;
		_h_ += curwaveid;
		_h_ += (int)remaintime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(curwaveid).append(",");
		_sb_.append(remaintime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEnterGuardTower _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(id - _o_.id);
		if (0 != _c_) return _c_;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = curwaveid - _o_.curwaveid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(remaintime - _o_.remaintime);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

