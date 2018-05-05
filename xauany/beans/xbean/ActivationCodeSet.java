
package xbean;

public interface ActivationCodeSet extends xdb.Bean {
	public ActivationCodeSet copy(); // deep clone
	public ActivationCodeSet toData(); // a Data instance
	public ActivationCodeSet toBean(); // a Bean instance
	public ActivationCodeSet toDataIf(); // a Data instance If need. else return this
	public ActivationCodeSet toBeanIf(); // a Bean instance If need. else return this

	public int getType(); // 
	public java.util.Set<Long> getValues(); // 
	public java.util.Set<Long> getValuesAsData(); // 
	public long getCreatetime(); // 
	public long getOpentime(); // 
	public long getExpiratetime(); // 
	public java.util.Set<Integer> getPlatformset(); // 可以激活的平台，空表示无平台限制
	public java.util.Set<Integer> getPlatformsetAsData(); // 可以激活的平台，空表示无平台限制
	public int getIsshared(); // 是否是共享的礼包码
	public boolean getIslogin(); // 是否用于激活码登陆

	public void setType(int _v_); // 
	public void setCreatetime(long _v_); // 
	public void setOpentime(long _v_); // 
	public void setExpiratetime(long _v_); // 
	public void setIsshared(int _v_); // 是否是共享的礼包码
	public void setIslogin(boolean _v_); // 是否用于激活码登陆
}
