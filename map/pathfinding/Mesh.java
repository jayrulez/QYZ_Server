package pathfinding;

import xdb.Trace;

import java.util.*;
import java.util.stream.Collectors;

public final class Mesh {
	public final List<Convex> convexs;
	public final double edgeNodeSplitLength;
	public final String name;
	public Mesh(String name, double edgeNodeSplitLength, Collection<Convex> cs) {
		this.name=name;
		this.edgeNodeSplitLength=edgeNodeSplitLength;
		this.convexs = new ArrayList<>(cs);
	}

    public final static double COMBIND_XZ_DISTANCE = 0.3;
    public final static double COMBIND_Y_DISTANCE = 0.5;
    public Mesh(cfg.navmesh.NavMesh ncfg) {
        this.name = ncfg.name;
        this.edgeNodeSplitLength = 10;
        {
            final cfg.navmesh.Mesh mcfg = ncfg.mesh;
            final int n = mcfg.vertexs.size();
            final Vertex[] vertexs = new Vertex[n];
            for (int i = 0; i < n; i++) {
                final cfg.navmesh.Vector3 cpos = mcfg.vertexs.get(i);
                final Vector3 ppos = new Vector3(cpos.x, cpos.y, cpos.z);
                int j;
                for (j = 0; j < i; j++) {
                    if (ppos.getSubXZMagnitude(vertexs[j].postion) < COMBIND_XZ_DISTANCE && Math.abs(ppos.y - vertexs[j].postion.y) < COMBIND_Y_DISTANCE) {
                        vertexs[i] = vertexs[j];
                        Trace.info("messh:{} combine vertex1{id:{} pos:{}} vertex2:{id:{} pos:{}}", ncfg.name, i, ppos, j, vertexs[j].postion);
                        break;
                    }
                }
                if (j == i) {
                    vertexs[i] = new Vertex(i, ppos);
                }
            }
            this.convexs = mcfg.convexs.stream().map(c -> {
                final Convex convex = new Convex();
                c.vertexids.stream().map(id -> vertexs[id]).forEach(convex.vertexs::add);
                return convex;
            }).filter(c -> {
                if(!c.check()) {
                    if(c.fix()) {
                        Trace.info("== fix convex:" + c);
                    } else if(new HashSet<>(c.vertexs).size() >= 3) {
                        System.out.println("== error convex:" + c);
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
        }
    }

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Mesh\n{");
		for(Convex c : convexs) {
			b.append('\t');
			b.append(c.toString());
			b.append('\n');
		}
		b.append("}");
		return b.toString();
	}
	
	public void check() {
		boolean error = false;
		for(Convex c : convexs) {
			if(!c.check()) {
				System.out.println("== invalid convex:" + c);
				error = true;
			}
		}
		if(error)
			throw new RuntimeException("invalid mesh");
	}


	@SuppressWarnings("unused")
	private static void checkConnect(LinkedList<Convex> convexs) {
		Set<Integer> nodeids = new HashSet<>();
		while (!convexs.isEmpty()) {
			nodeids.addAll(convexs.get(0).vertexs.stream().map(v -> v.id).collect(Collectors.toList()));
			convexs.remove(0);
			while (!convexs.isEmpty()) {
				boolean change = false;
				for (ListIterator<Convex> it = convexs.listIterator(); it.hasNext();) {
					final Convex c = it.next();
					if (c.vertexs.stream().anyMatch(v -> nodeids.contains(v.id))) {
						c.vertexs.stream().map(v -> v.id).forEach(nodeids::add);
						change = true;
						it.remove();
					}
				}
				if (!change) {
					break;
				}
			}
			System.out.println("==== not connect!");
			System.out.println(nodeids);
			nodeids.clear();
		}
	}
	
	public static void main(String[] args) throws Exception{
//		onLoad(Paths.get("C:\\svnwork\\game\\zxjres\\meshconfig"));
		//-53.3511 101.1609
		
		//System.out.println(meshCache);
//		pathfinding.NavGraph navGraph=new pathfinding.NavGraph(1,Mesh.getMesh("11.xml"));
//
//		System.out.println(navGraph.isValidPosition(new Vector3(0.2849f,0,-27.26997)));
//		System.exit(0);
		//long start=System.currentTimeMillis();
		//final int N = 1;
		//19, 0,-16
		//-59, 0,88
//		pathfinding.Vector3 startPos=new pathfinding.Vector3(-13.35,2.87, -11.83);
//		pathfinding.Vector3 endPos=new pathfinding.Vector3(22.77,0,-23.35);
//		pathfinding.Vector3 startPos=new pathfinding.Vector3(-20.057, -0.1311, 9.8554);
//		pathfinding.Vector3 endPos=new pathfinding.Vector3(-29.52, 0, 28.62);
		//System.out.println(navGraph.toValidPosition(endPos));
//		pathfinding.NavGraph.Path path=navGraph.findPath(startPos, endPos);
//		System.out.println(path);
//		for(int i=0;i<N;i++){
//			pathfinding.NavGraph.Path path=navGraph.findPath(startPos, endPos);
//			//if(i%10000==0)
//			System.out.println("i:"+i+",path.size:"+path.vertexs);
//		}
//		System.out.println(N * 1000.0/(System.currentTimeMillis()-start));
	}
}
