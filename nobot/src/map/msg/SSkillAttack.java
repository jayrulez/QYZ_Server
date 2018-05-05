
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSkillAttack__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSkillAttack extends __SSkillAttack__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6685188;

	public int getType() {
		return 6685188;
	}

	public int skillid; // 技能id
	public long attackerid; // 攻击者id
	public int attackid; // 打击事件id
	public long target; // 技能目标id(如果技能needtarget,在最初目标死亡或者离开攻击范围后会重新选定目标)
	public map.msg.Vector3 direction; // 技能方向(对于needtarget的技能,这个参数无用)
	public java.util.ArrayList<map.msg.AttackResult> attacks;
	public java.util.ArrayList<map.msg.HealResult> heals;

	public SSkillAttack() {
		direction = new map.msg.Vector3();
		attacks = new java.util.ArrayList<map.msg.AttackResult>();
		heals = new java.util.ArrayList<map.msg.HealResult>();
	}

	public SSkillAttack(int _skillid_, long _attackerid_, int _attackid_, long _target_, map.msg.Vector3 _direction_, java.util.ArrayList<map.msg.AttackResult> _attacks_, java.util.ArrayList<map.msg.HealResult> _heals_) {
		this.skillid = _skillid_;
		this.attackerid = _attackerid_;
		this.attackid = _attackid_;
		this.target = _target_;
		this.direction = _direction_;
		this.attacks = _attacks_;
		this.heals = _heals_;
	}

	public final boolean _validator_() {
		if (!direction._validator_()) return false;
		for (map.msg.AttackResult _v_ : attacks)
			if (!_v_._validator_()) return false;
		for (map.msg.HealResult _v_ : heals)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(skillid);
		_os_.marshal(attackerid);
		_os_.marshal(attackid);
		_os_.marshal(target);
		_os_.marshal(direction);
		_os_.compact_uint32(attacks.size());
		for (map.msg.AttackResult _v_ : attacks) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(heals.size());
		for (map.msg.HealResult _v_ : heals) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		skillid = _os_.unmarshal_int();
		attackerid = _os_.unmarshal_long();
		attackid = _os_.unmarshal_int();
		target = _os_.unmarshal_long();
		direction.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.AttackResult _v_ = new map.msg.AttackResult();
			_v_.unmarshal(_os_);
			attacks.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.HealResult _v_ = new map.msg.HealResult();
			_v_.unmarshal(_os_);
			heals.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSkillAttack) {
			SSkillAttack _o_ = (SSkillAttack)_o1_;
			if (skillid != _o_.skillid) return false;
			if (attackerid != _o_.attackerid) return false;
			if (attackid != _o_.attackid) return false;
			if (target != _o_.target) return false;
			if (!direction.equals(_o_.direction)) return false;
			if (!attacks.equals(_o_.attacks)) return false;
			if (!heals.equals(_o_.heals)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += skillid;
		_h_ += (int)attackerid;
		_h_ += attackid;
		_h_ += (int)target;
		_h_ += direction.hashCode();
		_h_ += attacks.hashCode();
		_h_ += heals.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skillid).append(",");
		_sb_.append(attackerid).append(",");
		_sb_.append(attackid).append(",");
		_sb_.append(target).append(",");
		_sb_.append(direction).append(",");
		_sb_.append(attacks).append(",");
		_sb_.append(heals).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

