package apns;

import java.util.concurrent.BlockingQueue;

import javapns.Push;
import javapns.notification.PushedNotification;
import javapns.notification.PushedNotifications;
import xdb.ThreadHelper;

public class ApnsThread extends ThreadHelper{
	private BlockingQueue<ApnsSendMsg> sharedMsgQ;
	
	public ApnsThread(String name, BlockingQueue<ApnsSendMsg> msgQ){
		super(name);
		this.sharedMsgQ = msgQ;
	}
	
	@Override
	public void run(){
		while(isRunning()) {
			try {
				ApnsSendMsg msg = sharedMsgQ.take();
				if( xdb.Trace.isDebugEnabled()) {
					xdb.Trace.debug("ApnsWorker send -> ApnsMsg = " + msg);
				}
				long startTime = System.currentTimeMillis();
				PushedNotifications pns = Push.payload(msg.getPayload(), ApnsCfg.getKeystore(), ApnsCfg.getPassword(), 
						ApnsCfg.isProduction(), ApnsCfg.getSendThreadNum(), msg.getDeviceTokens());
				long endTime = System.currentTimeMillis();
				if(endTime - startTime > 60 * 1000) {
					xdb.Trace.warn("apns wait too long time : " + (endTime - startTime) + " ms; deviceTokens.size = " + msg.getDeviceTokens().length);
				}
				if( xdb.Trace.isDebugEnabled()) {
					for(PushedNotification pn : pns) {
						xdb.Trace.debug("ApnsManager sendAll result:" + pn.isSuccessful() + ";Exception:" + pn.getException());
					}
				}
			}
			catch(InterruptedException ie){
				continue;
			}
			catch(Throwable e) {
				e.printStackTrace();
			}
		}
	}
}
