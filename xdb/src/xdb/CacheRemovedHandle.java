package xdb;

/**
 * 
 * 内存表设置了容量限制.超过限制被删除的记录通过这个接口回调。
 * 
 * 数据库表永远都不会回调这个接口。
 * 
 * 注册接口在生成出来的表接口里面。
 * 
 * @author lichenghua
 *
 * @param <K>
 * @param <V>
 */
public interface CacheRemovedHandle<K, V> {
	public void recordRemoved(K key, V value);
}
