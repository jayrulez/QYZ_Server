package xdb.util;

import com.goldhuman.Common.Marshal.OctetsStream;

/**
 * 
 * 自增长Key接口。
 * 
 * 实现必须正确实现 equals, hashCode
 * 
 * @author lichenghua
 *
 * @param <T>
 */

public interface AutoKey<T> {

	public T next();
	public T current();

	public String getName();

	// marshal 编码格式: 类型编号(byte) + 自定义数据(...).
	public OctetsStream marshal(OctetsStream os);

	/**
	 * 检查key是否正确。
	 * 如果需要调整自增长种子。
	 * 
	 * @param key
	 * @throws IllegalArgumentException if key is bad.
	 */
	public void accept(T key);
}
