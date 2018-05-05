package lx.gs.exchange;

import cfg.CfgMgr;
import cfg.equip.MainEquip;
import cfg.exchange.Category;
import cfg.item.EItemType;
import cfg.talisman.TalismanBasic;
import lx.gs.exchange.msg.QueryResult;

import java.util.*;

public final class Groups {
    private final HashMap<Long, Record> allItems = new HashMap<>();
    private final HashMap<String, OrderByRecords> nameGroups = new HashMap<>(); // name ->物品的索引
    private final HashMap<Integer, OrderByRecords> categoryGroups = new HashMap<>(); // 类别 -> 物品

    private final HashMap<Integer, Classification> classfications = new HashMap<>(); // itemid -> Category

    public Groups() {
        for (int i = 0; i < cfg.exchange.Category.TYPE_NUM; i++) {
            categoryGroups.put(i, new OrderByRecords());
        }
    }

    void genRecordsByName(String name, int orderByType, int sortOrder, int startIndex, int endIndex, QueryResult re) {
        final OrderByRecords g = nameGroups.get(name);
        if (g != null) {
            g.genRecords(orderByType, sortOrder, startIndex, endIndex, re);
        }
    }

    void genRecordsByCategory(int category, int orderByType, int sortOrder, int startIndex, int endIndex, QueryResult re) {
        final OrderByRecords g = categoryGroups.get(category);
        if (g != null) {
            g.genRecords(orderByType, sortOrder, startIndex, endIndex, re);
        }
    }

    public Collection<Record> getItems() {
        return new ArrayList<>(allItems.values());
    }

    xbean.ExchangeItem getItem(long id) {
        final Record r = allItems.get(id);
        return r != null ? r.item : null;
    }

    void addItem(xbean.ExchangeItem item) {
        final Record r = new Record(item);
        final long id = item.getId();
        if (allItems.put(id, r) != null) {
            throw new RuntimeException("duplication ExchangeItem. id:" + id);
        }
        final Classification c = getClassification(item.getModelid());

        for (Integer category : c.categorys) {
            categoryGroups.get(category).add(r);
        }

        for (String nameIndex : c.nameIndexs) {
            OrderByRecords g = nameGroups.get(nameIndex);
            if (g == null) {
                g = new OrderByRecords();
                nameGroups.put(nameIndex, g);
            }
            g.add(r);
        }
    }

    boolean removeItem(long id) {
        final Record r = allItems.remove(id);
        if (r == null) {
            return false;
        }

        final Classification c = getClassification(r.item.getModelid());

        for (Integer category : c.categorys) {
            categoryGroups.get(category).remove(r);
        }

        for (String nameIndex : c.nameIndexs) {
            nameGroups.get(nameIndex).remove(r);
        }
        return true;
    }

    boolean changeItem(xbean.ExchangeItem item) {
        if (!removeItem(item.getId()))
            return false;
        addItem(item);
        return true;
    }

    public static final class Classification {
        public final ArrayList<String> nameIndexs = new ArrayList<>();
        public final ArrayList<Integer> categorys = new ArrayList<>();
    }

    public Classification getClassification(int itemid) {
        Classification c = classfications.get(itemid);
        if (c == null) {
            c = createClassification(itemid);
            classfications.put(itemid, c);
        }
        return c;
    }

    private Classification createClassification(int itemid) {
        final Classification c = new Classification();

        final ArrayList<String> nameIndexs = c.nameIndexs;
        final ArrayList<Integer> categorys = c.categorys;
        {
            cfg.item.ItemBasic b = CfgMgr.itembasic.get(itemid);
            if (b != null) {
                genIndexs(b.name, nameIndexs);
                categorys.add(cfg.exchange.Category.MATERIAL);
                return c;
            }
        }
        {
            cfg.equip.Fragment f = CfgMgr.fragment.get(itemid);
            if (f != null) {
                genIndexs(f.name, nameIndexs);
                categorys.add(cfg.exchange.Category.MATERIAL);
                return c;
            }
        }
        {
            TalismanBasic talisman = CfgMgr.talismanbasic.get(itemid);
            if (talisman != null) {
                genIndexs(talisman.name, nameIndexs);
                categorys.add(Category.TALISMAN);
                return c;
            }
        }
        {
            cfg.equip.Equip e = CfgMgr.equip.get(itemid);
            if (e != null) {
                genIndexs(e.name, nameIndexs);
                switch (e.type) {
                    case EItemType.BANGLE: {
                        categorys.add(cfg.exchange.Category.ACCESSORY);
                        categorys.add(cfg.exchange.Category.BANGLE);
                        break;
                    }
                    case EItemType.NECKLACE: {
                        categorys.add(cfg.exchange.Category.ACCESSORY);
                        categorys.add(cfg.exchange.Category.NECKLACE);
                        break;
                    }
                    case EItemType.RING: {
                        categorys.add(cfg.exchange.Category.ACCESSORY);
                        categorys.add(cfg.exchange.Category.RING);
                        break;
                    }
                    case EItemType.WEAPON: {
                        categorys.add(cfg.exchange.Category.WEAPON);
                        final int profession = ((MainEquip) e).professionlimit.profession;
                        switch (profession) {
                            case cfg.role.EProfessionType.QINGYUNMEN: {
                                categorys.add(cfg.exchange.Category.QINGYUNMENG_WEAPON);
                                break;
                            }
                            case cfg.role.EProfessionType.GUIWANGZONG: {
                                categorys.add(cfg.exchange.Category.GUIWANGZONG_WEAPON);
                                break;
                            }
                            case cfg.role.EProfessionType.TIANYINSI: {
                                categorys.add(cfg.exchange.Category.TIANYINSI_WEAPON);
                                break;
                            }
                            default:
                                throw new RuntimeException("unknow profession:" + profession);
                        }
                        break;
                    }
                    case EItemType.CLOTH: {
                        categorys.add(cfg.exchange.Category.CLOTH);
                        final int profession = ((MainEquip) e).professionlimit.profession;
                        switch (profession) {
                            case cfg.role.EProfessionType.QINGYUNMEN: {
                                categorys.add(cfg.exchange.Category.QINGYUNMENG_CLOTH);
                                break;
                            }
                            case cfg.role.EProfessionType.GUIWANGZONG: {
                                categorys.add(cfg.exchange.Category.GUIWANGZONG_CLOTH);
                                break;
                            }
                            case cfg.role.EProfessionType.TIANYINSI: {
                                categorys.add(cfg.exchange.Category.TIANYINSI_CLOTH);
                                break;
                            }
                            default:
                                throw new RuntimeException("unknow profession:" + profession);
                        }
                        break;
                    }
                    case EItemType.HAT: {
                        categorys.add(cfg.exchange.Category.HAT);
                        final int profession = ((MainEquip) e).professionlimit.profession;
                        switch (profession) {
                            case cfg.role.EProfessionType.QINGYUNMEN: {
                                categorys.add(cfg.exchange.Category.QINGYUNMENG_HAT);
                                break;
                            }
                            case cfg.role.EProfessionType.GUIWANGZONG: {
                                categorys.add(cfg.exchange.Category.GUIWANGZONG_HAT);
                                break;
                            }
                            case cfg.role.EProfessionType.TIANYINSI: {
                                categorys.add(cfg.exchange.Category.TIANYINSI_HAT);
                                break;
                            }
                            default:
                                throw new RuntimeException("unknow profession:" + profession);
                        }
                        break;
                    }
                    case EItemType.SHOE: {
                        categorys.add(cfg.exchange.Category.SHOE);
                        final int profession = ((MainEquip) e).professionlimit.profession;
                        switch (profession) {
                            case cfg.role.EProfessionType.QINGYUNMEN: {
                                categorys.add(cfg.exchange.Category.QINGYUNMENG_SHOE);
                                break;
                            }
                            case cfg.role.EProfessionType.GUIWANGZONG: {
                                categorys.add(cfg.exchange.Category.GUIWANGZONG_SHOE);
                                break;
                            }
                            case cfg.role.EProfessionType.TIANYINSI: {
                                categorys.add(cfg.exchange.Category.TIANYINSI_SHOE);
                                break;
                            }
                            default:
                                throw new RuntimeException("unknow profession:" + profession);
                        }
                        break;
                    }
                }
                return c;
            }
        }
        throw new RuntimeException("unknow itemid:" + itemid);
    }

    public static void genIndexs(String name, ArrayList<String> indexs) {
        final int n = name.length();
        final HashSet<String> idxs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                idxs.add(name.substring(i, j));
            }
        }
        indexs.addAll(idxs);
    }

    public static void main(String[] args) {
        final ArrayList<String> index = new ArrayList<>();
        genIndexs("江南江南好", index);
        System.out.println(index);
    }

}
