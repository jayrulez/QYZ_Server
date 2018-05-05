
package lx.gs.activity.huiwu.msg;

import cfg.CfgMgr;
import cfg.cmd.CmdId;
import cfg.cmd.ConfigId;
import cfg.item.EItemBindType;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.activity.huiwu.FHuiWu;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.error.FError;
import lx.gs.logger.By;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CWorship__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CWorship extends __CWorship__ {
	@Override
	protected void process() {
		new AProcedure<CWorship>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final xbean.HuiWuPriviousTerm priviousTerm = xtable.Huiwupriviousterms.get(termid);
                FHuiWu.checkAttend(roleid);
                final ErrorCode err = FCondition.checkA(roleid, CfgMgr.huiwu.daiylyworshiptimes, 1, By.HuiWu_Worship, ConfigId.HUIWU, CmdId.WORSHIP);
                if(err.err())
                    return error(err);

                final xbean.HuiWuChampion champion = priviousTerm.getChampions().get(profession);
				final int newWorshipNum = champion.getWorshipnum() + 1;
				champion.setWorshipnum(newWorshipNum);
				final SWorship re = new SWorship();
				re.worshipnum = newWorshipNum;
                if(!FBonus.genAndAddBonus(roleid, CfgMgr.huiwu.dailyworshipaward, common.Bonus.BindType.BIND, re.bonus, By.HuiWu_Worship))
                    return false;
				response(re);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572068;

	public int getType() {
		return 6572068;
	}

	public int termid;
	public int profession;

	public CWorship() {
	}

	public CWorship(int _termid_, int _profession_) {
		this.termid = _termid_;
		this.profession = _profession_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(profession);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CWorship) {
			CWorship _o_ = (CWorship)_o1_;
			if (termid != _o_.termid) return false;
			if (profession != _o_.profession) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += profession;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CWorship _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = termid - _o_.termid;
		if (0 != _c_) return _c_;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

