
package xbean;

public interface Pet extends xdb.Bean {
	public Pet copy(); // deep clone
	public Pet toData(); // a Data instance
	public Pet toBean(); // a Bean instance
	public Pet toDataIf(); // a Data instance If need. else return this
	public Pet toBeanIf(); // a Bean instance If need. else return this

	public int getModelid(); // 
	public int getActiveskinid(); // 
	public java.util.Set<Integer> getSkinidlist(); // 
	public java.util.Set<Integer> getSkinidlistAsData(); // 
	public int getLevel(); // 伙伴的级别
	public long getExp(); // 伙伴的经验值
	public int getStarlevel(); // 星阶
	public java.util.Map<Integer, Integer> getSkills(); // 伙伴的技能信息
	public java.util.Map<Integer, Integer> getSkillsAsData(); // 伙伴的技能信息
	public int getAwakelevel(); // 觉醒的等级
	public java.util.Map<Integer, Float> getFixedattrs(); // 固定属性,不会被替换,目前只有buff属性添加到里面
	public java.util.Map<Integer, Float> getFixedattrsAsData(); // 固定属性,不会被替换,目前只有buff属性添加到里面
	public java.util.Map<Integer, Float> getKarmaattrs(); // 缘分属性,会有变化
	public java.util.Map<Integer, Float> getKarmaattrsAsData(); // 缘分属性,会有变化
	public java.util.Map<Integer, Float> getGrowthattrs(); // 成长属性,会有变化
	public java.util.Map<Integer, Float> getGrowthattrsAsData(); // 成长属性,会有变化
	public java.util.List<Integer> getBuffids(); // 
	public java.util.List<Integer> getBuffidsAsData(); // 
	public java.util.Map<Integer, Float> getLastwashrecord(); // 
	public java.util.Map<Integer, Float> getLastwashrecordAsData(); // 
	public java.util.Map<Integer, Float> getWashattrs(); // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
	public java.util.Map<Integer, Float> getWashattrsAsData(); // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存

	public void setModelid(int _v_); // 
	public void setActiveskinid(int _v_); // 
	public void setLevel(int _v_); // 伙伴的级别
	public void setExp(long _v_); // 伙伴的经验值
	public void setStarlevel(int _v_); // 星阶
	public void setAwakelevel(int _v_); // 觉醒的等级
}
