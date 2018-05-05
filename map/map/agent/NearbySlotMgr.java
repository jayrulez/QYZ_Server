package map.agent;

import java.util.Arrays;
import java.util.HashMap;

public final class NearbySlotMgr {
	private final Fighter self;

	// 根据站位距离分为几档,每档都有一些可用站位,
	// 根据攻击者的攻击距离,给它分配一个站位
	public final static int LEVEL_NUM = 3;
	public final static double MID_DISTANCE = 3;
	public final static int[] levelSlotNum = new int[] {12, 24, 48};
	private final LevelSlotState[] levelSlotStates = new LevelSlotState[LEVEL_NUM];
	private final HashMap<Fighter, Slot> fighterSlots = new HashMap<>();
	private Slot curSlot;
	
	
	public final static class LevelSlotState {
		public int totalAllocNum;
		public int allocNum[];
		public LevelSlotState(int slotNum) {
			totalAllocNum = 0;
			allocNum = new int[slotNum];
		}
	}
	
	public final static class Slot {
		public final int id;
		public final int level;
		public final double distance;
		public final double radian;
		final Fighter target;
		public Slot(int id, int level, double distance, double radian, Fighter target) {
			this.id = id;
			this.level = level;
			this.distance = distance;
			this.radian = radian;
			this.target = target;
		}
	}
	
	public NearbySlotMgr(Fighter fighter) {
		this.self = fighter;
	}
	
	public int getLevel(double distance) {
		return Math.min((int)(distance/MID_DISTANCE), LEVEL_NUM-1);
	}
	
	public Slot alloc(Fighter attacker, double distance) {
		final Slot oldSlot = fighterSlots.get(attacker);
		if(oldSlot != null) {
			return oldSlot;
		}
		pathfinding.Vector3 direction = attacker.getPosition().sub(self.getPosition());
		int level = getLevel(distance);
		LevelSlotState slotState = levelSlotStates[level];
		final int slotNum = levelSlotNum[level];
		if(slotState == null) {
			slotState = new LevelSlotState(slotNum);
			levelSlotStates[level] = slotState;
		}
		++slotState.totalAllocNum;

		final double radianDirection = direction.radianXZ();
		final int preferSlotId = Math.min((int)(radianDirection * slotNum / Math.PI / 2), slotNum - 1);
		int chooseSlotId = preferSlotId;
		final Slot newSlot;
		if(slotState.allocNum[chooseSlotId] == 0) {
			newSlot = new Slot(chooseSlotId, level, distance, radianDirection, self);
		} else {
			for(int i = 0 ; i < slotNum - 1 ; i++) {	
				int id = (i % 2 == 0 ? (slotNum + preferSlotId + i/2 + 1) : (slotNum + preferSlotId - i/2 - 1)) % slotNum;
				if(slotState.allocNum[id] == 0) {
					chooseSlotId = id;
					break;
				}
			}
			newSlot = new Slot(chooseSlotId, level, distance, chooseSlotId * Math.PI * 2 / slotNum, self);
		}
			
		++slotState.allocNum[chooseSlotId];
		fighterSlots.put(attacker, newSlot);
//		Trace.info("SlotMgr.alloc target:{} slotid:{} attacker:{} level:{} distance:{} radian:{} ",
//			self.getAid(), chooseSlotId, attacker.getAid(), level, distance, newSlot.radian);
		return newSlot;
	}
	
	public void free(Fighter attacker) {
		Slot slot = fighterSlots.remove(attacker);
		if(slot != null) {
//			Trace.info("SlotMgr.free target:{} slotid:{} attacker:{} level:{} distance:{} radian:{} ",
//					self.getAid(), slot.id, attacker.getAid(), slot.level, slot.distance, slot.radian);
			final LevelSlotState slotState = levelSlotStates[slot.level];
			--slotState.allocNum[slot.id];
			--slotState.totalAllocNum;
			if(slotState.allocNum[slot.id] < 0 || slotState.totalAllocNum < 0)
				throw new RuntimeException("NearSlotMgr. fatal error!");
		}
	}
	
	public void setSlot(Slot slot) {
		this.curSlot = slot;
	}
	
	public Slot getSlot() {
		return this.curSlot;
	}
	
	public void clearSlot() {
		if(this.curSlot != null) {
			this.curSlot.target.getSlotMgr().free(self);
			this.curSlot = null;
		}
	}
	
	public void onDead() {
        clearSlot();
	}
	
	public void onRevive() {
		
	}

	public void onLeave() {
		clearSlot();
        Arrays.fill(this.levelSlotStates, null);
        fighterSlots.keySet().forEach(f -> f.getSlotMgr().setSlot(null));
        fighterSlots.clear();
	}
}
