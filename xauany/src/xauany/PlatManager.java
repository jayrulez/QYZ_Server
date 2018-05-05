package xauany;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;

import java.rmi.UnmarshalException;
import java.util.HashMap;
import java.util.Map;

public class PlatManager {
	private static final Map<Integer, PlatProcess> TYPE_2_PROCESS_MAP = new HashMap<Integer, PlatProcess>();
	private static final Map<String, PlatProcess> PATH_2_PROCESS_MAP = new HashMap<String, PlatProcess>();
	
	public static PlatProcess getPlatProcess(int plattype) {
		return TYPE_2_PROCESS_MAP.get(plattype);
	}
	
	public static PlatProcess getPlatProcessByPath(String path){
		return PATH_2_PROCESS_MAP.get(path);
	}
	
	private static PlatProcess getPlatProcessByXio(xio.Xio xio) {
		final XioManager.XioInfo info = XioManager.getInstance().getXioInfo( xio);
		if(null == info){
			xdb.Trace.info("getPlatProcessByXio xio = " + xio + " lost XioInfo");
			return null;
		}
		return TYPE_2_PROCESS_MAP.get(info.getPlattype());
	}
	
	public static void registerPlatProcess(PlatProcess process) {
		final String path = process.getOrderCallbackPath();
		if(null != path && !path.isEmpty()){
			if(PATH_2_PROCESS_MAP.containsKey(path)){
				throw new IllegalArgumentException("Duplicate path in PlatManager. path = " + path);
			}
			PATH_2_PROCESS_MAP.put( path, process);
		}
		int plattype = process.getType();
		if(TYPE_2_PROCESS_MAP.containsKey(plattype)){
			throw new IllegalArgumentException("Duplicate plattype in PlatManager. plattype = " + plattype);
		}
		TYPE_2_PROCESS_MAP.put(plattype, process);
		
		if(xdb.Trace.isInfoEnabled()){
			xdb.Trace.info("PlatProcess registed. " + process);
		}
	}

	public static boolean containsPlatType(int plattype) {
		return TYPE_2_PROCESS_MAP.containsKey(plattype);
	}
	
	/**
	 * ƽ̨�û���½
	 * @param xio
	 * @param arg
	 * @param res
	 */
	public static void onAuAnyLogin(xio.Xio xio, gnet.AuAnyLoginArg arg, gnet.AuAnyLoginRes res) {
		final PlatProcess process = getPlatProcessByXio(xio);
		if (process != null){
			process.login(arg, res);
		}
	}
	
	public static com.goldhuman.Common.Octets marshalVars(Map<String,String> vars) {
		final com.goldhuman.Common.Marshal.OctetsStream os = new com.goldhuman.Common.Marshal.OctetsStream();
		os.compact_uint32(vars.size());
		for (java.util.Map.Entry<java.lang.String, java.lang.String> _e_ : vars.entrySet()) {
			os.marshal(_e_.getKey(), "UTF-16LE");
			os.marshal(_e_.getValue(), "UTF-16LE");
		}
		return os;
	}

	public static Map<String, String> unmarshalVars(Octets data) throws MarshalException {
	    final Map<String, String> var = new HashMap<>();
	    final OctetsStream os = OctetsStream.wrap(data);
        for(int n = os.uncompact_uint32() ; n > 0 ; n--) {
            var.put(os.unmarshal_String("UTF-16LE"), os.unmarshal_String("UTF-16LE"));
        }
        return var;
    }
}
