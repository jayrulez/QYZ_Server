package xio.rpc;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

public final class Int implements Marshal {
	private int value;

	@Override
	public OctetsStream marshal(final OctetsStream os) {
		return os.marshal(value);
	}

	@Override
	public OctetsStream unmarshal(final OctetsStream os) throws MarshalException {
		value = os.unmarshal_int();
		return os;
	}

	public Int() {
		
	}

	public Int(final int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(final int value) {
		this.value = value;
	}

	@Override
	public java.lang.String toString() {
		return java.lang.String.valueOf(value);
	}
}
