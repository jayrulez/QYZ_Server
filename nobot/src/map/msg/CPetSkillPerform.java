
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPetSkillPerform__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPetSkillPerform extends __CPetSkillPerform__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6710134;

	public int getType() {
		return 6710134;
	}

	public long petid;
	public int skillid;
	public long targetid;
	public map.msg.Vector3 direction;

	public CPetSkillPerform() {
		direction = new map.msg.Vector3();
	}

	public CPetSkillPerform(long _petid_, int _skillid_, long _targetid_, map.msg.Vector3 _direction_) {
		this.petid = _petid_;
		this.skillid = _skillid_;
		this.targetid = _targetid_;
		this.direction = _direction_;
	}

	public final boolean _validator_() {
		if (!direction._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petid);
		_os_.marshal(skillid);
		_os_.marshal(targetid);
		_os_.marshal(direction);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petid = _os_.unmarshal_long();
		skillid = _os_.unmarshal_int();
		targetid = _os_.unmarshal_long();
		direction.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPetSkillPerform) {
			CPetSkillPerform _o_ = (CPetSkillPerform)_o1_;
			if (petid != _o_.petid) return false;
			if (skillid != _o_.skillid) return false;
			if (targetid != _o_.targetid) return false;
			if (!direction.equals(_o_.direction)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)petid;
		_h_ += skillid;
		_h_ += (int)targetid;
		_h_ += direction.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petid).append(",");
		_sb_.append(skillid).append(",");
		_sb_.append(targetid).append(",");
		_sb_.append(direction).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

