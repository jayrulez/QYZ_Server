package gnet;

import java.util.HashMap;
import java.util.Map;

import com.goldhuman.Common.Marshal.MarshalException;

public class PlatUtils {
	
	private PlatUtils(){
		
	}
	
	/**
	 * @param octetsvars
	 * @return
	 * @throws MarshalException
	 */
	public static Map<String, String> unmarshalMap(com.goldhuman.Common.Octets octetsvars) throws MarshalException{
		Map<String, String> vars = new HashMap<>();
		final com.goldhuman.Common.Marshal.OctetsStream os = com.goldhuman.Common.Marshal.OctetsStream.wrap(octetsvars);
		int size = os.uncompact_uint32();
		for(int i = 0; i < size; i++){
			String k = os.unmarshal_String(xdb.Const.IO_CHARSET);
			String v = os.unmarshal_String(xdb.Const.IO_CHARSET);
			
			vars.put(k, v);
		}
		
		return vars;
	}
	
	
	public static com.goldhuman.Common.Octets marshalVars( Map<String,String> vars){
		final com.goldhuman.Common.Marshal.OctetsStream os = new com.goldhuman.Common.Marshal.OctetsStream();
		os.compact_uint32(vars.size());
		for (java.util.Map.Entry<java.lang.String, java.lang.String> _e_ : vars.entrySet()) {
			os.marshal(_e_.getKey(), "UTF-16LE");
			os.marshal(_e_.getValue(), "UTF-16LE");
		}
		return os;
	}
	
}
