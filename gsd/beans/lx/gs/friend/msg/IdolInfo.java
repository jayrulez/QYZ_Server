
package lx.gs.friend.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class IdolInfo implements Marshal {
	public int charm;
	public long guardid;
	public java.lang.String guardname;
	public long guardtime;
	public long guarddegree;

	public IdolInfo() {
		guardname = "";
	}

	public IdolInfo(int _charm_, long _guardid_, java.lang.String _guardname_, long _guardtime_, long _guarddegree_) {
		this.charm = _charm_;
		this.guardid = _guardid_;
		this.guardname = _guardname_;
		this.guardtime = _guardtime_;
		this.guarddegree = _guarddegree_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(charm);
		_os_.marshal(guardid);
		_os_.marshal(guardname, "UTF-16LE");
		_os_.marshal(guardtime);
		_os_.marshal(guarddegree);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		charm = _os_.unmarshal_int();
		guardid = _os_.unmarshal_long();
		guardname = _os_.unmarshal_String("UTF-16LE");
		guardtime = _os_.unmarshal_long();
		guarddegree = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof IdolInfo) {
			IdolInfo _o_ = (IdolInfo)_o1_;
			if (charm != _o_.charm) return false;
			if (guardid != _o_.guardid) return false;
			if (!guardname.equals(_o_.guardname)) return false;
			if (guardtime != _o_.guardtime) return false;
			if (guarddegree != _o_.guarddegree) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += charm;
		_h_ += (int)guardid;
		_h_ += guardname.hashCode();
		_h_ += (int)guardtime;
		_h_ += (int)guarddegree;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(charm).append(",");
		_sb_.append(guardid).append(",");
		_sb_.append("T").append(guardname.length()).append(",");
		_sb_.append(guardtime).append(",");
		_sb_.append(guarddegree).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

