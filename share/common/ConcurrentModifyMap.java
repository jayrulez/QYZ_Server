package common;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by HuangQiang on 2016/5/25.
 */
public class ConcurrentModifyMap<K, V> {
    private final static Object REMOVE = new Object();
    private final Map<K, V> map = new HashMap<>();
    private final Map<K, V> changeMap = new HashMap<>();
    private boolean inForEach = false;
    private final static class Pair<K, V> {
        public final K key;
        public final V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V get(K key) {
        if(changeMap.isEmpty()) {
            return map.get(key);
        } else {
            final V value = changeMap.get(key);
            if(value == null) {
                return map.get(key);
            } else {
                return value != REMOVE ? value : null;
            }
        }
    }

    public final int size() {
        if(changeMap.isEmpty()) {
            return map.size();
        } else {
            int extra = 0;
            for(Map.Entry<K, V> e : map.entrySet()) {
                if(e.getValue() != REMOVE) {
                    if(!map.containsKey(e.getKey()))
                        extra++;
                } else {
                    if(map.containsKey(e.getKey()))
                        extra--;
                }
            }
            return map.size() + extra;
        }
    }

    public void put(K key, V value) {
        if(inForEach) {
            if(map.containsKey(key)) {
                map.put(key, value);
                changeMap.remove(key);
            } else {
                changeMap.put(key, value);
            }
        } else {
            map.put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    public void remove(K key) {
        if(inForEach) {
            if(map.containsKey(key)) {
                changeMap.put(key, (V) REMOVE);
            } else {
                changeMap.remove(key);
            }
        } else {
            map.remove(key);
        }
    }

    /**
     * 移除所有 返回 false的 entry
     */
    public void update(BiFunction<K, V, Boolean> func) {
        this.inForEach = true;
        try {
            map.entrySet().removeIf(e -> !func.apply(e.getKey(), e.getValue()));
        } finally {
            this.inForEach = false;
            if (!changeMap.isEmpty()) {
                changeMap.forEach((k, v) -> {
                    if (v != REMOVE) {
                        changeMap.put(k, v);
                    } else {
                        changeMap.remove(k);
                    }
                });
            }
        }
    }

    public void forValues(Consumer<V> func) {
        this.inForEach = true;
        try {
            map.values().forEach(func::accept);
        } finally {
            this.inForEach = false;
            if (!changeMap.isEmpty()) {
                changeMap.forEach((k, v) -> {
                    if (v != REMOVE) {
                        changeMap.put(k, v);
                    } else {
                        changeMap.remove(k);
                    }
                });
                changeMap.clear();
            }
        }
    }

    public void forEntrySet(BiConsumer<K, V> func) {
        this.inForEach = true;
        try {
            map.forEach(func::accept);
        } finally {
            this.inForEach = false;
            if (!changeMap.isEmpty()) {
                changeMap.forEach((k, v) -> {
                    if (v != REMOVE) {
                        changeMap.put(k, v);
                    } else {
                        changeMap.remove(k);
                    }
                });
                changeMap.clear();
            }
        }
    }

    public void forEntrySet2(Consumer<Map.Entry<K, V>> func) {
        this.inForEach = true;
        try {
            map.entrySet().forEach(func::accept);
        } finally {
            this.inForEach = false;
            if (!changeMap.isEmpty()) {
                changeMap.forEach((k, v) -> {
                    if (v != REMOVE) {
                        changeMap.put(k, v);
                    } else {
                        changeMap.remove(k);
                    }
                });
                changeMap.clear();
            }
        }
    }

    public boolean anyMatch(Predicate<Map.Entry<K, V>> p) {
        this.inForEach = true;
        try {
            return map.entrySet().stream().anyMatch(p);
        } finally {
            this.inForEach = false;
            if (!changeMap.isEmpty()) {
                changeMap.forEach((k, v) -> {
                    if (v != REMOVE) {
                        changeMap.put(k, v);
                    } else {
                        changeMap.remove(k);
                    }
                });
                changeMap.clear();
            }
        }
    }

    public boolean allMatch(Predicate<Map.Entry<K, V>> p) {
        this.inForEach = true;
        try {
            return map.entrySet().stream().allMatch(p);
        } finally {
            this.inForEach = false;
            if (!changeMap.isEmpty()) {
                changeMap.forEach((k, v) -> {
                    if (v != REMOVE) {
                        changeMap.put(k, v);
                    } else {
                        changeMap.remove(k);
                    }
                });
                changeMap.clear();
            }
        }
    }

    public static void main(String[] argv) {
        ConcurrentModifyMap<Integer, Integer> d = new ConcurrentModifyMap<>();
        for(int i = 0 ; i < 10 ; i++) {
            d.put(i, i * i);
        }
        d.forValues(v -> System.out.println("forValues " + v));
        d.forEntrySet((k, v) -> System.out.println("forEnterSet k:" + k + ", v:" + v));
        d.forEntrySet2(e -> System.out.println("forEntrySet2 k:" + e.getKey() + ",v:" + e.getValue()));
    }
}
