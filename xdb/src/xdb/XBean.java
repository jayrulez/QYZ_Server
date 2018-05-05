package xdb;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import xgen.Type;

import java.util.concurrent.atomic.AtomicLong;
import java.util.List;
import java.util.ArrayList;

public class XBean implements Bean {
	private static final AtomicLong objid = new AtomicLong();

	private final Long _objid_ = objid.incrementAndGet();
	private XBean _parent_;
	private String _varname_;

	public XBean(XBean parent, String varname) {
		_parent_ = parent;
		_varname_ = varname;
	}

	final void xdbParent(XBean parent, String varname, boolean log) {
		if (null != parent) {
			if (null != _parent_) // parent != null && _parent_ != null
				throw new XManagedError();
		}
		else {
			if (null == _parent_) // parent == null && _parent_ == null
				throw new XManagedError("not managed");
		}
		if (log)
			Transaction.currentSavepoint().addIfAbsent(new LogKey(this, "_parent_"), new LogXP());
		_parent_ = parent;
		_varname_ = varname;
	}

	private class LogXP implements Log {
		private XBean parent;
		private String varname;

		LogXP() {
			this.parent = _parent_;
			this.varname = _varname_;
		}

		@Override
		public void commit() {
			// parent ���޸Ĳ����ĸ���Ʒ�����commit������޸Ļ�ͨ��dirty�����ﲻ��ͨ�档
			// notifyXdbDirty();
		}

		@Override
		public void rollback() {
			_parent_ = this.parent;
			_varname_ = this.varname;
		}
	}

	@Override
	public final boolean xdbManaged() {
		return _parent_ != null;
	}

	@Override
	public final String xdbVarname() {
		return _varname_;
	}

	@Override
	public final Bean xdbParent() {
		return _parent_;
	}

	void xdbLogNotify(xdb.logs.LogNotify notify) {
		if (null != _parent_)
			_parent_.xdbLogNotify(notify.push(new LogKey(_parent_, xdbVarname())));
	}

	@Override
	public final Long xdbObjId() {
		return _objid_;
	}

	@Override
	public void _reset_unsafe_() {
		throw new IllegalStateException();
	}

	@Override
	public OctetsStream marshal(OctetsStream arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public OctetsStream unmarshal(OctetsStream arg0) throws MarshalException {
		throw new UnsupportedOperationException();
	}

    public static void skipUnknownField(short tid, OctetsStream _os_) throws MarshalException {
        final short tag = (short)(tid & Type.TAG_MASK);
        //Trace.debug("skipunknownfield tag:" + tag + " , id:" + (tid & Type.ID_MASK));
        switch (tag) {
            case Type.Tag.BOOL: _os_.unmarshal_boolean(); break;
            case Type.Tag.SHORT: _os_.unmarshal_short(); break;
            case Type.Tag.INT: _os_.unmarshal_int(); break;
            case Type.Tag.LONG: _os_.unmarshal_long(); break;
            case Type.Tag.FLOAT: _os_.unmarshal_float(); break;
            case Type.Tag.BINARY: _os_.unmarshal_bytes(); break;
            case Type.Tag.STRING: _os_.unmarshal_String(xdb.Const.IO_CHARSET); break;
            case Type.Tag.LIST:
            case Type.Tag.SET:
            case Type.Tag.MAP:
            case Type.Tag.BEAN:
                _os_.unmarshal_Octets(); break;
            default:
                Trace.error("unkown xdb.tag:" + tag);
                throw new MarshalException();
        }
    }

	@Override
	public Bean toConst() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isConst() {
		return false;
	}

	@Override
	public boolean isData() {
		return false;
	}

	public xdb.logs.Listenable newListenable() {
		return null;
	}

	public final Forefathers xdbForefathers() {
		Forefathers forefathers = new Forefathers();
		xdbForefathers(forefathers);
		return forefathers;
	}

	public final void xdbForefathers(Forefathers forefathers) {
		if (null != this._parent_) {
			forefathers.forefathers.add(this._parent_);
			this._parent_.xdbForefathers(forefathers);
		}
	}

	public final class Forefathers {
		private List<Bean> forefathers = new ArrayList<Bean>();

		public Bean getBean() {
			return XBean.this;
		}

		public List<Bean> getForefathers() {
			return forefathers;
		}

		public Table getTable() {
			if (forefathers.isEmpty())
				return null;
			Bean last = forefathers.get(forefathers.size() - 1);
			if (last instanceof Table)
				return (Table)last;
			return null;
		}

		public TRecord<?, ?> getRecord() {
			if (forefathers.size() < 2)
				return null;
			Bean lesslast = forefathers.get(forefathers.size() - 2);
			if (lesslast instanceof TRecord<?, ?>)
				return (TRecord<?, ?>)lesslast;
			return null;
		}

		/**
		 * ���ص�ǰbean������������
		 * @return null if the bean is not managed by xdb��
		 */
		public Lockey getLockey() {
			TRecord<?, ?> record = this.getRecord();
			if (null != record)
				return record.getLockey();
			return null;
		}

		public boolean isHeldByXdb() {
			return null != this.getTable(); // ����Ҳ����ʹ�� getRecord, getLockey ���жϡ�
		}
	}

	private static volatile boolean _xdb_verify_ = false;

	public static void _set_xdb_verify_(boolean enable) {
		_xdb_verify_ = enable;
	}

	public static boolean _is_xdb_verify_() {
		return _xdb_verify_;
	}

	public final void _xdb_verify_unsafe_() {
		if (false == _xdb_verify_)
			return;

		if (false == xdb.Xdb.getInstance().getTables().isHeldFlushWriteLockByCurrentThread()) {
			Lockey lockey = this.xdbForefathers().getLockey();
			if (null != lockey && false == lockey.isHeldByCurrentThread())
				throw new UnfilialError(this.getClass().getName());
		}
	}

	public static class UnfilialError extends Error {
		private static final long serialVersionUID = -2377572699783238493L;

		public UnfilialError(String message) {
			super(message);
		}
	}
}
