
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MCreateMap__ extends xio.Protocol { }

/** 创建地图的协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MCreateMap extends __MCreateMap__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6703610;

	public int getType() {
		return 6703610;
	}

	public int retcode;
	public long mapid;

	public MCreateMap() {
	}

	public MCreateMap(int _retcode_, long _mapid_) {
		this.retcode = _retcode_;
		this.mapid = _mapid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(retcode);
		_os_.marshal(mapid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		retcode = _os_.unmarshal_int();
		mapid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MCreateMap) {
			MCreateMap _o_ = (MCreateMap)_o1_;
			if (retcode != _o_.retcode) return false;
			if (mapid != _o_.mapid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += retcode;
		_h_ += (int)mapid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(retcode).append(",");
		_sb_.append(mapid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(MCreateMap _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = retcode - _o_.retcode;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(mapid - _o_.mapid);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

