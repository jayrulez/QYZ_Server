
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleSkill extends xdb.XBean implements xbean.RoleSkill {
	private java.util.HashMap<Integer, xbean.SkillInfo> skills; // 
	private java.util.HashMap<Integer, Integer> equipskillpositions; // 

	@Override
	public void _reset_unsafe_() {
		skills.clear();
		equipskillpositions.clear();
	}

	RoleSkill(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
		equipskillpositions = new java.util.HashMap<Integer, Integer>();
	}

	public RoleSkill() {
		this(0, null, null);
	}

	public RoleSkill(RoleSkill _o_) {
		this(_o_, null, null);
	}

	RoleSkill(xbean.RoleSkill _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleSkill) assign((RoleSkill)_o1_);
		else if (_o1_ instanceof RoleSkill.Data) assign((RoleSkill.Data)_o1_);
		else if (_o1_ instanceof RoleSkill.Const) assign(((RoleSkill.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleSkill _o_) {
		_o_._xdb_verify_unsafe_();
		skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
		for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), new SkillInfo(_e_.getValue(), this, "skills"));
		equipskillpositions = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.equipskillpositions.entrySet())
			equipskillpositions.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(RoleSkill.Data _o_) {
		skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
		for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), new SkillInfo(_e_.getValue(), this, "skills"));
		equipskillpositions = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.equipskillpositions.entrySet())
			equipskillpositions.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)2);
    _os_.marshal((short)(24576|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipskillpositions.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : equipskillpositions.entrySet())
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
    				case (24576|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skills = new java.util.HashMap<Integer, xbean.SkillInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.SkillInfo _v_ = new SkillInfo(0, this, "skills");
		_v_.unmarshal(_os_);
		skills.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		equipskillpositions = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		equipskillpositions.put(_k_, _v_);
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
	public xbean.RoleSkill copy() {
		_xdb_verify_unsafe_();
		return new RoleSkill(this);
	}

	@Override
	public xbean.RoleSkill toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleSkill toBean() {
		_xdb_verify_unsafe_();
		return new RoleSkill(this); // same as copy()
	}

	@Override
	public xbean.RoleSkill toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleSkill toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.SkillInfo> getSkills() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "skills"), skills);
	}

	@Override
	public java.util.Map<Integer, xbean.SkillInfo> getSkillsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.SkillInfo> skills;
		RoleSkill _o_ = this;
		skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
		for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), new SkillInfo.Data(_e_.getValue()));
		return skills;
	}

	@Override
	public java.util.Map<Integer, Integer> getEquipskillpositions() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "equipskillpositions"), equipskillpositions);
	}

	@Override
	public java.util.Map<Integer, Integer> getEquipskillpositionsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> equipskillpositions;
		RoleSkill _o_ = this;
		equipskillpositions = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.equipskillpositions.entrySet())
			equipskillpositions.put(_e_.getKey(), _e_.getValue());
		return equipskillpositions;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleSkill _o_ = null;
		if ( _o1_ instanceof RoleSkill ) _o_ = (RoleSkill)_o1_;
		else if ( _o1_ instanceof RoleSkill.Const ) _o_ = ((RoleSkill.Const)_o1_).nThis();
		else return false;
		if (!skills.equals(_o_.skills)) return false;
		if (!equipskillpositions.equals(_o_.equipskillpositions)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += skills.hashCode();
		_h_ += equipskillpositions.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(skills);
		_sb_.append(",");
		_sb_.append(equipskillpositions);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("skills"));
		lb.add(new xdb.logs.ListenableMap().setVarName("equipskillpositions"));
		return lb;
	}

	private class Const implements xbean.RoleSkill {
		RoleSkill nThis() {
			return RoleSkill.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleSkill copy() {
			return RoleSkill.this.copy();
		}

		@Override
		public xbean.RoleSkill toData() {
			return RoleSkill.this.toData();
		}

		public xbean.RoleSkill toBean() {
			return RoleSkill.this.toBean();
		}

		@Override
		public xbean.RoleSkill toDataIf() {
			return RoleSkill.this.toDataIf();
		}

		public xbean.RoleSkill toBeanIf() {
			return RoleSkill.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.SkillInfo> getSkills() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(skills);
		}

		@Override
		public java.util.Map<Integer, xbean.SkillInfo> getSkillsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.SkillInfo> skills;
			RoleSkill _o_ = RoleSkill.this;
			skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
			for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), new SkillInfo.Data(_e_.getValue()));
			return skills;
		}

		@Override
		public java.util.Map<Integer, Integer> getEquipskillpositions() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(equipskillpositions);
		}

		@Override
		public java.util.Map<Integer, Integer> getEquipskillpositionsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> equipskillpositions;
			RoleSkill _o_ = RoleSkill.this;
			equipskillpositions = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.equipskillpositions.entrySet())
				equipskillpositions.put(_e_.getKey(), _e_.getValue());
			return equipskillpositions;
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
			return RoleSkill.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleSkill.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleSkill.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleSkill.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleSkill.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleSkill.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleSkill.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleSkill.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleSkill.this.toString();
		}

	}

	public static final class Data implements xbean.RoleSkill {
		private java.util.HashMap<Integer, xbean.SkillInfo> skills; // 
		private java.util.HashMap<Integer, Integer> equipskillpositions; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
			equipskillpositions = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.RoleSkill _o1_) {
			if (_o1_ instanceof RoleSkill) assign((RoleSkill)_o1_);
			else if (_o1_ instanceof RoleSkill.Data) assign((RoleSkill.Data)_o1_);
			else if (_o1_ instanceof RoleSkill.Const) assign(((RoleSkill.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleSkill _o_) {
			skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
			for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), new SkillInfo.Data(_e_.getValue()));
			equipskillpositions = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.equipskillpositions.entrySet())
				equipskillpositions.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(RoleSkill.Data _o_) {
			skills = new java.util.HashMap<Integer, xbean.SkillInfo>();
			for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), new SkillInfo.Data(_e_.getValue()));
			equipskillpositions = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.equipskillpositions.entrySet())
				equipskillpositions.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)2);
	_os_.marshal((short)(24576|  0));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, xbean.SkillInfo> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(equipskillpositions.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : equipskillpositions.entrySet())
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
					case (24576|  0):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skills = new java.util.HashMap<Integer, xbean.SkillInfo>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.SkillInfo _v_ = xbean.Pod.newSkillInfoData();
		_v_.unmarshal(_os_);
		skills.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		equipskillpositions = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		equipskillpositions.put(_k_, _v_);
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
		public xbean.RoleSkill copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleSkill toData() {
			return new Data(this);
		}

		public xbean.RoleSkill toBean() {
			return new RoleSkill(this, null, null);
		}

		@Override
		public xbean.RoleSkill toDataIf() {
			return this;
		}

		public xbean.RoleSkill toBeanIf() {
			return new RoleSkill(this, null, null);
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
		public java.util.Map<Integer, xbean.SkillInfo> getSkills() { // 
			return skills;
		}

		@Override
		public java.util.Map<Integer, xbean.SkillInfo> getSkillsAsData() { // 
			return skills;
		}

		@Override
		public java.util.Map<Integer, Integer> getEquipskillpositions() { // 
			return equipskillpositions;
		}

		@Override
		public java.util.Map<Integer, Integer> getEquipskillpositionsAsData() { // 
			return equipskillpositions;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleSkill.Data)) return false;
			RoleSkill.Data _o_ = (RoleSkill.Data) _o1_;
			if (!skills.equals(_o_.skills)) return false;
			if (!equipskillpositions.equals(_o_.equipskillpositions)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += skills.hashCode();
			_h_ += equipskillpositions.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(skills);
			_sb_.append(",");
			_sb_.append(equipskillpositions);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
