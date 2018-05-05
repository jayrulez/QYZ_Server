package lx.gs.talisman;

import cfg.CfgMgr;
import cfg.talisman.AwakeInfo;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bag.TalismanBag;
import lx.gs.event.EventModule;
import lx.gs.event.TalismanAwakeLevelUpEvent;
import lx.gs.logger.By;
import xbean.Talisman;
import xdb.Trace;

import java.util.ArrayList;
import java.util.List;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CTalismanAwake__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CTalismanAwake extends __CTalismanAwake__ {
	@Override
	protected void process() {
		new AProcedure<CTalismanAwake>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				Talisman talisman = FTalisman.getTalisman(roleid, bagtype, pos);
				if(talisman == null){
					return false;
				}
				if(!FTalisman.getModelById(talisman.getModelid()).canuse
						|| !CfgMgr.talismanawake.containsKey(talisman.getModelid())){
					return false;
				}
				final AwakeInfo acfg = CfgMgr.talismanawake.get(talisman.getModelid()).awakeinfo.get(talisman.getAwakelevel());

				TalismanBag talismanBag = FTalisman.getTalismanBag(roleid);
				List<Talisman> talismanList = new ArrayList<Talisman>();
				for (Talisman temp : talismanBag.getItems()) {
					if (temp != talisman
							&& temp.getModelid() == talisman.getModelid()
							&& FTalisman.isRawTalisman(temp)) {
						talismanList.add(temp);
					}
					if (talismanList.size() >= acfg.talismancost) break;
				}

				if (talismanList.size() < acfg.talismancost) {
					Trace.debug("awake roleid:{} talisman modelid:{} cost not enough", roleid, talisman.getModelid());
					error(ErrorCode.ITEM_NUMBER_NOT_ENOUGH);
					return false;
				}
				talismanList.forEach(temp -> {
					FBag.deleteItemByPos(talismanBag, temp.getPos(), By.Talisman_Awake);
				});

				talisman.setAwakelevel(talisman.getAwakelevel() + 1);
				EventModule.INSTANCE.broadcastEvent(new TalismanAwakeLevelUpEvent(roleid, talisman.getAwakelevel(), talisman.getModelid()));
				FTalisman.syncCombatPower(roleid, bagtype, pos);
				FTalisman.bindTalisman(roleid, bagtype, talisman);

				STalismanAwake re = new STalismanAwake();
				re.bagtype = bagtype;
				re.pos = pos;
				re.newlevel = talisman.getAwakelevel();
				response(re);
				return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6577320;

	public int getType() {
		return 6577320;
	}

	public int bagtype;
	public int pos;

	public CTalismanAwake() {
	}

	public CTalismanAwake(int _bagtype_, int _pos_) {
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
		if (_o1_ instanceof CTalismanAwake) {
			CTalismanAwake _o_ = (CTalismanAwake)_o1_;
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

	public int compareTo(CTalismanAwake _o_) {
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

