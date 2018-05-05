
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEndTeamFight__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEndTeamFight extends __SEndTeamFight__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6713605;

	public int getType() {
		return 6713605;
	}

	public int result;
	public map.msg.Bonus bonus;
	public java.util.ArrayList<map.msg.PlayerEvaluate> evaluate;

	public SEndTeamFight() {
		bonus = new map.msg.Bonus();
		evaluate = new java.util.ArrayList<map.msg.PlayerEvaluate>();
	}

	public SEndTeamFight(int _result_, map.msg.Bonus _bonus_, java.util.ArrayList<map.msg.PlayerEvaluate> _evaluate_) {
		this.result = _result_;
		this.bonus = _bonus_;
		this.evaluate = _evaluate_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		for (map.msg.PlayerEvaluate _v_ : evaluate)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(bonus);
		_os_.compact_uint32(evaluate.size());
		for (map.msg.PlayerEvaluate _v_ : evaluate) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.PlayerEvaluate _v_ = new map.msg.PlayerEvaluate();
			_v_.unmarshal(_os_);
			evaluate.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEndTeamFight) {
			SEndTeamFight _o_ = (SEndTeamFight)_o1_;
			if (result != _o_.result) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!evaluate.equals(_o_.evaluate)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += bonus.hashCode();
		_h_ += evaluate.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(evaluate).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

