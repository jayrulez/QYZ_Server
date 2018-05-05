package map.agent;

public interface Listener {
	int

		SKILL_ATTACKER_END = 111,

		BE_DAMAGE = 112,
        DO_DAMAGE = 113,

		HP_CHANGE = 303, // 血量变化


		DEATH = 400,
		LEAVE = 401,
		REVIVE = 402
		;
		
	
	
	void onTrigger(Agent go, int evtid, Object param);
}
