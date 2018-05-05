package map.agent;

import map.map.GameMap;
import map.skill.Skill;

/**
 * Created by HuangQiang on 2016/5/31.
 */
public abstract class SpawnObject<T extends cfg.skill.SpawnObject> {
    protected final long id;
    protected final Skill skill;
    protected final GameMap map;
    protected final Fighter caster;
    protected final T ocfg;
    protected long aliveTime;

    public SpawnObject(Skill skill, T ocfg) {
        this.id = Agent.allocAid();
        this.skill = skill;
        this.map = skill.getAttacker().getMap();
        this.caster = skill.getAttacker();
        this.ocfg = ocfg;
        this.aliveTime = 0;
    }

    public final long getId() {
        return id;
    }

    public final boolean update(long now) {
        this.aliveTime += skill.getAttacker().getDeltaTime();
        if(!onUpdate(now))
            return false;
        return this.aliveTime < ocfg.life * 1000;
    }

    public abstract boolean onUpdate(long now);
}
