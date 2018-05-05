package pathfinding;

public final class Edge {
	public final Vertex v1;
	public final Vertex v2;
	public Edge(Vertex v1, Vertex v2) {
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public int hashCode() {
		return v1.id ^ v2.id;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Edge) {
			Edge e = (Edge)o;
			return (v1 == e.v1 && v2 == e.v2) || (v1 == e.v2 && v2 == e.v1);
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return  String.format("Edge{%s(%s), %s(%s)} ", v1.id, v1.postion, v2.id, v2.postion);
	}
}
