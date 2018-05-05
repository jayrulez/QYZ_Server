
package lx.gs.equip;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Equip implements Marshal {
	public long equipid; // 装备id
	public int modelid; // model id
	public int position; // 包裹中的位置
	public long expiretime; // 过期时间
	public int isbind; // 是否绑定
	public lx.gs.equip.NormalEquipProp normalequip; // 普通装备的属性
	public lx.gs.equip.AccessoryProp accessory; // 饰品类装备的属性

	public Equip() {
		normalequip = new lx.gs.equip.NormalEquipProp();
		accessory = new lx.gs.equip.AccessoryProp();
	}

	public Equip(long _equipid_, int _modelid_, int _position_, long _expiretime_, int _isbind_, lx.gs.equip.NormalEquipProp _normalequip_, lx.gs.equip.AccessoryProp _accessory_) {
		this.equipid = _equipid_;
		this.modelid = _modelid_;
		this.position = _position_;
		this.expiretime = _expiretime_;
		this.isbind = _isbind_;
		this.normalequip = _normalequip_;
		this.accessory = _accessory_;
	}

	public final boolean _validator_() {
		if (!normalequip._validator_()) return false;
		if (!accessory._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(equipid);
		_os_.marshal(modelid);
		_os_.marshal(position);
		_os_.marshal(expiretime);
		_os_.marshal(isbind);
		_os_.marshal(normalequip);
		_os_.marshal(accessory);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		equipid = _os_.unmarshal_long();
		modelid = _os_.unmarshal_int();
		position = _os_.unmarshal_int();
		expiretime = _os_.unmarshal_long();
		isbind = _os_.unmarshal_int();
		normalequip.unmarshal(_os_);
		accessory.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Equip) {
			Equip _o_ = (Equip)_o1_;
			if (equipid != _o_.equipid) return false;
			if (modelid != _o_.modelid) return false;
			if (position != _o_.position) return false;
			if (expiretime != _o_.expiretime) return false;
			if (isbind != _o_.isbind) return false;
			if (!normalequip.equals(_o_.normalequip)) return false;
			if (!accessory.equals(_o_.accessory)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)equipid;
		_h_ += modelid;
		_h_ += position;
		_h_ += (int)expiretime;
		_h_ += isbind;
		_h_ += normalequip.hashCode();
		_h_ += accessory.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(equipid).append(",");
		_sb_.append(modelid).append(",");
		_sb_.append(position).append(",");
		_sb_.append(expiretime).append(",");
		_sb_.append(isbind).append(",");
		_sb_.append(normalequip).append(",");
		_sb_.append(accessory).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

