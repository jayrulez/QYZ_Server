
package lx.gs.equip;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 衣服，帽子，鞋子，武器
*/
public class NormalEquipProp implements Marshal , Comparable<NormalEquipProp>{
	public int anneallevel; // 装备强化等级
	public int perfuselevel; // 装备灌注等级

	public NormalEquipProp() {
	}

	public NormalEquipProp(int _anneallevel_, int _perfuselevel_) {
		this.anneallevel = _anneallevel_;
		this.perfuselevel = _perfuselevel_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(anneallevel);
		_os_.marshal(perfuselevel);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		anneallevel = _os_.unmarshal_int();
		perfuselevel = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof NormalEquipProp) {
			NormalEquipProp _o_ = (NormalEquipProp)_o1_;
			if (anneallevel != _o_.anneallevel) return false;
			if (perfuselevel != _o_.perfuselevel) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += anneallevel;
		_h_ += perfuselevel;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(anneallevel).append(",");
		_sb_.append(perfuselevel).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

	public int compareTo(NormalEquipProp _o_) {
		if (_o_ == this) return 0;
		int _c_ = 0;
		_c_ = anneallevel - _o_.anneallevel;
		if (0 != _c_) return _c_;
		_c_ = perfuselevel - _o_.perfuselevel;
		if (0 != _c_) return _c_;
		return _c_;
	}

}

