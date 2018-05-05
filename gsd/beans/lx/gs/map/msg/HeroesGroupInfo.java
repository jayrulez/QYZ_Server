
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 青云英雄录
*/
public class HeroesGroupInfo implements Marshal , Comparable<HeroesGroupInfo>{
	public int refreshtime; // 已经进行过的刷新次数
	public int ectypeid; // 上次随机到的副本id，为0或空则取默认值

	public HeroesGroupInfo() {
	}

	public HeroesGroupInfo(int _refreshtime_, int _ectypeid_) {
		this.refreshtime = _refreshtime_;
		this.ectypeid = _ectypeid_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(refreshtime);
		_os_.marshal(ectypeid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		refreshtime = _os_.unmarshal_int();
		ectypeid = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof HeroesGroupInfo) {
			HeroesGroupInfo _o_ = (HeroesGroupInfo)_o1_;
			if (refreshtime != _o_.refreshtime) return false;
			if (ectypeid != _o_.ectypeid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += refreshtime;
		_h_ += ectypeid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(refreshtime).append(",");
		_sb_.append(ectypeid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(HeroesGroupInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = refreshtime - _o_.refreshtime;
		if (0 != _c_) return _c_;
		_c_ = ectypeid - _o_.ectypeid;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

