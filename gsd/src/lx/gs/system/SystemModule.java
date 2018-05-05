package lx.gs.system;

import lx.gs.role.RoleModule;
import xdb.logs.Listener;
import xdb.logs.Note;

/**
 * Created by huangqiang on 2016/4/8.
 */
public enum SystemModule implements gs.Module, gs.GsdStartListener {
    INSTANCE;


    @Override
    public void afterGsdStart() {
        FSystem.initRobots();
        registerListeners();
    }

    private void registerListeners() {
        xtable.Gmsenseword.getTable().addListener(new Listener() {
            @Override
            public void onChanged(Object key) {	}

            @Override
            public void onRemoved(Object key) {	}

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                RoleModule.INSTANCE.updateSenseWords();
            }

        }, "value", "addwords");

        xtable.Gmsenseword.getTable().addListener(new Listener() {
            @Override
            public void onChanged(Object key) {	}

            @Override
            public void onRemoved(Object key) {	}

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                RoleModule.INSTANCE.updateSenseWords();
            }

        }, "value", "removewords");
    }

    @Override
    public void beforeGsdStop() {

    }

    @Override
    public void start() {

    }
}
