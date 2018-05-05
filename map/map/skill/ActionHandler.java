package map.skill;

public abstract class ActionHandler<T extends cfg.skill.Action> {
	protected final long startTime;
	protected final Skill skill;
	protected final T action;
	
	public ActionHandler(Skill skill, long startTime, T action) {
		this.skill = skill;
		this.startTime = startTime;
		this.action = action;
	}

	public abstract boolean process(long now);
}
