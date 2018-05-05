package map.map.story.action;

import map.map.story.AbstractStory;

/**
 * Created by HuangQiang on 2016/8/19.
 */
public class ShowGlobalTips extends CfgAction<cfg.ectype.ShowGlobalTips> {
    public ShowGlobalTips(cfg.ectype.ShowGlobalTips mcfg) {
        super(mcfg);
    }

    @Override
    public boolean run() {
        final AbstractStory map = (AbstractStory)vm.host;
        map.showCurLayoutGlobalTips();
        map.beginNotEndClientAction(mcfg.actionid);
        return false;
    }
}
