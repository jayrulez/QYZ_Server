
package lx.gs.exchange.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ExchangeItem implements Marshal {
	public long id;
	public int itemid;
	public int num;
	public int aprice; // 单价
	public int bindtype;
	public int anneallevel;
	public int perfuselevel;
	public lx.gs.equip.AccessoryProp prop;

	public ExchangeItem() {
		prop = new lx.gs.equip.AccessoryProp();
	}

	public ExchangeItem(long _id_, int _itemid_, int _num_, int _aprice_, int _bindtype_, int _anneallevel_, int _perfuselevel_, lx.gs.equip.AccessoryProp _prop_) {
		this.id = _id_;
		this.itemid = _itemid_;
		this.num = _num_;
		this.aprice = _aprice_;
		this.bindtype = _bindtype_;
		this.anneallevel = _anneallevel_;
		this.perfuselevel = _perfuselevel_;
		this.prop = _prop_;
	}

	public final boolean _validator_() {
		if (!prop._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(id);
		_os_.marshal(itemid);
		_os_.marshal(num);
		_os_.marshal(aprice);
		_os_.marshal(bindtype);
		_os_.marshal(anneallevel);
		_os_.marshal(perfuselevel);
		_os_.marshal(prop);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		id = _os_.unmarshal_long();
		itemid = _os_.unmarshal_int();
		num = _os_.unmarshal_int();
		aprice = _os_.unmarshal_int();
		bindtype = _os_.unmarshal_int();
		anneallevel = _os_.unmarshal_int();
		perfuselevel = _os_.unmarshal_int();
		prop.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ExchangeItem) {
			ExchangeItem _o_ = (ExchangeItem)_o1_;
			if (id != _o_.id) return false;
			if (itemid != _o_.itemid) return false;
			if (num != _o_.num) return false;
			if (aprice != _o_.aprice) return false;
			if (bindtype != _o_.bindtype) return false;
			if (anneallevel != _o_.anneallevel) return false;
			if (perfuselevel != _o_.perfuselevel) return false;
			if (!prop.equals(_o_.prop)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)id;
		_h_ += itemid;
		_h_ += num;
		_h_ += aprice;
		_h_ += bindtype;
		_h_ += anneallevel;
		_h_ += perfuselevel;
		_h_ += prop.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(id).append(",");
		_sb_.append(itemid).append(",");
		_sb_.append(num).append(",");
		_sb_.append(aprice).append(",");
		_sb_.append(bindtype).append(",");
		_sb_.append(anneallevel).append(",");
		_sb_.append(perfuselevel).append(",");
		_sb_.append(prop).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

