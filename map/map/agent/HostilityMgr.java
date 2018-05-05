package map.agent;

import java.util.*;

public final class HostilityMgr {
	public static class Record {
		public Record(Fighter agent, long lastAttackTime, int totalValue) {
			this.agent = agent;
			this.lastAttackTime = lastAttackTime;
			this.totalValue = totalValue;
		}
		public final Fighter agent;
		public long lastAttackTime;
		public int totalValue;
	}

	public HostilityMgr(Fighter self) {
		this.self = self;
		this.nextCheckExpireTime = self.getNow() + CHECK_EXPIRE_TIME;
	}
	private final Fighter self;
	private final HashMap<Fighter, Record> records = new HashMap<>();
	private long nextCheckExpireTime;

	public void addValue(Fighter agent, int value) {
        final long now = self.getNow();
		final Record r = records.get(agent);
		if(r != null) {
			r.lastAttackTime = now;
			r.totalValue += value;
		} else {
			records.put(agent, new Record(agent, now, value));
		}
	}

	private static class Stat {
	    Player player;
        long totalDamage;
    }
	public Player getMaxDamageTeamPlayer() {
	    final Map<Long, Stat> stats = new HashMap<>();
        for(Record r : records.values()) {
            final Fighter owner = r.agent.getOwner();
            if(owner.isActive() && owner.isPlayer()) {
                Player player = (Player)owner;
                long teamid = player.getTeam().teamid;
                if(teamid == 0)
                    teamid = player.getRoleid();
                Stat s = stats.get(teamid);
                if(s != null) {
                    s.totalDamage += r.totalValue;
                } else {
                    s = new Stat();
                    stats.put(teamid, s);
                    s.player = player;
                    s.totalDamage = r.totalValue;
                }
            }
        }
        return stats.isEmpty() ? null : Collections.max(stats.values(), (a, b) -> Long.signum(a.totalDamage - b.totalDamage)).player;
    }

    public final boolean isEmpty() {
        return records.isEmpty();
    }

	public final Collection<Record> getRecords() {
		return records.values();
	}

	private static long CHECK_EXPIRE_TIME = 10 * 1000;
	private static long EXPIRE_TIME = 30 * 1000;
	public void update(long now) {
        if(self.isDead()) return;
		if(now > nextCheckExpireTime && !records.isEmpty()) {
			for(Iterator<Record> it = records.values().iterator() ; it.hasNext() ;) {
				final Record r = it.next();
				if(!r.agent.isActive() || r.agent.isDead() || now - EXPIRE_TIME > r.lastAttackTime) {
					it.remove();
				}
			}
			nextCheckExpireTime = now + CHECK_EXPIRE_TIME;
		}
	}

	public void onDead() {

	}

	public void onRevive() {
		records.clear();
		nextCheckExpireTime = self.getNow() + CHECK_EXPIRE_TIME;
	}
}
