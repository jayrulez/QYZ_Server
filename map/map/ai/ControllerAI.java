package map.ai;

import map.agent.Fighter;
import map.agent.Listener;
import map.controller.Controller;

/**
 * Created by HuangQiang on 2016/5/19.
 */
public class ControllerAI extends AI {
    private final cfg.ai.ControllerAI acfg;
    private boolean init = false;
    public ControllerAI(Fighter self, AIFactory.Builder b) {
        super(self);
        this.acfg = (cfg.ai.ControllerAI)b.acfg;

        switchToAction(this::init);
    }

    private boolean init(long now) {
        self.addListener(Listener.DEATH, (go, evtid, param) -> {
            acfg.controllers.forEach(c -> self.getMap().closeController(c, Controller.CloseType.Kill));
        });
        acfg.controllers.forEach(self.getMap()::openController);
        switchToEnd();
        return false;
    }
}
