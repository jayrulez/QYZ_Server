
package lx.gs.role.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class EquipInfo implements Marshal {
	public int modelid; // model id
	public lx.gs.equip.NormalEquipProp normalequip; // 普通装备的属性
	public lx.gs.equip.AccessoryProp accessory; // 饰品类装备的属性

	public EquipInfo() {
		normalequip = new lx.gs.equip.NormalEquipProp();
		accessory = new lx.gs.equip.AccessoryProp();
	}

	public EquipInfo(int _modelid_, lx.gs.equip.NormalEquipProp _normalequip_, lx.gs.equip.AccessoryProp _accessory_) {
		this.modelid = _modelid_;
		this.normalequip = _normalequip_;
		this.accessory = _accessory_;
	}

	public final boolean _validator_() {
		if (!normalequip._validator_()) return false;
		if (!accessory._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(modelid);
		_os_.marshal(normalequip);
		_os_.marshal(accessory);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		modelid = _os_.unmarshal_int();
		normalequip.unmarshal(_os_);
		accessory.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof EquipInfo) {
			EquipInfo _o_ = (EquipInfo)_o1_;
			if (modelid != _o_.modelid) return false;
			if (!normalequip.equals(_o_.normalequip)) return false;
			if (!accessory.equals(_o_.accessory)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += modelid;
		_h_ += normalequip.hashCode();
		_h_ += accessory.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid).append(",");
		_sb_.append(normalequip).append(",");
		_sb_.append(accessory).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

