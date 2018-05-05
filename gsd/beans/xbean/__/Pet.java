
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Pet extends xdb.XBean implements xbean.Pet {
	private int modelid; // 
	private int activeskinid; // 
	private xdb.util.SetX<Integer> skinidlist; // 
	private int level; // 伙伴的级别
	private long exp; // 伙伴的经验值
	private int starlevel; // 星阶
	private java.util.HashMap<Integer, Integer> skills; // 伙伴的技能信息
	private int awakelevel; // 觉醒的等级
	private java.util.HashMap<Integer, Float> fixedattrs; // 固定属性,不会被替换,目前只有buff属性添加到里面
	private java.util.HashMap<Integer, Float> karmaattrs; // 缘分属性,会有变化
	private java.util.HashMap<Integer, Float> growthattrs; // 成长属性,会有变化
	private java.util.LinkedList<Integer> buffids; // 
	private java.util.HashMap<Integer, Float> lastwashrecord; // 
	private java.util.HashMap<Integer, Float> washattrs; // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存

	@Override
	public void _reset_unsafe_() {
		modelid = 0;
		activeskinid = 0;
		skinidlist.clear();
		level = 0;
		exp = 0L;
		starlevel = 0;
		skills.clear();
		awakelevel = 0;
		fixedattrs.clear();
		karmaattrs.clear();
		growthattrs.clear();
		buffids.clear();
		lastwashrecord.clear();
		washattrs.clear();
	}

	Pet(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		skinidlist = new xdb.util.SetX<Integer>();
		skills = new java.util.HashMap<Integer, Integer>();
		fixedattrs = new java.util.HashMap<Integer, Float>();
		karmaattrs = new java.util.HashMap<Integer, Float>();
		growthattrs = new java.util.HashMap<Integer, Float>();
		buffids = new java.util.LinkedList<Integer>();
		lastwashrecord = new java.util.HashMap<Integer, Float>();
		washattrs = new java.util.HashMap<Integer, Float>();
	}

	public Pet() {
		this(0, null, null);
	}

	public Pet(Pet _o_) {
		this(_o_, null, null);
	}

	Pet(xbean.Pet _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Pet) assign((Pet)_o1_);
		else if (_o1_ instanceof Pet.Data) assign((Pet.Data)_o1_);
		else if (_o1_ instanceof Pet.Const) assign(((Pet.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Pet _o_) {
		_o_._xdb_verify_unsafe_();
		modelid = _o_.modelid;
		activeskinid = _o_.activeskinid;
		skinidlist = new xdb.util.SetX<Integer>();
		skinidlist.addAll(_o_.skinidlist);
		level = _o_.level;
		exp = _o_.exp;
		starlevel = _o_.starlevel;
		skills = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), _e_.getValue());
		awakelevel = _o_.awakelevel;
		fixedattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.fixedattrs.entrySet())
			fixedattrs.put(_e_.getKey(), _e_.getValue());
		karmaattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.karmaattrs.entrySet())
			karmaattrs.put(_e_.getKey(), _e_.getValue());
		growthattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.growthattrs.entrySet())
			growthattrs.put(_e_.getKey(), _e_.getValue());
		buffids = new java.util.LinkedList<Integer>();
		buffids.addAll(_o_.buffids);
		lastwashrecord = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.lastwashrecord.entrySet())
			lastwashrecord.put(_e_.getKey(), _e_.getValue());
		washattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.washattrs.entrySet())
			washattrs.put(_e_.getKey(), _e_.getValue());
	}

	private void assign(Pet.Data _o_) {
		modelid = _o_.modelid;
		activeskinid = _o_.activeskinid;
		skinidlist = new xdb.util.SetX<Integer>();
		skinidlist.addAll(_o_.skinidlist);
		level = _o_.level;
		exp = _o_.exp;
		starlevel = _o_.starlevel;
		skills = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), _e_.getValue());
		awakelevel = _o_.awakelevel;
		fixedattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.fixedattrs.entrySet())
			fixedattrs.put(_e_.getKey(), _e_.getValue());
		karmaattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.karmaattrs.entrySet())
			karmaattrs.put(_e_.getKey(), _e_.getValue());
		growthattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.growthattrs.entrySet())
			growthattrs.put(_e_.getKey(), _e_.getValue());
		buffids = new java.util.LinkedList<Integer>();
		buffids.addAll(_o_.buffids);
		lastwashrecord = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.lastwashrecord.entrySet())
			lastwashrecord.put(_e_.getKey(), _e_.getValue());
		washattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.washattrs.entrySet())
			washattrs.put(_e_.getKey(), _e_.getValue());
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)14);
    _os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
    _os_.marshal((short)( 8192|  3));_os_.marshal(activeskinid);
    _os_.marshal((short)(20480|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skinidlist.size());
for (Integer _v_ : skinidlist) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  5));_os_.marshal(level);
    _os_.marshal((short)(10240| 15));_os_.marshal(exp);
    _os_.marshal((short)( 8192|  7));_os_.marshal(starlevel);
    _os_.marshal((short)(24576|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  9));_os_.marshal(awakelevel);
    _os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fixedattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : fixedattrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(karmaattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : karmaattrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 12));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(growthattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : growthattrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(buffids.size());
for (Integer _v_ : buffids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 14));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lastwashrecord.size());
for (java.util.Map.Entry<Integer, Float> _e_ : lastwashrecord.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(washattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : washattrs.entrySet())
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
    				case ( 8192|  2):modelid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  2):modelid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  3):activeskinid = _os_.unmarshal_int();
    				break;
    				case ( 6144|  3):activeskinid = _os_.unmarshal_short();
    				break;
    				case (20480|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	skinidlist.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  5):level = _os_.unmarshal_int();
    				break;
    				case ( 6144|  5):level = _os_.unmarshal_short();
    				break;
    				case (10240| 15):exp = _os_.unmarshal_long();
    				break;
    				case ( 6144| 15):exp = _os_.unmarshal_short();
    				break;
    				case ( 8192| 15):exp = _os_.unmarshal_int();
    				break;
    				case ( 8192|  7):starlevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  7):starlevel = _os_.unmarshal_short();
    				break;
    				case (24576|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
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
    				case ( 8192|  9):awakelevel = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):awakelevel = _os_.unmarshal_short();
    				break;
    				case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		fixedattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		fixedattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		karmaattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		karmaattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 12):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		growthattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		growthattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (22528| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	buffids.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (24576| 14):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		lastwashrecord = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		lastwashrecord.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		washattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		washattrs.put(_k_, _v_);
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
	public xbean.Pet copy() {
		_xdb_verify_unsafe_();
		return new Pet(this);
	}

	@Override
	public xbean.Pet toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Pet toBean() {
		_xdb_verify_unsafe_();
		return new Pet(this); // same as copy()
	}

	@Override
	public xbean.Pet toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Pet toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public int getModelid() { // 
		_xdb_verify_unsafe_();
		return modelid;
	}

	@Override
	public int getActiveskinid() { // 
		_xdb_verify_unsafe_();
		return activeskinid;
	}

	@Override
	public java.util.Set<Integer> getSkinidlist() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "skinidlist"), skinidlist);
	}

	public java.util.Set<Integer> getSkinidlistAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Set<Integer> skinidlist;
		Pet _o_ = this;
		skinidlist = new xdb.util.SetX<Integer>();
		skinidlist.addAll(_o_.skinidlist);
		return skinidlist;
	}

	@Override
	public int getLevel() { // 伙伴的级别
		_xdb_verify_unsafe_();
		return level;
	}

	@Override
	public long getExp() { // 伙伴的经验值
		_xdb_verify_unsafe_();
		return exp;
	}

	@Override
	public int getStarlevel() { // 星阶
		_xdb_verify_unsafe_();
		return starlevel;
	}

	@Override
	public java.util.Map<Integer, Integer> getSkills() { // 伙伴的技能信息
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "skills"), skills);
	}

	@Override
	public java.util.Map<Integer, Integer> getSkillsAsData() { // 伙伴的技能信息
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> skills;
		Pet _o_ = this;
		skills = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
			skills.put(_e_.getKey(), _e_.getValue());
		return skills;
	}

	@Override
	public int getAwakelevel() { // 觉醒的等级
		_xdb_verify_unsafe_();
		return awakelevel;
	}

	@Override
	public java.util.Map<Integer, Float> getFixedattrs() { // 固定属性,不会被替换,目前只有buff属性添加到里面
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "fixedattrs"), fixedattrs);
	}

	@Override
	public java.util.Map<Integer, Float> getFixedattrsAsData() { // 固定属性,不会被替换,目前只有buff属性添加到里面
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Float> fixedattrs;
		Pet _o_ = this;
		fixedattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.fixedattrs.entrySet())
			fixedattrs.put(_e_.getKey(), _e_.getValue());
		return fixedattrs;
	}

	@Override
	public java.util.Map<Integer, Float> getKarmaattrs() { // 缘分属性,会有变化
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "karmaattrs"), karmaattrs);
	}

	@Override
	public java.util.Map<Integer, Float> getKarmaattrsAsData() { // 缘分属性,会有变化
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Float> karmaattrs;
		Pet _o_ = this;
		karmaattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.karmaattrs.entrySet())
			karmaattrs.put(_e_.getKey(), _e_.getValue());
		return karmaattrs;
	}

	@Override
	public java.util.Map<Integer, Float> getGrowthattrs() { // 成长属性,会有变化
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "growthattrs"), growthattrs);
	}

	@Override
	public java.util.Map<Integer, Float> getGrowthattrsAsData() { // 成长属性,会有变化
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Float> growthattrs;
		Pet _o_ = this;
		growthattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.growthattrs.entrySet())
			growthattrs.put(_e_.getKey(), _e_.getValue());
		return growthattrs;
	}

	@Override
	public java.util.List<Integer> getBuffids() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "buffids"), buffids);
	}

	public java.util.List<Integer> getBuffidsAsData() { // 
		_xdb_verify_unsafe_();
		java.util.List<Integer> buffids;
		Pet _o_ = this;
		buffids = new java.util.LinkedList<Integer>();
		buffids.addAll(_o_.buffids);
		return buffids;
	}

	@Override
	public java.util.Map<Integer, Float> getLastwashrecord() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "lastwashrecord"), lastwashrecord);
	}

	@Override
	public java.util.Map<Integer, Float> getLastwashrecordAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Float> lastwashrecord;
		Pet _o_ = this;
		lastwashrecord = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.lastwashrecord.entrySet())
			lastwashrecord.put(_e_.getKey(), _e_.getValue());
		return lastwashrecord;
	}

	@Override
	public java.util.Map<Integer, Float> getWashattrs() { // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "washattrs"), washattrs);
	}

	@Override
	public java.util.Map<Integer, Float> getWashattrsAsData() { // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Float> washattrs;
		Pet _o_ = this;
		washattrs = new java.util.HashMap<Integer, Float>();
		for (java.util.Map.Entry<Integer, Float> _e_ : _o_.washattrs.entrySet())
			washattrs.put(_e_.getKey(), _e_.getValue());
		return washattrs;
	}

	@Override
	public void setModelid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "modelid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, modelid) {
					public void rollback() { modelid = _xdb_saved; }
				};}});
		modelid = _v_;
	}

	@Override
	public void setActiveskinid(int _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "activeskinid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, activeskinid) {
					public void rollback() { activeskinid = _xdb_saved; }
				};}});
		activeskinid = _v_;
	}

	@Override
	public void setLevel(int _v_) { // 伙伴的级别
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "level") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, level) {
					public void rollback() { level = _xdb_saved; }
				};}});
		level = _v_;
	}

	@Override
	public void setExp(long _v_) { // 伙伴的经验值
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "exp") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, exp) {
					public void rollback() { exp = _xdb_saved; }
				};}});
		exp = _v_;
	}

	@Override
	public void setStarlevel(int _v_) { // 星阶
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "starlevel") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, starlevel) {
					public void rollback() { starlevel = _xdb_saved; }
				};}});
		starlevel = _v_;
	}

	@Override
	public void setAwakelevel(int _v_) { // 觉醒的等级
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
		Pet _o_ = null;
		if ( _o1_ instanceof Pet ) _o_ = (Pet)_o1_;
		else if ( _o1_ instanceof Pet.Const ) _o_ = ((Pet.Const)_o1_).nThis();
		else return false;
		if (modelid != _o_.modelid) return false;
		if (activeskinid != _o_.activeskinid) return false;
		if (!skinidlist.equals(_o_.skinidlist)) return false;
		if (level != _o_.level) return false;
		if (exp != _o_.exp) return false;
		if (starlevel != _o_.starlevel) return false;
		if (!skills.equals(_o_.skills)) return false;
		if (awakelevel != _o_.awakelevel) return false;
		if (!fixedattrs.equals(_o_.fixedattrs)) return false;
		if (!karmaattrs.equals(_o_.karmaattrs)) return false;
		if (!growthattrs.equals(_o_.growthattrs)) return false;
		if (!buffids.equals(_o_.buffids)) return false;
		if (!lastwashrecord.equals(_o_.lastwashrecord)) return false;
		if (!washattrs.equals(_o_.washattrs)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += modelid;
		_h_ += activeskinid;
		_h_ += skinidlist.hashCode();
		_h_ += level;
		_h_ += exp;
		_h_ += starlevel;
		_h_ += skills.hashCode();
		_h_ += awakelevel;
		_h_ += fixedattrs.hashCode();
		_h_ += karmaattrs.hashCode();
		_h_ += growthattrs.hashCode();
		_h_ += buffids.hashCode();
		_h_ += lastwashrecord.hashCode();
		_h_ += washattrs.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(modelid);
		_sb_.append(",");
		_sb_.append(activeskinid);
		_sb_.append(",");
		_sb_.append(skinidlist);
		_sb_.append(",");
		_sb_.append(level);
		_sb_.append(",");
		_sb_.append(exp);
		_sb_.append(",");
		_sb_.append(starlevel);
		_sb_.append(",");
		_sb_.append(skills);
		_sb_.append(",");
		_sb_.append(awakelevel);
		_sb_.append(",");
		_sb_.append(fixedattrs);
		_sb_.append(",");
		_sb_.append(karmaattrs);
		_sb_.append(",");
		_sb_.append(growthattrs);
		_sb_.append(",");
		_sb_.append(buffids);
		_sb_.append(",");
		_sb_.append(lastwashrecord);
		_sb_.append(",");
		_sb_.append(washattrs);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("modelid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("activeskinid"));
		lb.add(new xdb.logs.ListenableSet().setVarName("skinidlist"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("level"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("exp"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("starlevel"));
		lb.add(new xdb.logs.ListenableMap().setVarName("skills"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("awakelevel"));
		lb.add(new xdb.logs.ListenableMap().setVarName("fixedattrs"));
		lb.add(new xdb.logs.ListenableMap().setVarName("karmaattrs"));
		lb.add(new xdb.logs.ListenableMap().setVarName("growthattrs"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("buffids"));
		lb.add(new xdb.logs.ListenableMap().setVarName("lastwashrecord"));
		lb.add(new xdb.logs.ListenableMap().setVarName("washattrs"));
		return lb;
	}

	private class Const implements xbean.Pet {
		Pet nThis() {
			return Pet.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Pet copy() {
			return Pet.this.copy();
		}

		@Override
		public xbean.Pet toData() {
			return Pet.this.toData();
		}

		public xbean.Pet toBean() {
			return Pet.this.toBean();
		}

		@Override
		public xbean.Pet toDataIf() {
			return Pet.this.toDataIf();
		}

		public xbean.Pet toBeanIf() {
			return Pet.this.toBeanIf();
		}

		@Override
		public int getModelid() { // 
			_xdb_verify_unsafe_();
			return modelid;
		}

		@Override
		public int getActiveskinid() { // 
			_xdb_verify_unsafe_();
			return activeskinid;
		}

		@Override
		public java.util.Set<Integer> getSkinidlist() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(skinidlist);
		}

		public java.util.Set<Integer> getSkinidlistAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Set<Integer> skinidlist;
			Pet _o_ = Pet.this;
		skinidlist = new xdb.util.SetX<Integer>();
		skinidlist.addAll(_o_.skinidlist);
			return skinidlist;
		}

		@Override
		public int getLevel() { // 伙伴的级别
			_xdb_verify_unsafe_();
			return level;
		}

		@Override
		public long getExp() { // 伙伴的经验值
			_xdb_verify_unsafe_();
			return exp;
		}

		@Override
		public int getStarlevel() { // 星阶
			_xdb_verify_unsafe_();
			return starlevel;
		}

		@Override
		public java.util.Map<Integer, Integer> getSkills() { // 伙伴的技能信息
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(skills);
		}

		@Override
		public java.util.Map<Integer, Integer> getSkillsAsData() { // 伙伴的技能信息
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> skills;
			Pet _o_ = Pet.this;
			skills = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), _e_.getValue());
			return skills;
		}

		@Override
		public int getAwakelevel() { // 觉醒的等级
			_xdb_verify_unsafe_();
			return awakelevel;
		}

		@Override
		public java.util.Map<Integer, Float> getFixedattrs() { // 固定属性,不会被替换,目前只有buff属性添加到里面
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(fixedattrs);
		}

		@Override
		public java.util.Map<Integer, Float> getFixedattrsAsData() { // 固定属性,不会被替换,目前只有buff属性添加到里面
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Float> fixedattrs;
			Pet _o_ = Pet.this;
			fixedattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.fixedattrs.entrySet())
				fixedattrs.put(_e_.getKey(), _e_.getValue());
			return fixedattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getKarmaattrs() { // 缘分属性,会有变化
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(karmaattrs);
		}

		@Override
		public java.util.Map<Integer, Float> getKarmaattrsAsData() { // 缘分属性,会有变化
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Float> karmaattrs;
			Pet _o_ = Pet.this;
			karmaattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.karmaattrs.entrySet())
				karmaattrs.put(_e_.getKey(), _e_.getValue());
			return karmaattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getGrowthattrs() { // 成长属性,会有变化
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(growthattrs);
		}

		@Override
		public java.util.Map<Integer, Float> getGrowthattrsAsData() { // 成长属性,会有变化
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Float> growthattrs;
			Pet _o_ = Pet.this;
			growthattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.growthattrs.entrySet())
				growthattrs.put(_e_.getKey(), _e_.getValue());
			return growthattrs;
		}

		@Override
		public java.util.List<Integer> getBuffids() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(buffids);
		}

		public java.util.List<Integer> getBuffidsAsData() { // 
			_xdb_verify_unsafe_();
			java.util.List<Integer> buffids;
			Pet _o_ = Pet.this;
		buffids = new java.util.LinkedList<Integer>();
		buffids.addAll(_o_.buffids);
			return buffids;
		}

		@Override
		public java.util.Map<Integer, Float> getLastwashrecord() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(lastwashrecord);
		}

		@Override
		public java.util.Map<Integer, Float> getLastwashrecordAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Float> lastwashrecord;
			Pet _o_ = Pet.this;
			lastwashrecord = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.lastwashrecord.entrySet())
				lastwashrecord.put(_e_.getKey(), _e_.getValue());
			return lastwashrecord;
		}

		@Override
		public java.util.Map<Integer, Float> getWashattrs() { // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(washattrs);
		}

		@Override
		public java.util.Map<Integer, Float> getWashattrsAsData() { // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Float> washattrs;
			Pet _o_ = Pet.this;
			washattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.washattrs.entrySet())
				washattrs.put(_e_.getKey(), _e_.getValue());
			return washattrs;
		}

		@Override
		public void setModelid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setActiveskinid(int _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLevel(int _v_) { // 伙伴的级别
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setExp(long _v_) { // 伙伴的经验值
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setStarlevel(int _v_) { // 星阶
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAwakelevel(int _v_) { // 觉醒的等级
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
			return Pet.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Pet.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Pet.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Pet.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Pet.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Pet.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Pet.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Pet.this.hashCode();
		}

		@Override
		public String toString() {
			return Pet.this.toString();
		}

	}

	public static final class Data implements xbean.Pet {
		private int modelid; // 
		private int activeskinid; // 
		private java.util.HashSet<Integer> skinidlist; // 
		private int level; // 伙伴的级别
		private long exp; // 伙伴的经验值
		private int starlevel; // 星阶
		private java.util.HashMap<Integer, Integer> skills; // 伙伴的技能信息
		private int awakelevel; // 觉醒的等级
		private java.util.HashMap<Integer, Float> fixedattrs; // 固定属性,不会被替换,目前只有buff属性添加到里面
		private java.util.HashMap<Integer, Float> karmaattrs; // 缘分属性,会有变化
		private java.util.HashMap<Integer, Float> growthattrs; // 成长属性,会有变化
		private java.util.LinkedList<Integer> buffids; // 
		private java.util.HashMap<Integer, Float> lastwashrecord; // 
		private java.util.HashMap<Integer, Float> washattrs; // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			skinidlist = new java.util.HashSet<Integer>();
			skills = new java.util.HashMap<Integer, Integer>();
			fixedattrs = new java.util.HashMap<Integer, Float>();
			karmaattrs = new java.util.HashMap<Integer, Float>();
			growthattrs = new java.util.HashMap<Integer, Float>();
			buffids = new java.util.LinkedList<Integer>();
			lastwashrecord = new java.util.HashMap<Integer, Float>();
			washattrs = new java.util.HashMap<Integer, Float>();
		}

		Data(xbean.Pet _o1_) {
			if (_o1_ instanceof Pet) assign((Pet)_o1_);
			else if (_o1_ instanceof Pet.Data) assign((Pet.Data)_o1_);
			else if (_o1_ instanceof Pet.Const) assign(((Pet.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Pet _o_) {
			modelid = _o_.modelid;
			activeskinid = _o_.activeskinid;
			skinidlist = new java.util.HashSet<Integer>();
			skinidlist.addAll(_o_.skinidlist);
			level = _o_.level;
			exp = _o_.exp;
			starlevel = _o_.starlevel;
			skills = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), _e_.getValue());
			awakelevel = _o_.awakelevel;
			fixedattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.fixedattrs.entrySet())
				fixedattrs.put(_e_.getKey(), _e_.getValue());
			karmaattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.karmaattrs.entrySet())
				karmaattrs.put(_e_.getKey(), _e_.getValue());
			growthattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.growthattrs.entrySet())
				growthattrs.put(_e_.getKey(), _e_.getValue());
			buffids = new java.util.LinkedList<Integer>();
			buffids.addAll(_o_.buffids);
			lastwashrecord = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.lastwashrecord.entrySet())
				lastwashrecord.put(_e_.getKey(), _e_.getValue());
			washattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.washattrs.entrySet())
				washattrs.put(_e_.getKey(), _e_.getValue());
		}

		private void assign(Pet.Data _o_) {
			modelid = _o_.modelid;
			activeskinid = _o_.activeskinid;
			skinidlist = new java.util.HashSet<Integer>();
			skinidlist.addAll(_o_.skinidlist);
			level = _o_.level;
			exp = _o_.exp;
			starlevel = _o_.starlevel;
			skills = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.skills.entrySet())
				skills.put(_e_.getKey(), _e_.getValue());
			awakelevel = _o_.awakelevel;
			fixedattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.fixedattrs.entrySet())
				fixedattrs.put(_e_.getKey(), _e_.getValue());
			karmaattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.karmaattrs.entrySet())
				karmaattrs.put(_e_.getKey(), _e_.getValue());
			growthattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.growthattrs.entrySet())
				growthattrs.put(_e_.getKey(), _e_.getValue());
			buffids = new java.util.LinkedList<Integer>();
			buffids.addAll(_o_.buffids);
			lastwashrecord = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.lastwashrecord.entrySet())
				lastwashrecord.put(_e_.getKey(), _e_.getValue());
			washattrs = new java.util.HashMap<Integer, Float>();
			for (java.util.Map.Entry<Integer, Float> _e_ : _o_.washattrs.entrySet())
				washattrs.put(_e_.getKey(), _e_.getValue());
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)14);
	_os_.marshal((short)( 8192|  2));_os_.marshal(modelid);
	_os_.marshal((short)( 8192|  3));_os_.marshal(activeskinid);
	_os_.marshal((short)(20480|  4));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skinidlist.size());
for (Integer _v_ : skinidlist) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  5));_os_.marshal(level);
	_os_.marshal((short)(10240| 15));_os_.marshal(exp);
	_os_.marshal((short)( 8192|  7));_os_.marshal(starlevel);
	_os_.marshal((short)(24576|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(skills.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : skills.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  9));_os_.marshal(awakelevel);
	_os_.marshal((short)(24576| 10));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(fixedattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : fixedattrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 11));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(karmaattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : karmaattrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 12));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(growthattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : growthattrs.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528| 13));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(buffids.size());
for (Integer _v_ : buffids) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 14));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lastwashrecord.size());
for (java.util.Map.Entry<Integer, Float> _e_ : lastwashrecord.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 16));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(washattrs.size());
for (java.util.Map.Entry<Integer, Float> _e_ : washattrs.entrySet())
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
					case ( 8192|  2):modelid = _os_.unmarshal_int();
					break;
					case ( 6144|  2):modelid = _os_.unmarshal_short();
					break;
					case ( 8192|  3):activeskinid = _os_.unmarshal_int();
					break;
					case ( 6144|  3):activeskinid = _os_.unmarshal_short();
					break;
					case (20480|  4):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	skinidlist.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  5):level = _os_.unmarshal_int();
					break;
					case ( 6144|  5):level = _os_.unmarshal_short();
					break;
					case (10240| 15):exp = _os_.unmarshal_long();
					break;
					case ( 6144| 15):exp = _os_.unmarshal_short();
					break;
					case ( 8192| 15):exp = _os_.unmarshal_int();
					break;
					case ( 8192|  7):starlevel = _os_.unmarshal_int();
					break;
					case ( 6144|  7):starlevel = _os_.unmarshal_short();
					break;
					case (24576|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
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
					case ( 8192|  9):awakelevel = _os_.unmarshal_int();
					break;
					case ( 6144|  9):awakelevel = _os_.unmarshal_short();
					break;
					case (24576| 10):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		fixedattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		fixedattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 11):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		karmaattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		karmaattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 12):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		growthattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		growthattrs.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (22528| 13):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	buffids.add(_v_);
}
_os_ = _temp_;}
					break;
					case (24576| 14):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		lastwashrecord = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		lastwashrecord.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 16):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		washattrs = new java.util.HashMap<Integer, Float>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		float _v_ = 0.0f;
		_v_ = _os_.unmarshal_float();
		washattrs.put(_k_, _v_);
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
		public xbean.Pet copy() {
			return new Data(this);
		}

		@Override
		public xbean.Pet toData() {
			return new Data(this);
		}

		public xbean.Pet toBean() {
			return new Pet(this, null, null);
		}

		@Override
		public xbean.Pet toDataIf() {
			return this;
		}

		public xbean.Pet toBeanIf() {
			return new Pet(this, null, null);
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
		public int getModelid() { // 
			return modelid;
		}

		@Override
		public int getActiveskinid() { // 
			return activeskinid;
		}

		@Override
		public java.util.Set<Integer> getSkinidlist() { // 
			return skinidlist;
		}

		@Override
		public java.util.Set<Integer> getSkinidlistAsData() { // 
			return skinidlist;
		}

		@Override
		public int getLevel() { // 伙伴的级别
			return level;
		}

		@Override
		public long getExp() { // 伙伴的经验值
			return exp;
		}

		@Override
		public int getStarlevel() { // 星阶
			return starlevel;
		}

		@Override
		public java.util.Map<Integer, Integer> getSkills() { // 伙伴的技能信息
			return skills;
		}

		@Override
		public java.util.Map<Integer, Integer> getSkillsAsData() { // 伙伴的技能信息
			return skills;
		}

		@Override
		public int getAwakelevel() { // 觉醒的等级
			return awakelevel;
		}

		@Override
		public java.util.Map<Integer, Float> getFixedattrs() { // 固定属性,不会被替换,目前只有buff属性添加到里面
			return fixedattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getFixedattrsAsData() { // 固定属性,不会被替换,目前只有buff属性添加到里面
			return fixedattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getKarmaattrs() { // 缘分属性,会有变化
			return karmaattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getKarmaattrsAsData() { // 缘分属性,会有变化
			return karmaattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getGrowthattrs() { // 成长属性,会有变化
			return growthattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getGrowthattrsAsData() { // 成长属性,会有变化
			return growthattrs;
		}

		@Override
		public java.util.List<Integer> getBuffids() { // 
			return buffids;
		}

		@Override
		public java.util.List<Integer> getBuffidsAsData() { // 
			return buffids;
		}

		@Override
		public java.util.Map<Integer, Float> getLastwashrecord() { // 
			return lastwashrecord;
		}

		@Override
		public java.util.Map<Integer, Float> getLastwashrecordAsData() { // 
			return lastwashrecord;
		}

		@Override
		public java.util.Map<Integer, Float> getWashattrs() { // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
			return washattrs;
		}

		@Override
		public java.util.Map<Integer, Float> getWashattrsAsData() { // 洗练属性，不会替换，只累加，但是需要旧值做判断所以单独存
			return washattrs;
		}

		@Override
		public void setModelid(int _v_) { // 
			modelid = _v_;
		}

		@Override
		public void setActiveskinid(int _v_) { // 
			activeskinid = _v_;
		}

		@Override
		public void setLevel(int _v_) { // 伙伴的级别
			level = _v_;
		}

		@Override
		public void setExp(long _v_) { // 伙伴的经验值
			exp = _v_;
		}

		@Override
		public void setStarlevel(int _v_) { // 星阶
			starlevel = _v_;
		}

		@Override
		public void setAwakelevel(int _v_) { // 觉醒的等级
			awakelevel = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Pet.Data)) return false;
			Pet.Data _o_ = (Pet.Data) _o1_;
			if (modelid != _o_.modelid) return false;
			if (activeskinid != _o_.activeskinid) return false;
			if (!skinidlist.equals(_o_.skinidlist)) return false;
			if (level != _o_.level) return false;
			if (exp != _o_.exp) return false;
			if (starlevel != _o_.starlevel) return false;
			if (!skills.equals(_o_.skills)) return false;
			if (awakelevel != _o_.awakelevel) return false;
			if (!fixedattrs.equals(_o_.fixedattrs)) return false;
			if (!karmaattrs.equals(_o_.karmaattrs)) return false;
			if (!growthattrs.equals(_o_.growthattrs)) return false;
			if (!buffids.equals(_o_.buffids)) return false;
			if (!lastwashrecord.equals(_o_.lastwashrecord)) return false;
			if (!washattrs.equals(_o_.washattrs)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += modelid;
			_h_ += activeskinid;
			_h_ += skinidlist.hashCode();
			_h_ += level;
			_h_ += exp;
			_h_ += starlevel;
			_h_ += skills.hashCode();
			_h_ += awakelevel;
			_h_ += fixedattrs.hashCode();
			_h_ += karmaattrs.hashCode();
			_h_ += growthattrs.hashCode();
			_h_ += buffids.hashCode();
			_h_ += lastwashrecord.hashCode();
			_h_ += washattrs.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(modelid);
			_sb_.append(",");
			_sb_.append(activeskinid);
			_sb_.append(",");
			_sb_.append(skinidlist);
			_sb_.append(",");
			_sb_.append(level);
			_sb_.append(",");
			_sb_.append(exp);
			_sb_.append(",");
			_sb_.append(starlevel);
			_sb_.append(",");
			_sb_.append(skills);
			_sb_.append(",");
			_sb_.append(awakelevel);
			_sb_.append(",");
			_sb_.append(fixedattrs);
			_sb_.append(",");
			_sb_.append(karmaattrs);
			_sb_.append(",");
			_sb_.append(growthattrs);
			_sb_.append(",");
			_sb_.append(buffids);
			_sb_.append(",");
			_sb_.append(lastwashrecord);
			_sb_.append(",");
			_sb_.append(washattrs);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
