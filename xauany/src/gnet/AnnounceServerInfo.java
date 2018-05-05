
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __AnnounceServerInfo__ extends xio.Protocol { }

/** 如果发给auany，auany不认识plattype就直接踢掉。
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class AnnounceServerInfo extends __AnnounceServerInfo__ {
	@Override
	protected void process() {
		final xio.Xio xio = getConnection();
		xauany.PlatProcess platprocess = xauany.PlatManager.getPlatProcess(plattype.plat);
		if(platprocess == null){
			xdb.Trace.warn("AnnounceServerInfo PlatProcess NOT FOUND. CLOSE SOCKET. serverid = " + serverid + " plattype = " + plattype.plat + ", xio:" + xio.toString());
			
			xio.close();
			
			return;
		}
		
		xauany.XioManager.getInstance().registerGs(xio, serverid, plattype.plat);
		
		xdb.Trace.info("AnnounceServerInfo serverid = " + serverid + " plattype = " + plattype.plat + " platprocess = " + platprocess.getName() + ", xio:" + xio.toString());
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 119;

	public int getType() {
		return 119;
	}

	public gnet.PlatType plattype;
	public int serverid;

	public AnnounceServerInfo() {
		plattype = new gnet.PlatType();
	}

	public AnnounceServerInfo(gnet.PlatType _plattype_, int _serverid_) {
		this.plattype = _plattype_;
		this.serverid = _serverid_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(plattype);
		_os_.marshal(serverid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		plattype.unmarshal(_os_);
		serverid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof AnnounceServerInfo) {
			AnnounceServerInfo _o_ = (AnnounceServerInfo)_o1_;
			if (!plattype.equals(_o_.plattype)) return false;
			if (serverid != _o_.serverid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += plattype.hashCode();
		_h_ += serverid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(plattype).append(",");
		_sb_.append(serverid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(AnnounceServerInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = plattype.compareTo(_o_.plattype);
		if (0 != _c_) return _c_;
		_c_ = serverid - _o_.serverid;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

