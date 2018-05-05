
package xbean;

public interface Accessory extends xdb.Bean {
	public Accessory copy(); // deep clone
	public Accessory toData(); // a Data instance
	public Accessory toBean(); // a Bean instance
	public Accessory toDataIf(); // a Data instance If need. else return this
	public Accessory toBeanIf(); // a Bean instance If need. else return this

	public java.util.List<xbean.AccessoryProp> getMainprop(); // 主属性,目前是2个
	public java.util.List<xbean.AccessoryProp> getMainpropAsData(); // 主属性,目前是2个
	public java.util.List<xbean.AccessoryProp> getExtraprop(); // 附加属性，目前是5个
	public java.util.List<xbean.AccessoryProp> getExtrapropAsData(); // 附加属性，目前是5个
	public xbean.AccessoryWashResult getLastwashrecord(); // 饰品洗练的临时结果,一次有效，成功应用或者取消后删除

}
