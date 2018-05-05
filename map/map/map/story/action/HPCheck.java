package map.map.story.action;

import map.agent.AttrMgr;
import map.agent.Listener;
import map.agent.Monster;
import map.map.story.AbstractStory;

/**
 * Created by huangqiang on 2016/1/18.
 */
public class HPCheck extends CfgAction<cfg.ectype.HPCheck> {
    private AbstractStory map;
    private boolean finish;
    private Listener listener;
    public HPCheck(cfg.ectype.HPCheck mcfg) {
        super(mcfg);
        listener = (go, evtid, param) -> {
            if(finish) return;
            if(go.isMonster()) {
                final Monster monster = (Monster)go;
                if(monster.getMonsterId() != mcfg.id) return;
                final AttrMgr pm = monster.getAttrMgr();
                finish = compare((double)pm.getHPValue() / pm.getHPFullValue(), mcfg.op, mcfg.percent);
            }
        };
    }

    @Override
    public void enter() {
        map = (AbstractStory)vm.host;
        finish = false;
        map.addListener(Listener.HP_CHANGE, listener);
        map.beginAction(mcfg.actionid, this, false, false);
    }

    @Override
    public boolean run() {
        return !finish;
    }

    @Override
    public void exit() {
        map.removeListener(Listener.HP_CHANGE, listener);
        map.endAction(mcfg.actionid);
    }

    @Override
    public void onDestroy() {
        if(entered) {
            exit();
        }
    }
}
