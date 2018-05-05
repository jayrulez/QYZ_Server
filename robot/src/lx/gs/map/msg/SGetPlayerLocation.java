
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetPlayerLocation__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetPlayerLocation extends __SGetPlayerLocation__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6579601;

	public int getType() {
		return 6579601;
	}

	public long roleid;
	public int maptype;
	public int worldid;
	public int lineid;
	public map.msg.Vector3 position;

	public SGetPlayerLocation() {
		position = new map.msg.Vector3();
	}

	public SGetPlayerLocation(long _roleid_, int _maptype_, int _worldid_, int _lineid_, map.msg.Vector3 _position_) {
		this.roleid = _roleid_;
		this.maptype = _maptype_;
		this.worldid = _worldid_;
		this.lineid = _lineid_;
		this.position = _position_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(roleid);
		_os_.marshal(maptype);
		_os_.marshal(worldid);
		_os_.marshal(lineid);
		_os_.marshal(position);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		roleid = _os_.unmarshal_long();
		maptype = _os_.unmarshal_int();
		worldid = _os_.unmarshal_int();
		lineid = _os_.unmarshal_int();
		position.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetPlayerLocation) {
			SGetPlayerLocation _o_ = (SGetPlayerLocation)_o1_;
			if (roleid != _o_.roleid) return false;
			if (maptype != _o_.maptype) return false;
			if (worldid != _o_.worldid) return false;
			if (lineid != _o_.lineid) return false;
			if (!position.equals(_o_.position)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)roleid;
		_h_ += maptype;
		_h_ += worldid;
		_h_ += lineid;
		_h_ += position.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid).append(",");
		_sb_.append(maptype).append(",");
		_sb_.append(worldid).append(",");
		_sb_.append(lineid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

