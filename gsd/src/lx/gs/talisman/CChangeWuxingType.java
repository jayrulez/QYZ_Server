package lx.gs.talisman;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CChangeWuxingType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CChangeWuxingType extends __CChangeWuxingType__ {
    @Override
    protected void process() {
//        new AProcedure<CChangeWuxingType>(this) {
//
//            @Override
//            protected boolean doProcess() throws Exception {
//                xbean.Talisman talisman = FTalisman.getTalisman(roleid, bagtype, pos);
//
//                //消耗造化值改变属性
//                if (!FRole.checkCostCurrency(roleid, cfg.currency.CurrencyType.ZaoHua, cfg.talisman.TalismanFeed.CHANGE_PROPERTY_COST, By.Talisman_Xilian)) {
//                    return error(ErrorCode.CURRENCY_IS_NOT_ENOUGH);
//                }
//                talisman.setWuxingtype(FTalisman.randomWuXingType());
//				FTalisman.bindTalisman(roleid, bagtype, talisman);
//
//                SChangeWuxingType re = new SChangeWuxingType();
//                re.bagtype = bagtype;
//                re.pos = pos;
//                re.wuxingtype = talisman.getWuxingtype();
//                return response(re);
//            }
//        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575201;

	public int getType() {
		return 6575201;
	}

	public int bagtype;
	public int pos;

	public CChangeWuxingType() {
	}

	public CChangeWuxingType(int _bagtype_, int _pos_) {
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
		if (_o1_ instanceof CChangeWuxingType) {
			CChangeWuxingType _o_ = (CChangeWuxingType)_o1_;
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

	public int compareTo(CChangeWuxingType _o_) {
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

