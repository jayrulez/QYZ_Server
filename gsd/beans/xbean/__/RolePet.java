
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RolePet extends xdb.XBean implements xbean.RolePet {
	private java.util.HashMap<Integer, xbean.Pet> petmap; // 
	private java.util.LinkedList<Integer> fightpets; // 
	private int activepetmodelid; // 
	private java.util.HashMap<Integer, Integer> petfragmentmap; // 
	private int totalcombatpower; // 

	@Override
	public void _reset_unsafe_() {
		petmap.clear();
		fightpets.clear();
		activepetmodelid = 0;
		petfragmentmap.clear();
		totalcombatpower = 0;
	}

	RolePet(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		petmap = new java.util.HashMap<Integer, xbean.Pet>();
		fightpets = new java.util.LinkedList<Integer>();
		petfragmentmap = new java.util.HashMap<Integer, Integer>();
	}

	public RolePet() {
		this(0, null, null);
	}

	public RolePet(RolePet _o_) {
		this(_o_, null, null);
	}

	RolePet(xbean.RolePet _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RolePet) assign((RolePet)_o1_);
		else if (_o1_ instanceof RolePet.Data) assign((RolePet.Data)_o1_);
		else if (_o1_ instanceof RolePet.Const) assign(((RolePet.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RolePet _o_) {
		_o_._xdb_verify_unsafe_();
		petmap = new java.util.HashMap<Integer, xbean.Pet>();
		for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : _o_.petmap.entrySet())
			petmap.put(_e_.getKey(), new Pet(_e_.getValue(), this, "petmap"));
		fightpets = new java.util.LinkedList<Integer>();
		fightpets.addAll(_o_.fightpets);
		activepetmodelid = _o_.activepetmodelid;
		petfragmentmap = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.petfragmentmap.entrySet())
			petfragmentmap.put(_e_.getKey(), _e_.getValue());
		totalcombatpower = _o_.totalcombatpower;
	}

	private void assign(RolePet.Data _o_) {
		petmap = new java.util.HashMap<Integer, xbean.Pet>();
		for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : _o_.petmap.entrySet())
			petmap.put(_e_.getKey(), new Pet(_e_.getValue(), this, "petmap"));
		fightpets = new java.util.LinkedList<Integer>();
		fightpets.addAll(_o_.fightpets);
		activepetmodelid = _o_.activepetmodelid;
		petfragmentmap = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.petfragmentmap.entrySet())
			petfragmentmap.put(_e_.getKey(), _e_.getValue());
		totalcombatpower = _o_.totalcombatpower;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)5);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(petmap.size());
for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : petmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fightpets.size());
for (Integer _v_ : fightpets) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  3));_os_.marshal(activepetmodelid);
    _os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(petfragmentmap.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : petfragmentmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  5));_os_.marshal(totalcombatpower);
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
		petmap = new java.util.HashMap<Integer, xbean.Pet>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Pet _v_ = new Pet(0, this, "petmap");
		_v_.unmarshal(_os_);
		petmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	fightpets.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  3):activepetmodelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):activepetmodelid = _os_.unmarshal_short();
    				break;
    				case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		petfragmentmap = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		petfragmentmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  5):totalcombatpower = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):totalcombatpower = _os_.unmarshal_short();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RolePet copy() {
		_xdb_verify_unsafe_();
		return new RolePet(this);
	}

	@Override
	public xbean.RolePet toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RolePet toBean() {
		_xdb_verify_unsafe_();
		return new RolePet(this); // same as copy()
	}

	@Override
	public xbean.RolePet toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RolePet toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.Pet> getPetmap() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "petmap"), petmap);
	}

	@Override
	public java.util.Map<Integer, xbean.Pet> getPetmapAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.Pet> petmap;
		RolePet _o_ = this;
		petmap = new java.util.HashMap<Integer, xbean.Pet>();
		for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : _o_.petmap.entrySet())
			petmap.put(_e_.getKey(), new Pet.Data(_e_.getValue()));
		return petmap;
	}

	@Override
	public java.util.List<Integer> getFightpets() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "fightpets"), fightpets);
	}

	public java.util.List<Integer> getFightpetsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> fightpets;
		RolePet _o_ = this;
		fightpets = new java.util.LinkedList<Integer>();
		fightpets.addAll(_o_.fightpets);
		return fightpets;
	}

	@Override
	public int getActivepetmodelid() { // 
		_xdb_verify_unsafe_();
		return activepetmodelid;
	}

	@Override
	public java.util.Map<Integer, Integer> getPetfragmentmap() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "petfragmentmap"), petfragmentmap);
	}

	@Override
	public java.util.Map<Integer, Integer> getPetfragmentmapAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> petfragmentmap;
		RolePet _o_ = this;
		petfragmentmap = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.petfragmentmap.entrySet())
			petfragmentmap.put(_e_.getKey(), _e_.getValue());
		return petfragmentmap;
	}

	@Override
	public int getTotalcombatpower() { // 
		_xdb_verify_unsafe_();
		return totalcombatpower;
	}

	@Override
	public void setActivepetmodelid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activepetmodelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activepetmodelid) {
					public void rollback() { activepetmodelid = _xdb_saved; }
				};}});
		activepetmodelid = _v_;
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
		RolePet _o_ = null;
		if ( _o1_ instanceof RolePet ) _o_ = (RolePet)_o1_;
		else if ( _o1_ instanceof RolePet.Const ) _o_ = ((RolePet.Const)_o1_).nThis();
		else return false;
		if (!petmap.equals(_o_.petmap)) return false;
		if (!fightpets.equals(_o_.fightpets)) return false;
		if (activepetmodelid != _o_.activepetmodelid) return false;
		if (!petfragmentmap.equals(_o_.petfragmentmap)) return false;
		if (totalcombatpower != _o_.totalcombatpower) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += petmap.hashCode();
		_h_ += fightpets.hashCode();
		_h_ += activepetmodelid;
		_h_ += petfragmentmap.hashCode();
		_h_ += totalcombatpower;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(petmap);
		_sb_.append(",");
		_sb_.append(fightpets);
		_sb_.append(",");
		_sb_.append(activepetmodelid);
		_sb_.append(",");
		_sb_.append(petfragmentmap);
		_sb_.append(",");
		_sb_.append(totalcombatpower);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("petmap"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("fightpets"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activepetmodelid"));
		lb.add(new xdb.logs.ListenableMap().setVarName("petfragmentmap"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("totalcombatpower"));
		return lb;
	}

	private class Const implements xbean.RolePet {
		RolePet nThis() {
			return RolePet.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RolePet copy() {
			return RolePet.this.copy();
		}

		@Override
		public xbean.RolePet toData() {
			return RolePet.this.toData();
		}

		public xbean.RolePet toBean() {
			return RolePet.this.toBean();
		}

		@Override
		public xbean.RolePet toDataIf() {
			return RolePet.this.toDataIf();
		}

		public xbean.RolePet toBeanIf() {
			return RolePet.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.Pet> getPetmap() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(petmap);
		}

		@Override
		public java.util.Map<Integer, xbean.Pet> getPetmapAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.Pet> petmap;
			RolePet _o_ = RolePet.this;
			petmap = new java.util.HashMap<Integer, xbean.Pet>();
			for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : _o_.petmap.entrySet())
				petmap.put(_e_.getKey(), new Pet.Data(_e_.getValue()));
			return petmap;
		}

		@Override
		public java.util.List<Integer> getFightpets() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(fightpets);
		}

		public java.util.List<Integer> getFightpetsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> fightpets;
			RolePet _o_ = RolePet.this;
		fightpets = new java.util.LinkedList<Integer>();
		fightpets.addAll(_o_.fightpets);
			return fightpets;
		}

		@Override
		public int getActivepetmodelid() { // 
			_xdb_verify_unsafe_();
			return activepetmodelid;
		}

		@Override
		public java.util.Map<Integer, Integer> getPetfragmentmap() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(petfragmentmap);
		}

		@Override
		public java.util.Map<Integer, Integer> getPetfragmentmapAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> petfragmentmap;
			RolePet _o_ = RolePet.this;
			petfragmentmap = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.petfragmentmap.entrySet())
				petfragmentmap.put(_e_.getKey(), _e_.getValue());
			return petfragmentmap;
		}

		@Override
		public int getTotalcombatpower() { // 
			_xdb_verify_unsafe_();
			return totalcombatpower;
		}

		@Override
		public void setActivepetmodelid(int _v_) { // 
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
			return RolePet.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RolePet.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RolePet.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RolePet.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RolePet.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RolePet.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RolePet.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RolePet.this.hashCode();
		}

		@Override
		public String toString() {
			return RolePet.this.toString();
		}

	}

	public static final class Data implements xbean.RolePet {
		private java.util.HashMap<Integer, xbean.Pet> petmap; // 
		private java.util.LinkedList<Integer> fightpets; // 
		private int activepetmodelid; // 
		private java.util.HashMap<Integer, Integer> petfragmentmap; // 
		private int totalcombatpower; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			petmap = new java.util.HashMap<Integer, xbean.Pet>();
			fightpets = new java.util.LinkedList<Integer>();
			petfragmentmap = new java.util.HashMap<Integer, Integer>();
		}

		Data(xbean.RolePet _o1_) {
			if (_o1_ instanceof RolePet) assign((RolePet)_o1_);
			else if (_o1_ instanceof RolePet.Data) assign((RolePet.Data)_o1_);
			else if (_o1_ instanceof RolePet.Const) assign(((RolePet.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RolePet _o_) {
			petmap = new java.util.HashMap<Integer, xbean.Pet>();
			for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : _o_.petmap.entrySet())
				petmap.put(_e_.getKey(), new Pet.Data(_e_.getValue()));
			fightpets = new java.util.LinkedList<Integer>();
			fightpets.addAll(_o_.fightpets);
			activepetmodelid = _o_.activepetmodelid;
			petfragmentmap = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.petfragmentmap.entrySet())
				petfragmentmap.put(_e_.getKey(), _e_.getValue());
			totalcombatpower = _o_.totalcombatpower;
		}

		private void assign(RolePet.Data _o_) {
			petmap = new java.util.HashMap<Integer, xbean.Pet>();
			for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : _o_.petmap.entrySet())
				petmap.put(_e_.getKey(), new Pet.Data(_e_.getValue()));
			fightpets = new java.util.LinkedList<Integer>();
			fightpets.addAll(_o_.fightpets);
			activepetmodelid = _o_.activepetmodelid;
			petfragmentmap = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.petfragmentmap.entrySet())
				petfragmentmap.put(_e_.getKey(), _e_.getValue());
			totalcombatpower = _o_.totalcombatpower;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)5);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(petmap.size());
for (java.util.Map.Entry<Integer, xbean.Pet> _e_ : petmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fightpets.size());
for (Integer _v_ : fightpets) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  3));_os_.marshal(activepetmodelid);
	_os_.marshal((short)(24576|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(petfragmentmap.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : petfragmentmap.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  5));_os_.marshal(totalcombatpower);
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
		petmap = new java.util.HashMap<Integer, xbean.Pet>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.Pet _v_ = xbean.Pod.newPetData();
		_v_.unmarshal(_os_);
		petmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (22528|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	fightpets.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  3):activepetmodelid = _os_.unmarshal_int();
					break;
					case ( 6144|  3):activepetmodelid = _os_.unmarshal_short();
					break;
					case (24576|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		petfragmentmap = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		petfragmentmap.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192|  5):totalcombatpower = _os_.unmarshal_int();
					break;
					case ( 6144|  5):totalcombatpower = _os_.unmarshal_short();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RolePet copy() {
			return new Data(this);
		}

		@Override
		public xbean.RolePet toData() {
			return new Data(this);
		}

		public xbean.RolePet toBean() {
			return new RolePet(this, null, null);
		}

		@Override
		public xbean.RolePet toDataIf() {
			return this;
		}

		public xbean.RolePet toBeanIf() {
			return new RolePet(this, null, null);
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
		public java.util.Map<Integer, xbean.Pet> getPetmap() { // 
			return petmap;
		}

		@Override
		public java.util.Map<Integer, xbean.Pet> getPetmapAsData() { // 
			return petmap;
		}

		@Override
		public java.util.List<Integer> getFightpets() { // 
			return fightpets;
		}

		@Override
		public java.util.List<Integer> getFightpetsAsData() { // 
			return fightpets;
		}

		@Override
		public int getActivepetmodelid() { // 
			return activepetmodelid;
		}

		@Override
		public java.util.Map<Integer, Integer> getPetfragmentmap() { // 
			return petfragmentmap;
		}

		@Override
		public java.util.Map<Integer, Integer> getPetfragmentmapAsData() { // 
			return petfragmentmap;
		}

		@Override
		public int getTotalcombatpower() { // 
			return totalcombatpower;
		}

		@Override
		public void setActivepetmodelid(int _v_) { // 
			activepetmodelid = _v_;
		}

		@Override
		public void setTotalcombatpower(int _v_) { // 
			totalcombatpower = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RolePet.Data)) return false;
			RolePet.Data _o_ = (RolePet.Data) _o1_;
			if (!petmap.equals(_o_.petmap)) return false;
			if (!fightpets.equals(_o_.fightpets)) return false;
			if (activepetmodelid != _o_.activepetmodelid) return false;
			if (!petfragmentmap.equals(_o_.petfragmentmap)) return false;
			if (totalcombatpower != _o_.totalcombatpower) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += petmap.hashCode();
			_h_ += fightpets.hashCode();
			_h_ += activepetmodelid;
			_h_ += petfragmentmap.hashCode();
			_h_ += totalcombatpower;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(petmap);
			_sb_.append(",");
			_sb_.append(fightpets);
			_sb_.append(",");
			_sb_.append(activepetmodelid);
			_sb_.append(",");
			_sb_.append(petfragmentmap);
			_sb_.append(",");
			_sb_.append(totalcombatpower);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
