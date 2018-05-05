
package xbean;

public interface Talisman extends xdb.Bean {
	public Talisman copy(); // deep clone
	public Talisman toData(); // a Data instance
	public Talisman toBean(); // a Bean instance
	public Talisman toDataIf(); // a Data instance If need. else return this
	public Talisman toBeanIf(); // a Bean instance If need. else return this

	public long getTalismanid(); // 
	public int getModelid(); // 法宝的策划配置id
	public int getPos(); // 物品在包裹中的位置，从1开始编号
	public boolean getIsbind(); // 法宝绑定类型
	public long getExpiretime(); // 过期时间
	public long getNormalexp(); // 法宝普通经验
	public int getNormallevel(); // 法宝普通等级,开始为1
	public int getStarexp(); // 法宝星阶经验
	public int getStarlevel(); // 法宝星阶等级,开始为1
	public int getWuxingtype(); // 法宝当前的五行属性类型
	public int getWuxingvalue(); // 五行属性攻击值
	public int getAwakelevel(); // 法宝觉醒等级,从0开始
	public java.util.Map<Integer, Integer> getSkills(); // 法宝技能
	public java.util.Map<Integer, Integer> getSkillsAsData(); // 法宝技能

	public void setTalismanid(long _v_); // 
	public void setModelid(int _v_); // 法宝的策划配置id
	public void setPos(int _v_); // 物品在包裹中的位置，从1开始编号
	public void setIsbind(boolean _v_); // 法宝绑定类型
	public void setExpiretime(long _v_); // 过期时间
	public void setNormalexp(long _v_); // 法宝普通经验
	public void setNormallevel(int _v_); // 法宝普通等级,开始为1
	public void setStarexp(int _v_); // 法宝星阶经验
	public void setStarlevel(int _v_); // 法宝星阶等级,开始为1
	public void setWuxingtype(int _v_); // 法宝当前的五行属性类型
	public void setWuxingvalue(int _v_); // 五行属性攻击值
	public void setAwakelevel(int _v_); // 法宝觉醒等级,从0开始
}
