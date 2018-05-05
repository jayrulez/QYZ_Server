
package map.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class FighterBuilder implements Marshal {
	public map.msg.AgentBuilder basic;
	public int camp;
	public int appeartype; // revive,born,dead 见cfg.fight.AppearType

	public FighterBuilder() {
		basic = new map.msg.AgentBuilder();
	}

	public FighterBuilder(map.msg.AgentBuilder _basic_, int _camp_, int _appeartype_) {
		this.basic = _basic_;
		this.camp = _camp_;
		this.appeartype = _appeartype_;
	}

	public final boolean _validator_() {
		if (!basic._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(basic);
		_os_.marshal(camp);
		_os_.marshal(appeartype);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		basic.unmarshal(_os_);
		camp = _os_.unmarshal_int();
		appeartype = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FighterBuilder) {
			FighterBuilder _o_ = (FighterBuilder)_o1_;
			if (!basic.equals(_o_.basic)) return false;
			if (camp != _o_.camp) return false;
			if (appeartype != _o_.appeartype) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += basic.hashCode();
		_h_ += camp;
		_h_ += appeartype;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(basic).append(",");
		_sb_.append(camp).append(",");
		_sb_.append(appeartype).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

