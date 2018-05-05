
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.HeroesMapModule;
import lx.gs.role.FRole;
import xbean.HeroesGroupMemInfo;
import xbean.RoleMemInfo;
import xdb.Trace;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CHeroGainAward__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CHeroGainAward extends __CHeroGainAward__ {
    @Override
    protected void process() {
        new AProcedure<CHeroGainAward>(this) {
            @Override
            protected boolean doProcess() throws Exception {
				Trace.info("gain heroes award {} {} {}", roleid, groupid, ectypeid);
                final RoleMemInfo roleMemInfo = FRole.getRoleMemInfo(roleid);
                if (!roleMemInfo.getHerogroupmeminfos().containsKey(groupid)) {
                    return error(ErrorCode.HERO_ECTYPE_NO_AWARD_WITH_GROUPID);
                }
                final HeroesGroupMemInfo info = roleMemInfo.getHerogroupmeminfos().get(groupid);
                if (info.getEctypeid() != ectypeid || info.getBonus().size() == 0) {
                    return error(ErrorCode.HERO_ECTYPE_NO_AWARD_WITH_ECTYPEID);
                }
                HeroesMapModule.addHeroesBonuses(info, roleid);
                // 清除该组副本相关数据
                roleMemInfo.getHerogroupmeminfos().remove(groupid);
                response(new SHeroGainAward());
                return true;
            }
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577383;

	public int getType() {
		return 6577383;
	}

	public int groupid;
	public int ectypeid;

	public CHeroGainAward() {
	}

	public CHeroGainAward(int _groupid_, int _ectypeid_) {
		this.groupid = _groupid_;
		this.ectypeid = _ectypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(groupid);
		_os_.marshal(ectypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		groupid = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CHeroGainAward) {
			CHeroGainAward _o_ = (CHeroGainAward)_o1_;
			if (groupid != _o_.groupid) return false;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += groupid;
		_h_ += ectypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(groupid).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CHeroGainAward _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = groupid - _o_.groupid;
		if (0 != _c_) return _c_;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

