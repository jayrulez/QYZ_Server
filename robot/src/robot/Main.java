package robot;

import com.goldhuman.Common.Conf;
import com.goldhuman.Common.ThreadPool;
import com.goldhuman.IO.PollIO;

/**
 * Created by HuangQiang on 2016/6/7.
 */
public class Main {



    public static void main(String[] argv) {
        if(argv.length != 2) {
            System.err.println("usage:  java -jar robot.jar scriptpath configdatapath");
            System.exit(-1);
        }

        try {

            Conf conf = Conf.GetInstance();
            conf.put("ThreadPool", "config", "(1,2)");

            final String scriptsRoot = argv[0];
            final String configDataRoot = argv[1];

            LuaState.Ins.init(scriptsRoot, configDataRoot);
            LuaState.Ins.start();

            ThreadPool.AddTask(PollIO.GetTask());

            while(true) {
                Thread.sleep(1000000000L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
