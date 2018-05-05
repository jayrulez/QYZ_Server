
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class HeroesAwardInfo implements Marshal {
	public int leftrefreshcount; // 剩余可刷新次数
	public int refreshcost; // 刷新奖励消耗元宝
	public int ectypeid;
	public map.msg.Bonus bonus;

	public HeroesAwardInfo() {
		bonus = new map.msg.Bonus();
	}

	public HeroesAwardInfo(int _leftrefreshcount_, int _refreshcost_, int _ectypeid_, map.msg.Bonus _bonus_) {
		this.leftrefreshcount = _leftrefreshcount_;
		this.refreshcost = _refreshcost_;
		this.ectypeid = _ectypeid_;
		this.bonus = _bonus_;
	}

	public final boolean _validator_() {
		if (!bonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(leftrefreshcount);
		_os_.marshal(refreshcost);
		_os_.marshal(ectypeid);
		_os_.marshal(bonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		leftrefreshcount = _os_.unmarshal_int();
		refreshcost = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		bonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HeroesAwardInfo) {
			HeroesAwardInfo _o_ = (HeroesAwardInfo)_o1_;
			if (leftrefreshcount != _o_.leftrefreshcount) return false;
			if (refreshcost != _o_.refreshcost) return false;
			if (ectypeid != _o_.ectypeid) return false;
			if (!bonus.equals(_o_.bonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += leftrefreshcount;
		_h_ += refreshcost;
		_h_ += ectypeid;
		_h_ += bonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(leftrefreshcount).append(",");
		_sb_.append(refreshcost).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(bonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

