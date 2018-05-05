
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __XCreateAttackCity__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class XCreateAttackCity extends __XCreateAttackCity__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6699867;

	public int getType() {
		return 6699867;
	}

	public int ectypeid;
	public int serverid;
	public int levelsectionid;
	public java.util.HashSet<Long> roleids;

	public XCreateAttackCity() {
		roleids = new java.util.HashSet<Long>();
	}

	public XCreateAttackCity(int _ectypeid_, int _serverid_, int _levelsectionid_, java.util.HashSet<Long> _roleids_) {
		this.ectypeid = _ectypeid_;
		this.serverid = _serverid_;
		this.levelsectionid = _levelsectionid_;
		this.roleids = _roleids_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.marshal(serverid);
		_os_.marshal(levelsectionid);
		_os_.compact_uint32(roleids.size());
		for (Long _v_ : roleids) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		serverid = _os_.unmarshal_int();
		levelsectionid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			roleids.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof XCreateAttackCity) {
			XCreateAttackCity _o_ = (XCreateAttackCity)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (serverid != _o_.serverid) return false;
			if (levelsectionid != _o_.levelsectionid) return false;
			if (!roleids.equals(_o_.roleids)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += serverid;
		_h_ += levelsectionid;
		_h_ += roleids.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(levelsectionid).append(",");
		_sb_.append(roleids).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

