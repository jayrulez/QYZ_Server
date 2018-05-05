
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSkillCastBomb__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSkillCastBomb extends __SSkillCastBomb__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6709947;

	public int getType() {
		return 6709947;
	}

	public int skillid; // 技能id
	public long attackerid; // 攻击者id
	public int objectid;
	public map.msg.Vector3 direction;
	public map.msg.Vector3 position;

	public SSkillCastBomb() {
		direction = new map.msg.Vector3();
		position = new map.msg.Vector3();
	}

	public SSkillCastBomb(int _skillid_, long _attackerid_, int _objectid_, map.msg.Vector3 _direction_, map.msg.Vector3 _position_) {
		this.skillid = _skillid_;
		this.attackerid = _attackerid_;
		this.objectid = _objectid_;
		this.direction = _direction_;
		this.position = _position_;
	}

	public final boolean _validator_() {
		if (!direction._validator_()) return false;
		if (!position._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(skillid);
		_os_.marshal(attackerid);
		_os_.marshal(objectid);
		_os_.marshal(direction);
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		skillid = _os_.unmarshal_int();
		attackerid = _os_.unmarshal_long();
		objectid = _os_.unmarshal_int();
		direction.unmarshal(_os_);
		position.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSkillCastBomb) {
			SSkillCastBomb _o_ = (SSkillCastBomb)_o1_;
			if (skillid != _o_.skillid) return false;
			if (attackerid != _o_.attackerid) return false;
			if (objectid != _o_.objectid) return false;
			if (!direction.equals(_o_.direction)) return false;
			if (!position.equals(_o_.position)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += skillid;
		_h_ += (int)attackerid;
		_h_ += objectid;
		_h_ += direction.hashCode();
		_h_ += position.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skillid).append(",");
		_sb_.append(attackerid).append(",");
		_sb_.append(objectid).append(",");
		_sb_.append(direction).append(",");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

