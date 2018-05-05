
package lx.gs.map.msg;

import cfg.CfgMgr;
import cfg.cmd.condition.YuanBao;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.map.FMap;

import java.util.List;
import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CHeroChangeEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CHeroChangeEctype extends __CHeroChangeEctype__ {
	@Override
	protected void process() {
		new AProcedure<CHeroChangeEctype>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final Map<Integer, xbean.HeroesGroupInfo> herogroups = FMap.getEctype(roleid).getHerogroups();
				xbean.HeroesGroupInfo groupInfo = herogroups.get(groupid);
				final int refreshtime = groupInfo.getRefreshtime();
				final List<YuanBao> changecosts = CfgMgr.herosets.changecost;
				// 超过花费集合索引取最大
				final YuanBao cost = refreshtime< changecosts.size() ? changecosts.get(refreshtime) : changecosts.get(changecosts.size() - 1);
				if (cost.amount > 0) {
					ErrorCode err = FCondition.checkA(roleid, cost, 1, By.Hero_Change_Ectype, 0, 0);
					if (err.err()) {
						return error(err);
					}
				}

				groupInfo.setRefreshtime(refreshtime + 1);
				groupInfo.setEctypeid(FMap.randomHeroEctypeId(CfgMgr.herosets.ectypemsg_id.get(groupid)));

				final SHeroChangeEctype resp = new SHeroChangeEctype();
				resp.groupid = groupid;
				resp.ectypeid = groupInfo.getEctypeid();
				response(resp);

				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6574902;

	public int getType() {
		return 6574902;
	}

	public int groupid;

	public CHeroChangeEctype() {
	}

	public CHeroChangeEctype(int _groupid_) {
		this.groupid = _groupid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(groupid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		groupid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CHeroChangeEctype) {
			CHeroChangeEctype _o_ = (CHeroChangeEctype)_o1_;
			if (groupid != _o_.groupid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += groupid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(groupid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CHeroChangeEctype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = groupid - _o_.groupid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

