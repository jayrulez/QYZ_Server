package lx.gs.talisman;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.logger.By;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUpgradeSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUpgradeSkill extends __CUpgradeSkill__ {
    @Override
    protected void process() {
        new AProcedure<CUpgradeSkill>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                xbean.Talisman talisman = FTalisman.getTalisman(roleid, bagtype, pos);
				if(!FTalisman.getModelById(talisman.getModelid()).canuse){
					return false;
				}
                final Map<Integer, Integer> skills = talisman.getSkills();
                final int newLevel = skills.get(skillid) + 1;
                final cfg.skill.SkillLvlupCost curcfg = CfgMgr.skilllvlupcost.get(skillid);
                if (newLevel > curcfg.skilllvlupdata.size())
                    return error(ErrorCode.CANNOT_UPGRADE_MAX_LEVEL);
                final cfg.skill.SkillLvlupData costcfg = curcfg.skilllvlupdata.get(newLevel - 1);
                if (talisman.getNormallevel() < costcfg.requirelvl)
                    return error(ErrorCode.TALISMAN_LEVEL_TOO_LOW);
                final ErrorCode err = FCondition.checkByReflection(roleid, costcfg, By.Talisman_Upgrade_Skill);
                if (err.err())
                    return error(err);
                skills.put(skillid, newLevel);
				FTalisman.bindTalisman(roleid, bagtype, talisman);
				return response(new SUpgradeSkill(bagtype, pos, skillid, newLevel));
            }
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579817;

	public int getType() {
		return 6579817;
	}

	public int bagtype;
	public int pos;
	public int skillid;

	public CUpgradeSkill() {
	}

	public CUpgradeSkill(int _bagtype_, int _pos_, int _skillid_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.skillid = _skillid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.marshal(skillid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		skillid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUpgradeSkill) {
			CUpgradeSkill _o_ = (CUpgradeSkill)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (skillid != _o_.skillid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += skillid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(skillid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUpgradeSkill _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = skillid - _o_.skillid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

