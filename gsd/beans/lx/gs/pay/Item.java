
package lx.gs.pay;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Item implements Marshal , Comparable<Item>{
	public int itemcfgid;
	public int count;

	public Item() {
	}

	public Item(int _itemcfgid_, int _count_) {
		this.itemcfgid = _itemcfgid_;
		this.count = _count_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(itemcfgid);
		_os_.marshal(count);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		itemcfgid = _os_.unmarshal_int();
		count = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Item) {
			Item _o_ = (Item)_o1_;
			if (itemcfgid != _o_.itemcfgid) return false;
			if (count != _o_.count) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += itemcfgid;
		_h_ += count;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(itemcfgid).append(",");
		_sb_.append(count).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Item _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = itemcfgid - _o_.itemcfgid;
		if (0 != _c_) return _c_;
		_c_ = count - _o_.count;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

