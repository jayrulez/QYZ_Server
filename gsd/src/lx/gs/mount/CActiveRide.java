
package lx.gs.mount;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gs.AProcedure;
import common.ErrorCode;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CActiveRide__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CActiveRide extends lx.gs.mount.__CActiveRide__ {
	@Override
	protected void process() {
		new AProcedure<CActiveRide>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				if(!FRide.isRideModuleUnlock(roleid)){
					return error(ErrorCode.RIDE_MODULE_LOCKED);
				}
				xbean.RoleRide info = FRide.getRoleRide(roleid);
				if(!info.getRides().containsKey(ridekey)){
					return error(ErrorCode.RIDE_NOT_GET);
				}
				info.setActiveride(ridekey);
				response(new SActiveRide(ridekey));
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555782;

	public int getType() {
		return 6555782;
	}

	public int ridekey; // 要使用的坐骑的key

	public CActiveRide() {
	}

	public CActiveRide(int _ridekey_) {
		this.ridekey = _ridekey_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ridekey);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ridekey = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CActiveRide) {
			CActiveRide _o_ = (CActiveRide)_o1_;
			if (ridekey != _o_.ridekey) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ridekey;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ridekey).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(CActiveRide _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ridekey - _o_.ridekey;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

