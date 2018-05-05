package lx.gs.rank;

/**
 * Created by huangqiang on 2016/3/23.
 */

import xbean.RankRecord;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by huangqiang on 2016/3/23.
 */
public abstract class SimpleOrderRank<T> {
    private final Map<Long, Integer> id2rank;
    private final Map<Integer, T> records;
    private final int capacity;
    public SimpleOrderRank(Map<Long, Integer> id2rank, Map<Integer, T> records, int capacity) {
        this.id2rank = id2rank;
        this.records = records;
        this.capacity = capacity;
    }


    protected abstract long getId(T r);
    protected abstract long getValue(T r);
    protected abstract void setValue(T r, long value);
    protected abstract T create(long id, long value);

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
    public final int update(long id, long newValue) {
        final int size = records.size();
        int oldRank = id2rank.getOrDefault(id, 0);
        final T r;
        if (oldRank > 0) {
            r = records.get(oldRank);
            final long oldValue = getValue(r);
            int newRank = oldRank;
            if (oldValue < newValue) {
                for( ; newRank > 1 && getValue(records.get(newRank - 1)) < newValue ; newRank--);
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
            } else if (newValue < oldValue) {
                for( ; newRank < size && newValue < getValue(records.get(newRank + 1)) ; newRank++);
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
            } else if(getValue(records.get(size)) < newValue) {
                oldRank = size;
                id2rank.remove(getId(records.get(size)));
            } else {
                return 0;
            }
            r = create(id, newValue);
            int newRank = oldRank;
            //System.out.println("insert:" + id + ", " + newValue);
            for( ; newRank > 1 && getValue(records.get(newRank - 1)) < newValue ; newRank--);
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
    public final int greaterUpdate(long id, long newValue) {
        final int size = records.size();
        int oldRank = id2rank.getOrDefault(id, 0);
        final T r;
        if (oldRank > 0) {
            r = records.get(oldRank);
            final long oldValue = getValue(r);
            int newRank = oldRank;
            if (oldValue < newValue) {
                for( ; newRank > 1 && getValue(records.get(newRank - 1)) < newValue ; newRank--);
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
            } else if(getValue(records.get(size)) < newValue) {
                oldRank = size;
                id2rank.remove(getId(records.get(size)));
            } else {
                return 0;
            }
            r = create(id, newValue);
            int newRank = oldRank;
            //System.out.println("insert:" + id + ", " + newValue);
            for( ; newRank > 1 && getValue(records.get(newRank - 1)) < newValue ; newRank--);
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

    public void setRank(long id, int newRank) {
        final T dstRecord = records.get(newRank);
        if(dstRecord == null) {
            //System.out.println("invalid rank:" + newRank);
            return;
        }
        final long newValue = getValue(dstRecord);
        int oldRank = id2rank.getOrDefault(id, 0);
        //System.out.printf("id:%s oldRank:%s newRank:%s newValue:%s\n", id, oldRank, newRank, newValue);
        if(oldRank == newRank)
            return;

        if (oldRank == 0) {
            oldRank = id2rank.size() + 1;
            id2rank.put(id, oldRank);
            records.put(oldRank, create(id, newValue));
        }

        final T r = records.get(oldRank);
        setValue(r, newValue);
        T x = records.remove(newRank);
        if (newRank < oldRank) {
            for (int i = newRank + 1; i <= oldRank; i++) {
                id2rank.put(getId(x), i);
                x = records.put(i, x);
            }
        } else {
            for (int i = newRank - 1; i >= oldRank; i--) {
                id2rank.put(getId(x), i);
                x = records.put(i, x);
            }
        }
        records.put(newRank, r);
        id2rank.put(id, newRank);
    }

    public void removeById(long id) {
        int rank = id2rank.getOrDefault(id, 0);
        if(rank <= 0) return;
        final int size = records.size();
        T x = records.remove(size);
        id2rank.remove(id);
        for(int i = size - 1 ; i >= rank ; i--) {
            id2rank.put(getId(x), i);
            x = records.put(i, x);
        }
    }

    public static void main(String[] argv) {
        final Random r = new Random(1218);
        final SimpleOrderRank<xbean.RankRecord> rank = new SimpleOrderRank<RankRecord>(new HashMap<>(), new HashMap<>(), 10) {
            @Override
            protected long getId(RankRecord r) {
                return r.getId();
            }

            @Override
            protected long getValue(RankRecord r) {
                return r.getValue();
            }

            @Override
            protected void setValue(RankRecord r, long value) {
                r.setValue(value);
            }

            @Override
            protected RankRecord create(long id, long value) {
                final RankRecord r = xbean.Pod.newRankRecordData();
                r.setId(id);
                r.setValue(value);
                return r;
            }
        };

        for(int j = 0 ; j < 3 ; j++) {
            for (int i = 0; i < 100; i++)
                rank.update(i, r.nextInt(200));
            System.out.println(rank.getRecords());
        }
        for (int i = 0; i < 50; i++)
            rank.update(i, r.nextInt(200));
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.setRank(1, 4);
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.setRank(1, 2);
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.setRank(1, 7);
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.removeById(49);
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.removeById(16);
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
        rank.removeById(39);
        System.out.println(rank.id2rank);
        System.out.println(rank.getRecords());
    }

}

