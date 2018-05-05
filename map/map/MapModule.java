package map;

import cfg.CfgMgr;
import cfg.character.ModelType;
import common.*;
import map.map.GameMap;
import map.map.MapMgr;
import pathfinding.Mesh;
import pathfinding.NavGraph;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by HuangQiang on 2016/5/26.
 */
public enum MapModule {
    INSTANCE;

    public static final HashMap<String, NavGraph> navGraphs = new HashMap<>();
    public static final HashMap<Integer, Integer> skillid2BaseEvolveSkillid = new HashMap<>();

    public static cfg.skill.ModelActions FABAO_MODEL_ACTIONS;
    public static int[][] campRelations;

    public static void prepareConf() {
        FABAO_MODEL_ACTIONS = CfgMgr.modelactions.get(CfgMgr.model.values().stream()
                .filter(m -> m.modeltype == ModelType.Talisman).findFirst().get().modelname);

        final HashSet<Integer> baseEvolveSkillids = new HashSet<>(CfgMgr.skilllvlupcost.keySet());
        // 计算所有技能的基础进阶等级技能
        for(cfg.skill.SkillLvlupCost scfg : CfgMgr.skilllvlupcost.values()) {
            if(scfg.nextskillid > 0)
                baseEvolveSkillids.remove(scfg.nextskillid);
        }

        for(int baseid : baseEvolveSkillids) {
            int curSkillid = baseid;
            do {
                skillid2BaseEvolveSkillid.put(curSkillid, baseid);
                curSkillid = CfgMgr.skilllvlupcost.get(curSkillid).nextskillid;
            } while(curSkillid > 0);
        }

        // 将技能里action按时间排序
        CfgMgr.modelactions.values().forEach(skill -> {
            for(cfg.skill.SkillAction sa : skill.skillactions.values()) {
                Collections.sort(sa.actions, (a, b) -> (int)((a.timeline - b.timeline) * 1000));
            }

        });

        final int campSize = CfgMgr.camprelation.size();
        campRelations = new int[campSize][];
        for(int i = 0 ; i < campSize ; i++) {
            final int[] relations = new int[campSize];
            final List<Integer> crealtions = CfgMgr.camprelation.get(i).relations;
            campRelations[i] = relations;
            for(int j = 0 ; j < campSize ; j++) {
                relations[j] = crealtions.get(j);
            }
        }
    }

    public void start() {
        prepareConf();
        MapMgr.Ins.start();
    }
}
