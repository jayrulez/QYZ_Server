
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateArenaEctype__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateArenaEctype extends __XCreateArenaEctype__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6707991;

	public int getType() {
		return 6707991;
	}

	public int ectypeid;
	public int serverid;
	public long roleid;
	public int profession;
	public map.msg.PlayerBuilder opponent;

	public XCreateArenaEctype() {
		opponent = new map.msg.PlayerBuilder();
	}

	public XCreateArenaEctype(int _ectypeid_, int _serverid_, long _roleid_, int _profession_, map.msg.PlayerBuilder _opponent_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.roleid = _roleid_;
		this.profession = _profession_;
		this.opponent = _opponent_;
	}

	public final boolean _validator_() {
		if (!opponent._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.marshal(roleid);
		_os_.marshal(profession);
		_os_.marshal(opponent);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		profession = _os_.unmarshal_int();
		opponent.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateArenaEctype) {
			XCreateArenaEctype _o_ = (XCreateArenaEctype)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (serverid != _o_.serverid) return false;
			if (roleid != _o_.roleid) return false;
			if (profession != _o_.profession) return false;
			if (!opponent.equals(_o_.opponent)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += serverid;
		_h_ += (int)roleid;
		_h_ += profession;
		_h_ += opponent.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(opponent).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

