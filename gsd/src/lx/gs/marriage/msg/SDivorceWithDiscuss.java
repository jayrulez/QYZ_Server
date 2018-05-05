
package lx.gs.marriage.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SDivorceWithDiscuss__ extends xio.Protocol { }

/** 协商离婚的结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SDivorceWithDiscuss extends __SDivorceWithDiscuss__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6578133;

	public int getType() {
		return 6578133;
	}

	public long bedivorceroleid; // 被离婚对象的id
	public java.lang.String bedivorcerolename; // 被离婚对象的名字
	public int divorceresult; // 0表示离婚失败，1表示离婚成功

	public SDivorceWithDiscuss() {
		bedivorcerolename = "";
	}

	public SDivorceWithDiscuss(long _bedivorceroleid_, java.lang.String _bedivorcerolename_, int _divorceresult_) {
		this.bedivorceroleid = _bedivorceroleid_;
		this.bedivorcerolename = _bedivorcerolename_;
		this.divorceresult = _divorceresult_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(bedivorceroleid);
		_os_.marshal(bedivorcerolename, "UTF-16LE");
		_os_.marshal(divorceresult);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		bedivorceroleid = _os_.unmarshal_long();
		bedivorcerolename = _os_.unmarshal_String("UTF-16LE");
		divorceresult = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SDivorceWithDiscuss) {
			SDivorceWithDiscuss _o_ = (SDivorceWithDiscuss)_o1_;
			if (bedivorceroleid != _o_.bedivorceroleid) return false;
			if (!bedivorcerolename.equals(_o_.bedivorcerolename)) return false;
			if (divorceresult != _o_.divorceresult) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)bedivorceroleid;
		_h_ += bedivorcerolename.hashCode();
		_h_ += divorceresult;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(bedivorceroleid).append(",");
		_sb_.append("T").append(bedivorcerolename.length()).append(",");
		_sb_.append(divorceresult).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

