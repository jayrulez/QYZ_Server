package map.agent;

import cfg.CfgMgr;
import cfg.fight.AbilityType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public final class StateMgr {
	private final Fighter self;
	public StateMgr(Fighter fighter) {
		this.self = fighter;
		this.minExpireTime = Long.MAX_VALUE;
		this.abilities = ~0;
	}

    private long calcExpireTime(long duration) {
        return duration == Long.MAX_VALUE ? Long.MAX_VALUE : self.getNow() + duration;
    }
	
	public final static class State {
		private final int id;
		private final cfg.fight.State stateCfg;
        public final int abilities;
		private long expireTime;
		public State(int id, cfg.fight.State scfg, long expireTime) {
			this.id = id;
			this.stateCfg = scfg;
            int flag = 0;
            for(int i = 0 ; i < scfg.abilities.size() ; i++)
                if(scfg.abilities.get(i))
                    flag |= (1 << i);
            this.abilities = flag;
            this.expireTime = expireTime;
		}
	}
	
	private final HashMap<Integer, State> states = new LinkedHashMap<>();
	private int abilities;
	private long minExpireTime;

    public static boolean test(int x, int offset) {
        return (x & (1 << offset)) != 0;
    }

    public State setStateForever(int id, int stateid) {
        setState(id, stateid, Long.MAX_VALUE);
        return states.get(id);
    }
	
	public void setState(int id, int stateid, long duration) {
        if(duration <= 0) return;
        State s = states.get(id);
		if(s == null) {
			s = new State(id, CfgMgr.state.get(stateid), calcExpireTime(duration));
			states.put(id, s);
			int oldAbilitys = this.abilities;
			this.abilities &= s.abilities;
			if(test(oldAbilitys, AbilityType.MOVE) && !test(s.abilities, AbilityType.MOVE)) {
				self.getMoveMgr().stopAtCurPosition();
			}
		} else {
            assert(s.stateCfg.id == stateid);
            s.expireTime = calcExpireTime(duration);
        }
        if(s.expireTime < minExpireTime)
            minExpireTime = s.expireTime;
	}
	
	public void clearState(int id) {
		if(states.remove(id) != null) {
			buildAbility();
		}
	}
	
	private void buildAbility() {
		minExpireTime = Long.MAX_VALUE;
		this.abilities = ~0;
		for(State s : states.values()) {
			if(s.expireTime < minExpireTime)
				minExpireTime = s.expireTime;
			this.abilities &= s.abilities;
		}
		//System.out.printf("agent:%d can move:%s\n ability:%s now:%d\n", self.getAid(), abilities[cfg.fight.AbilityType.MOVE], abilities, System.currentTimeMillis());
	}
	
	public void update(long now) {
        if(self.isDead()) return;
		if(now >= minExpireTime) {
            states.values().removeIf(s -> s.expireTime <= now);
			buildAbility();
		}
	}
	
	public boolean isEnable(int abilityid) {
		if(test(abilities, abilityid))
			return true;
		// 由于 update 每100ms才调用一次,有可能某个状态已经过期,但未清除
		// 因此尝试update后再检查ability
		final long now = self.getNow();
		if(now < minExpireTime)
			return false;
		update(now);
		return test(abilities, abilityid);
	}
}
