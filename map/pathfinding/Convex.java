package pathfinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public final class Convex {
	// 顶点必须按顺时针方向排布
	public final ArrayList<Vertex> vertexs = new ArrayList<Vertex>();
	
	/*
	 * 检查是否为凸多边形
	 */
	public boolean check() {
        if(new HashSet<>(vertexs).size() < 3)
            return false;
        int size = vertexs.size();
		for(int i = 0 ; i < size - 2 ; i++) {
			if(MathUtil.leftOf(vertexs.get(i).postion, vertexs.get(i + 1).postion, vertexs.get(i + 2).postion))
				return false;
		}
		return !MathUtil.leftOf(vertexs.get(size - 2).postion, vertexs.get(size - 1).postion, vertexs.get(0).postion)
			&& !MathUtil.leftOf(vertexs.get(size - 1).postion, vertexs.get(0).postion, vertexs.get(1).postion);
	}

    // 尝试修复 三边形
    // 如果顶点<3直接放弃
    // 如果顶点有重合,直接放弃
    // 如果顺序不对，交换.
    public boolean fix() {
        if(vertexs.size() != 3)
            return false;
        if(new HashSet<>(vertexs).size() != 3)
            return false;
//        if(MathUtil.lineOf(vertexs.get(0).postion, vertexs.get(1).postion, vertexs.get(2).postion))
//            return true;
        Collections.swap(vertexs, 0, 1);
        return true;
    }
	
	/**
	 * 
	 * @param p 顶点
	 * @return 顶点p是否在凸多边形内
	 */
	public boolean contains(Vector3 p) {
		int size = vertexs.size();
		for(int i = 0 ; i < size - 1 ; i++) {
			if(MathUtil.leftOf(vertexs.get(i).postion, vertexs.get(i+1).postion, p)) return false;
		}
		return !MathUtil.leftOf(vertexs.get(size - 1).postion, vertexs.get(0).postion, p);
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Convex{");
		b.append("[" + vertexs.size() + "] ");
		for(Vertex v : vertexs) {
			b.append(v.id);
			b.append(v.postion.toString());
			b.append(',');
		}
		b.append("}");
		return b.toString();
	}

}
