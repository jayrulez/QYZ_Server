package map.ai;

import map.agent.Fighter;
import map.agent.SkillMgr;
import map.skill.Skill;
import pathfinding.Vector3;

/**
 * Created by HuangQiang on 2016/6/18.
 */
public class SkillAI extends AI {

    private final SkillMgr skillMgr;
    public SkillAI(Fighter self, AIFactory.Builder b) {
        super(self);
        this.skillMgr = self.getSkillMgr();

        switchToAction(this::performSkill);
    }

    protected boolean performSkill(long now) {
        if(!skillMgr.isCurSkillFinished()) {
            return false;
        }

        final Vector3 direction = self.getOrient();
        final int skillid = skillMgr.getAvaliableSkill();

        if(skillid > 0) {
            final Skill.Builder param = new Skill.Builder();
            param.skillid = skillid;
            param.defencer = null;
            param.direction = direction;
            self.performSkillAndNotify(param);
        }
        return false;
    }
}
