
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterWorld__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterWorld extends __SEnterWorld__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6714464;

	public int getType() {
		return 6714464;
	}

	public long mapid;
	public int worldid;
	public int lineid;

	public SEnterWorld() {
	}

	public SEnterWorld(long _mapid_, int _worldid_, int _lineid_) {
		this.mapid = _mapid_;
		this.worldid = _worldid_;
		this.lineid = _lineid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mapid);
		_os_.marshal(worldid);
		_os_.marshal(lineid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mapid = _os_.unmarshal_long();
		worldid = _os_.unmarshal_int();
		lineid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterWorld) {
			SEnterWorld _o_ = (SEnterWorld)_o1_;
			if (mapid != _o_.mapid) return false;
			if (worldid != _o_.worldid) return false;
			if (lineid != _o_.lineid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)mapid;
		_h_ += worldid;
		_h_ += lineid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid).append(",");
		_sb_.append(worldid).append(",");
		_sb_.append(lineid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SEnterWorld _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(mapid - _o_.mapid);
		if (0 != _c_) return _c_;
		_c_ = worldid - _o_.worldid;
		if (0 != _c_) return _c_;
		_c_ = lineid - _o_.lineid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

