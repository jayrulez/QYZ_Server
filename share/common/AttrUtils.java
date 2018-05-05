package common;

import cfg.equip.EquipPropertyData;
import cfg.fight.AttrId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cfg.fight.AttrId.*;
import static cfg.fight.AttrId.HIT_RESIST_RATE_PERCENT;
import static cfg.fight.AttrId.HIT_RESIST_RATE_PERCENT_2;

/**
 * Created by HuangQiang on 2016/5/13.
 */
public class AttrUtils {
    public static void convert(cfg.fight.BasicAttr attr, Map<Integer, Float> rawattrs) {
        rawattrs.put(AttrId.HP_FULL_VALUE, attr.hp);
        rawattrs.put(AttrId.MP_FULL_VALUE, attr.mp);
        rawattrs.put(AttrId.ATTACK_VALUE_MIN, attr.attackvaluemin);
        rawattrs.put(AttrId.ATTACK_VALUE_MAX, attr.attackvaluemax);
        rawattrs.put(AttrId.DEFENCE, attr.defence);
        rawattrs.put(AttrId.HIT_RATE, attr.hitrate);
        rawattrs.put(AttrId.HIT_RESIST_RATE, attr.hitresistrate);
    }

    public static List<Float> convert(float[] src) {
        final ArrayList<Float> dst = new ArrayList<>();
        for(float x : src) {
            dst.add(x);
        }
        return dst;
    }

    public static void convert(List<Float> src, Map<Integer, Float> dst) {
        for(int i = 0, n = src.size() ; i < n ; i++) {
            dst.put(i, src.get(i));
        }
    }

    public static void convert(float[] src, Map<Integer, Float> dst) {
        for(int i = 0 ; i < src.length ; i++) {
            dst.put(i, src[i]);
        }
    }

    public static void fill(List<Float> dst, float[] src) {
        if(dst.size() == src.length) {
            for(int i = 0 ; i < src.length ; i++) {
                dst.set(i, src[i]);
            }
        } else {
            dst.clear();
            for(float x : src) {
                dst.add(x);
            }
        }
    }

    public static void fill(List<Float> dst, Map<Integer, Float> src) {
        final float[] rawAttrs = newRawAttrs();
        for(Map.Entry<Integer, Float> e : src.entrySet()) {
            rawAttrs[e.getKey()] = e.getValue();
        }

        dst.clear();
        dst.clear();
        for(float x : rawAttrs) {
            dst.add(x);
        }
    }

    public static void fill(float[] dst, List<Float> src) {
        assert(dst.length == src.size());
        int i = 0;
        for(float x : src)
            dst[i++] = x;
    }

    public static void fill(Map<Integer, Float> oldAttrs, Map<Integer, Float> newAttrs) {
        oldAttrs.clear();
        oldAttrs.putAll(newAttrs);
    }


    public static float[] newRawAttrs() {
        return new float[AttrId.EXT_ATTR_END];
    }

    public static float[] newFinalAttrs() {
        return new float[AttrId.BASE_ATTR_END];
    }

    public static void convert(cfg.fight.Attr attr, float[] rawattrs) {
        rawattrs[AttrId.HP_FULL_VALUE] = attr.hp;
        rawattrs[AttrId.MP_FULL_VALUE] = attr.mp;
        rawattrs[AttrId.ATTACK_VALUE_MIN] = attr.attackvaluemin;
        rawattrs[AttrId.ATTACK_VALUE_MAX] = attr.attackvaluemax;
        rawattrs[AttrId.DEFENCE] = attr.defence;
        rawattrs[AttrId.HIT_RATE] = attr.hitrate;
        rawattrs[AttrId.HIT_RESIST_RATE] = attr.hitresistrate;
        rawattrs[AttrId.CRIT_RATE] = attr.critrate;
        rawattrs[AttrId.CRIT_VALUE] = attr.critvalue;
        rawattrs[AttrId.CRIT_RESIST_RATE] = attr.critresistrate;
        rawattrs[AttrId.CRIT_RESIST_VALUE] = attr.critresistvalue;
        rawattrs[AttrId.EXCELLENT_RATE] = attr.excellentrate;
        rawattrs[AttrId.EXCELLENT_VALUE] = attr.excellentvalue;
        rawattrs[AttrId.EXCELLENT_RESIST_RATE] = attr.excellentresistrate;
        rawattrs[AttrId.EXCELLENT_RESIST_VALUE] = attr.excellentresistvalue;
        rawattrs[AttrId.LUCKY_VALUE] = attr.lucky;
        rawattrs[AttrId.ATTACK_MULTI_RATE] = attr.attackmultirate;
        rawattrs[AttrId.DEFENCE_MUTLI_RATE] = attr.defencemultirate;
        rawattrs[AttrId.ABNORMAL_RESIST_RATE] = attr.abnormalresistrate;
        rawattrs[AttrId.ABNORMAL_HIT_RATE] = attr.abnormalhitrate;
        rawattrs[AttrId.MOVE_SPEED] = attr.movespeed;
        rawattrs[AttrId.HP_HEAL_RATE] = attr.heal;
        rawattrs[AttrId.DAMAGE_TO_HUMAN] = attr.damagetohuman;
        rawattrs[AttrId.DAMAGE_TO_PET] = attr.damagetopet;
        rawattrs[AttrId.RESIST_DAMAGE_FROM_HUMAN] = attr.resistdamagefromhuman;
        rawattrs[AttrId.RESIST_DAMAGE_FROM_PET] = attr.resistdamagefrompet;
    }

    public static void convert(cfg.fight.Attr attr, Map<Integer, Float> rawattrs) {
        rawattrs.put(AttrId.HP_FULL_VALUE, attr.hp);
        rawattrs.put(AttrId.MP_FULL_VALUE, attr.mp);
        rawattrs.put(AttrId.ATTACK_VALUE_MIN, attr.attackvaluemin);
        rawattrs.put(AttrId.ATTACK_VALUE_MAX, attr.attackvaluemax);
        rawattrs.put(AttrId.DEFENCE, attr.defence);
        rawattrs.put(AttrId.HIT_RATE, attr.hitrate);
        rawattrs.put(AttrId.HIT_RESIST_RATE, attr.hitresistrate);
        rawattrs.put(AttrId.CRIT_RATE, attr.critrate);
        rawattrs.put(AttrId.CRIT_VALUE, attr.critvalue);
        rawattrs.put(AttrId.CRIT_RESIST_RATE, attr.critresistrate);
        rawattrs.put(AttrId.CRIT_RESIST_VALUE, attr.critresistvalue);
        rawattrs.put(AttrId.EXCELLENT_RATE, attr.excellentrate);
        rawattrs.put(AttrId.EXCELLENT_VALUE, attr.excellentvalue);
        rawattrs.put(AttrId.EXCELLENT_RESIST_RATE, attr.excellentresistrate);
        rawattrs.put(AttrId.EXCELLENT_RESIST_VALUE, attr.excellentresistvalue);
        rawattrs.put(AttrId.LUCKY_VALUE, attr.lucky);
        rawattrs.put(AttrId.ATTACK_MULTI_RATE, attr.attackmultirate);
        rawattrs.put(AttrId.DEFENCE_MUTLI_RATE, attr.defencemultirate);
        rawattrs.put(AttrId.ABNORMAL_RESIST_RATE, attr.abnormalresistrate);
        rawattrs.put(AttrId.ABNORMAL_HIT_RATE, attr.abnormalhitrate);
        rawattrs.put(AttrId.MOVE_SPEED, attr.movespeed);
        rawattrs.put(AttrId.HP_HEAL_RATE, attr.heal);
        rawattrs.put(AttrId.DAMAGE_TO_HUMAN, attr.damagetohuman);
        rawattrs.put(AttrId.DAMAGE_TO_PET, attr.damagetopet);
        rawattrs.put(AttrId.RESIST_DAMAGE_FROM_HUMAN, attr.resistdamagefromhuman);
        rawattrs.put(AttrId.RESIST_DAMAGE_FROM_PET, attr.resistdamagefrompet);
    }

    public static<T> int addValue(Map<T, Integer> map, T key, int add) {
        final int newValue = map.getOrDefault(key, 0) + add;
        map.put(key, newValue);
        return newValue;
    }

    public static<T> long addValue(Map<T, Long> map, T key, long add) {
        final long newValue = map.getOrDefault(key, 0L) + add;
        map.put(key, newValue);
        return newValue;
    }

    public static<T> float addValue(Map<T, Float> map, T key, float add) {
        final float newValue = map.getOrDefault(key, 0f) + add;
        map.put(key, newValue);
        return newValue;
    }

    public static<T> void addValue(Map<T, Float> result, Map<T, Float> add) {
        for(Map.Entry<T, Float> e : add.entrySet()) {
            final T key = e.getKey();
            result.put(key, result.getOrDefault(key, 0f) + e.getValue());
        }
    }

    public static void addValue(float[] rawAttrs, Map<Integer, Float> add) {
        for(Map.Entry<Integer, Float> e : add.entrySet()) {
            rawAttrs[e.getKey()] += e.getValue();
        }
    }

    public static float addValue(float[] rawAttrs, int key, float add) {
        return (rawAttrs[key] += add);
    }

    public static void addAttrs(Map<Integer, Float> rawattrs, List<EquipPropertyData> datas) {
        for(EquipPropertyData d : datas) {
            addValue(rawattrs, d.propertytype, d.value);
        }
    }

    public static void addAttrs(Map<Integer, Float> result, cfg.equip.EquipPropertyData attr) {
        addValue(result, attr.propertytype, attr.value);
    }

    public static void addAttrs(float[] rawattrs, List<EquipPropertyData> datas) {
        for(EquipPropertyData d : datas) {
            addValue(rawattrs, d.propertytype, d.value);
        }
    }

    public static void addAttrs(float[] result, cfg.equip.EquipPropertyData attr) {
        addValue(result, attr.propertytype, attr.value);
    }

    private static void calc11(float[] rawAttrs, float[] finalAttrs, int finalAttrid, int percentAttrid) {
        finalAttrs[finalAttrid] = rawAttrs[finalAttrid] * (1 + rawAttrs[percentAttrid]);
    }

    private static void calc111(float[] rawAttrs, float[] finalAttrs, int finalAttrid, int percentAttrid1, int percentAttrid2) {
        finalAttrs[finalAttrid] = rawAttrs[finalAttrid] * (1 + rawAttrs[percentAttrid1]) * (1 + rawAttrs[percentAttrid2]);
    }

    private static void calcBasic121(float[] rawAttrs, float[] finalAttrs, int finalAttrid, int percentAttrid1, int percentAttrid2) {
        finalAttrs[finalAttrid] = rawAttrs[finalAttrid] * (1 + rawAttrs[percentAttrid1] + rawAttrs[ALL_BASIC_ATTR_PERCENT]) * (1 + rawAttrs[percentAttrid2]);
    }

    private static void calcAttack232(float[] rawAttrs, float[] finalAttrs, int finalAttrid, int percentAttrid1, int percentAttrid2) {
        finalAttrs[finalAttrid] = (rawAttrs[finalAttrid] + rawAttrs[ATTACK_VALUE])
                * (1 + rawAttrs[percentAttrid1] + rawAttrs[ATTACK_VALUE_PERCENT] + rawAttrs[ALL_BASIC_ATTR_PERCENT])
                * (1 + rawAttrs[percentAttrid2] + rawAttrs[ATTACK_VALUE_PERCENT_2]);
    }

    public static float[] calcFinalAttrs(float[] rawAttrs) {
        final float[] finalAttrs = newFinalAttrs();
        for(int i = 0 ; i < AttrId.BASE_ATTR_END ; i++)
            AttrUtils.calcAttr(rawAttrs, finalAttrs, i, null);
        return finalAttrs;
    }

    public static void calcAttr(float[] rawAttrs, float[] finalAttrs, int attrid, Collection<Integer> changeAttrs) {
        switch (attrid) {
            case EXCELLENT_RATE:
            case EXCELLENT_RESIST_RATE:
            case EXCELLENT_VALUE:
            case EXCELLENT_RESIST_VALUE:
            case CRIT_RATE:
            case CRIT_RESIST_RATE:
            case CRIT_VALUE:
            case CRIT_RESIST_VALUE:
            case ATTACK_MULTI_RATE:
            case DEFENCE_MUTLI_RATE:
            case ABNORMAL_RESIST_RATE:
            case ABNORMAL_HIT_RATE:
            case HP_HEAL_RATE:
            case LUCKY_VALUE:
            case ADDITIONAL_DAMAGE:
            case DAMAGE_TO_HUMAN:
            case DAMAGE_TO_PET:
            case RESIST_DAMAGE_FROM_HUMAN:
            case RESIST_DAMAGE_FROM_PET:{
                finalAttrs[attrid] = rawAttrs[attrid];
                break;
            }

            case MODEL_SCALE: {
                finalAttrs[attrid] = rawAttrs[attrid];
                if(changeAttrs != null) {
                    changeAttrs.add(MODEL_SCALE);
                }
                break;
            }

            case HP_FULL_VALUE:
            case HP_FULL_VALUE_PERCENT:
            case HP_FULL_VALUE_PERCENT_2:{
                calcBasic121(rawAttrs, finalAttrs, HP_FULL_VALUE, HP_FULL_VALUE_PERCENT, HP_FULL_VALUE_PERCENT_2);
                if(changeAttrs != null) {
                    changeAttrs.add(HP_FULL_VALUE);
                }
                break;
            }
            case MP_FULL_VALUE:
            case MP_FULL_VALUE_PERCENT:
            case MP_FULL_VALUE_PERCENT_2:{
                calcBasic121(rawAttrs, finalAttrs, MP_FULL_VALUE, MP_FULL_VALUE_PERCENT, MP_FULL_VALUE_PERCENT_2);
                if(changeAttrs != null) {
                    changeAttrs.add(MP_FULL_VALUE);
                }
                break;
            }
            case ATTACK_VALUE_MIN:
            case ATTACK_VALUE_MIN_PERCENT:
            case ATTACK_VALUE_MIN_PERCENT_2:{
                calcAttack232(rawAttrs, finalAttrs, ATTACK_VALUE_MIN, ATTACK_VALUE_MIN_PERCENT, ATTACK_VALUE_MIN_PERCENT_2);
                break;
            }
            case ATTACK_VALUE_MAX:
            case ATTACK_VALUE_MAX_PERCENT:
            case ATTACK_VALUE_MAX_PERCENT_2:{
                calcAttack232(rawAttrs, finalAttrs, ATTACK_VALUE_MAX, ATTACK_VALUE_MAX_PERCENT, ATTACK_VALUE_MAX_PERCENT_2);
                break;
            }
            case ATTACK_VALUE:
            case ATTACK_VALUE_PERCENT:
            case ATTACK_VALUE_PERCENT_2:{
                calcAttack232(rawAttrs, finalAttrs, ATTACK_VALUE_MIN, ATTACK_VALUE_MIN_PERCENT, ATTACK_VALUE_MIN_PERCENT_2);
                calcAttack232(rawAttrs, finalAttrs, ATTACK_VALUE_MAX, ATTACK_VALUE_MAX_PERCENT, ATTACK_VALUE_MAX_PERCENT_2);
                break;
            }
            case DEFENCE:
            case DEFENCE_PERCENT:
            case DEFENCE_PERCENT_2:{
                calcBasic121(rawAttrs, finalAttrs, DEFENCE, DEFENCE_PERCENT, DEFENCE_PERCENT_2);
                break;
            }
            case HIT_RATE:
            case HIT_RATE_PERCENT:
            case HIT_RATE_PERCENT_2:{
                calcBasic121(rawAttrs, finalAttrs, HIT_RATE, HIT_RATE_PERCENT, HIT_RATE_PERCENT_2);
                break;
            }
            case HIT_RESIST_RATE:
            case HIT_RESIST_RATE_PERCENT:
            case HIT_RESIST_RATE_PERCENT_2:{
                calcBasic121(rawAttrs, finalAttrs, HIT_RESIST_RATE, HIT_RESIST_RATE_PERCENT, HIT_RESIST_RATE_PERCENT_2);
                break;
            }

            case MOVE_SPEED:
            case MOVE_SPEED_PERCENT:
            case MOVE_SPEED_PERCENT_2:{
                calc111(rawAttrs, finalAttrs, MOVE_SPEED, MOVE_SPEED_PERCENT, MOVE_SPEED_PERCENT_2);
                if(changeAttrs != null) {
                    changeAttrs.add(MOVE_SPEED);
                }
                break;
            }

            case ALL_BASIC_ATTR_PERCENT: {
                calcBasic121(rawAttrs, finalAttrs, HP_FULL_VALUE, HP_FULL_VALUE_PERCENT, HP_FULL_VALUE_PERCENT_2);
                if(changeAttrs != null) {
                    changeAttrs.add(HP_FULL_VALUE);
                }
                calcBasic121(rawAttrs, finalAttrs, MP_FULL_VALUE, MP_FULL_VALUE_PERCENT, MP_FULL_VALUE_PERCENT_2);
                if(changeAttrs != null) {
                    changeAttrs.add(MP_FULL_VALUE);
                }
                calcAttack232(rawAttrs, finalAttrs, ATTACK_VALUE_MIN, ATTACK_VALUE_MIN_PERCENT, ATTACK_VALUE_MIN_PERCENT_2);
                calcAttack232(rawAttrs, finalAttrs, ATTACK_VALUE_MAX, ATTACK_VALUE_MAX_PERCENT, ATTACK_VALUE_MAX_PERCENT_2);
                calcBasic121(rawAttrs, finalAttrs, DEFENCE, DEFENCE_PERCENT, DEFENCE_PERCENT_2);
                calcBasic121(rawAttrs, finalAttrs, HIT_RATE, HIT_RATE_PERCENT, HIT_RATE_PERCENT_2);
                calcBasic121(rawAttrs, finalAttrs, HIT_RESIST_RATE, HIT_RESIST_RATE_PERCENT, HIT_RESIST_RATE_PERCENT_2);
                break;
            }
            case HP_VALUE :
            case MP_VALUE:
                break;

            default:
                throw new RuntimeException("unknown propertyid:" + attrid);
        }
    }
}
