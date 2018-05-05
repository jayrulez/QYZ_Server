package xdb;

/**
 * Cache 遍历回调接口。
 * 
 * 锁只在回调时保持，然后在回调之后释放掉。
 * 
 * 不能修改。
 * 不能保存非常变量的引用。
 * 
 * @author lichenghua
 *
 */
public interface CacheQuery<K, V> {
	public void onQuery(K key, V value);
}
