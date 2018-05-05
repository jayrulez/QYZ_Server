
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SMonsterDrop__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SMonsterDrop extends __SMonsterDrop__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6707094;

	public int getType() {
		return 6707094;
	}

	public long owner;
	public map.msg.Bonus bonus;
	public map.msg.Vector3 position;
	public map.msg.Vector3 orient;

	public SMonsterDrop() {
		bonus = new map.msg.Bonus();
		position = new map.msg.Vector3();
		orient = new map.msg.Vector3();
	}

	public SMonsterDrop(long _owner_, map.msg.Bonus _bonus_, map.msg.Vector3 _position_, map.msg.Vector3 _orient_) {
		this.owner = _owner_;
		this.bonus = _bonus_;
		this.position = _position_;
		this.orient = _orient_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		if (!position._validator_()) return false;
		if (!orient._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(owner);
		_os_.marshal(bonus);
		_os_.marshal(position);
		_os_.marshal(orient);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		owner = _os_.unmarshal_long();
		bonus.unmarshal(_os_);
		position.unmarshal(_os_);
		orient.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SMonsterDrop) {
			SMonsterDrop _o_ = (SMonsterDrop)_o1_;
			if (owner != _o_.owner) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!position.equals(_o_.position)) return false;
			if (!orient.equals(_o_.orient)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)owner;
		_h_ += bonus.hashCode();
		_h_ += position.hashCode();
		_h_ += orient.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(owner).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(position).append(",");
		_sb_.append(orient).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

