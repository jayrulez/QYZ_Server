package serviced;

import common.AppXmlConfig;
import org.w3c.dom.Element;

public class Config extends AppXmlConfig {
	private static final Config INSTANCE = new Config();
    public static Config getInstance() {
        return INSTANCE;
    }

    private boolean debug;

    private String traceLevel;
    private boolean traceProtocol;

    private String xioXml;

    private String configDir;
    private String configEncoding;

    private String rmiHost;
    private int rmiPort;

    private int gmTelnetPort;

    private int serverid;


	@Override
    public String toString() {
	    return String.format("== Config{debug=%s,serverid=%s,trace.level=%s,trace.protocol=%s,xio.xml=%s,config.dir=%s," +
            "config.encoding=%s, rmi.host=%s,rmi.port=%s,gm.telnetport=%s}",
                debug, serverid, traceLevel, traceProtocol, xioXml, configDir,
                configEncoding, rmiHost, rmiPort, gmTelnetPort
        );
    }

    @Override
	protected void parse(Element root) throws Exception {
        debug = getBool(root, "debug");

        serverid = getInt(root, "serverid");

        traceLevel = getString(root,"trace.level");
        traceProtocol = getBool(root, "trace.protocol");

        xioXml = getString(root,"xio.xml");

        configDir = getString(root,"config.dir");
        configEncoding = getString(root,"config.encoding");

        rmiHost = getString(root,"rmi.host");
        rmiPort = getInt(root, "rmi.port");

        gmTelnetPort = getInt(root, "gm.telnetport");

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

    public String getXioXml() {
        return xioXml;
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

    public int getGmTelnetPort() {
        return gmTelnetPort;
    }
}
