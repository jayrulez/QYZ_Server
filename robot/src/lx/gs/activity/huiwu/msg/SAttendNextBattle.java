
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SAttendNextBattle__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SAttendNextBattle extends __SAttendNextBattle__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568392;

	public int getType() {
		return 6568392;
	}

	public int round;
	public long battlebegintime;
	public java.lang.String opponent;
	public int opponentcombatpower;

	public SAttendNextBattle() {
		opponent = "";
	}

	public SAttendNextBattle(int _round_, long _battlebegintime_, java.lang.String _opponent_, int _opponentcombatpower_) {
		this.round = _round_;
		this.battlebegintime = _battlebegintime_;
		this.opponent = _opponent_;
		this.opponentcombatpower = _opponentcombatpower_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(round);
		_os_.marshal(battlebegintime);
		_os_.marshal(opponent, "UTF-16LE");
		_os_.marshal(opponentcombatpower);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		round = _os_.unmarshal_int();
		battlebegintime = _os_.unmarshal_long();
		opponent = _os_.unmarshal_String("UTF-16LE");
		opponentcombatpower = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SAttendNextBattle) {
			SAttendNextBattle _o_ = (SAttendNextBattle)_o1_;
			if (round != _o_.round) return false;
			if (battlebegintime != _o_.battlebegintime) return false;
			if (!opponent.equals(_o_.opponent)) return false;
			if (opponentcombatpower != _o_.opponentcombatpower) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += round;
		_h_ += (int)battlebegintime;
		_h_ += opponent.hashCode();
		_h_ += opponentcombatpower;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(round).append(",");
		_sb_.append(battlebegintime).append(",");
		_sb_.append("T").append(opponent.length()).append(",");
		_sb_.append(opponentcombatpower).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

