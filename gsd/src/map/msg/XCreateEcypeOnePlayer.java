
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateEcypeOnePlayer__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateEcypeOnePlayer extends __XCreateEcypeOnePlayer__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6700496;

	public int getType() {
		return 6700496;
	}

	public int ectypeid;
	public byte istaskectype;
	public int serverid;
	public long roleid;
	public int profession;
	public int param1;
	public int param2;

	public XCreateEcypeOnePlayer() {
	}

	public XCreateEcypeOnePlayer(int _ectypeid_, byte _istaskectype_, int _serverid_, long _roleid_, int _profession_, int _param1_, int _param2_) {
		this.ectypeid = _ectypeid_;
		this.istaskectype = _istaskectype_;
		this.serverid = _serverid_;
		this.roleid = _roleid_;
		this.profession = _profession_;
		this.param1 = _param1_;
		this.param2 = _param2_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(istaskectype);
		_os_.marshal(serverid);
		_os_.marshal(roleid);
		_os_.marshal(profession);
		_os_.marshal(param1);
		_os_.marshal(param2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		istaskectype = _os_.unmarshal_byte();
		serverid = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		profession = _os_.unmarshal_int();
		param1 = _os_.unmarshal_int();
		param2 = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateEcypeOnePlayer) {
			XCreateEcypeOnePlayer _o_ = (XCreateEcypeOnePlayer)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (istaskectype != _o_.istaskectype) return false;
			if (serverid != _o_.serverid) return false;
			if (roleid != _o_.roleid) return false;
			if (profession != _o_.profession) return false;
			if (param1 != _o_.param1) return false;
			if (param2 != _o_.param2) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += (int)istaskectype;
		_h_ += serverid;
		_h_ += (int)roleid;
		_h_ += profession;
		_h_ += param1;
		_h_ += param2;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(istaskectype).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(profession).append(",");
		_sb_.append(param1).append(",");
		_sb_.append(param2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(XCreateEcypeOnePlayer _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		_c_ = istaskectype - _o_.istaskectype;
		if (0 != _c_) return _c_;
		_c_ = serverid - _o_.serverid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(roleid - _o_.roleid);
		if (0 != _c_) return _c_;
		_c_ = profession - _o_.profession;
		if (0 != _c_) return _c_;
		_c_ = param1 - _o_.param1;
		if (0 != _c_) return _c_;
		_c_ = param2 - _o_.param2;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

