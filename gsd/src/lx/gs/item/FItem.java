package lx.gs.item;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import cfg.cmd.ConfigId;
import cfg.item.EItemBindType;
import cfg.item.ItemBasic;
import cfg.item.ItemGiftPack;
import cfg.item.LevelUpType;
import com.goldhuman.Common.Marshal.Marshal;
import common.ErrorCode;
import common.Utils;
import lx.gs.bag.AbstractBag;
import lx.gs.bag.FBag;
import lx.gs.bag.ItemBag;
import lx.gs.bonus.FBonus;
import lx.gs.cmd.FCondition;
import lx.gs.dress.FDress;
import lx.gs.idgen.FIdGen;
import lx.gs.logger.By;
import lx.gs.map.FMap;
import lx.gs.mount.FRide;
import lx.gs.pet.FPet;
import lx.gs.role.FRole;
import map.msg.XUseMedicine;
import xbean.Item;
import xtable.Roleitembag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * 物品功能模块
 *
 * @author Jin Shuai
 */
public class FItem {

    public static ItemBag getItemBag(long roleId) {
        xbean.RoleItemBag dbBag = getDbBag(roleId);
        return new ItemBag(roleId, BagType.ITEM, dbBag.getCapacity());
    }

    public static xbean.RoleItemBag getDbBag(long roleId) {
        return Roleitembag.get(roleId);
    }

    public static Marshal convert(Item slot) {
        lx.gs.item.Item item = new lx.gs.item.Item();
        item.itemid = slot.getItemid();
        item.modelid = slot.getModelid();
        item.count = slot.getCount();
        item.isbind = slot.getIsbind() ? Const.TRUE : Const.FALSE;
        item.expiretime = slot.getExpiretime();
        item.position = slot.getPosition();
        return item;
    }

    public static ItemBasic getModelById(int modelId) {
        return CfgMgr.itembasic.get(modelId);
    }

    /**
     * 根据物品key和绑定类型创建一个物品对象
     *
     * @return
     */

    private static List<xbean.Item> createItem(int modelId, int num) {
        cfg.item.ItemBasic model = cfg.CfgMgr.itembasic.get(modelId);
        if(model == null){
            throw new IllegalArgumentException("item config id not found in config. " + modelId);
        }
        List<xbean.Item> result = new ArrayList<>();
        if (num <= 0) return result;
        int remain = num;
        int maxStack = getMaxStack(model);
        while (remain > 0) {
            xbean.Item newitem = xbean.Pod.newItem();
            newitem.setModelid(modelId);
            newitem.setIsbind(model.bindtype.bindtype == EItemBindType.BOUND);
            newitem.setPosition(AbstractBag.INVALID_POS);
            if (remain > maxStack) {
                newitem.setCount(maxStack);
            } else {
                newitem.setCount(remain);
            }
            result.add(newitem);
            remain -= newitem.getCount();
        }
        return result;
    }

    public static List<xbean.Item> createItem(long roleId, int modelId, int num, boolean isBind) {
        return createItem(roleId, modelId, num, isBind, null);
    }

    public static List<xbean.Item> createItem(long roleId, int modelId, int num, Boolean isBind, Long expireTime) {
        List<xbean.Item> result = createItem(modelId, num);
        result.forEach(item -> {
            item.setItemid(FIdGen.allocItemId(roleId));
            if (isBind != null) item.setIsbind(isBind);
            if (expireTime != null) item.setExpiretime(expireTime);
        });
        return result;
    }

    public static boolean addItemToBag(long roleid, int modelId, int num, boolean isBind, By by) {
        return FBag.addItemToBag(roleid, BagType.ITEM, modelId, num, isBind, by);
    }

    public static boolean addItemToBag(long roleid, List<xbean.Item> items, By by) {
        return FBag.addItemToBag(roleid, BagType.ITEM, items, by);
    }

    public static ErrorCode doItemAction(long roleid, cfg.item.ItemBasic itemconf, int usenum, By by) {
        switch (itemconf.getTypeId()) {
            case cfg.item.ItemMedicine.TYPEID: {
                FMap.dispatchMessageInProcedure(roleid, new XUseMedicine(itemconf.id));
                return ErrorCode.OK;
            }
            case cfg.item.ItemGiftPack.TYPEID: {
                if(!FBonus.addBonus(roleid, ((ItemGiftPack) itemconf).itempacklist, usenum, common.Bonus.BindType.BIND, by)){
                    return ErrorCode.BAG_FULL;
                }
                return ErrorCode.OK;
            }
            case cfg.item.ItemDress.TYPEID: {
                cfg.item.ItemDress dconf = (cfg.item.ItemDress) itemconf;
                return FDress.unlockDress(roleid, dconf.dressid, dconf.effectiveime);
            }
            case cfg.item.ItemRiding.TYPEID: {
                cfg.item.ItemRiding rconf = (cfg.item.ItemRiding) itemconf;
                return FRide.unlockRide(roleid, rconf.ridingid, rconf.effectiveime);
            }
            case cfg.item.ItemLevelUp.TYPEID: {
                final cfg.item.ItemLevelUp ucfg = (cfg.item.ItemLevelUp) itemconf;
                switch (ucfg.leveluptype) {
                    case LevelUpType.ROLE_LEVEL: {
                        return FRole.addLevel(roleid, usenum);
                    }
                    default:
                        return ErrorCode.ITEM_CAN_NOT_USE;
                }
            }
            default: {
                return ErrorCode.ITEM_CAN_NOT_USE;
            }
        }
    }

    public static boolean spendItemBindFirst(long roleId, int itemModelId, int count, By by) {
        return spendItemBindFirst(roleId, itemModelId, count, null, by);
    }

    public static boolean spendItemBindFirst(long roleId, int itemModelId, int count, Collection<Item> costBindItemList, By by) {
        if (CfgMgr.petfragment.containsKey(itemModelId)) {
            return FPet.addPetFragment(roleId, itemModelId, -count, by);
        }
        return FBag.deleteBindFirst(roleId, FBag.belongBagType(itemModelId), itemModelId, count, costBindItemList, by);
    }

    public static int getItemNum(long roleid, int modelId) {
        return getItemNum(roleid, modelId, null);
    }

    public static int getItemNum(long roleid, int modelId, Boolean isBind) {
        return getItemBag(roleid).countItem(modelId, isBind);
    }

    public static ErrorCode useItemByPos(long roleid, int pos, int usenumber) {
        ItemBag itemBag = FItem.getItemBag(roleid);
        xbean.Item useItem = FBag.delItemByPosNum(itemBag, pos, usenumber, By.Item_Use);
        if (useItem == null) {
            return ErrorCode.ITEM_NUMBER_NOT_ENOUGH;
        }
        itemBag.notifyChange();

        return useItem(roleid, getModelById(useItem.getModelid()), usenumber, By.Item_Use);
    }

    public static ErrorCode useItem(long roleid, cfg.item.ItemBasic itemconf, int usenum, By by) {
        if (usenum <= 0) {
            return ErrorCode.PARAM_ERROR;
        }
        if (!itemconf.batch && usenum > 1) {
            return ErrorCode.ITEM_CAN_NOT_BATCH_USE;
        }
        ErrorCode err = FCondition.checkByReflection(roleid, itemconf, usenum, By.Item_Use, ConfigId.ITEMBASIC, itemconf.id);
        if (err.err()) {
            return err;
        }
        return doItemAction(roleid, itemconf, usenum, by);
    }

    public static int getMaxStack(ItemBasic model) {
        return Utils.selfOrMin(model.maxpile);
    }
}
