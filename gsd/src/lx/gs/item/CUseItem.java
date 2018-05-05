
package lx.gs.item;

import cfg.item.ItemBasic;
import cfg.item.ItemMedicine;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.map.FMap;
import map.msg.XUseItem;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUseItem__ extends xio.Protocol { }

/** 使用物品
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUseItem extends __CUseItem__ {
	@Override
	protected void process() {
		new AProcedure<CUseItem>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				xbean.Item item = FItem.getItemBag(roleid).getByPosition(pos);
				if(item == null){
					return false;
				}
				ItemBasic model = FItem.getModelById(item.getModelid());
				if(model.getTypeId() == ItemMedicine.TYPEID){
					FMap.dispatchMessageInProcedure(roleid, new XUseItem(pos, usenumber));
					return true;
				}
				ErrorCode errorCode = FItem.useItemByPos(roleid, pos, usenumber);
				return errorCode.ok() || error(errorCode);
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573540;

	public int getType() {
		return 6573540;
	}

	public int pos;
	public int usenumber;

	public CUseItem() {
	}

	public CUseItem(int _pos_, int _usenumber_) {
		this.pos = _pos_;
		this.usenumber = _usenumber_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pos);
		_os_.marshal(usenumber);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pos = _os_.unmarshal_int();
		usenumber = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUseItem) {
			CUseItem _o_ = (CUseItem)_o1_;
			if (pos != _o_.pos) return false;
			if (usenumber != _o_.usenumber) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pos;
		_h_ += usenumber;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pos).append(",");
		_sb_.append(usenumber).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUseItem _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		_c_ = usenumber - _o_.usenumber;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

