package lx.gs.fragment;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bag.FragmentBag;
import lx.gs.logger.By;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CCompoundFragment__ extends xio.Protocol { }

/** 碎片合成
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CCompoundFragment extends __CCompoundFragment__ {
    @Override
    protected void process() {
        new AProcedure<CCompoundFragment>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                FragmentBag fragmentBag = FFragment.getFragmentBag(roleid);
                xbean.Fragment fragment = fragmentBag.getByPosition(pos);
                cfg.equip.Fragment model = FFragment.getModelById(fragment.getModelid());

                int currCount = fragment.getCount();
                boolean isBind = fragment.getIsbind();
                boolean newItemBind = isBind;

                if (currCount >= model.number) {
					if(FBag.delItemByPosNum(fragmentBag, pos, model.number, By.Fragment_Compount) == null){
						return error(ErrorCode.FRAGMENT_NOT_ENOUGH);
					}
                } else {
					if (fragmentBag.countItem(fragment.getModelid()) < model.number) {
						return error(ErrorCode.FRAGMENT_NOT_ENOUGH);
					}

                    int sameTypeCount = fragmentBag.countItem(fragment.getModelid(), isBind);
                    if (!isBind && sameTypeCount >= model.number) {
						if(FBag.deleteItemByPos(fragmentBag, pos, By.Fragment_Compount) == null
								|| !FBag.deleteUnBind(fragmentBag, fragment.getModelid(), model.number - currCount, By.Fragment_Compount))
							return error(ErrorCode.FRAGMENT_NOT_ENOUGH);
                    } else {
                        int needRemain = model.number;
                        if (isBind) {
							if(FBag.deleteItemByPos(fragmentBag, pos, By.Fragment_Compount) == null){
								return error(ErrorCode.FRAGMENT_NOT_ENOUGH);
							}
							needRemain -= currCount;
                        }
						if(!FBag.deleteBindFirst(fragmentBag, fragment.getModelid(), needRemain, By.Fragment_Compount)){
							return error(ErrorCode.FRAGMENT_NOT_ENOUGH);
						}
                        newItemBind = true;
                    }
                }
                return FBag.addItemToBag(roleid, model.equipID, 1, newItemBind, By.Fragment_Compount);
			}
        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555909;

	public int getType() {
		return 6555909;
	}

	public int pos; // 位置

	public CCompoundFragment() {
	}

	public CCompoundFragment(int _pos_) {
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
		if (_o1_ instanceof CCompoundFragment) {
			CCompoundFragment _o_ = (CCompoundFragment)_o1_;
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

	public int compareTo(CCompoundFragment _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = pos - _o_.pos;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

