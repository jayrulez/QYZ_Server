
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleMapContext implements Marshal {
	public byte isnew;
	public map.msg.RoleMapInfo1 info1;
	public map.msg.RoleMapInfo2 info2;

	public RoleMapContext() {
		info1 = new map.msg.RoleMapInfo1();
		info2 = new map.msg.RoleMapInfo2();
	}

	public RoleMapContext(byte _isnew_, map.msg.RoleMapInfo1 _info1_, map.msg.RoleMapInfo2 _info2_) {
		this.isnew = _isnew_;
		this.info1 = _info1_;
		this.info2 = _info2_;
	}

	public final boolean _validator_() {
		if (!info1._validator_()) return false;
		if (!info2._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(isnew);
		_os_.marshal(info1);
		_os_.marshal(info2);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		isnew = _os_.unmarshal_byte();
		info1.unmarshal(_os_);
		info2.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleMapContext) {
			RoleMapContext _o_ = (RoleMapContext)_o1_;
			if (isnew != _o_.isnew) return false;
			if (!info1.equals(_o_.info1)) return false;
			if (!info2.equals(_o_.info2)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)isnew;
		_h_ += info1.hashCode();
		_h_ += info2.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(isnew).append(",");
		_sb_.append(info1).append(",");
		_sb_.append(info2).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

