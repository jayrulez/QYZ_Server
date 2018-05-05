
package lx.gs.item;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 物品的数据结构
*/
public class Item implements Marshal , Comparable<Item>{
	public long itemid; // 物品唯一id
	public int modelid; // 物品model id
	public int position; // 物品在包裹中的位置，从0开始编号
	public int count; // 数量
	public long expiretime; // 到期时间，如果为0，表示没有时间限制
	public int isbind; // 物品是否绑定

	public Item() {
	}

	public Item(long _itemid_, int _modelid_, int _position_, int _count_, long _expiretime_, int _isbind_) {
		this.itemid = _itemid_;
		this.modelid = _modelid_;
		this.position = _position_;
		this.count = _count_;
		this.expiretime = _expiretime_;
		this.isbind = _isbind_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(itemid);
		_os_.marshal(modelid);
		_os_.marshal(position);
		_os_.marshal(count);
		_os_.marshal(expiretime);
		_os_.marshal(isbind);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		itemid = _os_.unmarshal_long();
		modelid = _os_.unmarshal_int();
		position = _os_.unmarshal_int();
		count = _os_.unmarshal_int();
		expiretime = _os_.unmarshal_long();
		isbind = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Item) {
			Item _o_ = (Item)_o1_;
			if (itemid != _o_.itemid) return false;
			if (modelid != _o_.modelid) return false;
			if (position != _o_.position) return false;
			if (count != _o_.count) return false;
			if (expiretime != _o_.expiretime) return false;
			if (isbind != _o_.isbind) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)itemid;
		_h_ += modelid;
		_h_ += position;
		_h_ += count;
		_h_ += (int)expiretime;
		_h_ += isbind;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(itemid).append(",");
		_sb_.append(modelid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(count).append(",");
		_sb_.append(expiretime).append(",");
		_sb_.append(isbind).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Item _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = Long.signum(itemid - _o_.itemid);
		if (0 != _c_) return _c_;
		_c_ = modelid - _o_.modelid;
		if (0 != _c_) return _c_;
		_c_ = position - _o_.position;
		if (0 != _c_) return _c_;
		_c_ = count - _o_.count;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(expiretime - _o_.expiretime);
		if (0 != _c_) return _c_;
		_c_ = isbind - _o_.isbind;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

