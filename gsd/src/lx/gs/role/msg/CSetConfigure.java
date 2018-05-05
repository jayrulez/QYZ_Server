
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

import gs.AProcedure;
import lx.gs.role.FRole;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __CSetConfigure__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class CSetConfigure extends __CSetConfigure__ {
	@Override
	protected void process() {
		new AProcedure<CSetConfigure>(this) {

			@Override
			protected boolean doProcess() throws Exception {
				final xbean.RoleConfigure conf = FRole.getConfigure(roleid);
				conf.getDatas().put(key, data);
				return true;
			}
			
		}.validateRoleidAndExecute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6570725;

	public int getType() {
		return 6570725;
	}

	public java.lang.String key;
	public java.lang.String data;

	public CSetConfigure() {
		key = "";
		data = "";
	}

	public CSetConfigure(java.lang.String _key_, java.lang.String _data_) {
		this.key = _key_;
		this.data = _data_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(key, "UTF-16LE");
		_os_.marshal(data, "UTF-16LE");
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		key = _os_.unmarshal_String("UTF-16LE");
		data = _os_.unmarshal_String("UTF-16LE");
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof CSetConfigure) {
			CSetConfigure _o_ = (CSetConfigure)_o1_;
			if (!key.equals(_o_.key)) return false;
			if (!data.equals(_o_.data)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += key.hashCode();
		_h_ += data.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("T").append(key.length()).append(",");
		_sb_.append("T").append(data.length()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

