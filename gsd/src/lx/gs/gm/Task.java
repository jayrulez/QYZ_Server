package lx.gs.gm;

import gm.annotation.Cmd;
import gm.annotation.Module;
import xdb.Procedure;

/**
 * @author Jin Shuai
 */
@Module(comment="自定义task, 用来临时解决问题，class刷新后记得重置，以免多次执行")
public class Task {

    @Cmd(comment="执行自定义任务，先reload class")
    public Object execute() {
        new Procedure() {
            @Override
            protected boolean process() {
                new TaskContent().run();
                return true;
            }
        }.call();
        return true;
    }
}
