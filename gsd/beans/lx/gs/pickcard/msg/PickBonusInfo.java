
package lx.gs.pickcard.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class PickBonusInfo implements Marshal {
	public int issplit; // 是否拆分，0：否，1:是
	public map.msg.Bonus bonus;
	public map.msg.Bonus splitbonus;

	public PickBonusInfo() {
		bonus = new map.msg.Bonus();
		splitbonus = new map.msg.Bonus();
	}

	public PickBonusInfo(int _issplit_, map.msg.Bonus _bonus_, map.msg.Bonus _splitbonus_) {
		this.issplit = _issplit_;
		this.bonus = _bonus_;
		this.splitbonus = _splitbonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		if (!splitbonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(issplit);
		_os_.marshal(bonus);
		_os_.marshal(splitbonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		issplit = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		splitbonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof PickBonusInfo) {
			PickBonusInfo _o_ = (PickBonusInfo)_o1_;
			if (issplit != _o_.issplit) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			if (!splitbonus.equals(_o_.splitbonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += issplit;
		_h_ += bonus.hashCode();
		_h_ += splitbonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(issplit).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(splitbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

