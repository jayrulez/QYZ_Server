
package lx.gs.activity.worldmonster.msg;

import cfg.CfgMgr;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetKillExpMonBonus__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetKillExpMonBonus extends __CGetKillExpMonBonus__ {
	@Override
	protected void process() {
        new AProcedure<CGetKillExpMonBonus>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.DailyResetData dailyResetData = FLimit.getDaily(roleid);
                if(dailyResetData.getKillexpmonsters() < num){
                    return error(ErrorCode.KILL_AMOUNT_NOT_ENOUGH);
                }
                if(dailyResetData.getRecexpmonbonus().contains(num)){
                    return error(ErrorCode.HAS_RECEIVE_BONUS);
                }
                int level = xtable.Roleinfos.selectLevel(roleid);
                cfg.ectype.ExpMonsterMsg detail = null;
                for(cfg.ectype.ExpMonsterMsg conf : CfgMgr.expmonster.monstermsg){
                    if(level <= conf.maxlevel){
                        detail = conf;
                        break;
                    }
                }
                if (detail == null){
                    return false;
                }
                cfg.cmd.action.Bonus bonus = null;
                for(cfg.ectype.ExpMonsterBonus conf : detail.monsterbonus){
                    if(conf.killnum == num){
                        bonus = conf.killbonus;
                        break;
                    }
                }
                if(bonus == null){
                    return error(ErrorCode.PARAM_ERROR);
                }
                dailyResetData.getRecexpmonbonus().add(num);
                SGetKillExpMonBonus response = new SGetKillExpMonBonus();
                boolean result = FBonus.genAndAddBonus(roleid, bonus, common.Bonus.BindType.BIND, response.bonus, By.Kill_Exp_Monster);
                if(!result){
                    return false;
                }
                response.num = num;
                response(response);
                return true;
            }
        }.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584256;

	public int getType() {
		return 6584256;
	}

	public int num; // 领取击杀多少只经验怪的奖励

	public CGetKillExpMonBonus() {
	}

	public CGetKillExpMonBonus(int _num_) {
		this.num = _num_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(num);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		num = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetKillExpMonBonus) {
			CGetKillExpMonBonus _o_ = (CGetKillExpMonBonus)_o1_;
			if (num != _o_.num) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += num;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(num).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetKillExpMonBonus _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = num - _o_.num;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

