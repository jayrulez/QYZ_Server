
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnterTeamSpeed__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnterTeamSpeed extends __SEnterTeamSpeed__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6710188;

	public int getType() {
		return 6710188;
	}

	public int levelindex; // 按等级分段的索引，获取对应等级的副本信息
	public int teamid; // 队伍
	public long remaintime;
	public java.util.HashMap<Integer,Integer> teamscore;

	public SEnterTeamSpeed() {
		teamscore = new java.util.HashMap<Integer,Integer>();
	}

	public SEnterTeamSpeed(int _levelindex_, int _teamid_, long _remaintime_, java.util.HashMap<Integer,Integer> _teamscore_) {
		this.levelindex = _levelindex_;
		this.teamid = _teamid_;
		this.remaintime = _remaintime_;
		this.teamscore = _teamscore_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(levelindex);
		_os_.marshal(teamid);
		_os_.marshal(remaintime);
		_os_.compact_uint32(teamscore.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : teamscore.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		levelindex = _os_.unmarshal_int();
		teamid = _os_.unmarshal_int();
		remaintime = _os_.unmarshal_long();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			teamscore.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnterTeamSpeed) {
			SEnterTeamSpeed _o_ = (SEnterTeamSpeed)_o1_;
			if (levelindex != _o_.levelindex) return false;
			if (teamid != _o_.teamid) return false;
			if (remaintime != _o_.remaintime) return false;
			if (!teamscore.equals(_o_.teamscore)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += levelindex;
		_h_ += teamid;
		_h_ += (int)remaintime;
		_h_ += teamscore.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(levelindex).append(",");
		_sb_.append(teamid).append(",");
		_sb_.append(remaintime).append(",");
		_sb_.append(teamscore).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

