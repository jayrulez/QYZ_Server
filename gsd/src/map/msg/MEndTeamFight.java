
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEndTeamFight__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEndTeamFight extends __MEndTeamFight__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6708304;

	public int getType() {
		return 6708304;
	}

	public int result; // 负输,0平,正赢
	public byte quit;
	public map.msg.Bonus bonus;
	public java.util.ArrayList<Integer> evaluates;

	public MEndTeamFight() {
		bonus = new map.msg.Bonus();
		evaluates = new java.util.ArrayList<Integer>();
	}

	public MEndTeamFight(int _result_, byte _quit_, map.msg.Bonus _bonus_, java.util.ArrayList<Integer> _evaluates_) {
		this.result = _result_;
		this.quit = _quit_;
		this.bonus = _bonus_;
		this.evaluates = _evaluates_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(quit);
		_os_.marshal(bonus);
		_os_.compact_uint32(evaluates.size());
		for (Integer _v_ : evaluates) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		quit = _os_.unmarshal_byte();
		bonus.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			evaluates.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEndTeamFight) {
			MEndTeamFight _o_ = (MEndTeamFight)_o1_;
			if (result != _o_.result) return false;
			if (quit != _o_.quit) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!evaluates.equals(_o_.evaluates)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += (int)quit;
		_h_ += bonus.hashCode();
		_h_ += evaluates.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(quit).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(evaluates).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

