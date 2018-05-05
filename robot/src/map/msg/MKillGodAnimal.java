
package map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __MKillGodAnimal__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class MKillGodAnimal extends __MKillGodAnimal__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6712866;

	public int getType() {
		return 6712866;
	}

	public long familyid;
	public int bossid;
	public int bosslvl;
	public java.util.ArrayList<map.msg.SingleRoleBonus> membersbonus;
	public java.util.ArrayList<map.msg.SingleRoleBonus> lasthitbonus;
	public java.util.ArrayList<map.msg.SingleRoleBonus> luckybonus;

	public MKillGodAnimal() {
		membersbonus = new java.util.ArrayList<map.msg.SingleRoleBonus>();
		lasthitbonus = new java.util.ArrayList<map.msg.SingleRoleBonus>();
		luckybonus = new java.util.ArrayList<map.msg.SingleRoleBonus>();
	}

	public MKillGodAnimal(long _familyid_, int _bossid_, int _bosslvl_, java.util.ArrayList<map.msg.SingleRoleBonus> _membersbonus_, java.util.ArrayList<map.msg.SingleRoleBonus> _lasthitbonus_, java.util.ArrayList<map.msg.SingleRoleBonus> _luckybonus_) {
		this.familyid = _familyid_;
		this.bossid = _bossid_;
		this.bosslvl = _bosslvl_;
		this.membersbonus = _membersbonus_;
		this.lasthitbonus = _lasthitbonus_;
		this.luckybonus = _luckybonus_;
	}

	public final boolean _validator_() {
		for (map.msg.SingleRoleBonus _v_ : membersbonus)
			if (!_v_._validator_()) return false;
		for (map.msg.SingleRoleBonus _v_ : lasthitbonus)
			if (!_v_._validator_()) return false;
		for (map.msg.SingleRoleBonus _v_ : luckybonus)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(familyid);
		_os_.marshal(bossid);
		_os_.marshal(bosslvl);
		_os_.compact_uint32(membersbonus.size());
		for (map.msg.SingleRoleBonus _v_ : membersbonus) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(lasthitbonus.size());
		for (map.msg.SingleRoleBonus _v_ : lasthitbonus) {
			_os_.marshal(_v_);
		}
		_os_.compact_uint32(luckybonus.size());
		for (map.msg.SingleRoleBonus _v_ : luckybonus) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		familyid = _os_.unmarshal_long();
		bossid = _os_.unmarshal_int();
		bosslvl = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.SingleRoleBonus _v_ = new map.msg.SingleRoleBonus();
			_v_.unmarshal(_os_);
			membersbonus.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.SingleRoleBonus _v_ = new map.msg.SingleRoleBonus();
			_v_.unmarshal(_os_);
			lasthitbonus.add(_v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			map.msg.SingleRoleBonus _v_ = new map.msg.SingleRoleBonus();
			_v_.unmarshal(_os_);
			luckybonus.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof MKillGodAnimal) {
			MKillGodAnimal _o_ = (MKillGodAnimal)_o1_;
			if (familyid != _o_.familyid) return false;
			if (bossid != _o_.bossid) return false;
			if (bosslvl != _o_.bosslvl) return false;
			if (!membersbonus.equals(_o_.membersbonus)) return false;
			if (!lasthitbonus.equals(_o_.lasthitbonus)) return false;
			if (!luckybonus.equals(_o_.luckybonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)familyid;
		_h_ += bossid;
		_h_ += bosslvl;
		_h_ += membersbonus.hashCode();
		_h_ += lasthitbonus.hashCode();
		_h_ += luckybonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(familyid).append(",");
		_sb_.append(bossid).append(",");
		_sb_.append(bosslvl).append(",");
		_sb_.append(membersbonus).append(",");
		_sb_.append(lasthitbonus).append(",");
		_sb_.append(luckybonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

