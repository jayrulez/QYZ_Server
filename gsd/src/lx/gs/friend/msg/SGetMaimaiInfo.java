
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SGetMaimaiInfo__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SGetMaimaiInfo extends __SGetMaimaiInfo__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6583186;

	public int getType() {
		return 6583186;
	}

	public int result;
	public long roleid;
	public java.util.HashMap<Integer,lx.gs.friend.msg.MMInfoList> mminfo;

	public SGetMaimaiInfo() {
		mminfo = new java.util.HashMap<Integer,lx.gs.friend.msg.MMInfoList>();
	}

	public SGetMaimaiInfo(int _result_, long _roleid_, java.util.HashMap<Integer,lx.gs.friend.msg.MMInfoList> _mminfo_) {
		this.result = _result_;
		this.roleid = _roleid_;
		this.mminfo = _mminfo_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.friend.msg.MMInfoList> _e_ : mminfo.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(result);
		_os_.marshal(roleid);
		_os_.compact_uint32(mminfo.size());
		for (java.util.Map.Entry<Integer, lx.gs.friend.msg.MMInfoList> _e_ : mminfo.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		result = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.friend.msg.MMInfoList _v_ = new lx.gs.friend.msg.MMInfoList();
			_v_.unmarshal(_os_);
			mminfo.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SGetMaimaiInfo) {
			SGetMaimaiInfo _o_ = (SGetMaimaiInfo)_o1_;
			if (result != _o_.result) return false;
			if (roleid != _o_.roleid) return false;
			if (!mminfo.equals(_o_.mminfo)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += result;
		_h_ += (int)roleid;
		_h_ += mminfo.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(result).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(mminfo).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

