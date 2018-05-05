
package lx.gs.limit.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Limit implements Marshal {
	public long id;
	public java.util.HashMap<Integer,Integer> typenums; // limittype -> num 每种限制类型相应的已达到的次数

	public Limit() {
		typenums = new java.util.HashMap<Integer,Integer>();
	}

	public Limit(long _id_, java.util.HashMap<Integer,Integer> _typenums_) {
		this.id = _id_;
		this.typenums = _typenums_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.compact_uint32(typenums.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : typenums.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			typenums.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Limit) {
			Limit _o_ = (Limit)_o1_;
			if (id != _o_.id) return false;
			if (!typenums.equals(_o_.typenums)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += typenums.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(typenums).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

