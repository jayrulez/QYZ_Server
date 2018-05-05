
package lx.gs.jade;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

// {{{ RPCGEN_IMPORT_BEGIN
// {{{ DO NOT EDIT THIS

abstract class __SHuntJewelry__ extends xio.Protocol { }

// DO NOT EDIT THIS }}}
// RPCGEN_IMPORT_END }}}

public class SHuntJewelry extends lx.gs.jade.__SHuntJewelry__ {
	@Override
	protected void process() {
		// protocol handle
	}

	// {{{ RPCGEN_DEFINE_BEGIN
	// {{{ DO NOT EDIT THIS
	public static final int PROTOCOL_TYPE = 6581117;

	public int getType() {
		return 6581117;
	}

	public int num; // 猎取次数
	public int jewelrygetlevel; // 猎取师档位
	public java.util.ArrayList<lx.gs.jade.Jewelry> jewelrylist; // 宝珠背包

	public SHuntJewelry() {
		jewelrylist = new java.util.ArrayList<lx.gs.jade.Jewelry>();
	}

	public SHuntJewelry(int _num_, int _jewelrygetlevel_, java.util.ArrayList<lx.gs.jade.Jewelry> _jewelrylist_) {
		this.num = _num_;
		this.jewelrygetlevel = _jewelrygetlevel_;
		this.jewelrylist = _jewelrylist_;
	}

	public final boolean _validator_() {
		for (lx.gs.jade.Jewelry _v_ : jewelrylist)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(num);
		_os_.marshal(jewelrygetlevel);
		_os_.compact_uint32(jewelrylist.size());
		for (lx.gs.jade.Jewelry _v_ : jewelrylist) {
			_os_.marshal(_v_);
		}
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		num = _os_.unmarshal_int();
		jewelrygetlevel = _os_.unmarshal_int();
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.jade.Jewelry _v_ = new lx.gs.jade.Jewelry();
			_v_.unmarshal(_os_);
			jewelrylist.add(_v_);
		}
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof SHuntJewelry) {
			SHuntJewelry _o_ = (SHuntJewelry)_o1_;
			if (num != _o_.num) return false;
			if (jewelrygetlevel != _o_.jewelrygetlevel) return false;
			if (!jewelrylist.equals(_o_.jewelrylist)) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += num;
		_h_ += jewelrygetlevel;
		_h_ += jewelrylist.hashCode();
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(num).append(",");
		_sb_.append(jewelrygetlevel).append(",");
		_sb_.append(jewelrylist).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	// DO NOT EDIT THIS }}}
	// RPCGEN_DEFINE_END }}}

}

