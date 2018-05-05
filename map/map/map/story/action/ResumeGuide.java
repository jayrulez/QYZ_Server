package map.map.story.action;

import map.map.story.AbstractStory;

/**
 * Created by huangqiang on 2015/12/28.
 */
public class ResumeGuide extends CfgAction<cfg.ectype.ResumeGuide> {

    public ResumeGuide(cfg.ectype.ResumeGuide mcfg) {
        super(mcfg);
    }

    @Override
    public boolean run() {
        final AbstractStory map = (AbstractStory)vm.host;
        map.endPromptAction();
        return false;
    }
}
