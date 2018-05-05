
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MEnterMap__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MEnterMap extends __MEnterMap__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6715524;

	public int getType() {
		return 6715524;
	}

	public int ctxid;
	public int retcode;
	public long roleid;
	public map.msg.RoleMapContext cur;
	public int rolenum;

	public MEnterMap() {
		cur = new map.msg.RoleMapContext();
	}

	public MEnterMap(int _ctxid_, int _retcode_, long _roleid_, map.msg.RoleMapContext _cur_, int _rolenum_) {
		this.ctxid = _ctxid_;
		this.retcode = _retcode_;
		this.roleid = _roleid_;
		this.cur = _cur_;
		this.rolenum = _rolenum_;
	}

	public final boolean _validator_() {
		if (!cur._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ctxid);
		_os_.marshal(retcode);
		_os_.marshal(roleid);
		_os_.marshal(cur);
		_os_.marshal(rolenum);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ctxid = _os_.unmarshal_int();
		retcode = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		cur.unmarshal(_os_);
		rolenum = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MEnterMap) {
			MEnterMap _o_ = (MEnterMap)_o1_;
			if (ctxid != _o_.ctxid) return false;
			if (retcode != _o_.retcode) return false;
			if (roleid != _o_.roleid) return false;
			if (!cur.equals(_o_.cur)) return false;
			if (rolenum != _o_.rolenum) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ctxid;
		_h_ += retcode;
		_h_ += (int)roleid;
		_h_ += cur.hashCode();
		_h_ += rolenum;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ctxid).append(",");
		_sb_.append(retcode).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(cur).append(",");
		_sb_.append(rolenum).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

