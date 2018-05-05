
package lx.gs.notice.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Notice implements Marshal {
	public com.goldhuman.Common.Octets content;

	public Notice() {
		content = new com.goldhuman.Common.Octets();
	}

	public Notice(com.goldhuman.Common.Octets _content_) {
		this.content = _content_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(content);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		content = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Notice) {
			Notice _o_ = (Notice)_o1_;
			if (!content.equals(_o_.content)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += content.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append("B").append(content.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

