
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateHuiWu__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateHuiWu extends __XCreateHuiWu__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6702180;

	public int getType() {
		return 6702180;
	}

	public int ectypeid;
	public int serverid;
	public int profession;
	public int roundindex;
	public int battleindex;
	public map.msg.PlayerBuilder player1;
	public map.msg.PlayerBuilder player2;

	public XCreateHuiWu() {
		player1 = new map.msg.PlayerBuilder();
		player2 = new map.msg.PlayerBuilder();
	}

	public XCreateHuiWu(int _ectypeid_, int _serverid_, int _profession_, int _roundindex_, int _battleindex_, map.msg.PlayerBuilder _player1_, map.msg.PlayerBuilder _player2_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.profession = _profession_;
		this.roundindex = _roundindex_;
		this.battleindex = _battleindex_;
		this.player1 = _player1_;
		this.player2 = _player2_;
	}

	public final boolean _validator_() {
		if (!player1._validator_()) return false;
		if (!player2._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.marshal(profession);
		_os_.marshal(roundindex);
		_os_.marshal(battleindex);
		_os_.marshal(player1);
		_os_.marshal(player2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		roundindex = _os_.unmarshal_int();
		battleindex = _os_.unmarshal_int();
		player1.unmarshal(_os_);
		player2.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateHuiWu) {
			XCreateHuiWu _o_ = (XCreateHuiWu)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (serverid != _o_.serverid) return false;
			if (profession != _o_.profession) return false;
			if (roundindex != _o_.roundindex) return false;
			if (battleindex != _o_.battleindex) return false;
			if (!player1.equals(_o_.player1)) return false;
			if (!player2.equals(_o_.player2)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += serverid;
		_h_ += profession;
		_h_ += roundindex;
		_h_ += battleindex;
		_h_ += player1.hashCode();
		_h_ += player2.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(roundindex).append(",");
		_sb_.append(battleindex).append(",");
		_sb_.append(player1).append(",");
		_sb_.append(player2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

