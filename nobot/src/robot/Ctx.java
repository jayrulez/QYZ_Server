package robot;

import lx.gs.login.RoleDetail;

import java.util.ArrayList;

/**
 * Created by HuangQiang on 2016/9/7.
 */
public class Ctx {
    public final String account;
    public volatile long userid;
    public volatile long roleid;
    public volatile int index;
    public final ArrayList<map.msg.Vector3> linger = new ArrayList<>();
    public RoleDetail roleDetail;
    public long targetid;

    public Ctx(String account) {
        this.account = account;
    }
}
