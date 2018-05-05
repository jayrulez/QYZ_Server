package robot;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Octets;
import com.goldhuman.IO.Protocol.Manager;
import com.goldhuman.IO.Protocol.Protocol;
import com.goldhuman.IO.Protocol.ProtocolException;
import com.goldhuman.IO.Protocol.Session;

/**
 * Created by HuangQiang on 2016/6/7.
 */
public class LuaProtocol extends Protocol {
    public Octets data;

    public LuaProtocol(int ptype, Octets data) {
        this.type = ptype;
        this.data = data;
    }

    @Override
    public void Process(Manager manager, Session session) throws ProtocolException {

    }

    @Override
    public OctetsStream marshal(OctetsStream os) {
        os.insert(os.size(), data);
        return os;
    }

    @Override
    public OctetsStream unmarshal(OctetsStream os) throws MarshalException {
        return null;
    }
}
