
package lx.gs.amulet;

import cfg.Const;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.FMap;
import lx.gs.role.FRoleAttr;
import map.msg.XChangeAmulet;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CApplyAmuletWashResult__ extends xio.Protocol { }

/** 确认应用洗练结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CApplyAmuletWashResult extends lx.gs.amulet.__CApplyAmuletWashResult__ {
	@Override
	protected void process() {
		new AProcedure<CApplyAmuletWashResult>(this){
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleAmuletInfo info = FAmulet.get(roleid);

				ErrorCode errorCode = FAmulet.checkOperation(info, pageid);
				if (errorCode.err()) {
					return error(errorCode);
				}

				xbean.AmuletPage page = info.getPagemap().get(pageid);
				boolean isChange = FAmulet.applyWashResult(page);

				SApplyAmuletWashResult result = new SApplyAmuletWashResult();
				result.pageid = pageid;

				if(isChange){
					FMap.dispatchMessageInProcedure(roleid, new XChangeAmulet(FAmulet.getPrfsSkillAddInfo(roleid)));

					page.getPropmap().values().stream()
							.filter(p -> p.getIslock() == Const.FALSE)
							.forEach(p -> result.changeprop.put(p.getPropindex(), FAmulet.convert(p)));

					FRoleAttr.updateRoleCombatPower(roleid);
				}

				response(result);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6584825;

	public int getType() {
		return 6584825;
	}

	public int pageid; // 洗练页面

	public CApplyAmuletWashResult() {
	}

	public CApplyAmuletWashResult(int _pageid_) {
		this.pageid = _pageid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pageid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pageid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CApplyAmuletWashResult) {
			CApplyAmuletWashResult _o_ = (CApplyAmuletWashResult)_o1_;
			if (pageid != _o_.pageid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pageid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pageid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CApplyAmuletWashResult _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pageid - _o_.pageid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

