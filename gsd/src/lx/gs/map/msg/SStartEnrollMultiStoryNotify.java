
package lx.gs.map.msg;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SStartEnrollMultiStoryNotify__ extends xio.Protocol { }

/** 开始匹配后通知所有玩家
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SStartEnrollMultiStoryNotify extends __SStartEnrollMultiStoryNotify__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6563658;

	public int getType() {
		return 6563658;
	}

	public int ectypeid; // 匹配中的副本
	public java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo> roleinfos;

	public SStartEnrollMultiStoryNotify() {
		roleinfos = new java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo>();
	}

	public SStartEnrollMultiStoryNotify(int _ectypeid_, java.util.ArrayList<lx.gs.map.msg.EnrollBriefInfo> _roleinfos_) {
		this.ectypeid = _ectypeid_;
		this.roleinfos = _roleinfos_;
	}

	public final boolean _validator_() {
		for (lx.gs.map.msg.EnrollBriefInfo _v_ : roleinfos)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(ectypeid);
		_os_.compact_uint32(roleinfos.size());
		for (lx.gs.map.msg.EnrollBriefInfo _v_ : roleinfos) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		ectypeid = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.map.msg.EnrollBriefInfo _v_ = new lx.gs.map.msg.EnrollBriefInfo();
			_v_.unmarshal(_os_);
			roleinfos.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SStartEnrollMultiStoryNotify) {
			SStartEnrollMultiStoryNotify _o_ = (SStartEnrollMultiStoryNotify)_o1_;
			if (ectypeid != _o_.ectypeid) return false;
			if (!roleinfos.equals(_o_.roleinfos)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += ectypeid;
		_h_ += roleinfos.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(ectypeid).append(",");
		_sb_.append(roleinfos).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

