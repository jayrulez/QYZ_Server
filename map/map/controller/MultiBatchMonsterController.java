package map.controller;

import map.map.GameMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by HuangQiang on 2016/5/6.
 */
public class MultiBatchMonsterController {

//
//    public static class BatchMonsterInfo {
//        public int index;
//        public boolean isboss;
//        public int delaySeconds;
//        public Map<Integer, Integer> monsters;
//        public int regionid;
//        public int ignoreRegionid;
//        public cfg.map.PatrolInfo patrolInfo;
//        GameMap.OnCreateMonster onCreateMonster;
//        GameMap.OnBatchEnd onBatchEnd;
//    }
//
//    private final GameMap gamemap;
//    private final List<BatchMonsterInfo> batches;
//    private final GameMap.OnCreateMonster onCreateMonster;
//    private final GameMap.OnBatchEnd onBatchEnd;
//    private int index;
//    public MultiBatchMonsterController(GameMap gamemap, List<BatchMonsterInfo> batches, GameMap.OnCreateMonster onCreateMonster) {
//        this(gamemap, batches, onCreateMonster, null);
//    }
//
//    public MultiBatchMonsterController(GameMap gamemap, List<BatchMonsterInfo> batches, GameMap.OnCreateMonster onCreateMonster, GameMap.OnBatchEnd onBatchEnd) {
//        this.gamemap = gamemap;
//        this.batches = batches;
//        this.onCreateMonster = onCreateMonster;
//        this.onBatchEnd = onBatchEnd;
//        this.index = -1;
//    }
//
//    public MultiBatchMonsterController(GameMap gamemap, List<BatchMonsterInfo> batches) {
//        this(gamemap, batches, null);
//    }
//
//    public static List<BatchMonsterInfo> convert(List<cfg.ectype.MonsterInfo> batches, int delaySeconds, int ignoreRegionid) {
//        return batches.stream().map(b -> {
//            final BatchMonsterInfo bi = new BatchMonsterInfo();
//            bi.delaySeconds = delaySeconds;
//            bi.regionid = b.regionid;
//            bi.ignoreRegionid = ignoreRegionid;
//            bi.isboss = b.isboss;
//            bi.monsters = b.monsters;
//            bi.patrolInfo = b.patrol;
//            return bi;
//        }).collect(Collectors.toList());
//    }
//
//    public void start() {
//        next();
//    }
//
//    private void next() {
//        if(++index < batches.size()) {
//            final BatchMonsterInfo info = batches.get(index);
//            gamemap.schedule(() -> {
//                createMonsters(info);
//            }, info.delaySeconds * 1000L);
//        } else {
//            onBatchEnd.process();
//        }
//    }
//
//    private void createMonsters(BatchMonsterInfo info) {
//        gamemap.createMonsters(info.monsters, info.regionid, info.ignoreRegionid, info.patrolInfo, onCreateMonster, () -> {
//            next();
//        });
//    }
}
