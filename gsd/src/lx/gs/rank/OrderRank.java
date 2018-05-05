package lx.gs.rank;

import xbean.RankRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by huangqiang on 2016/3/23.
 */
public abstract class OrderRank<T, V> {
    private final Map<Long, Integer> id2rank;
    private final Map<Integer, T> records;
    private final int capacity;
    public OrderRank(Map<Long, Integer> id2rank, Map<Integer, T> records, int capacity) {
        this.id2rank = id2rank;
        this.records = records;
        this.capacity = capacity;
    }

    protected abstract boolean less(V x, V y);
    protected abstract long getId(T r);
    protected abstract V getValue(T r);
    protected abstract void setValue(T r, V value);
    protected abstract T create(long id, V value);

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

    /**
     * 如果玩家已经在榜中,newValue < curValue时，仍然更新,排名或升或降
     */
    public final int update(long id, V newValue) {
        final int size = records.size();
        int oldRank = id2rank.getOrDefault(id, 0);
        final T r;
        if (oldRank > 0) {
            r = records.get(oldRank);
            V oldValue = getValue(r);
            int newRank = oldRank;
            if (less(oldValue, newValue)) {
                for( ; newRank > 1 && less(getValue(records.get(newRank - 1)), newValue) ; newRank--);
                if(newRank < oldRank) {
                    T x = records.remove(newRank);
                    for(int i = newRank + 1 ; i <= oldRank ; i++) {
                        id2rank.put(getId(x), i);
                        x = records.put(i, x);
                    }
                    records.put(newRank, r);
                    id2rank.put(id, newRank);
                }
                setValue(r, newValue);
            } else if (less(newValue, oldValue)) {
                for( ; newRank < size && less(newValue, getValue(records.get(newRank + 1))) ; newRank++);
                if(newRank > oldRank) {
                    T x = records.remove(newRank);
                    for(int i = newRank - 1 ; i >= oldRank ; i--) {
                        id2rank.put(getId(x), i);
                        x = records.put(i, x);
                    }
                    records.put(newRank, r);
                    id2rank.put(id, newRank);
                }
                setValue(r, newValue);
            }
            return newRank;
        } else {
            if(size < capacity) {
                oldRank = size + 1;
            } else if(less(getValue(records.get(size)), newValue)) {
                oldRank = size;
            } else {
                return 0;
            }
            r = create(id, newValue);

            int newRank = oldRank;
            for( ; newRank > 1 && less(getValue(records.get(newRank - 1)), newValue) ; newRank--);
            if(newRank < oldRank) {
                T x = records.remove(newRank);
                for (int i = newRank + 1; i <= oldRank; i++) {
                    id2rank.put(getId(x), i);
                    x = records.put(i, x);
                }
            }
            records.put(newRank, r);
            id2rank.put(id, newRank);
            return newRank;
        }
    }

    /**
     *  如果玩家已经在榜中,newValue > curValue时才更新,也就是排名只升不降
     */
    public final int greaterUpdate(long id, V newValue) {
        final int size = records.size();
        int oldRank = id2rank.getOrDefault(id, 0);
        final T r;
        if (oldRank > 0) {
            r = records.get(oldRank);
            V oldValue = getValue(r);
            int newRank = oldRank;
            if (less(oldValue, newValue)) {
                for( ; newRank > 1 && less(getValue(records.get(newRank - 1)), newValue) ; newRank--);
                if(newRank < oldRank) {
                    T x = records.remove(newRank);
                    for(int i = newRank + 1 ; i <= oldRank ; i++) {
                        id2rank.put(getId(x), i);
                        x = records.put(i, x);
                    }
                    records.put(newRank, r);
                    id2rank.put(id, newRank);
                }
                setValue(r, newValue);
            }
            return newRank;
        } else {
            if(size < capacity) {
                oldRank = size + 1;
            } else if(less(getValue(records.get(size)), newValue)) {
                oldRank = size;
            } else {
                return 0;
            }
            r = create(id, newValue);

            int newRank = oldRank;
            for( ; newRank > 1 && less(getValue(records.get(newRank - 1)), newValue) ; newRank--);
            if(newRank < oldRank) {
                T x = records.remove(newRank);
                for (int i = newRank + 1; i <= oldRank; i++) {
                    id2rank.put(getId(x), i);
                    x = records.put(i, x);
                }
            }
            records.put(newRank, r);
            id2rank.put(id, newRank);
            return newRank;
        }
    }

    public static void main(String[] argv) {
        final Random r = new Random(1218);
        final OrderRank<xbean.RankRecord, Long> rank = new OrderRank<RankRecord, Long>(new HashMap<>(), new HashMap<>(), 10) {
            @Override
            protected boolean less(Long x, Long y) {
                return x < y;
            }

            @Override
            protected long getId(RankRecord r) {
                return r.getId();
            }

            @Override
            protected Long getValue(RankRecord r) {
                return r.getValue();
            }

            @Override
            protected void setValue(RankRecord r, Long value) {
                r.setValue(value);
            }

            @Override
            protected RankRecord create(long id, Long value) {
                final RankRecord r = xbean.Pod.newRankRecordData();
                r.setId(id);
                r.setValue(value);
                return r;
            }
        };
        for(int j = 0 ; j < 3 ; j++) {
            for(int i = 0 ; i < 100 ; i++)
                rank.update(i, Long.valueOf(r.nextInt(200)));
            System.out.println(rank.getRecords());
        }
        for(int i = 0 ; i < 50 ; i++)
            rank.update(i, Long.valueOf(r.nextInt(200)));
        System.out.println(rank.getRecords());
    }

}
