
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGuardTowerMatcherUpdate__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGuardTowerMatcherUpdate extends __SGuardTowerMatcherUpdate__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585119;

	public int getType() {
		return 6585119;
	}

	public int matched; // 0 尚未匹配成功 1 匹配成功
	public lx.gs.map.msg.MatchTeamInfo teaminfo;
	public int countdown; // 匹配成功之后的倒计时s

	public SGuardTowerMatcherUpdate() {
		teaminfo = new lx.gs.map.msg.MatchTeamInfo();
	}

	public SGuardTowerMatcherUpdate(int _matched_, lx.gs.map.msg.MatchTeamInfo _teaminfo_, int _countdown_) {
		this.matched = _matched_;
		this.teaminfo = _teaminfo_;
		this.countdown = _countdown_;
	}

	public final boolean _validator_() {
		if (!teaminfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(matched);
		_os_.marshal(teaminfo);
		_os_.marshal(countdown);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		matched = _os_.unmarshal_int();
		teaminfo.unmarshal(_os_);
		countdown = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGuardTowerMatcherUpdate) {
			SGuardTowerMatcherUpdate _o_ = (SGuardTowerMatcherUpdate)_o1_;
			if (matched != _o_.matched) return false;
			if (!teaminfo.equals(_o_.teaminfo)) return false;
			if (countdown != _o_.countdown) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += matched;
		_h_ += teaminfo.hashCode();
		_h_ += countdown;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(matched).append(",");
		_sb_.append(teaminfo).append(",");
		_sb_.append(countdown).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

