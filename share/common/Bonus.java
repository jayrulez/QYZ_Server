package common;

import cfg.CfgMgr;
import cfg.cmd.action.CareerBonus;
import cfg.cmd.action.CopyBonus;
import cfg.cmd.action.Drop;
import cfg.cmd.action.RandomItems;
import cfg.currency.CurrencyType;
import cfg.monster.MultiRoundDrop;

import java.util.List;

import static cfg.Const.NULL;

/**
 * Created by huangqiang on 2016/4/28.
 */
public class Bonus {
    public enum BindType {
        BIND(0),
        NOT_BIND(1);

        BindType(int value) {
            this.value = value;
        }

        public int toInt() {
            return value;
        }
        private final int value;

        public static BindType of(int v) {
            switch (v) {
                case 0 : return BIND;
                case 1 : return NOT_BIND;
                default: throw new RuntimeException("invalid bind type:" + v);
            }
        }
    }

    public static void genBonusByProfession(int profession, cfg.cmd.action.Bonus src, map.msg.Bonus dst) {
        genBonusByProfession(profession, src, 1, dst);
    }

    public static void genBonusByProfession(int profession, cfg.cmd.action.Bonus src, int num, map.msg.Bonus dst) {
        if(num < 0) throw new RuntimeException("num can't < 0");
        switch (src.getTypeId()) {
            case cfg.cmd.action.BindType.TYPEID: {
                final cfg.cmd.action.BindType c = (cfg.cmd.action.BindType) src;
                if (c.bindtype == NULL) return;
                dst.bindtype = c.bindtype;
                break;
            }
            case cfg.cmd.action.Currency.TYPEID: {
                final cfg.cmd.action.Currency c = (cfg.cmd.action.Currency) src;
                if (c.type == NULL) return;
                common.Utils.addValue(dst.items, (int)c.type, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.XuNiBi.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.XuNiBi, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.YuanBao.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.YuanBao, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.BindYuanBao.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.BindYuanBao, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.LingJing.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.LingJing, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.JingYan.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.JingYan, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.ZaoHua.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.ZaoHua, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.WuXing.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.WuXing, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.ShiMen.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.ShiMen, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.ZhanChang.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.ZhanChang, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.ShengWang.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.ShengWang, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.HuoBanJiFen.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.HuoBanJiFen, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.FaBaoJiFen.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.FaBaoJiFen, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.ChengJiu.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.ChengJiu, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.BangGong.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.BangGong, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.TianFu.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.TianFu, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.TiLi.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.TiLi, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.ChongZhiJiFen.TYPEID: {
                final cfg.cmd.action.FixCurrency c = (cfg.cmd.action.FixCurrency) src;
                if (c.amount == NULL) return;
                common.Utils.addValue(dst.items, CurrencyType.ChongZhiJiFen, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.Currencys.TYPEID: {
                final cfg.cmd.action.Currencys cs = (cfg.cmd.action.Currencys) src;
                for (cfg.cmd.action.Currency c : cs.currencys) {
                    assert (c.type != NULL);
                    common.Utils.addValue(dst.items, (int)c.type, Math.multiplyExact(c.amount, num));
                }
                break;
            }
            case cfg.cmd.action.OneItem.TYPEID: {
                final cfg.cmd.action.OneItem c = (cfg.cmd.action.OneItem) src;
                if (c.itemid == NULL) return;
                common.Utils.addValue(dst.items, c.itemid, num);
                break;
            }
            case cfg.cmd.action.OneItems.TYPEID: {
                for (int itemid : ((cfg.cmd.action.OneItems) src).items) {
                    assert (itemid != NULL);
                    common.Utils.addValue(dst.items, itemid, num);
                }
                break;
            }
            case cfg.cmd.action.Item.TYPEID: {
                final cfg.cmd.action.Item c = (cfg.cmd.action.Item) src;
                if (c.itemid == NULL) return;
                common.Utils.addValue(dst.items, c.itemid, Math.multiplyExact(c.amount, num));
                break;
            }
            case cfg.cmd.action.Items.TYPEID: {
                for (cfg.cmd.action.Item item : ((cfg.cmd.action.Items) src).items) {
                    assert (item.itemid != NULL);
                    common.Utils.addValue(dst.items, item.itemid, item.amount * num);
                }
                break;
            }
            case RandomItems.TYPEID: {
                final List<Integer> items = ((RandomItems)src).items;
                for(int i = 0 ; i < num ; i++) {
                    common.Utils.addValue(dst.items, common.Utils.randomChoose(items), 1);
                }
                break;
            }
            case CareerBonus.TYPEID: {
                final cfg.cmd.action.CareerBonus c = (cfg.cmd.action.CareerBonus) src;
                if (profession == c.career) {
                    genBonusByProfession(profession, c.bonus, num, dst);
                }
                break;
            }
            case cfg.cmd.action.MultiBonus.TYPEID: {
                for (cfg.cmd.action.Bonus b : ((cfg.cmd.action.MultiBonus) src).bonuss) {
                    genBonusByProfession(profession, b, num, dst);
                }
                break;
            }
            case cfg.cmd.action.RepeatBonus.TYPEID: {
                final cfg.cmd.action.RepeatBonus c = (cfg.cmd.action.RepeatBonus)src;
                genBonusByProfession(profession, c.bonus, Math.multiplyExact(c.num, num), dst);
                break;
            }
            case CopyBonus.TYPEID: {
                final cfg.cmd.action.CopyBonus c = (cfg.cmd.action.CopyBonus)src;
                final map.msg.Bonus baseBonus = new map.msg.Bonus();
                genBonusByProfession(profession, c.bonus, 1, baseBonus);
                final int n = Math.multiplyExact(c.num, num);
                baseBonus.items.entrySet().forEach(e -> e.setValue(Math.multiplyExact(e.getValue(), n)));
                common.Utils.addIntValue(dst.items, baseBonus.items);
                break;
            }
            case cfg.cmd.action.RandomBonus.TYPEID: {
                for (int i = 0; i < num; i++) {
                    double rand = common.Utils.random01();
                    for (cfg.cmd.action.BonusRandomInfo b : ((cfg.cmd.action.RandomBonus) src).bonuss) {
                        if (b.droprate > rand) {
                            genBonusByProfession(profession, b.bonus, 1, dst);
                            break;
                        }
                        rand -= b.droprate;
                    }
                }
                break;
            }
            case Drop.TYPEID: {
                genBonusByProfession(profession, CfgMgr.drop.get(((Drop)src).dropid).droplist, num, dst);
                break;
            }
            default:
                throw new RuntimeException("unknown bonus:" + src);
        }
    }


    public static void genBonusByProfession(int profession, cfg.cmd.action.Bonus src, int num, BindType bindType, map.msg.Bonus dst) {
        dst.bindtype = bindType.value;
        genBonusByProfession(profession, src, num, dst);
    }

    public static int calcAttenuationExp(int baseExp, int levelDiff) {
        final int idx = Math.min(Math.abs(levelDiff) / cfg.bonus.Drop.ATTENUATION_INTERVAL, cfg.bonus.Drop.EXP_ATTENUATION.length - 1);
        return baseExp * cfg.bonus.Drop.EXP_ATTENUATION[idx] / 100;
    }

    public static double calcAttenuationDropProbality(int levelDiff) {
        final int idx = Math.min(Math.abs(levelDiff) / cfg.bonus.Drop.ATTENUATION_INTERVAL, cfg.bonus.Drop.DROP_ATTENUATION.length - 1);
        return cfg.bonus.Drop.DROP_ATTENUATION[idx] / 100.0;
    }

    public static void genDropItemsByProfession(int profession, List<MultiRoundDrop> droplists, double dropRate, List<map.msg.Bonus> dst) {
        for (MultiRoundDrop drop : droplists) {
            final cfg.bonus.Drop dropCfg = CfgMgr.drop.get(drop.drop.dropid);
            for (int i = 0; i < drop.num; i++) {
                if (common.Utils.random01() < dropRate) {
                    final map.msg.Bonus bonus = new map.msg.Bonus();
                    genBonusByProfession(profession, dropCfg.droplist, 1, bonus);
                    if(!isEmpty(bonus))
                        dst.add(bonus);
                }
            }
        }
    }

    public static void merge(map.msg.Bonus dst, map.msg.Bonus src) {
        Utils.addIntValue(dst.items, src.items);
    }

    public static boolean isEmpty(map.msg.Bonus bonus) {
        return bonus.items.isEmpty();
    }
}
