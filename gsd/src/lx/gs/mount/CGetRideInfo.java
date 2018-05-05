
package lx.gs.mount;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import xbean.RoleRide;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetRideInfo__ extends xio.Protocol { }

/** 获取
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetRideInfo extends lx.gs.mount.__CGetRideInfo__ {
	@Override
	protected void process() {
		new AProcedure<CGetRideInfo>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				SGetRideInfo result = new SGetRideInfo();
				RoleRide roleRide = FRide.getRoleRide(roleid);
				result.activeride = roleRide.getActiveride();
				roleRide.getRides().values().forEach(ride -> result.rideinfo.add(FRide.convert(ride)));
				response(result);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555780;

	public int getType() {
		return 6555780;
	}


	public CGetRideInfo() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetRideInfo) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CGetRideInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

