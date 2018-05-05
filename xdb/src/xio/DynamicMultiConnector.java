package xio;

import org.w3c.dom.Element;
import xdb.Trace;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by HuangQiang on 2016/9/1.
 */
public class DynamicMultiConnector extends Creator {
    DynamicMultiConnector() {
        Trace.info("create DynamicMultiConnector");
    }

    private final Map<String, Connector> connectors = new HashMap<>();

    private boolean isOpen;

    public static Creator create(Manager manager, Element e) throws Exception {
        String clsName = e.getAttribute("class");
        DynamicMultiConnector connector = clsName.isEmpty() ? new DynamicMultiConnector() :
                (DynamicMultiConnector) Class.forName(clsName).newInstance();
        connector.parse(manager, e);
        connector.initNameAndAddress(e.getAttribute("name"), null);
        return connector;
    }

    @Override
    void onClose(Xio xio, Throwable e) {

    }

    @Override
    void open() {
        synchronized (this) {
            if(!isOpen) {
                isOpen = true;
                for(Connector c : connectors.values()) {
                    c.open();
                }
            }
        }
    }

    @Override
    void close() {
        synchronized (this) {
            if(isOpen) {
                isOpen = false;
                for(Connector c : connectors.values()) {
                    c.close();
                }
            }
        }
    }

    public boolean addConnection(String name, String ip, int port) {
        synchronized (this) {
            if(connectors.containsKey(name))
                return false;
            Trace.info("DynamicMultiConnector.addConnection. name:{} ip:{} port:{}", name, ip, port);
            final Connector connector = Connector.create(getManager(), getInputBufferSize(), getOutputBufferSize(),
                    getSendBufferSize(), getReceiveBufferSize(), isTcpNoDelay(), ip, port, true);
            connectors.put(name, connector);
            if(isOpen)
                connector.open();
            return true;
        }
    }

    public boolean removeConnection(String name) {
        synchronized (this) {
            final Connector connector = connectors.remove(name);
            if(connector != null) {
                Trace.info("DynamicMultiConnector.removeConnection. name:{} connector:{}", name, connector);
                connector.close();
                return true;
            } else {
                return false;
            }
        }
    }

    public final static class Address {
        public final String ip;
        public final int port;
        public Address(String ip , int port) {
            this.ip = ip;
            this.port = port;
        }
    }

    public void setConnections(Map<String, Address> servers) {
        synchronized (this) {
            servers.forEach((name, addr) -> {
                try {
                    if (!connectors.containsKey(name)) {
                        addConnection(name, addr.ip, addr.port);
                    }
                } catch (Exception e) {
                    Trace.error("DynamicMultiConnector.setConnections add name:" + name, e);
                }
            });
            new HashMap<>(connectors).forEach((name, conn) -> {
                try {
                    if(!servers.containsKey(name))
                        removeConnection(name);
                } catch (Exception e) {
                    Trace.error("DynamicMultiConnector.setConnections remove name:" + name, e);
                }
            });
        }
    }
}
