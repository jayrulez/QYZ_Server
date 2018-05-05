
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gnet.link.Onlines;
import lx.gs.activity.worldboss.WorldBoss;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetWorldBossLineStatus__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetWorldBossLineStatus extends __CGetWorldBossLineStatus__ {
	@Override
	protected void process() {
		WorldBoss.sendSGetWorldBossLineStatus(Onlines.getInstance().find(this).getRoleid(), bossid);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574426;

	public int getType() {
		return 6574426;
	}

	public int bossid;

	public CGetWorldBossLineStatus() {
	}

	public CGetWorldBossLineStatus(int _bossid_) {
		this.bossid = _bossid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bossid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bossid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetWorldBossLineStatus) {
			CGetWorldBossLineStatus _o_ = (CGetWorldBossLineStatus)_o1_;
			if (bossid != _o_.bossid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bossid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bossid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetWorldBossLineStatus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bossid - _o_.bossid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

