package lx.gs.talisman;

import cfg.CfgMgr;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import common.ErrorCode;
import lx.gs.item.FItem;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CWuxingWash__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CWuxingWash extends __CWuxingWash__ {
    @Override
    protected void process() {
        new AProcedure<CWuxingWash>(this) {

            @Override
            protected boolean doProcess() throws Exception {
                xbean.Talisman talisman = FTalisman.getTalisman(roleid, bagtype, pos);
				if(!FTalisman.getModelById(talisman.getModelid()).canuse){
					return false;
				}
                final xbean.RoleTalismanBag info = FTalisman.getDbBag(roleid);
                final cfg.talisman.TalismanFeed conf = CfgMgr.talismanfeed.get(info.getLuckytype());
                if (!FItem.spendItemBindFirst(roleid, cfg.talisman.TalismanFeed.REQUIRE_ITEM, 1, null)) {//洗练消耗五彩石
                    return error(ErrorCode.ITEM_NUMBER_NOT_ENOUGH);
                }

                final int wuxingMaxValue = (int) (CfgMgr.talismanbasic.get(talisman.getModelid()).specialattackrate
                        * CfgMgr.talismanexp.get(talisman.getNormallevel()).specialattackrate
                        * CfgMgr.talismanevlove.get(talisman.getStarlevel()).maturerate);
                if (talisman.getWuxingvalue() >= wuxingMaxValue)
                    return error(ErrorCode.TALISMAN_WUXING_HAS_GOT_MAX);

                final int newWashTimes = info.getLuckywashtimes() + 1;
                info.setLuckywashtimes(newWashTimes);

                final int criticalTimes = FTalisman.genCriticalTimes(conf);
                final int newWuxingValue = Math.min(wuxingMaxValue, FTalisman.genNewWuxingValue(talisman.getWuxingvalue(), criticalTimes, conf));
                talisman.setWuxingvalue(newWuxingValue);
				FTalisman.syncCombatPower(roleid, bagtype, pos);
				FTalisman.bindTalisman(roleid, bagtype, talisman);

				final SWuxingWash re = new SWuxingWash();
                re.bagtype = bagtype;
                re.pos = pos;
                re.criticaltimes = criticalTimes;
                re.wuxingvalue = newWuxingValue;
                re.washtimes = newWashTimes;
				response(re);

				if (info.getLuckywashtimes() >= conf.maxusetime) {
					FTalisman.setLuckyType(info, FTalisman.randomLuckyType());
					response(new SLuckyInfo(info.getLuckytype(), info.getLuckywashtimes()));
				}

                return true;
            }

        }.validateRoleidAndExecute();
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6567167;

	public int getType() {
		return 6567167;
	}

	public int bagtype;
	public int pos;

	public CWuxingWash() {
	}

	public CWuxingWash(int _bagtype_, int _pos_) {
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
		if (_o1_ instanceof CWuxingWash) {
			CWuxingWash _o_ = (CWuxingWash)_o1_;
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

	public int compareTo(CWuxingWash _o_) {
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

