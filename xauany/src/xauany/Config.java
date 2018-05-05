package xauany;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.goldhuman.Common.Octets;

public class Config {
	public static final String OCTETS_CHARSET_ANSI = "ISO-8859-1";
	public static final String OCTETS_CHARSET_UNICODE = "UTF-16LE";
	public static final String OCTETS_CHARSET_UTF8 = "UTF-8";
	public static final Charset UTF8 = Charset.forName(OCTETS_CHARSET_UTF8);
	
	public static final int PLAT_TYPE_NONE = -1;
	public static final int SERVER_ID_NONE = -1;
	
	private static final Config INSTANCE = new Config();

    private boolean debug;

    private int serverid;

	private int jmxrmiport;
	private int jmxserverport;
	private String httpserverip;
	private int httpserverport;
	private Octets xiooutputsecurity;
	private Octets xioinputsecurity;

    private String xioXml;
    private String xdbXml;

    private int gmPort;
	private String gmPassword;

    private boolean activeCodeOpen;
    private boolean activeCodeLogin;
    private Set<Integer> activeCodeIncludePlatforms;
    private Set<Integer> activeCodeExcludePlatforms;

	private Config() {
	}

	public static Config getInstance() {
		return INSTANCE;
	}
	
	public void load(String filename) throws Exception {
		final Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(filename);
		final Element root = doc.getDocumentElement();

        debug = root.getAttribute("debug").equalsIgnoreCase("true");

        serverid = Integer.parseInt(root.getAttribute("serverid"));

        xioXml = root.getAttribute("xio.xml");
        xdbXml = root.getAttribute("xdb.xml");

		jmxrmiport = Integer.parseInt(root.getAttribute("jmx.rmiport"));
		jmxserverport = Integer.parseInt(root.getAttribute("jmx.serverport"));

		httpserverip = root.getAttribute("http.server.ip");
		httpserverport = Integer.parseInt(root.getAttribute("http.server.port"));
		xiooutputsecurity = Octets.wrap(root.getAttribute("xio.output.security"), OCTETS_CHARSET_ANSI);
		xioinputsecurity = Octets.wrap(root.getAttribute("xio.input.security"), OCTETS_CHARSET_ANSI);


        gmPort = Integer.parseInt(root.getAttribute("gm.port"));
		gmPassword = root.getAttribute("gm.password");

        activeCodeOpen = root.getAttribute("activecode.open").equalsIgnoreCase("true");
        activeCodeLogin = root.getAttribute("activecode.login").equalsIgnoreCase("true");
        activeCodeIncludePlatforms = Arrays.asList(root.getAttribute("activecode.includeplatforms").split(",")).stream()
                .filter(s -> !s.trim().isEmpty()).map(x -> Integer.parseInt(x)).collect(Collectors.toSet());
        activeCodeExcludePlatforms = Arrays.asList(root.getAttribute("activecode.excludeplatforms").split(",")).stream()
                .filter(s -> !s.trim().isEmpty()).map(x -> Integer.parseInt(x)).collect(Collectors.toSet());

        System.out.println("Config.load( " + filename + ") " + this);
			
		final NodeList subs = root.getChildNodes();
		for (int i = 0; i < subs.getLength(); i++) {
			final Node node = subs.item(i);
			if (Node.ELEMENT_NODE == node.getNodeType()){
				parseElement((Element) node);
			}
		}
	}

    @Override
    public String toString() {
        return String.format("Config{debug=%s,serverid:%s,activecode{open=%s,login=%s,include=%s,exclude=%s},gm{port=%s,password=%s}}",
                debug, serverid, activeCodeOpen, activeCodeLogin, activeCodeIncludePlatforms, activeCodeExcludePlatforms, gmPort, gmPassword);
    }

	private void parseElement(Element ele) throws Exception {
		if (!"plat".equalsIgnoreCase(ele.getNodeName())){
			return;
		}

		final String classname = ele.getAttribute("class");
		if (xdb.Trace.isDebugEnabled()){
			xdb.Trace.debug("Config.load plat classname = " + classname);
		}
		
		Class<?> cls = getClass().getClassLoader().loadClass(classname);
		PlatProcess process = (PlatProcess) cls.newInstance();
		process.init(ele);
		PlatManager.registerPlatProcess(process);
	}

    public boolean isDebug() {
        return debug;
    }

    public int getServerid() {
        return serverid;
    }

    public String getXioXml() {
        return xioXml;
    }

    public String getXdbXml() {
        return xdbXml;
    }

    public String getHttpServerIP() {
		return httpserverip;
	}

	public int getHttpServerPort() {
		return httpserverport;
	}

	public int getJmxRmiPort() {
		return jmxrmiport;
	}

	public int getJmxServerPort() {
		return jmxserverport;
	}
	
	public Octets getXioOutputSecurity() {
		return xiooutputsecurity;
	}

	public Octets getXioInputSecurity() {
		return xioinputsecurity;
	}

    public int getGmPort() {
        return gmPort;
    }

    public String getGmPassword() {
		return gmPassword;
	}

    public boolean isActiveCodeOpen() {
        return activeCodeOpen;
    }

    public boolean isActiveCodeLogin() {
        return activeCodeLogin;
    }

    public Set<Integer> getActiveCodeIncludePlatforms() {
        return activeCodeIncludePlatforms;
    }

    public Set<Integer> getActiveCodeExcludePlatforms() {
        return activeCodeExcludePlatforms;
    }

}
