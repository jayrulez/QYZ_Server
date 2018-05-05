
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XLeaveMap__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XLeaveMap extends __XLeaveMap__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6695623;

	public int getType() {
		return 6695623;
	}

	public int ctxid;
	public long roleid;
	public long mapid;
	public byte reserve;
	public int reason;

	public XLeaveMap() {
	}

	public XLeaveMap(int _ctxid_, long _roleid_, long _mapid_, byte _reserve_, int _reason_) {
		this.ctxid = _ctxid_;
		this.roleid = _roleid_;
		this.mapid = _mapid_;
		this.reserve = _reserve_;
		this.reason = _reason_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctxid);
		_os_.marshal(roleid);
		_os_.marshal(mapid);
		_os_.marshal(reserve);
		_os_.marshal(reason);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctxid = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		mapid = _os_.unmarshal_long();
		reserve = _os_.unmarshal_byte();
		reason = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XLeaveMap) {
			XLeaveMap _o_ = (XLeaveMap)_o1_;
			if (ctxid != _o_.ctxid) return false;
			if (roleid != _o_.roleid) return false;
			if (mapid != _o_.mapid) return false;
			if (reserve != _o_.reserve) return false;
			if (reason != _o_.reason) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ctxid;
		_h_ += (int)roleid;
		_h_ += (int)mapid;
		_h_ += (int)reserve;
		_h_ += reason;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctxid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(mapid).append(",");
		_sb_.append(reserve).append(",");
		_sb_.append(reason).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XLeaveMap _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ctxid - _o_.ctxid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(mapid - _o_.mapid);
		if (0 != _c_) return _c_;
		_c_ = reserve - _o_.reserve;
		if (0 != _c_) return _c_;
		_c_ = reason - _o_.reason;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

