package lx.gs.gm;

import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import lx.gs.schedule.FSchedule;

/**
 * Created by huangqiang on 2016/3/11.
 */

@Module(comment="控制器相关")
public class Controller {

    @Cmd(comment = "提前强行开启控制器")
    public void openAhead(@Param(name="controllerid", comment = "控制器id") int controllerid) {
        final lx.gs.schedule.Controller controller = FSchedule.getController(controllerid);
        controller.openAhead();
    }

    @Cmd(comment = "提前强行结束控制器")
    public void closeAhead(@Param(name="controllerid", comment = "控制器id") int controllerid) {
        final lx.gs.schedule.Controller controller = FSchedule.getController(controllerid);
        controller.closeAhead();
    }
}
