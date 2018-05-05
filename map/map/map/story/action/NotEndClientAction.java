package map.map.story.action;

import cfg.ectype.Action;
import map.map.story.AbstractStory;

/**
 * Created by huangqiang on 2016/2/25.
 */
public class NotEndClientAction extends CfgAction<cfg.ectype.Action> {
    public NotEndClientAction(Action mcfg) {
        super(mcfg);
    }

    @Override
    public boolean run() {
        final AbstractStory map = (AbstractStory)vm.host;
        map.beginNotEndClientAction(mcfg.actionid);
        return false;
    }
}
