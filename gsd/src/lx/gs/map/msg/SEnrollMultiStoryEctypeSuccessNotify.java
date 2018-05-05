
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SEnrollMultiStoryEctypeSuccessNotify__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SEnrollMultiStoryEctypeSuccessNotify extends __SEnrollMultiStoryEctypeSuccessNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6576190;

	public int getType() {
		return 6576190;
	}

	public int lefttime; // 匹配成功，通知剩余开启时间
	public java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo> roleinfos;

	public SEnrollMultiStoryEctypeSuccessNotify() {
		roleinfos = new java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo>();
	}

	public SEnrollMultiStoryEctypeSuccessNotify(int _lefttime_, java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo> _roleinfos_) {
		this.lefttime = _lefttime_;
		this.roleinfos = _roleinfos_;
	}

	public final boolean _validator_() {
		for (lx.gs.map.msg.EnrollBriefInfo _v_ : roleinfos)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(lefttime);
		_os_.compact_uint32(roleinfos.size());
		for (lx.gs.map.msg.EnrollBriefInfo _v_ : roleinfos) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		lefttime = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.map.msg.EnrollBriefInfo _v_ = new lx.gs.map.msg.EnrollBriefInfo();
			_v_.unmarshal(_os_);
			roleinfos.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SEnrollMultiStoryEctypeSuccessNotify) {
			SEnrollMultiStoryEctypeSuccessNotify _o_ = (SEnrollMultiStoryEctypeSuccessNotify)_o1_;
			if (lefttime != _o_.lefttime) return false;
			if (!roleinfos.equals(_o_.roleinfos)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += lefttime;
		_h_ += roleinfos.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(lefttime).append(",");
		_sb_.append(roleinfos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

