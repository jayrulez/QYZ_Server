package apns;

import java.util.Arrays;

import javapns.notification.PushNotificationPayload;

public class ApnsSendMsg{
	private final String[] deviceTokens;
	private final PushNotificationPayload payload;
	
	public ApnsSendMsg(String[] deviceTokens, PushNotificationPayload payload) {
		super();
		this.deviceTokens = deviceTokens;
		this.payload = payload;
	}

	public String[] getDeviceTokens() {
		return deviceTokens;
	}

	public PushNotificationPayload getPayload() {
		return payload;
	}
	
	@Override
	public String toString() {
		return "payload->" + payload + ";tokens->" + Arrays.asList(deviceTokens);
	}
	
}
