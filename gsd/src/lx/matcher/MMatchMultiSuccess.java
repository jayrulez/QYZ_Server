
package lx.matcher;

import cfg.CfgMgr;
import cfg.cmd.ConfigId;
import cfg.cmd.condition.LimitType;
import cfg.currency.CurrencyType;
import cfg.ectype.TeamStoryEctype;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import common.TaskQueue;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bonus.FBonus;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.map.msg.SEnrollMultiStoryEctypeSuccessNotify;
import lx.gs.role.FRole;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MMatchMultiSuccess__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MMatchMultiSuccess extends __MMatchMultiSuccess__ {
	@Override
	protected void process() {
        new xdb.Procedure(){
            @Override
            protected boolean process() {
                Set<Long> roleInThisGsd = new HashSet<>();
                members.forEach(i -> {
                    if(xtable.Roleinfos.select(i.roleid) != null){
                        roleInThisGsd.add(i.roleid);
                    }
                });
                cfg.ectype.TeamStoryEctype conf = CfgMgr.teamstoryectype.get(ectypeid);
                for(long i : roleInThisGsd) {
                    int usedTimes = FLimit.getLimitTimes(i, ConfigId.MULTI_STORY_ECTYPE, ectypeid, LimitType.DAY);
                    if (usedTimes < conf.daylimit.num && !FRole.checkCostCurrency(i, FBonus.getRoleInfo(i), CurrencyType.TiLi, conf.costtili.amount, By.Ectype)) {
                        return false;
                    }
                }
                SEnrollMultiStoryEctypeSuccessNotify notify = new SEnrollMultiStoryEctypeSuccessNotify();
                notify.lefttime = TeamStoryEctype.MATCH_SUCCESS_WAITING_TIME;
                notify.roleinfos = members;
                roleInThisGsd.forEach(i -> {
                    FMap.succMatch(i);
                    gnet.link.Onlines.getInstance().send(i, notify);
                });
                TaskQueue.getScheduleExecutor().schedule(() -> {
                    roleInThisGsd.forEach(i -> FMap.enterMapNotInProcedure(i, mapid));
                }, TeamStoryEctype.MATCH_SUCCESS_WAITING_TIME, TimeUnit.SECONDS);
                return true;
            }
        }.execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6650362;

	public int getType() {
		return 6650362;
	}

	public long mapid;
	public int ectypeid;
	public java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo> members;

	public MMatchMultiSuccess() {
		members = new java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo>();
	}

	public MMatchMultiSuccess(long _mapid_, int _ectypeid_, java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo> _members_) {
		this.mapid = _mapid_;
		this.ectypeid = _ectypeid_;
		this.members = _members_;
	}

	public final boolean _validator_() {
		for (lx.gs.map.msg.EnrollBriefInfo _v_ : members)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mapid);
		_os_.marshal(ectypeid);
		_os_.compact_uint32(members.size());
		for (lx.gs.map.msg.EnrollBriefInfo _v_ : members) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mapid = _os_.unmarshal_long();
		ectypeid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.map.msg.EnrollBriefInfo _v_ = new lx.gs.map.msg.EnrollBriefInfo();
			_v_.unmarshal(_os_);
			members.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MMatchMultiSuccess) {
			MMatchMultiSuccess _o_ = (MMatchMultiSuccess)_o1_;
			if (mapid != _o_.mapid) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (!members.equals(_o_.members)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)mapid;
		_h_ += ectypeid;
		_h_ += members.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(members).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

