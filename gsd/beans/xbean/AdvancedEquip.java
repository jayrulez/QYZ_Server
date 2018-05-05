
package xbean;

public interface AdvancedEquip extends xdb.Bean {
	public AdvancedEquip copy(); // deep clone
	public AdvancedEquip toData(); // a Data instance
	public AdvancedEquip toBean(); // a Bean instance
	public AdvancedEquip toDataIf(); // a Data instance If need. else return this
	public AdvancedEquip toBeanIf(); // a Bean instance If need. else return this


}
