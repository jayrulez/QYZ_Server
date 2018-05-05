
package lx.gs.activity.worldmonster.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetExpMonsterPosition__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetExpMonsterPosition extends __SGetExpMonsterPosition__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6586010;

	public int getType() {
		return 6586010;
	}

	public int mapid;
	public float xposition;
	public float zposition;

	public SGetExpMonsterPosition() {
	}

	public SGetExpMonsterPosition(int _mapid_, float _xposition_, float _zposition_) {
		this.mapid = _mapid_;
		this.xposition = _xposition_;
		this.zposition = _zposition_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mapid);
		_os_.marshal(xposition);
		_os_.marshal(zposition);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mapid = _os_.unmarshal_int();
		xposition = _os_.unmarshal_float();
		zposition = _os_.unmarshal_float();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetExpMonsterPosition) {
			SGetExpMonsterPosition _o_ = (SGetExpMonsterPosition)_o1_;
			if (mapid != _o_.mapid) return false;
			if (xposition != _o_.xposition) return false;
			if (zposition != _o_.zposition) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += mapid;
		_h_ += Float.floatToIntBits(xposition);
		_h_ += Float.floatToIntBits(zposition);
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid).append(",");
		_sb_.append(xposition).append(",");
		_sb_.append(zposition).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

