
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateEctypeMultiStory__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateEctypeMultiStory extends __XCreateEctypeMultiStory__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6704835;

	public int getType() {
		return 6704835;
	}

	public int ectypeid;
	public byte istaskectype;
	public int serverid;
	public java.util.HashMap<Long,Integer> roles;
	public java.util.HashMap<Long,Integer> rolesstar;
	public java.util.HashMap<Long,Integer> roleusedtimes;

	public XCreateEctypeMultiStory() {
		roles = new java.util.HashMap<Long,Integer>();
		rolesstar = new java.util.HashMap<Long,Integer>();
		roleusedtimes = new java.util.HashMap<Long,Integer>();
	}

	public XCreateEctypeMultiStory(int _ectypeid_, byte _istaskectype_, int _serverid_, java.util.HashMap<Long,Integer> _roles_, java.util.HashMap<Long,Integer> _rolesstar_, java.util.HashMap<Long,Integer> _roleusedtimes_) {
		this.ectypeid = _ectypeid_;
		this.istaskectype = _istaskectype_;
		this.serverid = _serverid_;
		this.roles = _roles_;
		this.rolesstar = _rolesstar_;
		this.roleusedtimes = _roleusedtimes_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(istaskectype);
		_os_.marshal(serverid);
		_os_.compact_uint32(roles.size());
		for (java.util.Map.Entry<Long, Integer> _e_ : roles.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(rolesstar.size());
		for (java.util.Map.Entry<Long, Integer> _e_ : rolesstar.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(roleusedtimes.size());
		for (java.util.Map.Entry<Long, Integer> _e_ : roleusedtimes.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		istaskectype = _os_.unmarshal_byte();
		serverid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			int _v_;
			_v_ = _os_.unmarshal_int();
			roles.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			int _v_;
			_v_ = _os_.unmarshal_int();
			rolesstar.put(_k_, _v_);
		}
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			long _k_;
			_k_ = _os_.unmarshal_long();
			int _v_;
			_v_ = _os_.unmarshal_int();
			roleusedtimes.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateEctypeMultiStory) {
			XCreateEctypeMultiStory _o_ = (XCreateEctypeMultiStory)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (istaskectype != _o_.istaskectype) return false;
			if (serverid != _o_.serverid) return false;
			if (!roles.equals(_o_.roles)) return false;
			if (!rolesstar.equals(_o_.rolesstar)) return false;
			if (!roleusedtimes.equals(_o_.roleusedtimes)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += (int)istaskectype;
		_h_ += serverid;
		_h_ += roles.hashCode();
		_h_ += rolesstar.hashCode();
		_h_ += roleusedtimes.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(istaskectype).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(roles).append(",");
		_sb_.append(rolesstar).append(",");
		_sb_.append(roleusedtimes).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

