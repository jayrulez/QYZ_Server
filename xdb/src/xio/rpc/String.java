package xio.rpc;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

public final class String implements Marshal {
	private java.lang.String value;

	@Override
	public OctetsStream marshal(final OctetsStream os) {
		return os.marshal(value, xdb.Const.IO_CHARSET);
	}

	@Override
	public OctetsStream unmarshal(final OctetsStream os) throws MarshalException {
		value = os.unmarshal_String(xdb.Const.IO_CHARSET);
		return os;
	}

	public String() {
	}

	public String(final java.lang.String value) {
		this.value = value;
	}

	public java.lang.String getValue() {
		return value;
	}

	public void setValue(final java.lang.String value) {
		this.value = value;
	}

	@Override
	public java.lang.String toString() {
		return value;
	}
}
