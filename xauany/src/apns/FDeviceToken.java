package apns;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 
 *
 */
public class FDeviceToken {
	
	/**
	 * 保存deviceToken
	 * @param userid
	 * @param deviceToken
	 */
	public static void save(long userid, final String deviceToken){
		if( xdb.Trace.isDebugEnabled()) {
			xdb.Trace.debug("ApnsManager saveDeviceToken userid = " + userid + ", deviceToken" + deviceToken);
		}
		
		final String token = deviceToken.replace(" ", "").replace("<", "").replace(">", "");
		new xdb.Procedure(){
			protected boolean process() {
				try {
					if(null != xtable.Apnsdevices.select(userid)) {
						xtable.Apnsdevices.remove(userid);
					}
					xbean.ApnsDevice device = xbean.Pod.newApnsDevice();
					device.setUpdatetime(System.currentTimeMillis());
					device.setToken(token);
					xtable.Apnsdevices.insert(userid, device);
				} catch(Exception e) {
					e.printStackTrace();
				}
				return true;
			}
        }.call();
	}
	
	/**
	 * 通过userid查找devicetoken
	 * @param userid
	 * @return
	 */
	public static String get(final long userid){
		TokenGet get = new TokenGet(userid);
		
		get.call();
		
		return get.token;
	}
	
	public static Set<String> multiGet(Collection<Long> userids){
		Set<String> tokens = new HashSet<String>();
		for(long userid : userids){
			String token = get(userid);
			if(token != null && !token.isEmpty()){
				tokens.add(token);
			}
		}
		
		return tokens;
	}
	
	private static class TokenGet extends xdb.Procedure{
		private final long userid;
		private String token;
		
		TokenGet(long userid){
			this.userid = userid;
		}
		
		@Override
		protected boolean process(){
			xbean.ApnsDevice device = xtable.Apnsdevices.select(userid);
			if(null != device) {
				if(isExpired(device)) {
					xtable.Apnsdevices.remove(userid);
				}
				else{
					token = device.getToken();
				}
			}
			
			return true;
		}
	}
	
	private static boolean isExpired(xbean.ApnsDevice device) {
		//1年过期
		return System.currentTimeMillis() - device.getUpdatetime() > 365 * TimeUnit.DAYS.toMillis(1);
	}
	
	public static Set<String> getAll(){
		Set<String> tokens = new HashSet<String>();
		xtable.Apnsdevices.getTable().walk(new xdb.TTable.IWalk<Long, xbean.ApnsDevice>(){

			@Override
			public boolean onRecord(Long k, xbean.ApnsDevice v) {
				if(!isExpired(v)){
					tokens.add(v.getToken());
				}
				
				return true;
			}
			
		});
		
		return tokens;
	}
}
