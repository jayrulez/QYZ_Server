
package lx.gs.amulet;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SApplyAmuletWashResult__ extends xio.Protocol { }

/** 确认应用洗练结果
*/
// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SApplyAmuletWashResult extends lx.gs.amulet.__SApplyAmuletWashResult__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6573512;

	public int getType() {
		return 6573512;
	}

	public int pageid; // 洗练页面
	public java.util.HashMap<Integer,lx.gs.amulet.AmuletPropperty> changeprop; // 护符属性

	public SApplyAmuletWashResult() {
		changeprop = new java.util.HashMap<Integer,lx.gs.amulet.AmuletPropperty>();
	}

	public SApplyAmuletWashResult(int _pageid_, java.util.HashMap<Integer,lx.gs.amulet.AmuletPropperty> _changeprop_) {
		this.pageid = _pageid_;
		this.changeprop = _changeprop_;
	}

	public final boolean _validator_() {
		for (java.util.Map.Entry<Integer, lx.gs.amulet.AmuletPropperty> _e_ : changeprop.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(pageid);
		_os_.compact_uint32(changeprop.size());
		for (java.util.Map.Entry<Integer, lx.gs.amulet.AmuletPropperty> _e_ : changeprop.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		pageid = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.amulet.AmuletPropperty _v_ = new lx.gs.amulet.AmuletPropperty();
			_v_.unmarshal(_os_);
			changeprop.put(_k_, _v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SApplyAmuletWashResult) {
			SApplyAmuletWashResult _o_ = (SApplyAmuletWashResult)_o1_;
			if (pageid != _o_.pageid) return false;
			if (!changeprop.equals(_o_.changeprop)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += pageid;
		_h_ += changeprop.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(pageid).append(",");
		_sb_.append(changeprop).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

