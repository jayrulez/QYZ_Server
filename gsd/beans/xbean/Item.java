
package xbean;

public interface Item extends xdb.Bean {
	public Item copy(); // deep clone
	public Item toData(); // a Data instance
	public Item toBean(); // a Bean instance
	public Item toDataIf(); // a Data instance If need. else return this
	public Item toBeanIf(); // a Bean instance If need. else return this

	public long getItemid(); // 物品唯一id
	public int getModelid(); // 物品model id
	public int getCount(); // 物品数量
	public boolean getIsbind(); // 是否绑定
	public int getPosition(); // 在包裹中的位置
	public long getExpiretime(); // 过期时间

	public void setItemid(long _v_); // 物品唯一id
	public void setModelid(int _v_); // 物品model id
	public void setCount(int _v_); // 物品数量
	public void setIsbind(boolean _v_); // 是否绑定
	public void setPosition(int _v_); // 在包裹中的位置
	public void setExpiretime(long _v_); // 过期时间
}
