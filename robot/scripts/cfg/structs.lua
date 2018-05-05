local os = require 'cfg.datastream'
local insert = table.insert
local ipairs = ipairs
local setmetatable = setmetatable
local find = string.find
local sub = string.sub
local function get_or_create(namespace)
local t = _G
local idx = 1
while true do
local start, ends = find(namespace, '.', idx, true)
local subname = sub(namespace, idx, start and start - 1)
local subt = t[subname]
if not subt then
subt = {}
      t[subname] = subt
end
    t = subt
if start then
idx = ends + 1
else
return t
end
end
end
function os:gettype(typename)
return self['get_' .. typename:gsub('%.', '_')](self)
end

local meta
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Name'
get_or_create('cfg.role')['Name'] = meta
function os:get_cfg_role_Name()
local o = {}
setmetatable(o, cfg.role.Name)
local _list = self:get_list('cfg_role_Names')
o.firstnames = _list
o.lastnames = self:get_cfg_role_Names()
local _list = self:get_list('cfg_role_DecorateName')
o.deconames = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.PetSkillUpgrade'
get_or_create('cfg.cmd.condition')['PetSkillUpgrade'] = meta
function os:get_cfg_cmd_condition_PetSkillUpgrade()
local o = {}
setmetatable(o, cfg.cmd.condition.PetSkillUpgrade)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ZaoHua'
get_or_create('cfg.cmd.condition')['ZaoHua'] = meta
function os:get_cfg_cmd_condition_ZaoHua()
local o = {}
setmetatable(o, cfg.cmd.condition.ZaoHua)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.IntervalEffect'
get_or_create('cfg.buff')['IntervalEffect'] = meta
function os:get_cfg_buff_IntervalEffect()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.Const'
meta.BAG_DEFAULT_SIZE = 100
meta.BAG_DEFAULT_UNLOCK_SIZE = 40
get_or_create('cfg.talisman')['Const'] = meta
function os:get_cfg_talisman_Const()
local o = {}
setmetatable(o, cfg.talisman.Const)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.GetBuff'
get_or_create('cfg.talisman')['GetBuff'] = meta
function os:get_cfg_talisman_GetBuff()
local o = {}
setmetatable(o, cfg.talisman.GetBuff)
o.buffid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.ItemInfo'
get_or_create('cfg.task')['ItemInfo'] = meta
function os:get_cfg_task_ItemInfo()
local o = {}
setmetatable(o, cfg.task.ItemInfo)
o.itemid = self:get_int()
o.itemcount = self:get_int()
o.professionbelongs = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ZhanChang'
get_or_create('cfg.cmd.condition')['ZhanChang'] = meta
function os:get_cfg_cmd_condition_ZhanChang()
local o = {}
setmetatable(o, cfg.cmd.condition.ZhanChang)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CurveArea'
get_or_create('cfg.ectype')['CurveArea'] = meta
function os:get_cfg_ectype_CurveArea()
local o = {}
setmetatable(o, cfg.ectype.CurveArea)
o.curveid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mine.Mineral'
get_or_create('cfg.mine')['Mineral'] = meta
function os:get_cfg_mine_Mineral()
local o = {}
setmetatable(o, cfg.mine.Mineral)
o.id = self:get_int()
o.name = self:get_string()
o.path = self:get_string()
o.costitemid = self:get_int()
o.costtime = self:get_float()
o.disappeartime = self:get_float()
o.refreshtime = self:get_float()
o.protecttime = self:get_float()
o.canbreak = self:get_bool()
o.requiretaskid = self:get_int()
o.showmineralbar = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.slowtime'
get_or_create('cfg.ectype')['slowtime'] = meta
function os:get_cfg_ectype_slowtime()
local o = {}
setmetatable(o, cfg.ectype.slowtime)
o.id = self:get_int()
o.rate = self:get_float()
o.duration = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemBasic'
get_or_create('cfg.item')['ItemBasic'] = meta
function os:get_cfg_item_ItemBasic()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.dailyactivity.activitytip'
get_or_create('cfg.dailyactivity')['activitytip'] = meta
function os:get_cfg_dailyactivity_activitytip()
local o = {}
setmetatable(o, cfg.dailyactivity.activitytip)
o.activitytipmap = self:get_map('int', 'string')
o.tipshowduration = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.BeAttackEffect'
get_or_create('cfg.skill')['BeAttackEffect'] = meta
function os:get_cfg_skill_BeAttackEffect()
local o = {}
setmetatable(o, cfg.skill.BeAttackEffect)
o.id = self:get_int()
o.curve = self:get_int()
o.defenceraction = self:get_string()
o.defencereffectid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.WuXing'
get_or_create('cfg.cmd.action')['WuXing'] = meta
function os:get_cfg_cmd_action_WuXing()
local o = {}
setmetatable(o, cfg.cmd.action.WuXing)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EquipAnneal'
meta.FAIL_REDUCE_NUMBER = {0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
meta.FAIL_REDUCE_BASELEVEL = 5
get_or_create('cfg.equip')['EquipAnneal'] = meta
function os:get_cfg_equip_EquipAnneal()
local o = {}
setmetatable(o, cfg.equip.EquipAnneal)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_equip_EnhanceData')
o.adddata = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CircularArea'
get_or_create('cfg.ectype')['CircularArea'] = meta
function os:get_cfg_ectype_CircularArea()
local o = {}
setmetatable(o, cfg.ectype.CircularArea)
o.position = self:get_cfg_map_Vector2()
o.radius = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotQTE'
meta.timeScale = 0.005
get_or_create('cfg.plot')['PlotQTE'] = meta
function os:get_cfg_plot_PlotQTE()
local o = {}
setmetatable(o, cfg.plot.PlotQTE)
o.index = self:get_string()
local _list = self:get_list('cfg_plot_QTEButton')
o.buttons = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.CircleRegion'
get_or_create('cfg.map')['CircleRegion'] = meta
function os:get_cfg_map_CircleRegion()
local o = {}
setmetatable(o, cfg.map.CircleRegion)
o.center = self:get_cfg_map_Vector3()
o.radius = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.YuanBao'
get_or_create('cfg.cmd.condition')['YuanBao'] = meta
function os:get_cfg_cmd_condition_YuanBao()
local o = {}
setmetatable(o, cfg.cmd.condition.YuanBao)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIWidget'
get_or_create('cfg.ui')['ContentUIWidget'] = meta
function os:get_cfg_ui_ContentUIWidget()
local o = {}
setmetatable(o, cfg.ui.ContentUIWidget)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.depth = self:get_int()
o.width = self:get_int()
o.height = self:get_int()
o.pivot = self:get_string()
o.color = self:get_cfg_ui_Color()
o.autoResizeBoxCollider = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.LacerationbyLevel'
get_or_create('cfg.buff')['LacerationbyLevel'] = meta
function os:get_cfg_buff_LacerationbyLevel()
local o = {}
setmetatable(o, cfg.buff.LacerationbyLevel)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
local _list = self:get_list('string')
o.introduction = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.FlyingSwordHeight'
get_or_create('cfg.equip')['FlyingSwordHeight'] = meta
function os:get_cfg_equip_FlyingSwordHeight()
local o = {}
setmetatable(o, cfg.equip.FlyingSwordHeight)
o.id = self:get_int()
o.faction = self:get_int()
o.gender = self:get_int()
o.offsety = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.CoolDown'
get_or_create('cfg.cmd.condition')['CoolDown'] = meta
function os:get_cfg_cmd_condition_CoolDown()
local o = {}
setmetatable(o, cfg.cmd.condition.CoolDown)
o.time = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.AnimTypeSelector'
get_or_create('cfg.skill')['AnimTypeSelector'] = meta
function os:get_cfg_skill_AnimTypeSelector()
local o = {}
setmetatable(o, cfg.skill.AnimTypeSelector)
o.type = self:get_int()
local _list = self:get_list('string')
o.animtypes = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FamilyStation'
get_or_create('cfg.ectype')['FamilyStation'] = meta
function os:get_cfg_ectype_FamilyStation()
local o = {}
setmetatable(o, cfg.ectype.FamilyStation)
o.minspellnum = self:get_int()
o.maxspellnum = self:get_int()
o.refreshinterval = self:get_int()
o.maxbonustimes = self:get_int()
o.bornpositions = self:get_cfg_map_Vector2()
o.familymanagerposition = self:get_cfg_map_Vector2()
o.blackmarkeposition = self:get_cfg_map_Vector2()
o.partyposition = self:get_cfg_map_Vector2()
o.godanimalposition = self:get_cfg_map_Vector2()
o.spellmsg = self:get_cfg_ectype_Spell()
local _list = self:get_list('cfg_ectype_GodMonsterInfo')
o.monsters = _list
o.godanimalextraBonus = self:get_cfg_cmd_action_MultiBonus()
o.godanimaldroprune = self:get_int()
local _list = self:get_list('cfg_map_Vector2')
o.godanimalboxpositions = _list
o.godanimalboxexisttime = self:get_int()
o.godanimalboxmaxeat = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.BuffAttack'
get_or_create('cfg.skill')['BuffAttack'] = meta
function os:get_cfg_skill_BuffAttack()
local o = {}
setmetatable(o, cfg.skill.BuffAttack)
o.target = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyTaskReward'
get_or_create('cfg.family')['FamilyTaskReward'] = meta
function os:get_cfg_family_FamilyTaskReward()
local o = {}
setmetatable(o, cfg.family.FamilyTaskReward)
o.level = self:get_int()
o.exp = self:get_int()
o.gold = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Periodic2'
get_or_create('cfg.ai')['Periodic2'] = meta
function os:get_cfg_ai_Periodic2()
local o = {}
setmetatable(o, cfg.ai.Periodic2)
o.interval = self:get_float()
o.maxrepeat = self:get_int()
o.expression = self:get_cfg_ai_Expression()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.DailyEctypeInfo'
get_or_create('cfg.ectype')['DailyEctypeInfo'] = meta
function os:get_cfg_ectype_DailyEctypeInfo()
local o = {}
setmetatable(o, cfg.ectype.DailyEctypeInfo)
o.viptimes = self:get_cfg_cmd_condition_VipLimits()
o.revivecost = self:get_cfg_ectype_ReviveCost()
o.resetopencountcost = self:get_cfg_ectype_ResetOpenCount()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.If'
get_or_create('cfg.ai')['If'] = meta
function os:get_cfg_ai_If()
local o = {}
setmetatable(o, cfg.ai.If)
o.condition = self:get_cfg_ai_BoolExpr()
o.iftrue = self:get_cfg_ai_Expression()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.FamilyMoney'
get_or_create('cfg.cmd.condition')['FamilyMoney'] = meta
function os:get_cfg_cmd_condition_FamilyMoney()
local o = {}
setmetatable(o, cfg.cmd.condition.FamilyMoney)
o.money = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.TaskReward'
get_or_create('cfg.task')['TaskReward'] = meta
function os:get_cfg_task_TaskReward()
local o = {}
setmetatable(o, cfg.task.TaskReward)
o.money = self:get_int()
o.ingot = self:get_int()
o.exp = self:get_int()
o.rewarditem = self:get_cfg_task_RewardItem()
o.titleid = self:get_int()
o.deletetitle = self:get_bool()
o.taskid = self:get_int()
o.transfertolocation = self:get_cfg_task_LocationPointData()
o.refreshtasklibrary = self:get_bool()
o.prestigetype = self:get_int()
o.prestige = self:get_int()
o.broadcasttype = self:get_int()
o.broadcastcontent = self:get_string()
local _list = self:get_list('cfg_task_MonsterController')
o.monstercontroller = _list
o.factionexp = self:get_int()
local _list = self:get_list('cfg_task_SkillInfo')
o.rewardskill = _list
local _list = self:get_list('cfg_task_ItemInfo')
o.professionrewarditem = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.ModelActions'
get_or_create('cfg.skill')['ModelActions'] = meta
function os:get_cfg_skill_ModelActions()
local o = {}
setmetatable(o, cfg.skill.ModelActions)
o.modelname = self:get_string()
o.basemodelname = self:get_string()
o.templatemodelname = self:get_string()
o.actions = self:get_map('string', 'cfg_skill_ModelAction')
o.skillactions = self:get_map('string', 'cfg_skill_SkillAction')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanBasic'
meta.PUBLIC_SKILL_CD = 10
meta.BAG_SIZE = 30
get_or_create('cfg.talisman')['TalismanBasic'] = meta
function os:get_cfg_talisman_TalismanBasic()
local o = {}
setmetatable(o, cfg.talisman.TalismanBasic)
o.id = self:get_int()
o.name = self:get_string()
o.introduction = self:get_string()
o.canuse = self:get_bool()
o.quality = self:get_int()
o.icon = self:get_string()
o.modelpath = self:get_string()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.qualityexp = self:get_int()
o.maturerate = self:get_cfg_pet_Maturerate()
o.attr = self:get_cfg_fight_BasicAttr()
o.specialattackrate = self:get_float()
o.uisize = self:get_float()
o.uipositionx = self:get_float()
o.uipositiony = self:get_float()
o.uipositionz = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.LockObject'
get_or_create('cfg.guide')['LockObject'] = meta
function os:get_cfg_guide_LockObject()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.ShiMen'
get_or_create('cfg.cmd.action')['ShiMen'] = meta
function os:get_cfg_cmd_action_ShiMen()
local o = {}
setmetatable(o, cfg.cmd.action.ShiMen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Passage'
get_or_create('cfg.ectype')['Passage'] = meta
function os:get_cfg_ectype_Passage()
local o = {}
setmetatable(o, cfg.ectype.Passage)
o.curveid = self:get_int()
o.linkedlayout = self:get_int()
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.FamilyBonus'
get_or_create('cfg.bonus')['FamilyBonus'] = meta
function os:get_cfg_bonus_FamilyBonus()
local o = {}
setmetatable(o, cfg.bonus.FamilyBonus)
o.bonusid = self:get_int()
o.requirefamilylvl = self:get_cfg_cmd_condition_MinFamilyLevel()
o.weekbonustimes = self:get_cfg_cmd_condition_Limit()
o.bonus = self:get_cfg_cmd_action_Items()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.BeginnerEquip'
get_or_create('cfg.ectype')['BeginnerEquip'] = meta
function os:get_cfg_ectype_BeginnerEquip()
local o = {}
setmetatable(o, cfg.ectype.BeginnerEquip)
o.profession = self:get_int()
o.weaponid = self:get_int()
o.clothid = self:get_int()
o.hatid = self:get_int()
o.shoesid = self:get_int()
o.notweaponanneallevel = self:get_int()
o.weaponanneallevel = self:get_int()
o.perfuselevel = self:get_int()
o.talismanid = self:get_int()
o.battlepower = self:get_int()
local _list = self:get_list('int')
o.skillorder = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.AttackCityRefreshMonster'
get_or_create('cfg.ectype')['AttackCityRefreshMonster'] = meta
function os:get_cfg_ectype_AttackCityRefreshMonster()
local o = {}
setmetatable(o, cfg.ectype.AttackCityRefreshMonster)
o.refreshtime = self:get_int()
o.refreshmsg = self:get_string()
local _list = self:get_list('cfg_ectype_MonsterRefreshInfo')
o.monsters = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.FollowInfo'
get_or_create('cfg.pet')['FollowInfo'] = meta
function os:get_cfg_pet_FollowInfo()
local o = {}
setmetatable(o, cfg.pet.FollowInfo)
o.degree = self:get_float()
o.distance = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Hat'
get_or_create('cfg.equip')['Hat'] = meta
function os:get_cfg_equip_Hat()
local o = {}
setmetatable(o, cfg.equip.Hat)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.nextid = self:get_int()
o.extraequipid = self:get_int()
o.upgradecurrencycost = self:get_cfg_cmd_condition_XuNiBi()
o.carryingitemnum = self:get_int()
o.recommendrate = self:get_float()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.property = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIButton'
get_or_create('cfg.ui')['ContentUIButton'] = meta
function os:get_cfg_ui_ContentUIButton()
local o = {}
setmetatable(o, cfg.ui.ContentUIButton)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.tweenTarget = self:get_cfg_ui_ContentUITransform()
o.dragHighlight = self:get_bool()
o.duration = self:get_float()
o.hover = self:get_cfg_ui_Color()
o.pressed = self:get_cfg_ui_Color()
o.disabledColor = self:get_cfg_ui_Color()
o.hoverSprite2D = self:get_string()
o.pressedSprite2D = self:get_string()
o.disabledSprite2D = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.Const'
meta.MAX_FRIEND_AMOUNT = 100
meta.BLOCK_LIST_AMOUNT = 100
meta.FRIEND_GET_MM = 1
meta.STRANGE_GET_MM = 0
get_or_create('cfg.friend')['Const'] = meta
function os:get_cfg_friend_Const()
local o = {}
setmetatable(o, cfg.friend.Const)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.BindYuanBao'
get_or_create('cfg.cmd.action')['BindYuanBao'] = meta
function os:get_cfg_cmd_action_BindYuanBao()
local o = {}
setmetatable(o, cfg.cmd.action.BindYuanBao)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.CollectItem'
get_or_create('cfg.operationalactivity')['CollectItem'] = meta
function os:get_cfg_operationalactivity_CollectItem()
local o = {}
setmetatable(o, cfg.operationalactivity.CollectItem)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.totallimit = self:get_cfg_cmd_condition_Limit()
o.items = self:get_cfg_cmd_condition_Items()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.BonusData'
get_or_create('cfg.equip')['BonusData'] = meta
function os:get_cfg_equip_BonusData()
local o = {}
setmetatable(o, cfg.equip.BonusData)
o.bonuslevel = self:get_int()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.bonusvalue = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.AccessoryQuality'
get_or_create('cfg.operationalactivity')['AccessoryQuality'] = meta
function os:get_cfg_operationalactivity_AccessoryQuality()
local o = {}
setmetatable(o, cfg.operationalactivity.AccessoryQuality)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.level = self:get_int()
o.qulity = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIList'
get_or_create('cfg.ui')['ContentUIList'] = meta
function os:get_cfg_ui_ContentUIList()
local o = {}
setmetatable(o, cfg.ui.ContentUIList)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.m_prefabListItem = self:get_cfg_ui_ContentUITransform()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.achievement.AchievementTitle'
get_or_create('cfg.achievement')['AchievementTitle'] = meta
function os:get_cfg_achievement_AchievementTitle()
local o = {}
setmetatable(o, cfg.achievement.AchievementTitle)
o.id = self:get_int()
o.achievementtype = self:get_int()
o.achievementtypename = self:get_string()
o.type = self:get_int()
o.value = self:get_long()
o.isamount = self:get_bool()
o.name = self:get_string()
o.detail = self:get_string()
o.titleid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.BangPai'
get_or_create('cfg.cmd.action')['BangPai'] = meta
function os:get_cfg_cmd_action_BangPai()
local o = {}
setmetatable(o, cfg.cmd.action.BangPai)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.controller.IntervalController'
get_or_create('cfg.controller')['IntervalController'] = meta
function os:get_cfg_controller_IntervalController()
local o = {}
setmetatable(o, cfg.controller.IntervalController)
o.id = self:get_int()
o.name = self:get_string()
o.introcuction = self:get_string()
o.owner = self:get_string()
o.duration = self:get_float()
o.waitbeforeopen = self:get_int()
o.waitbeforeclose = self:get_int()
o.activity = self:get_cfg_controller_Activity()
o.hour = self:get_int()
o.minute = self:get_int()
o.year = self:get_int()
o.month = self:get_int()
o.day = self:get_int()
o.intervaldays = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.CanPlaySkill'
get_or_create('cfg.cmd.condition')['CanPlaySkill'] = meta
function os:get_cfg_cmd_condition_CanPlaySkill()
local o = {}
setmetatable(o, cfg.cmd.condition.CanPlaySkill)
local _list = self:get_list('int')
o.skillids = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.huiwu.HuiWu'
meta.ELIMINATE_RANK = 3088
meta.BAOSONG_RANK = 12
meta.DIRECT_ELECTION = 4
meta.PRESELECT_BROCAST_TERMINAL = 4
meta.HUIWU_PRESELECTION1_SUCC_MAILID = 4
meta.HUIWU_PRESELECTION1_FAIL_MAILID = 5
meta.HUIWU_PRESELECTION2_SUCC_MAILID = 6
meta.HUIWU_PRESELECTION2_FAIL_MAILID = 7
meta.HUIWU_BATTLE_FAIL_MAILID = 8
meta.HUIWU_BATTLE_WIN_MAILID = 14
meta.HUIWU_CHAMPION_MAILID = 9
meta.HUIWU_CONTINUE_CHAMPION_MAILID = 10
meta.CHAMPION_CTX_CELEBRITY = 1
meta.CHAMPION_CTX_MAIN = 2
get_or_create('cfg.huiwu')['HuiWu'] = meta
function os:get_cfg_huiwu_HuiWu()
local o = {}
setmetatable(o, cfg.huiwu.HuiWu)
o.ectypeid = self:get_int()
o.requirelevel = self:get_cfg_cmd_condition_MinLevel()
o.areaid = self:get_int()
o.countdown = self:get_int()
o.ectypetime = self:get_int()
o.preselect1num = self:get_int()
o.preselect2num = self:get_int()
o.preselect2priornum = self:get_int()
o.preselect2luckynum = self:get_int()
local _list = self:get_list('int')
o.specialranks = _list
local _list = self:get_list('int')
o.specialpositions = _list
o.enrollopen = self:get_cfg_common_WeekTime()
o.enrollend = self:get_cfg_common_WeekTime()
o.preselectopen1 = self:get_cfg_common_WeekTime()
o.preselectend1 = self:get_cfg_common_WeekTime()
o.preselectopen2 = self:get_cfg_common_WeekTime()
o.preselectend2 = self:get_cfg_common_WeekTime()
o.battleopen = self:get_cfg_common_WeekTime()
o.mainregionid = self:get_int()
local _list = self:get_list('cfg_huiwu_BattleTimeControler')
o.battletime = _list
o.daiylyworshiptimes = self:get_cfg_cmd_condition_DayLimit()
o.dailyworshipaward = self:get_cfg_cmd_action_RandomBonus()
o.guessaward = self:get_cfg_cmd_action_MultiBonus()
o.enrollaward = self:get_cfg_cmd_action_MultiBonus()
o.preselectaward = self:get_cfg_cmd_action_MultiBonus()
local _list = self:get_list('cfg_huiwu_rankAward')
o.battleaward = _list
o.continuitytimes = self:get_int()
o.continuityaward = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Heal'
get_or_create('cfg.skill')['Heal'] = meta
function os:get_cfg_skill_Heal()
local o = {}
setmetatable(o, cfg.skill.Heal)
o.target = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExeCondition'
get_or_create('cfg.ectype')['ExeCondition'] = meta
function os:get_cfg_ectype_ExeCondition()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetConfig'
get_or_create('cfg.pet')['PetConfig'] = meta
function os:get_cfg_pet_PetConfig()
local o = {}
setmetatable(o, cfg.pet.PetConfig)
local _list = self:get_list('int')
o.petslotopenlevel = _list
o.equipcd = self:get_cfg_cmd_condition_CoolDown()
o.deadcd = self:get_float()
o.attackcd = self:get_float()
o.follownearradius = self:get_float()
o.followfarradius = self:get_float()
o.guardradius = self:get_float()
o.followcheckinterval = self:get_float()
o.attackspace = self:get_float()
o.idlespace = self:get_float()
local _list = self:get_list('cfg_pet_PetFollow')
o.follow = _list
o.follow_petamount = {}
for _, _V in ipairs(_list) do
o.follow_petamount[_V.petamount] = _V
end
local _list = self:get_list('cfg_pet_AwakeBackgroundTexture')
o.awaketexture = _list
o.awaketexture_awakelevel = {}
for _, _V in ipairs(_list) do
o.awaketexture_awakelevel[_V.awakelevel] = _V
end
o.qualitycolor = self:get_map('int', 'string')
o.washopenlevel = self:get_cfg_cmd_condition_MinLevel()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.setting.GraphicSetting'
get_or_create('cfg.setting')['GraphicSetting'] = meta
function os:get_cfg_setting_GraphicSetting()
local o = {}
setmetatable(o, cfg.setting.GraphicSetting)
o.camerafarclip = self:get_int()
local _list = self:get_list('int')
o.scenestriplist = _list
local _list = self:get_list('int')
o.hideeffectlist = _list
o.hideothernamehp = self:get_bool()
local _list = self:get_list('int')
o.hideflytextlist = _list
o.loddistancemap = self:get_map('int', 'int')
o.fogsettingid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanConsume'
get_or_create('cfg.talisman')['TalismanConsume'] = meta
function os:get_cfg_talisman_TalismanConsume()
local o = {}
setmetatable(o, cfg.talisman.TalismanConsume)
o.consumetype = self:get_string()
local _list = self:get_list('int')
o.itemid = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Stamina'
get_or_create('cfg.cmd.action')['Stamina'] = meta
function os:get_cfg_cmd_action_Stamina()
local o = {}
setmetatable(o, cfg.cmd.action.Stamina)
o.amount = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.active.SystemLookBack'
get_or_create('cfg.active')['SystemLookBack'] = meta
function os:get_cfg_active_SystemLookBack()
local o = {}
setmetatable(o, cfg.active.SystemLookBack)
o.eventtype = self:get_int()
o.name = self:get_string()
o.systemicon = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MinFamilyShopLevel'
get_or_create('cfg.cmd.condition')['MinFamilyShopLevel'] = meta
function os:get_cfg_cmd_condition_MinFamilyShopLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.MinFamilyShopLevel)
o.level = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.OwnTask'
get_or_create('cfg.cmd.condition')['OwnTask'] = meta
function os:get_cfg_cmd_condition_OwnTask()
local o = {}
setmetatable(o, cfg.cmd.condition.OwnTask)
o.taskid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITweenTransform'
get_or_create('cfg.ui')['ContentUITweenTransform'] = meta
function os:get_cfg_ui_ContentUITweenTransform()
local o = {}
setmetatable(o, cfg.ui.ContentUITweenTransform)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.style = self:get_string()
o.animationCurve = self:get_cfg_ui_AnimationCurve()
o.duration = self:get_float()
o.delay = self:get_float()
o.tweenGroup = self:get_int()
o.ignoreTimeScale = self:get_bool()
o.from = self:get_cfg_ui_ContentUITransform()
o.to = self:get_cfg_ui_ContentUITransform()
o.parentWhenFinished = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EquipSuits'
get_or_create('cfg.equip')['EquipSuits'] = meta
function os:get_cfg_equip_EquipSuits()
local o = {}
setmetatable(o, cfg.equip.EquipSuits)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('int')
o.includeid = _list
local _list = self:get_list('cfg_equip_SuitsData')
o.suitsbonus = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GFX'
get_or_create('cfg.ectype')['GFX'] = meta
function os:get_cfg_ectype_GFX()
local o = {}
setmetatable(o, cfg.ectype.GFX)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.GetBuff'
get_or_create('cfg.pet')['GetBuff'] = meta
function os:get_cfg_pet_GetBuff()
local o = {}
setmetatable(o, cfg.pet.GetBuff)
o.buffid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.LoginDay'
get_or_create('cfg.cmd.condition')['LoginDay'] = meta
function os:get_cfg_cmd_condition_LoginDay()
local o = {}
setmetatable(o, cfg.cmd.condition.LoginDay)
o.day = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pay.Charge'
get_or_create('cfg.pay')['Charge'] = meta
function os:get_cfg_pay_Charge()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.ExpTable'
get_or_create('cfg.role')['ExpTable'] = meta
function os:get_cfg_role_ExpTable()
local o = {}
setmetatable(o, cfg.role.ExpTable)
o.level = self:get_int()
o.exp = self:get_long()
o.bonusexp = self:get_long()
o.partyexp = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.TrailAndAttackThenWalkBack'
get_or_create('cfg.ai')['TrailAndAttackThenWalkBack'] = meta
function os:get_cfg_ai_TrailAndAttackThenWalkBack()
local o = {}
setmetatable(o, cfg.ai.TrailAndAttackThenWalkBack)
o.guardradius = self:get_float()
o.choosetargetpolicy = self:get_cfg_ai_ChooseTargetPolicy()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.If2'
get_or_create('cfg.ai')['If2'] = meta
function os:get_cfg_ai_If2()
local o = {}
setmetatable(o, cfg.ai.If2)
o.condition = self:get_cfg_ai_BoolExpr()
o.iftrue = self:get_cfg_ai_Expression()
o.iffalse = self:get_cfg_ai_Expression()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.dailyactivity.DailyEctypeTab'
get_or_create('cfg.dailyactivity')['DailyEctypeTab'] = meta
function os:get_cfg_dailyactivity_DailyEctypeTab()
local o = {}
setmetatable(o, cfg.dailyactivity.DailyEctypeTab)
o.order = self:get_int()
o.name = self:get_string()
o.ectypetype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Drop'
get_or_create('cfg.cmd.action')['Drop'] = meta
function os:get_cfg_cmd_action_Drop()
local o = {}
setmetatable(o, cfg.cmd.action.Drop)
o.dropid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotAssets'
meta.maxloadtime = 5
get_or_create('cfg.plot')['PlotAssets'] = meta
function os:get_cfg_plot_PlotAssets()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.StoryNoteLevel'
get_or_create('cfg.operationalactivity')['StoryNoteLevel'] = meta
function os:get_cfg_operationalactivity_StoryNoteLevel()
local o = {}
setmetatable(o, cfg.operationalactivity.StoryNoteLevel)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.PatrolInfo'
get_or_create('cfg.map')['PatrolInfo'] = meta
function os:get_cfg_map_PatrolInfo()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Condition'
get_or_create('cfg.cmd.condition')['Condition'] = meta
function os:get_cfg_cmd_condition_Condition()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bag.SlotCondition'
get_or_create('cfg.bag')['SlotCondition'] = meta
function os:get_cfg_bag_SlotCondition()
local o = {}
setmetatable(o, cfg.bag.SlotCondition)
o.bagtype = self:get_int()
o.slotindex = self:get_int()
o.condition = self:get_cfg_cmd_condition_Condition()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanHelpInfo'
get_or_create('cfg.talisman')['TalismanHelpInfo'] = meta
function os:get_cfg_talisman_TalismanHelpInfo()
local o = {}
setmetatable(o, cfg.talisman.TalismanHelpInfo)
o.indexname = self:get_string()
o.helpinfo = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Names'
get_or_create('cfg.role')['Names'] = meta
function os:get_cfg_role_Names()
local o = {}
setmetatable(o, cfg.role.Names)
local _list = self:get_list('string')
o.names = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.EffectInfo'
get_or_create('cfg.buff')['EffectInfo'] = meta
function os:get_cfg_buff_EffectInfo()
local o = {}
setmetatable(o, cfg.buff.EffectInfo)
o.effectid = self:get_int()
o.description = self:get_string()
o.endconditiontype = self:get_int()
local _list = self:get_list('float')
o.duration = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIBasicSprite'
get_or_create('cfg.ui')['ContentUIBasicSprite'] = meta
function os:get_cfg_ui_ContentUIBasicSprite()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.AlterGuide'
get_or_create('cfg.ectype')['AlterGuide'] = meta
function os:get_cfg_ectype_AlterGuide()
local o = {}
setmetatable(o, cfg.ectype.AlterGuide)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.content = self:get_string()
local _list = self:get_list('int')
o.guideparams = _list
o.bpathfinding = self:get_bool()
o.targetposition = self:get_cfg_map_Vector2()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.Mall'
get_or_create('cfg.mall')['Mall'] = meta
function os:get_cfg_mall_Mall()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.OpenEctype'
get_or_create('cfg.cmd.condition')['OpenEctype'] = meta
function os:get_cfg_cmd_condition_OpenEctype()
local o = {}
setmetatable(o, cfg.cmd.condition.OpenEctype)
o.ectypeid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MinFamilyLevel'
get_or_create('cfg.cmd.condition')['MinFamilyLevel'] = meta
function os:get_cfg_cmd_condition_MinFamilyLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.MinFamilyLevel)
o.level = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemFlower'
get_or_create('cfg.item')['ItemFlower'] = meta
function os:get_cfg_item_ItemFlower()
local o = {}
setmetatable(o, cfg.item.ItemFlower)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.flowertype = self:get_int()
o.frienddegree = self:get_int()
o.charmdegree = self:get_int()
o.image = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.controller.Normal'
get_or_create('cfg.controller')['Normal'] = meta
function os:get_cfg_controller_Normal()
local o = {}
setmetatable(o, cfg.controller.Normal)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIButtonScale'
get_or_create('cfg.ui')['ContentUIButtonScale'] = meta
function os:get_cfg_ui_ContentUIButtonScale()
local o = {}
setmetatable(o, cfg.ui.ContentUIButtonScale)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.tweenTarget = self:get_cfg_ui_ContentUITransform()
o.hover = self:get_cfg_ui_Vector3()
o.pressed = self:get_cfg_ui_Vector3()
o.duration = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.RepeatBonus'
get_or_create('cfg.cmd.action')['RepeatBonus'] = meta
function os:get_cfg_cmd_action_RepeatBonus()
local o = {}
setmetatable(o, cfg.cmd.action.RepeatBonus)
o.bonus = self:get_cfg_cmd_action_Bonus()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.EnviromentOperate'
get_or_create('cfg.ectype')['EnviromentOperate'] = meta
function os:get_cfg_ectype_EnviromentOperate()
local o = {}
setmetatable(o, cfg.ectype.EnviromentOperate)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.VipLimitsLite'
get_or_create('cfg.cmd.condition')['VipLimitsLite'] = meta
function os:get_cfg_cmd_condition_VipLimitsLite()
local o = {}
setmetatable(o, cfg.cmd.condition.VipLimitsLite)
local _list = self:get_list('int')
o.entertimes = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.QTEButton'
get_or_create('cfg.plot')['QTEButton'] = meta
function os:get_cfg_plot_QTEButton()
local o = {}
setmetatable(o, cfg.plot.QTEButton)
o.mode = self:get_int()
o.count = self:get_int()
o.number = self:get_int()
o.posx = self:get_float()
o.posy = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.MaimaiRelationship'
get_or_create('cfg.friend')['MaimaiRelationship'] = meta
function os:get_cfg_friend_MaimaiRelationship()
local o = {}
setmetatable(o, cfg.friend.MaimaiRelationship)
o.relationship = self:get_int()
o.gender = self:get_int()
o.nametext = self:get_string()
o.deletetext = self:get_string()
o.reqgender = self:get_int()
o.maxnum = self:get_int()
o.correspondingrelationshipmale = self:get_int()
o.correspondingrelationshipfemale = self:get_int()
o.icon = self:get_string()
o.basicrelation = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AccessoryProperty'
get_or_create('cfg.equip')['AccessoryProperty'] = meta
function os:get_cfg_equip_AccessoryProperty()
local o = {}
setmetatable(o, cfg.equip.AccessoryProperty)
o.propertyid = self:get_int()
o.weight = self:get_int()
o.minvalue = self:get_float()
o.maxvalue = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.RegionEffect'
get_or_create('cfg.ectype')['RegionEffect'] = meta
function os:get_cfg_ectype_RegionEffect()
local o = {}
setmetatable(o, cfg.ectype.RegionEffect)
o.effect = self:get_string()
o.name = self:get_string()
o.position = self:get_cfg_map_Vector2()
o.angle = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.ContinuityLogin'
get_or_create('cfg.bonus')['ContinuityLogin'] = meta
function os:get_cfg_bonus_ContinuityLogin()
local o = {}
setmetatable(o, cfg.bonus.ContinuityLogin)
o.dayscount = self:get_int()
o.opentimes = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.LandScape'
get_or_create('cfg.map')['LandScape'] = meta
function os:get_cfg_map_LandScape()
local o = {}
setmetatable(o, cfg.map.LandScape)
o.id = self:get_int()
o.scenepath = self:get_string()
local _list = self:get_list('cfg_map_Controller')
o.controllers = _list
o.controllers_id = {}
for _, _V in ipairs(_list) do
o.controllers_id[_V.id] = _V
end
local _list = self:get_list('cfg_map_LandscapeEntrance')
o.entrances = _list
o.entrances_id = {}
for _, _V in ipairs(_list) do
o.entrances_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.BossAI'
get_or_create('cfg.ai')['BossAI'] = meta
function os:get_cfg_ai_BossAI()
local o = {}
setmetatable(o, cfg.ai.BossAI)
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilySkillCost'
get_or_create('cfg.family')['FamilySkillCost'] = meta
function os:get_cfg_family_FamilySkillCost()
local o = {}
setmetatable(o, cfg.family.FamilySkillCost)
o.skillid = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
local _list = self:get_list('cfg_family_SkillInfo')
o.skillinfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.LingJingMall'
get_or_create('cfg.mall')['LingJingMall'] = meta
function os:get_cfg_mall_LingJingMall()
local o = {}
setmetatable(o, cfg.mall.LingJingMall)
o.id = self:get_int()
o.itemid = self:get_cfg_cmd_action_OneItem()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.cost = self:get_cfg_cmd_condition_Currency()
o.introduce = self:get_string()
o.limitlist = self:get_cfg_cmd_condition_Limits()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyName'
get_or_create('cfg.family')['FamilyName'] = meta
function os:get_cfg_family_FamilyName()
local o = {}
setmetatable(o, cfg.family.FamilyName)
local _list = self:get_list('string')
o.firstname = _list
local _list = self:get_list('string')
o.lastname = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.HighLottery'
get_or_create('cfg.lottery')['HighLottery'] = meta
function os:get_cfg_lottery_HighLottery()
local o = {}
setmetatable(o, cfg.lottery.HighLottery)
o.id = self:get_int()
o.requirecurrency = self:get_cfg_cmd_condition_Currency()
o.requireitem = self:get_cfg_cmd_condition_OneItem()
o.requireitem2 = self:get_cfg_cmd_condition_OneItem()
o.refreshtimes = self:get_cfg_cmd_condition_DayLimit()
o.refreshinterval = self:get_cfg_cmd_condition_CoolDown()
o.recievedcurrency = self:get_cfg_cmd_action_Currency()
local _list = self:get_list('cfg_lottery_HighLotteryDetail')
o.lotterylist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Controller'
get_or_create('cfg.map')['Controller'] = meta
function os:get_cfg_map_Controller()
local o = {}
setmetatable(o, cfg.map.Controller)
o.id = self:get_int()
local _list = self:get_list('cfg_map_Deployment')
o.deployments = _list
o.deployments_deploymentid = {}
for _, _V in ipairs(_list) do
o.deployments_deploymentid[_V.deploymentid] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.StoryEctype'
get_or_create('cfg.ectype')['StoryEctype'] = meta
function os:get_cfg_ectype_StoryEctype()
local o = {}
setmetatable(o, cfg.ectype.StoryEctype)
o.id = self:get_int()
o.storyname = self:get_string()
o.introduction = self:get_string()
o.chapter = self:get_int()
o.section = self:get_int()
o.costtili = self:get_cfg_cmd_condition_TiLi()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.battlepower = self:get_int()
o.sweepcondition = self:get_int()
local _list = self:get_list('cfg_ectype_StarConditionInfo')
o.starcondition = _list
o.starbonus = self:get_cfg_cmd_action_MultiBonus()
o.ectypedrop = self:get_cfg_cmd_action_MultiBonus()
local _list = self:get_list('int')
o.showitems = _list
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.bgmpic = self:get_string()
o.ifend = self:get_bool()
o.storybossicon = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Move'
get_or_create('cfg.ectype')['Move'] = meta
function os:get_cfg_ectype_Move()
local o = {}
setmetatable(o, cfg.ectype.Move)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.UIMainRedDot'
get_or_create('cfg.ui')['UIMainRedDot'] = meta
function os:get_cfg_ui_UIMainRedDot()
local o = {}
setmetatable(o, cfg.ui.UIMainRedDot)
o.id = self:get_int()
o.functionname = self:get_int()
o.opentype = self:get_int()
o.conid = self:get_int()
o.dottype = self:get_int()
o.desc = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.EctypeGrade'
get_or_create('cfg.ui')['EctypeGrade'] = meta
function os:get_cfg_ui_EctypeGrade()
local o = {}
setmetatable(o, cfg.ui.EctypeGrade)
o.index = self:get_string()
o.time = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.WorldBoss'
meta.COINPOINT_OPEN_LEVEL = 30
meta.LINE_PARA = 0.5
meta.TALK_INTERVAL = 5
meta.TALK_LAST = 5
get_or_create('cfg.ectype')['WorldBoss'] = meta
function os:get_cfg_ectype_WorldBoss()
local o = {}
setmetatable(o, cfg.ectype.WorldBoss)
o.id = self:get_int()
o.monsterid = self:get_int()
o.mapid = self:get_int()
o.position = self:get_cfg_map_Vector2()
o.fightforce = self:get_int()
local _list = self:get_list('int')
o.showbonusid = _list
o.bosstalk = self:get_string()
o.bossdes = self:get_string()
o.prebroadcast = self:get_cfg_ectype_Broadcast()
o.endtime = self:get_int()
local _list = self:get_list('cfg_ectype_TimeControler')
o.opentimes = _list
o.localposx = self:get_float()
o.localposy = self:get_float()
o.scale = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.NPC'
get_or_create('cfg.guide')['NPC'] = meta
function os:get_cfg_guide_NPC()
local o = {}
setmetatable(o, cfg.guide.NPC)
o.type = self:get_int()
local _list = self:get_list('float')
o.dialogpos = _list
o.text = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.CircleRegionSet'
get_or_create('cfg.map')['CircleRegionSet'] = meta
function os:get_cfg_map_CircleRegionSet()
local o = {}
setmetatable(o, cfg.map.CircleRegionSet)
o.id = self:get_int()
local _list = self:get_list('cfg_map_IndexedCircleRegion')
o.regions = _list
o.regions_id = {}
for _, _V in ipairs(_list) do
o.regions_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.RuneInfo'
get_or_create('cfg.ectype')['RuneInfo'] = meta
function os:get_cfg_ectype_RuneInfo()
local o = {}
setmetatable(o, cfg.ectype.RuneInfo)
o.runeid = self:get_int()
o.weight = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.TalismanLevel'
get_or_create('cfg.cmd.action')['TalismanLevel'] = meta
function os:get_cfg_cmd_action_TalismanLevel()
local o = {}
setmetatable(o, cfg.cmd.action.TalismanLevel)
o.amount = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Prologue'
meta.MAX_BOSS_BODY_RADIUS = 30
get_or_create('cfg.ectype')['Prologue'] = meta
function os:get_cfg_ectype_Prologue()
local o = {}
setmetatable(o, cfg.ectype.Prologue)
o.level = self:get_int()
o.id = self:get_int()
local _list = self:get_list('cfg_ectype_BeginnerEquip')
o.professionequips = _list
o.professionequips_profession = {}
for _, _V in ipairs(_list) do
o.professionequips_profession[_V.profession] = _V
end
o.flystartareax = self:get_float()
o.flystartareaz = self:get_float()
o.flyrouteid = self:get_int()
o.flyguideid = self:get_int()
o.flyendareax = self:get_float()
o.flyendareaz = self:get_float()
o.flyregionid = self:get_int()
o.cg_create_first_role = self:get_string()
o.cg_create_first_role_mode = self:get_int()
o.cg_ectype_end = self:get_string()
o.cg_ectype_end_mode = self:get_int()
o.statusinfo = self:get_cfg_fight_Attr()
o.banmusic = self:get_map('int', 'int')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Equiped'
get_or_create('cfg.cmd.condition')['Equiped'] = meta
function os:get_cfg_cmd_condition_Equiped()
local o = {}
setmetatable(o, cfg.cmd.condition.Equiped)
local _list = self:get_list('int')
o.id = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.SetAbnormalState'
get_or_create('cfg.buff')['SetAbnormalState'] = meta
function os:get_cfg_buff_SetAbnormalState()
local o = {}
setmetatable(o, cfg.buff.SetAbnormalState)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.statetype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyJob'
get_or_create('cfg.family')['FamilyJob'] = meta
function os:get_cfg_family_FamilyJob()
local o = {}
setmetatable(o, cfg.family.FamilyJob)
o.jobid = self:get_int()
o.name = self:get_int()
o.displayname = self:get_string()
o.enrollperm = self:get_bool()
o.kickoutperm = self:get_bool()
o.familyskillperm = self:get_bool()
o.caneditdeclaration = self:get_bool()
o.caneditannouncement = self:get_bool()
local _list = self:get_list('int')
o.amount = _list
o.appointjobs = self:get_set('int')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Cloth'
get_or_create('cfg.equip')['Cloth'] = meta
function os:get_cfg_equip_Cloth()
local o = {}
setmetatable(o, cfg.equip.Cloth)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.nextid = self:get_int()
o.extraequipid = self:get_int()
o.upgradecurrencycost = self:get_cfg_cmd_condition_XuNiBi()
o.carryingitemnum = self:get_int()
o.recommendrate = self:get_float()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.property = _list
o.male = self:get_string()
o.female = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Const'
meta.MAX_LEVEL = 90
meta.PLAYER_SELECT_TARGET_RADIUS = 15
meta.PLAYER_LOSE_TARGET_RADIUS = 20
meta.SMART_ATTACK = 1
meta.LOCAL_NAVMESH = 1
get_or_create('cfg.role')['Const'] = meta
function os:get_cfg_role_Const()
local o = {}
setmetatable(o, cfg.role.Const)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilySkill'
get_or_create('cfg.family')['FamilySkill'] = meta
function os:get_cfg_family_FamilySkill()
local o = {}
setmetatable(o, cfg.family.FamilySkill)
o.skilllvl = self:get_int()
o.requirefamilycapital = self:get_cfg_cmd_condition_FamilyMoney()
o.requirefamilylvl = self:get_cfg_cmd_condition_MinFamilyLevel()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SpecialSwitch'
get_or_create('cfg.ectype')['SpecialSwitch'] = meta
function os:get_cfg_ectype_SpecialSwitch()
local o = {}
setmetatable(o, cfg.ectype.SpecialSwitch)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.SceneObjDeployment'
get_or_create('cfg.map')['SceneObjDeployment'] = meta
function os:get_cfg_map_SceneObjDeployment()
local o = {}
setmetatable(o, cfg.map.SceneObjDeployment)
o.deploymentid = self:get_int()
o.objid = self:get_int()
o.location = self:get_cfg_map_MultiPoints()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.KillMonsterData'
get_or_create('cfg.task')['KillMonsterData'] = meta
function os:get_cfg_task_KillMonsterData()
local o = {}
setmetatable(o, cfg.task.KillMonsterData)
o.monsterid = self:get_int()
o.monstercount = self:get_int()
o.dropitemid = self:get_int()
o.dropitemcount = self:get_int()
o.probability = self:get_float()
o.rolelevellimit = self:get_bool()
o.droponeinteam = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Limit'
get_or_create('cfg.cmd.condition')['Limit'] = meta
function os:get_cfg_cmd_condition_Limit()
local o = {}
setmetatable(o, cfg.cmd.condition.Limit)
o.type = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SpeedLvMsg'
get_or_create('cfg.ectype')['SpeedLvMsg'] = meta
function os:get_cfg_ectype_SpeedLvMsg()
local o = {}
setmetatable(o, cfg.ectype.SpeedLvMsg)
o.id = self:get_int()
o.lv = self:get_cfg_cmd_condition_MinMaxLevel()
o.showbonusid = self:get_cfg_cmd_action_OneItems()
o.ectypeid = self:get_int()
o.bossref = self:get_cfg_map_MonsterSpawn()
o.bossregion = self:get_int()
o.team1 = self:get_cfg_ectype_TeamRegionInfo()
o.team2 = self:get_cfg_ectype_TeamRegionInfo()
local _list = self:get_list('cfg_ectype_SpeedMonsterRefInfo')
o.monsters = _list
local _list = self:get_list('cfg_ectype_GradeBonus')
o.gradebonus = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.HitZone'
meta.CYLINDER = 1
meta.RECT = 0
meta.TRIANGLE = 2
get_or_create('cfg.skill')['HitZone'] = meta
function os:get_cfg_skill_HitZone()
local o = {}
setmetatable(o, cfg.skill.HitZone)
o.id = self:get_int()
o.shape = self:get_int()
o.zoffset = self:get_float()
o.xlength = self:get_float()
o.bottomheight = self:get_float()
o.topheight = self:get_float()
o.zlength = self:get_float()
o.yangle = self:get_float()
o.yrotationangle = self:get_float()
o.maxtarget = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.FriendHelpInfo'
get_or_create('cfg.friend')['FriendHelpInfo'] = meta
function os:get_cfg_friend_FriendHelpInfo()
local o = {}
setmetatable(o, cfg.friend.FriendHelpInfo)
o.index = self:get_string()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.LingJing'
get_or_create('cfg.cmd.action')['LingJing'] = meta
function os:get_cfg_cmd_action_LingJing()
local o = {}
setmetatable(o, cfg.cmd.action.LingJing)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITween'
get_or_create('cfg.ui')['ContentUITween'] = meta
function os:get_cfg_ui_ContentUITween()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AnnealRate'
meta.RATE_BASE_NUMBER = 10000
get_or_create('cfg.equip')['AnnealRate'] = meta
function os:get_cfg_equip_AnnealRate()
local o = {}
setmetatable(o, cfg.equip.AnnealRate)
o.level = self:get_int()
o.rate = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.modulehelpinfo.ModuleHelpInfo'
get_or_create('cfg.modulehelpinfo')['ModuleHelpInfo'] = meta
function os:get_cfg_modulehelpinfo_ModuleHelpInfo()
local o = {}
setmetatable(o, cfg.modulehelpinfo.ModuleHelpInfo)
o.modulename = self:get_string()
o.infos = self:get_map('string', 'cfg_modulehelpinfo_HelpInfo')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetMaxLevel'
get_or_create('cfg.operationalactivity')['PetMaxLevel'] = meta
function os:get_cfg_operationalactivity_PetMaxLevel()
local o = {}
setmetatable(o, cfg.operationalactivity.PetMaxLevel)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Case'
get_or_create('cfg.ai')['Case'] = meta
function os:get_cfg_ai_Case()
local o = {}
setmetatable(o, cfg.ai.Case)
o.condition = self:get_cfg_ai_BoolExpr()
o.expression = self:get_cfg_ai_Expression()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.ExpandAttr'
get_or_create('cfg.fight')['ExpandAttr'] = meta
function os:get_cfg_fight_ExpandAttr()
local o = {}
setmetatable(o, cfg.fight.ExpandAttr)
o.critrate = self:get_float()
o.critvalue = self:get_float()
o.critresistrate = self:get_float()
o.critresistvalue = self:get_float()
o.excellentrate = self:get_float()
o.excellentvalue = self:get_float()
o.excellentresistrate = self:get_float()
o.excellentresistvalue = self:get_float()
o.lucky = self:get_float()
o.attackmultirate = self:get_float()
o.defencemultirate = self:get_float()
o.abnormalresistrate = self:get_float()
o.abnormalhitrate = self:get_float()
o.movespeed = self:get_float()
o.heal = self:get_float()
o.damagetohuman = self:get_float()
o.damagetopet = self:get_float()
o.resistdamagefromhuman = self:get_float()
o.resistdamagefrompet = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Jewelry'
get_or_create('cfg.equip')['Jewelry'] = meta
function os:get_cfg_equip_Jewelry()
local o = {}
setmetatable(o, cfg.equip.Jewelry)
o.jewelryid = self:get_int()
o.jewelryname = self:get_string()
o.icon = self:get_string()
o.introduce = self:get_string()
o.quality = self:get_int()
o.propertytype = self:get_int()
o.basicvalue = self:get_float()
o.maturerate = self:get_float()
o.quatilyexp = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotIndex'
get_or_create('cfg.plot')['PlotIndex'] = meta
function os:get_cfg_plot_PlotIndex()
local o = {}
setmetatable(o, cfg.plot.PlotIndex)
o.index = self:get_string()
o.id = self:get_int()
o.displayname = self:get_string()
o.introduction = self:get_string()
o.mapname = self:get_string()
o.mapid = self:get_int()
o.cutscenegroup = self:get_cfg_plot_CutsceneGroup()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.npc.NPC'
get_or_create('cfg.npc')['NPC'] = meta
function os:get_cfg_npc_NPC()
local o = {}
setmetatable(o, cfg.npc.NPC)
o.id = self:get_int()
o.name = self:get_string()
o.isexclusive = self:get_bool()
o.describename = self:get_string()
o.title = self:get_string()
o.scene = self:get_string()
o.modelname = self:get_string()
o.speakrate = self:get_float()
local _list = self:get_list('string')
o.opentext = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.EquipSource'
get_or_create('cfg.item')['EquipSource'] = meta
function os:get_cfg_item_EquipSource()
local o = {}
setmetatable(o, cfg.item.EquipSource)
o.type = self:get_int()
local _list = self:get_list('cfg_item_SourceInfo')
o.sourcelist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.commoneffect.SoundEffect'
get_or_create('cfg.commoneffect')['SoundEffect'] = meta
function os:get_cfg_commoneffect_SoundEffect()
local o = {}
setmetatable(o, cfg.commoneffect.SoundEffect)
o.timeline = self:get_float()
o.probability = self:get_float()
o.volumemin = self:get_float()
o.volumemax = self:get_float()
local _list = self:get_list('string')
o.pathlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.CanJoinActivity'
get_or_create('cfg.cmd.condition')['CanJoinActivity'] = meta
function os:get_cfg_cmd_condition_CanJoinActivity()
local o = {}
setmetatable(o, cfg.cmd.condition.CanJoinActivity)
o.activitytype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Or'
get_or_create('cfg.ai')['Or'] = meta
function os:get_cfg_ai_Or()
local o = {}
setmetatable(o, cfg.ai.Or)
o.condition1 = self:get_cfg_ai_BoolExpr()
o.condition2 = self:get_cfg_ai_BoolExpr()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.RandomPatrol'
get_or_create('cfg.map')['RandomPatrol'] = meta
function os:get_cfg_map_RandomPatrol()
local o = {}
setmetatable(o, cfg.map.RandomPatrol)
o.regionsetid = self:get_int()
o.regionid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.system.SystemConfig'
get_or_create('cfg.system')['SystemConfig'] = meta
function os:get_cfg_system_SystemConfig()
local o = {}
setmetatable(o, cfg.system.SystemConfig)
o.dayidlehour = self:get_int()
o.keepaliveinterval = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Currency'
get_or_create('cfg.cmd.action')['Currency'] = meta
function os:get_cfg_cmd_action_Currency()
local o = {}
setmetatable(o, cfg.cmd.action.Currency)
o.type = self:get_int()
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetConsume'
get_or_create('cfg.pet')['PetConsume'] = meta
function os:get_cfg_pet_PetConsume()
local o = {}
setmetatable(o, cfg.pet.PetConsume)
local _list = self:get_list('int')
o.consumeitem = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.ChengJiu'
get_or_create('cfg.cmd.action')['ChengJiu'] = meta
function os:get_cfg_cmd_action_ChengJiu()
local o = {}
setmetatable(o, cfg.cmd.action.ChengJiu)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.exchange.ExchangeIndex'
get_or_create('cfg.exchange')['ExchangeIndex'] = meta
function os:get_cfg_exchange_ExchangeIndex()
local o = {}
setmetatable(o, cfg.exchange.ExchangeIndex)
o.id = self:get_int()
o.name = self:get_int()
o.displayname = self:get_string()
o.parentindex = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pathfly.Areocraft'
meta.defaultmodelname = "空瑞"
meta.startanimname = "voyage"
meta.loopanimname = "stand"
meta.endanimname = "voyaged"
get_or_create('cfg.pathfly')['Areocraft'] = meta
function os:get_cfg_pathfly_Areocraft()
local o = {}
setmetatable(o, cfg.pathfly.Areocraft)
o.id = self:get_int()
o.modelname = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddPropertyByLevel'
get_or_create('cfg.buff')['AddPropertyByLevel'] = meta
function os:get_cfg_buff_AddPropertyByLevel()
local o = {}
setmetatable(o, cfg.buff.AddPropertyByLevel)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
local _list = self:get_list('string')
o.introduction = _list
o.property = self:get_int()
local _list = self:get_list('float')
o.value = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaRivalBound'
get_or_create('cfg.arena')['ArenaRivalBound'] = meta
function os:get_cfg_arena_ArenaRivalBound()
local o = {}
setmetatable(o, cfg.arena.ArenaRivalBound)
o.low = self:get_int()
o.up = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.CopyBonus'
get_or_create('cfg.cmd.action')['CopyBonus'] = meta
function os:get_cfg_cmd_action_CopyBonus()
local o = {}
setmetatable(o, cfg.cmd.action.CopyBonus)
o.bonus = self:get_cfg_cmd_action_Bonus()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.JoinFamily'
get_or_create('cfg.cmd.condition')['JoinFamily'] = meta
function os:get_cfg_cmd_condition_JoinFamily()
local o = {}
setmetatable(o, cfg.cmd.condition.JoinFamily)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.audio.StoryDialogSound'
get_or_create('cfg.audio')['StoryDialogSound'] = meta
function os:get_cfg_audio_StoryDialogSound()
local o = {}
setmetatable(o, cfg.audio.StoryDialogSound)
o.id = self:get_int()
o.name = self:get_string()
o.maleaudio = self:get_int()
o.femaleaudio = self:get_int()
o.boyaudio = self:get_int()
o.girlaudio = self:get_int()
o.npcaudio = self:get_int()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.XuNiBi'
get_or_create('cfg.cmd.action')['XuNiBi'] = meta
function os:get_cfg_cmd_action_XuNiBi()
local o = {}
setmetatable(o, cfg.cmd.action.XuNiBi)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.SkillInfo'
get_or_create('cfg.family')['SkillInfo'] = meta
function os:get_cfg_family_SkillInfo()
local o = {}
setmetatable(o, cfg.family.SkillInfo)
o.property = self:get_cfg_equip_EquipPropertyData()
o.requirefamilycapital = self:get_cfg_cmd_condition_FamilyMoney()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pay.MonthCard'
get_or_create('cfg.pay')['MonthCard'] = meta
function os:get_cfg_pay_MonthCard()
local o = {}
setmetatable(o, cfg.pay.MonthCard)
o.chargeid = self:get_int()
o.displayorder = self:get_int()
o.price = self:get_int()
o.platform = self:get_map('int', 'int')
o.getyuanbao = self:get_cfg_cmd_action_YuanBao()
o.getbindyuanbao = self:get_cfg_cmd_action_BindYuanBao()
o.notetext = self:get_string()
o.backgourndimage = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.PetLevel'
get_or_create('cfg.cmd.condition')['PetLevel'] = meta
function os:get_cfg_cmd_condition_PetLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.PetLevel)
o.level = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MinLevel'
get_or_create('cfg.cmd.condition')['MinLevel'] = meta
function os:get_cfg_cmd_condition_MinLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.MinLevel)
o.level = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FamilyTeam'
get_or_create('cfg.ectype')['FamilyTeam'] = meta
function os:get_cfg_ectype_FamilyTeam()
local o = {}
setmetatable(o, cfg.ectype.FamilyTeam)
o.ectypeid = self:get_int()
o.rewardfinishnum = self:get_cfg_cmd_condition_DayLimit()
o.minteammembernum = self:get_int()
o.enteradddbuffid = self:get_int()
o.waveinterval = self:get_int()
o.mainregionid = self:get_int()
o.showrewards = self:get_cfg_cmd_action_OneItems()
local _list = self:get_list('cfg_ectype_FamilyTeamLevelMonserInfo')
o.levelmonsterinfos = _list
local _list = self:get_list('cfg_ectype_FamilyTeamLevelReward')
o.levelbonus = _list
o.levelbonus_level = {}
for _, _V in ipairs(_list) do
o.levelbonus_level[_V.level] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.Boss'
meta.picktimes = 3
get_or_create('cfg.family')['Boss'] = meta
function os:get_cfg_family_Boss()
local o = {}
setmetatable(o, cfg.family.Boss)
o.bossid = self:get_int()
o.name = self:get_string()
o.introduction = self:get_string()
o.offsety = self:get_int()
o.scale = self:get_int()
local _list = self:get_list('cfg_family_BossInfo')
o.bossinfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EquipAppend'
get_or_create('cfg.equip')['EquipAppend'] = meta
function os:get_cfg_equip_EquipAppend()
local o = {}
setmetatable(o, cfg.equip.EquipAppend)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_equip_EnhanceData')
o.adddata = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.ShowHideData'
get_or_create('cfg.task')['ShowHideData'] = meta
function os:get_cfg_task_ShowHideData()
local o = {}
setmetatable(o, cfg.task.ShowHideData)
local _list = self:get_list('cfg_task_ShowHideGroup')
o.showhide = _list
o.worldmapid = self:get_int()
o.alias = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.AttrSequence'
get_or_create('cfg.fight')['AttrSequence'] = meta
function os:get_cfg_fight_AttrSequence()
local o = {}
setmetatable(o, cfg.fight.AttrSequence)
local _list = self:get_list('cfg_fight_AttrIdType')
o.basic = _list
local _list = self:get_list('cfg_fight_AttrIdType')
o.advance = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.GroupCoolDown'
get_or_create('cfg.cmd.condition')['GroupCoolDown'] = meta
function os:get_cfg_cmd_condition_GroupCoolDown()
local o = {}
setmetatable(o, cfg.cmd.condition.GroupCoolDown)
o.groupid = self:get_int()
o.time = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.monster.Monster'
meta.DROP_CURRENCY_TYPE = 10200001
meta.TEAM_SHARE_EXP_RADIUS = 50
meta.MONSTER_LEAVE_DELAY_WHEN_DEAD = 5
get_or_create('cfg.monster')['Monster'] = meta
function os:get_cfg_monster_Monster()
local o = {}
setmetatable(o, cfg.monster.Monster)
o.id = self:get_int()
o.name = self:get_string()
o.level = self:get_int()
o.type = self:get_string()
o.camp = self:get_int()
o.monstertype = self:get_int()
local _list = self:get_list('string')
o.battletalk = _list
o.battletalkprobability = self:get_float()
o.attackvoice = self:get_int()
o.beattackvoice = self:get_int()
o.deadvoice = self:get_int()
o.patrolvoice = self:get_int()
o.modelname = self:get_string()
o.scale = self:get_float()
o.playborneffect = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.BossFeed'
get_or_create('cfg.family')['BossFeed'] = meta
function os:get_cfg_family_BossFeed()
local o = {}
setmetatable(o, cfg.family.BossFeed)
o.feedid = self:get_int()
o.feedlimit = self:get_cfg_cmd_condition_VipLimits()
o.exp = self:get_int()
o.buildrate = self:get_cfg_cmd_action_AddFamilyMoneyBuild()
o.familycontribution = self:get_cfg_cmd_action_BangGong()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUISprite'
get_or_create('cfg.ui')['ContentUISprite'] = meta
function os:get_cfg_ui_ContentUISprite()
local o = {}
setmetatable(o, cfg.ui.ContentUISprite)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.depth = self:get_int()
o.width = self:get_int()
o.height = self:get_int()
o.pivot = self:get_string()
o.color = self:get_cfg_ui_Color()
o.autoResizeBoxCollider = self:get_bool()
o.type = self:get_string()
o.flip = self:get_string()
o.centerType = self:get_string()
o.leftType = self:get_string()
o.rightType = self:get_string()
o.bottomType = self:get_string()
o.topType = self:get_string()
o.fillDirection = self:get_string()
o.fillAmount = self:get_float()
o.invert = self:get_bool()
o.atlas = self:get_string()
o.sprite = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Heal'
get_or_create('cfg.cmd.action')['Heal'] = meta
function os:get_cfg_cmd_action_Heal()
local o = {}
setmetatable(o, cfg.cmd.action.Heal)
o.HP = self:get_float()
o.HPPrecent = self:get_float()
o.MP = self:get_float()
o.MPPrecent = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.modulehelpinfo.HelpInfo'
get_or_create('cfg.modulehelpinfo')['HelpInfo'] = meta
function os:get_cfg_modulehelpinfo_HelpInfo()
local o = {}
setmetatable(o, cfg.modulehelpinfo.HelpInfo)
o.title = self:get_string()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.setting.FogSetting'
get_or_create('cfg.setting')['FogSetting'] = meta
function os:get_cfg_setting_FogSetting()
local o = {}
setmetatable(o, cfg.setting.FogSetting)
o.enable = self:get_bool()
o.mode = self:get_int()
o.density = self:get_float()
o.colorred = self:get_int()
o.colorgreen = self:get_int()
o.colorblue = self:get_int()
o.coloralpha = self:get_int()
o.startdistance = self:get_int()
o.enddistance = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddHPByLevelInterval'
get_or_create('cfg.buff')['AddHPByLevelInterval'] = meta
function os:get_cfg_buff_AddHPByLevelInterval()
local o = {}
setmetatable(o, cfg.buff.AddHPByLevelInterval)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
local _list = self:get_list('string')
o.introduction = _list
local _list = self:get_list('float')
o.value = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.EquipPerfuseTotal'
get_or_create('cfg.operationalactivity')['EquipPerfuseTotal'] = meta
function os:get_cfg_operationalactivity_EquipPerfuseTotal()
local o = {}
setmetatable(o, cfg.operationalactivity.EquipPerfuseTotal)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.PlayerActionCrossFade'
get_or_create('cfg.skill')['PlayerActionCrossFade'] = meta
function os:get_cfg_skill_PlayerActionCrossFade()
local o = {}
setmetatable(o, cfg.skill.PlayerActionCrossFade)
o.id = self:get_int()
o.statebegin = self:get_string()
o.stateend = self:get_string()
o.crossfadeduration = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Deployment'
get_or_create('cfg.map')['Deployment'] = meta
function os:get_cfg_map_Deployment()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.RankBonus'
get_or_create('cfg.bonus')['RankBonus'] = meta
function os:get_cfg_bonus_RankBonus()
local o = {}
setmetatable(o, cfg.bonus.RankBonus)
o.ranktype = self:get_int()
local _list = self:get_list('cfg_bonus_RankBonusList')
o.bonuslist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bag.BagConfig'
get_or_create('cfg.bag')['BagConfig'] = meta
function os:get_cfg_bag_BagConfig()
local o = {}
setmetatable(o, cfg.bag.BagConfig)
o.bagtype = self:get_int()
o.stackable = self:get_bool()
o.initcapacity = self:get_int()
o.maxcapacity = self:get_int()
o.unlockgridcost = self:get_cfg_cmd_condition_FixCurrency()
o.sortcd = self:get_cfg_cmd_condition_CoolDown()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MultiCondition'
get_or_create('cfg.cmd.condition')['MultiCondition'] = meta
function os:get_cfg_cmd_condition_MultiCondition()
local o = {}
setmetatable(o, cfg.cmd.condition.MultiCondition)
local _list = self:get_list('cfg_cmd_condition_Condition')
o.conditions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddMPInterval'
get_or_create('cfg.buff')['AddMPInterval'] = meta
function os:get_cfg_buff_AddMPInterval()
local o = {}
setmetatable(o, cfg.buff.AddMPInterval)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.tips.BroadcastCfg'
get_or_create('cfg.tips')['BroadcastCfg'] = meta
function os:get_cfg_tips_BroadcastCfg()
local o = {}
setmetatable(o, cfg.tips.BroadcastCfg)
local _list = self:get_list('int')
o.rolelevelup = _list
o.minanneal = self:get_int()
local _list = self:get_list('int')
o.perfuse = _list
o.mintalismanstar = self:get_int()
o.minpetawake = self:get_int()
o.minorangepetawake = self:get_int()
o.mintalismanawake = self:get_int()
o.minorangetalismanawake = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EquipSortOrder'
meta.EQUIP_SORT_ORDER = "profession,quality,level,type"
meta.EQUIP_SORT_RULE = "desc,desc,desc,desc"
get_or_create('cfg.equip')['EquipSortOrder'] = meta
function os:get_cfg_equip_EquipSortOrder()
local o = {}
setmetatable(o, cfg.equip.EquipSortOrder)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.ModelActionBase'
get_or_create('cfg.skill')['ModelActionBase'] = meta
function os:get_cfg_skill_ModelActionBase()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AnnealItemCost'
get_or_create('cfg.equip')['AnnealItemCost'] = meta
function os:get_cfg_equip_AnnealItemCost()
local o = {}
setmetatable(o, cfg.equip.AnnealItemCost)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('int')
o.itemcost = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.monster.MonsterVoice'
get_or_create('cfg.monster')['MonsterVoice'] = meta
function os:get_cfg_monster_MonsterVoice()
local o = {}
setmetatable(o, cfg.monster.MonsterVoice)
o.voiceid = self:get_int()
o.voicetype = self:get_string()
local _list = self:get_list('int')
o.voicelist = _list
o.playrate = self:get_float()
o.minvolume = self:get_float()
o.maxvolume = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Keyframe'
get_or_create('cfg.ui')['Keyframe'] = meta
function os:get_cfg_ui_Keyframe()
local o = {}
setmetatable(o, cfg.ui.Keyframe)
o.time = self:get_float()
o.value = self:get_float()
o.tangentMode = self:get_int()
o.inTangent = self:get_float()
o.outTangent = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pathfly.PathNode'
get_or_create('cfg.pathfly')['PathNode'] = meta
function os:get_cfg_pathfly_PathNode()
local o = {}
setmetatable(o, cfg.pathfly.PathNode)
o.time = self:get_float()
o.position = self:get_cfg_pathfly_Vector3()
o.rotation = self:get_cfg_pathfly_Vector3()
o.localscale = self:get_cfg_pathfly_Vector3()
o.intangent = self:get_cfg_pathfly_Vector3()
o.outtangent = self:get_cfg_pathfly_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Print'
get_or_create('cfg.ai')['Print'] = meta
function os:get_cfg_ai_Print()
local o = {}
setmetatable(o, cfg.ai.Print)
o.text = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.Task'
get_or_create('cfg.task')['Task'] = meta
function os:get_cfg_task_Task()
local o = {}
setmetatable(o, cfg.task.Task)
o.id = self:get_int()
o.filepath = self:get_string()
o.basic = self:get_cfg_task_TaskBase()
o.accept = self:get_cfg_task_TaskAccept()
o.failed = self:get_cfg_task_TaskFailed()
o.complete = self:get_cfg_task_TaskComplete()
o.reward = self:get_cfg_task_TaskReward()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.character.Model'
get_or_create('cfg.character')['Model'] = meta
function os:get_cfg_character_Model()
local o = {}
setmetatable(o, cfg.character.Model)
o.modeltype = self:get_int()
o.modelname = self:get_string()
o.headicon = self:get_string()
o.portrait = self:get_string()
o.modelpath = self:get_string()
o.avatarid = self:get_string()
o.bodyradius = self:get_float()
o.namehighshift = self:get_float()
o.modelscale = self:get_float()
o.uimodelscalemodify = self:get_float()
o.aperturecolor = self:get_int()
o.aperturescale = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.EctypeCurveSet'
get_or_create('cfg.map')['EctypeCurveSet'] = meta
function os:get_cfg_map_EctypeCurveSet()
local o = {}
setmetatable(o, cfg.map.EctypeCurveSet)
o.id = self:get_int()
local _list = self:get_list('cfg_map_IndexedBezierCurve')
o.curves = _list
o.curves_id = {}
for _, _V in ipairs(_list) do
o.curves_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemSortOrder'
meta.ITEM_SORT_ORDER = "level,quality,number"
meta.ITEM_SORT_RULE = "desc,desc,asc"
get_or_create('cfg.item')['ItemSortOrder'] = meta
function os:get_cfg_item_ItemSortOrder()
local o = {}
setmetatable(o, cfg.item.ItemSortOrder)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.EnterRegion'
get_or_create('cfg.cmd.condition')['EnterRegion'] = meta
function os:get_cfg_cmd_condition_EnterRegion()
local o = {}
setmetatable(o, cfg.cmd.condition.EnterRegion)
local _list = self:get_list('float')
o.coordinate = _list
o.distance = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.TaskComplete'
get_or_create('cfg.task')['TaskComplete'] = meta
function os:get_cfg_task_TaskComplete()
local o = {}
setmetatable(o, cfg.task.TaskComplete)
o.mode = self:get_int()
o.npcid = self:get_int()
o.rolelevel = self:get_int()
o.rolemoney = self:get_int()
o.prestigetype = self:get_int()
o.prestige = self:get_int()
local _list = self:get_list('cfg_task_ItemInfo')
o.collectitem = _list
o.collectitem_itemid = {}
for _, _V in ipairs(_list) do
o.collectitem_itemid[_V.itemid] = _V
end
o.needkillmonster = self:get_bool()
local _list = self:get_list('cfg_task_KillMonsterData')
o.killmonster = _list
o.randomkillmonster = self:get_bool()
o.location = self:get_cfg_task_LocationRangeData()
o.npclocation = self:get_cfg_task_LocationRangeData()
local _list = self:get_list('cfg_task_FinishSpecialEventData')
o.finishspecialevent = _list
o.finishall = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.BroadcastType'
get_or_create('cfg.common')['BroadcastType'] = meta
function os:get_cfg_common_BroadcastType()
local o = {}
setmetatable(o, cfg.common.BroadcastType)
o.id = self:get_int()
o.name = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaMultiPvPBonus'
get_or_create('cfg.arena')['ArenaMultiPvPBonus'] = meta
function os:get_cfg_arena_ArenaMultiPvPBonus()
local o = {}
setmetatable(o, cfg.arena.ArenaMultiPvPBonus)
o.id = self:get_int()
o.score = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.SoundEffect'
get_or_create('cfg.skill')['SoundEffect'] = meta
function os:get_cfg_skill_SoundEffect()
local o = {}
setmetatable(o, cfg.skill.SoundEffect)
o.timeline = self:get_float()
o.probability = self:get_float()
o.volumemin = self:get_float()
o.volumemax = self:get_float()
local _list = self:get_list('string')
o.pathlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.HighLotteryDetail'
get_or_create('cfg.lottery')['HighLotteryDetail'] = meta
function os:get_cfg_lottery_HighLotteryDetail()
local o = {}
setmetatable(o, cfg.lottery.HighLotteryDetail)
o.lotterycount = self:get_int()
o.requiremultiple = self:get_int()
o.bonuslist = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExpEctype'
meta.OPEN_CG = 10009005
meta.CHANGE_CG = 10009006
get_or_create('cfg.ectype')['ExpEctype'] = meta
function os:get_cfg_ectype_ExpEctype()
local o = {}
setmetatable(o, cfg.ectype.ExpEctype)
o.id = self:get_int()
o.costtili = self:get_cfg_cmd_condition_TiLi()
o.ectypetype = self:get_int()
o.storyname = self:get_string()
o.introduction = self:get_string()
o.decs = self:get_string()
o.backgroundpic = self:get_string()
local _list = self:get_list('int')
o.showbonusid = _list
o.mainregionid = self:get_int()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.showlv = self:get_string()
o.battlepower = self:get_int()
o.doublebonustime = self:get_int()
o.ectyperandomdrop = self:get_cfg_cmd_action_RandomBonus()
o.monsterreftime = self:get_int()
o.buffstartindex = self:get_int()
local _list = self:get_list('cfg_ectype_ExpMonsterInfo')
o.monsters = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.NoviceGuideText'
get_or_create('cfg.guide')['NoviceGuideText'] = meta
function os:get_cfg_guide_NoviceGuideText()
local o = {}
setmetatable(o, cfg.guide.NoviceGuideText)
o.id = self:get_int()
o.desc = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ArenaEctype'
get_or_create('cfg.ectype')['ArenaEctype'] = meta
function os:get_cfg_ectype_ArenaEctype()
local o = {}
setmetatable(o, cfg.ectype.ArenaEctype)
o.id = self:get_int()
o.mainregionid = self:get_int()
o.airwallregionid = self:get_int()
o.countdown = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.IndexedCircleRegion'
get_or_create('cfg.map')['IndexedCircleRegion'] = meta
function os:get_cfg_map_IndexedCircleRegion()
local o = {}
setmetatable(o, cfg.map.IndexedCircleRegion)
o.id = self:get_int()
o.allowpk = self:get_bool()
o.allowride = self:get_bool()
o.allowtrade = self:get_bool()
o.circle = self:get_cfg_map_CircleRegion()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ClimbTowerSweep'
get_or_create('cfg.ectype')['ClimbTowerSweep'] = meta
function os:get_cfg_ectype_ClimbTowerSweep()
local o = {}
setmetatable(o, cfg.ectype.ClimbTowerSweep)
o.limit = self:get_cfg_cmd_condition_DayLimit()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.SuckBlood'
get_or_create('cfg.buff')['SuckBlood'] = meta
function os:get_cfg_buff_SuckBlood()
local o = {}
setmetatable(o, cfg.buff.SuckBlood)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AppendCost'
meta.COST_ITEM_ID = 10400006
get_or_create('cfg.equip')['AppendCost'] = meta
function os:get_cfg_equip_AppendCost()
local o = {}
setmetatable(o, cfg.equip.AppendCost)
o.type = self:get_int()
local _list = self:get_list('int')
o.itemcost = _list
local _list = self:get_list('int')
o.expenses = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MultiPolygon'
get_or_create('cfg.map')['MultiPolygon'] = meta
function os:get_cfg_map_MultiPolygon()
local o = {}
setmetatable(o, cfg.map.MultiPolygon)
local _list = self:get_list('cfg_map_WeightedPolygonRegion')
o.polygons = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExpMonsterInfo'
get_or_create('cfg.ectype')['ExpMonsterInfo'] = meta
function os:get_cfg_ectype_ExpMonsterInfo()
local o = {}
setmetatable(o, cfg.ectype.ExpMonsterInfo)
o.refmsg = self:get_string()
local _list = self:get_list('cfg_ectype_CurrencyMonsterRef')
o.monsterref = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.YuanBao'
get_or_create('cfg.cmd.action')['YuanBao'] = meta
function os:get_cfg_cmd_action_YuanBao()
local o = {}
setmetatable(o, cfg.cmd.action.YuanBao)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.Date'
get_or_create('cfg.common')['Date'] = meta
function os:get_cfg_common_Date()
local o = {}
setmetatable(o, cfg.common.Date)
o.year = self:get_int()
o.month = self:get_int()
o.day = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FamilyTeamLevelMonserInfo'
get_or_create('cfg.ectype')['FamilyTeamLevelMonserInfo'] = meta
function os:get_cfg_ectype_FamilyTeamLevelMonserInfo()
local o = {}
setmetatable(o, cfg.ectype.FamilyTeamLevelMonserInfo)
o.level = self:get_int()
local _list = self:get_list('cfg_ectype_FamilyTeamMonsterWave')
o.monsterwaves = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.WildcardSex'
get_or_create('cfg.common')['WildcardSex'] = meta
function os:get_cfg_common_WildcardSex()
local o = {}
setmetatable(o, cfg.common.WildcardSex)
o.id = self:get_int()
o.male = self:get_string()
o.female = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.OneItem'
get_or_create('cfg.cmd.action')['OneItem'] = meta
function os:get_cfg_cmd_action_OneItem()
local o = {}
setmetatable(o, cfg.cmd.action.OneItem)
o.itemid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Action'
get_or_create('cfg.skill')['Action'] = meta
function os:get_cfg_skill_Action()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.AnimationCurve'
get_or_create('cfg.ui')['AnimationCurve'] = meta
function os:get_cfg_ui_AnimationCurve()
local o = {}
setmetatable(o, cfg.ui.AnimationCurve)
local _list = self:get_list('cfg_ui_Keyframe')
o.keys = _list
o.postWrapMode = self:get_string()
o.preWrapMode = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.AwakeInfo'
get_or_create('cfg.talisman')['AwakeInfo'] = meta
function os:get_cfg_talisman_AwakeInfo()
local o = {}
setmetatable(o, cfg.talisman.AwakeInfo)
o.talismancost = self:get_int()
o.displaytext = self:get_string()
local _list = self:get_list('cfg_talisman_AwakeEffect')
o.effect = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.Timer'
get_or_create('cfg.common')['Timer'] = meta
function os:get_cfg_common_Timer()
local o = {}
setmetatable(o, cfg.common.Timer)
o.id = self:get_int()
o.name = self:get_string()
o.comment = self:get_string()
o.timertype = self:get_int()
o.month = self:get_int()
o.day = self:get_int()
o.hour = self:get_int()
o.minute = self:get_int()
o.second = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TimeLimit'
get_or_create('cfg.ectype')['TimeLimit'] = meta
function os:get_cfg_ectype_TimeLimit()
local o = {}
setmetatable(o, cfg.ectype.TimeLimit)
o.day = self:get_int()
o.hour = self:get_int()
o.minute = self:get_int()
o.second = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.nextfunctiontips'
get_or_create('cfg.ui')['nextfunctiontips'] = meta
function os:get_cfg_ui_nextfunctiontips()
local o = {}
setmetatable(o, cfg.ui.nextfunctiontips)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.conid = self:get_int()
o.coniddesc = self:get_string()
o.functiondesc = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.Aura'
get_or_create('cfg.buff')['Aura'] = meta
function os:get_cfg_buff_Aura()
local o = {}
setmetatable(o, cfg.buff.Aura)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.buffid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.BangPai'
get_or_create('cfg.cmd.condition')['BangPai'] = meta
function os:get_cfg_cmd_condition_BangPai()
local o = {}
setmetatable(o, cfg.cmd.condition.BangPai)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.DecorateName'
get_or_create('cfg.role')['DecorateName'] = meta
function os:get_cfg_role_DecorateName()
local o = {}
setmetatable(o, cfg.role.DecorateName)
o.name = self:get_string()
o.position = self:get_int()
o.weight = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Xor'
get_or_create('cfg.ai')['Xor'] = meta
function os:get_cfg_ai_Xor()
local o = {}
setmetatable(o, cfg.ai.Xor)
o.condition1 = self:get_cfg_ai_BoolExpr()
o.condition2 = self:get_cfg_ai_BoolExpr()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.BasicAttr'
get_or_create('cfg.fight')['BasicAttr'] = meta
function os:get_cfg_fight_BasicAttr()
local o = {}
setmetatable(o, cfg.fight.BasicAttr)
o.hp = self:get_float()
o.mp = self:get_float()
o.attackvaluemin = self:get_float()
o.attackvaluemax = self:get_float()
o.defence = self:get_float()
o.hitrate = self:get_float()
o.hitresistrate = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Items'
get_or_create('cfg.cmd.action')['Items'] = meta
function os:get_cfg_cmd_action_Items()
local o = {}
setmetatable(o, cfg.cmd.action.Items)
local _list = self:get_list('cfg_cmd_action_Item')
o.items = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Const'
meta.MAX_EQUIP_SKILL_NUM = 6
get_or_create('cfg.skill')['Const'] = meta
function os:get_cfg_skill_Const()
local o = {}
setmetatable(o, cfg.skill.Const)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Shoe'
get_or_create('cfg.equip')['Shoe'] = meta
function os:get_cfg_equip_Shoe()
local o = {}
setmetatable(o, cfg.equip.Shoe)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.nextid = self:get_int()
o.extraequipid = self:get_int()
o.upgradecurrencycost = self:get_cfg_cmd_condition_XuNiBi()
o.carryingitemnum = self:get_int()
o.recommendrate = self:get_float()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.property = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TimeControler'
get_or_create('cfg.ectype')['TimeControler'] = meta
function os:get_cfg_ectype_TimeControler()
local o = {}
setmetatable(o, cfg.ectype.TimeControler)
o.hour = self:get_int()
o.minute = self:get_int()
o.second = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.RoleCombatPower'
get_or_create('cfg.operationalactivity')['RoleCombatPower'] = meta
function os:get_cfg_operationalactivity_RoleCombatPower()
local o = {}
setmetatable(o, cfg.operationalactivity.RoleCombatPower)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.DateTime'
get_or_create('cfg.common')['DateTime'] = meta
function os:get_cfg_common_DateTime()
local o = {}
setmetatable(o, cfg.common.DateTime)
o.year = self:get_int()
o.month = self:get_int()
o.day = self:get_int()
o.hour = self:get_int()
o.minute = self:get_int()
o.second = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemDress'
get_or_create('cfg.item')['ItemDress'] = meta
function os:get_cfg_item_ItemDress()
local o = {}
setmetatable(o, cfg.item.ItemDress)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.dressid = self:get_int()
o.effectiveime = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SwitchUntil'
get_or_create('cfg.ectype')['SwitchUntil'] = meta
function os:get_cfg_ectype_SwitchUntil()
local o = {}
setmetatable(o, cfg.ectype.SwitchUntil)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
local _list = self:get_list('cfg_ectype_Case')
o.cases = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.shakemoneytree.ShakeDetail'
get_or_create('cfg.shakemoneytree')['ShakeDetail'] = meta
function os:get_cfg_shakemoneytree_ShakeDetail()
local o = {}
setmetatable(o, cfg.shakemoneytree.ShakeDetail)
o.shaketimes = self:get_int()
o.cost = self:get_cfg_cmd_condition_YuanBao()
o.getmoney = self:get_cfg_cmd_action_XuNiBi()
o.limit = self:get_cfg_cmd_condition_MinVipLevel()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Movement'
meta.MoveBack = 2
meta.MoveToTarget = 1
meta.MoveInDirection = 0
get_or_create('cfg.skill')['Movement'] = meta
function os:get_cfg_skill_Movement()
local o = {}
setmetatable(o, cfg.skill.Movement)
o.timeline = self:get_float()
o.duration = self:get_float()
o.speed = self:get_float()
o.acceleration = self:get_float()
o.type = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.FlyWeapon'
get_or_create('cfg.skill')['FlyWeapon'] = meta
function os:get_cfg_skill_FlyWeapon()
local o = {}
setmetatable(o, cfg.skill.FlyWeapon)
o.timeline = self:get_float()
o.id = self:get_int()
o.spawntype = self:get_int()
o.life = self:get_float()
o.effectid = self:get_int()
o.totarget = self:get_bool()
o.tracecurveid = self:get_int()
o.offsetx = self:get_float()
o.offsety = self:get_float()
o.offsetz = self:get_float()
o.tracetype = self:get_int()
o.casterbindtype = self:get_int()
o.targetbindtype = self:get_int()
o.bulletradius = self:get_float()
o.passbody = self:get_bool()
o.beattackeffectid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.SpawnObject'
meta.SPAWNTYPE_FLYWEAPON = 0
meta.SPAWNTYPE_BOMB = 1
meta.SPAWNTYPE_OBJECT = 2
get_or_create('cfg.skill')['SpawnObject'] = meta
function os:get_cfg_skill_SpawnObject()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.EctypeSingle'
get_or_create('cfg.ectype')['EctypeSingle'] = meta
function os:get_cfg_ectype_EctypeSingle()
local o = {}
setmetatable(o, cfg.ectype.EctypeSingle)
o.ectypes = self:get_map('int', 'cfg_ectype_DailyEctypeInfo')
o.resetopencountlimit = self:get_cfg_cmd_condition_VipLimits()
o.clientactiontimeout = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AmuletConfig'
meta.AMULET_PROP_COUNT_PER_PAGE = 6
meta.AMULET_PAGE_COUNT = 3
meta.AMULET_DAY_WASH_COUNT_CMD_ID = 0
meta.STATE_UNLOCK = 0
meta.STATE_LOCK = 1
meta.STATE_UN_OPEN = 0
meta.STATE_OPEN = 1
get_or_create('cfg.equip')['AmuletConfig'] = meta
function os:get_cfg_equip_AmuletConfig()
local o = {}
setmetatable(o, cfg.equip.AmuletConfig)
local _list = self:get_list('cfg_cmd_condition_Item')
o.lockcost = _list
local _list = self:get_list('int')
o.expandlevel = _list
local _list = self:get_list('int')
o.qualityjudge = _list
local _list = self:get_list('string')
o.icon = _list
o.washcost = self:get_cfg_cmd_condition_VipLimits2()
o.lockitemid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Sequence'
get_or_create('cfg.ectype')['Sequence'] = meta
function os:get_cfg_ectype_Sequence()
local o = {}
setmetatable(o, cfg.ectype.Sequence)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
local _list = self:get_list('cfg_ectype_Action')
o.actions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.LocationRangeData'
get_or_create('cfg.task')['LocationRangeData'] = meta
function os:get_cfg_task_LocationRangeData()
local o = {}
setmetatable(o, cfg.task.LocationRangeData)
o.minx = self:get_float()
o.miny = self:get_float()
o.minz = self:get_float()
o.maxx = self:get_float()
o.maxy = self:get_float()
o.maxz = self:get_float()
o.worldmapid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Bonus'
get_or_create('cfg.cmd.action')['Bonus'] = meta
function os:get_cfg_cmd_action_Bonus()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.CompleteAchievement'
get_or_create('cfg.cmd.condition')['CompleteAchievement'] = meta
function os:get_cfg_cmd_condition_CompleteAchievement()
local o = {}
setmetatable(o, cfg.cmd.condition.CompleteAchievement)
o.achievementid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.DayTimeRange'
get_or_create('cfg.common')['DayTimeRange'] = meta
function os:get_cfg_common_DayTimeRange()
local o = {}
setmetatable(o, cfg.common.DayTimeRange)
o.begintime = self:get_cfg_common_DayTime()
o.endtime = self:get_cfg_common_DayTime()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.HeroEctype'
get_or_create('cfg.ectype')['HeroEctype'] = meta
function os:get_cfg_ectype_HeroEctype()
local o = {}
setmetatable(o, cfg.ectype.HeroEctype)
o.id = self:get_int()
o.bossid = self:get_int()
o.uiscale = self:get_float()
o.mainregionid = self:get_int()
local _list = self:get_list('cfg_ectype_MonsterInfo')
o.monsters = _list
local _list = self:get_list('int')
o.showbonusid = _list
local _list = self:get_list('cfg_ectype_HeroEctypeBonus')
o.heroectypebonus = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaShengwang'
get_or_create('cfg.arena')['ArenaShengwang'] = meta
function os:get_cfg_arena_ArenaShengwang()
local o = {}
setmetatable(o, cfg.arena.ArenaShengwang)
o.id = self:get_int()
o.minrank = self:get_int()
o.addshengwang = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.UIFunctionOpen'
get_or_create('cfg.ui')['UIFunctionOpen'] = meta
function os:get_cfg_ui_UIFunctionOpen()
local o = {}
setmetatable(o, cfg.ui.UIFunctionOpen)
o.id = self:get_int()
o.functionname = self:get_int()
o.opentype = self:get_int()
o.conid = self:get_int()
o.desc = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.chat.Const'
meta.MAX_TEXT_SIZE = 40
meta.MAX_VOICE_SIZE = 100000
meta.MAX_IMAGE_SIZE = 300000
meta.FACE_PRICE = 68
meta.TOP_CHANNEL_PRICE = 100
get_or_create('cfg.chat')['Const'] = meta
function os:get_cfg_chat_Const()
local o = {}
setmetatable(o, cfg.chat.Const)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.SkillAction'
meta.TARGET_ENERMY = 1
meta.TARGET_TEAMMATE = 2
meta.TARGET_SELF = 3
meta.TARGET_ALL = 4
meta.RELATE_SELF = 1
meta.RELATE_TARGET = 2
meta.DEFAULT_NEXTSKILLEXPIRETIME = 1.0
get_or_create('cfg.skill')['SkillAction'] = meta
function os:get_cfg_skill_SkillAction()
local o = {}
setmetatable(o, cfg.skill.SkillAction)
o.actionname = self:get_string()
o.actionsourcetype = self:get_int()
o.othermodelname = self:get_string()
o.actionfile = self:get_string()
o.foreactionfile = self:get_string()
o.succactionfile = self:get_string()
o.actionspeed = self:get_float()
o.loopplay = self:get_int()
o.effectid = self:get_int()
local _list = self:get_list('cfg_skill_Action')
o.actions = _list
local _list = self:get_list('cfg_skill_Effect')
o.effects = _list
o.effects_id = {}
for _, _V in ipairs(_list) do
o.effects_id[_V.id] = _V
end
o.nextskillexpiretime = self:get_float()
o.endattackingtime = self:get_float()
o.needtarget = self:get_bool()
o.showprogress = self:get_bool()
o.caninterrupt = self:get_bool()
o.attackrange = self:get_float()
o.showattackaera = self:get_bool()
o.canrotate = self:get_bool()
o.canmove = self:get_bool()
o.startmovetime = self:get_float()
o.endmovetime = self:get_float()
o.relatetype = self:get_int()
local _list = self:get_list('cfg_skill_AttackList')
o.attacklists = _list
o.attacklists_id = {}
for _, _V in ipairs(_list) do
o.attacklists_id[_V.id] = _V
end
local _list = self:get_list('cfg_skill_HitZone')
o.hitzones = _list
o.hitzones_id = {}
for _, _V in ipairs(_list) do
o.hitzones_id[_V.id] = _V
end
local _list = self:get_list('cfg_skill_BeAttackEffect')
o.beattackeffects = _list
o.beattackeffects_id = {}
for _, _V in ipairs(_list) do
o.beattackeffects_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.huiwu.BattleTimeControler'
get_or_create('cfg.huiwu')['BattleTimeControler'] = meta
function os:get_cfg_huiwu_BattleTimeControler()
local o = {}
setmetatable(o, cfg.huiwu.BattleTimeControler)
o.round = self:get_int()
o.wait = self:get_int()
o.battle = self:get_int()
o.relax = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.BonusRandomInfo'
get_or_create('cfg.cmd.action')['BonusRandomInfo'] = meta
function os:get_cfg_cmd_action_BonusRandomInfo()
local o = {}
setmetatable(o, cfg.cmd.action.BonusRandomInfo)
o.bonus = self:get_cfg_cmd_action_Bonus()
o.droprate = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.SFXDeployment'
get_or_create('cfg.map')['SFXDeployment'] = meta
function os:get_cfg_map_SFXDeployment()
local o = {}
setmetatable(o, cfg.map.SFXDeployment)
o.deploymentid = self:get_int()
o.sfxid = self:get_int()
o.location = self:get_cfg_map_MultiPoints()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemEnhance'
get_or_create('cfg.item')['ItemEnhance'] = meta
function os:get_cfg_item_ItemEnhance()
local o = {}
setmetatable(o, cfg.item.ItemEnhance)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.sprite = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyShop'
get_or_create('cfg.family')['FamilyShop'] = meta
function os:get_cfg_family_FamilyShop()
local o = {}
setmetatable(o, cfg.family.FamilyShop)
o.shoplevel = self:get_int()
o.shoprequirecapital = self:get_cfg_cmd_condition_FamilyMoney()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Necklace'
get_or_create('cfg.equip')['Necklace'] = meta
function os:get_cfg_equip_Necklace()
local o = {}
setmetatable(o, cfg.equip.Necklace)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
local _list = self:get_list('int')
o.mainproperty = _list
local _list = self:get_list('int')
o.mainproperty2 = _list
local _list = self:get_list('int')
o.viceproperty = _list
local _list = self:get_list('int')
o.rankweight = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.monster.MultiRoundDrop'
get_or_create('cfg.monster')['MultiRoundDrop'] = meta
function os:get_cfg_monster_MultiRoundDrop()
local o = {}
setmetatable(o, cfg.monster.MultiRoundDrop)
o.drop = self:get_cfg_cmd_action_Drop()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Vector2'
get_or_create('cfg.map')['Vector2'] = meta
function os:get_cfg_map_Vector2()
local o = {}
setmetatable(o, cfg.map.Vector2)
o.x = self:get_float()
o.y = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Vector3'
get_or_create('cfg.map')['Vector3'] = meta
function os:get_cfg_map_Vector3()
local o = {}
setmetatable(o, cfg.map.Vector3)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AccessoryColor'
meta.JUDGE_COLOR_LIST = {0.2,0.35,0.45,0.6,0.8}
meta.JUDGE_COLOR_MAIN = 5
get_or_create('cfg.equip')['AccessoryColor'] = meta
function os:get_cfg_equip_AccessoryColor()
local o = {}
setmetatable(o, cfg.equip.AccessoryColor)
o.level = self:get_int()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.standard = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.AddFamilyMoneyBuild'
get_or_create('cfg.cmd.action')['AddFamilyMoneyBuild'] = meta
function os:get_cfg_cmd_action_AddFamilyMoneyBuild()
local o = {}
setmetatable(o, cfg.cmd.action.AddFamilyMoneyBuild)
o.money = self:get_long()
o.buildv = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.shakemoneytree.ShakeMoneyTree'
get_or_create('cfg.shakemoneytree')['ShakeMoneyTree'] = meta
function os:get_cfg_shakemoneytree_ShakeMoneyTree()
local o = {}
setmetatable(o, cfg.shakemoneytree.ShakeMoneyTree)
o.viplimit = self:get_cfg_cmd_condition_VipLimitsLite()
local _list = self:get_list('cfg_shakemoneytree_ShakeDetail')
o.shakeinfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ResetOpenCount'
get_or_create('cfg.ectype')['ResetOpenCount'] = meta
function os:get_cfg_ectype_ResetOpenCount()
local o = {}
setmetatable(o, cfg.ectype.ResetOpenCount)
o.cost = self:get_cfg_cmd_condition_VipLimits()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FreshGuide'
get_or_create('cfg.ectype')['FreshGuide'] = meta
function os:get_cfg_ectype_FreshGuide()
local o = {}
setmetatable(o, cfg.ectype.FreshGuide)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.guideid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AccessoryConfig'
get_or_create('cfg.equip')['AccessoryConfig'] = meta
function os:get_cfg_equip_AccessoryConfig()
local o = {}
setmetatable(o, cfg.equip.AccessoryConfig)
o.washcost = self:get_cfg_cmd_condition_XuNiBi()
o.abandoncost = self:get_cfg_cmd_condition_YuanBao()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetSkillLvlupData'
get_or_create('cfg.pet')['PetSkillLvlupData'] = meta
function os:get_cfg_pet_PetSkillLvlupData()
local o = {}
setmetatable(o, cfg.pet.PetSkillLvlupData)
o.requirepetlvl = self:get_int()
o.cost = self:get_map('int', 'long')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.ChargeBonus'
meta.chargeNPC = 23000020
get_or_create('cfg.bonus')['ChargeBonus'] = meta
function os:get_cfg_bonus_ChargeBonus()
local o = {}
setmetatable(o, cfg.bonus.ChargeBonus)
o.bonusid = self:get_int()
o.bonustype = self:get_int()
o.requirevalue = self:get_int()
o.bonuslist = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotProfessionConfig'
get_or_create('cfg.plot')['PlotProfessionConfig'] = meta
function os:get_cfg_plot_PlotProfessionConfig()
local o = {}
setmetatable(o, cfg.plot.PlotProfessionConfig)
o.genderconfig = self:get_map('int', 'cfg_plot_PlotGenderConfig')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.active.Activeevent'
get_or_create('cfg.active')['Activeevent'] = meta
function os:get_cfg_active_Activeevent()
local o = {}
setmetatable(o, cfg.active.Activeevent)
o.id = self:get_int()
o.eventtype = self:get_int()
o.decs = self:get_string()
o.addtype = self:get_int()
o.times = self:get_int()
o.addnum = self:get_int()
o.uientry = self:get_string()
o.uitabindex = self:get_int()
o.uitabindex02 = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.DailyEctype'
get_or_create('cfg.ectype')['DailyEctype'] = meta
function os:get_cfg_ectype_DailyEctype()
local o = {}
setmetatable(o, cfg.ectype.DailyEctype)
o.id = self:get_int()
o.costtili = self:get_cfg_cmd_condition_TiLi()
o.ectypetype = self:get_int()
o.storyname = self:get_string()
o.introduction = self:get_string()
o.decs = self:get_string()
o.backgroundpic = self:get_string()
local _list = self:get_list('int')
o.showbonusid = _list
o.mainregionid = self:get_int()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.showlv = self:get_string()
o.battlepower = self:get_int()
o.doublebonustime = self:get_int()
local _list = self:get_list('cfg_ectype_MonsterInfo')
o.monsters = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.TianFu'
get_or_create('cfg.cmd.condition')['TianFu'] = meta
function os:get_cfg_cmd_condition_TianFu()
local o = {}
setmetatable(o, cfg.cmd.condition.TianFu)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITransform'
get_or_create('cfg.ui')['ContentUITransform'] = meta
function os:get_cfg_ui_ContentUITransform()
local o = {}
setmetatable(o, cfg.ui.ContentUITransform)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.NoviceGuide'
meta.CONTROLLER = 2
meta.TOTALFINDOBJECTTIMES = 80
meta.TRIGGERNEXTGUIDETIME = 300
meta.FINDOBJINTERVAL = 1
meta.NONMANDOTORYTIME = 10
meta.OPENDISPLAYTIME = 3
meta.TARGETPANELDEPTH = 701
get_or_create('cfg.guide')['NoviceGuide'] = meta
function os:get_cfg_guide_NoviceGuide()
local o = {}
setmetatable(o, cfg.guide.NoviceGuide)
o.id = self:get_int()
o.isinectype = self:get_bool()
o.issavepoint = self:get_bool()
o.ismandatory = self:get_bool()
o.isdisskipbutton = self:get_bool()
o.ispause = self:get_bool()
o.islocked = self:get_bool()
o.delaytime = self:get_float()
o.audio = self:get_int()
o.overaudio = self:get_int()
local _list = self:get_list('cfg_guide_GuideEffect')
o.guideeffect = _list
local _list = self:get_list('cfg_cmd_condition_Condition')
o.triggerconditions = _list
local _list = self:get_list('cfg_cmd_condition_Condition')
o.completeconditions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetQuality'
get_or_create('cfg.operationalactivity')['PetQuality'] = meta
function os:get_cfg_operationalactivity_PetQuality()
local o = {}
setmetatable(o, cfg.operationalactivity.PetQuality)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.qulity = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.BossChallenge'
get_or_create('cfg.family')['BossChallenge'] = meta
function os:get_cfg_family_BossChallenge()
local o = {}
setmetatable(o, cfg.family.BossChallenge)
o.bosslevel = self:get_int()
o.bossrequirecapital = self:get_cfg_cmd_condition_FamilyMoney()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.State'
get_or_create('cfg.fight')['State'] = meta
function os:get_cfg_fight_State()
local o = {}
setmetatable(o, cfg.fight.State)
o.id = self:get_int()
o.useinbuff = self:get_bool()
o.modelpath = self:get_string()
local _list = self:get_list('bool')
o.abilities = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pathfly.Paths'
get_or_create('cfg.pathfly')['Paths'] = meta
function os:get_cfg_pathfly_Paths()
local o = {}
setmetatable(o, cfg.pathfly.Paths)
o.id = self:get_int()
o.path = self:get_cfg_pathfly_PathCurve()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.CareerBonus'
get_or_create('cfg.cmd.action')['CareerBonus'] = meta
function os:get_cfg_cmd_action_CareerBonus()
local o = {}
setmetatable(o, cfg.cmd.action.CareerBonus)
o.career = self:get_int()
o.bonus = self:get_cfg_cmd_action_Bonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaRankStep'
get_or_create('cfg.arena')['ArenaRankStep'] = meta
function os:get_cfg_arena_ArenaRankStep()
local o = {}
setmetatable(o, cfg.arena.ArenaRankStep)
o.upbound = self:get_int()
local _list = self:get_list('cfg_arena_ArenaRivalBound')
o.rivalbounds = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Periodic'
get_or_create('cfg.ai')['Periodic'] = meta
function os:get_cfg_ai_Periodic()
local o = {}
setmetatable(o, cfg.ai.Periodic)
o.interval = self:get_float()
o.expression = self:get_cfg_ai_Expression()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GuardTowerZone'
get_or_create('cfg.ectype')['GuardTowerZone'] = meta
function os:get_cfg_ectype_GuardTowerZone()
local o = {}
setmetatable(o, cfg.ectype.GuardTowerZone)
o.zoneid = self:get_int()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.ectypeid = self:get_int()
o.baseposition = self:get_cfg_map_Vector2()
o.mainregionid = self:get_int()
o.baseid = self:get_int()
o.winaward = self:get_cfg_cmd_action_MultiBonus()
o.maxkillaward = self:get_cfg_cmd_action_MultiBonus()
local _list = self:get_list('int')
o.showbonusid = _list
local _list = self:get_list('cfg_ectype_GuardTowerMonsterInfo')
o.monsterwaves = _list
o.monsterwaves_id = {}
for _, _V in ipairs(_list) do
o.monsterwaves_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.HeroEctypeMsg'
get_or_create('cfg.ectype')['HeroEctypeMsg'] = meta
function os:get_cfg_ectype_HeroEctypeMsg()
local o = {}
setmetatable(o, cfg.ectype.HeroEctypeMsg)
o.id = self:get_int()
o.groupname = self:get_string()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.defaultid = self:get_int()
o.icon = self:get_string()
local _list = self:get_list('cfg_ectype_EctypeRandom')
o.ectyperandom = _list
local _list = self:get_list('int')
o.petid = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MonsterDeployment'
get_or_create('cfg.map')['MonsterDeployment'] = meta
function os:get_cfg_map_MonsterDeployment()
local o = {}
setmetatable(o, cfg.map.MonsterDeployment)
o.deploymentid = self:get_int()
o.defaulton = self:get_bool()
o.patroltype = self:get_int()
o.regeneratetype = self:get_int()
local _list = self:get_list('cfg_map_MonsterSpawn')
o.monsters = _list
o.location = self:get_cfg_map_DeploymentLocation()
o.path = self:get_cfg_map_BezierCurve()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.JadeLevel'
get_or_create('cfg.operationalactivity')['JadeLevel'] = meta
function os:get_cfg_operationalactivity_JadeLevel()
local o = {}
setmetatable(o, cfg.operationalactivity.JadeLevel)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bag.SlotBagConfig'
get_or_create('cfg.bag')['SlotBagConfig'] = meta
function os:get_cfg_bag_SlotBagConfig()
local o = {}
setmetatable(o, cfg.bag.SlotBagConfig)
local _list = self:get_list('cfg_bag_SlotCondition')
o.condtioninfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.achievement.AchievementNormal'
get_or_create('cfg.achievement')['AchievementNormal'] = meta
function os:get_cfg_achievement_AchievementNormal()
local o = {}
setmetatable(o, cfg.achievement.AchievementNormal)
o.id = self:get_int()
o.achievementtype = self:get_int()
o.achievementtypename = self:get_string()
o.type = self:get_int()
o.value = self:get_long()
o.isamount = self:get_bool()
o.name = self:get_string()
o.detail = self:get_string()
o.icon = self:get_string()
o.chengjiupoint = self:get_cfg_cmd_action_ChengJiu()
o.bindyuanbao = self:get_cfg_cmd_action_BindYuanBao()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.DayTime'
get_or_create('cfg.common')['DayTime'] = meta
function os:get_cfg_common_DayTime()
local o = {}
setmetatable(o, cfg.common.DayTime)
o.hour = self:get_int()
o.minute = self:get_int()
o.second = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.JingYan'
get_or_create('cfg.cmd.condition')['JingYan'] = meta
function os:get_cfg_cmd_condition_JingYan()
local o = {}
setmetatable(o, cfg.cmd.condition.JingYan)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetWash'
meta.COST_ITEM_ID = 10400013
meta.WASH_XUNIBI_KEY = 1
meta.WASH_YUANBAO_KEY = 2
get_or_create('cfg.pet')['PetWash'] = meta
function os:get_cfg_pet_PetWash()
local o = {}
setmetatable(o, cfg.pet.PetWash)
o.feedid = self:get_int()
o.name = self:get_string()
o.cost = self:get_cfg_cmd_condition_MultiCondition()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.OwnSkill'
get_or_create('cfg.cmd.condition')['OwnSkill'] = meta
function os:get_cfg_cmd_condition_OwnSkill()
local o = {}
setmetatable(o, cfg.cmd.condition.OwnSkill)
local _list = self:get_list('int')
o.skillids = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.TimeLimit'
get_or_create('cfg.cmd.condition')['TimeLimit'] = meta
function os:get_cfg_cmd_condition_TimeLimit()
local o = {}
setmetatable(o, cfg.cmd.condition.TimeLimit)
o.limittype = self:get_int()
o.starttime = self:get_int()
o.endtime = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.navmesh.Convex'
get_or_create('cfg.navmesh')['Convex'] = meta
function os:get_cfg_navmesh_Convex()
local o = {}
setmetatable(o, cfg.navmesh.Convex)
local _list = self:get_list('int')
o.vertexids = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.CampRelation'
get_or_create('cfg.fight')['CampRelation'] = meta
function os:get_cfg_fight_CampRelation()
local o = {}
setmetatable(o, cfg.fight.CampRelation)
o.camp = self:get_int()
local _list = self:get_list('int')
o.relations = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddHPInterval'
get_or_create('cfg.buff')['AddHPInterval'] = meta
function os:get_cfg_buff_AddHPInterval()
local o = {}
setmetatable(o, cfg.buff.AddHPInterval)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Currency'
get_or_create('cfg.cmd.condition')['Currency'] = meta
function os:get_cfg_cmd_condition_Currency()
local o = {}
setmetatable(o, cfg.cmd.condition.Currency)
o.currencytype = self:get_int()
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Switch'
get_or_create('cfg.ai')['Switch'] = meta
function os:get_cfg_ai_Switch()
local o = {}
setmetatable(o, cfg.ai.Switch)
local _list = self:get_list('cfg_ai_Case')
o.cases = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ChapterAward'
get_or_create('cfg.ectype')['ChapterAward'] = meta
function os:get_cfg_ectype_ChapterAward()
local o = {}
setmetatable(o, cfg.ectype.ChapterAward)
o.awardid = self:get_int()
o.requirestar = self:get_int()
o.award = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Enviroment'
get_or_create('cfg.ectype')['Enviroment'] = meta
function os:get_cfg_ectype_Enviroment()
local o = {}
setmetatable(o, cfg.ectype.Enviroment)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.storynote.StoryNote'
get_or_create('cfg.storynote')['StoryNote'] = meta
function os:get_cfg_storynote_StoryNote()
local o = {}
setmetatable(o, cfg.storynote.StoryNote)
o.chapterid = self:get_int()
o.chaptername = self:get_string()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.chapterbgicon = self:get_string()
local _list = self:get_list('cfg_storynote_NoteInfo')
o.noteinfo = _list
o.noteinfo_noteid = {}
for _, _V in ipairs(_list) do
o.noteinfo_noteid[_V.noteid] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.OR'
get_or_create('cfg.cmd.condition')['OR'] = meta
function os:get_cfg_cmd_condition_OR()
local o = {}
setmetatable(o, cfg.cmd.condition.OR)
o.first = self:get_cfg_cmd_condition_Condition()
o.second = self:get_cfg_cmd_condition_Condition()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemSource'
get_or_create('cfg.item')['ItemSource'] = meta
function os:get_cfg_item_ItemSource()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.PetExp'
get_or_create('cfg.cmd.action')['PetExp'] = meta
function os:get_cfg_cmd_action_PetExp()
local o = {}
setmetatable(o, cfg.cmd.action.PetExp)
o.amount = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.Rank'
get_or_create('cfg.operationalactivity')['Rank'] = meta
function os:get_cfg_operationalactivity_Rank()
local o = {}
setmetatable(o, cfg.operationalactivity.Rank)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.type = self:get_int()
o.ranking = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Ring'
get_or_create('cfg.equip')['Ring'] = meta
function os:get_cfg_equip_Ring()
local o = {}
setmetatable(o, cfg.equip.Ring)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
local _list = self:get_list('int')
o.mainproperty = _list
local _list = self:get_list('int')
o.mainproperty2 = _list
local _list = self:get_list('int')
o.viceproperty = _list
local _list = self:get_list('int')
o.rankweight = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.DailyGift'
get_or_create('cfg.operationalactivity')['DailyGift'] = meta
function os:get_cfg_operationalactivity_DailyGift()
local o = {}
setmetatable(o, cfg.operationalactivity.DailyGift)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.viplimit = self:get_cfg_cmd_condition_MinVipLevel()
o.limit = self:get_cfg_cmd_condition_DayLimit()
o.original = self:get_cfg_cmd_condition_YuanBao()
o.off = self:get_cfg_cmd_condition_YuanBao()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIPanel'
get_or_create('cfg.ui')['ContentUIPanel'] = meta
function os:get_cfg_ui_ContentUIPanel()
local o = {}
setmetatable(o, cfg.ui.ContentUIPanel)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.alpha = self:get_float()
o.depth = self:get_int()
o.clipping = self:get_string()
o.clipOffset = self:get_cfg_ui_Vector2()
o.baseClipRegion = self:get_cfg_ui_Vector4()
o.clipSoftness = self:get_cfg_ui_Vector2()
o.clipTexture = self:get_string()
o.renderQueue = self:get_string()
o.sortingOrder = self:get_int()
o.generateNormals = self:get_bool()
o.cullWhileDragging = self:get_bool()
o.alwaysOnScreen = self:get_bool()
o.softBorderPadding = self:get_bool()
o.anchorOffset = self:get_bool()
o.widgetsAreStatic = self:get_bool()
o.showInPanelTool = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.BleedPercent'
get_or_create('cfg.buff')['BleedPercent'] = meta
function os:get_cfg_buff_BleedPercent()
local o = {}
setmetatable(o, cfg.buff.BleedPercent)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.ActivityEntry'
get_or_create('cfg.operationalactivity')['ActivityEntry'] = meta
function os:get_cfg_operationalactivity_ActivityEntry()
local o = {}
setmetatable(o, cfg.operationalactivity.ActivityEntry)
o.id = self:get_int()
o.condition = self:get_cfg_operationalactivity_ActivityCondition()
o.reward = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.OneItems'
get_or_create('cfg.cmd.action')['OneItems'] = meta
function os:get_cfg_cmd_action_OneItems()
local o = {}
setmetatable(o, cfg.cmd.action.OneItems)
local _list = self:get_list('int')
o.items = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIProgressBar'
get_or_create('cfg.ui')['ContentUIProgressBar'] = meta
function os:get_cfg_ui_ContentUIProgressBar()
local o = {}
setmetatable(o, cfg.ui.ContentUIProgressBar)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.value = self:get_float()
o.alpha = self:get_float()
o.numberOfSteps = self:get_int()
o.backgroundWidget = self:get_cfg_ui_ContentUITransform()
o.foregroundWidget = self:get_cfg_ui_ContentUITransform()
o.thumb = self:get_cfg_ui_ContentUITransform()
o.fillDirection = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.EctypeRandom'
get_or_create('cfg.ectype')['EctypeRandom'] = meta
function os:get_cfg_ectype_EctypeRandom()
local o = {}
setmetatable(o, cfg.ectype.EctypeRandom)
o.id = self:get_int()
o.itemid = self:get_int()
o.weight = self:get_int()
o.color = self:get_int()
o.refstring = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.MonthlyCard'
get_or_create('cfg.bonus')['MonthlyCard'] = meta
function os:get_cfg_bonus_MonthlyCard()
local o = {}
setmetatable(o, cfg.bonus.MonthlyCard)
o.dayscount = self:get_int()
o.bonus = self:get_cfg_cmd_action_Currency()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.BaoZi'
get_or_create('cfg.bonus')['BaoZi'] = meta
function os:get_cfg_bonus_BaoZi()
local o = {}
setmetatable(o, cfg.bonus.BaoZi)
o.mealtype = self:get_int()
o.starthour = self:get_int()
o.startminute = self:get_int()
o.endhour = self:get_int()
o.endminute = self:get_int()
o.gettili = self:get_cfg_cmd_action_TiLi()
o.requireyuanbao = self:get_cfg_cmd_condition_YuanBao()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CallMineral'
get_or_create('cfg.ectype')['CallMineral'] = meta
function os:get_cfg_ectype_CallMineral()
local o = {}
setmetatable(o, cfg.ectype.CallMineral)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Gender'
get_or_create('cfg.cmd.condition')['Gender'] = meta
function os:get_cfg_cmd_condition_Gender()
local o = {}
setmetatable(o, cfg.cmd.condition.Gender)
o.gender = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.OnlineTimeBonus'
get_or_create('cfg.bonus')['OnlineTimeBonus'] = meta
function os:get_cfg_bonus_OnlineTimeBonus()
local o = {}
setmetatable(o, cfg.bonus.OnlineTimeBonus)
o.onlinetimes = self:get_int()
o.bonuslist = self:get_cfg_cmd_action_RandomBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Skilldmg'
get_or_create('cfg.skill')['Skilldmg'] = meta
function os:get_cfg_skill_Skilldmg()
local o = {}
setmetatable(o, cfg.skill.Skilldmg)
o.id = self:get_int()
o.name = self:get_string()
o.nextskillid = self:get_int()
o.actionname = self:get_string()
o.actiontype = self:get_int()
o.icon = self:get_string()
o.introduction = self:get_string()
o.isnormal = self:get_bool()
o.cd = self:get_float()
o.cost = self:get_float()
o.costperlvl = self:get_float()
local _list = self:get_list('cfg_skill_HitPointAction')
o.hitpoints = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.LockUI'
get_or_create('cfg.guide')['LockUI'] = meta
function os:get_cfg_guide_LockUI()
local o = {}
setmetatable(o, cfg.guide.LockUI)
o.controlobject = self:get_int()
o.controlboxcollider = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.DialogueInfo'
get_or_create('cfg.task')['DialogueInfo'] = meta
function os:get_cfg_task_DialogueInfo()
local o = {}
setmetatable(o, cfg.task.DialogueInfo)
o.role = self:get_int()
o.npcid = self:get_int()
o.pos = self:get_int()
o.dialogcontent = self:get_string()
o.action = self:get_int()
o.voiceid = self:get_int()
o.delay = self:get_int()
o.dialogframetype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.DialogFrame'
get_or_create('cfg.common')['DialogFrame'] = meta
function os:get_cfg_common_DialogFrame()
local o = {}
setmetatable(o, cfg.common.DialogFrame)
o.id = self:get_int()
o.name = self:get_string()
o.frame = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Equip'
meta.MAX_APPEND_LEVEL = 90
meta.MAX_ANNEAL_LEVEL = 20
meta.UPGRADE_COST_ITEM = 10400007
meta.DISASSEMBLY_CURRENCY = 10200004
meta.ABANDON_CURRENCY = 10200002
meta.ABANDON_COST = 10
meta.MIN_UPGRADE_LEVEL = 60
meta.UPGRADE_DELTA_LEVEL = 15
meta.MAX_UPGRADE_LEVEL = 90
get_or_create('cfg.equip')['Equip'] = meta
function os:get_cfg_equip_Equip()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.RankBonusList'
get_or_create('cfg.bonus')['RankBonusList'] = meta
function os:get_cfg_bonus_RankBonusList()
local o = {}
setmetatable(o, cfg.bonus.RankBonusList)
o.requirerank = self:get_int()
o.bonuslist = self:get_cfg_cmd_action_Bonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExpMonsterBonus'
get_or_create('cfg.ectype')['ExpMonsterBonus'] = meta
function os:get_cfg_ectype_ExpMonsterBonus()
local o = {}
setmetatable(o, cfg.ectype.ExpMonsterBonus)
o.killnum = self:get_int()
o.bonusdes = self:get_string()
o.killbonus = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.ChooseTargetPolicy'
get_or_create('cfg.ai')['ChooseTargetPolicy'] = meta
function os:get_cfg_ai_ChooseTargetPolicy()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.Slider'
get_or_create('cfg.guide')['Slider'] = meta
function os:get_cfg_guide_Slider()
local o = {}
setmetatable(o, cfg.guide.Slider)
o.type = self:get_int()
local _list = self:get_list('float')
o.pos = _list
o.fromRotation = self:get_float()
o.toRotation = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ConditionTimer'
get_or_create('cfg.ectype')['ConditionTimer'] = meta
function os:get_cfg_ectype_ConditionTimer()
local o = {}
setmetatable(o, cfg.ectype.ConditionTimer)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.RewardItem'
get_or_create('cfg.task')['RewardItem'] = meta
function os:get_cfg_task_RewardItem()
local o = {}
setmetatable(o, cfg.task.RewardItem)
local _list = self:get_list('int')
o.itemid = _list
local _list = self:get_list('int')
o.itemcount = _list
local _list = self:get_list('float')
o.itemprob = _list
o.totalprob = self:get_float()
o.randomselectoneitem = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.RunOnceAI'
get_or_create('cfg.ai')['RunOnceAI'] = meta
function os:get_cfg_ai_RunOnceAI()
local o = {}
setmetatable(o, cfg.ai.RunOnceAI)
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AbnormalDamageUp'
get_or_create('cfg.buff')['AbnormalDamageUp'] = meta
function os:get_cfg_buff_AbnormalDamageUp()
local o = {}
setmetatable(o, cfg.buff.AbnormalDamageUp)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ShowGlobalTips'
get_or_create('cfg.ectype')['ShowGlobalTips'] = meta
function os:get_cfg_ectype_ShowGlobalTips()
local o = {}
setmetatable(o, cfg.ectype.ShowGlobalTips)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.audio.SoundEffects'
get_or_create('cfg.audio')['SoundEffects'] = meta
function os:get_cfg_audio_SoundEffects()
local o = {}
setmetatable(o, cfg.audio.SoundEffects)
o.soundtype = self:get_int()
local _list = self:get_list('int')
o.soundlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.OperationalActivity'
get_or_create('cfg.operationalactivity')['OperationalActivity'] = meta
function os:get_cfg_operationalactivity_OperationalActivity()
local o = {}
setmetatable(o, cfg.operationalactivity.OperationalActivity)
o.type = self:get_int()
o.title = self:get_string()
o.content = self:get_string()
o.texture = self:get_string()
o.unfinishlabel = self:get_string()
o.finishedlabel = self:get_string()
o.currencyname = self:get_string()
o.currencytype = self:get_int()
o.timerange = self:get_cfg_common_DateTimeRange()
o.isstartactivity = self:get_bool()
o.relativestarttime = self:get_int()
o.relativeendtime = self:get_int()
local _list = self:get_list('cfg_operationalactivity_ActivityEntry')
o.activityinfo = _list
o.activityinfo_id = {}
for _, _V in ipairs(_list) do
o.activityinfo_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.EctypeRegionSet'
get_or_create('cfg.map')['EctypeRegionSet'] = meta
function os:get_cfg_map_EctypeRegionSet()
local o = {}
setmetatable(o, cfg.map.EctypeRegionSet)
o.id = self:get_int()
local _list = self:get_list('cfg_map_IndexedPolygonRegion')
o.regions = _list
o.regions_id = {}
for _, _V in ipairs(_list) do
o.regions_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyInfo'
meta.OPEN_LEVEL = 20
meta.DECLARATION_LENGTH = 18
meta.PUBLICINFO_LENGTH = 50
meta.MAX_FAMILY_LEVEL = 7
meta.MAX_LOG_SIZE = 50
meta.MAX_RANK_NUM = 100
meta.NAME_MAX_LENGTH = 20
meta.NAME_MIN_LENGTH = 1
meta.ONLINE_DAY_NUM = 0
meta.CREATE_REQUIRE_YUANBAO = 250
meta.MIN_CROWD_FUND_YUANBAO = 100
meta.MIN_ADD_FUND_YUANBAO = 10
meta.MIN_ADD_FUND_YUANBAO_STEP = 10
meta.FUND_FAILED_COST = 0.1
meta.FUNDING_TIME = 43200
meta.MAX_QUIT_NUM = 2
meta.DISALLOW_ACTION_HOUR_AFTER_QUIT = 2
meta.MAX_APPLY_NUM = 10
meta.FAMILY_MEMBER_STR = "教众"
meta.DEFAULT_DECLARATION = "欢迎大家加入家族"
meta.DEFAULT_PUBLICINFO = "请输入家族公告，最多输入50个字！"
meta.ANIMAL_ACTIVITY_WEEK_NUM = 2
meta.ACTIVE_TRANSFER_RATE = 2
get_or_create('cfg.family')['FamilyInfo'] = meta
function os:get_cfg_family_FamilyInfo()
local o = {}
setmetatable(o, cfg.family.FamilyInfo)
o.familylvl = self:get_int()
o.requirebuildrate = self:get_int()
o.memberamount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemTitle'
get_or_create('cfg.item')['ItemTitle'] = meta
function os:get_cfg_item_ItemTitle()
local o = {}
setmetatable(o, cfg.item.ItemTitle)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ResumeGuide'
get_or_create('cfg.ectype')['ResumeGuide'] = meta
function os:get_cfg_ectype_ResumeGuide()
local o = {}
setmetatable(o, cfg.ectype.ResumeGuide)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetAi'
meta.PET_CHECK_AREA_1 = 8
meta.PET_CHECK_AREA_2 = 15
meta.PET_CHECK_TERMINAL = 1
meta.PET_ATK_AREA = 8
meta.PET_ATK_CHECK_TERMINAL = 1
get_or_create('cfg.pet')['PetAi'] = meta
function os:get_cfg_pet_PetAi()
local o = {}
setmetatable(o, cfg.pet.PetAi)
o.id = self:get_int()
local _list = self:get_list('int')
o.ai = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.HintInfo'
get_or_create('cfg.task')['HintInfo'] = meta
function os:get_cfg_task_HintInfo()
local o = {}
setmetatable(o, cfg.task.HintInfo)
o.acceptedhint = self:get_string()
o.unacceptedhint = self:get_string()
o.donehint = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.Effect'
get_or_create('cfg.buff')['Effect'] = meta
function os:get_cfg_buff_Effect()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.controller.Activity'
get_or_create('cfg.controller')['Activity'] = meta
function os:get_cfg_controller_Activity()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Dialog'
get_or_create('cfg.ectype')['Dialog'] = meta
function os:get_cfg_ectype_Dialog()
local o = {}
setmetatable(o, cfg.ectype.Dialog)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.id = self:get_int()
o.isbubble = self:get_bool()
o.speakertype = self:get_int()
o.time = self:get_int()
o.audioid = self:get_int()
o.content = self:get_string()
o.side = self:get_int()
o.isstop = self:get_bool()
o.dialogtype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.DirectBuy'
get_or_create('cfg.item')['DirectBuy'] = meta
function os:get_cfg_item_DirectBuy()
local o = {}
setmetatable(o, cfg.item.DirectBuy)
o.id = self:get_int()
o.name = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Delay'
get_or_create('cfg.ectype')['Delay'] = meta
function os:get_cfg_ectype_Delay()
local o = {}
setmetatable(o, cfg.ectype.Delay)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.PocketShop'
get_or_create('cfg.mall')['PocketShop'] = meta
function os:get_cfg_mall_PocketShop()
local o = {}
setmetatable(o, cfg.mall.PocketShop)
o.id = self:get_int()
o.itemid = self:get_cfg_cmd_action_OneItem()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.cost = self:get_cfg_cmd_condition_Currency()
o.introduce = self:get_string()
o.limitlist = self:get_cfg_cmd_condition_Limits()
o.requirelevel = self:get_cfg_cmd_condition_MinLevel()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.SpecBonus'
get_or_create('cfg.family')['SpecBonus'] = meta
function os:get_cfg_family_SpecBonus()
local o = {}
setmetatable(o, cfg.family.SpecBonus)
o.requiretaskamount = self:get_int()
o.bonus = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.MonsterRefreshInfo'
get_or_create('cfg.ectype')['MonsterRefreshInfo'] = meta
function os:get_cfg_ectype_MonsterRefreshInfo()
local o = {}
setmetatable(o, cfg.ectype.MonsterRefreshInfo)
o.regionid = self:get_int()
o.monsters = self:get_cfg_map_MonsterSpawn()
o.patrol = self:get_cfg_map_PatrolInfo()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITweenRotation'
get_or_create('cfg.ui')['ContentUITweenRotation'] = meta
function os:get_cfg_ui_ContentUITweenRotation()
local o = {}
setmetatable(o, cfg.ui.ContentUITweenRotation)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.style = self:get_string()
o.animationCurve = self:get_cfg_ui_AnimationCurve()
o.duration = self:get_float()
o.delay = self:get_float()
o.tweenGroup = self:get_int()
o.ignoreTimeScale = self:get_bool()
o.from = self:get_cfg_ui_Vector3()
o.to = self:get_cfg_ui_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaMultiList'
get_or_create('cfg.arena')['ArenaMultiList'] = meta
function os:get_cfg_arena_ArenaMultiList()
local o = {}
setmetatable(o, cfg.arena.ArenaMultiList)
o.id = self:get_int()
o.label = self:get_string()
o.tabname = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotGenderConfig'
get_or_create('cfg.plot')['PlotGenderConfig'] = meta
function os:get_cfg_plot_PlotGenderConfig()
local o = {}
setmetatable(o, cfg.plot.PlotGenderConfig)
o.cameradeviation = self:get_cfg_plot_Vector3()
o.rolemodelname = self:get_string()
o.rolehandlename = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.SkillInfo'
get_or_create('cfg.task')['SkillInfo'] = meta
function os:get_cfg_task_SkillInfo()
local o = {}
setmetatable(o, cfg.task.SkillInfo)
o.skillid = self:get_int()
o.skilllevel = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ShengWang'
get_or_create('cfg.cmd.condition')['ShengWang'] = meta
function os:get_cfg_cmd_condition_ShengWang()
local o = {}
setmetatable(o, cfg.cmd.condition.ShengWang)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.active.Findback'
get_or_create('cfg.active')['Findback'] = meta
function os:get_cfg_active_Findback()
local o = {}
setmetatable(o, cfg.active.Findback)
o.lv = self:get_int()
local _list = self:get_list('cfg_active_SystemLookBack')
o.findsystemlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.False'
get_or_create('cfg.ai')['False'] = meta
function os:get_cfg_ai_False()
local o = {}
setmetatable(o, cfg.ai.False)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SpeedMonsterRefInfo'
get_or_create('cfg.ectype')['SpeedMonsterRefInfo'] = meta
function os:get_cfg_ectype_SpeedMonsterRefInfo()
local o = {}
setmetatable(o, cfg.ectype.SpeedMonsterRefInfo)
o.monsters = self:get_cfg_map_MonsterSpawn()
o.regionid = self:get_int()
o.grade = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.BezierCurve'
get_or_create('cfg.map')['BezierCurve'] = meta
function os:get_cfg_map_BezierCurve()
local o = {}
setmetatable(o, cfg.map.BezierCurve)
local _list = self:get_list('cfg_map_BeizerVertex')
o.vertices = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.MultiAction'
get_or_create('cfg.cmd.action')['MultiAction'] = meta
function os:get_cfg_cmd_action_MultiAction()
local o = {}
setmetatable(o, cfg.cmd.action.MultiAction)
local _list = self:get_list('cfg_cmd_action_Action')
o.actions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemMedicine'
get_or_create('cfg.item')['ItemMedicine'] = meta
function os:get_cfg_item_ItemMedicine()
local o = {}
setmetatable(o, cfg.item.ItemMedicine)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.buffid = self:get_int()
o.hp = self:get_int()
o.mp = self:get_int()
o.cdgroup = self:get_cfg_cmd_condition_GroupCoolDown()
o.medicinetype = self:get_int()
o.roundicon = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.NPCDeployment'
get_or_create('cfg.map')['NPCDeployment'] = meta
function os:get_cfg_map_NPCDeployment()
local o = {}
setmetatable(o, cfg.map.NPCDeployment)
o.deploymentid = self:get_int()
o.npcid = self:get_int()
o.defaulton = self:get_bool()
o.position = self:get_cfg_map_Vector3()
o.orientation = self:get_cfg_map_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Rect'
get_or_create('cfg.ui')['Rect'] = meta
function os:get_cfg_ui_Rect()
local o = {}
setmetatable(o, cfg.ui.Rect)
o.left = self:get_float()
o.top = self:get_float()
o.width = self:get_float()
o.height = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetTotalLevel'
get_or_create('cfg.operationalactivity')['PetTotalLevel'] = meta
function os:get_cfg_operationalactivity_PetTotalLevel()
local o = {}
setmetatable(o, cfg.operationalactivity.PetTotalLevel)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CharacterAction'
get_or_create('cfg.ectype')['CharacterAction'] = meta
function os:get_cfg_ectype_CharacterAction()
local o = {}
setmetatable(o, cfg.ectype.CharacterAction)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.id = self:get_int()
o.action = self:get_string()
o.playtype = self:get_int()
o.actortype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.Maturerate'
get_or_create('cfg.pet')['Maturerate'] = meta
function os:get_cfg_pet_Maturerate()
local o = {}
setmetatable(o, cfg.pet.Maturerate)
o.hpmaturerate = self:get_float()
o.mpmaturerate = self:get_float()
o.minatkmaturerate = self:get_float()
o.maxatkmaturerate = self:get_float()
o.defmaturerate = self:get_float()
o.hitmaturerate = self:get_float()
o.hitresistmaturerate = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.Wish'
get_or_create('cfg.bonus')['Wish'] = meta
function os:get_cfg_bonus_Wish()
local o = {}
setmetatable(o, cfg.bonus.Wish)
o.wishitem = self:get_int()
o.bonus = self:get_cfg_cmd_action_Item()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.commoneffect.ShakeScreen'
get_or_create('cfg.commoneffect')['ShakeScreen'] = meta
function os:get_cfg_commoneffect_ShakeScreen()
local o = {}
setmetatable(o, cfg.commoneffect.ShakeScreen)
o.timeline = self:get_float()
o.type = self:get_string()
o.frequency = self:get_int()
o.frequencykeepduration = self:get_float()
o.frequencyattenuation = self:get_float()
o.amplitude = self:get_float()
o.amplitudeattenuation = self:get_float()
o.life = self:get_float()
o.minrange = self:get_float()
o.maxrange = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.GuideEffect'
get_or_create('cfg.guide')['GuideEffect'] = meta
function os:get_cfg_guide_GuideEffect()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MineralSpawn'
get_or_create('cfg.map')['MineralSpawn'] = meta
function os:get_cfg_map_MineralSpawn()
local o = {}
setmetatable(o, cfg.map.MineralSpawn)
o.mineralid = self:get_int()
o.count = self:get_int()
o.regeneratecd = self:get_float()
o.regeneratecount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.TabGroup'
get_or_create('cfg.ui')['TabGroup'] = meta
function os:get_cfg_ui_TabGroup()
local o = {}
setmetatable(o, cfg.ui.TabGroup)
o.tabgroupname = self:get_string()
o.tabgroupicon = self:get_string()
o.backgroundtype = self:get_int()
o.conid = self:get_int()
local _list = self:get_list('cfg_ui_Tab')
o.tabs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.StopTimer'
get_or_create('cfg.ectype')['StopTimer'] = meta
function os:get_cfg_ectype_StopTimer()
local o = {}
setmetatable(o, cfg.ectype.StopTimer)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanEvlove'
get_or_create('cfg.talisman')['TalismanEvlove'] = meta
function os:get_cfg_talisman_TalismanEvlove()
local o = {}
setmetatable(o, cfg.talisman.TalismanEvlove)
o.level = self:get_int()
o.name = self:get_string()
o.maturerate = self:get_float()
o.requireexp = self:get_int()
o.levellimit = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.ActivityCondition'
get_or_create('cfg.operationalactivity')['ActivityCondition'] = meta
function os:get_cfg_operationalactivity_ActivityCondition()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanExp'
get_or_create('cfg.talisman')['TalismanExp'] = meta
function os:get_cfg_talisman_TalismanExp()
local o = {}
setmetatable(o, cfg.talisman.TalismanExp)
o.level = self:get_int()
o.requireexp = self:get_int()
o.maturerate = self:get_float()
o.specialattackrate = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.RandomRune'
get_or_create('cfg.ectype')['RandomRune'] = meta
function os:get_cfg_ectype_RandomRune()
local o = {}
setmetatable(o, cfg.ectype.RandomRune)
o.id = self:get_int()
o.model = self:get_string()
o.icon = self:get_string()
local _list = self:get_list('cfg_ectype_RuneRandomInfo')
o.runes = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TalismanQuality'
get_or_create('cfg.operationalactivity')['TalismanQuality'] = meta
function os:get_cfg_operationalactivity_TalismanQuality()
local o = {}
setmetatable(o, cfg.operationalactivity.TalismanQuality)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.qulity = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.MarrigeConfig'
get_or_create('cfg.friend')['MarrigeConfig'] = meta
function os:get_cfg_friend_MarrigeConfig()
local o = {}
setmetatable(o, cfg.friend.MarrigeConfig)
local _list = self:get_list('cfg_friend_MarrigePackage')
o.marrigepack = _list
o.requirelevel = self:get_int()
local _list = self:get_list('int')
o.giftpackid = _list
local _list = self:get_list('cfg_cmd_action_Currencys')
o.giftcurrency = _list
o.divorceprice = self:get_cfg_cmd_condition_YuanBao()
o.marrigetextlength = self:get_int()
o.divorcetextlength = self:get_int()
o.divorceitemid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetFollow'
get_or_create('cfg.pet')['PetFollow'] = meta
function os:get_cfg_pet_PetFollow()
local o = {}
setmetatable(o, cfg.pet.PetFollow)
o.petamount = self:get_int()
local _list = self:get_list('cfg_pet_FollowInfo')
o.followlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIScrollView'
get_or_create('cfg.ui')['ContentUIScrollView'] = meta
function os:get_cfg_ui_ContentUIScrollView()
local o = {}
setmetatable(o, cfg.ui.ContentUIScrollView)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.contentPivot = self:get_string()
o.movement = self:get_string()
o.dragEffect = self:get_string()
o.scrollWheelFactor = self:get_float()
o.momentumAmount = self:get_float()
o.restrictWithinPanel = self:get_bool()
o.disableDragIfFits = self:get_bool()
o.smoothDragStart = self:get_bool()
o.iOSDragEmulation = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SpecialEffect'
get_or_create('cfg.ectype')['SpecialEffect'] = meta
function os:get_cfg_ectype_SpecialEffect()
local o = {}
setmetatable(o, cfg.ectype.SpecialEffect)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.path = self:get_string()
o.isopen = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.ArenaRanking'
get_or_create('cfg.operationalactivity')['ArenaRanking'] = meta
function os:get_cfg_operationalactivity_ArenaRanking()
local o = {}
setmetatable(o, cfg.operationalactivity.ArenaRanking)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.BoolExpr'
get_or_create('cfg.ai')['BoolExpr'] = meta
function os:get_cfg_ai_BoolExpr()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.And'
get_or_create('cfg.ai')['And'] = meta
function os:get_cfg_ai_And()
local o = {}
setmetatable(o, cfg.ai.And)
o.condition1 = self:get_cfg_ai_BoolExpr()
o.condition2 = self:get_cfg_ai_BoolExpr()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExpMonsterRef'
get_or_create('cfg.ectype')['ExpMonsterRef'] = meta
function os:get_cfg_ectype_ExpMonsterRef()
local o = {}
setmetatable(o, cfg.ectype.ExpMonsterRef)
o.mapid = self:get_int()
local _list = self:get_list('cfg_ectype_PointInfo')
o.refreshopint = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.RandomBonus'
get_or_create('cfg.cmd.action')['RandomBonus'] = meta
function os:get_cfg_cmd_action_RandomBonus()
local o = {}
setmetatable(o, cfg.cmd.action.RandomBonus)
local _list = self:get_list('cfg_cmd_action_BonusRandomInfo')
o.bonuss = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITweenAlpha'
get_or_create('cfg.ui')['ContentUITweenAlpha'] = meta
function os:get_cfg_ui_ContentUITweenAlpha()
local o = {}
setmetatable(o, cfg.ui.ContentUITweenAlpha)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.style = self:get_string()
o.animationCurve = self:get_cfg_ui_AnimationCurve()
o.duration = self:get_float()
o.delay = self:get_float()
o.tweenGroup = self:get_int()
o.ignoreTimeScale = self:get_bool()
o.from = self:get_float()
o.to = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetMaxAwake'
get_or_create('cfg.operationalactivity')['PetMaxAwake'] = meta
function os:get_cfg_operationalactivity_PetMaxAwake()
local o = {}
setmetatable(o, cfg.operationalactivity.PetMaxAwake)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.RandomItems'
get_or_create('cfg.cmd.action')['RandomItems'] = meta
function os:get_cfg_cmd_action_RandomItems()
local o = {}
setmetatable(o, cfg.cmd.action.RandomItems)
local _list = self:get_list('int')
o.items = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIAtlasSpriteData'
get_or_create('cfg.ui')['ContentUIAtlasSpriteData'] = meta
function os:get_cfg_ui_ContentUIAtlasSpriteData()
local o = {}
setmetatable(o, cfg.ui.ContentUIAtlasSpriteData)
o.name = self:get_string()
o.x = self:get_int()
o.y = self:get_int()
o.width = self:get_int()
o.height = self:get_int()
o.borderLeft = self:get_int()
o.borderRight = self:get_int()
o.borderTop = self:get_int()
o.borderBottom = self:get_int()
o.paddingLeft = self:get_int()
o.paddingRight = self:get_int()
o.paddingTop = self:get_int()
o.paddingBottom = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.achievement.Achievement'
get_or_create('cfg.achievement')['Achievement'] = meta
function os:get_cfg_achievement_Achievement()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ReviveCost'
get_or_create('cfg.ectype')['ReviveCost'] = meta
function os:get_cfg_ectype_ReviveCost()
local o = {}
setmetatable(o, cfg.ectype.ReviveCost)
o.cost = self:get_cfg_cmd_condition_XuNiBi()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AnnealItemEffect'
meta.RATE_BASE_NUMBER = 10000
get_or_create('cfg.equip')['AnnealItemEffect'] = meta
function os:get_cfg_equip_AnnealItemEffect()
local o = {}
setmetatable(o, cfg.equip.AnnealItemEffect)
o.id = self:get_int()
o.name = self:get_string()
o.lostcontrol = self:get_int()
local _list = self:get_list('int')
o.effect = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIToggle'
get_or_create('cfg.ui')['ContentUIToggle'] = meta
function os:get_cfg_ui_ContentUIToggle()
local o = {}
setmetatable(o, cfg.ui.ContentUIToggle)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.group = self:get_int()
o.optionCanBeNone = self:get_bool()
o.value = self:get_bool()
o.activeSprite = self:get_cfg_ui_ContentUITransform()
o.activeAnimation = self:get_string()
o.instantTween = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.LotteryTextureLevel'
get_or_create('cfg.lottery')['LotteryTextureLevel'] = meta
function os:get_cfg_lottery_LotteryTextureLevel()
local o = {}
setmetatable(o, cfg.lottery.LotteryTextureLevel)
o.pickcardtimes = self:get_string()
o.texturedetail = self:get_map('int', 'cfg_lottery_LotteryTextureDetail')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotAssetAudio'
get_or_create('cfg.plot')['PlotAssetAudio'] = meta
function os:get_cfg_plot_PlotAssetAudio()
local o = {}
setmetatable(o, cfg.plot.PlotAssetAudio)
o.index = self:get_string()
o.assettype = self:get_int()
o.detailtype = self:get_string()
o.indextable = self:get_string()
o.path = self:get_string()
o.introduction = self:get_string()
o.extraasset = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Spell'
get_or_create('cfg.ectype')['Spell'] = meta
function os:get_cfg_ectype_Spell()
local o = {}
setmetatable(o, cfg.ectype.Spell)
o.refreshtime = self:get_int()
local _list = self:get_list('cfg_map_Vector2')
o.positions = _list
local _list = self:get_list('cfg_ectype_RuneInfo')
o.runeinfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotAssetModel'
get_or_create('cfg.plot')['PlotAssetModel'] = meta
function os:get_cfg_plot_PlotAssetModel()
local o = {}
setmetatable(o, cfg.plot.PlotAssetModel)
o.index = self:get_string()
o.assettype = self:get_int()
o.detailtype = self:get_string()
o.indextable = self:get_string()
o.path = self:get_string()
o.introduction = self:get_string()
o.extraasset = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Controller'
get_or_create('cfg.ectype')['Controller'] = meta
function os:get_cfg_ectype_Controller()
local o = {}
setmetatable(o, cfg.ectype.Controller)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.audio.DialogSound'
get_or_create('cfg.audio')['DialogSound'] = meta
function os:get_cfg_audio_DialogSound()
local o = {}
setmetatable(o, cfg.audio.DialogSound)
o.id = self:get_int()
o.task = self:get_string()
o.isplayer = self:get_bool()
o.maleaudio = self:get_int()
o.femaleaudio = self:get_int()
o.boyaudio = self:get_int()
o.girlaudio = self:get_int()
o.npcaudio = self:get_int()
o.dialogtext = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.TraceObject'
meta.TRACETYPE_FLY = 0
meta.TRACETYPE_FIXED = 1
meta.BINDTYPE_BODY = 0
meta.BINDTYPE_HEAD = 1
meta.BINDTYPE_FOOT = 2
meta.BODY_CORRECT = 0.7
meta.HEAD_CORRECT = 1.3
get_or_create('cfg.skill')['TraceObject'] = meta
function os:get_cfg_skill_TraceObject()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.ShakeScreen'
get_or_create('cfg.skill')['ShakeScreen'] = meta
function os:get_cfg_skill_ShakeScreen()
local o = {}
setmetatable(o, cfg.skill.ShakeScreen)
o.timeline = self:get_float()
o.type = self:get_string()
o.frequency = self:get_int()
o.frequencykeepduration = self:get_float()
o.frequencyattenuation = self:get_float()
o.amplitude = self:get_float()
o.amplitudeattenuation = self:get_float()
o.life = self:get_float()
o.minrange = self:get_float()
o.maxrange = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.PetLevel'
get_or_create('cfg.cmd.action')['PetLevel'] = meta
function os:get_cfg_cmd_action_PetLevel()
local o = {}
setmetatable(o, cfg.cmd.action.PetLevel)
o.amount = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.BuffRune'
get_or_create('cfg.ectype')['BuffRune'] = meta
function os:get_cfg_ectype_BuffRune()
local o = {}
setmetatable(o, cfg.ectype.BuffRune)
o.id = self:get_int()
o.model = self:get_string()
o.icon = self:get_string()
o.buffid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemEntry'
get_or_create('cfg.item')['ItemEntry'] = meta
function os:get_cfg_item_ItemEntry()
local o = {}
setmetatable(o, cfg.item.ItemEntry)
o.itemid = self:get_int()
o.number = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.broadcast.Notice'
get_or_create('cfg.broadcast')['Notice'] = meta
function os:get_cfg_broadcast_Notice()
local o = {}
setmetatable(o, cfg.broadcast.Notice)
o.name = self:get_string()
o.content = self:get_string()
o.date = self:get_string()
o.isnew = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CirculateTimer'
get_or_create('cfg.ectype')['CirculateTimer'] = meta
function os:get_cfg_ectype_CirculateTimer()
local o = {}
setmetatable(o, cfg.ectype.CirculateTimer)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyTask'
get_or_create('cfg.family')['FamilyTask'] = meta
function os:get_cfg_family_FamilyTask()
local o = {}
setmetatable(o, cfg.family.FamilyTask)
o.task = self:get_int()
o.npc = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.exchange.Exchange'
get_or_create('cfg.exchange')['Exchange'] = meta
function os:get_cfg_exchange_Exchange()
local o = {}
setmetatable(o, cfg.exchange.Exchange)
o.id = self:get_int()
o.name = self:get_string()
o.index = self:get_int()
o.defaultyuanbao = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Portal'
meta.portalindex1 = "chuansongmen_01"
meta.portalindex2 = "chuansongmen_02"
get_or_create('cfg.map')['Portal'] = meta
function os:get_cfg_map_Portal()
local o = {}
setmetatable(o, cfg.map.Portal)
o.srcregionid = self:get_int()
o.srcregionname = self:get_string()
o.dstworldmapid = self:get_int()
o.dstregion = self:get_cfg_map_Vector2()
o.effectpos = self:get_cfg_map_Vector3()
o.rotation = self:get_float()
o.effecttype = self:get_int()
o.displaytext = self:get_bool()
o.transmode = self:get_int()
o.pathid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.achievement.TitleEvolve'
get_or_create('cfg.achievement')['TitleEvolve'] = meta
function os:get_cfg_achievement_TitleEvolve()
local o = {}
setmetatable(o, cfg.achievement.TitleEvolve)
o.name = self:get_string()
o.needvalue = self:get_cfg_cmd_condition_ChengJiu()
o.titleid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.TeamMemberNumber'
get_or_create('cfg.cmd.condition')['TeamMemberNumber'] = meta
function os:get_cfg_cmd_condition_TeamMemberNumber()
local o = {}
setmetatable(o, cfg.cmd.condition.TeamMemberNumber)
o.min = self:get_int()
o.max = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.ParticleEffect'
get_or_create('cfg.skill')['ParticleEffect'] = meta
function os:get_cfg_skill_ParticleEffect()
local o = {}
setmetatable(o, cfg.skill.ParticleEffect)
o.timeline = self:get_float()
o.id = self:get_int()
o.type = self:get_int()
o.fadeouttime = self:get_float()
o.path = self:get_string()
o.life = self:get_float()
o.followdirection = self:get_bool()
o.followbeattackeddirection = self:get_bool()
o.scale = self:get_float()
o.casterbindtype = self:get_int()
o.followbonedirection = self:get_bool()
o.targetbindtype = self:get_int()
o.instancetracetype = self:get_int()
o.worldoffsetx = self:get_float()
o.worldoffsety = self:get_float()
o.worldoffsetz = self:get_float()
o.worldrotx = self:get_float()
o.worldroty = self:get_float()
o.worldrotz = self:get_float()
o.boneposx = self:get_float()
o.boneposy = self:get_float()
o.boneposz = self:get_float()
o.bonerotx = self:get_float()
o.boneroty = self:get_float()
o.bonerotz = self:get_float()
o.bonescalex = self:get_float()
o.bonescaley = self:get_float()
o.bonescalez = self:get_float()
o.bonename = self:get_string()
o.tracetime = self:get_float()
o.aligntype = self:get_int()
o.ispooldestoryed = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.Idol'
get_or_create('cfg.friend')['Idol'] = meta
function os:get_cfg_friend_Idol()
local o = {}
setmetatable(o, cfg.friend.Idol)
o.id = self:get_long()
o.name = self:get_string()
o.sign = self:get_string()
o.guardtalk = self:get_string()
o.image = self:get_string()
o.icon = self:get_string()
o.guardthreshold = self:get_int()
local _list = self:get_list('cfg_friend_BonusList')
o.bonuslist = _list
o.guardtitleid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaSpecialAward'
get_or_create('cfg.arena')['ArenaSpecialAward'] = meta
function os:get_cfg_arena_ArenaSpecialAward()
local o = {}
setmetatable(o, cfg.arena.ArenaSpecialAward)
o.times = self:get_int()
o.award = self:get_cfg_cmd_action_Bonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MaxLevel'
get_or_create('cfg.cmd.condition')['MaxLevel'] = meta
function os:get_cfg_cmd_condition_MaxLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.MaxLevel)
o.level = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Tab'
get_or_create('cfg.ui')['Tab'] = meta
function os:get_cfg_ui_Tab()
local o = {}
setmetatable(o, cfg.ui.Tab)
o.tabname = self:get_string()
o.hide = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaInfo'
get_or_create('cfg.arena')['ArenaInfo'] = meta
function os:get_cfg_arena_ArenaInfo()
local o = {}
setmetatable(o, cfg.arena.ArenaInfo)
o.index = self:get_string()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.DailyRecharge'
get_or_create('cfg.operationalactivity')['DailyRecharge'] = meta
function os:get_cfg_operationalactivity_DailyRecharge()
local o = {}
setmetatable(o, cfg.operationalactivity.DailyRecharge)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetStageStar'
get_or_create('cfg.pet')['PetStageStar'] = meta
function os:get_cfg_pet_PetStageStar()
local o = {}
setmetatable(o, cfg.pet.PetStageStar)
o.qualitylevel = self:get_int()
o.introduction = self:get_string()
o.requirepetlvl = self:get_int()
local _list = self:get_list('cfg_cmd_condition_Item')
o.requireitem = _list
o.requirexunibi = self:get_cfg_cmd_condition_XuNiBi()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Profession'
get_or_create('cfg.role')['Profession'] = meta
function os:get_cfg_role_Profession()
local o = {}
setmetatable(o, cfg.role.Profession)
o.faction = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.isopen = self:get_bool()
o.modelname = self:get_string()
o.modelname2 = self:get_string()
o.weight = self:get_int()
o.talismanactionname = self:get_string()
o.defaultweaponid = self:get_int()
o.createweaponid = self:get_int()
o.createarmourid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GuardTowerMonsterInfo'
get_or_create('cfg.ectype')['GuardTowerMonsterInfo'] = meta
function os:get_cfg_ectype_GuardTowerMonsterInfo()
local o = {}
setmetatable(o, cfg.ectype.GuardTowerMonsterInfo)
o.id = self:get_int()
o.regionid = self:get_int()
o.clearaward = self:get_cfg_cmd_action_MultiBonus()
o.monsters = self:get_map('int', 'int')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GuardTower'
get_or_create('cfg.ectype')['GuardTower'] = meta
function os:get_cfg_ectype_GuardTower()
local o = {}
setmetatable(o, cfg.ectype.GuardTower)
o.lvlimit = self:get_cfg_cmd_condition_MinLevel()
o.opentime = self:get_cfg_ectype_TimeControler()
o.last = self:get_int()
o.dailylimit = self:get_cfg_cmd_condition_VipLimitsLite()
o.preparetime = self:get_int()
o.bgmpic = self:get_string()
o.matchtimeout = self:get_int()
o.entercountdown = self:get_int()
o.spellmsg = self:get_cfg_ectype_Spell()
local _list = self:get_list('cfg_ectype_GuardTowerZone')
o.zones = _list
o.zones_zoneid = {}
for _, _V in ipairs(_list) do
o.zones_zoneid[_V.zoneid] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FamilyTeamMonsterWave'
get_or_create('cfg.ectype')['FamilyTeamMonsterWave'] = meta
function os:get_cfg_ectype_FamilyTeamMonsterWave()
local o = {}
setmetatable(o, cfg.ectype.FamilyTeamMonsterWave)
o.regionid = self:get_int()
o.monster = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Item'
get_or_create('cfg.cmd.action')['Item'] = meta
function os:get_cfg_cmd_action_Item()
local o = {}
setmetatable(o, cfg.cmd.action.Item)
o.itemid = self:get_int()
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITweenPosition'
get_or_create('cfg.ui')['ContentUITweenPosition'] = meta
function os:get_cfg_ui_ContentUITweenPosition()
local o = {}
setmetatable(o, cfg.ui.ContentUITweenPosition)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.style = self:get_string()
o.animationCurve = self:get_cfg_ui_AnimationCurve()
o.duration = self:get_float()
o.delay = self:get_float()
o.tweenGroup = self:get_int()
o.ignoreTimeScale = self:get_bool()
o.from = self:get_cfg_ui_Vector3()
o.to = self:get_cfg_ui_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ProfessionLimit'
get_or_create('cfg.cmd.condition')['ProfessionLimit'] = meta
function os:get_cfg_cmd_condition_ProfessionLimit()
local o = {}
setmetatable(o, cfg.cmd.condition.ProfessionLimit)
o.profession = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Paomadeng'
get_or_create('cfg.role')['Paomadeng'] = meta
function os:get_cfg_role_Paomadeng()
local o = {}
setmetatable(o, cfg.role.Paomadeng)
local _list = self:get_list('int')
o.rolelevelup = _list
o.minanneal = self:get_int()
local _list = self:get_list('int')
o.perfuse = _list
o.mintalismanstart = self:get_int()
o.minpetawake = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemExperience'
get_or_create('cfg.item')['ItemExperience'] = meta
function os:get_cfg_item_ItemExperience()
local o = {}
setmetatable(o, cfg.item.ItemExperience)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.effect = self:get_cfg_cmd_action_AddExperience()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.storynote.NoteInfo'
get_or_create('cfg.storynote')['NoteInfo'] = meta
function os:get_cfg_storynote_NoteInfo()
local o = {}
setmetatable(o, cfg.storynote.NoteInfo)
o.noteid = self:get_int()
o.requireitemlist = self:get_cfg_cmd_condition_Items()
o.addproperty = self:get_cfg_equip_EquipPropertyData()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.audio.Audio'
get_or_create('cfg.audio')['Audio'] = meta
function os:get_cfg_audio_Audio()
local o = {}
setmetatable(o, cfg.audio.Audio)
o.id = self:get_int()
o.type = self:get_int()
o.priority = self:get_int()
o.cliplist = self:get_string()
o.minidletime = self:get_int()
o.maxidletime = self:get_int()
o.minvolume = self:get_float()
o.maxvolume = self:get_float()
o.probability = self:get_float()
o.isloop = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.HitPointAction'
get_or_create('cfg.skill')['HitPointAction'] = meta
function os:get_cfg_skill_HitPointAction()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SurviveTime'
get_or_create('cfg.ectype')['SurviveTime'] = meta
function os:get_cfg_ectype_SurviveTime()
local o = {}
setmetatable(o, cfg.ectype.SurviveTime)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.SkillEffect'
get_or_create('cfg.buff')['SkillEffect'] = meta
function os:get_cfg_buff_SkillEffect()
local o = {}
setmetatable(o, cfg.buff.SkillEffect)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Bomb'
get_or_create('cfg.skill')['Bomb'] = meta
function os:get_cfg_skill_Bomb()
local o = {}
setmetatable(o, cfg.skill.Bomb)
o.timeline = self:get_float()
o.id = self:get_int()
o.spawntype = self:get_int()
o.life = self:get_float()
o.effectid = self:get_int()
o.totarget = self:get_bool()
o.tracecurveid = self:get_int()
o.offsetx = self:get_float()
o.offsety = self:get_float()
o.offsetz = self:get_float()
o.tracetype = self:get_int()
o.casterbindtype = self:get_int()
o.targetbindtype = self:get_int()
o.attacklistid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.DeploymentLocation'
get_or_create('cfg.map')['DeploymentLocation'] = meta
function os:get_cfg_map_DeploymentLocation()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.AnimType'
meta.AnyState = "任何状态"
meta.Stand = "站立"
meta.Run = "奔跑"
meta.Jump = "跳跃"
meta.JumpLoop = "跳跃中"
meta.JumpEnd = "跳跃结束"
meta.Walk = "走"
meta.StandFight = "战斗站立"
meta.RunFight = "战斗奔跑"
meta.JumpFight = "战斗跳跃"
meta.JumpLoopFight = "战斗跳跃中"
meta.JumpEndFight = "战斗跳跃结束"
meta.PathFlyStart = "轨迹飞行开始"
meta.PathFlyLoop = "轨迹飞行中"
meta.PathFlyEnd = "轨迹飞行结束"
meta.Idle = "空闲"
meta.GetUp = "起身"
meta.StandRide = "骑行站立"
meta.RunRide = "骑行中"
meta.StandFly = "飞行"
meta.Fly = "飞行中"
meta.PullSword = "拔出武器"
meta.Inlayersword = "收起武器"
meta.Born = "出生"
meta.Hit = "被击"
meta.Stun = "被击晕"
meta.Float = "被击飞"
meta.Mining = "采矿"
meta.Dying = "死亡"
meta.Death = "已死亡"
meta.BindEffect = "绑定特效"
meta.BindFlyEffect = "飞行特效"
get_or_create('cfg.skill')['AnimType'] = meta
function os:get_cfg_skill_AnimType()
local o = {}
setmetatable(o, cfg.skill.AnimType)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Variator'
get_or_create('cfg.ectype')['Variator'] = meta
function os:get_cfg_ectype_Variator()
local o = {}
setmetatable(o, cfg.ectype.Variator)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Exit'
get_or_create('cfg.ectype')['Exit'] = meta
function os:get_cfg_ectype_Exit()
local o = {}
setmetatable(o, cfg.ectype.Exit)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.XuNiBi'
get_or_create('cfg.cmd.condition')['XuNiBi'] = meta
function os:get_cfg_cmd_condition_XuNiBi()
local o = {}
setmetatable(o, cfg.cmd.condition.XuNiBi)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.NoviceGuideSet'
get_or_create('cfg.guide')['NoviceGuideSet'] = meta
function os:get_cfg_guide_NoviceGuideSet()
local o = {}
setmetatable(o, cfg.guide.NoviceGuideSet)
o.id = self:get_int()
local _list = self:get_list('int')
o.subset = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.Cutscene'
get_or_create('cfg.plot')['Cutscene'] = meta
function os:get_cfg_plot_Cutscene()
local o = {}
setmetatable(o, cfg.plot.Cutscene)
o.playmode = self:get_int()
o.scriptname = self:get_string()
o.scenename = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.BlackMall'
get_or_create('cfg.mall')['BlackMall'] = meta
function os:get_cfg_mall_BlackMall()
local o = {}
setmetatable(o, cfg.mall.BlackMall)
o.id = self:get_int()
o.itemid = self:get_cfg_cmd_action_OneItem()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.cost = self:get_cfg_cmd_condition_Currency()
o.introduce = self:get_string()
o.limitlist = self:get_cfg_cmd_condition_Limits()
o.open = self:get_cfg_cmd_condition_BlackMallOpen()
o.page = self:get_int()
o.openprobability = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EnhanceData'
get_or_create('cfg.equip')['EnhanceData'] = meta
function os:get_cfg_equip_EnhanceData()
local o = {}
setmetatable(o, cfg.equip.EnhanceData)
o.addpropertyid = self:get_int()
local _list = self:get_list('int')
o.addvalues = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.MultiRune'
get_or_create('cfg.ectype')['MultiRune'] = meta
function os:get_cfg_ectype_MultiRune()
local o = {}
setmetatable(o, cfg.ectype.MultiRune)
o.id = self:get_int()
o.model = self:get_string()
o.icon = self:get_string()
o.items = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Sequence'
get_or_create('cfg.ai')['Sequence'] = meta
function os:get_cfg_ai_Sequence()
local o = {}
setmetatable(o, cfg.ai.Sequence)
local _list = self:get_list('cfg_ai_Expression')
o.exprs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.Branch'
get_or_create('cfg.plot')['Branch'] = meta
function os:get_cfg_plot_Branch()
local o = {}
setmetatable(o, cfg.plot.Branch)
o.id = self:get_int()
o.talktype = self:get_int()
o.namefontsize = self:get_int()
o.contentfontsize = self:get_int()
o.branch = self:get_bool()
local _list = self:get_list('cfg_plot_TalkInfo')
o.talks = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.ChooseNearest'
get_or_create('cfg.ai')['ChooseNearest'] = meta
function os:get_cfg_ai_ChooseNearest()
local o = {}
setmetatable(o, cfg.ai.ChooseNearest)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetMaxStar'
get_or_create('cfg.operationalactivity')['PetMaxStar'] = meta
function os:get_cfg_operationalactivity_PetMaxStar()
local o = {}
setmetatable(o, cfg.operationalactivity.PetMaxStar)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FamilyTeamLevelReward'
get_or_create('cfg.ectype')['FamilyTeamLevelReward'] = meta
function os:get_cfg_ectype_FamilyTeamLevelReward()
local o = {}
setmetatable(o, cfg.ectype.FamilyTeamLevelReward)
o.level = self:get_int()
o.bonus = self:get_cfg_cmd_action_JingYan()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.Drop'
meta.ATTENUATION_INTERVAL = 15
meta.EXP_ATTENUATION = {100,75,50,25,0}
meta.DROP_ATTENUATION = {100,80,60,40,40}
meta.EXP_RADIUS = 20
meta.SHOW_TIME = 2
meta.AUTO_BATTLE_SHOW_TIME = 5
meta.PROTECT_TIME = 30
meta.DISAPPEAR_TIME = 180
meta.PICK_RADIUS = 15
meta.CURVE_TYPE = 0
meta.DROP_ITEM_RADIUS = 5
get_or_create('cfg.bonus')['Drop'] = meta
function os:get_cfg_bonus_Drop()
local o = {}
setmetatable(o, cfg.bonus.Drop)
o.id = self:get_int()
o.dropname = self:get_string()
o.droplist = self:get_cfg_cmd_action_Bonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.ControllerAI'
get_or_create('cfg.ai')['ControllerAI'] = meta
function os:get_cfg_ai_ControllerAI()
local o = {}
setmetatable(o, cfg.ai.ControllerAI)
o.id = self:get_int()
local _list = self:get_list('int')
o.controllers = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.HPCheck'
get_or_create('cfg.ectype')['HPCheck'] = meta
function os:get_cfg_ectype_HPCheck()
local o = {}
setmetatable(o, cfg.ectype.HPCheck)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.id = self:get_int()
o.op = self:get_int()
o.percent = self:get_float()
o.slowmotion = self:get_bool()
o.motionid = self:get_string()
o.autofight = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.dailyactivity.DailyActivityTab'
get_or_create('cfg.dailyactivity')['DailyActivityTab'] = meta
function os:get_cfg_dailyactivity_DailyActivityTab()
local o = {}
setmetatable(o, cfg.dailyactivity.DailyActivityTab)
o.order = self:get_int()
o.name = self:get_string()
o.label = self:get_string()
o.tabindex = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.HPPercent'
get_or_create('cfg.ectype')['HPPercent'] = meta
function os:get_cfg_ectype_HPPercent()
local o = {}
setmetatable(o, cfg.ectype.HPPercent)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.JingYan'
get_or_create('cfg.cmd.action')['JingYan'] = meta
function os:get_cfg_cmd_action_JingYan()
local o = {}
setmetatable(o, cfg.cmd.action.JingYan)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIProgressBarBase'
get_or_create('cfg.ui')['ContentUIProgressBarBase'] = meta
function os:get_cfg_ui_ContentUIProgressBarBase()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.Karma'
get_or_create('cfg.pet')['Karma'] = meta
function os:get_cfg_pet_Karma()
local o = {}
setmetatable(o, cfg.pet.Karma)
o.karmaname = self:get_string()
o.introduction = self:get_string()
local _list = self:get_list('int')
o.petkeys = _list
o.carmatype = self:get_int()
local _list = self:get_list('cfg_pet_KarmaProp')
o.prop = _list
o.prop_level = {}
for _, _V in ipairs(_list) do
o.prop_level[_V.level] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.TiLi'
get_or_create('cfg.cmd.action')['TiLi'] = meta
function os:get_cfg_cmd_action_TiLi()
local o = {}
setmetatable(o, cfg.cmd.action.TiLi)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pathfly.PathCurve'
get_or_create('cfg.pathfly')['PathCurve'] = meta
function os:get_cfg_pathfly_PathCurve()
local o = {}
setmetatable(o, cfg.pathfly.PathCurve)
o.mode = self:get_string()
o.constspeed = self:get_bool()
o.speed = self:get_float()
o.positionvary = self:get_bool()
o.rotationvary = self:get_bool()
o.scalevary = self:get_bool()
local _list = self:get_list('cfg_pathfly_PathNode')
o.nodes = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITweenScale'
get_or_create('cfg.ui')['ContentUITweenScale'] = meta
function os:get_cfg_ui_ContentUITweenScale()
local o = {}
setmetatable(o, cfg.ui.ContentUITweenScale)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.style = self:get_string()
o.animationCurve = self:get_cfg_ui_AnimationCurve()
o.duration = self:get_float()
o.delay = self:get_float()
o.tweenGroup = self:get_int()
o.ignoreTimeScale = self:get_bool()
o.from = self:get_cfg_ui_Vector3()
o.to = self:get_cfg_ui_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Expression'
get_or_create('cfg.ai')['Expression'] = meta
function os:get_cfg_ai_Expression()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Currencys'
get_or_create('cfg.cmd.condition')['Currencys'] = meta
function os:get_cfg_cmd_condition_Currencys()
local o = {}
setmetatable(o, cfg.cmd.condition.Currencys)
local _list = self:get_list('cfg_cmd_condition_Currency')
o.currencys = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetKarma'
get_or_create('cfg.pet')['PetKarma'] = meta
function os:get_cfg_pet_PetKarma()
local o = {}
setmetatable(o, cfg.pet.PetKarma)
o.id = self:get_int()
o.petname = self:get_string()
local _list = self:get_list('cfg_pet_Karma')
o.petkarmas = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.KarmaProp'
get_or_create('cfg.pet')['KarmaProp'] = meta
function os:get_cfg_pet_KarmaProp()
local o = {}
setmetatable(o, cfg.pet.KarmaProp)
o.level = self:get_int()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.karmadata = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CallMonster'
get_or_create('cfg.ectype')['CallMonster'] = meta
function os:get_cfg_ectype_CallMonster()
local o = {}
setmetatable(o, cfg.ectype.CallMonster)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AnnealCost'
meta.COST_ITEM_ID = 10400001
get_or_create('cfg.equip')['AnnealCost'] = meta
function os:get_cfg_equip_AnnealCost()
local o = {}
setmetatable(o, cfg.equip.AnnealCost)
o.type = self:get_int()
local _list = self:get_list('int')
o.itemcost = _list
local _list = self:get_list('int')
o.expenses = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.DlgShowMode'
get_or_create('cfg.ui')['DlgShowMode'] = meta
function os:get_cfg_ui_DlgShowMode()
local o = {}
setmetatable(o, cfg.ui.DlgShowMode)
o.index = self:get_string()
o.showlimitmode = self:get_int()
local _list = self:get_list('string')
o.showlimitdlgs = _list
o.hidelimitmode = self:get_int()
local _list = self:get_list('string')
o.hidelimitdlgs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.character.shadow'
get_or_create('cfg.character')['shadow'] = meta
function os:get_cfg_character_shadow()
local o = {}
setmetatable(o, cfg.character.shadow)
o.modelpath = self:get_string()
o.bneedshadow = self:get_bool()
o.scale = self:get_cfg_map_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.NearNPC'
get_or_create('cfg.cmd.condition')['NearNPC'] = meta
function os:get_cfg_cmd_condition_NearNPC()
local o = {}
setmetatable(o, cfg.cmd.condition.NearNPC)
o.npcid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.ModelActionTemplate'
get_or_create('cfg.skill')['ModelActionTemplate'] = meta
function os:get_cfg_skill_ModelActionTemplate()
local o = {}
setmetatable(o, cfg.skill.ModelActionTemplate)
o.modelname = self:get_string()
o.actions = self:get_map('string', 'string')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.MissionKillMonster'
get_or_create('cfg.ectype')['MissionKillMonster'] = meta
function os:get_cfg_ectype_MissionKillMonster()
local o = {}
setmetatable(o, cfg.ectype.MissionKillMonster)
o.monsterid = self:get_int()
o.enviroment = self:get_int()
o.count = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Buff'
get_or_create('cfg.ectype')['Buff'] = meta
function os:get_cfg_ectype_Buff()
local o = {}
setmetatable(o, cfg.ectype.Buff)
o.buffid = self:get_int()
o.icon = self:get_string()
local _list = self:get_list('int')
o.price = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CheckVariator'
get_or_create('cfg.ectype')['CheckVariator'] = meta
function os:get_cfg_ectype_CheckVariator()
local o = {}
setmetatable(o, cfg.ectype.CheckVariator)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddHPPercentInterval'
get_or_create('cfg.buff')['AddHPPercentInterval'] = meta
function os:get_cfg_buff_AddHPPercentInterval()
local o = {}
setmetatable(o, cfg.buff.AddHPPercentInterval)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.StoryLayout'
get_or_create('cfg.ectype')['StoryLayout'] = meta
function os:get_cfg_ectype_StoryLayout()
local o = {}
setmetatable(o, cfg.ectype.StoryLayout)
o.id = self:get_int()
local _list = self:get_list('cfg_ectype_Layout')
o.layouts = _list
o.layouts_id = {}
for _, _V in ipairs(_list) do
o.layouts_id[_V.id] = _V
end
o.storyexitscene = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.HeroSets'
meta.bonusrefreshtime = 5
get_or_create('cfg.ectype')['HeroSets'] = meta
function os:get_cfg_ectype_HeroSets()
local o = {}
setmetatable(o, cfg.ectype.HeroSets)
o.dailylimit = self:get_cfg_cmd_condition_DayLimit()
local _list = self:get_list('int')
o.resettime = _list
local _list = self:get_list('cfg_cmd_condition_YuanBao')
o.changecost = _list
local _list = self:get_list('cfg_ectype_HeroEctypeMsg')
o.ectypemsg = _list
o.ectypemsg_id = {}
for _, _V in ipairs(_list) do
o.ectypemsg_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CurrencyMonsterRef'
get_or_create('cfg.ectype')['CurrencyMonsterRef'] = meta
function os:get_cfg_ectype_CurrencyMonsterRef()
local o = {}
setmetatable(o, cfg.ectype.CurrencyMonsterRef)
o.monsters = self:get_map('int', 'int')
o.regionid = self:get_int()
o.patrol = self:get_cfg_map_PatrolInfo()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.BonusConfig'
get_or_create('cfg.bonus')['BonusConfig'] = meta
function os:get_cfg_bonus_BonusConfig()
local o = {}
setmetatable(o, cfg.bonus.BonusConfig)
local _list = self:get_list('int')
o.retroactivecost = _list
local _list = self:get_list('int')
o.vipretroactive = _list
o.vowtime = self:get_int()
o.vowitem = self:get_int()
local _list = self:get_list('int')
o.viptimes = _list
o.monthlycard = self:get_cfg_cmd_condition_YuanBao()
o.growplan = self:get_cfg_cmd_condition_YuanBao()
o.growplanmaxlvl = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.TaskAccept'
get_or_create('cfg.task')['TaskAccept'] = meta
function os:get_cfg_task_TaskAccept()
local o = {}
setmetatable(o, cfg.task.TaskAccept)
o.mode = self:get_int()
o.npcid = self:get_int()
local _list = self:get_list('int')
o.pretaskid = _list
local _list = self:get_list('int')
o.mutextaskid = _list
o.finishanyonepretask = self:get_bool()
local _list = self:get_list('int')
o.titleid = _list
local _list = self:get_list('cfg_task_ItemInfo')
o.acceptrewarditem = _list
o.week = self:get_int()
o.rolelevelmin = self:get_int()
o.rolelevelmax = self:get_int()
o.factionlevelmin = self:get_int()
o.factionlevelmax = self:get_int()
o.prestigetype = self:get_int()
o.prestige = self:get_int()
local _list = self:get_list('int')
o.professionid = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GradeBonus'
get_or_create('cfg.ectype')['GradeBonus'] = meta
function os:get_cfg_ectype_GradeBonus()
local o = {}
setmetatable(o, cfg.ectype.GradeBonus)
o.grade = self:get_int()
o.bonus = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemTask'
get_or_create('cfg.item')['ItemTask'] = meta
function os:get_cfg_item_ItemTask()
local o = {}
setmetatable(o, cfg.item.ItemTask)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.AwakeInfo'
get_or_create('cfg.pet')['AwakeInfo'] = meta
function os:get_cfg_pet_AwakeInfo()
local o = {}
setmetatable(o, cfg.pet.AwakeInfo)
o.awakeid = self:get_int()
o.requirepetlevel = self:get_int()
o.petfragmentcost = self:get_int()
o.requirexunibi = self:get_cfg_cmd_condition_XuNiBi()
o.displaytext = self:get_string()
local _list = self:get_list('cfg_pet_AwakeEffect')
o.effect = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.active.Growup'
get_or_create('cfg.active')['Growup'] = meta
function os:get_cfg_active_Growup()
local o = {}
setmetatable(o, cfg.active.Growup)
o.systemid = self:get_int()
o.systemname = self:get_int()
o.name = self:get_string()
o.desc = self:get_string()
o.icon = self:get_string()
o.growtype = self:get_int()
o.uientry = self:get_string()
o.uitabindex = self:get_int()
o.uitabindex02 = self:get_int()
o.showbasedialog = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.PkInfo'
get_or_create('cfg.role')['PkInfo'] = meta
function os:get_cfg_role_PkInfo()
local o = {}
setmetatable(o, cfg.role.PkInfo)
o.level = self:get_cfg_cmd_condition_MinLevel()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.BonusList'
get_or_create('cfg.friend')['BonusList'] = meta
function os:get_cfg_friend_BonusList()
local o = {}
setmetatable(o, cfg.friend.BonusList)
o.bonusid = self:get_int()
o.frienddegree = self:get_int()
o.introduction = self:get_string()
o.bonus = self:get_cfg_cmd_action_Items()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanFeed'
meta.WASH_COST = 0
meta.REQUIRE_ITEM = 10400012
meta.REQUIRE_ITEM_NUM = 1
meta.CHANGE_PROPERTY_COST = 100
meta.FREE_CHANGE_LUCK_TIMES = 2
meta.WASH_LUCK_COST = 10
meta.BEST_LUCK_COST = 30
meta.BEST_LUCK_ID = 1
meta.DEFAULT_LUCK_ID = 3
meta.DEFAULT_WUXING = 2
meta.WUXING_OPEN_LEVEL = 50
get_or_create('cfg.talisman')['TalismanFeed'] = meta
function os:get_cfg_talisman_TalismanFeed()
local o = {}
setmetatable(o, cfg.talisman.TalismanFeed)
o.id = self:get_int()
o.name = self:get_int()
o.luckname = self:get_string()
o.maxusetime = self:get_int()
o.probability = self:get_float()
o.decribe = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MinMaxLevel'
get_or_create('cfg.cmd.condition')['MinMaxLevel'] = meta
function os:get_cfg_cmd_condition_MinMaxLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.MinMaxLevel)
o.min = self:get_int()
o.max = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.Vector3'
get_or_create('cfg.plot')['Vector3'] = meta
function os:get_cfg_plot_Vector3()
local o = {}
setmetatable(o, cfg.plot.Vector3)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.True'
get_or_create('cfg.ai')['True'] = meta
function os:get_cfg_ai_True()
local o = {}
setmetatable(o, cfg.ai.True)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.Bleed'
get_or_create('cfg.buff')['Bleed'] = meta
function os:get_cfg_buff_Bleed()
local o = {}
setmetatable(o, cfg.buff.Bleed)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Not'
get_or_create('cfg.ai')['Not'] = meta
function os:get_cfg_ai_Not()
local o = {}
setmetatable(o, cfg.ai.Not)
o.condition = self:get_cfg_ai_BoolExpr()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.BeginnerBonus'
get_or_create('cfg.bonus')['BeginnerBonus'] = meta
function os:get_cfg_bonus_BeginnerBonus()
local o = {}
setmetatable(o, cfg.bonus.BeginnerBonus)
o.dayscount = self:get_int()
o.bonuslist = self:get_cfg_cmd_action_Items()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.Normal'
get_or_create('cfg.guide')['Normal'] = meta
function os:get_cfg_guide_Normal()
local o = {}
setmetatable(o, cfg.guide.Normal)
o.id = self:get_int()
o.controldlg = self:get_string()
o.controluiobject = self:get_string()
o.handtype = self:get_int()
o.arrowtype = self:get_int()
o.bordertype = self:get_int()
local _list = self:get_list('float')
o.scale = _list
o.fixorbind = self:get_bool()
local _list = self:get_list('float')
o.offset = _list
o.addcomponent = self:get_bool()
o.needclip = self:get_bool()
local _list = self:get_list('float')
o.clipoffset = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.CurvePatrol'
get_or_create('cfg.map')['CurvePatrol'] = meta
function os:get_cfg_map_CurvePatrol()
local o = {}
setmetatable(o, cfg.map.CurvePatrol)
o.curversetid = self:get_int()
o.curverid = self:get_int()
o.patroltype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.ClickOver'
get_or_create('cfg.guide')['ClickOver'] = meta
function os:get_cfg_guide_ClickOver()
local o = {}
setmetatable(o, cfg.guide.ClickOver)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.QualityTexture'
get_or_create('cfg.pet')['QualityTexture'] = meta
function os:get_cfg_pet_QualityTexture()
local o = {}
setmetatable(o, cfg.pet.QualityTexture)
o.qualitytexture = self:get_map('int', 'string')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Riding'
meta.OPEN_LEVEL = 0
meta.RECOVERRIDE_TIME = 2
meta.PORTAL_HEIGHT = 5
get_or_create('cfg.equip')['Riding'] = meta
function os:get_cfg_equip_Riding()
local o = {}
setmetatable(o, cfg.equip.Riding)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.displayorder = self:get_int()
o.modelname = self:get_string()
o.introduction = self:get_string()
o.gain = self:get_string()
o.gainpage = self:get_string()
o.quickbuy = self:get_bool()
o.viplimit = self:get_cfg_cmd_condition_MinVipLevel()
o.price = self:get_cfg_cmd_condition_YuanBao()
o.ridingmotion = self:get_int()
o.quality = self:get_int()
o.speedbuff = self:get_float()
o.ridingheight = self:get_float()
o.initalminhigh = self:get_float()
o.initalmaxhigh = self:get_float()
o.upspeed = self:get_float()
o.downspeed = self:get_float()
o.ridespeed = self:get_float()
o.flyspeed = self:get_float()
o.initialheightinride = self:get_float()
o.initialheightinfly = self:get_float()
o.riggingpoint = self:get_string()
o.scale = self:get_float()
o.rotationy = self:get_float()
o.height = self:get_float()
o.rotateplayer = self:get_bool()
local _list = self:get_list('float')
o.rotation = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.ArenaWin'
get_or_create('cfg.operationalactivity')['ArenaWin'] = meta
function os:get_cfg_operationalactivity_ArenaWin()
local o = {}
setmetatable(o, cfg.operationalactivity.ArenaWin)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.navmesh.Mesh'
get_or_create('cfg.navmesh')['Mesh'] = meta
function os:get_cfg_navmesh_Mesh()
local o = {}
setmetatable(o, cfg.navmesh.Mesh)
local _list = self:get_list('cfg_navmesh_Vector3')
o.vertexs = _list
local _list = self:get_list('cfg_navmesh_Convex')
o.convexs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.NULL'
get_or_create('cfg.ai')['NULL'] = meta
function os:get_cfg_ai_NULL()
local o = {}
setmetatable(o, cfg.ai.NULL)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.MultiPlayerEctype'
meta.COINPOINT_OPEN_LEVEL = 30
get_or_create('cfg.ectype')['MultiPlayerEctype'] = meta
function os:get_cfg_ectype_MultiPlayerEctype()
local o = {}
setmetatable(o, cfg.ectype.MultiPlayerEctype)
o.requirelevel = self:get_cfg_cmd_condition_MinMaxLevel()
o.fightforce = self:get_int()
local _list = self:get_list('int')
o.opentime = _list
o.signuptime = self:get_int()
o.dailytimes = self:get_int()
o.viptimes = self:get_cfg_cmd_condition_VipLimits()
local _list = self:get_list('cfg_cmd_action_OneItem')
o.showbonusid = _list
o.entertime = self:get_int()
o.waittime = self:get_int()
local _list = self:get_list('cfg_ectype_GradeBonus')
o.gradebonus = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TalismanStar'
get_or_create('cfg.operationalactivity')['TalismanStar'] = meta
function os:get_cfg_operationalactivity_TalismanStar()
local o = {}
setmetatable(o, cfg.operationalactivity.TalismanStar)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemLevelUp'
get_or_create('cfg.item')['ItemLevelUp'] = meta
function os:get_cfg_item_ItemLevelUp()
local o = {}
setmetatable(o, cfg.item.ItemLevelUp)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.leveluptype = self:get_int()
o.lvl = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pay.FirstCharge'
get_or_create('cfg.pay')['FirstCharge'] = meta
function os:get_cfg_pay_FirstCharge()
local o = {}
setmetatable(o, cfg.pay.FirstCharge)
o.bonus = self:get_cfg_cmd_action_Bonus()
o.rmbtojifen = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotTalk'
get_or_create('cfg.plot')['PlotTalk'] = meta
function os:get_cfg_plot_PlotTalk()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AmuletWash'
get_or_create('cfg.equip')['AmuletWash'] = meta
function os:get_cfg_equip_AmuletWash()
local o = {}
setmetatable(o, cfg.equip.AmuletWash)
local _list = self:get_list('int')
o.probablilty = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.dailyactivity.WeekendActivityTab'
get_or_create('cfg.dailyactivity')['WeekendActivityTab'] = meta
function os:get_cfg_dailyactivity_WeekendActivityTab()
local o = {}
setmetatable(o, cfg.dailyactivity.WeekendActivityTab)
o.order = self:get_int()
o.name = self:get_string()
o.label = self:get_string()
o.tabindex = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Area'
get_or_create('cfg.ectype')['Area'] = meta
function os:get_cfg_ectype_Area()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.OpenTime'
get_or_create('cfg.family')['OpenTime'] = meta
function os:get_cfg_family_OpenTime()
local o = {}
setmetatable(o, cfg.family.OpenTime)
o.day = self:get_int()
o.hour = self:get_int()
o.minute = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AnnealItemUseLimit'
get_or_create('cfg.equip')['AnnealItemUseLimit'] = meta
function os:get_cfg_equip_AnnealItemUseLimit()
local o = {}
setmetatable(o, cfg.equip.AnnealItemUseLimit)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('bool')
o.uselimit = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.uiarea'
get_or_create('cfg.ui')['uiarea'] = meta
function os:get_cfg_ui_uiarea()
local o = {}
setmetatable(o, cfg.ui.uiarea)
o.id = self:get_int()
o.areatype = self:get_int()
o.posleftbuttom = self:get_cfg_map_Vector2()
o.posrightup = self:get_cfg_map_Vector2()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.TaskType'
get_or_create('cfg.common')['TaskType'] = meta
function os:get_cfg_common_TaskType()
local o = {}
setmetatable(o, cfg.common.TaskType)
o.id = self:get_int()
o.name = self:get_string()
o.countlimit = self:get_int()
o.resetcounttimerid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.GetSkill'
get_or_create('cfg.pet')['GetSkill'] = meta
function os:get_cfg_pet_GetSkill()
local o = {}
setmetatable(o, cfg.pet.GetSkill)
o.skillid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.FixCurrency'
get_or_create('cfg.cmd.action')['FixCurrency'] = meta
function os:get_cfg_cmd_action_FixCurrency()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.WidgetOfList'
get_or_create('cfg.guide')['WidgetOfList'] = meta
function os:get_cfg_guide_WidgetOfList()
local o = {}
setmetatable(o, cfg.guide.WidgetOfList)
o.id = self:get_int()
o.controldlg = self:get_string()
o.controluiobject = self:get_string()
o.handtype = self:get_int()
o.arrowtype = self:get_int()
o.bordertype = self:get_int()
local _list = self:get_list('float')
o.scale = _list
o.fixorbind = self:get_bool()
local _list = self:get_list('float')
o.offset = _list
o.addcomponent = self:get_bool()
o.needclip = self:get_bool()
local _list = self:get_list('float')
o.clipoffset = _list
o.index = self:get_int()
o.widgetname = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.weapon.weapon'
get_or_create('cfg.equip.weapon')['weapon'] = meta
function os:get_cfg_equip_weapon_weapon()
local o = {}
setmetatable(o, cfg.equip.weapon.weapon)
o.id = self:get_int()
o.weaponname = self:get_string()
o.lpath = self:get_string()
o.rpath = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PlayAudio'
get_or_create('cfg.ectype')['PlayAudio'] = meta
function os:get_cfg_ectype_PlayAudio()
local o = {}
setmetatable(o, cfg.ectype.PlayAudio)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.RoleLevel'
get_or_create('cfg.operationalactivity')['RoleLevel'] = meta
function os:get_cfg_operationalactivity_RoleLevel()
local o = {}
setmetatable(o, cfg.operationalactivity.RoleLevel)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.StaticAI'
get_or_create('cfg.ai')['StaticAI'] = meta
function os:get_cfg_ai_StaticAI()
local o = {}
setmetatable(o, cfg.ai.StaticAI)
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExpMonsterMsg'
get_or_create('cfg.ectype')['ExpMonsterMsg'] = meta
function os:get_cfg_ectype_ExpMonsterMsg()
local o = {}
setmetatable(o, cfg.ectype.ExpMonsterMsg)
o.minlevel = self:get_int()
o.maxlevel = self:get_int()
o.battlepower = self:get_int()
o.monsterid = self:get_int()
o.positionx = self:get_float()
o.positiony = self:get_float()
o.scale = self:get_float()
local _list = self:get_list('cfg_ectype_ExpMonsterRef')
o.monsterref = _list
local _list = self:get_list('cfg_ectype_ExpMonsterBonus')
o.monsterbonus = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIListItem'
get_or_create('cfg.ui')['ContentUIListItem'] = meta
function os:get_cfg_ui_ContentUIListItem()
local o = {}
setmetatable(o, cfg.ui.ContentUIListItem)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.m_nIndex = self:get_int()
o.m_uiSpriteIcon = self:get_cfg_ui_ContentUITransform()
o.m_uiSpriteRank = self:get_cfg_ui_ContentUITransform()
o.m_uiTextureIcon = self:get_cfg_ui_ContentUITransform()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.SkillDescribe'
get_or_create('cfg.skill')['SkillDescribe'] = meta
function os:get_cfg_skill_SkillDescribe()
local o = {}
setmetatable(o, cfg.skill.SkillDescribe)
o.skillid = self:get_int()
o.name = self:get_string()
local _list = self:get_list('string')
o.description = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.VipLimits2'
get_or_create('cfg.cmd.condition')['VipLimits2'] = meta
function os:get_cfg_cmd_condition_VipLimits2()
local o = {}
setmetatable(o, cfg.cmd.condition.VipLimits2)
local _list = self:get_list('int')
o.entertimes = _list
local _list = self:get_list('cfg_cmd_condition_Condition')
o.costs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.ArenaAI'
get_or_create('cfg.ai')['ArenaAI'] = meta
function os:get_cfg_ai_ArenaAI()
local o = {}
setmetatable(o, cfg.ai.ArenaAI)
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.BossConfig'
get_or_create('cfg.family')['BossConfig'] = meta
function os:get_cfg_family_BossConfig()
local o = {}
setmetatable(o, cfg.family.BossConfig)
local _list = self:get_list('cfg_family_OpenTime')
o.opentime = _list
o.signtime = self:get_int()
o.battletime = self:get_int()
o.monsterinfo = self:get_map('int', 'int')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.AchievementPoints'
get_or_create('cfg.operationalactivity')['AchievementPoints'] = meta
function os:get_cfg_operationalactivity_AchievementPoints()
local o = {}
setmetatable(o, cfg.operationalactivity.AchievementPoints)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIGrid'
get_or_create('cfg.ui')['ContentUIGrid'] = meta
function os:get_cfg_ui_ContentUIGrid()
local o = {}
setmetatable(o, cfg.ui.ContentUIGrid)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.arrangement = self:get_string()
o.style = self:get_string()
o.cellWidth = self:get_float()
o.cellHeight = self:get_float()
o.maxPerLine = self:get_int()
o.animateSmoothly = self:get_bool()
o.hideInactive = self:get_bool()
o.keepWithinPanel = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.commoneffect.Action'
get_or_create('cfg.commoneffect')['Action'] = meta
function os:get_cfg_commoneffect_Action()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.AwakeEffect'
get_or_create('cfg.talisman')['AwakeEffect'] = meta
function os:get_cfg_talisman_AwakeEffect()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Jade'
get_or_create('cfg.equip')['Jade'] = meta
function os:get_cfg_equip_Jade()
local o = {}
setmetatable(o, cfg.equip.Jade)
o.jadelvl = self:get_int()
o.icon = self:get_string()
o.requirelvl = self:get_cfg_cmd_condition_MinLevel()
o.requireitem = self:get_cfg_cmd_condition_Item()
o.percent = self:get_float()
o.maxbonus = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.DisplayDlg'
get_or_create('cfg.cmd.condition')['DisplayDlg'] = meta
function os:get_cfg_cmd_condition_DisplayDlg()
local o = {}
setmetatable(o, cfg.cmd.condition.DisplayDlg)
o.open = self:get_int()
o.dialogname = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.exchange.Const'
meta.MAX_EXCHANGE_ITEM_NUM = 50
meta.MAX_EXCHANGE_LOG_NUM = 50
meta.BUY_COST_CURRENTCY_TYPE = 10200002
meta.NUM_OF_PAGE = 20
meta.DEFAULT_SORT_TYPE = 0
meta.DEFAULT_SORT_ORDER = 0
meta.OPEN_LEVEL = 0
meta.EXCHANGE_TAX = 0.05
meta.EXCHANGE_UNSHELVE_TIME = 86400
meta.UNSHELVE_MAILID = 17
get_or_create('cfg.exchange')['Const'] = meta
function os:get_cfg_exchange_Const()
local o = {}
setmetatable(o, cfg.exchange.Const)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PersonalBoss'
meta.TALK_INTERVAL = 5
meta.TALK_LAST = 5
meta.ENDOFFTIME = 1
meta.REVIVETIME = 30
get_or_create('cfg.ectype')['PersonalBoss'] = meta
function os:get_cfg_ectype_PersonalBoss()
local o = {}
setmetatable(o, cfg.ectype.PersonalBoss)
o.ectypeid = self:get_int()
o.battlepower = self:get_int()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.viplimit = self:get_cfg_cmd_condition_MinVipLevel()
o.tasklimit = self:get_cfg_cmd_condition_CompleteTask()
o.taskdescribe = self:get_string()
o.name = self:get_string()
local _list = self:get_list('int')
o.showbonusitemid = _list
o.bossid = self:get_int()
o.allowicon = self:get_string()
o.forbidicon = self:get_string()
o.bossicon = self:get_string()
o.localposx = self:get_float()
o.localposy = self:get_float()
o.scale = self:get_float()
o.bosstalk = self:get_string()
o.introduction = self:get_string()
o.mainregionid = self:get_int()
local _list = self:get_list('cfg_ectype_MonsterInfo')
o.monsters = _list
o.bonus = self:get_cfg_cmd_action_Bonus()
o.daylimit = self:get_cfg_cmd_condition_VipLimits()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.PassiveSkill'
get_or_create('cfg.skill')['PassiveSkill'] = meta
function os:get_cfg_skill_PassiveSkill()
local o = {}
setmetatable(o, cfg.skill.PassiveSkill)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.introduction = self:get_string()
o.passivebuffid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ExpMonster'
get_or_create('cfg.ectype')['ExpMonster'] = meta
function os:get_cfg_ectype_ExpMonster()
local o = {}
setmetatable(o, cfg.ectype.ExpMonster)
o.decs = self:get_string()
o.lasttime = self:get_int()
local _list = self:get_list('cfg_ectype_TimeControler')
o.opentimes = _list
o.refreshtime = self:get_int()
o.id = self:get_int()
o.minlevel = self:get_cfg_cmd_condition_MinLevel()
local _list = self:get_list('cfg_ectype_ExpMonsterMsg')
o.monstermsg = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.commoneffect.CommonEffect'
get_or_create('cfg.commoneffect')['CommonEffect'] = meta
function os:get_cfg_commoneffect_CommonEffect()
local o = {}
setmetatable(o, cfg.commoneffect.CommonEffect)
o.id = self:get_int()
local _list = self:get_list('cfg_commoneffect_Action')
o.actions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MineralDeployment'
get_or_create('cfg.map')['MineralDeployment'] = meta
function os:get_cfg_map_MineralDeployment()
local o = {}
setmetatable(o, cfg.map.MineralDeployment)
o.deploymentid = self:get_int()
local _list = self:get_list('cfg_map_MineralSpawn')
o.minerals = _list
o.location = self:get_cfg_map_DeploymentLocation()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.MonsterInfo'
get_or_create('cfg.ectype')['MonsterInfo'] = meta
function os:get_cfg_ectype_MonsterInfo()
local o = {}
setmetatable(o, cfg.ectype.MonsterInfo)
o.isboss = self:get_bool()
o.regionid = self:get_int()
o.monsters = self:get_map('int', 'int')
o.patrol = self:get_cfg_map_PatrolInfo()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.SourceInfo'
get_or_create('cfg.item')['SourceInfo'] = meta
function os:get_cfg_item_SourceInfo()
local o = {}
setmetatable(o, cfg.item.SourceInfo)
o.desc = self:get_string()
o.dlgname = self:get_string()
o.tabindex1 = self:get_int()
o.tabindex2 = self:get_int()
o.tabindex3 = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TalismanAwake'
get_or_create('cfg.operationalactivity')['TalismanAwake'] = meta
function os:get_cfg_operationalactivity_TalismanAwake()
local o = {}
setmetatable(o, cfg.operationalactivity.TalismanAwake)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mail.Consts'
meta.MAX_MAIL_NUM = 20
meta.ACCESSORY_MAIL_EXPIRE_TIME = 2592000
meta.NO_ACCESSORY_MAIL_EXPIRE_TIME = 604800
meta.GM_MAIL_ID = 1
get_or_create('cfg.mail')['Consts'] = meta
function os:get_cfg_mail_Consts()
local o = {}
setmetatable(o, cfg.mail.Consts)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.IsNavigating'
get_or_create('cfg.cmd.condition')['IsNavigating'] = meta
function os:get_cfg_cmd_condition_IsNavigating()
local o = {}
setmetatable(o, cfg.cmd.condition.IsNavigating)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.LotteryTexture'
get_or_create('cfg.lottery')['LotteryTexture'] = meta
function os:get_cfg_lottery_LotteryTexture()
local o = {}
setmetatable(o, cfg.lottery.LotteryTexture)
o.recievedcurrency = self:get_int()
o.name = self:get_string()
o.order = self:get_int()
o.recievedwholecardbgicon = self:get_string()
o.exchangeicon = self:get_string()
o.levels = self:get_map('int', 'cfg_lottery_LotteryTextureLevel')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.DefaultAI'
get_or_create('cfg.ai')['DefaultAI'] = meta
function os:get_cfg_ai_DefaultAI()
local o = {}
setmetatable(o, cfg.ai.DefaultAI)
o.id = self:get_int()
o.walkback = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.PolygonRegion'
get_or_create('cfg.map')['PolygonRegion'] = meta
function os:get_cfg_map_PolygonRegion()
local o = {}
setmetatable(o, cfg.map.PolygonRegion)
local _list = self:get_list('cfg_map_Vector3')
o.vertices = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Layout'
get_or_create('cfg.ectype')['Layout'] = meta
function os:get_cfg_ectype_Layout()
local o = {}
setmetatable(o, cfg.ectype.Layout)
o.id = self:get_int()
o.name = self:get_string()
o.gfxid = self:get_int()
o.area = self:get_cfg_ectype_Area()
local _list = self:get_list('cfg_ectype_Passage')
o.enters = _list
o.enters_id = {}
for _, _V in ipairs(_list) do
o.enters_id[_V.id] = _V
end
local _list = self:get_list('cfg_ectype_Passage')
o.exits = _list
o.exits_id = {}
for _, _V in ipairs(_list) do
o.exits_id[_V.id] = _V
end
local _list = self:get_list('cfg_ectype_Action')
o.scripts = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaConfig'
meta.firsttitle = "天下第一"
meta.secondtitle = "天下第二"
meta.thirdtitle = "天下第三"
get_or_create('cfg.arena')['ArenaConfig'] = meta
function os:get_cfg_arena_ArenaConfig()
local o = {}
setmetatable(o, cfg.arena.ArenaConfig)
o.openlevel = self:get_int()
o.shengwangrefreshinterval = self:get_int()
o.maxreportnum = self:get_int()
o.arrowindex = self:get_string()
o.arrowdistance = self:get_float()
o.challengelimit = self:get_cfg_cmd_condition_VipLimits()
o.refreshopponentlimit = self:get_cfg_cmd_condition_VipLimits()
local _list = self:get_list('cfg_bonus_RankBonusList')
o.rankbonus = _list
local _list = self:get_list('cfg_arena_ArenaAward')
o.awardlist = _list
local _list = self:get_list('cfg_arena_ArenaShengwang')
o.shengwangsteplist = _list
local _list = self:get_list('cfg_arena_ArenaSpecialAward')
o.specialawardlist = _list
o.specialawardlist_times = {}
for _, _V in ipairs(_list) do
o.specialawardlist_times[_V.times] = _V
end
o.introduction = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.setting.SettingConfig'
get_or_create('cfg.setting')['SettingConfig'] = meta
function os:get_cfg_setting_SettingConfig()
local o = {}
setmetatable(o, cfg.setting.SettingConfig)
local _list = self:get_list('cfg_setting_GraphicSetting')
o.graphicsettings = _list
o.sceneobjectlayermap = self:get_map('int', 'int')
local _list = self:get_list('cfg_setting_FogSetting')
o.defaultfogsettings = _list
o.scenefogsettings = self:get_map('string', 'cfg_setting_LinearFogSettingList')
local _list = self:get_list('string')
o.ignorefogscenes = _list
local _list = self:get_list('int')
o.androidmemthreshold = _list
local _list = self:get_list('int')
o.iosmemthreshold = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Broadcast'
get_or_create('cfg.ectype')['Broadcast'] = meta
function os:get_cfg_ectype_Broadcast()
local o = {}
setmetatable(o, cfg.ectype.Broadcast)
o.broadcastdec = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GodMonsterInfo'
get_or_create('cfg.ectype')['GodMonsterInfo'] = meta
function os:get_cfg_ectype_GodMonsterInfo()
local o = {}
setmetatable(o, cfg.ectype.GodMonsterInfo)
o.id = self:get_int()
o.position = self:get_cfg_map_Vector2()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.Disperse'
get_or_create('cfg.buff')['Disperse'] = meta
function os:get_cfg_buff_Disperse()
local o = {}
setmetatable(o, cfg.buff.Disperse)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.NormalAttack'
get_or_create('cfg.skill')['NormalAttack'] = meta
function os:get_cfg_skill_NormalAttack()
local o = {}
setmetatable(o, cfg.skill.NormalAttack)
o.target = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyTaskConfig'
get_or_create('cfg.family')['FamilyTaskConfig'] = meta
function os:get_cfg_family_FamilyTaskConfig()
local o = {}
setmetatable(o, cfg.family.FamilyTaskConfig)
o.openlevel = self:get_int()
o.nextliblevel = self:get_int()
o.circletaskamount = self:get_int()
o.daycirclelimit = self:get_cfg_cmd_condition_DayLimit()
o.weekbonus = self:get_int()
o.weekalert = self:get_int()
o.refreshtime = self:get_int()
o.completecost = self:get_cfg_cmd_condition_YuanBao()
local _list = self:get_list('float')
o.taskbonusrate = _list
local _list = self:get_list('float')
o.circlebonusrate = _list
o.npcid = self:get_int()
o.simpleclearamount = self:get_int()
local _list = self:get_list('string')
o.tasktalk = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TalismanMaxLevel'
get_or_create('cfg.operationalactivity')['TalismanMaxLevel'] = meta
function os:get_cfg_operationalactivity_TalismanMaxLevel()
local o = {}
setmetatable(o, cfg.operationalactivity.TalismanMaxLevel)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.Attr'
get_or_create('cfg.fight')['Attr'] = meta
function os:get_cfg_fight_Attr()
local o = {}
setmetatable(o, cfg.fight.Attr)
o.hp = self:get_float()
o.mp = self:get_float()
o.attackvaluemin = self:get_float()
o.attackvaluemax = self:get_float()
o.defence = self:get_float()
o.hitrate = self:get_float()
o.hitresistrate = self:get_float()
o.critrate = self:get_float()
o.critvalue = self:get_float()
o.critresistrate = self:get_float()
o.critresistvalue = self:get_float()
o.excellentrate = self:get_float()
o.excellentvalue = self:get_float()
o.excellentresistrate = self:get_float()
o.excellentresistvalue = self:get_float()
o.lucky = self:get_float()
o.attackmultirate = self:get_float()
o.defencemultirate = self:get_float()
o.abnormalresistrate = self:get_float()
o.abnormalhitrate = self:get_float()
o.movespeed = self:get_float()
o.heal = self:get_float()
o.damagetohuman = self:get_float()
o.damagetopet = self:get_float()
o.resistdamagefromhuman = self:get_float()
o.resistdamagefrompet = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.VipLimits'
get_or_create('cfg.cmd.condition')['VipLimits'] = meta
function os:get_cfg_cmd_condition_VipLimits()
local o = {}
setmetatable(o, cfg.cmd.condition.VipLimits)
o.currencytype = self:get_int()
local _list = self:get_list('int')
o.entertimes = _list
local _list = self:get_list('int')
o.amout = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.SimpleCondition'
get_or_create('cfg.operationalactivity')['SimpleCondition'] = meta
function os:get_cfg_operationalactivity_SimpleCondition()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PlayCG'
get_or_create('cfg.ectype')['PlayCG'] = meta
function os:get_cfg_ectype_PlayCG()
local o = {}
setmetatable(o, cfg.ectype.PlayCG)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.name = self:get_string()
o.canskip = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.FaBaoJiFen'
get_or_create('cfg.cmd.condition')['FaBaoJiFen'] = meta
function os:get_cfg_cmd_condition_FaBaoJiFen()
local o = {}
setmetatable(o, cfg.cmd.condition.FaBaoJiFen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.BindYuanBao'
get_or_create('cfg.cmd.condition')['BindYuanBao'] = meta
function os:get_cfg_cmd_condition_BindYuanBao()
local o = {}
setmetatable(o, cfg.cmd.condition.BindYuanBao)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.RechargeShop'
get_or_create('cfg.operationalactivity')['RechargeShop'] = meta
function os:get_cfg_operationalactivity_RechargeShop()
local o = {}
setmetatable(o, cfg.operationalactivity.RechargeShop)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.limit = self:get_cfg_cmd_condition_Limit()
o.cost = self:get_cfg_cmd_condition_ChongZhiJiFen()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Title'
meta.OPEN_LEVEL = 10
meta.TitlePrefabGroupPath = "ui/dlgheadtitle.ui"
get_or_create('cfg.role')['Title'] = meta
function os:get_cfg_role_Title()
local o = {}
setmetatable(o, cfg.role.Title)
o.id = self:get_int()
o.titletype = self:get_int()
o.showtype = self:get_int()
o.titletypename = self:get_string()
o.name = self:get_string()
o.path = self:get_string()
o.condition = self:get_string()
o.titletime = self:get_int()
o.description = self:get_string()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.property = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CurrencyEctype'
meta.OPEN_CG = 10009004
get_or_create('cfg.ectype')['CurrencyEctype'] = meta
function os:get_cfg_ectype_CurrencyEctype()
local o = {}
setmetatable(o, cfg.ectype.CurrencyEctype)
o.id = self:get_int()
o.costtili = self:get_cfg_cmd_condition_TiLi()
o.ectypetype = self:get_int()
o.storyname = self:get_string()
o.introduction = self:get_string()
o.decs = self:get_string()
o.backgroundpic = self:get_string()
local _list = self:get_list('int')
o.showbonusid = _list
o.mainregionid = self:get_int()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.showlv = self:get_string()
o.battlepower = self:get_int()
o.ectyperandomdrop = self:get_cfg_cmd_action_RandomBonus()
o.refmsg = self:get_string()
o.hurttocurrency = self:get_float()
o.monster = self:get_int()
o.position = self:get_cfg_map_Vector2()
o.orientangle = self:get_float()
o.maxgetcurrency = self:get_int()
o.spellbonustime = self:get_int()
local _list = self:get_list('cfg_map_Vector2')
o.positions = _list
local _list = self:get_list('cfg_ectype_RuneInfo')
o.runeinfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TeamRegionInfo'
get_or_create('cfg.ectype')['TeamRegionInfo'] = meta
function os:get_cfg_ectype_TeamRegionInfo()
local o = {}
setmetatable(o, cfg.ectype.TeamRegionInfo)
o.bornregion = self:get_int()
o.bornposition = self:get_cfg_map_Vector2()
local _list = self:get_list('cfg_ectype_SpeedRegionInfo')
o.speedregioninfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.SuitsData'
get_or_create('cfg.equip')['SuitsData'] = meta
function os:get_cfg_equip_SuitsData()
local o = {}
setmetatable(o, cfg.equip.SuitsData)
o.propertydata = self:get_cfg_equip_EquipPropertyData()
o.amountlimit = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.RoleConfig'
get_or_create('cfg.role')['RoleConfig'] = meta
function os:get_cfg_role_RoleConfig()
local o = {}
setmetatable(o, cfg.role.RoleConfig)
o.maxtili = self:get_int()
o.levelupgaintili = self:get_cfg_cmd_action_TiLi()
o.addonetiliinterval = self:get_int()
o.buyaddtili = self:get_cfg_cmd_action_TiLi()
o.tilidanid = self:get_int()
o.buytilicost = self:get_cfg_cmd_condition_VipLimits()
o.renamecost = self:get_cfg_cmd_condition_VipLimits()
o.minnamelength = self:get_int()
o.maxnamelength = self:get_int()
local _list = self:get_list('int')
o.playeramount = _list
local _list = self:get_list('int')
o.monsteramount = _list
local _list = self:get_list('float')
o.cameraposition = _list
o.autobanskill = self:get_map('int', 'int')
o.invitecooldown = self:get_cfg_cmd_condition_CoolDown()
o.minspeaklevel = self:get_int()
o.minreportlevel = self:get_int()
o.everydayreportnum = self:get_int()
o.basicspeakinterval = self:get_int()
local _list = self:get_list('int')
o.bereportednum = _list
local _list = self:get_list('int')
o.silenttime = _list
local _list = self:get_list('int')
o.intervalreducebyvip = _list
local _list = self:get_list('int')
o.intervallevel = _list
local _list = self:get_list('int')
o.intervalreducebylevel = _list
o.pvpdamage = self:get_float()
local _list = self:get_list('float')
o.autobattleradius = _list
o.defaultradius = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Dress'
meta.DEFAULT_DRESSID = 55000001
meta.OPEN_LEVEL = 10
get_or_create('cfg.equip')['Dress'] = meta
function os:get_cfg_equip_Dress()
local o = {}
setmetatable(o, cfg.equip.Dress)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.displayorder = self:get_int()
o.modelname = self:get_map('int', 'string')
o.introduction = self:get_string()
o.gainfunction = self:get_int()
o.quickbuy = self:get_bool()
o.viplimit = self:get_cfg_cmd_condition_MinVipLevel()
o.price = self:get_cfg_cmd_condition_YuanBao()
o.sex = self:get_int()
o.gainpage = self:get_string()
o.quality = self:get_int()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.propertylist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.MultiSource'
get_or_create('cfg.item')['MultiSource'] = meta
function os:get_cfg_item_MultiSource()
local o = {}
setmetatable(o, cfg.item.MultiSource)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_item_SourceInfo')
o.sourcelist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Items'
get_or_create('cfg.cmd.condition')['Items'] = meta
function os:get_cfg_cmd_condition_Items()
local o = {}
setmetatable(o, cfg.cmd.condition.Items)
local _list = self:get_list('cfg_cmd_condition_Item')
o.items = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.monster.SkillModelBind'
get_or_create('cfg.monster')['SkillModelBind'] = meta
function os:get_cfg_monster_SkillModelBind()
local o = {}
setmetatable(o, cfg.monster.SkillModelBind)
o.modelname = self:get_string()
o.attackrange = self:get_float()
local _list = self:get_list('int')
o.skills = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITweenColor'
get_or_create('cfg.ui')['ContentUITweenColor'] = meta
function os:get_cfg_ui_ContentUITweenColor()
local o = {}
setmetatable(o, cfg.ui.ContentUITweenColor)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.style = self:get_string()
o.animationCurve = self:get_cfg_ui_AnimationCurve()
o.duration = self:get_float()
o.delay = self:get_float()
o.tweenGroup = self:get_int()
o.ignoreTimeScale = self:get_bool()
o.from = self:get_cfg_ui_Color()
o.to = self:get_cfg_ui_Color()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PlaySkill'
get_or_create('cfg.ectype')['PlaySkill'] = meta
function os:get_cfg_ectype_PlaySkill()
local o = {}
setmetatable(o, cfg.ectype.PlaySkill)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.characterid = self:get_int()
o.skillid = self:get_int()
o.position = self:get_cfg_map_Vector2()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemCurrency'
get_or_create('cfg.item')['ItemCurrency'] = meta
function os:get_cfg_item_ItemCurrency()
local o = {}
setmetatable(o, cfg.item.ItemCurrency)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.currencytype = self:get_int()
o.amount = self:get_int()
o.sprite = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddProperty'
get_or_create('cfg.buff')['AddProperty'] = meta
function os:get_cfg_buff_AddProperty()
local o = {}
setmetatable(o, cfg.buff.AddProperty)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.property = self:get_int()
o.value = self:get_float()
o.maxoverlay = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.LandscapeEntrance'
get_or_create('cfg.map')['LandscapeEntrance'] = meta
function os:get_cfg_map_LandscapeEntrance()
local o = {}
setmetatable(o, cfg.map.LandscapeEntrance)
o.id = self:get_int()
o.region = self:get_cfg_map_PolygonRegion()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.BlackMallOpen'
get_or_create('cfg.cmd.condition')['BlackMallOpen'] = meta
function os:get_cfg_cmd_condition_BlackMallOpen()
local o = {}
setmetatable(o, cfg.cmd.condition.BlackMallOpen)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.OpenEctype'
get_or_create('cfg.cmd.action')['OpenEctype'] = meta
function os:get_cfg_cmd_action_OpenEctype()
local o = {}
setmetatable(o, cfg.cmd.action.OpenEctype)
o.ectypeid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pay.NormalCharge'
get_or_create('cfg.pay')['NormalCharge'] = meta
function os:get_cfg_pay_NormalCharge()
local o = {}
setmetatable(o, cfg.pay.NormalCharge)
o.chargeid = self:get_int()
o.displayorder = self:get_int()
o.price = self:get_int()
o.platform = self:get_map('int', 'int')
o.getyuanbao = self:get_cfg_cmd_action_YuanBao()
o.firstgetbindyuanbao = self:get_cfg_cmd_action_BindYuanBao()
o.getbindyuanbao = self:get_cfg_cmd_action_BindYuanBao()
o.notetext = self:get_string()
o.backgourndimage = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.NullPatrol'
get_or_create('cfg.map')['NullPatrol'] = meta
function os:get_cfg_map_NullPatrol()
local o = {}
setmetatable(o, cfg.map.NullPatrol)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.SingleSource'
get_or_create('cfg.item')['SingleSource'] = meta
function os:get_cfg_item_SingleSource()
local o = {}
setmetatable(o, cfg.item.SingleSource)
o.id = self:get_int()
o.name = self:get_string()
o.source = self:get_cfg_item_SourceInfo()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EquipPropertyData'
get_or_create('cfg.equip')['EquipPropertyData'] = meta
function os:get_cfg_equip_EquipPropertyData()
local o = {}
setmetatable(o, cfg.equip.EquipPropertyData)
o.propertytype = self:get_int()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.WeightedPolygonRegion'
get_or_create('cfg.map')['WeightedPolygonRegion'] = meta
function os:get_cfg_map_WeightedPolygonRegion()
local o = {}
setmetatable(o, cfg.map.WeightedPolygonRegion)
local _list = self:get_list('cfg_map_Vector3')
o.vertices = _list
o.weight = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.ZaoHua'
get_or_create('cfg.cmd.action')['ZaoHua'] = meta
function os:get_cfg_cmd_action_ZaoHua()
local o = {}
setmetatable(o, cfg.cmd.action.ZaoHua)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Scene'
meta.HEIGHTMAP_MAX = 20000
meta.HEIGHTMAP_MIN = -20000
meta.DIFFWITHGROUNDANDSKY = 1.5
meta.LOADSCENEDELAYTIME = 1.0
meta.RELOADTIME = 5
meta.RELOADDELAY = 0.1
get_or_create('cfg.map')['Scene'] = meta
function os:get_cfg_map_Scene()
local o = {}
setmetatable(o, cfg.map.Scene)
o.name = self:get_string()
o.groundheightmap = self:get_string()
o.navmeshname = self:get_string()
o.skyheightmap = self:get_string()
o.skyregionset = self:get_int()
o.skyheightceil = self:get_float()
o.backgroundmusicid = self:get_int()
o.scenesize = self:get_float()
o.thunbnailsize = self:get_float()
o.pivot = self:get_int()
o.offsetX = self:get_float()
o.offsetZ = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.Revive'
meta.AUTOREVIVETIME = 30
get_or_create('cfg.role')['Revive'] = meta
function os:get_cfg_role_Revive()
local o = {}
setmetatable(o, cfg.role.Revive)
o.viprevivetimes = self:get_cfg_cmd_condition_VipLimitsLite()
o.reviveYuanBao = self:get_cfg_cmd_condition_YuanBao()
o.reviveitem = self:get_cfg_cmd_condition_OneItem()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ShiMen'
get_or_create('cfg.cmd.condition')['ShiMen'] = meta
function os:get_cfg_cmd_condition_ShiMen()
local o = {}
setmetatable(o, cfg.cmd.condition.ShiMen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.LingJing'
get_or_create('cfg.cmd.condition')['LingJing'] = meta
function os:get_cfg_cmd_condition_LingJing()
local o = {}
setmetatable(o, cfg.cmd.condition.LingJing)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Attack'
get_or_create('cfg.skill')['Attack'] = meta
function os:get_cfg_skill_Attack()
local o = {}
setmetatable(o, cfg.skill.Attack)
o.timeline = self:get_float()
o.id = self:get_int()
o.hitzoneid = self:get_int()
o.beattackeffectid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Parallel'
get_or_create('cfg.ectype')['Parallel'] = meta
function os:get_cfg_ectype_Parallel()
local o = {}
setmetatable(o, cfg.ectype.Parallel)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
local _list = self:get_list('cfg_ectype_Action')
o.actions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.Exchange'
get_or_create('cfg.lottery')['Exchange'] = meta
function os:get_cfg_lottery_Exchange()
local o = {}
setmetatable(o, cfg.lottery.Exchange)
o.id = self:get_int()
o.item = self:get_cfg_cmd_action_Item()
o.requirecurrency = self:get_cfg_cmd_condition_Currency()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.desc = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Rune'
get_or_create('cfg.ectype')['Rune'] = meta
function os:get_cfg_ectype_Rune()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotConfig'
get_or_create('cfg.plot')['PlotConfig'] = meta
function os:get_cfg_plot_PlotConfig()
local o = {}
setmetatable(o, cfg.plot.PlotConfig)
o.professtionconfig = self:get_map('int', 'cfg_plot_PlotProfessionConfig')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.BattlePower'
get_or_create('cfg.fight')['BattlePower'] = meta
function os:get_cfg_fight_BattlePower()
local o = {}
setmetatable(o, cfg.fight.BattlePower)
o.hp = self:get_float()
o.mp = self:get_float()
o.minatk = self:get_float()
o.maxatk = self:get_float()
o.defence = self:get_float()
o.hit = self:get_float()
o.hitresist = self:get_float()
o.crit = self:get_float()
o.excellent = self:get_float()
o.luck = self:get_float()
o.abnormal = self:get_float()
o.skilllevel = self:get_float()
o.addtionaldamage = self:get_float()
o.talismanawakebonus = self:get_float()
o.talismanrate = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.EctypeBasic'
meta.successaudioid = 224
meta.failedaudioid = 225
get_or_create('cfg.ectype')['EctypeBasic'] = meta
function os:get_cfg_ectype_EctypeBasic()
local o = {}
setmetatable(o, cfg.ectype.EctypeBasic)
o.id = self:get_int()
o.type = self:get_int()
o.scenename = self:get_string()
o.ectypename = self:get_string()
o.regionsetid = self:get_int()
o.reviveinfo = self:get_cfg_ectype_ReviveInfo()
o.totaltime = self:get_int()
o.startposition = self:get_cfg_map_Vector2()
o.startrotation = self:get_float()
o.endofftime = self:get_int()
o.audioid = self:get_int()
o.enterfight = self:get_bool()
o.rebornfight = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.AddExperience'
get_or_create('cfg.cmd.action')['AddExperience'] = meta
function os:get_cfg_cmd_action_AddExperience()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Limits'
get_or_create('cfg.cmd.condition')['Limits'] = meta
function os:get_cfg_cmd_condition_Limits()
local o = {}
setmetatable(o, cfg.cmd.condition.Limits)
local _list = self:get_list('cfg_cmd_condition_Limit')
o.limits = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.AttrIdType'
get_or_create('cfg.fight')['AttrIdType'] = meta
function os:get_cfg_fight_AttrIdType()
local o = {}
setmetatable(o, cfg.fight.AttrIdType)
o.type = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.FloorInfo'
get_or_create('cfg.ectype')['FloorInfo'] = meta
function os:get_cfg_ectype_FloorInfo()
local o = {}
setmetatable(o, cfg.ectype.FloorInfo)
o.id = self:get_int()
o.regionid = self:get_int()
o.score = self:get_int()
o.requirelevel = self:get_int()
o.firstbonus = self:get_cfg_cmd_action_MultiBonus()
o.normalbonus = self:get_cfg_cmd_action_MultiBonus()
o.monsters = self:get_map('int', 'int')
o.battlevalue = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.MonthBonus'
meta.RETROACTIVE_CURRENCY = 10200002
get_or_create('cfg.bonus')['MonthBonus'] = meta
function os:get_cfg_bonus_MonthBonus()
local o = {}
setmetatable(o, cfg.bonus.MonthBonus)
o.dayscount = self:get_int()
o.requireviplevel = self:get_cfg_cmd_condition_MinVipLevel()
o.bonuslist = self:get_cfg_cmd_action_Item()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.KillWorldBoss'
get_or_create('cfg.operationalactivity')['KillWorldBoss'] = meta
function os:get_cfg_operationalactivity_KillWorldBoss()
local o = {}
setmetatable(o, cfg.operationalactivity.KillWorldBoss)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.controller.MonthlyController'
get_or_create('cfg.controller')['MonthlyController'] = meta
function os:get_cfg_controller_MonthlyController()
local o = {}
setmetatable(o, cfg.controller.MonthlyController)
o.id = self:get_int()
o.name = self:get_string()
o.introcuction = self:get_string()
o.owner = self:get_string()
o.duration = self:get_float()
o.waitbeforeopen = self:get_int()
o.waitbeforeclose = self:get_int()
o.activity = self:get_cfg_controller_Activity()
o.day = self:get_int()
o.hour = self:get_int()
o.minute = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AmuletSkillLvl'
get_or_create('cfg.equip')['AmuletSkillLvl'] = meta
function os:get_cfg_equip_AmuletSkillLvl()
local o = {}
setmetatable(o, cfg.equip.AmuletSkillLvl)
o.skillid = self:get_int()
o.skillid2 = self:get_int()
o.carrer = self:get_int()
local _list = self:get_list('int')
o.weightlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.ClimbTower'
get_or_create('cfg.operationalactivity')['ClimbTower'] = meta
function os:get_cfg_operationalactivity_ClimbTower()
local o = {}
setmetatable(o, cfg.operationalactivity.ClimbTower)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetBasicStatus'
meta.TURN_PET_INTO_FRAGMENT_RATE = 0.7
get_or_create('cfg.pet')['PetBasicStatus'] = meta
function os:get_cfg_pet_PetBasicStatus()
local o = {}
setmetatable(o, cfg.pet.PetBasicStatus)
o.id = self:get_int()
o.name = self:get_string()
o.introduction = self:get_string()
o.displayorder = self:get_int()
o.icon = self:get_string()
o.ai = self:get_int()
o.modelname = self:get_string()
local _list = self:get_list('int')
o.fashionpath = _list
o.bindtype = self:get_int()
o.basiccolor = self:get_int()
o.fragmentid = self:get_int()
local _list = self:get_list('string')
o.battletalk = _list
o.pettype = self:get_int()
o.feature = self:get_string()
local _list = self:get_list('int')
o.featurelist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.AttackAction'
get_or_create('cfg.skill')['AttackAction'] = meta
function os:get_cfg_skill_AttackAction()
local o = {}
setmetatable(o, cfg.skill.AttackAction)
o.prevbuffid = self:get_int()
o.succbuffid = self:get_int()
local _list = self:get_list('float')
o.rate = _list
local _list = self:get_list('float')
o.damage = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Camera'
get_or_create('cfg.map')['Camera'] = meta
function os:get_cfg_map_Camera()
local o = {}
setmetatable(o, cfg.map.Camera)
o.id = self:get_int()
o.CameraModeAnglex = self:get_float()
o.CameraModeAngley = self:get_float()
o.CameraModeHeight = self:get_float()
o.CameraModeDistance = self:get_float()
o.CameraModeDistanceMin = self:get_float()
o.CameraModeDistanceMax = self:get_float()
o.CameraAngleX = self:get_float()
o.CameraAngleMinX = self:get_float()
o.CameraAngleMaxX = self:get_float()
o.CameraModeBackground = self:get_string()
o.CameraModeFov = self:get_float()
o.CameraModeFar = self:get_float()
o.Offset = self:get_float()
o.MoveSpeedRateX = self:get_float()
o.MoveSpeedRateY = self:get_float()
o.MoveDistanceSpeed = self:get_float()
o.CameraAngleDampSpeed = self:get_float()
o.CameraDistanceDampSpeed = self:get_float()
o.Orhtographic = self:get_float()
o.IsOrthoGraphic = self:get_int()
o.AngleXMin = self:get_float()
o.AngleXMax = self:get_float()
o.defaultdistance = self:get_float()
o.lockeddistancemin = self:get_float()
o.lockeddistancemax = self:get_float()
o.lockedlowangle = self:get_float()
o.lockedhightangle = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CollectMineral'
get_or_create('cfg.ectype')['CollectMineral'] = meta
function os:get_cfg_ectype_CollectMineral()
local o = {}
setmetatable(o, cfg.ectype.CollectMineral)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
local _list = self:get_list('cfg_ectype_MissionCollectMineral')
o.missions = _list
o.missions_mineralid = {}
for _, _V in ipairs(_list) do
o.missions_mineralid[_V.mineralid] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.MonsterController'
get_or_create('cfg.task')['MonsterController'] = meta
function os:get_cfg_task_MonsterController()
local o = {}
setmetatable(o, cfg.task.MonsterController)
o.controllerid = self:get_int()
o.trigger = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetExp'
get_or_create('cfg.pet')['PetExp'] = meta
function os:get_cfg_pet_PetExp()
local o = {}
setmetatable(o, cfg.pet.PetExp)
o.level = self:get_int()
o.exp = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.CutsceneGroup'
get_or_create('cfg.plot')['CutsceneGroup'] = meta
function os:get_cfg_plot_CutsceneGroup()
local o = {}
setmetatable(o, cfg.plot.CutsceneGroup)
o.mode = self:get_int()
local _list = self:get_list('cfg_plot_Cutscene')
o.cutscenes = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.CareerSkillList'
get_or_create('cfg.skill')['CareerSkillList'] = meta
function os:get_cfg_skill_CareerSkillList()
local o = {}
setmetatable(o, cfg.skill.CareerSkillList)
o.career = self:get_int()
o.normalskillid = self:get_int()
local _list = self:get_list('int')
o.skilllist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.AwakeBackgroundTexture'
get_or_create('cfg.pet')['AwakeBackgroundTexture'] = meta
function os:get_cfg_pet_AwakeBackgroundTexture()
local o = {}
setmetatable(o, cfg.pet.AwakeBackgroundTexture)
o.awakelevel = self:get_int()
o.backgroundtexture = self:get_cfg_pet_QualityTexture()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.OneItem'
get_or_create('cfg.cmd.condition')['OneItem'] = meta
function os:get_cfg_cmd_condition_OneItem()
local o = {}
setmetatable(o, cfg.cmd.condition.OneItem)
o.itemid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.huiwu.rankAward'
get_or_create('cfg.huiwu')['rankAward'] = meta
function os:get_cfg_huiwu_rankAward()
local o = {}
setmetatable(o, cfg.huiwu.rankAward)
o.rank = self:get_int()
o.award = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PlayerEffect'
get_or_create('cfg.ectype')['PlayerEffect'] = meta
function os:get_cfg_ectype_PlayerEffect()
local o = {}
setmetatable(o, cfg.ectype.PlayerEffect)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.id = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanAwake'
meta.AWAKE_COST = 0
get_or_create('cfg.talisman')['TalismanAwake'] = meta
function os:get_cfg_talisman_TalismanAwake()
local o = {}
setmetatable(o, cfg.talisman.TalismanAwake)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_talisman_AwakeInfo')
o.awakeinfo = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ProfessionCG'
get_or_create('cfg.ectype')['ProfessionCG'] = meta
function os:get_cfg_ectype_ProfessionCG()
local o = {}
setmetatable(o, cfg.ectype.ProfessionCG)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.professioncgs = self:get_map('int', 'string')
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.TaskBase'
get_or_create('cfg.task')['TaskBase'] = meta
function os:get_cfg_task_TaskBase()
local o = {}
setmetatable(o, cfg.task.TaskBase)
o.name = self:get_string()
o.tasktype = self:get_int()
o.contenttype = self:get_int()
o.canbecancle = self:get_bool()
o.canbesearch = self:get_bool()
o.hidetask = self:get_bool()
o.autobetrigger = self:get_bool()
o.close = self:get_bool()
o.autopathfind = self:get_bool()
o.finishedtaskcount = self:get_bool()
o.autofinish = self:get_bool()
o.counttype = self:get_int()
o.countlimit = self:get_int()
o.resetcounttimerid = self:get_int()
o.navmode = self:get_int()
local _list = self:get_list('cfg_task_ItemInfo')
o.givenitem = _list
o.callnpc = self:get_cfg_task_CallNpcInfo()
local _list = self:get_list('cfg_task_MonsterController')
o.monstercontroller = _list
o.npcshowhide = self:get_cfg_task_ShowHideData()
o.mineshowhide = self:get_cfg_task_ShowHideData()
o.gfxshowhide = self:get_cfg_task_ShowHideData()
local _list = self:get_list('cfg_task_DialogueInfo')
o.npcdialogue = _list
local _list = self:get_list('cfg_task_DialogueInfo')
o.undone = _list
local _list = self:get_list('cfg_task_DialogueInfo')
o.success = _list
o.hints = self:get_cfg_task_HintInfo()
local _list = self:get_list('cfg_task_DialogueInfo')
o.shoutaccepted = _list
local _list = self:get_list('cfg_task_DialogueInfo')
o.shoutuncommitted = _list
local _list = self:get_list('cfg_task_DialogueInfo')
o.shoutcompleted = _list
o.ringtasktalk = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIWidgetBase'
get_or_create('cfg.ui')['ContentUIWidgetBase'] = meta
function os:get_cfg_ui_ContentUIWidgetBase()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.Property'
get_or_create('cfg.pet')['Property'] = meta
function os:get_cfg_pet_Property()
local o = {}
setmetatable(o, cfg.pet.Property)
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.gainability = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EnhanceConfig'
get_or_create('cfg.equip')['EnhanceConfig'] = meta
function os:get_cfg_equip_EnhanceConfig()
local o = {}
setmetatable(o, cfg.equip.EnhanceConfig)
o.level = self:get_int()
o.anneallimit = self:get_int()
o.appendlimit = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.FaBaoJiFen'
get_or_create('cfg.cmd.action')['FaBaoJiFen'] = meta
function os:get_cfg_cmd_action_FaBaoJiFen()
local o = {}
setmetatable(o, cfg.cmd.action.FaBaoJiFen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.Transport'
meta.COLDTIME = 3
get_or_create('cfg.map')['Transport'] = meta
function os:get_cfg_map_Transport()
local o = {}
setmetatable(o, cfg.map.Transport)
o.minlvl = self:get_cfg_cmd_condition_MinLevel()
o.requireitemid = self:get_cfg_cmd_condition_OneItem()
o.daylimit = self:get_cfg_cmd_condition_VipLimitsLite()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetAwake'
get_or_create('cfg.pet')['PetAwake'] = meta
function os:get_cfg_pet_PetAwake()
local o = {}
setmetatable(o, cfg.pet.PetAwake)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_pet_AwakeInfo')
o.awakelvlup = _list
o.awakelvlup_awakeid = {}
for _, _V in ipairs(_list) do
o.awakelvlup_awakeid[_V.awakeid] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.LayoutFinished'
get_or_create('cfg.ectype')['LayoutFinished'] = meta
function os:get_cfg_ectype_LayoutFinished()
local o = {}
setmetatable(o, cfg.ectype.LayoutFinished)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ShapeShift'
get_or_create('cfg.ectype')['ShapeShift'] = meta
function os:get_cfg_ectype_ShapeShift()
local o = {}
setmetatable(o, cfg.ectype.ShapeShift)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.characterid = self:get_int()
o.shapeid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.PlayCGOver'
get_or_create('cfg.cmd.condition')['PlayCGOver'] = meta
function os:get_cfg_cmd_condition_PlayCGOver()
local o = {}
setmetatable(o, cfg.cmd.condition.PlayCGOver)
local _list = self:get_list('int')
o.cgids = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TeamFight'
get_or_create('cfg.ectype')['TeamFight'] = meta
function os:get_cfg_ectype_TeamFight()
local o = {}
setmetatable(o, cfg.ectype.TeamFight)
o.id = self:get_int()
o.mainregionid = self:get_int()
o.airwallregionid = self:get_int()
o.team1bornregionid = self:get_int()
o.team2bornregionid = self:get_int()
o.levellimit = self:get_int()
o.matchnum = self:get_int()
o.quitmatchforbidtime = self:get_int()
local _list = self:get_list('cfg_common_DayTimeRange')
o.opentimes = _list
o.extrabonustimes = self:get_int()
o.dailywintimes = self:get_int()
o.dailywinbonus = self:get_cfg_cmd_action_MultiBonus()
o.winscore = self:get_int()
o.losescore = self:get_int()
o.drawscore = self:get_int()
local _list = self:get_list('cfg_ectype_GradeBonus')
o.weekscorebonus = _list
o.roundtime = self:get_int()
o.timeouttime = self:get_int()
o.matchsucccountdown = self:get_int()
local _list = self:get_list('int')
o.roundvipdeltagroup = _list
local _list = self:get_list('cfg_ectype_TeamFightLevelGroup')
o.levelgroups = _list
o.preparetime = self:get_int()
o.completeneedkillnum = self:get_int()
local _list = self:get_list('cfg_map_Vector2')
o.bornpositions = _list
local _list = self:get_list('cfg_map_Vector2')
o.revivepositions = _list
o.runefirstrefreshtime = self:get_int()
o.runeinfo = self:get_cfg_ectype_Spell()
local _list = self:get_list('cfg_ectype_Evaluate')
o.evaluate = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.LotteryTextureDetail'
get_or_create('cfg.lottery')['LotteryTextureDetail'] = meta
function os:get_cfg_lottery_LotteryTextureDetail()
local o = {}
setmetatable(o, cfg.lottery.LotteryTextureDetail)
o.showdesc = self:get_bool()
o.desc = self:get_string()
o.freetype = self:get_int()
o.type = self:get_int()
o.iscooldown = self:get_bool()
o.isdaylimit = self:get_bool()
o.canuseitem = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.setting.LinearFogSetting'
get_or_create('cfg.setting')['LinearFogSetting'] = meta
function os:get_cfg_setting_LinearFogSetting()
local o = {}
setmetatable(o, cfg.setting.LinearFogSetting)
o.startdistance = self:get_int()
o.enddistance = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetTotalAwake'
get_or_create('cfg.operationalactivity')['PetTotalAwake'] = meta
function os:get_cfg_operationalactivity_PetTotalAwake()
local o = {}
setmetatable(o, cfg.operationalactivity.PetTotalAwake)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.LotteryItemOffset'
get_or_create('cfg.lottery')['LotteryItemOffset'] = meta
function os:get_cfg_lottery_LotteryItemOffset()
local o = {}
setmetatable(o, cfg.lottery.LotteryItemOffset)
o.itemid = self:get_int()
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
o.scale = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Weapon'
get_or_create('cfg.equip')['Weapon'] = meta
function os:get_cfg_equip_Weapon()
local o = {}
setmetatable(o, cfg.equip.Weapon)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.nextid = self:get_int()
o.extraequipid = self:get_int()
o.upgradecurrencycost = self:get_cfg_cmd_condition_XuNiBi()
o.carryingitemnum = self:get_int()
o.recommendrate = self:get_float()
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.property = _list
o.left = self:get_string()
o.right = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUILabel'
get_or_create('cfg.ui')['ContentUILabel'] = meta
function os:get_cfg_ui_ContentUILabel()
local o = {}
setmetatable(o, cfg.ui.ContentUILabel)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.depth = self:get_int()
o.width = self:get_int()
o.height = self:get_int()
o.pivot = self:get_string()
o.color = self:get_cfg_ui_Color()
o.autoResizeBoxCollider = self:get_bool()
o.fontType = self:get_string()
o.font = self:get_string()
o.fontSize = self:get_int()
o.fontStyle = self:get_string()
o.material = self:get_string()
o.text = self:get_string()
o.overflowMethod = self:get_string()
o.mMaxWidth = self:get_int()
o.alignment = self:get_string()
o.keepCrispWhenShrunk = self:get_string()
o.applyGradient = self:get_bool()
o.gradientTop = self:get_cfg_ui_Color()
o.gradientBottom = self:get_cfg_ui_Color()
o.effectStyle = self:get_string()
o.effectColor = self:get_cfg_ui_Color()
o.effectDistance = self:get_cfg_ui_Vector2()
o.useFloatSpacing = self:get_bool()
o.spacingX = self:get_int()
o.spacingY = self:get_int()
o.floatSpacingX = self:get_float()
o.floatSpacingY = self:get_float()
o.maxLineCount = self:get_int()
o.supportEncoding = self:get_bool()
o.symbolStyle = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.PassEctype'
get_or_create('cfg.cmd.condition')['PassEctype'] = meta
function os:get_cfg_cmd_condition_PassEctype()
local o = {}
setmetatable(o, cfg.cmd.condition.PassEctype)
o.ectypeid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.BeizerVertex'
get_or_create('cfg.map')['BeizerVertex'] = meta
function os:get_cfg_map_BeizerVertex()
local o = {}
setmetatable(o, cfg.map.BeizerVertex)
o.position = self:get_cfg_map_Vector3()
o.leftctrl = self:get_cfg_map_Vector3()
o.rightctrl = self:get_cfg_map_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MapLoading'
get_or_create('cfg.map')['MapLoading'] = meta
function os:get_cfg_map_MapLoading()
local o = {}
setmetatable(o, cfg.map.MapLoading)
o.texturename = self:get_string()
local _list = self:get_list('string')
o.texts = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.TimerType'
get_or_create('cfg.common')['TimerType'] = meta
function os:get_cfg_common_TimerType()
local o = {}
setmetatable(o, cfg.common.TimerType)
o.id = self:get_int()
o.name = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.GrowPlan'
meta.FIRST_GROW_PLAN_END_INDEX = 3
meta.SECOND_GROW_PLAN_START_INDEX = 4
meta.SECOND_GROW_PLAN_END_INDEX = 8
meta.THIRD_GROW_PLAN_START_INDEX = 9
meta.THIRD_GROW_PLAN_END_INDEX = 14
get_or_create('cfg.bonus')['GrowPlan'] = meta
function os:get_cfg_bonus_GrowPlan()
local o = {}
setmetatable(o, cfg.bonus.GrowPlan)
o.daycount = self:get_int()
o.bonuslist = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.MainEquip'
get_or_create('cfg.equip')['MainEquip'] = meta
function os:get_cfg_equip_MainEquip()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.TalkInfo'
get_or_create('cfg.plot')['TalkInfo'] = meta
function os:get_cfg_plot_TalkInfo()
local o = {}
setmetatable(o, cfg.plot.TalkInfo)
o.talkperson = self:get_string()
o.talkcontent = self:get_string()
o.picture = self:get_string()
o.sound = self:get_string()
o.start = self:get_float()
o.duration = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.TiLi'
get_or_create('cfg.cmd.condition')['TiLi'] = meta
function os:get_cfg_cmd_condition_TiLi()
local o = {}
setmetatable(o, cfg.cmd.condition.TiLi)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.IndexedPolygonRegion'
get_or_create('cfg.map')['IndexedPolygonRegion'] = meta
function os:get_cfg_map_IndexedPolygonRegion()
local o = {}
setmetatable(o, cfg.map.IndexedPolygonRegion)
o.id = self:get_int()
o.allowpk = self:get_bool()
o.allowride = self:get_bool()
o.allowtrade = self:get_bool()
o.polygon = self:get_cfg_map_PolygonRegion()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.SkillLvlupData'
get_or_create('cfg.skill')['SkillLvlupData'] = meta
function os:get_cfg_skill_SkillLvlupData()
local o = {}
setmetatable(o, cfg.skill.SkillLvlupData)
o.requirelvl = self:get_int()
o.requirecurrency1 = self:get_cfg_cmd_condition_XuNiBi()
o.requirecurrency2 = self:get_cfg_cmd_condition_ZaoHua()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.ArenaMall'
get_or_create('cfg.mall')['ArenaMall'] = meta
function os:get_cfg_mall_ArenaMall()
local o = {}
setmetatable(o, cfg.mall.ArenaMall)
o.id = self:get_int()
o.itemid = self:get_cfg_cmd_action_OneItem()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.cost = self:get_cfg_cmd_condition_Currency()
o.introduce = self:get_string()
o.limitlist = self:get_cfg_cmd_condition_Limits()
o.page = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.JadeEnhance'
get_or_create('cfg.equip')['JadeEnhance'] = meta
function os:get_cfg_equip_JadeEnhance()
local o = {}
setmetatable(o, cfg.equip.JadeEnhance)
local _list = self:get_list('cfg_equip_JadeEnhanceData')
o.enhancedata = _list
o.enhancedata_enhancetypeid = {}
for _, _V in ipairs(_list) do
o.enhancedata_enhancetypeid[_V.enhancetypeid] = _V
end
o.searchcost = self:get_cfg_cmd_condition_XuNiBi()
local _list = self:get_list('int')
o.holeopenlevel = _list
o.enhanceitemid = self:get_cfg_cmd_condition_OneItem()
o.level4cost = self:get_cfg_cmd_condition_YuanBao()
o.packlarge = self:get_int()
o.openlevel = self:get_int()
local _list = self:get_list('string')
o.gettertexture = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetSkill'
get_or_create('cfg.pet')['PetSkill'] = meta
function os:get_cfg_pet_PetSkill()
local o = {}
setmetatable(o, cfg.pet.PetSkill)
o.petid = self:get_int()
o.name = self:get_string()
local _list = self:get_list('int')
o.skilllist = _list
local _list = self:get_list('int')
o.awakeskill = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.GetBuff'
get_or_create('cfg.ectype')['GetBuff'] = meta
function os:get_cfg_ectype_GetBuff()
local o = {}
setmetatable(o, cfg.ectype.GetBuff)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.arena.ArenaAward'
get_or_create('cfg.arena')['ArenaAward'] = meta
function os:get_cfg_arena_ArenaAward()
local o = {}
setmetatable(o, cfg.arena.ArenaAward)
o.maxlvl = self:get_int()
o.winaward = self:get_cfg_cmd_action_Bonus()
o.loseaward = self:get_cfg_cmd_action_Bonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.CallNpcInfo'
get_or_create('cfg.task')['CallNpcInfo'] = meta
function os:get_cfg_task_CallNpcInfo()
local o = {}
setmetatable(o, cfg.task.CallNpcInfo)
o.npcid = self:get_int()
o.callnpclocation = self:get_cfg_task_LocationPointData()
o.callnpclifetime = self:get_int()
o.lifetime = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.RoleLevel'
get_or_create('cfg.cmd.action')['RoleLevel'] = meta
function os:get_cfg_cmd_action_RoleLevel()
local o = {}
setmetatable(o, cfg.cmd.action.RoleLevel)
o.amount = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Case'
get_or_create('cfg.ectype')['Case'] = meta
function os:get_cfg_ectype_Case()
local o = {}
setmetatable(o, cfg.ectype.Case)
local _list = self:get_list('cfg_ectype_ExeCondition')
o.conditions = _list
o.action = self:get_cfg_ectype_Action()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.DropModel'
get_or_create('cfg.bonus')['DropModel'] = meta
function os:get_cfg_bonus_DropModel()
local o = {}
setmetatable(o, cfg.bonus.DropModel)
o.itemid = self:get_int()
o.name = self:get_string()
o.namecolor = self:get_int()
o.modelpath = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Evaluate'
get_or_create('cfg.ectype')['Evaluate'] = meta
function os:get_cfg_ectype_Evaluate()
local o = {}
setmetatable(o, cfg.ectype.Evaluate)
o.evaluateid = self:get_int()
o.evaluatename = self:get_string()
o.conditionid = self:get_int()
o.effectid = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ItemRune'
get_or_create('cfg.ectype')['ItemRune'] = meta
function os:get_cfg_ectype_ItemRune()
local o = {}
setmetatable(o, cfg.ectype.ItemRune)
o.id = self:get_int()
o.model = self:get_string()
o.icon = self:get_string()
o.itemid = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.MallNPC'
meta.DISPLAYTIME = 5
get_or_create('cfg.mall')['MallNPC'] = meta
function os:get_cfg_mall_MallNPC()
local o = {}
setmetatable(o, cfg.mall.MallNPC)
o.malltype = self:get_int()
o.cornucopianpc = self:get_int()
o.offset = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.WorldMap'
meta.MAX_PLAYER_NUM = 10
meta.WORLDMAP_PATH = "BG_WorldMap"
meta.DEFAULT_MAPID = 100
meta.FIGHT_STATE_DURATION = 10
get_or_create('cfg.map')['WorldMap'] = meta
function os:get_cfg_map_WorldMap()
local o = {}
setmetatable(o, cfg.map.WorldMap)
o.id = self:get_int()
o.scenename = self:get_string()
o.mapname = self:get_string()
o.landscapeid = self:get_int()
o.collisiondetect = self:get_bool()
o.isdungeon = self:get_bool()
o.suggestplayernum = self:get_int()
o.maxlineplayernum = self:get_int()
o.initlinenum = self:get_int()
o.maxlinenum = self:get_int()
o.SurfaceHeightList = self:get_float()
o.MapBgAudioEffect = self:get_int()
o.CameraId = self:get_int()
o.WorldFlyInX = self:get_float()
o.WorldFlyInY = self:get_float()
o.RangeMinX = self:get_float()
o.RangeMaxX = self:get_float()
o.iscity = self:get_bool()
o.footprint = self:get_string()
o.MaterialType = self:get_int()
o.AmbientLightR = self:get_int()
o.AmbientLightG = self:get_int()
o.AmbientLightB = self:get_int()
o.AmbientLightA = self:get_int()
o.RotateX = self:get_float()
o.allowpk = self:get_bool()
o.allowride = self:get_bool()
o.allowtrade = self:get_bool()
o.openlevel = self:get_int()
o.mappath = self:get_string()
o.regionsetid = self:get_int()
local _list = self:get_list('cfg_map_Portal')
o.portals = _list
o.portals_srcregionid = {}
for _, _V in ipairs(_list) do
o.portals_srcregionid[_V.srcregionid] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.Single'
get_or_create('cfg.plot')['Single'] = meta
function os:get_cfg_plot_Single()
local o = {}
setmetatable(o, cfg.plot.Single)
o.id = self:get_int()
o.talktype = self:get_int()
o.namefontsize = self:get_int()
o.contentfontsize = self:get_int()
o.branch = self:get_bool()
o.talk = self:get_cfg_plot_TalkInfo()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.JadeEnhanceData'
get_or_create('cfg.equip')['JadeEnhanceData'] = meta
function os:get_cfg_equip_JadeEnhanceData()
local o = {}
setmetatable(o, cfg.equip.JadeEnhanceData)
o.enhancetypeid = self:get_int()
o.enhancetypename = self:get_string()
o.currency = self:get_cfg_cmd_condition_Currency()
o.minbonus = self:get_int()
o.maxbonus = self:get_int()
o.criticalrate = self:get_float()
o.criticalvalue = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.BangGong'
get_or_create('cfg.cmd.condition')['BangGong'] = meta
function os:get_cfg_cmd_condition_BangGong()
local o = {}
setmetatable(o, cfg.cmd.condition.BangGong)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.EquipAnnealTotal'
get_or_create('cfg.operationalactivity')['EquipAnnealTotal'] = meta
function os:get_cfg_operationalactivity_EquipAnnealTotal()
local o = {}
setmetatable(o, cfg.operationalactivity.EquipAnnealTotal)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.SkillLvlupCost'
get_or_create('cfg.skill')['SkillLvlupCost'] = meta
function os:get_cfg_skill_SkillLvlupCost()
local o = {}
setmetatable(o, cfg.skill.SkillLvlupCost)
o.skillid = self:get_int()
o.owner = self:get_string()
o.nextskillid = self:get_int()
o.requireawakelvl = self:get_int()
local _list = self:get_list('cfg_skill_SkillLvlupData')
o.skilllvlupdata = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.AddBuff'
get_or_create('cfg.cmd.action')['AddBuff'] = meta
function os:get_cfg_cmd_action_AddBuff()
local o = {}
setmetatable(o, cfg.cmd.action.AddBuff)
o.buffid = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.IndexedBezierCurve'
get_or_create('cfg.map')['IndexedBezierCurve'] = meta
function os:get_cfg_map_IndexedBezierCurve()
local o = {}
setmetatable(o, cfg.map.IndexedBezierCurve)
o.id = self:get_int()
o.curve = self:get_cfg_map_BezierCurve()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITexture'
get_or_create('cfg.ui')['ContentUITexture'] = meta
function os:get_cfg_ui_ContentUITexture()
local o = {}
setmetatable(o, cfg.ui.ContentUITexture)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.depth = self:get_int()
o.width = self:get_int()
o.height = self:get_int()
o.pivot = self:get_string()
o.color = self:get_cfg_ui_Color()
o.autoResizeBoxCollider = self:get_bool()
o.type = self:get_string()
o.flip = self:get_string()
o.centerType = self:get_string()
o.leftType = self:get_string()
o.rightType = self:get_string()
o.bottomType = self:get_string()
o.topType = self:get_string()
o.fillDirection = self:get_string()
o.fillAmount = self:get_float()
o.invert = self:get_bool()
o.mainTexture = self:get_string()
o.material = self:get_string()
o.shader = self:get_string()
o.uvRect = self:get_cfg_ui_Rect()
o.fixedAspect = self:get_bool()
o.border = self:get_cfg_ui_Vector4()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Fragment'
get_or_create('cfg.equip')['Fragment'] = meta
function os:get_cfg_equip_Fragment()
local o = {}
setmetatable(o, cfg.equip.Fragment)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.maxpile = self:get_int()
o.cansell = self:get_bool()
o.equipID = self:get_int()
o.number = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.BangGong'
get_or_create('cfg.cmd.action')['BangGong'] = meta
function os:get_cfg_cmd_action_BangGong()
local o = {}
setmetatable(o, cfg.cmd.action.BangGong)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.TraceCurve'
get_or_create('cfg.skill')['TraceCurve'] = meta
function os:get_cfg_skill_TraceCurve()
local o = {}
setmetatable(o, cfg.skill.TraceCurve)
o.id = self:get_int()
o.velocity = self:get_float()
o.angle = self:get_float()
o.hacc = self:get_float()
o.vacc = self:get_float()
o.hbounceacc = self:get_float()
o.vbounceacc = self:get_float()
o.bouncecoe = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUITable'
get_or_create('cfg.ui')['ContentUITable'] = meta
function os:get_cfg_ui_ContentUITable()
local o = {}
setmetatable(o, cfg.ui.ContentUITable)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.columns = self:get_int()
o.direction = self:get_string()
o.sorting = self:get_string()
o.pivot = self:get_string()
o.cellAlignment = self:get_string()
o.hideInactive = self:get_bool()
o.keepWithinPanel = self:get_bool()
o.padding = self:get_cfg_ui_Vector2()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.FamilyParty'
get_or_create('cfg.family')['FamilyParty'] = meta
function os:get_cfg_family_FamilyParty()
local o = {}
setmetatable(o, cfg.family.FamilyParty)
local _list = self:get_list('int')
o.starttime = _list
local _list = self:get_list('int')
o.endtime = _list
local _list = self:get_list('int')
o.starttime2 = _list
local _list = self:get_list('int')
o.endtime2 = _list
o.duration = self:get_int()
o.familyectypeid = self:get_int()
o.upperbound = self:get_int()
o.showitem = self:get_cfg_cmd_action_MultiBonus()
o.effectposition = self:get_cfg_map_Vector3()
o.effectzoomin = self:get_cfg_map_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.WuXing'
get_or_create('cfg.cmd.condition')['WuXing'] = meta
function os:get_cfg_cmd_condition_WuXing()
local o = {}
setmetatable(o, cfg.cmd.condition.WuXing)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.BlackMarket'
get_or_create('cfg.family')['BlackMarket'] = meta
function os:get_cfg_family_BlackMarket()
local o = {}
setmetatable(o, cfg.family.BlackMarket)
local _list = self:get_list('cfg_common_DayTimeRange')
o.opentime = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.While'
get_or_create('cfg.ai')['While'] = meta
function os:get_cfg_ai_While()
local o = {}
setmetatable(o, cfg.ai.While)
o.condition = self:get_cfg_ai_BoolExpr()
local _list = self:get_list('cfg_ai_Expression')
o.exprs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.GradeExchange'
get_or_create('cfg.lottery')['GradeExchange'] = meta
function os:get_cfg_lottery_GradeExchange()
local o = {}
setmetatable(o, cfg.lottery.GradeExchange)
o.cost = self:get_int()
o.currencytype = self:get_int()
local _list = self:get_list('cfg_lottery_Exchange')
o.exchangelist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Transmit'
get_or_create('cfg.ectype')['Transmit'] = meta
function os:get_cfg_ectype_Transmit()
local o = {}
setmetatable(o, cfg.ectype.Transmit)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Bangle'
get_or_create('cfg.equip')['Bangle'] = meta
function os:get_cfg_equip_Bangle()
local o = {}
setmetatable(o, cfg.equip.Bangle)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.type = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.break2lingjing = self:get_cfg_cmd_action_LingJing()
local _list = self:get_list('int')
o.mainproperty = _list
local _list = self:get_list('int')
o.mainproperty2 = _list
local _list = self:get_list('int')
o.viceproperty = _list
local _list = self:get_list('int')
o.rankweight = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.MissionCollectMineral'
get_or_create('cfg.ectype')['MissionCollectMineral'] = meta
function os:get_cfg_ectype_MissionCollectMineral()
local o = {}
setmetatable(o, cfg.ectype.MissionCollectMineral)
o.mineralid = self:get_int()
o.enviroment = self:get_int()
o.count = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Chapter'
get_or_create('cfg.ectype')['Chapter'] = meta
function os:get_cfg_ectype_Chapter()
local o = {}
setmetatable(o, cfg.ectype.Chapter)
o.chapterid = self:get_int()
o.chaptername = self:get_string()
o.introduction = self:get_string()
local _list = self:get_list('cfg_ectype_ChapterAward')
o.bonus = _list
o.bonus_awardid = {}
for _, _V in ipairs(_list) do
o.bonus_awardid[_V.awardid] = _V
end
o.chapterbgmpic = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.JewelryGet'
get_or_create('cfg.equip')['JewelryGet'] = meta
function os:get_cfg_equip_JewelryGet()
local o = {}
setmetatable(o, cfg.equip.JewelryGet)
o.getlevel = self:get_int()
o.uprate = self:get_float()
o.downrate = self:get_float()
local _list = self:get_list('float')
o.ratelist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.MultiBonus'
get_or_create('cfg.cmd.action')['MultiBonus'] = meta
function os:get_cfg_cmd_action_MultiBonus()
local o = {}
setmetatable(o, cfg.cmd.action.MultiBonus)
local _list = self:get_list('cfg_cmd_action_Bonus')
o.bonuss = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.HeroEctypeBonus'
get_or_create('cfg.ectype')['HeroEctypeBonus'] = meta
function os:get_cfg_ectype_HeroEctypeBonus()
local o = {}
setmetatable(o, cfg.ectype.HeroEctypeBonus)
o.cost = self:get_cfg_cmd_condition_Currency()
o.bonus = self:get_cfg_cmd_action_Bonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIBase'
get_or_create('cfg.ui')['ContentUIBase'] = meta
function os:get_cfg_ui_ContentUIBase()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.BossInfo'
get_or_create('cfg.family')['BossInfo'] = meta
function os:get_cfg_family_BossInfo()
local o = {}
setmetatable(o, cfg.family.BossInfo)
o.requireexp = self:get_int()
o.requipreplayerlvl = self:get_cfg_cmd_condition_MinLevel()
o.requirefamilylvl = self:get_cfg_cmd_condition_MinFamilyLevel()
o.monsterid = self:get_int()
o.requirefamilycapital = self:get_cfg_cmd_condition_FamilyMoney()
o.dropitem = self:get_cfg_cmd_action_OneItem()
local _list = self:get_list('int')
o.dropamount = _list
o.luckybonus = self:get_cfg_cmd_action_OneItem()
o.lasthitbonus = self:get_cfg_cmd_action_OneItem()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.WeekTimeRange'
get_or_create('cfg.common')['WeekTimeRange'] = meta
function os:get_cfg_common_WeekTimeRange()
local o = {}
setmetatable(o, cfg.common.WeekTimeRange)
o.begintime = self:get_cfg_common_WeekTime()
o.endtime = self:get_cfg_common_WeekTime()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemGiftPack'
get_or_create('cfg.item')['ItemGiftPack'] = meta
function os:get_cfg_item_ItemGiftPack()
local o = {}
setmetatable(o, cfg.item.ItemGiftPack)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.itempacklist = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.plot.PlotCall'
get_or_create('cfg.plot')['PlotCall'] = meta
function os:get_cfg_plot_PlotCall()
local o = {}
setmetatable(o, cfg.plot.PlotCall)
o.key = self:get_string()
local _list = self:get_list('string')
o.calls = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.fight.StatusText'
get_or_create('cfg.fight')['StatusText'] = meta
function os:get_cfg_fight_StatusText()
local o = {}
setmetatable(o, cfg.fight.StatusText)
o.attrtype = self:get_int()
o.text = self:get_string()
o.displaytype = self:get_int()
o.spritename = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.Display'
get_or_create('cfg.guide')['Display'] = meta
function os:get_cfg_guide_Display()
local o = {}
setmetatable(o, cfg.guide.Display)
o.istexture = self:get_bool()
o.icon = self:get_string()
o.desc = self:get_string()
o.desc1 = self:get_string()
o.target = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanRecycle'
get_or_create('cfg.talisman')['TalismanRecycle'] = meta
function os:get_cfg_talisman_TalismanRecycle()
local o = {}
setmetatable(o, cfg.talisman.TalismanRecycle)
local _list = self:get_list('cfg_talisman_RecycleExp')
o.expitemid = _list
local _list = self:get_list('cfg_talisman_RecycleExp')
o.qualitytalismanid = _list
local _list = self:get_list('int')
o.allexpitemid = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.EquipAnnealMax'
get_or_create('cfg.operationalactivity')['EquipAnnealMax'] = meta
function os:get_cfg_operationalactivity_EquipAnnealMax()
local o = {}
setmetatable(o, cfg.operationalactivity.EquipAnnealMax)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.AttackList'
get_or_create('cfg.skill')['AttackList'] = meta
function os:get_cfg_skill_AttackList()
local o = {}
setmetatable(o, cfg.skill.AttackList)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_skill_Attack')
o.attacks = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.StarConditionInfo'
get_or_create('cfg.ectype')['StarConditionInfo'] = meta
function os:get_cfg_ectype_StarConditionInfo()
local o = {}
setmetatable(o, cfg.ectype.StarConditionInfo)
o.condition = self:get_int()
o.description = self:get_string()
o.value = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.ContinuityLoginBonus'
get_or_create('cfg.bonus')['ContinuityLoginBonus'] = meta
function os:get_cfg_bonus_ContinuityLoginBonus()
local o = {}
setmetatable(o, cfg.bonus.ContinuityLoginBonus)
o.bonuslist = self:get_cfg_cmd_action_RandomBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.AttackCity'
get_or_create('cfg.ectype')['AttackCity'] = meta
function os:get_cfg_ectype_AttackCity()
local o = {}
setmetatable(o, cfg.ectype.AttackCity)
o.id = self:get_int()
o.bgpic = self:get_string()
o.requirelevel = self:get_cfg_cmd_condition_MinLevel()
o.signuptime = self:get_cfg_common_WeekTimeRange()
o.opentime = self:get_cfg_common_WeekTimeRange()
local _list = self:get_list('cfg_ectype_AttackCityLevelSection')
o.sections = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.LevelStatusInfo'
get_or_create('cfg.role')['LevelStatusInfo'] = meta
function os:get_cfg_role_LevelStatusInfo()
local o = {}
setmetatable(o, cfg.role.LevelStatusInfo)
o.level = self:get_int()
o.attr = self:get_cfg_fight_Attr()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ReviveInfo'
get_or_create('cfg.ectype')['ReviveInfo'] = meta
function os:get_cfg_ectype_ReviveInfo()
local o = {}
setmetatable(o, cfg.ectype.ReviveInfo)
o.type = self:get_int()
o.maxcount = self:get_int()
o.position = self:get_cfg_map_Vector2()
o.time = self:get_int()
o.cost = self:get_cfg_cmd_condition_Currency()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Enter'
get_or_create('cfg.ectype')['Enter'] = meta
function os:get_cfg_ectype_Enter()
local o = {}
setmetatable(o, cfg.ectype.Enter)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TeamFight'
get_or_create('cfg.operationalactivity')['TeamFight'] = meta
function os:get_cfg_operationalactivity_TeamFight()
local o = {}
setmetatable(o, cfg.operationalactivity.TeamFight)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ClimbTowerEctype'
meta.OFFSET_FLOOR_FROM_MAX_FLOOR = 10
meta.OFFLINE_BONUS_MAILID = 11
get_or_create('cfg.ectype')['ClimbTowerEctype'] = meta
function os:get_cfg_ectype_ClimbTowerEctype()
local o = {}
setmetatable(o, cfg.ectype.ClimbTowerEctype)
o.id = self:get_int()
o.baseid = self:get_int()
o.mainregionid = self:get_int()
o.revivetime = self:get_int()
o.dailylimit = self:get_cfg_cmd_condition_VipLimitsLite()
o.levellimit = self:get_cfg_cmd_condition_MinLevel()
o.sweep = self:get_cfg_ectype_ClimbTowerSweep()
local _list = self:get_list('cfg_ectype_Buff')
o.buffs = _list
o.buffs_buffid = {}
for _, _V in ipairs(_list) do
o.buffs_buffid[_V.buffid] = _V
end
local _list = self:get_list('cfg_ectype_FloorInfo')
o.floors = _list
o.floors_id = {}
for _, _V in ipairs(_list) do
o.floors_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.ForeverAll'
get_or_create('cfg.ai')['ForeverAll'] = meta
function os:get_cfg_ai_ForeverAll()
local o = {}
setmetatable(o, cfg.ai.ForeverAll)
local _list = self:get_list('cfg_ai_Expression')
o.exprs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.navmesh.Vector3'
get_or_create('cfg.navmesh')['Vector3'] = meta
function os:get_cfg_navmesh_Vector3()
local o = {}
setmetatable(o, cfg.navmesh.Vector3)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PathFinding'
get_or_create('cfg.ectype')['PathFinding'] = meta
function os:get_cfg_ectype_PathFinding()
local o = {}
setmetatable(o, cfg.ectype.PathFinding)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.content = self:get_string()
o.curveid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MonsterHp'
get_or_create('cfg.cmd.condition')['MonsterHp'] = meta
function os:get_cfg_cmd_condition_MonsterHp()
local o = {}
setmetatable(o, cfg.cmd.condition.MonsterHp)
o.monsterid = self:get_int()
o.hp = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.TitleTypeName'
get_or_create('cfg.role')['TitleTypeName'] = meta
function os:get_cfg_role_TitleTypeName()
local o = {}
setmetatable(o, cfg.role.TitleTypeName)
o.typeid = self:get_int()
o.typename = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.TaskFailed'
get_or_create('cfg.task')['TaskFailed'] = meta
function os:get_cfg_task_TaskFailed()
local o = {}
setmetatable(o, cfg.task.TaskFailed)
o.roledead = self:get_bool()
o.playeroffline = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.DiamondMall'
get_or_create('cfg.mall')['DiamondMall'] = meta
function os:get_cfg_mall_DiamondMall()
local o = {}
setmetatable(o, cfg.mall.DiamondMall)
o.id = self:get_int()
o.itemid = self:get_cfg_cmd_action_OneItem()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.cost = self:get_cfg_cmd_condition_Currency()
o.introduce = self:get_string()
o.limitlist = self:get_cfg_cmd_condition_Limits()
o.page = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.FixedPoint'
get_or_create('cfg.map')['FixedPoint'] = meta
function os:get_cfg_map_FixedPoint()
local o = {}
setmetatable(o, cfg.map.FixedPoint)
o.position = self:get_cfg_map_Vector3()
o.orientation = self:get_cfg_map_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ChengJiu'
get_or_create('cfg.cmd.condition')['ChengJiu'] = meta
function os:get_cfg_cmd_condition_ChengJiu()
local o = {}
setmetatable(o, cfg.cmd.condition.ChengJiu)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TeamFightLevelGroup'
get_or_create('cfg.ectype')['TeamFightLevelGroup'] = meta
function os:get_cfg_ectype_TeamFightLevelGroup()
local o = {}
setmetatable(o, cfg.ectype.TeamFightLevelGroup)
o.level = self:get_int()
o.winbonus = self:get_cfg_cmd_action_MultiBonus()
o.losebonus = self:get_cfg_cmd_action_MultiBonus()
o.drawbonus = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.EquipPerfuseMax'
get_or_create('cfg.operationalactivity')['EquipPerfuseMax'] = meta
function os:get_cfg_operationalactivity_EquipPerfuseMax()
local o = {}
setmetatable(o, cfg.operationalactivity.EquipPerfuseMax)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.ModelAction'
get_or_create('cfg.skill')['ModelAction'] = meta
function os:get_cfg_skill_ModelAction()
local o = {}
setmetatable(o, cfg.skill.ModelAction)
o.actionname = self:get_string()
o.actionsourcetype = self:get_int()
o.othermodelname = self:get_string()
o.actionfile = self:get_string()
o.foreactionfile = self:get_string()
o.succactionfile = self:get_string()
o.actionspeed = self:get_float()
o.loopplay = self:get_int()
o.effectid = self:get_int()
local _list = self:get_list('cfg_skill_Action')
o.actions = _list
local _list = self:get_list('cfg_skill_Effect')
o.effects = _list
o.effects_id = {}
for _, _V in ipairs(_list) do
o.effects_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.ZhanChang'
get_or_create('cfg.cmd.action')['ZhanChang'] = meta
function os:get_cfg_cmd_action_ZhanChang()
local o = {}
setmetatable(o, cfg.cmd.action.ZhanChang)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.tips.TipsContent'
get_or_create('cfg.tips')['TipsContent'] = meta
function os:get_cfg_tips_TipsContent()
local o = {}
setmetatable(o, cfg.tips.TipsContent)
o.id = self:get_int()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.FixCurrency'
get_or_create('cfg.cmd.condition')['FixCurrency'] = meta
function os:get_cfg_cmd_condition_FixCurrency()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.AddDescription'
get_or_create('cfg.ectype')['AddDescription'] = meta
function os:get_cfg_ectype_AddDescription()
local o = {}
setmetatable(o, cfg.ectype.AddDescription)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemOther'
get_or_create('cfg.item')['ItemOther'] = meta
function os:get_cfg_item_ItemOther()
local o = {}
setmetatable(o, cfg.item.ItemOther)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Color'
get_or_create('cfg.ui')['Color'] = meta
function os:get_cfg_ui_Color()
local o = {}
setmetatable(o, cfg.ui.Color)
o.a = self:get_float()
o.b = self:get_float()
o.g = self:get_float()
o.r = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Action'
get_or_create('cfg.ectype')['Action'] = meta
function os:get_cfg_ectype_Action()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Vector4'
get_or_create('cfg.ui')['Vector4'] = meta
function os:get_cfg_ui_Vector4()
local o = {}
setmetatable(o, cfg.ui.Vector4)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
o.w = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.setting.LinearFogSettingList'
get_or_create('cfg.setting')['LinearFogSettingList'] = meta
function os:get_cfg_setting_LinearFogSettingList()
local o = {}
setmetatable(o, cfg.setting.LinearFogSettingList)
local _list = self:get_list('cfg_setting_LinearFogSetting')
o.linearfogsettinglist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Vector3'
get_or_create('cfg.ui')['Vector3'] = meta
function os:get_cfg_ui_Vector3()
local o = {}
setmetatable(o, cfg.ui.Vector3)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.PointInfo'
get_or_create('cfg.ectype')['PointInfo'] = meta
function os:get_cfg_ectype_PointInfo()
local o = {}
setmetatable(o, cfg.ectype.PointInfo)
o.position = self:get_cfg_map_Vector2()
o.orient = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.FirstRecharge'
get_or_create('cfg.cmd.condition')['FirstRecharge'] = meta
function os:get_cfg_cmd_condition_FirstRecharge()
local o = {}
setmetatable(o, cfg.cmd.condition.FirstRecharge)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.AnnealBonus'
get_or_create('cfg.equip')['AnnealBonus'] = meta
function os:get_cfg_equip_AnnealBonus()
local o = {}
setmetatable(o, cfg.equip.AnnealBonus)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_equip_BonusData')
o.bonus = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.RemoveDescription'
get_or_create('cfg.ectype')['RemoveDescription'] = meta
function os:get_cfg_ectype_RemoveDescription()
local o = {}
setmetatable(o, cfg.ectype.RemoveDescription)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.DateTimeRange'
get_or_create('cfg.common')['DateTimeRange'] = meta
function os:get_cfg_common_DateTimeRange()
local o = {}
setmetatable(o, cfg.common.DateTimeRange)
o.begintime = self:get_cfg_common_DateTime()
o.endtime = self:get_cfg_common_DateTime()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MonsterAppear'
get_or_create('cfg.cmd.condition')['MonsterAppear'] = meta
function os:get_cfg_cmd_condition_MonsterAppear()
local o = {}
setmetatable(o, cfg.cmd.condition.MonsterAppear)
o.monsterid = self:get_int()
o.distance = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.OnceTimer'
get_or_create('cfg.ectype')['OnceTimer'] = meta
function os:get_cfg_ectype_OnceTimer()
local o = {}
setmetatable(o, cfg.ectype.OnceTimer)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mail.Mail'
get_or_create('cfg.mail')['Mail'] = meta
function os:get_cfg_mail_Mail()
local o = {}
setmetatable(o, cfg.mail.Mail)
o.id = self:get_int()
o.sender = self:get_int()
o.title = self:get_string()
o.content = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.BeAttackCurve'
get_or_create('cfg.skill')['BeAttackCurve'] = meta
function os:get_cfg_skill_BeAttackCurve()
local o = {}
setmetatable(o, cfg.skill.BeAttackCurve)
o.id = self:get_int()
o.note = self:get_string()
o.Ename = self:get_string()
o.mass = self:get_int()
o.typeid = self:get_int()
o.velocity = self:get_float()
o.randV = self:get_string()
o.angle = self:get_float()
o.distance = self:get_float()
o.randDX = self:get_string()
o.randDY = self:get_string()
o.randDZ = self:get_string()
o.angleToGather = self:get_float()
o.gravity = self:get_float()
o.friction = self:get_float()
o.defaultGravity = self:get_float()
o.decay = self:get_int()
o.actionInTheAir = self:get_string()
o.actionToClimb = self:get_string()
o.maxTime = self:get_float()
o.shakeID = self:get_int()
o.Time = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUISlider'
get_or_create('cfg.ui')['ContentUISlider'] = meta
function os:get_cfg_ui_ContentUISlider()
local o = {}
setmetatable(o, cfg.ui.ContentUISlider)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.value = self:get_float()
o.alpha = self:get_float()
o.numberOfSteps = self:get_int()
o.backgroundWidget = self:get_cfg_ui_ContentUITransform()
o.foregroundWidget = self:get_cfg_ui_ContentUITransform()
o.thumb = self:get_cfg_ui_ContentUITransform()
o.fillDirection = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SpeedRegionInfo'
get_or_create('cfg.ectype')['SpeedRegionInfo'] = meta
function os:get_cfg_ectype_SpeedRegionInfo()
local o = {}
setmetatable(o, cfg.ectype.SpeedRegionInfo)
o.regionid = self:get_int()
o.needgrade = self:get_int()
o.isbossregion = self:get_bool()
o.decs = self:get_string()
o.rebornposition = self:get_cfg_map_Vector2()
o.aimposition = self:get_cfg_map_Vector2()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.WildcardProfession'
get_or_create('cfg.common')['WildcardProfession'] = meta
function os:get_cfg_common_WildcardProfession()
local o = {}
setmetatable(o, cfg.common.WildcardProfession)
o.id = self:get_int()
o.qingyun = self:get_string()
o.tianyin = self:get_string()
o.guiwang = self:get_string()
o.hehuan = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Currencys'
get_or_create('cfg.cmd.action')['Currencys'] = meta
function os:get_cfg_cmd_action_Currencys()
local o = {}
setmetatable(o, cfg.cmd.action.Currencys)
local _list = self:get_list('cfg_cmd_action_Currency')
o.currencys = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.EquipBindSfx'
get_or_create('cfg.equip')['EquipBindSfx'] = meta
function os:get_cfg_equip_EquipBindSfx()
local o = {}
setmetatable(o, cfg.equip.EquipBindSfx)
o.equipid = self:get_int()
local _list = self:get_list('int')
o.sfxlist = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.TianFu'
get_or_create('cfg.cmd.action')['TianFu'] = meta
function os:get_cfg_cmd_action_TianFu()
local o = {}
setmetatable(o, cfg.cmd.action.TianFu)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.AlertRange'
get_or_create('cfg.ectype')['AlertRange'] = meta
function os:get_cfg_ectype_AlertRange()
local o = {}
setmetatable(o, cfg.ectype.AlertRange)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.WeekTime'
get_or_create('cfg.common')['WeekTime'] = meta
function os:get_cfg_common_WeekTime()
local o = {}
setmetatable(o, cfg.common.WeekTime)
o.weekday = self:get_int()
o.hour = self:get_int()
o.minute = self:get_int()
o.second = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pathfly.Vector3'
get_or_create('cfg.pathfly')['Vector3'] = meta
function os:get_cfg_pathfly_Vector3()
local o = {}
setmetatable(o, cfg.pathfly.Vector3)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.Rank'
get_or_create('cfg.bonus')['Rank'] = meta
function os:get_cfg_bonus_Rank()
local o = {}
setmetatable(o, cfg.bonus.Rank)
o.ranktype = self:get_int()
o.isrolerank = self:get_bool()
o.showsize = self:get_int()
o.ranksize = self:get_int()
o.updatetype = self:get_int()
o.realtime = self:get_bool()
o.desc = self:get_bool()
o.refreshrate = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.AcceptTask'
get_or_create('cfg.cmd.condition')['AcceptTask'] = meta
function os:get_cfg_cmd_condition_AcceptTask()
local o = {}
setmetatable(o, cfg.cmd.condition.AcceptTask)
o.taskid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.KillMonster'
get_or_create('cfg.ectype')['KillMonster'] = meta
function os:get_cfg_ectype_KillMonster()
local o = {}
setmetatable(o, cfg.ectype.KillMonster)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
local _list = self:get_list('cfg_ectype_MissionKillMonster')
o.missions = _list
o.missions_monsterid = {}
for _, _V in ipairs(_list) do
o.missions_monsterid[_V.monsterid] = _V
end
o.slowmotion = self:get_bool()
o.motionid = self:get_string()
o.autofight = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.EquipQuality'
get_or_create('cfg.operationalactivity')['EquipQuality'] = meta
function os:get_cfg_operationalactivity_EquipQuality()
local o = {}
setmetatable(o, cfg.operationalactivity.EquipQuality)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.level = self:get_int()
o.qulity = self:get_int()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ChongZhiJiFen'
get_or_create('cfg.cmd.condition')['ChongZhiJiFen'] = meta
function os:get_cfg_cmd_condition_ChongZhiJiFen()
local o = {}
setmetatable(o, cfg.cmd.condition.ChongZhiJiFen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.team.team'
meta.ReFollowTime = 1
meta.FollowDistance = 2
get_or_create('cfg.team')['team'] = meta
function os:get_cfg_team_team()
local o = {}
setmetatable(o, cfg.team.team)
o.requirelevel = self:get_int()
o.teammembermaxcount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.commoneffect.ParticleEffect'
get_or_create('cfg.commoneffect')['ParticleEffect'] = meta
function os:get_cfg_commoneffect_ParticleEffect()
local o = {}
setmetatable(o, cfg.commoneffect.ParticleEffect)
o.timeline = self:get_float()
o.type = self:get_int()
o.fadeouttime = self:get_float()
o.path = self:get_string()
o.life = self:get_float()
o.followdirection = self:get_bool()
o.followbeattackeddirection = self:get_bool()
o.scale = self:get_float()
o.casterbindtype = self:get_int()
o.followbonedirection = self:get_bool()
o.targetbindtype = self:get_int()
o.instancetracetype = self:get_int()
o.worldoffsetx = self:get_float()
o.worldoffsety = self:get_float()
o.worldoffsetz = self:get_float()
o.bonename = self:get_string()
o.boneposx = self:get_float()
o.boneposy = self:get_float()
o.boneposz = self:get_float()
o.bonerotx = self:get_float()
o.boneroty = self:get_float()
o.bonerotz = self:get_float()
o.bonescalex = self:get_float()
o.bonescaley = self:get_float()
o.bonescalez = self:get_float()
o.tracetime = self:get_float()
o.aligntype = self:get_int()
o.ispooldestoryed = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.broadcast.Broadcast'
get_or_create('cfg.broadcast')['Broadcast'] = meta
function os:get_cfg_broadcast_Broadcast()
local o = {}
setmetatable(o, cfg.broadcast.Broadcast)
local _list = self:get_list('cfg_broadcast_Notice')
o.notices = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.Parallel'
get_or_create('cfg.ai')['Parallel'] = meta
function os:get_cfg_ai_Parallel()
local o = {}
setmetatable(o, cfg.ai.Parallel)
local _list = self:get_list('cfg_ai_Expression')
o.exprs = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.Buff'
get_or_create('cfg.buff')['Buff'] = meta
function os:get_cfg_buff_Buff()
local o = {}
setmetatable(o, cfg.buff.Buff)
o.id = self:get_int()
o.buffname = self:get_string()
o.name = self:get_string()
local _list = self:get_list('cfg_buff_EffectInfo')
o.effects = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.item.ItemRiding'
get_or_create('cfg.item')['ItemRiding'] = meta
function os:get_cfg_item_ItemRiding()
local o = {}
setmetatable(o, cfg.item.ItemRiding)
o.id = self:get_int()
o.name = self:get_string()
o.itemtype = self:get_int()
o.displayitemtype = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.gender = self:get_cfg_cmd_condition_Gender()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.levellimit = self:get_cfg_cmd_condition_MinMaxLevel()
o.maxpile = self:get_int()
o.batch = self:get_bool()
o.cansell = self:get_bool()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
o.ridingid = self:get_int()
o.effectiveime = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.CountType'
get_or_create('cfg.common')['CountType'] = meta
function os:get_cfg_common_CountType()
local o = {}
setmetatable(o, cfg.common.CountType)
o.id = self:get_int()
o.name = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.JewelryLvlUp'
get_or_create('cfg.equip')['JewelryLvlUp'] = meta
function os:get_cfg_equip_JewelryLvlUp()
local o = {}
setmetatable(o, cfg.equip.JewelryLvlUp)
o.jewelrylvl = self:get_int()
o.requireexp = self:get_int()
o.basicvalue = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.DamageValue'
get_or_create('cfg.ectype')['DamageValue'] = meta
function os:get_cfg_ectype_DamageValue()
local o = {}
setmetatable(o, cfg.ectype.DamageValue)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.role.BroadcastLimit'
get_or_create('cfg.role')['BroadcastLimit'] = meta
function os:get_cfg_role_BroadcastLimit()
local o = {}
setmetatable(o, cfg.role.BroadcastLimit)
o.maxtotalremain = self:get_int()
o.maxsendpersecond = self:get_int()
o.addremainpersecond = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.LocationPointData'
get_or_create('cfg.task')['LocationPointData'] = meta
function os:get_cfg_task_LocationPointData()
local o = {}
setmetatable(o, cfg.task.LocationPointData)
o.x = self:get_float()
o.y = self:get_float()
o.z = self:get_float()
o.worldmapid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.ChongZhiJiFen'
get_or_create('cfg.cmd.action')['ChongZhiJiFen'] = meta
function os:get_cfg_cmd_action_ChongZhiJiFen()
local o = {}
setmetatable(o, cfg.cmd.action.ChongZhiJiFen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.AwakeEffect'
get_or_create('cfg.pet')['AwakeEffect'] = meta
function os:get_cfg_pet_AwakeEffect()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.common.NavigationMode'
get_or_create('cfg.common')['NavigationMode'] = meta
function os:get_cfg_common_NavigationMode()
local o = {}
setmetatable(o, cfg.common.NavigationMode)
o.id = self:get_int()
o.name = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Transform'
get_or_create('cfg.ui')['Transform'] = meta
function os:get_cfg_ui_Transform()
local o = {}
setmetatable(o, cfg.ui.Transform)
o.position = self:get_cfg_ui_Vector3()
o.eularangles = self:get_cfg_ui_Vector3()
o.scale = self:get_cfg_ui_Vector3()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ORs'
get_or_create('cfg.cmd.condition')['ORs'] = meta
function os:get_cfg_cmd_condition_ORs()
local o = {}
setmetatable(o, cfg.cmd.condition.ORs)
local _list = self:get_list('cfg_cmd_condition_Condition')
o.conditions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.JewelryLvlLimit'
get_or_create('cfg.equip')['JewelryLvlLimit'] = meta
function os:get_cfg_equip_JewelryLvlLimit()
local o = {}
setmetatable(o, cfg.equip.JewelryLvlLimit)
o.level = self:get_int()
local _list = self:get_list('int')
o.jewelrylvl = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.FramentSortOrder'
meta.ITEM_SORT_ORDER = "profession,quality,level,eitemtype"
meta.ITEM_SORT_RULE = "desc,desc,desc,desc"
get_or_create('cfg.equip')['FramentSortOrder'] = meta
function os:get_cfg_equip_FramentSortOrder()
local o = {}
setmetatable(o, cfg.equip.FramentSortOrder)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetSkillCostInfo'
get_or_create('cfg.pet')['PetSkillCostInfo'] = meta
function os:get_cfg_pet_PetSkillCostInfo()
local o = {}
setmetatable(o, cfg.pet.PetSkillCostInfo)
o.requirelvl = self:get_int()
o.cost = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.MinVipLevel'
get_or_create('cfg.cmd.condition')['MinVipLevel'] = meta
function os:get_cfg_cmd_condition_MinVipLevel()
local o = {}
setmetatable(o, cfg.cmd.condition.MinVipLevel)
o.level = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.moduleunlockcond'
get_or_create('cfg.ui')['moduleunlockcond'] = meta
function os:get_cfg_ui_moduleunlockcond()
local o = {}
setmetatable(o, cfg.ui.moduleunlockcond)
o.id = self:get_int()
o.openlevel = self:get_int()
o.opentaskid = self:get_int()
o.desc = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.ItemOfList'
get_or_create('cfg.guide')['ItemOfList'] = meta
function os:get_cfg_guide_ItemOfList()
local o = {}
setmetatable(o, cfg.guide.ItemOfList)
o.id = self:get_int()
o.controldlg = self:get_string()
o.controluiobject = self:get_string()
o.handtype = self:get_int()
o.arrowtype = self:get_int()
o.bordertype = self:get_int()
local _list = self:get_list('float')
o.scale = _list
o.fixorbind = self:get_bool()
local _list = self:get_list('float')
o.offset = _list
o.addcomponent = self:get_bool()
o.needclip = self:get_bool()
local _list = self:get_list('float')
o.clipoffset = _list
local _list = self:get_list('int')
o.itemid = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.active.Activebonus'
get_or_create('cfg.active')['Activebonus'] = meta
function os:get_cfg_active_Activebonus()
local o = {}
setmetatable(o, cfg.active.Activebonus)
o.grade = self:get_int()
o.icon = self:get_string()
o.award = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddMPByLevelInterval'
get_or_create('cfg.buff')['AddMPByLevelInterval'] = meta
function os:get_cfg_buff_AddMPByLevelInterval()
local o = {}
setmetatable(o, cfg.buff.AddMPByLevelInterval)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
local _list = self:get_list('string')
o.introduction = _list
local _list = self:get_list('float')
o.value = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ai.ChooseDefencer'
get_or_create('cfg.ai')['ChooseDefencer'] = meta
function os:get_cfg_ai_ChooseDefencer()
local o = {}
setmetatable(o, cfg.ai.ChooseDefencer)
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.ShengWang'
get_or_create('cfg.cmd.action')['ShengWang'] = meta
function os:get_cfg_cmd_action_ShengWang()
local o = {}
setmetatable(o, cfg.cmd.action.ShengWang)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Vector2'
get_or_create('cfg.ui')['Vector2'] = meta
function os:get_cfg_ui_Vector2()
local o = {}
setmetatable(o, cfg.ui.Vector2)
o.x = self:get_float()
o.y = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.AttackCityLevelSection'
get_or_create('cfg.ectype')['AttackCityLevelSection'] = meta
function os:get_cfg_ectype_AttackCityLevelSection()
local o = {}
setmetatable(o, cfg.ectype.AttackCityLevelSection)
o.requirelevel = self:get_cfg_cmd_condition_MaxLevel()
o.groupnum = self:get_int()
o.mainregionid = self:get_int()
local _list = self:get_list('int')
o.bornregion = _list
o.bonusids = self:get_cfg_cmd_action_OneItems()
local _list = self:get_list('cfg_ectype_AttackCityRefreshMonster')
o.monsterinfos = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.CallSpecialEffect'
get_or_create('cfg.ectype')['CallSpecialEffect'] = meta
function os:get_cfg_ectype_CallSpecialEffect()
local o = {}
setmetatable(o, cfg.ectype.CallSpecialEffect)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.TalismanExp'
get_or_create('cfg.cmd.action')['TalismanExp'] = meta
function os:get_cfg_cmd_action_TalismanExp()
local o = {}
setmetatable(o, cfg.cmd.action.TalismanExp)
o.amount = self:get_long()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.Item'
get_or_create('cfg.cmd.condition')['Item'] = meta
function os:get_cfg_cmd_condition_Item()
local o = {}
setmetatable(o, cfg.cmd.condition.Item)
o.itemid = self:get_int()
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.Property'
get_or_create('cfg.talisman')['Property'] = meta
function os:get_cfg_talisman_Property()
local o = {}
setmetatable(o, cfg.talisman.Property)
local _list = self:get_list('cfg_equip_EquipPropertyData')
o.gainability = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.PetTotalStar'
get_or_create('cfg.operationalactivity')['PetTotalStar'] = meta
function os:get_cfg_operationalactivity_PetTotalStar()
local o = {}
setmetatable(o, cfg.operationalactivity.PetTotalStar)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.skill.Effect'
get_or_create('cfg.skill')['Effect'] = meta
function os:get_cfg_skill_Effect()
local o = {}
setmetatable(o, cfg.skill.Effect)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('cfg_skill_Action')
o.actions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.HasActiveRide'
get_or_create('cfg.cmd.condition')['HasActiveRide'] = meta
function os:get_cfg_cmd_condition_HasActiveRide()
local o = {}
setmetatable(o, cfg.cmd.condition.HasActiveRide)
o.has = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.Dialog'
get_or_create('cfg.ui')['Dialog'] = meta
function os:get_cfg_ui_Dialog()
local o = {}
setmetatable(o, cfg.ui.Dialog)
o.dlgname = self:get_string()
o.parenttype = self:get_int()
o.showreturn = self:get_bool()
o.showcurrency = self:get_bool()
o.showcurrency0 = self:get_int()
o.showcurrency1 = self:get_int()
o.showcurrency2 = self:get_int()
o.showcurrency3 = self:get_int()
o.showedgebackground = self:get_bool()
o.backgroundtype = self:get_int()
local _list = self:get_list('cfg_ui_TabGroup')
o.tabgroups = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetFragment'
get_or_create('cfg.pet')['PetFragment'] = meta
function os:get_cfg_pet_PetFragment()
local o = {}
setmetatable(o, cfg.pet.PetFragment)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.level = self:get_int()
o.quality = self:get_int()
o.prize = self:get_int()
o.professionlimit = self:get_cfg_cmd_condition_ProfessionLimit()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.introduction = self:get_string()
o.maxpile = self:get_int()
o.cansell = self:get_bool()
o.petID = self:get_int()
o.number = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.TalismanSkill'
get_or_create('cfg.talisman')['TalismanSkill'] = meta
function os:get_cfg_talisman_TalismanSkill()
local o = {}
setmetatable(o, cfg.talisman.TalismanSkill)
o.id = self:get_int()
o.name = self:get_string()
local _list = self:get_list('int')
o.skillid = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.HuoBanJiFen'
get_or_create('cfg.cmd.condition')['HuoBanJiFen'] = meta
function os:get_cfg_cmd_condition_HuoBanJiFen()
local o = {}
setmetatable(o, cfg.cmd.condition.HuoBanJiFen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.BlackMarketOpenTime'
get_or_create('cfg.family')['BlackMarketOpenTime'] = meta
function os:get_cfg_family_BlackMarketOpenTime()
local o = {}
setmetatable(o, cfg.family.BlackMarketOpenTime)
o.starthour = self:get_int()
o.startminute = self:get_int()
o.endhour = self:get_int()
o.endminute = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.List'
get_or_create('cfg.guide')['List'] = meta
function os:get_cfg_guide_List()
local o = {}
setmetatable(o, cfg.guide.List)
o.id = self:get_int()
o.controldlg = self:get_string()
o.controluiobject = self:get_string()
o.handtype = self:get_int()
o.arrowtype = self:get_int()
o.bordertype = self:get_int()
local _list = self:get_list('float')
o.scale = _list
o.fixorbind = self:get_bool()
local _list = self:get_list('float')
o.offset = _list
o.addcomponent = self:get_bool()
o.needclip = self:get_bool()
local _list = self:get_list('float')
o.clipoffset = _list
o.index = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.BindType'
get_or_create('cfg.cmd.action')['BindType'] = meta
function os:get_cfg_cmd_action_BindType()
local o = {}
setmetatable(o, cfg.cmd.action.BindType)
o.bindtype = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.talisman.RecycleExp'
get_or_create('cfg.talisman')['RecycleExp'] = meta
function os:get_cfg_talisman_RecycleExp()
local o = {}
setmetatable(o, cfg.talisman.RecycleExp)
o.itemkey = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.sfxInfo'
get_or_create('cfg.equip')['sfxInfo'] = meta
function os:get_cfg_equip_sfxInfo()
local o = {}
setmetatable(o, cfg.equip.sfxInfo)
o.sfxid = self:get_int()
o.sfxtype = self:get_int()
o.attachpoint = self:get_string()
o.path = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MultiPoints'
get_or_create('cfg.map')['MultiPoints'] = meta
function os:get_cfg_map_MultiPoints()
local o = {}
setmetatable(o, cfg.map.MultiPoints)
local _list = self:get_list('cfg_map_FixedPoint')
o.positions = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.RuneRandomInfo'
get_or_create('cfg.ectype')['RuneRandomInfo'] = meta
function os:get_cfg_ectype_RuneRandomInfo()
local o = {}
setmetatable(o, cfg.ectype.RuneRandomInfo)
o.rune = self:get_int()
o.droprate = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIScrollBar'
get_or_create('cfg.ui')['ContentUIScrollBar'] = meta
function os:get_cfg_ui_ContentUIScrollBar()
local o = {}
setmetatable(o, cfg.ui.ContentUIScrollBar)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.value = self:get_float()
o.alpha = self:get_float()
o.numberOfSteps = self:get_int()
o.backgroundWidget = self:get_cfg_ui_ContentUITransform()
o.foregroundWidget = self:get_cfg_ui_ContentUITransform()
o.thumb = self:get_cfg_ui_ContentUITransform()
o.fillDirection = self:get_string()
o.barSize = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.Reflect'
get_or_create('cfg.buff')['Reflect'] = meta
function os:get_cfg_buff_Reflect()
local o = {}
setmetatable(o, cfg.buff.Reflect)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.equip.Accessory'
meta.VICE_PROPERTY_NUM = 5
meta.MAIN_PROP1_KEY = 1
meta.MAIN_PROP2_KEY = 2
get_or_create('cfg.equip')['Accessory'] = meta
function os:get_cfg_equip_Accessory()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.ServerController'
get_or_create('cfg.cmd.condition')['ServerController'] = meta
function os:get_cfg_cmd_condition_ServerController()
local o = {}
setmetatable(o, cfg.cmd.condition.ServerController)
o.controllerid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pet.PetSkin'
get_or_create('cfg.pet')['PetSkin'] = meta
function os:get_cfg_pet_PetSkin()
local o = {}
setmetatable(o, cfg.pet.PetSkin)
o.id = self:get_int()
o.name = self:get_string()
o.icon = self:get_string()
o.displayorder = self:get_int()
o.gain = self:get_string()
o.detail = self:get_string()
o.price = self:get_cfg_cmd_condition_YuanBao()
o.quality = self:get_int()
o.petid = self:get_int()
o.modelname = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ui.ContentUIPlaySound'
get_or_create('cfg.ui')['ContentUIPlaySound'] = meta
function os:get_cfg_ui_ContentUIPlaySound()
local o = {}
setmetatable(o, cfg.ui.ContentUIPlaySound)
o.path = self:get_string()
local _list = self:get_list('int')
o.pathlist = _list
o.transform = self:get_cfg_ui_Transform()
o.audioClip = self:get_string()
o.trigger = self:get_string()
o.volume = self:get_float()
o.pitch = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pay.GrowPlan'
get_or_create('cfg.pay')['GrowPlan'] = meta
function os:get_cfg_pay_GrowPlan()
local o = {}
setmetatable(o, cfg.pay.GrowPlan)
o.chargeid = self:get_int()
o.displayorder = self:get_int()
o.price = self:get_int()
o.platform = self:get_map('int', 'int')
o.growplantype = self:get_int()
o.logindaynum = self:get_int()
o.thresholddaynum = self:get_int()
o.startdayindex = self:get_int()
o.notetext = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.SetEnviroment'
get_or_create('cfg.ectype')['SetEnviroment'] = meta
function os:get_cfg_ectype_SetEnviroment()
local o = {}
setmetatable(o, cfg.ectype.SetEnviroment)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.monster.DefaultAIParam'
get_or_create('cfg.monster')['DefaultAIParam'] = meta
function os:get_cfg_monster_DefaultAIParam()
local o = {}
setmetatable(o, cfg.monster.DefaultAIParam)
o.ai = self:get_int()
o.proactive = self:get_bool()
o.guardradius = self:get_int()
o.traceradius = self:get_int()
o.hostilitytype = self:get_int()
o.walkbackhealhprate = self:get_int()
o.walkbackhealhppercentrate = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.HuoBanJiFen'
get_or_create('cfg.cmd.action')['HuoBanJiFen'] = meta
function os:get_cfg_cmd_action_HuoBanJiFen()
local o = {}
setmetatable(o, cfg.cmd.action.HuoBanJiFen)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.FinishSpecialEventData'
get_or_create('cfg.task')['FinishSpecialEventData'] = meta
function os:get_cfg_task_FinishSpecialEventData()
local o = {}
setmetatable(o, cfg.task.FinishSpecialEventData)
o.eventtype = self:get_int()
o.id = self:get_int()
o.targetid = self:get_int()
o.location = self:get_cfg_task_LocationPointData()
o.playcgtype = self:get_int()
o.count = self:get_int()
o.description = self:get_string()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.Switch'
get_or_create('cfg.ectype')['Switch'] = meta
function os:get_cfg_ectype_Switch()
local o = {}
setmetatable(o, cfg.ectype.Switch)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
local _list = self:get_list('cfg_ectype_Case')
o.cases = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TeamSpeed'
get_or_create('cfg.operationalactivity')['TeamSpeed'] = meta
function os:get_cfg_operationalactivity_TeamSpeed()
local o = {}
setmetatable(o, cfg.operationalactivity.TeamSpeed)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.task.ShowHideGroup'
get_or_create('cfg.task')['ShowHideGroup'] = meta
function os:get_cfg_task_ShowHideGroup()
local o = {}
setmetatable(o, cfg.task.ShowHideGroup)
local _list = self:get_list('int')
o.allid = _list
o.showhideaccept = self:get_bool()
o.showhidecomplete = self:get_bool()
o.showhidefinish = self:get_bool()
o.showhidefail = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.operationalactivity.TotalCost'
get_or_create('cfg.operationalactivity')['TotalCost'] = meta
function os:get_cfg_operationalactivity_TotalCost()
local o = {}
setmetatable(o, cfg.operationalactivity.TotalCost)
o.conditiontype = self:get_int()
o.description1 = self:get_string()
o.description2 = self:get_string()
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.bonus.VipBonus'
get_or_create('cfg.bonus')['VipBonus'] = meta
function os:get_cfg_bonus_VipBonus()
local o = {}
setmetatable(o, cfg.bonus.VipBonus)
o.viplevel = self:get_int()
o.needcharge = self:get_int()
local _list = self:get_list('string')
o.bonustext = _list
o.gainbonus = self:get_cfg_cmd_action_Items()
o.buylimit = self:get_cfg_cmd_condition_Limit()
o.price = self:get_cfg_cmd_condition_YuanBao()
o.showprice = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.ControllerOperation'
get_or_create('cfg.ectype')['ControllerOperation'] = meta
function os:get_cfg_ectype_ControllerOperation()
local o = {}
setmetatable(o, cfg.ectype.ControllerOperation)
o.actionid = self:get_int()
o.isglobal = self:get_bool()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.buff.AddMPPercentInterval'
get_or_create('cfg.buff')['AddMPPercentInterval'] = meta
function os:get_cfg_buff_AddMPPercentInterval()
local o = {}
setmetatable(o, cfg.buff.AddMPPercentInterval)
o.id = self:get_int()
o.name = self:get_string()
o.hitrate = self:get_float()
o.showicon = self:get_bool()
o.icontype = self:get_string()
o.commoneffectid = self:get_int()
o.displaypriority = self:get_int()
o.ispersistent = self:get_bool()
o.introduction = self:get_string()
o.value = self:get_float()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.pay.DayChargeBonus'
get_or_create('cfg.pay')['DayChargeBonus'] = meta
function os:get_cfg_pay_DayChargeBonus()
local o = {}
setmetatable(o, cfg.pay.DayChargeBonus)
o.id = self:get_int()
o.groupid = self:get_int()
o.description = self:get_string()
o.unfinishlabel = self:get_string()
o.finishedlabel = self:get_string()
o.num = self:get_int()
o.reward = self:get_cfg_cmd_action_MultiBonus()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.family.Pray'
get_or_create('cfg.family')['Pray'] = meta
function os:get_cfg_family_Pray()
local o = {}
setmetatable(o, cfg.family.Pray)
o.prayid = self:get_int()
o.prayname = self:get_string()
o.familycontribution = self:get_cfg_cmd_action_BangGong()
o.familycapital = self:get_cfg_cmd_action_AddFamilyMoneyBuild()
o.daylimit = self:get_cfg_cmd_condition_VipLimitsLite()
o.cost = self:get_cfg_cmd_condition_FixCurrency()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.guide.DisplaySkill'
get_or_create('cfg.guide')['DisplaySkill'] = meta
function os:get_cfg_guide_DisplaySkill()
local o = {}
setmetatable(o, cfg.guide.DisplaySkill)
local _list = self:get_list('int')
o.desc = _list
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.action.Action'
get_or_create('cfg.cmd.action')['Action'] = meta
function os:get_cfg_cmd_action_Action()
return self['get_' .. self:get_string():gsub('%.', '_')](self)
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.CompleteTask'
get_or_create('cfg.cmd.condition')['CompleteTask'] = meta
function os:get_cfg_cmd_condition_CompleteTask()
local o = {}
setmetatable(o, cfg.cmd.condition.CompleteTask)
o.taskid = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TeamStoryEctype'
meta.MATCH_TIME_MAX = 60
meta.MATCH_SUCCESS_WAITING_TIME = 5
get_or_create('cfg.ectype')['TeamStoryEctype'] = meta
function os:get_cfg_ectype_TeamStoryEctype()
local o = {}
setmetatable(o, cfg.ectype.TeamStoryEctype)
o.id = self:get_int()
o.bgmpic = self:get_string()
o.ectypepic = self:get_string()
o.storyname = self:get_string()
o.introduction = self:get_string()
o.difficulty = self:get_int()
local _list = self:get_list('int')
o.showbonusid = _list
o.preectypeid = self:get_int()
o.groupid = self:get_int()
o.sectionid = self:get_int()
o.costtili = self:get_cfg_cmd_condition_TiLi()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
o.tasklimit = self:get_cfg_cmd_condition_CompleteTask()
o.battlepower = self:get_int()
local _list = self:get_list('cfg_ectype_StarConditionInfo')
o.starcondition = _list
o.starbonus = self:get_cfg_cmd_action_MultiBonus()
o.ectypedrop = self:get_cfg_cmd_action_MultiBonus()
local _list = self:get_list('int')
o.showaward = _list
o.timelimit = self:get_int()
o.daylimit = self:get_cfg_cmd_condition_DayLimit()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.DayLimit'
get_or_create('cfg.cmd.condition')['DayLimit'] = meta
function os:get_cfg_cmd_condition_DayLimit()
local o = {}
setmetatable(o, cfg.cmd.condition.DayLimit)
o.num = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.cmd.condition.CombatPower'
get_or_create('cfg.cmd.condition')['CombatPower'] = meta
function os:get_cfg_cmd_condition_CombatPower()
local o = {}
setmetatable(o, cfg.cmd.condition.CombatPower)
o.amount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.friend.MarrigePackage'
get_or_create('cfg.friend')['MarrigePackage'] = meta
function os:get_cfg_friend_MarrigePackage()
local o = {}
setmetatable(o, cfg.friend.MarrigePackage)
o.marrigepackid = self:get_int()
o.marrigepackprice = self:get_cfg_cmd_condition_FixCurrency()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.map.MonsterSpawn'
get_or_create('cfg.map')['MonsterSpawn'] = meta
function os:get_cfg_map_MonsterSpawn()
local o = {}
setmetatable(o, cfg.map.MonsterSpawn)
o.monsterid = self:get_int()
o.count = self:get_int()
o.regeneratecd = self:get_float()
o.regeneratecount = self:get_int()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.ectype.TeamSpeed'
get_or_create('cfg.ectype')['TeamSpeed'] = meta
function os:get_cfg_ectype_TeamSpeed()
local o = {}
setmetatable(o, cfg.ectype.TeamSpeed)
o.id = self:get_int()
o.battlelast = self:get_int()
o.singuplast = self:get_int()
o.desc = self:get_string()
o.openlevel = self:get_cfg_cmd_condition_MinLevel()
local _list = self:get_list('cfg_common_DayTimeRange')
o.timeinfo = _list
o.dailylimit = self:get_cfg_cmd_condition_VipLimitsLite()
local _list = self:get_list('cfg_ectype_SpeedLvMsg')
o.lvmsg = _list
o.lvmsg_id = {}
for _, _V in ipairs(_list) do
o.lvmsg_id[_V.id] = _V
end
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.mall.FamilyMall'
get_or_create('cfg.mall')['FamilyMall'] = meta
function os:get_cfg_mall_FamilyMall()
local o = {}
setmetatable(o, cfg.mall.FamilyMall)
o.id = self:get_int()
o.itemid = self:get_cfg_cmd_action_OneItem()
o.bindtype = self:get_cfg_cmd_action_BindType()
o.cost = self:get_cfg_cmd_condition_Currency()
o.introduce = self:get_string()
o.limitlist = self:get_cfg_cmd_condition_Limits()
o.minfamilylevel = self:get_cfg_cmd_condition_MinFamilyShopLevel()
return o
end
meta = {}
meta.__index = meta
meta.class = 'cfg.lottery.LotteryTypeToCfg'
get_or_create('cfg.lottery')['LotteryTypeToCfg'] = meta
function os:get_cfg_lottery_LotteryTypeToCfg()
local o = {}
setmetatable(o, cfg.lottery.LotteryTypeToCfg)
o.type = self:get_int()
o.cfgid = self:get_int()
return o
end
get_or_create('cfg.lottery')['PickType'] = {
NULL = -1,
HUO_BAN = 1,
FA_BAO = 2,
HUO_BAN_TEN = 3,
FA_BAO_TEN = 4,
}
get_or_create('cfg.cmd')['CmdId'] = {
NULL = -1,
WORSHIP = 1,
BUYTILI = 2,
}
get_or_create('cfg.fight')['StateType'] = {
NULL = -1,
ENTER_PROTECT = 0,
NOT_FIGHT = 1,
DORMANT = 2,
CANNOT_MOVE = 3,
CANNOT_SKILL = 4,
PLAY_CG = 5,
RIDE_WALK = 6,
RIDE_FLY = 7,
FAINT = 8,
FREEZE = 9,
SILENCE = 10,
ITEM_FORBID = 11,
INVINCIBLE = 12,
THERPAY_FORBID = 13,
HARMFUL_FORBID = 14,
STATE_TYPE_NUM = 15,
}
get_or_create('cfg.map')['Reason'] = {
NULL = -1,
ENTER_PREV_MAP = 0,
ENTER_NEW_MAP = 1,
ENTER_NEW_WORLD = 2,
SWITCH_LINE = 3,
LOGIN = 4,
RELOGIN = 5,
LOGOUT = 6,
INTERN_EXCEPTION = 7,
ALIVE_EXPIRE = 8,
COLLECTED = 9,
DEAD = 10,
FOLLOW_OWNER = 11,
UNEQIUP = 12,
CLOSE_CONTROLLER = 13,
EATEN = 14,
IDLE_EXPIRE = 15,
LEAVE_FAMILY = 16,
BE_KICK_OUT_FAMILY = 17,
MAP_CLOSE_KICKOUT = 18,
}
get_or_create('cfg.role')['TitleType'] = {
NULL = -1,
TASK = 1,
IDOL = 2,
ACHIEVEMENT = 3,
ACTIVITY = 4,
RANK = 5,
ARENA = 6,
}
get_or_create('cfg.operationalactivity')['OperationStatus'] = {
NULL = -1,
NOT_COMPLETE = 0,
COMPLETE = 1,
GETREWARD = 2,
}
get_or_create('cfg.bag')['BagType'] = {
NULL = -1,
EQUIP = 0,
EQUIP_BODY = 1,
TALISMAN = 2,
TALISMAN_BODY = 3,
ITEM = 4,
FRAGMENT = 5,
DEPOT_FRAGMENT = 6,
DEPOT_EQUIP = 7,
DEPOT_TALISMAN = 8,
DEPOT_ITEM = 9,
}
get_or_create('cfg.task')['EDialogueRoleType'] = {
NULL = -1,
PLAYERROLE = 0,
NPC = 1,
}
get_or_create('cfg.role')['SightGroup'] = {
NULL = -1,
ME_AND_PET = 0,
I_ATTACKING_PLAYER_AND_PET = 1,
ATTACKING_ME_PLAYER_AND_PET = 2,
I_ATTACKING_MONSTER = 3,
ATTACKING_ME_MONSTER = 4,
NPC = 5,
OTHER_PLAYER_AND_PET = 6,
OTHER_MONSTER = 7,
MINE = 8,
BRIEF = 9,
}
get_or_create('cfg.ectype')['EctypeType'] = {
NULL = -1,
WORLD = 1,
CLIMB_TOWER = 2,
STORY = 3,
CURRENCY = 4,
EXP = 5,
ZAOHUA = 6,
PETECTYPE = 7,
FABAOECTYPE = 8,
YUPEI = 9,
HUFU = 10,
PERSONAL_BOSS = 11,
CHALLENGE = 12,
ARENA = 13,
GUARDTOWER = 14,
HEROES = 15,
TEAMFIGHT = 16,
PROLOGUE = 17,
HUIWU = 18,
FAMILY = 19,
TEAM_SPEED = 20,
ATK_CITY = 21,
MULTI_STORY = 22,
PLAIN_STORY = 23,
FAMILY_TEAM = 24,
}
get_or_create('cfg.active')['AddType'] = {
NULL = -1,
ADDPERTIME = 1,
ADDTOTAL = 2,
}
get_or_create('cfg.arena')['ChallengeType'] = {
NULL = -1,
CHALLENGE = 0,
BECHALLENGE = 1,
}
get_or_create('cfg.map')['MapType'] = {
NULL = -1,
WORLD = 1,
ECTYPE = 2,
}
get_or_create('cfg.buff')['DisperseType'] = {
NULL = -1,
ALL = 0,
ADVANTAGE_BUFF = 1,
DISADVANTAGE_BUFF = 2,
}
get_or_create('cfg.task')['EFinishSpecialEventType'] = {
NULL = -1,
MINING = 0,
USING_SKILL = 1,
PLAYING_CG = 2,
DOING_ECTYPE = 3,
}
get_or_create('cfg.lottery')['LotterType'] = {
NULL = -1,
ONE_LOTTERY = 1,
TEN_LOTTERY = 2,
}
get_or_create('cfg.audio')['AudioLogicalType'] = {
NULL = -1,
ECTYPEEVENT = 1,
CITYEVENT = 2,
ENVIROMENT = 3,
STORYLINES = 4,
NONSTORYLINES = 5,
BOSSKEYSKILL = 6,
PLAYERROLESKILL = 7,
USERINTERFACE = 8,
OTHERPLAYERSKILL = 9,
}
get_or_create('cfg.ectype')['DIFFICULTY'] = {
NULL = -1,
EASY = 1,
NORMAL = 2,
HARD = 3,
}
get_or_create('cfg.mall')['DiamondMallPageList'] = {
NULL = -1,
CU_XIAO = 1,
RI_CHANG = 2,
QIANG_HUA = 3,
BANG_ZUAN = 4,
}
get_or_create('cfg.mall')['ArenaShopPageList'] = {
NULL = -1,
EXCHANGE = 1,
}
get_or_create('cfg.ectype')['WorldBossStateType'] = {
NULL = -1,
NOT_EXIED = 0,
ALIVE = 1,
KILLED = 2,
}
get_or_create('cfg.chat')['MediaType'] = {
NULL = -1,
TEXT = 0,
VOICE = 1,
}
get_or_create('cfg.setting')['FogMod'] = {
NULL = -1,
LINEAR = 1,
EXPONENTIAL = 2,
EXPONENTIALSQUARED = 3,
}
get_or_create('cfg.guide')['BorderType'] = {
NULL = -1,
NONE = 0,
RECTANGLE = 1,
CIRCLE = 2,
}
get_or_create('cfg.ectype')['LayoutType'] = {
NULL = -1,
ENTER = 0,
NORMAL = 1,
EXIT = 2,
}
get_or_create('cfg.ectype')['SweepCondition'] = {
NULL = -1,
CLEAR = 1,
THREE_STAR = 2,
}
get_or_create('cfg.huiwu')['Stage'] = {
NULL = -1,
BEFORE_ENROLL = 0,
BEGIN_ENROLL = 1,
END_ENROLL = 2,
BEGIN_PRESELECT1 = 3,
END_PRESELECT1 = 4,
BEGIN_PRESELECT2 = 5,
END_PRESELECT2 = 6,
BEGIN_BATTLE = 7,
END_BATTLE = 8,
}
get_or_create('cfg.item')['MedicineType'] = {
NULL = -1,
WHITE = 0,
GREEN = 1,
}
get_or_create('cfg.skill')['SkillHitType'] = {
NULL = -1,
NORMAL = 1,
OBJECT = 2,
CHARGE = 3,
HEAL = 4,
}
get_or_create('cfg.item')['EItemBindType'] = {
NULL = -1,
BOUND = 0,
NOT_BOUND = 1,
}
get_or_create('cfg.ectype')['AreaType'] = {
NULL = -1,
CURVE = 0,
CIRCULAR = 1,
}
get_or_create('cfg.bonus')['MealType'] = {
NULL = -1,
LUNCH = 1,
DINNER = 2,
}
get_or_create('cfg.ui')['UIFunctionList'] = {
NULL = -1,
BAG_AMULET = 0,
BAG_JADE = 1,
BAG_GANGQI = 2,
BAG_FASHEN = 3,
EQUIP_PERFUSE = 4,
EQUIP_UPGRADE = 5,
ACCESSORY_WASH = 6,
ACCESSORY_TRANSFER = 7,
TALISMAN_STARORDER = 8,
TALISMAN_AWAKE = 9,
TALISMAN_WUXING = 10,
TALISMAN_DECOMPOSITION = 11,
PET_STAGESTAR = 12,
PET_AWAKE = 13,
PET_WASH = 14,
}
get_or_create('cfg.cmd')['ConfigId'] = {
NULL = -1,
MALL = 1,
ITEMBASIC = 2,
RANK = 4,
FAMILY_PRAY = 5,
REVIVE = 6,
STORY_ECTYPE = 7,
CLIMB_TOWER_ECTYPE = 8,
DAILY_ECTYPE = 9,
TRANSPORT = 12,
PERSONAL_BOSS_ECTYPE = 14,
TALISMAN_LUCK = 15,
FAMILY_FEED = 16,
ARENA_CHALLENGE = 17,
ARENA_REFRESH_OPPONENT = 18,
PET_ACTIVE_EQUIP = 19,
AMULET_WASH = 20,
HUIWU = 21,
DAILY_ECTYPE_RESET_OPEN_COUNT = 22,
EXCHANGE_HUOBAN = 23,
EXCHANGE_FABAO = 24,
PICKCARD = 25,
STORY_ECTYPE_RESET_OPEN_COUNT = 26,
PET_EQUIP = 27,
FAMILY_LEVEL_BONUS = 28,
ROLE = 29,
MULTI_STORY_ECTYPE = 30,
TEAM_SPEED = 31,
GUARD_TOWER_ECTYPE = 35,
HEROES_ECTYPE = 36,
SHAKE_MONEY_TREE = 37,
OPERATION_ACTIVITY = 38,
VIP_PACKAGE_BUY = 39,
COMMAND = 40,
CHAT = 41,
SWEEP_CLIMBTOWER = 42,
FAMILY_TEAM_ECTYPE = 43,
}
get_or_create('cfg.ectype')['EctypeEnviroment'] = {
NULL = -1,
env1 = 1,
env2 = 2,
env3 = 3,
env4 = 4,
env5 = 5,
env6 = 6,
env7 = 7,
}
get_or_create('cfg.ectype')['MathOperator'] = {
NULL = -1,
ADD = 1,
SUB = 2,
MUL = 3,
SET = 4,
}
get_or_create('cfg.audio')['SoundTypes'] = {
NULL = -1,
BEATTACK = 1,
ROLEFOOTSTEP = 2,
MOUNTFOOTSTEP = 3,
VICTORYFAILED = 4,
}
get_or_create('cfg.friend')['MaimaiRelationshipType'] = {
NULL = -1,
BanLvNan = 1,
BanLvNv = 2,
YiXiong = 3,
YiJie = 4,
YiDi = 5,
YiMei = 6,
LanYan = 7,
HongYan = 8,
XiongDi = 9,
GuiMi = 10,
SuDi = 11,
}
get_or_create('cfg.exchange')['Category'] = {
NULL = -1,
ALL = 0,
WEAPON = 1,
QINGYUNMENG_WEAPON = 2,
GUIWANGZONG_WEAPON = 3,
TIANYINSI_WEAPON = 4,
CLOTH = 5,
QINGYUNMENG_CLOTH = 6,
GUIWANGZONG_CLOTH = 7,
TIANYINSI_CLOTH = 8,
HAT = 9,
QINGYUNMENG_HAT = 10,
GUIWANGZONG_HAT = 11,
TIANYINSI_HAT = 12,
SHOE = 13,
QINGYUNMENG_SHOE = 14,
GUIWANGZONG_SHOE = 15,
TIANYINSI_SHOE = 16,
ACCESSORY = 17,
BANGLE = 18,
NECKLACE = 19,
RING = 20,
MATERIAL = 21,
TALISMAN = 22,
AMULET = 23,
PET = 24,
TYPE_NUM = 25,
}
get_or_create('cfg.item')['FlowerType'] = {
NULL = -1,
NPC = 1,
PLAYER = 2,
}
get_or_create('cfg.currency')['CurrencyType'] = {
NULL = -1,
XuNiBi = 10200001,
YuanBao = 10200002,
BindYuanBao = 10200003,
LingJing = 10200004,
JingYan = 10200005,
ZaoHua = 10200006,
WuXing = 10200007,
BangPai = 10200008,
ShiMen = 10200009,
ZhanChang = 10200010,
ShengWang = 10200011,
HuoBanJiFen = 10200012,
FaBaoJiFen = 10200013,
ChengJiu = 10200014,
BangGong = 10200015,
TianFu = 10200016,
TiLi = 10200017,
ChongZhiJiFen = 10200018,
}
get_or_create('cfg.cmd.condition')['LimitType'] = {
NULL = -1,
DAY = 1,
WEEK = 2,
MONTH = 3,
LIFELONG = 4,
}
get_or_create('cfg.ectype')['MatchType'] = {
NULL = -1,
TEAM_FIGHT = 1,
GUARD_TOWER = 2,
MULTI_STORY = 3,
ATTACK_CITY = 4,
TEAM_SPEED = 5,
}
get_or_create('cfg.plot')['QTEModeType'] = {
NULL = -1,
Combo = 0,
Click = 1,
}
get_or_create('cfg.map')['PatrolType'] = {
NULL = -1,
ONCE = 0,
ROUND = 1,
CYCLE = 2,
REPEAT = 3,
RANDOM = 4,
}
get_or_create('cfg.plot')['PlotAssetType'] = {
NULL = -1,
Model = 0,
Audio = 1,
Special = 2,
}
get_or_create('cfg.fight')['AttackType'] = {
NULL = -1,
NORMAL = 1,
METAL = 2,
WOOD = 3,
EARTH = 4,
WATER = 5,
FIRE = 6,
}
get_or_create('cfg.map')['RegenerateType'] = {
NULL = -1,
BYSINGLE = 0,
BYGROUP = 1,
BYSINGLEINGROUP = 2,
}
get_or_create('cfg.buff')['EndCondition'] = {
NULL = -1,
BY_TIME = 0,
BY_NEXT_ATTACK = 1,
BY_TRANSIENT = 2,
BY_REMOVE = 3,
}
get_or_create('cfg.mine')['MineState'] = {
NULL = -1,
PROTECTED = 0,
NORMAL = 1,
DIGGING = 2,
DIGGED = 3,
}
get_or_create('cfg.skill')['AnimTypeSelectType'] = {
NULL = -1,
Default = 0,
OnlyDefaultClip = 1,
UI = 2,
Npc = 4,
}
get_or_create('cfg.bonus')['RankType'] = {
NULL = -1,
COMBAT_POWER = 1,
LEVEL = 2,
PET = 3,
FABAO = 4,
FAMILY = 5,
ARENA = 6,
CLIMB_TOWER = 7,
FLOWER = 8,
XUNIBI = 9,
}
get_or_create('cfg.role')['StatisticType'] = {
NULL = -1,
TOTAL = 0,
SEND = 1,
NECESSARY = 2,
OTHER = 3,
UNNECESSARY = 4,
SEND_UNNECESSARY = 5,
MOVE = 6,
SKILL_PERFROM = 7,
SKILL_ATTACK = 8,
SKILL_INTERRUPT = 9,
ATTR = 10,
BUFF = 11,
}
get_or_create('cfg.skill')['CompressType'] = {
NULL = -1,
All = 1,
OnlyScale = 2,
None = 3,
}
get_or_create('cfg.fight')['AgentType'] = {
NULL = -1,
PLAYER = 1,
MONSTER = 2,
NPC = 4,
ITEM = 8,
MINE = 16,
FAKE_PLAYER = 32,
PET = 64,
RUNE = 128,
}
get_or_create('cfg.mall')['MallType'] = {
NULL = -1,
DIAMOND_MALL = 1,
FAMILY_MALL = 2,
LINJING_MALL = 3,
BLACK_MALL = 4,
ARENA_MALL = 5,
POCKET_SHOP = 6,
DAILY_ACTIVITY = 7,
FIRST_CHARGE = 8,
}
get_or_create('cfg.map')['ReviveType'] = {
NULL = -1,
CUR_POSITION = 0,
REVIVE_POSITION = 1,
}
get_or_create('cfg.task')['EDialogueActionType'] = {
NULL = -1,
NEXT = 0,
LINK = 1,
COMPLETE = 2,
PASS = 3,
}
get_or_create('cfg.mall')['BlackShopPageList'] = {
NULL = -1,
HEI_SHI = 1,
}
get_or_create('cfg.fight')['FlytextOffset'] = {
NULL = -1,
HORIZONTAL = 300,
VERTICAL = 300,
}
get_or_create('cfg.chat')['ChannelType'] = {
NULL = -1,
WORLD = 1,
PRIVATE = 2,
FAMILY = 3,
TEAM = 4,
SYSTEM = 5,
INVITE = 6,
TOP = 7,
}
get_or_create('cfg.pet')['PetType'] = {
NULL = -1,
GONGJI = 0,
FANGYU = 1,
FUZHU = 2,
}
get_or_create('cfg.ectype')['CompareOperator'] = {
NULL = -1,
EQUAL = 1,
GREATER = 2,
LESS = 3,
GREATER_OR_EQUAL = 4,
LESS_OR_EQUAL = 5,
NOT_EQUAL = 6,
}
get_or_create('cfg.achievement')['CounterType'] = {
NULL = -1,
TASK_MAINLINE = 1,
LEVEL = 2,
CONTINUE_LOGIN = 3,
XUNIBI_NUM_OBTAIN = 4,
XUNIBI_NUM_CONSUME = 5,
KILL_MONSTER = 6,
KILL_BOSS = 7,
MAX_ANNEAL_EQUIP = 8,
MAX_PERFUSE_EQUIP = 9,
TOTAL_ANNEAL_EQUIP = 10,
TOTAL_PERFUSE_EQUIP = 11,
PASS_STORY_ECTYPE = 12,
PASS_CURRENCY_ECTYPE = 13,
PASS_EXP_ECTYPE = 14,
PASS_TALISMAN_ECTYPE = 15,
PASS_ZAOHUA_ECTYPE = 16,
PVP_1V1_WIN = 17,
PVP_HUIWU_WIN = 18,
PVP_ZHENGBA_WIN = 19,
FRIEND_NUM = 20,
COMPANION_NUM = 21,
TOTAL_SOULMATE_NUM = 22,
TOTAL_CONFIDANT_NUM = 23,
YIXIONG_NUM = 24,
YIDI_NUM = 25,
YIJIE_NUM = 26,
YIMEI_NUM = 27,
PASS_NORMAL_ECTYPE = 28,
PASS_ELITE_ECTYPE = 29,
PASS_EVIL_ECTYPE = 30,
SHENGWANG = 31,
PASS_DAILY_ECTYPEvalue = 32,
PASS_CLIMB_TOWER_ECTYPE = 33,
PASS_PERSONAL_BOSS_ECTYPE = 34,
PASS_CHALLENGE_ECTYPE = 35,
PVP_3V3_WIN = 36,
PASS_PET_ECTYPE = 37,
REBORN_LEVEL = 38,
PVP_HUIWU_POJUN = 39,
PVP_HUIWU_DAMANHGUAN = 40,
PVP_HUIWU_JIANLOUWANG = 41,
PVP_HUIWU_LAOMO = 42,
SKILL_LEVEL = 43,
}
get_or_create('cfg.tips')['TipsCode'] = {
NULL = -1,
PLAYER_ALREADY_IN_TEAM = 10,
PLAYER_TEAM_FULL = 11,
MY_TEAM_FULL = 12,
ALREADY_IN_TEAM = 13,
NOT_LEADER = 14,
NOT_IN_TEAM = 15,
TEAM_FULL = 16,
OPERATION_LATER = 17,
INVITE_SUCC = 18,
BAG_FULL_TRANSFER_2_MAIL = 19,
BAG_FULL = 20,
MAKE_TEAM_SUCC = 21,
JOIN_TEAM = 22,
OFFLINE = 23,
DEPOT_FULL = 24,
TEAM_MEMBER_ALREADY_APPLY = 25,
EXCEED_LIMIT = 26,
MODULE_IS_LOCK = 27,
HUIWU_CHAMPINION_QINGYUN = 28,
HUIWU_CHAMPINION_TIANYIN = 29,
HUIWU_CHAMPINION_GUIWANG = 30,
COMBATPOWER_CHAMPION_LOGIN = 31,
LEVEL_CHAMPION_LOGIN = 32,
TALISMAN_CHAMPION_LOGIN = 33,
PET_CHAMPION_LOGIN = 34,
XUNIBI_CHAMPION_LOGIN = 35,
FLOWER_CHAMPION_LOGIN = 36,
FAMILY_CHAMPION_LOGIN = 37,
ANNEAL_UP_RED_BROADCAST = 38,
ANNEAL_UP_ORANGE_BROADCAST = 39,
PERFUSE_UP_RED_BROADCAST = 40,
PERFUSE_UP_ORANGE_BROADCAST = 41,
MYSTERY_EQUIP_BROADCAST = 42,
RECRUIT_RED_BROADCAST = 43,
RECRUIT_ORANGE_BROADCAST = 44,
STAR_UP_RED_BROADCAST = 45,
STAR_UP_ORANGE_BROADCAST = 46,
AWAKE_RED_BROADCAST = 47,
AWAKE_ORANGE_BROADCAST = 48,
LEVEL_UP_BROADCAST = 49,
IDOL_TITLE_BROADCAST = 50,
LOGIN_FORBIDEN = 51,
ONLINE_ROLE_FULL = 52,
BECOME_IDOL_PROTECTOR = 53,
IDOL_PROTECTOR_LOGIN = 54,
SOMEONE_INVITE_JOIN_TEAM = 55,
SOMEONE_JOIN_TEAM = 56,
LEAVE_TEAM = 57,
SOMEONE_LEAVE_TEAM = 58,
SOMEONE_INVITE_JOIN_FAMILY = 59,
}
get_or_create('cfg.setting')['FightFlyTextType'] = {
NULL = -1,
SELF = 1,
}
get_or_create('cfg.lottery')['HighLotteryType'] = {
NULL = -1,
Null = 0,
HUOBAN_HIGH_ONE = 1,
HUOBAN_HIGH_TEN = 2,
HUOBAN_HIGH_FREE_ONE = 3,
HUOBAN_HIGH_FREE_TEN = 12,
HUOBAN_LOW_ONE = 4,
HUOBAN_LOW_TEN = 5,
HUOBAN_LOW_FREE = 6,
FABAO_ONE = 7,
FABAO_TEN = 8,
FABAO_FREE_ONE = 9,
FABAO_FREE_TEN = 13,
FABAO_XUNIBI_ONE = 10,
FABAO_XUNIBI_TEN = 11,
}
get_or_create('cfg.talisman')['LuckType'] = {
NULL = -1,
LUCKLVL_1 = 1,
LUCKLVL_2 = 2,
LUCKLVL_3 = 3,
LUCKLVL_4 = 4,
LUCKLVL_5 = 5,
}
get_or_create('cfg.achievement')['AchievementType'] = {
NULL = -1,
TASK = 0,
GROWUP = 1,
LOGIN = 2,
WEALTH = 3,
FIGHT = 4,
EQUIPE = 5,
ECTYPE = 6,
PVP = 7,
OTHER = 8,
SOCIAL = 9,
BOSS = 10,
ARENATITLE = 11,
TEAMFIGHTTITLE = 12,
}
get_or_create('cfg.character')['ApertureColorType'] = {
NULL = -1,
Red = 0,
Yellow = 1,
Blue = 2,
}
get_or_create('cfg.item')['EItemColor'] = {
NULL = -1,
WHITE = 0,
GREEN = 1,
BLUE = 2,
PURPLE = 3,
ORANGE = 4,
RED = 5,
}
get_or_create('cfg.item')['EItemType'] = {
NULL = -1,
COMMON = 1,
MEDICINE = 2,
CURRENCY = 3,
GIFTPACK = 4,
ENHANCE = 5,
OTHER = 6,
QUEST = 7,
FLOWER = 8,
JEWELRY = 9,
FASHION = 10,
RIDING = 11,
EXPITEM = 12,
TITLE = 13,
FRAGMENT = 14,
PETFRAGMENT = 15,
PET = 16,
TALISMAN = 17,
BANGLE = 18,
NECKLACE = 19,
RING = 20,
WEAPON = 21,
CLOTH = 22,
HAT = 23,
SHOE = 24,
SKIN = 25,
}
get_or_create('cfg.role')['GenderType'] = {
NULL = -1,
MALE = 0,
FEMALE = 1,
}
get_or_create('cfg.ui')['BackgroundType'] = {
NULL = -1,
None = 0,
LeftRight = 1,
Center = 2,
}
get_or_create('cfg.ectype')['AttackCityStage'] = {
NULL = -1,
CLOSED = 0,
SIGNUP = 1,
OPEN = 2,
}
get_or_create('cfg.role')['NamePosition'] = {
NULL = -1,
FROND = 0,
MIDDLE = 1,
END = 2,
}
get_or_create('cfg.map')['PivotPos'] = {
NULL = -1,
CENTER = 0,
LEFTBOTTOM = 1,
}
get_or_create('cfg.exchange')['OrderByType'] = {
NULL = -1,
LEVEL = 0,
APRICE = 1,
TOTAL_PRICE = 2,
TYPE_NUM = 3,
}
get_or_create('cfg.bonus')['SignType'] = {
NULL = -1,
NORMAL_SIGN = 0,
ADD_SIGN = 1,
}
get_or_create('cfg.family')['FamilyJobEnum'] = {
NULL = -1,
MEMBER = 0,
CHIEF = 1,
VICE_CHIEF = 2,
QING_LONG_SHI = 3,
BAI_HU_SHI = 4,
ZUO_HU_FA = 5,
YOU_HU_FA = 6,
XUAN_WU_SHI = 7,
ZHU_QUE_SHI = 8,
}
get_or_create('cfg.ui')['DlgLimitMode'] = {
NULL = -1,
ModeNone = 0,
ModeAllow = 1,
ModeForbid = 2,
ModeAll = 3,
}
get_or_create('cfg.ai')['HostilityType'] = {
NULL = -1,
FIX = 0,
DYNAMIC = 1,
}
get_or_create('cfg.fight')['AttrId'] = {
NULL = -1,
HP_VALUE = 0,
MP_VALUE = 1,
EXCELLENT_RATE = 2,
EXCELLENT_RESIST_RATE = 3,
EXCELLENT_VALUE = 4,
EXCELLENT_RESIST_VALUE = 5,
CRIT_RATE = 6,
CRIT_RESIST_RATE = 7,
CRIT_VALUE = 8,
CRIT_RESIST_VALUE = 9,
ATTACK_MULTI_RATE = 10,
DEFENCE_MUTLI_RATE = 11,
ABNORMAL_RESIST_RATE = 12,
ABNORMAL_HIT_RATE = 13,
HP_HEAL_RATE = 14,
LUCKY_VALUE = 15,
HP_FULL_VALUE = 16,
MP_FULL_VALUE = 17,
ATTACK_VALUE_MIN = 18,
ATTACK_VALUE_MAX = 19,
DEFENCE = 20,
MOVE_SPEED = 21,
HIT_RATE = 22,
HIT_RESIST_RATE = 23,
ADDITIONAL_DAMAGE = 24,
DAMAGE_TO_HUMAN = 25,
DAMAGE_TO_PET = 26,
RESIST_DAMAGE_FROM_HUMAN = 27,
RESIST_DAMAGE_FROM_PET = 28,
MODEL_SCALE = 29,
BASE_ATTR_END = 30,
EXT_ATTR_START = 64,
HP_VALUE_PERCENT = 64,
MP_VALUE_PERCENT = 65,
HP_FULL_VALUE_PERCENT = 66,
MP_FULL_VALUE_PERCENT = 67,
ATTACK_VALUE_MIN_PERCENT = 68,
ATTACK_VALUE_MAX_PERCENT = 69,
DEFENCE_PERCENT = 70,
ATTACK_VALUE = 71,
ATTACK_VALUE_PERCENT = 72,
HIT_RATE_PERCENT = 73,
HIT_RESIST_RATE_PERCENT = 74,
MOVE_SPEED_PERCENT = 75,
HP_FULL_VALUE_PERCENT_2 = 76,
MP_FULL_VALUE_PERCENT_2 = 77,
ATTACK_VALUE_MIN_PERCENT_2 = 78,
ATTACK_VALUE_MAX_PERCENT_2 = 79,
DEFENCE_PERCENT_2 = 80,
ATTACK_VALUE_PERCENT_2 = 81,
HIT_RATE_PERCENT_2 = 82,
HIT_RESIST_RATE_PERCENT_2 = 83,
MOVE_SPEED_PERCENT_2 = 84,
ALL_BASIC_ATTR_PERCENT = 85,
EXT_ATTR_END = 86,
HP_DEC = 87,
MP_DEC = 88,
HP_HEAL = 89,
CALC_ATTACK_MULTI_RATE = 90,
CALC_ATTR_NUM = 91,
}
get_or_create('cfg.task')['EPlayingCGType'] = {
NULL = -1,
WHEN_ACCEPTING = 1,
WHEN_UNCOMMITTED = 2,
WHEN_COMPLETING = 3,
}
get_or_create('cfg.tips')['LocationType'] = {
NULL = -1,
CENTER = 0,
RIGHT_DOWN = 1,
CENTER_SCROLL = 2,
ALERT = 3,
}
get_or_create('cfg.ectype')['TeamFightStage'] = {
NULL = -1,
OPEN = 1,
CLOSED = 2,
}
get_or_create('cfg.exchange')['SortOrder'] = {
NULL = -1,
ASC = 0,
DESC = 1,
}
get_or_create('cfg.pay')['GrowPlanType'] = {
NULL = -1,
FIRSTTYPE = 1,
SECONDTYPE = 2,
THIRDTYPE = 3,
}
get_or_create('cfg.ectype')['EctypeState'] = {
NULL = -1,
PREPARE = 0,
START = 1,
}
get_or_create('cfg.plot')['PlotAudioType'] = {
NULL = -1,
music = 0,
sound = 1,
voice = 2,
}
get_or_create('cfg.ectype')['VedioControlMode'] = {
NULL = -1,
FULL = 0,
MINIMAL = 1,
CANCELONINPUT = 2,
HIDDEN = 3,
}
get_or_create('cfg')['Const'] = {
NULL = -1,
FALSE = 0,
TRUE = 1,
}
get_or_create('cfg.mail')['SenderType'] = {
NULL = -1,
SYSTEM = 0,
FAMILY = 1,
}
get_or_create('cfg.ectype')['TargetType'] = {
NULL = -1,
SELF = 0,
BASE = 1,
}
get_or_create('cfg.task')['EDialoguePosType'] = {
NULL = -1,
LEFT = 0,
RIGHT = 1,
}
get_or_create('cfg.setting')['CharacterEffectType'] = {
NULL = -1,
SELF = 1,
PLAYER = 2,
MONSTER = 3,
}
get_or_create('cfg.fight')['PKState'] = {
NULL = -1,
PEACE = 1,
FAMILY_AND_TEAM = 2,
TEAM = 3,
}
get_or_create('cfg.pet')['StarKarmaType'] = {
NULL = -1,
XINGJIE = 1,
JUEXING = 2,
}
get_or_create('cfg.buff')['EffectPosition'] = {
NULL = -1,
HEAD = 0,
BODY = 1,
FOOT = 2,
}
get_or_create('cfg.plot')['PlayMode'] = {
NULL = -1,
Cannot = 0,
Normal = 1,
AfterChangeMap = 2,
IndexPlay = 3,
}
get_or_create('cfg.friend')['BasicRelationshipType'] = {
NULL = -1,
Friend = 1,
Enemy = 2,
}
get_or_create('cfg.fight')['DisplayType'] = {
NULL = -1,
NORMAL = 1,
ROUND = 2,
PERCENT = 3,
}
get_or_create('cfg.huiwu')['RoundStage'] = {
NULL = -1,
ROUND_PREPARE = 0,
ROUND_FIGHT = 1,
ROUND_REST = 2,
}
get_or_create('cfg.role')['TitleShowType'] = {
NULL = -1,
Text = 1,
Sprite = 2,
Texture = 3,
Prefab = 4,
}
get_or_create('cfg.plot')['EPlotDialogType'] = {
NULL = -1,
DialogBox = 0,
MovieStyle = 1,
CenterAside = 2,
}
get_or_create('cfg.bonus')['DropState'] = {
NULL = -1,
SHOW = 0,
PROTECT = 1,
UNPROTECT = 2,
}
get_or_create('cfg.role')['BroadcastGroup'] = {
NULL = -1,
ChangeAttrs = 0,
Move = 1,
ChangeEffect = 2,
SkillAttack = 3,
SkillPerform = 4,
}
get_or_create('cfg.skill')['ActionSourceType'] = {
NULL = -1,
SelfModel = 0,
Template = 1,
OtherModel = 2,
}
get_or_create('cfg.plot')['PlotModelType'] = {
NULL = -1,
character = 0,
effect = 1,
item = 2,
}
get_or_create('cfg.ectype')['EvaluateCondition'] = {
NULL = -1,
MAX_KILL = 1,
MAX_CONTINUE_KILL = 2,
MAX_DAMAGE_KILL_CONTINUE_KILL = 4,
MAX_DAMAGE_MIN_KILL = 5,
MIN_DAMAGE_MAX_KILL = 6,
MVP = 3,
}
get_or_create('cfg.skill')['ActionType'] = {
NULL = -1,
IMMEDIATELY = 0,
FLY = 1,
CALLBACK = 2,
KEEPCALL = 3,
BOMB = 4,
RAY = 5,
QTE = 6,
SPRINT = 7,
TALISMAN = 8,
}
get_or_create('cfg.ui')['UIMainAreaType'] = {
NULL = -1,
HEAD = 0,
FUNCTION = 1,
SKILL = 2,
CHAT = 3,
JOYSTICK = 4,
TASK = 5,
}
get_or_create('cfg.huiwu')['BattleState'] = {
NULL = -1,
WIN = 0,
LOSE = 1,
}
get_or_create('cfg.achievement')['Status'] = {
NULL = -1,
NOTCOMPLETED = 0,
COMPLETED = 1,
GETREWARD = 2,
}
get_or_create('cfg.skill')['SkillType'] = {
NULL = -1,
ACTIVE = 1,
PASSIVE = 2,
}
get_or_create('cfg.bonus')['UpdateType'] = {
NULL = -1,
ORDER = 0,
SWAP = 1,
}
get_or_create('cfg.fight')['AbilityType'] = {
NULL = -1,
NORMAL_ATTACK = 0,
SKILL_ATTACK = 1,
MOVE = 2,
BEATTACKED = 3,
AI = 4,
BEHEAL = 5,
NOT_IMMUNE_DEBUFF = 6,
}
get_or_create('cfg.plot')['CutsceneGroupMode'] = {
NULL = -1,
Single = 0,
Parallel = 1,
Sequence = 2,
}
get_or_create('cfg.ui')['FunctionList'] = {
NULL = -1,
NONE = 0,
BAG = 1,
WELFARE = 2,
SHOP = 3,
MOUNT = 4,
FASHION = 5,
TITLE = 6,
MAIL = 7,
ACHIEVEMENT = 8,
CORNUCOPIA = 9,
ECTYPE = 10,
ACTIVITY = 11,
FAMILY = 12,
SKILL = 13,
ROLE = 14,
BATTLE = 15,
PRAY = 16,
EXCHANGE = 17,
HEAD = 18,
SHIELDUI = 19,
MOUNTSHORTCUT = 20,
PARTNER = 21,
TFBOYS = 22,
FRIEND = 23,
RANKLIST = 24,
PHARMACY = 25,
WAREHOUSE = 26,
MAIMAI = 27,
SPACE = 28,
SETTING = 29,
DAILYEXTRAEXP = 30,
PLUSSIGN = 31,
LIVENESS = 32,
VIPLEVEL = 33,
IDOL = 34,
}
get_or_create('cfg.ectype')['StarCondition'] = {
NULL = -1,
CLEAR = 1,
DEAD_TIMES_LOWER_THAN = 2,
CLEAR_IN_SECONDS = 3,
}
get_or_create('cfg.ui')['FunctionOpenType'] = {
NULL = -1,
UNLOCK = 0,
APPEAR = 1,
}
get_or_create('cfg.fight')['CampType'] = {
NULL = -1,
ENEMY = 0,
PLAYER = 1,
PLAYER_RED = 2,
PLAYER_BLUE = 3,
PLAYER1 = 4,
PLAYER2 = 5,
MINETOWER = 6,
GOODMAN = 7,
}
get_or_create('cfg.equip')['GetDressFunction'] = {
NULL = -1,
MONEY = 0,
ACTIVITY = 1,
PAGE = 2,
}
get_or_create('cfg.bonus')['ChargeBonusType'] = {
NULL = -1,
EVERYDAY_CHARGE = 1,
TOTAL_CHARGE = 2,
TOTAL_CONSUME = 3,
}
get_or_create('cfg.active')['GrowupType'] = {
NULL = -1,
UPGRADE = 1,
MATERIAL = 2,
RICHER = 3,
PET = 4,
TALISMAN = 5,
EQUIP = 6,
}
get_or_create('cfg.role')['EProfessionType'] = {
NULL = -1,
QINGYUNMEN = 1,
TIANYINSI = 2,
GUIWANGZONG = 3,
HEHUANPAI = 4,
}
get_or_create('cfg.fight')['Relation'] = {
NULL = -1,
ENEMY = 0,
FRIEND = 1,
SELF = 2,
}
get_or_create('cfg.item')['EItemMedicineType'] = {
NULL = -1,
HEAL_HP = 1,
HEAL_MP = 2,
HEAL_HP_PERCENT = 3,
}
get_or_create('cfg.operationalactivity')['ConditionType'] = {
NULL = -1,
Upgrade = 1,
Collection = 2,
Charge = 3,
GiftBag = 4,
}
get_or_create('cfg.guide')['Direction'] = {
NULL = -1,
NONE = 0,
UP = 1,
DOWN = 2,
LEFT = 3,
RIGHT = 4,
}
get_or_create('cfg.ui')['DotType'] = {
NULL = -1,
NONE = 0,
ONCE = 1,
ALWAYS = 2,
}
get_or_create('cfg.equip')['RideType'] = {
NULL = -1,
NONE = 0,
WALK = 1,
FLY = 2,
}
get_or_create('cfg.setting')['SceneObjectType'] = {
NULL = -1,
LIGHT = 1,
BUILDING = 2,
STAIRS = 3,
CLOUD = 4,
WATER = 5,
TREE = 6,
GRASS = 7,
STONE = 8,
SFX = 9,
GROUND = 10,
SOUND = 11,
}
get_or_create('cfg.guide')['NoviceModuleList'] = {
NULL = -1,
BAG = 0,
ECTYPE = 1,
ACTIVITY = 2,
FAMILY = 3,
BATTLE = 4,
HEAD = 5,
PARTNER = 6,
DAILYEXTRAEXP = 7,
IDOL = 8,
}
get_or_create('cfg.item')['LevelUpType'] = {
NULL = -1,
ROLE_LEVEL = 1,
PET_LEVEL = 2,
TALISMAN_LEVEL = 3,
}
get_or_create('cfg.active')['EventNum'] = {
NULL = -1,
TOTAL = 0,
COINPOINT = 1,
EXPPOINT = 2,
STORYPOINT = 3,
MANYPOINT = 4,
CLIMBTOWER = 5,
ARENA = 6,
HEROBOOK = 7,
SPECTRUM = 8,
ANNEAL = 9,
PERFUSE = 10,
USEHUOBANDAN = 11,
USEFABAODAN = 12,
HUFUPOINT = 13,
YUPEIPOINT = 14,
HONGMENG = 15,
XUEZHAN = 16,
KILLMONSTER = 17,
PARTY = 18,
PRAY = 19,
FEEDBOSS = 20,
WINHUIWU = 21,
RINGTASK = 22,
TASK = 23,
RECHARGE = 24,
WISH = 25,
FAMILYPROFITEER = 26,
FAMILYSTORE = 27,
TREASUREBOWL = 28,
QIMAIHUIWU = 29,
JIUYIQIANKUNDING = 30,
STORE = 31,
WORLDBOSS = 32,
SHOUYAOGONGCHENG = 33,
SHAKEMONEYTREE = 34,
FIELDBOSS = 35,
RECRUIT = 36,
ARENAREWARD = 37,
SILINGJIANGSHI = 38,
MANHUANGSHOUCHAO = 39,
}
get_or_create('cfg.lottery')['LotterLevel'] = {
NULL = -1,
LOW = 1,
HIGH = 2,
}
get_or_create('cfg.cmd.condition')['ActivityType'] = {
NULL = -1,
XUEZHANQINGYUN = 0,
SHOUYAOGONGCHENG = 1,
HONGMENGZHENGBA = 2,
QIMAIHUIWU = 3,
TIANXIAHUIWU = 4,
}
get_or_create('cfg.ectype')['AttackCityPlayerState'] = {
NULL = -1,
NOT_SIGNUP = 0,
SIGNUP = 1,
}
get_or_create('cfg.dailyactivity')['ActivityTipEnum'] = {
NULL = -1,
TianXiaHuiWu = 0,
HongMengZhengBa_Enroll = 1,
QiMaiHuiWu = 2,
HongMengZhengBa_Countdown = 3,
YaoShouGongCheng = 4,
ManHuangShouChao = 5,
}
get_or_create('cfg.character')['ModelType'] = {
NULL = -1,
Base = 0,
Player = 1,
Monster = 2,
Npc = 3,
Pet = 4,
Dress = 5,
Talisman = 6,
Effect = 7,
Item = 8,
Equip = 9,
Mineral = 10,
Boss = 11,
Riding = 12,
Fabao = 13,
Rune = 14,
CharacterCG = 15,
NoAnimation = 97,
Template = 98,
Other = 99,
}
get_or_create('cfg.monster')['MonsterType'] = {
NULL = -1,
NORMAL = 1,
ELITE = 2,
BOSS = 3,
}
return os