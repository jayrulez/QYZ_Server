package lx.gs.talisman;

import cfg.CfgMgr;
import cfg.bag.BagType;
import gs.LevelUpListener;
import gs.RoleAttrListener;
import xbean.RoleTalismanBag;
import xdb.Procedure;
import xdb.Transaction;
import xdb.logs.Note;

public enum TalismanModule implements gs.Module, gs.RoleLoginListener, RoleAttrListener, LevelUpListener {
    INSTANCE;

    double totalLuckProbability;

    static final long[] normalLevelNeedTotalExp = new long[FTalisman.TALISMAN_NORMAL_MAX_LEVEL + 1];
    static final  int[] starLevelNeedTotalExp = new int[FTalisman.TALISMAN_STAR_MAX_LEVEL + 1];

    @Override
    public void afterRoleLoginInProcedure(long roleid) {
        RoleTalismanBag talismanBag = FTalisman.getDbBag(roleid);
        SLuckyInfo sLuckyInfo = new SLuckyInfo();
        sLuckyInfo.luckytype = talismanBag.getLuckytype();
        sLuckyInfo.washcount = talismanBag.getLuckywashtimes();
        Transaction.tsendWhileCommit(roleid, sLuckyInfo);

        //FTalisman.syncToMap(roleid);
    }

    @Override
    public void beforeRoleLogoutInProcedure(long roleid) {
    }

    @Override
    public void start() {
        totalLuckProbability = CfgMgr.talismanfeed.values().stream().mapToDouble(f -> f.probability).sum();

        long normalExp = 0;
        normalLevelNeedTotalExp[0] = 0;
        normalLevelNeedTotalExp[1] = 0;
        for (int level = 2; level <= FTalisman.TALISMAN_NORMAL_MAX_LEVEL; level++) {
            normalExp += CfgMgr.talismanexp.get(level - 1).requireexp;
            normalLevelNeedTotalExp[level] = normalExp;
        }

        int starExp = 0;
        starLevelNeedTotalExp[0] = 0;
        starLevelNeedTotalExp[1] = 0;
        for (int level = 2; level <= FTalisman.TALISMAN_STAR_MAX_LEVEL; level++) {
            starExp += CfgMgr.talismanevlove.get(level - 1).requireexp;
            starLevelNeedTotalExp[level] = starExp;
        }
        registerListeners();
    }

    private void registerListeners() {
        xtable.Roletalismanbag.getTable().addListener(new xdb.logs.Listener() {
            @Override
            public void onChanged(Object key) {
            }

            @Override
            public void onRemoved(Object key) {
            }

            @Override
            public void onChanged(Object key, String fullVarName, Note note) {
                new Procedure() {
                    @Override
                    protected boolean process() {
                        FTalisman.onEquipTalismanChange((Long) key);
                        return true;
                    }
                }.execute();
            }
        }, "value", "equipedtalismans");
    }


    @Override
    public void updateRoleAttr(long roleid) {
        FTalisman.syncProp(roleid);
    }


    @Override
    public void onLevelUp(long roleid, int oldLevel, int newLevel) {
        FTalisman.getDbBag(roleid).getTalismans().values().forEach(talisman -> {
            int oldStarLevel = talisman.getStarlevel();
            FTalisman.addStarExp(roleid, talisman, 0, BagType.TALISMAN);
            int newStarLevel = talisman.getStarlevel();
            if(newStarLevel != oldStarLevel){
                final SAddStarExp ret = new SAddStarExp();
                ret.bagtype = BagType.TALISMAN;
                ret.pos = talisman.getPos();
                ret.newlevel = talisman.getStarlevel();
                ret.newexp = talisman.getStarexp();
                Transaction.tsendWhileCommit(roleid, ret);
            }


            int oldTalismanLevel = talisman.getNormallevel();
            FTalisman.addNormalExp(roleid, talisman, 0, BagType.TALISMAN);
            int newTalismanLevel = talisman.getNormallevel();
            if(oldTalismanLevel != newTalismanLevel){
                final SAddNormalExp ret = new SAddNormalExp();
                ret.bagtype = BagType.TALISMAN;
                ret.pos = talisman.getPos();
                ret.newlevel = talisman.getNormallevel();
                ret.newexp = talisman.getNormalexp();
                Transaction.tsendWhileCommit(roleid, ret);
            }
        });

        FTalisman.getDbBag(roleid).getEquipedtalismans().values().forEach(talisman -> {
            int oldStarLevel = talisman.getStarlevel();
            FTalisman.addStarExp(roleid, talisman, 0, BagType.TALISMAN_BODY);
            int newStarLevel = talisman.getStarlevel();
            if(newStarLevel != oldStarLevel){
                final SAddStarExp ret = new SAddStarExp();
                ret.bagtype = BagType.TALISMAN_BODY;
                ret.pos = talisman.getPos();
                ret.newlevel = talisman.getStarlevel();
                ret.newexp = talisman.getStarexp();
                Transaction.tsendWhileCommit(roleid, ret);
            }


            int oldTalismanLevel = talisman.getNormallevel();
            FTalisman.addNormalExp(roleid, talisman, 0, BagType.TALISMAN_BODY);
            int newTalismanLevel = talisman.getNormallevel();
            if(oldTalismanLevel != newTalismanLevel){
                final SAddNormalExp ret = new SAddNormalExp();
                ret.bagtype = BagType.TALISMAN_BODY;
                ret.pos = talisman.getPos();
                ret.newlevel = talisman.getNormallevel();
                ret.newexp = talisman.getNormalexp();
                Transaction.tsendWhileCommit(roleid, ret);
            }
        });
    }
}
