package xauany;

import apns.ApnsManager;
import xauany.gm.GmModule;

import com.sun.net.httpserver.HttpServer;

import org.w3c.dom.Document;

import xdb.Trace;
import xdb.util.JMXServer;
import xio.XioConf;

import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.ReflectionException;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	private static javax.management.MBeanServer mbs = java.lang.management.ManagementFactory.getPlatformMBeanServer();
	
	private static void registerMbean(Object obj,String name) throws javax.management.NotCompliantMBeanException,javax.management.MBeanRegistrationException,javax.management.InstanceAlreadyExistsException,javax.management.MalformedObjectNameException, IntrospectionException, InstanceNotFoundException, ReflectionException{
		javax.management.ObjectName objectName = new javax.management.ObjectName("bean:name="+name);
		mbs.registerMBean(obj, objectName);
	}

	private static void initXdbModule() throws Exception {
	    final Config c = Config.getInstance();
		final xdb.XdbConf conf=new xdb.XdbConf(c.getServerid(), c.getXdbXml());
		final xdb.Xdb myxdb = xdb.Xdb.getInstance();
		myxdb.setConf(conf);

        if (Config.getInstance().isDebug())
            xdb.Trace.set(Trace.DEBUG);
		myxdb.start();
	}
	
	private static void initOnlinesModule() throws Exception {
        XioConf.loadAndRegister(Config.getInstance().getXioXml());
	}

	static private void initHttpServer() throws Exception {
		InetSocketAddress sa = new InetSocketAddress( Config.getInstance().getHttpServerIP(), Config.getInstance().getHttpServerPort());
		final HttpServer server = HttpServer.create(sa, 0);
		final ExecutorService executor = Executors.newCachedThreadPool();
		server.setExecutor(executor);
		server.createContext("/", new HttpServerHandler());
		server.start();
	}
	
	static private void initJMXServer() throws IOException {
		new JMXServer(Config.getInstance().getJmxRmiPort(), Config.getInstance().getJmxServerPort(), null, null, null).start();
	}

	
	public static void exit() {
		xdb.Trace.info("auany start to exit");
		xio.Engine.getInstance().close();
		xdb.Xdb.getInstance().stop();
		xdb.Trace.info("auany end to exit");
	}


    private static void usage() {
        System.out.println("Usage: java -jar xauany.jar xauany.config.my.xml");
        System.exit(0);
    }

    public static void main(String args[]) throws Exception {
        if(args.length != 1) {
            Main.usage();
        }
        Config.getInstance().load(args[0]);
        initXdbModule();
        initJMXServer();
        initOnlinesModule();
        initHttpServer();

		xio.Engine.getInstance().open();
		final Stopper stopper = new Stopper();
		registerMbean(stopper,"stopper");
		
		OrderNotifyMgr.INSTANCE.onServerStartup();
		ApnsManager.INSTANCE.init("apns.properties");
		
		FRobot.reserveRobotidIf();

        Config conf = Config.getInstance();
		GmModule.startWithPort(conf.getGmPort(), 0, conf.isDebug());
		
		stopper.doWait();
		exit();
	}
}
