
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Findbacktype implements Marshal , Comparable<Findbacktype>{
	public final static int JINBI_FIND = 1; // 金币找回
	public final static int YUANBAO_FIND = 2; // 元宝找回
	public final static int JINBI_FINDALL = 3; // 金币一键找回所有
	public final static int YUANBAO_FINDALL = 4; // 元宝一键找回所有


	public Findbacktype() {
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Findbacktype) {
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Findbacktype _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		return _c_;
	}

}

