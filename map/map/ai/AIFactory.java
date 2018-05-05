package map.ai;


import cfg.ai.*;
import cfg.ai.ControllerAI;
import cfg.ai.RunOnceAI;
import map.agent.Fighter;

import java.util.List;

public final class AIFactory {
		public final static class Builder {
			public cfg.ai.AI acfg;

			public boolean proactive;
			public float guardRadius;
			public float traceRadius;
			public int hostilitytype;
			public int walkbackhealhprate;
			public float walkbackhealhppercentrate;

			public List<pathfinding.Vector3> patrolPath;
			public int patrolType;
		}


	public static AI create(Fighter self, Builder b) {
		switch (b.acfg.getTypeId()) {
			case cfg.ai.DefaultAI.TYPEID: {
//				return new map.ai.DefaulAI(self, b);
                return new map.ai.MonsterAI(self, b);
			}
			case ArenaAI.TYPEID: {
				return new ArenaPlayerAI(self, b);
			}

            case ControllerAI.TYPEID: {
                return new map.ai.ControllerAI(self, b);
            }
            case cfg.ai.StaticAI.TYPEID: {
                return new SkillAI(self, b);
            }
            case RunOnceAI.TYPEID: {
                return new map.ai.RunOnceAI(self, b);
            }
			default: {
				throw new RuntimeException("unknown ai:" + b.acfg);
			}
		}
	}
}
