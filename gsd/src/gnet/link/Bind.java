
package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Bind__ extends xio.Protocol { }

/** gs to link
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Bind extends __Bind__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65537;

	public int getType() {
		return 65537;
	}

	public int pvid;
	public java.util.HashSet<Integer> linksids;

	public Bind() {
		linksids = new java.util.HashSet<Integer>();
	}

	public Bind(int _pvid_, java.util.HashSet<Integer> _linksids_) {
		this.pvid = _pvid_;
		this.linksids = _linksids_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pvid);
		_os_.compact_uint32(linksids.size());
		for (Integer _v_ : linksids) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pvid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			linksids.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Bind) {
			Bind _o_ = (Bind)_o1_;
			if (pvid != _o_.pvid) return false;
			if (!linksids.equals(_o_.linksids)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pvid;
		_h_ += linksids.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pvid).append(",");
		_sb_.append(linksids).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

