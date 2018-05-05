package xdb;

/**
 * TTable jmx 管理接口 
 * @author lichenghua
 *
 */
public interface TTableMBean {
	public String getTableName();
	public String getLockName();
	public String getPersistenceName();

	// cache info
	public int getCacheCapacity();
	public void setCacheCapacity(int capacity); // only for debug.
	public int getCacheSize();
	public String getCacheClassName();

	// table access counter
	public long getCountAdd();               // add 次数
	public long getCountAddMiss();           // add 不命中 cache 次数
	public long getCountAddStorageMiss();    // add 数据库中已经存在 key 次数

	public long getCountGet();               // get 次数
	public long getCountGetMiss();           // get 不命中 cache 次数
	public long getCountGetStorageMiss();    // get 数据库中不存在 key 次数

	public long getCountRemove();            // remove 次数
	public long getCountRemoveMiss();        // remove 不命中 cache 次数
	public long getCountRemoveStorageMiss(); // remove 数据库中不存在 key 次数

	// 计算值，方便察看
	public String getPercentAddHit();        // AddHit = Add - AddMiss, AddHit / Add
	public String getPercentGetHit();        // GetHit = Get - GetMiss, GetHit / Get
	public String getPercentRemoveHit();     // RemoveHit = Remove - RemoveMiss, RemoveHit / Remove
	public String getPercentCacheHit();      // (AddHit + RemoveHit + GetHit) / (Add + Remove + Get)

	// Storage
	public long getStorageCountMarshal0();
	public long getStorageCountMarshalN();
	public long getStorageCountMarshalNTryFail();
	public long getStorageCountFlush();
	public long getStorageCountSnapshot();
	public long getStorageFlushKeySize();
	public long getStorageFlushValueSize();
}
