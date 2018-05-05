package lx.gs.talisman;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import lx.gs.bag.BodyTalismanBag;
import lx.gs.bag.TalismanBag;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CUnEquipTalisman__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CUnEquipTalisman extends __CUnEquipTalisman__ {
    @Override
    protected void process() {
        new AProcedure<CUnEquipTalisman>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                BodyTalismanBag bodyTalismanBag = FTalisman.getBodyTalismanBag(roleid);
                TalismanBag talismanBag = FTalisman.getTalismanBag(roleid);
                return bodyTalismanBag.unload(talismanBag);
            }

        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6575471;

	public int getType() {
		return 6575471;
	}


	public CUnEquipTalisman() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CUnEquipTalisman) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CUnEquipTalisman _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

