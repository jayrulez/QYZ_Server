
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
import xdb.Trace;
import xio.Manager;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __GsToAuany__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class GsToAuany extends __GsToAuany__ {
	@Override
	protected void process() {
        try {
            final Stub stub = ((xio.Protocol.Coder) getManager().getCoder()).getStub(ptype);
            final xio.Protocol proto = stub.newInstance();
            proto.unmarshal(OctetsStream.wrap(pdata));
            proto.setConnection(getConnection());
            proto.setContext(this);
            proto.dispatch(stub);
        } catch (Exception e) {
            Trace.error("GsToAuany:" + this + " process fail", e);
        }
	}

	public void reply(xio.Protocol toSend) {
        final AuanyToGs msg = new AuanyToGs();
        msg.userid = userid;
        msg.roleid = roleid;
        msg.ptype = toSend.getType();
        final OctetsStream os = new OctetsStream();
        os.marshal(toSend);
        msg.pdata = os;
        msg.send(getConnection());
    }

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 147;

	public int getType() {
		return 147;
	}

	public long userid;
	public long roleid;
	public int ptype;
	public com.goldhuman.Common.Octets pdata;

	public GsToAuany() {
		pdata = new com.goldhuman.Common.Octets();
	}

	public GsToAuany(long _userid_, long _roleid_, int _ptype_, com.goldhuman.Common.Octets _pdata_) {
		this.userid = _userid_;
		this.roleid = _roleid_;
		this.ptype = _ptype_;
		this.pdata = _pdata_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(userid);
		_os_.marshal(roleid);
		_os_.marshal(ptype);
		_os_.marshal(pdata);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		userid = _os_.unmarshal_long();
		roleid = _os_.unmarshal_long();
		ptype = _os_.unmarshal_int();
		pdata = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GsToAuany) {
			GsToAuany _o_ = (GsToAuany)_o1_;
			if (userid != _o_.userid) return false;
			if (roleid != _o_.roleid) return false;
			if (ptype != _o_.ptype) return false;
			if (!pdata.equals(_o_.pdata)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)userid;
		_h_ += (int)roleid;
		_h_ += ptype;
		_h_ += pdata.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(userid).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(ptype).append(",");
		_sb_.append("B").append(pdata.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

