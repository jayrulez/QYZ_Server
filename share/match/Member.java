package match;

/**
 * Created by HuangQiang on 2016/5/9.
 */
public class Member {
    public final long roleid;
    public Member(long roleid) {
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return String.format("Member{roleid:%s}", roleid);
    }
}
