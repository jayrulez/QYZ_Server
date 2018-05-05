
package lx.gs.task.msg;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.task.FTask;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetWeekCompleteBonus__ extends xio.Protocol { }

/** 每周完成一定小环环任务数后领奖，目前完成10，20，40，70环数后会有奖励
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetWeekCompleteBonus extends __CGetWeekCompleteBonus__ {
	@Override
	protected void process() {
		new AProcedure<CGetWeekCompleteBonus>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleTask taskInfo = FTask.getTask(roleid);
				if(taskInfo.getWeekspebonus().contains(bonuslvl)){
					return error(ErrorCode.HAS_RECEIVED_THIS_BONUS);
				}
				final int roleLvl = xtable.Roleinfos.get(roleid).getLevel();
				cfg.family.SpecBonus conf = CfgMgr.familytaskspecreward.get(roleLvl).specbonuslist.get(bonuslvl);
				int completedNums = taskInfo.getWeekcompletedsmallfamtasks();
				if(completedNums < conf.requiretaskamount){
					return error(ErrorCode.COMPLETE_NUMS_NOT_ENOUGH);
				}
//				xdb.Trace.info("Received week complete bonus，bonus lvl = {}, completeNums = {}", bonuslvl, completedNums);
				taskInfo.getWeekspebonus().add(bonuslvl);
				SGetWeekCompleteBonus response = new SGetWeekCompleteBonus();
                response.bonuslvl = bonuslvl;
				response(response);
				return FBonus.genAndAddBonus(roleid, conf.bonus, common.Bonus.BindType.BIND, response.bonus, By.Family_Task);
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581935;

	public int getType() {
		return 6581935;
	}

	public int bonuslvl; // 要领取的档位,用0，1，2，3...表示

	public CGetWeekCompleteBonus() {
	}

	public CGetWeekCompleteBonus(int _bonuslvl_) {
		this.bonuslvl = _bonuslvl_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bonuslvl);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bonuslvl = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetWeekCompleteBonus) {
			CGetWeekCompleteBonus _o_ = (CGetWeekCompleteBonus)_o1_;
			if (bonuslvl != _o_.bonuslvl) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bonuslvl;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bonuslvl).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetWeekCompleteBonus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bonuslvl - _o_.bonuslvl;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

