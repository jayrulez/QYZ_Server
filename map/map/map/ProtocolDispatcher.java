package map.map;

import com.goldhuman.Common.Octets;
import common.ErrorCode;
import map.msg.RoleProtocols;

import java.util.Set;

/**
 * Created by huangqiang on 2016/4/24.
 */
public interface ProtocolDispatcher {
    //发往客户端的接口
    void sendClient(long roleid, xio.Protocol msg);
    void multicastClient(Set<Long> roleids, xio.Protocol msg);

    xio.Protocol createSError(ErrorCode err);

    void sendServer(int serverid, xio.Protocol msg);
    default void sendServerByRoleid(long roleid, xio.Protocol msg) {
        sendServer((int)(roleid & ((1 << 12) - 1)), msg);
    }
    void sendXdbRole(long roleid, xio.Protocol msg);
    void sendXdbServer(int serverid, xio.Protocol msg);

    xio.Protocol decode(int ptype, Octets pdata);
}
