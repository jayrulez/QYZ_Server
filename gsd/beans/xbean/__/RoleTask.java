
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleTask extends xdb.XBean implements xbean.RoleTask {
	private long roleid; // 
	private long minhistoryexpiretime; // 
	private java.util.HashMap<Integer, xbean.TaskHistory> historys; // 
	private java.util.HashMap<Integer, Integer> lastcompletetaskid; // 记录上一个完成任务的id，包括主线和支线任务
	private int acceptbranchtasks; // 记录接取的支线任务条数
	private xdb.util.SetX<Integer> shownpcs; // 要显式的npcid集合
	private xdb.util.SetX<Integer> hidemines; // 要隐藏的矿物集合
	private java.util.HashMap<Integer, xbean.TaskData> tasks; // 
	private java.util.HashMap<Integer, Integer> variables; // 
	private java.util.ArrayList<xbean.FamilyTaskDetail> acceptedfamilytasks; // 当前接收的环任务概览
	private int completednumsinfamtasks; // 每组环任务中完成的环数
	private int isuseyuanbao; // 是否使用了元宝来直接完成环任务，0，否；1，是
	private int iscancletask; // 是否取消过任务,0，否；1，是
	private int dailycompletedfamtasks; // 每天完成的环任务组数
	private int weekcompletedsmallfamtasks; // 每周完成的环任务组数
	private java.util.LinkedList<Integer> weekspebonus; // 已经领取的周计数任务奖励
	private long lastgiveupfamtasktime; // 上次放弃家族任务的时间
	private int guidebranchtaskid; // 主界面上当前显示的支线任务
	private java.util.HashMap<Integer, Long> accepttasktime; // 接取任务时的时间,记日志用
	private java.util.HashMap<Integer, Integer> familytaskorder; // 测试环任务用，按顺序取环任务
	private java.util.HashMap<Integer, Integer> familylasttaskorder; // 测试环任务用，取最后一环
	private xdb.util.SetX<Integer> allcandobranch; // 所有能做的支线任务的第一个任务id

	@Override
	public void _reset_unsafe_() {
		roleid = 0L;
		minhistoryexpiretime = 0L;
		historys.clear();
		lastcompletetaskid.clear();
		acceptbranchtasks = 0;
		shownpcs.clear();
		hidemines.clear();
		tasks.clear();
		variables.clear();
		acceptedfamilytasks.clear();
		completednumsinfamtasks = 0;
		isuseyuanbao = 0;
		iscancletask = 0;
		dailycompletedfamtasks = 0;
		weekcompletedsmallfamtasks = 0;
		weekspebonus.clear();
		lastgiveupfamtasktime = 0L;
		guidebranchtaskid = 0;
		accepttasktime.clear();
		familytaskorder.clear();
		familylasttaskorder.clear();
		allcandobranch.clear();
	}

	RoleTask(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
		lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
		shownpcs = new xdb.util.SetX<Integer>();
		hidemines = new xdb.util.SetX<Integer>();
		tasks = new java.util.HashMap<Integer, xbean.TaskData>();
		variables = new java.util.HashMap<Integer, Integer>();
		acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
		weekspebonus = new java.util.LinkedList<Integer>();
		accepttasktime = new java.util.HashMap<Integer, Long>();
		familytaskorder = new java.util.HashMap<Integer, Integer>();
		familylasttaskorder = new java.util.HashMap<Integer, Integer>();
		allcandobranch = new xdb.util.SetX<Integer>();
	}

	public RoleTask() {
		this(0, null, null);
	}

	public RoleTask(RoleTask _o_) {
		this(_o_, null, null);
	}

	RoleTask(xbean.RoleTask _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleTask) assign((RoleTask)_o1_);
		else if (_o1_ instanceof RoleTask.Data) assign((RoleTask.Data)_o1_);
		else if (_o1_ instanceof RoleTask.Const) assign(((RoleTask.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleTask _o_) {
		_o_._xdb_verify_unsafe_();
		roleid = _o_.roleid;
		minhistoryexpiretime = _o_.minhistoryexpiretime;
		historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
		for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : _o_.historys.entrySet())
			historys.put(_e_.getKey(), new TaskHistory(_e_.getValue(), this, "historys"));
		lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.lastcompletetaskid.entrySet())
			lastcompletetaskid.put(_e_.getKey(), _e_.getValue());
		acceptbranchtasks = _o_.acceptbranchtasks;
		shownpcs = new xdb.util.SetX<Integer>();
		shownpcs.addAll(_o_.shownpcs);
		hidemines = new xdb.util.SetX<Integer>();
		hidemines.addAll(_o_.hidemines);
		tasks = new java.util.HashMap<Integer, xbean.TaskData>();
		for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : _o_.tasks.entrySet())
			tasks.put(_e_.getKey(), new TaskData(_e_.getValue(), this, "tasks"));
		variables = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.variables.entrySet())
			variables.put(_e_.getKey(), _e_.getValue());
		acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
		for (xbean.FamilyTaskDetail _v_ : _o_.acceptedfamilytasks)
			acceptedfamilytasks.add(new FamilyTaskDetail(_v_, this, "acceptedfamilytasks"));
		completednumsinfamtasks = _o_.completednumsinfamtasks;
		isuseyuanbao = _o_.isuseyuanbao;
		iscancletask = _o_.iscancletask;
		dailycompletedfamtasks = _o_.dailycompletedfamtasks;
		weekcompletedsmallfamtasks = _o_.weekcompletedsmallfamtasks;
		weekspebonus = new java.util.LinkedList<Integer>();
		weekspebonus.addAll(_o_.weekspebonus);
		lastgiveupfamtasktime = _o_.lastgiveupfamtasktime;
		guidebranchtaskid = _o_.guidebranchtaskid;
		accepttasktime = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.accepttasktime.entrySet())
			accepttasktime.put(_e_.getKey(), _e_.getValue());
		familytaskorder = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familytaskorder.entrySet())
			familytaskorder.put(_e_.getKey(), _e_.getValue());
		familylasttaskorder = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familylasttaskorder.entrySet())
			familylasttaskorder.put(_e_.getKey(), _e_.getValue());
		allcandobranch = new xdb.util.SetX<Integer>();
		allcandobranch.addAll(_o_.allcandobranch);
	}

	private void assign(RoleTask.Data _o_) {
		roleid = _o_.roleid;
		minhistoryexpiretime = _o_.minhistoryexpiretime;
		historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
		for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : _o_.historys.entrySet())
			historys.put(_e_.getKey(), new TaskHistory(_e_.getValue(), this, "historys"));
		lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.lastcompletetaskid.entrySet())
			lastcompletetaskid.put(_e_.getKey(), _e_.getValue());
		acceptbranchtasks = _o_.acceptbranchtasks;
		shownpcs = new xdb.util.SetX<Integer>();
		shownpcs.addAll(_o_.shownpcs);
		hidemines = new xdb.util.SetX<Integer>();
		hidemines.addAll(_o_.hidemines);
		tasks = new java.util.HashMap<Integer, xbean.TaskData>();
		for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : _o_.tasks.entrySet())
			tasks.put(_e_.getKey(), new TaskData(_e_.getValue(), this, "tasks"));
		variables = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.variables.entrySet())
			variables.put(_e_.getKey(), _e_.getValue());
		acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
		for (xbean.FamilyTaskDetail _v_ : _o_.acceptedfamilytasks)
			acceptedfamilytasks.add(new FamilyTaskDetail(_v_, this, "acceptedfamilytasks"));
		completednumsinfamtasks = _o_.completednumsinfamtasks;
		isuseyuanbao = _o_.isuseyuanbao;
		iscancletask = _o_.iscancletask;
		dailycompletedfamtasks = _o_.dailycompletedfamtasks;
		weekcompletedsmallfamtasks = _o_.weekcompletedsmallfamtasks;
		weekspebonus = new java.util.LinkedList<Integer>();
		weekspebonus.addAll(_o_.weekspebonus);
		lastgiveupfamtasktime = _o_.lastgiveupfamtasktime;
		guidebranchtaskid = _o_.guidebranchtaskid;
		accepttasktime = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.accepttasktime.entrySet())
			accepttasktime.put(_e_.getKey(), _e_.getValue());
		familytaskorder = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familytaskorder.entrySet())
			familytaskorder.put(_e_.getKey(), _e_.getValue());
		familylasttaskorder = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familylasttaskorder.entrySet())
			familylasttaskorder.put(_e_.getKey(), _e_.getValue());
		allcandobranch = new xdb.util.SetX<Integer>();
		allcandobranch.addAll(_o_.allcandobranch);
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)22);
    _os_.marshal((short)(10240|  6));_os_.marshal(roleid);
    _os_.marshal((short)(10240|  1));_os_.marshal(minhistoryexpiretime);
    _os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(historys.size());
for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : historys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 15));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lastcompletetaskid.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : lastcompletetaskid.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192| 16));_os_.marshal(acceptbranchtasks);
    _os_.marshal((short)(20480| 17));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(shownpcs.size());
for (Integer _v_ : shownpcs) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(20480| 20));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(hidemines.size());
for (Integer _v_ : hidemines) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(tasks.size());
for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : tasks.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(variables.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : variables.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(22528|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(acceptedfamilytasks.size());
for (xbean.FamilyTaskDetail _v_ : acceptedfamilytasks) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)( 8192|  9));_os_.marshal(completednumsinfamtasks);
    _os_.marshal((short)( 8192| 13));_os_.marshal(isuseyuanbao);
    _os_.marshal((short)( 8192| 18));_os_.marshal(iscancletask);
    _os_.marshal((short)( 8192| 10));_os_.marshal(dailycompletedfamtasks);
    _os_.marshal((short)( 8192| 11));_os_.marshal(weekcompletedsmallfamtasks);
    _os_.marshal((short)(22528| 14));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(weekspebonus.size());
for (Integer _v_ : weekspebonus) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(10240| 12));_os_.marshal(lastgiveupfamtasktime);
    _os_.marshal((short)( 8192| 19));_os_.marshal(guidebranchtaskid);
    _os_.marshal((short)(24576| 23));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(accepttasktime.size());
for (java.util.Map.Entry<Integer, Long> _e_ : accepttasktime.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 21));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(familytaskorder.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : familytaskorder.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(24576| 22));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(familylasttaskorder.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : familylasttaskorder.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
    _os_.marshal((short)(20480| 24));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(allcandobranch.size());
for (Integer _v_ : allcandobranch) {
	_os_.marshal(_v_);
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
    				case (10240|  6):roleid = _os_.unmarshal_long();
    				break;
    				case ( 6144|  6):roleid = _os_.unmarshal_short();
    				break;
    				case ( 8192|  6):roleid = _os_.unmarshal_int();
    				break;
    				case (10240|  1):minhistoryexpiretime = _os_.unmarshal_long();
    				break;
    				case ( 6144|  1):minhistoryexpiretime = _os_.unmarshal_short();
    				break;
    				case ( 8192|  1):minhistoryexpiretime = _os_.unmarshal_int();
    				break;
    				case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		historys = new java.util.HashMap<Integer, xbean.TaskHistory>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.TaskHistory _v_ = new TaskHistory(0, this, "historys");
		_v_.unmarshal(_os_);
		historys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 15):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		lastcompletetaskid = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		lastcompletetaskid.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case ( 8192| 16):acceptbranchtasks = _os_.unmarshal_int();
    				break;
    				case ( 6144| 16):acceptbranchtasks = _os_.unmarshal_short();
    				break;
    				case (20480| 17):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	shownpcs.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (20480| 20):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	hidemines.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		tasks = new java.util.HashMap<Integer, xbean.TaskData>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.TaskData _v_ = new TaskData(0, this, "tasks");
		_v_.unmarshal(_os_);
		tasks.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		variables = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		variables.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (22528|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.FamilyTaskDetail _v_ = new FamilyTaskDetail(0, this, "acceptedfamilytasks");
	_v_.unmarshal(_os_);
	acceptedfamilytasks.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case ( 8192|  9):completednumsinfamtasks = _os_.unmarshal_int();
    				break;
    				case ( 6144|  9):completednumsinfamtasks = _os_.unmarshal_short();
    				break;
    				case ( 8192| 13):isuseyuanbao = _os_.unmarshal_int();
    				break;
    				case ( 6144| 13):isuseyuanbao = _os_.unmarshal_short();
    				break;
    				case ( 8192| 18):iscancletask = _os_.unmarshal_int();
    				break;
    				case ( 6144| 18):iscancletask = _os_.unmarshal_short();
    				break;
    				case ( 8192| 10):dailycompletedfamtasks = _os_.unmarshal_int();
    				break;
    				case ( 6144| 10):dailycompletedfamtasks = _os_.unmarshal_short();
    				break;
    				case ( 8192| 11):weekcompletedsmallfamtasks = _os_.unmarshal_int();
    				break;
    				case ( 6144| 11):weekcompletedsmallfamtasks = _os_.unmarshal_short();
    				break;
    				case (22528| 14):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	weekspebonus.add(_v_);
}
_os_ = _temp_;}
    				break;
    				case (10240| 12):lastgiveupfamtasktime = _os_.unmarshal_long();
    				break;
    				case ( 6144| 12):lastgiveupfamtasktime = _os_.unmarshal_short();
    				break;
    				case ( 8192| 12):lastgiveupfamtasktime = _os_.unmarshal_int();
    				break;
    				case ( 8192| 19):guidebranchtaskid = _os_.unmarshal_int();
    				break;
    				case ( 6144| 19):guidebranchtaskid = _os_.unmarshal_short();
    				break;
    				case (24576| 23):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		accepttasktime = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		accepttasktime.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 21):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		familytaskorder = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		familytaskorder.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (24576| 22):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		familylasttaskorder = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		familylasttaskorder.put(_k_, _v_);
	}
}
_os_ = _temp_;}
    				break;
    				case (20480| 24):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	allcandobranch.add(_v_);
}
_os_ = _temp_;}
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.RoleTask copy() {
		_xdb_verify_unsafe_();
		return new RoleTask(this);
	}

	@Override
	public xbean.RoleTask toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTask toBean() {
		_xdb_verify_unsafe_();
		return new RoleTask(this); // same as copy()
	}

	@Override
	public xbean.RoleTask toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleTask toBeanIf() {
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
	public long getMinhistoryexpiretime() { // 
		_xdb_verify_unsafe_();
		return minhistoryexpiretime;
	}

	@Override
	public java.util.Map<Integer, xbean.TaskHistory> getHistorys() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "historys"), historys);
	}

	@Override
	public java.util.Map<Integer, xbean.TaskHistory> getHistorysAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.TaskHistory> historys;
		RoleTask _o_ = this;
		historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
		for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : _o_.historys.entrySet())
			historys.put(_e_.getKey(), new TaskHistory.Data(_e_.getValue()));
		return historys;
	}

	@Override
	public java.util.Map<Integer, Integer> getLastcompletetaskid() { // 记录上一个完成任务的id，包括主线和支线任务
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "lastcompletetaskid"), lastcompletetaskid);
	}

	@Override
	public java.util.Map<Integer, Integer> getLastcompletetaskidAsData() { // 记录上一个完成任务的id，包括主线和支线任务
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> lastcompletetaskid;
		RoleTask _o_ = this;
		lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.lastcompletetaskid.entrySet())
			lastcompletetaskid.put(_e_.getKey(), _e_.getValue());
		return lastcompletetaskid;
	}

	@Override
	public int getAcceptbranchtasks() { // 记录接取的支线任务条数
		_xdb_verify_unsafe_();
		return acceptbranchtasks;
	}

	@Override
	public java.util.Set<Integer> getShownpcs() { // 要显式的npcid集合
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "shownpcs"), shownpcs);
	}

	public java.util.Set<Integer> getShownpcsAsData() { // 要显式的npcid集合
		_xdb_verify_unsafe_();
		java.util.Set<Integer> shownpcs;
		RoleTask _o_ = this;
		shownpcs = new xdb.util.SetX<Integer>();
		shownpcs.addAll(_o_.shownpcs);
		return shownpcs;
	}

	@Override
	public java.util.Set<Integer> getHidemines() { // 要隐藏的矿物集合
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "hidemines"), hidemines);
	}

	public java.util.Set<Integer> getHideminesAsData() { // 要隐藏的矿物集合
		_xdb_verify_unsafe_();
		java.util.Set<Integer> hidemines;
		RoleTask _o_ = this;
		hidemines = new xdb.util.SetX<Integer>();
		hidemines.addAll(_o_.hidemines);
		return hidemines;
	}

	@Override
	public java.util.Map<Integer, xbean.TaskData> getTasks() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "tasks"), tasks);
	}

	@Override
	public java.util.Map<Integer, xbean.TaskData> getTasksAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.TaskData> tasks;
		RoleTask _o_ = this;
		tasks = new java.util.HashMap<Integer, xbean.TaskData>();
		for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : _o_.tasks.entrySet())
			tasks.put(_e_.getKey(), new TaskData.Data(_e_.getValue()));
		return tasks;
	}

	@Override
	public java.util.Map<Integer, Integer> getVariables() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "variables"), variables);
	}

	@Override
	public java.util.Map<Integer, Integer> getVariablesAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> variables;
		RoleTask _o_ = this;
		variables = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.variables.entrySet())
			variables.put(_e_.getKey(), _e_.getValue());
		return variables;
	}

	@Override
	public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasks() { // 当前接收的环任务概览
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "acceptedfamilytasks"), acceptedfamilytasks);
	}

	public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasksAsData() { // 当前接收的环任务概览
		_xdb_verify_unsafe_();
		java.util.List<xbean.FamilyTaskDetail> acceptedfamilytasks;
		RoleTask _o_ = this;
		acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
		for (xbean.FamilyTaskDetail _v_ : _o_.acceptedfamilytasks)
			acceptedfamilytasks.add(new FamilyTaskDetail.Data(_v_));
		return acceptedfamilytasks;
	}

	@Override
	public int getCompletednumsinfamtasks() { // 每组环任务中完成的环数
		_xdb_verify_unsafe_();
		return completednumsinfamtasks;
	}

	@Override
	public int getIsuseyuanbao() { // 是否使用了元宝来直接完成环任务，0，否；1，是
		_xdb_verify_unsafe_();
		return isuseyuanbao;
	}

	@Override
	public int getIscancletask() { // 是否取消过任务,0，否；1，是
		_xdb_verify_unsafe_();
		return iscancletask;
	}

	@Override
	public int getDailycompletedfamtasks() { // 每天完成的环任务组数
		_xdb_verify_unsafe_();
		return dailycompletedfamtasks;
	}

	@Override
	public int getWeekcompletedsmallfamtasks() { // 每周完成的环任务组数
		_xdb_verify_unsafe_();
		return weekcompletedsmallfamtasks;
	}

	@Override
	public java.util.List<Integer> getWeekspebonus() { // 已经领取的周计数任务奖励
		_xdb_verify_unsafe_();
		return xdb.Logs.logList(new xdb.LogKey(this, "weekspebonus"), weekspebonus);
	}

	public java.util.List<Integer> getWeekspebonusAsData() { // 已经领取的周计数任务奖励
		_xdb_verify_unsafe_();
		java.util.List<Integer> weekspebonus;
		RoleTask _o_ = this;
		weekspebonus = new java.util.LinkedList<Integer>();
		weekspebonus.addAll(_o_.weekspebonus);
		return weekspebonus;
	}

	@Override
	public long getLastgiveupfamtasktime() { // 上次放弃家族任务的时间
		_xdb_verify_unsafe_();
		return lastgiveupfamtasktime;
	}

	@Override
	public int getGuidebranchtaskid() { // 主界面上当前显示的支线任务
		_xdb_verify_unsafe_();
		return guidebranchtaskid;
	}

	@Override
	public java.util.Map<Integer, Long> getAccepttasktime() { // 接取任务时的时间,记日志用
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "accepttasktime"), accepttasktime);
	}

	@Override
	public java.util.Map<Integer, Long> getAccepttasktimeAsData() { // 接取任务时的时间,记日志用
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Long> accepttasktime;
		RoleTask _o_ = this;
		accepttasktime = new java.util.HashMap<Integer, Long>();
		for (java.util.Map.Entry<Integer, Long> _e_ : _o_.accepttasktime.entrySet())
			accepttasktime.put(_e_.getKey(), _e_.getValue());
		return accepttasktime;
	}

	@Override
	public java.util.Map<Integer, Integer> getFamilytaskorder() { // 测试环任务用，按顺序取环任务
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "familytaskorder"), familytaskorder);
	}

	@Override
	public java.util.Map<Integer, Integer> getFamilytaskorderAsData() { // 测试环任务用，按顺序取环任务
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> familytaskorder;
		RoleTask _o_ = this;
		familytaskorder = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familytaskorder.entrySet())
			familytaskorder.put(_e_.getKey(), _e_.getValue());
		return familytaskorder;
	}

	@Override
	public java.util.Map<Integer, Integer> getFamilylasttaskorder() { // 测试环任务用，取最后一环
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "familylasttaskorder"), familylasttaskorder);
	}

	@Override
	public java.util.Map<Integer, Integer> getFamilylasttaskorderAsData() { // 测试环任务用，取最后一环
		_xdb_verify_unsafe_();
		java.util.Map<Integer, Integer> familylasttaskorder;
		RoleTask _o_ = this;
		familylasttaskorder = new java.util.HashMap<Integer, Integer>();
		for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familylasttaskorder.entrySet())
			familylasttaskorder.put(_e_.getKey(), _e_.getValue());
		return familylasttaskorder;
	}

	@Override
	public java.util.Set<Integer> getAllcandobranch() { // 所有能做的支线任务的第一个任务id
		_xdb_verify_unsafe_();
		return xdb.Logs.logSet(new xdb.LogKey(this, "allcandobranch"), allcandobranch);
	}

	public java.util.Set<Integer> getAllcandobranchAsData() { // 所有能做的支线任务的第一个任务id
		_xdb_verify_unsafe_();
		java.util.Set<Integer> allcandobranch;
		RoleTask _o_ = this;
		allcandobranch = new xdb.util.SetX<Integer>();
		allcandobranch.addAll(_o_.allcandobranch);
		return allcandobranch;
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
	public void setMinhistoryexpiretime(long _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "minhistoryexpiretime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, minhistoryexpiretime) {
					public void rollback() { minhistoryexpiretime = _xdb_saved; }
				};}});
		minhistoryexpiretime = _v_;
	}

	@Override
	public void setAcceptbranchtasks(int _v_) { // 记录接取的支线任务条数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "acceptbranchtasks") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, acceptbranchtasks) {
					public void rollback() { acceptbranchtasks = _xdb_saved; }
				};}});
		acceptbranchtasks = _v_;
	}

	@Override
	public void setCompletednumsinfamtasks(int _v_) { // 每组环任务中完成的环数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "completednumsinfamtasks") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, completednumsinfamtasks) {
					public void rollback() { completednumsinfamtasks = _xdb_saved; }
				};}});
		completednumsinfamtasks = _v_;
	}

	@Override
	public void setIsuseyuanbao(int _v_) { // 是否使用了元宝来直接完成环任务，0，否；1，是
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "isuseyuanbao") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, isuseyuanbao) {
					public void rollback() { isuseyuanbao = _xdb_saved; }
				};}});
		isuseyuanbao = _v_;
	}

	@Override
	public void setIscancletask(int _v_) { // 是否取消过任务,0，否；1，是
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "iscancletask") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, iscancletask) {
					public void rollback() { iscancletask = _xdb_saved; }
				};}});
		iscancletask = _v_;
	}

	@Override
	public void setDailycompletedfamtasks(int _v_) { // 每天完成的环任务组数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "dailycompletedfamtasks") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, dailycompletedfamtasks) {
					public void rollback() { dailycompletedfamtasks = _xdb_saved; }
				};}});
		dailycompletedfamtasks = _v_;
	}

	@Override
	public void setWeekcompletedsmallfamtasks(int _v_) { // 每周完成的环任务组数
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "weekcompletedsmallfamtasks") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, weekcompletedsmallfamtasks) {
					public void rollback() { weekcompletedsmallfamtasks = _xdb_saved; }
				};}});
		weekcompletedsmallfamtasks = _v_;
	}

	@Override
	public void setLastgiveupfamtasktime(long _v_) { // 上次放弃家族任务的时间
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "lastgiveupfamtasktime") {
			protected xdb.Log create() {
				return new xdb.logs.LogLong(this, lastgiveupfamtasktime) {
					public void rollback() { lastgiveupfamtasktime = _xdb_saved; }
				};}});
		lastgiveupfamtasktime = _v_;
	}

	@Override
	public void setGuidebranchtaskid(int _v_) { // 主界面上当前显示的支线任务
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "guidebranchtaskid") {
			protected xdb.Log create() {
				return new xdb.logs.LogInt(this, guidebranchtaskid) {
					public void rollback() { guidebranchtaskid = _xdb_saved; }
				};}});
		guidebranchtaskid = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleTask _o_ = null;
		if ( _o1_ instanceof RoleTask ) _o_ = (RoleTask)_o1_;
		else if ( _o1_ instanceof RoleTask.Const ) _o_ = ((RoleTask.Const)_o1_).nThis();
		else return false;
		if (roleid != _o_.roleid) return false;
		if (minhistoryexpiretime != _o_.minhistoryexpiretime) return false;
		if (!historys.equals(_o_.historys)) return false;
		if (!lastcompletetaskid.equals(_o_.lastcompletetaskid)) return false;
		if (acceptbranchtasks != _o_.acceptbranchtasks) return false;
		if (!shownpcs.equals(_o_.shownpcs)) return false;
		if (!hidemines.equals(_o_.hidemines)) return false;
		if (!tasks.equals(_o_.tasks)) return false;
		if (!variables.equals(_o_.variables)) return false;
		if (!acceptedfamilytasks.equals(_o_.acceptedfamilytasks)) return false;
		if (completednumsinfamtasks != _o_.completednumsinfamtasks) return false;
		if (isuseyuanbao != _o_.isuseyuanbao) return false;
		if (iscancletask != _o_.iscancletask) return false;
		if (dailycompletedfamtasks != _o_.dailycompletedfamtasks) return false;
		if (weekcompletedsmallfamtasks != _o_.weekcompletedsmallfamtasks) return false;
		if (!weekspebonus.equals(_o_.weekspebonus)) return false;
		if (lastgiveupfamtasktime != _o_.lastgiveupfamtasktime) return false;
		if (guidebranchtaskid != _o_.guidebranchtaskid) return false;
		if (!accepttasktime.equals(_o_.accepttasktime)) return false;
		if (!familytaskorder.equals(_o_.familytaskorder)) return false;
		if (!familylasttaskorder.equals(_o_.familylasttaskorder)) return false;
		if (!allcandobranch.equals(_o_.allcandobranch)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += roleid;
		_h_ += minhistoryexpiretime;
		_h_ += historys.hashCode();
		_h_ += lastcompletetaskid.hashCode();
		_h_ += acceptbranchtasks;
		_h_ += shownpcs.hashCode();
		_h_ += hidemines.hashCode();
		_h_ += tasks.hashCode();
		_h_ += variables.hashCode();
		_h_ += acceptedfamilytasks.hashCode();
		_h_ += completednumsinfamtasks;
		_h_ += isuseyuanbao;
		_h_ += iscancletask;
		_h_ += dailycompletedfamtasks;
		_h_ += weekcompletedsmallfamtasks;
		_h_ += weekspebonus.hashCode();
		_h_ += lastgiveupfamtasktime;
		_h_ += guidebranchtaskid;
		_h_ += accepttasktime.hashCode();
		_h_ += familytaskorder.hashCode();
		_h_ += familylasttaskorder.hashCode();
		_h_ += allcandobranch.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(roleid);
		_sb_.append(",");
		_sb_.append(minhistoryexpiretime);
		_sb_.append(",");
		_sb_.append(historys);
		_sb_.append(",");
		_sb_.append(lastcompletetaskid);
		_sb_.append(",");
		_sb_.append(acceptbranchtasks);
		_sb_.append(",");
		_sb_.append(shownpcs);
		_sb_.append(",");
		_sb_.append(hidemines);
		_sb_.append(",");
		_sb_.append(tasks);
		_sb_.append(",");
		_sb_.append(variables);
		_sb_.append(",");
		_sb_.append(acceptedfamilytasks);
		_sb_.append(",");
		_sb_.append(completednumsinfamtasks);
		_sb_.append(",");
		_sb_.append(isuseyuanbao);
		_sb_.append(",");
		_sb_.append(iscancletask);
		_sb_.append(",");
		_sb_.append(dailycompletedfamtasks);
		_sb_.append(",");
		_sb_.append(weekcompletedsmallfamtasks);
		_sb_.append(",");
		_sb_.append(weekspebonus);
		_sb_.append(",");
		_sb_.append(lastgiveupfamtasktime);
		_sb_.append(",");
		_sb_.append(guidebranchtaskid);
		_sb_.append(",");
		_sb_.append(accepttasktime);
		_sb_.append(",");
		_sb_.append(familytaskorder);
		_sb_.append(",");
		_sb_.append(familylasttaskorder);
		_sb_.append(",");
		_sb_.append(allcandobranch);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("roleid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("minhistoryexpiretime"));
		lb.add(new xdb.logs.ListenableMap().setVarName("historys"));
		lb.add(new xdb.logs.ListenableMap().setVarName("lastcompletetaskid"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("acceptbranchtasks"));
		lb.add(new xdb.logs.ListenableSet().setVarName("shownpcs"));
		lb.add(new xdb.logs.ListenableSet().setVarName("hidemines"));
		lb.add(new xdb.logs.ListenableMap().setVarName("tasks"));
		lb.add(new xdb.logs.ListenableMap().setVarName("variables"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("acceptedfamilytasks"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("completednumsinfamtasks"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("isuseyuanbao"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("iscancletask"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("dailycompletedfamtasks"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("weekcompletedsmallfamtasks"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("weekspebonus"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("lastgiveupfamtasktime"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("guidebranchtaskid"));
		lb.add(new xdb.logs.ListenableMap().setVarName("accepttasktime"));
		lb.add(new xdb.logs.ListenableMap().setVarName("familytaskorder"));
		lb.add(new xdb.logs.ListenableMap().setVarName("familylasttaskorder"));
		lb.add(new xdb.logs.ListenableSet().setVarName("allcandobranch"));
		return lb;
	}

	private class Const implements xbean.RoleTask {
		RoleTask nThis() {
			return RoleTask.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleTask copy() {
			return RoleTask.this.copy();
		}

		@Override
		public xbean.RoleTask toData() {
			return RoleTask.this.toData();
		}

		public xbean.RoleTask toBean() {
			return RoleTask.this.toBean();
		}

		@Override
		public xbean.RoleTask toDataIf() {
			return RoleTask.this.toDataIf();
		}

		public xbean.RoleTask toBeanIf() {
			return RoleTask.this.toBeanIf();
		}

		@Override
		public long getRoleid() { // 
			_xdb_verify_unsafe_();
			return roleid;
		}

		@Override
		public long getMinhistoryexpiretime() { // 
			_xdb_verify_unsafe_();
			return minhistoryexpiretime;
		}

		@Override
		public java.util.Map<Integer, xbean.TaskHistory> getHistorys() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(historys);
		}

		@Override
		public java.util.Map<Integer, xbean.TaskHistory> getHistorysAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.TaskHistory> historys;
			RoleTask _o_ = RoleTask.this;
			historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
			for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : _o_.historys.entrySet())
				historys.put(_e_.getKey(), new TaskHistory.Data(_e_.getValue()));
			return historys;
		}

		@Override
		public java.util.Map<Integer, Integer> getLastcompletetaskid() { // 记录上一个完成任务的id，包括主线和支线任务
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(lastcompletetaskid);
		}

		@Override
		public java.util.Map<Integer, Integer> getLastcompletetaskidAsData() { // 记录上一个完成任务的id，包括主线和支线任务
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> lastcompletetaskid;
			RoleTask _o_ = RoleTask.this;
			lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.lastcompletetaskid.entrySet())
				lastcompletetaskid.put(_e_.getKey(), _e_.getValue());
			return lastcompletetaskid;
		}

		@Override
		public int getAcceptbranchtasks() { // 记录接取的支线任务条数
			_xdb_verify_unsafe_();
			return acceptbranchtasks;
		}

		@Override
		public java.util.Set<Integer> getShownpcs() { // 要显式的npcid集合
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(shownpcs);
		}

		public java.util.Set<Integer> getShownpcsAsData() { // 要显式的npcid集合
			_xdb_verify_unsafe_();
			java.util.Set<Integer> shownpcs;
			RoleTask _o_ = RoleTask.this;
		shownpcs = new xdb.util.SetX<Integer>();
		shownpcs.addAll(_o_.shownpcs);
			return shownpcs;
		}

		@Override
		public java.util.Set<Integer> getHidemines() { // 要隐藏的矿物集合
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(hidemines);
		}

		public java.util.Set<Integer> getHideminesAsData() { // 要隐藏的矿物集合
			_xdb_verify_unsafe_();
			java.util.Set<Integer> hidemines;
			RoleTask _o_ = RoleTask.this;
		hidemines = new xdb.util.SetX<Integer>();
		hidemines.addAll(_o_.hidemines);
			return hidemines;
		}

		@Override
		public java.util.Map<Integer, xbean.TaskData> getTasks() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(tasks);
		}

		@Override
		public java.util.Map<Integer, xbean.TaskData> getTasksAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.TaskData> tasks;
			RoleTask _o_ = RoleTask.this;
			tasks = new java.util.HashMap<Integer, xbean.TaskData>();
			for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : _o_.tasks.entrySet())
				tasks.put(_e_.getKey(), new TaskData.Data(_e_.getValue()));
			return tasks;
		}

		@Override
		public java.util.Map<Integer, Integer> getVariables() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(variables);
		}

		@Override
		public java.util.Map<Integer, Integer> getVariablesAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> variables;
			RoleTask _o_ = RoleTask.this;
			variables = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.variables.entrySet())
				variables.put(_e_.getKey(), _e_.getValue());
			return variables;
		}

		@Override
		public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasks() { // 当前接收的环任务概览
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(acceptedfamilytasks);
		}

		public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasksAsData() { // 当前接收的环任务概览
			_xdb_verify_unsafe_();
			java.util.List<xbean.FamilyTaskDetail> acceptedfamilytasks;
			RoleTask _o_ = RoleTask.this;
		acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
		for (xbean.FamilyTaskDetail _v_ : _o_.acceptedfamilytasks)
			acceptedfamilytasks.add(new FamilyTaskDetail.Data(_v_));
			return acceptedfamilytasks;
		}

		@Override
		public int getCompletednumsinfamtasks() { // 每组环任务中完成的环数
			_xdb_verify_unsafe_();
			return completednumsinfamtasks;
		}

		@Override
		public int getIsuseyuanbao() { // 是否使用了元宝来直接完成环任务，0，否；1，是
			_xdb_verify_unsafe_();
			return isuseyuanbao;
		}

		@Override
		public int getIscancletask() { // 是否取消过任务,0，否；1，是
			_xdb_verify_unsafe_();
			return iscancletask;
		}

		@Override
		public int getDailycompletedfamtasks() { // 每天完成的环任务组数
			_xdb_verify_unsafe_();
			return dailycompletedfamtasks;
		}

		@Override
		public int getWeekcompletedsmallfamtasks() { // 每周完成的环任务组数
			_xdb_verify_unsafe_();
			return weekcompletedsmallfamtasks;
		}

		@Override
		public java.util.List<Integer> getWeekspebonus() { // 已经领取的周计数任务奖励
			_xdb_verify_unsafe_();
			return xdb.Consts.constList(weekspebonus);
		}

		public java.util.List<Integer> getWeekspebonusAsData() { // 已经领取的周计数任务奖励
			_xdb_verify_unsafe_();
			java.util.List<Integer> weekspebonus;
			RoleTask _o_ = RoleTask.this;
		weekspebonus = new java.util.LinkedList<Integer>();
		weekspebonus.addAll(_o_.weekspebonus);
			return weekspebonus;
		}

		@Override
		public long getLastgiveupfamtasktime() { // 上次放弃家族任务的时间
			_xdb_verify_unsafe_();
			return lastgiveupfamtasktime;
		}

		@Override
		public int getGuidebranchtaskid() { // 主界面上当前显示的支线任务
			_xdb_verify_unsafe_();
			return guidebranchtaskid;
		}

		@Override
		public java.util.Map<Integer, Long> getAccepttasktime() { // 接取任务时的时间,记日志用
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(accepttasktime);
		}

		@Override
		public java.util.Map<Integer, Long> getAccepttasktimeAsData() { // 接取任务时的时间,记日志用
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Long> accepttasktime;
			RoleTask _o_ = RoleTask.this;
			accepttasktime = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.accepttasktime.entrySet())
				accepttasktime.put(_e_.getKey(), _e_.getValue());
			return accepttasktime;
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilytaskorder() { // 测试环任务用，按顺序取环任务
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(familytaskorder);
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilytaskorderAsData() { // 测试环任务用，按顺序取环任务
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> familytaskorder;
			RoleTask _o_ = RoleTask.this;
			familytaskorder = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familytaskorder.entrySet())
				familytaskorder.put(_e_.getKey(), _e_.getValue());
			return familytaskorder;
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilylasttaskorder() { // 测试环任务用，取最后一环
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(familylasttaskorder);
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilylasttaskorderAsData() { // 测试环任务用，取最后一环
			_xdb_verify_unsafe_();
			java.util.Map<Integer, Integer> familylasttaskorder;
			RoleTask _o_ = RoleTask.this;
			familylasttaskorder = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familylasttaskorder.entrySet())
				familylasttaskorder.put(_e_.getKey(), _e_.getValue());
			return familylasttaskorder;
		}

		@Override
		public java.util.Set<Integer> getAllcandobranch() { // 所有能做的支线任务的第一个任务id
			_xdb_verify_unsafe_();
			return xdb.Consts.constSet(allcandobranch);
		}

		public java.util.Set<Integer> getAllcandobranchAsData() { // 所有能做的支线任务的第一个任务id
			_xdb_verify_unsafe_();
			java.util.Set<Integer> allcandobranch;
			RoleTask _o_ = RoleTask.this;
		allcandobranch = new xdb.util.SetX<Integer>();
		allcandobranch.addAll(_o_.allcandobranch);
			return allcandobranch;
		}

		@Override
		public void setRoleid(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setMinhistoryexpiretime(long _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setAcceptbranchtasks(int _v_) { // 记录接取的支线任务条数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setCompletednumsinfamtasks(int _v_) { // 每组环任务中完成的环数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIsuseyuanbao(int _v_) { // 是否使用了元宝来直接完成环任务，0，否；1，是
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setIscancletask(int _v_) { // 是否取消过任务,0，否；1，是
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setDailycompletedfamtasks(int _v_) { // 每天完成的环任务组数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setWeekcompletedsmallfamtasks(int _v_) { // 每周完成的环任务组数
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setLastgiveupfamtasktime(long _v_) { // 上次放弃家族任务的时间
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setGuidebranchtaskid(int _v_) { // 主界面上当前显示的支线任务
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
			return RoleTask.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleTask.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleTask.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleTask.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleTask.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleTask.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleTask.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleTask.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleTask.this.toString();
		}

	}

	public static final class Data implements xbean.RoleTask {
		private long roleid; // 
		private long minhistoryexpiretime; // 
		private java.util.HashMap<Integer, xbean.TaskHistory> historys; // 
		private java.util.HashMap<Integer, Integer> lastcompletetaskid; // 记录上一个完成任务的id，包括主线和支线任务
		private int acceptbranchtasks; // 记录接取的支线任务条数
		private java.util.HashSet<Integer> shownpcs; // 要显式的npcid集合
		private java.util.HashSet<Integer> hidemines; // 要隐藏的矿物集合
		private java.util.HashMap<Integer, xbean.TaskData> tasks; // 
		private java.util.HashMap<Integer, Integer> variables; // 
		private java.util.ArrayList<xbean.FamilyTaskDetail> acceptedfamilytasks; // 当前接收的环任务概览
		private int completednumsinfamtasks; // 每组环任务中完成的环数
		private int isuseyuanbao; // 是否使用了元宝来直接完成环任务，0，否；1，是
		private int iscancletask; // 是否取消过任务,0，否；1，是
		private int dailycompletedfamtasks; // 每天完成的环任务组数
		private int weekcompletedsmallfamtasks; // 每周完成的环任务组数
		private java.util.LinkedList<Integer> weekspebonus; // 已经领取的周计数任务奖励
		private long lastgiveupfamtasktime; // 上次放弃家族任务的时间
		private int guidebranchtaskid; // 主界面上当前显示的支线任务
		private java.util.HashMap<Integer, Long> accepttasktime; // 接取任务时的时间,记日志用
		private java.util.HashMap<Integer, Integer> familytaskorder; // 测试环任务用，按顺序取环任务
		private java.util.HashMap<Integer, Integer> familylasttaskorder; // 测试环任务用，取最后一环
		private java.util.HashSet<Integer> allcandobranch; // 所有能做的支线任务的第一个任务id

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
			lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
			shownpcs = new java.util.HashSet<Integer>();
			hidemines = new java.util.HashSet<Integer>();
			tasks = new java.util.HashMap<Integer, xbean.TaskData>();
			variables = new java.util.HashMap<Integer, Integer>();
			acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
			weekspebonus = new java.util.LinkedList<Integer>();
			accepttasktime = new java.util.HashMap<Integer, Long>();
			familytaskorder = new java.util.HashMap<Integer, Integer>();
			familylasttaskorder = new java.util.HashMap<Integer, Integer>();
			allcandobranch = new java.util.HashSet<Integer>();
		}

		Data(xbean.RoleTask _o1_) {
			if (_o1_ instanceof RoleTask) assign((RoleTask)_o1_);
			else if (_o1_ instanceof RoleTask.Data) assign((RoleTask.Data)_o1_);
			else if (_o1_ instanceof RoleTask.Const) assign(((RoleTask.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleTask _o_) {
			roleid = _o_.roleid;
			minhistoryexpiretime = _o_.minhistoryexpiretime;
			historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
			for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : _o_.historys.entrySet())
				historys.put(_e_.getKey(), new TaskHistory.Data(_e_.getValue()));
			lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.lastcompletetaskid.entrySet())
				lastcompletetaskid.put(_e_.getKey(), _e_.getValue());
			acceptbranchtasks = _o_.acceptbranchtasks;
			shownpcs = new java.util.HashSet<Integer>();
			shownpcs.addAll(_o_.shownpcs);
			hidemines = new java.util.HashSet<Integer>();
			hidemines.addAll(_o_.hidemines);
			tasks = new java.util.HashMap<Integer, xbean.TaskData>();
			for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : _o_.tasks.entrySet())
				tasks.put(_e_.getKey(), new TaskData.Data(_e_.getValue()));
			variables = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.variables.entrySet())
				variables.put(_e_.getKey(), _e_.getValue());
			acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
			for (xbean.FamilyTaskDetail _v_ : _o_.acceptedfamilytasks)
				acceptedfamilytasks.add(new FamilyTaskDetail.Data(_v_));
			completednumsinfamtasks = _o_.completednumsinfamtasks;
			isuseyuanbao = _o_.isuseyuanbao;
			iscancletask = _o_.iscancletask;
			dailycompletedfamtasks = _o_.dailycompletedfamtasks;
			weekcompletedsmallfamtasks = _o_.weekcompletedsmallfamtasks;
			weekspebonus = new java.util.LinkedList<Integer>();
			weekspebonus.addAll(_o_.weekspebonus);
			lastgiveupfamtasktime = _o_.lastgiveupfamtasktime;
			guidebranchtaskid = _o_.guidebranchtaskid;
			accepttasktime = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.accepttasktime.entrySet())
				accepttasktime.put(_e_.getKey(), _e_.getValue());
			familytaskorder = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familytaskorder.entrySet())
				familytaskorder.put(_e_.getKey(), _e_.getValue());
			familylasttaskorder = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familylasttaskorder.entrySet())
				familylasttaskorder.put(_e_.getKey(), _e_.getValue());
			allcandobranch = new java.util.HashSet<Integer>();
			allcandobranch.addAll(_o_.allcandobranch);
		}

		private void assign(RoleTask.Data _o_) {
			roleid = _o_.roleid;
			minhistoryexpiretime = _o_.minhistoryexpiretime;
			historys = new java.util.HashMap<Integer, xbean.TaskHistory>();
			for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : _o_.historys.entrySet())
				historys.put(_e_.getKey(), new TaskHistory.Data(_e_.getValue()));
			lastcompletetaskid = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.lastcompletetaskid.entrySet())
				lastcompletetaskid.put(_e_.getKey(), _e_.getValue());
			acceptbranchtasks = _o_.acceptbranchtasks;
			shownpcs = new java.util.HashSet<Integer>();
			shownpcs.addAll(_o_.shownpcs);
			hidemines = new java.util.HashSet<Integer>();
			hidemines.addAll(_o_.hidemines);
			tasks = new java.util.HashMap<Integer, xbean.TaskData>();
			for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : _o_.tasks.entrySet())
				tasks.put(_e_.getKey(), new TaskData.Data(_e_.getValue()));
			variables = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.variables.entrySet())
				variables.put(_e_.getKey(), _e_.getValue());
			acceptedfamilytasks = new java.util.ArrayList<xbean.FamilyTaskDetail>();
			for (xbean.FamilyTaskDetail _v_ : _o_.acceptedfamilytasks)
				acceptedfamilytasks.add(new FamilyTaskDetail.Data(_v_));
			completednumsinfamtasks = _o_.completednumsinfamtasks;
			isuseyuanbao = _o_.isuseyuanbao;
			iscancletask = _o_.iscancletask;
			dailycompletedfamtasks = _o_.dailycompletedfamtasks;
			weekcompletedsmallfamtasks = _o_.weekcompletedsmallfamtasks;
			weekspebonus = new java.util.LinkedList<Integer>();
			weekspebonus.addAll(_o_.weekspebonus);
			lastgiveupfamtasktime = _o_.lastgiveupfamtasktime;
			guidebranchtaskid = _o_.guidebranchtaskid;
			accepttasktime = new java.util.HashMap<Integer, Long>();
			for (java.util.Map.Entry<Integer, Long> _e_ : _o_.accepttasktime.entrySet())
				accepttasktime.put(_e_.getKey(), _e_.getValue());
			familytaskorder = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familytaskorder.entrySet())
				familytaskorder.put(_e_.getKey(), _e_.getValue());
			familylasttaskorder = new java.util.HashMap<Integer, Integer>();
			for (java.util.Map.Entry<Integer, Integer> _e_ : _o_.familylasttaskorder.entrySet())
				familylasttaskorder.put(_e_.getKey(), _e_.getValue());
			allcandobranch = new java.util.HashSet<Integer>();
			allcandobranch.addAll(_o_.allcandobranch);
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)22);
	_os_.marshal((short)(10240|  6));_os_.marshal(roleid);
	_os_.marshal((short)(10240|  1));_os_.marshal(minhistoryexpiretime);
	_os_.marshal((short)(24576|  2));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(historys.size());
for (java.util.Map.Entry<Integer, xbean.TaskHistory> _e_ : historys.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 15));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(lastcompletetaskid.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : lastcompletetaskid.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192| 16));_os_.marshal(acceptbranchtasks);
	_os_.marshal((short)(20480| 17));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(shownpcs.size());
for (Integer _v_ : shownpcs) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(20480| 20));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(hidemines.size());
for (Integer _v_ : hidemines) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  3));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(tasks.size());
for (java.util.Map.Entry<Integer, xbean.TaskData> _e_ : tasks.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576|  7));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(variables.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : variables.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(22528|  8));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(acceptedfamilytasks.size());
for (xbean.FamilyTaskDetail _v_ : acceptedfamilytasks) {
	_v_.marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)( 8192|  9));_os_.marshal(completednumsinfamtasks);
	_os_.marshal((short)( 8192| 13));_os_.marshal(isuseyuanbao);
	_os_.marshal((short)( 8192| 18));_os_.marshal(iscancletask);
	_os_.marshal((short)( 8192| 10));_os_.marshal(dailycompletedfamtasks);
	_os_.marshal((short)( 8192| 11));_os_.marshal(weekcompletedsmallfamtasks);
	_os_.marshal((short)(22528| 14));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(weekspebonus.size());
for (Integer _v_ : weekspebonus) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(10240| 12));_os_.marshal(lastgiveupfamtasktime);
	_os_.marshal((short)( 8192| 19));_os_.marshal(guidebranchtaskid);
	_os_.marshal((short)(24576| 23));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(accepttasktime.size());
for (java.util.Map.Entry<Integer, Long> _e_ : accepttasktime.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 21));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(familytaskorder.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : familytaskorder.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(24576| 22));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(familylasttaskorder.size());
for (java.util.Map.Entry<Integer, Integer> _e_ : familylasttaskorder.entrySet())
{
	_os_.marshal(_e_.getKey());
	_os_.marshal(_e_.getValue());
}
_temp_.marshal(_os_); _os_ = _temp_;}
	_os_.marshal((short)(20480| 24));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(allcandobranch.size());
for (Integer _v_ : allcandobranch) {
	_os_.marshal(_v_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (10240|  6):roleid = _os_.unmarshal_long();
					break;
					case ( 6144|  6):roleid = _os_.unmarshal_short();
					break;
					case ( 8192|  6):roleid = _os_.unmarshal_int();
					break;
					case (10240|  1):minhistoryexpiretime = _os_.unmarshal_long();
					break;
					case ( 6144|  1):minhistoryexpiretime = _os_.unmarshal_short();
					break;
					case ( 8192|  1):minhistoryexpiretime = _os_.unmarshal_int();
					break;
					case (24576|  2):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		historys = new java.util.HashMap<Integer, xbean.TaskHistory>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.TaskHistory _v_ = xbean.Pod.newTaskHistoryData();
		_v_.unmarshal(_os_);
		historys.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 15):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		lastcompletetaskid = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		lastcompletetaskid.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case ( 8192| 16):acceptbranchtasks = _os_.unmarshal_int();
					break;
					case ( 6144| 16):acceptbranchtasks = _os_.unmarshal_short();
					break;
					case (20480| 17):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	shownpcs.add(_v_);
}
_os_ = _temp_;}
					break;
					case (20480| 20):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	hidemines.add(_v_);
}
_os_ = _temp_;}
					break;
					case (24576|  3):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		tasks = new java.util.HashMap<Integer, xbean.TaskData>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.TaskData _v_ = xbean.Pod.newTaskDataData();
		_v_.unmarshal(_os_);
		tasks.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576|  7):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		variables = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		variables.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (22528|  8):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	xbean.FamilyTaskDetail _v_ = xbean.Pod.newFamilyTaskDetailData();
	_v_.unmarshal(_os_);
	acceptedfamilytasks.add(_v_);
}
_os_ = _temp_;}
					break;
					case ( 8192|  9):completednumsinfamtasks = _os_.unmarshal_int();
					break;
					case ( 6144|  9):completednumsinfamtasks = _os_.unmarshal_short();
					break;
					case ( 8192| 13):isuseyuanbao = _os_.unmarshal_int();
					break;
					case ( 6144| 13):isuseyuanbao = _os_.unmarshal_short();
					break;
					case ( 8192| 18):iscancletask = _os_.unmarshal_int();
					break;
					case ( 6144| 18):iscancletask = _os_.unmarshal_short();
					break;
					case ( 8192| 10):dailycompletedfamtasks = _os_.unmarshal_int();
					break;
					case ( 6144| 10):dailycompletedfamtasks = _os_.unmarshal_short();
					break;
					case ( 8192| 11):weekcompletedsmallfamtasks = _os_.unmarshal_int();
					break;
					case ( 6144| 11):weekcompletedsmallfamtasks = _os_.unmarshal_short();
					break;
					case (22528| 14):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	weekspebonus.add(_v_);
}
_os_ = _temp_;}
					break;
					case (10240| 12):lastgiveupfamtasktime = _os_.unmarshal_long();
					break;
					case ( 6144| 12):lastgiveupfamtasktime = _os_.unmarshal_short();
					break;
					case ( 8192| 12):lastgiveupfamtasktime = _os_.unmarshal_int();
					break;
					case ( 8192| 19):guidebranchtaskid = _os_.unmarshal_int();
					break;
					case ( 6144| 19):guidebranchtaskid = _os_.unmarshal_short();
					break;
					case (24576| 23):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		accepttasktime = new java.util.HashMap<Integer, Long>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		long _v_ = 0;
		_v_ = _os_.unmarshal_long();
		accepttasktime.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 21):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		familytaskorder = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		familytaskorder.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (24576| 22):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		familylasttaskorder = new java.util.HashMap<Integer, Integer>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		int _v_ = 0;
		_v_ = _os_.unmarshal_int();
		familylasttaskorder.put(_k_, _v_);
	}
}
_os_ = _temp_;}
					break;
					case (20480| 24):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
for (int size = _os_.uncompact_uint32(); size > 0; --size) {
	int _v_ = 0;
	_v_ = _os_.unmarshal_int();
	allcandobranch.add(_v_);
}
_os_ = _temp_;}
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.RoleTask copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleTask toData() {
			return new Data(this);
		}

		public xbean.RoleTask toBean() {
			return new RoleTask(this, null, null);
		}

		@Override
		public xbean.RoleTask toDataIf() {
			return this;
		}

		public xbean.RoleTask toBeanIf() {
			return new RoleTask(this, null, null);
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
		public long getMinhistoryexpiretime() { // 
			return minhistoryexpiretime;
		}

		@Override
		public java.util.Map<Integer, xbean.TaskHistory> getHistorys() { // 
			return historys;
		}

		@Override
		public java.util.Map<Integer, xbean.TaskHistory> getHistorysAsData() { // 
			return historys;
		}

		@Override
		public java.util.Map<Integer, Integer> getLastcompletetaskid() { // 记录上一个完成任务的id，包括主线和支线任务
			return lastcompletetaskid;
		}

		@Override
		public java.util.Map<Integer, Integer> getLastcompletetaskidAsData() { // 记录上一个完成任务的id，包括主线和支线任务
			return lastcompletetaskid;
		}

		@Override
		public int getAcceptbranchtasks() { // 记录接取的支线任务条数
			return acceptbranchtasks;
		}

		@Override
		public java.util.Set<Integer> getShownpcs() { // 要显式的npcid集合
			return shownpcs;
		}

		@Override
		public java.util.Set<Integer> getShownpcsAsData() { // 要显式的npcid集合
			return shownpcs;
		}

		@Override
		public java.util.Set<Integer> getHidemines() { // 要隐藏的矿物集合
			return hidemines;
		}

		@Override
		public java.util.Set<Integer> getHideminesAsData() { // 要隐藏的矿物集合
			return hidemines;
		}

		@Override
		public java.util.Map<Integer, xbean.TaskData> getTasks() { // 
			return tasks;
		}

		@Override
		public java.util.Map<Integer, xbean.TaskData> getTasksAsData() { // 
			return tasks;
		}

		@Override
		public java.util.Map<Integer, Integer> getVariables() { // 
			return variables;
		}

		@Override
		public java.util.Map<Integer, Integer> getVariablesAsData() { // 
			return variables;
		}

		@Override
		public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasks() { // 当前接收的环任务概览
			return acceptedfamilytasks;
		}

		@Override
		public java.util.List<xbean.FamilyTaskDetail> getAcceptedfamilytasksAsData() { // 当前接收的环任务概览
			return acceptedfamilytasks;
		}

		@Override
		public int getCompletednumsinfamtasks() { // 每组环任务中完成的环数
			return completednumsinfamtasks;
		}

		@Override
		public int getIsuseyuanbao() { // 是否使用了元宝来直接完成环任务，0，否；1，是
			return isuseyuanbao;
		}

		@Override
		public int getIscancletask() { // 是否取消过任务,0，否；1，是
			return iscancletask;
		}

		@Override
		public int getDailycompletedfamtasks() { // 每天完成的环任务组数
			return dailycompletedfamtasks;
		}

		@Override
		public int getWeekcompletedsmallfamtasks() { // 每周完成的环任务组数
			return weekcompletedsmallfamtasks;
		}

		@Override
		public java.util.List<Integer> getWeekspebonus() { // 已经领取的周计数任务奖励
			return weekspebonus;
		}

		@Override
		public java.util.List<Integer> getWeekspebonusAsData() { // 已经领取的周计数任务奖励
			return weekspebonus;
		}

		@Override
		public long getLastgiveupfamtasktime() { // 上次放弃家族任务的时间
			return lastgiveupfamtasktime;
		}

		@Override
		public int getGuidebranchtaskid() { // 主界面上当前显示的支线任务
			return guidebranchtaskid;
		}

		@Override
		public java.util.Map<Integer, Long> getAccepttasktime() { // 接取任务时的时间,记日志用
			return accepttasktime;
		}

		@Override
		public java.util.Map<Integer, Long> getAccepttasktimeAsData() { // 接取任务时的时间,记日志用
			return accepttasktime;
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilytaskorder() { // 测试环任务用，按顺序取环任务
			return familytaskorder;
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilytaskorderAsData() { // 测试环任务用，按顺序取环任务
			return familytaskorder;
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilylasttaskorder() { // 测试环任务用，取最后一环
			return familylasttaskorder;
		}

		@Override
		public java.util.Map<Integer, Integer> getFamilylasttaskorderAsData() { // 测试环任务用，取最后一环
			return familylasttaskorder;
		}

		@Override
		public java.util.Set<Integer> getAllcandobranch() { // 所有能做的支线任务的第一个任务id
			return allcandobranch;
		}

		@Override
		public java.util.Set<Integer> getAllcandobranchAsData() { // 所有能做的支线任务的第一个任务id
			return allcandobranch;
		}

		@Override
		public void setRoleid(long _v_) { // 
			roleid = _v_;
		}

		@Override
		public void setMinhistoryexpiretime(long _v_) { // 
			minhistoryexpiretime = _v_;
		}

		@Override
		public void setAcceptbranchtasks(int _v_) { // 记录接取的支线任务条数
			acceptbranchtasks = _v_;
		}

		@Override
		public void setCompletednumsinfamtasks(int _v_) { // 每组环任务中完成的环数
			completednumsinfamtasks = _v_;
		}

		@Override
		public void setIsuseyuanbao(int _v_) { // 是否使用了元宝来直接完成环任务，0，否；1，是
			isuseyuanbao = _v_;
		}

		@Override
		public void setIscancletask(int _v_) { // 是否取消过任务,0，否；1，是
			iscancletask = _v_;
		}

		@Override
		public void setDailycompletedfamtasks(int _v_) { // 每天完成的环任务组数
			dailycompletedfamtasks = _v_;
		}

		@Override
		public void setWeekcompletedsmallfamtasks(int _v_) { // 每周完成的环任务组数
			weekcompletedsmallfamtasks = _v_;
		}

		@Override
		public void setLastgiveupfamtasktime(long _v_) { // 上次放弃家族任务的时间
			lastgiveupfamtasktime = _v_;
		}

		@Override
		public void setGuidebranchtaskid(int _v_) { // 主界面上当前显示的支线任务
			guidebranchtaskid = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleTask.Data)) return false;
			RoleTask.Data _o_ = (RoleTask.Data) _o1_;
			if (roleid != _o_.roleid) return false;
			if (minhistoryexpiretime != _o_.minhistoryexpiretime) return false;
			if (!historys.equals(_o_.historys)) return false;
			if (!lastcompletetaskid.equals(_o_.lastcompletetaskid)) return false;
			if (acceptbranchtasks != _o_.acceptbranchtasks) return false;
			if (!shownpcs.equals(_o_.shownpcs)) return false;
			if (!hidemines.equals(_o_.hidemines)) return false;
			if (!tasks.equals(_o_.tasks)) return false;
			if (!variables.equals(_o_.variables)) return false;
			if (!acceptedfamilytasks.equals(_o_.acceptedfamilytasks)) return false;
			if (completednumsinfamtasks != _o_.completednumsinfamtasks) return false;
			if (isuseyuanbao != _o_.isuseyuanbao) return false;
			if (iscancletask != _o_.iscancletask) return false;
			if (dailycompletedfamtasks != _o_.dailycompletedfamtasks) return false;
			if (weekcompletedsmallfamtasks != _o_.weekcompletedsmallfamtasks) return false;
			if (!weekspebonus.equals(_o_.weekspebonus)) return false;
			if (lastgiveupfamtasktime != _o_.lastgiveupfamtasktime) return false;
			if (guidebranchtaskid != _o_.guidebranchtaskid) return false;
			if (!accepttasktime.equals(_o_.accepttasktime)) return false;
			if (!familytaskorder.equals(_o_.familytaskorder)) return false;
			if (!familylasttaskorder.equals(_o_.familylasttaskorder)) return false;
			if (!allcandobranch.equals(_o_.allcandobranch)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += roleid;
			_h_ += minhistoryexpiretime;
			_h_ += historys.hashCode();
			_h_ += lastcompletetaskid.hashCode();
			_h_ += acceptbranchtasks;
			_h_ += shownpcs.hashCode();
			_h_ += hidemines.hashCode();
			_h_ += tasks.hashCode();
			_h_ += variables.hashCode();
			_h_ += acceptedfamilytasks.hashCode();
			_h_ += completednumsinfamtasks;
			_h_ += isuseyuanbao;
			_h_ += iscancletask;
			_h_ += dailycompletedfamtasks;
			_h_ += weekcompletedsmallfamtasks;
			_h_ += weekspebonus.hashCode();
			_h_ += lastgiveupfamtasktime;
			_h_ += guidebranchtaskid;
			_h_ += accepttasktime.hashCode();
			_h_ += familytaskorder.hashCode();
			_h_ += familylasttaskorder.hashCode();
			_h_ += allcandobranch.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(roleid);
			_sb_.append(",");
			_sb_.append(minhistoryexpiretime);
			_sb_.append(",");
			_sb_.append(historys);
			_sb_.append(",");
			_sb_.append(lastcompletetaskid);
			_sb_.append(",");
			_sb_.append(acceptbranchtasks);
			_sb_.append(",");
			_sb_.append(shownpcs);
			_sb_.append(",");
			_sb_.append(hidemines);
			_sb_.append(",");
			_sb_.append(tasks);
			_sb_.append(",");
			_sb_.append(variables);
			_sb_.append(",");
			_sb_.append(acceptedfamilytasks);
			_sb_.append(",");
			_sb_.append(completednumsinfamtasks);
			_sb_.append(",");
			_sb_.append(isuseyuanbao);
			_sb_.append(",");
			_sb_.append(iscancletask);
			_sb_.append(",");
			_sb_.append(dailycompletedfamtasks);
			_sb_.append(",");
			_sb_.append(weekcompletedsmallfamtasks);
			_sb_.append(",");
			_sb_.append(weekspebonus);
			_sb_.append(",");
			_sb_.append(lastgiveupfamtasktime);
			_sb_.append(",");
			_sb_.append(guidebranchtaskid);
			_sb_.append(",");
			_sb_.append(accepttasktime);
			_sb_.append(",");
			_sb_.append(familytaskorder);
			_sb_.append(",");
			_sb_.append(familylasttaskorder);
			_sb_.append(",");
			_sb_.append(allcandobranch);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
