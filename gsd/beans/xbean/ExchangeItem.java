
package xbean;

public interface ExchangeItem extends xdb.Bean {
	public ExchangeItem copy(); // deep clone
	public ExchangeItem toData(); // a Data instance
	public ExchangeItem toBean(); // a Bean instance
	public ExchangeItem toDataIf(); // a Data instance If need. else return this
	public ExchangeItem toBeanIf(); // a Bean instance If need. else return this

	public long getId(); // 
	public long getOwner(); // 
	public int getPrice(); // 单价
	public int getModelid(); // 
	public int getNum(); // 
	public long getExpiretime(); // 
	public int getAnneallevel(); // 
	public int getPerfuselevel(); // 
	public java.util.List<xbean.AccessoryProp> getAccessorymainprop(); // 
	public java.util.List<xbean.AccessoryProp> getAccessorymainpropAsData(); // 
	public java.util.List<xbean.AccessoryProp> getAccessoryviceprop(); // 
	public java.util.List<xbean.AccessoryProp> getAccessoryvicepropAsData(); // 
	public long getUnshelvetime(); // 下架时间

	public void setId(long _v_); // 
	public void setOwner(long _v_); // 
	public void setPrice(int _v_); // 单价
	public void setModelid(int _v_); // 
	public void setNum(int _v_); // 
	public void setExpiretime(long _v_); // 
	public void setAnneallevel(int _v_); // 
	public void setPerfuselevel(int _v_); // 
	public void setUnshelvetime(long _v_); // 下架时间
}
