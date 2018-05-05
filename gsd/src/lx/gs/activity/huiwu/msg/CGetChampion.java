
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetChampion__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetChampion extends __CGetChampion__ {
	@Override
	protected void process() {
		new AProcedure<CGetChampion>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final xbean.HuiWuPriviousTerm priviousTerm = xtable.Huiwupriviousterms.get(termid);
				final xbean.HuiWuChampion champion = priviousTerm.getChampions().get(profession);

				final SGetChampion re = new SGetChampion();
				re.ctx = ctx;
				re.termid = termid;
				re.profession = profession;
                if(champion != null) {
                    re.championinfo.createtime = priviousTerm.getEndtime();
                    re.championinfo.awardword = champion.getAwardword();
                    re.championinfo.worshipnum = champion.getWorshipnum();
                    FRole.genRoleShowInfo(champion.getRoleid(), re.championinfo.showinfo);
                }
				response(re);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567049;

	public int getType() {
		return 6567049;
	}

	public int termid;
	public int profession;
	public int ctx;

	public CGetChampion() {
	}

	public CGetChampion(int _termid_, int _profession_, int _ctx_) {
		this.termid = _termid_;
		this.profession = _profession_;
		this.ctx = _ctx_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(termid);
		_os_.marshal(profession);
		_os_.marshal(ctx);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		termid = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		ctx = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetChampion) {
			CGetChampion _o_ = (CGetChampion)_o1_;
			if (termid != _o_.termid) return false;
			if (profession != _o_.profession) return false;
			if (ctx != _o_.ctx) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += termid;
		_h_ += profession;
		_h_ += ctx;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(termid).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(ctx).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetChampion _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = termid - _o_.termid;
		if (0 != _c_) return _c_;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		_c_ = ctx - _o_.ctx;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

