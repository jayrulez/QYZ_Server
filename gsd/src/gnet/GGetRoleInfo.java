
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __GGetRoleInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class GGetRoleInfo extends __GGetRoleInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 155;

	public int getType() {
		return 155;
	}

	public final static int OK = 0;
	public final static int NO_ROLE = 1;

	public long xid;
	public int err;
	public java.util.HashMap<Integer,Integer> info; // 查询信息:数值

	public GGetRoleInfo() {
		info = new java.util.HashMap<Integer,Integer>();
	}

	public GGetRoleInfo(long _xid_, int _err_, java.util.HashMap<Integer,Integer> _info_) {
		this.xid = _xid_;
		this.err = _err_;
		this.info = _info_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(xid);
		_os_.marshal(err);
		_os_.compact_uint32(info.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : info.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		xid = _os_.unmarshal_long();
		err = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			info.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GGetRoleInfo) {
			GGetRoleInfo _o_ = (GGetRoleInfo)_o1_;
			if (xid != _o_.xid) return false;
			if (err != _o_.err) return false;
			if (!info.equals(_o_.info)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)xid;
		_h_ += err;
		_h_ += info.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(xid).append(",");
		_sb_.append(err).append(",");
		_sb_.append(info).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

