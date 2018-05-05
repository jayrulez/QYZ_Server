package robot;

import com.goldhuman.Common.Octets;
import common.TaskQueue;
import xdb.Trace;
import xio.DynamicMultiConnector;
import xio.Xio;
import xio.security.*;

import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by HuangQiang on 2016/9/7.
 */
public class Client extends xio.Manager {
    private static Client Ins;
    public static Client getIns() {
        return Ins;
    }

    public Client() {
        Ins = this;
    }

    public final static Octets token = Octets.wrap("token".getBytes(StandardCharsets.UTF_8));

    private String ip;
    private int port;
    private int count;
    public void init(String ip, int port, String prefix, int count) {
        Trace.info("Client.init ip:{} port:{} prefix:{} count:{}", ip, port, prefix, count);
        this.ip = ip;
        this.port = port;
        this.count = count;
        for(int i = 0 ; i < count ; i++) {
            final String account = i + "@" + prefix;
            accounts.add(account);
        }
    }

    private int connectionNum;
    public void start() {
        TaskQueue.getScheduleExecutor().scheduleAtFixedRate(() -> {
            for(int i = 0; i < Config.getInstance().getLoginPerSecond() && connectionNum < count ; i++, connectionNum++) {
                addConnection("client:" + connectionNum, ip, port);
                Trace.info("addConnection. index:{} ip:{} port:{}", connectionNum, ip, port);
            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    protected synchronized void removeXio(Xio xio, Throwable throwable) {
        final Ctx ctx = xio2Account.get(xio);
        if(ctx != null) {
            ctx.roleid = 0;
            accounts.add(ctx.account);
        }
        Trace.info("Client.delXio account:{} xio:{}", ctx != null ? ctx.account : null, xio);
    }

    private LinkedList<String> accounts = new LinkedList<>();
    private Map<Xio, Ctx> xio2Account = new ConcurrentHashMap<>();

    @Override
    protected synchronized void addXio(Xio xio) {
        final String account = accounts.pollFirst();
        xio2Account.put(xio, new Ctx(account));
        Trace.info("Client.addXio account:{} xio:{}", account, xio);
    }

    public synchronized void addConnection(String name, String ip, int port) {
        try {
            ((DynamicMultiConnector) getCreator("Client")).addConnection(name, ip, port);
        } catch (Exception e) {
            Trace.error("addConnection.", e);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Xio get() {
        return null;
    }

    @Override
    public void execute(xio.Protocol msg) {
        final Ctx ctx = xio2Account.get(msg.getConnection());
        msg.setContext(ctx);
        Trace.debug("recv account:{} userid:{} roleid:{} msg:{}{}", ctx.account, ctx.userid, ctx.roleid, msg.getClass().getName(), msg);
        super.execute(msg);
    }


    static byte[] generateKeyByPassword(byte[] identity, byte[] password, byte[] nonce)
    {
        final Security s =  new HmacMd5Hash();
        s.setParameter(Octets.wrap(password));
        s.doUpdate(Octets.wrap(identity));
        s.doUpdate(Octets.wrap(nonce));
        final Octets key = new Octets();
        return s.doFinal(key).getBytes();
    }

    public static Octets genKeyExchangeNonceAndSetInOutSecurity(xio.Xio io, String strUserName, Octets token, Octets nonce)
    {
        final Octets opw = new Octets();
        final Octets userName = Octets.wrap(strUserName.getBytes(StandardCharsets.UTF_8));
        opw.insert(opw.size(), userName);
        opw.insert(opw.size(), token);
        byte[] password = new MD5Hash().doDigest(opw).getBytes();

        byte[] outkey = generateKeyByPassword(userName.getBytes(), password, nonce.getBytes());
        SecurityFilter.setXioOutputSecurity(io, new ARCFourSecurity(),Octets.wrap(outkey));

        byte[] randomBytes = new byte[16];
        Octets randomOct = Octets.wrap(randomBytes);
        new xio.security.Random().doUpdate(randomOct);
        byte[] inkey = generateKeyByPassword(userName.getBytes(), password, randomBytes);
        SecurityFilter.setXioInputSecurity(io, new JDecompressARCFourSecurity(), Octets.wrap(inkey));
        return Octets.wrap(randomBytes);
    }
}
