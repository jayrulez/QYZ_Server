
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleMapInfo1 implements Marshal {
	public long mapid;
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;
	public int ridestatus;

	public RoleMapInfo1() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public RoleMapInfo1(long _mapid_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_, int _ridestatus_) {
		this.mapid = _mapid_;
		this.position = _position_;
		this.orient = _orient_;
		this.ridestatus = _ridestatus_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(mapid);
		_os_.marshal(position);
		_os_.marshal(orient);
		_os_.marshal(ridestatus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		mapid = _os_.unmarshal_long();
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		ridestatus = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleMapInfo1) {
			RoleMapInfo1 _o_ = (RoleMapInfo1)_o1_;
			if (mapid != _o_.mapid) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			if (ridestatus != _o_.ridestatus) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)mapid;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		_h_ += ridestatus;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(mapid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(ridestatus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

