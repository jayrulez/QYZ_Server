
package lx.gs.equip.accessory;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.msg.SItemBind;
import lx.gs.equip.FEquip;
import xbean.Equip;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CApplyAccessoryWashResult__ extends xio.Protocol { }

/** 确定接受饰品洗练结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CApplyAccessoryWashResult extends __CApplyAccessoryWashResult__ {
	@Override
	protected void process() {
		new AProcedure<CApplyAccessoryWashResult>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				Equip equip = FEquip.getEquip(roleid, bagtype, pos);
				if(!FEquip.isAccessory(equip)){
					return error(ErrorCode.PARAM_ERROR);
				}
				boolean isBind = equip.getIsbind();

				//消息处理好再执行替换，下面会清空洗练信息
				SApplyAccessoryWashResult result = new SApplyAccessoryWashResult();
				result.bagtype = bagtype;
				result.pos = pos;
				result.oldpropindex = equip.getAccessory().getLastwashrecord().getOldpropindex() + 1;
				result.newprop = FEquip.dbProp2Proto(equip.getAccessory().getLastwashrecord().getNewprop());

				boolean isSucc = FEquip.applyWash(equip);
				if(isSucc){
					if(!isBind && equip.getIsbind()){
						response(new SItemBind(bagtype, pos));
					}
					response(result);
				}
				return isSucc;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6572935;

	public int getType() {
		return 6572935;
	}

	public int bagtype; // 饰品所在包裹类型
	public int pos; // 饰品所在位置

	public CApplyAccessoryWashResult() {
	}

	public CApplyAccessoryWashResult(int _bagtype_, int _pos_) {
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
		if (_o1_ instanceof CApplyAccessoryWashResult) {
			CApplyAccessoryWashResult _o_ = (CApplyAccessoryWashResult)_o1_;
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

	public int compareTo(CApplyAccessoryWashResult _o_) {
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

