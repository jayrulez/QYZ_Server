package lx.gs.talisman;

import cfg.CfgMgr;
import cfg.talisman.TalismanBasic;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import gs.Utils;
import lx.gs.bag.FBag;
import lx.gs.bag.TalismanBag;
import lx.gs.logger.By;
import xbean.Talisman;
import xdb.Trace;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CAddStarExp__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CAddStarExp extends __CAddStarExp__ {
    @Override
    protected void process() {
        new AProcedure<CAddStarExp>(this) {
            @Override
            protected boolean doProcess() throws Exception {
                Talisman talisman = FTalisman.getTalisman(roleid, bagtype, pos);
				if(!FTalisman.getModelById(talisman.getModelid()).canuse){
					return false;
				}
                TalismanBag talismanBag = FTalisman.getTalismanBag(roleid);
				if(Utils.isNull(costtalisman)){
					return false;
				}
				if(talisman.getStarlevel() >= FTalisman.TALISMAN_STAR_MAX_LEVEL){
					return error(ErrorCode.TALISMAN_STAR_MAX_LEVEL);
				}
                int totalExp = 0;
                for (int pos : costtalisman) {
                    Talisman cost = FBag.deleteItemByPos(talismanBag, pos, By.Talisman_Star_Evolve);
                    if (cost == null) {
                        return error(ErrorCode.PARAM_ERROR);
                    }
                    if (!FTalisman.isRawTalisman(cost)) {
                        Trace.error("roleid:{} cost talisman pos:{} not raw", roleid, pos);
                        return false;
                    }
                    TalismanBasic conf = CfgMgr.talismanbasic.get(cost.getModelid());
                    totalExp += conf.qualityexp;
                }
                FTalisman.addStarExp(roleid, talisman, totalExp, bagtype);
				FTalisman.bindTalisman(roleid, bagtype, talisman);

                final SAddStarExp ret = new SAddStarExp();
                ret.bagtype = bagtype;
                ret.pos = pos;
                ret.newlevel = talisman.getStarlevel();
                ret.newexp = talisman.getStarexp();
                return response(ret);
            }

        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579572;

	public int getType() {
		return 6579572;
	}

	public int bagtype;
	public int pos;
	public java.util.HashSet<Integer> costtalisman; // 消耗的法宝

	public CAddStarExp() {
		costtalisman = new java.util.HashSet<Integer>();
	}

	public CAddStarExp(int _bagtype_, int _pos_, java.util.HashSet<Integer> _costtalisman_) {
		this.bagtype = _bagtype_;
		this.pos = _pos_;
		this.costtalisman = _costtalisman_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bagtype);
		_os_.marshal(pos);
		_os_.compact_uint32(costtalisman.size());
		for (Integer _v_ : costtalisman) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bagtype = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			costtalisman.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CAddStarExp) {
			CAddStarExp _o_ = (CAddStarExp)_o1_;
			if (bagtype != _o_.bagtype) return false;
			if (pos != _o_.pos) return false;
			if (!costtalisman.equals(_o_.costtalisman)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bagtype;
		_h_ += pos;
		_h_ += costtalisman.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bagtype).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(costtalisman).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

