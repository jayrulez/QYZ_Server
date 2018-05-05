
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SSkillPerform__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SSkillPerform extends __SSkillPerform__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6685190;

	public int getType() {
		return 6685190;
	}

	public int retcode;
	public int skillid;
	public long attackerid;
	public long targetid;
	public map.msg.Vector3 direction;
	public int mp;

	public SSkillPerform() {
		direction = new map.msg.Vector3();
	}

	public SSkillPerform(int _retcode_, int _skillid_, long _attackerid_, long _targetid_, map.msg.Vector3 _direction_, int _mp_) {
		this.retcode = _retcode_;
		this.skillid = _skillid_;
		this.attackerid = _attackerid_;
		this.targetid = _targetid_;
		this.direction = _direction_;
		this.mp = _mp_;
	}

	public final boolean _validator_() {
		if (!direction._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(retcode);
		_os_.marshal(skillid);
		_os_.marshal(attackerid);
		_os_.marshal(targetid);
		_os_.marshal(direction);
		_os_.marshal(mp);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		retcode = _os_.unmarshal_int();
		skillid = _os_.unmarshal_int();
		attackerid = _os_.unmarshal_long();
		targetid = _os_.unmarshal_long();
		direction.unmarshal(_os_);
		mp = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SSkillPerform) {
			SSkillPerform _o_ = (SSkillPerform)_o1_;
			if (retcode != _o_.retcode) return false;
			if (skillid != _o_.skillid) return false;
			if (attackerid != _o_.attackerid) return false;
			if (targetid != _o_.targetid) return false;
			if (!direction.equals(_o_.direction)) return false;
			if (mp != _o_.mp) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += retcode;
		_h_ += skillid;
		_h_ += (int)attackerid;
		_h_ += (int)targetid;
		_h_ += direction.hashCode();
		_h_ += mp;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(retcode).append(",");
		_sb_.append(skillid).append(",");
		_sb_.append(attackerid).append(",");
		_sb_.append(targetid).append(",");
		_sb_.append(direction).append(",");
		_sb_.append(mp).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

