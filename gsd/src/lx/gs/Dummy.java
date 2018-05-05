
package lx.gs;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __Dummy__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class Dummy extends __Dummy__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6553618;

	public int getType() {
		return 6553618;
	}

	public lx.gs.rank.msg.GeneralRankInfo generalrankinfo;
	public lx.gs.dailyactivity.msg.Findbacktype findback;
	public lx.gs.rank.msg.FamilyRankInfo familyrankinfo;
	public lx.gs.equip.Equip equip;
	public lx.gs.item.Item item;
	public lx.gs.fragment.Fragment fragment;
	public lx.gs.talisman.Talisman talisman;
	public lx.gs.pet.Pet pet;

	public Dummy() {
		generalrankinfo = new lx.gs.rank.msg.GeneralRankInfo();
		findback = new lx.gs.dailyactivity.msg.Findbacktype();
		familyrankinfo = new lx.gs.rank.msg.FamilyRankInfo();
		equip = new lx.gs.equip.Equip();
		item = new lx.gs.item.Item();
		fragment = new lx.gs.fragment.Fragment();
		talisman = new lx.gs.talisman.Talisman();
		pet = new lx.gs.pet.Pet();
	}

	public Dummy(lx.gs.rank.msg.GeneralRankInfo _generalrankinfo_, lx.gs.dailyactivity.msg.Findbacktype _findback_, lx.gs.rank.msg.FamilyRankInfo _familyrankinfo_, lx.gs.equip.Equip _equip_, lx.gs.item.Item _item_, lx.gs.fragment.Fragment _fragment_, lx.gs.talisman.Talisman _talisman_, lx.gs.pet.Pet _pet_) {
		this.generalrankinfo = _generalrankinfo_;
		this.findback = _findback_;
		this.familyrankinfo = _familyrankinfo_;
		this.equip = _equip_;
		this.item = _item_;
		this.fragment = _fragment_;
		this.talisman = _talisman_;
		this.pet = _pet_;
	}

	public final boolean _validator_() {
		if (!generalrankinfo._validator_()) return false;
		if (!findback._validator_()) return false;
		if (!familyrankinfo._validator_()) return false;
		if (!equip._validator_()) return false;
		if (!item._validator_()) return false;
		if (!fragment._validator_()) return false;
		if (!talisman._validator_()) return false;
		if (!pet._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(generalrankinfo);
		_os_.marshal(findback);
		_os_.marshal(familyrankinfo);
		_os_.marshal(equip);
		_os_.marshal(item);
		_os_.marshal(fragment);
		_os_.marshal(talisman);
		_os_.marshal(pet);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		generalrankinfo.unmarshal(_os_);
		findback.unmarshal(_os_);
		familyrankinfo.unmarshal(_os_);
		equip.unmarshal(_os_);
		item.unmarshal(_os_);
		fragment.unmarshal(_os_);
		talisman.unmarshal(_os_);
		pet.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Dummy) {
			Dummy _o_ = (Dummy)_o1_;
			if (!generalrankinfo.equals(_o_.generalrankinfo)) return false;
			if (!findback.equals(_o_.findback)) return false;
			if (!familyrankinfo.equals(_o_.familyrankinfo)) return false;
			if (!equip.equals(_o_.equip)) return false;
			if (!item.equals(_o_.item)) return false;
			if (!fragment.equals(_o_.fragment)) return false;
			if (!talisman.equals(_o_.talisman)) return false;
			if (!pet.equals(_o_.pet)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += generalrankinfo.hashCode();
		_h_ += findback.hashCode();
		_h_ += familyrankinfo.hashCode();
		_h_ += equip.hashCode();
		_h_ += item.hashCode();
		_h_ += fragment.hashCode();
		_h_ += talisman.hashCode();
		_h_ += pet.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(generalrankinfo).append(",");
		_sb_.append(findback).append(",");
		_sb_.append(familyrankinfo).append(",");
		_sb_.append(equip).append(",");
		_sb_.append(item).append(",");
		_sb_.append(fragment).append(",");
		_sb_.append(talisman).append(",");
		_sb_.append(pet).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

