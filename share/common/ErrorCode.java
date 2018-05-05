package common;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;

public enum ErrorCode {
    //通用
    OK(0,""),
    TODO(1, "待填"),
    INTERNAL_ERR(2, "异常"),

    CURRENCY_IS_NOT_ENOUGH(100,"金钱不够"),
    BAG_FULL(101,"背包位置不够"),
    PARAM_ERROR(102,"参数错误"),
    ITEM_NOT_FOUND(103,"物品未找到"),
    LEVEL_TOO_HIGH(104,""),
    ROLE_NOT_EXISTED_OR_NOT_ONLINE(105,"角色信息不存在或者用户已下线"),
    HAS_RECEIVED(106,"已经领取过该奖励"),
    NOT_ENOUGH_LEVEL(107,"等级不够"),
    NOT_ENOUGH_SCORES(108,"积分不够"),
    YUANBAO_NOT_ENOUGH(109,"元宝不足"),
    XUNIBI_NOT_ENOUGH(110,"金币不足"),
    EXCEED_LIMIT(111, "超出最大次数"),
    NOT_ENOUGH_VIP_LEVEL(112, "VIP等级不足"),
    COOL_DOWN(113, "冷却未完成"),
    TIME_NOT_YET(114, "时间未到"),
    COMBATPOWER_NOT_ENOUGH(115, "战力不足"),
    OPERATION_LATER(116, "操作过于频繁，请稍后再试"),
    KILL_AMOUNT_NOT_ENOUGH(117,"击败怪物数量不足"),

    BINDYUANBAO_NOT_ENOUGH(118,"绑定元宝不足"),
    LINGJING_NOT_ENOUGH(119,"灵晶不足"),
    JINGYAN_NOT_ENOUGH(120,"经验不足"),
    ZAOHUA_NOT_ENOUGH(121,"造化值不足"),
    WUXING_NOT_ENOUGH(122,"悟性值不足"),
    BANGPAI_NOT_ENOUGH(123,"帮派贡献不足"),
    SHIMEN_NOT_ENOUGH(124,"师门贡献不足"),
    ZHANCHANG_NOT_ENOUGH(125,"战场声望不足"),
    SHENGWANG_NOT_ENOUGH(126,"竞技场声望不足"),
    HUOBANJIFEN_NOT_ENOUGH(127,"伙伴积分不足"),
    FABAOJIFEN_NOT_ENOUGH(128,"法宝积分不足"),
    CHENGJIU_NOT_ENOUGH(129,"成就值不足"),
    BANGGONG_NOT_ENOUGH(130,"帮贡不足"),
    TIANFU_NOT_ENOUGH(131,"天赋不足"),
    CHONGZHIJIFEN_NOT_ENOUGH(132,"充值积分不足"),


    //角色
    ROLE_NOT_FOUND(200,"角色未找到"),
    OVER_ALCHEMY_LIMIT(201,"今日点石成金次数已用完"),
    OVER_VITALITY_LIMIT(202,"今日购买体力次数已用完"),
    OVER_REAL_VITALITY_CAPACITY(203,"超过体力最大上限，无需购买体力了"),
    ROLE_EQUIP_INFO_NOT_FOUND(204,"角色的装备信息未找到"),
    PROFESSION_NOT_MATCH(205, "职业不符合"),
    GENDER_NOT_MATCH(206, "性别不符合"),
    MAX_ROLE_LEVEL(207, "已达到最大角色等级"),
    TILI_FULL(208, "体力已满"),
    TILI_NOT_ENOUGH(209,"体力不够"),

    //地图
    LINE_NOT_EXIST(300,"没有对应的分线"),
    LINE_FULL(301,"分线玩家已满"),
    MAP_NOT_EXIST(302,"没有此地图"),
    LINE_NOT_AVAILABLE(303,"没有可用的线"),
    AGENT_NOT_FIND(304,"没有找到对象"),
    ECTYPE_MAP_NOT_EXIST(305,"没有此副本"),
    CANNOT_ENTER_ECTYPE_IN_ECTYPE(306,"已经在副本中,无法进入另一个副本"),
    INVALID_ECTYPEID(307,"无效的副本id"),
    OUT_OFF_DIG_DISTANCE(308,"超出采集范围"),
    DIGGING_BY_OTHER(309,"正被他人采集中"),
    CANNOT_DIG(310,"无法采集"),
    DIGGING_NOT_FINISH(311,"采集未完成"),
    DIG_REQUIRE_TASK(312,"需要任务才能采集"),
    MINE_IN_PROTECTED(313,"保护期"),
    DIG_REQUIRE_BUFF(314,"需要buff才能采集"),
    CANNOT_LEAVE_ECTYPE_IN_OTHER_MAP(315,"当前不在副本地图"),
    ENTER_CUR_MAP(316, "已经在此地图"),
    MAP_IS_FULL(317, "地图人数已满"),
    NOT_IN_DIGGING(318, "不在采集中"),
    CAN_NOT_PICK_OTHER_PLAYER_ITEM(319, "不能捡别人的物品"),
    CAN_NOT_PICK_SHOW_ITEM(320, "掉落展示期不能捡"),
    NOT_IN_ECTYPE(321, "当前不在副本中"),
    CANNOT_ENTER_WORLD_IN_ECTYPE(322, "在副本中无法切换地图"),
    STORY_ECTYPE_NOT_FINISH_PREV(323, "需要先完成之前章节的副本"),
    UNKNOWN_DAILY_ECTYPE_TYPE(324, "未知的日常副本类型"),
    MAX_TOWER_FLOOR(325, "达到最大爬塔层数"),
    MAX_CUR_LEVEL_TOWER_FLOOR(326, "达到当前等级最大爬塔层数"),
    BASE_DESTROY(327, "基地被摧毁"),
    ECTYPE_TIMEOUT(328, "副本已到结束时间"),
    MAX_REVIVE_NUM(329, "超出最大复活次数"),
    HAS_OBTAIN_CHAPTER_REWARD(330, "已经领过奖"),
    CHAPTER_STAR_NOT_ENOUGH(331, "星数不够,无法领奖"),
    STORY_ECTYPE_SWEEP_NEED_3_STAR(332, "只有三星才能扫荡"),
    STORY_ECTYPE_SWEEP_NEED_CLEAR(333, "只有能关才能扫荡"),
    NPC_NOT_OPEN(334, "npc未开启"),
    FAR_FROM_NPC(335, "离npc太远"),
    ECTYPE_PLAYER_LEAVE(336, "玩家中途退出"),
    ECTYPE_PLAYER_DEAD(337, "玩家死亡"),
    ECTYPE_MAX_DEAD_COUNT(338, "达到最大死亡次数"),
    NOT_NEED_RESET_OPEN_COUNT(339, "无需重置日常副本完成次数"),
    ECTYPE_PLAYER_LOGOUT(340, "玩家下线"),
    ECTYPE_PLAYER_EXIT(341, "玩家中止副本"),
    MAP_NOT_READY(342, "地图正在初始化"),
    PLAYER_NOT_IN_MAP(343, "玩家还未进入地图,无法离开"),
    NOT_ADMIT_ENTER(344, "无权进入此地图"),
    HAS_IN_ENROLL_MULTI_STORY(345,"已经在匹配中多人挑战中"),
    HAS_EXCEED_MAX_ENROLL_NUMS(346,"超过最大匹配人数"),
    PRE_MULTI_STORY_NOT_COMPLETE(347,"还有前置关卡未完成"),
    HAS_OBTAIN_TEAM_FIGHT_DAY_REWARD(348, "已经领过奖励"),
    TODAY_WIN_NUM_NOT_ENOUGH(349, "未达到领奖所需胜利次数"),
    HAS_OBTAIN_TEAM_FIGHT_WEEK_REWARD(350, "已经领过奖励"),
    WEEK_SCORE_NOT_ENOUGH(351, "未达到领奖所需积分"),
    ALL_PLAYER_HAS_LEAVE(352,"所有玩家都已离开"),
    CAN_NOT_LEAVE_MULTISTORY(353,"不能离开多人剧情副本"),
    ONE_TEAM_PLAYERS_ALL_LEAVE(354, "某队玩家都离开了副本"),
    YOU_COME_LATER(356,"你来迟了"),
    TEAM_FIGHT_CLOSED(357, "现在不是活动进行的时间"),
    ATTACK_CITY_NOT_OPEN(358, "活动未开启"),
    ATTACK_CITY_SIGNUP_NOT_BEGIN(359, "报名未开始"),
    TEAMSPEED_NOT_APPLY_TIME(360, "当前不是报名时间"),
    TEAMSPEED_MEMBER_CAN_NOT_APPLY(361, "队员无法报名"),
    TEAMSPEED_MEMBERS_ERROR(362, "队伍人数错误"),
    TEAMSPEED_MEMBERS_NOT_IN_SAME_SCOPE(363, "队员不在同一等级段"),
    TEAMSPEED_NOT_LEADER(364, "不是队长"),
    TEAMSPEED_NOT_APPLY(365, "没有报名"),
    LEVEL_NOT_ENOUGH_CAN_NOT_ENTER_WORLD(366, "等级太低无法进入地图"),
    TEAMSPEED_CAN_NOT_MATCH_AFTER_CANCEL(367, "取消报名后此轮无法再报名"),
    TEAMSPEED_ALREADY_APPLY(368, "已经报名了"),
    CLIMBTOWER_FLOOR_IS_ZERO(369, "当前塔层数为0,无法扫荡"),
    CLOSE_MAP_KICK_PLAYER(370, "关闭地图时踢出玩家"),
    FAMILY_TEAM_NOT_INT_TEAM(371, "必须组队才能报名"),
    FAMILY_TEAM_MEMBER_NOT_ENOUGH(372, "队伍人数不足"),
    FAMILY_TEAM_NOT_INT_FAMLIY(373, "队伍成员必须都已加入家族"),
    FAMILY_TEAM_NOT_IN_SAME_FAMILY(374, "队伍成员必须同一个家族"),
    FAMILY_TEAM_MEMBER_CANNOT_APPLY(375, "只有队长才能报名"),

    //排名
    THIS_RANK_HAS_NOT_REWARD(400,"该榜无法领奖"),
    HAS_GOT_ALL_REWARD(401,"没有可领奖励"),

    //任务
    NOT_EXIST_TASKID(500,"不存在的任务"),
    CAN_NOT_ACCEPT(501,"不满足接任务条件"),
    NOT_NPC_TASK(502,"不是npc任务"),
    NOT_CUR_NPC_TASK(503,"当前npc没有此任务"),
    NOT_IN_TASK_LIST(504,"不在已接取任务列表中"),
    TASK_NOT_COMPLETED(505,"任务未完成"),
    IN_TASK_LIST(506,"已经在任务列表中"),
    EXCEED_MAX_TASK_FINISH_NUM(507, "已达到最大完成次数"),
    PRETASK_NOT_FINISH(508, "前置任务未完成"),
    MUTEXTASK_FINISH(509, "已完成互斥任务,不能接取本任务"),
    NOT_FAMILY_NPC(510,"不是家族npc"),
    HAS_ACCEPT_FAMILY_TASK(511,"已经接取家族任务了"),
    NOT_HAS_ACCEPT_FAMILY_TASK(512,"没有接取家族任务"),
    CAN_NOT_ACCEPT_FAMILY_TASK(513,"不能接取家族任务"),
    NOT_FAMILY_TASK(514,"不是家族任务"),
    EXCEED_BRANCH_NUMS(515,"超过能接取的支线任务数量"),
    CAN_NOT_CLEAR_FAMILY_TASK(516,"不能一键清环"),
    HAS_RECEIVED_THIS_BONUS(517,"已经领取该档位奖励了"),
    COMPLETE_NUMS_NOT_ENOUGH(518,"完成数量不够"),
    FAMTASK_ORDER_WRONG(519,"当前家族环任务顺序错误"),
    TASK_HAS_NOT_ECTYPEID(520, "当前任务没有副本"),
    PRE_TASK_HAS_NOT_COMPLETE(521,"前一个任务还未完成"),

    //邮箱
    MAIL_ACCESSORY_OBTAINED(600,"邮件附件已领"),
    MAILBOX_FULL(601,"邮箱已满"),
    MAIL_NOT_EXIST(602,"没有此邮件"),
    MAIL_HAS_READ(603,"邮箱已读过"),

    //好友
    FRIEND_NOT_FOUND(700,"未找到该角色"),
    FRIEND_MY_FRIENDLIST_OVERFLOW(701,"自己的朋友列表满了"),
    FRIEND_MY_REQUESTLIST_OVERFLOW(702,"自己申请别人的好友队列满了"),
    FRIEND_PEER_FRIENDLIST_OVERFLOW(703,"对方的好友列表满了"),
    FRIEND_ALREADY_REQUESTING(704,"已经在申请了"),
    FRIEND_NOT_BEREQUESTED(705,"对方没有申请加你好友"),
    FRIEND_ALREADY_FRIEND(706,"对方已经是好友"),
    FRIEND_BLOCKED_BY_PEER(707,"被对方屏蔽，无法提出好友申请"),
    FRIEND_NOT_FIND_IN_LIST(708,"未在响应的列表中找到角色信息"),
    FLOWER_EXCEED_DAY_LIMIT(709,"超过了每天限制鲜花的次数"),
    IDOL_AWARD_ALREADY_CLAIMED(710,"偶像奖励已经领取过了"),
    FRIEND_DEGREE_TOO_LOW(711,"奖励无法领取，好友度不够"),
    FLOWER_NOT_ENOUGH(712,"鲜花数量不够"),
    FRIEND_NOT_FRIEND(713,"对方不是好友"),
    FRIEND_MY_ERROR_GENDER(714,"自己性别不对"),
    FRIEND_PEER_ERROR_GENDER(715,"对方性别不对"),
    FRIEND_MY_MM_OVERFLOW(716,"自己脉脉列表已满"),
    FRIEND_PEER_MM_OVERFLOW(717,"对方脉脉列表已满"),
    FRIEND_ALREADY_MM(718,"已经建立了脉脉关系"),
    FRIEND_NOT_MM(719,"对方不是脉脉关系"),
    FRIEND_NOT_ALLOW_GETMM(720,"对方不让看脉脉"),
    IN_OTHER_BLACK_LIST(721,"对方已将你拉黑"),
    IN_YOUR_BLACK_LIST(722,"对方在你的黑名单中"),


    //聊天
    EXCCEED_MAX_TEXT_SIZE(800,"超出最大文本长度"),
    EXCCEED_MAX_VOICE_SIZE(801,"超出最大语音长度"),
    UNKNOWN_CHANNEL(802,"未知频道"),
    VOICE_NOT_FIND(803,"语音不存在"),
    CANNOT_CHAT_IN_SYSTEM_CHANEL(804,"不能在系统频道聊天"),
    IMAGE_NOT_FIND(805, "图片不存在"),
    CHAT_FORBIDDEN(806, "您已被禁言"),
    TALK_TOO_BUSY(807, "您发言太频繁"),
    NO_LEFT_REPORT_TIME(808,"没有可用的举报次数了"),

    //充值
    NOT_PAY(900,"未支付"),
    NOT_PAY_ENOUGH(901,"钱不够,无法完成支付"),
    PAY_AWARD_HAS_GOT(902,"已领完奖励"),
    PAY_HAS_GOT_PAY_RETURN(903, "已经领过充值返还"),
    PAY_HAS_NOT_PAY_RETURN(904, "没有充值返还"),
    HAS_BUY_THIS_PRODUCT_TODAY(905,"今天已经够买该产品了"),

    //装备
    EQUIP_UNLOAD_EQUIPTYPE_NOT_EXIST(1000,"装备类型（该位置）不存在装备"),
    EQUIP_BAG_IS_FULL(1001,"包裹已满"),
    EQUIP_NOT_EXIST(1002,"装备不存在"),
    EQUIP_LOADERROR_REQ_LEVEL(1003,"不满足所需的等级"),
    EQUIP_LOADERROR_REQ_PROFESSION(1004,"不满足职业限制"),
    RES_GOLD_OVERFLOW(1005,"金币太多溢出"),
    EQUIP_ITEM_NOT_EXIST(1007,"物品不在包裹里面"),
    EQUIP_ITEM_NOT_RESOLVABLE(1008,"物品不可分解"),
    EQUIP_ENHANCEAPPEND_ID_NOTEXIST(1009,"参数错误，在idbypos没有找到装备"),
    EQUIP_ENHANCE_STRENGTHEN_LIMIT(1010,"达到最大强化等级，1010"),
    EQUIP_APPEND_NOT_ENOUGH_STONE(1011,"没有足够的精炼石"),
    EQUIP_APPEND_NOT_ENOUGH_EQUIP(1012,"没有足够的装备"),
    EQUIP_PERFUSE_MAX_LEVEL(1013,"达到你能灌注的最大等级"),
    EQUIP_SELL_FAIL(1014,"装备卖出失败，金币超过最大值"),
    EQUIP_EXTEND_NOT_SAME_TYPE(1015,"装备类型不同，无法继承"),
    EQUIP_EXTEND_NOT_SAME_PROFESSION(1016,"非同门派装备无法继承"),
    EQUIP_APPEND_NOT_ENOUGH_ITEM(1017,"非绑定道具数量不足"),
    EQUIP_ANNEAL_HELPITEM_NOT_VALID(1018,"炼器辅助物品不支持"),
    EQUIP_ANNEAL_HELPITEM_NOT_ENOUGH(1019,"炼器辅助物品不足"),
    EQUIP_ANNEAL_COSTITEM_NOT_ENOUGH(1020,"炼器符物品不足"),
    EQUIP_EVOLVE_NOT_SUPPORT_TYPE(1021,"该装备类型不支持神器进阶"),
    EQUIP_EVOLVE_COST_ITEM_NOT_ENOUGH(1022,"神器进阶消耗的物品数量不足"),
    EQUIP_EVOLVE_CAN_NOT_EVOLVE(1023,"该装备无法进阶"),
    RES_LINGJING_ERROR(1024,"灵晶增加或者减少出错"),
    EQUIP_ACCESSORY_NOT_SUPPORT(1025,"饰品不支持该操作"),
    ACC_TYPE_NOT_SAME(1026,"饰品的类型不相同"),
    EQUIP_SPLIT_ITEM_BAG_FULL(1027,"装备拆解的时候物品背包已满，无法放入拆解后的"),
    EQUIP_EVOLVE_COST_EQUIP_NOT_FIND(1028,"神器进阶消耗的装备不存在"),
    EQUIP_PERFUSE_NOT_VALID(1029,"灌注等级不符合转移条件"),
    EQUIP_ANNEAL_NOT_VALID(1030,"炼器等级不符合转移条件"),

    //时装
    DRESS_ALREADY_ACTIVE(1101,"时装已经激活"),
    DRESS_NOT_GET(1102,"时装还未获得"),
    DRESS_NOT_EXISTED(1103,"时装不存在"),
    DRESS_NOT_ACTIVED(1104,"时装未激活"),
    DRESS_ALREADY_HAVE(1105,"已有该时装"),

    //坐骑
    RIDE_MODULE_LOCKED(1200,""),
    RIDE_ALREADY_ACTIVE(1201,"坐骑已经激活"),
    RIDE_NOT_GET(1202,"坐骑还未获得"),
    RIDE_NOT_EXISTED(1203,"坐骑不存在"),
    RIDE_NOT_ACTIVED(1204,"坐骑未激活"),
    RIDE_ALREADY_GET(1205,"坐骑已永久获得"),

    //物品
    ITEM_BAG_CAPACITY_FULL(1300,"背包容量已经最大"),
    ITEM_NUMBER_NOT_ENOUGH(1301,"背包中的物品个数不足！"),
    ITEM_CANNOT_SELL(1302,"该物品不允许售卖!"),
    ITEM_SELL_FAIL(1303,"物品售卖失败"),
    ITEM_CAN_NOT_USE(1304,"玩家级别无法使用该物品"),
    ITEM_CAN_NOT_BATCH_USE(1305, "物品不能批量使用"),

    //碎片
    FRAGMENT_NOT_ENOUGH(1400,"碎片数量不够"),
    FRAGMENT_NOT_FIND(1401,"碎片未找到"),
    FRAGMENT_BAG_IS_FULL(1402, "碎片背包已满"),

    //商城
    EXCEED_BUY_LIMIT(1500,"已达到购买次数上限"),
    UNKNOWN_GOODS_ID(1501,"没有此商品"),
    BUY_NUM(1502,"购买个数必须大于1502"),
    BLACK_MALL_NOT_OPEN(1503, "黑市商城未开启"),

    //福利
    ADD_SIGN_LIMIT(1600,"已达到补签次数上限"),
    SING_DATE_ERROR(1601,"签到日期错误"),
    UKNOW_SIGN_TYPE(1602,"错误签到类型"),
    NO_LEFT_TIME(1603,"次数已满"),
    TIME_NOT_ENOUGH(1604,"在线时长不够"),
    NOT_ENOUGH_CHARGE(1605,"充值金额不够"),
    NOT_ENOUGH_COST(1606,"消费金额不够"),
    NOT_PUT_PET(1607,"请放入许愿的伙伴"),
    NOT_ENOUGH_WISH_TIME(1608,"许愿时间不够"),
    NOT_ENOUGH_BAODIE(1609,"许愿物品数量不够"),
    NOT_NEED_ADD_SIGN(1610,"没有需要补签的日期"),
    WRONG_MONTH_CARD_DATE(1611,"错误月卡日期"),
    HAS_SIGNED(1612,"已经签到过"),
    NOT_ENOUGH_LOGIN_DAYS(1613,"登录天数不够"),
    HAS_BUY_GROWPLAN(1614,"已经购买过成长计划"),
    HAS_EATEN_LUNCH(1615,"已经吃了午餐"),
    HAS_EATEN_DINNER(1616,"已经吃了晚餐"),
    NOT_THE_EAT_TIME(1617,"没到吃饭时间"),
    EXCEED_BUY_LEVEL(1618,"超过购买等级"),
    HAS_RECEIVE_BONUS(1619,"已领取过该奖励"),
    NOT_BUY_GROWPLAN(1620,"没有购买成长计划"),
    WRONG_GROW_PLAN_TYPE(1621,"错误成长计划类型"),
    MUST_COMPLETE_PRE_GROW(1622,"必须领完前一个成长计划奖励"),
    //抽卡
    UNKNOWN_TYPE(1700,"非法类型"),
    EXCEED_MAX_EXCHANGE_TIEMS(1701,"达到兑换次数上限"),
    CAN_NOT_FREE_PICK_CARD(1702, "不能免费抽卡"),




    //个人boss战
    ACTIVITY_INFO_NOT_FIND(1800,"活动信息未找到"),
    BOSS_INFO_NOT_FIND(1801,"Boss信息未找到"),
    CHALLENGE_COUNT_OVERFLOW(1802,"挑战次数已达最大限制"),
    BOSS_IS_LOCKED(1803,"Boss未解锁，需要达到限定的级别"),

    //竞技场
    ARENA_NOT_IN_CHALLENGE_LIST(1900, "对手不在挑战列表中"),
    ARENA_CHALLENGER_LOCKED(1901, "对手正被别人挑战"),
    ARENA_OBTAIN_REWARD(1902, "已经领过奖"),
    ARENA_CAN_NOT_OBTAIN_REWARD(1903, "未达到领奖所需胜利场次"),
    ARENA_NOT_OPEN(1904, "竞技场未开启"),
    ARENA_SELF_LOCKED(1905, "由于上一场竞技场未正确结束,过一段时间后才能参加"),

    //交易
    NOT_IN_EXCHANGE_ITEM_LIST(2000,"物品未上架"),
    EXCEED_MAX_EXCHANGE_ITEM_NUM(2001,"达到最大上架数"),
    INVALID_PRICE(2002,"非法价格"),
    ADD_UNBIND_ITEM_ONLY(2003,"只有非绑定物品才能上架"),
    EXCEED_MAX_OVERLAY_NUM(2004,"超出物品最大堆叠数"),
    INVALID_BAG_TYPE(2005,"非法背包类型"),
    CANNOT_BUY_SELF_ITEM(2006,"不能购买自己的物品"),
    EXCEED_AVALIABLE_NUM(2007,"非法购买个数"),
    ITEM_CAN_NOT_TRADE(2008, "物品不能交易"),
    EXCHANGE_PRICE_OVERLIMIT(2009, "总价格超出上限"),

    //技能
    EXCEED_MAX_EQUIP_SKILL_NUM(2100,"已达到最大可激活技能数"),
    NOT_INT_SKILL_LIST(2101,"不在技能列表"),
    DUPLICATE_EQUIP_POSITION(2102,"重复的技能装备位置"),
    CANNOT_EQUIP_PASSIVE_SKILL(2103,"不能装备被动技能"),
    CUR_LEVEL_CANNOT_EVOLVE(2104,"未升级到最高等级,不能进阶"),
    NOT_NEXT_EVOLVE_SKILL(2105,"已达到最高进阶等级"),
    CANNOT_UPGRADE_MAX_LEVEL(2106,"已达到最高等级"),
    SKILL_NEED_TARGET(2107, "没有指定技能目标"),
    SKILL_OUT_ATTACK_RANGE(2108, "超出攻击范围"),
    INVALID_SKILLID(2109, "无效技能id"),
    SKILL_NOT_COLDDOWN(2110, "技能在冷却中"),
    SKILL_UNAVALIABLE(2111, "技能不可用"),
    SKILL_FORBID(2112, "当前状态不能使用技能"),
    INVALID_SKILL_TARGET(2113, "无效技能目标"),
    SKILL_PERFORM_MP_NOT_ENOUGH(2114, "魔量不足"),

    //组队
    TEAM_ALREADY_CREATED(2200,"你已经组建了队伍"),
    ONLY_LEADER_CAN_BREAKUP_TEAM(2201,"只有队长才可以解散队伍"),
    ALREADY_IN_TEAM(2202,"你当前已经处在一个队伍中了"),
    LEADER_NOT_INVITE_YOU(2203,"队长没有邀请你"),
    ONLY_LEADER_CAN_INVITE_FOLLOW(2204,"只有队长才可以邀请队友跟随"),
    ONLY_LEADER_CAN_UNFOLLOW_TEAMMEMBER(2205,"只有队长才可以解除队友跟随"),
    ONLY_LEADER_CAN_KICKOUT_TEAMMEMBER(2206,"只有队长才可以提出队友"),
    ONLY_LEADER_CAN_INVITE_JOIN(2207,"只有队长才可以邀请别人加入"),
    ONLY_LEADER_CAN_TRANSFER_LEADERSHIP(2208,"只有队长才可以转移队长"),
    ROLE_NOT_ONLINE(2209,"角色不在线"),
    TEAM_INFO_NOT_FIND(2210,"组队信息不存在"),
    TEAMMEMBER_NOT_EXISTED(2211,"队友不存在"),
    TEAMMEMBER_NOT_ONLINE(2212,"队友已下线"),
    LEADER_NOT_EXISTED(2213,"队长信息不存在"),
    LEADER_NOT_ONLINE(2214,"队长已下线"),
    NOT_YOUR_TEAMMEMBER(2215,"不是你的队友"),
    ALREADY_IN_OTHER_TEAM(2216,"已经在其他队伍中"),
    NOT_REQUEST(2217,"对方未发出入队申请"),
    NOT_IN_TEAM(2218,"不是队员"),
    TEAM_FULL(2219,"队伍已满"),
    ALREADY_INVITE(2220,"邀请已发送,不能频繁邀请,时间显示分钟"),
    ALREADY_REQUEST_TO_JOIN(2221,"申请已发送，不能频繁发送申请,时间显示分钟"),
    ONLY_LEADER_CAN_ACCPET_REQUEST(2222,"只有队长能够接受请求"),
    PARAM_NOT_VALID(2223, "非法参数"),
    NOT_TEAM_LEADER(2224,"不是队长"),
    TEAM_MEMBER_NOT_IN_LEVELGROUP(2225,"队员不在同一等级段"),

    //称号
    TITLE_MODULE_LOCKED(2300,"称号功能尚未开启"),
    TITLE_ALREADY_ACTIVE(2301,"称号已经激活"),
    TITLE_NOT_GET(2302,"称号还未获得"),
    TITLE_NOT_EXISTED(2303,"称号不存在"),
    TITLE_NOT_ACTIVED(2304,"称号尚未激活"),
    TITLE_EXPIRE(2305,"称号已经过期"),

    //仓库
    EQUIP_DEPOT_IS_FULL(2400,"装备仓库已满"),
    FRAGMENT_DEPOT_IS_FULL(2401,"碎片仓库已满"),
    ITEM_DEPOT_IS_FULL(2402,"物品仓库已满"),
    TALISMAN_DEPOT_IS_FULL(2403,"法宝仓库已满"),
    PET_DEPOT_IS_FULL(2404,"伙伴仓库已满"),
    XUNIBI_DEPOT_IS_FULL(2405,"金币仓库已满"),

    // 成就
    ACHIEVEMENT_NOT_COMPLETED(2500, "成就未完成"),
    ACHIEVEMENT_GET_REWARD(2501, "成就已领完奖励"),
    ACHIEVEMENT_INVALID_EVOLVE_TITLE_ID(2502, "非法的成就称号"),

    // cmd
    INVALID_CONFIGID(2600, "非法的confgid"),
    INVALID_NUM(2601, "非法的num"),

    //日常活跃与小助手
    HAS_FIND_BACK(2700,"已经找回了"),
    NO_FIND_CONTENT(2701,"没有可找回的内容"),

    //家族模块
    FAMILY_NOT_EXISTED(2800,"家族信息不存在"),
    FAMILY_MODULE_LOCKED(2801,"家族模块20级开启"),
    NAME_SHORTLEN(2802,"抱歉，您输入的角色昵称太短"),
    NAME_OVERLEN(2803,"抱歉，您输入的角色昵称已超过6个字，请重新输入"),
    NAME_DUPLICATED(2804,"抱歉，您输入的角色昵称已存在"),
    FAMILY_ALREADY_IN_ONE(2805,"已经在一个家族中"),
    ONLY_CHIEF_VICECHIEF_CAN_ACTION(2806,"普通成员无法进行该操作"),
    FAMILY_FULL(2807,"家族成员已满"),
    TEXT_INCLUDE_SENCITIVE_WORDS(2808,"信息包含敏感词汇"),
    TEXT_OVERLENT(2809,"信息长度超限"),
    NOT_IN_FAMILY(2810,"非家族成员"),
    ONLY_CHIEF_CAN_TRANSFER_CHIEF(2811,"只有教主才能任命教主"),
    JOB_STAFF_FULL(2812,"该职位人员已经满了"),
    FAMILY_ACTION_DISALLOWED_AFATER_QUIT(2813,"退出家族两次以上后，2小时无法创建和申请加入其它家族"),
    FAMILY_MONEY_NOT_ENOUGH(2814,"家族资金不够"),
    FAMILY_EXCEED_MAXSKILLLEVEL(2815,"已经达到最大可升级的升级"),
    FAMILY_SKILL_NOT_STUDYED(2816,"家族技能尚未学习"),
    FAMILY_AWARD_ALREADY_CLAIMED(2817,"该级别的家族奖励已经领取过"),
    FAMILY_ANIMAL_ACTIVITY_EXCEED_LIMIT(2818,"活动次数超过限制"),
    FAMILY_ANIMAL_ACTIVITY_LAUNCH_TIME_DISALLOWED(2819,"活动开始时间不允许"),
    FAMILY_ANIMAL_ACTIVITY_ALREADY_LAUNCHED(2820,"神兽挑战活动结束后才可开启下一次活动"),
    FAMILY_ANIMAL_IS_MAX_LEVEL(2821,"家族神兽已经达到最高级别"),
    FAMILY_ANIMAL_EVOLVE_CODITION_NOT_MEET(2822,"家族神兽进化条件不满足"),
    FAMILY_CHIEF_CAN_NOT_RELEASED(2823,"家族教主不能免职"),
    FAMILY_JUST_CHIEF_CAN_RELEASE_VICE_CHIEF(2824,"只有教主才能免职副教主"),
    FAMILY_ALREADY_HAS_JOB(2825,"已经有职位信息"),
    FAMILY_CHIEF_CAN_NOT_APPOINT(2826,"家族教主职位不能任命只能转移"),
    FAMILY_OPERATION_PRIVILEGE_NOT_ENOUGH(2827,"操作权限不够"),
    FAMILY_LEVEL_TOO_LOW(2829,"家族等级太低"),
    FAMILY_MALL_LEVEL_TOO_LOW(2830, "家族商店等级太低"),
    ALREADY_IN_CROWD(2831,"已经发起众筹了"),
    YUANBAO_EXCEED(2832,"元宝超过了需要的数量"),
    MIN_CROWD_FUND_YUANBAO_NOT_ENOUGH(2833,"众筹所需最少元宝不够"),
    NOT_INIT_CORWD_FAMILY(2834,"没有发起过众筹"),
    HAS_OPEN_FAMILY_PARTY(2835,"家族聚宴已经开启过了"),
    NOT_THE_OPEN_TIME(2836,"没到聚宴开启时间"),
    PARTY_TIME_HAS_PAST(2837,"聚宴时间已经过了"),
    HAS_EAT_ENOUGH_RUNE(2838,"该种类拾取数量达到上限"),
    NOT_THE_CALL_TIME(2839,"请稍等一段时间再发送"),
    THE_PARTY_IS_DOING(2840,"家族聚宴正在进行"),
    MUST_IN_FAMILY_STATION(2841,"必须在家族驻地"),
    FAMILY_ANIMAL_ACTIVITY_IS_DOING(2842,"家族神兽挑战正在进行"),
    WRONG_LAUNCH_DATE(2843,"家族神兽开启日期错误"),
    FAMILY_GOD_ANIMAL_EXP_NOT_ENOUGH(2844,"家族神兽经验不足"),
    HAS_JOIN_OTHER_FAMILY(2845,"已经加入其它家族了"),
    CAN_NOT_CHANGE_LINE_IN_FAMILYSTATION(2846,"家族驻地内不能换线"),
    APPLY_FAMILY_NUM_MAX(2847,"申请加入家族的数量达到上限"),
    HAS_APPLAY_THIS_FAMILY(2848,"已经申请过该家族了"),
    FAMILY_HAS_EXIST(2849,"您输入的家族名已存在"),
    FAMILY_REQUEST_NUM_MAX(2850, "该家族的申请数量已达上限"),
    ONLY_CHIEF_CAN_APPOINT_JOB(2851,"只有教主副教主才能任命职位"),
    HAS_IN_OTHER_FAMILY(2852,"对方已经在别的家族了"),
    OTHER_FAMILY_HAS_FULL(2853,"对方的家族已经满了"),
    OTHER_IS_IN_QUIT_TIME(2854,"对方退出家族次数过多，两小时内无法加入家族"),
    //伙伴
    PET_MODULE_LOCKED(2900,"伙伴模块尚未开始"),
    PET_NOT_FOUND(2901,"伙伴未找到"),
    PET_SKIN_NOT_BUYED(2902,"伙伴皮肤尚未购买"),
    PET_SKILL_ALREADY_MAXLEVEL(2903,"技能已经达到最高级"),
    PET_SKILL_NOT_STUDYED(2904,"技能尚未学习"),
    PET_SKIN_NOT_MATCH(2905,"该伙伴不能使用此皮肤"),
    PET_FRAGMENT_NOT_ENOUGH(3000,"伙伴碎片数量不足"),
    PET_ALREADY_EXIST(3001,"伙伴已经存在"),
    PET_SKILL_NOT_FOUND(3002,"技能未找到"),
    PET_TALENT_NOT_FOUND(3003,"天赋未找到"),
    PET_SKILL_LEVEL_TOO_LOW(3004,"伙伴技能等级不足"),
    PET_LEVEL_TOO_LOW(3005,"伙伴的级别不足"),
    PET_TALENT_GROUP_TOTAL_LEVEL_NOT_ENOUGH(3006,"天赋组内总级别不满足条件"),
    PET_TALENT_ALREADY_MAXLEVEL(3007,"天赋已经达到最高级别"),
    PET_ALREADY_MAXLEVEL(3008,"伙伴已经是最大等级"),
    PET_STAGE_ALREADY_MAXLEVEL(3009,"伙伴已经达到最大阶星"),
    ROLE_LEVEL_TOO_LOW(3010,"角色级别不足"),
    PET_WAKE_ALREADY_MAXLEVEL(3011,"伙伴已经觉醒到最高级别"),
    PET_WAKE_FRAGMENT_NUM_NOT_ENOUGH(3012,"伙伴觉醒的碎片数量不够"),
    PET_WAKE_ASSIST_PET_LEVEL_TOO_HIGH(3013,"伙伴觉醒的辅助伙伴级别不能高于当前伙伴级别"),
    PET_RECYCLE_FAIL(3014,"伙伴归元失败，可能是背包满"),
    PET_CANNOT_EQUIP(3015, "无法上阵"),
    PET_CANNOT_UNEQUIP(3016, "无法下阵"),
    PET_HAS_NOT_EQUIP(3017, "宠物未上阵,不能助战"),
    PET_CANNOT_ASIST(3018, "无法助阵"),
    PET_WASH_MAX(3019, "已达上限，无法洗练"),


    //法宝
    TALISMAN_NOT_EXISTED(3100,"该法宝不存在"),
    ALREADY_EQUIPED_TALISMAN(3101,"已经装备了法宝"),
    TALISMAN_NUMS_NOT_ENOUGH(3102,"法宝数量不够"),
    TALISMAN_WUXING_HAS_GOT_MAX(3103,"法宝五行属性值已达到上限"),
    TALISMAN_AWAKE_LEVEL_GOT_MAX(3104,"法宝觉醒等级已达到上限"),
    CHOOSE_A_LUCKY_TYPE(3105,"请先选择运势"),
    GOT_MAX_TIME_IN_CUR_LUCKY_TYPE(3106,"当前运势下洗练次数达到上限"),
    TALISMAN_IS_INIT(3107,"该法宝没有被强化过"),
    TALISMAN_ADD_STAR_COST_TALISMAN_NOT_EXIST(3108, "法宝升星消耗的法宝不存在"),
    TALISMAN_SORT_SIZE_NOT_MATCH(3109, "整理后个数与背包内法宝个数不匹配"),
    TALISMAN_SORT_NEW_POSITION_DUPLICATE(3110, "整理后背包位置重复"),
    TALISMAN_SORT_NEW_POSITION_INVALID(3111, "整理后出现非法背包位置"),
    TALISMAN_BAG_HAS_FULL(3112,"法宝背包已满"),
    TALISMAN_LEVEL_TOO_LOW(3113, "法宝等级太低"),
    TALISMAN_MAX_LEVEL(3114, "法宝已达到最大等级"),
    TALISMAN_CAN_NOT_EXCEED_ROLE_LEVEL(3115, "法宝等级不能超过玩家等级"),
    TALISMAN_STAR_MAX_LEVEL(3116, "法宝星阶已达到最高等级"),


    // 登陆
    NAME_INVALID(4200, "非法名字"),
    NAME_DUPLICATE(4201, "重复名字"),
    OTHER_ERROR(4204, "其他错误"),
    PROFESSION_NOT_OPEN(4205, "职业未开放"),
    GENDER_INVALID(4206, "不合法的性别"),
    NAME_SENSE(4207, "名字有敏感词"),


    //七脉会武信息
    HUIWU_INFO_NOT_EXISTED(5100,"会武活动未创建"),
    HUIWU_ALREAY_ENROLL(5101,"已经报名"),
    HUIWU_ENROLL_END(5102,"报名已经结束"),
    HUIWU_ENROLL_FULL(5103,"报名人数已经满"),
    HUIWU_ALREADY_GUESSED(5104,"已经竞猜过了"),
    HUIWU_GUESS_ROLE_NOT_EXISTED(5105,"竞猜的玩家不存在"),
    HUIWU_FIANL_ALREADY_BEGIN(5106,"决赛已经开始，不能竞猜了"),


    //护符
    AMULET_PAGE_LOCKED(5401,"护符页面未解锁"),



    //结婚
    HAS_MARRIED(3200,"已经结婚了"),
    MUST_DIFF_GENDER(3201,"必须不同性别"),
    NOT_HAVE_CAILI(3202,"没有彩礼"),
    IS_PROPOSING(3203,"求婚冷却时间未到"),
    NOT_YOUR_WIFE_OR_HUSBAND(3204,"该角色跟你不是伴侣"),
    ALREADY_HAVE_CAILI(3205,"已经有彩礼了"),
    NOT_HAVE_XIUSHU(3206,"没有休书"),
    ONLY_LUXURY_WIRTE_OATH(3207,"只有豪华彩礼才能自定义宣言"),
    HAS_WISHED(3208,"已经祝福过了"),
    OTHER_HAS_MARRIED(3209,"对方已经结婚了"),
    MARRIAGE_LEVEL_NOT_ENOUGH(3210,"对方结婚等级不够"),

    //玉佩
    NOT_ENOUGH_BONUS(6000, "增加属性攻击固定值没达到上限"),
	ERROR_JADE_POSITION(6001, "玉佩孔位置错误"),
	ERROR_JEWELRY_INDEX(6002, "宝珠背包索引错误"),
	JADE_MAX_LEVEL(6003, "已达到最高等级"),


    //血战青云
    GD_NO_CHALLENGE_TIME(7001, "挑战次数已用完"),
    GD_MAX_WAVE(7002, "已击杀所有怪物"),
    GD_MATCH_NO_ZONE_LEVEL(7003, "没有该等级段的副本"),
    GD_MATCH_MIN_POWER_TOO_LARGE(7004, "最低战斗力不能大于自身战斗力"),
    GD_MATCH_ONLY_TEAM_LEADER_CAN_START(7005, "您当前不是队长，请由队长点击匹配。"),
    GD_MATCH_MEMBER_OUT_of_RANGE(7006, "队伍中有成员与你不在同一等级段内，不能匹配。"),
    GD_MATCH_MEMBER_IN_ECTYPE(7007, "队伍中有成员在副本中，不能匹配。"),
    GD_MATCH_MEMBER_NO_CHALLENGE_TIME(7008, "队伍中有玩家挑战次数已满，不能发起匹配。"),
    GD_TODAY_NOT_BEGIN(7009, "今日《血战青云》尚未开始。"),
    GD_TODAY_END(7010, "今日《血战青云》已经结束。"),

    //青云英雄录
    HERO_ECTYPE_NO_AWARD_WITH_GROUPID(8001, "该组无可刷新奖励"),
    HERO_ECTYPE_NO_AWARD_WITH_ECTYPEID(8002, "该组副本无可刷新奖励"),
    HERO_ECTYPE_REWARD_REFRESH_MAX_TIME(8003, "已达到最大刷新次数"),
    HERO_ECTYPE_CHALLENGE_MAX(8004, "已达到最大挑战次数"),


    // 匹配
    SOME_MEMBER_IN_MATCH(6100, "队伍内有人已经在匹配队列中"),
    MATCH_TIMEOUT(6101, "匹配超时,未能匹配到玩家"),
    TEAM_IS_EMPTY(6102, "队伍不能为空"),
    NOT_INT_MATCH(6103, "不在匹配队列中"),
    FORBID_MATCH(6104, "由于逃跑行为，10分钟内不能再进行匹配操作"),
    CAN_NOT_ENTER_ECTYPE_IN_MATCH(6105, "已经在匹配队列中,不能开启副本"),
    CAN_NOT_MATCH_AFTER_READY(6106, "已经匹配成功,不能再匹配"),
    MEMBER_NOT_SAME_MATCH_GROUP(6107, "成员不在同个等级段,不能参与匹配"),
    SOME_MEMBER_LEVEL_TOO_LOW(6108, "队伍内有人等级太低"),
    ONLY_TEAM_LEADER_CAN_START(6109, "组队时应由队长发起匹配"),
    SOME_MEMBER_TILI_NOT_ENOUTH(6110,"队伍内有人体力不够"),
    SOME_MEMBER_IS_IN_ECTYPE(6111,"队伍内有人在副本中"),
    SOME_MEMBER_EXCEED_LIMIT(6112,"队伍中有人本日次数已用尽"),
    HAS_IN_ECTTYPE(6113,"您已经在副本中了"),
    SOME_MEMBER_IN_ECTYPE(6114, "队伍中有成员在副本中，不能匹配。"),
    TILI_NOT_ENOUGH_AND_CANCEL_MATCH(6115,"体力不足，已退出多人剧情匹配"),
    HAS_IN_MATCH(6116, "您已经在匹配队列中"),

    // 风华录
    STORY_NOTE_HAS_ACTIVED(6201, "已经激活这个记录"),

    // 活动
    ACTIVITY_NOT_EXIST(6300, "没有此活动"),
    ACTIVITY_HAS_SCHEDULE(6301, "已添加这个时段的活动"),
    ACTIVITY_NOT_OPEN(6302,"活动没有开启"),
    ACTIVITY_NOT_COMPLETE(6303,"活动条件未达成"),
    ACTIVITY_HAS_GET_REWARD(6304,"活动已经领奖"),

    // 激活码,礼包码
    AC_NET_ERROR(6400, "网络错误"),
    ;
    private int errorid;
    private String desc;
    
    ErrorCode(int errorid, String desc) {
        this.errorid = errorid;
        this.desc = desc;
    }
    
    public int getErrorId(){
        return errorid;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public boolean ok() {
        return this == OK;
    }
    
    public boolean err() {
        return this != OK;
    }
    
    public static void main(String[] args) throws IOException {
    	final ArrayList<String> outs = new ArrayList<>();
    	outs.add("return {");
    	for(ErrorCode e : ErrorCode.values()) {
            outs.add(String.format("%s = %s, [%s] = \"%s\",", e.name(), e.getErrorId(), e.getErrorId(), e.getDesc()));
    	}
    	outs.add("}");
    	Files.write(new File(args[0]).toPath(),
    		outs.stream().collect(Collectors.joining("\n")).getBytes(StandardCharsets.UTF_8));
    }
}
