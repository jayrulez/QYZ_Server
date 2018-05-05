package lx.gs.talisman;

import cfg.Const;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.talisman.TalismanFeed;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;

import common.ErrorCode;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CChangeLuckType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CChangeLuckType extends __CChangeLuckType__ {
    @Override
    protected void process() {
        new AProcedure<CChangeLuckType>(this) {

            @Override
            protected boolean doProcess() throws Exception {
                final xbean.RoleTalismanBag talismanBag = FTalisman.getDbBag(roleid);
                final xbean.RoleInfo role = xtable.Roleinfos.get(roleid);
                if (isbestlucky == Const.TRUE) { //如果是使用的必然转运,那么消耗元宝
                    if (talismanBag.getLuckytype() == TalismanFeed.BEST_LUCK_ID) {
                        xdb.Trace.error("roleid:{} has been best luck", roleid);
                        return false;
                    }
                    if (FRole.checkCostYuanBao(roleid, role, TalismanFeed.BEST_LUCK_COST, By.Talisman_Change_Luck)) {
                        FTalisman.setLuckyType(talismanBag, TalismanFeed.BEST_LUCK_ID);
                    } else {
                        return error(ErrorCode.YUANBAO_NOT_ENOUGH);
                    }
                } else {
                    if (!FLimit.checkLimitAndAddNum(roleid, ConfigId.TALISMAN_LUCK, 0, 1, LimitType.DAY, TalismanFeed.FREE_CHANGE_LUCK_TIMES)) {
                        if (!FRole.checkCostYuanBao(roleid, role, TalismanFeed.WASH_LUCK_COST, By.Talisman_Change_Luck)) {
                            return error(ErrorCode.YUANBAO_NOT_ENOUGH);
                        }
                    }
                    FTalisman.setLuckyType(talismanBag, FTalisman.randomLuckyType());
                }
				return response(new SLuckyInfo(talismanBag.getLuckytype(), talismanBag.getLuckywashtimes()));
            }

        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585431;

	public int getType() {
		return 6585431;
	}

	public int isbestlucky; // 是否是必然大吉，0表示否，1表示是

	public CChangeLuckType() {
	}

	public CChangeLuckType(int _isbestlucky_) {
		this.isbestlucky = _isbestlucky_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(isbestlucky);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		isbestlucky = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CChangeLuckType) {
			CChangeLuckType _o_ = (CChangeLuckType)_o1_;
			if (isbestlucky != _o_.isbestlucky) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += isbestlucky;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(isbestlucky).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CChangeLuckType _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = isbestlucky - _o_.isbestlucky;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

