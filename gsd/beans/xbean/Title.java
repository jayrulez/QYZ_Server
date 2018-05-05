
package xbean;

public interface Title extends xdb.Bean {
	public Title copy(); // deep clone
	public Title toData(); // a Data instance
	public Title toBean(); // a Bean instance
	public Title toDataIf(); // a Data instance If need. else return this
	public Title toBeanIf(); // a Bean instance If need. else return this

	public int getTitlekey(); // 称号id
	public int getTitletype(); // 称号所属类型，战力榜，竞技场榜，财富榜等
	public int getState(); // 称号的状态，0为未获得，1为获得，2为激活
	public long getGettime(); // 称号获得的时间
	public long getActivetime(); // 称号激活的时间
	public long getExpiretime(); // 称号到期时间

	public void setTitlekey(int _v_); // 称号id
	public void setTitletype(int _v_); // 称号所属类型，战力榜，竞技场榜，财富榜等
	public void setState(int _v_); // 称号的状态，0为未获得，1为获得，2为激活
	public void setGettime(long _v_); // 称号获得的时间
	public void setActivetime(long _v_); // 称号激活的时间
	public void setExpiretime(long _v_); // 称号到期时间
}
