
package lx.gs.role.title.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetTitleInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetTitleInfo extends __SGetTitleInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6557902;

	public int getType() {
		return 6557902;
	}

	public lx.gs.role.title.msg.RoleTitle titleinfo;

	public SGetTitleInfo() {
		titleinfo = new lx.gs.role.title.msg.RoleTitle();
	}

	public SGetTitleInfo(lx.gs.role.title.msg.RoleTitle _titleinfo_) {
		this.titleinfo = _titleinfo_;
	}

	public final boolean _validator_() {
		if (!titleinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(titleinfo);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		titleinfo.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetTitleInfo) {
			SGetTitleInfo _o_ = (SGetTitleInfo)_o1_;
			if (!titleinfo.equals(_o_.titleinfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += titleinfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titleinfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

