
package lx.gs.equip.accessory;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.cmd.FCondition;
import lx.gs.equip.FEquip;
import common.ErrorCode;
import lx.gs.logger.By;
import xbean.Equip;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CRestoreAccessoryWashResult__ extends xio.Protocol { }

/** 取消洗练结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CRestoreAccessoryWashResult extends __CRestoreAccessoryWashResult__ {
	@Override
	protected void process() {
		new AProcedure<CRestoreAccessoryWashResult>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				Equip equip = FEquip.getEquip(roleid, bagtype, pos);
				if(!FEquip.isAccessory(equip)){
					return error(ErrorCode.PARAM_ERROR);
				}
				//消耗货币
				cfg.equip.AccessoryConfig aconf = cfg.CfgMgr.accessoryconfig;
				ErrorCode err = FCondition.checkA(roleid, aconf.abandoncost, 1, By.Accessory_Wash, 0, 0);
				if(err.err()) return error(err);

				FEquip.clearWash(equip.getAccessory().getLastwashrecord());

				response(new SRestoreAccessoryWashResult(bagtype, pos));
				return true;
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567548;

	public int getType() {
		return 6567548;
	}

	public int bagtype; // 饰品所在包裹类型
	public int pos; // 饰品所在位置

	public CRestoreAccessoryWashResult() {
	}

	public CRestoreAccessoryWashResult(int _bagtype_, int _pos_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CRestoreAccessoryWashResult) {
			CRestoreAccessoryWashResult _o_ = (CRestoreAccessoryWashResult)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CRestoreAccessoryWashResult _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype - _o_.bagtype;
		if (0 != _c_) return _c_;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

