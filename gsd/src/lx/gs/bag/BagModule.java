package lx.gs.bag;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import cfg.cmd.condition.Condition;
import cfg.item.EItemType;
import cfg.talisman.TalismanFeed;
import lx.gs.cmd.FCondition;
import lx.gs.depot.FDepot;
import xtable.Goldcoindepot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Jin Shuai
 */
public enum BagModule implements gs.Module, gs.DayIdleListener, gs.RoleCreateListener, gs.RoleLoginListener {
    INSTANCE;

    private final Map<Integer, Integer> equipPosOnBody = new HashMap<>();
    private final Map<Integer, Integer> bagDepotMap = new HashMap<>();
    private final Map<Long, Map<Integer, Long>> sortCdMap = new ConcurrentHashMap<>();

    private final Map<Integer, Map<Integer, Condition>> slotConditionMap = new HashMap<>();

    @Override
    public void start() {
        initBodyEquipBagConfig();
        initBagDepotAssociate();

        CfgMgr.slotbagconfig.condtioninfo.forEach(condition -> {
            if(!slotConditionMap.containsKey(condition.bagtype)){
                slotConditionMap.put(condition.bagtype, new HashMap<>());
            }
            slotConditionMap.get(condition.bagtype).put(condition.slotindex, condition.condition);
        });
    }

    public boolean isSlotUsable(long roleId, int bagType, int slotPosition){
        Condition condition;
        if(slotConditionMap.containsKey(bagType) && (condition = slotConditionMap.get(bagType).get(slotPosition)) != null){
            return FCondition.checkA(roleId, condition, 1, null, -1, -1).ok();
        }
        return true;
    }

    private void initBagDepotAssociate() {
        bagDepotMap.put(BagType.EQUIP, BagType.DEPOT_EQUIP);
        bagDepotMap.put(BagType.FRAGMENT, BagType.DEPOT_FRAGMENT);
        bagDepotMap.put(BagType.TALISMAN, BagType.DEPOT_TALISMAN);
        bagDepotMap.put(BagType.ITEM, BagType.DEPOT_ITEM);

        bagDepotMap.put(BagType.DEPOT_EQUIP, BagType.EQUIP);
        bagDepotMap.put(BagType.DEPOT_FRAGMENT, BagType.FRAGMENT);
        bagDepotMap.put(BagType.DEPOT_TALISMAN, BagType.TALISMAN);
        bagDepotMap.put(BagType.DEPOT_ITEM, BagType.ITEM);
    }

    public int getAssociatedBagOrDepot(int bagType) {
        if (bagDepotMap.containsKey(bagType)) {
            return bagDepotMap.get(bagType);
        }
        return Const.NULL;
    }

    @Override
    public void onDayIdle() {}

    @Override
    public void onRoleCreateInProcedure(long roleid) {
        /** 包裹 */
        xbean.RoleItemBag itemBag = xbean.Pod.newRoleItemBag();
        xtable.Roleitembag.add(roleid, itemBag);

        xbean.RoleEquipBag equipBag = xbean.Pod.newRoleEquipBag();
        xtable.Roleequipbag.add(roleid, equipBag);

        xbean.RoleFragmentBag fragBag = xbean.Pod.newRoleFragmentBag();
        xtable.Rolefragmentbag.add(roleid, fragBag);

        xbean.RoleTalismanBag talismanBag = xbean.Pod.newRoleTalismanBag();
        talismanBag.setLuckytype(TalismanFeed.DEFAULT_LUCK_ID);
        xtable.Roletalismanbag.add(roleid, talismanBag);

        /** 仓库 */
        xbean.RoleEquipDepot equipDepot = xbean.Pod.newRoleEquipDepot();
        xtable.Roleequipdepot.insert(roleid, equipDepot);

        xbean.RoleTalismanDepot talismanDepot = xbean.Pod.newRoleTalismanDepot();
        xtable.Roletalismandepot.insert(roleid, talismanDepot);

        xbean.RoleFragmentDepot fragmentDepot = xbean.Pod.newRoleFragmentDepot();
        xtable.Rolefragmentdepot.insert(roleid, fragmentDepot);

        xbean.RoleItemDepot itemDepot = xbean.Pod.newRoleItemDepot();
        xtable.Roleitemdepot.insert(roleid, itemDepot);

        Goldcoindepot.insert(roleid, 0L);
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {
        /** 包裹 */
//        xtable.Roleitembag.delete(roleid);
//        xtable.Roleequipbag.delete(roleid);
//        xtable.Rolefragmentbag.delete(roleid);
//        xtable.Roletalismanbag.delete(roleid);

        /** 仓库 */
//        xtable.Roleequipdepot.delete(roleid);
//        xtable.Rolefragmentdepot.delete(roleid);
//        xtable.Roleitemdepot.delete(roleid);
//        xtable.Roletalismandepot.delete(roleid);
//        xtable.Goldcoindepot.delete(roleid);
    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        BagType.enums.forEach(bagType -> FBag.getBag(roleid, bagType).syncBag());
        FDepot.syncDepotGoldcoin(roleid);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {}

    private void initBodyEquipBagConfig() {
        equipPosOnBody.put(EItemType.WEAPON, 1);
        equipPosOnBody.put(EItemType.HAT, 2);
        equipPosOnBody.put(EItemType.CLOTH, 3);
        equipPosOnBody.put(EItemType.SHOE, 4);
        equipPosOnBody.put(EItemType.RING, 5);
        equipPosOnBody.put(EItemType.NECKLACE, 7);
        equipPosOnBody.put(EItemType.BANGLE, 8);
    }

    public int getPosOnBody(int equipType) {
        if (equipPosOnBody.containsKey(equipType)) {
            return equipPosOnBody.get(equipType);
        }
        return AbstractBag.INVALID_POS;
    }

    public boolean onSort(long roleId, int bagType){
        long now = System.currentTimeMillis();
        if(!sortCdMap.containsKey(roleId)){
            Map<Integer, Long> map = new HashMap<>();
            BagType.enums.forEach(integer -> map.put(integer, 0l));
            sortCdMap.putIfAbsent(roleId, map);
        }
        long cd = TimeUnit.SECONDS.toMillis((long) CfgMgr.bagconfig.get(bagType).sortcd.time);
        long canUseTime = sortCdMap.get(roleId).get(bagType) + cd;
        if(canUseTime > now){
            return false;
        }
        sortCdMap.get(roleId).put(bagType, now + cd);
        return true;
    }
}