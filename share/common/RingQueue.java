package common;

/**
 * Created by HuangQiang on 2016/5/31.
 */
public class RingQueue<T> {
    private final int capacity;
    private final Object[] datas;
    private long headid;
    private long tailid;
    public RingQueue(int capacity) {
        this.capacity = capacity;
        this.datas = new Object[capacity];
        this.headid = this.tailid = 0;
    }

    public long getHeadid() {
        return headid;
    }


    public long getTailid() {
        return tailid;
    }

    @SuppressWarnings("unchecked")
    public T get(long index) {
        return (T)datas[(int)(index % capacity)];
    }

    public boolean add(T obj) {
        if(tailid - headid < capacity) {
            datas[(int) (tailid++ % capacity)] = obj;
            return true;
        } else {
            return false;
        }
    }

    public long forceAdd(T obj) {
        datas[(int) (tailid++ % capacity)] = obj;
        if(tailid - headid > capacity) {
           headid = tailid - capacity;
        }
        return tailid;
    }

    public void removeUntil(long newHeadid) {
        if(newHeadid > headid) {
            for(long i = headid, e = Math.min(newHeadid, tailid) ; i < e ; i++) {
                datas[(int)(i % capacity)] = null;
            }
            this.headid = newHeadid;
            if(newHeadid > tailid) {
                tailid = newHeadid;
            }
        }
    }

    public void reset() {
        for(long i = headid ; i < tailid ; i++) {
            datas[(int)(i % capacity)] = null;
        }
        this.headid = this.tailid = 0;
    }
}
