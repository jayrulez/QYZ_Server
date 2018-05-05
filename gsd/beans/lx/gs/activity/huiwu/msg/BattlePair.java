
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class BattlePair implements Marshal {
	public lx.gs.activity.huiwu.msg.BattleRole role1;
	public lx.gs.activity.huiwu.msg.BattleRole role2;
	public int state; // cfg.huiwu.BattleState

	public BattlePair() {
		role1 = new lx.gs.activity.huiwu.msg.BattleRole();
		role2 = new lx.gs.activity.huiwu.msg.BattleRole();
	}

	public BattlePair(lx.gs.activity.huiwu.msg.BattleRole _role1_, lx.gs.activity.huiwu.msg.BattleRole _role2_, int _state_) {
		this.role1 = _role1_;
		this.role2 = _role2_;
		this.state = _state_;
	}

	public final boolean _validator_() {
		if (!role1._validator_()) return false;
		if (!role2._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(role1);
		_os_.marshal(role2);
		_os_.marshal(state);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		role1.unmarshal(_os_);
		role2.unmarshal(_os_);
		state = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof BattlePair) {
			BattlePair _o_ = (BattlePair)_o1_;
			if (!role1.equals(_o_.role1)) return false;
			if (!role2.equals(_o_.role2)) return false;
			if (state != _o_.state) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += role1.hashCode();
		_h_ += role2.hashCode();
		_h_ += state;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(role1).append(",");
		_sb_.append(role2).append(",");
		_sb_.append(state).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

