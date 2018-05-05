
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import common.ErrorCode;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CLoadJewelry__ extends xio.Protocol { }

/** 装配宝珠
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CLoadJewelry extends lx.gs.jade.__CLoadJewelry__ {
	@Override
	protected void process() {
		new AProcedure<CLoadJewelry>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				xbean.RoleJadeInfo jadeinfo = FJade.getRoleJadeInfo(roleid);

				if (jadeinfo.getHolenum() < param.position) {
					return error(ErrorCode.ERROR_JADE_POSITION);
				}

				if (jadeinfo.getJewelrybag().size() < param.index) {
					return error(ErrorCode.ERROR_JEWELRY_INDEX);
				}

				xbean.Jewelry jewelry = jadeinfo.getJewelrybag().remove(param.index - 1);
				if (jewelry == null) {
					return error(ErrorCode.ERROR_JEWELRY_INDEX);
				}

				xbean.Jewelry oldjewelry = jadeinfo.getJewelry().put(param.position, jewelry);
				if(oldjewelry!=null){
					jadeinfo.getJewelrybag().add(param.index - 1, oldjewelry);
				}
				
				SLoadJewelry result = new SLoadJewelry();
				result.index = param.index;
				result.position = param.position;
				result.jewelry = FJade.makeProtocolJewelry(jewelry);
				result.oldjewelry = FJade.makeProtocolJewelry(oldjewelry);

				response(result);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571797;

	public int getType() {
		return 6571797;
	}

	public int index; // 宝珠在包裹中的编号
	public int position; // 宝珠玉佩上的位置

	public CLoadJewelry() {
	}

	public CLoadJewelry(int _index_, int _position_) {
		this.index = _index_;
		this.position = _position_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(index);
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		index = _os_.unmarshal_int();
		position = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CLoadJewelry) {
			CLoadJewelry _o_ = (CLoadJewelry)_o1_;
			if (index != _o_.index) return false;
			if (position != _o_.position) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += index;
		_h_ += position;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(index).append(",");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CLoadJewelry _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = index - _o_.index;
		if (0 != _c_) return _c_;
		_c_ = position - _o_.position;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
