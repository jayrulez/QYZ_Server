
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SCreateWorldBoss__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SCreateWorldBoss extends __SCreateWorldBoss__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6564692;

	public int getType() {
		return 6564692;
	}

	public int worldbossid;
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;

	public SCreateWorldBoss() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public SCreateWorldBoss(int _worldbossid_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_) {
		this.worldbossid = _worldbossid_;
		this.position = _position_;
		this.orient = _orient_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
        return orient._validator_();
    }

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(worldbossid);
		_os_.marshal(position);
		_os_.marshal(orient);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		worldbossid = _os_.unmarshal_int();
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SCreateWorldBoss) {
			SCreateWorldBoss _o_ = (SCreateWorldBoss)_o1_;
			if (worldbossid != _o_.worldbossid) return false;
			if (!position.equals(_o_.position)) return false;
            return orient.equals(_o_.orient);
        }
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += worldbossid;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(worldbossid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

