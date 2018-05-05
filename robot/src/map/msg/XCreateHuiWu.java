
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
	public long roleid1;
	public long roleid2;

	public XCreateHuiWu() {
	}

	public XCreateHuiWu(int _ectypeid_, int _serverid_, int _profession_, int _roundindex_, int _battleindex_, long _roleid1_, long _roleid2_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.profession = _profession_;
		this.roundindex = _roundindex_;
		this.battleindex = _battleindex_;
		this.roleid1 = _roleid1_;
		this.roleid2 = _roleid2_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.marshal(profession);
		_os_.marshal(roundindex);
		_os_.marshal(battleindex);
		_os_.marshal(roleid1);
		_os_.marshal(roleid2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		profession = _os_.unmarshal_int();
		roundindex = _os_.unmarshal_int();
		battleindex = _os_.unmarshal_int();
		roleid1 = _os_.unmarshal_long();
		roleid2 = _os_.unmarshal_long();
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
			if (roleid1 != _o_.roleid1) return false;
			if (roleid2 != _o_.roleid2) return false;
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
		_h_ += (int)roleid1;
		_h_ += (int)roleid2;
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
		_sb_.append(roleid1).append(",");
		_sb_.append(roleid2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XCreateHuiWu _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = serverid - _o_.serverid;
		if (0 != _c_) return _c_;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		_c_ = roundindex - _o_.roundindex;
		if (0 != _c_) return _c_;
		_c_ = battleindex - _o_.battleindex;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid1 - _o_.roleid1);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid2 - _o_.roleid2);
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

