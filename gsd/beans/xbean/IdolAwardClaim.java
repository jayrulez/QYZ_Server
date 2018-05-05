
package xbean;

public interface IdolAwardClaim extends xdb.Bean {
	public IdolAwardClaim copy(); // deep clone
	public IdolAwardClaim toData(); // a Data instance
	public IdolAwardClaim toBean(); // a Bean instance
	public IdolAwardClaim toDataIf(); // a Data instance If need. else return this
	public IdolAwardClaim toBeanIf(); // a Bean instance If need. else return this

	public java.util.Set<Integer> getClaiminfo(); // 领取状况
	public java.util.Set<Integer> getClaiminfoAsData(); // 领取状况

}
