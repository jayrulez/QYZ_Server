package lx.gs.gm;

import gm.GmException;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import gnet.link.Onlines;
import lx.gs.login.LoginModule;

/**
 * @author Jin Shuai
 */
@Module(comment="服务器相关命令")
public class Server {

    @Cmd(comment="修改最高在线人数")
    public Object setMaxOnline(@Param(name="max online", comment="最大在线人数") int maxonline) {
        if(maxonline < 0 || maxonline > 10000){
            throw new GmException("range :[1, 10000], param : " + maxonline);
        }
        Onlines.getInstance().setMaxOnline(maxonline);
        return true;
    }

    @Cmd(comment="修改删除保留时间")
    public Object setPutoff(@Param(name="putoff", comment="单位：秒") int putoff) {
        LoginModule.INSTANCE.setPutOff(putoff);
        return true;
    }
}
