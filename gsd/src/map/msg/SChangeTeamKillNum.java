
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SChangeTeamKillNum__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SChangeTeamKillNum extends __SChangeTeamKillNum__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714265;

	public int getType() {
		return 6714265;
	}

	public int camp;
	public int killnum;

	public SChangeTeamKillNum() {
	}

	public SChangeTeamKillNum(int _camp_, int _killnum_) {
		this.camp = _camp_;
		this.killnum = _killnum_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(camp);
		_os_.marshal(killnum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		camp = _os_.unmarshal_int();
		killnum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SChangeTeamKillNum) {
			SChangeTeamKillNum _o_ = (SChangeTeamKillNum)_o1_;
			if (camp != _o_.camp) return false;
			if (killnum != _o_.killnum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += camp;
		_h_ += killnum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(camp).append(",");
		_sb_.append(killnum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SChangeTeamKillNum _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = camp - _o_.camp;
		if (0 != _c_) return _c_;
		_c_ = killnum - _o_.killnum;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

