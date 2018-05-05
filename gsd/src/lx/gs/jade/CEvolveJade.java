
package lx.gs.jade;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import common.ErrorCode;
import lx.gs.event.EventModule;
import lx.gs.event.JadeLevelUpEvent;
import lx.gs.logger.By;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEvolveJade__ extends xio.Protocol { }

/** 玉佩进阶
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEvolveJade extends lx.gs.jade.__CEvolveJade__ {
	@Override
	protected void process() {
		new AProcedure<CEvolveJade>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleJadeInfo jadeinfo = FJade.getRoleJadeInfo(roleid);
				int currLvl = jadeinfo.getLevel();
				if(!CfgMgr.jade.containsKey(currLvl + 1)){
					return error(ErrorCode.JADE_MAX_LEVEL);
				}
				cfg.equip.Jade conf = cfg.CfgMgr.jade.get(currLvl);

				ErrorCode err = FCondition.checkByReflection(roleid, conf, By.Evolve_Jade);
				if (err.err())
					return error(err);

				if (jadeinfo.getBonus() < conf.maxbonus) {
					return error(ErrorCode.NOT_ENOUGH_BONUS);
				}

				int level = FJade.setJadeLevel(roleid, currLvl + 1);
				EventModule.INSTANCE.broadcastEvent(new JadeLevelUpEvent(roleid));
				SEvolveJade result = new SEvolveJade();
				result.jade.level = level;
				result.jade.bonus = jadeinfo.getBonus();

				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564658;

	public int getType() {
		return 6564658;
	}


	public CEvolveJade() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CEvolveJade) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CEvolveJade _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
