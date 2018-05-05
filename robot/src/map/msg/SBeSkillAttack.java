
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SBeSkillAttack__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SBeSkillAttack extends __SBeSkillAttack__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6701442;

	public int getType() {
		return 6701442;
	}

	public java.util.ArrayList<map.msg.BeAttackResult> attacks;
	public java.util.ArrayList<map.msg.BeHealResult> heals;
	public int hp;

	public SBeSkillAttack() {
		attacks = new java.util.ArrayList<map.msg.BeAttackResult>();
		heals = new java.util.ArrayList<map.msg.BeHealResult>();
	}

	public SBeSkillAttack(java.util.ArrayList<map.msg.BeAttackResult> _attacks_, java.util.ArrayList<map.msg.BeHealResult> _heals_, int _hp_) {
		this.attacks = _attacks_;
		this.heals = _heals_;
		this.hp = _hp_;
	}

	public final boolean _validator_() {
		for (map.msg.BeAttackResult _v_ : attacks)
			if (!_v_._validator_()) return false;
		for (map.msg.BeHealResult _v_ : heals)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(attacks.size());
		for (map.msg.BeAttackResult _v_ : attacks) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(heals.size());
		for (map.msg.BeHealResult _v_ : heals) {
			_os_.marshal(_v_);
		}
		_os_.marshal(hp);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.BeAttackResult _v_ = new map.msg.BeAttackResult();
			_v_.unmarshal(_os_);
			attacks.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.BeHealResult _v_ = new map.msg.BeHealResult();
			_v_.unmarshal(_os_);
			heals.add(_v_);
		}
		hp = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SBeSkillAttack) {
			SBeSkillAttack _o_ = (SBeSkillAttack)_o1_;
			if (!attacks.equals(_o_.attacks)) return false;
			if (!heals.equals(_o_.heals)) return false;
			if (hp != _o_.hp) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += attacks.hashCode();
		_h_ += heals.hashCode();
		_h_ += hp;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(attacks).append(",");
		_sb_.append(heals).append(",");
		_sb_.append(hp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

