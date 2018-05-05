
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndWolrdBoss__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndWolrdBoss extends __SEndWolrdBoss__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568024;

	public int getType() {
		return 6568024;
	}

	public int worldbossid;

	public SEndWolrdBoss() {
	}

	public SEndWolrdBoss(int _worldbossid_) {
		this.worldbossid = _worldbossid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(worldbossid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		worldbossid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndWolrdBoss) {
			SEndWolrdBoss _o_ = (SEndWolrdBoss)_o1_;
            return worldbossid == _o_.worldbossid;
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += worldbossid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(worldbossid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEndWolrdBoss _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = worldbossid - _o_.worldbossid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

