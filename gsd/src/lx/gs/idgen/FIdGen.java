package lx.gs.idgen;

import gs.Utils;
import gs.GlobalIdGen;
import xbean.Pod;
import xtable.Idgen;

/**
 * Created by huangqiang on 2016/3/4.
 */
public class FIdGen {
    private static GlobalIdGen globalIdGen;

    public static void init() {
        globalIdGen = new GlobalIdGen(Utils.getServerId());
    }

    private static xbean.IdGen getRole(long roleId) {
        xbean.IdGen idGen = Idgen.get(roleId);
        if(idGen == null){
            idGen = Pod.newIdGen();
            Idgen.add(roleId, idGen);
        }
        return idGen;
    }

    public static long allocItemId(long roleId){
        xbean.IdGen idGen = getRole(roleId);
        long incrementId = idGen.getItemid() + 1;
        idGen.setItemid(incrementId);
        return incrementId;
    }

    public static long allocExchangeId(){
        return (1 << 12) | Utils.getServerId();  // serverid 是12位
    }

    /**
     * 注意 ：由于客户端不支持64位id, 此方法生成的id只供服务器使用
     * @return
     */
    public static long nextUniqueId(){
        return globalIdGen.nextId();
    }

}
