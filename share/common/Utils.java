package common;

import cfg.ectype.EctypeType;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import xdb.Trace;
import xio.Protocol;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by huangqiang on 2016/4/22.
 */
public class Utils {

    public static map.msg.Vector3 c2m(cfg.map.Vector2 pos) {
        return new map.msg.Vector3(pos.x, 0, pos.y);
    }

    public static void dynamicProcess(Object object, xio.Protocol msg) {
        try {
            Trace.debug("{}.dynamicProcess msg:{} {}", object.getClass().getName(), msg.getClass().getName(), msg);
            object.getClass().getMethod("process", msg.getClass()).invoke(object, msg);
        } catch (Exception e) {
            Trace.error(object.getClass().getName() + ".dynamicProcess " + msg.getClass().getName(), e);
        }
    }


    public final static int BIT_LINEID = 16;
    public final static int BIT_MAPID = 8;

    public final static int BIT_MAP_TYPE = 8;
    public final static int BIT_SERVERID_HOLDER = 12;
//    public final static int BIT_SERVERID_CREATOR = 12;

    public final static int TYPE_WORLD = EctypeType.WORLD;
    public final static int TYPE_FAMILY = EctypeType.FAMILY;

    public static int getLowBits(long id, int bits) {
        return (int)(id & ((1 << bits) - 1));
    }

    public static int getTypeById(long id) {
        return  getLowBits(id, BIT_MAP_TYPE);
    }

    public static boolean isWorld(long id) {
        return getTypeById(id) == TYPE_WORLD;
    }

    public static boolean isWorldOrFamily(long id) {
        final int type = getTypeById(id);
        return type == TYPE_WORLD || type == TYPE_FAMILY;
    }

    public static boolean isEctype(long id) {
        return id != 0 && !isWorldOrFamily(id);
    }

    public static boolean isFamily(long id){
        return getTypeById(id) == TYPE_FAMILY;
    }


    public static boolean isSameWorld(long mapid1, long mapid2) {
        return isWorld(mapid1) && isWorld(mapid2) && getWorldidById(mapid1) == getWorldidById(mapid2);
    }

    public static int getHolderserveridById(long id) {
        return getLowBits(id >> BIT_MAP_TYPE, BIT_SERVERID_HOLDER);
    }

//    public static int getOwnerServeridById(long id) {
//        return getLowBits(id >> (BIT_MAP_TYPE + BIT_SERVERID_HOLDER), BIT_SERVERID_CREATOR);
//    }

    public static int getServeridByRoleid(long roleid) {
        return getLowBits(roleid, 12);
    }

    public static long makeId(int serverid, int holdserverid, int worldid, int lineid) {
        long id = (lineid << BIT_MAPID) + worldid;
//        id = (id << BIT_SERVERID_CREATOR) + serverid;
        id = (id << BIT_SERVERID_HOLDER) + holdserverid;
        id = (id << BIT_MAP_TYPE) + TYPE_WORLD;
        return id;
    }

    /**
     * 每个家族唯一的mapid
     * @param serverid
     * @param holdserverid
     * @param familyId
     * @return
     */
    public static long makeFamilyId(int serverid, int holdserverid, long familyId){
        int seed = 1;
        long id = (seed << BIT_MAPID) + familyId;
//        id = (id << BIT_SERVERID_CREATOR) + serverid;
        id = (id << BIT_SERVERID_HOLDER) + holdserverid;
        id = (id << BIT_MAP_TYPE) + EctypeType.FAMILY;
        return id;
    }

    public static int getWorldidById(long id) {
        return getLowBits(id >> (/* BIT_SERVERID_CREATOR + */BIT_SERVERID_HOLDER + BIT_MAP_TYPE), BIT_MAPID);
    }

    public static int getLineididById(long id) {
        return getLowBits(id >> (BIT_MAPID + /* BIT_SERVERID_CREATOR + */ BIT_SERVERID_HOLDER + BIT_MAP_TYPE), BIT_LINEID);
    }


    public static long getUidById(long id) {
        return id >> (BIT_MAP_TYPE + BIT_SERVERID_HOLDER /* + BIT_SERVERID_CREATOR */);
    }

    public static long getUidserverById(long id) {
        return id >> (BIT_MAP_TYPE + BIT_SERVERID_HOLDER);
    }




    public static Random random() {
        return ThreadLocalRandom.current();
    }

    public static double random01() {
        return random().nextDouble();
    }

    public static float random01f(){
        return random().nextFloat();
    }

    public static int randomRange(int min, int max) {
        return min + random().nextInt(max - min);
    }

    public static double randomRange(double min, double max) {
        return min + (max - min) * random().nextDouble();
    }


    public static<T> int addValue(Map<T, Integer> map, T key, int add) {
        final int newValue = map.getOrDefault(key, 0) + add;
        map.put(key, newValue);
        return newValue;
    }

    public static<T> long addValue(Map<T, Long> map, T key, long add) {
        final long newValue = map.getOrDefault(key, 0L) + add;
        map.put(key, newValue);
        return newValue;
    }

    public static<T> float addValue(Map<T, Float> map, T key, float add) {
        final float newValue = map.getOrDefault(key, 0f) + add;
        map.put(key, newValue);
        return newValue;
    }

    public static<T> void addValue(Map<T, Float> result, Map<T, Float> add) {
        for(Map.Entry<T, Float> e : add.entrySet()) {
            final T key = e.getKey();
            result.put(key, result.getOrDefault(key, 0f) + e.getValue());
        }
    }

    public static<T> void addIntValue(Map<T, Integer> result, Map<T, Integer> add) {
        for(Map.Entry<T, Integer> e : add.entrySet()) {
            final T key = e.getKey();
            result.put(key, result.getOrDefault(key, 0) + e.getValue());
        }
    }

    public static byte toByte(boolean value) {
        return value ? (byte)1 : (byte)0;
    }

    public static <T> T randomChoose(List<T> list) {
        final int n = list.size();
        return n == 1 ? list.get(0) : list.get(random().nextInt(list.size()));
    }

    public static <T> T getOrLast(List<T> list, int index) {
        return list.get(Math.min(index, list.size() - 1));
    }
    public static <T> T getLast(List<T> list) {
        return list.isEmpty() ? null : list.get(list.size() - 1);
    }

    public static <T> void removeLast(List<T> list) {
        list.remove(list.size() - 1);
    }

    public static <T> void addNotNull(List<T> list, T value) {
        if(value != null)
            list.add(value);
    }

    public static <T> boolean addIfNotPresent(List<T> list, T value) {
        if(!list.contains(value)) {
            list.add(value);
            return true;
        } else {
            return false;
        }
    }

    public static int sumInt(Collection<Integer> arr) {
        int total = 0;
        for(int x : arr)
            total += x;
        return total;
    }

    public static long sumIntToLong(Collection<Integer> arr) {
        long total = 0;
        for(int x : arr)
            total += x;
        return total;
    }

    public static long sumLong(Collection<Long> arr) {
        long total = 0;
        for(long x : arr)
            total += x;
        return total;
    }

    /**
     * 在min到max之间 (不包括max)随机产生count个不重复的数
     */
    public static Collection<Integer> getMutiRandom(int minValue, int maxValue, int count){
        if(count <= 0)
            return Collections.emptyList();
        final int n = maxValue - minValue;
        if(n >= count * 4) {
            final HashSet<Integer> result = new HashSet<>();
            for(int i = 0 ; i < count; ) {
                if(result.add(randomRange(minValue,maxValue)))
                    i++;
            }
            return result;
        } else if(count < n) {
            final ArrayList<Integer> result = new ArrayList<>(count);
            final int[] intList = new int[n];
            for(int i = 0 ; i < n ;i++)
                intList[i] = minValue + i;
            for(int i = 0 ; i < count; i++) {
                final int index = randomRange(i, n);
                result.add(intList[index]);
                intList[index] = intList[i];
            }
            return result;
        } else {
            final ArrayList<Integer> result = new ArrayList<>(n);
            for(int i = minValue ; i < maxValue ; i++)
                result.add(i);
            return result;
        }
    }

    /**
     * 根据一组概率随机出其中一个概率，并返回其索引值
     * @param rateList
     * @return
     */
    public static int getRandomIndex(List<Integer> rateList) {
        int total = 0;
        for (int i : rateList) {
            total += i;
        }
        int randomInt = random().nextInt(total);

        int flg = 0;
        for (int i = 0; i < rateList.size(); i++) {
            flg += rateList.get(i);
            if (randomInt < flg) {
                return i;
            }
        }
        return 0;
    }

    public static int getRandomIndex(double[] weights) {
        double total = 0;
        for (double w : weights) {
            total += w;
        }
        double r = random().nextDouble() * total;

        for(int i = 0 ; i < weights.length ; i++) {
            if((r -= weights[i]) <= 0)
                return i;
        }
        return weights.length - 1;
    }

    /**
     * 自身为正则返回自身，否则返回最小的正整数
     * @param val
     * @return
     */
    public static int selfOrMin(int val){
        return val < 1 ? 1 : val;
    }

    /**
     * 自身为正则返回自身，否则返回最大的正整数
     * @param val
     * @return
     */
    public static int selfOrMax(int val){
        return val < 1 ? Integer.MAX_VALUE : val;
    }

    /**
     * 自身为正则返回自身，否则返回最小的正整数
     * @param val
     * @return
     */
    public static long selfOrMin(long val){
        return val < 1 ? 1 : val;
    }

    /**
     * 自身为正则返回自身，否则返回最大的正整数
     * @param val
     * @return
     */
    public static long selfOrMax(long val){
        return val < 1 ? Long.MAX_VALUE : val;
    }

    public static xio.Protocol decode(xio.Manager manager, int ptype, Octets pdata) {
        try {
            Protocol.Stub stub2 = ((Protocol.Coder) (manager.getCoder())).getStub(ptype);
            xio.Protocol p = stub2.newInstance();
            p.unmarshal(OctetsStream.wrap(pdata));
            return p;
        } catch (Exception e) {
            Trace.error("Util.decode ptype:" + ptype, e);
        }
        return null;
    }
}
