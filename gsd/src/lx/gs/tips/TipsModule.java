package lx.gs.tips;

import cfg.CfgMgr;
import cfg.equip.Equip;
import cfg.item.EItemColor;
import cfg.pet.PetBasicStatus;
import cfg.talisman.TalismanBasic;
import cfg.tips.LocationType;
import cfg.tips.TipsCode;
import gs.Module;
import lx.gs.equip.FEquip;
import lx.gs.event.*;
import lx.gs.pet.FPet;
import lx.gs.talisman.FTalisman;
import xtable.Roleinfos;

/**
 * @author Jin Shuai
 */
public enum TipsModule implements Module {
    INSTANCE;

    @Override
    public void start() {
        registerListener();
    }

    private void registerListener() {
        EventModule.INSTANCE.registerListener(EventType.TALISMAN_AWAKE_LEVEL_UP, event -> {
            TalismanAwakeLevelUpEvent talismanEvent = event.cast();
            TalismanBasic model = FTalisman.getModelById(talismanEvent.modelId);
            if(model.quality == EItemColor.RED && talismanEvent.newLevel >= CfgMgr.broadcastcfg.mintalismanawake){
                String rolename = Roleinfos.selectName(talismanEvent.roleId);
                FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.AWAKE_RED_BROADCAST, rolename, model.name, talismanEvent.newLevel + "");
            }
            if(model.quality == EItemColor.ORANGE && talismanEvent.newLevel >= CfgMgr.broadcastcfg.minorangetalismanawake){
                String rolename = Roleinfos.selectName(talismanEvent.roleId);
                FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.AWAKE_ORANGE_BROADCAST, rolename, model.name, talismanEvent.newLevel + "");
            }
        });

        EventModule.INSTANCE.registerListener(EventType.PET_AWAKE_UP, event -> {
            PetAwakeUpEvent petEvent = event.cast();
            PetBasicStatus model = FPet.getModelById(petEvent.modelId);
            if(model.basiccolor == EItemColor.RED && petEvent.newLevel >= CfgMgr.broadcastcfg.minpetawake){
                String rolename = Roleinfos.selectName(petEvent.roleId);
                FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.AWAKE_RED_BROADCAST, rolename, model.name, petEvent.newLevel + "");
            }
            if(model.basiccolor == EItemColor.ORANGE && petEvent.newLevel >= CfgMgr.broadcastcfg.minorangepetawake){
                String rolename = Roleinfos.selectName(petEvent.roleId);
                FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.AWAKE_ORANGE_BROADCAST, rolename, model.name, petEvent.newLevel + "");
            }
        });

        EventModule.INSTANCE.registerListener(EventType.EQUIP_ANNEAL, event -> {
            EquipAnnealEvent equipAnnealEvent = event.cast();
            Equip model = FEquip.getEquipModel(equipAnnealEvent.modelId);
            if(equipAnnealEvent.newLevel >= CfgMgr.broadcastcfg.minanneal){
                String rolename = Roleinfos.selectName(event.roleId);
                if(model.quality == EItemColor.RED){
                    FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.ANNEAL_UP_RED_BROADCAST, rolename, model.name, equipAnnealEvent.newLevel + "");
                }
                if(model.quality == EItemColor.ORANGE){
                    FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.ANNEAL_UP_ORANGE_BROADCAST, rolename, model.name, equipAnnealEvent.newLevel + "");
                }
            }
        });

        EventModule.INSTANCE.registerListener(EventType.TALISMAN_STAR_LEVEL_UP, event -> {
            TalismanStarLevelUpEvent levelUpEvent = event.cast();
            TalismanBasic model = FTalisman.getModelById(levelUpEvent.modelId);
            if(levelUpEvent.currStarLevel >= CfgMgr.broadcastcfg.mintalismanstar){
                String rolename = Roleinfos.selectName(event.roleId);
                if(model.quality == EItemColor.RED){
                    FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.STAR_UP_RED_BROADCAST, rolename, model.name, levelUpEvent.currStarLevel + "");
                }
                if(model.quality == EItemColor.ORANGE){
                    FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.STAR_UP_ORANGE_BROADCAST, rolename, model.name, levelUpEvent.currStarLevel + "");
                }
            }
        });

        EventModule.INSTANCE.registerListener(EventType.LEVEL_UP, event -> {
            LevelUpEvent levelUpEvent = event.cast();
            if(CfgMgr.broadcastcfg.rolelevelup.contains(levelUpEvent.currLevel)){
                String rolename = Roleinfos.selectName(event.roleId);
                FTips.broadcastWhileCommit(LocationType.CENTER_SCROLL, TipsCode.LEVEL_UP_BROADCAST, rolename, levelUpEvent.currLevel + "");
            }
        });
    }
}
