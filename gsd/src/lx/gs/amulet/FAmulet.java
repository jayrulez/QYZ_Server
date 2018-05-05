package lx.gs.amulet;

import cfg.CfgMgr;
import cfg.Const;
import cfg.equip.AmuletConfig;
import common.ErrorCode;
import gs.Utils;
import lx.gs.equip.EquipModule;
import lx.gs.skill.FSkill;
import xdb.Transaction;
import xtable.Roleinfos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 护符
 *
 * @author Jin Shuai
 */
public class FAmulet {

    /**
     * 检查护符操作的合法性
     *
     * @param amuletInfo
     * @param pageId
     * @return
     */
    public static ErrorCode checkOperation(xbean.RoleAmuletInfo amuletInfo, int pageId) {
        if (pageId <= 0 || pageId > AmuletConfig.AMULET_PAGE_COUNT) {
            return ErrorCode.PARAM_ERROR;
        }
        return amuletInfo.getPagemap().get(pageId) == null
                ? ErrorCode.AMULET_PAGE_LOCKED
                : ErrorCode.OK;
    }

    public static HashMap<Integer, Integer> getPrfsSkillAddInfo(long roleId){
        HashMap<Integer, Integer> skillAddMap = new HashMap<>();
        int professionId = Roleinfos.selectProfession(roleId);
        get(roleId).getPagemap().values().forEach(amuletPage ->
                amuletPage.getPropmap().values().stream()
                        .filter(p -> p.getProfessionid() == professionId)
                        .forEach(p -> common.Utils.addValue(skillAddMap, p.getSkillid(), p.getAddlevel())));
        return skillAddMap;
    }

    /**
     * 登录时做等级检查
     * @param roleid
     */
    public static void onRoleLogin(long roleid) {
        onRoleLevelUp(roleid, Roleinfos.selectLevel(roleid));
        Transaction.tsendWhileCommit(roleid, new SGetAmuletInfo(convert(get(roleid))));
    }

    /**
     * 升级时检查是否可以开启新的护符页面
     * @param roleId
     * @param newlevel
     */
    public static void onRoleLevelUp(long roleId, int newlevel) {
        AmuletConfig conf = cfg.CfgMgr.amuletconfig;
        Map<Integer, xbean.AmuletPage> amuletPageMap = get(roleId).getPagemap();
        for (int i = 0; i < AmuletConfig.AMULET_PAGE_COUNT; i++) {
            int pageIndex = i + 1;
            if (newlevel >= conf.expandlevel.get(i) && !amuletPageMap.containsKey(pageIndex)) {
                xbean.AmuletPage page = xbean.Pod.newAmuletPage();
                page.setPageindex(pageIndex);
                initAmuletPage(page);
                washAmulet(roleId, page);
                applyWashResult(page);
                amuletPageMap.put(page.getPageindex(), page);

                Transaction.tsendWhileCommit(roleId, new SAmuletPageOpenNotify(pageIndex));
            }
        }
    }

    /**
     * 获取护符信息
     *
     * @param roleId
     * @return
     */
    public static xbean.RoleAmuletInfo get(long roleId) {
        return xtable.Amulet.get(roleId);
    }

    /**
     * 初始化护符页
     *
     * @param page
     */
    private static void initAmuletPage(xbean.AmuletPage page) {
        Map<Integer, xbean.AmuletProperty> propMap = page.getPropmap();
        for (int i = 1; i <= AmuletConfig.AMULET_PROP_COUNT_PER_PAGE; i++) {
            xbean.AmuletProperty prop = xbean.Pod.newAmuletProperty();
            prop.setPropindex(i);
            prop.setIslock(Const.FALSE);
            propMap.put(prop.getPropindex(), prop);
        }
    }

    /**
     * 从选择列表中随机选择count个技能
     *
     * @return
     */
    private static List<xbean.AmuletProperty> getRandomSkills(int count, List<Integer> skillChoiceList) {
        List<xbean.AmuletProperty> result = new ArrayList<>();
        for (int i : common.Utils.getMutiRandom(0, skillChoiceList.size(), count)) {
            int skillid = skillChoiceList.get(i);
            xbean.AmuletProperty prop = xbean.Pod.newAmuletProperty();
            prop.setSkillid(skillid);
            prop.setProfessionid(FSkill.getPrfsIdBySkillId(skillid));
            prop.setIslock(Const.FALSE);
            prop.setAddlevel(FAmulet.getRandomSkillAddLevel(skillid));
            result.add(prop);
        }
        return result;
    }

    /**
     * 洗练护符
     *
     * @param roleid
     * @param p
     */
    public static void washAmulet(long roleid, xbean.AmuletPage p) {
        int prfsId = Roleinfos.selectProfession(roleid);
        // 本门派技能选择列表
        List<Integer> prfsSkillChoiceList = EquipModule.INSTANCE.getPrfsWashSkills(prfsId);
        // 其他门派技能选择列表
        List<Integer> otherPrfsSkillChoiceList = EquipModule.INSTANCE.getOtherPrfsWashSkills(prfsId);
        //随机选出本门派应该出多少条技能
        int prfsSkillCount = common.Utils.getRandomIndex(CfgMgr.amuletwash.probablilty);
        //各属性是否参与洗练,属性index从1开始，所以这里的数组长度为属性条数+1
        boolean[] isWash = new boolean[AmuletConfig.AMULET_PROP_COUNT_PER_PAGE + 1];
        //参与洗练的属性条数
        int washCount = AmuletConfig.AMULET_PROP_COUNT_PER_PAGE;

        //属性map
        Map<Integer, xbean.AmuletProperty> proppertyMap = p.getPropmap();
        //上一次洗练结果
        Map<Integer, xbean.AmuletProperty> lastwashresult = p.getLastwashresult();

        for (xbean.AmuletProperty amuletProp : proppertyMap.values()) {
            int propIndex = amuletProp.getPropindex();
            isWash[propIndex] = true;
            if (amuletProp.getIslock() == Const.TRUE) {
                isWash[propIndex] = false;
                washCount--;
                int skillId = amuletProp.getSkillid();
                if (prfsSkillChoiceList.contains(skillId)) {
                    prfsSkillChoiceList.remove(Integer.valueOf(skillId));
                    prfsSkillCount--;
                } else {
                    otherPrfsSkillChoiceList.remove(Integer.valueOf(skillId));
                }
            }
        }

        prfsSkillCount = prfsSkillCount < 0 ? 0 : prfsSkillCount < washCount ? prfsSkillCount : washCount;
        //其他门派的技能数量
        int otherPrfsCount = washCount - prfsSkillCount;

        //随机选出prfsSkillCount个本门派技能，不能重复
        List<xbean.AmuletProperty> randomPrfsSkills = getRandomSkills(prfsSkillCount, prfsSkillChoiceList);
        List<xbean.AmuletProperty> randomOtherPrfsSkills = getRandomSkills(otherPrfsCount, otherPrfsSkillChoiceList);

        //保存随机出的结果
        List<xbean.AmuletProperty> allSkills = new ArrayList<>(randomPrfsSkills);
        allSkills.addAll(randomOtherPrfsSkills);

        lastwashresult.clear();
        for (int i = 1; i <= AmuletConfig.AMULET_PROP_COUNT_PER_PAGE; i++) {
            xbean.AmuletProperty prop = proppertyMap.get(i).copy();
            if (isWash[i]) {
                prop = allSkills.remove(common.Utils.random().nextInt(allSkills.size()));
                prop.setPropindex(i);
            }
            lastwashresult.put(i, prop);
        }
    }

    /**
     * 计算每个技能应该+的数值，根据权重计算
     *
     * @param skillid
     * @return
     */
    public static int getRandomSkillAddLevel(int skillid) {
        cfg.equip.AmuletSkillLvl sconf = cfg.CfgMgr.amuletskilllvl.get(skillid);
        if(sconf == null){
            return 1;
        }
        //技能增加的值等于索引值+1
        return common.Utils.getRandomIndex(sconf.weightlist) + 1;
    }

    /**
     * 用洗练结果替换
     *
     * @param p
     */
    public static boolean applyWashResult(xbean.AmuletPage p) {
        Map<Integer, xbean.AmuletProperty> lastwashresult = p.getLastwashresult();
        if (Utils.isNotNull(lastwashresult)) {
            Map<Integer, xbean.AmuletProperty> propMap = p.getPropmap();
            propMap.clear();
            lastwashresult.values().forEach(prop -> propMap.put(prop.getPropindex(), prop.copy()));
            lastwashresult.clear();
            return true;
        }
        return false;
    }

    /**
     * 取消洗练结果
     *
     * @param p
     */
    public static void cancelWashResult(xbean.AmuletPage p) {
        Map<Integer, xbean.AmuletProperty> lastwashresult = p.getLastwashresult();
        if (Utils.isNotNull(lastwashresult)) {
            lastwashresult.clear();
        }
    }

    public static lx.gs.amulet.AmuletPropperty convert(xbean.AmuletProperty p) {
        lx.gs.amulet.AmuletPropperty result = new lx.gs.amulet.AmuletPropperty();
        result.propindex = p.getPropindex();
        result.islock = p.getIslock();
        result.skillid = p.getSkillid();
        result.professionid = p.getProfessionid();
        result.addlevel = p.getAddlevel();
        return result;
    }

    public static lx.gs.amulet.AmuletPage convert(xbean.AmuletPage p) {
        lx.gs.amulet.AmuletPage result = new lx.gs.amulet.AmuletPage();
        result.pageindex = p.getPageindex();
        p.getPropmap().values().forEach(prop -> result.propmap.put(prop.getPropindex(), convert(prop)));
        return result;
    }

    public static lx.gs.amulet.RoleAmuletInfo convert(xbean.RoleAmuletInfo p) {
        lx.gs.amulet.RoleAmuletInfo result = new lx.gs.amulet.RoleAmuletInfo();
        p.getPagemap().values().forEach(page -> result.pagemap.put(page.getPageindex(), convert(page)));
        return result;
    }

}
