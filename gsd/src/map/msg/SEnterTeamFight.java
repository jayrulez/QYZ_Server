
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterTeamFight__ extends xio.Protocol { }

/** 天下会武
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterTeamFight extends __SEnterTeamFight__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6696588;

	public int getType() {
		return 6696588;
	}

	public long id;
	public int ectypeid;
	public int state; // 见 cfg.ectype.EctypeState
	public long remaintime;
	public int mycamp;
	public java.util.HashMap<Integer,Integer> teamkillnums;

	public SEnterTeamFight() {
		teamkillnums = new java.util.HashMap<Integer,Integer>();
	}

	public SEnterTeamFight(long _id_, int _ectypeid_, int _state_, long _remaintime_, int _mycamp_, java.util.HashMap<Integer,Integer> _teamkillnums_) {
		this.id = _id_;
		this.ectypeid = _ectypeid_;
		this.state = _state_;
		this.remaintime = _remaintime_;
		this.mycamp = _mycamp_;
		this.teamkillnums = _teamkillnums_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(ectypeid);
		_os_.marshal(state);
		_os_.marshal(remaintime);
		_os_.marshal(mycamp);
		_os_.compact_uint32(teamkillnums.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : teamkillnums.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		ectypeid = _os_.unmarshal_int();
		state = _os_.unmarshal_int();
		remaintime = _os_.unmarshal_long();
		mycamp = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			teamkillnums.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterTeamFight) {
			SEnterTeamFight _o_ = (SEnterTeamFight)_o1_;
			if (id != _o_.id) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (state != _o_.state) return false;
			if (remaintime != _o_.remaintime) return false;
			if (mycamp != _o_.mycamp) return false;
			if (!teamkillnums.equals(_o_.teamkillnums)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += ectypeid;
		_h_ += state;
		_h_ += (int)remaintime;
		_h_ += mycamp;
		_h_ += teamkillnums.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(state).append(",");
		_sb_.append(remaintime).append(",");
		_sb_.append(mycamp).append(",");
		_sb_.append(teamkillnums).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

