package lx.gs.rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by huangqiang on 2016/3/23.
 */
public class SimpleSwapRank {
    private final Map<Long, Integer> id2rank;
    private final Map<Integer, Long> records;
    private final int capacity;
    public SimpleSwapRank(Map<Long, Integer> id2rank, Map<Integer, Long> records, int capacity) {
        this.id2rank = id2rank;
        this.records = records;
        this.capacity = capacity;
    }

    public final Map<Integer, Long> getRecords() {
        return records;
    }

    public final int getRankById(long id) {
        return id2rank.getOrDefault(id, 0);
    }

    public final long getIdByRank(int rank) {
        return records.getOrDefault(rank, 0L);
    }


    public final boolean add(long id) {
        if(id2rank.containsKey(id))
            return true;
        final int size = records.size();
        if(size >= capacity)
            return false;
        id2rank.put(id, size + 1);
        records.put(size + 1, id);
        return true;
    }

    public void swap(long id1, long id2) {
        final Integer oldRank1 = id2rank.get(id1);
        final Integer oldRank2 = id2rank.get(id2);
        if(oldRank1 != null) {
            id2rank.put(id2, oldRank1);
            if(oldRank2 != null) {
                records.remove(oldRank2);
                records.put(oldRank1, id2);
                records.put(oldRank2, id1);
                id2rank.put(id1, oldRank2);
            } else {
                records.put(oldRank1, id2);
                id2rank.remove(id1);
            }
        } else {
            if(oldRank2 != null) {
                id2rank.put(id1, oldRank2);
                records.put(oldRank2, id1);
                id2rank.remove(id2);
            }
        }
    }

    public static void main(String[] argv) {
        final Random r = new Random(1218);
        final SimpleSwapRank rank = new SimpleSwapRank(new HashMap<>(), new HashMap<>(), 10);
        for(int i = 0 ; i < 100 ; i++)
            rank.add(Long.valueOf(i));
        System.out.println(rank.getRecords());
        rank.swap(1, 2);
        System.out.println(rank.getRecords());
        rank.swap(4, 11);
        System.out.println(rank.getRecords());
        rank.swap(12, 5);
        System.out.println(rank.getRecords());
        rank.swap(13, 14);
        System.out.println(rank.getRecords());
        rank.swap(11, 12);
        System.out.println(rank.getRecords());
    }
}
