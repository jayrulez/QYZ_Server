
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Talisman extends xdb.XBean implements xbean.Talisman {
	private long talismanid; // 
	private int modelid; // 法宝的策划配置id
	private int pos; // 物品在包裹中的位置，从1开始编号
	private boolean isbind; // 法宝绑定类型
	private long expiretime; // 过期时间
	private long normalexp; // 法宝普通经验
	private int normallevel; // 法宝普通等级,开始为1
	private int starexp; // 法宝星阶经验
	private int starlevel; // 法宝星阶等级,开始为1
	private int wuxingtype; // 法宝当前的五行属性类型
	private int wuxingvalue; // 五行属性攻击值
	private int awakelevel; // 法宝觉醒等级,从0开始
	private java.util.HashMap<Integer, Integer> skills; // 法宝技能

	@Override
	public void _reset_unsafe_() {
		talismanid = 0L;
		modelid = 0;
		pos = 0;
		isbind = false;
		expiretime = 0L;
		normalexp = 0L;
		normallevel = 1;
		starexp = 0;
		starlevel = 1;
		wuxingtype = 0;
		wuxingvalue = 0;
		awakelevel = 0;
		skills.clear();
	}

	Talisman(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		normallevel = 1;
		starlevel = 1;
		skills = new java.util.HashMap<Integer, Integer>();
	}

	public Talisman() {
		this(0, null, null);
	}

	public Talisman(Talisman _o_) {
		this(_o_, null, null);
	}

	Talisman(xbean.Talisman _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Talisman) assign((Talisman)_o1_);
		else if (_o1_ instanceof Talisman.Data) assign((Talisman.Data)_o1_);
		else if (_o1_ instanceof Talisman.Const) assign(((Talisman.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Talisman _o_) {
		_o_._xdb_verify_unsafe_();
		talismanid = _o_.talismanid;
		modelid = _o_.modelid;
		pos = _o_.pos;
		isbind = _o_.isbind;
		expiretime = _o_.expiretime;
		normalexp = _o_.normalexp;
		normallevel = _o_.normallevel;
		starexp = _o_.starexp;
		starlevel = _o_.starlevel;
		wuxingtype = _o_.wuxingtype;
		wuxingvalue = _o_.wuxingvalue;
		awakelevel = _o_.awakelevel;
		skills = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(Talisman.Data _o_) {
		talismanid = _o_.talismanid;
		modelid = _o_.modelid;
		pos = _o_.pos;
		isbind = _o_.isbind;
		expiretime = _o_.expiretime;
		normalexp = _o_.normalexp;
		normallevel = _o_.normallevel;
		starexp = _o_.starexp;
		starlevel = _o_.starlevel;
		wuxingtype = _o_.wuxingtype;
		wuxingvalue = _o_.wuxingvalue;
		awakelevel = _o_.awakelevel;
		skills = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)13);
    _os_.marshal((short)(10240|  1));_os_.marshal(talismanid);
    _os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
    _os_.marshal((short)( 8192|  3));_os_.marshal(pos);
    _os_.marshal((short)( 2048|  4));_os_.marshal(isbind);
    _os_.marshal((short)(10240|  5));_os_.marshal(expiretime);
    _os_.marshal((short)(10240|  6));_os_.marshal(normalexp);
    _os_.marshal((short)( 8192|  7));_os_.marshal(normallevel);
    _os_.marshal((short)( 8192|  8));_os_.marshal(starexp);
    _os_.marshal((short)( 8192|  9));_os_.marshal(starlevel);
    _os_.marshal((short)( 8192| 10));_os_.marshal(wuxingtype);
    _os_.marshal((short)( 8192| 11));_os_.marshal(wuxingvalue);
    _os_.marshal((short)( 8192| 12));_os_.marshal(awakelevel);
    _os_.marshal((short)(24576| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  1):talismanid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):talismanid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):talismanid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  2):modelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):modelid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):pos = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):pos = _os_.unmarshal_short();
    				break;
    				case ( 2048|  4):isbind = _os_.unmarshal_boolean();
    				break;
    				case (10240|  5):expiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  5):expiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  5):expiretime = _os_.unmarshal_int();
    				break;
    				case (10240|  6):normalexp = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):normalexp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):normalexp = _os_.unmarshal_int();
    				break;
    				case ( 8192|  7):normallevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):normallevel = _os_.unmarshal_short();
    				break;
    				case ( 8192|  8):starexp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  8):starexp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  9):starlevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):starlevel = _os_.unmarshal_short();
    				break;
    				case ( 8192| 10):wuxingtype = _os_.unmarshal_int();
    				break;
    				case ( 6144| 10):wuxingtype = _os_.unmarshal_short();
    				break;
    				case ( 8192| 11):wuxingvalue = _os_.unmarshal_int();
    				break;
    				case ( 6144| 11):wuxingvalue = _os_.unmarshal_short();
    				break;
    				case ( 8192| 12):awakelevel = _os_.unmarshal_int();
    				break;
    				case ( 6144| 12):awakelevel = _os_.unmarshal_short();
    				break;
    				case (24576| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skills = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		skills.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Talisman copy() {
		_xdb_verify_unsafe_();
		return new Talisman(this);
	}

	@Override
	public xbean.Talisman toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Talisman toBean() {
		_xdb_verify_unsafe_();
		return new Talisman(this); // same as copy()
	}

	@Override
	public xbean.Talisman toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Talisman toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getTalismanid() { // 
		_xdb_verify_unsafe_();
		return talismanid;
	}

	@Override
	public int getModelid() { // 法宝的策划配置id
		_xdb_verify_unsafe_();
		return modelid;
	}

	@Override
	public int getPos() { // 物品在包裹中的位置，从1开始编号
		_xdb_verify_unsafe_();
		return pos;
	}

	@Override
	public boolean getIsbind() { // 法宝绑定类型
		_xdb_verify_unsafe_();
		return isbind;
	}

	@Override
	public long getExpiretime() { // 过期时间
		_xdb_verify_unsafe_();
		return expiretime;
	}

	@Override
	public long getNormalexp() { // 法宝普通经验
		_xdb_verify_unsafe_();
		return normalexp;
	}

	@Override
	public int getNormallevel() { // 法宝普通等级,开始为1
		_xdb_verify_unsafe_();
		return normallevel;
	}

	@Override
	public int getStarexp() { // 法宝星阶经验
		_xdb_verify_unsafe_();
		return starexp;
	}

	@Override
	public int getStarlevel() { // 法宝星阶等级,开始为1
		_xdb_verify_unsafe_();
		return starlevel;
	}

	@Override
	public int getWuxingtype() { // 法宝当前的五行属性类型
		_xdb_verify_unsafe_();
		return wuxingtype;
	}

	@Override
	public int getWuxingvalue() { // 五行属性攻击值
		_xdb_verify_unsafe_();
		return wuxingvalue;
	}

	@Override
	public int getAwakelevel() { // 法宝觉醒等级,从0开始
		_xdb_verify_unsafe_();
		return awakelevel;
	}

	@Override
	public java.util.Map<Integer, Integer> getSkills() { // 法宝技能
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "skills"), skills);
	}

	@Override
	public java.util.Map<Integer, Integer> getSkillsAsData() { // 法宝技能
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> skills;
		Talisman _o_ = this;
		skills = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), _e_.getValue());
		return skills;
	}

	@Override
	public void setTalismanid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "talismanid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, talismanid) {
					public void rollback() { talismanid = _xdb_saved; }
				};}});
		talismanid = _v_;
	}

	@Override
	public void setModelid(int _v_) { // 法宝的策划配置id
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "modelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, modelid) {
					public void rollback() { modelid = _xdb_saved; }
				};}});
		modelid = _v_;
	}

	@Override
	public void setPos(int _v_) { // 物品在包裹中的位置，从1开始编号
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "pos") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, pos) {
					public void rollback() { pos = _xdb_saved; }
				};}});
		pos = _v_;
	}

	@Override
	public void setIsbind(boolean _v_) { // 法宝绑定类型
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isbind") {
			protected xdb.Log create() {
				return new xdb.logs.LogObject<Boolean>(this, isbind) {
					public void rollback() { isbind = _xdb_saved; }
				};}});
		isbind = _v_;
	}

	@Override
	public void setExpiretime(long _v_) { // 过期时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "expiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, expiretime) {
					public void rollback() { expiretime = _xdb_saved; }
				};}});
		expiretime = _v_;
	}

	@Override
	public void setNormalexp(long _v_) { // 法宝普通经验
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "normalexp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, normalexp) {
					public void rollback() { normalexp = _xdb_saved; }
				};}});
		normalexp = _v_;
	}

	@Override
	public void setNormallevel(int _v_) { // 法宝普通等级,开始为1
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "normallevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, normallevel) {
					public void rollback() { normallevel = _xdb_saved; }
				};}});
		normallevel = _v_;
	}

	@Override
	public void setStarexp(int _v_) { // 法宝星阶经验
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "starexp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, starexp) {
					public void rollback() { starexp = _xdb_saved; }
				};}});
		starexp = _v_;
	}

	@Override
	public void setStarlevel(int _v_) { // 法宝星阶等级,开始为1
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "starlevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, starlevel) {
					public void rollback() { starlevel = _xdb_saved; }
				};}});
		starlevel = _v_;
	}

	@Override
	public void setWuxingtype(int _v_) { // 法宝当前的五行属性类型
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "wuxingtype") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, wuxingtype) {
					public void rollback() { wuxingtype = _xdb_saved; }
				};}});
		wuxingtype = _v_;
	}

	@Override
	public void setWuxingvalue(int _v_) { // 五行属性攻击值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "wuxingvalue") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, wuxingvalue) {
					public void rollback() { wuxingvalue = _xdb_saved; }
				};}});
		wuxingvalue = _v_;
	}

	@Override
	public void setAwakelevel(int _v_) { // 法宝觉醒等级,从0开始
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "awakelevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, awakelevel) {
					public void rollback() { awakelevel = _xdb_saved; }
				};}});
		awakelevel = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Talisman _o_ = null;
		if ( _o1_ instanceof Talisman ) _o_ = (Talisman)_o1_;
		else if ( _o1_ instanceof Talisman.Const ) _o_ = ((Talisman.Const)_o1_).nThis();
		else return false;
		if (talismanid != _o_.talismanid) return false;
		if (modelid != _o_.modelid) return false;
		if (pos != _o_.pos) return false;
		if (isbind != _o_.isbind) return false;
		if (expiretime != _o_.expiretime) return false;
		if (normalexp != _o_.normalexp) return false;
		if (normallevel != _o_.normallevel) return false;
		if (starexp != _o_.starexp) return false;
		if (starlevel != _o_.starlevel) return false;
		if (wuxingtype != _o_.wuxingtype) return false;
		if (wuxingvalue != _o_.wuxingvalue) return false;
		if (awakelevel != _o_.awakelevel) return false;
		if (!skills.equals(_o_.skills)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += talismanid;
		_h_ += modelid;
		_h_ += pos;
		_h_ += isbind ? 1231 : 1237;
		_h_ += expiretime;
		_h_ += normalexp;
		_h_ += normallevel;
		_h_ += starexp;
		_h_ += starlevel;
		_h_ += wuxingtype;
		_h_ += wuxingvalue;
		_h_ += awakelevel;
		_h_ += skills.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(talismanid);
		_sb_.append(",");
		_sb_.append(modelid);
		_sb_.append(",");
		_sb_.append(pos);
		_sb_.append(",");
		_sb_.append(isbind);
		_sb_.append(",");
		_sb_.append(expiretime);
		_sb_.append(",");
		_sb_.append(normalexp);
		_sb_.append(",");
		_sb_.append(normallevel);
		_sb_.append(",");
		_sb_.append(starexp);
		_sb_.append(",");
		_sb_.append(starlevel);
		_sb_.append(",");
		_sb_.append(wuxingtype);
		_sb_.append(",");
		_sb_.append(wuxingvalue);
		_sb_.append(",");
		_sb_.append(awakelevel);
		_sb_.append(",");
		_sb_.append(skills);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("talismanid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("pos"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isbind"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("expiretime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("normalexp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("normallevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("starexp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("starlevel"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("wuxingtype"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("wuxingvalue"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("awakelevel"));
		lb.add(new xdb.logs.ListenableMap().setVarName("skills"));
		return lb;
	}

	private class Const implements xbean.Talisman {
		Talisman nThis() {
			return Talisman.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Talisman copy() {
			return Talisman.this.copy();
		}

		@Override
		public xbean.Talisman toData() {
			return Talisman.this.toData();
		}

		public xbean.Talisman toBean() {
			return Talisman.this.toBean();
		}

		@Override
		public xbean.Talisman toDataIf() {
			return Talisman.this.toDataIf();
		}

		public xbean.Talisman toBeanIf() {
			return Talisman.this.toBeanIf();
		}

		@Override
		public long getTalismanid() { // 
			_xdb_verify_unsafe_();
			return talismanid;
		}

		@Override
		public int getModelid() { // 法宝的策划配置id
			_xdb_verify_unsafe_();
			return modelid;
		}

		@Override
		public int getPos() { // 物品在包裹中的位置，从1开始编号
			_xdb_verify_unsafe_();
			return pos;
		}

		@Override
		public boolean getIsbind() { // 法宝绑定类型
			_xdb_verify_unsafe_();
			return isbind;
		}

		@Override
		public long getExpiretime() { // 过期时间
			_xdb_verify_unsafe_();
			return expiretime;
		}

		@Override
		public long getNormalexp() { // 法宝普通经验
			_xdb_verify_unsafe_();
			return normalexp;
		}

		@Override
		public int getNormallevel() { // 法宝普通等级,开始为1
			_xdb_verify_unsafe_();
			return normallevel;
		}

		@Override
		public int getStarexp() { // 法宝星阶经验
			_xdb_verify_unsafe_();
			return starexp;
		}

		@Override
		public int getStarlevel() { // 法宝星阶等级,开始为1
			_xdb_verify_unsafe_();
			return starlevel;
		}

		@Override
		public int getWuxingtype() { // 法宝当前的五行属性类型
			_xdb_verify_unsafe_();
			return wuxingtype;
		}

		@Override
		public int getWuxingvalue() { // 五行属性攻击值
			_xdb_verify_unsafe_();
			return wuxingvalue;
		}

		@Override
		public int getAwakelevel() { // 法宝觉醒等级,从0开始
			_xdb_verify_unsafe_();
			return awakelevel;
		}

		@Override
		public java.util.Map<Integer, Integer> getSkills() { // 法宝技能
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(skills);
		}

		@Override
		public java.util.Map<Integer, Integer> getSkillsAsData() { // 法宝技能
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> skills;
			Talisman _o_ = Talisman.this;
			skills = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), _e_.getValue());
			return skills;
		}

		@Override
		public void setTalismanid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setModelid(int _v_) { // 法宝的策划配置id
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPos(int _v_) { // 物品在包裹中的位置，从1开始编号
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsbind(boolean _v_) { // 法宝绑定类型
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNormalexp(long _v_) { // 法宝普通经验
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setNormallevel(int _v_) { // 法宝普通等级,开始为1
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStarexp(int _v_) { // 法宝星阶经验
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStarlevel(int _v_) { // 法宝星阶等级,开始为1
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWuxingtype(int _v_) { // 法宝当前的五行属性类型
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWuxingvalue(int _v_) { // 五行属性攻击值
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAwakelevel(int _v_) { // 法宝觉醒等级,从0开始
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean toConst() {
			_xdb_verify_unsafe_();
			return this;
		}

		@Override
		public boolean isConst() {
			_xdb_verify_unsafe_();
			return true;
		}

		@Override
		public boolean isData() {
			return Talisman.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Talisman.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Talisman.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Talisman.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Talisman.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Talisman.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Talisman.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Talisman.this.hashCode();
		}

		@Override
		public String toString() {
			return Talisman.this.toString();
		}

	}

	public static final class Data implements xbean.Talisman {
		private long talismanid; // 
		private int modelid; // 法宝的策划配置id
		private int pos; // 物品在包裹中的位置，从1开始编号
		private boolean isbind; // 法宝绑定类型
		private long expiretime; // 过期时间
		private long normalexp; // 法宝普通经验
		private int normallevel; // 法宝普通等级,开始为1
		private int starexp; // 法宝星阶经验
		private int starlevel; // 法宝星阶等级,开始为1
		private int wuxingtype; // 法宝当前的五行属性类型
		private int wuxingvalue; // 五行属性攻击值
		private int awakelevel; // 法宝觉醒等级,从0开始
		private java.util.HashMap<Integer, Integer> skills; // 法宝技能

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			normallevel = 1;
			starlevel = 1;
			skills = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.Talisman _o1_) {
			if (_o1_ instanceof Talisman) assign((Talisman)_o1_);
			else if (_o1_ instanceof Talisman.Data) assign((Talisman.Data)_o1_);
			else if (_o1_ instanceof Talisman.Const) assign(((Talisman.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Talisman _o_) {
			talismanid = _o_.talismanid;
			modelid = _o_.modelid;
			pos = _o_.pos;
			isbind = _o_.isbind;
			expiretime = _o_.expiretime;
			normalexp = _o_.normalexp;
			normallevel = _o_.normallevel;
			starexp = _o_.starexp;
			starlevel = _o_.starlevel;
			wuxingtype = _o_.wuxingtype;
			wuxingvalue = _o_.wuxingvalue;
			awakelevel = _o_.awakelevel;
			skills = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(Talisman.Data _o_) {
			talismanid = _o_.talismanid;
			modelid = _o_.modelid;
			pos = _o_.pos;
			isbind = _o_.isbind;
			expiretime = _o_.expiretime;
			normalexp = _o_.normalexp;
			normallevel = _o_.normallevel;
			starexp = _o_.starexp;
			starlevel = _o_.starlevel;
			wuxingtype = _o_.wuxingtype;
			wuxingvalue = _o_.wuxingvalue;
			awakelevel = _o_.awakelevel;
			skills = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)13);
	_os_.marshal((short)(10240|  1));_os_.marshal(talismanid);
	_os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
	_os_.marshal((short)( 8192|  3));_os_.marshal(pos);
	_os_.marshal((short)( 2048|  4));_os_.marshal(isbind);
	_os_.marshal((short)(10240|  5));_os_.marshal(expiretime);
	_os_.marshal((short)(10240|  6));_os_.marshal(normalexp);
	_os_.marshal((short)( 8192|  7));_os_.marshal(normallevel);
	_os_.marshal((short)( 8192|  8));_os_.marshal(starexp);
	_os_.marshal((short)( 8192|  9));_os_.marshal(starlevel);
	_os_.marshal((short)( 8192| 10));_os_.marshal(wuxingtype);
	_os_.marshal((short)( 8192| 11));_os_.marshal(wuxingvalue);
	_os_.marshal((short)( 8192| 12));_os_.marshal(awakelevel);
	_os_.marshal((short)(24576| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  1):talismanid = _os_.unmarshal_long();
					break;
					case ( 6144|  1):talismanid = _os_.unmarshal_short();
					break;
					case ( 8192|  1):talismanid = _os_.unmarshal_int();
					break;
					case ( 8192|  2):modelid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):modelid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):pos = _os_.unmarshal_int();
					break;
					case ( 6144|  3):pos = _os_.unmarshal_short();
					break;
					case ( 2048|  4):isbind = _os_.unmarshal_boolean();
					break;
					case (10240|  5):expiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  5):expiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  5):expiretime = _os_.unmarshal_int();
					break;
					case (10240|  6):normalexp = _os_.unmarshal_long();
					break;
					case ( 6144|  6):normalexp = _os_.unmarshal_short();
					break;
					case ( 8192|  6):normalexp = _os_.unmarshal_int();
					break;
					case ( 8192|  7):normallevel = _os_.unmarshal_int();
					break;
					case ( 6144|  7):normallevel = _os_.unmarshal_short();
					break;
					case ( 8192|  8):starexp = _os_.unmarshal_int();
					break;
					case ( 6144|  8):starexp = _os_.unmarshal_short();
					break;
					case ( 8192|  9):starlevel = _os_.unmarshal_int();
					break;
					case ( 6144|  9):starlevel = _os_.unmarshal_short();
					break;
					case ( 8192| 10):wuxingtype = _os_.unmarshal_int();
					break;
					case ( 6144| 10):wuxingtype = _os_.unmarshal_short();
					break;
					case ( 8192| 11):wuxingvalue = _os_.unmarshal_int();
					break;
					case ( 6144| 11):wuxingvalue = _os_.unmarshal_short();
					break;
					case ( 8192| 12):awakelevel = _os_.unmarshal_int();
					break;
					case ( 6144| 12):awakelevel = _os_.unmarshal_short();
					break;
					case (24576| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skills = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		skills.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Talisman copy() {
			return new Data(this);
		}

		@Override
		public xbean.Talisman toData() {
			return new Data(this);
		}

		public xbean.Talisman toBean() {
			return new Talisman(this, null, null);
		}

		@Override
		public xbean.Talisman toDataIf() {
			return this;
		}

		public xbean.Talisman toBeanIf() {
			return new Talisman(this, null, null);
		}

		// xdb.Bean interface. Data Unsupported
		public boolean xdbManaged() { throw new UnsupportedOperationException(); }
		public xdb.Bean xdbParent() { throw new UnsupportedOperationException(); }
		public String xdbVarname()  { throw new UnsupportedOperationException(); }
		public Long    xdbObjId()   { throw new UnsupportedOperationException(); }
		public xdb.Bean toConst()   { throw new UnsupportedOperationException(); }
		public boolean isConst()    { return false; }
		public boolean isData()     { return true; }

		@Override
		public long getTalismanid() { // 
			return talismanid;
		}

		@Override
		public int getModelid() { // 法宝的策划配置id
			return modelid;
		}

		@Override
		public int getPos() { // 物品在包裹中的位置，从1开始编号
			return pos;
		}

		@Override
		public boolean getIsbind() { // 法宝绑定类型
			return isbind;
		}

		@Override
		public long getExpiretime() { // 过期时间
			return expiretime;
		}

		@Override
		public long getNormalexp() { // 法宝普通经验
			return normalexp;
		}

		@Override
		public int getNormallevel() { // 法宝普通等级,开始为1
			return normallevel;
		}

		@Override
		public int getStarexp() { // 法宝星阶经验
			return starexp;
		}

		@Override
		public int getStarlevel() { // 法宝星阶等级,开始为1
			return starlevel;
		}

		@Override
		public int getWuxingtype() { // 法宝当前的五行属性类型
			return wuxingtype;
		}

		@Override
		public int getWuxingvalue() { // 五行属性攻击值
			return wuxingvalue;
		}

		@Override
		public int getAwakelevel() { // 法宝觉醒等级,从0开始
			return awakelevel;
		}

		@Override
		public java.util.Map<Integer, Integer> getSkills() { // 法宝技能
			return skills;
		}

		@Override
		public java.util.Map<Integer, Integer> getSkillsAsData() { // 法宝技能
			return skills;
		}

		@Override
		public void setTalismanid(long _v_) { // 
			talismanid = _v_;
		}

		@Override
		public void setModelid(int _v_) { // 法宝的策划配置id
			modelid = _v_;
		}

		@Override
		public void setPos(int _v_) { // 物品在包裹中的位置，从1开始编号
			pos = _v_;
		}

		@Override
		public void setIsbind(boolean _v_) { // 法宝绑定类型
			isbind = _v_;
		}

		@Override
		public void setExpiretime(long _v_) { // 过期时间
			expiretime = _v_;
		}

		@Override
		public void setNormalexp(long _v_) { // 法宝普通经验
			normalexp = _v_;
		}

		@Override
		public void setNormallevel(int _v_) { // 法宝普通等级,开始为1
			normallevel = _v_;
		}

		@Override
		public void setStarexp(int _v_) { // 法宝星阶经验
			starexp = _v_;
		}

		@Override
		public void setStarlevel(int _v_) { // 法宝星阶等级,开始为1
			starlevel = _v_;
		}

		@Override
		public void setWuxingtype(int _v_) { // 法宝当前的五行属性类型
			wuxingtype = _v_;
		}

		@Override
		public void setWuxingvalue(int _v_) { // 五行属性攻击值
			wuxingvalue = _v_;
		}

		@Override
		public void setAwakelevel(int _v_) { // 法宝觉醒等级,从0开始
			awakelevel = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Talisman.Data)) return false;
			Talisman.Data _o_ = (Talisman.Data) _o1_;
			if (talismanid != _o_.talismanid) return false;
			if (modelid != _o_.modelid) return false;
			if (pos != _o_.pos) return false;
			if (isbind != _o_.isbind) return false;
			if (expiretime != _o_.expiretime) return false;
			if (normalexp != _o_.normalexp) return false;
			if (normallevel != _o_.normallevel) return false;
			if (starexp != _o_.starexp) return false;
			if (starlevel != _o_.starlevel) return false;
			if (wuxingtype != _o_.wuxingtype) return false;
			if (wuxingvalue != _o_.wuxingvalue) return false;
			if (awakelevel != _o_.awakelevel) return false;
			if (!skills.equals(_o_.skills)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += talismanid;
			_h_ += modelid;
			_h_ += pos;
			_h_ += isbind ? 1231 : 1237;
			_h_ += expiretime;
			_h_ += normalexp;
			_h_ += normallevel;
			_h_ += starexp;
			_h_ += starlevel;
			_h_ += wuxingtype;
			_h_ += wuxingvalue;
			_h_ += awakelevel;
			_h_ += skills.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(talismanid);
			_sb_.append(",");
			_sb_.append(modelid);
			_sb_.append(",");
			_sb_.append(pos);
			_sb_.append(",");
			_sb_.append(isbind);
			_sb_.append(",");
			_sb_.append(expiretime);
			_sb_.append(",");
			_sb_.append(normalexp);
			_sb_.append(",");
			_sb_.append(normallevel);
			_sb_.append(",");
			_sb_.append(starexp);
			_sb_.append(",");
			_sb_.append(starlevel);
			_sb_.append(",");
			_sb_.append(wuxingtype);
			_sb_.append(",");
			_sb_.append(wuxingvalue);
			_sb_.append(",");
			_sb_.append(awakelevel);
			_sb_.append(",");
			_sb_.append(skills);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
