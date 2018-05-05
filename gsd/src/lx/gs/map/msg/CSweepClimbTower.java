
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.active.EventNum;
import cfg.cmd.ConfigId;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.dailyactivity.FDailyActivity;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.MapModule;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSweepClimbTower__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSweepClimbTower extends __CSweepClimbTower__ {
	@Override
	protected void process() {
		new AProcedure<CSweepClimbTower>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                final xbean.RoleEctype info = FMap.getEctype(roleid);
                final int ectypeid = MapModule.CLIMB_TOWER_ECTYPE_ID;
                final xbean.ClimbTowerInfo towerInfo = info.getClimbtowers().get(ectypeid);
                if(towerInfo == null || towerInfo.getMaxfloorid() <= 0)
                    return error(ErrorCode.CLIMBTOWER_FLOOR_IS_ZERO);

                final SSweepClimbTower re = new SSweepClimbTower();
                final cfg.ectype.ClimbTowerEctype ccfg = CfgMgr.climbtowerectype.get(ectypeid);
                ErrorCode err = FCondition.checkByReflection(roleid, ccfg, 1, By.Sweep_ClimbTower, ConfigId.CLIMB_TOWER_ECTYPE, ectypeid);
                if(err.err())
                    return error(err);
                err = FCondition.checkByReflection(roleid, ccfg.sweep, 1, By.Sweep_ClimbTower, ConfigId.SWEEP_CLIMBTOWER, ectypeid);
                if(err.err())
                    return error(err);
                final int profession = xtable.Roleinfos.selectProfession(roleid);
                re.bonus.bindtype = EItemBindType.BOUND;
                for(int floor = 1, maxFloorid = towerInfo.getMaxfloorid(); floor <= maxFloorid ; floor++) {
                    FBonus.genBonusByProfession(profession, ccfg.floors_id.get(floor).normalbonus, 1, re.bonus);
                }
                boolean result = FBonus.addBonus(roleid, re.bonus, By.Sweep_ClimbTower);
                if(result) {
                    response(re);
                    FDailyActivity.addActiveScores(roleid, EventNum.CLIMBTOWER);
                }
                return result;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575969;

	public int getType() {
		return 6575969;
	}


	public CSweepClimbTower() {
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
		if (_o1_ instanceof CSweepClimbTower) {
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

	public int compareTo(CSweepClimbTower _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

