
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetBattleRound__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetBattleRound extends __SGetBattleRound__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573442;

	public int getType() {
		return 6573442;
	}

	public int round;
	public int profession;
	public java.util.ArrayList<lx.gs.activity.huiwu.msg.BattlePair> battles;

	public SGetBattleRound() {
		battles = new java.util.ArrayList<lx.gs.activity.huiwu.msg.BattlePair>();
	}

	public SGetBattleRound(int _round_, int _profession_, java.util.ArrayList<lx.gs.activity.huiwu.msg.BattlePair> _battles_) {
		this.round = _round_;
		this.profession = _profession_;
		this.battles = _battles_;
	}

	public final boolean _validator_() {
		for (lx.gs.activity.huiwu.msg.BattlePair _v_ : battles)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(round);
		_os_.marshal(profession);
		_os_.compact_uint32(battles.size());
		for (lx.gs.activity.huiwu.msg.BattlePair _v_ : battles) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		round = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.activity.huiwu.msg.BattlePair _v_ = new lx.gs.activity.huiwu.msg.BattlePair();
			_v_.unmarshal(_os_);
			battles.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetBattleRound) {
			SGetBattleRound _o_ = (SGetBattleRound)_o1_;
			if (round != _o_.round) return false;
			if (profession != _o_.profession) return false;
			if (!battles.equals(_o_.battles)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += round;
		_h_ += profession;
		_h_ += battles.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(round).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(battles).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

