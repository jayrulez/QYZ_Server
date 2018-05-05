package robot;

import cfg.CfgMgr;
import xdb.Trace;

import java.io.File;

/**
 * Created by HuangQiang on 2016/9/7.
 */
public class Main {
    private static void usage() {
        System.out.println("Usage: java -jar robot.jar robot.config.xml");
        System.exit(0);
    }

    public static void main(String args[]) throws Exception {
        if(args.length != 1) {
            usage();
        }

        final Config conf = Config.getInstance();
        conf.load(args[0]);
        CfgMgr.DataDir.dir = conf.getConfigDir();
        CfgMgr.DataDir.encoding = conf.getConfigEncoding();
        CfgMgr.load();

        xdb.Trace.set(Trace.valueOf(conf.getTraceLevel().toUpperCase()));
        xdb.Trace.openNew(new File("."), ":file", 0, 0);

        xdb.Executor.start(0, Runtime.getRuntime().availableProcessors() + 3, 5, 5, 5000);
        xio.XioConf.loadAndRegister(conf.getXioXml());
        xio.Engine.getInstance().open();

        Client.getIns().init(conf.getLinkIp(), conf.getLinkPort(), conf.getRobotPrefix(), conf.getRobotNum());
        Client.getIns().start();

        while (true) {
            Thread.sleep(10000000000L);
        }
    }


}
