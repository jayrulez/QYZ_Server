package lx.gs.gm;

import cfg.CfgMgr;
import gm.GmSession;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.logger.By;
import lx.gs.pet.FPet;

import java.util.HashMap;
import java.util.LinkedHashMap;

@Module(comment="伙伴模块GM命令")
public class Pet {

    @Cmd(comment="获得所有伙伴名字和id对照信息")
    public Object all(){
        HashMap<String, String> result = new LinkedHashMap<>();
        CfgMgr.petbasicstatus.values().forEach(pet -> result.put(pet.modelname, pet.id + ""));
        return result;
    }

	@Cmd(comment="获取伙伴原始属性")
	public Object prop(
			@Param(name="petid") int petid){
        long roleId = GmSession.current().getRoleid();

		HashMap<String, String> result = new LinkedHashMap<>();
		xbean.Pet pet = FPet.getPetByModelId(roleId, petid);
        if(pet == null){
            result.put("伙伴还未获得","");
            return result;
        }
        result.put("成长属性", "");
        pet.getGrowthattrs().forEach((integer, aFloat) -> add(result, integer, aFloat));
        result.put("缘分属性", "");
        pet.getKarmaattrs().forEach((integer, aFloat) -> add(result, integer, aFloat));
        result.put("觉醒属性", "");
        pet.getFixedattrs().forEach((integer, aFloat) -> add(result, integer, aFloat));
        result.put("洗练属性", "");
        pet.getWashattrs().forEach((integer, aFloat) -> add(result, integer, aFloat));
        return result;
	}

    private static void add(HashMap<String, String> map, int key, float val){
        if(val != 0){
            map.put(key + "", val + "");
        }
    }

    @Cmd(comment="添加伙伴碎片")
    public Object addfrag(
            @Param(name="fragmentId") int fragmentId,
            @Param(name="num") int num
    ){
        long roleId = GmSession.current().getRoleid();
        FPet.addPetFragment(roleId, fragmentId, num, By.Gm);
        return true;
    }

}
