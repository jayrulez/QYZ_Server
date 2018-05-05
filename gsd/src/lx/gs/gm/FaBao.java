package lx.gs.gm;

import cfg.bag.BagType;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.bag.FBag;
import lx.gs.logger.By;

import java.util.HashMap;

@Module(comment = "法宝模块GM命令")
public class FaBao {

    @Cmd(comment = "增加一个法宝到背包")
    public Object addFabaoToBag(
            @Param(name = "roleid", comment = "角色id:roleid") long roleid,
            @Param(name = "itemkey", comment = "物品key:itemkey") int itemkey,
            @Param(name = "number", comment = "物品的数量:number") int number) {
        HashMap<String, Object> result = new HashMap<String, Object>();
        boolean flag = FBag.addItemToBag(roleid, BagType.TALISMAN, itemkey, number, false, By.Gm);
        result.put("result", flag);
        return result;
    }
}
