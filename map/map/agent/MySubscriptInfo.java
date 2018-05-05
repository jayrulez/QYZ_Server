package map.agent;

/**
 * Created by HuangQiang on 2016/7/5.
 */
public class MySubscriptInfo {
    public final Agent agent;
    public boolean stillSubscript;

    public int lastHp;
    public MySubscriptInfo(Agent agent, boolean stillSubscript) {
        this.agent = agent;
        this.stillSubscript = stillSubscript;
    }
}
