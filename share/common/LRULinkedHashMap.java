package common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HuangQiang on 2016/9/1.
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private int size;

    private LRULinkedHashMap(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }

    public static <K, V> LRULinkedHashMap<K, V> newInstance(int size) {
        return new LRULinkedHashMap<K, V>(size);
    }

}
