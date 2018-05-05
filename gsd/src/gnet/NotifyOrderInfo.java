package gnet;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __NotifyOrderInfo__ extends xio.Protocol { }

/** 支付通知
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class NotifyOrderInfo extends __NotifyOrderInfo__ {
	@Override
	protected void process() {
		new lx.gs.pay.PPayOrderCallback(this).execute();
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 141;

	public int getType() {
		return 141;
	}

	public gnet.PlatType plattype; // 平台唯一标识
	public java.lang.String platorderid; // 平台用的订单号
	public java.lang.String gsorderid; // 游戏内自己用的订单号
	public long userid;
	public com.goldhuman.Common.Octets vars;

	public NotifyOrderInfo() {
		plattype = new gnet.PlatType();
		platorderid = "";
		gsorderid = "";
		userid = -1;
		vars = new com.goldhuman.Common.Octets();
	}

	public NotifyOrderInfo(gnet.PlatType _plattype_, java.lang.String _platorderid_, java.lang.String _gsorderid_, long _userid_, com.goldhuman.Common.Octets _vars_) {
		this.plattype = _plattype_;
		this.platorderid = _platorderid_;
		this.gsorderid = _gsorderid_;
		this.userid = _userid_;
		this.vars = _vars_;
	}

	public final boolean _validator_() {
		if (!plattype._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(plattype);
		_os_.marshal(platorderid, "UTF-16LE");
		_os_.marshal(gsorderid, "UTF-16LE");
		_os_.marshal(userid);
		_os_.marshal(vars);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		plattype.unmarshal(_os_);
		platorderid = _os_.unmarshal_String("UTF-16LE");
		gsorderid = _os_.unmarshal_String("UTF-16LE");
		userid = _os_.unmarshal_long();
		vars = _os_.unmarshal_Octets();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NotifyOrderInfo) {
			NotifyOrderInfo _o_ = (NotifyOrderInfo)_o1_;
			if (!plattype.equals(_o_.plattype)) return false;
			if (!platorderid.equals(_o_.platorderid)) return false;
			if (!gsorderid.equals(_o_.gsorderid)) return false;
			if (userid != _o_.userid) return false;
			if (!vars.equals(_o_.vars)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += plattype.hashCode();
		_h_ += platorderid.hashCode();
		_h_ += gsorderid.hashCode();
		_h_ += (int)userid;
		_h_ += vars.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(plattype).append(",");
		_sb_.append("T").append(platorderid.length()).append(",");
		_sb_.append("T").append(gsorderid.length()).append(",");
		_sb_.append(userid).append(",");
		_sb_.append("B").append(vars.size()).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

