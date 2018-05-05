
package lx.gs.pickcard.msg;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.cmd.action.Bonus;
import cfg.item.EItemColor;
import cfg.pet.PetBasicStatus;
import cfg.pet.PetFragment;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import common.ErrorCode;
import gs.AProcedure;
import lx.gs.bag.FBag;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.pet.FPet;
import lx.gs.pickcard.FPickCard;
import lx.gs.tips.FTips;
import xtable.Roleinfos;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CPickCardByType__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CPickCardByType extends __CPickCardByType__ {
	@Override
	protected void process() {
		new AProcedure<CPickCardByType>(this){
            @Override
            protected boolean doProcess() throws Exception {
                xbean.PickCardInfo pickInfo = FPickCard.get(roleid);
                int cfgid = CfgMgr.lotterytypetocfg.get(picktype).cfgid;
                cfg.lottery.HighLottery conf = CfgMgr.highlottery.get(cfgid);
                SPickCardByType response = new SPickCardByType();
                response.picktype = picktype;
                int times;
                Bonus bonus;
                int receiveCurrencyTime;
                switch (picktype){
                    case HUOBAN_HIGH_ONE:
                        receiveCurrencyTime = 1;
                        ErrorCode err = FCondition.checkA(roleid, conf.requirecurrency, 1, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getHuobanhighyuanbao();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setHuobanhighyuanbao(times + 1);
                        FPickCard.addBonusPerPick(response);
                        FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.NOT_BIND, response.pickbonus.get(0).bonus);
                        break;
                    case HUOBAN_HIGH_TEN:
                        receiveCurrencyTime = 10;
                        err = FCondition.checkA(roleid, conf.requirecurrency, 10, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getHuobanhighyuanbao();
                        pickInfo.setHuobanhighyuanbao(times + 10);
                        int temp = 10;
                        while (temp > 0) {//随机产生10次
                            FPickCard.addBonusPerPick(response);
                            bonus = FPickCard.findBonusDetail(conf, times + 11 - temp);
                            FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.NOT_BIND, response.pickbonus.get(10-temp).bonus);
                            temp--;
                        }
                        break;
                    case HUOBAN_HIGH_FREE_ONE: //先使用免费抽，再使用物品抽
                        receiveCurrencyTime = 1;
                        times = pickInfo.getHuobanhighyuanbao();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setHuobanhighyuanbao(times + 1);
                        FPickCard.addBonusPerPick(response);
                        if(FPickCard.isCanFreePick(roleid, conf.refreshinterval, conf.id)){
                            FBonus.genBonus(roleid, bonus, 1,  common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                            break;
                        }
                        if(FCondition.checkA(roleid, conf.requireitem, 1, By.Pick_Card, 0, 0).ok()){
                            FBonus.genBonus(roleid, bonus, 1,  common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                        }else {
                            return error(ErrorCode.CAN_NOT_FREE_PICK_CARD);
                        }
                        break;
                    case HUOBAN_HIGH_FREE_TEN: //只能用抽卡券
                        receiveCurrencyTime = 10;
                        times = pickInfo.getHuobanhighyuanbao();
                        pickInfo.setHuobanhighyuanbao(times + 10);
                        if(FCondition.checkA(roleid, conf.requireitem2, 1, By.Pick_Card, 0, 0).ok()){
                            temp = 10;
                            while (temp > 0) {//随机产生10次
                                FPickCard.addBonusPerPick(response);
                                bonus = FPickCard.findBonusDetail(conf, times + 11 - temp);
                                FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.BIND, response.pickbonus.get(10-temp).bonus);
                                temp--;
                            }
                        }else {
                            return error(ErrorCode.CAN_NOT_FREE_PICK_CARD);
                        }
                        break;
                    case HUOBAN_LOW_ONE:
                        receiveCurrencyTime = 1;
                        err = FCondition.checkA(roleid, conf.requirecurrency, 1, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getHuobanlowyuanbao();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setHuobanlowyuanbao(times + 1);
                        FPickCard.addBonusPerPick(response);
                        FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.NOT_BIND, response.pickbonus.get(0).bonus);
                        break;
                    case HUOBAN_LOW_TEN:
                        receiveCurrencyTime = 10;
                        err = FCondition.checkA(roleid, conf.requirecurrency, 10, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getHuobanlowyuanbao();
                        pickInfo.setHuobanlowyuanbao(times + 10);
                        temp = 10;
                        while (temp > 0) {//随机产生10次
                            FPickCard.addBonusPerPick(response);
                            bonus = FPickCard.findBonusDetail(conf, times + 11 - temp);
                            FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.NOT_BIND, response.pickbonus.get(10 - temp).bonus);
                            temp--;
                        }
                        break;
                    case HUOBAN_LOW_FREE:  //暂时没有抽卡物品,每天免费五次
                        receiveCurrencyTime = 1;
                        times = pickInfo.getHuobanlowyuanbao();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setHuobanlowyuanbao(times + 1);
                        FPickCard.addBonusPerPick(response);
                        if(FPickCard.isCanFreePick(roleid, conf.refreshtimes, conf.id)){
                            FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                        } else {
                            return error(ErrorCode.CAN_NOT_FREE_PICK_CARD);
                        }
                        break;
                    case FABAO_ONE:
                        if(FBag.getBagEmptyGridNum(roleid, BagType.FRAGMENT) < 1){
                            return error(ErrorCode.BAG_FULL);
                        }
                        receiveCurrencyTime = 1;
                        err = FCondition.checkA(roleid, conf.requirecurrency, 1, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getFabaoyuanbao();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setFabaoyuanbao(times + 1);
                        FPickCard.addBonusPerPick(response);
                        FBonus.genBonus(roleid, bonus,1, common.Bonus.BindType.NOT_BIND, response.pickbonus.get(0).bonus);
                        break;
                    case FABAO_TEN:
                        if(FBag.getBagEmptyGridNum(roleid, BagType.FRAGMENT) < 10){
                            return error(ErrorCode.BAG_FULL);
                        }
                        receiveCurrencyTime = 10;
                        err = FCondition.checkA(roleid, conf.requirecurrency, 10, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getFabaoyuanbao();
                        pickInfo.setFabaoyuanbao(times + 10);
                        temp = 10;
                        while (temp > 0) {//随机产生10次
                            FPickCard.addBonusPerPick(response);
                            bonus = FPickCard.findBonusDetail(conf, times + 11 - temp);
                            FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.NOT_BIND, response.pickbonus.get(10 - temp).bonus);
                            temp--;
                        }
                        break;
                    case FABAO_FREE_ONE:
                        if(FBag.getBagEmptyGridNum(roleid, BagType.FRAGMENT) < 1){
                            return error(ErrorCode.BAG_FULL);
                        }
                        receiveCurrencyTime = 1;
                        times = pickInfo.getFabaofree();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setFabaofree(times + 1);
                        FPickCard.addBonusPerPick(response);
                        if(FPickCard.isCanFreePick(roleid, conf.refreshinterval, conf.id)){
                            FBonus.genBonus(roleid, bonus, 1,  common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                            break;
                        }
                        if(FCondition.checkA(roleid, conf.requireitem, 1, By.Pick_Card, 0, 0).ok()){
                            FBonus.genBonus(roleid, bonus, 1 , common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                        }else {
                            return error(ErrorCode.CAN_NOT_FREE_PICK_CARD);
                        }
                        break;
                    case FABAO_FREE_TEN: //法宝元宝抽卡券十抽
                        if(FBag.getBagEmptyGridNum(roleid, BagType.FRAGMENT) < 10){
                            return error(ErrorCode.BAG_FULL);
                        }
                        receiveCurrencyTime = 10;
                        times = pickInfo.getFabaofree();
                        pickInfo.setFabaofree(times + 10);
                        if(FCondition.checkA(roleid, conf.requireitem2, 1, By.Pick_Card, 0, 0).ok()){
                            temp = 10;
                            while (temp > 0) {//随机产生10次
                                FPickCard.addBonusPerPick(response);
                                bonus = FPickCard.findBonusDetail(conf, times + 11 - temp);
                                FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.BIND, response.pickbonus.get(10 - temp).bonus);
                                temp--;
                            }
                        }else {
                            return error(ErrorCode.CAN_NOT_FREE_PICK_CARD);
                        }
                        break;
                    case FABAO_XUNIBI_ONE:
                        if(FBag.getBagEmptyGridNum(roleid, BagType.FRAGMENT) < 1){
                            return error(ErrorCode.BAG_FULL);
                        }
                        receiveCurrencyTime = 1;
                        FPickCard.addBonusPerPick(response);
                        times = pickInfo.getFabaoxunibi();
                        bonus = FPickCard.findBonusDetail(conf, times + 1);
                        pickInfo.setFabaoxunibi(times + 1);
                        if(FPickCard.isCanFreePick(roleid, conf.refreshtimes, conf.id)){
                            FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                            break;
                        }
                        err = FCondition.checkA(roleid, conf.requirecurrency, 1, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        FBonus.genBonus(roleid, bonus, 1,  common.Bonus.BindType.BIND, response.pickbonus.get(0).bonus);
                        break;
                    case FABAO_XUNIBI_TEN:
                        if(FBag.getBagEmptyGridNum(roleid, BagType.FRAGMENT) < 10){
                            return error(ErrorCode.BAG_FULL);
                        }
                        receiveCurrencyTime = 10;
                        err = FCondition.checkA(roleid, conf.requirecurrency, 10, By.Pick_Card, 0, 0);
                        if(err.err()){
                            return error(err);
                        }
                        times = pickInfo.getFabaoxunibi();
                        pickInfo.setFabaoxunibi(times + 10);
                        temp = 10;
                        while (temp > 0) {//随机产生10次
                            FPickCard.addBonusPerPick(response);
                            bonus = FPickCard.findBonusDetail(conf, times + 11 - temp);
                            FBonus.genBonus(roleid, bonus, 1, common.Bonus.BindType.BIND, response.pickbonus.get(10 - temp).bonus);
                            temp--;
                        }
                        break;
                    default:
                        return error(ErrorCode.PARAM_ERROR);
                }
                for(PickBonusInfo bonusInfo : response.pickbonus){//如果已经有对应了伙伴了，那么要将其转成伙伴对应的碎片
                    for(int id : bonusInfo.bonus.items.keySet()){
                        cfg.pet.PetBasicStatus petModel = CfgMgr.petbasicstatus.get(id);
                        cfg.talisman.TalismanBasic talismanModel = CfgMgr.talismanbasic.get(id);
                        if(petModel != null || talismanModel != null){
                            int color = petModel == null ? talismanModel.quality : petModel.basiccolor;
                            String name = petModel == null ? talismanModel.name : petModel.name;
                            if(color == EItemColor.RED){
                                FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.RECRUIT_RED_BROADCAST, Roleinfos.selectName(roleid), name);
                            }
                        }
                        cfg.pet.PetBasicStatus pbs = FPet.getModelById(id);
                        if(pbs == null){//如果奖励不是伙伴
                            continue;
                        }
                        if(FPet.isPetExist(roleid, id)){ //如果存在对应伙伴,补全拆分信息
                            bonusInfo.issplit = 1;
                            PetFragment petFragment = FPet.getPetFragmentById(pbs.fragmentid);
                            int amount = (int) (petFragment.number * PetBasicStatus.TURN_PET_INTO_FRAGMENT_RATE);
                            bonusInfo.splitbonus.items.put(pbs.fragmentid, amount);
                        }
                    }
                    if (!FBonus.addBonus(roleid, bonusInfo.bonus, By.Pick_Card)) {//一个个添加每次抽卡的奖励
                        return error(ErrorCode.BAG_FULL);
                    }
                }
                FLogger.recruit(roleid, FBonus.getRoleInfo(roleid), picktype);
                boolean result = FBonus.addBonus(roleid, conf.recievedcurrency, receiveCurrencyTime, common.Bonus.BindType.BIND, By.Pick_Card);//增加对应的积分
                if(result){
                    FPickCard.sendPickCardTimes(roleid, pickInfo);
                    response(response);
                }
                return result;
            }
        }.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6565020;

	public int getType() {
		return 6565020;
	}

	public final static int HUOBAN_HIGH_ONE = 1; // 伙伴高配额单抽
	public final static int HUOBAN_HIGH_TEN = 2; // 伙伴高配额十连抽
	public final static int HUOBAN_HIGH_FREE_ONE = 3; // 伙伴高配额免费单抽,三天刷新一次免费机会，有抽卡券
	public final static int HUOBAN_HIGH_FREE_TEN = 12; // 伙伴高配额免费十抽，只能用抽卡券
	public final static int HUOBAN_LOW_ONE = 4; // 伙伴低配额单抽
	public final static int HUOBAN_LOW_TEN = 5; // 伙伴低配额十连抽
	public final static int HUOBAN_LOW_FREE = 6; // 伙伴低配额免费抽，每天刷新5次
	public final static int FABAO_ONE = 7; // 法宝元宝单抽
	public final static int FABAO_TEN = 8; // 法宝元宝十连抽
	public final static int FABAO_FREE_ONE = 9; // 法宝元宝免费单抽
	public final static int FABAO_FREE_TEN = 13; // 法宝元宝免费十抽,只能用抽卡券
	public final static int FABAO_XUNIBI_ONE = 10; // 法宝虚拟币抽
	public final static int FABAO_XUNIBI_TEN = 11; // 法宝虚拟币十抽

	public int picktype;

	public CPickCardByType() {
	}

	public CPickCardByType(int _picktype_) {
		this.picktype = _picktype_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(picktype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		picktype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CPickCardByType) {
			CPickCardByType _o_ = (CPickCardByType)_o1_;
			if (picktype != _o_.picktype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += picktype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(picktype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CPickCardByType _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = picktype - _o_.picktype;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

