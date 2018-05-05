
package lx.gs.dailyactivity.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class Undoactiveinfo implements Marshal {
	public int eventtype;
	public int undotimes;
	public long costjinbi;
	public long costyuanbao;
	public map.msg.Bonus jinbifindbackbonus;
	public map.msg.Bonus yuanbaofindbackbonus;

	public Undoactiveinfo() {
		jinbifindbackbonus = new map.msg.Bonus();
		yuanbaofindbackbonus = new map.msg.Bonus();
	}

	public Undoactiveinfo(int _eventtype_, int _undotimes_, long _costjinbi_, long _costyuanbao_, map.msg.Bonus _jinbifindbackbonus_, map.msg.Bonus _yuanbaofindbackbonus_) {
		this.eventtype = _eventtype_;
		this.undotimes = _undotimes_;
		this.costjinbi = _costjinbi_;
		this.costyuanbao = _costyuanbao_;
		this.jinbifindbackbonus = _jinbifindbackbonus_;
		this.yuanbaofindbackbonus = _yuanbaofindbackbonus_;
	}

	public final boolean _validator_() {
		if (!jinbifindbackbonus._validator_()) return false;
		if (!yuanbaofindbackbonus._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(eventtype);
		_os_.marshal(undotimes);
		_os_.marshal(costjinbi);
		_os_.marshal(costyuanbao);
		_os_.marshal(jinbifindbackbonus);
		_os_.marshal(yuanbaofindbackbonus);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		eventtype = _os_.unmarshal_int();
		undotimes = _os_.unmarshal_int();
		costjinbi = _os_.unmarshal_long();
		costyuanbao = _os_.unmarshal_long();
		jinbifindbackbonus.unmarshal(_os_);
		yuanbaofindbackbonus.unmarshal(_os_);
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Undoactiveinfo) {
			Undoactiveinfo _o_ = (Undoactiveinfo)_o1_;
			if (eventtype != _o_.eventtype) return false;
			if (undotimes != _o_.undotimes) return false;
			if (costjinbi != _o_.costjinbi) return false;
			if (costyuanbao != _o_.costyuanbao) return false;
			if (!jinbifindbackbonus.equals(_o_.jinbifindbackbonus)) return false;
			if (!yuanbaofindbackbonus.equals(_o_.yuanbaofindbackbonus)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += eventtype;
		_h_ += undotimes;
		_h_ += (int)costjinbi;
		_h_ += (int)costyuanbao;
		_h_ += jinbifindbackbonus.hashCode();
		_h_ += yuanbaofindbackbonus.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(eventtype).append(",");
		_sb_.append(undotimes).append(",");
		_sb_.append(costjinbi).append(",");
		_sb_.append(costyuanbao).append(",");
		_sb_.append(jinbifindbackbonus).append(",");
		_sb_.append(yuanbaofindbackbonus).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

