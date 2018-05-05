
package lx.gs.pay;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;
// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SPaySuccessNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SPaySuccessNotify extends __SPaySuccessNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6555405;

	public int getType() {
		return 6555405;
	}

	public int productid;
	public int dailypaystatus;
	public int dailytotalpaystatus;
	public int yuanbao; // 本次充值获得的元宝
	public int bindyuanbao; // 本次充值获得的绑定元宝

	public SPaySuccessNotify() {
	}

	public SPaySuccessNotify(int _productid_, int _dailypaystatus_, int _dailytotalpaystatus_, int _yuanbao_, int _bindyuanbao_) {
		this.productid = _productid_;
		this.dailypaystatus = _dailypaystatus_;
		this.dailytotalpaystatus = _dailytotalpaystatus_;
		this.yuanbao = _yuanbao_;
		this.bindyuanbao = _bindyuanbao_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(productid);
		_os_.marshal(dailypaystatus);
		_os_.marshal(dailytotalpaystatus);
		_os_.marshal(yuanbao);
		_os_.marshal(bindyuanbao);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		productid = _os_.unmarshal_int();
		dailypaystatus = _os_.unmarshal_int();
		dailytotalpaystatus = _os_.unmarshal_int();
		yuanbao = _os_.unmarshal_int();
		bindyuanbao = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SPaySuccessNotify) {
			SPaySuccessNotify _o_ = (SPaySuccessNotify)_o1_;
			if (productid != _o_.productid) return false;
			if (dailypaystatus != _o_.dailypaystatus) return false;
			if (dailytotalpaystatus != _o_.dailytotalpaystatus) return false;
			if (yuanbao != _o_.yuanbao) return false;
			if (bindyuanbao != _o_.bindyuanbao) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += productid;
		_h_ += dailypaystatus;
		_h_ += dailytotalpaystatus;
		_h_ += yuanbao;
		_h_ += bindyuanbao;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(productid).append(",");
		_sb_.append(dailypaystatus).append(",");
		_sb_.append(dailytotalpaystatus).append(",");
		_sb_.append(yuanbao).append(",");
		_sb_.append(bindyuanbao).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(SPaySuccessNotify _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = productid - _o_.productid;
		if (0 != _c_) return _c_;
		_c_ = dailypaystatus - _o_.dailypaystatus;
		if (0 != _c_) return _c_;
		_c_ = dailytotalpaystatus - _o_.dailytotalpaystatus;
		if (0 != _c_) return _c_;
		_c_ = yuanbao - _o_.yuanbao;
		if (0 != _c_) return _c_;
		_c_ = bindyuanbao - _o_.bindyuanbao;
		if (0 != _c_) return _c_;
		return _c_;
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

