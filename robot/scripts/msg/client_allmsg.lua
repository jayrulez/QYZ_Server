local msg = require 'common.message'
msg.add_type('gnet', {
name='KeepAlive',
id=100,
{ name='code', type='long'},
})
msg.add_type('gnet', {
name='Challenge',
id=101,
{ name='version', type='int'},
{ name='plattype', type='gnet.PlatType'},
{ name='serverid', type='int'},
{ name='serverload', type='gnet.ServerLoad'},
})
msg.add_type('gnet', {
name='ErrorInfo',
id=102,
{ name='errcode', type='gnet.ErrCode'},
{ name='info', type='octets'},
})
msg.add_type('gnet', {
name='Response',
id=103,
{ name='user_identity', type='octets'},
{ name='token', type='octets'},
{ name='plattype', type='gnet.PlatType'},
{ name='deviceid', type='octets'},
{ name='os', type='octets'},
{ name='platform', type='octets'},
})
msg.add_type('gnet', {
name='KeyExchange',
id=104,
{ name='nonce', type='octets'},
{ name='kick_olduser', type='byte'},
})
msg.add_type('gnet', {
name='OnlineAnnounce',
id=105,
{ name='userid', type='long'},
})
msg.add_type('gnet', {
name='DispatchByUserid',
id=106,
{ name='userid', type='long'},
{ name='ptype', type='int'},
{ name='pdata', type='octets'},
})
msg.add_type('gnet', {
name='RequireLoginActivationCode',
id=120,
{ name='err', type='gnet.ActivationCodeErr'},
{ name='localsid', type='int'},
{ name='userid', type='long'},
})
msg.add_type('gnet', {
name='InputLoginActivationCode',
id=121,
{ name='localsid', type='int'},
{ name='userid', type='long'},
{ name='code', type='octets'},
})
msg.add_type('gnet', {
name='ForwardClient',
id=172,
{ name='roleid', type='long'},
{ name='proto', type='gnet.ProtocolData'},
})
msg.add_type('gnet', {
name='ForwardMultiClients',
id=173,
{ name='roleids', type='set', value='long'},
{ name='proto', type='gnet.ProtocolData'},
})
msg.add_type('gnet', {
name='AnnounceGsd',
id=174,
{ name='serverid', type='int'},
{ name='ip', type='string'},
{ name='port', type='int'},
})
msg.add_type('lx.gs.login', {
name='CGetRoleList',
id=6553601,
})
msg.add_type('lx.gs.login', {
name='SGetRoleList',
id=6553602,
{ name='prevloginroleid', type='long'},
{ name='roles', type='list', value='lx.gs.role.msg.RoleBrief'},
{ name='deleteinfo', type='map', key='long', value='long'},
})
msg.add_type('lx.gs.login', {
name='CCreateRole',
id=6553603,
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='gender', type='int'},
})
msg.add_type('lx.gs.login', {
name='SCreateRole',
id=6553604,
{ name='err', type='int'},
{ name='newinfo', type='lx.gs.role.msg.RoleBrief'},
{ name='servertime', type='long'},
})
msg.add_type('lx.gs.login', {
name='CDeleteRole',
id=6553605,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.login', {
name='SDeleteRole',
id=6553606,
OK = 0,
DELETE_TOO_MANY_ROLE = 1,
ERR = 2,
{ name='err', type='int'},
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.login', {
name='CRoleLogin',
id=6553607,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.login', {
name='SRoleLogin',
id=6553608,
OK = 0,
ROLEID_NOT_IN_USER = 1,
ROLEINFO_NOT_FOUND = 2,
USER_NOT_FOUND = 3,
ROLE_LOGIN_IS_FORBIDDEN = 4,
ROLE_NOT_LOGIN = 5,
SESSION_CORRUPT = 6,
TOOMANY_ONLINES_ROLE = 7,
SERVER_LOADAVG_BUSY = 8,
{ name='err', type='int'},
{ name='roledetail', type='lx.gs.login.RoleDetail'},
{ name='servertime', type='long'},
{ name='servertimezone', type='string'},
{ name='isservermerge', type='int'},
{ name='forbiddesc', type='string'},
})
msg.add_type('lx.gs.login', {
name='CRandomName',
id=6553609,
{ name='gender', type='int'},
})
msg.add_type('lx.gs.login', {
name='SRandomName',
id=6553610,
{ name='name', type='string'},
})
msg.add_type('lx.gs.login', {
name='CRoleLogout',
id=6553617,
})
msg.add_type('lx.gs', {
name='Dummy',
id=6553618,
{ name='generalrankinfo', type='lx.gs.rank.msg.GeneralRankInfo'},
{ name='findback', type='lx.gs.dailyactivity.msg.Findbacktype'},
{ name='familyrankinfo', type='lx.gs.rank.msg.FamilyRankInfo'},
{ name='equip', type='lx.gs.equip.Equip'},
{ name='item', type='lx.gs.item.Item'},
{ name='fragment', type='lx.gs.fragment.Fragment'},
{ name='talisman', type='lx.gs.talisman.Talisman'},
{ name='pet', type='lx.gs.pet.Pet'},
})
msg.add_type('lx.gs.friend.msg', {
name='CGetFriendInfo',
id=6553901,
})
msg.add_type('lx.gs.friend.msg', {
name='SGetFriendInfo',
id=6553902,
{ name='myinfo', type='lx.gs.friend.msg.RoleFriendInfo'},
{ name='friendinfo', type='map', key='long', value='lx.gs.friend.msg.FriendInfo'},
{ name='requestinginfo', type='map', key='long', value='lx.gs.friend.msg.RoleShowInfo'},
{ name='blackinfo', type='map', key='long', value='lx.gs.friend.msg.RoleShowInfo'},
{ name='enemyinfo', type='map', key='long', value='lx.gs.friend.msg.EnemyShowInfo'},
{ name='idolcharminfo', type='map', key='long', value='lx.gs.friend.msg.IdolInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='CRequestFriend',
id=6553903,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRequestFriend',
id=6553904,
{ name='friend', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='CAcceptFriend',
id=6553905,
{ name='roleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SAcceptFriend',
id=6553906,
{ name='friendlist', type='list', value='lx.gs.friend.msg.FriendInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='CRejectFriend',
id=6553907,
{ name='roleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRejectFriend',
id=6553908,
{ name='roleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='CDeleteFriend',
id=6553909,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SDeleteFriend',
id=6553910,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='CSearchFriend',
id=6553913,
{ name='searchkey', type='string'},
})
msg.add_type('lx.gs.friend.msg', {
name='SSearchFriend',
id=6553914,
{ name='searchkey', type='string'},
{ name='friendlist', type='list', value='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='CBlackFriend',
id=6553915,
{ name='roleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SBlackFriend',
id=6553916,
{ name='okroleidlist', type='list', value='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='CUnBlackFriend',
id=6553917,
{ name='roleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SUnBlackFriend',
id=6553918,
{ name='okroleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRequestFriendNotify',
id=6553926,
{ name='friend', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SAcceptFriendNotify',
id=6553927,
{ name='friend', type='lx.gs.friend.msg.FriendInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRejectFriendNotify',
id=6553928,
{ name='friend', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SDeleteFriendNotify',
id=6553929,
{ name='friend', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SBlackFriendNotify',
id=6553930,
{ name='friend', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SUnBlackFriendNotify',
id=6553931,
{ name='friend', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.chat.msg', {
name='CChat',
id=6554001,
{ name='channel', type='int'},
{ name='receiver', type='long'},
{ name='text', type='string'},
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='invitechannel', type='int'},
{ name='voice', type='octets'},
{ name='voiceduration', type='float'},
})
msg.add_type('lx.gs.chat.msg', {
name='SPrivateMessage',
id=6554004,
{ name='content', type='lx.gs.chat.msg.Content'},
})
msg.add_type('lx.gs.chat.msg', {
name='STeamMessage',
id=6554005,
{ name='content', type='lx.gs.chat.msg.Content'},
})
msg.add_type('lx.gs.chat.msg', {
name='SFamilyMessage',
id=6554006,
{ name='content', type='lx.gs.chat.msg.Content'},
})
msg.add_type('lx.gs.chat.msg', {
name='SWorldMessage',
id=6554007,
{ name='content', type='lx.gs.chat.msg.Content'},
})
msg.add_type('lx.gs.chat.msg', {
name='SSystemMessage',
id=6554008,
{ name='content', type='octets'},
})
msg.add_type('lx.gs.chat.msg', {
name='CGetVoice',
id=6554009,
{ name='voiceid', type='int'},
})
msg.add_type('lx.gs.chat.msg', {
name='SGetVoice',
id=6554010,
{ name='voiceid', type='int'},
{ name='voice', type='octets'},
})
msg.add_type('lx.gs.chat.msg', {
name='SInviteMsg',
id=6554011,
{ name='roleid', type='long'},
{ name='name', type='string'},
{ name='channel', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='STask',
id=6554300,
{ name='variables', type='map', key='int', value='int'},
{ name='completehistory', type='map', key='int', value='int'},
{ name='acceptedtasks', type='list', value='lx.gs.task.msg.TaskInfo'},
{ name='curfamtasks', type='list', value='lx.gs.task.msg.FamilyTaskInfo'},
{ name='completefamtasknum', type='int'},
{ name='comdaycycle', type='int'},
{ name='comweeksmallcycle', type='int'},
{ name='lastgiveuofamtime', type='long'},
{ name='shownpcs', type='set', value='int'},
{ name='hidemines', type='set', value='int'},
{ name='isuseyuanbaocomtask', type='int'},
{ name='iscanclefamtask', type='int'},
{ name='receivedweekbonus', type='list', value='int'},
{ name='guidebranchtaskid', type='int'},
{ name='allcandobranch', type='list', value='int'},
{ name='unlockcomtasks', type='list', value='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SChangeHistory',
id=6554301,
{ name='changes', type='map', key='int', value='int'},
{ name='removes', type='list', value='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SChangeTask',
id=6554302,
{ name='task', type='lx.gs.task.msg.TaskInfo'},
})
msg.add_type('lx.gs.task.msg', {
name='CAcceptTask',
id=6554304,
{ name='npcid', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SAcceptTask',
id=6554305,
{ name='npcid', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='CCancelTask',
id=6554306,
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SCancelTask',
id=6554307,
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='CCompleteTask',
id=6554308,
{ name='npcid', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SCompleteTask',
id=6554309,
{ name='npcid', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SChangeVariable',
id=6554311,
{ name='changes', type='map', key='int', value='int'},
{ name='removes', type='list', value='int'},
})
msg.add_type('lx.gs.task.msg', {
name='CChooseShowBranchTask',
id=6554312,
{ name='guidebranchtaskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SChooseShowBranchTask',
id=6554313,
{ name='guidebranchtaskid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SCancelFamilyTask',
id=6554315,
{ name='curtaskorder', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.mail.msg', {
name='SMailBox',
id=6554400,
{ name='mails', type='list', value='lx.gs.mail.msg.Mail'},
})
msg.add_type('lx.gs.mail.msg', {
name='SNewMail',
id=6554401,
{ name='mail', type='lx.gs.mail.msg.Mail'},
})
msg.add_type('lx.gs.mail.msg', {
name='CDelMail',
id=6554402,
{ name='delmailids', type='list', value='int'},
})
msg.add_type('lx.gs.mail.msg', {
name='SDelMail',
id=6554403,
{ name='delmailids', type='list', value='int'},
})
msg.add_type('lx.gs.mail.msg', {
name='CObtainMailAccessory',
id=6554405,
{ name='mailids', type='list', value='int'},
})
msg.add_type('lx.gs.mail.msg', {
name='SObtainMailAccessory',
id=6554406,
{ name='mailids', type='list', value='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.mail.msg', {
name='CReadMail',
id=6554408,
{ name='mailid', type='int'},
})
msg.add_type('lx.gs.mail.msg', {
name='SReadMail',
id=6554409,
{ name='mailid', type='int'},
})
msg.add_type('lx.gs.pay', {
name='CGetPay',
id=6555401,
})
msg.add_type('lx.gs.pay', {
name='SGetPay',
id=6555402,
{ name='products', type='list', value='lx.gs.pay.Product'},
{ name='isfirstpayused', type='int'},
{ name='firstpayaward', type='lx.gs.pay.PayAward'},
{ name='dailypayaward', type='lx.gs.pay.PayAward'},
{ name='dailytotalpayaward', type='lx.gs.pay.PayAward'},
})
msg.add_type('lx.gs.pay', {
name='CGetAppOrder',
id=6555403,
{ name='productid', type='int'},
})
msg.add_type('lx.gs.pay', {
name='SGetAppOrder',
id=6555404,
OK = 0,
PRODUCT_NOT_FOUND = 1,
{ name='err', type='int'},
{ name='orderid', type='string'},
{ name='productid', type='int'},
})
msg.add_type('lx.gs.pay', {
name='SPaySuccessNotify',
id=6555405,
{ name='productid', type='int'},
{ name='dailypaystatus', type='int'},
{ name='dailytotalpaystatus', type='int'},
{ name='yuanbao', type='int'},
{ name='bindyuanbao', type='int'},
})
msg.add_type('lx.gs.equip.normalequip', {
name='CSwapEquipProp',
id=6555432,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
{ name='isswapanneal', type='int'},
{ name='isswapperfuse', type='int'},
})
msg.add_type('lx.gs.equip.accessory', {
name='SRestoreAccessoryWashResult',
id=6555711,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.equip.accessory', {
name='SSwapAccessoryProp',
id=6555758,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
})
msg.add_type('lx.gs.equip', {
name='CRecommandEquip',
id=6555764,
{ name='pos', type='int'},
})
msg.add_type('lx.gs.dress', {
name='CGetDressInfo',
id=6555770,
})
msg.add_type('lx.gs.dress', {
name='SGetDressInfo',
id=6555771,
{ name='dresslist', type='list', value='lx.gs.dress.Dress'},
{ name='activedress', type='int'},
})
msg.add_type('lx.gs.dress', {
name='CActiveDress',
id=6555772,
{ name='dresskey', type='int'},
})
msg.add_type('lx.gs.dress', {
name='SActiveDress',
id=6555773,
{ name='dresskey', type='int'},
})
msg.add_type('lx.gs.dress', {
name='SDressGetNotify',
id=6555774,
{ name='dress', type='lx.gs.dress.Dress'},
})
msg.add_type('lx.gs.mount', {
name='CGetRideInfo',
id=6555780,
})
msg.add_type('lx.gs.mount', {
name='SGetRideInfo',
id=6555781,
{ name='rideinfo', type='list', value='lx.gs.mount.Ride'},
{ name='activeride', type='int'},
})
msg.add_type('lx.gs.mount', {
name='CActiveRide',
id=6555782,
{ name='ridekey', type='int'},
})
msg.add_type('lx.gs.mount', {
name='SActiveRide',
id=6555783,
{ name='ridekey', type='int'},
})
msg.add_type('lx.gs.mount', {
name='SRideGetNotify',
id=6555784,
{ name='ride', type='lx.gs.mount.Ride'},
})
msg.add_type('lx.gs.amulet', {
name='SWashAmulet',
id=6555788,
{ name='pageid', type='int'},
{ name='washresult', type='map', key='int', value='lx.gs.amulet.AmuletPropperty'},
})
msg.add_type('lx.gs.fragment', {
name='CCompoundFragment',
id=6555909,
{ name='pos', type='int'},
})
msg.add_type('lx.gs.depot.msg', {
name='CTransferItem',
id=6555956,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.depot.msg', {
name='CSaveGoldCoin',
id=6555980,
{ name='amount', type='long'},
})
msg.add_type('lx.gs.depot.msg', {
name='SSyncGoldCoin',
id=6555981,
{ name='depotgoldcoin', type='long'},
})
msg.add_type('lx.gs.depot.msg', {
name='CTakeGoldCoin',
id=6555982,
{ name='amount', type='long'},
})
msg.add_type('lx.gs.pickcard.msg', {
name='CScoreExchange',
id=6556009,
{ name='exchangetyep', type='int'},
{ name='id', type='int'},
})
msg.add_type('lx.gs.pickcard.msg', {
name='SScoreExchange',
id=6556010,
{ name='exchangetyep', type='int'},
{ name='id', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CNewGift',
id=6556105,
{ name='newgiftid', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SNewGift',
id=6556106,
{ name='newgiftid', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CSign',
id=6556109,
{ name='signtype', type='int'},
{ name='signdate', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SSign',
id=6556110,
{ name='signtype', type='int'},
{ name='signdate', type='int'},
{ name='signgift', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CContinueLoginGift',
id=6556112,
{ name='boxid', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SContinueLoginGift',
id=6556113,
{ name='boxid', type='int'},
{ name='logingift', type='map.msg.Bonus'},
{ name='lefttimes', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CGetOnlineGift',
id=6556116,
{ name='gifttimetype', type='list', value='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SGetOnlineGift',
id=6556117,
{ name='onlinegift', type='map', key='int', value='map.msg.Bonus'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CGetMonthCardGift',
id=6556118,
{ name='date', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SGetMonthCardGift',
id=6556119,
{ name='date', type='int'},
{ name='monthcardgift', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CGetGrowPlanGift',
id=6556120,
{ name='growplantype', type='int'},
{ name='giftindx', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SGetGrwoPlanGift',
id=6556121,
{ name='growplantype', type='int'},
{ name='giftindx', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CGetPayGift',
id=6556124,
{ name='id', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SGetPayGift',
id=6556125,
{ name='id', type='int'},
{ name='paygift', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CStartWish',
id=6556126,
{ name='petid', type='long'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SStartWish',
id=6556127,
{ name='petid', type='long'},
{ name='wishtimes', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CGetWishGift',
id=6556141,
{ name='petid', type='long'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SGetWishGift',
id=6556142,
{ name='wishgift', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.family.msg', {
name='CAppointJob',
id=6556180,
{ name='memberid', type='long'},
{ name='jobid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CFindFamily',
id=6556181,
{ name='familyname', type='string'},
{ name='startindex', type='int'},
})
msg.add_type('lx.gs', {
name='SError',
id=6556200,
OK = 0,
{ name='errcode', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='SCurrencyChange',
id=6556202,
{ name='currencys', type='map', key='int', value='long'},
})
msg.add_type('lx.gs.role.msg', {
name='SVipExpChange',
id=6556203,
{ name='exp', type='long'},
{ name='level', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='CGetRoleInfo',
id=6556204,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.role.msg', {
name='SGetRoleInfo',
id=6556205,
{ name='roleid', type='long'},
{ name='roleinfo', type='lx.gs.role.msg.RoleShowInfo5'},
})
msg.add_type('lx.gs.role.msg', {
name='SUseCode',
id=6556206,
{ name='retcode', type='int'},
{ name='code', type='string'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.limit.msg', {
name='SLimit',
id=6556300,
{ name='limits', type='list', value='lx.gs.limit.msg.Limit'},
{ name='cooldowns', type='list', value='lx.gs.limit.msg.CoolDown'},
})
msg.add_type('lx.gs.limit.msg', {
name='SLimitChange',
id=6556301,
{ name='changelimits', type='list', value='lx.gs.limit.msg.Limit'},
{ name='removelimits', type='list', value='long'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='CGetActiveInfo',
id=6556431,
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='SGetActiveInfo',
id=6556432,
{ name='scores', type='int'},
{ name='activetimes', type='map', key='int', value='int'},
{ name='receivedbonus', type='list', value='int'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='CGetActiveBonus',
id=6556433,
{ name='bonustype', type='int'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='SGetActiveBonus',
id=6556434,
{ name='bonustype', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='CGetUndoActive',
id=6556435,
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='SGetUndoActive',
id=6556436,
{ name='undoactive', type='lx.gs.dailyactivity.msg.Undoactiveinfos'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='CFindBack',
id=6556437,
{ name='findtype', type='int'},
{ name='eventtype', type='int'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='SFindBack',
id=6556438,
{ name='findtype', type='int'},
{ name='eventtype', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SAcceptMM',
id=6556581,
{ name='result', type='int'},
{ name='mmtype', type='int'},
{ name='mmroleinfo', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.notice.msg', {
name='SNotice',
id=6556600,
{ name='notices', type='list', value='lx.gs.notice.msg.Notice'},
})
msg.add_type('lx.gs.exchange.msg', {
name='SInfo',
id=6557601,
{ name='items', type='list', value='lx.gs.exchange.msg.ExchangeItem'},
{ name='logs', type='list', value='lx.gs.exchange.msg.ExchangeLog'},
})
msg.add_type('lx.gs.exchange.msg', {
name='CAddItem',
id=6557602,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='num', type='int'},
{ name='aprice', type='int'},
})
msg.add_type('lx.gs.exchange.msg', {
name='SAddItem',
id=6557603,
{ name='item', type='lx.gs.exchange.msg.ExchangeItem'},
})
msg.add_type('lx.gs.exchange.msg', {
name='CDelItem',
id=6557604,
{ name='exchangeid', type='long'},
})
msg.add_type('lx.gs.exchange.msg', {
name='SDelItem',
id=6557605,
{ name='exchangeid', type='long'},
})
msg.add_type('lx.gs.exchange.msg', {
name='SNewLog',
id=6557609,
{ name='log', type='lx.gs.exchange.msg.ExchangeLog'},
})
msg.add_type('lx.gs.exchange.msg', {
name='CQuery',
id=6557610,
{ name='name', type='string'},
{ name='category', type='int'},
{ name='orderby', type='int'},
{ name='sortorder', type='int'},
{ name='startindex', type='int'},
{ name='endindex', type='int'},
})
msg.add_type('lx.gs.exchange.msg', {
name='SQuery',
id=6557611,
{ name='name', type='string'},
{ name='category', type='int'},
{ name='orderby', type='int'},
{ name='sortorder', type='int'},
{ name='startindex', type='int'},
{ name='queryresult', type='lx.gs.exchange.msg.QueryResult'},
})
msg.add_type('lx.gs.exchange.msg', {
name='SBuyByOther',
id=6557612,
{ name='exchangeid', type='long'},
{ name='remainnum', type='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='SInfo',
id=6557701,
{ name='skills', type='list', value='lx.gs.skill.msg.SkillInfo'},
{ name='equipskillpositions', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='CChangeEquipActiveSkill',
id=6557702,
{ name='equipskillpositions', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='SChangeEquipActiveSkill',
id=6557703,
{ name='equipskillpositions', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='CUpgradeSkill',
id=6557704,
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='SUpgradeSkill',
id=6557705,
{ name='skillid', type='int'},
{ name='newlevel', type='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='CEvolveSkill',
id=6557706,
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.skill.msg', {
name='SEvolveSkill',
id=6557707,
{ name='oldskillid', type='int'},
{ name='newskillid', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='SInitTeam',
id=6557806,
{ name='roleteaminfo', type='lx.gs.team.msg.RoleTeamInfo'},
{ name='team', type='lx.gs.team.msg.Team'},
})
msg.add_type('lx.gs.team.msg', {
name='CCreateTeam',
id=6557807,
})
msg.add_type('lx.gs.team.msg', {
name='SSyncTeam',
id=6557808,
{ name='team', type='lx.gs.team.msg.Team'},
})
msg.add_type('lx.gs.team.msg', {
name='CInviteJoinTeam',
id=6557809,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SInviteJoinTeam',
id=6557810,
{ name='roleid', type='long'},
{ name='rolename', type='string'},
})
msg.add_type('lx.gs.team.msg', {
name='CBreakupTeam',
id=6557812,
})
msg.add_type('lx.gs.team.msg', {
name='SBreakupTeam',
id=6557813,
})
msg.add_type('lx.gs.team.msg', {
name='CAcceptInvite',
id=6557815,
{ name='inviteroleid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='CTransferLeader',
id=6557827,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='STransferLeader',
id=6557829,
{ name='newleaderid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='CRequestJoinTeam',
id=6557830,
{ name='teamid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SRequestJoinTeam',
id=6557832,
{ name='requestrole', type='lx.gs.role.msg.RoleShowInfo4'},
})
msg.add_type('lx.gs.team.msg', {
name='CKickoutMember',
id=6557833,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SKickoutMember',
id=6557834,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='CQuitTeam',
id=6557836,
})
msg.add_type('lx.gs.team.msg', {
name='SQuitTeam',
id=6557837,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='CInviteFollow',
id=6557839,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SInviteFollow',
id=6557840,
RES_OK = 0,
{ name='result', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='SInviteFollowNotify',
id=6557841,
{ name='leader', type='lx.gs.team.msg.TeamMember'},
})
msg.add_type('lx.gs.team.msg', {
name='CFollowLeader',
id=6557842,
{ name='teamid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SFollowLeader',
id=6557843,
RES_OK = 0,
{ name='result', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='CUnFollowTeamMember',
id=6557844,
{ name='unfollowmemberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SUnFollowTeamMember',
id=6557845,
RES_OK = 0,
{ name='result', type='int'},
{ name='unfollowmemberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SUnFollowTeamMemberNotify',
id=6557846,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='CSetAutoSetting',
id=6557847,
AUTO_ACCEPT_INVITE = 1,
AUTO_ACCEPT_REQUEST = 2,
{ name='opttype', type='int'},
{ name='cfgvalue', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='SSetAutoSetting',
id=6557848,
{ name='opttype', type='int'},
{ name='cfgvalue', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='SJoinTeamNotify',
id=6557849,
{ name='member', type='lx.gs.team.msg.TeamMember'},
})
msg.add_type('lx.gs.team.msg', {
name='CUnFollowLeader',
id=6557850,
{ name='teamid', type='long'},
})
msg.add_type('lx.gs.team.msg', {
name='SUnFollowLeader',
id=6557851,
{ name='teamid', type='long'},
})
msg.add_type('lx.gs.role.title.msg', {
name='CGetTitleInfo',
id=6557901,
})
msg.add_type('lx.gs.role.title.msg', {
name='SGetTitleInfo',
id=6557902,
{ name='titleinfo', type='lx.gs.role.title.msg.RoleTitle'},
})
msg.add_type('lx.gs.role.title.msg', {
name='CActiveTitle',
id=6557903,
{ name='titlekey', type='int'},
{ name='titletype', type='int'},
})
msg.add_type('lx.gs.role.title.msg', {
name='SActiveTitle',
id=6557904,
{ name='titlekey', type='int'},
{ name='titletype', type='int'},
})
msg.add_type('lx.gs.role.title.msg', {
name='STitleGetNotify',
id=6557905,
{ name='title', type='lx.gs.role.title.msg.Title'},
})
msg.add_type('lx.gs.role.title.msg', {
name='STitleTimeOutNotify',
id=6557906,
{ name='title', type='lx.gs.role.title.msg.Title'},
})
msg.add_type('lx.gs.role.title.msg', {
name='CDeActiveTitle',
id=6557907,
{ name='titlekey', type='int'},
{ name='titletype', type='int'},
})
msg.add_type('lx.gs.role.title.msg', {
name='SDeActiveTitle',
id=6557908,
{ name='titlekey', type='int'},
{ name='titletype', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CFamilyPray',
id=6558601,
TYPE_COMMON = 1,
TYPE_ADVANCE = 2,
TYPE_SUPER = 3,
{ name='burntype', type='int'},
})
msg.add_type('lx.gs.rank.msg', {
name='CGetReward',
id=6558621,
{ name='ranktype', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CWashPet',
id=6558901,
{ name='modelid', type='int'},
{ name='washid', type='int'},
{ name='isten', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SHeroGainAward',
id=6559601,
})
msg.add_type('lx.gs.map.msg', {
name='SGuardTowerMatchStart',
id=6563642,
})
msg.add_type('lx.gs.map.msg', {
name='SStartEnrollMultiStoryNotify',
id=6563658,
{ name='ectypeid', type='int'},
{ name='roleinfos', type='list', value='lx.gs.map.msg.EnrollBriefInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SAcceptMMNotify',
id=6563788,
{ name='mmtype', type='int'},
{ name='mmroleinfo', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.task.msg', {
name='CClearFamTask',
id=6563815,
})
msg.add_type('lx.gs.task.msg', {
name='SGetFamilyTask',
id=6563845,
{ name='npcid', type='int'},
{ name='taskinfo', type='lx.gs.task.msg.FamilyTaskInfo'},
})
msg.add_type('lx.gs.achievement.msg', {
name='SEvolveTitle',
id=6563853,
{ name='titleid', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='CChangeName',
id=6563907,
{ name='newname', type='string'},
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='SGetWorldBossList',
id=6563913,
{ name='bosses', type='list', value='lx.gs.activity.worldboss.msg.WorldBossInfo'},
})
msg.add_type('lx.gs.task.msg', {
name='CCompleteFamTaskWithYuanbao',
id=6563919,
{ name='curtaskorder', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyInfo',
id=6563928,
})
msg.add_type('lx.gs.friend.msg', {
name='SRequestMMNotify',
id=6563938,
{ name='reqmmtype', type='int'},
{ name='mmtype', type='int'},
{ name='mmroleinfo', type='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='SSetMMAuthorization',
id=6563979,
{ name='result', type='int'},
{ name='allowfriendgetmm', type='int'},
{ name='allowstrangergetmm', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyMemberInfo',
id=6564135,
{ name='memberid', type='long'},
{ name='baseinfo', type='lx.gs.family.msg.FamilyMember'},
{ name='publicinfo', type='lx.gs.role.msg.RoleShowInfo3'},
})
msg.add_type('lx.gs.map.msg', {
name='COpenDailyEctype',
id=6564142,
{ name='ectypetype', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SStudyFamilySkill',
id=6564204,
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SInviteJoinFamily',
id=6564212,
{ name='beinviteroieid', type='long'},
})
msg.add_type('lx.gs.jade', {
name='CUnloadJewelry',
id=6564221,
{ name='position', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='STransferChiefNotify',
id=6564232,
{ name='operator', type='lx.gs.family.msg.FamilyMember'},
{ name='member', type='lx.gs.family.msg.FamilyMember'},
{ name='jobid', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncPetCombatPower',
id=6564248,
{ name='modelid', type='int'},
{ name='combatpower', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SActivePet',
id=6564339,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='SCurrencyAlert',
id=6564408,
{ name='currencys', type='list', value='lx.gs.role.msg.CurrencyAddRecord'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CShakeMoneyTree',
id=6564422,
})
msg.add_type('lx.gs.map.msg', {
name='SHeroChangeEctype',
id=6564566,
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SAppointJobNotify',
id=6564581,
{ name='operator', type='lx.gs.family.msg.FamilyMember'},
{ name='member', type='lx.gs.family.msg.FamilyMember'},
{ name='jobid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SRejectRequestJoinFNotify',
id=6564607,
{ name='family', type='lx.gs.family.msg.FamilyBasicInfo'},
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.jade', {
name='CEvolveJade',
id=6564658,
})
msg.add_type('lx.gs.jade', {
name='CEnhanceJewelry',
id=6564739,
{ name='position', type='int'},
{ name='index', type='int'},
{ name='doglist', type='list', value='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='SSyncItems',
id=6564788,
{ name='bagtype', type='int'},
{ name='iteminfo', type='map', key='int', value='octets'},
})
msg.add_type('lx.gs.activity.msg', {
name='SReceiveActivityBonus',
id=6564843,
{ name='id', type='int'},
{ name='cmdid', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='SDayOver',
id=6564896,
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyRequestingInfo',
id=6564905,
{ name='requestinglist', type='map', key='long', value='lx.gs.role.msg.RoleShowInfo4'},
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyActivityInfo',
id=6564943,
})
msg.add_type('lx.gs.map.msg', {
name='SChangeTeamFight',
id=6565010,
{ name='info', type='lx.gs.map.msg.TeamFightInfo'},
})
msg.add_type('lx.gs.pickcard.msg', {
name='CPickCardByType',
id=6565020,
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
{ name='picktype', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='CUnlockGrid',
id=6565022,
{ name='bagtype', type='int'},
{ name='unlocknum', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='CDeleteMM',
id=6565051,
{ name='roleid', type='long'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CEnrollAttackCity',
id=6565130,
})
msg.add_type('lx.gs.friend.msg', {
name='SIdolGuardNotify',
id=6565154,
{ name='idolid', type='long'},
{ name='oldguardid', type='long'},
{ name='guardid', type='long'},
{ name='guardname', type='string'},
{ name='guardtime', type='long'},
})
msg.add_type('lx.gs.chat.msg', {
name='CReportPlayer',
id=6565161,
{ name='bereportid', type='long'},
})
msg.add_type('lx.gs.jade', {
name='CSummonRole',
id=6565169,
{ name='role', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CActivePet',
id=6565173,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='SSort',
id=6565195,
{ name='bagtype', type='int'},
{ name='itempos', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SFriendDegreeNotify',
id=6565375,
{ name='notifytype', type='int'},
{ name='roleid', type='long'},
{ name='frienddegree', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGuardTowerMatchCancel',
id=6565408,
{ name='retcode', type='int'},
})
msg.add_type('lx.gs.rank.msg', {
name='SGetRank',
id=6565446,
{ name='ranktype', type='int'},
{ name='ranksize', type='int'},
{ name='rankstart', type='int'},
{ name='data', type='octets'},
{ name='mycurrank', type='int'},
})
msg.add_type('lx.gs.arena.msg', {
name='SObtainDailySuccReward',
id=6565581,
{ name='rewardid', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRequestMM',
id=6565693,
{ name='result', type='int'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SBuyCaili',
id=6565696,
{ name='cailitypeid', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CUpgradePetAwake',
id=6565703,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CUpLevelMall',
id=6565923,
})
msg.add_type('lx.gs.marriage.msg', {
name='SDivorceWithBook',
id=6565978,
{ name='bedivorceroleid', type='long'},
{ name='bedivorcerolename', type='string'},
})
msg.add_type('lx.gs.family.msg', {
name='CRaiseGodAnimal',
id=6565983,
RAISE_TYPE_XUNIBI = 1,
RAISE_TYPE_YUANBAO = 2,
{ name='raisetype', type='int'},
{ name='animalid', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CUpgradePetStar',
id=6566001,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='CUnLockAmulet',
id=6566035,
{ name='pageid', type='int'},
{ name='amuletid', type='int'},
})
msg.add_type('lx.gs.treasurebowl', {
name='CTreasureBowlBreak',
id=6566059,
{ name='poslist', type='list', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CCancelMatchTeamFight',
id=6566083,
})
msg.add_type('lx.gs.storynote.msg', {
name='CActiveNote',
id=6566129,
{ name='chapterid', type='int'},
{ name='noteid', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='SSyncCapacity',
id=6566173,
{ name='bagtype', type='int'},
{ name='capacity', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='SLockAmulet',
id=6566193,
{ name='pageid', type='int'},
{ name='amuletid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SCompleteFamilyTask',
id=6566295,
{ name='curtaskorder', type='int'},
{ name='taskid', type='int'},
{ name='comdaycycle', type='int'},
{ name='completefamtasknum', type='int'},
{ name='comweeksmallcycle', type='int'},
{ name='bonus', type='map.msg.Bonus'},
{ name='nextfamtask', type='lx.gs.task.msg.FamilyTaskInfo'},
})
msg.add_type('lx.gs.pet.msg', {
name='CUnActivePet',
id=6566302,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='SAmuletPageOpenNotify',
id=6566318,
{ name='pageid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SLeaveTeamSpeed',
id=6566319,
})
msg.add_type('lx.gs.limit.msg', {
name='SCoolDownChange',
id=6566336,
{ name='id', type='long'},
{ name='expiretime', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyMembersInfo',
id=6566349,
{ name='jobinfo', type='map', key='int', value='lx.gs.family.msg.FamilyJobStaffList'},
{ name='members', type='map', key='long', value='lx.gs.family.msg.FamilyMember'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CBuyCaili',
id=6566522,
{ name='cailitypeid', type='int'},
})
msg.add_type('lx.gs.achievement.msg', {
name='CEvolveTitle',
id=6566528,
{ name='titleid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='COpenPersonalBossEctype',
id=6566531,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CTransferChief',
id=6566577,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SStudyFamilySkillNotify',
id=6566649,
{ name='skillid', type='int'},
{ name='skill', type='lx.gs.family.msg.FamilySkill'},
})
msg.add_type('lx.gs.map.msg', {
name='CBeginMatchTeamFight',
id=6566806,
})
msg.add_type('lx.gs.jade', {
name='SJadeUnLockNotify',
id=6566822,
{ name='jade', type='lx.gs.jade.Jade'},
{ name='holenum', type='int'},
})
msg.add_type('lx.gs.pay', {
name='SBuyMonthCardNotify',
id=6566884,
{ name='buymonthcardtime', type='int'},
{ name='monthcardleftdays', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='COpenFamilyTeamEctype',
id=6566922,
})
msg.add_type('lx.gs.family.msg', {
name='SFamilyPray',
id=6566927,
{ name='burntype', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CGetChampion',
id=6567049,
{ name='termid', type='int'},
{ name='profession', type='int'},
{ name='ctx', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CLeaveTeamAndEnrollSingle',
id=6567076,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='CWuxingWash',
id=6567167,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CSweepChapterAllStoryEctype',
id=6567169,
{ name='chapterid', type='int'},
})
msg.add_type('lx.gs.rank.msg', {
name='SInfo',
id=6567203,
{ name='ranks', type='list', value='lx.gs.rank.msg.RankInfo'},
})
msg.add_type('lx.gs.talisman', {
name='SWuxingWash',
id=6567296,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='criticaltimes', type='int'},
{ name='wuxingvalue', type='int'},
{ name='washtimes', type='int'},
})
msg.add_type('lx.gs.equip.accessory', {
name='CRestoreAccessoryWashResult',
id=6567548,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SPartyEndNotify',
id=6567599,
})
msg.add_type('lx.gs.task.msg', {
name='COpenTaskEctype',
id=6567638,
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.arena.msg', {
name='CChallenge',
id=6567929,
{ name='rank', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='SAddNormalExp',
id=6567985,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='newlevel', type='int'},
{ name='newexp', type='long'},
})
msg.add_type('lx.gs.equip.accessory', {
name='SApplyAccessoryWashResult',
id=6567986,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='oldpropindex', type='int'},
{ name='newprop', type='lx.gs.equip.PropInfo'},
})
msg.add_type('lx.gs.arena.msg', {
name='CRefreshChallenge',
id=6568007,
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyWelfareInfo',
id=6568019,
})
msg.add_type('lx.gs.map.msg', {
name='CSetPKState',
id=6568130,
{ name='worldid', type='int'},
{ name='pkstate', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='STalismanRecycle',
id=6568193,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='expitems', type='map', key='int', value='int'},
{ name='staritems', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGuardTowerMatchError',
id=6568198,
{ name='retcode', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SAttendNextBattle',
id=6568392,
{ name='round', type='int'},
{ name='battlebegintime', type='long'},
{ name='opponent', type='string'},
{ name='opponentcombatpower', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='SUnLockAmulet',
id=6568411,
{ name='pageid', type='int'},
{ name='amuletid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SResetStoryEctypeOpenCount',
id=6568425,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CGetAttackCityInfo',
id=6568484,
})
msg.add_type('lx.gs.chat.msg', {
name='STopMessage',
id=6568501,
{ name='content', type='lx.gs.chat.msg.Content'},
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyWelfareInfo',
id=6568512,
{ name='welfare', type='lx.gs.family.msg.FamilyWelfare'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SBedivorceWithDiscuss',
id=6568518,
{ name='divorceroleid', type='long'},
})
msg.add_type('lx.gs.task.msg', {
name='SCompleteFamTaskWithYuanbao',
id=6568593,
{ name='curtaskorder', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SPetInfo',
id=6568617,
{ name='petfragment', type='map', key='int', value='int'},
{ name='petmap', type='map', key='int', value='lx.gs.pet.Pet'},
{ name='fightpets', type='list', value='int'},
{ name='activemodelid', type='int'},
})
msg.add_type('lx.gs.pickcard.msg', {
name='SPickCardByType',
id=6568736,
{ name='picktype', type='int'},
{ name='pickbonus', type='list', value='lx.gs.pickcard.msg.PickBonusInfo'},
})
msg.add_type('lx.gs.chat.msg', {
name='SChatMsg',
id=6568747,
{ name='lasttalktime', type='long'},
{ name='silentendtime', type='long'},
{ name='leftreporttime', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SClaimFamilyBonus',
id=6568781,
{ name='claimlevel', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CEquipPetSkin',
id=6568809,
{ name='modelid', type='int'},
{ name='skinid', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='CWashAmulet',
id=6568907,
{ name='pageid', type='int'},
})
msg.add_type('lx.gs.storynote.msg', {
name='SInfo',
id=6568931,
{ name='chapters', type='map', key='int', value='lx.gs.storynote.msg.Chapter'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CDivorceWithDiscuss',
id=6568993,
{ name='bedivorceroleid', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SDeleteMMForProposeNotify',
id=6569110,
{ name='mmtype', type='int'},
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.pet.msg', {
name='SWashMaxValue',
id=6569130,
{ name='modelid', type='int'},
{ name='currvalues', type='map', key='int', value='float'},
{ name='maxvalues', type='map', key='int', value='float'},
})
msg.add_type('lx.gs.login', {
name='SKickRole',
id=6569194,
{ name='desc', type='string'},
})
msg.add_type('lx.gs.achievement.msg', {
name='SInfo',
id=6569195,
{ name='achievementstates', type='map', key='int', value='int'},
{ name='counters', type='map', key='int', value='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SKickoutFamilyMemberNotify',
id=6569346,
{ name='memberid', type='long'},
{ name='family', type='lx.gs.family.msg.FamilyBasicInfo'},
})
msg.add_type('lx.gs.family.msg', {
name='SGodAnimalActivityStartNotify',
id=6569459,
{ name='countdowntime', type='long'},
})
msg.add_type('lx.gs.storynote.msg', {
name='CInfo',
id=6569484,
})
msg.add_type('lx.gs.achievement.msg', {
name='SComplete',
id=6569558,
{ name='achievementid', type='int'},
})
msg.add_type('lx.gs.cmd.msg', {
name='SCommand',
id=6569865,
{ name='errcode', type='int'},
{ name='errparam', type='int'},
{ name='moduleid', type='int'},
{ name='cmdid', type='int'},
{ name='num', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.family.msg', {
name='SFamilyPartyOpenNotify',
id=6569920,
{ name='openid', type='long'},
})
msg.add_type('lx.gs.login', {
name='SCancelDelteRole',
id=6569951,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.task.msg', {
name='CCompleteFamilyTask',
id=6570045,
{ name='curtaskorder', type='int'},
{ name='taskid', type='int'},
{ name='npcid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SCreateFamily',
id=6570072,
{ name='family', type='lx.gs.family.msg.FamilyBasicInfo'},
})
msg.add_type('lx.gs.family.msg', {
name='STransferChief',
id=6570146,
{ name='member', type='lx.gs.family.msg.FamilyMember'},
})
msg.add_type('lx.gs.map.msg', {
name='CGuardTowerMatchCancel',
id=6570162,
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyMembersInfo',
id=6570182,
})
msg.add_type('lx.gs.equip.normalequip', {
name='CUpgradeEquip',
id=6570203,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SLaunchGodAnimalActivityNotify',
id=6570215,
{ name='launchroleid', type='long'},
{ name='starttime', type='long'},
})
msg.add_type('lx.gs.role.msg', {
name='SBuyTili',
id=6570240,
})
msg.add_type('lx.gs.bag.msg', {
name='CSyncBag',
id=6570242,
{ name='bagtype', type='int'},
})
msg.add_type('lx.gs.rank.msg', {
name='SGetReward',
id=6570362,
{ name='ranktype', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SDeleteEnemy',
id=6570369,
{ name='okroleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SEatBaozi',
id=6570416,
{ name='eattype', type='int'},
{ name='addtili', type='int'},
})
msg.add_type('lx.gs.dress', {
name='SDressExpired',
id=6570439,
{ name='dresskey', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CUpgradePetSkill',
id=6570496,
{ name='modelid', type='int'},
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SResponseNotify',
id=6570501,
{ name='responseroleid', type='long'},
{ name='responserolename', type='string'},
{ name='result', type='int'},
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='SWorldBossNotice',
id=6570520,
{ name='msg', type='string'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SFriendMarryNotify',
id=6570538,
{ name='proposeroleid', type='long'},
{ name='proposename', type='string'},
{ name='beproposeroleid', type='long'},
{ name='beproposedname', type='string'},
{ name='proposetype', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='SChangeName',
id=6570556,
{ name='changetimes', type='int'},
{ name='newname', type='string'},
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyRequestingInfo',
id=6570557,
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyActivityInfo',
id=6570627,
{ name='activity', type='lx.gs.family.msg.FamilyActivity'},
})
msg.add_type('lx.gs.role.msg', {
name='CSetConfigure',
id=6570725,
{ name='key', type='string'},
{ name='data', type='string'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncPetSkin',
id=6570727,
{ name='modelid', type='int'},
{ name='skinid', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SMarriageInfo',
id=6570877,
{ name='coupleroleid', type='long'},
{ name='curproposeid', type='long'},
{ name='startproposetime', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyLog',
id=6570992,
{ name='familyid', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SUpLevelMallNotify',
id=6571041,
{ name='family', type='lx.gs.family.msg.FamilyBasicInfo'},
})
msg.add_type('lx.gs.task.msg', {
name='CCancelFamilyTask',
id=6571118,
{ name='curtaskorder', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.mount', {
name='SRideExpired',
id=6571127,
{ name='rideid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CEnrollMultiStoryEctype',
id=6571201,
SINGLE = 1,
TEAM = 2,
{ name='ectypeid', type='int'},
{ name='enrolltype', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='CCancelAmuletWashResult',
id=6571205,
{ name='pageid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CObtainTeamFightWeekReward',
id=6571230,
{ name='rewardid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGetAttackCityInfo',
id=6571293,
{ name='stage', type='int'},
{ name='signup', type='byte'},
})
msg.add_type('lx.gs.paomadeng.msg', {
name='SPaomadengShow',
id=6571363,
ANNEAL = 1,
PERFUSE = 2,
EVOLVE = 3,
PICKCARD = 4,
TALISMAN_STARTLEVEL = 5,
PET_AWAKE = 6,
ROLE_LEVELUP = 7,
IDOL_TITLE = 8,
{ name='roleid', type='long'},
{ name='rolename', type='string'},
{ name='operatetype', type='int'},
{ name='id', type='int'},
{ name='lvl', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='COpenStoryEctype',
id=6571470,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='CAgreeJoin',
id=6571518,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.arena.msg', {
name='CObtainDailySuccReward',
id=6571529,
{ name='rewardid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CObtainChapterReward',
id=6571545,
{ name='chapterid', type='int'},
{ name='rewardindex', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='CSyncItems',
id=6571587,
{ name='bagtype', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SObtainTeamFightDayReward',
id=6571669,
})
msg.add_type('lx.gs.activity.msg', {
name='CReceiveActivityBonus',
id=6571688,
{ name='id', type='int'},
{ name='cmdid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SFindFamily',
id=6571695,
{ name='startindex', type='int'},
{ name='familyname', type='string'},
{ name='families', type='list', value='lx.gs.family.msg.FamilyBasicInfo'},
})
msg.add_type('lx.gs.map.msg', {
name='SPrepareMultiStoryEctypeFailNotify',
id=6571699,
{ name='reason', type='string'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SRoundStage',
id=6571714,
{ name='termid', type='int'},
{ name='round', type='int'},
{ name='roundstage', type='int'},
})
msg.add_type('lx.gs.jade', {
name='SUnloadJewelry',
id=6571736,
{ name='position', type='int'},
{ name='index', type='int'},
{ name='jewelry', type='lx.gs.jade.Jewelry'},
})
msg.add_type('lx.gs.map.msg', {
name='SSweepStoryEctype',
id=6571777,
{ name='ectypeid', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs', {
name='CPing',
id=6571796,
{ name='sendclienttime', type='long'},
{ name='receivedmessagecount', type='long'},
})
msg.add_type('lx.gs.jade', {
name='CLoadJewelry',
id=6571797,
{ name='index', type='int'},
{ name='position', type='int'},
})
msg.add_type('lx.gs.chat.msg', {
name='CGetOnlineState',
id=6571847,
{ name='roles', type='set', value='long'},
})
msg.add_type('lx.gs.bag.msg', {
name='SChange',
id=6571886,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='newnum', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SRejectRequestJoinF',
id=6571897,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.achievement.msg', {
name='SCounterChange',
id=6571901,
{ name='countertype', type='int'},
{ name='value', type='long'},
})
msg.add_type('lx.gs.jade', {
name='SEvolveJade',
id=6571921,
{ name='jade', type='lx.gs.jade.Jade'},
})
msg.add_type('lx.gs.family.msg', {
name='SEnterFamilyStation',
id=6571963,
})
msg.add_type('lx.gs.map.msg', {
name='SLeaveTeamAndEnrollSingle',
id=6572025,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SCallPet',
id=6572061,
{ name='addpet', type='lx.gs.pet.Pet'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CWorship',
id=6572068,
{ name='termid', type='int'},
{ name='profession', type='int'},
})
msg.add_type('lx.gs.activity.msg', {
name='SActivityChangeNotify',
id=6572084,
{ name='id', type='int'},
{ name='cmdid', type='int'},
{ name='status', type='int'},
})
msg.add_type('lx.gs.jade', {
name='SGetJadeInfo',
id=6572085,
{ name='jadeinfo', type='lx.gs.jade.RoleJadeInfo'},
})
msg.add_type('lx.gs.storynote.msg', {
name='SActiveNote',
id=6572088,
{ name='chapterid', type='int'},
{ name='noteid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CResetStoryEctypeOpenCount',
id=6572113,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='SAddStarExp',
id=6572309,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='newlevel', type='int'},
{ name='newexp', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SUpgradePetAwake',
id=6572346,
{ name='modelid', type='int'},
{ name='awakelevel', type='int'},
})
msg.add_type('lx.gs', {
name='SPing',
id=6572349,
{ name='sendclienttime', type='long'},
{ name='sendservertime', type='long'},
{ name='recvclienttime', type='long'},
{ name='sendmessagecount', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='CSetMMAuthorization',
id=6572361,
{ name='allowfriendgetmm', type='int'},
{ name='allowstrangergetmm', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CHeroRefreshAward',
id=6572366,
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SRequestJoinAllFamily',
id=6572380,
{ name='familyids', type='list', value='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SKickoutFamilyMember',
id=6572408,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.jade', {
name='SSummonRole',
id=6572432,
{ name='role', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SResetDailyEctypeOpenCount',
id=6572514,
{ name='ectypetype', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SBlackMallList',
id=6572533,
{ name='lists', type='list', value='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SUpgradePetStar',
id=6572557,
{ name='modelid', type='int'},
{ name='starlevel', type='int'},
})
msg.add_type('lx.gs.dress', {
name='SDeActiveDress',
id=6572560,
})
msg.add_type('lx.gs.task.msg', {
name='CGetFamilyTask',
id=6572602,
{ name='npcid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CLeaveTeamSpeed',
id=6572605,
})
msg.add_type('lx.gs.family.msg', {
name='CUpMaxSkillLevel',
id=6572662,
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyInfo',
id=6572685,
{ name='family', type='lx.gs.family.msg.FamilyBasicInfo'},
{ name='selfinfo', type='lx.gs.family.msg.FamilyMember'},
{ name='selfinitid', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SFamilyInfoChangeNotify',
id=6572828,
{ name='declaration', type='string'},
{ name='publicinfo', type='string'},
{ name='money', type='long'},
{ name='curlvlbuilddegree', type='int'},
{ name='flevel', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CGuardTowerMatchStart',
id=6572889,
{ name='minpower', type='int'},
})
msg.add_type('lx.gs.equip.accessory', {
name='CApplyAccessoryWashResult',
id=6572935,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SRaiseGodAnimal',
id=6572941,
{ name='raisetype', type='int'},
{ name='animal', type='lx.gs.family.msg.FamilyGodAnimal'},
})
msg.add_type('lx.gs.login', {
name='SRoleRelogin',
id=6572954,
{ name='err', type='int'},
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SSweepChapterAllStoryEctype',
id=6572999,
{ name='chapterid', type='int'},
{ name='bonus', type='map', key='int', value='map.msg.Bonus'},
})
msg.add_type('lx.gs.friend.msg', {
name='SSendFlowerNotify',
id=6573071,
{ name='senderinfo', type='lx.gs.friend.msg.FriendInfo'},
{ name='flowers', type='list', value='lx.gs.friend.msg.FlowerInfo'},
})
msg.add_type('lx.gs.chat.msg', {
name='SReportPlayer',
id=6573073,
{ name='bereportid', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SCancelTeamSpeedApply',
id=6573076,
})
msg.add_type('lx.gs.pet.msg', {
name='CLoadPet',
id=6573099,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CGetPlayerLocation',
id=6573175,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.exchange.msg', {
name='CBuy',
id=6573225,
{ name='exchangeid', type='long'},
{ name='num', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncPetAttrs',
id=6573227,
{ name='modelid', type='int'},
{ name='attrs', type='map', key='int', value='float'},
})
msg.add_type('lx.gs.bag.msg', {
name='CSplitItem',
id=6573279,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='splitnumber', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SBeginMatchTeamFight',
id=6573357,
{ name='errcode', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SGetBattleRound',
id=6573442,
{ name='round', type='int'},
{ name='profession', type='int'},
{ name='battles', type='list', value='lx.gs.activity.huiwu.msg.BattlePair'},
})
msg.add_type('lx.gs.amulet', {
name='CLockAmulet',
id=6573456,
{ name='pageid', type='int'},
{ name='amuletid', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='SApplyAmuletWashResult',
id=6573512,
{ name='pageid', type='int'},
{ name='changeprop', type='map', key='int', value='lx.gs.amulet.AmuletPropperty'},
})
msg.add_type('lx.gs.map.msg', {
name='SChangeMatch',
id=6573529,
{ name='matchtype', type='int'},
{ name='nextmatchtime', type='long'},
})
msg.add_type('lx.gs.item', {
name='CUseItem',
id=6573540,
{ name='pos', type='int'},
{ name='usenumber', type='int'},
})
msg.add_type('lx.gs.cmd.msg', {
name='CCommand',
id=6573551,
{ name='moduleid', type='int'},
{ name='cmdid', type='int'},
{ name='num', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SOtherMemberCancelMatchTeamFight',
id=6573597,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SEvolveGodAnimal',
id=6573598,
{ name='animal', type='lx.gs.family.msg.FamilyGodAnimal'},
})
msg.add_type('lx.gs.role.msg', {
name='SCombatPowerChange',
id=6573613,
{ name='combatpower', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SWashPet',
id=6573650,
{ name='modelid', type='int'},
{ name='deltavalues', type='map', key='int', value='float'},
})
msg.add_type('lx.gs.task.msg', {
name='SAcceptFamilyTask',
id=6573709,
{ name='npcid', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CResetDailyEctypeOpenCount',
id=6573765,
{ name='ectypetype', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CCancelRequestJoinF',
id=6573772,
{ name='familyid', type='long'},
})
msg.add_type('lx.gs.arena.msg', {
name='SGetChallenge',
id=6573808,
{ name='challengeranks', type='list', value='lx.gs.arena.msg.ChallangeInfo'},
})
msg.add_type('lx.gs.activity.worldmonster.msg', {
name='CGetExpMonsterPosition',
id=6573867,
})
msg.add_type('lx.gs.map.msg', {
name='CGetWorldLines',
id=6573891,
{ name='worldid', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='SFollowTeamMemberNotify',
id=6574030,
{ name='followmemberid', type='long'},
})
msg.add_type('lx.gs.task.msg', {
name='SOpenTaskEctype',
id=6574194,
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SAcceptFailNotify',
id=6574270,
{ name='acceptid', type='long'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='SActiveInfoChangeNotify',
id=6574271,
{ name='scores', type='int'},
{ name='changeactivetimes', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SKillOther',
id=6574291,
{ name='defencername', type='string'},
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='CGetWorldBossLineStatus',
id=6574426,
{ name='bossid', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SShakeMoneyTree',
id=6574502,
{ name='time', type='int'},
{ name='receinexunibi', type='long'},
{ name='criticalnum', type='float'},
})
msg.add_type('lx.gs.family.msg', {
name='CStudyFamilySkill',
id=6574540,
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SBattleResult',
id=6574544,
{ name='opponentname', type='string'},
{ name='win', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CInviteJoinFamily',
id=6574548,
{ name='beinviteroieid', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SCallAllFamilyMembers',
id=6574581,
})
msg.add_type('lx.gs.equip', {
name='SRecommandEquip',
id=6574585,
{ name='result', type='int'},
})
msg.add_type('lx.gs.equip.accessory', {
name='SWashAccessory',
id=6574606,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='oldpropindex', type='int'},
{ name='newprop', type='lx.gs.equip.PropInfo'},
})
msg.add_type('lx.gs.mount', {
name='SDeActiveRide',
id=6574647,
})
msg.add_type('lx.gs.exchange.msg', {
name='SBuy',
id=6574676,
{ name='exchangeid', type='long'},
{ name='num', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='SSyncTalismanCombatPower',
id=6574783,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='val', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SBeproposed',
id=6574784,
{ name='proposeroleid', type='long'},
{ name='proposerolename', type='string'},
{ name='proposetype', type='int'},
{ name='proposeoath', type='string'},
})
msg.add_type('lx.gs.pay', {
name='SPayReturnInfo',
id=6574806,
{ name='hasgotpayreturn', type='byte'},
{ name='vipexp', type='long'},
{ name='yuanbao', type='long'},
{ name='bindyuanbao', type='long'},
})
msg.add_type('lx.gs.task.msg', {
name='SClearFamTask',
id=6574849,
{ name='bonus', type='map.msg.Bonus'},
{ name='acceptedtasks', type='list', value='lx.gs.task.msg.TaskInfo'},
{ name='curfamtasks', type='list', value='lx.gs.task.msg.FamilyTaskInfo'},
{ name='completefamtasknum', type='int'},
{ name='comdaycycle', type='int'},
{ name='comweeksmallcycle', type='int'},
{ name='isuseyuanbaocomtask', type='int'},
{ name='iscanclefamtask', type='int'},
})
msg.add_type('lx.gs.activity.worldmonster.msg', {
name='SGetKillExpMonBonus',
id=6574851,
{ name='num', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.map.msg', {
name='CHeroChangeEctype',
id=6574902,
{ name='groupid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SObtainTeamFightWeekReward',
id=6574918,
{ name='rewardid', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='SSwapByPos',
id=6574945,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
})
msg.add_type('lx.gs.achievement.msg', {
name='CGetReward',
id=6575020,
{ name='achievementid', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='CRejectMM',
id=6575075,
{ name='roleid', type='long'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.chat.msg', {
name='CBuyChatFace',
id=6575092,
{ name='name', type='string'},
})
msg.add_type('lx.gs.talisman', {
name='CChangeWuxingType',
id=6575201,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='COpenClimbTowerEctype',
id=6575229,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CUnloadPet',
id=6575244,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SAcceptRequestJoinFNotify',
id=6575273,
{ name='family', type='lx.gs.family.msg.FamilyBasicInfo'},
{ name='member', type='lx.gs.family.msg.FamilyMember'},
})
msg.add_type('lx.gs.role.msg', {
name='SGetConfigures',
id=6575283,
{ name='datas', type='map', key='string', value='string'},
})
msg.add_type('lx.gs.family.msg', {
name='SQuitFamily',
id=6575332,
})
msg.add_type('lx.gs.leaderboard.msg', {
name='SRoleRanking',
id=6575406,
{ name='info', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncPetSkill',
id=6575438,
{ name='modelid', type='int'},
{ name='skillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SRequestJoinFamily',
id=6575448,
{ name='familyid', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SEnrollAttackCity',
id=6575466,
})
msg.add_type('lx.gs.talisman', {
name='CUnEquipTalisman',
id=6575471,
})
msg.add_type('lx.gs.map.msg', {
name='CTeamApplyTeamSpeed',
id=6575507,
})
msg.add_type('lx.gs.pet.msg', {
name='CBuyPetSkin',
id=6575522,
{ name='modelid', type='int'},
{ name='skinid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SEnrollMultiStoryEctype',
id=6575548,
{ name='ectypeid', type='int'},
{ name='enrolltype', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='CSell',
id=6575552,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='sellnum', type='int'},
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='SGetWorldBossLineStatus',
id=6575695,
{ name='bossid', type='int'},
{ name='lines', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRejectMM',
id=6575901,
{ name='result', type='int'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CBuyXiushu',
id=6575958,
})
msg.add_type('lx.gs.map.msg', {
name='CSweepClimbTower',
id=6575969,
})
msg.add_type('lx.gs.pet.msg', {
name='SUnActivePet',
id=6575997,
})
msg.add_type('lx.gs.family.msg', {
name='CGetFamilyMemberInfo',
id=6576028,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SBedivorcedNotify',
id=6576040,
{ name='divorceroleid', type='long'},
{ name='divorcerolename', type='string'},
{ name='divorcetime', type='long'},
{ name='bookcontent', type='string'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CAttemptPropose',
id=6576050,
{ name='beproposedroleid', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SEnrollMultiStoryEctypeSuccessNotify',
id=6576190,
{ name='lefttime', type='int'},
{ name='roleinfos', type='list', value='lx.gs.map.msg.EnrollBriefInfo'},
})
msg.add_type('lx.gs.map.msg', {
name='SGuardTowerMatchMemberOut',
id=6576200,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SGetPreselectionRoleList',
id=6576202,
{ name='profession', type='int'},
{ name='roles', type='list', value='lx.gs.activity.huiwu.msg.PreselectionRole'},
})
msg.add_type('lx.gs.family.msg', {
name='SAcceptRequestJoinF',
id=6576295,
{ name='member', type='lx.gs.family.msg.FamilyMember'},
})
msg.add_type('lx.gs.friend.msg', {
name='CGetMaimaiInfo',
id=6576304,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SBuyXiushu',
id=6576321,
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='CGetWorldBossList',
id=6576344,
})
msg.add_type('lx.gs.dress', {
name='CBuyDress',
id=6576363,
{ name='dresskey', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SUpLevelMall',
id=6576376,
})
msg.add_type('lx.gs.pet.msg', {
name='CUpgradePetLevel',
id=6576416,
{ name='modelid', type='int'},
{ name='materialpos', type='int'},
{ name='materialnum', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='COpenFamilyParty',
id=6576573,
})
msg.add_type('lx.gs.login', {
name='CCancelDelteRole',
id=6576594,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='SAddEnemyNotify',
id=6576622,
{ name='enemy', type='lx.gs.friend.msg.EnemyShowInfo'},
})
msg.add_type('lx.gs.pet.msg', {
name='CWashPetConfirm',
id=6576814,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SFriendOnlineNotify',
id=6576851,
{ name='roleid', type='long'},
{ name='online', type='int'},
})
msg.add_type('lx.gs.pay', {
name='CGetPayReturn',
id=6576860,
})
msg.add_type('lx.gs.task.msg', {
name='SGetWeekCompleteBonus',
id=6576981,
{ name='bonuslvl', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.friend.msg', {
name='SClaimIdolAward',
id=6577004,
{ name='idolid', type='long'},
{ name='awardid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SAddNewBranchTaskNotify',
id=6577009,
{ name='newtask', type='list', value='int'},
})
msg.add_type('lx.gs.task.msg', {
name='SCancelHideMinesNotify',
id=6577073,
{ name='unhideminse', type='set', value='int'},
})
msg.add_type('lx.gs.chat.msg', {
name='SBeSilentNotify',
id=6577159,
{ name='silentendtime', type='long'},
})
msg.add_type('lx.gs.dress', {
name='SBuyDress',
id=6577189,
{ name='dresskey', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='SLevelChange',
id=6577201,
{ name='level', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SCancelEnrollMultiStoryEctype',
id=6577203,
{ name='result', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='CTalismanAwake',
id=6577320,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.pay', {
name='SBuyVipPackage',
id=6577322,
{ name='index', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CWashPetCancel',
id=6577348,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CHeroGainAward',
id=6577383,
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.pickcard.msg', {
name='SPickcardTimes',
id=6577453,
{ name='huobanhigh', type='int'},
{ name='huobanlow', type='int'},
{ name='fabaohigh', type='int'},
{ name='fabaolow', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='CRequestMM',
id=6577475,
{ name='roleid', type='long'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='SCancelAmuletWashResult',
id=6577493,
{ name='pageid', type='int'},
})
msg.add_type('lx.gs.leaderboard.msg', {
name='CGetRankReward',
id=6577524,
{ name='ranktype', type='int'},
})
msg.add_type('lx.gs.chat.msg', {
name='SSyncChatFace',
id=6577553,
{ name='name', type='set', value='string'},
})
msg.add_type('lx.gs', {
name='STips',
id=6577752,
{ name='location', type='int'},
{ name='contentid', type='int'},
{ name='content', type='string'},
{ name='param', type='list', value='string'},
})
msg.add_type('lx.gs.arena.msg', {
name='SInfo',
id=6577819,
{ name='isopen', type='byte'},
{ name='rank', type='int'},
{ name='challengesuccnum', type='int'},
{ name='obtainrewards', type='list', value='int'},
{ name='reports', type='list', value='lx.gs.arena.msg.FightReport'},
{ name='bestrank', type='int'},
{ name='lastrewardrank', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRoleCharmNotify',
id=6577848,
{ name='notifytype', type='int'},
{ name='roleid', type='long'},
{ name='charm', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SGuess',
id=6577902,
{ name='profession', type='int'},
{ name='target', type='long'},
{ name='beguessnum', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CFriendWish',
id=6577940,
{ name='proposeroleid', type='long'},
{ name='beproposeroleid', type='long'},
{ name='proposetype', type='int'},
})
msg.add_type('lx.gs.rank.msg', {
name='CGetRank',
id=6577970,
{ name='ranktype', type='int'},
{ name='rankstart', type='int'},
{ name='rankend', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SDeleteMMNotify',
id=6578009,
{ name='mmtype', type='int'},
{ name='roleid', type='long'},
{ name='rolename', type='string'},
})
msg.add_type('lx.gs.family.msg', {
name='CResponseInvite',
id=6578017,
{ name='result', type='int'},
{ name='familyid', type='long'},
{ name='inviteroleid', type='long'},
})
msg.add_type('lx.gs.bag.msg', {
name='CSortBag',
id=6578091,
{ name='bagtype', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SDivorceWithDiscuss',
id=6578133,
{ name='bedivorceroleid', type='long'},
{ name='bedivorcerolename', type='string'},
{ name='divorceresult', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='SGetAmuletInfo',
id=6578149,
{ name='amuletinfo', type='lx.gs.amulet.RoleAmuletInfo'},
})
msg.add_type('lx.gs.family.msg', {
name='CRequestJoinFamily',
id=6578257,
{ name='familyid', type='long'},
})
msg.add_type('lx.gs.task.msg', {
name='SDailyResetFamTaskNotify',
id=6578381,
{ name='comdaycycle', type='int'},
{ name='comweeksmallcycle', type='int'},
})
msg.add_type('lx.gs', {
name='SError2',
id=6578401,
{ name='err', type='string'},
})
msg.add_type('lx.gs.chat.msg', {
name='SGetOnlineState',
id=6578403,
{ name='onlineroles', type='set', value='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SLaunchGodAnimalActivity',
id=6578471,
})
msg.add_type('lx.gs.family.msg', {
name='SResponseInvite',
id=6578506,
{ name='result', type='int'},
{ name='familyname', type='string'},
})
msg.add_type('lx.gs.map.msg', {
name='CObtainTeamFightDayReward',
id=6578760,
})
msg.add_type('lx.gs.friend.msg', {
name='SSendFlower',
id=6578792,
{ name='sendtype', type='int'},
{ name='reveiverid', type='long'},
{ name='flowers', type='list', value='lx.gs.friend.msg.FlowerInfo'},
})
msg.add_type('lx.gs.role.msg', {
name='SKillMonster',
id=6578817,
{ name='todaytotaladdmonsterexp', type='long'},
{ name='petexps', type='list', value='lx.gs.role.msg.PetExp'},
{ name='currencys', type='list', value='lx.gs.role.msg.CurrencyAddRecord'},
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='SWorldBossKillNotice',
id=6578932,
{ name='killername', type='string'},
{ name='msg', type='string'},
})
msg.add_type('lx.gs.map.msg', {
name='SChangeAttackCityStage',
id=6578955,
{ name='stage', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CEvolvePetSkill',
id=6578964,
{ name='modelid', type='int'},
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='CAcceptMM',
id=6578966,
{ name='roleid', type='long'},
{ name='reqmmtype', type='int'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='CAddNormalExp',
id=6579019,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='materialpos', type='int'},
{ name='materialnum', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SBekillByOther',
id=6579082,
{ name='attackername', type='string'},
{ name='defencer', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='SAppointJob',
id=6579097,
{ name='member', type='lx.gs.family.msg.FamilyMember'},
{ name='jobid', type='int'},
})
msg.add_type('lx.gs.activity.msg', {
name='SActivity',
id=6579108,
{ name='activityinfos', type='map', key='int', value='lx.gs.activity.msg.ActivityInfo'},
})
msg.add_type('lx.gs.family.msg', {
name='CClaimFamilyBonus',
id=6579117,
{ name='claimlevel', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncFightPets',
id=6579146,
{ name='fightpets', type='list', value='int'},
})
msg.add_type('lx.gs.talisman', {
name='CEquipTalisman',
id=6579245,
{ name='pos', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CGetRequestedFamily',
id=6579303,
})
msg.add_type('lx.gs.family.msg', {
name='SUpMaxSkillLevel',
id=6579305,
})
msg.add_type('lx.gs.map.msg', {
name='CGetDailyBestRecord',
id=6579486,
{ name='ectypetype', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='CClaimIdolAward',
id=6579519,
{ name='idolid', type='long'},
{ name='awardid', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='CAddStarExp',
id=6579572,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='costtalisman', type='set', value='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='CUnEquipPetSkin',
id=6579590,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGetPlayerLocation',
id=6579601,
{ name='roleid', type='long'},
{ name='maptype', type='int'},
{ name='worldid', type='int'},
{ name='lineid', type='int'},
{ name='position', type='map.msg.Vector3'},
})
msg.add_type('lx.gs.friend.msg', {
name='SDeleteMM',
id=6579603,
{ name='result', type='int'},
{ name='roleid', type='long'},
{ name='mmtype', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SChangeSection',
id=6579610,
{ name='chapterid', type='int'},
{ name='sectionid', type='int'},
{ name='star', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGetDailyBestRecord',
id=6579621,
{ name='ectypetype', type='int'},
{ name='name', type='string'},
{ name='mincosttime', type='int'},
{ name='mymincosttime', type='int'},
})
msg.add_type('lx.gs.equip.normalequip', {
name='CAnnealEquip',
id=6579722,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='unbindonly', type='int'},
{ name='usewanbifu', type='int'},
{ name='helpitemmodelid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SGetRequestedFamily',
id=6579804,
{ name='ids', type='list', value='long'},
})
msg.add_type('lx.gs.talisman', {
name='CUpgradeSkill',
id=6579817,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='skillid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SCancelMatchTeamFight',
id=6579901,
{ name='errcode', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SWorship',
id=6579963,
{ name='worshipnum', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.pet.msg', {
name='CCallPet',
id=6579970,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SQuitFamilyNotify',
id=6580009,
{ name='memberid', type='long'},
{ name='membername', type='string'},
})
msg.add_type('lx.gs.pet.msg', {
name='CWashMaxValue',
id=6580164,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.login', {
name='CRoleRelogin',
id=6580194,
{ name='roleid', type='long'},
{ name='receivedmessagecount', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='CEvolveGodAnimal',
id=6580241,
{ name='animalid', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SFriendWishNotify',
id=6580306,
{ name='friendname', type='string'},
{ name='wishgift', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.rank.msg', {
name='SGetMyRank',
id=6580318,
{ name='ranktype', type='int'},
{ name='currank', type='int'},
})
msg.add_type('lx.gs.pay', {
name='SGetPayReturn',
id=6580353,
})
msg.add_type('lx.gs.family.msg', {
name='CUpdateDeclarationOrPublic',
id=6580368,
UPDATE_DECLARATION = 1,
UPDATE_PUBLIC = 2,
{ name='updatetype', type='int'},
{ name='newtext', type='string'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CEatBaozi',
id=6580377,
EAT_LUNCH = 1,
EAT_DINNER = 2,
RE_EAT_LUNCH = 3,
RE_EAT_DINNER = 4,
{ name='eattype', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='CUseCode',
id=6580486,
{ name='code', type='string'},
})
msg.add_type('lx.gs.map.msg', {
name='SHeroGroupSyncInfo',
id=6580497,
{ name='groupid', type='int'},
{ name='groupinfo', type='lx.gs.map.msg.HeroesGroupInfo'},
})
msg.add_type('lx.gs.amulet', {
name='CGetAmuletInfo',
id=6580575,
})
msg.add_type('lx.gs.map.msg', {
name='SCancelEnrollMultiStorySuccessNotify',
id=6580900,
})
msg.add_type('lx.gs.arena.msg', {
name='SNewFightReport',
id=6580914,
{ name='report', type='lx.gs.arena.msg.FightReport'},
})
msg.add_type('lx.gs.jade', {
name='SEnhanceJewelry',
id=6580950,
{ name='position', type='int'},
{ name='index', type='int'},
{ name='jewelry', type='lx.gs.jade.Jewelry'},
{ name='jewelrybag', type='list', value='lx.gs.jade.Jewelry'},
})
msg.add_type('lx.gs.map.msg', {
name='CLeaveMap',
id=6581002,
})
msg.add_type('lx.gs.family.msg', {
name='CRejectRequestJoinF',
id=6581037,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.jade', {
name='CGetJadeInfo',
id=6581063,
})
msg.add_type('lx.gs.jade', {
name='CEnhanceJade',
id=6581077,
{ name='enhancetypeid', type='int'},
{ name='num', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CPersonalApplyTeamSpeed',
id=6581081,
})
msg.add_type('lx.gs.family.msg', {
name='CEnterFamilyStation',
id=6581103,
NORMAL_ENTER = 0,
OPEN_PARTY = 1,
BLACK_MARKET = 2,
FAMILY_MANAGER = 3,
GOD_ANIMAL = 4,
{ name='isopenparty', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CCreateFamily',
id=6581106,
{ name='familyname', type='string'},
})
msg.add_type('lx.gs.jade', {
name='SHuntJewelry',
id=6581117,
{ name='num', type='int'},
{ name='jewelrygetlevel', type='int'},
{ name='jewelrylist', type='list', value='lx.gs.jade.Jewelry'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CPropose',
id=6581170,
{ name='beproposedroleid', type='long'},
{ name='proposeoath', type='string'},
})
msg.add_type('lx.gs.talisman', {
name='SUpgradeSkill',
id=6581184,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='skillid', type='int'},
{ name='skilllevel', type='int'},
})
msg.add_type('lx.gs.equip.normalequip', {
name='SUpgradeEquip',
id=6581237,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
})
msg.add_type('lx.gs.dress', {
name='CDeActiveDress',
id=6581317,
})
msg.add_type('lx.gs.jade', {
name='SLoadJewelry',
id=6581351,
{ name='index', type='int'},
{ name='position', type='int'},
{ name='jewelry', type='lx.gs.jade.Jewelry'},
{ name='oldjewelry', type='lx.gs.jade.Jewelry'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncPetLevel',
id=6581358,
{ name='modelid', type='int'},
{ name='level', type='int'},
{ name='exp', type='long'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CEnroll',
id=6581372,
})
msg.add_type('lx.gs.pet.msg', {
name='SWashPetCancel',
id=6581376,
{ name='modelid', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='STalismanAwake',
id=6581404,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='newlevel', type='int'},
})
msg.add_type('lx.gs.equip', {
name='CUnloadEquip',
id=6581503,
{ name='pos', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CLaunchGodAnimalActivity',
id=6581514,
})
msg.add_type('lx.gs.mount', {
name='CBuyRide',
id=6581695,
{ name='ridekey', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SBuyPetSkin',
id=6581708,
{ name='modelid', type='int'},
{ name='addskinid', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='CBuyTili',
id=6581791,
})
msg.add_type('lx.gs.achievement.msg', {
name='SGetReward',
id=6581819,
{ name='achievementid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SLeaveMap',
id=6581828,
})
msg.add_type('lx.gs.family.msg', {
name='CQuitFamily',
id=6581898,
})
msg.add_type('lx.gs.map.msg', {
name='CEnterAttackCity',
id=6581902,
})
msg.add_type('lx.gs.task.msg', {
name='CGetWeekCompleteBonus',
id=6581935,
{ name='bonuslvl', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CCancelTeamSpeedApply',
id=6582025,
})
msg.add_type('lx.gs.equip.normalequip', {
name='CPerfuseEquip',
id=6582031,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='unbindonly', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='COpenHeroEctype',
id=6582066,
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CDivorceWithBook',
id=6582102,
{ name='bedivorceroleid', type='long'},
{ name='bookcontent', type='string'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='STermStageChange',
id=6582120,
{ name='termid', type='int'},
{ name='stage', type='int'},
})
msg.add_type('lx.gs.leaderboard.msg', {
name='SLatestLeaderBoard',
id=6582141,
{ name='latestbord', type='map', key='int', value='lx.gs.leaderboard.msg.BoardInfo'},
})
msg.add_type('lx.gs.marriage.msg', {
name='CBedivorceWithDiscuss',
id=6582336,
{ name='divorceroleid', type='long'},
{ name='divorceresult', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGetHeroEctypeInfo',
id=6582371,
{ name='herogroups', type='map', key='int', value='lx.gs.map.msg.HeroesGroupInfo'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SAttemptPropose',
id=6582606,
{ name='errcode', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SSweepClimbTower',
id=6582612,
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.map.msg', {
name='SGetWorldLines',
id=6582648,
{ name='worldid', type='int'},
{ name='lines', type='list', value='lx.gs.map.msg.LineInfo'},
})
msg.add_type('lx.gs.map.msg', {
name='CEnterWorld',
id=6582681,
{ name='worldid', type='int'},
{ name='lineid', type='int'},
})
msg.add_type('lx.gs.pet.msg', {
name='SEvolvePetSkill',
id=6582727,
{ name='modelid', type='int'},
{ name='oldskillid', type='int'},
{ name='newskillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('lx.gs.bonus.msg', {
name='CBonusInfo',
id=6582740,
})
msg.add_type('lx.gs.friend.msg', {
name='CDeleteEnemy',
id=6582779,
{ name='roleidlist', type='list', value='long'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='STermInfo',
id=6582808,
{ name='termid', type='int'},
{ name='stage', type='int'},
{ name='roundindex', type='int'},
{ name='roundstage', type='int'},
{ name='opentime', type='long'},
{ name='guessendtime', type='long'},
{ name='guessroleid', type='long'},
{ name='hasenroll', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CAcceptRequestJoinF',
id=6582812,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='CKickoutFamilyMember',
id=6582876,
{ name='memberid', type='long'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SEnroll',
id=6582886,
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.family.msg', {
name='SCancelRequestJoinF',
id=6582912,
{ name='familyid', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SEctypeInfo',
id=6582946,
{ name='climbtowers', type='map', key='int', value='lx.gs.map.msg.ClimbTowerInfo'},
{ name='chapters', type='map', key='int', value='lx.gs.map.msg.ChapterInfo'},
{ name='dailys', type='map', key='int', value='lx.gs.map.msg.DailyInfo'},
{ name='teamfight', type='lx.gs.map.msg.TeamFightInfo'},
{ name='multistory', type='map', key='int', value='lx.gs.map.msg.MultiStoryInfo'},
{ name='matchtype', type='int'},
{ name='nextmatchtime', type='long'},
})
msg.add_type('lx.gs.leaderboard.msg', {
name='SYesterdayRanking',
id=6582964,
{ name='info', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SMatchTeamFightSucc',
id=6583040,
{ name='countdown', type='int'},
{ name='team1', type='lx.gs.map.msg.MatchTeamInfo'},
{ name='team2', type='lx.gs.map.msg.MatchTeamInfo'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SFriendWish',
id=6583044,
{ name='marrygift', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.map.msg', {
name='CGetHeroEctypeInfo',
id=6583067,
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CGetBattleRound',
id=6583081,
{ name='round', type='int'},
{ name='profession', type='int'},
})
msg.add_type('lx.gs.activity.worldmonster.msg', {
name='ExpMonsterNotify',
id=6583127,
START = 1,
END = 2,
{ name='eventtype', type='int'},
{ name='msg', type='string'},
})
msg.add_type('lx.gs.friend.msg', {
name='SGetMaimaiInfo',
id=6583186,
{ name='result', type='int'},
{ name='roleid', type='long'},
{ name='mminfo', type='map', key='int', value='lx.gs.friend.msg.MMInfoList'},
})
msg.add_type('lx.gs.bonus.msg', {
name='SBonusInfo',
id=6583195,
{ name='receivednewgift', type='list', value='int'},
{ name='totallogindays', type='int'},
{ name='totaldays', type='int'},
{ name='signedlist', type='list', value='int'},
{ name='hastodaysign', type='int'},
{ name='continuedays', type='int'},
{ name='lefttimes', type='int'},
{ name='receivedconlogingift', type='list', value='int'},
{ name='receivedtype', type='list', value='int'},
{ name='dailyonlinetime', type='long'},
{ name='monthcardplayer', type='int'},
{ name='monthcardleftday', type='int'},
{ name='dates', type='list', value='int'},
{ name='buygrowplantype', type='int'},
{ name='buygrowplantypetwo', type='int'},
{ name='buygrowplantypethr', type='int'},
{ name='growplanonetime', type='int'},
{ name='growplantwotime', type='int'},
{ name='growplanthreetime', type='int'},
{ name='threegifts', type='list', value='int'},
{ name='fivegifts', type='list', value='int'},
{ name='sevengifts', type='list', value='int'},
{ name='isfirstpayused', type='byte'},
{ name='dailytotalcharge', type='long'},
{ name='totalcharge', type='long'},
{ name='totalcost', type='long'},
{ name='hasbuyproduct', type='list', value='int'},
{ name='receivedchargegifts', type='list', value='int'},
{ name='wishtimes', type='int'},
{ name='lastwishtime', type='long'},
{ name='lastwishpetid', type='long'},
{ name='iseatlunch', type='int'},
{ name='iseatdinner', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SOpenFamilyParty',
id=6583216,
})
msg.add_type('lx.gs.map.msg', {
name='STeamSpeedTimeInfo',
id=6583341,
{ name='opentime', type='list', value='lx.gs.map.msg.TimeRange'},
{ name='applytime', type='int'},
{ name='competitiontime', type='int'},
})
msg.add_type('lx.gs.equip.accessory', {
name='CWashAccessory',
id=6583363,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='washpropindex', type='int'},
{ name='materialpos', type='int'},
})
msg.add_type('lx.gs.activity.worldmonster.msg', {
name='SKillExpMonsterNums',
id=6583420,
{ name='nums', type='int'},
{ name='receivedbonus', type='list', value='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CGuess',
id=6583526,
{ name='profession', type='int'},
{ name='target', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SObtainChapterReward',
id=6583739,
{ name='chapterid', type='int'},
{ name='rewardindex', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='SLuckyInfo',
id=6583777,
{ name='luckytype', type='int'},
{ name='washcount', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SRejectMMNotify',
id=6583789,
{ name='mmtype', type='int'},
{ name='roleid', type='long'},
{ name='rolename', type='string'},
})
msg.add_type('lx.gs.equip.accessory', {
name='CSwapAccessoryProp',
id=6583800,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
})
msg.add_type('lx.gs.pay', {
name='SBuyGrowPlanNotify',
id=6583872,
{ name='growplantype', type='int'},
})
msg.add_type('lx.gs.arena.msg', {
name='SChallenge',
id=6583897,
{ name='newrank', type='int'},
{ name='challengesuccnum', type='int'},
{ name='challengeranks', type='list', value='lx.gs.arena.msg.ChallangeInfo'},
})
msg.add_type('lx.gs.equip.normalequip', {
name='SSwapEquipProp',
id=6583918,
{ name='bagtype1', type='int'},
{ name='pos1', type='int'},
{ name='bagtype2', type='int'},
{ name='pos2', type='int'},
{ name='isswapanneal', type='int'},
{ name='isswapperfuse', type='int'},
})
msg.add_type('lx.gs.jade', {
name='CHuntJewelry',
id=6583949,
{ name='num', type='int'},
})
msg.add_type('lx.gs.jade', {
name='SEnhanceJade',
id=6583989,
{ name='enhancetypeid', type='int'},
{ name='num', type='int'},
{ name='addbonus', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='SChangeAttrs',
id=6583997,
{ name='combatpower', type='int'},
{ name='attrs', type='map', key='int', value='float'},
})
msg.add_type('lx.gs.family.msg', {
name='SUpdateDeclarationOrPublic',
id=6584056,
{ name='updatetype', type='int'},
{ name='newtext', type='string'},
})
msg.add_type('lx.gs.bag.msg', {
name='CBatchSell',
id=6584149,
{ name='bagtype', type='int'},
{ name='posset', type='set', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='CSweepStoryEctype',
id=6584208,
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SCancelRequestJoinFNotify',
id=6584218,
{ name='roleid', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='SChangeClimbTower',
id=6584220,
{ name='ectypeid', type='int'},
{ name='info', type='lx.gs.map.msg.ClimbTowerInfo'},
})
msg.add_type('lx.gs.activity.worldmonster.msg', {
name='CGetKillExpMonBonus',
id=6584256,
{ name='num', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='SGuardLoginNotify',
id=6584263,
{ name='idolid', type='long'},
{ name='guardid', type='long'},
{ name='guardname', type='string'},
})
msg.add_type('lx.gs.talisman', {
name='CTalismanRecycle',
id=6584317,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='SMove',
id=6584463,
{ name='srcbagtype', type='int'},
{ name='srcpos', type='int'},
{ name='destbagtype', type='int'},
{ name='destpos', type='int'},
})
msg.add_type('lx.gs.mount', {
name='SBuyRide',
id=6584488,
{ name='ridekey', type='int'},
})
msg.add_type('lx.gs.equip', {
name='CLoadEquip',
id=6584502,
{ name='pos', type='int'},
})
msg.add_type('lx.gs.bag.msg', {
name='SItemBind',
id=6584667,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='CCallAllFamilyMembers',
id=6584717,
})
msg.add_type('lx.gs.map.msg', {
name='SHeroRefreshAward',
id=6584797,
{ name='awardinfo', type='map.msg.HeroesAwardInfo'},
})
msg.add_type('lx.gs.amulet', {
name='CApplyAmuletWashResult',
id=6584825,
{ name='pageid', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SAllSomePeopleMarryNotify',
id=6584994,
{ name='content', type='string'},
{ name='proposename', type='string'},
{ name='proposegender', type='int'},
{ name='beproposedname', type='string'},
{ name='beproposegender', type='int'},
})
msg.add_type('lx.gs.marriage.msg', {
name='SPropose',
id=6585013,
{ name='proposeid', type='long'},
{ name='beproposedroleid', type='long'},
{ name='proposetype', type='int'},
{ name='proposeresult', type='int'},
})
msg.add_type('lx.gs.equip.normalequip', {
name='SPerfuseInfoUpdate',
id=6585088,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='newlevel', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='SGuardTowerMatcherUpdate',
id=6585119,
{ name='matched', type='int'},
{ name='teaminfo', type='lx.gs.map.msg.MatchTeamInfo'},
{ name='countdown', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SInviteJoinNotify',
id=6585253,
{ name='inviteroleid', type='long'},
{ name='inviterolename', type='string'},
{ name='familyid', type='long'},
{ name='familyname', type='string'},
})
msg.add_type('lx.gs.map.msg', {
name='CCancelEnrollMultiStoryEctype',
id=6585326,
})
msg.add_type('lx.gs.equip.normalequip', {
name='SAnnealEquip',
id=6585344,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='newlevel', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='CChangeLuckType',
id=6585431,
{ name='isbestlucky', type='int'},
})
msg.add_type('lx.gs.talisman', {
name='SChangeWuxingType',
id=6585537,
{ name='bagtype', type='int'},
{ name='pos', type='int'},
{ name='wuxingtype', type='int'},
})
msg.add_type('lx.gs.arena.msg', {
name='CGetChallenge',
id=6585541,
})
msg.add_type('lx.gs.map.msg', {
name='SApplyTeamSpeedSucc',
id=6585554,
})
msg.add_type('lx.gs.rank.msg', {
name='CGetMyRank',
id=6585617,
{ name='ranktype', type='int'},
})
msg.add_type('lx.gs.mount', {
name='CDeActiveRide',
id=6585681,
})
msg.add_type('lx.gs.pay', {
name='SVipLevelNotify',
id=6585686,
{ name='newlevel', type='int'},
{ name='totalcharge', type='long'},
})
msg.add_type('lx.gs.map.msg', {
name='CTransferWorldUseItem',
id=6585812,
{ name='worldid', type='int'},
{ name='position', type='map.msg.Vector3'},
})
msg.add_type('lx.gs.pet.msg', {
name='SSyncPetFragment',
id=6585901,
{ name='petfragment', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SRequestJoinFamilyNotify',
id=6585918,
{ name='roleinfo', type='lx.gs.role.msg.RoleShowInfo4'},
})
msg.add_type('lx.gs.activity.worldmonster.msg', {
name='SGetExpMonsterPosition',
id=6586010,
{ name='mapid', type='int'},
{ name='xposition', type='float'},
{ name='zposition', type='float'},
})
msg.add_type('lx.gs.treasurebowl', {
name='STreasureBowlBreak',
id=6586017,
{ name='receivedlingjing', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='CSendFlower',
id=6586055,
{ name='sendtype', type='int'},
{ name='reveiverid', type='long'},
{ name='flowers', type='list', value='lx.gs.friend.msg.FlowerInfo'},
})
msg.add_type('lx.gs.pay', {
name='CBuyVipPackage',
id=6586079,
{ name='index', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='SGetChampion',
id=6586099,
{ name='termid', type='int'},
{ name='profession', type='int'},
{ name='ctx', type='int'},
{ name='championinfo', type='lx.gs.activity.huiwu.msg.ChampionInfo'},
})
msg.add_type('lx.gs.task.msg', {
name='CAcceptFamilyTask',
id=6586140,
{ name='npcid', type='int'},
{ name='taskid', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CEnterBattleEctype',
id=6586147,
})
msg.add_type('lx.gs.family.msg', {
name='CRequestJoinAllFamily',
id=6586198,
})
msg.add_type('lx.gs.marriage.msg', {
name='CBeproposed',
id=6586200,
{ name='proposeroleid', type='long'},
{ name='proposeresult', type='int'},
{ name='proposetype', type='int'},
{ name='proposeoath', type='string'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='CGetPreselectionRoleList',
id=6586220,
{ name='profession', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='SGetFamilyLog',
id=6586221,
{ name='familyid', type='long'},
{ name='logs', type='list', value='lx.gs.family.msg.FamilyLogReport'},
})
msg.add_type('map.msg', {
name='SStop',
id=6684774,
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='CMove',
id=6684775,
{ name='position', type='map.msg.Vector3'},
{ name='target', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SMove',
id=6684776,
{ name='position', type='map.msg.Vector3'},
{ name='target', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='CStop',
id=6684782,
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='RoleProtocols',
id=6684785,
{ name='roleid', type='long'},
{ name='ptype', type='int'},
{ name='data', type='octets'},
})
msg.add_type('map.msg', {
name='SNearbyAgentLeave',
id=6684793,
{ name='agentids', type='list', value='long'},
})
msg.add_type('map.msg', {
name='CFindAgent',
id=6684804,
{ name='agenttemplateid', type='int'},
})
msg.add_type('map.msg', {
name='SFindAgent',
id=6684805,
{ name='errcode', type='int'},
{ name='agenttemplateid', type='int'},
{ name='position', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SEnterStoryEctype',
id=6684809,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='remaintime', type='long'},
{ name='remainrevivecount', type='int'},
{ name='enviroments', type='map', key='int', value='int'},
{ name='openlayouts', type='list', value='map.msg.LayoutInfo'},
{ name='activeactions', type='list', value='int'},
})
msg.add_type('map.msg', {
name='SCloseLayout',
id=6684815,
{ name='layoutid', type='int'},
})
msg.add_type('map.msg', {
name='SChangeEntry',
id=6684816,
{ name='layoutid', type='int'},
{ name='entryid', type='int'},
{ name='open', type='int'},
})
msg.add_type('map.msg', {
name='SChangeExit',
id=6684817,
{ name='layoutid', type='int'},
{ name='exitid', type='int'},
{ name='open', type='int'},
})
msg.add_type('map.msg', {
name='SChangeEnviroment',
id=6684818,
{ name='envname', type='int'},
{ name='value', type='int'},
})
msg.add_type('map.msg', {
name='SCompleteLayout',
id=6684819,
{ name='layoutid', type='int'},
})
msg.add_type('map.msg', {
name='CCloseLayout',
id=6684822,
{ name='layoutid', type='int'},
})
msg.add_type('map.msg', {
name='CDigMineBegin',
id=6684823,
{ name='mineagentid', type='long'},
})
msg.add_type('map.msg', {
name='SDigMineBegin',
id=6684824,
{ name='mineagentid', type='long'},
})
msg.add_type('map.msg', {
name='CDigMineEnd',
id=6684825,
{ name='mineagentid', type='long'},
})
msg.add_type('map.msg', {
name='SDigMineEnd',
id=6684826,
{ name='mineagentid', type='long'},
})
msg.add_type('map.msg', {
name='SMineChange',
id=6684827,
{ name='state', type='int'},
})
msg.add_type('map.msg', {
name='SNearbyPlayerEnter',
id=6684842,
{ name='playerid', type='long'},
{ name='serverid', type='int'},
{ name='gender', type='int'},
{ name='profession', type='int'},
{ name='name', type='string'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='familyname', type='string'},
{ name='dressid', type='int'},
{ name='rideid', type='int'},
{ name='ridestatus', type='int'},
{ name='titleid', type='int'},
{ name='fabaoid', type='int'},
{ name='equips', type='list', value='map.msg.EquipBrief'},
{ name='effects', type='list', value='map.msg.EffectInfo'},
{ name='pkstate', type='int'},
{ name='fightercommon', type='map.msg.FighterCommon'},
})
msg.add_type('map.msg', {
name='SNearbyNPCEnter',
id=6684843,
{ name='agentid', type='long'},
{ name='npcid', type='int'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SNearbyMonsterEnter',
id=6684844,
{ name='agentid', type='long'},
{ name='monsterid', type='int'},
{ name='fightercommon', type='map.msg.FighterCommon'},
})
msg.add_type('map.msg', {
name='SNearbyMineEnter',
id=6684845,
{ name='agentid', type='long'},
{ name='mineid', type='int'},
{ name='digger', type='long'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
{ name='state', type='int'},
})
msg.add_type('map.msg', {
name='SNearbyRuneEnter',
id=6684847,
{ name='agentid', type='long'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
{ name='runeid', type='int'},
})
msg.add_type('map.msg', {
name='SAddEffect',
id=6685176,
{ name='effect', type='map.msg.EffectInfo'},
})
msg.add_type('map.msg', {
name='XChangePetSkill',
id=6685177,
{ name='skillid', type='int'},
{ name='skilllevel', type='int'},
})
msg.add_type('map.msg', {
name='SDead',
id=6685178,
{ name='attacker', type='long'},
})
msg.add_type('map.msg', {
name='SChangeAttrs',
id=6685179,
{ name='attrs', type='map', key='int', value='float'},
})
msg.add_type('map.msg', {
name='SSkillAttack',
id=6685188,
{ name='skillid', type='int'},
{ name='attackerid', type='long'},
{ name='attackid', type='int'},
{ name='target', type='long'},
{ name='direction', type='map.msg.Vector3'},
{ name='attacks', type='list', value='map.msg.AttackResult'},
{ name='heals', type='list', value='map.msg.HealResult'},
})
msg.add_type('map.msg', {
name='CSkillPerform',
id=6685189,
{ name='skillid', type='int'},
{ name='targetid', type='long'},
{ name='direction', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SSkillPerform',
id=6685190,
{ name='retcode', type='int'},
{ name='skillid', type='int'},
{ name='attackerid', type='long'},
{ name='targetid', type='long'},
{ name='direction', type='map.msg.Vector3'},
{ name='mp', type='int'},
})
msg.add_type('map.msg', {
name='SSkillInterrupt',
id=6685191,
{ name='skillid', type='int'},
})
msg.add_type('map.msg', {
name='SEnterClimbTower',
id=6694787,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='buffbuynums', type='map', key='int', value='int'},
{ name='totalscore', type='int'},
{ name='curfloorid', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='SChangeRide',
id=6694920,
{ name='rideid', type='int'},
{ name='ridetype', type='int'},
})
msg.add_type('map.msg', {
name='SNewWaveOpen',
id=6695111,
{ name='waveid', type='int'},
})
msg.add_type('map.msg', {
name='MWorldPKKill',
id=6695256,
{ name='attacker', type='long'},
{ name='defencer', type='long'},
})
msg.add_type('map.msg', {
name='SChangeDress',
id=6695409,
{ name='dressid', type='int'},
})
msg.add_type('map.msg', {
name='SEnterPlainStoryEctype',
id=6695571,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='remaintime', type='long'},
{ name='remainrevivecount', type='int'},
{ name='enviroments', type='map', key='int', value='int'},
{ name='openlayouts', type='list', value='map.msg.LayoutInfo'},
{ name='activeactions', type='list', value='int'},
})
msg.add_type('map.msg', {
name='XLeaveMap',
id=6695623,
{ name='ctxid', type='int'},
{ name='roleid', type='long'},
{ name='mapid', type='long'},
{ name='reserve', type='byte'},
{ name='reason', type='int'},
})
msg.add_type('map.msg', {
name='SActionBegin',
id=6695765,
{ name='actionid', type='int'},
})
msg.add_type('map.msg', {
name='MRpc',
id=6695824,
{ name='ctxid', type='long'},
{ name='ptype', type='int'},
{ name='result', type='octets'},
})
msg.add_type('map.msg', {
name='CBuyBuff',
id=6695833,
{ name='buffid', type='int'},
})
msg.add_type('map.msg', {
name='MEndArenaEctype',
id=6695899,
{ name='retcode', type='int'},
{ name='opponentid', type='long'},
})
msg.add_type('map.msg', {
name='XPlayCGSetState',
id=6696059,
{ name='taskid', type='int'},
})
msg.add_type('map.msg', {
name='SOrient',
id=6696178,
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='CEctypeStatistic',
id=6696200,
})
msg.add_type('map.msg', {
name='MEnterEctypeLogger',
id=6696437,
{ name='ectypeid', type='int'},
})
msg.add_type('map.msg', {
name='SChangePKState',
id=6696443,
{ name='pkstate', type='int'},
})
msg.add_type('map.msg', {
name='SEnterTeamFight',
id=6696588,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='state', type='int'},
{ name='remaintime', type='long'},
{ name='mycamp', type='int'},
{ name='teamkillnums', type='map', key='int', value='int'},
})
msg.add_type('map.msg', {
name='XLeaveOrKickFamily',
id=6696640,
{ name='roleid', type='long'},
{ name='familyid', type='long'},
{ name='reason', type='int'},
})
msg.add_type('map.msg', {
name='SChangeRawAttrs',
id=6696702,
{ name='resethpmp', type='int'},
{ name='combatpower', type='int'},
{ name='attrs', type='list', value='float'},
})
msg.add_type('map.msg', {
name='XCreateFamilyTeam',
id=6697156,
{ name='serverid', type='int'},
{ name='level', type='int'},
{ name='members', type='list', value='map.msg.FamilyTeamMember'},
})
msg.add_type('map.msg', {
name='CRevive',
id=6697279,
{ name='revivetype', type='int'},
})
msg.add_type('map.msg', {
name='SAfterEnterFamilyStation',
id=6697426,
{ name='remaintime', type='int'},
{ name='godanimalremaintime', type='int'},
})
msg.add_type('map.msg', {
name='MEndGuardTower',
id=6697849,
{ name='ectypeid', type='int'},
{ name='errcode', type='int'},
})
msg.add_type('map.msg', {
name='MEndEctypeLogger',
id=6697924,
{ name='ectypeid', type='int'},
{ name='result', type='int'},
{ name='time', type='long'},
})
msg.add_type('map.msg', {
name='MEndFamilyParty',
id=6698280,
{ name='joinroles', type='list', value='long'},
})
msg.add_type('map.msg', {
name='SFindAgentByType',
id=6698556,
{ name='errcode', type='int'},
{ name='agenttype', type='int'},
{ name='position', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SChangeFamily',
id=6698763,
{ name='name', type='string'},
})
msg.add_type('map.msg', {
name='SEnterPrologue',
id=6698890,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='remaintime', type='long'},
{ name='remainrevivecount', type='int'},
{ name='enviroments', type='map', key='int', value='int'},
{ name='openlayouts', type='list', value='map.msg.LayoutInfo'},
{ name='activeactions', type='list', value='int'},
})
msg.add_type('map.msg', {
name='XNewSkill',
id=6698906,
{ name='skillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='SStartFamilyParty',
id=6699108,
{ name='remaintime', type='int'},
})
msg.add_type('map.msg', {
name='MEndTeamSpeed',
id=6699137,
{ name='iswin', type='int'},
{ name='ectypeid', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='MapContexProtocols',
id=6699138,
{ name='count', type='int'},
{ name='data', type='octets'},
})
msg.add_type('map.msg', {
name='SNewMonsterWave',
id=6699185,
{ name='waveindex', type='int'},
})
msg.add_type('map.msg', {
name='XAddPetBuff',
id=6699349,
{ name='buffid', type='int'},
})
msg.add_type('map.msg', {
name='MLeaveMap',
id=6699468,
{ name='ctxid', type='int'},
{ name='retcode', type='int'},
{ name='roleid', type='long'},
{ name='reserve', type='byte'},
{ name='reason', type='int'},
{ name='cur', type='map.msg.RoleMapContext'},
{ name='news', type='list', value='map.msg.RoleMapContext'},
{ name='rolenum', type='int'},
})
msg.add_type('map.msg', {
name='SEndDailyEctype',
id=6699555,
{ name='errcode', type='int'},
{ name='totalbonus', type='map.msg.Bonus'},
{ name='bonuss', type='list', value='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='CTransferWorld',
id=6699618,
{ name='portalid', type='int'},
})
msg.add_type('map.msg', {
name='RolePetProtocol',
id=6699649,
{ name='petkey', type='int'},
{ name='proto', type='gnet.ProtocolData'},
})
msg.add_type('map.msg', {
name='MFindNearbyTeam',
id=6699710,
{ name='teamlist', type='set', value='long'},
})
msg.add_type('map.msg', {
name='XCreateAttackCity',
id=6699867,
{ name='ectypeid', type='int'},
{ name='serverid', type='int'},
{ name='levelsectionid', type='int'},
{ name='roleids', type='set', value='long'},
})
msg.add_type('map.msg', {
name='SChangeLevel',
id=6699954,
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='XCreateTeamFight',
id=6700231,
{ name='ectypeid', type='int'},
{ name='serverid', type='int'},
{ name='levelgroupid', type='int'},
{ name='team1', type='list', value='map.msg.TeamFightMember'},
{ name='team2', type='list', value='map.msg.TeamFightMember'},
})
msg.add_type('map.msg', {
name='MCollect',
id=6700457,
{ name='mineid', type='int'},
})
msg.add_type('map.msg', {
name='XToOpenFamilyParty',
id=6700481,
{ name='roleid', type='long'},
{ name='eventid', type='int'},
})
msg.add_type('map.msg', {
name='XCreateEcypeOnePlayer',
id=6700496,
{ name='ectypeid', type='int'},
{ name='istaskectype', type='byte'},
{ name='serverid', type='int'},
{ name='roleid', type='long'},
{ name='profession', type='int'},
{ name='param1', type='int'},
{ name='param2', type='int'},
})
msg.add_type('map.msg', {
name='XChangeTalisman',
id=6700703,
{ name='talismanmodelid', type='int'},
{ name='skills', type='map', key='int', value='int'},
{ name='buffs', type='list', value='int'},
})
msg.add_type('map.msg', {
name='MAddBonus',
id=6700832,
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='MAddEnemy',
id=6700991,
{ name='enemyid', type='long'},
})
msg.add_type('map.msg', {
name='SCurveFlyEnd',
id=6701066,
})
msg.add_type('map.msg', {
name='SBeSkillAttack',
id=6701442,
{ name='attacks', type='list', value='map.msg.BeAttackResult'},
{ name='heals', type='list', value='map.msg.BeHealResult'},
{ name='hp', type='int'},
})
msg.add_type('map.msg', {
name='SGodAnimalLvlup',
id=6701585,
{ name='familymapid', type='long'},
{ name='bossid', type='int'},
{ name='bosslvl', type='int'},
})
msg.add_type('map.msg', {
name='SChangeVipLevel',
id=6701652,
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='XCreateMonsterAtPlayerPosition',
id=6701666,
{ name='monsterid', type='int'},
{ name='num', type='int'},
})
msg.add_type('map.msg', {
name='MEndStoryEctype',
id=6701902,
{ name='ectypeid', type='int'},
{ name='star', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='XCreateHuiWu',
id=6702180,
{ name='ectypeid', type='int'},
{ name='serverid', type='int'},
{ name='profession', type='int'},
{ name='roundindex', type='int'},
{ name='battleindex', type='int'},
{ name='roleid1', type='long'},
{ name='roleid2', type='long'},
})
msg.add_type('map.msg', {
name='XEnterMap',
id=6702334,
{ name='ctxid', type='int'},
{ name='mapid', type='long'},
{ name='player', type='map.msg.PlayerBuilder'},
})
msg.add_type('map.msg', {
name='MEnterArenaLogger',
id=6702379,
{ name='ectypeid', type='int'},
{ name='enemyroleid', type='long'},
})
msg.add_type('map.msg', {
name='SEndPersonalBossEctype',
id=6702401,
{ name='errcode', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='XUseItem',
id=6702529,
{ name='pos', type='int'},
{ name='usenumber', type='int'},
})
msg.add_type('map.msg', {
name='MapProtocol',
id=6702683,
{ name='mapid', type='long'},
{ name='proto', type='gnet.ProtocolData'},
})
msg.add_type('map.msg', {
name='SEctypeStatistic',
id=6702843,
{ name='teams', type='list', value='map.msg.TeamStatistic'},
})
msg.add_type('map.msg', {
name='SEndGuardTower',
id=6702857,
{ name='errcode', type='int'},
{ name='bonus', type='map.msg.Bonus'},
{ name='maxkill', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='SEnterHeroes',
id=6703112,
{ name='id', type='long'},
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('map.msg', {
name='SChangePetStarLevel',
id=6703386,
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='MCreateMap',
id=6703610,
{ name='retcode', type='int'},
{ name='mapid', type='long'},
})
msg.add_type('map.msg', {
name='SEndPrologue',
id=6703690,
{ name='errcode', type='int'},
})
msg.add_type('map.msg', {
name='ServicedRefs',
id=6703897,
{ name='bonus', type='map.msg.Bonus'},
{ name='vector3', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SNewFloorOpen',
id=6703937,
{ name='floorid', type='int'},
})
msg.add_type('map.msg', {
name='RoleXdbProtocol',
id=6703965,
{ name='roleid', type='long'},
{ name='proto', type='gnet.ProtocolData'},
})
msg.add_type('map.msg', {
name='CFindPath',
id=6703967,
{ name='src', type='map.msg.Vector3'},
{ name='dst', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SNewAttackCityMonster',
id=6704021,
{ name='sectionindex', type='int'},
{ name='monsterbatchindex', type='int'},
})
msg.add_type('map.msg', {
name='RoleMapProtocol',
id=6704070,
{ name='roleid', type='long'},
{ name='proto', type='gnet.ProtocolData'},
})
msg.add_type('map.msg', {
name='MPlayerKillMonster',
id=6704198,
{ name='monsters', type='map', key='int', value='int'},
{ name='inworld', type='byte'},
{ name='baseexp', type='list', value='int'},
{ name='expbonusrate', type='float'},
{ name='currency', type='list', value='int'},
{ name='pets', type='list', value='int'},
{ name='bonuss', type='list', value='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='CSetMessgeMode',
id=6704235,
{ name='mode', type='int'},
})
msg.add_type('map.msg', {
name='SEndTeamSpeed',
id=6704438,
{ name='result', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='XChangeTeam',
id=6704457,
{ name='team', type='map.msg.Team'},
})
msg.add_type('map.msg', {
name='MEndPlainStoryEctype',
id=6704487,
{ name='ectypeid', type='int'},
})
msg.add_type('map.msg', {
name='CEndClimbTowerEctype',
id=6704524,
})
msg.add_type('map.msg', {
name='COpenLayout',
id=6704620,
{ name='layoutid', type='int'},
})
msg.add_type('map.msg', {
name='MSaveLocation',
id=6704695,
{ name='id', type='long'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SPraiseMember',
id=6704699,
{ name='from', type='long'},
{ name='to', type='long'},
})
msg.add_type('map.msg', {
name='XUseMedicine',
id=6704709,
{ name='itemid', type='int'},
})
msg.add_type('map.msg', {
name='XChangePetLevel',
id=6704826,
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='XCreateEctypeMultiStory',
id=6704835,
{ name='ectypeid', type='int'},
{ name='istaskectype', type='byte'},
{ name='serverid', type='int'},
{ name='roles', type='map', key='long', value='int'},
{ name='rolesstar', type='map', key='long', value='int'},
{ name='roleusedtimes', type='map', key='long', value='int'},
})
msg.add_type('map.msg', {
name='CEatRune',
id=6704894,
{ name='runeagentid', type='long'},
})
msg.add_type('map.msg', {
name='SBeginFight',
id=6704992,
{ name='state', type='int'},
})
msg.add_type('map.msg', {
name='SLaunchFamilyGodAnimal',
id=6705015,
{ name='familymapid', type='long'},
{ name='starttime', type='long'},
{ name='endtime', type='long'},
})
msg.add_type('map.msg', {
name='XGetPlayerLocation',
id=6705093,
{ name='queryroleid', type='long'},
})
msg.add_type('map.msg', {
name='SEndArenaEctype',
id=6705171,
{ name='errcode', type='int'},
{ name='newrank', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='XChangePetAttr',
id=6705189,
{ name='attr', type='list', value='float'},
})
msg.add_type('map.msg', {
name='MEndClimbTowerEctype',
id=6705210,
{ name='retcode', type='int'},
{ name='ectypeid', type='int'},
{ name='newfloorid', type='int'},
{ name='costtime', type='int'},
{ name='lastfloorcosttime', type='int'},
{ name='firstbonus', type='map.msg.Bonus'},
{ name='normalbonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='CChangeRide',
id=6705441,
{ name='ridetype', type='int'},
})
msg.add_type('map.msg', {
name='CFindNearbyRole',
id=6705580,
})
msg.add_type('map.msg', {
name='SCurrencyGet',
id=6705628,
{ name='total', type='int'},
})
msg.add_type('map.msg', {
name='CPlayCGOver',
id=6705831,
})
msg.add_type('map.msg', {
name='SEquipPet',
id=6706003,
{ name='pet', type='map.msg.PetBuilder'},
})
msg.add_type('map.msg', {
name='SDeadCount',
id=6706158,
{ name='count', type='int'},
})
msg.add_type('map.msg', {
name='CCurveFlyBegin',
id=6706212,
{ name='portalid', type='int'},
{ name='curposition', type='map.msg.Vector3'},
{ name='dstposition', type='map.msg.Vector3'},
{ name='curveid', type='int'},
})
msg.add_type('map.msg', {
name='SChangePetSkin',
id=6706407,
{ name='skinid', type='int'},
})
msg.add_type('map.msg', {
name='CPetStop',
id=6706467,
{ name='petid', type='long'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='XChangeAmulet',
id=6706654,
{ name='amulets', type='map', key='int', value='int'},
})
msg.add_type('map.msg', {
name='SEnterPersonalBossEctype',
id=6706685,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='CEctypeDamageStatistic',
id=6706776,
})
msg.add_type('map.msg', {
name='SMonsterDrop',
id=6707094,
{ name='owner', type='long'},
{ name='bonus', type='map.msg.Bonus'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SEnterMultiStoryEctype',
id=6707575,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='remaintime', type='long'},
{ name='remainrevivecount', type='int'},
{ name='enviroments', type='map', key='int', value='int'},
{ name='openlayouts', type='list', value='map.msg.LayoutInfo'},
{ name='activeactions', type='list', value='int'},
})
msg.add_type('map.msg', {
name='SSyncTeamSpeedScore',
id=6707639,
{ name='teamscore', type='map', key='int', value='int'},
})
msg.add_type('map.msg', {
name='SChangeName',
id=6707658,
{ name='name', type='string'},
})
msg.add_type('map.msg', {
name='CPetMove',
id=6707676,
{ name='petid', type='long'},
{ name='position', type='map.msg.Vector3'},
{ name='target', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SActionEnd',
id=6707771,
{ name='actionid', type='int'},
})
msg.add_type('map.msg', {
name='SChangeHp',
id=6707905,
{ name='hp', type='int'},
})
msg.add_type('map.msg', {
name='MapContexProtocol',
id=6707916,
{ name='index', type='int'},
{ name='data', type='octets'},
})
msg.add_type('map.msg', {
name='SSyncTeamSpeedBossDamage',
id=6707949,
{ name='bossdamage', type='map', key='int', value='float'},
})
msg.add_type('map.msg', {
name='XCreateArenaEctype',
id=6707991,
{ name='ectypeid', type='int'},
{ name='serverid', type='int'},
{ name='roleid', type='long'},
{ name='profession', type='int'},
{ name='opponent', type='map.msg.PlayerBuilder'},
})
msg.add_type('map.msg', {
name='SChangePetAwakeLevel',
id=6708037,
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='CDigMineCancel',
id=6708109,
{ name='mineagentid', type='long'},
})
msg.add_type('map.msg', {
name='MEndTeamFight',
id=6708304,
{ name='result', type='int'},
{ name='quit', type='byte'},
{ name='bonus', type='map.msg.Bonus'},
{ name='evaluates', type='list', value='int'},
})
msg.add_type('map.msg', {
name='XCloseExpMonster',
id=6708455,
{ name='monsterid', type='int'},
})
msg.add_type('map.msg', {
name='SJump',
id=6708458,
})
msg.add_type('map.msg', {
name='XCreateWorldMap',
id=6708459,
{ name='serverid', type='int'},
{ name='worldid', type='int'},
{ name='lineid', type='int'},
})
msg.add_type('map.msg', {
name='SAutoStartParty',
id=6708564,
{ name='remaintime', type='int'},
})
msg.add_type('map.msg', {
name='SEnterAttackCity',
id=6708659,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='state', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='XCheckWorldRevive',
id=6708678,
{ name='revivetype', type='int'},
})
msg.add_type('map.msg', {
name='MTransferWorld',
id=6708744,
{ name='worldid', type='int'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SFindPath',
id=6708970,
{ name='path', type='list', value='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SFindNearbyTeam',
id=6708982,
{ name='teamlist', type='list', value='map.msg.TeamInfo'},
})
msg.add_type('map.msg', {
name='CJump',
id=6709011,
})
msg.add_type('map.msg', {
name='XOffline',
id=6709017,
})
msg.add_type('map.msg', {
name='XCreatExpMonster',
id=6709106,
{ name='lvlindex', type='int'},
{ name='positionindex', type='int'},
})
msg.add_type('map.msg', {
name='SKillGodAnimalNotify',
id=6709263,
{ name='membersbonus', type='list', value='map.msg.SingleRoleBonus'},
{ name='lasthitbonus', type='list', value='map.msg.SingleRoleBonus'},
{ name='luckybonus', type='list', value='map.msg.SingleRoleBonus'},
})
msg.add_type('map.msg', {
name='SEatRune',
id=6709281,
{ name='runeid', type='int'},
{ name='runeagentid', type='long'},
{ name='result', type='int'},
})
msg.add_type('map.msg', {
name='SCountDown',
id=6709421,
{ name='endtime', type='long'},
})
msg.add_type('map.msg', {
name='XKeepAlive',
id=6709426,
})
msg.add_type('map.msg', {
name='SEndAttackCity',
id=6709536,
})
msg.add_type('map.msg', {
name='XRpc',
id=6709628,
{ name='ctxid', type='long'},
{ name='ptype', type='int'},
{ name='argument', type='octets'},
})
msg.add_type('map.msg', {
name='CFindNearbyTeam',
id=6709685,
})
msg.add_type('map.msg', {
name='SBuyBuff',
id=6709727,
{ name='buffid', type='int'},
{ name='buynum', type='int'},
{ name='totalscore', type='int'},
})
msg.add_type('map.msg', {
name='XUpgradeSkill',
id=6709813,
{ name='skillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='SSkillCastBomb',
id=6709947,
{ name='skillid', type='int'},
{ name='attackerid', type='long'},
{ name='objectid', type='int'},
{ name='direction', type='map.msg.Vector3'},
{ name='position', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='MEndArenaLogger',
id=6710123,
{ name='ectypeid', type='int'},
{ name='result', type='int'},
{ name='time', type='long'},
{ name='enemyroleid', type='long'},
})
msg.add_type('map.msg', {
name='CPetSkillPerform',
id=6710134,
{ name='petid', type='long'},
{ name='skillid', type='int'},
{ name='targetid', type='long'},
{ name='direction', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SEnterTeamSpeed',
id=6710188,
{ name='levelindex', type='int'},
{ name='teamid', type='int'},
{ name='remaintime', type='long'},
{ name='teamscore', type='map', key='int', value='int'},
})
msg.add_type('map.msg', {
name='SEndFamilyTeam',
id=6710254,
{ name='errcode', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='SEnterArenaEctype',
id=6710442,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='state', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='SReady',
id=6710513,
})
msg.add_type('map.msg', {
name='MKillExpMonster',
id=6711053,
{ name='roleids', type='list', value='long'},
})
msg.add_type('map.msg', {
name='XCreateFamilyStation',
id=6711355,
{ name='familyid', type='long'},
{ name='serverid', type='int'},
{ name='members', type='list', value='long'},
{ name='godanimallvl', type='map', key='int', value='int'},
})
msg.add_type('map.msg', {
name='XChangePKState',
id=6711550,
{ name='worldid', type='int'},
{ name='pkstate', type='int'},
})
msg.add_type('map.msg', {
name='MUseItem',
id=6711632,
{ name='pos', type='int'},
{ name='usenumber', type='int'},
})
msg.add_type('map.msg', {
name='XResetRoleParty',
id=6711708,
})
msg.add_type('map.msg', {
name='MEndPersonalBossEctype',
id=6711809,
{ name='ectypeid', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='SScoreChange',
id=6711837,
{ name='totalscore', type='int'},
})
msg.add_type('map.msg', {
name='SRevive',
id=6711859,
})
msg.add_type('map.msg', {
name='SNearbyPetEnter',
id=6712006,
{ name='agentid', type='long'},
{ name='petkey', type='int'},
{ name='owenrid', type='long'},
{ name='level', type='int'},
{ name='starlevel', type='int'},
{ name='awakelevel', type='int'},
{ name='skinid', type='int'},
{ name='fightercommon', type='map.msg.FighterCommon'},
})
msg.add_type('map.msg', {
name='SChangeFabao',
id=6712073,
{ name='fabaokey', type='int'},
})
msg.add_type('map.msg', {
name='MEndMultiStoryEctype',
id=6712102,
{ name='result', type='int'},
{ name='ectypeid', type='int'},
{ name='star', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='XEvolveSkill',
id=6712165,
{ name='oldskillid', type='int'},
{ name='newskillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='SKillEvent',
id=6712179,
{ name='attackername', type='string'},
{ name='attackercamp', type='int'},
{ name='defencer', type='string'},
{ name='defencercamp', type='int'},
{ name='petkey', type='int'},
})
msg.add_type('map.msg', {
name='MEndPrologue',
id=6712198,
{ name='errcode', type='int'},
})
msg.add_type('map.msg', {
name='XEndWorldBoss',
id=6712255,
{ name='bossid', type='int'},
})
msg.add_type('map.msg', {
name='SEndMultiStoryEctype',
id=6712286,
{ name='errcode', type='int'},
{ name='ectypeid', type='int'},
{ name='star', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='SKillGodAnimal',
id=6712517,
{ name='bossid', type='int'},
{ name='isover', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='XPlayerChangeTask',
id=6712848,
{ name='taskmonsters', type='set', value='int'},
})
msg.add_type('map.msg', {
name='MKillGodAnimal',
id=6712866,
{ name='familyid', type='long'},
{ name='bossid', type='int'},
{ name='bosslvl', type='int'},
{ name='membersbonus', type='list', value='map.msg.SingleRoleBonus'},
{ name='lasthitbonus', type='list', value='map.msg.SingleRoleBonus'},
{ name='luckybonus', type='list', value='map.msg.SingleRoleBonus'},
})
msg.add_type('map.msg', {
name='SEndClimbTowerEctype',
id=6712904,
{ name='errcode', type='int'},
{ name='oldmaxfloorid', type='int'},
{ name='newmaxfloorid', type='int'},
{ name='firstbonus', type='map.msg.Bonus'},
{ name='normalbonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='COrient',
id=6712960,
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='MKillWorldBoss',
id=6712971,
{ name='lineid', type='int'},
{ name='killer', type='long'},
{ name='bossid', type='int'},
})
msg.add_type('map.msg', {
name='MEndDailyEctype',
id=6713050,
{ name='ectypeid', type='int'},
{ name='costtime', type='int'},
{ name='bonuss', type='list', value='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='SFindNearbyRole',
id=6713087,
{ name='rolelist', type='list', value='map.msg.NearbyRoleShowInfo'},
})
msg.add_type('map.msg', {
name='XCreateGuardTowerEctype',
id=6713516,
{ name='ectypeid', type='int'},
{ name='serverid', type='int'},
{ name='roles', type='map', key='long', value='int'},
{ name='zoneid', type='int'},
})
msg.add_type('map.msg', {
name='SEndTeamFight',
id=6713605,
{ name='result', type='int'},
{ name='bonus', type='map.msg.Bonus'},
{ name='evaluate', type='list', value='map.msg.PlayerEvaluate'},
})
msg.add_type('map.msg', {
name='SEndPlainStoryEctype',
id=6713627,
{ name='errcode', type='int'},
})
msg.add_type('map.msg', {
name='MGodAnimalActEnd',
id=6713694,
{ name='familyid', type='long'},
})
msg.add_type('map.msg', {
name='SEctypeDamageStatistic',
id=6713705,
{ name='agentdamages', type='map', key='long', value='long'},
})
msg.add_type('map.msg', {
name='SEndHuiWu',
id=6713746,
{ name='result', type='int'},
{ name='roundindex', type='int'},
{ name='battleindex', type='int'},
})
msg.add_type('map.msg', {
name='XCreateTeamSpeed',
id=6713831,
{ name='ectypeid', type='int'},
{ name='serverid', type='int'},
{ name='levelgroupid', type='int'},
{ name='team1', type='list', value='long'},
{ name='team2', type='list', value='long'},
{ name='rewardrole', type='list', value='long'},
})
msg.add_type('map.msg', {
name='XCreateWorldBoss',
id=6713853,
{ name='bossid', type='int'},
{ name='monsterid', type='int'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='CMoveToDefaultPosition',
id=6713921,
})
msg.add_type('map.msg', {
name='SEnterFamilyTeam',
id=6714053,
{ name='ectypeid', type='int'},
{ name='mapid', type='long'},
{ name='monsterwaveindex', type='int'},
})
msg.add_type('map.msg', {
name='SEnterHuiWu',
id=6714079,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='state', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='XTransfer',
id=6714126,
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='CCurveFlyEnd',
id=6714226,
{ name='orient', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='SChangeTeamKillNum',
id=6714265,
{ name='camp', type='int'},
{ name='killnum', type='int'},
})
msg.add_type('map.msg', {
name='SEnterWorld',
id=6714464,
{ name='mapid', type='long'},
{ name='worldid', type='int'},
{ name='lineid', type='int'},
})
msg.add_type('map.msg', {
name='MGetPlayerLocation',
id=6714528,
{ name='queryroleid', type='long'},
{ name='maptype', type='int'},
{ name='worldid', type='int'},
{ name='lineid', type='int'},
{ name='position', type='map.msg.Vector3'},
})
msg.add_type('map.msg', {
name='XChangeRide',
id=6714576,
{ name='rideid', type='int'},
})
msg.add_type('map.msg', {
name='XHuiWuFightBegin',
id=6714607,
{ name='player1', type='map.msg.PlayerBuilder'},
{ name='player2', type='map.msg.PlayerBuilder'},
})
msg.add_type('map.msg', {
name='MKillTaskMonster',
id=6714708,
{ name='roles', type='list', value='long'},
{ name='monsterid', type='int'},
})
msg.add_type('map.msg', {
name='SOpenLayout',
id=6714754,
{ name='layout', type='map.msg.LayoutInfo'},
})
msg.add_type('map.msg', {
name='SFlyWeaponAttack',
id=6714797,
{ name='skillid', type='int'},
{ name='attackerid', type='long'},
{ name='objectid', type='int'},
{ name='attacks', type='list', value='map.msg.AttackResult'},
})
msg.add_type('map.msg', {
name='SCurveFlyBegin',
id=6714969,
{ name='portalid', type='int'},
{ name='curposition', type='map.msg.Vector3'},
{ name='dstposition', type='map.msg.Vector3'},
{ name='curveid', type='int'},
})
msg.add_type('map.msg', {
name='SEndStoryEctype',
id=6714994,
{ name='errcode', type='int'},
{ name='isfirst3star', type='int'},
{ name='star', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='MEndHuiWu',
id=6715172,
{ name='result', type='int'},
{ name='profession', type='int'},
{ name='roundindex', type='int'},
{ name='battleindex', type='int'},
})
msg.add_type('map.msg', {
name='SChangeTitle',
id=6715231,
{ name='titleid', type='int'},
})
msg.add_type('map.msg', {
name='MEndFamilyTeam',
id=6715262,
{ name='errcode', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='SEnterGuardTower',
id=6715338,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='curwaveid', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='SEctypeMemberEnter',
id=6715479,
{ name='id', type='long'},
{ name='name', type='string'},
{ name='gender', type='int'},
{ name='profession', type='int'},
{ name='level', type='int'},
{ name='familyname', type='string'},
{ name='serverid', type='int'},
{ name='viplevel', type='int'},
})
msg.add_type('map.msg', {
name='MEnterMap',
id=6715524,
{ name='ctxid', type='int'},
{ name='retcode', type='int'},
{ name='roleid', type='long'},
{ name='cur', type='map.msg.RoleMapContext'},
{ name='rolenum', type='int'},
})
msg.add_type('map.msg', {
name='MCheckWorldRevive',
id=6715708,
{ name='revivetype', type='int'},
})
msg.add_type('map.msg', {
name='CFindAgentByType',
id=6715711,
{ name='agenttype', type='int'},
})
msg.add_type('map.msg', {
name='SEnterDailyEctype',
id=6716058,
{ name='id', type='long'},
{ name='ectypeid', type='int'},
{ name='waveindex', type='int'},
{ name='remaintime', type='long'},
})
msg.add_type('map.msg', {
name='CReady',
id=6716137,
})
msg.add_type('map.msg', {
name='CClientActionEnd',
id=6716193,
{ name='actionid', type='int'},
})
msg.add_type('map.msg', {
name='SChangeEquip',
id=6716289,
{ name='equips', type='list', value='map.msg.EquipBrief'},
})
msg.add_type('map.msg', {
name='CPraiseMember',
id=6716432,
{ name='member', type='long'},
})
msg.add_type('map.msg', {
name='MEndHeroes',
id=6716495,
{ name='retcode', type='int'},
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('map.msg', {
name='SDigMineCancel',
id=6716866,
{ name='mineagentid', type='long'},
})
msg.add_type('map.msg', {
name='SUnequipPet',
id=6716926,
{ name='petmodelid', type='int'},
})
msg.add_type('map.msg', {
name='SEndHeroes',
id=6717362,
{ name='retcode', type='int'},
{ name='groupid', type='int'},
{ name='ectypeid', type='int'},
{ name='awardinfo', type='map.msg.HeroesAwardInfo'},
})
msg.add_type('map.msg', {
name='SRemoveEffect',
id=6717374,
{ name='id', type='int'},
})
msg.add_type('map.msg', {
name='SSkillCastFlyWeapon',
id=6717431,
{ name='skillid', type='int'},
{ name='attackerid', type='long'},
{ name='objectid', type='int'},
{ name='direction', type='map.msg.Vector3'},
})
msg.add_type('gnet', {
name='ActivationCodeErr',
ERR_SUCCESS = 0,
ERR_FORMATE_INVALID = 1,
ERR_INVALID = 2,
ERR_TYPE_NOT_MATCH = 3,
ERR_CODE_IS_USED = 4,
ERR_CODE_IS_EXPIRATED = 5,
ERR_CODE_IS_NOT_OPEN = 6,
ERR_FUNCTION_IS_CLOSED = 7,
ERR_PLATFORM_NOT_MATCH = 8,
ERR_HAS_ALEADY_ACTIVATED = 9,
ERR_NETWORK = 10,
ERR_EXCEED_DAY_USENUM = 11,
ERR_EXCEED_ALL_USENUM = 12,
ERR_INTERNAL = 13,
ERR_LEVEL_TOO_LOWE = 15,
ERR_LEVEL_TOO_HIGH = 16,
{ name='code', type='int'},
})
msg.add_type('gnet', {
name='ErrCode',
ERR_SUCCESS = 0,
ERR_PASSWD_INVALID = 1,
ERR_PASSWD_TRY_EXCEED = 2,
ERR_LOGINIP_TRY_EXCEED = 3,
ERR_KICK_BY_ANOTHER_USER = 4,
ERR_MULTILOGIN = 5,
ERR_AUANY_LOGIN_EXCEPTION = 6,
ERR_AUANY_LOGIN_NET_ERROR = 7,
ERR_SERVER_OVERLOAD = 8,
ERR_LINK_NOT_CONNECT_DELIVER = 9,
ERR_LINK_NOT_CONNECT_GS = 10,
ERR_ROLEFORBID = 11,
{ name='code', type='int'},
})
msg.add_type('gnet', {
name='PlatType',
TEST = 0,
ONESDK = 1,
{ name='plat', type='int'},
})
msg.add_type('gnet', {
name='ProtocolData',
{ name='ptype', type='int'},
{ name='pdata', type='octets'},
})
msg.add_type('gnet', {
name='ServerLoad',
GREEN = 1,
YELLOW = 2,
RED = 3,
UNAVAILABLE = 4,
{ name='load', type='byte'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='BattlePair',
{ name='role1', type='lx.gs.activity.huiwu.msg.BattleRole'},
{ name='role2', type='lx.gs.activity.huiwu.msg.BattleRole'},
{ name='state', type='int'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='BattleRole',
{ name='name', type='string'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='ChampionInfo',
{ name='showinfo', type='lx.gs.role.msg.RoleShowInfo1'},
{ name='awardword', type='string'},
{ name='worshipnum', type='int'},
{ name='createtime', type='long'},
})
msg.add_type('lx.gs.activity.huiwu.msg', {
name='PreselectionRole',
{ name='roleid', type='long'},
{ name='name', type='string'},
{ name='combatpower', type='int'},
{ name='beguessnum', type='int'},
})
msg.add_type('lx.gs.activity.msg', {
name='ActivityInfo',
{ name='id', type='int'},
{ name='isopen', type='byte'},
{ name='opentime', type='long'},
{ name='closetime', type='long'},
{ name='status', type='int'},
})
msg.add_type('lx.gs.activity.worldboss.msg', {
name='WorldBossInfo',
{ name='bossid', type='int'},
{ name='opentime', type='long'},
{ name='isopen', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='AmuletPage',
{ name='pageindex', type='int'},
{ name='propmap', type='map', key='int', value='lx.gs.amulet.AmuletPropperty'},
})
msg.add_type('lx.gs.amulet', {
name='AmuletPropperty',
{ name='propindex', type='int'},
{ name='islock', type='int'},
{ name='skillid', type='int'},
{ name='professionid', type='int'},
{ name='addlevel', type='int'},
})
msg.add_type('lx.gs.amulet', {
name='RoleAmuletInfo',
{ name='pagemap', type='map', key='int', value='lx.gs.amulet.AmuletPage'},
})
msg.add_type('lx.gs.arena.msg', {
name='ChallangeInfo',
{ name='rank', type='int'},
{ name='roleinfo', type='lx.gs.role.msg.RoleShowInfo1'},
{ name='pets', type='list', value='lx.gs.role.msg.PetBrief'},
})
msg.add_type('lx.gs.arena.msg', {
name='FightReport',
{ name='fighttime', type='long'},
{ name='challengetype', type='int'},
{ name='succ', type='int'},
{ name='oldrank', type='int'},
{ name='newrank', type='int'},
{ name='opponentname', type='string'},
})
msg.add_type('lx.gs.chat.msg', {
name='Content',
{ name='senderid', type='long'},
{ name='sendername', type='string'},
{ name='senderprofession', type='int'},
{ name='sendergender', type='int'},
{ name='receiverid', type='long'},
{ name='receivername', type='string'},
{ name='text', type='string'},
{ name='bagtype', type='int'},
{ name='item', type='octets'},
{ name='voiceid', type='int'},
{ name='voiceduration', type='float'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='Findbacktype',
JINBI_FIND = 1,
YUANBAO_FIND = 2,
JINBI_FINDALL = 3,
YUANBAO_FINDALL = 4,
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='Undoactiveinfo',
{ name='eventtype', type='int'},
{ name='undotimes', type='int'},
{ name='costjinbi', type='long'},
{ name='costyuanbao', type='long'},
{ name='jinbifindbackbonus', type='map.msg.Bonus'},
{ name='yuanbaofindbackbonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.dailyactivity.msg', {
name='Undoactiveinfos',
{ name='undoactive', type='map', key='int', value='lx.gs.dailyactivity.msg.Undoactiveinfo'},
})
msg.add_type('lx.gs.dress', {
name='Dress',
{ name='dresskey', type='int'},
{ name='expiretime', type='long'},
})
msg.add_type('lx.gs.equip', {
name='AccessoryProp',
{ name='mainprop', type='list', value='lx.gs.equip.PropInfo'},
{ name='extraprop', type='list', value='lx.gs.equip.PropInfo'},
})
msg.add_type('lx.gs.equip', {
name='Equip',
{ name='equipid', type='long'},
{ name='modelid', type='int'},
{ name='position', type='int'},
{ name='expiretime', type='long'},
{ name='isbind', type='int'},
{ name='normalequip', type='lx.gs.equip.NormalEquipProp'},
{ name='accessory', type='lx.gs.equip.AccessoryProp'},
})
msg.add_type('lx.gs.equip', {
name='NormalEquipProp',
{ name='anneallevel', type='int'},
{ name='perfuselevel', type='int'},
})
msg.add_type('lx.gs.equip', {
name='PropInfo',
{ name='key', type='int'},
{ name='val', type='float'},
})
msg.add_type('lx.gs.exchange.msg', {
name='ExchangeItem',
{ name='id', type='long'},
{ name='itemid', type='int'},
{ name='num', type='int'},
{ name='aprice', type='int'},
{ name='bindtype', type='int'},
{ name='anneallevel', type='int'},
{ name='perfuselevel', type='int'},
{ name='prop', type='lx.gs.equip.AccessoryProp'},
})
msg.add_type('lx.gs.exchange.msg', {
name='ExchangeLog',
{ name='seller', type='long'},
{ name='buyer', type='long'},
{ name='buyername', type='string'},
{ name='item', type='lx.gs.exchange.msg.ExchangeItem'},
{ name='time', type='long'},
})
msg.add_type('lx.gs.exchange.msg', {
name='QueryResult',
{ name='totalnum', type='int'},
{ name='items', type='list', value='lx.gs.exchange.msg.ExchangeItem'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyActivity',
{ name='godanimalinfo', type='map', key='int', value='lx.gs.family.msg.FamilyGodAnimal'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyBasicInfo',
{ name='familyid', type='long'},
{ name='familyname', type='string'},
{ name='flevel', type='int'},
{ name='money', type='long'},
{ name='curlvlbuilddegree', type='int'},
{ name='totalbuilddegree', type='int'},
{ name='totaobanggong', type='long'},
{ name='declaration', type='string'},
{ name='publicinfo', type='string'},
{ name='publictime', type='long'},
{ name='malllevel', type='int'},
{ name='membernum', type='int'},
{ name='chiefid', type='long'},
{ name='chiefname', type='string'},
{ name='chieflvl', type='int'},
{ name='rank', type='int'},
{ name='godanimalstarttime', type='long'},
{ name='godanimalendtime', type='long'},
{ name='familyrequestinfo', type='int'},
{ name='familypartylastopentime', type='long'},
{ name='familypartylastcalltime', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyGodAnimal',
{ name='animalid', type='int'},
{ name='animallevel', type='int'},
{ name='exp', type='long'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyJobStaffList',
{ name='staffs', type='list', value='long'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyLogReport',
TYPE_JOIN_FAMILY = 1,
TYPE_PROMOTION = 2,
TYPE_QUIT_FAMILY = 3,
TYPE_CHIEF_TRANSFER = 4,
TYPE_UPLEVEL_FAMILY = 5,
TYPE_PRAY = 6,
TYPE_RAISE_ANIMAL = 7,
TYPE_CREATE_FAMILY = 8,
TYPE_UPLEVEL_MALL = 9,
TYPE_KICKOUT_MEMBER = 10,
{ name='actiontype', type='int'},
{ name='roleid', type='long'},
{ name='actiontime', type='long'},
{ name='rolename', type='string'},
{ name='number', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyMember',
{ name='roleid', type='long'},
{ name='jointime', type='long'},
{ name='pcontribution', type='long'},
{ name='dailybuild', type='long'},
{ name='familyjob', type='int'},
{ name='rolename', type='string'},
{ name='lastonlinetime', type='long'},
{ name='isonline', type='int'},
{ name='attackpower', type='int'},
{ name='level', type='int'},
{ name='totalquitnum', type='int'},
{ name='viplevel', type='int'},
{ name='professiontype', type='int'},
{ name='gender', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilySkill',
{ name='skillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('lx.gs.family.msg', {
name='FamilyWelfare',
{ name='skills', type='map', key='int', value='lx.gs.family.msg.FamilySkill'},
{ name='maxskilllevel', type='int'},
})
msg.add_type('lx.gs.fragment', {
name='Fragment',
{ name='fragmentid', type='long'},
{ name='modelid', type='int'},
{ name='position', type='int'},
{ name='count', type='int'},
{ name='expiretime', type='long'},
{ name='isbind', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='EnemyShowInfo',
{ name='roleid', type='long'},
{ name='rolename', type='string'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='attackpower', type='int'},
{ name='online', type='int'},
{ name='time', type='long'},
{ name='killtime', type='int'},
{ name='bekilltime', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='FlowerInfo',
{ name='flowerid', type='int'},
{ name='flowernum', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='FriendInfo',
{ name='roleinfo', type='lx.gs.friend.msg.RoleShowInfo'},
{ name='charmdegree', type='int'},
{ name='frienddegree', type='int'},
{ name='relation', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='IdolAwardClaim',
{ name='claiminfo', type='set', value='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='IdolInfo',
{ name='charm', type='int'},
{ name='guardid', type='long'},
{ name='guardname', type='string'},
{ name='guardtime', type='long'},
{ name='guarddegree', type='long'},
})
msg.add_type('lx.gs.friend.msg', {
name='MMInfoList',
{ name='mmlist', type='list', value='lx.gs.friend.msg.RoleShowInfo'},
})
msg.add_type('lx.gs.friend.msg', {
name='RoleFriendInfo',
{ name='idolfrienddegree', type='map', key='long', value='int'},
{ name='idolawardclaiminfo', type='map', key='long', value='lx.gs.friend.msg.IdolAwardClaim'},
{ name='relations', type='map', key='int', value='lx.gs.friend.msg.MMInfoList'},
{ name='allowfriendgetmm', type='int'},
{ name='allowstrangergetmm', type='int'},
})
msg.add_type('lx.gs.friend.msg', {
name='RoleShowInfo',
{ name='roleid', type='long'},
{ name='rolename', type='string'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='attackpower', type='int'},
{ name='online', type='int'},
{ name='time', type='long'},
})
msg.add_type('lx.gs.item', {
name='Item',
{ name='itemid', type='long'},
{ name='modelid', type='int'},
{ name='position', type='int'},
{ name='count', type='int'},
{ name='expiretime', type='long'},
{ name='isbind', type='int'},
})
msg.add_type('lx.gs.jade', {
name='Jade',
{ name='level', type='int'},
{ name='bonus', type='int'},
})
msg.add_type('lx.gs.jade', {
name='Jewelry',
{ name='id', type='int'},
{ name='level', type='int'},
{ name='exp', type='int'},
})
msg.add_type('lx.gs.jade', {
name='RoleJadeInfo',
{ name='jade', type='lx.gs.jade.Jade'},
{ name='holenum', type='int'},
{ name='jewelry', type='map', key='int', value='lx.gs.jade.Jewelry'},
{ name='jewelrybag', type='list', value='lx.gs.jade.Jewelry'},
{ name='jewelrygetlevel', type='int'},
})
msg.add_type('lx.gs.leaderboard.msg', {
name='BoardEntry',
{ name='id', type='long'},
{ name='name', type='string'},
{ name='val1', type='long'},
{ name='val2', type='int'},
})
msg.add_type('lx.gs.leaderboard.msg', {
name='BoardInfo',
{ name='info', type='list', value='lx.gs.leaderboard.msg.BoardEntry'},
})
msg.add_type('lx.gs.limit.msg', {
name='CoolDown',
{ name='id', type='long'},
{ name='expiretime', type='long'},
})
msg.add_type('lx.gs.limit.msg', {
name='Limit',
{ name='id', type='long'},
{ name='typenums', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.login', {
name='RoleDetail',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='rolename', type='string'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='level', type='int'},
{ name='vipexp', type='long'},
{ name='viplevel', type='int'},
{ name='familyid', type='long'},
{ name='currencys', type='map', key='int', value='long'},
{ name='combatpower', type='int'},
{ name='attrs', type='map', key='int', value='float'},
{ name='todaytotaladdmonsterexp', type='long'},
{ name='changenametimes', type='int'},
{ name='creatroletime', type='long'},
})
msg.add_type('lx.gs.mail.msg', {
name='Mail',
{ name='id', type='int'},
{ name='mailid', type='int'},
{ name='sendtime', type='long'},
{ name='title', type='string'},
{ name='content', type='string'},
{ name='accessory', type='map.msg.Bonus'},
{ name='params', type='list', value='string'},
{ name='read', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='ChapterInfo',
{ name='sectionstars', type='list', value='int'},
{ name='obtainrewardindexs', type='list', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='ClimbTowerInfo',
{ name='maxfloorid', type='int'},
{ name='costtime', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='DailyInfo',
{ name='maxvalue', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='EnrollBriefInfo',
{ name='roleid', type='long'},
{ name='gender', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='HeroesGroupInfo',
{ name='refreshtime', type='int'},
{ name='ectypeid', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='LineInfo',
{ name='lineid', type='int'},
{ name='rolenum', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='MatchTeamInfo',
{ name='members', type='list', value='lx.gs.role.msg.RoleShowInfo4'},
})
msg.add_type('lx.gs.map.msg', {
name='MultiStoryInfo',
{ name='beststar', type='int'},
})
msg.add_type('lx.gs.map.msg', {
name='TeamFightInfo',
{ name='weekscore', type='int'},
{ name='todaywinnum', type='int'},
{ name='obtaintodaywinreward', type='byte'},
{ name='todayfightnum', type='int'},
{ name='obtainscorerewards', type='list', value='int'},
})
msg.add_type('lx.gs.map.msg', {
name='TimeRange',
{ name='start', type='string'},
{ name='xend', type='string'},
})
msg.add_type('lx.gs.mount', {
name='Ride',
{ name='ridekey', type='int'},
{ name='expiretime', type='long'},
})
msg.add_type('lx.gs.notice.msg', {
name='Notice',
{ name='content', type='octets'},
})
msg.add_type('lx.gs.pay', {
name='Item',
{ name='itemcfgid', type='int'},
{ name='count', type='int'},
})
msg.add_type('lx.gs.pay', {
name='PayAward',
STATUS_NOT_PAY = 0,
STATUS_NOT_GET_AWARD = 1,
STATUS_GET_AWARD = 2,
{ name='status', type='int'},
{ name='awarditems', type='list', value='lx.gs.pay.Item'},
})
msg.add_type('lx.gs.pay', {
name='Product',
{ name='productid', type='int'},
{ name='rmb', type='int'},
{ name='money', type='int'},
{ name='firstpaymoney', type='int'},
{ name='payreturnmoney', type='int'},
})
msg.add_type('lx.gs.pet', {
name='Pet',
{ name='modelid', type='int'},
{ name='activeskinid', type='int'},
{ name='skinidlist', type='list', value='int'},
{ name='level', type='int'},
{ name='exp', type='long'},
{ name='starlevel', type='int'},
{ name='skills', type='map', key='int', value='int'},
{ name='awakelevel', type='int'},
{ name='attrs', type='map', key='int', value='float'},
{ name='combatpower', type='long'},
})
msg.add_type('lx.gs.pickcard.msg', {
name='PickBonusInfo',
{ name='issplit', type='int'},
{ name='bonus', type='map.msg.Bonus'},
{ name='splitbonus', type='map.msg.Bonus'},
})
msg.add_type('lx.gs.rank.msg', {
name='FamilyRankInfo',
{ name='id', type='long'},
{ name='name', type='string'},
{ name='level', type='int'},
{ name='value', type='long'},
{ name='chiefid', type='long'},
})
msg.add_type('lx.gs.rank.msg', {
name='GeneralRankInfo',
{ name='roleid', type='long'},
{ name='name', type='string'},
{ name='level', type='int'},
{ name='value', type='long'},
})
msg.add_type('lx.gs.rank.msg', {
name='RankInfo',
{ name='ranktype', type='int'},
{ name='snaprank', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='CurrencyAddRecord',
{ name='ctype', type='int'},
{ name='add', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='EquipInfo',
{ name='modelid', type='int'},
{ name='normalequip', type='lx.gs.equip.NormalEquipProp'},
{ name='accessory', type='lx.gs.equip.AccessoryProp'},
})
msg.add_type('lx.gs.role.msg', {
name='PetBrief',
{ name='petkey', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='PetExp',
{ name='modelid', type='int'},
{ name='level', type='int'},
{ name='exp', type='long'},
})
msg.add_type('lx.gs.role.msg', {
name='RoleBrief',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='rolename', type='string'},
{ name='profession', type='int'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='gender', type='int'},
{ name='dressid', type='int'},
{ name='equips', type='list', value='map.msg.EquipBrief'},
})
msg.add_type('lx.gs.role.msg', {
name='RoleShowInfo1',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='equips', type='list', value='map.msg.EquipBrief'},
{ name='dressid', type='int'},
{ name='combatpower', type='int'},
})
msg.add_type('lx.gs.role.msg', {
name='RoleShowInfo3',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='combatpower', type='int'},
{ name='equips', type='list', value='map.msg.EquipBrief'},
{ name='dressid', type='int'},
{ name='title', type='int'},
{ name='familyname', type='string'},
{ name='familyjob', type='int'},
{ name='familylevel', type='int'},
{ name='lovername', type='string'},
{ name='fightattrs', type='map', key='int', value='float'},
{ name='lastonlinetime', type='long'},
})
msg.add_type('lx.gs.role.msg', {
name='RoleShowInfo4',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='viplevel', type='int'},
{ name='level', type='int'},
{ name='gender', type='int'},
{ name='combatpower', type='int'},
{ name='lastonlinetime', type='long'},
{ name='familyid', type='long'},
})
msg.add_type('lx.gs.role.msg', {
name='RoleShowInfo5',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='combatpower', type='int'},
{ name='equips', type='list', value='lx.gs.role.msg.EquipInfo'},
{ name='dressid', type='int'},
{ name='title', type='int'},
{ name='familyname', type='string'},
{ name='familyjob', type='int'},
{ name='familylevel', type='int'},
{ name='lovername', type='string'},
{ name='fightattrs', type='map', key='int', value='float'},
{ name='lastonlinetime', type='long'},
})
msg.add_type('lx.gs.role.title.msg', {
name='GroupTitle',
{ name='titleinfo', type='list', value='lx.gs.role.title.msg.Title'},
})
msg.add_type('lx.gs.role.title.msg', {
name='RoleTitle',
{ name='activekey', type='int'},
{ name='activetype', type='int'},
{ name='titles', type='map', key='int', value='lx.gs.role.title.msg.GroupTitle'},
})
msg.add_type('lx.gs.role.title.msg', {
name='Title',
STATE_GET = 1,
STATE_ACTIVED = 2,
{ name='titlekey', type='int'},
{ name='titletype', type='int'},
{ name='state', type='int'},
{ name='gettime', type='long'},
{ name='activetime', type='long'},
{ name='expiretime', type='long'},
})
msg.add_type('lx.gs.skill.msg', {
name='SkillInfo',
{ name='skillid', type='int'},
{ name='level', type='int'},
})
msg.add_type('lx.gs.storynote.msg', {
name='Chapter',
{ name='notes', type='list', value='int'},
})
msg.add_type('lx.gs.talisman', {
name='Talisman',
{ name='talismanid', type='long'},
{ name='modelid', type='int'},
{ name='pos', type='int'},
{ name='isbind', type='int'},
{ name='normalexp', type='long'},
{ name='normallevel', type='int'},
{ name='starexp', type='int'},
{ name='starlevel', type='int'},
{ name='wuxingtype', type='int'},
{ name='wuxingvalue', type='int'},
{ name='awakelevel', type='int'},
{ name='skills', type='map', key='int', value='int'},
{ name='combatpower', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='FamilyTaskInfo',
{ name='taskid', type='int'},
{ name='npcid', type='int'},
})
msg.add_type('lx.gs.task.msg', {
name='TaskInfo',
{ name='taskid', type='int'},
{ name='counter', type='map', key='int', value='int'},
})
msg.add_type('lx.gs.team.msg', {
name='RoleTeamInfo',
{ name='teamid', type='long'},
{ name='autoacceptrequest', type='int'},
{ name='autoacceptinvite', type='int'},
})
msg.add_type('lx.gs.team.msg', {
name='Team',
{ name='teamid', type='long'},
{ name='leaderid', type='long'},
{ name='createtime', type='long'},
{ name='members', type='map', key='long', value='lx.gs.team.msg.TeamMember'},
})
msg.add_type('lx.gs.team.msg', {
name='TeamMember',
{ name='roleid', type='long'},
{ name='jointime', type='long'},
{ name='follow', type='int'},
{ name='roleinfo', type='lx.gs.role.msg.RoleShowInfo4'},
})
msg.add_type('map.msg', {
name='AgentBuilder',
{ name='agentid', type='long'},
{ name='atype', type='int'},
{ name='subtype', type='int'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
{ name='bodyheight', type='float'},
{ name='bodyradius', type='float'},
})
msg.add_type('map.msg', {
name='AttackResult',
{ name='defencerid', type='long'},
{ name='ismiss', type='byte'},
{ name='iscrit', type='byte'},
{ name='isexcellent', type='byte'},
{ name='islucky', type='byte'},
{ name='attack', type='int'},
{ name='hp', type='int'},
})
msg.add_type('map.msg', {
name='BeAttackResult',
{ name='ismiss', type='byte'},
{ name='iscrit', type='byte'},
{ name='isexcellent', type='byte'},
{ name='islucky', type='byte'},
{ name='attack', type='int'},
})
msg.add_type('map.msg', {
name='BeHealResult',
{ name='heal', type='int'},
})
msg.add_type('map.msg', {
name='Bonus',
{ name='bindtype', type='int'},
{ name='items', type='map', key='int', value='int'},
})
msg.add_type('map.msg', {
name='EffectInfo',
{ name='id', type='int'},
{ name='overlaynum', type='int'},
{ name='level', type='int'},
})
msg.add_type('map.msg', {
name='EquipBrief',
{ name='equipkey', type='int'},
{ name='anneallevel', type='int'},
{ name='perfuselevel', type='int'},
})
msg.add_type('map.msg', {
name='FamilyTeamMember',
{ name='roleid', type='long'},
{ name='hasbonus', type='byte'},
})
msg.add_type('map.msg', {
name='FighterBuilder',
{ name='basic', type='map.msg.AgentBuilder'},
{ name='camp', type='int'},
{ name='appeartype', type='int'},
})
msg.add_type('map.msg', {
name='FighterCommon',
{ name='isdead', type='int'},
{ name='isrevive', type='int'},
{ name='isborn', type='int'},
{ name='camp', type='int'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
{ name='targetposition', type='map.msg.Vector3'},
{ name='skills', type='map', key='int', value='int'},
{ name='attrs', type='map', key='int', value='float'},
})
msg.add_type('map.msg', {
name='HealResult',
{ name='defencerid', type='long'},
{ name='heal', type='int'},
{ name='hp', type='int'},
})
msg.add_type('map.msg', {
name='HeroesAwardInfo',
{ name='leftrefreshcount', type='int'},
{ name='refreshcost', type='int'},
{ name='ectypeid', type='int'},
{ name='bonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='LayoutInfo',
{ name='id', type='int'},
{ name='completed', type='int'},
{ name='openentryids', type='list', value='int'},
{ name='openexitids', type='list', value='int'},
{ name='showglobaltips', type='byte'},
})
msg.add_type('map.msg', {
name='MemberInfo',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='viplevel', type='int'},
{ name='level', type='int'},
{ name='gender', type='int'},
{ name='combatpower', type='int'},
})
msg.add_type('map.msg', {
name='MemberStatistic',
{ name='name', type='string'},
{ name='ownername', type='string'},
{ name='damage', type='int'},
{ name='kill', type='int'},
{ name='rune', type='int'},
})
msg.add_type('map.msg', {
name='NearbyRoleShowInfo',
{ name='roleid', type='long'},
{ name='serverid', type='int'},
{ name='name', type='string'},
{ name='profession', type='int'},
{ name='gender', type='int'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='combatpower', type='int'},
})
msg.add_type('map.msg', {
name='PetBuilder',
{ name='basic', type='map.msg.FighterBuilder'},
{ name='ownerid', type='long'},
{ name='level', type='int'},
{ name='starlevel', type='int'},
{ name='awakelevel', type='int'},
{ name='skinid', type='int'},
{ name='skills', type='map', key='int', value='int'},
{ name='hp', type='int'},
{ name='mp', type='int'},
{ name='attrs', type='list', value='float'},
{ name='buffs', type='list', value='int'},
{ name='defaultrelateownerposition', type='map.msg.Vector3'},
{ name='serverai', type='byte'},
})
msg.add_type('map.msg', {
name='PetMapContext',
{ name='hp', type='int'},
{ name='mp', type='int'},
{ name='deathtime', type='int'},
{ name='skills', type='map', key='int', value='long'},
})
msg.add_type('map.msg', {
name='PlayerBuilder',
{ name='basic', type='map.msg.FighterBuilder'},
{ name='serverid', type='int'},
{ name='gender', type='int'},
{ name='profession', type='int'},
{ name='name', type='string'},
{ name='level', type='int'},
{ name='viplevel', type='int'},
{ name='familyname', type='string'},
{ name='iscantakeparty', type='int'},
{ name='taskmonsters', type='set', value='int'},
{ name='dressid', type='int'},
{ name='rideid', type='int'},
{ name='ridestatus', type='int'},
{ name='titleid', type='int'},
{ name='fabaoid', type='int'},
{ name='fabaoskills', type='map', key='int', value='int'},
{ name='fabaobuffs', type='list', value='int'},
{ name='roleskills', type='map', key='int', value='int'},
{ name='amulets', type='map', key='int', value='int'},
{ name='equips', type='list', value='map.msg.EquipBrief'},
{ name='attrs', type='list', value='float'},
{ name='combatpower', type='int'},
{ name='pkstate', type='int'},
{ name='team', type='map.msg.Team'},
{ name='pets', type='list', value='map.msg.PetBuilder'},
{ name='last', type='list', value='map.msg.RoleMapContext'},
{ name='cur', type='map.msg.RoleMapContext'},
{ name='ready', type='byte'},
})
msg.add_type('map.msg', {
name='PlayerEvaluate',
{ name='name', type='string'},
{ name='camp', type='int'},
{ name='damage', type='int'},
{ name='continuekill', type='int'},
{ name='kill', type='int'},
{ name='evaluates', type='list', value='int'},
})
msg.add_type('map.msg', {
name='RoleMapContext',
{ name='isnew', type='byte'},
{ name='info1', type='map.msg.RoleMapInfo1'},
{ name='info2', type='map.msg.RoleMapInfo2'},
})
msg.add_type('map.msg', {
name='RoleMapInfo1',
{ name='mapid', type='long'},
{ name='position', type='map.msg.Vector3'},
{ name='orient', type='map.msg.Vector3'},
{ name='ridestatus', type='int'},
})
msg.add_type('map.msg', {
name='RoleMapInfo2',
{ name='hp', type='int'},
{ name='mp', type='int'},
{ name='deathtime', type='long'},
{ name='pets', type='map', key='int', value='map.msg.PetMapContext'},
{ name='skills', type='map', key='int', value='long'},
})
msg.add_type('map.msg', {
name='SingleRoleBonus',
{ name='roleid', type='long'},
{ name='rolename', type='string'},
{ name='rolebonus', type='map.msg.Bonus'},
})
msg.add_type('map.msg', {
name='Team',
{ name='teamid', type='long'},
{ name='members', type='list', value='long'},
})
msg.add_type('map.msg', {
name='TeamFightMember',
{ name='roleid', type='long'},
{ name='profession', type='int'},
})
msg.add_type('map.msg', {
name='TeamInfo',
{ name='teamid', type='long'},
{ name='membernum', type='int'},
{ name='leader', type='map.msg.MemberInfo'},
})
msg.add_type('map.msg', {
name='TeamStatistic',
{ name='members', type='list', value='map.msg.MemberStatistic'},
})
msg.add_type('map.msg', {
name='Vector3',
{ name='x', type='float'},
{ name='y', type='float'},
{ name='z', type='float'},
})

