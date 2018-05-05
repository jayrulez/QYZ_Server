package lx.gs.gm;

import cfg.CfgMgr;
import cfg.Const;
import cfg.bag.BagType;
import cfg.pet.PetFragment;
import gm.GmException;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.bag.FBag;
import lx.gs.bag.ItemBag;
import lx.gs.item.FItem;
import lx.gs.item.ItemModule;
import lx.gs.logger.By;
import lx.gs.pet.FPet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Module(comment = "背包模块GM命令,包括物品背包，碎片背包，装备背包")
public class Bag {
    @Cmd(comment = "获取装备背包内容.")
    public Object getEquipBag() {
        final long roleid = GmSession.current().getRoleid();
        HashMap<String, Object> result = new HashMap<String, Object>();
        xbean.RoleEquipBag bag = xtable.Roleequipbag.get(roleid);
        if (bag != null) {
            result.put("dressed", bag.getEquiponbodymap());
            result.put("bag", bag.getEquipmap());
        }
        return result;
    }

    @Cmd(comment = "根据名字获取物品id")
    public List<Integer> getId(@Param(name = "name", comment = "物品name:name") String name) {
        return ItemModule.INSTANCE.getIdByName(name);
    }

    @Cmd(comment = "根据物品id获取名字")
    public String getName(@Param(name = "id", comment = "物品id:id") int id) {
        return ItemModule.INSTANCE.getNameById(id);
    }

    @Cmd(comment = "获取物品背包内容.")
    public Object getItemBag() {
        final long roleid = GmSession.current().getRoleid();
        HashMap<String, Object> result = new HashMap<String, Object>();
        ItemBag bag = FItem.getItemBag(roleid);
        if (bag != null) {
            result.put("bag", bag.getItems());
        }
        return result;
    }

    @Cmd(comment = "添加物品到背包, 可以添加背包支持的任何类型")
    public Object addItem(
            @Param(name = "item", comment = "物品,可以填id，也可以填名字") String item,
            @Param(name = "number", comment = "物品的数量:number") int number,
            @Param(name = "isbind", comment = "绑定填1，非绑定填0，填其他则绑定") int isbind) {
        final long roleid = GmSession.current().getRoleid();
        int itemkey;
        try {
            itemkey = Integer.parseInt(item);
        } catch (Exception e){
            List<Integer> idLIst = getId(item);
            if(idLIst.size() > 1){
                throw new GmException("添加失败，物品有多个，id : " + new ArrayList<>(idLIst).toString());
            }
            if(idLIst.isEmpty()){
                throw new GmException("添加失败，找不到物品");
            }
            itemkey = idLIst.get(0);
        }
        if(itemkey == Const.NULL){
            throw new GmException("找不到此物品");
        }

        boolean res = FBag.addItemToBag(roleid, itemkey, number, isbind != Const.FALSE, By.Gm);
        if(!res){
            throw new GmException("添加失败， 查看包裹容量是否足够");
        }
        return res;
    }


    @Cmd(comment = "清除碎片包裹信息")
    public Object clearFragBag(
    ) {
        final long roleid = GmSession.current().getRoleid();
        return FBag.getBag(roleid, BagType.FRAGMENT).clear();
    }

    @Cmd(comment = "清除物品包裹信息")
    public Object clearItemBag() {
        final long roleid = GmSession.current().getRoleid();
        return FBag.getBag(roleid, BagType.ITEM).clear();
    }

    @Cmd(comment = "清除装备包裹信息")
    public Object clearEquipBag() {
        final long roleid = GmSession.current().getRoleid();
        return FBag.getBag(roleid, BagType.EQUIP).clear();
    }

    @Cmd(comment = "增加一个伙伴")
    public Object addPet(
            @Param(name = "petkey", comment = "伙伴key:petkey") int petkey
    ) {
        final long roleid = GmSession.current().getRoleid();
        FPet.addPet(roleid, petkey, By.Gm);
        return  "success";
    }

    @Cmd(comment = "增加伙伴碎片,所有各加1000")
    public Object addPetFragment(
    ) {
        final long roleid = GmSession.current().getRoleid();
        boolean ret = true;
        for (PetFragment petFragment : CfgMgr.petfragment.values()) {
            ret &= FPet.addPetFragment(roleid, petFragment.id, 1000, By.Gm);
        }
        FPet.syncFragment(roleid);
        return ret;
    }
}
