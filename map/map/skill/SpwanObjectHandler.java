package map.skill;

import cfg.skill.Bomb;
import cfg.skill.FlyWeapon;
import cfg.skill.SkillAction;
import cfg.skill.SpawnObject;
import map.map.GameMap;

/**
 * Created by HuangQiang on 2016/5/31.
 */
public class SpwanObjectHandler extends ActionHandler<cfg.skill.SpawnObject> {
    private final int hitPointOffset;
    public SpwanObjectHandler(Skill skill, long startTime, SpawnObject action, int hitPointOffset) {
        super(skill, startTime, action);
        this.hitPointOffset = hitPointOffset;
    }

    @Override
    public boolean process(long now) {
        final GameMap map = skill.getAttacker().getMap();
        switch (action.getTypeId()) {
            case FlyWeapon.TYPEID: {
                map.addSpawnObject(new map.agent.FlyWeapon(skill, (FlyWeapon)action));
                break;
            }
            case Bomb.TYPEID: {
                map.addSpawnObject(new map.agent.Bomb(skill, (Bomb)action, hitPointOffset));
                break;
            }
            default:
                throw new RuntimeException("Unknown spawnobject:" + action);
        }


        return false;
    }
}
