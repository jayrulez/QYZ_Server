
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XEnterMap__ extends xio.Protocol { }

/** 进出地图的协议
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XEnterMap extends __XEnterMap__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6702334;

	public int getType() {
		return 6702334;
	}

	public int ctxid;
	public long mapid;
	public map.msg.PlayerBuilder player;

	public XEnterMap() {
		player = new map.msg.PlayerBuilder();
	}

	public XEnterMap(int _ctxid_, long _mapid_, map.msg.PlayerBuilder _player_) {
		this.ctxid = _ctxid_;
		this.mapid = _mapid_;
		this.player = _player_;
	}

	public final boolean _validator_() {
		if (!player._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctxid);
		_os_.marshal(mapid);
		_os_.marshal(player);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctxid = _os_.unmarshal_int();
		mapid = _os_.unmarshal_long();
		player.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XEnterMap) {
			XEnterMap _o_ = (XEnterMap)_o1_;
			if (ctxid != _o_.ctxid) return false;
			if (mapid != _o_.mapid) return false;
			if (!player.equals(_o_.player)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ctxid;
		_h_ += (int)mapid;
		_h_ += player.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctxid).append(",");
		_sb_.append(mapid).append(",");
		_sb_.append(player).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

