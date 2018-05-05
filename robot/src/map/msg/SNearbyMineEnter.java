
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNearbyMineEnter__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNearbyMineEnter extends __SNearbyMineEnter__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684845;

	public int getType() {
		return 6684845;
	}

	public long agentid; // 进入视野的物体(玩家或怪物或道具等等)
	public int mineid;
	public long digger;
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;
	public int state;

	public SNearbyMineEnter() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public SNearbyMineEnter(long _agentid_, int _mineid_, long _digger_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_, int _state_) {
		this.agentid = _agentid_;
		this.mineid = _mineid_;
		this.digger = _digger_;
		this.position = _position_;
		this.orient = _orient_;
		this.state = _state_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agentid);
		_os_.marshal(mineid);
		_os_.marshal(digger);
		_os_.marshal(position);
		_os_.marshal(orient);
		_os_.marshal(state);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agentid = _os_.unmarshal_long();
		mineid = _os_.unmarshal_int();
		digger = _os_.unmarshal_long();
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		state = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNearbyMineEnter) {
			SNearbyMineEnter _o_ = (SNearbyMineEnter)_o1_;
			if (agentid != _o_.agentid) return false;
			if (mineid != _o_.mineid) return false;
			if (digger != _o_.digger) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			if (state != _o_.state) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)agentid;
		_h_ += mineid;
		_h_ += (int)digger;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		_h_ += state;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agentid).append(",");
		_sb_.append(mineid).append(",");
		_sb_.append(digger).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(state).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

