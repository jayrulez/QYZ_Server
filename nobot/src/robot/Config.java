package robot;

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
    private String linkIp;
    private int    linkPort;

    private String configDir;
    private String configEncoding;

    private String robotPrefix;
    private int robotNum;

    private int loginPerSecond;

	@Override
    public String toString() {
	    return String.format("== Config{debug=%s,trace.level=%s,trace.protocol=%s,xio.xml=%s,config.dir=%s,config.encoding=%s," +
            "link.ip=%s, link.port=%s,robot.prefix=%s, robot.num=%s, robot.loginpersecond=%s}",
                debug, traceLevel, traceProtocol, xioXml, configDir, configEncoding,
                linkIp, linkPort, robotPrefix, robotNum, loginPerSecond);
    }

    @Override
	protected void parse(Element root) throws Exception {
        debug = getBool(root, "debug");
        traceLevel = getString(root,"trace.level");
        traceProtocol = getBool(root, "trace.protocol");

        xioXml = getString(root,"xio.xml");

        linkIp = getString(root, "xio.link.ip");
        linkPort = getInt(root, "xio.link.port");

        configDir = getString(root,"config.dir");
        configEncoding = getString(root,"config.encoding");

        robotPrefix = getString(root, "robot.prefix");
        robotNum = getInt(root, "robot.count");
        loginPerSecond = getInt(root, "robot.loginpersecond");

        System.out.println(this);
	}

    public boolean isDebug() {
        return debug;
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

    public String getLinkIp() {
        return linkIp;
    }

    public int getLinkPort() {
        return linkPort;
    }

    public String getRobotPrefix() {
        return robotPrefix;
    }

    public int getRobotNum() {
        return robotNum;
    }

    public int getLoginPerSecond() {
        return loginPerSecond;
    }
}
