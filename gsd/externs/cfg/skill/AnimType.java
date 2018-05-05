package cfg.skill;
public final class AnimType extends cfg.CfgObject {
	public final static int TYPEID = -1230360878;
	final public int getTypeId() { return TYPEID; }
	public static final String AnyState = "任何状态";
	public static final String Stand = "站立";
	public static final String Run = "奔跑";
	public static final String Jump = "跳跃";
	public static final String JumpLoop = "跳跃中";
	public static final String JumpEnd = "跳跃结束";
	public static final String Walk = "走";
	public static final String StandFight = "战斗站立";
	public static final String RunFight = "战斗奔跑";
	public static final String JumpFight = "战斗跳跃";
	public static final String JumpLoopFight = "战斗跳跃中";
	public static final String JumpEndFight = "战斗跳跃结束";
	public static final String PathFlyStart = "轨迹飞行开始";
	public static final String PathFlyLoop = "轨迹飞行中";
	public static final String PathFlyEnd = "轨迹飞行结束";
	public static final String Idle = "空闲";
	public static final String GetUp = "起身";
	public static final String StandRide = "骑行站立";
	public static final String RunRide = "骑行中";
	public static final String StandFly = "飞行";
	public static final String Fly = "飞行中";
	public static final String PullSword = "拔出武器";
	public static final String Inlayersword = "收起武器";
	public static final String Born = "出生";
	public static final String Hit = "被击";
	public static final String Stun = "被击晕";
	public static final String Float = "被击飞";
	public static final String Mining = "采矿";
	public static final String Dying = "死亡";
	public static final String Death = "已死亡";
	public static final String BindEffect = "绑定特效";
	public static final String BindFlyEffect = "飞行特效";
	public static final String TransformEnd = "变身结束";
	public static final String FleshRecasting = "血肉重铸";
	public AnimType(cfg.DataStream fs) {
	}
}