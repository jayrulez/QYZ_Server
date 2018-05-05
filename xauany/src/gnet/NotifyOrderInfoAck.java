
package gnet;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __NotifyOrderInfoAck__ extends xio.Protocol { }

/** gs 说反馈说是否收到
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class NotifyOrderInfoAck extends __NotifyOrderInfoAck__ {
	@Override
	protected void process() {
		xauany.OrderNotifyMgr.INSTANCE.notifyOrderInfoAck(this);
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 142;

	public int getType() {
		return 142;
	}

	public final static int ERR_SUCCEED = 0;
	public final static int ERR_ORDER_UNKNOWN = 1;
	public final static int ERR_USER_UNKNOWN = 2;
	public final static int ERR_VARS_INVALID = 3;

	public int errcode;
	public gnet.PlatType plattype;
	public java.lang.String platorderid; // 平台用的订单号
	public java.lang.String gsorderid; // 游戏内自己用的订单号
	public long userid;

	public NotifyOrderInfoAck() {
		plattype = new gnet.PlatType();
		platorderid = "";
		gsorderid = "";
		userid = -1;
	}

	public NotifyOrderInfoAck(int _errcode_, gnet.PlatType _plattype_, java.lang.String _platorderid_, java.lang.String _gsorderid_, long _userid_) {
		this.errcode = _errcode_;
		this.plattype = _plattype_;
		this.platorderid = _platorderid_;
		this.gsorderid = _gsorderid_;
		this.userid = _userid_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(errcode);
		_os_.marshal(plattype);
		_os_.marshal(platorderid, "UTF-16LE");
		_os_.marshal(gsorderid, "UTF-16LE");
		_os_.marshal(userid);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		errcode = _os_.unmarshal_int();
		plattype.unmarshal(_os_);
		platorderid = _os_.unmarshal_String("UTF-16LE");
		gsorderid = _os_.unmarshal_String("UTF-16LE");
		userid = _os_.unmarshal_long();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NotifyOrderInfoAck) {
			NotifyOrderInfoAck _o_ = (NotifyOrderInfoAck)_o1_;
			if (errcode != _o_.errcode) return false;
			if (!plattype.equals(_o_.plattype)) return false;
			if (!platorderid.equals(_o_.platorderid)) return false;
			if (!gsorderid.equals(_o_.gsorderid)) return false;
			if (userid != _o_.userid) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += errcode;
		_h_ += plattype.hashCode();
		_h_ += platorderid.hashCode();
		_h_ += gsorderid.hashCode();
		_h_ += (int)userid;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(errcode).append(",");
		_sb_.append(plattype).append(",");
		_sb_.append("T").append(platorderid.length()).append(",");
		_sb_.append("T").append(gsorderid.length()).append(",");
		_sb_.append(userid).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

