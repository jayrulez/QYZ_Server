
package lx.gs.mount;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import common.ErrorCode;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CDeActiveRide__ extends xio.Protocol { }

/** 卸下坐骑
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CDeActiveRide extends lx.gs.mount.__CDeActiveRide__ {
	@Override
	protected void process() {
		new AProcedure<CDeActiveRide>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if(!FRide.isRideModuleUnlock(roleid)){
					return error(ErrorCode.RIDE_MODULE_LOCKED);
				}
				xbean.RoleRide info = FRide.getRoleRide(roleid);
				int oldId = info.getActiveride();
				if(!info.getRides().containsKey(oldId)){
					return error(ErrorCode.RIDE_NOT_GET);
				}
				info.setActiveride(0);
				response(new SDeActiveRide());
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6585681;

	public int getType() {
		return 6585681;
	}


	public CDeActiveRide() {
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
		if (_o1_ instanceof CDeActiveRide) {
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

	public int compareTo(CDeActiveRide _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

