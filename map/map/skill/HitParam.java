package map.skill;

public final class HitParam {
	public HitParam(AttackInfo ctx, int hurt) {
		this.ctx = ctx;
		this.hurt = hurt;
	}
	
	public final AttackInfo ctx;
	public final int hurt;
}
