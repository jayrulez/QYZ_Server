
package lx.gs.activity.huiwu.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class ChampionInfo implements Marshal {
	public lx.gs.role.msg.RoleShowInfo1 showinfo;
	public java.lang.String awardword;
	public int worshipnum;
	public long createtime;

	public ChampionInfo() {
		showinfo = new lx.gs.role.msg.RoleShowInfo1();
		awardword = "";
	}

	public ChampionInfo(lx.gs.role.msg.RoleShowInfo1 _showinfo_, java.lang.String _awardword_, int _worshipnum_, long _createtime_) {
		this.showinfo = _showinfo_;
		this.awardword = _awardword_;
		this.worshipnum = _worshipnum_;
		this.createtime = _createtime_;
	}

	public final boolean _validator_() {
		if (!showinfo._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(showinfo);
		_os_.marshal(awardword, "UTF-16LE");
		_os_.marshal(worshipnum);
		_os_.marshal(createtime);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		showinfo.unmarshal(_os_);
		awardword = _os_.unmarshal_String("UTF-16LE");
		worshipnum = _os_.unmarshal_int();
		createtime = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof ChampionInfo) {
			ChampionInfo _o_ = (ChampionInfo)_o1_;
			if (!showinfo.equals(_o_.showinfo)) return false;
			if (!awardword.equals(_o_.awardword)) return false;
			if (worshipnum != _o_.worshipnum) return false;
			if (createtime != _o_.createtime) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += showinfo.hashCode();
		_h_ += awardword.hashCode();
		_h_ += worshipnum;
		_h_ += (int)createtime;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(showinfo).append(",");
		_sb_.append("T").append(awardword.length()).append(",");
		_sb_.append(worshipnum).append(",");
		_sb_.append(createtime).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

