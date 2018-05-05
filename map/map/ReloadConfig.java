package map;

import cfg.CfgMgr;
import xdb.Trace;

/**
 * Created by HuangQiang on 2016/9/2.
 * --车库源码（www.src.cool）
 */
public class ReloadConfig implements Runnable {

    @Override
    public void run() {
        Trace.info("ReloadConfig begin.");
        CfgMgr.load();
        Trace.info("ReloadConfig end.");
    }
}
