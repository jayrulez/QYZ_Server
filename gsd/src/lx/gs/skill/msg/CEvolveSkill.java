
package lx.gs.skill.msg;

import gs.AProcedure;
import common.ErrorCode;
import lx.gs.map.FMap;
import lx.gs.skill.FSkill;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import map.msg.XEvolveSkill;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEvolveSkill__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEvolveSkill extends __CEvolveSkill__ {
	@Override
	protected void process() {
		new AProcedure<CEvolveSkill>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final SEvolveSkill re = new SEvolveSkill();
				re.oldskillid = skillid;
				final ErrorCode err = FSkill.evolve(roleid, skillid, re);
				if(err.err())
					return error(err);
				FMap.dispatchMessageInProcedure(roleid, new XEvolveSkill(skillid, re.newskillid, 1));
				response(re);
				return true;
			}
			
		}.validateRoleidAndExecute();
	
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557706;

	public int getType() {
		return 6557706;
	}

	public int skillid;

	public CEvolveSkill() {
	}

	public CEvolveSkill(int _skillid_) {
		this.skillid = _skillid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(skillid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		skillid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEvolveSkill) {
			CEvolveSkill _o_ = (CEvolveSkill)_o1_;
			if (skillid != _o_.skillid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += skillid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skillid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEvolveSkill _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = skillid - _o_.skillid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

