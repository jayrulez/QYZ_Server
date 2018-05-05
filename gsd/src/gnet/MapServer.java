package gnet;

import gs.Config;
import map.map.Handler;
import xdb.Trace;
import xio.Protocol;
import xio.Xio;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HuangQiang on 2016/9/4.
 */
public class MapServer extends xio.Manager {
    private static volatile MapServer instance;

    public static MapServer getInstance() {
        return instance;
    }

    public MapServer() {
        instance = this;
    }

    @Override
    protected void removeXio(Xio xio, Throwable throwable) {
        MapClient.getInstance().removeXio(xio, throwable);
    }

    @Override
    protected void addXio(Xio xio) {
        MapClient.getInstance().addXio(xio);
    }

    public void execute(Protocol msg) {
       MapClient.getInstance().execute(msg);
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
