
package lx.gs.role.title.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Title implements Marshal , Comparable<Title>{
	public final static int STATE_GET = 1;
	public final static int STATE_ACTIVED = 2;

	public int titlekey; // 称号id
	public int titletype; // 称号所属类型，战力榜，竞技场榜，财富榜等
	public int state; // 称号的状态，0为未获得，1为获得，2为激活
	public long gettime; // 称号获得的时间
	public long activetime; // 称号激活的时间
	public long expiretime; // 称号到期的时间

	public Title() {
	}

	public Title(int _titlekey_, int _titletype_, int _state_, long _gettime_, long _activetime_, long _expiretime_) {
		this.titlekey = _titlekey_;
		this.titletype = _titletype_;
		this.state = _state_;
		this.gettime = _gettime_;
		this.activetime = _activetime_;
		this.expiretime = _expiretime_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(titlekey);
		_os_.marshal(titletype);
		_os_.marshal(state);
		_os_.marshal(gettime);
		_os_.marshal(activetime);
		_os_.marshal(expiretime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		titlekey = _os_.unmarshal_int();
		titletype = _os_.unmarshal_int();
		state = _os_.unmarshal_int();
		gettime = _os_.unmarshal_long();
		activetime = _os_.unmarshal_long();
		expiretime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Title) {
			Title _o_ = (Title)_o1_;
			if (titlekey != _o_.titlekey) return false;
			if (titletype != _o_.titletype) return false;
			if (state != _o_.state) return false;
			if (gettime != _o_.gettime) return false;
			if (activetime != _o_.activetime) return false;
			if (expiretime != _o_.expiretime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += titlekey;
		_h_ += titletype;
		_h_ += state;
		_h_ += (int)gettime;
		_h_ += (int)activetime;
		_h_ += (int)expiretime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(titlekey).append(",");
		_sb_.append(titletype).append(",");
		_sb_.append(state).append(",");
		_sb_.append(gettime).append(",");
		_sb_.append(activetime).append(",");
		_sb_.append(expiretime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(Title _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = titlekey - _o_.titlekey;
		if (0 != _c_) return _c_;
		_c_ = titletype - _o_.titletype;
		if (0 != _c_) return _c_;
		_c_ = state - _o_.state;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(gettime - _o_.gettime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(activetime - _o_.activetime);
		if (0 != _c_) return _c_;
		_c_ = Long.signum(expiretime - _o_.expiretime);
		if (0 != _c_) return _c_;
		return _c_;
	}

}

