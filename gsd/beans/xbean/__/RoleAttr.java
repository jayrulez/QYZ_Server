
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleAttr extends xdb.XBean implements xbean.RoleAttr {
	private long roleid; // 
	private int rolecombatpower; // 
	private java.util.LinkedList<Float> rawattrs; // 
	private java.util.LinkedList<Float> finalattrs; // 
	private java.util.HashMap<String, xbean.GroupAttr> groupattrs; // 
	private int hp; // 
	private int mp; // 
	private java.util.HashMap<Integer, Long> skillcolddowns; // 
	private int resethpmp; // 
	private int petcombatpower; // 
	private int totalcombatpower; // 

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		rolecombatpower = 0;
		rawattrs.clear();
		finalattrs.clear();
		groupattrs.clear();
		hp = 0;
		mp = 0;
		skillcolddowns.clear();
		resethpmp = 0;
		petcombatpower = 0;
		totalcombatpower = 0;
	}

	RoleAttr(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		rawattrs = new java.util.LinkedList<Float>();
		finalattrs = new java.util.LinkedList<Float>();
		groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
		skillcolddowns = new java.util.HashMap<Integer, Long>();
	}

	public RoleAttr() {
		this(0, null, null);
	}

	public RoleAttr(RoleAttr _o_) {
		this(_o_, null, null);
	}

	RoleAttr(xbean.RoleAttr _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleAttr) assign((RoleAttr)_o1_);
		else if (_o1_ instanceof RoleAttr.Data) assign((RoleAttr.Data)_o1_);
		else if (_o1_ instanceof RoleAttr.Const) assign(((RoleAttr.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleAttr _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		rolecombatpower = _o_.rolecombatpower;
		rawattrs = new java.util.LinkedList<Float>();
		rawattrs.addAll(_o_.rawattrs);
		finalattrs = new java.util.LinkedList<Float>();
		finalattrs.addAll(_o_.finalattrs);
		groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
		for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : _o_.groupattrs.entrySet())
			groupattrs.put(_e_.getKey(), new GroupAttr(_e_.getValue(), this, "groupattrs"));
		hp = _o_.hp;
		mp = _o_.mp;
		skillcolddowns = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.skillcolddowns.entrySet())
			skillcolddowns.put(_e_.getKey(), _e_.getValue());
		resethpmp = _o_.resethpmp;
		petcombatpower = _o_.petcombatpower;
		totalcombatpower = _o_.totalcombatpower;
	}

	private void assign(RoleAttr.Data _o_) {
		roleid = _o_.roleid;
		rolecombatpower = _o_.rolecombatpower;
		rawattrs = new java.util.LinkedList<Float>();
		rawattrs.addAll(_o_.rawattrs);
		finalattrs = new java.util.LinkedList<Float>();
		finalattrs.addAll(_o_.finalattrs);
		groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
		for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : _o_.groupattrs.entrySet())
			groupattrs.put(_e_.getKey(), new GroupAttr(_e_.getValue(), this, "groupattrs"));
		hp = _o_.hp;
		mp = _o_.mp;
		skillcolddowns = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.skillcolddowns.entrySet())
			skillcolddowns.put(_e_.getKey(), _e_.getValue());
		resethpmp = _o_.resethpmp;
		petcombatpower = _o_.petcombatpower;
		totalcombatpower = _o_.totalcombatpower;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)11);
    _os_.marshal((short)(10240|  3));_os_.marshal(roleid);
    _os_.marshal((short)( 8192|  1));_os_.marshal(rolecombatpower);
    _os_.marshal((short)(22528|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rawattrs.size());
for (Float _v_ : rawattrs) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(finalattrs.size());
for (Float _v_ : finalattrs) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(groupattrs.size());
for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : groupattrs.entrySet())
{
	_os_.marshal(_e_.getKey(), xdb.Const.IO_CHARSET);
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  6));_os_.marshal(hp);
    _os_.marshal((short)( 8192|  7));_os_.marshal(mp);
    _os_.marshal((short)(24576|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skillcolddowns.size());
for (java.util.Map.Entry<Integer, Long> _e_ : skillcolddowns.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  9));_os_.marshal(resethpmp);
    _os_.marshal((short)( 8192| 10));_os_.marshal(petcombatpower);
    _os_.marshal((short)( 8192| 11));_os_.marshal(totalcombatpower);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (10240|  3):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  3):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):roleid = _os_.unmarshal_int();
    				break;
    				case ( 8192|  1):rolecombatpower = _os_.unmarshal_int();
    				break;
    				case ( 6144|  1):rolecombatpower = _os_.unmarshal_short();
    				break;
    				case (22528|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	float _v_ = 0.0f;
	_v_ = _os_.unmarshal_float();
	rawattrs.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	float _v_ = 0.0f;
	_v_ = _os_.unmarshal_float();
	finalattrs.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		groupattrs = new java.util.HashMap<String, xbean.GroupAttr>(size * 2);
	}
	for (; size > 0; --size)
	{
		String _k_ = "";
		_k_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		xbean.GroupAttr _v_ = new GroupAttr(0, this, "groupattrs");
		_v_.unmarshal(_os_);
		groupattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  6):hp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  6):hp = _os_.unmarshal_short();
    				break;
    				case ( 8192|  7):mp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):mp = _os_.unmarshal_short();
    				break;
    				case (24576|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skillcolddowns = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		skillcolddowns.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  9):resethpmp = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):resethpmp = _os_.unmarshal_short();
    				break;
    				case ( 8192| 10):petcombatpower = _os_.unmarshal_int();
    				break;
    				case ( 6144| 10):petcombatpower = _os_.unmarshal_short();
    				break;
    				case ( 8192| 11):totalcombatpower = _os_.unmarshal_int();
    				break;
    				case ( 6144| 11):totalcombatpower = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleAttr copy() {
		_xdb_verify_unsafe_();
		return new RoleAttr(this);
	}

	@Override
	public xbean.RoleAttr toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAttr toBean() {
		_xdb_verify_unsafe_();
		return new RoleAttr(this); // same as copy()
	}

	@Override
	public xbean.RoleAttr toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleAttr toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public long getRoleid() { // 
		_xdb_verify_unsafe_();
		return roleid;
	}

	@Override
	public int getRolecombatpower() { // 
		_xdb_verify_unsafe_();
		return rolecombatpower;
	}

	@Override
	public java.util.List<Float> getRawattrs() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "rawattrs"), rawattrs);
	}

	public java.util.List<Float> getRawattrsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Float> rawattrs;
		RoleAttr _o_ = this;
		rawattrs = new java.util.LinkedList<Float>();
		rawattrs.addAll(_o_.rawattrs);
		return rawattrs;
	}

	@Override
	public java.util.List<Float> getFinalattrs() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "finalattrs"), finalattrs);
	}

	public java.util.List<Float> getFinalattrsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Float> finalattrs;
		RoleAttr _o_ = this;
		finalattrs = new java.util.LinkedList<Float>();
		finalattrs.addAll(_o_.finalattrs);
		return finalattrs;
	}

	@Override
	public java.util.Map<String, xbean.GroupAttr> getGroupattrs() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "groupattrs"), groupattrs);
	}

	@Override
	public java.util.Map<String, xbean.GroupAttr> getGroupattrsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<String, xbean.GroupAttr> groupattrs;
		RoleAttr _o_ = this;
		groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
		for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : _o_.groupattrs.entrySet())
			groupattrs.put(_e_.getKey(), new GroupAttr.Data(_e_.getValue()));
		return groupattrs;
	}

	@Override
	public int getHp() { // 
		_xdb_verify_unsafe_();
		return hp;
	}

	@Override
	public int getMp() { // 
		_xdb_verify_unsafe_();
		return mp;
	}

	@Override
	public java.util.Map<Integer, Long> getSkillcolddowns() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "skillcolddowns"), skillcolddowns);
	}

	@Override
	public java.util.Map<Integer, Long> getSkillcolddownsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Long> skillcolddowns;
		RoleAttr _o_ = this;
		skillcolddowns = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.skillcolddowns.entrySet())
			skillcolddowns.put(_e_.getKey(), _e_.getValue());
		return skillcolddowns;
	}

	@Override
	public int getResethpmp() { // 
		_xdb_verify_unsafe_();
		return resethpmp;
	}

	@Override
	public int getPetcombatpower() { // 
		_xdb_verify_unsafe_();
		return petcombatpower;
	}

	@Override
	public int getTotalcombatpower() { // 
		_xdb_verify_unsafe_();
		return totalcombatpower;
	}

	@Override
	public void setRoleid(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "roleid") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, roleid) {
					public void rollback() { roleid = _xdb_saved; }
				};}});
		roleid = _v_;
	}

	@Override
	public void setRolecombatpower(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "rolecombatpower") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, rolecombatpower) {
					public void rollback() { rolecombatpower = _xdb_saved; }
				};}});
		rolecombatpower = _v_;
	}

	@Override
	public void setHp(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "hp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, hp) {
					public void rollback() { hp = _xdb_saved; }
				};}});
		hp = _v_;
	}

	@Override
	public void setMp(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "mp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, mp) {
					public void rollback() { mp = _xdb_saved; }
				};}});
		mp = _v_;
	}

	@Override
	public void setResethpmp(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "resethpmp") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, resethpmp) {
					public void rollback() { resethpmp = _xdb_saved; }
				};}});
		resethpmp = _v_;
	}

	@Override
	public void setPetcombatpower(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "petcombatpower") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, petcombatpower) {
					public void rollback() { petcombatpower = _xdb_saved; }
				};}});
		petcombatpower = _v_;
	}

	@Override
	public void setTotalcombatpower(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "totalcombatpower") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, totalcombatpower) {
					public void rollback() { totalcombatpower = _xdb_saved; }
				};}});
		totalcombatpower = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleAttr _o_ = null;
		if ( _o1_ instanceof RoleAttr ) _o_ = (RoleAttr)_o1_;
		else if ( _o1_ instanceof RoleAttr.Const ) _o_ = ((RoleAttr.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (rolecombatpower != _o_.rolecombatpower) return false;
		if (!rawattrs.equals(_o_.rawattrs)) return false;
		if (!finalattrs.equals(_o_.finalattrs)) return false;
		if (!groupattrs.equals(_o_.groupattrs)) return false;
		if (hp != _o_.hp) return false;
		if (mp != _o_.mp) return false;
		if (!skillcolddowns.equals(_o_.skillcolddowns)) return false;
		if (resethpmp != _o_.resethpmp) return false;
		if (petcombatpower != _o_.petcombatpower) return false;
		if (totalcombatpower != _o_.totalcombatpower) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += rolecombatpower;
		_h_ += rawattrs.hashCode();
		_h_ += finalattrs.hashCode();
		_h_ += groupattrs.hashCode();
		_h_ += hp;
		_h_ += mp;
		_h_ += skillcolddowns.hashCode();
		_h_ += resethpmp;
		_h_ += petcombatpower;
		_h_ += totalcombatpower;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(rolecombatpower);
		_sb_.append(",");
		_sb_.append(rawattrs);
		_sb_.append(",");
		_sb_.append(finalattrs);
		_sb_.append(",");
		_sb_.append(groupattrs);
		_sb_.append(",");
		_sb_.append(hp);
		_sb_.append(",");
		_sb_.append(mp);
		_sb_.append(",");
		_sb_.append(skillcolddowns);
		_sb_.append(",");
		_sb_.append(resethpmp);
		_sb_.append(",");
		_sb_.append(petcombatpower);
		_sb_.append(",");
		_sb_.append(totalcombatpower);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("rolecombatpower"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("rawattrs"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("finalattrs"));
		lb.add(new xdb.logs.ListenableMap().setVarName("groupattrs"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("hp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("mp"));
		lb.add(new xdb.logs.ListenableMap().setVarName("skillcolddowns"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("resethpmp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("petcombatpower"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalcombatpower"));
		return lb;
	}

	private class Const implements xbean.RoleAttr {
		RoleAttr nThis() {
			return RoleAttr.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleAttr copy() {
			return RoleAttr.this.copy();
		}

		@Override
		public xbean.RoleAttr toData() {
			return RoleAttr.this.toData();
		}

		public xbean.RoleAttr toBean() {
			return RoleAttr.this.toBean();
		}

		@Override
		public xbean.RoleAttr toDataIf() {
			return RoleAttr.this.toDataIf();
		}

		public xbean.RoleAttr toBeanIf() {
			return RoleAttr.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public int getRolecombatpower() { // 
			_xdb_verify_unsafe_();
			return rolecombatpower;
		}

		@Override
		public java.util.List<Float> getRawattrs() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(rawattrs);
		}

		public java.util.List<Float> getRawattrsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Float> rawattrs;
			RoleAttr _o_ = RoleAttr.this;
		rawattrs = new java.util.LinkedList<Float>();
		rawattrs.addAll(_o_.rawattrs);
			return rawattrs;
		}

		@Override
		public java.util.List<Float> getFinalattrs() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(finalattrs);
		}

		public java.util.List<Float> getFinalattrsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Float> finalattrs;
			RoleAttr _o_ = RoleAttr.this;
		finalattrs = new java.util.LinkedList<Float>();
		finalattrs.addAll(_o_.finalattrs);
			return finalattrs;
		}

		@Override
		public java.util.Map<String, xbean.GroupAttr> getGroupattrs() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(groupattrs);
		}

		@Override
		public java.util.Map<String, xbean.GroupAttr> getGroupattrsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<String, xbean.GroupAttr> groupattrs;
			RoleAttr _o_ = RoleAttr.this;
			groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
			for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : _o_.groupattrs.entrySet())
				groupattrs.put(_e_.getKey(), new GroupAttr.Data(_e_.getValue()));
			return groupattrs;
		}

		@Override
		public int getHp() { // 
			_xdb_verify_unsafe_();
			return hp;
		}

		@Override
		public int getMp() { // 
			_xdb_verify_unsafe_();
			return mp;
		}

		@Override
		public java.util.Map<Integer, Long> getSkillcolddowns() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(skillcolddowns);
		}

		@Override
		public java.util.Map<Integer, Long> getSkillcolddownsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Long> skillcolddowns;
			RoleAttr _o_ = RoleAttr.this;
			skillcolddowns = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.skillcolddowns.entrySet())
				skillcolddowns.put(_e_.getKey(), _e_.getValue());
			return skillcolddowns;
		}

		@Override
		public int getResethpmp() { // 
			_xdb_verify_unsafe_();
			return resethpmp;
		}

		@Override
		public int getPetcombatpower() { // 
			_xdb_verify_unsafe_();
			return petcombatpower;
		}

		@Override
		public int getTotalcombatpower() { // 
			_xdb_verify_unsafe_();
			return totalcombatpower;
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setRolecombatpower(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setHp(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMp(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setResethpmp(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setPetcombatpower(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setTotalcombatpower(int _v_) { // 
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
			return RoleAttr.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleAttr.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleAttr.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleAttr.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleAttr.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleAttr.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleAttr.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleAttr.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleAttr.this.toString();
		}

	}

	public static final class Data implements xbean.RoleAttr {
		private long roleid; // 
		private int rolecombatpower; // 
		private java.util.LinkedList<Float> rawattrs; // 
		private java.util.LinkedList<Float> finalattrs; // 
		private java.util.HashMap<String, xbean.GroupAttr> groupattrs; // 
		private int hp; // 
		private int mp; // 
		private java.util.HashMap<Integer, Long> skillcolddowns; // 
		private int resethpmp; // 
		private int petcombatpower; // 
		private int totalcombatpower; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			rawattrs = new java.util.LinkedList<Float>();
			finalattrs = new java.util.LinkedList<Float>();
			groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
			skillcolddowns = new java.util.HashMap<Integer, Long>();
		}

		Data(xbean.RoleAttr _o1_) {
			if (_o1_ instanceof RoleAttr) assign((RoleAttr)_o1_);
			else if (_o1_ instanceof RoleAttr.Data) assign((RoleAttr.Data)_o1_);
			else if (_o1_ instanceof RoleAttr.Const) assign(((RoleAttr.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleAttr _o_) {
			roleid = _o_.roleid;
			rolecombatpower = _o_.rolecombatpower;
			rawattrs = new java.util.LinkedList<Float>();
			rawattrs.addAll(_o_.rawattrs);
			finalattrs = new java.util.LinkedList<Float>();
			finalattrs.addAll(_o_.finalattrs);
			groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
			for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : _o_.groupattrs.entrySet())
				groupattrs.put(_e_.getKey(), new GroupAttr.Data(_e_.getValue()));
			hp = _o_.hp;
			mp = _o_.mp;
			skillcolddowns = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.skillcolddowns.entrySet())
				skillcolddowns.put(_e_.getKey(), _e_.getValue());
			resethpmp = _o_.resethpmp;
			petcombatpower = _o_.petcombatpower;
			totalcombatpower = _o_.totalcombatpower;
		}

		private void assign(RoleAttr.Data _o_) {
			roleid = _o_.roleid;
			rolecombatpower = _o_.rolecombatpower;
			rawattrs = new java.util.LinkedList<Float>();
			rawattrs.addAll(_o_.rawattrs);
			finalattrs = new java.util.LinkedList<Float>();
			finalattrs.addAll(_o_.finalattrs);
			groupattrs = new java.util.HashMap<String, xbean.GroupAttr>();
			for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : _o_.groupattrs.entrySet())
				groupattrs.put(_e_.getKey(), new GroupAttr.Data(_e_.getValue()));
			hp = _o_.hp;
			mp = _o_.mp;
			skillcolddowns = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.skillcolddowns.entrySet())
				skillcolddowns.put(_e_.getKey(), _e_.getValue());
			resethpmp = _o_.resethpmp;
			petcombatpower = _o_.petcombatpower;
			totalcombatpower = _o_.totalcombatpower;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)11);
	_os_.marshal((short)(10240|  3));_os_.marshal(roleid);
	_os_.marshal((short)( 8192|  1));_os_.marshal(rolecombatpower);
	_os_.marshal((short)(22528|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(rawattrs.size());
for (Float _v_ : rawattrs) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(finalattrs.size());
for (Float _v_ : finalattrs) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  5));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(groupattrs.size());
for (java.util.Map.Entry<String, xbean.GroupAttr> _e_ : groupattrs.entrySet())
{
	_os_.marshal(_e_.getKey(), xdb.Const.IO_CHARSET);
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  6));_os_.marshal(hp);
	_os_.marshal((short)( 8192|  7));_os_.marshal(mp);
	_os_.marshal((short)(24576|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skillcolddowns.size());
for (java.util.Map.Entry<Integer, Long> _e_ : skillcolddowns.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  9));_os_.marshal(resethpmp);
	_os_.marshal((short)( 8192| 10));_os_.marshal(petcombatpower);
	_os_.marshal((short)( 8192| 11));_os_.marshal(totalcombatpower);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  3):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  3):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):roleid = _os_.unmarshal_int();
					break;
					case ( 8192|  1):rolecombatpower = _os_.unmarshal_int();
					break;
					case ( 6144|  1):rolecombatpower = _os_.unmarshal_short();
					break;
					case (22528|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	float _v_ = 0.0f;
	_v_ = _os_.unmarshal_float();
	rawattrs.add(_v_);
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	float _v_ = 0.0f;
	_v_ = _os_.unmarshal_float();
	finalattrs.add(_v_);
}
_os_ = _temp_;}
					break;
					case (24576|  5):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		groupattrs = new java.util.HashMap<String, xbean.GroupAttr>(size * 2);
	}
	for (; size > 0; --size)
	{
		String _k_ = "";
		_k_ = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
		xbean.GroupAttr _v_ = xbean.Pod.newGroupAttrData();
		_v_.unmarshal(_os_);
		groupattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  6):hp = _os_.unmarshal_int();
					break;
					case ( 6144|  6):hp = _os_.unmarshal_short();
					break;
					case ( 8192|  7):mp = _os_.unmarshal_int();
					break;
					case ( 6144|  7):mp = _os_.unmarshal_short();
					break;
					case (24576|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		skillcolddowns = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		skillcolddowns.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  9):resethpmp = _os_.unmarshal_int();
					break;
					case ( 6144|  9):resethpmp = _os_.unmarshal_short();
					break;
					case ( 8192| 10):petcombatpower = _os_.unmarshal_int();
					break;
					case ( 6144| 10):petcombatpower = _os_.unmarshal_short();
					break;
					case ( 8192| 11):totalcombatpower = _os_.unmarshal_int();
					break;
					case ( 6144| 11):totalcombatpower = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleAttr copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleAttr toData() {
			return new Data(this);
		}

		public xbean.RoleAttr toBean() {
			return new RoleAttr(this, null, null);
		}

		@Override
		public xbean.RoleAttr toDataIf() {
			return this;
		}

		public xbean.RoleAttr toBeanIf() {
			return new RoleAttr(this, null, null);
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
		public long getRoleid() { // 
			return roleid;
		}

		@Override
		public int getRolecombatpower() { // 
			return rolecombatpower;
		}

		@Override
		public java.util.List<Float> getRawattrs() { // 
			return rawattrs;
		}

		@Override
		public java.util.List<Float> getRawattrsAsData() { // 
			return rawattrs;
		}

		@Override
		public java.util.List<Float> getFinalattrs() { // 
			return finalattrs;
		}

		@Override
		public java.util.List<Float> getFinalattrsAsData() { // 
			return finalattrs;
		}

		@Override
		public java.util.Map<String, xbean.GroupAttr> getGroupattrs() { // 
			return groupattrs;
		}

		@Override
		public java.util.Map<String, xbean.GroupAttr> getGroupattrsAsData() { // 
			return groupattrs;
		}

		@Override
		public int getHp() { // 
			return hp;
		}

		@Override
		public int getMp() { // 
			return mp;
		}

		@Override
		public java.util.Map<Integer, Long> getSkillcolddowns() { // 
			return skillcolddowns;
		}

		@Override
		public java.util.Map<Integer, Long> getSkillcolddownsAsData() { // 
			return skillcolddowns;
		}

		@Override
		public int getResethpmp() { // 
			return resethpmp;
		}

		@Override
		public int getPetcombatpower() { // 
			return petcombatpower;
		}

		@Override
		public int getTotalcombatpower() { // 
			return totalcombatpower;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setRolecombatpower(int _v_) { // 
			rolecombatpower = _v_;
		}

		@Override
		public void setHp(int _v_) { // 
			hp = _v_;
		}

		@Override
		public void setMp(int _v_) { // 
			mp = _v_;
		}

		@Override
		public void setResethpmp(int _v_) { // 
			resethpmp = _v_;
		}

		@Override
		public void setPetcombatpower(int _v_) { // 
			petcombatpower = _v_;
		}

		@Override
		public void setTotalcombatpower(int _v_) { // 
			totalcombatpower = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleAttr.Data)) return false;
			RoleAttr.Data _o_ = (RoleAttr.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (rolecombatpower != _o_.rolecombatpower) return false;
			if (!rawattrs.equals(_o_.rawattrs)) return false;
			if (!finalattrs.equals(_o_.finalattrs)) return false;
			if (!groupattrs.equals(_o_.groupattrs)) return false;
			if (hp != _o_.hp) return false;
			if (mp != _o_.mp) return false;
			if (!skillcolddowns.equals(_o_.skillcolddowns)) return false;
			if (resethpmp != _o_.resethpmp) return false;
			if (petcombatpower != _o_.petcombatpower) return false;
			if (totalcombatpower != _o_.totalcombatpower) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += rolecombatpower;
			_h_ += rawattrs.hashCode();
			_h_ += finalattrs.hashCode();
			_h_ += groupattrs.hashCode();
			_h_ += hp;
			_h_ += mp;
			_h_ += skillcolddowns.hashCode();
			_h_ += resethpmp;
			_h_ += petcombatpower;
			_h_ += totalcombatpower;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(rolecombatpower);
			_sb_.append(",");
			_sb_.append(rawattrs);
			_sb_.append(",");
			_sb_.append(finalattrs);
			_sb_.append(",");
			_sb_.append(groupattrs);
			_sb_.append(",");
			_sb_.append(hp);
			_sb_.append(",");
			_sb_.append(mp);
			_sb_.append(",");
			_sb_.append(skillcolddowns);
			_sb_.append(",");
			_sb_.append(resethpmp);
			_sb_.append(",");
			_sb_.append(petcombatpower);
			_sb_.append(",");
			_sb_.append(totalcombatpower);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
