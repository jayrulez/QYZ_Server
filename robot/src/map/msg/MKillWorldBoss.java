
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MKillWorldBoss__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MKillWorldBoss extends __MKillWorldBoss__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6712971;

	public int getType() {
		return 6712971;
	}

	public int lineid;
	public long killer;
	public int bossid;

	public MKillWorldBoss() {
	}

	public MKillWorldBoss(int _lineid_, long _killer_, int _bossid_) {
		this.lineid = _lineid_;
		this.killer = _killer_;
		this.bossid = _bossid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(lineid);
		_os_.marshal(killer);
		_os_.marshal(bossid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		lineid = _os_.unmarshal_int();
		killer = _os_.unmarshal_long();
		bossid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MKillWorldBoss) {
			MKillWorldBoss _o_ = (MKillWorldBoss)_o1_;
			if (lineid != _o_.lineid) return false;
			if (killer != _o_.killer) return false;
			if (bossid != _o_.bossid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += lineid;
		_h_ += (int)killer;
		_h_ += bossid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lineid).append(",");
		_sb_.append(killer).append(",");
		_sb_.append(bossid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(MKillWorldBoss _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = lineid - _o_.lineid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(killer - _o_.killer);
		if (0 != _c_) return _c_;
		_c_ = bossid - _o_.bossid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

