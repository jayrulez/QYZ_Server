
package lx.gs.treasurebowl;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.EquipBag;
import lx.gs.bag.FBag;
import lx.gs.equip.FEquip;
import lx.gs.logger.By;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTreasureBowlBreak__ extends xio.Protocol { }

/** 聚宝盆系统拆解装备，得到灵晶
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTreasureBowlBreak extends __CTreasureBowlBreak__ {
	@Override
	protected void process() {
		new AProcedure<CTreasureBowlBreak>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if(poslist.isEmpty()){
					return error(ErrorCode.PARAM_ERROR);
				}
				EquipBag equipBag = FEquip.getEquipBag(roleid);
				long totalLingJing = 0;
				for(int pos : poslist){
					xbean.Equip equip = FBag.deleteItemByPos(equipBag, pos, By.TreasureBowl);
					if(equip == null) return error(ErrorCode.PARAM_ERROR);
					cfg.equip.Equip model = FEquip.getEquipModel(equip);
					totalLingJing += model.break2lingjing.amount;
				}
				FRole.addCurrency(roleid, cfg.currency.CurrencyType.LingJing, totalLingJing, By.TreasureBowl);
				return response(new STreasureBowlBreak(totalLingJing));
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6566059;

	public int getType() {
		return 6566059;
	}

	public java.util.ArrayList<Integer> poslist;

	public CTreasureBowlBreak() {
		poslist = new java.util.ArrayList<Integer>();
	}

	public CTreasureBowlBreak(java.util.ArrayList<Integer> _poslist_) {
		this.poslist = _poslist_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(poslist.size());
		for (Integer _v_ : poslist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			poslist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CTreasureBowlBreak) {
			CTreasureBowlBreak _o_ = (CTreasureBowlBreak)_o1_;
			if (!poslist.equals(_o_.poslist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += poslist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(poslist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

