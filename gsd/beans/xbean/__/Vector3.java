
package xbean.__;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public final class Vector3 extends xdb.XBean implements xbean.Vector3 {
	private float x; // 
	private float y; // 
	private float z; // 

	@Override
	public void _reset_unsafe_() {
		x = 0.0f;
		y = 0.0f;
		z = 0.0f;
	}

	Vector3(int __, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
	}

	public Vector3() {
		this(0, null, null);
	}

	public Vector3(Vector3 _o_) {
		this(_o_, null, null);
	}

	Vector3(xbean.Vector3 _o1_, xdb.XBean _xp_, String _vn_) {
		super(_xp_, _vn_);
		if (_o1_ instanceof Vector3) assign((Vector3)_o1_);
		else if (_o1_ instanceof Vector3.Data) assign((Vector3.Data)_o1_);
		else if (_o1_ instanceof Vector3.Const) assign(((Vector3.Const)_o1_).nThis());
		else throw new UnsupportedOperationException();
	}

	private void assign(Vector3 _o_) {
		_o_._xdb_verify_unsafe_();
		x = _o_.x;
		y = _o_.y;
		z = _o_.z;
	}

	private void assign(Vector3.Data _o_) {
		x = _o_.x;
		y = _o_.y;
		z = _o_.z;
	}

    	@Override
    	public final OctetsStream marshal(OctetsStream _os_) {
    		_xdb_verify_unsafe_();
    		_os_.marshal((short)3);
    _os_.marshal((short)(12288|  1));_os_.marshal(x);
    _os_.marshal((short)(12288|  2));_os_.marshal(y);
    _os_.marshal((short)(12288|  3));_os_.marshal(z);
    		return _os_;
    	}

    	@Override
    	public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
    		_xdb_verify_unsafe_();
    		for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
    			final short _id_ = _os_.unmarshal_short();
    			switch(_id_) {
    				case (12288|  1):x = _os_.unmarshal_float();
    				break;
    				case (12288|  2):y = _os_.unmarshal_float();
    				break;
    				case (12288|  3):z = _os_.unmarshal_float();
    				break;
    default:skipUnknownField(_id_, _os_);
    			}
    		}
    		return _os_;
    	}

	@Override
	public xbean.Vector3 copy() {
		_xdb_verify_unsafe_();
		return new Vector3(this);
	}

	@Override
	public xbean.Vector3 toData() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Vector3 toBean() {
		_xdb_verify_unsafe_();
		return new Vector3(this); // same as copy()
	}

	@Override
	public xbean.Vector3 toDataIf() {
		_xdb_verify_unsafe_();
		return new Data(this);
	}

	public xbean.Vector3 toBeanIf() {
		_xdb_verify_unsafe_();
		return this;
	}

	@Override
	public xdb.Bean toConst() {
		_xdb_verify_unsafe_();
		return new Const();
	}

	@Override
	public float getX() { // 
		_xdb_verify_unsafe_();
		return x;
	}

	@Override
	public float getY() { // 
		_xdb_verify_unsafe_();
		return y;
	}

	@Override
	public float getZ() { // 
		_xdb_verify_unsafe_();
		return z;
	}

	@Override
	public void setX(float _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "x") {
			protected xdb.Log create() {
				return new xdb.logs.LogFloat(this, x) {
					public void rollback() { x = _xdb_saved; }
				};}});
		x = _v_;
	}

	@Override
	public void setY(float _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "y") {
			protected xdb.Log create() {
				return new xdb.logs.LogFloat(this, y) {
					public void rollback() { y = _xdb_saved; }
				};}});
		y = _v_;
	}

	@Override
	public void setZ(float _v_) { // 
		_xdb_verify_unsafe_();
		xdb.Logs.logIf(new xdb.LogKey(this, "z") {
			protected xdb.Log create() {
				return new xdb.logs.LogFloat(this, z) {
					public void rollback() { z = _xdb_saved; }
				};}});
		z = _v_;
	}

	@Override
	public final boolean equals(Object _o1_) {
		_xdb_verify_unsafe_();
		Vector3 _o_ = null;
		if ( _o1_ instanceof Vector3 ) _o_ = (Vector3)_o1_;
		else if ( _o1_ instanceof Vector3.Const ) _o_ = ((Vector3.Const)_o1_).nThis();
		else return false;
		if (x != _o_.x) return false;
		if (y != _o_.y) return false;
		if (z != _o_.z) return false;
		return true;
	}

	@Override
	public final int hashCode() {
		_xdb_verify_unsafe_();
		int _h_ = 0;
		_h_ += x;
		_h_ += y;
		_h_ += z;
		return _h_;
	}

	@Override
	public String toString() {
		_xdb_verify_unsafe_();
		StringBuilder _sb_ = new StringBuilder();
		_sb_.append("(");
		_sb_.append(x);
		_sb_.append(",");
		_sb_.append(y);
		_sb_.append(",");
		_sb_.append(z);
		_sb_.append(")");
		return _sb_.toString();
	}

	@Override
	public xdb.logs.Listenable newListenable() {
		xdb.logs.ListenableBean lb = new xdb.logs.ListenableBean();
		lb.add(new xdb.logs.ListenableChanged().setVarName("x"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("y"));
		lb.add(new xdb.logs.ListenableChanged().setVarName("z"));
		return lb;
	}

	private class Const implements xbean.Vector3 {
		Vector3 nThis() {
			return Vector3.this;
		}

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		@Override
		public xbean.Vector3 copy() {
			return Vector3.this.copy();
		}

		@Override
		public xbean.Vector3 toData() {
			return Vector3.this.toData();
		}

		public xbean.Vector3 toBean() {
			return Vector3.this.toBean();
		}

		@Override
		public xbean.Vector3 toDataIf() {
			return Vector3.this.toDataIf();
		}

		public xbean.Vector3 toBeanIf() {
			return Vector3.this.toBeanIf();
		}

		@Override
		public float getX() { // 
			_xdb_verify_unsafe_();
			return x;
		}

		@Override
		public float getY() { // 
			_xdb_verify_unsafe_();
			return y;
		}

		@Override
		public float getZ() { // 
			_xdb_verify_unsafe_();
			return z;
		}

		@Override
		public void setX(float _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setY(float _v_) { // 
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public void setZ(float _v_) { // 
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
			return Vector3.this.isData();
		}

		@Override
		public OctetsStream marshal(OctetsStream _os_) {
			return Vector3.this.marshal(_os_);
		}

		@Override
		public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
			_xdb_verify_unsafe_();
			throw new UnsupportedOperationException();
		}

		@Override
		public xdb.Bean xdbParent() {
			return Vector3.this.xdbParent();
		}

		@Override
		public boolean xdbManaged() {
			return Vector3.this.xdbManaged();
		}

		@Override
		public String xdbVarname() {
			return Vector3.this.xdbVarname();
		}

		@Override
		public Long xdbObjId() {
			return Vector3.this.xdbObjId();
		}

		@Override
		public boolean equals(Object obj) {
			return Vector3.this.equals(obj);
		}

		@Override
		public int hashCode() {
			return Vector3.this.hashCode();
		}

		@Override
		public String toString() {
			return Vector3.this.toString();
		}

	}

	public static final class Data implements xbean.Vector3 {
		private float x; // 
		private float y; // 
		private float z; // 

		@Override
		public void _reset_unsafe_() {
			throw new UnsupportedOperationException();
		}

		public Data() {
		}

		Data(xbean.Vector3 _o1_) {
			if (_o1_ instanceof Vector3) assign((Vector3)_o1_);
			else if (_o1_ instanceof Vector3.Data) assign((Vector3.Data)_o1_);
			else if (_o1_ instanceof Vector3.Const) assign(((Vector3.Const)_o1_).nThis());
			else throw new UnsupportedOperationException();
		}

		private void assign(Vector3 _o_) {
			x = _o_.x;
			y = _o_.y;
			z = _o_.z;
		}

		private void assign(Vector3.Data _o_) {
			x = _o_.x;
			y = _o_.y;
			z = _o_.z;
		}

		@Override
		public final OctetsStream marshal(OctetsStream _os_) {
			_os_.marshal((short)3);
	_os_.marshal((short)(12288|  1));_os_.marshal(x);
	_os_.marshal((short)(12288|  2));_os_.marshal(y);
	_os_.marshal((short)(12288|  3));_os_.marshal(z);
			return _os_;
		}

		@Override
		public final OctetsStream unmarshal(OctetsStream _os_) throws MarshalException {
			for(int  _var_num_ = _os_.unmarshal_short() ; _var_num_-- > 0 ; ) {
				final short _id_ = _os_.unmarshal_short();
				switch(_id_) {
					case (12288|  1):x = _os_.unmarshal_float();
					break;
					case (12288|  2):y = _os_.unmarshal_float();
					break;
					case (12288|  3):z = _os_.unmarshal_float();
					break;
	default:skipUnknownField(_id_, _os_);
				}
			}
			return _os_;
		}

		@Override
		public xbean.Vector3 copy() {
			return new Data(this);
		}

		@Override
		public xbean.Vector3 toData() {
			return new Data(this);
		}

		public xbean.Vector3 toBean() {
			return new Vector3(this, null, null);
		}

		@Override
		public xbean.Vector3 toDataIf() {
			return this;
		}

		public xbean.Vector3 toBeanIf() {
			return new Vector3(this, null, null);
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
		public float getX() { // 
			return x;
		}

		@Override
		public float getY() { // 
			return y;
		}

		@Override
		public float getZ() { // 
			return z;
		}

		@Override
		public void setX(float _v_) { // 
			x = _v_;
		}

		@Override
		public void setY(float _v_) { // 
			y = _v_;
		}

		@Override
		public void setZ(float _v_) { // 
			z = _v_;
		}

		@Override
		public final boolean equals(Object _o1_) {
			if (!(_o1_ instanceof Vector3.Data)) return false;
			Vector3.Data _o_ = (Vector3.Data) _o1_;
			if (x != _o_.x) return false;
			if (y != _o_.y) return false;
			if (z != _o_.z) return false;
			return true;
		}

		@Override
		public final int hashCode() {
			int _h_ = 0;
			_h_ += x;
			_h_ += y;
			_h_ += z;
			return _h_;
		}

		@Override
		public String toString() {
			StringBuilder _sb_ = new StringBuilder();
			_sb_.append("(");
			_sb_.append(x);
			_sb_.append(",");
			_sb_.append(y);
			_sb_.append(",");
			_sb_.append(z);
			_sb_.append(")");
			return _sb_.toString();
		}

	}
}
