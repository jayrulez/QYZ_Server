package lx.gs.bag;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import lx.gs.depot.FDepot;
import lx.gs.equip.FEquip;
import lx.gs.event.AddItemEvent;
import lx.gs.event.DeleteItemEvent;
import lx.gs.event.EventModule;
import lx.gs.event.TalismanAddEvent;
import lx.gs.fragment.FFragment;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.logger.FLogger;
import lx.gs.talisman.FTalisman;
import xtable.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class FBag {

    @SuppressWarnings("unchecked")
    public static <E> StorageBag<E> getStorageBag(long roleid, int bagtype) {
        AbstractBag bag = getBag(roleid, bagtype);
        if (bag instanceof StorageBag) {
            return (StorageBag<E>) bag;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <E> AbstractBag<E> getBag(long roleid, int bagtype) {
        return getBag0(roleid, bagtype);
    }

    private static AbstractBag getBag0(long roleid, int bagtype) {
        switch (bagtype) {
            case BagType.EQUIP_BODY: return FEquip.getBodyBag(roleid);
            case BagType.EQUIP: return FEquip.getEquipBag(roleid);
            case BagType.ITEM: return FItem.getItemBag(roleid);
            case BagType.TALISMAN: return FTalisman.getTalismanBag(roleid);
            case BagType.TALISMAN_BODY: return FTalisman.getBodyTalismanBag(roleid);
            case BagType.FRAGMENT: return FFragment.getFragmentBag(roleid);
            case BagType.DEPOT_EQUIP:
            case BagType.DEPOT_FRAGMENT:
            case BagType.DEPOT_TALISMAN:
            case BagType.DEPOT_ITEM: return FDepot.getDepot(roleid, bagtype);
            default: return null;
        }
    }

    @SuppressWarnings("unchecked")
    public static <E> Map<Integer, E> getDbData(long roleId, int bagType) {
        return (Map<Integer, E>) getDbData0(roleId, bagType);
    }

    private static Map<Integer, ?> getDbData0(long roleId, int bagType) {
        switch (bagType){
            case BagType.EQUIP : return FEquip.getDbBag(roleId).getEquipmap();
            case BagType.EQUIP_BODY : return FEquip.getDbBag(roleId).getEquiponbodymap();
            case BagType.DEPOT_EQUIP : return Roleequipdepot.get(roleId).getEquipbag();

            case BagType.TALISMAN : return FTalisman.getDbBag(roleId).getTalismans();
            case BagType.TALISMAN_BODY : return FTalisman.getDbBag(roleId).getEquipedtalismans();
            case BagType.DEPOT_TALISMAN : return Roletalismandepot.get(roleId).getTalismans();

            case BagType.ITEM : return FItem.getDbBag(roleId).getItems();
            case BagType.DEPOT_ITEM : return Roleitemdepot.get(roleId).getItems();

            case BagType.FRAGMENT : return FFragment.getDbBag(roleId).getItems();
            case BagType.DEPOT_FRAGMENT : return Rolefragmentdepot.get(roleId).getFragments();
        }
        return null;
    }

    public static int getBagEmptyGridNum(long roleId, int bagType){
        return getBag(roleId, bagType).emptyGridNum();
    }

    public static <E> E deleteItemByPos(long roleId, int bagType, int pos, By by){
        return deleteItemByPos(getBag(roleId, bagType), pos, by);
    }

    public static <E> E deleteItemByPos(AbstractBag<E> bag, int pos, By by){
        E item = bag.deleteByPosition(pos);
        if(item != null) {
            FLogger.costitem(bag.roleid, Roleinfos.get(bag.roleid), bag.getModelId(item), bag.getNum(item), by);
            broadcastDeleteEvent(bag.roleid, bag.bagType);
        }
        return item;
    }

    public static <E> E delItemByPosNum(long roleId, int bagType, int pos, int num, By by){
        return delItemByPosNum(getStorageBag(roleId, bagType), pos, num, by);
    }

    public static <E> E delItemByPosNum(StorageBag<E> bag, int pos, int num, By by){
        E item = bag.deleteByPosition(pos, num);
        if(item != null) {
            FLogger.costitem(bag.roleid, Roleinfos.get(bag.roleid), bag.getModelId(item), num, by);
            broadcastDeleteEvent(bag.roleid, bag.bagType);
        }
        return item;
    }

    public static boolean deleteBindFirst(long roleId, int bagType, int modelId, int count, By by) {
        return deleteBindFirst(getStorageBag(roleId, bagType), modelId, count, null, by);
    }

    public static <E> boolean deleteBindFirst(long roleId, int bagType, int modelId, int count, Collection<E> costBindItemList, By by) {
        return deleteBindFirst(getStorageBag(roleId, bagType), modelId, count, costBindItemList, by);
    }

    public static <E> boolean deleteBindFirst(StorageBag<E> bag, int modelId, int count, By by) {
        return deleteBindFirst(bag, modelId, count, null, by);
    }

    public static <E> boolean deleteBindFirst(StorageBag<E> bag, int modelId, int count, Collection<E> costBindItemList, By by) {
        boolean result = bag.deleteBindFirst(modelId, count, costBindItemList);
        if(result) FLogger.costitem(bag.roleid, Roleinfos.get(bag.roleid), modelId, count, by);
        broadcastDeleteEvent(bag.roleid, bag.bagType);
        return result;
    }

    public static <E> boolean deleteUnBind(StorageBag<E> bag, int modelId, int count, By by) {
        boolean result = bag.delete(modelId, count, false);
        if(result) FLogger.costitem(bag.roleid, Roleinfos.get(bag.roleid), modelId, count, by);
        broadcastDeleteEvent(bag.roleid, bag.bagType);
        return result;
    }

    public static int belongBagType(int modelId) {
        if (CfgMgr.equip.containsKey(modelId)) {
            return BagType.EQUIP;
        } else if (CfgMgr.itembasic.containsKey(modelId)) {
            return BagType.ITEM;
        } else if (CfgMgr.fragment.containsKey(modelId)) {
            return BagType.FRAGMENT;
        } else if (CfgMgr.talismanbasic.containsKey(modelId)) {
            return BagType.TALISMAN;
        }
        return Const.NULL;
    }

    public static boolean addItemToBag(long roleId, int modelId, int count, boolean isBind, By by) {
        return addItemToBag(roleId, modelId, count, isBind, null, by);
    }

    public static boolean addItemToBag(long roleId, int modelId, int count, Boolean isBind, Long expireTime, By by) {
        int bagType = belongBagType(modelId);
        if (bagType == Const.NULL) return false;
        return addItemToBag(roleId, bagType, modelId, count, isBind, expireTime, by);
    }

    public static boolean addItemToBag(long roleid, int bagType, int modelId, int num, boolean isBind, By by) {
        return addItemToBag(roleid, bagType, modelId, num, isBind, null, by);
    }

    public static boolean addItemToBag(long roleId, int bagType, int modelId, int num, Boolean isBind, Long expireTime, By by) {
        return addItemToBag(getStorageBag(roleId, bagType), modelId, num, isBind, expireTime, by);
    }

    public static boolean addItemToBag(StorageBag bag, int modelId, int num, Boolean isBind, Long expireTime, By by) {
        boolean result = bag.addItem(modelId, num, isBind, expireTime);
        if(result) FLogger.gainitem(bag.roleid, Roleinfos.get(bag.roleid), modelId, num, by);
        broadcastAddEvent(bag.roleid, bag.bagType);
        return result;
    }



    public static <E> boolean addItemToBag(long roleid, int bagType, List<E> items, By by) {
        return addItemToBag(getStorageBag(roleid, bagType), items, by);
    }

    public static <E> boolean addItemToBag(StorageBag<E> bag, Map<Integer, Integer> items, boolean isBind, By by){
        List<E> list = new ArrayList<>();
        items.forEach((id, count) -> list.addAll(bag.createItem(id, count, isBind)));
        return addItemToBag(bag, list, by);
    }

    public static <E> boolean addItemToBag(StorageBag<E> bag, List<E> items, By by) {
        for (E item : items) {
            if (!bag.add(item)){
                return false;
            }
            FLogger.gainitem(bag.roleid, Roleinfos.get(bag.roleid), bag.getModelId(item), bag.getNum(item), by);
        }
        broadcastAddEvent(bag.roleid, bag.bagType);
        return true;
    }

    public static void broadcastAddEvent(long roleId, int bagType) {
        switch (bagType){
            case BagType.ITEM: EventModule.INSTANCE.broadcastEvent(new AddItemEvent(roleId)); break;
            case BagType.TALISMAN: EventModule.INSTANCE.broadcastEvent(new TalismanAddEvent(roleId)); break;
            default: break;
        }
    }

    public static void broadcastDeleteEvent(long roleId, int bagType) {
        switch (bagType){
            case BagType.ITEM: EventModule.INSTANCE.broadcastEvent(new DeleteItemEvent(roleId)); break;
            default: break;
        }
    }

    public static int countItemByQuality(long roleId, int bagType, int quality, int level) {
        if(bagType == BagType.EQUIP_BODY){
            return (int) FEquip.getBodyBag(roleId).getItems().stream()
                    .filter(equip -> {
                        cfg.equip.Equip conf = FEquip.getEquipModel(equip);
                        return conf.quality == quality && conf.level == level;
                    })
                    .count();
        } else if(bagType == BagType.TALISMAN_BODY){
            return (int) FTalisman.getBodyTalismanBag(roleId).getItems().stream()
                    .filter(talisman -> FTalisman.getModelById(talisman.getModelid()).quality == quality)
                    .count();
        }
        return getStorageBag(roleId, bagType).countItemByQuality(quality);
    }
}
