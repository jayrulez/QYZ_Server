package lx.gs.talisman;

import cfg.bag.BagType;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bag.BagModule;
import lx.gs.bag.BodyTalismanBag;
import lx.gs.bag.TalismanBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CEquipTalisman__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CEquipTalisman extends __CEquipTalisman__ {
    @Override
    protected void process() {
        new AProcedure<CEquipTalisman>(this) {
            @Override
            protected boolean doProcess() throws Exception {
				if(!BagModule.INSTANCE.isSlotUsable(roleid, BagType.TALISMAN_BODY, BodyTalismanBag.SINGLE_POS_INDEX)){
					return error(LocationType.CENTER, TipsCode.MODULE_IS_LOCK);
				}

                TalismanBag talismanBag = FTalisman.getTalismanBag(roleid);
                BodyTalismanBag bodyTalismanBag = FTalisman.getBodyTalismanBag(roleid);
				xbean.Talisman talisman = talismanBag.getByPosition(pos);
				if(!FTalisman.getModelById(talisman.getModelid()).canuse){
					return false;
				}
				FTalisman.bindTalisman(roleid, BagType.TALISMAN, talisman);

				return bodyTalismanBag.load(talismanBag, pos);
            }

        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579245;

	public int getType() {
		return 6579245;
	}

	public int pos;

	public CEquipTalisman() {
	}

	public CEquipTalisman(int _pos_) {
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
		if (_o1_ instanceof CEquipTalisman) {
			CEquipTalisman _o_ = (CEquipTalisman)_o1_;
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

	public int compareTo(CEquipTalisman _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

