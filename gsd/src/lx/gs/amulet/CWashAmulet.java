
package lx.gs.amulet;

import cfg.Const;
import cfg.cmd.ConfigId;
import cfg.equip.AmuletConfig;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import xbean.AmuletProperty;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CWashAmulet__ extends xio.Protocol { }

/** 洗练护符
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CWashAmulet extends lx.gs.amulet.__CWashAmulet__ {
	@Override
	protected void process() {
		new AProcedure<CWashAmulet>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleAmuletInfo info = FAmulet.get(roleid);
				ErrorCode errorCode = FAmulet.checkOperation(info, pageid);
				if (errorCode.err()) {
					return error(errorCode);
				}

				AmuletConfig conf = cfg.CfgMgr.amuletconfig;
				int locked = 0;
				xbean.AmuletPage page = info.getPagemap().get(pageid);
				for (AmuletProperty property : page.getPropmap().values()) {
					if (property.getIslock() == Const.TRUE) {
						locked++;
					}
				}
				if (locked > conf.lockcost.size()) {
					locked = conf.lockcost.size();
				}
				//锁定消耗护符
				if (locked > 0) {
					ErrorCode err1 = FCondition.checkA(roleid, conf.lockcost.get(locked - 1), 1, By.Amulet_Wash, -1, -1);
					if (err1.err())
						return error(err1);
				}

				// 根据每日洗练次数消耗
				ErrorCode err = FCondition.checkA(roleid, conf.washcost, 1, By.Amulet_Wash, ConfigId.AMULET_WASH, AmuletConfig.AMULET_DAY_WASH_COUNT_CMD_ID);
				if (err.err()){
					return error(err);
				}

				FAmulet.washAmulet(roleid, page);

				SWashAmulet result = new SWashAmulet();
				result.pageid = pageid;
				page.getLastwashresult().values().forEach(p -> result.washresult.put(p.getPropindex(), FAmulet.convert(p)));
				response(result);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6568907;

	public int getType() {
		return 6568907;
	}

	public int pageid; // 洗练页面

	public CWashAmulet() {
	}

	public CWashAmulet(int _pageid_) {
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
		if (_o1_ instanceof CWashAmulet) {
			CWashAmulet _o_ = (CWashAmulet)_o1_;
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

	public int compareTo(CWashAmulet _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pageid - _o_.pageid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

