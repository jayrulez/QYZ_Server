
package xbean;

public interface FamilyActivity extends xdb.Bean {
	public FamilyActivity copy(); // deep clone
	public FamilyActivity toData(); // a Data instance
	public FamilyActivity toBean(); // a Bean instance
	public FamilyActivity toDataIf(); // a Data instance If need. else return this
	public FamilyActivity toBeanIf(); // a Bean instance If need. else return this

	public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfo(); // 家族神兽信息
	public java.util.Map<Integer, xbean.FamilyGodAnimal> getGodanimalinfoAsData(); // 家族神兽信息

}
