
package lx.gs.bonus.msg;

import cfg.CfgMgr;
import cfg.bonus.BONUSTYPE;
import cfg.cmd.action.Bonus;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.login.FLogin;

import java.util.List;
import java.util.stream.Collectors;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetOnlineGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetOnlineGift extends __CGetOnlineGift__ {
	@Override
	protected void process() {
		// protocol handle
		new AProcedure<CGetOnlineGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleInfo roleinfo = FBonus.getRoleInfo(roleid);
				xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
				long dailyOnlinetime = FLogin.calcTodayOnlineTime(roleinfo);
				SGetOnlineGift response = new SGetOnlineGift();
				if (gifttimetype.stream().anyMatch(i -> i > dailyOnlinetime)) {
					return error(ErrorCode.TIME_NOT_ENOUGH);
				}
				for (int i : gifttimetype) {
                    if(welfare.getReceivedonlinegift().contains(i)){
                        return error(ErrorCode.HAS_RECEIVE_BONUS);
                    }
					welfare.getReceivedonlinegift().add(i);
					cfg.bonus.OnlineTimeBonus randomgift = CfgMgr.onlinetimebonus.get(i);
					// 目前配置的是每次只发放一件
					map.msg.Bonus reBonus = new map.msg.Bonus();
					if(!FBonus.genAndAddBonus(roleid, randomgift.bonuslist, common.Bonus.BindType.BIND, reBonus, By.Active_Award)){
						return false;
					}
					response.onlinegift.put(i, reBonus);
                    FLogger.welfare(roleid, roleinfo, BONUSTYPE.DAILY_ONLINE, i);
				}
				response(response);
				return true;
			}

		}.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556116;

	public int getType() {
		return 6556116;
	}

	public java.util.ArrayList<Integer> gifttimetype;

	public CGetOnlineGift() {
		gifttimetype = new java.util.ArrayList<Integer>();
	}

	public CGetOnlineGift(java.util.ArrayList<Integer> _gifttimetype_) {
		this.gifttimetype = _gifttimetype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(gifttimetype.size());
		for (Integer _v_ : gifttimetype) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			gifttimetype.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetOnlineGift) {
			CGetOnlineGift _o_ = (CGetOnlineGift)_o1_;
			if (!gifttimetype.equals(_o_.gifttimetype)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += gifttimetype.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(gifttimetype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
