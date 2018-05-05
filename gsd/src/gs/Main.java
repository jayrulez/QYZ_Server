package gs;

import cfg.CfgMgr;
import cfg.CfgMgr.DataDir;
import common.Rpc;
import common.Time;
import gm.PackageScanner;
import gnet.MapClient;
import gnet.link.Onlines;
import lx.gs.cmd.FCmd;
import lx.gs.gm.GmModule;
import lx.gs.idgen.FIdGen;
import lx.gs.rank.FRank;
import lx.gs.role.msg.SDayOver;
import map.map.MapMgr;
import xdb.Procedure;
import xdb.Trace;
import xdb.Xdb;
import xdb.Xdb.StopHandle;
import xdb.XdbConf;
import xdb.util.JMXServer;
import xio.Protocol;

import java.util.Arrays;
import java.util.Set;
import java.util.TimerTask;

public class Main {
    private static void usage() {
        System.out.println("Usage: java -jar gsd.jar gsd.config.xml");
        System.exit(0);
    }

    public static void main(String args[]) throws Exception {
        if(args.length != 1) {
            Main.usage();
        }

        Config conf = Config.getInstance();
        conf.load(args[0]);

        DataDir.dir = conf.getConfigDir();
        DataDir.encoding = conf.getConfigEncoding();
        CfgMgr.load();
        ConfigCheck.check();

        JMXServer jmxserver = new JMXServer(conf.getRmiPort(), conf.getRmiPort() + 1, conf.getRmiHost(), null, null);
        jmxserver.start();
        try {
            final int serverid = conf.getServerid();
            Xdb.getInstance().setConf(new XdbConf(serverid, conf.getXdbXml()));
            Trace.set(Trace.valueOf(conf.getTraceLevel().toUpperCase()));
            Onlines.loadConf(conf.getXioXml());
            Onlines.setTraceProtocolAtInfoLevel(conf.isTraceProtocol());

            Procedure.setOlines(Onlines.getInstance());

            Xdb.getInstance().start();

            Xdb.getInstance().setStopHandle(new StopHandle() {
                @Override
                public void afterStop() {
                    jmxserver.stop();
                    Logger.eventlog.close();
                }

                @Override
                public void beforeStop() {
                	Listeners.INSTANCE.beforeGsdStop();
                }
            });

            Logger.init(serverid);
            Time.refreshTimeInfo();
            FCmd.init();
            FIdGen.init();
            MapClient.init();

            Trace.info("modules start begin");
            Set<Class<?>> classSet = PackageScanner.scan("lx.gs", true);
            for(Class<?> clazz : classSet) {
                if (Arrays.asList(clazz.getInterfaces()).contains(Module.class)){
                    Object[] enumConstants = clazz.getEnumConstants();
                    if (enumConstants != null && enumConstants.length == 1){
                        Module module = (Module)enumConstants[0];
                        Listeners.INSTANCE.listenModule(module);
                        module.start();
                    } else {
                        Trace.warn("module:{} NOT ENUM SINGLETON", clazz.getName());
                    }
                }
            }
            Trace.info("modules start end");

            Listeners.INSTANCE.afterGsdStart();

            Trace.info("network start begin");
            Xdb.getInstance().startNetwork();
            Trace.info("network start end");
            
            Time.everydaySchedule(new TimerTask() {

                @Override
                public void run() {
                	Time.refreshTimeInfo();
                    FRank.refreshRanks(true);
                    Onlines.getInstance().broadcast(new SDayOver());
                    Listeners.INSTANCE.dayOver();
                }

            }, 0, 0);

            Time.everydaySchedule(new TimerTask() {

                @Override
                public void run() {
                    Listeners.INSTANCE.dayIdle();
                }

            }, CfgMgr.systemconfig.dayidlehour, 0);

            Time.hourSchedule(() -> Listeners.INSTANCE.hourChanged());

            long keepAliveInterval = CfgMgr.systemconfig.keepaliveinterval * 1000L;
            Xdb.timer().schedule(new TimerTask() {
				@Override
				public void run() {
					Onlines.getInstance().keepAlive();
                    Onlines.getInstance().checkExpire();
				}
            	
            }, keepAliveInterval, keepAliveInterval);

            GmModule.startWithPort(conf.getGmTelnetPort(), conf.getGmCsPort(), 0, conf.getGmPassword(), conf.isDebug());
            
            while (true) {
                Thread.sleep(1000000000);
            }
        } catch(Throwable t) {
        	t.printStackTrace();
        } finally {
            Xdb.getInstance().stop();
            jmxserver.stop();
            Logger.eventlog.close();
        }
	}
}
