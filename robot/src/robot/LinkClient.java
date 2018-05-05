package robot;

import com.goldhuman.Common.Conf;
import com.goldhuman.Common.Octets;
import com.goldhuman.Common.Security.MD5Hash;
import com.goldhuman.Common.Security.Security;
import com.goldhuman.IO.Protocol.Protocol;
import com.goldhuman.IO.Protocol.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangQiang on 2016/6/7.
 */
public class LinkClient extends com.goldhuman.IO.Protocol.Manager {
    public final static TaskQueue taskQueue = new TaskQueue();

    private final static Map<String, LinkClient> clients = new HashMap<>();

    private final String username;
    private volatile Session onlySession;

    public LinkClient(String username) {
        this.username = username;
        this.onlySession = null;
    }

    public static void open(String ip, String port, String username) {

        final Conf conf = Conf.GetInstance();
        conf.put("client", "type", "tcp");
        conf.put("client", "address", ip);
        conf.put("client", "port", port);

        final LinkClient client = new LinkClient(username);
        clients.put(username, client);
        Protocol.Client(client);
    }

    public static void send(String username, int type, byte[] data) {
        final LinkClient client = clients.get(username);
        if(client != null && client.onlySession != null && client.Send(client.onlySession, new LuaProtocol(type, Octets.wrap(data)))) {
        } else {
            System.out.println("send fail. username:" + username + ",type:" + type + ",size:" + data.length
                    + ",client:" + client + ",session:" + (client != null ? client.onlySession : null));
        }
    }

    @Override
    protected void OnAddSession(Session session) {
        this.onlySession = session;
        System.out.println("onaddsession " + username);
    }

    @Override
    protected void OnDelSession(Session session) {
        this.onlySession = null;
        System.out.println("ondelsession " + username);
    }

    @Override
    protected void onUnknownProtocol(Session session, int type, Octets data) {
        LuaState.Ins.onUserProtocol(username, type, data);
    }

    @Override
    protected String Identification() {
        return "client";
    }

    static byte[] generateKeyByPassword(byte[] identity, byte[] password, byte[] nonce)
    {
        final Security s = Security.Create("HMAC_MD5");
        s.SetParameter(Octets.wrap(password));
        s.Update(Octets.wrap(identity));
        s.Update(Octets.wrap(nonce));
        final Octets key = new Octets();
        return s.Final(key).getBytes();
    }

    public static byte[] genKeyExchangeNonceAndSetInOutSecurity(String strUserName, byte[] userName, byte[] token, byte[] nonce)
    {
        final Octets opw = new Octets();
        opw.insert(opw.size(), userName);
        opw.insert(opw.size(), token);
        byte[] password = MD5Hash.Digest(opw).getBytes();


        byte[] outkey = generateKeyByPassword(userName, password, nonce);
        final LinkClient link = clients.get(strUserName);
        link.onlySession.SetOSecurity("ARC4", Octets.wrap(outkey));


        byte[] randomBytes = new byte[16];
        Octets randomOct = Octets.wrap(randomBytes);
        Security.Create("RANDOM").Update(randomOct);
        byte[] inkey = generateKeyByPassword(userName, password, randomBytes);
        link.onlySession.SetISecurity("DECOMPRESS_ARC4", Octets.wrap(inkey));
        return randomBytes;
    }
}
