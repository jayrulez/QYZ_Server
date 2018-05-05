package lx.gs.pet;

import cfg.Const;
import gs.LevelUpListener;
import gs.RoleCreateListener;
import lx.gs.event.EventModule;
import lx.gs.event.PetCombatPowerChangeEvent;
import lx.gs.pet.msg.SSyncPetLevel;
import lx.gs.role.FRoleAttr;
import xbean.Pod;
import xbean.RolePet;
import xdb.Procedure;
import xdb.Transaction;
import xdb.logs.Listener;
import xdb.logs.Note;
import xtable.Roleattrs;
import xtable.Rolepet;

/**
 * Created by huangqiang on 2016/3/8.
 */
public enum PetModule implements RoleCreateListener,gs.Module, gs.RoleLoginListener, LevelUpListener {
    INSTANCE ;

    @Override
    public void onRoleCreateInProcedure(long roleid) {
        RolePet rolePet = Pod.newRolePet();
        rolePet.setActivepetmodelid(Const.NULL);
        Rolepet.add(roleid, rolePet);
    }

    @Override
    public void onRoleDeleteInProcedure(long roleid) {}

    @Override
    public void start() {
        registerLiteners();
    }

    private void registerLiteners() {
        Rolepet.getTable().addListener(new Listener() {
            @Override
            public void onChanged(Object key) {}

            @Override
            public void onRemoved(Object key) {}

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                new Procedure(){
                    @Override
                    protected boolean process() throws Exception {
                        FPet.calcTotalCombat((Long) key);
                        return true;
                    }
                }.call();
            }
        }, "value", "fightpets");

        Rolepet.getTable().addListener(new Listener() {
            @Override
            public void onChanged(Object key) {}

            @Override
            public void onRemoved(Object key) {}

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                new Procedure(){
                    @Override
                    protected boolean process() throws Exception {
                        long roleId = (long) key;
                        int currCombatPower = Rolepet.selectTotalcombatpower(roleId);
                        FRoleAttr.updatePetCombatPower(Roleattrs.get(roleId), currCombatPower);
                        EventModule.INSTANCE.broadcastEvent(new PetCombatPowerChangeEvent(roleId, currCombatPower));
                        return true;
                    }
                }.call();
            }
        }, "value", "totalcombatpower");

    }

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        FPet.onRoleLogin(roleid);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {
    }


    @Override
    public void onLevelUp(long roleid, int oldLevel, int newLevel) {
        FPet.get(roleid).getPetmap().values().forEach(pet -> {
            int oldPetLevel = pet.getLevel();
            FPet.addExp(roleid, pet, 0, newLevel);
            int newPetLevel = pet.getLevel();
            if(newPetLevel != oldPetLevel){
                Transaction.tsendWhileCommit(roleid, new SSyncPetLevel(pet.getModelid(), pet.getLevel(), pet.getExp()));
            }
        });
    }
}
