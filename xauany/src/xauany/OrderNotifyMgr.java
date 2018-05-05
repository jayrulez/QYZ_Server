package xauany;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public enum OrderNotifyMgr {
	INSTANCE;
	
	private final ConcurrentMap<String, ScheduledFuture<?>> futureMap = new ConcurrentHashMap<>();
	
	public void putNotifyFuture(String gsOrderid, ScheduledFuture<?> future){
		futureMap.put(gsOrderid, future);
	}
	
	public void notifyOrderInfo2Gs(final String gsOrderid){
		if(xdb.Trace.isDebugEnabled()){
			xdb.Trace.debug("notifyOrderInfo2Gs. gsOrderid = " + gsOrderid);
		}
		PNotifyOrderInfo notifyOrder = new PNotifyOrderInfo(gsOrderid);
		if(notifyOrder.call()){
			// notify gs
			XioManager.getInstance().sendProtocol(notifyOrder.getServerid(), notifyOrder.getProtocol());
			
			//前10次没分钟通知一次，超过10次以后没1个小时通知一次
			long notifyInterval = TimeUnit.MINUTES.toMillis(1);
			if(notifyOrder.getTimes() > 10){
				notifyInterval = TimeUnit.HOURS.toMillis(1);
			}
			
			//一段时间后重新执行该通知任务
			ScheduledFuture<?> future = xdb.Executor.getInstance().schedule(
					new NotifyOrderInfo2Gs(gsOrderid), notifyInterval, TimeUnit.MILLISECONDS);
			
			futureMap.put(gsOrderid, future);
		}
	}
	
	private class NotifyOrderInfo2Gs implements Runnable{
		final String gsOrderid;

		public NotifyOrderInfo2Gs(String gsOrderid) {
			super();
			this.gsOrderid = gsOrderid;
		}
		
		@Override
		public void run(){
			OrderNotifyMgr.INSTANCE.notifyOrderInfo2Gs(gsOrderid);
		}
	}
	
	/**
	 * 停止发送消息
	 */
	public void notifyOrderInfoAck(gnet.NotifyOrderInfoAck orderAck){
		String gsOrderid = orderAck.gsorderid;
		ScheduledFuture<?> future = futureMap.remove(gsOrderid);
		if(future != null){
			future.cancel(false);
		}
		
		new xdb.Procedure(){
			
			@Override
			protected boolean process(){
				xbean.UncompletedOrderInfo order = xtable.Uncompletedorderinfos.get(gsOrderid);
				if (order != null) {
					xtable.Uncompletedorderinfos.remove(gsOrderid);
					
					if (orderAck.errcode != gnet.NotifyOrderInfoAck.ERR_SUCCEED) {
						//记录错误信息
						xbean.GsdErrorOrderInfo errorOrder = xbean.Pod.newGsdErrorOrderInfo();
						errorOrder.getOrder().setServerid(order.getServerid());
						errorOrder.getOrder().setPlattype(order.getPlattype());
						errorOrder.getOrder().setPlatorderid(order.getPlatorderid());
						errorOrder.getOrder().setUserid(order.getUserid());
						errorOrder.getOrder().setTimes(order.getTimes());
						errorOrder.setReason(orderAck.errcode);
						
						xtable.Gsderrororderinfos.add(gsOrderid, errorOrder);
					}
				}
				
				return true;
			}
		}.call();
	}
	
	/**
	 * 服务器启动后遍历有完成的订单，通知gs
	 */
	public void onServerStartup(){
		xtable.Uncompletedorderinfos.getTable().walk((k, v) -> {
            //延迟1~2分钟发送，不直接发送是因为这时还没有和deliver连上；随机是为了避免集中发送消息
            long delay = TimeUnit.MINUTES.toMillis(1) + xdb.Xdb.random().nextInt((int)TimeUnit.MINUTES.toMillis(1));
            xdb.Executor.getInstance().schedule(new NotifyOrderInfo2Gs(k), delay, TimeUnit.MILLISECONDS);

            return true;
        });
	}
}
