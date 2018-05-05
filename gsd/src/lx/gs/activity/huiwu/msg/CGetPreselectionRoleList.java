
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.activity.huiwu.FHuiWu;
import xbean.HuiWuCurTerm;
import xtable.Roleattrs;
import xtable.Roleinfos;

import java.util.Map;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetPreselectionRoleList__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetPreselectionRoleList extends __CGetPreselectionRoleList__ {
	@Override
	protected void process() {
		new AProcedure<CGetPreselectionRoleList>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				final HuiWuCurTerm info = FHuiWu.getCurTermInfo();

				final SGetPreselectionRoleList re = new SGetPreselectionRoleList();
				re.profession = profession;
				for (Map.Entry<Long, Integer> e : info.getTerminfobyprofession().get(profession).getPreselection2roleidbeguessnums().entrySet()) {
					final long rid = e.getKey();
					re.roles.add(new  PreselectionRole(rid, Roleinfos.selectName(rid), Roleattrs.selectTotalcombatpower(rid), e.getValue()));
				}
				response(re);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586220;

	public int getType() {
		return 6586220;
	}

	public int profession;

	public CGetPreselectionRoleList() {
	}

	public CGetPreselectionRoleList(int _profession_) {
		this.profession = _profession_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(profession);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		profession = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetPreselectionRoleList) {
			CGetPreselectionRoleList _o_ = (CGetPreselectionRoleList)_o1_;
			if (profession != _o_.profession) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += profession;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(profession).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetPreselectionRoleList _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

