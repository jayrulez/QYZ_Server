
package lx.gs.chat.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import gnet.link.Onlines;
import gs.AProcedure;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CGetOnlineState__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CGetOnlineState extends __CGetOnlineState__ {
	@Override
	protected void process() {
		new AProcedure<CGetOnlineState>(this) {
			@Override
			protected boolean doProcess() throws Exception {
				SGetOnlineState ret = new SGetOnlineState();
				int i = 0;
				for (Long role : roles) {
					if(Onlines.getInstance().find(role) != null){
						ret.onlineroles.add(role);
						i++;
					}
					if(i >= 50){
						break; // 暂时一次只能查50个
					}
				}
				response(ret);
				return true;
			}
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6571847;

	public int getType() {
		return 6571847;
	}

	public java.util.HashSet<Long> roles; // 查询玩家的在线状态，上线50个

	public CGetOnlineState() {
		roles = new java.util.HashSet<Long>();
	}

	public CGetOnlineState(java.util.HashSet<Long> _roles_) {
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
		if (_o1_ instanceof CGetOnlineState) {
			CGetOnlineState _o_ = (CGetOnlineState)_o1_;
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

