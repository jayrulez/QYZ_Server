
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNewFloorOpen__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNewFloorOpen extends __SNewFloorOpen__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6703937;

	public int getType() {
		return 6703937;
	}

	public int floorid;

	public SNewFloorOpen() {
	}

	public SNewFloorOpen(int _floorid_) {
		this.floorid = _floorid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(floorid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		floorid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNewFloorOpen) {
			SNewFloorOpen _o_ = (SNewFloorOpen)_o1_;
			if (floorid != _o_.floorid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += floorid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(floorid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SNewFloorOpen _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = floorid - _o_.floorid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

