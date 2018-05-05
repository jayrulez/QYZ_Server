package lx.gs.item;


import cfg.CfgMgr;
import cfg.currency.CurrencyType;
import cfg.item.EItemType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiong on 2016/6/6.
 */
public enum ItemModule implements gs.Module {
    INSTANCE;

    private static Map<Integer, Integer> item2ItemType = new HashMap<>();

    private Map<String, List<Integer>> name2Id = new HashMap<>();
    
    private Map<Integer, String> id2Name = new HashMap<>();

    @Override
    public void start() {
        //filter currency
        CfgMgr.itembasic.values().stream().filter(e -> !CurrencyType.enums.contains(e.id)).forEach(e -> item2ItemType.put(e.id, e.itemtype));
        CfgMgr.equip.values().forEach(e -> item2ItemType.put(e.id, e.type));
        CfgMgr.fragment.values().forEach(e -> item2ItemType.put(e.id, EItemType.FRAGMENT));
        CfgMgr.talismanbasic.values().forEach(e -> item2ItemType.put(e.id, EItemType.TALISMAN));
        CfgMgr.petbasicstatus.values().forEach(e -> item2ItemType.put(e.id, EItemType.PET));
        CfgMgr.petfragment.values().forEach(e -> item2ItemType.put(e.id, EItemType.PETFRAGMENT));
        CfgMgr.petskin.values().forEach(e -> item2ItemType.put(e.id, EItemType.SKIN));
        CfgMgr.dress.values().forEach(e -> item2ItemType.put(e.id, EItemType.FASHION));
        CfgMgr.riding.values().forEach(e -> item2ItemType.put(e.id, EItemType.RIDING));

        initItemName();
    }

    private void initItemName() {
        CfgMgr.itembasic.values().stream().filter(e -> !CurrencyType.enums.contains(e.id))
                .forEach(e -> add(e.id, e.name));
        CfgMgr.equip.values().forEach(e -> add(e.id, e.name));
        CfgMgr.fragment.values().forEach(e -> add(e.id, e.name));
        CfgMgr.talismanbasic.values().forEach(e -> add(e.id, e.name));
        CfgMgr.petbasicstatus.values().forEach(e -> add(e.id, e.name));
        CfgMgr.petfragment.values().forEach(e -> add(e.id, e.name));
        CfgMgr.petskin.values().forEach(e -> add(e.id, e.name));
        CfgMgr.dress.values().forEach(e -> add(e.id, e.name));
        CfgMgr.riding.values().forEach(e -> add(e.id, e.name));
    }

    private void add( int id, String name){
        id2Name.put(id, name);

        if(!name2Id.containsKey(name)){
            name2Id.put(name, new ArrayList<>());
        }
        name2Id.get(name).add(id);
    }
    
    public List<Integer> getIdByName(String name){
        return name2Id.getOrDefault(name, new ArrayList<>());
    }

    public String getNameById(int id){
        return id2Name.getOrDefault(id, "unknown");
    }

    public static int getItemType(int itemCfgId){
        if(item2ItemType.containsKey(itemCfgId)){
            return item2ItemType.get(itemCfgId);
        }else {
            return EItemType.NULL;
        }
    }
}
