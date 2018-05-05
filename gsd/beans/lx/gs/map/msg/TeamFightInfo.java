
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class TeamFightInfo implements Marshal {
	public int weekscore; // 本周积分
	public int todaywinnum; // 本日胜利次数
	public byte obtaintodaywinreward; // 是否领过今日奖励
	public int todayfightnum; // 本日参加次数
	public java.util.ArrayList<Integer> obtainscorerewards; // 本周已领积分奖励

	public TeamFightInfo() {
		obtainscorerewards = new java.util.ArrayList<Integer>();
	}

	public TeamFightInfo(int _weekscore_, int _todaywinnum_, byte _obtaintodaywinreward_, int _todayfightnum_, java.util.ArrayList<Integer> _obtainscorerewards_) {
		this.weekscore = _weekscore_;
		this.todaywinnum = _todaywinnum_;
		this.obtaintodaywinreward = _obtaintodaywinreward_;
		this.todayfightnum = _todayfightnum_;
		this.obtainscorerewards = _obtainscorerewards_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(weekscore);
		_os_.marshal(todaywinnum);
		_os_.marshal(obtaintodaywinreward);
		_os_.marshal(todayfightnum);
		_os_.compact_uint32(obtainscorerewards.size());
		for (Integer _v_ : obtainscorerewards) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		weekscore = _os_.unmarshal_int();
		todaywinnum = _os_.unmarshal_int();
		obtaintodaywinreward = _os_.unmarshal_byte();
		todayfightnum = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			int _v_;
			_v_ = _os_.unmarshal_int();
			obtainscorerewards.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof TeamFightInfo) {
			TeamFightInfo _o_ = (TeamFightInfo)_o1_;
			if (weekscore != _o_.weekscore) return false;
			if (todaywinnum != _o_.todaywinnum) return false;
			if (obtaintodaywinreward != _o_.obtaintodaywinreward) return false;
			if (todayfightnum != _o_.todayfightnum) return false;
			if (!obtainscorerewards.equals(_o_.obtainscorerewards)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += weekscore;
		_h_ += todaywinnum;
		_h_ += (int)obtaintodaywinreward;
		_h_ += todayfightnum;
		_h_ += obtainscorerewards.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(weekscore).append(",");
		_sb_.append(todaywinnum).append(",");
		_sb_.append(obtaintodaywinreward).append(",");
		_sb_.append(todayfightnum).append(",");
		_sb_.append(obtainscorerewards).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

