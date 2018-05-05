
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class FamilyWelfare extends xdb.XBean implements xbean.FamilyWelfare {
	private java.util.HashMap<Integer, xbean.FamilySkill> skills; // 家族技能信息
	private int maxskilllevel; // 家族技能最大值

	@Override
	public void _reset_unsafe_() {
		skills.clear();
		maxskilllevel = 1;
	}

	FamilyWelfare(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
		maxskilllevel = 1;
	}

	public FamilyWelfare() {
		this(0, null, null);
	}

	public FamilyWelfare(FamilyWelfare _o_) {
		this(_o_, null, null);
	}

	FamilyWelfare(xbean.FamilyWelfare _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof FamilyWelfare) assign((FamilyWelfare)_o1_);
		else if (_o1_ instanceof FamilyWelfare.Data) assign((FamilyWelfare.Data)_o1_);
		else if (_o1_ instanceof FamilyWelfare.Const) assign(((FamilyWelfare.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(FamilyWelfare _o_) {
		_o_._xdb_verify_unsafe_();
		skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
		for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), new FamilySkill(_e_.getValue(), this, "skills"));
		maxskilllevel = _o_.maxskilllevel;
	}

	private void assign(FamilyWelfare.Data _o_) {
		skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
		for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), new FamilySkill(_e_.getValue(), this, "skills"));
		maxskilllevel = _o_.maxskilllevel;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  2));_os_.marshal(maxskilllevel);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skills = new java.util.HashMap<Integer, xbean.FamilySkill>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.FamilySkill _v_ = new FamilySkill(0, this, "skills");
		_v_.unmarshal(_os_);
		skills.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  2):maxskilllevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):maxskilllevel = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.FamilyWelfare copy() {
		_xdb_verify_unsafe_();
		return new FamilyWelfare(this);
	}

	@Override
	public xbean.FamilyWelfare toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyWelfare toBean() {
		_xdb_verify_unsafe_();
		return new FamilyWelfare(this); // same as copy()
	}

	@Override
	public xbean.FamilyWelfare toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.FamilyWelfare toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.FamilySkill> getSkills() { // 家族技能信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "skills"), skills);
	}

	@Override
	public java.util.Map<Integer, xbean.FamilySkill> getSkillsAsData() { // 家族技能信息
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.FamilySkill> skills;
		FamilyWelfare _o_ = this;
		skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
		for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), new FamilySkill.Data(_e_.getValue()));
		return skills;
	}

	@Override
	public int getMaxskilllevel() { // 家族技能最大值
		_xdb_verify_unsafe_();
		return maxskilllevel;
	}

	@Override
	public void setMaxskilllevel(int _v_) { // 家族技能最大值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "maxskilllevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, maxskilllevel) {
					public void rollback() { maxskilllevel = _xdb_saved; }
				};}});
		maxskilllevel = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		FamilyWelfare _o_ = null;
		if ( _o1_ instanceof FamilyWelfare ) _o_ = (FamilyWelfare)_o1_;
		else if ( _o1_ instanceof FamilyWelfare.Const ) _o_ = ((FamilyWelfare.Const)_o1_).nThis();
		else return false;
		if (!skills.equals(_o_.skills)) return false;
		if (maxskilllevel != _o_.maxskilllevel) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += skills.hashCode();
		_h_ += maxskilllevel;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skills);
		_sb_.append(",");
		_sb_.append(maxskilllevel);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("skills"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("maxskilllevel"));
		return lb;
	}

	private class Const implements xbean.FamilyWelfare {
		FamilyWelfare nThis() {
			return FamilyWelfare.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.FamilyWelfare copy() {
			return FamilyWelfare.this.copy();
		}

		@Override
		public xbean.FamilyWelfare toData() {
			return FamilyWelfare.this.toData();
		}

		public xbean.FamilyWelfare toBean() {
			return FamilyWelfare.this.toBean();
		}

		@Override
		public xbean.FamilyWelfare toDataIf() {
			return FamilyWelfare.this.toDataIf();
		}

		public xbean.FamilyWelfare toBeanIf() {
			return FamilyWelfare.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.FamilySkill> getSkills() { // 家族技能信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(skills);
		}

		@Override
		public java.util.Map<Integer, xbean.FamilySkill> getSkillsAsData() { // 家族技能信息
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.FamilySkill> skills;
			FamilyWelfare _o_ = FamilyWelfare.this;
			skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
			for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), new FamilySkill.Data(_e_.getValue()));
			return skills;
		}

		@Override
		public int getMaxskilllevel() { // 家族技能最大值
			_xdb_verify_unsafe_();
			return maxskilllevel;
		}

		@Override
		public void setMaxskilllevel(int _v_) { // 家族技能最大值
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
			return FamilyWelfare.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return FamilyWelfare.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return FamilyWelfare.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return FamilyWelfare.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return FamilyWelfare.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return FamilyWelfare.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return FamilyWelfare.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return FamilyWelfare.this.hashCode();
		}

		@Override
		public String toString() {
			return FamilyWelfare.this.toString();
		}

	}

	public static final class Data implements xbean.FamilyWelfare {
		private java.util.HashMap<Integer, xbean.FamilySkill> skills; // 家族技能信息
		private int maxskilllevel; // 家族技能最大值

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
			maxskilllevel = 1;
		}

		Data(xbean.FamilyWelfare _o1_) {
			if (_o1_ instanceof FamilyWelfare) assign((FamilyWelfare)_o1_);
			else if (_o1_ instanceof FamilyWelfare.Data) assign((FamilyWelfare.Data)_o1_);
			else if (_o1_ instanceof FamilyWelfare.Const) assign(((FamilyWelfare.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(FamilyWelfare _o_) {
			skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
			for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), new FamilySkill.Data(_e_.getValue()));
			maxskilllevel = _o_.maxskilllevel;
		}

		private void assign(FamilyWelfare.Data _o_) {
			skills = new java.util.HashMap<Integer, xbean.FamilySkill>();
			for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), new FamilySkill.Data(_e_.getValue()));
			maxskilllevel = _o_.maxskilllevel;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, xbean.FamilySkill> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  2));_os_.marshal(maxskilllevel);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skills = new java.util.HashMap<Integer, xbean.FamilySkill>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.FamilySkill _v_ = xbean.Pod.newFamilySkillData();
		_v_.unmarshal(_os_);
		skills.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  2):maxskilllevel = _os_.unmarshal_int();
					break;
					case ( 6144|  2):maxskilllevel = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.FamilyWelfare copy() {
			return new Data(this);
		}

		@Override
		public xbean.FamilyWelfare toData() {
			return new Data(this);
		}

		public xbean.FamilyWelfare toBean() {
			return new FamilyWelfare(this, null, null);
		}

		@Override
		public xbean.FamilyWelfare toDataIf() {
			return this;
		}

		public xbean.FamilyWelfare toBeanIf() {
			return new FamilyWelfare(this, null, null);
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
		public java.util.Map<Integer, xbean.FamilySkill> getSkills() { // 家族技能信息
			return skills;
		}

		@Override
		public java.util.Map<Integer, xbean.FamilySkill> getSkillsAsData() { // 家族技能信息
			return skills;
		}

		@Override
		public int getMaxskilllevel() { // 家族技能最大值
			return maxskilllevel;
		}

		@Override
		public void setMaxskilllevel(int _v_) { // 家族技能最大值
			maxskilllevel = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof FamilyWelfare.Data)) return false;
			FamilyWelfare.Data _o_ = (FamilyWelfare.Data) _o1_;
			if (!skills.equals(_o_.skills)) return false;
			if (maxskilllevel != _o_.maxskilllevel) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += skills.hashCode();
			_h_ += maxskilllevel;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(skills);
			_sb_.append(",");
			_sb_.append(maxskilllevel);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
