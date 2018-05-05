package apns;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javapns.notification.PushNotificationPayload;

public enum ApnsManager {
	INSTANCE;
	
	public void init(String apnsCfgFile) throws IOException{
		ApnsCfg.init(apnsCfgFile);
		
		ApnsThreadPool.INSTANCE.start();
	}
	
	public void saveDeviceToken(int userid, final String deviceToken){
		FDeviceToken.save(userid, deviceToken);
	}
	
	/**
	 * 发送推送消息
	 * @param userids
	 * @param msg
	 * @param badge
	 * @param priority
	 */
	public void sendMsg(Collection<Long> userids, String msg, byte badge, byte priority){
		Set<String> deviceTokens = FDeviceToken.multiGet(userids);
		
		PushNotificationPayload payload = PushNotificationPayload.complex();
		payload.addAlert(msg);
		payload.addBadge(badge);
		
		sendMsg(deviceTokens.toArray(new String[deviceTokens.size()]), payload, priority);
	}
	
	/**
	 * 根据devicetoke将消息发送出去
	 * @param devicetokens
	 * @param msg
	 * @param badge
	 * @param priority
	 */
	private void sendMsg(String[] devicetokens, PushNotificationPayload payload, byte priority){
		ApnsSendMsg sendMsg = new ApnsSendMsg(devicetokens, payload);
		ApnsThreadPool.INSTANCE.sendApnsMsg(sendMsg, priority);
	}
	
	public void sendAllMsg(String msg, byte badge, byte priority){
		PushNotificationPayload payload = PushNotificationPayload.complex();
		payload.addAlert(msg);
		payload.addBadge(badge);
		
		Set<String> deviceTokens = FDeviceToken.getAll();
		
		int sendPerNum = ApnsCfg.getSendAllPerNum();
		String[] tmpTokens = new String[sendPerNum];
		int tempIndex = 0;
		for(String deviceToken : deviceTokens){
			tmpTokens[tempIndex++] = deviceToken;
			if(tempIndex == sendPerNum - 1){
				sendMsg(tmpTokens, payload, priority);
				
				tmpTokens = new String[sendPerNum];
				tempIndex = 0;
			}
		}
		
		if(tempIndex > 0){
			String[] finalTokens = new String[tempIndex];
			System.arraycopy(tmpTokens, 0, finalTokens, 0, finalTokens.length);
			
			sendMsg(finalTokens, payload, priority);
		}
	}
}
