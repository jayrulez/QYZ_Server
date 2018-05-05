package lx.gs.pet;

import cfg.CfgMgr;
import cfg.Const;
import cfg.fight.AttrId;
import cfg.pet.*;
import common.AttrUtils;
import common.ErrorCode;
import gs.Utils;
import lx.gs.SError;
import lx.gs.event.AddPetEvent;
import lx.gs.event.EventModule;
import lx.gs.event.PetLevelUpEvent;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.map.FMap;
import lx.gs.pet.msg.*;
import lx.gs.role.FRoleAttr;
import lx.gs.role.msg.PetBrief;
import lx.gs.skill.FSkill;
import map.msg.*;
import xbean.Pet;
import xbean.RolePet;
import xdb.Trace;
import xdb.Transaction;
import xtable.Roleinfos;
import xtable.Rolepet;

import java.util.*;
import java.util.stream.Collectors;

import static common.AttrUtils.newFinalAttrs;
import static common.AttrUtils.newRawAttrs;

/**
 * Created by huangqiang on 2016/3/7.
 */
public class FPet {
    public static final int FIGHT_PET_MAX_NUM = 3;

    public static xbean.RolePet get(long roleid) {
        return Rolepet.get(roleid);
    }

    public static Pet getPetByModelId(long roleid, int modelId) {
        return get(roleid).getPetmap().get(modelId);
    }

    private static xbean.Pet createByModelId(int modelid) {
        final xbean.Pet pet = xbean.Pod.newPet();
        pet.setModelid(modelid);
        pet.setStarlevel(1);
        pet.setLevel(1);
        onPetLevelUp(pet);

        checkPetNewAvaliableSkills(-1, pet, false);
        return pet;
    }

    public static lx.gs.pet.Pet convert(Pet dbPet) {
        final lx.gs.pet.Pet pet = new lx.gs.pet.Pet();
        pet.modelid = dbPet.getModelid();
        pet.activeskinid = dbPet.getActiveskinid();
        pet.skinidlist.addAll(dbPet.getSkinidlist());
        pet.level = dbPet.getLevel();
        pet.exp = dbPet.getExp();
        pet.starlevel = dbPet.getStarlevel();
        pet.awakelevel = dbPet.getAwakelevel();
        pet.attrs.putAll(getPetFinalAttrMap(getPetRawAttrs(dbPet)));
        pet.skills.putAll(dbPet.getSkills());
        pet.combatpower = getCombatPower(dbPet);
        return pet;
    }

    private static void syncCombatPower(long roleId, Pet pet) {
        Transaction.tsendWhileCommit(roleId, new SSyncPetCombatPower(pet.getModelid(), getCombatPower(pet)));
    }

    private static int getCombatPower(Pet pet){
        return FRoleAttr.calcCombatPower(getPetFinalAttrs(getPetRawAttrs(pet)), pet.getSkills().values().stream().mapToInt(t -> t).sum());
    }

    public static void calcTotalCombat(long roleId){
        RolePet rolePet = get(roleId);
        Map<Integer, Pet> petMap = get(roleId).getPetmap();
        int total = rolePet.getFightpets().stream()
                .mapToInt(modelId -> getCombatPower(petMap.get(modelId)))
                .sum();
        if(rolePet.getTotalcombatpower() != total){
            rolePet.setTotalcombatpower(total);
        }
    }

    public static Pet addPet(long roleid, int modelid, By by) {
        final xbean.Pet pet = createByModelId(modelid);
        get(roleid).getPetmap().put(pet.getModelid(), pet);
        FLogger.gainitem(roleid, Roleinfos.get(roleid), modelid, 1, by);
        Transaction.tsendWhileCommit(roleid, new SCallPet(convert(pet)));
        return pet;
    }

    private static void randomAddWashAttr(Map<Integer, Float> attrs, int attrid, List<Integer> ranges) {
        common.AttrUtils.addValue(attrs, attrid, common.Utils.randomRange(ranges.get(0), ranges.get(1) + 1));
    }

    public static void boundWashAttr(Map<Integer, Float> oldWash, Map<Integer, Float> addWashAttrs, int attrid, float maxValue) {
        final float old = oldWash.getOrDefault(attrid, 0f);

        final float minAdd = -old;
        final float maxAdd = maxValue - old;
        final float curAdd = addWashAttrs.get(attrid);

        if(curAdd > maxAdd)
            addWashAttrs.put(attrid, maxAdd);
        else if(curAdd < minAdd)
            addWashAttrs.put(attrid, minAdd);
    }

    public static void genWashAtrrs(Map<Integer, Float> washAtrrs, xbean.Pet pet, PetWash wcfg, int num) {
        for(int i = 0 ; i < num ; i++) {
            randomAddWashAttr(washAtrrs, AttrId.HP_FULL_VALUE, wcfg.hp);
            randomAddWashAttr(washAtrrs, AttrId.MP_FULL_VALUE, wcfg.mp);
            randomAddWashAttr(washAtrrs, AttrId.ATTACK_VALUE_MIN, wcfg.minatk);
            randomAddWashAttr(washAtrrs, AttrId.ATTACK_VALUE_MAX, wcfg.maxatk);
            randomAddWashAttr(washAtrrs, AttrId.DEFENCE, wcfg.defence);
            randomAddWashAttr(washAtrrs, AttrId.HIT_RATE, wcfg.hitrate);
            randomAddWashAttr(washAtrrs, AttrId.HIT_RESIST_RATE, wcfg.hitresist);
        }

        final Map<Integer, Float> maxVal = getWashMaxVal(pet);
        // 洗炼增加的总属性不能超过上限，也不能低于0
        washAttrId().keySet().forEach(attrId -> {
            boundWashAttr(pet.getWashattrsAsData(), washAtrrs, attrId, maxVal.get(attrId));
        });
    }

    public static SWashMaxValue getWashMaxProto(Pet pet){
        Map<Integer, Float> rawAttrMap = new HashMap<>();
        //等级属性
        AttrUtils.addValue(rawAttrMap, pet.getGrowthattrs());
        //缘分属性
        AttrUtils.addValue(rawAttrMap, pet.getKarmaattrs());
        //觉醒buff属性
        AttrUtils.addValue(rawAttrMap, pet.getFixedattrs());

        Map<Integer, Float> maxWashMap = new HashMap<>(rawAttrMap);
        Map<Integer, Float> finalAttrMap = new HashMap<>(rawAttrMap);

        //洗练最大属性
        AttrUtils.addValue(maxWashMap, getWashMaxVal(pet));
        AttrUtils.addValue(finalAttrMap, pet.getWashattrs());

        finalAttrMap = getPetFinalAttrMap(finalAttrMap);
        rawAttrMap = getPetFinalAttrMap(rawAttrMap);
        maxWashMap = getPetFinalAttrMap(maxWashMap);

        SWashMaxValue sWashMaxValue = new SWashMaxValue();
        sWashMaxValue.modelid = pet.getModelid();

        for (int id : washAttrId().keySet()) {
            sWashMaxValue.currvalues.put(id, finalAttrMap.getOrDefault(id, 0f) - rawAttrMap.getOrDefault(id, 0f));
            sWashMaxValue.maxvalues.put(id, maxWashMap.getOrDefault(id, 0f) - rawAttrMap.getOrDefault(id, 0f));
        }
        return sWashMaxValue;
    }


    public static Map<Integer, Integer> washAttrId(){
        Map<Integer, Integer> map = new HashMap<>();
        map.put(AttrId.HP_FULL_VALUE, AttrId.HP_FULL_VALUE_PERCENT);
        map.put(AttrId.MP_FULL_VALUE, AttrId.MP_FULL_VALUE_PERCENT);
        map.put(AttrId.ATTACK_VALUE_MIN, AttrId.ATTACK_VALUE_MIN_PERCENT);
        map.put(AttrId.ATTACK_VALUE_MAX, AttrId.ATTACK_VALUE_MAX_PERCENT);
        map.put(AttrId.DEFENCE, AttrId.DEFENCE_PERCENT);
        map.put(AttrId.HIT_RATE, AttrId.HIT_RATE_PERCENT);
        map.put(AttrId.HIT_RESIST_RATE, AttrId.HIT_RESIST_RATE_PERCENT);
        return map;
    }

    public static Map<Integer, Float> getWashMaxVal(Pet pet){
        final cfg.pet.PetExp pcfg = CfgMgr.petexp.get(pet.getLevel());
        final float multiRate = pcfg.enhancerate * pcfg.levelmaturerate * CfgMgr.petstagestar.get(pet.getStarlevel()).qualitymaturerate;
        final cfg.pet.Maturerate mcfg = CfgMgr.petbasicstatus.get(pet.getModelid()).matureratelist;

        Map<Integer, Float> map = new HashMap<>();
        map.put(AttrId.HP_FULL_VALUE, multiRate * mcfg.hpmaturerate);
        map.put(AttrId.MP_FULL_VALUE, multiRate * mcfg.mpmaturerate);
        map.put(AttrId.ATTACK_VALUE_MIN, multiRate * mcfg.minatkmaturerate);
        map.put(AttrId.ATTACK_VALUE_MAX, multiRate * mcfg.maxatkmaturerate);
        map.put(AttrId.DEFENCE, multiRate * mcfg.defmaturerate);
        map.put(AttrId.HIT_RATE, multiRate * mcfg.hitmaturerate);
        map.put(AttrId.HIT_RESIST_RATE, multiRate * mcfg.hitresistmaturerate);
        return map;
    }

    public static void checkPetNewAvaliableSkills(long roleid, xbean.Pet pet, boolean notifyChange) {
        final int level = pet.getLevel();
        Map<Integer, Integer> petSkills = pet.getSkills();

        Set<Integer> checkSkills = CfgMgr.petskill.get(pet.getModelid()).skilllist.stream()
                .filter(skillId -> !petSkills.containsKey(skillId)
                        && !petSkills.containsKey(FSkill.getBaseEvolveSkillid(skillId)))
                .collect(Collectors.toSet());

        checkSkills.forEach(skillId -> {
            if((CfgMgr.skilldmg.containsKey(skillId) && CfgMgr.skilldmg.get(skillId).isnormal)
                    || (CfgMgr.skilllvlupcost.containsKey(skillId) && CfgMgr.skilllvlupcost.get(skillId).skilllvlupdata.get(0).requirelvl <= level)
                    ){
                petSkills.put(skillId, 1);
                if(notifyChange) {
                    syncPetSkill(roleid, pet, skillId);
                }
            }
        });
    }

    public static boolean isActiveEquipPet(long roleid, int modelId) {
        return isActiveEquipPet(get(roleid), modelId);
    }

    public static boolean isActiveEquipPet(xbean.RolePet info, int modelId) {
        return info.getPetmap().containsKey(modelId) && info.getActivepetmodelid() == modelId;
    }

    public static boolean isFightPet(xbean.RolePet info, int modelId) {
        return info.getFightpets().contains(Integer.valueOf(modelId));
    }

    public static boolean isFightPet(long roleId, int modelId){
        return isFightPet(get(roleId), modelId);
    }

    public static void onPetLevelUp(Pet pet){
        //成长属性
        Map<Integer, Float> growthattrs = pet.getGrowthattrs();
        growthattrs.clear();

        final cfg.pet.PetBasicStatus model = CfgMgr.petbasicstatus.get(pet.getModelid());
        common.AttrUtils.convert(model.attr, growthattrs);

        float multiRate = CfgMgr.petexp.get(pet.getLevel()).levelmaturerate * CfgMgr.petstagestar.get(pet.getStarlevel()).qualitymaturerate;
        final cfg.pet.Maturerate mr = model.matureratelist;
        common.AttrUtils.addValue(growthattrs, AttrId.HP_FULL_VALUE, mr.hpmaturerate * multiRate);
        common.AttrUtils.addValue(growthattrs, AttrId.MP_FULL_VALUE, mr.mpmaturerate * multiRate);
        common.AttrUtils.addValue(growthattrs, AttrId.ATTACK_VALUE_MIN, mr.minatkmaturerate * multiRate);
        common.AttrUtils.addValue(growthattrs, AttrId.ATTACK_VALUE_MAX, mr.maxatkmaturerate * multiRate);
        common.AttrUtils.addValue(growthattrs, AttrId.DEFENCE, mr.defmaturerate * multiRate);
        common.AttrUtils.addValue(growthattrs, AttrId.HIT_RATE, mr.hitmaturerate * multiRate);
        common.AttrUtils.addValue(growthattrs, AttrId.HIT_RESIST_RATE, mr.hitresistmaturerate * multiRate);
    }

    public static float[] getPetFinalAttrs(Map<Integer, Float> attrMap){
        final float[] rawAttrs = newRawAttrs();
        attrMap.forEach((integer, aFloat) -> rawAttrs[integer] += aFloat);

        final float[] finalAttrs = newFinalAttrs();
        for(int i = 0 ; i < finalAttrs.length ; i++)
            AttrUtils.calcAttr(rawAttrs, finalAttrs, i, null);

        return finalAttrs;
    }

    public static Map<Integer, Float> getPetFinalAttrMap(Map<Integer, Float> rawAttrMap){
        final float[] finalAttrs = getPetFinalAttrs(rawAttrMap);
        Map<Integer, Float> finalAttrMap = new HashMap<>();
        for (int i = 0; i < finalAttrs.length; i++) {
            finalAttrMap.put(i, finalAttrs[i]);
        }
        return finalAttrMap;
    }

    public static Map<Integer, Float> getPetRawAttrs(Pet pet){
        Map<Integer, Float> attrMap = new HashMap<>();

        //等级属性
        AttrUtils.addValue(attrMap, pet.getGrowthattrs());

        //缘分属性
        AttrUtils.addValue(attrMap, pet.getKarmaattrs());

        //觉醒buff属性
        AttrUtils.addValue(attrMap, pet.getFixedattrs());

        //洗练属性
        AttrUtils.addValue(attrMap, pet.getWashattrs());
        return attrMap;
    }

    public static void genPetAttrsAndBuffs(xbean.Pet pet, List<Float> rawAttrs, List<Integer> buffs) {
        float[] attrs = AttrUtils.newRawAttrs();
        getPetRawAttrs(pet).forEach((integer, aFloat) -> attrs[integer] = aFloat);
        AttrUtils.fill(rawAttrs, attrs);
        buffs.addAll(pet.getBuffids());
    }

    public static void addExp(long roleid, xbean.Pet pet, long addExp, int roleLevel) {
        final int maxLevel = Math.min(CfgMgr.petexp.size(), roleLevel);
        long oldExp = pet.getExp();
        long newExp = oldExp + addExp;
        final int oldLevel = pet.getLevel();
        int newLevel = oldLevel;
        while(newLevel < maxLevel && newLevel < roleLevel) {
            long needExp = common.Utils.selfOrMax(CfgMgr.petexp.get(newLevel).exp);
            if(needExp > newExp)
                break;
            newExp -= needExp;
            newLevel++;
        }
        if(newExp != oldExp){
            pet.setExp(newExp);
        }

        if(newLevel > oldLevel) {
            pet.setLevel(newLevel);
            EventModule.INSTANCE.broadcastEvent(new PetLevelUpEvent(roleid));
            onPetLevelUp(pet);
            checkPetNewAvaliableSkills(roleid, pet, true);
            if(isActiveEquipPet(roleid, pet.getModelid())) {
                FMap.dispatchPetMessageInProcedure(roleid, pet.getModelid(), new XChangePetLevel(pet.getLevel()));
            }
            syncPetAttrs(roleid, pet);
        }
    }

    public static PetBasicStatus getModelById(int modelid) {
        return CfgMgr.petbasicstatus.get(modelid);
    }

    public static void syncFightPet(long roleid) {
        Transaction.tsendWhileCommit(roleid, new SSyncFightPets(new ArrayList<>(get(roleid).getFightpets())));
    }

    public static void onRoleLogin(long roleid) {
        RolePet rolePet = get(roleid);
        SPetInfo sPetInfo = new SPetInfo();
        sPetInfo.activemodelid = rolePet.getActivepetmodelid();
        sPetInfo.fightpets.addAll(rolePet.getFightpets());
        sPetInfo.petfragment.putAll(rolePet.getPetfragmentmap());
        rolePet.getPetmap().forEach((integer, pet) -> {
            checkPetNewAvaliableSkills(roleid, pet, false);
            onPetLevelUp(pet);
            updatePetKarma(roleid, pet);
            sPetInfo.petmap.put(integer, convert(pet));
        });
        Transaction.tsendWhileCommit(roleid, sPetInfo);
        calcTotalCombat(roleid);
    }

    public static void syncFragment(long roleId) {
        Transaction.tsendWhileCommit(roleId, new SSyncPetFragment(new HashMap<Integer, Integer>(get(roleId).getPetfragmentmapAsData())));
    }

    public static void syncFragmentByModelId(long roleId, int modelId) {
        SSyncPetFragment sSyncPetFragment = new SSyncPetFragment();
        sSyncPetFragment.petfragment.put(modelId, get(roleId).getPetfragmentmap().getOrDefault(modelId, 0));
        Transaction.tsendWhileCommit(roleId, sSyncPetFragment);
    }

    public static void checkRoleKarma(long roleId) {
        get(roleId).getPetmap().values().stream()
                .filter(pet -> updatePetKarma(roleId, pet))
                .forEach(pet -> syncPetAttrs(roleId, pet));
    }

    private static boolean updatePetKarma(long roleId, Pet pet) {
        Map<Integer, Pet> petMap = get(roleId).getPetmap();
        HashMap<Integer, Float> props = new HashMap<>();
        CfgMgr.petkarma.get(pet.getModelid()).petkarmas.forEach(karma -> {
            int minLevel = Const.NULL;
            for (Integer petkey : karma.petkeys) {
                Pet requirePet = petMap.get(petkey);
                if(requirePet == null){
                    minLevel = Const.NULL;
                    break;
                }
                if(minLevel == Const.NULL){
                    if(karma.carmatype == StarKarmaType.JUEXING){
                        minLevel = requirePet.getAwakelevel();
                    } else if(karma.carmatype == StarKarmaType.XINGJIE){
                        minLevel = requirePet.getStarlevel();
                    }
                } else {
                    if(karma.carmatype == StarKarmaType.JUEXING){
                        minLevel = Math.min(minLevel, requirePet.getAwakelevel());
                    } else if(karma.carmatype == StarKarmaType.XINGJIE){
                        minLevel = Math.min(minLevel, requirePet.getStarlevel());
                    }
                }
            }
            if(minLevel != Const.NULL){
                int targetLvl = minLevel;
                if(!karma.prop_level.containsKey(targetLvl)){
                    targetLvl = Const.NULL;
                    for (int lvl : karma.prop_level.keySet()) {
                        if(lvl < minLevel){
                            targetLvl = targetLvl == Const.NULL ? lvl : Math.max(lvl, targetLvl);
                        }
                    }
                }
                KarmaProp prop = karma.prop_level.get(targetLvl);
                if(prop != null && Utils.isNotNull(prop.karmadata)){
                    AttrUtils.addAttrs(props, prop.karmadata);
                }
            }
        });
        Map<Integer, Float> karmaAttr = pet.getKarmaattrs();
        if(!props.equals(karmaAttr)){
            karmaAttr.clear();
            karmaAttr.putAll(props);
            return true;
        }
        return false;
    }

    public static void syncPetSkill(long roleId, Pet pet, int skillId) {
        Map<Integer, Integer> skillMap = pet.getSkills();
        if(skillMap.containsKey(skillId)){
            Transaction.tsendWhileCommit(roleId, new SSyncPetSkill(pet.getModelid(), skillId, skillMap.get(skillId)));

            if(isActiveEquipPet(roleId, pet.getModelid())) {
                FMap.dispatchPetMessageInProcedure(roleId, pet.getModelid(), new XChangePetSkill(skillId, skillMap.get(skillId)));
            }

            syncCombatPower(roleId, pet);

            if(isFightPet(roleId, pet.getModelid())){
                calcTotalCombat(roleId);
            }
        }

    }

    public static void syncPetSkin(long roleId, Pet pet) {
        Transaction.tsendWhileCommit(roleId, new SSyncPetSkin(pet.getModelid(), pet.getActiveskinid()));

        if(isActiveEquipPet(roleId, pet.getModelid())) {
            FMap.dispatchPetMessageInProcedure(roleId, pet.getModelid(), new SChangePetSkin(pet.getActiveskinid()));
        }
    }

    public static void syncPetAttrs(long roleId, Pet pet) {
        Map<Integer, Float> attrs = getPetRawAttrs(pet);

        Transaction.tsendWhileCommit(roleId, new SSyncPetAttrs(pet.getModelid(), new HashMap<>(getPetFinalAttrMap(attrs))));

        RolePet rolepet = get(roleId);
        if(isActiveEquipPet(rolepet, pet.getModelid())) {
            XChangePetAttr xChangePetAttr = new XChangePetAttr();
            AttrUtils.fill(xChangePetAttr.attr, attrs);
            FMap.dispatchPetMessageInProcedure(roleId, pet.getModelid(), xChangePetAttr);
        }
        if(isFightPet(rolepet, pet.getModelid())) {
            calcTotalCombat(roleId);
        }
        // 战力必然随着属性变化而变化
        syncCombatPower(roleId, pet);
    }

    public static void syncAwake(long roleid, Pet pet) {
        Transaction.tsendWhileCommit(roleid, new SUpgradePetAwake(pet.getModelid(), pet.getAwakelevel()));

        if(isActiveEquipPet(roleid, pet.getModelid())){
            FMap.dispatchPetMessageInProcedure(roleid, pet.getModelid(), new SChangePetAwakeLevel(pet.getAwakelevel()));
        }
    }

    public static boolean addPetFragment(long roleid, int fragmentId, int num, By by) {
        if(!CfgMgr.petfragment.containsKey(fragmentId)){
            return false;
        }
        RolePet rolePet = get(roleid);
        Map<Integer, Integer> fragmentMap = rolePet.getPetfragmentmap();
        int curr = fragmentMap.getOrDefault(fragmentId, 0);
        curr += num;
        if(curr < 0) {
            Transaction.tsend(roleid, new SError(ErrorCode.PET_FRAGMENT_NOT_ENOUGH.getErrorId()));
            return false;
        }
        fragmentMap.put(fragmentId, curr);
        if(num < 0){
            FLogger.costitem(roleid, Roleinfos.get(roleid), fragmentId, num, by);
        } else if(num > 0){
            FLogger.gainitem(roleid, Roleinfos.get(roleid), fragmentId, num, by);
        }
        syncFragmentByModelId(roleid, fragmentId);
        return true;
    }

    public static boolean addPet(long roleid, int modelId, int num, By by) {
        if(num < 1){
            Trace.error("num is error");
            return false;
        }
        RolePet rolePet = get(roleid);
        Map<Integer, Pet> petMap = rolePet.getPetmap();
        if(!petMap.containsKey(modelId)){
            num--;
            addPet(roleid, modelId, by);
            EventModule.INSTANCE.broadcastEvent(new AddPetEvent(roleid));
        }
        if(num == 0) return true;
        int fragmentId = getModelById(modelId).fragmentid;
        PetFragment petFragment = getPetFragmentById(fragmentId);
        int total = (int) (Math.multiplyExact(petFragment.number, num) * PetBasicStatus.TURN_PET_INTO_FRAGMENT_RATE);
        return addPetFragment(roleid, fragmentId, total, by);
    }

    public static PetFragment getPetFragmentById(int fragmentId) {
        return CfgMgr.petfragment.get(fragmentId);
    }

    public static boolean isPetExist(long roleId, int petModelId){
        return get(roleId).getPetmap().containsKey(petModelId);
    }

    public static void activePet(long roleid, Pet pet) {
        FMap.dispatchMessageInProcedure(roleid, new SEquipPet(FMap.createPetBuilder(roleid, pet)));
        Transaction.tsendWhileCommit(roleid, new SActivePet(pet.getModelid()));
    }

    public static void genPetBriefs(long roleid, List<PetBrief> pets) {
        Rolepet.getTable().select(roleid, rolePet ->{
            for (int petId : rolePet.getFightpets()) {
                pets.add(new PetBrief(petId));
            }
            return rolePet;
        });
    }
}
