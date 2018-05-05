package serviced;

import cfg.CfgMgr;
import common.Rpc;
import gm.GmLauncher;
import gnet.ServiceServer;
import multistory.MutilStoryModule;
import teamfight.TeamFightModule;
import xdb.Trace;
import xdb.util.JMXServer;
import xio.Protocol;

import java.io.File;

/**
 * Created by HuangQiang on 2016/8/18.
 */
public class Main {
    private static void usage() {
        System.out.println("Usage: java -jar serviced.jar mapd.serviced.my.xml");
        System.exit(0);
    }

    public static void main(String args[]) throws Exception {
        if (args.length != 1) {
            usage();
        }

        final Config conf = Config.getInstance();
        conf.load(args[0]);
        CfgMgr.DataDir.dir = conf.getConfigDir();
        CfgMgr.DataDir.encoding = conf.getConfigEncoding();
        CfgMgr.load();

        xdb.Trace.set(Trace.valueOf(conf.getTraceLevel().toUpperCase()));
        xdb.Trace.openNew(new File("."), ":file", CfgMgr.systemconfig.dayidlehour, 0);

        Rpc.init(conf.getServerid(), new Rpc.Sender() {
            @Override
            public void sendRemote(int serverid, long ctxid, Protocol argument) {
                ServiceServer.getInstance().sendGsd(serverid, Rpc.make(ctxid, argument));
            }

            @Override
            public void sendLocal(long ctxid, Protocol argument) {
                throw new RuntimeException("Rpc.sendLocal not implements");
            }
        });

        TeamFightModule.INSTANCE.start();
        MutilStoryModule.INSTANCE.start();
        guardtower.GuardTowerModule.INSTANCE.start();

        xdb.Executor.start(0, Runtime.getRuntime().availableProcessors() + 3, 5, 5, 5000);
        xio.XioConf.loadAndRegister(conf.getXioXml());
        xio.Engine.getInstance().open();

        JMXServer jmxserver = new JMXServer(conf.getRmiPort(), conf.getRmiPort() + 1, conf.getRmiHost(), null, null);
        jmxserver.start();

        new GmLauncher()
                .test(true)
                .gmport(conf.getGmTelnetPort())
                //.autoScanGmPackage(GmModule.class.getPackage().getName()) //扫描package下带有gm.annotation.Module注解的class并自动注册到GM命令中
                .start();

        while (true) {
            Thread.sleep(10000000000L);
        }
    }
}
