
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class RoleStoryNote extends xdb.XBean implements xbean.RoleStoryNote {
	private java.util.HashMap<Integer, xbean.StoryNoteChapter> chapters; // 

	@Override
	public void _reset_unsafe_() {
		chapters.clear();
	}

	RoleStoryNote(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
	}

	public RoleStoryNote() {
		this(0, null, null);
	}

	public RoleStoryNote(RoleStoryNote _o_) {
		this(_o_, null, null);
	}

	RoleStoryNote(xbean.RoleStoryNote _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof RoleStoryNote) assign((RoleStoryNote)_o1_);
		else if (_o1_ instanceof RoleStoryNote.Data) assign((RoleStoryNote.Data)_o1_);
		else if (_o1_ instanceof RoleStoryNote.Const) assign(((RoleStoryNote.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(RoleStoryNote _o_) {
		_o_._xdb_verify_unsafe_();
		chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
		for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : _o_.chapters.entrySet())
			chapters.put(_e_.getKey(), new StoryNoteChapter(_e_.getValue(), this, "chapters"));
	}

	private void assign(RoleStoryNote.Data _o_) {
		chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
		for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : _o_.chapters.entrySet())
			chapters.put(_e_.getKey(), new StoryNoteChapter(_e_.getValue(), this, "chapters"));
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)1);
    _os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(chapters.size());
for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : chapters.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
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
    				case (24576|  1):{final OctetsStream _temp_ = _os_; _os_ = OctetsStream.wrap(_temp_.unmarshal_Octets());
{
	int size = _os_.uncompact_uint32();
	if (size >= 12) { // {java.util.HashMap} 16 * 0.75 = 12
		chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.StoryNoteChapter _v_ = new StoryNoteChapter(0, this, "chapters");
		_v_.unmarshal(_os_);
		chapters.put(_k_, _v_);
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
	public xbean.RoleStoryNote copy() {
		_xdb_verify_unsafe_();
		return new RoleStoryNote(this);
	}

	@Override
	public xbean.RoleStoryNote toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleStoryNote toBean() {
		_xdb_verify_unsafe_();
		return new RoleStoryNote(this); // same as copy()
	}

	@Override
	public xbean.RoleStoryNote toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.RoleStoryNote toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public java.util.Map<Integer, xbean.StoryNoteChapter> getChapters() { // 
		_xdb_verify_unsafe_();
		return xdb.Logs.logMap(new xdb.LogKey(this, "chapters"), chapters);
	}

	@Override
	public java.util.Map<Integer, xbean.StoryNoteChapter> getChaptersAsData() { // 
		_xdb_verify_unsafe_();
		java.util.Map<Integer, xbean.StoryNoteChapter> chapters;
		RoleStoryNote _o_ = this;
		chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
		for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : _o_.chapters.entrySet())
			chapters.put(_e_.getKey(), new StoryNoteChapter.Data(_e_.getValue()));
		return chapters;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		RoleStoryNote _o_ = null;
		if ( _o1_ instanceof RoleStoryNote ) _o_ = (RoleStoryNote)_o1_;
		else if ( _o1_ instanceof RoleStoryNote.Const ) _o_ = ((RoleStoryNote.Const)_o1_).nThis();
		else return false;
		if (!chapters.equals(_o_.chapters)) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += chapters.hashCode();
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(chapters);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableMap().setVarName("chapters"));
		return lb;
	}

	private class Const implements xbean.RoleStoryNote {
		RoleStoryNote nThis() {
			return RoleStoryNote.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.RoleStoryNote copy() {
			return RoleStoryNote.this.copy();
		}

		@Override
		public xbean.RoleStoryNote toData() {
			return RoleStoryNote.this.toData();
		}

		public xbean.RoleStoryNote toBean() {
			return RoleStoryNote.this.toBean();
		}

		@Override
		public xbean.RoleStoryNote toDataIf() {
			return RoleStoryNote.this.toDataIf();
		}

		public xbean.RoleStoryNote toBeanIf() {
			return RoleStoryNote.this.toBeanIf();
		}

		@Override
		public java.util.Map<Integer, xbean.StoryNoteChapter> getChapters() { // 
			_xdb_verify_unsafe_();
			return xdb.Consts.constMap(chapters);
		}

		@Override
		public java.util.Map<Integer, xbean.StoryNoteChapter> getChaptersAsData() { // 
			_xdb_verify_unsafe_();
			java.util.Map<Integer, xbean.StoryNoteChapter> chapters;
			RoleStoryNote _o_ = RoleStoryNote.this;
			chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
			for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : _o_.chapters.entrySet())
				chapters.put(_e_.getKey(), new StoryNoteChapter.Data(_e_.getValue()));
			return chapters;
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
			return RoleStoryNote.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return RoleStoryNote.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return RoleStoryNote.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return RoleStoryNote.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return RoleStoryNote.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return RoleStoryNote.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return RoleStoryNote.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return RoleStoryNote.this.hashCode();
		}

		@Override
		public String toString() {
			return RoleStoryNote.this.toString();
		}

	}

	public static final class Data implements xbean.RoleStoryNote {
		private java.util.HashMap<Integer, xbean.StoryNoteChapter> chapters; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
			chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
		}

		Data(xbean.RoleStoryNote _o1_) {
			if (_o1_ instanceof RoleStoryNote) assign((RoleStoryNote)_o1_);
			else if (_o1_ instanceof RoleStoryNote.Data) assign((RoleStoryNote.Data)_o1_);
			else if (_o1_ instanceof RoleStoryNote.Const) assign(((RoleStoryNote.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(RoleStoryNote _o_) {
			chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
			for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : _o_.chapters.entrySet())
				chapters.put(_e_.getKey(), new StoryNoteChapter.Data(_e_.getValue()));
		}

		private void assign(RoleStoryNote.Data _o_) {
			chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>();
			for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : _o_.chapters.entrySet())
				chapters.put(_e_.getKey(), new StoryNoteChapter.Data(_e_.getValue()));
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)1);
	_os_.marshal((short)(24576|  1));{final OctetsStream _temp_ = _os_; _os_ = new OctetsStream();
_os_.compact_uint32(chapters.size());
for (java.util.Map.Entry<Integer, xbean.StoryNoteChapter> _e_ : chapters.entrySet())
{
	_os_.marshal(_e_.getKey());
	_e_.getValue().marshal(_os_);
}
_temp_.marshal(_os_); _os_ = _temp_;}
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
		chapters = new java.util.HashMap<Integer, xbean.StoryNoteChapter>(size * 2);
	}
	for (; size > 0; --size)
	{
		int _k_ = 0;
		_k_ = _os_.unmarshal_int();
		xbean.StoryNoteChapter _v_ = xbean.Pod.newStoryNoteChapterData();
		_v_.unmarshal(_os_);
		chapters.put(_k_, _v_);
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
		public xbean.RoleStoryNote copy() {
			return new Data(this);
		}

		@Override
		public xbean.RoleStoryNote toData() {
			return new Data(this);
		}

		public xbean.RoleStoryNote toBean() {
			return new RoleStoryNote(this, null, null);
		}

		@Override
		public xbean.RoleStoryNote toDataIf() {
			return this;
		}

		public xbean.RoleStoryNote toBeanIf() {
			return new RoleStoryNote(this, null, null);
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
		public java.util.Map<Integer, xbean.StoryNoteChapter> getChapters() { // 
			return chapters;
		}

		@Override
		public java.util.Map<Integer, xbean.StoryNoteChapter> getChaptersAsData() { // 
			return chapters;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof RoleStoryNote.Data)) return false;
			RoleStoryNote.Data _o_ = (RoleStoryNote.Data) _o1_;
			if (!chapters.equals(_o_.chapters)) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += chapters.hashCode();
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(chapters);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
