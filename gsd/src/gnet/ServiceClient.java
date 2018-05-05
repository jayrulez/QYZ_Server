package gnet;

import gs.Config;
import map.map.Handler;
import map.msg.XRpc;
import xdb.Trace;
import xio.Xio;

/**
 * Created by HuangQiang on 2016/8/17.
 */
public class ServiceClient extends xio.ManagerSimple {
    private static ServiceClient instance;

    public static ServiceClient getInstance() {
        return instance;
    }

    public ServiceClient() {
        instance = this;
    }

    public static boolean send(xio.Protocol msg) {
        Trace.debug("ServiceClient.send. msg:{}{}", msg.getClass().getName(), msg);
        return msg.send(instance);
    }


    @Override
    protected void onXioAdded(Xio io) {
        super.onXioAdded(io);
        final Config conf = Config.getInstance();
        new AnnounceGsd(conf.getServerid(), conf.getMapServerIp(), conf.getMapServerPort()).send(io);
    }

    @Override
    protected void onXioRemoved(Xio io, Throwable e) {
        super.onXioRemoved(io, e);
        MapClient.getInstance().removeXio(io, e);
    }

    @Override
    public void execute(xio.Protocol msg) {
        if(msg.getClass() == XRpc.class) {
            Handler.processXRpc((XRpc)msg);
        } else {
            msg.run();
        }
    }
}
