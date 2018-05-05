
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SStudyFamilySkillNotify__ extends xio.Protocol { }

/** 学习家族技能
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SStudyFamilySkillNotify extends __SStudyFamilySkillNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566649;

	public int getType() {
		return 6566649;
	}

	public int skillid; // 家族技能id
	public lx.gs.family.msg.FamilySkill skill; // 升级后的技能x信息

	public SStudyFamilySkillNotify() {
		skill = new lx.gs.family.msg.FamilySkill();
	}

	public SStudyFamilySkillNotify(int _skillid_, lx.gs.family.msg.FamilySkill _skill_) {
		this.skillid = _skillid_;
		this.skill = _skill_;
	}

	public final boolean _validator_() {
		if (!skill._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(skillid);
		_os_.marshal(skill);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		skillid = _os_.unmarshal_int();
		skill.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SStudyFamilySkillNotify) {
			SStudyFamilySkillNotify _o_ = (SStudyFamilySkillNotify)_o1_;
			if (skillid != _o_.skillid) return false;
			if (!skill.equals(_o_.skill)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += skillid;
		_h_ += skill.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skillid).append(",");
		_sb_.append(skill).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SStudyFamilySkillNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = skillid - _o_.skillid;
		if (0 != _c_) return _c_;
		_c_ = skill.compareTo(_o_.skill);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

