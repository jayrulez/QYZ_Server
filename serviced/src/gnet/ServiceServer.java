package gnet;

import common.TaskQueue;
import serviced.Config;
import xdb.Trace;
import xio.Xio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by HuangQiang on 2016/8/18.
 */
public final class ServiceServer extends xio.Manager {
    private static volatile ServiceServer instance;
    public static ServiceServer getInstance() {
        return instance;
    }

    public ServiceServer() {
        instance = this;
    }

    private final static class ServerInfo {
        public final String ip;
        public final int port;
        public final xio.Xio io;
        public ServerInfo(String ip, int port, xio.Xio io) {
            this.ip = ip;
            this.port = port;
            this.io = io;
        }
    }

    private final Map<Integer, ServerInfo> gsds = new ConcurrentHashMap<>();
    private volatile List<Integer> cacheGsds = new ArrayList<>();

    @Override
    protected void addXio(Xio xio) {
        new AnnounceServiced(Config.getInstance().getServerid()).send(xio);
    }

    @Override
    protected void removeXio(Xio xio, Throwable throwable) {
        synchronized (this) {
            if(gsds.values().removeIf(s -> s.io == xio))
                notifyGsdsChange();
        }
    }

    public void onAddGsd(int serverid, String ip, int port, Xio xio) {
        Trace.info("ServiceServer.onAddGsd serverid:{}", serverid);
        synchronized (this) {
            final ServerInfo oldInfo = gsds.put(serverid, new ServerInfo(ip, port, xio));
            if (oldInfo != null)
                oldInfo.io.close();
            notifyGsdsChange();
        }
    }

    public int getRandomServerid() {
        final List<Integer> gsdids = cacheGsds;
        return gsdids.isEmpty() ? 0 : common.Utils.randomChoose(gsdids);
    }

    public boolean sendGsd(int serverid, xio.Protocol msg) {
        Trace.debug("ServiceServer.senGsd serverid:{} msg:{}{}", serverid, msg.getClass().getName(), msg);
        final ServerInfo info = gsds.get(serverid);
        return info != null && msg.send(info.io);
    }

    public boolean sendGsdByRoleid(long roleid, xio.Protocol msg) {
        return sendGsd(common.Utils.getServeridByRoleid(roleid), msg);
    }

    private void notifyGsdsChange() {
        cacheGsds = new ArrayList<>(gsds.keySet());

        final AnnounceGsds msg = new AnnounceGsds();
        gsds.forEach((id, info) -> {
            msg.gsds.add(new GsdInfo(id, info.ip, info.port));
        });
        Trace.debug("notifyGsdsChange:{}", msg);
        gsds.values().forEach(x -> msg.send(x.io));
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Xio get() {
        return null;
    }
}
