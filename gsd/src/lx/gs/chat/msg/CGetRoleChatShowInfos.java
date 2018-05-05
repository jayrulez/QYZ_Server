
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import gnet.link.Onlines;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetRoleChatShowInfos__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetRoleChatShowInfos extends __CGetRoleChatShowInfos__ {
	@Override
	protected void process() {
		final SGetRoleChatShowInfos msg = new SGetRoleChatShowInfos();
        for(long roleid : roles) {
            xtable.Roleinfos.getTable().select(roleid, info -> {
               final RoleChatShowInfo r = new RoleChatShowInfo();
                r.name = info.getName();
                r.gender = info.getGender();
                r.profession = info.getProfession();
                r.level = info.getLevel();
                r.viplevel = info.getViplevel();
                r.isonline = common.Utils.toByte(Onlines.getInstance().find(roleid) != null);
                msg.roles.put(roleid, r);
                return info;
            });
        }

        Onlines.getInstance().sendResponse(this, msg);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573716;

	public int getType() {
		return 6573716;
	}

	public java.util.LinkedList<Long> roles;

	public CGetRoleChatShowInfos() {
		roles = new java.util.LinkedList<Long>();
	}

	public CGetRoleChatShowInfos(java.util.LinkedList<Long> _roles_) {
		this.roles = _roles_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(roles.size());
		for (Long _v_ : roles) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			long _v_;
			_v_ = _os_.unmarshal_long();
			roles.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CGetRoleChatShowInfos) {
			CGetRoleChatShowInfos _o_ = (CGetRoleChatShowInfos)_o1_;
			if (!roles.equals(_o_.roles)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += roles.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roles).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

