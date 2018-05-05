package map.skill;

import map.agent.Fighter;
import map.buff.factor.Context;

/**
 * Created by HuangQiang on 2016/5/9.
 */
public class DamageParam {
    public final Fighter attacker;
    public final Fighter defencer;
    public final Context ctx;
    public final int damage;

    public DamageParam(Fighter attacker, Fighter defencer, Context ctx, int damage) {
        this.attacker = attacker;
        this.defencer = defencer;
        this.ctx = ctx;
        this.damage = damage;
    }
}
