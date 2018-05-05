package xauany.gm;

import com.goldhuman.Common.Marshal.MarshalException;
import com.goldhuman.Common.Octets;
import gm.GmCmdCommonError;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import xauany.PlatManager;
import xdb.Trace;

import java.util.*;

/**
 * Created by HuangQiang on 2016/8/15.
 */

@Module(comment="充值相关")
public class Pay {

    @Cmd(comment = "生成一条测试充值返还记录")
    public Object genOnePayReturnRecord(@Param(name="userid", comment = "userid")long userid,
                                        @Param(name="roleid", comment = "roleid")long roleid,
                                        @Param(name="hasgotreturn", comment = "?????????????")boolean hasgotreturn,
                                        @Param(name="totalpay", comment = "?????")long totalpay) {

        final xbean.UserPayInfo info = xbean.Pod.newUserPayInfo();
        info.setRoleid(roleid);
        info.setTotalpay(totalpay);
        info.setHasgotreturn(hasgotreturn);
        xtable.Userpays.getTable().put(userid, info);
        return "succ";
    }

    @Cmd(autoTransaction = false, comment = "根据充值订单历史生成所有充值返还记录")
    public Object genPayReturn() {
        final List<Long> userids = new ArrayList<>();
        xtable.Userpays.getTable().walk((userid, info) -> {
            userids.add(userid);
            return true;
        });
        new xdb.Procedure() {
            @Override
            protected boolean process() {
                for(long userid : userids) {
                    xtable.Userpays.remove(userid);
                }
                return true;
            }
        }.call();
        final Map<Long, xbean.UserPayInfo> userTotalPays = new HashMap<>();
        final Set<Long> excludePrices = new HashSet<>(Arrays.asList(2800L, 6000L, 9000L));
        xtable.Onesdkorderinfos.getTable().walk((orderid, info) -> {
            try {
                final Long userid = xtable.Onesdkusers.selectUserid(info.getUseridentity());
                final long amount = Long.parseLong(PlatManager.unmarshalVars(new Octets(info.getVarsCopy())).get("amount"));
                Trace.debug("Pay.genPayReturn orderid:{} useridentify:{} userid:{} amount:{}", orderid, info.getUseridentity(), userid, amount);
                xbean.UserPayInfo userPayInfo = userTotalPays.get(userid);
                if(userPayInfo == null) {
                    userPayInfo = xbean.Pod.newUserPayInfoData();
                    userTotalPays.put(userid, userPayInfo);
                }
                userPayInfo.setTotalpay(userPayInfo.getTotalpay() + amount);
                userPayInfo.setTotalyunbao(userPayInfo.getTotalyunbao() + amount/10);
                userPayInfo.setTotalbindyuanbao(userPayInfo.getTotalbindyuanbao() + amount * 2/10);
                if (!excludePrices.contains(amount)) {
                    userPayInfo.setTotalvipexp(userPayInfo.getTotalvipexp() + amount/10);
                }
            } catch (MarshalException e) {
                Trace.error("Pay.genPayReturn", e);
            }
            return true;
        });
        userTotalPays.forEach((userid, totalpay) -> {
            if(totalpay.getTotalpay() > 0) {
                new xdb.Procedure() {
                    @Override
                    protected boolean process() {
                        xtable.Userpays.insert(userid, totalpay.toBean());
                        Trace.info("Pay.genPayReturn userid:{} totalpay:{} totalyuanbao:{} totalbindyuanbao:{} totalvipexp:{}",
                                userid, totalpay.getTotalpay(), totalpay.getTotalyunbao(), totalpay.getTotalbindyuanbao(), totalpay.getTotalvipexp());
                        return true;
                    }
                }.execute();
            }
        });
        return "succ";
    }

    @Cmd(comment = "清空所有充值返还记录")
    public Object clearPayReturn() {
        final List<Long> userids = new ArrayList<>();
        xtable.Userpays.getTable().walk((userid, info) -> {
            userids.add(userid);
            return true;
        });

        for(long userid : userids) {
            xtable.Userpays.remove(userid);
        }
        return "succ";
    }
}
