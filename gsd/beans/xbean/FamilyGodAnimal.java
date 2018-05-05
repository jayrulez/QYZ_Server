
package xbean;

public interface FamilyGodAnimal extends xdb.Bean {
	public FamilyGodAnimal copy(); // deep clone
	public FamilyGodAnimal toData(); // a Data instance
	public FamilyGodAnimal toBean(); // a Bean instance
	public FamilyGodAnimal toDataIf(); // a Data instance If need. else return this
	public FamilyGodAnimal toBeanIf(); // a Bean instance If need. else return this

	public int getAnimalid(); // 
	public int getAnimallevel(); // 神兽进化到的level
	public long getExp(); // 神兽经验值

	public void setAnimalid(int _v_); // 
	public void setAnimallevel(int _v_); // 神兽进化到的level
	public void setExp(long _v_); // 神兽经验值
}
