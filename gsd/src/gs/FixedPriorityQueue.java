package gs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 容量固定的堆，用来取最大的N个值（比较器的比较值 * -1则获得的是最小的N个值，N = capacity）
 * 原理：堆容量固定，堆顶是由比较器比较出来的最小的值，遍历待排序数据，如果比堆顶大，则替换堆顶，插入到堆中
 * @author Jin Shuai
 */
public final class FixedPriorityQueue<E> extends PriorityQueue<E>{
    private int capacity;

    public FixedPriorityQueue(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    public FixedPriorityQueue(int capacity, Comparator<? super E> comparator) {
        super(capacity, comparator);
        this.capacity = capacity;
    }

    @Override
    public boolean offer(E entry) {
        if(size() == capacity){
            if(compare(entry, peek()) <= 0){  //如果小于等于堆顶，则小于等于堆中所有元素，那么丢弃，插入队列失败
                return false;
            }
            poll();  //否则取出堆顶，丢弃，使队列可以继续插入
        }
        return super.offer(entry);
    }

    /**
     * 返回堆中的所有元素，顺序从大到小
     * @return
     */
    public List<E> getSortedData(){
        return getSortedData((o1, o2) -> compare(o1, o2) * -1);
    }

    /**
     * 自定义排序
     * @param comparator
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<E> getSortedData(Comparator<? super E> comparator){
        List<E> ret = this.stream().filter(e -> e != null).collect(Collectors.toList());
        Collections.sort(ret, comparator);
        return ret;
    }

    @SuppressWarnings("unchecked")
    private int compare(E e1, E e2){
        Comparator<? super E> comparator = comparator();
        return comparator != null
                ? comparator.compare(e1, e2)
                : ((Comparable<? super E>) e1).compareTo(e2);
    }

}
