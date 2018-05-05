
package lx.gs.jade;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class RoleJadeInfo implements Marshal {
	public lx.gs.jade.Jade jade; // 玉佩等级
	public int holenum; // 打开的孔数量
	public java.util.HashMap<Integer,lx.gs.jade.Jewelry> jewelry; // 装备到玉佩上的宝珠
	public java.util.ArrayList<lx.gs.jade.Jewelry> jewelrybag; // 宝珠背包
	public int jewelrygetlevel; // 猎取师档位

	public RoleJadeInfo() {
		jade = new lx.gs.jade.Jade();
		jewelry = new java.util.HashMap<Integer,lx.gs.jade.Jewelry>();
		jewelrybag = new java.util.ArrayList<lx.gs.jade.Jewelry>();
	}

	public RoleJadeInfo(lx.gs.jade.Jade _jade_, int _holenum_, java.util.HashMap<Integer,lx.gs.jade.Jewelry> _jewelry_, java.util.ArrayList<lx.gs.jade.Jewelry> _jewelrybag_, int _jewelrygetlevel_) {
		this.jade = _jade_;
		this.holenum = _holenum_;
		this.jewelry = _jewelry_;
		this.jewelrybag = _jewelrybag_;
		this.jewelrygetlevel = _jewelrygetlevel_;
	}

	public final boolean _validator_() {
		if (!jade._validator_()) return false;
		for (java.util.Map.Entry<Integer, lx.gs.jade.Jewelry> _e_ : jewelry.entrySet()) {
			if (!_e_.getValue()._validator_()) return false;
		}
		for (lx.gs.jade.Jewelry _v_ : jewelrybag)
			if (!_v_._validator_()) return false;
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(jade);
		_os_.marshal(holenum);
		_os_.compact_uint32(jewelry.size());
		for (java.util.Map.Entry<Integer, lx.gs.jade.Jewelry> _e_ : jewelry.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.compact_uint32(jewelrybag.size());
		for (lx.gs.jade.Jewelry _v_ : jewelrybag) {
			_os_.marshal(_v_);
		}
		_os_.marshal(jewelrygetlevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		jade.unmarshal(_os_);
		holenum = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			lx.gs.jade.Jewelry _v_ = new lx.gs.jade.Jewelry();
			_v_.unmarshal(_os_);
			jewelry.put(_k_, _v_);
		}
		for (int _size_ = _os_.uncompact_uint32(); _size_ > 0; --_size_) {
			lx.gs.jade.Jewelry _v_ = new lx.gs.jade.Jewelry();
			_v_.unmarshal(_os_);
			jewelrybag.add(_v_);
		}
		jewelrygetlevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof RoleJadeInfo) {
			RoleJadeInfo _o_ = (RoleJadeInfo)_o1_;
			if (!jade.equals(_o_.jade)) return false;
			if (holenum != _o_.holenum) return false;
			if (!jewelry.equals(_o_.jewelry)) return false;
			if (!jewelrybag.equals(_o_.jewelrybag)) return false;
			if (jewelrygetlevel != _o_.jewelrygetlevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += jade.hashCode();
		_h_ += holenum;
		_h_ += jewelry.hashCode();
		_h_ += jewelrybag.hashCode();
		_h_ += jewelrygetlevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(jade).append(",");
		_sb_.append(holenum).append(",");
		_sb_.append(jewelry).append(",");
		_sb_.append(jewelrybag).append(",");
		_sb_.append(jewelrygetlevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

