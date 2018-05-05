
package lx.gs.arena.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ChallangeInfo implements Marshal {
	public int rank;
	public lx.gs.role.msg.RoleShowInfo1 roleinfo;
	public java.util.ArrayList<lx.gs.role.msg.PetBrief> pets;

	public ChallangeInfo() {
		roleinfo = new lx.gs.role.msg.RoleShowInfo1();
		pets = new java.util.ArrayList<lx.gs.role.msg.PetBrief>();
	}

	public ChallangeInfo(int _rank_, lx.gs.role.msg.RoleShowInfo1 _roleinfo_, java.util.ArrayList<lx.gs.role.msg.PetBrief> _pets_) {
		this.rank = _rank_;
		this.roleinfo = _roleinfo_;
		this.pets = _pets_;
	}

	public final boolean _validator_() {
		if (!roleinfo._validator_()) return false;
		for (lx.gs.role.msg.PetBrief _v_ : pets)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(rank);
		_os_.marshal(roleinfo);
		_os_.compact_uint32(pets.size());
		for (lx.gs.role.msg.PetBrief _v_ : pets) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		rank = _os_.unmarshal_int();
		roleinfo.unmarshal(_os_);
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.role.msg.PetBrief _v_ = new lx.gs.role.msg.PetBrief();
			_v_.unmarshal(_os_);
			pets.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ChallangeInfo) {
			ChallangeInfo _o_ = (ChallangeInfo)_o1_;
			if (rank != _o_.rank) return false;
			if (!roleinfo.equals(_o_.roleinfo)) return false;
			if (!pets.equals(_o_.pets)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += rank;
		_h_ += roleinfo.hashCode();
		_h_ += pets.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(rank).append(",");
		_sb_.append(roleinfo).append(",");
		_sb_.append(pets).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

