
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class AgentBuilder implements Marshal {
	public long agentid;
	public int atype;
	public int subtype;
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;
	public float bodyheight;
	public float bodyradius;

	public AgentBuilder() {
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public AgentBuilder(long _agentid_, int _atype_, int _subtype_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_, float _bodyheight_, float _bodyradius_) {
		this.agentid = _agentid_;
		this.atype = _atype_;
		this.subtype = _subtype_;
		this.position = _position_;
		this.orient = _orient_;
		this.bodyheight = _bodyheight_;
		this.bodyradius = _bodyradius_;
	}

	public final boolean _validator_() {
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agentid);
		_os_.marshal(atype);
		_os_.marshal(subtype);
		_os_.marshal(position);
		_os_.marshal(orient);
		_os_.marshal(bodyheight);
		_os_.marshal(bodyradius);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agentid = _os_.unmarshal_long();
		atype = _os_.unmarshal_int();
		subtype = _os_.unmarshal_int();
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		bodyheight = _os_.unmarshal_float();
		bodyradius = _os_.unmarshal_float();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AgentBuilder) {
			AgentBuilder _o_ = (AgentBuilder)_o1_;
			if (agentid != _o_.agentid) return false;
			if (atype != _o_.atype) return false;
			if (subtype != _o_.subtype) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			if (bodyheight != _o_.bodyheight) return false;
			if (bodyradius != _o_.bodyradius) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)agentid;
		_h_ += atype;
		_h_ += subtype;
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		_h_ += Float.floatToIntBits(bodyheight);
		_h_ += Float.floatToIntBits(bodyradius);
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agentid).append(",");
		_sb_.append(atype).append(",");
		_sb_.append(subtype).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(bodyheight).append(",");
		_sb_.append(bodyradius).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

