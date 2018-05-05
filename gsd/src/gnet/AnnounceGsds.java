
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AnnounceGsds__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AnnounceGsds extends __AnnounceGsds__ {
	@Override
	protected void process() {
		MapClient.getInstance().setGsds(gsds);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 176;

	public int getType() {
		return 176;
	}

	public java.util.ArrayList<gnet.GsdInfo> gsds;

	public AnnounceGsds() {
		gsds = new java.util.ArrayList<gnet.GsdInfo>();
	}

	public AnnounceGsds(java.util.ArrayList<gnet.GsdInfo> _gsds_) {
		this.gsds = _gsds_;
	}

	public final boolean _validator_() {
		for (gnet.GsdInfo _v_ : gsds)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.compact_uint32(gsds.size());
		for (gnet.GsdInfo _v_ : gsds) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			gnet.GsdInfo _v_ = new gnet.GsdInfo();
			_v_.unmarshal(_os_);
			gsds.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AnnounceGsds) {
			AnnounceGsds _o_ = (AnnounceGsds)_o1_;
			if (!gsds.equals(_o_.gsds)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += gsds.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(gsds).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

