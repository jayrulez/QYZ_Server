package map.buff.effect;

import cfg.CfgMgr;
import cfg.fight.AbilityType;
import map.agent.Fighter;
import map.agent.StateMgr;

public final class SetAbnormalState extends CfgEffect<cfg.buff.SetAbnormalState> {

	public SetAbnormalState(cfg.buff.SetAbnormalState ecfg, Fighter caster, int endType, long endTime) {
		super(ecfg, 1, 1, caster, endType, endTime);
	}
	
	@Override
	protected void onInstall() {
		final StateMgr.State state = target.getStateMgr().setStateForever(getId(), ecfg.statetype);
        // 如果施加的负面buff中有不能移动的effect,
        // 打断当前技能
        if(caster != target && !StateMgr.test(state.abilities, AbilityType.MOVE))
            target.getSkillMgr().interruptCurSkill(false);
    }

    @Override
    protected void onUninstall() {
        target.getStateMgr().clearState(getId());
    }

}
