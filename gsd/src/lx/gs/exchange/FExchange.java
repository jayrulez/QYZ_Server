package lx.gs.exchange;

import cfg.CfgMgr;
import cfg.bag.BagType;
import cfg.exchange.Const;
import common.ErrorCode;
import lx.gs.bag.FBag;
import lx.gs.equip.FEquip;
import lx.gs.exchange.msg.CQuery;
import lx.gs.exchange.msg.SDelItem;
import lx.gs.exchange.msg.SInfo;
import lx.gs.exchange.msg.SQuery;
import lx.gs.item.FItem;
import lx.gs.logger.By;
import lx.gs.mail.FMail;
import xbean.AccessoryProp;
import xbean.ExchangeItem;
import xbean.Pod;
import xtable.Roleinfos;

import java.util.*;
import java.util.stream.Collectors;

public final class FExchange {
    public static xbean.RoleExchange get(long roleid) {
        xbean.RoleExchange info = xtable.Roleexchange.get(roleid);
        if (info == null) {
            info = xbean.Pod.newRoleExchange();
            xtable.Roleexchange.insert(roleid, info);
        }
        return info;
    }

    public static lx.gs.exchange.msg.ExchangeItem convert(xbean.ExchangeItem item) {
        final lx.gs.exchange.msg.ExchangeItem e = new lx.gs.exchange.msg.ExchangeItem();
        e.id = item.getId();
        e.itemid = item.getModelid();
        e.num = item.getNum();
        e.aprice = item.getPrice();
        e.anneallevel = item.getAnneallevel();
        e.perfuselevel = item.getPerfuselevel();
        e.prop.mainprop.addAll(item.getAccessorymainprop().stream().map(FEquip::dbProp2Proto).collect(Collectors.toList()));
        e.prop.extraprop.addAll(item.getAccessoryviceprop().stream().map(FEquip::dbProp2Proto).collect(Collectors.toList()));
        return e;
    }

    public static SInfo createSInfo(long roleid) {
        final SInfo re = new SInfo();

        final xbean.RoleExchange info = get(roleid);
        for (Long exid : info.getItems()) {
            re.items.add(convert(xtable.Exchangeitem.get(exid)));
        }
        for (Long logid : info.getLogs()) {
            re.logs.add(convert(xtable.Exchangelog.get(logid)));
        }
        return re;
    }

    public static lx.gs.exchange.msg.ExchangeLog convert(xbean.ExchangeLog log) {
        final lx.gs.exchange.msg.ExchangeLog e = new lx.gs.exchange.msg.ExchangeLog();
        e.seller = log.getSeller();
        e.buyer = log.getBuyer();
        e.buyername = Roleinfos.selectName(e.buyer);
        e.time = log.getTime();
        e.item = convert(log.getItem());
        return e;
    }

    public static ErrorCode addExchangeItemToBag(long roleid, long id, int count) {
        final xbean.ExchangeItem item = xtable.Exchangeitem.get(id);
        final int itemid = item.getModelid();
        final int num = count < 0 ? item.getNum() : count;
        By by = count < 0 ? By.Exchange_Del : By.Exchange_Buy;

        if(CfgMgr.equip.containsKey(itemid)) {
            final xbean.Equip equip = FEquip.createEquip(roleid, itemid, false);
            equip.getNormalequip().setAnneallevel(item.getAnneallevel());
            equip.getNormalequip().setPerfuselevel(item.getPerfuselevel());
            equip.getAccessory().getMainprop().clear();
            equip.getAccessory().getExtraprop().clear();
            //恢复原来的属性，放回背包里面去
            for (AccessoryProp prop : item.getAccessorymainprop()) {
                AccessoryProp add = Pod.newAccessoryProp();
                add.setKey(prop.getKey());
                add.setVal(prop.getVal());
                equip.getAccessory().getMainprop().add(add);
            }
            for (AccessoryProp prop : item.getAccessoryviceprop()) {
                AccessoryProp add = Pod.newAccessoryProp();
                add.setKey(prop.getKey());
                add.setVal(prop.getVal());
                equip.getAccessory().getExtraprop().add(add);
            }
            if (!FBag.addItemToBag(roleid, BagType.EQUIP, Arrays.asList(equip), by))
                return ErrorCode.BAG_FULL;
        } else {
            if(!FBag.addItemToBag(roleid, itemid, num, false, by))
                return ErrorCode.BAG_FULL;
        }
        return ErrorCode.OK;
    }


    public static synchronized SQuery query(CQuery msg) {
        final SQuery re = new SQuery();
        re.name = msg.name;
        re.orderby = msg.orderby;
        re.sortorder = msg.sortorder;
        re.category = msg.category;
        re.startindex = msg.startindex;
        final Groups g = ExchangeModule.group;
        if (!msg.name.isEmpty()) {
            g.genRecordsByName(msg.name, msg.orderby, msg.sortorder, msg.startindex, msg.endindex, re.queryresult);
        } else {
            g.genRecordsByCategory(msg.category, msg.orderby, msg.sortorder, msg.startindex, msg.endindex, re.queryresult);
        }

        return re;
    }

    public static synchronized void addItem(ExchangeItem ei) {
        ExchangeModule.group.addItem(ei);
    }

    public static synchronized Collection<Record> getItems() {
        return ExchangeModule.group.getItems();
    }

    public static synchronized xbean.ExchangeItem getItem(long id) {
        return ExchangeModule.group.getItem(id);
    }

    public static synchronized boolean removeItem(long id) {
        return ExchangeModule.group.removeItem(id);
    }


    public static boolean changeItem(xbean.ExchangeItem item) {
        return ExchangeModule.group.changeItem(item);
    }

    public static void gen(xbean.ExchangeItem item, long buyer, xbean.ExchangeLog log) {
        log.setSeller(item.getOwner());
        log.setBuyer(buyer);
        log.setTime(System.currentTimeMillis());
        final xbean.ExchangeItem dst = log.getItem();
        dst.setId(item.getId());
        dst.setModelid(item.getModelid());
        dst.setNum(item.getNum());
        dst.setOwner(item.getOwner());
        dst.setPrice(item.getPrice());
        dst.setExpiretime(item.getExpiretime());
        dst.setAnneallevel(item.getAnneallevel());
        dst.setPerfuselevel(item.getAnneallevel());
        for (AccessoryProp prop : item.getAccessorymainprop()) {
            AccessoryProp add = Pod.newAccessoryProp();
            add.setKey(prop.getKey());
            add.setVal(prop.getVal());
            dst.getAccessorymainprop().add(add);
        }
        for (AccessoryProp prop : item.getAccessoryviceprop()) {
            AccessoryProp add = Pod.newAccessoryProp();
            add.setKey(prop.getKey());
            add.setVal(prop.getVal());
            dst.getAccessoryviceprop().add(add);
        }
    }


    public static boolean delItem(long exchangeid, boolean unshelve) {
        final xbean.ExchangeItem item = xtable.Exchangeitem.select(exchangeid);
        if(item == null)
            return false;
        final long roleid = item.getOwner();
        final xbean.RoleExchange info = FExchange.get(roleid);
        if(!info.getItems().remove(exchangeid))
            return false;
        final ErrorCode err = FExchange.addExchangeItemToBag(roleid, exchangeid, -1);
        if(err.err())
            return false;
        if(unshelve)
            FMail.addMail(roleid, Const.UNSHELVE_MAILID, Arrays.asList(Integer.toString(item.getModelid())));
        xtable.Exchangeitem.delete(exchangeid);

        xdb.Transaction.tsendWhileCommit(roleid, new SDelItem(exchangeid));
        if(!FExchange.removeItem(exchangeid)) {
            xdb.Trace.error("impossible! delItem. remove ExchangeItem fail!. id:" + exchangeid);
            return false;
        }
        return true;
    }
}
