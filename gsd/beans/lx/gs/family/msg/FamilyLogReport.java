
package lx.gs.family.msg;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 家族日志记录
*/
public class FamilyLogReport implements Marshal {
	public final static int TYPE_JOIN_FAMILY = 1;
	public final static int TYPE_PROMOTION = 2;
	public final static int TYPE_QUIT_FAMILY = 3;
	public final static int TYPE_CHIEF_TRANSFER = 4;
	public final static int TYPE_UPLEVEL_FAMILY = 5;
	public final static int TYPE_PRAY = 6;
	public final static int TYPE_RAISE_ANIMAL = 7;
	public final static int TYPE_CREATE_FAMILY = 8;
	public final static int TYPE_UPLEVEL_MALL = 9;
	public final static int TYPE_KICKOUT_MEMBER = 10;

	public int actiontype; // 家族日志类型，入帮，升职等等
	public long roleid; // 角色id
	public long actiontime; // 日志记录的时间
	public java.lang.String rolename; // 角色名字
	public int number; // 动作对应的数值，如果有的话,比如如果是promotion，那么对应promotion的职位id，如果是烧香，对应的是增加的帮贡，如果是升级，对应的升级到的level

	public FamilyLogReport() {
		rolename = "";
	}

	public FamilyLogReport(int _actiontype_, long _roleid_, long _actiontime_, java.lang.String _rolename_, int _number_) {
		this.actiontype = _actiontype_;
		this.roleid = _roleid_;
		this.actiontime = _actiontime_;
		this.rolename = _rolename_;
		this.number = _number_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(actiontype);
		_os_.marshal(roleid);
		_os_.marshal(actiontime);
		_os_.marshal(rolename, "UTF-16LE");
		_os_.marshal(number);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		actiontype = _os_.unmarshal_int();
		roleid = _os_.unmarshal_long();
		actiontime = _os_.unmarshal_long();
		rolename = _os_.unmarshal_String("UTF-16LE");
		number = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof FamilyLogReport) {
			FamilyLogReport _o_ = (FamilyLogReport)_o1_;
			if (actiontype != _o_.actiontype) return false;
			if (roleid != _o_.roleid) return false;
			if (actiontime != _o_.actiontime) return false;
			if (!rolename.equals(_o_.rolename)) return false;
			if (number != _o_.number) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += actiontype;
		_h_ += (int)roleid;
		_h_ += (int)actiontime;
		_h_ += rolename.hashCode();
		_h_ += number;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(actiontype).append(",");
		_sb_.append(roleid).append(",");
		_sb_.append(actiontime).append(",");
		_sb_.append("T").append(rolename.length()).append(",");
		_sb_.append(number).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

