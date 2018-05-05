package map.map;

import map.agent.Agent;
import pathfinding.Rect;
import pathfinding.Vector3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public final class AgentMap {
	private final ArrayList<CellInfo>[][] containerCells;
	private final HashMap<Agent, CellInfo> objectPositionMap;
	private final Rect box;
	private final int xSize;
	private final int zSize;
	private final double xCoef;
	private final double zCoef;
	
	private static class CellInfo {
		public final Agent agent;
		public int indexX;
		public int indexZ;
		public CellInfo(Agent o, int gx, int gz) {
			agent = o;
			indexX = gx;
			indexZ = gz;
		}
	}

	@SuppressWarnings("unchecked")
	public AgentMap(Rect box, int xSize, int zSize) {
		this.box = box;
		this.xSize = xSize;
		this.zSize = zSize;
		xCoef = (box.maxx - box.minx) / xSize;
		zCoef = (box.maxz - box.minz) / zSize;
		containerCells = (ArrayList<CellInfo>[][])new ArrayList[this.xSize][this.zSize];
		for(int i = 0 ; i < this.xSize ; i++)
			for(int j = 0 ; j < this.zSize ; j++)
				containerCells[i][j] = null;
		objectPositionMap = new HashMap<>();
	}
	
	public int position2IndexX(double x) {
		final int i = (int)((x - box.minx) / xCoef);
		if(i < 0)
			return 0;
		else if(i >= xSize)
			return xSize - 1;
		else
			return i;
	}
	
	public int position2IndexZ(double z) {
		final int i = (int)((z - box.minz) / zCoef);
		if(i < 0)
			return 0;
		else if(i >= zSize)
			return zSize - 1;
		else
			return i;
	}

	private ArrayList<CellInfo> getCells(int x, int z) {
		ArrayList<CellInfo> cells = containerCells[x][z];
		if(cells == null) {
			cells = new ArrayList<>();
			containerCells[x][z] = cells;
		}
		return cells;
	}

	public void add(Agent obj) {
		if(objectPositionMap.containsKey(obj)) {
			throw new RuntimeException(String.format("agentid:%s agent:%s has in agentmap", obj.getAid(), obj));
		}
		final Vector3 position = obj.getPosition();
		final int newX = position2IndexX(position.x);
		final int newZ = position2IndexZ(position.z);

		final CellInfo info = new CellInfo(obj, newX, newZ);
		getCells(newX, newZ).add(info);
		objectPositionMap.put(obj, info);
	}
	
	public void update(Agent obj) {
		final CellInfo info = objectPositionMap.get(obj);
		final Vector3 position = obj.getPosition();
		final int newX = position2IndexX(position.x);
		final int newZ = position2IndexZ(position.z);
		final int oldx = info.indexX;
		final int oldz = info.indexZ;
		if (oldx != newX || oldz != newZ) {
			getCells(oldx, oldz).remove(info);
			getCells(newX, newZ).add(info);
			info.indexX = newX;
			info.indexZ = newZ;
		}
	}
	
	public void remove(Agent agent) {
		final CellInfo info = objectPositionMap.remove(agent);
		containerCells[info.indexX][info.indexZ].remove(info);
	}
	
//	public ArrayList<Agent> getNearbyAgents(Agent obj, int type, double radius) {
//		final Vector3 position = obj.getPosition();
//		return getAgentsInCircle(type, position.x, position.z, radius);
//	}
//
//	public ArrayList<Agent> getAgentsInCircle(int type, double x, double z, double radius) {
//		final ArrayList<Agent> agents = new ArrayList<Agent>();
//		foreachAgentsInCircle(type, x, z, radius, (agent) -> agents.add(agent));
//		return agents;
//	}
	
	public <T extends Agent> void foreachNearbyAgents(Agent a, int type, double distance, Consumer<T> func) {
		final Vector3 pos = a.getPosition();
		foreachAgentsInCircle(type, pos.x, pos.z, distance, func);
	}

    public <T extends Agent> void foreachNearbyAgents(Vector3 pos, int type, double distance, Consumer<T> func) {
        foreachAgentsInCircle(type, pos.x, pos.z, distance, func);
    }

	private  <T extends Agent> void foreachAgentsInCircle(int type, double x, double z, double distance, Consumer<T> func) {
		int minx = position2IndexX(x - distance);
		int maxx = position2IndexX(x + distance);
		int miny = position2IndexZ(z - distance);
		int maxy = position2IndexZ(z + distance);
		foreachAgentsInCircle(type, minx, maxx, miny, maxy, x, z, distance, func);
	}

	@SuppressWarnings("unchecked")
	private <T extends Agent> void foreachAgentsInCircle(int type, int minx, int maxx, int minz, int maxz, double centerX, double centerZ, double distance, Consumer<T> func) {
		final double squareDistance = distance * distance;
		for(int i = minx ; i <= maxx ; i++) {
			for(int j = minz ; j <= maxz ; j++) {
				if(containerCells[i][j] == null) continue;
                final ArrayList<CellInfo> agents = containerCells[i][j];
				for(int k = 0, n = agents.size() ; k < n ; k++) {
				    final Agent agent = agents.get(k).agent;
					if(!agent.isActive() || (agent.getType() & type) == 0) continue;
					final Vector3 pos = agent.getPosition();
					final double deltaX = pos.x - centerX;
					final double deltaZ = pos.z - centerZ;
					if(deltaX * deltaX + deltaZ * deltaZ <= squareDistance) {
						func.accept((T)agent);
					}
				}
			}
		}
	}
	
}
