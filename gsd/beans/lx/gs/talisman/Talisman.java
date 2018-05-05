
package lx.gs.talisman;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

/** 每个法宝的具体信息
*/
public class Talisman implements Marshal {
	public long talismanid;
	public int modelid; // 法宝的策划配置id
	public int pos; // 物品在包裹中的位置，从0开始编号
	public int isbind; // 法宝绑定类型
	public long normalexp; // 法宝普通经验
	public int normallevel; // 法宝普通等级,开始为1
	public int starexp; // 法宝星阶经验
	public int starlevel; // 法宝星阶等级,开始为1
	public int wuxingtype; // 法宝当前的五行属性类型
	public int wuxingvalue; // 五行属性攻击值
	public int awakelevel; // 法宝觉醒等级,觉醒后提升高级属性
	public java.util.HashMap<Integer,Integer> skills;
	public int combatpower; // 法宝战力

	public Talisman() {
		normallevel = 1;
		starlevel = 1;
		skills = new java.util.HashMap<Integer,Integer>();
	}

	public Talisman(long _talismanid_, int _modelid_, int _pos_, int _isbind_, long _normalexp_, int _normallevel_, int _starexp_, int _starlevel_, int _wuxingtype_, int _wuxingvalue_, int _awakelevel_, java.util.HashMap<Integer,Integer> _skills_, int _combatpower_) {
		this.talismanid = _talismanid_;
		this.modelid = _modelid_;
		this.pos = _pos_;
		this.isbind = _isbind_;
		this.normalexp = _normalexp_;
		this.normallevel = _normallevel_;
		this.starexp = _starexp_;
		this.starlevel = _starlevel_;
		this.wuxingtype = _wuxingtype_;
		this.wuxingvalue = _wuxingvalue_;
		this.awakelevel = _awakelevel_;
		this.skills = _skills_;
		this.combatpower = _combatpower_;
	}

	public final boolean _validator_() {
		return true;
	}

	public OctetsStream marshal(OctetsStream _os_) {
		_os_.marshal(talismanid);
		_os_.marshal(modelid);
		_os_.marshal(pos);
		_os_.marshal(isbind);
		_os_.marshal(normalexp);
		_os_.marshal(normallevel);
		_os_.marshal(starexp);
		_os_.marshal(starlevel);
		_os_.marshal(wuxingtype);
		_os_.marshal(wuxingvalue);
		_os_.marshal(awakelevel);
		_os_.compact_uint32(skills.size());
		for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet()) {
			_os_.marshal(_e_.getKey());
			_os_.marshal(_e_.getValue());
		}
		_os_.marshal(combatpower);
		return _os_;
	}

	public OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
		talismanid = _os_.unmarshal_long();
		modelid = _os_.unmarshal_int();
		pos = _os_.unmarshal_int();
		isbind = _os_.unmarshal_int();
		normalexp = _os_.unmarshal_long();
		normallevel = _os_.unmarshal_int();
		starexp = _os_.unmarshal_int();
		starlevel = _os_.unmarshal_int();
		wuxingtype = _os_.unmarshal_int();
		wuxingvalue = _os_.unmarshal_int();
		awakelevel = _os_.unmarshal_int();
		for (int size = _os_.uncompact_uint32(); size > 0; --size) {
			int _k_;
			_k_ = _os_.unmarshal_int();
			int _v_;
			_v_ = _os_.unmarshal_int();
			skills.put(_k_, _v_);
		}
		combatpower = _os_.unmarshal_int();
		return _os_;
	}

	public boolean equals(Object _o1_) {
		if (_o1_ == this) return true;
		if (_o1_ instanceof Talisman) {
			Talisman _o_ = (Talisman)_o1_;
			if (talismanid != _o_.talismanid) return false;
			if (modelid != _o_.modelid) return false;
			if (pos != _o_.pos) return false;
			if (isbind != _o_.isbind) return false;
			if (normalexp != _o_.normalexp) return false;
			if (normallevel != _o_.normallevel) return false;
			if (starexp != _o_.starexp) return false;
			if (starlevel != _o_.starlevel) return false;
			if (wuxingtype != _o_.wuxingtype) return false;
			if (wuxingvalue != _o_.wuxingvalue) return false;
			if (awakelevel != _o_.awakelevel) return false;
			if (!skills.equals(_o_.skills)) return false;
			if (combatpower != _o_.combatpower) return false;
			return true;
		}
		return false;
	}

	public int hashCode() {
		int _h_ = 0;
		_h_ += (int)talismanid;
		_h_ += modelid;
		_h_ += pos;
		_h_ += isbind;
		_h_ += (int)normalexp;
		_h_ += normallevel;
		_h_ += starexp;
		_h_ += starlevel;
		_h_ += wuxingtype;
		_h_ += wuxingvalue;
		_h_ += awakelevel;
		_h_ += skills.hashCode();
		_h_ += combatpower;
		return _h_;
	}

	public String toString() {
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(talismanid).append(",");
		_sb_.append(modelid).append(",");
		_sb_.append(pos).append(",");
		_sb_.append(isbind).append(",");
		_sb_.append(normalexp).append(",");
		_sb_.append(normallevel).append(",");
		_sb_.append(starexp).append(",");
		_sb_.append(starlevel).append(",");
		_sb_.append(wuxingtype).append(",");
		_sb_.append(wuxingvalue).append(",");
		_sb_.append(awakelevel).append(",");
		_sb_.append(skills).append(",");
		_sb_.append(combatpower).append(",");
		_sb_.append(")");
		return _sb_.toString();
	}

}

