package lx.gs.rank;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by huangqiang on 2016/3/23.
 */
public abstract class SwapRank<T> {
    private final Map<Long, Integer> id2rank;
    private final Map<Integer, T> records;
    private final int capacity;
    public SwapRank(Map<Long, Integer> id2rank, Map<Integer, T> records, int capacity) {
        this.id2rank = id2rank;
        this.records = records;
        this.capacity = capacity;
    }

    protected abstract long getId(T r);
    protected abstract T create(long id);

    public final int size() {
        return records.size();
    }

    public final Map<Integer, T> getRecords() {
        return records;
    }

    public final int getRankById(long id) {
        return id2rank.getOrDefault(id, 0);
    }

    public final long getIdByRank(int rank) {
        final T r = records.get(rank);
        return r != null ? getId(r) : 0;
    }

    public final T getRecordById(long id) {
        final Integer rank = id2rank.get(id);
        return rank != null ? records.get(rank) : null;
    }

    public final T getRecordByRank(int rank) {
        return records.get(rank);
    }


    public final boolean addId(long id) {
        return add(create(id));
    }

    public final boolean add(T record) {
        final long id = getId(record);
        if(id2rank.containsKey(id))
            return true;
        final int size = records.size();
        if(size >= capacity)
            return false;
        id2rank.put(id, size + 1);
        records.put(size + 1, record);
        return true;
    }

    public void swap(long id1, long id2) {
        final Integer oldRank1 = id2rank.get(id1);
        final Integer oldRank2 = id2rank.get(id2);
        T r1;
        T r2;
        if(oldRank1 != null) {
            id2rank.put(id2, oldRank1);
            if(oldRank2 != null) {
                r2 = records.remove(oldRank2);
                r1 = records.put(oldRank1, r2);
                records.put(oldRank2, r1);
                id2rank.put(id1, oldRank2);
            } else {
                records.put(oldRank1, create(id2));
                id2rank.remove(id1);
            }
        } else {
            if(oldRank2 != null) {
                id2rank.put(id1, oldRank2);
                records.put(oldRank2, create(id1));
                id2rank.remove(id2);
            }
        }
    }

    public void setRank(long roleid, int rank) {
        final T dstRecord = records.get(rank);
        if(dstRecord != null) {
            swap(roleid, getId(dstRecord));
        }
    }
    /*
    public void swap(long id1, long id2) {
        final int size = records.size();
        final Integer oldRank1 = id2rank.get(id1);
        final Integer oldRank2 = id2rank.get(id2);
        T r1;
        T r2;
        if(oldRank1 != null) {
            id2rank.put(id2, oldRank1);
            if(oldRank2 != null) {
                r2 = records.remove(oldRank2);
                r1 = records.put(oldRank1, r2);
                records.put(oldRank2, r1);
                id2rank.put(id1, oldRank2);
            } else {
                r1 = records.put(oldRank1, create(id2));
                if(size < capacity) {
                    records.put(size + 1, r1);
                    id2rank.put(id1, size + 1);
                }
            }
        } else {
            if(oldRank2 != null) {
                id2rank.put(id1, oldRank2);
                r2 = records.put(oldRank2, create(id1));
                if(size < capacity) {
                    records.put(size + 1, r2);
                    id2rank.put(id2, size + 1);
                }
            } else {

            }
        }
    }
    */

    public static void main(String[] argv) {
        final Random r = new Random(1218);
        final SwapRank<Long> rank = new SwapRank<Long>(new HashMap<>(), new HashMap<>(), 10) {
            @Override
            protected long getId(Long r) {
                return r;
            }

            @Override
            protected Long create(long id) {
                return id;
            }
        };
        for(int i = 0 ; i < 100 ; i++)
            rank.add(Long.valueOf(i));
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.swap(1, 2);
        System.out.println(rank.getRecords());
        rank.swap(4, 11);
        System.out.println(rank.getRecords());
        rank.swap(12, 5);
        System.out.println(rank.getRecords());
        rank.swap(13, 14);
        System.out.println(rank.getRecords());
        rank.setRank(-1, 5);
        System.out.println(rank.getRecords());
        rank.setRank(6, 3);
        System.out.println(rank.getRecords());
    }

}
