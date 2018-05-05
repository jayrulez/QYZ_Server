package map;

import cfg.map.WeightedPolygonRegion;
import common.Utils;
import map.map.MapMgr;
import pathfinding.Vector3;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static common.Utils.*;

/**
 * Created by huangqiang on 2016/4/19.
 */
public class MapUtils {

    private final static float NULL_FLOAT = 0f;
    public static map.msg.Vector3 nullmvector() {
        return new map.msg.Vector3(NULL_FLOAT, NULL_FLOAT, NULL_FLOAT);
    }

    public static pathfinding.Vector3 nullpvector() {
        return new pathfinding.Vector3(NULL_FLOAT, NULL_FLOAT, NULL_FLOAT);
    }

    public static boolean isNullVector3(pathfinding.Vector3 v) {
        return v.x == NULL_FLOAT && v.y == NULL_FLOAT && v.z == NULL_FLOAT;
    }

    public static long makeEctypeId(long uid, int ectypeType) {
        long id = uid/* (uid << BIT_SERVERID_CREATOR) + serverid*/;
        id = (id << BIT_SERVERID_HOLDER) + MapMgr.Ins.getLocalServerid();
        id = (id << BIT_MAP_TYPE) + ectypeType;
        return id;
    }

    public static Vector3 createOrient(double angle) {
        return Vector3.FORWARD.rotateXZAngle(-angle);
    }

    public final static class Location {
        public Vector3 position;
        public Vector3 orient;
    }

    public static boolean inside(Vector3 p, List<cfg.map.Vector3> polygon) {
        int count = 0;
        for(int i = 0, n = polygon.size() ; i < n ; i++) {
            final cfg.map.Vector3 a = polygon.get(i);
            final cfg.map.Vector3 b = polygon.get((i + 1) % n);
            if((a.x <= p.x && p.x <= b.x) || (b.x <= p.x && p.x <= a.x)) {
                final double r = (p.x - a.x) * (a.z - b.z) - (p.z - a.z) * (a.x - b.x);
                if (r == 0) {
                    // 在边上
                    if (a.x != p.x || p.x != b.x || (a.z <= p.z && p.z <= b.z) || (b.z <= p.z && p.z <= a.z)) {
                        return true;
                    }
                } else if (r / (a.x - b.x) > 0) {
                    count++;
                }
            }
        }
        return count % 2 == 1;
    }

    public static Location randomPolygonsPositionAndOrient(cfg.map.WeightedPolygonRegion region) {
        return randomPolygonsPositionAndOrient(region.vertices);
    }

    public static Location randomPolygonsPositionAndOrient(cfg.map.PolygonRegion region) {
        return randomPolygonsPositionAndOrient(region.vertices);
    }

    public static Vector3 randomRotation() {
        final double angle = Math.PI * 2 * Utils.random01();
        return new pathfinding.Vector3(Math.cos(angle), 0, Math.sin(angle));
    }

    public static Vector3 fixRotation(float angle){
        return new pathfinding.Vector3(Math.cos(angle), 0, Math.sin(angle));
    }

    public static Location randomPolygonsPositionAndOrient(List<cfg.map.Vector3> vertices) {
        final Location loc = new Location();
        loc.orient = randomRotation();
        final double[] areas = new double[vertices.size() - 2];

        final cfg.map.Vector3 p1 = vertices.get(0);
        for(int i = 0 ; i < areas.length ; i++) {
            final cfg.map.Vector3 p2 = vertices.get(i + 1);
            final cfg.map.Vector3 p3 = vertices.get(i + 2);
            areas[i] = Math.abs((p2.x - p1.x) * (p3.z - p1.z) - (p2.z - p1.z) * (p3.x - p1.x));
        }

        final int randomAeraIndex = common.Utils.getRandomIndex(areas);
        final cfg.map.Vector3 p2 = vertices.get(randomAeraIndex + 1);
        final cfg.map.Vector3 p3 = vertices.get(randomAeraIndex + 2);

        double w1 = common.Utils.random01();
        double w2 = common.Utils.random01();
        if(w1 + w2 > 1) {
            w1 = 1 - w1;
            w2 = 1 - w2;
        }
        double w3 = 1 - w1 - w2;

        loc.position = new Vector3(w1 * p1.x + w2 * p2.x + w3 * p3.x, w1 * p1.y + w2 * p2.y + w3 * p3.y, w1 * p1.z + w2 * p2.z + w3 * p3.z);
        return loc;
    }

    public static Vector3 randomPolygonsPosition(List<Vector3> vertices) {
        final double[] areas = new double[vertices.size() - 2];

        final Vector3 p1 = vertices.get(0);
        for(int i = 0 ; i < areas.length ; i++) {
            final Vector3 p2 = vertices.get(i + 1);
            final Vector3 p3 = vertices.get(i + 2);
            areas[i] = Math.abs((p2.x - p1.x) * (p3.z - p1.z) - (p2.z - p1.z) * (p3.x - p1.x));
        }

        final int randomAeraIndex = common.Utils.getRandomIndex(areas);
        final Vector3 p2 = vertices.get(randomAeraIndex + 1);
        final Vector3 p3 = vertices.get(randomAeraIndex + 2);

        double w1 = common.Utils.random01();
        double w2 = common.Utils.random01();
        if(w1 + w2 > 1) {
            w1 = 1 - w1;
            w2 = 1 - w2;
        }
        double w3 = 1 - w1 - w2;
        return new Vector3(w1 * p1.x + w2 * p2.x + w3 * p3.x, w1 * p1.y + w2 * p2.y + w3 * p3.y, w1 * p1.z + w2 * p2.z + w3 * p3.z);
    }

    public static Location randomMultiPolygonsPosition(cfg.map.MultiPolygon location) {
        final List<WeightedPolygonRegion> vertexs = location.polygons;
        final List<Double> areas = vertexs.stream().map(p -> calcArea(p)).collect(Collectors.toList());

        double totalArea = areas.stream().mapToDouble(d -> d).sum();
        double area = Utils.random01() * totalArea;
        for(int i = 0 ; i < areas.size() ; i++) {
            area -= areas.get(i);
            if(area < 0) {
                return randomPolygonsPositionAndOrient(vertexs.get(i));
            }
        }
        return null;
    }

    public static pathfinding.Vector3 c2p(cfg.map.Vector3 p) {
        return new pathfinding.Vector3(p.x, p.y, p.z);
    }
    public static pathfinding.Vector3 c2p(cfg.map.Vector2 p) {
        return new pathfinding.Vector3(p.x, 0, p.y);
    }

    public static map.msg.Vector3 p2m(pathfinding.Vector3 p) {
        return p != null ? new map.msg.Vector3((float)p.x, (float)p.y, (float)p.z) : nullmvector();
    }

    public static pathfinding.Vector3 m2p(map.msg.Vector3 p) {
        return p != null ? new pathfinding.Vector3(p.x, p.y, p.z) : nullpvector();
    }
    public static map.msg.Vector3 c2m(cfg.map.Vector3 pos) {
        return new map.msg.Vector3(pos.x, pos.y, pos.z);
    }
    public static map.msg.Vector3 c2m(cfg.map.Vector2 pos) {
        return new map.msg.Vector3(pos.x, 0, pos.y);
    }

    public static double calcArea(cfg.map.WeightedPolygonRegion region) {
        return calcArea(region.vertices);
    }

    public static double calcArea(List<cfg.map.Vector3> vertexs) {
        assert(vertexs.size() >= 3);
        double total = 0;
        cfg.map.Vector3 a = vertexs.get(vertexs.size() - 1);
        for(cfg.map.Vector3 b : vertexs) {
            total += a.x * b.z - a.z * b.x;
        }
        return Math.abs(total);
    }

    public static Vector3 randomPositionInCircle(Vector3 center, double radius) {
        final Random random = Utils.random();
        final double angle = random.nextDouble() * 2 * Math.PI;
        final double r = radius * random.nextDouble();
        return new Vector3(center.x + r * Math.cos(angle), center.y, center.z + r * Math.sin(angle));
    }



}
