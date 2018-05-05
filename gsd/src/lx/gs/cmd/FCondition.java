package lx.gs.cmd;

import cfg.Const;
import cfg.DataStream;
import cfg.cmd.condition.*;
import cfg.currency.CurrencyType;
import common.ErrorCode;
import lx.gs.family.FFamily;
import lx.gs.item.FItem;
import lx.gs.limit.FLimit;
import lx.gs.logger.By;
import lx.gs.role.FRole;
import lx.gs.task.FTask;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static cfg.Const.NULL;
import static common.ErrorCode.OK;

public final class FCondition {
	private static final Map<Integer, Integer> cfg2currencyType = new HashMap<>();
	static {
		cfg2currencyType.put(ZhanChang.TYPEID, CurrencyType.ZhanChang);
		cfg2currencyType.put(BangPai.TYPEID, CurrencyType.BangPai);
		cfg2currencyType.put(ShiMen.TYPEID, CurrencyType.ShiMen);
		cfg2currencyType.put(TiLi.TYPEID, CurrencyType.TiLi);
		cfg2currencyType.put(ShengWang.TYPEID, CurrencyType.ShengWang);
		cfg2currencyType.put(YuanBao.TYPEID, CurrencyType.YuanBao);
		cfg2currencyType.put(LingJing.TYPEID, CurrencyType.LingJing);
		cfg2currencyType.put(ZaoHua.TYPEID, CurrencyType.ZaoHua);
		cfg2currencyType.put(HuoBanJiFen.TYPEID, CurrencyType.HuoBanJiFen);
		cfg2currencyType.put(BindYuanBao.TYPEID, CurrencyType.BindYuanBao);
		cfg2currencyType.put(TianFu.TYPEID, CurrencyType.TianFu);
		cfg2currencyType.put(XuNiBi.TYPEID, CurrencyType.XuNiBi);
		cfg2currencyType.put(ChongZhiJiFen.TYPEID, CurrencyType.ChongZhiJiFen);
		cfg2currencyType.put(FaBaoJiFen.TYPEID, CurrencyType.FaBaoJiFen);
		cfg2currencyType.put(JingYan.TYPEID, CurrencyType.JingYan);
		cfg2currencyType.put(WuXing.TYPEID, CurrencyType.WuXing);
		cfg2currencyType.put(BangGong.TYPEID, CurrencyType.BangGong);
		cfg2currencyType.put(ChengJiu.TYPEID, CurrencyType.ChengJiu);
	}

	public static ErrorCode checkA(long roleid, Condition condition,
		int costnum, By costby, int configid, int cmdid) {
	    if(costnum < 0) throw new RuntimeException("cost num can't < 0");
		switch(condition.getTypeId()) {
			case OR.TYPEID: {
				final OR c = (OR)condition;
				final ErrorCode err = checkA(roleid, c.first, costnum, costby, configid, cmdid);
				return err.ok() ? err : checkA(roleid, c.second, costnum, costby, configid, cmdid);
			}
			case ORs.TYPEID: {
				final ORs ors = (ORs)condition;
				ErrorCode err = OK;
				for(Condition c : ors.conditions) {
					err = checkA(roleid, c, costnum, costby, configid, cmdid);
					if(err.ok())
						return err;
				}
				return err;
			}
			case Gender.TYPEID: {
				final Gender c = (Gender)condition;
				return c.gender != Const.NULL && xtable.Roleinfos.get(roleid).getGender() != c.gender ? ErrorCode.GENDER_NOT_MATCH : OK;
			}
			case MaxLevel.TYPEID: {
				final MaxLevel c = (MaxLevel)condition;
				return (c.level != NULL && xtable.Roleinfos.get(roleid).getLevel() > c.level) ?
					ErrorCode.LEVEL_TOO_HIGH : OK;
			}
			case MinLevel.TYPEID: {
				final MinLevel c = (MinLevel)condition;
				return (c.level != NULL && xtable.Roleinfos.get(roleid).getLevel() < c.level) ?
					ErrorCode.NOT_ENOUGH_LEVEL : OK;
			}
			case MinMaxLevel.TYPEID: {
				final MinMaxLevel c = (MinMaxLevel)condition;
				final int level = xtable.Roleinfos.get(roleid).getLevel();
				if(c.min != NULL && level < c.min)
					return ErrorCode.NOT_ENOUGH_LEVEL;
				else if(c.max != NULL && level > c.max)
					return ErrorCode.LEVEL_TOO_HIGH;
				return OK;
			}
			case MinVipLevel.TYPEID: {
				final MinVipLevel c = (MinVipLevel)condition;
				if(c.level != NULL && xtable.Roleinfos.get(roleid).getViplevel() < c.level)
					return ErrorCode.NOT_ENOUGH_VIP_LEVEL;
				return OK;
			}
			case MinFamilyLevel.TYPEID: {
				final MinFamilyLevel c = (MinFamilyLevel)condition;
				if(c.level != NULL) {
					final xbean.Family family =  FFamily.selectFamilyByRoleId(roleid);
					if(family == null)
						return ErrorCode.NOT_IN_FAMILY;
					if(family.getFlevel() < c.level)
						return ErrorCode.FAMILY_LEVEL_TOO_LOW;
				}
				return OK;
			}
			case MinFamilyShopLevel.TYPEID: {
				final MinFamilyShopLevel c = (MinFamilyShopLevel)condition;
				if(c.level != NULL) {
					final xbean.Family family =  FFamily.selectFamilyByRoleId(roleid);
					if(family == null)
						return ErrorCode.NOT_IN_FAMILY;
					if(family.getMalllevel() < c.level)
						return ErrorCode.FAMILY_MALL_LEVEL_TOO_LOW;
				}
				return OK;
			}
			case Item.TYPEID: {
				final Item c = (Item)condition;
				return c.itemid != NULL && c.amount > 0
						&& !FItem.spendItemBindFirst(roleid, c.itemid, Math.multiplyExact(c.amount, costnum), costby) ?
					ErrorCode.ITEM_NUMBER_NOT_ENOUGH : OK;
			}
            case Items.TYPEID: {
                for(Item c : ((Items)condition).items) {
                    if(!FItem.spendItemBindFirst(roleid, c.itemid, Math.multiplyExact(c.amount, costnum), costby))
                        return ErrorCode.ITEM_NUMBER_NOT_ENOUGH;
                }
                return OK;
            }
			case OneItem.TYPEID: {
				final OneItem c = (OneItem)condition;
				return c.itemid != NULL && !FItem.spendItemBindFirst(roleid, c.itemid, costnum, costby) ?
					ErrorCode.ITEM_NUMBER_NOT_ENOUGH : OK;
			}

			case Currency.TYPEID: {
				final Currency c = (Currency)condition;
				return c.currencytype == NULL ? ErrorCode.OK : FRole.costCurrency(roleid, c.currencytype, Math.multiplyExact(c.amount, costnum), costby);
			}
			case Currencys.TYPEID: {
				final Currencys c = (Currencys)condition;
				for(Currency cc : c.currencys) {
					assert(cc.currencytype != NULL);
					ErrorCode errorCode = FRole.costCurrency(roleid, cc.currencytype, Math.multiplyExact(cc.amount, costnum), costby);
					if(errorCode.err()) {
						return errorCode;
					}
				}
				return OK;
			}

			// 货币类型统一处理开始，不要随便添加，添加的话，在FConditon.cfg2currencyType中添加类型
			case ZhanChang.TYPEID:
			case BangPai.TYPEID:
			case ShiMen.TYPEID:
			case TiLi.TYPEID:
			case ShengWang.TYPEID:
			case YuanBao.TYPEID:
			case LingJing.TYPEID:
			case ZaoHua.TYPEID:
			case HuoBanJiFen.TYPEID:
			case BindYuanBao.TYPEID:
			case TianFu.TYPEID:
			case XuNiBi.TYPEID:
			case ChongZhiJiFen.TYPEID:
			case FaBaoJiFen.TYPEID:
			case JingYan.TYPEID:
			case WuXing.TYPEID:
			case BangGong.TYPEID:
			case ChengJiu.TYPEID: {
				final FixCurrency c = (FixCurrency)condition;
				int currencyType = cfg2currencyType.get(condition.getTypeId());
				return c.amount == NULL ? ErrorCode.OK : FRole.costCurrency(roleid, currencyType, Math.multiplyExact(c.amount, costnum), costby);
			}
			// 货币类型统一处理结束

			case FamilyMoney.TYPEID: {
				final FamilyMoney c = (FamilyMoney)condition;
				return FFamily.costFamilyMoneyByRole(roleid, Math.multiplyExact(c.money, costnum), costby);
			}
			case VipLimitsLite.TYPEID: {
				final VipLimitsLite c = (VipLimitsLite)condition;
				return FLimit.checkVipLimitAndAddNum(roleid, configid, cmdid, costnum, c, costby) ?
						OK : ErrorCode.EXCEED_LIMIT;
			}
			case VipLimits.TYPEID: {
				final VipLimits c = (VipLimits)condition;
				return FLimit.checkVipLimitAndAddNum(roleid, configid, cmdid, costnum, c, costby);
			}
			case VipLimits2.TYPEID: {
				final VipLimits2 c = (VipLimits2)condition;
				return FLimit.checkVipLimitAndAddNum(roleid, configid, cmdid, costnum, c, costby);
			}

			case Limit.TYPEID: {
				final Limit c = (Limit)condition;
				return c.type != NULL && !FLimit.checkLimitAndAddNum(roleid, configid, cmdid, costnum, Arrays.asList(c)) ?
					ErrorCode.EXCEED_LIMIT : OK;
			}

			case DayLimit.TYPEID: {
				final DayLimit c = (DayLimit)condition;
				return c.num != NULL && !FLimit.checkLimitAndAddNum(roleid, configid, cmdid, costnum, LimitType.DAY, c.num) ?
					ErrorCode.EXCEED_LIMIT : OK;
			}

			case Limits.TYPEID: {
				final Limits c = (Limits)condition;
				return FLimit.checkLimitAndAddNum(roleid, configid, cmdid, costnum, c.limits) ?
					OK : ErrorCode.EXCEED_LIMIT;
			}

			case CoolDown.TYPEID: {
				final CoolDown c = (CoolDown)condition;
				return c.time <= 0 || FLimit.checkCoolDown(roleid, configid, cmdid, c) ? OK : ErrorCode.COOL_DOWN;
			}

			case GroupCoolDown.TYPEID: {
				final GroupCoolDown c = (GroupCoolDown)condition;
				return c.time <= 0 || FLimit.checkCoolDown(roleid, configid, cmdid, c) ? OK : ErrorCode.COOL_DOWN;
			}

			case ProfessionLimit.TYPEID: {
				final ProfessionLimit c = (ProfessionLimit)condition;
				return c.profession == NULL || xtable.Roleinfos.selectProfession(roleid) == c.profession ? OK : ErrorCode.PROFESSION_NOT_MATCH;
			}
			case MultiCondition.TYPEID: {
				final MultiCondition mc = (MultiCondition)condition;
				for(Condition cond : mc.conditions) {
					final ErrorCode err = checkA(roleid, cond, costnum, costby, configid, cmdid);
					if(err.err())
						return err;
				}
				return OK;
			}
			case CompleteTask.TYPEID: {
				final CompleteTask c = (CompleteTask)condition;
				return c.taskid != NULL && FTask.getCompleteCount(FTask.getTask(roleid), c.taskid) == 0 ?
					 ErrorCode.TASK_NOT_COMPLETED : OK;
			}

			default: {
				throw new RuntimeException("unknown condition:" + condition);
			}
		}
	}
	
	public static ErrorCode check(long roleid, int costnum, By costby, int configid, int cmdid,
		Collection<Condition> conditions) {
		for(Condition condition : conditions) {
			final ErrorCode err = checkA(roleid, condition, costnum, costby, configid, cmdid);
			if(err.err())
				return err;
		}
		return OK;
	}
	
	public static ErrorCode checkByReflection(long roleid, Object cmd, int costnum, By costby, int configid, int cmdid) {
		Class<?> cls = cmd.getClass();
		for(Field f : cls.getFields()) {
			try {
				if(Condition.class.isAssignableFrom(f.getType())) {		
					final ErrorCode err = checkA(roleid, (Condition) f.get(cmd), costnum, costby, configid, cmdid);
					if (err.err())
						return err;
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// impossible
				return ErrorCode.INTERNAL_ERR;
			}
		}
		return OK;
	}
	
	public static ErrorCode checkByReflection(long roleid, Object cmd, int costnum, By costby) {
		return checkByReflection(roleid, cmd, costnum, costby, NULL, NULL);
	}
	
	public static ErrorCode checkByReflection(long roleid, Object cmd, By costby) {
		return checkByReflection(roleid, cmd, 1, costby, NULL, NULL);
	}
}
