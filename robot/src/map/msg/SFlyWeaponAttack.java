
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SFlyWeaponAttack__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SFlyWeaponAttack extends __SFlyWeaponAttack__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714797;

	public int getType() {
		return 6714797;
	}

	public int skillid; // 技能id
	public long attackerid; // 攻击者id
	public int objectid; // 打击事件id
	public java.util.ArrayList<map.msg.AttackResult> attacks;

	public SFlyWeaponAttack() {
		attacks = new java.util.ArrayList<map.msg.AttackResult>();
	}

	public SFlyWeaponAttack(int _skillid_, long _attackerid_, int _objectid_, java.util.ArrayList<map.msg.AttackResult> _attacks_) {
		this.skillid = _skillid_;
		this.attackerid = _attackerid_;
		this.objectid = _objectid_;
		this.attacks = _attacks_;
	}

	public final boolean _validator_() {
		for (map.msg.AttackResult _v_ : attacks)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(skillid);
		_os_.marshal(attackerid);
		_os_.marshal(objectid);
		_os_.compact_uint32(attacks.size());
		for (map.msg.AttackResult _v_ : attacks) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		skillid = _os_.unmarshal_int();
		attackerid = _os_.unmarshal_long();
		objectid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.AttackResult _v_ = new map.msg.AttackResult();
			_v_.unmarshal(_os_);
			attacks.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SFlyWeaponAttack) {
			SFlyWeaponAttack _o_ = (SFlyWeaponAttack)_o1_;
			if (skillid != _o_.skillid) return false;
			if (attackerid != _o_.attackerid) return false;
			if (objectid != _o_.objectid) return false;
			if (!attacks.equals(_o_.attacks)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += skillid;
		_h_ += (int)attackerid;
		_h_ += objectid;
		_h_ += attacks.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skillid).append(",");
		_sb_.append(attackerid).append(",");
		_sb_.append(objectid).append(",");
		_sb_.append(attacks).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

