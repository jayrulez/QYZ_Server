package lx.gs.gm;

import xdb.Trace;

/**
 * @author Jin Shuai
 */
public class TaskContent implements Runnable {

    @Override
    public void run() {
        try{
            logic();
        } catch (Exception e){
            Trace.error(e);
        }
    }

    /**
     * 在这里填写逻辑内容后，重新编译，然后在服务器执行reload
     */
    private void logic() {
        System.out.println("default");
    }
}
