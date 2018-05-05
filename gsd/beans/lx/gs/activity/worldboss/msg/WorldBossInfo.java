
package lx.gs.activity.worldboss.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class WorldBossInfo implements Marshal , Comparable<WorldBossInfo>{
	public int bossid;
	public long opentime;
	public int isopen;

	public WorldBossInfo() {
	}

	public WorldBossInfo(int _bossid_, long _opentime_, int _isopen_) {
		this.bossid = _bossid_;
		this.opentime = _opentime_;
		this.isopen = _isopen_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bossid);
		_os_.marshal(opentime);
		_os_.marshal(isopen);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bossid = _os_.unmarshal_int();
		opentime = _os_.unmarshal_long();
		isopen = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof WorldBossInfo) {
			WorldBossInfo _o_ = (WorldBossInfo)_o1_;
			if (bossid != _o_.bossid) return false;
			if (opentime != _o_.opentime) return false;
			if (isopen != _o_.isopen) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += bossid;
		_h_ += (int)opentime;
		_h_ += isopen;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bossid).append(",");
		_sb_.append(opentime).append(",");
		_sb_.append(isopen).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(WorldBossInfo _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = bossid - _o_.bossid;
		if (0 != _c_) return _c_;
		_c_ = Long.signum(opentime - _o_.opentime);
		if (0 != _c_) return _c_;
		_c_ = isopen - _o_.isopen;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

