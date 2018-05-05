
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBattleResult__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBattleResult extends __SBattleResult__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574544;

	public int getType() {
		return 6574544;
	}

	public java.lang.String opponentname;
	public int win; // 0 输, 1赢

	public SBattleResult() {
		opponentname = "";
	}

	public SBattleResult(java.lang.String _opponentname_, int _win_) {
		this.opponentname = _opponentname_;
		this.win = _win_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(opponentname, "UTF-16LE");
		_os_.marshal(win);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		opponentname = _os_.unmarshal_String("UTF-16LE");
		win = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBattleResult) {
			SBattleResult _o_ = (SBattleResult)_o1_;
			if (!opponentname.equals(_o_.opponentname)) return false;
			if (win != _o_.win) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += opponentname.hashCode();
		_h_ += win;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(opponentname.length()).append(",");
		_sb_.append(win).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

