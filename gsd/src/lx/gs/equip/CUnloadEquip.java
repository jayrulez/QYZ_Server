
package lx.gs.equip;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnloadEquip__ extends xio.Protocol { }

/** 从身上卸载装备到包裹
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnloadEquip extends __CUnloadEquip__ {
	@Override
	protected void process() {
		new AProcedure<CUnloadEquip>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				if(!FEquip.getBodyBag(roleid).unload(pos, FEquip.getEquipBag(roleid))){
					return error(ErrorCode.BAG_FULL);
				}
				return true;
			}
		}.validateRoleidAndExecute();	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581503;

	public int getType() {
		return 6581503;
	}

	public int pos; // 格子号

	public CUnloadEquip() {
	}

	public CUnloadEquip(int _pos_) {
		this.pos = _pos_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pos);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pos = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnloadEquip) {
			CUnloadEquip _o_ = (CUnloadEquip)_o1_;
			if (pos != _o_.pos) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pos;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUnloadEquip _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

