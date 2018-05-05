
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Bonus implements Marshal {
	public int bindtype;
	public java.util.HashMap<Integer,Integer> items; // 物品，包含装备,碎片和消耗性物品

	public Bonus() {
		items = new java.util.HashMap<Integer,Integer>();
	}

	public Bonus(int _bindtype_, java.util.HashMap<Integer,Integer> _items_) {
		this.bindtype = _bindtype_;
		this.items = _items_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bindtype);
		_os_.compact_uint32(items.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : items.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bindtype = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			items.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Bonus) {
			Bonus _o_ = (Bonus)_o1_;
			if (bindtype != _o_.bindtype) return false;
			if (!items.equals(_o_.items)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bindtype;
		_h_ += items.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bindtype).append(",");
		_sb_.append(items).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

