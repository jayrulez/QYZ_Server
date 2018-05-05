
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNearbyMonsterEnter__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNearbyMonsterEnter extends __SNearbyMonsterEnter__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6684844;

	public int getType() {
		return 6684844;
	}

	public long agentid; // 进入视野的物体(玩家或怪物或道具等等)
	public int monsterid;
	public map.msg.FighterCommon fightercommon;

	public SNearbyMonsterEnter() {
		fightercommon = new map.msg.FighterCommon();
	}

	public SNearbyMonsterEnter(long _agentid_, int _monsterid_, map.msg.FighterCommon _fightercommon_) {
		this.agentid = _agentid_;
		this.monsterid = _monsterid_;
		this.fightercommon = _fightercommon_;
	}

	public final boolean _validator_() {
		if (!fightercommon._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agentid);
		_os_.marshal(monsterid);
		_os_.marshal(fightercommon);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agentid = _os_.unmarshal_long();
		monsterid = _os_.unmarshal_int();
		fightercommon.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNearbyMonsterEnter) {
			SNearbyMonsterEnter _o_ = (SNearbyMonsterEnter)_o1_;
			if (agentid != _o_.agentid) return false;
			if (monsterid != _o_.monsterid) return false;
			if (!fightercommon.equals(_o_.fightercommon)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)agentid;
		_h_ += monsterid;
		_h_ += fightercommon.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agentid).append(",");
		_sb_.append(monsterid).append(",");
		_sb_.append(fightercommon).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

