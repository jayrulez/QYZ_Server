package xauany.code;

import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import xauany.Config;
import xauany.Main;
import xauany.PNewUser;
import xauany.utils.Utils;

public class FCode {
	public static final int CODE_TYPE_LOGIN = -1;

	public static int getUserPlatformByUserid(long userid) {
        return getUserPlatformByXdbuserid(PNewUser.toXdbUserID(userid));
    }

    public static int getUserPlatformByXdbuserid(long xdbuserid) {
        String useridentity = xtable.Users.selectUseridentity(xdbuserid);
        String[] platform_identity_pair = useridentity.split("_");
        if(platform_identity_pair.length == 2){
            try{
                return Integer.parseInt(platform_identity_pair[0]);
            }catch(Exception e){
                xdb.Trace.error(e);
            }
        }
        return 0;
    }
	
	public static boolean isUseActivationCodeLogin(int platform){
        final Config config = Config.getInstance();
        if(!config.isActiveCodeLogin())
            return false;
        if(!config.getActiveCodeIncludePlatforms().isEmpty())
            return config.getActiveCodeIncludePlatforms().contains(platform);
        else
            return !config.getActiveCodeExcludePlatforms().contains(platform);
	}
	
	
	public static xbean.GlobalActivationCode getGlobalActivationCode(){
		xbean.GlobalActivationCode globalActivationCode = xtable.Globalactivationcodes.get(0);
		if(globalActivationCode == null){
			globalActivationCode = xbean.Pod.newGlobalActivationCode();
			xtable.Globalactivationcodes.insert(0, globalActivationCode);
		}
		
		return globalActivationCode;
	}
}
