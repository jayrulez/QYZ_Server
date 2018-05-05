
package gnet.link;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Dispatch__ extends xio.Protocol { }

/** link to gs
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Dispatch extends __Dispatch__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 65541;

	public int getType() {
		return 65541;
	}

	public int linksid;
	public long userid;
	public int ptype;
	public com.goldhuman.Common.Octets pdata;

	public Dispatch() {
		pdata = new com.goldhuman.Common.Octets();
	}

	public Dispatch(int _linksid_, long _userid_, int _ptype_, com.goldhuman.Common.Octets _pdata_) {
		this.linksid = _linksid_;
		this.userid = _userid_;
		this.ptype = _ptype_;
		this.pdata = _pdata_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(linksid);
		_os_.marshal(userid);
		_os_.marshal(ptype);
		_os_.marshal(pdata);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		linksid = _os_.unmarshal_int();
		userid = _os_.unmarshal_long();
		ptype = _os_.unmarshal_int();
		pdata = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Dispatch) {
			Dispatch _o_ = (Dispatch)_o1_;
			if (linksid != _o_.linksid) return false;
			if (userid != _o_.userid) return false;
			if (ptype != _o_.ptype) return false;
			if (!pdata.equals(_o_.pdata)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += linksid;
		_h_ += (int)userid;
		_h_ += ptype;
		_h_ += pdata.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(linksid).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(pdata.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

