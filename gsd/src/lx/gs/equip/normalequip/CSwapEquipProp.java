
package lx.gs.equip.normalequip;

import cfg.Const;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.msg.SItemBind;
import lx.gs.equip.FEquip;
import xbean.Equip;
import xdb.Transaction;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSwapEquipProp__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSwapEquipProp extends lx.gs.equip.normalequip.__CSwapEquipProp__ {
	@Override
	protected void process() {
		new AProcedure<CSwapEquipProp>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				//可以选择只交换一种，协议需要修改，处理也需要修改。只要两个装备中有一个被绑定，继承成功后两个都变为绑定的。（数据结构定义要改，碎片也有绑定非绑定属性）
				Equip e1 = FEquip.getEquip(roleid, bagtype1, pos1);
				Equip e2 = FEquip.getEquip(roleid, bagtype2, pos2);

				ErrorCode errorCode = FEquip.swapEquipProp(e1, e2, isswapanneal == Const.TRUE, isswapperfuse == Const.TRUE);

				if(errorCode.err()){
					return error(errorCode);
				}

				if(e1.getIsbind() || e2.getIsbind()){
					if(!e1.getIsbind()){
						e1.setIsbind(true);
						Transaction.tsendWhileCommit(roleid, new SItemBind(bagtype1, e1.getPosition()));
					}
					if(!e2.getIsbind()){
						e2.setIsbind(true);
						Transaction.tsendWhileCommit(roleid, new SItemBind(bagtype2, e2.getPosition()));
					}
				}

				SSwapEquipProp result = new SSwapEquipProp();
				result.bagtype1 = bagtype1;
				result.bagtype2 = bagtype2;
				result.pos1 = pos1;
				result.pos2 = pos2;
				result.isswapanneal = isswapanneal;
				result.isswapperfuse = isswapperfuse;
				return response(result);
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555432;

	public int getType() {
		return 6555432;
	}

	public int bagtype1;
	public int pos1; // 装备1id
	public int bagtype2;
	public int pos2; // 装备2id
	public int isswapanneal; // 是否交换炼器等级
	public int isswapperfuse; // 是否交换追加等级

	public CSwapEquipProp() {
	}

	public CSwapEquipProp(int _bagtype1_, int _pos1_, int _bagtype2_, int _pos2_, int _isswapanneal_, int _isswapperfuse_) {
		this.bagtype1 = _bagtype1_;
		this.pos1 = _pos1_;
		this.bagtype2 = _bagtype2_;
		this.pos2 = _pos2_;
		this.isswapanneal = _isswapanneal_;
		this.isswapperfuse = _isswapperfuse_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype1);
		_os_.marshal(pos1);
		_os_.marshal(bagtype2);
		_os_.marshal(pos2);
		_os_.marshal(isswapanneal);
		_os_.marshal(isswapperfuse);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype1 = _os_.unmarshal_int();
		pos1 = _os_.unmarshal_int();
		bagtype2 = _os_.unmarshal_int();
		pos2 = _os_.unmarshal_int();
		isswapanneal = _os_.unmarshal_int();
		isswapperfuse = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSwapEquipProp) {
			CSwapEquipProp _o_ = (CSwapEquipProp)_o1_;
			if (bagtype1 != _o_.bagtype1) return false;
			if (pos1 != _o_.pos1) return false;
			if (bagtype2 != _o_.bagtype2) return false;
			if (pos2 != _o_.pos2) return false;
			if (isswapanneal != _o_.isswapanneal) return false;
			if (isswapperfuse != _o_.isswapperfuse) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype1;
		_h_ += pos1;
		_h_ += bagtype2;
		_h_ += pos2;
		_h_ += isswapanneal;
		_h_ += isswapperfuse;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype1).append(",");
		_sb_.append(pos1).append(",");
		_sb_.append(bagtype2).append(",");
		_sb_.append(pos2).append(",");
		_sb_.append(isswapanneal).append(",");
		_sb_.append(isswapperfuse).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CSwapEquipProp _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bagtype1 - _o_.bagtype1;
		if (0 != _c_) return _c_;
		_c_ = pos1 - _o_.pos1;
		if (0 != _c_) return _c_;
		_c_ = bagtype2 - _o_.bagtype2;
		if (0 != _c_) return _c_;
		_c_ = pos2 - _o_.pos2;
		if (0 != _c_) return _c_;
		_c_ = isswapanneal - _o_.isswapanneal;
		if (0 != _c_) return _c_;
		_c_ = isswapperfuse - _o_.isswapperfuse;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

