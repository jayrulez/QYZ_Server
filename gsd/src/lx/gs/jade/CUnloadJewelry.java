
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import common.ErrorCode;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnloadJewelry__ extends xio.Protocol { }

/** 卸载宝珠
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnloadJewelry extends lx.gs.jade.__CUnloadJewelry__ {
	@Override
	protected void process() {
		new AProcedure<CUnloadJewelry>(this){

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleJadeInfo jadeinfo = FJade.getRoleJadeInfo(roleid);

				if(jadeinfo.getHolenum() < param.position){
					return error(ErrorCode.ERROR_JADE_POSITION);
				}

				xbean.Jewelry jewelry = jadeinfo.getJewelry().remove(param.position);
				jadeinfo.getJewelrybag().add(jewelry);
				SUnloadJewelry result = new SUnloadJewelry();
				result.index = jadeinfo.getJewelrybag().size();
				result.position = param.position;
				result.jewelry = FJade.makeProtocolJewelry(jewelry);
						
				response(result);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564221;

	public int getType() {
		return 6564221;
	}

	public int position; // 宝珠索引

	public CUnloadJewelry() {
	}

	public CUnloadJewelry(int _position_) {
		this.position = _position_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		position = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnloadJewelry) {
			CUnloadJewelry _o_ = (CUnloadJewelry)_o1_;
			if (position != _o_.position) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += position;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUnloadJewelry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = position - _o_.position;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

