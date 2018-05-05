
package gnet;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class GsdInfo implements Marshal {
	public int serverid;
	public java.lang.String ip;
	public int port;

	public GsdInfo() {
		ip = "";
	}

	public GsdInfo(int _serverid_, java.lang.String _ip_, int _port_) {
		this.serverid = _serverid_;
		this.ip = _ip_;
		this.port = _port_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(serverid);
		_os_.marshal(ip, "UTF-16LE");
		_os_.marshal(port);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		serverid = _os_.unmarshal_int();
		ip = _os_.unmarshal_String("UTF-16LE");
		port = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof GsdInfo) {
			GsdInfo _o_ = (GsdInfo)_o1_;
			if (serverid != _o_.serverid) return false;
			if (!ip.equals(_o_.ip)) return false;
			if (port != _o_.port) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += serverid;
		_h_ += ip.hashCode();
		_h_ += port;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(serverid).append(",");
		_sb_.append("T").append(ip.length()).append(",");
		_sb_.append(port).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

