package gs;

import common.AppXmlConfig;
import org.w3c.dom.Element;

public final class Config extends AppXmlConfig {
	private static final Config INSTANCE = new Config();
    public static Config getInstance() {
        return INSTANCE;
    }

    private boolean debug;

    private int serverid;

    private String traceLevel;
    private boolean traceProtocol;

    private String xdbXml;

    private String xioXml;
    private String mapServerIp;
    private int mapServerPort;

    private String configDir;
    private String configEncoding;

    private String rmiHost;
    private int rmiPort;

    private boolean gmFromChat;
    private int gmTelnetPort;
    private int gmCsPort;
    private String gmPassword;
    private int maxOnline;


	@Override
    public String toString() {
	    return String.format("== Config{serverid:%s,debug=%s,trace.level=%s,trace.protocol=%s,xdb.xml=%s,xio.xml=%s,xio.mapserverip:%s,xio.mapserverport:%s,config.dir=%s," +
            "config.encoding=%s,rmi.host=%s,rmi.port=%s,gm.fromchat=%s,gm.telnetport=%s,gm.csport=%s,gm.password=%s}",
                serverid, debug, traceLevel, traceProtocol, xdbXml, xioXml, mapServerIp, mapServerPort, configDir,
                configEncoding, rmiHost, rmiPort, gmFromChat, gmTelnetPort, gmCsPort, gmPassword
        );
    }

    @Override
	protected void parse(Element root) throws Exception {
        debug = getBool(root, "debug");

        serverid = getInt(root, "serverid");

        traceLevel = getString(root,"trace.level");
        traceProtocol = getBool(root, "trace.protocol");

        xdbXml = getString(root,"xdb.xml");
        xioXml = getString(root,"xio.xml");
        mapServerIp = getString(root, "xio.mapserver.ip");
        mapServerPort = getInt(root, "xio.mapserver.port");

        configDir = getString(root,"config.dir");
        configEncoding = getString(root,"config.encoding");

        rmiHost = getString(root,"rmi.host");
        rmiPort = getInt(root, "rmi.port");

        gmFromChat = getBool(root, "gm.fromchat");
        gmTelnetPort = getInt(root, "gm.telnetport");
        gmCsPort = getInt(root, "gm.csport");
        gmPassword = getString(root, "gm.password");

        maxOnline = getInt(root, "server.maxonline");

        System.out.println(this);
	}

    public boolean isDebug() {
        return debug;
    }

    public int getServerid() {
        return serverid;
    }

    public String getTraceLevel() {
        return traceLevel;
    }

    public boolean isTraceProtocol() {
        return traceProtocol;
    }

    public String getXdbXml() {
        return xdbXml;
    }

    public String getXioXml() {
        return xioXml;
    }

    public String getMapServerIp() {
        return mapServerIp;
    }

    public int getMapServerPort() {
        return mapServerPort;
    }

    public String getConfigDir() {
        return configDir;
    }

    public String getConfigEncoding() {
        return configEncoding;
    }

    public String getRmiHost() {
        return rmiHost;
    }

    public int getRmiPort() {
        return rmiPort;
    }

    public boolean isGmFromChat() {
        return gmFromChat;
    }

    public int getGmTelnetPort() {
        return gmTelnetPort;
    }

    public int getGmCsPort() {
        return gmCsPort;
    }

    public String getGmPassword() {
        return gmPassword;
    }

    public int getMaxOnline() {
        return maxOnline;
    }
}
