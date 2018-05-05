
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateTeamSpeed__ extends xio.Protocol { }

/** </protocol>
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateTeamSpeed extends __XCreateTeamSpeed__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6713831;

	public int getType() {
		return 6713831;
	}

	public int ectypeid;
	public int serverid;
	public int levelgroupid;
	public java.util.ArrayList<Long> team1;
	public java.util.ArrayList<Long> team2;
	public java.util.ArrayList<Long> rewardrole;

	public XCreateTeamSpeed() {
		team1 = new java.util.ArrayList<Long>();
		team2 = new java.util.ArrayList<Long>();
		rewardrole = new java.util.ArrayList<Long>();
	}

	public XCreateTeamSpeed(int _ectypeid_, int _serverid_, int _levelgroupid_, java.util.ArrayList<Long> _team1_, java.util.ArrayList<Long> _team2_, java.util.ArrayList<Long> _rewardrole_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.levelgroupid = _levelgroupid_;
		this.team1 = _team1_;
		this.team2 = _team2_;
		this.rewardrole = _rewardrole_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.marshal(levelgroupid);
		_os_.compact_uint32(team1.size());
		for (Long _v_ : team1) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(team2.size());
		for (Long _v_ : team2) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(rewardrole.size());
		for (Long _v_ : rewardrole) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		levelgroupid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			team1.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			team2.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			rewardrole.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateTeamSpeed) {
			XCreateTeamSpeed _o_ = (XCreateTeamSpeed)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (serverid != _o_.serverid) return false;
			if (levelgroupid != _o_.levelgroupid) return false;
			if (!team1.equals(_o_.team1)) return false;
			if (!team2.equals(_o_.team2)) return false;
			if (!rewardrole.equals(_o_.rewardrole)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += serverid;
		_h_ += levelgroupid;
		_h_ += team1.hashCode();
		_h_ += team2.hashCode();
		_h_ += rewardrole.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(levelgroupid).append(",");
		_sb_.append(team1).append(",");
		_sb_.append(team2).append(",");
		_sb_.append(rewardrole).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

