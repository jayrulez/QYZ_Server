package lx.gs.tips;

import cfg.Const;
import cfg.tips.LocationType;
import gnet.link.Onlines;
import gnet.link.Role;
import lx.gs.STips;
import xdb.Trace;
import xdb.Transaction;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Jin Shuai
 */
public class FTips {

    public static void send(long roleId, int location, int tipsCode, String... params){
        Onlines.getInstance().send(roleId, create(location, tipsCode, params));
    }

    public static void sendWhileCommit(long roleId, int location, int tipsCode, String... params){
        Transaction.tsendWhileCommit(roleId, create(location, tipsCode, params));
    }

    public static STips create(int location, int tipsCode, String... params){
        return new STips(location, tipsCode, "", toList(params));
    }

    public static STips create(int location, String content, String... params){
        return new STips(location, Const.NULL, content, toList(params));
    }

    public static STips create(int location, int tipsCode){
        return new STips(location, tipsCode, "", new ArrayList<>());
    }

    public static STips create(int location, String content){
        return new STips(location, Const.NULL, content, new ArrayList<>());
    }

    public static void broadcast(int location, int tipsCode, String... params){
        try{
            if(!LocationType.enums.contains(Integer.valueOf(location))){
                location = LocationType.CENTER;
            }
            ArrayList<String> list = toList(params);
            Onlines.getInstance().broadcast(new STips(location, tipsCode, "", list));
        }catch (Exception e){
            Trace.error(e);
        }
    }

    public static void broadcast(int location, String content, String... params){
        try{
            if(!LocationType.enums.contains(Integer.valueOf(location))){
                location = LocationType.CENTER;
            }
            ArrayList<String> list = toList(params);
            Onlines.getInstance().broadcast(new STips(location, Const.NULL, content, list));
        }catch (Exception e){
            Trace.error(e);
        }
    }


    public static void broadcastWhileCommit(int location, int tipsCode, String... params){
        try{
            if(!LocationType.enums.contains(Integer.valueOf(location))){
                location = LocationType.CENTER;
            }
            ArrayList<String> list = toList(params);
            for (Role role : Onlines.getInstance().roles()) {
                Transaction.tsendWhileCommit(role.getRoleid(), new STips(location, tipsCode, "", list));
            }
        }catch (Exception e){
            Trace.error(e);
        }
    }

    public static void broadcastWhileCommit(int location, String content, String... params){
        try{
            if(!LocationType.enums.contains(Integer.valueOf(location))){
                location = LocationType.CENTER;
            }
            ArrayList<String> list = toList(params);
            for (Role role : Onlines.getInstance().roles()) {
                Transaction.tsendWhileCommit(role.getRoleid(), new STips(location, Const.NULL, content, list));
            }
        }catch (Exception e){
            Trace.error(e);
        }
    }

    private static ArrayList<String> toList(String... param){
        ArrayList<String> ret = new ArrayList<>();
        if(param != null && param.length > 0){
            ret.addAll(Arrays.asList(param));
        }
        return ret;
    }
}
