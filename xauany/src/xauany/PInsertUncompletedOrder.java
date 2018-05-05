package xauany;

public class PInsertUncompletedOrder extends xdb.Procedure{
	private String gsOrderid;
	
	private int serverid; // 
	private int plattype; // 
	private String platorderid; // 
	private long userid; //
	private byte [] vars; // 
	
	public PInsertUncompletedOrder(String gsOrderid, int serverid,
			int plattype, String platorderid, long userid, byte[] vars) {
		super();
		this.gsOrderid = gsOrderid;
		this.serverid = serverid;
		this.plattype = plattype;
		this.platorderid = platorderid;
		this.userid = userid;
		this.vars = vars;
	}
	
	@Override
	protected boolean process(){
		xbean.UncompletedOrderInfo uncompletedOrder = xbean.Pod.newUncompletedOrderInfo();
		uncompletedOrder.setServerid(serverid);
		uncompletedOrder.setPlattype(plattype);
		uncompletedOrder.setPlatorderid(platorderid);
		uncompletedOrder.setUserid(userid);
		uncompletedOrder.setVarsCopy(vars);
		uncompletedOrder.setTimes(1);
		
		boolean addSuccess = xtable.Uncompletedorderinfos.add(gsOrderid, uncompletedOrder);
		if(!addSuccess){
			xtable.Uncompletedorderinfos.remove(gsOrderid);
			xtable.Uncompletedorderinfos.insert(gsOrderid, uncompletedOrder);
		}
		
		return true;
	}

	public String getGsOrderid() {
		return gsOrderid;
	}
	
}
