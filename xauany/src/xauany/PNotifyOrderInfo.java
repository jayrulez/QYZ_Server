package xauany;

public class PNotifyOrderInfo extends xdb.Procedure{
	private String gsOrderid;
	
	private int serverid;
	private gnet.NotifyOrderInfo protocol;
	private int times;
	
	public PNotifyOrderInfo(String gsOrderid) {
		super();
		this.gsOrderid = gsOrderid;
	}

	@Override
	protected boolean process(){
		xbean.UncompletedOrderInfo uncompletedOrder =  xtable.Uncompletedorderinfos.get(gsOrderid);
		if(uncompletedOrder == null){
			xdb.Trace.warn("xtable.Uncompletedorderinfos.get(gsOrderid) == null. gsOrderid = " + gsOrderid);
			return false;
		}
		//incr times
		uncompletedOrder.setTimes(uncompletedOrder.getTimes() + 1);
		
		protocol = new gnet.NotifyOrderInfo();
		protocol.plattype.plat = uncompletedOrder.getPlattype();
		protocol.platorderid = uncompletedOrder.getPlatorderid();
		protocol.gsorderid = gsOrderid;
		protocol.userid = uncompletedOrder.getUserid();
		protocol.vars.replace(uncompletedOrder.getVarsCopy());
		
		
		serverid = uncompletedOrder.getServerid();
		times = uncompletedOrder.getTimes();
		
		return true;
	}

	public int getServerid() {
		return serverid;
	}

	public gnet.NotifyOrderInfo getProtocol() {
		return protocol;
	}

	public int getTimes() {
		return times;
	}
	
	
}
