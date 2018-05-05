
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SNearbyPetEnter__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SNearbyPetEnter extends __SNearbyPetEnter__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6712006;

	public int getType() {
		return 6712006;
	}

	public long agentid; // 进入视野的物体(玩家或怪物或道具等等)
	public int petkey;
	public long owenrid;
	public int level;
	public int starlevel;
	public int awakelevel;
	public int skinid;
	public map.msg.FighterCommon fightercommon;

	public SNearbyPetEnter() {
		fightercommon = new map.msg.FighterCommon();
	}

	public SNearbyPetEnter(long _agentid_, int _petkey_, long _owenrid_, int _level_, int _starlevel_, int _awakelevel_, int _skinid_, map.msg.FighterCommon _fightercommon_) {
		this.agentid = _agentid_;
		this.petkey = _petkey_;
		this.owenrid = _owenrid_;
		this.level = _level_;
		this.starlevel = _starlevel_;
		this.awakelevel = _awakelevel_;
		this.skinid = _skinid_;
		this.fightercommon = _fightercommon_;
	}

	public final boolean _validator_() {
		if (!fightercommon._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(agentid);
		_os_.marshal(petkey);
		_os_.marshal(owenrid);
		_os_.marshal(level);
		_os_.marshal(starlevel);
		_os_.marshal(awakelevel);
		_os_.marshal(skinid);
		_os_.marshal(fightercommon);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		agentid = _os_.unmarshal_long();
		petkey = _os_.unmarshal_int();
		owenrid = _os_.unmarshal_long();
		level = _os_.unmarshal_int();
		starlevel = _os_.unmarshal_int();
		awakelevel = _os_.unmarshal_int();
		skinid = _os_.unmarshal_int();
		fightercommon.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SNearbyPetEnter) {
			SNearbyPetEnter _o_ = (SNearbyPetEnter)_o1_;
			if (agentid != _o_.agentid) return false;
			if (petkey != _o_.petkey) return false;
			if (owenrid != _o_.owenrid) return false;
			if (level != _o_.level) return false;
			if (starlevel != _o_.starlevel) return false;
			if (awakelevel != _o_.awakelevel) return false;
			if (skinid != _o_.skinid) return false;
			if (!fightercommon.equals(_o_.fightercommon)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)agentid;
		_h_ += petkey;
		_h_ += (int)owenrid;
		_h_ += level;
		_h_ += starlevel;
		_h_ += awakelevel;
		_h_ += skinid;
		_h_ += fightercommon.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(agentid).append(",");
		_sb_.append(petkey).append(",");
		_sb_.append(owenrid).append(",");
		_sb_.append(level).append(",");
		_sb_.append(starlevel).append(",");
		_sb_.append(awakelevel).append(",");
		_sb_.append(skinid).append(",");
		_sb_.append(fightercommon).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

