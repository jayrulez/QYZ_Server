
package lx.gs.bonus.msg;

import cfg.bonus.BONUSTYPE;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FAction;
import lx.gs.cmd.FCmd;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.pet.FPet;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetWishGift__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetWishGift extends __CGetWishGift__ {
	@Override
	protected void process() {
		new AProcedure<CGetWishGift>(this) {

			@Override
			protected boolean doProcess() throws Exception {
                int vipLevel = xtable.Roleinfos.selectViplevel(roleid);
                xbean.RoleWelfareInfo welfare = FBonus.get(roleid);
                if(welfare.getWishtimes() >= cfg.CfgMgr.bonusconfig.viptimes.get(vipLevel)){
                    return error(ErrorCode.NO_LEFT_TIME); //有一次免费许愿机会,如果许愿次数达到上限后就不让许愿了
                }
                //现在传的petid是策划配置id
                int itemKey = cfg.CfgMgr.bonusconfig.vowitem;
                if(!FItem.spendItemBindFirst(roleid, itemKey, 1, By.Star_Wish)){
                    return error(ErrorCode.NOT_ENOUGH_BAODIE);
                }
                if(FPet.getPetByModelId(roleid, (int)petid) == null){
                    return error(ErrorCode.PET_NOT_FOUND);
                }
//				if (System.currentTimeMillis() - welfare.getLastwishtime() < cfg.CfgMgr.bonusconfig.vowtime * 1000) {
//					return error(ErrorCode.NOT_ENOUGH_WISH_TIME);
//				}
//				final long petId = welfare.getLastwishpetid();
//				if(petId == 0){
//					return error(ErrorCode.NOT_PUT_PET);
//				}
                SGetWishGift response = new SGetWishGift();
                welfare.setWishtimes(welfare.getWishtimes() + 1);
                welfare.setLastwishtime(0L);  //重置许愿时间
                welfare.setLastwishpetid(0);
                cfg.bonus.Wish conf = cfg.CfgMgr.wish.get((int)petid);
                FCmd.Context ctx = FAction.processByReflection(roleid, conf, By.Wish);
                if(ctx.errcode.err()){
                    return error(ErrorCode.INTERNAL_ERR);
                }
                FLogger.welfare(roleid, FBonus.getRoleInfo(roleid), BONUSTYPE.WISH, (int)petid);
                response.wishgift = ctx.bonus;
                response(response);
                return true;
			}

		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6556141;

	public int getType() {
		return 6556141;
	}

	public long petid;

	public CGetWishGift() {
	}

	public CGetWishGift(long _petid_) {
		this.petid = _petid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(petid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		petid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetWishGift) {
			CGetWishGift _o_ = (CGetWishGift)_o1_;
			if (petid != _o_.petid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)petid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetWishGift _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(petid - _o_.petid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}
