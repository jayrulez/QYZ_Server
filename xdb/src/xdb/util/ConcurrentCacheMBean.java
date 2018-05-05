package xdb.util;

public interface ConcurrentCacheMBean {
	public String getName();
	public long getCountGet();
	public long getCountMiss();
	public String getPercentGetHit(); // (get - miss) / get
	public int getSize();
	public int getCapacity();

	public void setCapacity(int capacity);
}
