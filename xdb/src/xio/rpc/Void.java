package xio.rpc;

import com.goldhuman.Common.Marshal.Marshal;
import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;

public final class Void implements Marshal {

	@Override
	public OctetsStream marshal(final OctetsStream os) {
		return os;
	}

	@Override
	public OctetsStream unmarshal(final OctetsStream os) throws MarshalException {
		return os;
	}

}
