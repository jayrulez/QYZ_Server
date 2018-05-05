
package xbean;

/**
 * bean factory
 */
public final class Pod {
	public static xdb.util.BeanPool<IntList> poolIntList = new xdb.util.BeanPool<IntList>() {
		@Override
		protected IntList newBean() {
			return new xbean.__.IntList();
		}
	};

	public static IntList newIntList() {
		return poolIntList.get();
	}

	public static void _reset_unsafe_add_(IntList bean) {
		poolIntList._reset_unsafe_add_(bean);
	}

	public static void padd(IntList bean) {
		xdb.Procedure.padd(bean, poolIntList);
	}

	public static IntList newIntListData() {
		return new xbean.__.IntList.Data();
	}

	public static xdb.util.BeanPool<LongList> poolLongList = new xdb.util.BeanPool<LongList>() {
		@Override
		protected LongList newBean() {
			return new xbean.__.LongList();
		}
	};

	public static LongList newLongList() {
		return poolLongList.get();
	}

	public static void _reset_unsafe_add_(LongList bean) {
		poolLongList._reset_unsafe_add_(bean);
	}

	public static void padd(LongList bean) {
		xdb.Procedure.padd(bean, poolLongList);
	}

	public static LongList newLongListData() {
		return new xbean.__.LongList.Data();
	}

	public static xdb.util.BeanPool<IntSet> poolIntSet = new xdb.util.BeanPool<IntSet>() {
		@Override
		protected IntSet newBean() {
			return new xbean.__.IntSet();
		}
	};

	public static IntSet newIntSet() {
		return poolIntSet.get();
	}

	public static void _reset_unsafe_add_(IntSet bean) {
		poolIntSet._reset_unsafe_add_(bean);
	}

	public static void padd(IntSet bean) {
		xdb.Procedure.padd(bean, poolIntSet);
	}

	public static IntSet newIntSetData() {
		return new xbean.__.IntSet.Data();
	}

	public static xdb.util.BeanPool<LongSet> poolLongSet = new xdb.util.BeanPool<LongSet>() {
		@Override
		protected LongSet newBean() {
			return new xbean.__.LongSet();
		}
	};

	public static LongSet newLongSet() {
		return poolLongSet.get();
	}

	public static void _reset_unsafe_add_(LongSet bean) {
		poolLongSet._reset_unsafe_add_(bean);
	}

	public static void padd(LongSet bean) {
		xdb.Procedure.padd(bean, poolLongSet);
	}

	public static LongSet newLongSetData() {
		return new xbean.__.LongSet.Data();
	}

	public static xdb.util.BeanPool<User> poolUser = new xdb.util.BeanPool<User>() {
		@Override
		protected User newBean() {
			return new xbean.__.User();
		}
	};

	public static User newUser() {
		return poolUser.get();
	}

	public static void _reset_unsafe_add_(User bean) {
		poolUser._reset_unsafe_add_(bean);
	}

	public static void padd(User bean) {
		xdb.Procedure.padd(bean, poolUser);
	}

	public static User newUserData() {
		return new xbean.__.User.Data();
	}

	public static xdb.util.BeanPool<OnlineUser> poolOnlineUser = new xdb.util.BeanPool<OnlineUser>() {
		@Override
		protected OnlineUser newBean() {
			return new xbean.__.OnlineUser();
		}
	};

	public static OnlineUser newOnlineUser() {
		return poolOnlineUser.get();
	}

	public static void _reset_unsafe_add_(OnlineUser bean) {
		poolOnlineUser._reset_unsafe_add_(bean);
	}

	public static void padd(OnlineUser bean) {
		xdb.Procedure.padd(bean, poolOnlineUser);
	}

	public static OnlineUser newOnlineUserData() {
		return new xbean.__.OnlineUser.Data();
	}

	public static xdb.util.BeanPool<DeletedRole> poolDeletedRole = new xdb.util.BeanPool<DeletedRole>() {
		@Override
		protected DeletedRole newBean() {
			return new xbean.__.DeletedRole();
		}
	};

	public static DeletedRole newDeletedRole() {
		return poolDeletedRole.get();
	}

	public static void _reset_unsafe_add_(DeletedRole bean) {
		poolDeletedRole._reset_unsafe_add_(bean);
	}

	public static void padd(DeletedRole bean) {
		xdb.Procedure.padd(bean, poolDeletedRole);
	}

	public static DeletedRole newDeletedRoleData() {
		return new xbean.__.DeletedRole.Data();
	}

	public static xdb.util.BeanPool<UserDeletedRole> poolUserDeletedRole = new xdb.util.BeanPool<UserDeletedRole>() {
		@Override
		protected UserDeletedRole newBean() {
			return new xbean.__.UserDeletedRole();
		}
	};

	public static UserDeletedRole newUserDeletedRole() {
		return poolUserDeletedRole.get();
	}

	public static void _reset_unsafe_add_(UserDeletedRole bean) {
		poolUserDeletedRole._reset_unsafe_add_(bean);
	}

	public static void padd(UserDeletedRole bean) {
		xdb.Procedure.padd(bean, poolUserDeletedRole);
	}

	public static UserDeletedRole newUserDeletedRoleData() {
		return new xbean.__.UserDeletedRole.Data();
	}

	public static xdb.util.BeanPool<RoleMemInfo> poolRoleMemInfo = new xdb.util.BeanPool<RoleMemInfo>() {
		@Override
		protected RoleMemInfo newBean() {
			return new xbean.__.RoleMemInfo();
		}
	};

	public static RoleMemInfo newRoleMemInfo() {
		return poolRoleMemInfo.get();
	}

	public static void _reset_unsafe_add_(RoleMemInfo bean) {
		poolRoleMemInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleMemInfo bean) {
		xdb.Procedure.padd(bean, poolRoleMemInfo);
	}

	public static RoleMemInfo newRoleMemInfoData() {
		return new xbean.__.RoleMemInfo.Data();
	}

	public static xdb.util.BeanPool<RoleInfo> poolRoleInfo = new xdb.util.BeanPool<RoleInfo>() {
		@Override
		protected RoleInfo newBean() {
			return new xbean.__.RoleInfo();
		}
	};

	public static RoleInfo newRoleInfo() {
		return poolRoleInfo.get();
	}

	public static void _reset_unsafe_add_(RoleInfo bean) {
		poolRoleInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleInfo bean) {
		xdb.Procedure.padd(bean, poolRoleInfo);
	}

	public static RoleInfo newRoleInfoData() {
		return new xbean.__.RoleInfo.Data();
	}

	public static xdb.util.BeanPool<RoleConfigure> poolRoleConfigure = new xdb.util.BeanPool<RoleConfigure>() {
		@Override
		protected RoleConfigure newBean() {
			return new xbean.__.RoleConfigure();
		}
	};

	public static RoleConfigure newRoleConfigure() {
		return poolRoleConfigure.get();
	}

	public static void _reset_unsafe_add_(RoleConfigure bean) {
		poolRoleConfigure._reset_unsafe_add_(bean);
	}

	public static void padd(RoleConfigure bean) {
		xdb.Procedure.padd(bean, poolRoleConfigure);
	}

	public static RoleConfigure newRoleConfigureData() {
		return new xbean.__.RoleConfigure.Data();
	}

	public static xdb.util.BeanPool<TimeRange> poolTimeRange = new xdb.util.BeanPool<TimeRange>() {
		@Override
		protected TimeRange newBean() {
			return new xbean.__.TimeRange();
		}
	};

	public static TimeRange newTimeRange() {
		return poolTimeRange.get();
	}

	public static void _reset_unsafe_add_(TimeRange bean) {
		poolTimeRange._reset_unsafe_add_(bean);
	}

	public static void padd(TimeRange bean) {
		xdb.Procedure.padd(bean, poolTimeRange);
	}

	public static TimeRange newTimeRangeData() {
		return new xbean.__.TimeRange.Data();
	}

	public static xdb.util.BeanPool<GlobalActivityOpenInfos> poolGlobalActivityOpenInfos = new xdb.util.BeanPool<GlobalActivityOpenInfos>() {
		@Override
		protected GlobalActivityOpenInfos newBean() {
			return new xbean.__.GlobalActivityOpenInfos();
		}
	};

	public static GlobalActivityOpenInfos newGlobalActivityOpenInfos() {
		return poolGlobalActivityOpenInfos.get();
	}

	public static void _reset_unsafe_add_(GlobalActivityOpenInfos bean) {
		poolGlobalActivityOpenInfos._reset_unsafe_add_(bean);
	}

	public static void padd(GlobalActivityOpenInfos bean) {
		xdb.Procedure.padd(bean, poolGlobalActivityOpenInfos);
	}

	public static GlobalActivityOpenInfos newGlobalActivityOpenInfosData() {
		return new xbean.__.GlobalActivityOpenInfos.Data();
	}

	public static xdb.util.BeanPool<GlobalActivity> poolGlobalActivity = new xdb.util.BeanPool<GlobalActivity>() {
		@Override
		protected GlobalActivity newBean() {
			return new xbean.__.GlobalActivity();
		}
	};

	public static GlobalActivity newGlobalActivity() {
		return poolGlobalActivity.get();
	}

	public static void _reset_unsafe_add_(GlobalActivity bean) {
		poolGlobalActivity._reset_unsafe_add_(bean);
	}

	public static void padd(GlobalActivity bean) {
		xdb.Procedure.padd(bean, poolGlobalActivity);
	}

	public static GlobalActivity newGlobalActivityData() {
		return new xbean.__.GlobalActivity.Data();
	}

	public static xdb.util.BeanPool<GlobalAllActivity> poolGlobalAllActivity = new xdb.util.BeanPool<GlobalAllActivity>() {
		@Override
		protected GlobalAllActivity newBean() {
			return new xbean.__.GlobalAllActivity();
		}
	};

	public static GlobalAllActivity newGlobalAllActivity() {
		return poolGlobalAllActivity.get();
	}

	public static void _reset_unsafe_add_(GlobalAllActivity bean) {
		poolGlobalAllActivity._reset_unsafe_add_(bean);
	}

	public static void padd(GlobalAllActivity bean) {
		xdb.Procedure.padd(bean, poolGlobalAllActivity);
	}

	public static GlobalAllActivity newGlobalAllActivityData() {
		return new xbean.__.GlobalAllActivity.Data();
	}

	public static xdb.util.BeanPool<RoleActivityStatus> poolRoleActivityStatus = new xdb.util.BeanPool<RoleActivityStatus>() {
		@Override
		protected RoleActivityStatus newBean() {
			return new xbean.__.RoleActivityStatus();
		}
	};

	public static RoleActivityStatus newRoleActivityStatus() {
		return poolRoleActivityStatus.get();
	}

	public static void _reset_unsafe_add_(RoleActivityStatus bean) {
		poolRoleActivityStatus._reset_unsafe_add_(bean);
	}

	public static void padd(RoleActivityStatus bean) {
		xdb.Procedure.padd(bean, poolRoleActivityStatus);
	}

	public static RoleActivityStatus newRoleActivityStatusData() {
		return new xbean.__.RoleActivityStatus.Data();
	}

	public static xdb.util.BeanPool<RoleActivityRecord> poolRoleActivityRecord = new xdb.util.BeanPool<RoleActivityRecord>() {
		@Override
		protected RoleActivityRecord newBean() {
			return new xbean.__.RoleActivityRecord();
		}
	};

	public static RoleActivityRecord newRoleActivityRecord() {
		return poolRoleActivityRecord.get();
	}

	public static void _reset_unsafe_add_(RoleActivityRecord bean) {
		poolRoleActivityRecord._reset_unsafe_add_(bean);
	}

	public static void padd(RoleActivityRecord bean) {
		xdb.Procedure.padd(bean, poolRoleActivityRecord);
	}

	public static RoleActivityRecord newRoleActivityRecordData() {
		return new xbean.__.RoleActivityRecord.Data();
	}

	public static xdb.util.BeanPool<RoleActivityRecordMap> poolRoleActivityRecordMap = new xdb.util.BeanPool<RoleActivityRecordMap>() {
		@Override
		protected RoleActivityRecordMap newBean() {
			return new xbean.__.RoleActivityRecordMap();
		}
	};

	public static RoleActivityRecordMap newRoleActivityRecordMap() {
		return poolRoleActivityRecordMap.get();
	}

	public static void _reset_unsafe_add_(RoleActivityRecordMap bean) {
		poolRoleActivityRecordMap._reset_unsafe_add_(bean);
	}

	public static void padd(RoleActivityRecordMap bean) {
		xdb.Procedure.padd(bean, poolRoleActivityRecordMap);
	}

	public static RoleActivityRecordMap newRoleActivityRecordMapData() {
		return new xbean.__.RoleActivityRecordMap.Data();
	}

	public static xdb.util.BeanPool<RoleAllActivity> poolRoleAllActivity = new xdb.util.BeanPool<RoleAllActivity>() {
		@Override
		protected RoleAllActivity newBean() {
			return new xbean.__.RoleAllActivity();
		}
	};

	public static RoleAllActivity newRoleAllActivity() {
		return poolRoleAllActivity.get();
	}

	public static void _reset_unsafe_add_(RoleAllActivity bean) {
		poolRoleAllActivity._reset_unsafe_add_(bean);
	}

	public static void padd(RoleAllActivity bean) {
		xdb.Procedure.padd(bean, poolRoleAllActivity);
	}

	public static RoleAllActivity newRoleAllActivityData() {
		return new xbean.__.RoleAllActivity.Data();
	}

	public static xdb.util.BeanPool<IdGen> poolIdGen = new xdb.util.BeanPool<IdGen>() {
		@Override
		protected IdGen newBean() {
			return new xbean.__.IdGen();
		}
	};

	public static IdGen newIdGen() {
		return poolIdGen.get();
	}

	public static void _reset_unsafe_add_(IdGen bean) {
		poolIdGen._reset_unsafe_add_(bean);
	}

	public static void padd(IdGen bean) {
		xdb.Procedure.padd(bean, poolIdGen);
	}

	public static IdGen newIdGenData() {
		return new xbean.__.IdGen.Data();
	}

	public static xdb.util.BeanPool<Forbid> poolForbid = new xdb.util.BeanPool<Forbid>() {
		@Override
		protected Forbid newBean() {
			return new xbean.__.Forbid();
		}
	};

	public static Forbid newForbid() {
		return poolForbid.get();
	}

	public static void _reset_unsafe_add_(Forbid bean) {
		poolForbid._reset_unsafe_add_(bean);
	}

	public static void padd(Forbid bean) {
		xdb.Procedure.padd(bean, poolForbid);
	}

	public static Forbid newForbidData() {
		return new xbean.__.Forbid.Data();
	}

	public static xdb.util.BeanPool<ForbidItem> poolForbidItem = new xdb.util.BeanPool<ForbidItem>() {
		@Override
		protected ForbidItem newBean() {
			return new xbean.__.ForbidItem();
		}
	};

	public static ForbidItem newForbidItem() {
		return poolForbidItem.get();
	}

	public static void _reset_unsafe_add_(ForbidItem bean) {
		poolForbidItem._reset_unsafe_add_(bean);
	}

	public static void padd(ForbidItem bean) {
		xdb.Procedure.padd(bean, poolForbidItem);
	}

	public static ForbidItem newForbidItemData() {
		return new xbean.__.ForbidItem.Data();
	}

	public static xdb.util.BeanPool<GroupAttr> poolGroupAttr = new xdb.util.BeanPool<GroupAttr>() {
		@Override
		protected GroupAttr newBean() {
			return new xbean.__.GroupAttr();
		}
	};

	public static GroupAttr newGroupAttr() {
		return poolGroupAttr.get();
	}

	public static void _reset_unsafe_add_(GroupAttr bean) {
		poolGroupAttr._reset_unsafe_add_(bean);
	}

	public static void padd(GroupAttr bean) {
		xdb.Procedure.padd(bean, poolGroupAttr);
	}

	public static GroupAttr newGroupAttrData() {
		return new xbean.__.GroupAttr.Data();
	}

	public static xdb.util.BeanPool<RoleAttr> poolRoleAttr = new xdb.util.BeanPool<RoleAttr>() {
		@Override
		protected RoleAttr newBean() {
			return new xbean.__.RoleAttr();
		}
	};

	public static RoleAttr newRoleAttr() {
		return poolRoleAttr.get();
	}

	public static void _reset_unsafe_add_(RoleAttr bean) {
		poolRoleAttr._reset_unsafe_add_(bean);
	}

	public static void padd(RoleAttr bean) {
		xdb.Procedure.padd(bean, poolRoleAttr);
	}

	public static RoleAttr newRoleAttrData() {
		return new xbean.__.RoleAttr.Data();
	}

	public static xdb.util.BeanPool<Vector3> poolVector3 = new xdb.util.BeanPool<Vector3>() {
		@Override
		protected Vector3 newBean() {
			return new xbean.__.Vector3();
		}
	};

	public static Vector3 newVector3() {
		return poolVector3.get();
	}

	public static void _reset_unsafe_add_(Vector3 bean) {
		poolVector3._reset_unsafe_add_(bean);
	}

	public static void padd(Vector3 bean) {
		xdb.Procedure.padd(bean, poolVector3);
	}

	public static Vector3 newVector3Data() {
		return new xbean.__.Vector3.Data();
	}

	public static xdb.util.BeanPool<RoleMapInfo> poolRoleMapInfo = new xdb.util.BeanPool<RoleMapInfo>() {
		@Override
		protected RoleMapInfo newBean() {
			return new xbean.__.RoleMapInfo();
		}
	};

	public static RoleMapInfo newRoleMapInfo() {
		return poolRoleMapInfo.get();
	}

	public static void _reset_unsafe_add_(RoleMapInfo bean) {
		poolRoleMapInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleMapInfo bean) {
		xdb.Procedure.padd(bean, poolRoleMapInfo);
	}

	public static RoleMapInfo newRoleMapInfoData() {
		return new xbean.__.RoleMapInfo.Data();
	}

	public static xdb.util.BeanPool<RoleMap> poolRoleMap = new xdb.util.BeanPool<RoleMap>() {
		@Override
		protected RoleMap newBean() {
			return new xbean.__.RoleMap();
		}
	};

	public static RoleMap newRoleMap() {
		return poolRoleMap.get();
	}

	public static void _reset_unsafe_add_(RoleMap bean) {
		poolRoleMap._reset_unsafe_add_(bean);
	}

	public static void padd(RoleMap bean) {
		xdb.Procedure.padd(bean, poolRoleMap);
	}

	public static RoleMap newRoleMapData() {
		return new xbean.__.RoleMap.Data();
	}

	public static xdb.util.BeanPool<RandomBonus> poolRandomBonus = new xdb.util.BeanPool<RandomBonus>() {
		@Override
		protected RandomBonus newBean() {
			return new xbean.__.RandomBonus();
		}
	};

	public static RandomBonus newRandomBonus() {
		return poolRandomBonus.get();
	}

	public static void _reset_unsafe_add_(RandomBonus bean) {
		poolRandomBonus._reset_unsafe_add_(bean);
	}

	public static void padd(RandomBonus bean) {
		xdb.Procedure.padd(bean, poolRandomBonus);
	}

	public static RandomBonus newRandomBonusData() {
		return new xbean.__.RandomBonus.Data();
	}

	public static xdb.util.BeanPool<HeroesGroupMemInfo> poolHeroesGroupMemInfo = new xdb.util.BeanPool<HeroesGroupMemInfo>() {
		@Override
		protected HeroesGroupMemInfo newBean() {
			return new xbean.__.HeroesGroupMemInfo();
		}
	};

	public static HeroesGroupMemInfo newHeroesGroupMemInfo() {
		return poolHeroesGroupMemInfo.get();
	}

	public static void _reset_unsafe_add_(HeroesGroupMemInfo bean) {
		poolHeroesGroupMemInfo._reset_unsafe_add_(bean);
	}

	public static void padd(HeroesGroupMemInfo bean) {
		xdb.Procedure.padd(bean, poolHeroesGroupMemInfo);
	}

	public static HeroesGroupMemInfo newHeroesGroupMemInfoData() {
		return new xbean.__.HeroesGroupMemInfo.Data();
	}

	public static xdb.util.BeanPool<HeroesGroupInfo> poolHeroesGroupInfo = new xdb.util.BeanPool<HeroesGroupInfo>() {
		@Override
		protected HeroesGroupInfo newBean() {
			return new xbean.__.HeroesGroupInfo();
		}
	};

	public static HeroesGroupInfo newHeroesGroupInfo() {
		return poolHeroesGroupInfo.get();
	}

	public static void _reset_unsafe_add_(HeroesGroupInfo bean) {
		poolHeroesGroupInfo._reset_unsafe_add_(bean);
	}

	public static void padd(HeroesGroupInfo bean) {
		xdb.Procedure.padd(bean, poolHeroesGroupInfo);
	}

	public static HeroesGroupInfo newHeroesGroupInfoData() {
		return new xbean.__.HeroesGroupInfo.Data();
	}

	public static xdb.util.BeanPool<ClimbTowerInfo> poolClimbTowerInfo = new xdb.util.BeanPool<ClimbTowerInfo>() {
		@Override
		protected ClimbTowerInfo newBean() {
			return new xbean.__.ClimbTowerInfo();
		}
	};

	public static ClimbTowerInfo newClimbTowerInfo() {
		return poolClimbTowerInfo.get();
	}

	public static void _reset_unsafe_add_(ClimbTowerInfo bean) {
		poolClimbTowerInfo._reset_unsafe_add_(bean);
	}

	public static void padd(ClimbTowerInfo bean) {
		xdb.Procedure.padd(bean, poolClimbTowerInfo);
	}

	public static ClimbTowerInfo newClimbTowerInfoData() {
		return new xbean.__.ClimbTowerInfo.Data();
	}

	public static xdb.util.BeanPool<ChapterInfo> poolChapterInfo = new xdb.util.BeanPool<ChapterInfo>() {
		@Override
		protected ChapterInfo newBean() {
			return new xbean.__.ChapterInfo();
		}
	};

	public static ChapterInfo newChapterInfo() {
		return poolChapterInfo.get();
	}

	public static void _reset_unsafe_add_(ChapterInfo bean) {
		poolChapterInfo._reset_unsafe_add_(bean);
	}

	public static void padd(ChapterInfo bean) {
		xdb.Procedure.padd(bean, poolChapterInfo);
	}

	public static ChapterInfo newChapterInfoData() {
		return new xbean.__.ChapterInfo.Data();
	}

	public static xdb.util.BeanPool<DailyInfo> poolDailyInfo = new xdb.util.BeanPool<DailyInfo>() {
		@Override
		protected DailyInfo newBean() {
			return new xbean.__.DailyInfo();
		}
	};

	public static DailyInfo newDailyInfo() {
		return poolDailyInfo.get();
	}

	public static void _reset_unsafe_add_(DailyInfo bean) {
		poolDailyInfo._reset_unsafe_add_(bean);
	}

	public static void padd(DailyInfo bean) {
		xdb.Procedure.padd(bean, poolDailyInfo);
	}

	public static DailyInfo newDailyInfoData() {
		return new xbean.__.DailyInfo.Data();
	}

	public static xdb.util.BeanPool<TeamFightInfo> poolTeamFightInfo = new xdb.util.BeanPool<TeamFightInfo>() {
		@Override
		protected TeamFightInfo newBean() {
			return new xbean.__.TeamFightInfo();
		}
	};

	public static TeamFightInfo newTeamFightInfo() {
		return poolTeamFightInfo.get();
	}

	public static void _reset_unsafe_add_(TeamFightInfo bean) {
		poolTeamFightInfo._reset_unsafe_add_(bean);
	}

	public static void padd(TeamFightInfo bean) {
		xdb.Procedure.padd(bean, poolTeamFightInfo);
	}

	public static TeamFightInfo newTeamFightInfoData() {
		return new xbean.__.TeamFightInfo.Data();
	}

	public static xdb.util.BeanPool<RoleEctype> poolRoleEctype = new xdb.util.BeanPool<RoleEctype>() {
		@Override
		protected RoleEctype newBean() {
			return new xbean.__.RoleEctype();
		}
	};

	public static RoleEctype newRoleEctype() {
		return poolRoleEctype.get();
	}

	public static void _reset_unsafe_add_(RoleEctype bean) {
		poolRoleEctype._reset_unsafe_add_(bean);
	}

	public static void padd(RoleEctype bean) {
		xdb.Procedure.padd(bean, poolRoleEctype);
	}

	public static RoleEctype newRoleEctypeData() {
		return new xbean.__.RoleEctype.Data();
	}

	public static xdb.util.BeanPool<RoleChat> poolRoleChat = new xdb.util.BeanPool<RoleChat>() {
		@Override
		protected RoleChat newBean() {
			return new xbean.__.RoleChat();
		}
	};

	public static RoleChat newRoleChat() {
		return poolRoleChat.get();
	}

	public static void _reset_unsafe_add_(RoleChat bean) {
		poolRoleChat._reset_unsafe_add_(bean);
	}

	public static void padd(RoleChat bean) {
		xdb.Procedure.padd(bean, poolRoleChat);
	}

	public static RoleChat newRoleChatData() {
		return new xbean.__.RoleChat.Data();
	}

	public static xdb.util.BeanPool<DailyEctypeRecord> poolDailyEctypeRecord = new xdb.util.BeanPool<DailyEctypeRecord>() {
		@Override
		protected DailyEctypeRecord newBean() {
			return new xbean.__.DailyEctypeRecord();
		}
	};

	public static DailyEctypeRecord newDailyEctypeRecord() {
		return poolDailyEctypeRecord.get();
	}

	public static void _reset_unsafe_add_(DailyEctypeRecord bean) {
		poolDailyEctypeRecord._reset_unsafe_add_(bean);
	}

	public static void padd(DailyEctypeRecord bean) {
		xdb.Procedure.padd(bean, poolDailyEctypeRecord);
	}

	public static DailyEctypeRecord newDailyEctypeRecordData() {
		return new xbean.__.DailyEctypeRecord.Data();
	}

	public static xdb.util.BeanPool<EctypeSingle> poolEctypeSingle = new xdb.util.BeanPool<EctypeSingle>() {
		@Override
		protected EctypeSingle newBean() {
			return new xbean.__.EctypeSingle();
		}
	};

	public static EctypeSingle newEctypeSingle() {
		return poolEctypeSingle.get();
	}

	public static void _reset_unsafe_add_(EctypeSingle bean) {
		poolEctypeSingle._reset_unsafe_add_(bean);
	}

	public static void padd(EctypeSingle bean) {
		xdb.Procedure.padd(bean, poolEctypeSingle);
	}

	public static EctypeSingle newEctypeSingleData() {
		return new xbean.__.EctypeSingle.Data();
	}

	public static xdb.util.BeanPool<AppOrder> poolAppOrder = new xdb.util.BeanPool<AppOrder>() {
		@Override
		protected AppOrder newBean() {
			return new xbean.__.AppOrder();
		}
	};

	public static AppOrder newAppOrder() {
		return poolAppOrder.get();
	}

	public static void _reset_unsafe_add_(AppOrder bean) {
		poolAppOrder._reset_unsafe_add_(bean);
	}

	public static void padd(AppOrder bean) {
		xdb.Procedure.padd(bean, poolAppOrder);
	}

	public static AppOrder newAppOrderData() {
		return new xbean.__.AppOrder.Data();
	}

	public static xdb.util.BeanPool<RoleOrderHistroy> poolRoleOrderHistroy = new xdb.util.BeanPool<RoleOrderHistroy>() {
		@Override
		protected RoleOrderHistroy newBean() {
			return new xbean.__.RoleOrderHistroy();
		}
	};

	public static RoleOrderHistroy newRoleOrderHistroy() {
		return poolRoleOrderHistroy.get();
	}

	public static void _reset_unsafe_add_(RoleOrderHistroy bean) {
		poolRoleOrderHistroy._reset_unsafe_add_(bean);
	}

	public static void padd(RoleOrderHistroy bean) {
		xdb.Procedure.padd(bean, poolRoleOrderHistroy);
	}

	public static RoleOrderHistroy newRoleOrderHistroyData() {
		return new xbean.__.RoleOrderHistroy.Data();
	}

	public static xdb.util.BeanPool<RolePay> poolRolePay = new xdb.util.BeanPool<RolePay>() {
		@Override
		protected RolePay newBean() {
			return new xbean.__.RolePay();
		}
	};

	public static RolePay newRolePay() {
		return poolRolePay.get();
	}

	public static void _reset_unsafe_add_(RolePay bean) {
		poolRolePay._reset_unsafe_add_(bean);
	}

	public static void padd(RolePay bean) {
		xdb.Procedure.padd(bean, poolRolePay);
	}

	public static RolePay newRolePayData() {
		return new xbean.__.RolePay.Data();
	}

	public static xdb.util.BeanPool<RoleFriend> poolRoleFriend = new xdb.util.BeanPool<RoleFriend>() {
		@Override
		protected RoleFriend newBean() {
			return new xbean.__.RoleFriend();
		}
	};

	public static RoleFriend newRoleFriend() {
		return poolRoleFriend.get();
	}

	public static void _reset_unsafe_add_(RoleFriend bean) {
		poolRoleFriend._reset_unsafe_add_(bean);
	}

	public static void padd(RoleFriend bean) {
		xdb.Procedure.padd(bean, poolRoleFriend);
	}

	public static RoleFriend newRoleFriendData() {
		return new xbean.__.RoleFriend.Data();
	}

	public static xdb.util.BeanPool<IdolAwardClaim> poolIdolAwardClaim = new xdb.util.BeanPool<IdolAwardClaim>() {
		@Override
		protected IdolAwardClaim newBean() {
			return new xbean.__.IdolAwardClaim();
		}
	};

	public static IdolAwardClaim newIdolAwardClaim() {
		return poolIdolAwardClaim.get();
	}

	public static void _reset_unsafe_add_(IdolAwardClaim bean) {
		poolIdolAwardClaim._reset_unsafe_add_(bean);
	}

	public static void padd(IdolAwardClaim bean) {
		xdb.Procedure.padd(bean, poolIdolAwardClaim);
	}

	public static IdolAwardClaim newIdolAwardClaimData() {
		return new xbean.__.IdolAwardClaim.Data();
	}

	public static xdb.util.BeanPool<RoleRelation> poolRoleRelation = new xdb.util.BeanPool<RoleRelation>() {
		@Override
		protected RoleRelation newBean() {
			return new xbean.__.RoleRelation();
		}
	};

	public static RoleRelation newRoleRelation() {
		return poolRoleRelation.get();
	}

	public static void _reset_unsafe_add_(RoleRelation bean) {
		poolRoleRelation._reset_unsafe_add_(bean);
	}

	public static void padd(RoleRelation bean) {
		xdb.Procedure.padd(bean, poolRoleRelation);
	}

	public static RoleRelation newRoleRelationData() {
		return new xbean.__.RoleRelation.Data();
	}

	public static xdb.util.BeanPool<Enemyinfo> poolEnemyinfo = new xdb.util.BeanPool<Enemyinfo>() {
		@Override
		protected Enemyinfo newBean() {
			return new xbean.__.Enemyinfo();
		}
	};

	public static Enemyinfo newEnemyinfo() {
		return poolEnemyinfo.get();
	}

	public static void _reset_unsafe_add_(Enemyinfo bean) {
		poolEnemyinfo._reset_unsafe_add_(bean);
	}

	public static void padd(Enemyinfo bean) {
		xdb.Procedure.padd(bean, poolEnemyinfo);
	}

	public static Enemyinfo newEnemyinfoData() {
		return new xbean.__.Enemyinfo.Data();
	}

	public static xdb.util.BeanPool<RoleFriendsInfo> poolRoleFriendsInfo = new xdb.util.BeanPool<RoleFriendsInfo>() {
		@Override
		protected RoleFriendsInfo newBean() {
			return new xbean.__.RoleFriendsInfo();
		}
	};

	public static RoleFriendsInfo newRoleFriendsInfo() {
		return poolRoleFriendsInfo.get();
	}

	public static void _reset_unsafe_add_(RoleFriendsInfo bean) {
		poolRoleFriendsInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleFriendsInfo bean) {
		xdb.Procedure.padd(bean, poolRoleFriendsInfo);
	}

	public static RoleFriendsInfo newRoleFriendsInfoData() {
		return new xbean.__.RoleFriendsInfo.Data();
	}

	public static xdb.util.BeanPool<IdolCharmInfo> poolIdolCharmInfo = new xdb.util.BeanPool<IdolCharmInfo>() {
		@Override
		protected IdolCharmInfo newBean() {
			return new xbean.__.IdolCharmInfo();
		}
	};

	public static IdolCharmInfo newIdolCharmInfo() {
		return poolIdolCharmInfo.get();
	}

	public static void _reset_unsafe_add_(IdolCharmInfo bean) {
		poolIdolCharmInfo._reset_unsafe_add_(bean);
	}

	public static void padd(IdolCharmInfo bean) {
		xdb.Procedure.padd(bean, poolIdolCharmInfo);
	}

	public static IdolCharmInfo newIdolCharmInfoData() {
		return new xbean.__.IdolCharmInfo.Data();
	}

	public static xdb.util.BeanPool<RoleMarriage> poolRoleMarriage = new xdb.util.BeanPool<RoleMarriage>() {
		@Override
		protected RoleMarriage newBean() {
			return new xbean.__.RoleMarriage();
		}
	};

	public static RoleMarriage newRoleMarriage() {
		return poolRoleMarriage.get();
	}

	public static void _reset_unsafe_add_(RoleMarriage bean) {
		poolRoleMarriage._reset_unsafe_add_(bean);
	}

	public static void padd(RoleMarriage bean) {
		xdb.Procedure.padd(bean, poolRoleMarriage);
	}

	public static RoleMarriage newRoleMarriageData() {
		return new xbean.__.RoleMarriage.Data();
	}

	public static xdb.util.BeanPool<Item> poolItem = new xdb.util.BeanPool<Item>() {
		@Override
		protected Item newBean() {
			return new xbean.__.Item();
		}
	};

	public static Item newItem() {
		return poolItem.get();
	}

	public static void _reset_unsafe_add_(Item bean) {
		poolItem._reset_unsafe_add_(bean);
	}

	public static void padd(Item bean) {
		xdb.Procedure.padd(bean, poolItem);
	}

	public static Item newItemData() {
		return new xbean.__.Item.Data();
	}

	public static xdb.util.BeanPool<RoleItemBag> poolRoleItemBag = new xdb.util.BeanPool<RoleItemBag>() {
		@Override
		protected RoleItemBag newBean() {
			return new xbean.__.RoleItemBag();
		}
	};

	public static RoleItemBag newRoleItemBag() {
		return poolRoleItemBag.get();
	}

	public static void _reset_unsafe_add_(RoleItemBag bean) {
		poolRoleItemBag._reset_unsafe_add_(bean);
	}

	public static void padd(RoleItemBag bean) {
		xdb.Procedure.padd(bean, poolRoleItemBag);
	}

	public static RoleItemBag newRoleItemBagData() {
		return new xbean.__.RoleItemBag.Data();
	}

	public static xdb.util.BeanPool<Equip> poolEquip = new xdb.util.BeanPool<Equip>() {
		@Override
		protected Equip newBean() {
			return new xbean.__.Equip();
		}
	};

	public static Equip newEquip() {
		return poolEquip.get();
	}

	public static void _reset_unsafe_add_(Equip bean) {
		poolEquip._reset_unsafe_add_(bean);
	}

	public static void padd(Equip bean) {
		xdb.Procedure.padd(bean, poolEquip);
	}

	public static Equip newEquipData() {
		return new xbean.__.Equip.Data();
	}

	public static xdb.util.BeanPool<NormalEquip> poolNormalEquip = new xdb.util.BeanPool<NormalEquip>() {
		@Override
		protected NormalEquip newBean() {
			return new xbean.__.NormalEquip();
		}
	};

	public static NormalEquip newNormalEquip() {
		return poolNormalEquip.get();
	}

	public static void _reset_unsafe_add_(NormalEquip bean) {
		poolNormalEquip._reset_unsafe_add_(bean);
	}

	public static void padd(NormalEquip bean) {
		xdb.Procedure.padd(bean, poolNormalEquip);
	}

	public static NormalEquip newNormalEquipData() {
		return new xbean.__.NormalEquip.Data();
	}

	public static xdb.util.BeanPool<Accessory> poolAccessory = new xdb.util.BeanPool<Accessory>() {
		@Override
		protected Accessory newBean() {
			return new xbean.__.Accessory();
		}
	};

	public static Accessory newAccessory() {
		return poolAccessory.get();
	}

	public static void _reset_unsafe_add_(Accessory bean) {
		poolAccessory._reset_unsafe_add_(bean);
	}

	public static void padd(Accessory bean) {
		xdb.Procedure.padd(bean, poolAccessory);
	}

	public static Accessory newAccessoryData() {
		return new xbean.__.Accessory.Data();
	}

	public static xdb.util.BeanPool<AccessoryProp> poolAccessoryProp = new xdb.util.BeanPool<AccessoryProp>() {
		@Override
		protected AccessoryProp newBean() {
			return new xbean.__.AccessoryProp();
		}
	};

	public static AccessoryProp newAccessoryProp() {
		return poolAccessoryProp.get();
	}

	public static void _reset_unsafe_add_(AccessoryProp bean) {
		poolAccessoryProp._reset_unsafe_add_(bean);
	}

	public static void padd(AccessoryProp bean) {
		xdb.Procedure.padd(bean, poolAccessoryProp);
	}

	public static AccessoryProp newAccessoryPropData() {
		return new xbean.__.AccessoryProp.Data();
	}

	public static xdb.util.BeanPool<AccessoryWashResult> poolAccessoryWashResult = new xdb.util.BeanPool<AccessoryWashResult>() {
		@Override
		protected AccessoryWashResult newBean() {
			return new xbean.__.AccessoryWashResult();
		}
	};

	public static AccessoryWashResult newAccessoryWashResult() {
		return poolAccessoryWashResult.get();
	}

	public static void _reset_unsafe_add_(AccessoryWashResult bean) {
		poolAccessoryWashResult._reset_unsafe_add_(bean);
	}

	public static void padd(AccessoryWashResult bean) {
		xdb.Procedure.padd(bean, poolAccessoryWashResult);
	}

	public static AccessoryWashResult newAccessoryWashResultData() {
		return new xbean.__.AccessoryWashResult.Data();
	}

	public static xdb.util.BeanPool<RoleEquipBag> poolRoleEquipBag = new xdb.util.BeanPool<RoleEquipBag>() {
		@Override
		protected RoleEquipBag newBean() {
			return new xbean.__.RoleEquipBag();
		}
	};

	public static RoleEquipBag newRoleEquipBag() {
		return poolRoleEquipBag.get();
	}

	public static void _reset_unsafe_add_(RoleEquipBag bean) {
		poolRoleEquipBag._reset_unsafe_add_(bean);
	}

	public static void padd(RoleEquipBag bean) {
		xdb.Procedure.padd(bean, poolRoleEquipBag);
	}

	public static RoleEquipBag newRoleEquipBagData() {
		return new xbean.__.RoleEquipBag.Data();
	}

	public static xdb.util.BeanPool<AdvancedEquip> poolAdvancedEquip = new xdb.util.BeanPool<AdvancedEquip>() {
		@Override
		protected AdvancedEquip newBean() {
			return new xbean.__.AdvancedEquip();
		}
	};

	public static AdvancedEquip newAdvancedEquip() {
		return poolAdvancedEquip.get();
	}

	public static void _reset_unsafe_add_(AdvancedEquip bean) {
		poolAdvancedEquip._reset_unsafe_add_(bean);
	}

	public static void padd(AdvancedEquip bean) {
		xdb.Procedure.padd(bean, poolAdvancedEquip);
	}

	public static AdvancedEquip newAdvancedEquipData() {
		return new xbean.__.AdvancedEquip.Data();
	}

	public static xdb.util.BeanPool<Fragment> poolFragment = new xdb.util.BeanPool<Fragment>() {
		@Override
		protected Fragment newBean() {
			return new xbean.__.Fragment();
		}
	};

	public static Fragment newFragment() {
		return poolFragment.get();
	}

	public static void _reset_unsafe_add_(Fragment bean) {
		poolFragment._reset_unsafe_add_(bean);
	}

	public static void padd(Fragment bean) {
		xdb.Procedure.padd(bean, poolFragment);
	}

	public static Fragment newFragmentData() {
		return new xbean.__.Fragment.Data();
	}

	public static xdb.util.BeanPool<RoleFragmentBag> poolRoleFragmentBag = new xdb.util.BeanPool<RoleFragmentBag>() {
		@Override
		protected RoleFragmentBag newBean() {
			return new xbean.__.RoleFragmentBag();
		}
	};

	public static RoleFragmentBag newRoleFragmentBag() {
		return poolRoleFragmentBag.get();
	}

	public static void _reset_unsafe_add_(RoleFragmentBag bean) {
		poolRoleFragmentBag._reset_unsafe_add_(bean);
	}

	public static void padd(RoleFragmentBag bean) {
		xdb.Procedure.padd(bean, poolRoleFragmentBag);
	}

	public static RoleFragmentBag newRoleFragmentBagData() {
		return new xbean.__.RoleFragmentBag.Data();
	}

	public static xdb.util.BeanPool<Talisman> poolTalisman = new xdb.util.BeanPool<Talisman>() {
		@Override
		protected Talisman newBean() {
			return new xbean.__.Talisman();
		}
	};

	public static Talisman newTalisman() {
		return poolTalisman.get();
	}

	public static void _reset_unsafe_add_(Talisman bean) {
		poolTalisman._reset_unsafe_add_(bean);
	}

	public static void padd(Talisman bean) {
		xdb.Procedure.padd(bean, poolTalisman);
	}

	public static Talisman newTalismanData() {
		return new xbean.__.Talisman.Data();
	}

	public static xdb.util.BeanPool<RoleTalismanBag> poolRoleTalismanBag = new xdb.util.BeanPool<RoleTalismanBag>() {
		@Override
		protected RoleTalismanBag newBean() {
			return new xbean.__.RoleTalismanBag();
		}
	};

	public static RoleTalismanBag newRoleTalismanBag() {
		return poolRoleTalismanBag.get();
	}

	public static void _reset_unsafe_add_(RoleTalismanBag bean) {
		poolRoleTalismanBag._reset_unsafe_add_(bean);
	}

	public static void padd(RoleTalismanBag bean) {
		xdb.Procedure.padd(bean, poolRoleTalismanBag);
	}

	public static RoleTalismanBag newRoleTalismanBagData() {
		return new xbean.__.RoleTalismanBag.Data();
	}

	public static xdb.util.BeanPool<AmuletProperty> poolAmuletProperty = new xdb.util.BeanPool<AmuletProperty>() {
		@Override
		protected AmuletProperty newBean() {
			return new xbean.__.AmuletProperty();
		}
	};

	public static AmuletProperty newAmuletProperty() {
		return poolAmuletProperty.get();
	}

	public static void _reset_unsafe_add_(AmuletProperty bean) {
		poolAmuletProperty._reset_unsafe_add_(bean);
	}

	public static void padd(AmuletProperty bean) {
		xdb.Procedure.padd(bean, poolAmuletProperty);
	}

	public static AmuletProperty newAmuletPropertyData() {
		return new xbean.__.AmuletProperty.Data();
	}

	public static xdb.util.BeanPool<AmuletPage> poolAmuletPage = new xdb.util.BeanPool<AmuletPage>() {
		@Override
		protected AmuletPage newBean() {
			return new xbean.__.AmuletPage();
		}
	};

	public static AmuletPage newAmuletPage() {
		return poolAmuletPage.get();
	}

	public static void _reset_unsafe_add_(AmuletPage bean) {
		poolAmuletPage._reset_unsafe_add_(bean);
	}

	public static void padd(AmuletPage bean) {
		xdb.Procedure.padd(bean, poolAmuletPage);
	}

	public static AmuletPage newAmuletPageData() {
		return new xbean.__.AmuletPage.Data();
	}

	public static xdb.util.BeanPool<RoleAmuletInfo> poolRoleAmuletInfo = new xdb.util.BeanPool<RoleAmuletInfo>() {
		@Override
		protected RoleAmuletInfo newBean() {
			return new xbean.__.RoleAmuletInfo();
		}
	};

	public static RoleAmuletInfo newRoleAmuletInfo() {
		return poolRoleAmuletInfo.get();
	}

	public static void _reset_unsafe_add_(RoleAmuletInfo bean) {
		poolRoleAmuletInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleAmuletInfo bean) {
		xdb.Procedure.padd(bean, poolRoleAmuletInfo);
	}

	public static RoleAmuletInfo newRoleAmuletInfoData() {
		return new xbean.__.RoleAmuletInfo.Data();
	}

	public static xdb.util.BeanPool<Jewelry> poolJewelry = new xdb.util.BeanPool<Jewelry>() {
		@Override
		protected Jewelry newBean() {
			return new xbean.__.Jewelry();
		}
	};

	public static Jewelry newJewelry() {
		return poolJewelry.get();
	}

	public static void _reset_unsafe_add_(Jewelry bean) {
		poolJewelry._reset_unsafe_add_(bean);
	}

	public static void padd(Jewelry bean) {
		xdb.Procedure.padd(bean, poolJewelry);
	}

	public static Jewelry newJewelryData() {
		return new xbean.__.Jewelry.Data();
	}

	public static xdb.util.BeanPool<RoleJadeInfo> poolRoleJadeInfo = new xdb.util.BeanPool<RoleJadeInfo>() {
		@Override
		protected RoleJadeInfo newBean() {
			return new xbean.__.RoleJadeInfo();
		}
	};

	public static RoleJadeInfo newRoleJadeInfo() {
		return poolRoleJadeInfo.get();
	}

	public static void _reset_unsafe_add_(RoleJadeInfo bean) {
		poolRoleJadeInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleJadeInfo bean) {
		xdb.Procedure.padd(bean, poolRoleJadeInfo);
	}

	public static RoleJadeInfo newRoleJadeInfoData() {
		return new xbean.__.RoleJadeInfo.Data();
	}

	public static xdb.util.BeanPool<Pet> poolPet = new xdb.util.BeanPool<Pet>() {
		@Override
		protected Pet newBean() {
			return new xbean.__.Pet();
		}
	};

	public static Pet newPet() {
		return poolPet.get();
	}

	public static void _reset_unsafe_add_(Pet bean) {
		poolPet._reset_unsafe_add_(bean);
	}

	public static void padd(Pet bean) {
		xdb.Procedure.padd(bean, poolPet);
	}

	public static Pet newPetData() {
		return new xbean.__.Pet.Data();
	}

	public static xdb.util.BeanPool<RolePet> poolRolePet = new xdb.util.BeanPool<RolePet>() {
		@Override
		protected RolePet newBean() {
			return new xbean.__.RolePet();
		}
	};

	public static RolePet newRolePet() {
		return poolRolePet.get();
	}

	public static void _reset_unsafe_add_(RolePet bean) {
		poolRolePet._reset_unsafe_add_(bean);
	}

	public static void padd(RolePet bean) {
		xdb.Procedure.padd(bean, poolRolePet);
	}

	public static RolePet newRolePetData() {
		return new xbean.__.RolePet.Data();
	}

	public static xdb.util.BeanPool<RoleEquipDepot> poolRoleEquipDepot = new xdb.util.BeanPool<RoleEquipDepot>() {
		@Override
		protected RoleEquipDepot newBean() {
			return new xbean.__.RoleEquipDepot();
		}
	};

	public static RoleEquipDepot newRoleEquipDepot() {
		return poolRoleEquipDepot.get();
	}

	public static void _reset_unsafe_add_(RoleEquipDepot bean) {
		poolRoleEquipDepot._reset_unsafe_add_(bean);
	}

	public static void padd(RoleEquipDepot bean) {
		xdb.Procedure.padd(bean, poolRoleEquipDepot);
	}

	public static RoleEquipDepot newRoleEquipDepotData() {
		return new xbean.__.RoleEquipDepot.Data();
	}

	public static xdb.util.BeanPool<RoleFragmentDepot> poolRoleFragmentDepot = new xdb.util.BeanPool<RoleFragmentDepot>() {
		@Override
		protected RoleFragmentDepot newBean() {
			return new xbean.__.RoleFragmentDepot();
		}
	};

	public static RoleFragmentDepot newRoleFragmentDepot() {
		return poolRoleFragmentDepot.get();
	}

	public static void _reset_unsafe_add_(RoleFragmentDepot bean) {
		poolRoleFragmentDepot._reset_unsafe_add_(bean);
	}

	public static void padd(RoleFragmentDepot bean) {
		xdb.Procedure.padd(bean, poolRoleFragmentDepot);
	}

	public static RoleFragmentDepot newRoleFragmentDepotData() {
		return new xbean.__.RoleFragmentDepot.Data();
	}

	public static xdb.util.BeanPool<RoleItemDepot> poolRoleItemDepot = new xdb.util.BeanPool<RoleItemDepot>() {
		@Override
		protected RoleItemDepot newBean() {
			return new xbean.__.RoleItemDepot();
		}
	};

	public static RoleItemDepot newRoleItemDepot() {
		return poolRoleItemDepot.get();
	}

	public static void _reset_unsafe_add_(RoleItemDepot bean) {
		poolRoleItemDepot._reset_unsafe_add_(bean);
	}

	public static void padd(RoleItemDepot bean) {
		xdb.Procedure.padd(bean, poolRoleItemDepot);
	}

	public static RoleItemDepot newRoleItemDepotData() {
		return new xbean.__.RoleItemDepot.Data();
	}

	public static xdb.util.BeanPool<RoleTalismanDepot> poolRoleTalismanDepot = new xdb.util.BeanPool<RoleTalismanDepot>() {
		@Override
		protected RoleTalismanDepot newBean() {
			return new xbean.__.RoleTalismanDepot();
		}
	};

	public static RoleTalismanDepot newRoleTalismanDepot() {
		return poolRoleTalismanDepot.get();
	}

	public static void _reset_unsafe_add_(RoleTalismanDepot bean) {
		poolRoleTalismanDepot._reset_unsafe_add_(bean);
	}

	public static void padd(RoleTalismanDepot bean) {
		xdb.Procedure.padd(bean, poolRoleTalismanDepot);
	}

	public static RoleTalismanDepot newRoleTalismanDepotData() {
		return new xbean.__.RoleTalismanDepot.Data();
	}

	public static xdb.util.BeanPool<Dress> poolDress = new xdb.util.BeanPool<Dress>() {
		@Override
		protected Dress newBean() {
			return new xbean.__.Dress();
		}
	};

	public static Dress newDress() {
		return poolDress.get();
	}

	public static void _reset_unsafe_add_(Dress bean) {
		poolDress._reset_unsafe_add_(bean);
	}

	public static void padd(Dress bean) {
		xdb.Procedure.padd(bean, poolDress);
	}

	public static Dress newDressData() {
		return new xbean.__.Dress.Data();
	}

	public static xdb.util.BeanPool<RoleDress> poolRoleDress = new xdb.util.BeanPool<RoleDress>() {
		@Override
		protected RoleDress newBean() {
			return new xbean.__.RoleDress();
		}
	};

	public static RoleDress newRoleDress() {
		return poolRoleDress.get();
	}

	public static void _reset_unsafe_add_(RoleDress bean) {
		poolRoleDress._reset_unsafe_add_(bean);
	}

	public static void padd(RoleDress bean) {
		xdb.Procedure.padd(bean, poolRoleDress);
	}

	public static RoleDress newRoleDressData() {
		return new xbean.__.RoleDress.Data();
	}

	public static xdb.util.BeanPool<Ride> poolRide = new xdb.util.BeanPool<Ride>() {
		@Override
		protected Ride newBean() {
			return new xbean.__.Ride();
		}
	};

	public static Ride newRide() {
		return poolRide.get();
	}

	public static void _reset_unsafe_add_(Ride bean) {
		poolRide._reset_unsafe_add_(bean);
	}

	public static void padd(Ride bean) {
		xdb.Procedure.padd(bean, poolRide);
	}

	public static Ride newRideData() {
		return new xbean.__.Ride.Data();
	}

	public static xdb.util.BeanPool<RoleRide> poolRoleRide = new xdb.util.BeanPool<RoleRide>() {
		@Override
		protected RoleRide newBean() {
			return new xbean.__.RoleRide();
		}
	};

	public static RoleRide newRoleRide() {
		return poolRoleRide.get();
	}

	public static void _reset_unsafe_add_(RoleRide bean) {
		poolRoleRide._reset_unsafe_add_(bean);
	}

	public static void padd(RoleRide bean) {
		xdb.Procedure.padd(bean, poolRoleRide);
	}

	public static RoleRide newRoleRideData() {
		return new xbean.__.RoleRide.Data();
	}

	public static xdb.util.BeanPool<ARankInfo> poolARankInfo = new xdb.util.BeanPool<ARankInfo>() {
		@Override
		protected ARankInfo newBean() {
			return new xbean.__.ARankInfo();
		}
	};

	public static ARankInfo newARankInfo() {
		return poolARankInfo.get();
	}

	public static void _reset_unsafe_add_(ARankInfo bean) {
		poolARankInfo._reset_unsafe_add_(bean);
	}

	public static void padd(ARankInfo bean) {
		xdb.Procedure.padd(bean, poolARankInfo);
	}

	public static ARankInfo newARankInfoData() {
		return new xbean.__.ARankInfo.Data();
	}

	public static xdb.util.BeanPool<RoleRank> poolRoleRank = new xdb.util.BeanPool<RoleRank>() {
		@Override
		protected RoleRank newBean() {
			return new xbean.__.RoleRank();
		}
	};

	public static RoleRank newRoleRank() {
		return poolRoleRank.get();
	}

	public static void _reset_unsafe_add_(RoleRank bean) {
		poolRoleRank._reset_unsafe_add_(bean);
	}

	public static void padd(RoleRank bean) {
		xdb.Procedure.padd(bean, poolRoleRank);
	}

	public static RoleRank newRoleRankData() {
		return new xbean.__.RoleRank.Data();
	}

	public static xdb.util.BeanPool<RankRecord> poolRankRecord = new xdb.util.BeanPool<RankRecord>() {
		@Override
		protected RankRecord newBean() {
			return new xbean.__.RankRecord();
		}
	};

	public static RankRecord newRankRecord() {
		return poolRankRecord.get();
	}

	public static void _reset_unsafe_add_(RankRecord bean) {
		poolRankRecord._reset_unsafe_add_(bean);
	}

	public static void padd(RankRecord bean) {
		xdb.Procedure.padd(bean, poolRankRecord);
	}

	public static RankRecord newRankRecordData() {
		return new xbean.__.RankRecord.Data();
	}

	public static xdb.util.BeanPool<ARank> poolARank = new xdb.util.BeanPool<ARank>() {
		@Override
		protected ARank newBean() {
			return new xbean.__.ARank();
		}
	};

	public static ARank newARank() {
		return poolARank.get();
	}

	public static void _reset_unsafe_add_(ARank bean) {
		poolARank._reset_unsafe_add_(bean);
	}

	public static void padd(ARank bean) {
		xdb.Procedure.padd(bean, poolARank);
	}

	public static ARank newARankData() {
		return new xbean.__.ARank.Data();
	}

	public static xdb.util.BeanPool<BoardEntry> poolBoardEntry = new xdb.util.BeanPool<BoardEntry>() {
		@Override
		protected BoardEntry newBean() {
			return new xbean.__.BoardEntry();
		}
	};

	public static BoardEntry newBoardEntry() {
		return poolBoardEntry.get();
	}

	public static void _reset_unsafe_add_(BoardEntry bean) {
		poolBoardEntry._reset_unsafe_add_(bean);
	}

	public static void padd(BoardEntry bean) {
		xdb.Procedure.padd(bean, poolBoardEntry);
	}

	public static BoardEntry newBoardEntryData() {
		return new xbean.__.BoardEntry.Data();
	}

	public static xdb.util.BeanPool<BoardInfo> poolBoardInfo = new xdb.util.BeanPool<BoardInfo>() {
		@Override
		protected BoardInfo newBean() {
			return new xbean.__.BoardInfo();
		}
	};

	public static BoardInfo newBoardInfo() {
		return poolBoardInfo.get();
	}

	public static void _reset_unsafe_add_(BoardInfo bean) {
		poolBoardInfo._reset_unsafe_add_(bean);
	}

	public static void padd(BoardInfo bean) {
		xdb.Procedure.padd(bean, poolBoardInfo);
	}

	public static BoardInfo newBoardInfoData() {
		return new xbean.__.BoardInfo.Data();
	}

	public static xdb.util.BeanPool<BoardRecordEntry> poolBoardRecordEntry = new xdb.util.BeanPool<BoardRecordEntry>() {
		@Override
		protected BoardRecordEntry newBean() {
			return new xbean.__.BoardRecordEntry();
		}
	};

	public static BoardRecordEntry newBoardRecordEntry() {
		return poolBoardRecordEntry.get();
	}

	public static void _reset_unsafe_add_(BoardRecordEntry bean) {
		poolBoardRecordEntry._reset_unsafe_add_(bean);
	}

	public static void padd(BoardRecordEntry bean) {
		xdb.Procedure.padd(bean, poolBoardRecordEntry);
	}

	public static BoardRecordEntry newBoardRecordEntryData() {
		return new xbean.__.BoardRecordEntry.Data();
	}

	public static xdb.util.BeanPool<BoardRecord> poolBoardRecord = new xdb.util.BeanPool<BoardRecord>() {
		@Override
		protected BoardRecord newBean() {
			return new xbean.__.BoardRecord();
		}
	};

	public static BoardRecord newBoardRecord() {
		return poolBoardRecord.get();
	}

	public static void _reset_unsafe_add_(BoardRecord bean) {
		poolBoardRecord._reset_unsafe_add_(bean);
	}

	public static void padd(BoardRecord bean) {
		xdb.Procedure.padd(bean, poolBoardRecord);
	}

	public static BoardRecord newBoardRecordData() {
		return new xbean.__.BoardRecord.Data();
	}

	public static xdb.util.BeanPool<TaskHistory> poolTaskHistory = new xdb.util.BeanPool<TaskHistory>() {
		@Override
		protected TaskHistory newBean() {
			return new xbean.__.TaskHistory();
		}
	};

	public static TaskHistory newTaskHistory() {
		return poolTaskHistory.get();
	}

	public static void _reset_unsafe_add_(TaskHistory bean) {
		poolTaskHistory._reset_unsafe_add_(bean);
	}

	public static void padd(TaskHistory bean) {
		xdb.Procedure.padd(bean, poolTaskHistory);
	}

	public static TaskHistory newTaskHistoryData() {
		return new xbean.__.TaskHistory.Data();
	}

	public static xdb.util.BeanPool<TaskData> poolTaskData = new xdb.util.BeanPool<TaskData>() {
		@Override
		protected TaskData newBean() {
			return new xbean.__.TaskData();
		}
	};

	public static TaskData newTaskData() {
		return poolTaskData.get();
	}

	public static void _reset_unsafe_add_(TaskData bean) {
		poolTaskData._reset_unsafe_add_(bean);
	}

	public static void padd(TaskData bean) {
		xdb.Procedure.padd(bean, poolTaskData);
	}

	public static TaskData newTaskDataData() {
		return new xbean.__.TaskData.Data();
	}

	public static xdb.util.BeanPool<FamilyTaskDetail> poolFamilyTaskDetail = new xdb.util.BeanPool<FamilyTaskDetail>() {
		@Override
		protected FamilyTaskDetail newBean() {
			return new xbean.__.FamilyTaskDetail();
		}
	};

	public static FamilyTaskDetail newFamilyTaskDetail() {
		return poolFamilyTaskDetail.get();
	}

	public static void _reset_unsafe_add_(FamilyTaskDetail bean) {
		poolFamilyTaskDetail._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyTaskDetail bean) {
		xdb.Procedure.padd(bean, poolFamilyTaskDetail);
	}

	public static FamilyTaskDetail newFamilyTaskDetailData() {
		return new xbean.__.FamilyTaskDetail.Data();
	}

	public static xdb.util.BeanPool<RoleTask> poolRoleTask = new xdb.util.BeanPool<RoleTask>() {
		@Override
		protected RoleTask newBean() {
			return new xbean.__.RoleTask();
		}
	};

	public static RoleTask newRoleTask() {
		return poolRoleTask.get();
	}

	public static void _reset_unsafe_add_(RoleTask bean) {
		poolRoleTask._reset_unsafe_add_(bean);
	}

	public static void padd(RoleTask bean) {
		xdb.Procedure.padd(bean, poolRoleTask);
	}

	public static RoleTask newRoleTaskData() {
		return new xbean.__.RoleTask.Data();
	}

	public static xdb.util.BeanPool<Bonus> poolBonus = new xdb.util.BeanPool<Bonus>() {
		@Override
		protected Bonus newBean() {
			return new xbean.__.Bonus();
		}
	};

	public static Bonus newBonus() {
		return poolBonus.get();
	}

	public static void _reset_unsafe_add_(Bonus bean) {
		poolBonus._reset_unsafe_add_(bean);
	}

	public static void padd(Bonus bean) {
		xdb.Procedure.padd(bean, poolBonus);
	}

	public static Bonus newBonusData() {
		return new xbean.__.Bonus.Data();
	}

	public static xdb.util.BeanPool<Mail> poolMail = new xdb.util.BeanPool<Mail>() {
		@Override
		protected Mail newBean() {
			return new xbean.__.Mail();
		}
	};

	public static Mail newMail() {
		return poolMail.get();
	}

	public static void _reset_unsafe_add_(Mail bean) {
		poolMail._reset_unsafe_add_(bean);
	}

	public static void padd(Mail bean) {
		xdb.Procedure.padd(bean, poolMail);
	}

	public static Mail newMailData() {
		return new xbean.__.Mail.Data();
	}

	public static xdb.util.BeanPool<MailBox> poolMailBox = new xdb.util.BeanPool<MailBox>() {
		@Override
		protected MailBox newBean() {
			return new xbean.__.MailBox();
		}
	};

	public static MailBox newMailBox() {
		return poolMailBox.get();
	}

	public static void _reset_unsafe_add_(MailBox bean) {
		poolMailBox._reset_unsafe_add_(bean);
	}

	public static void padd(MailBox bean) {
		xdb.Procedure.padd(bean, poolMailBox);
	}

	public static MailBox newMailBoxData() {
		return new xbean.__.MailBox.Data();
	}

	public static xdb.util.BeanPool<SystemMail> poolSystemMail = new xdb.util.BeanPool<SystemMail>() {
		@Override
		protected SystemMail newBean() {
			return new xbean.__.SystemMail();
		}
	};

	public static SystemMail newSystemMail() {
		return poolSystemMail.get();
	}

	public static void _reset_unsafe_add_(SystemMail bean) {
		poolSystemMail._reset_unsafe_add_(bean);
	}

	public static void padd(SystemMail bean) {
		xdb.Procedure.padd(bean, poolSystemMail);
	}

	public static SystemMail newSystemMailData() {
		return new xbean.__.SystemMail.Data();
	}

	public static xdb.util.BeanPool<RoleWelfareInfo> poolRoleWelfareInfo = new xdb.util.BeanPool<RoleWelfareInfo>() {
		@Override
		protected RoleWelfareInfo newBean() {
			return new xbean.__.RoleWelfareInfo();
		}
	};

	public static RoleWelfareInfo newRoleWelfareInfo() {
		return poolRoleWelfareInfo.get();
	}

	public static void _reset_unsafe_add_(RoleWelfareInfo bean) {
		poolRoleWelfareInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleWelfareInfo bean) {
		xdb.Procedure.padd(bean, poolRoleWelfareInfo);
	}

	public static RoleWelfareInfo newRoleWelfareInfoData() {
		return new xbean.__.RoleWelfareInfo.Data();
	}

	public static xdb.util.BeanPool<DailyActive> poolDailyActive = new xdb.util.BeanPool<DailyActive>() {
		@Override
		protected DailyActive newBean() {
			return new xbean.__.DailyActive();
		}
	};

	public static DailyActive newDailyActive() {
		return poolDailyActive.get();
	}

	public static void _reset_unsafe_add_(DailyActive bean) {
		poolDailyActive._reset_unsafe_add_(bean);
	}

	public static void padd(DailyActive bean) {
		xdb.Procedure.padd(bean, poolDailyActive);
	}

	public static DailyActive newDailyActiveData() {
		return new xbean.__.DailyActive.Data();
	}

	public static xdb.util.BeanPool<PickCardInfo> poolPickCardInfo = new xdb.util.BeanPool<PickCardInfo>() {
		@Override
		protected PickCardInfo newBean() {
			return new xbean.__.PickCardInfo();
		}
	};

	public static PickCardInfo newPickCardInfo() {
		return poolPickCardInfo.get();
	}

	public static void _reset_unsafe_add_(PickCardInfo bean) {
		poolPickCardInfo._reset_unsafe_add_(bean);
	}

	public static void padd(PickCardInfo bean) {
		xdb.Procedure.padd(bean, poolPickCardInfo);
	}

	public static PickCardInfo newPickCardInfoData() {
		return new xbean.__.PickCardInfo.Data();
	}

	public static xdb.util.BeanPool<Limit> poolLimit = new xdb.util.BeanPool<Limit>() {
		@Override
		protected Limit newBean() {
			return new xbean.__.Limit();
		}
	};

	public static Limit newLimit() {
		return poolLimit.get();
	}

	public static void _reset_unsafe_add_(Limit bean) {
		poolLimit._reset_unsafe_add_(bean);
	}

	public static void padd(Limit bean) {
		xdb.Procedure.padd(bean, poolLimit);
	}

	public static Limit newLimitData() {
		return new xbean.__.Limit.Data();
	}

	public static xdb.util.BeanPool<CoolDown> poolCoolDown = new xdb.util.BeanPool<CoolDown>() {
		@Override
		protected CoolDown newBean() {
			return new xbean.__.CoolDown();
		}
	};

	public static CoolDown newCoolDown() {
		return poolCoolDown.get();
	}

	public static void _reset_unsafe_add_(CoolDown bean) {
		poolCoolDown._reset_unsafe_add_(bean);
	}

	public static void padd(CoolDown bean) {
		xdb.Procedure.padd(bean, poolCoolDown);
	}

	public static CoolDown newCoolDownData() {
		return new xbean.__.CoolDown.Data();
	}

	public static xdb.util.BeanPool<RoleLimit> poolRoleLimit = new xdb.util.BeanPool<RoleLimit>() {
		@Override
		protected RoleLimit newBean() {
			return new xbean.__.RoleLimit();
		}
	};

	public static RoleLimit newRoleLimit() {
		return poolRoleLimit.get();
	}

	public static void _reset_unsafe_add_(RoleLimit bean) {
		poolRoleLimit._reset_unsafe_add_(bean);
	}

	public static void padd(RoleLimit bean) {
		xdb.Procedure.padd(bean, poolRoleLimit);
	}

	public static RoleLimit newRoleLimitData() {
		return new xbean.__.RoleLimit.Data();
	}

	public static xdb.util.BeanPool<DailyArena> poolDailyArena = new xdb.util.BeanPool<DailyArena>() {
		@Override
		protected DailyArena newBean() {
			return new xbean.__.DailyArena();
		}
	};

	public static DailyArena newDailyArena() {
		return poolDailyArena.get();
	}

	public static void _reset_unsafe_add_(DailyArena bean) {
		poolDailyArena._reset_unsafe_add_(bean);
	}

	public static void padd(DailyArena bean) {
		xdb.Procedure.padd(bean, poolDailyArena);
	}

	public static DailyArena newDailyArenaData() {
		return new xbean.__.DailyArena.Data();
	}

	public static xdb.util.BeanPool<DailyMonsterExp> poolDailyMonsterExp = new xdb.util.BeanPool<DailyMonsterExp>() {
		@Override
		protected DailyMonsterExp newBean() {
			return new xbean.__.DailyMonsterExp();
		}
	};

	public static DailyMonsterExp newDailyMonsterExp() {
		return poolDailyMonsterExp.get();
	}

	public static void _reset_unsafe_add_(DailyMonsterExp bean) {
		poolDailyMonsterExp._reset_unsafe_add_(bean);
	}

	public static void padd(DailyMonsterExp bean) {
		xdb.Procedure.padd(bean, poolDailyMonsterExp);
	}

	public static DailyMonsterExp newDailyMonsterExpData() {
		return new xbean.__.DailyMonsterExp.Data();
	}

	public static xdb.util.BeanPool<DailyResetData> poolDailyResetData = new xdb.util.BeanPool<DailyResetData>() {
		@Override
		protected DailyResetData newBean() {
			return new xbean.__.DailyResetData();
		}
	};

	public static DailyResetData newDailyResetData() {
		return poolDailyResetData.get();
	}

	public static void _reset_unsafe_add_(DailyResetData bean) {
		poolDailyResetData._reset_unsafe_add_(bean);
	}

	public static void padd(DailyResetData bean) {
		xdb.Procedure.padd(bean, poolDailyResetData);
	}

	public static DailyResetData newDailyResetDataData() {
		return new xbean.__.DailyResetData.Data();
	}

	public static xdb.util.BeanPool<Notice> poolNotice = new xdb.util.BeanPool<Notice>() {
		@Override
		protected Notice newBean() {
			return new xbean.__.Notice();
		}
	};

	public static Notice newNotice() {
		return poolNotice.get();
	}

	public static void _reset_unsafe_add_(Notice bean) {
		poolNotice._reset_unsafe_add_(bean);
	}

	public static void padd(Notice bean) {
		xdb.Procedure.padd(bean, poolNotice);
	}

	public static Notice newNoticeData() {
		return new xbean.__.Notice.Data();
	}

	public static xdb.util.BeanPool<RoleNotice> poolRoleNotice = new xdb.util.BeanPool<RoleNotice>() {
		@Override
		protected RoleNotice newBean() {
			return new xbean.__.RoleNotice();
		}
	};

	public static RoleNotice newRoleNotice() {
		return poolRoleNotice.get();
	}

	public static void _reset_unsafe_add_(RoleNotice bean) {
		poolRoleNotice._reset_unsafe_add_(bean);
	}

	public static void padd(RoleNotice bean) {
		xdb.Procedure.padd(bean, poolRoleNotice);
	}

	public static RoleNotice newRoleNoticeData() {
		return new xbean.__.RoleNotice.Data();
	}

	public static xdb.util.BeanPool<ArenaFightReport> poolArenaFightReport = new xdb.util.BeanPool<ArenaFightReport>() {
		@Override
		protected ArenaFightReport newBean() {
			return new xbean.__.ArenaFightReport();
		}
	};

	public static ArenaFightReport newArenaFightReport() {
		return poolArenaFightReport.get();
	}

	public static void _reset_unsafe_add_(ArenaFightReport bean) {
		poolArenaFightReport._reset_unsafe_add_(bean);
	}

	public static void padd(ArenaFightReport bean) {
		xdb.Procedure.padd(bean, poolArenaFightReport);
	}

	public static ArenaFightReport newArenaFightReportData() {
		return new xbean.__.ArenaFightReport.Data();
	}

	public static xdb.util.BeanPool<RoleArena> poolRoleArena = new xdb.util.BeanPool<RoleArena>() {
		@Override
		protected RoleArena newBean() {
			return new xbean.__.RoleArena();
		}
	};

	public static RoleArena newRoleArena() {
		return poolRoleArena.get();
	}

	public static void _reset_unsafe_add_(RoleArena bean) {
		poolRoleArena._reset_unsafe_add_(bean);
	}

	public static void padd(RoleArena bean) {
		xdb.Procedure.padd(bean, poolRoleArena);
	}

	public static RoleArena newRoleArenaData() {
		return new xbean.__.RoleArena.Data();
	}

	public static xdb.util.BeanPool<RoleExchange> poolRoleExchange = new xdb.util.BeanPool<RoleExchange>() {
		@Override
		protected RoleExchange newBean() {
			return new xbean.__.RoleExchange();
		}
	};

	public static RoleExchange newRoleExchange() {
		return poolRoleExchange.get();
	}

	public static void _reset_unsafe_add_(RoleExchange bean) {
		poolRoleExchange._reset_unsafe_add_(bean);
	}

	public static void padd(RoleExchange bean) {
		xdb.Procedure.padd(bean, poolRoleExchange);
	}

	public static RoleExchange newRoleExchangeData() {
		return new xbean.__.RoleExchange.Data();
	}

	public static xdb.util.BeanPool<ExchangeLog> poolExchangeLog = new xdb.util.BeanPool<ExchangeLog>() {
		@Override
		protected ExchangeLog newBean() {
			return new xbean.__.ExchangeLog();
		}
	};

	public static ExchangeLog newExchangeLog() {
		return poolExchangeLog.get();
	}

	public static void _reset_unsafe_add_(ExchangeLog bean) {
		poolExchangeLog._reset_unsafe_add_(bean);
	}

	public static void padd(ExchangeLog bean) {
		xdb.Procedure.padd(bean, poolExchangeLog);
	}

	public static ExchangeLog newExchangeLogData() {
		return new xbean.__.ExchangeLog.Data();
	}

	public static xdb.util.BeanPool<ExchangeItem> poolExchangeItem = new xdb.util.BeanPool<ExchangeItem>() {
		@Override
		protected ExchangeItem newBean() {
			return new xbean.__.ExchangeItem();
		}
	};

	public static ExchangeItem newExchangeItem() {
		return poolExchangeItem.get();
	}

	public static void _reset_unsafe_add_(ExchangeItem bean) {
		poolExchangeItem._reset_unsafe_add_(bean);
	}

	public static void padd(ExchangeItem bean) {
		xdb.Procedure.padd(bean, poolExchangeItem);
	}

	public static ExchangeItem newExchangeItemData() {
		return new xbean.__.ExchangeItem.Data();
	}

	public static xdb.util.BeanPool<RoleTeamInfo> poolRoleTeamInfo = new xdb.util.BeanPool<RoleTeamInfo>() {
		@Override
		protected RoleTeamInfo newBean() {
			return new xbean.__.RoleTeamInfo();
		}
	};

	public static RoleTeamInfo newRoleTeamInfo() {
		return poolRoleTeamInfo.get();
	}

	public static void _reset_unsafe_add_(RoleTeamInfo bean) {
		poolRoleTeamInfo._reset_unsafe_add_(bean);
	}

	public static void padd(RoleTeamInfo bean) {
		xdb.Procedure.padd(bean, poolRoleTeamInfo);
	}

	public static RoleTeamInfo newRoleTeamInfoData() {
		return new xbean.__.RoleTeamInfo.Data();
	}

	public static xdb.util.BeanPool<TeamMember> poolTeamMember = new xdb.util.BeanPool<TeamMember>() {
		@Override
		protected TeamMember newBean() {
			return new xbean.__.TeamMember();
		}
	};

	public static TeamMember newTeamMember() {
		return poolTeamMember.get();
	}

	public static void _reset_unsafe_add_(TeamMember bean) {
		poolTeamMember._reset_unsafe_add_(bean);
	}

	public static void padd(TeamMember bean) {
		xdb.Procedure.padd(bean, poolTeamMember);
	}

	public static TeamMember newTeamMemberData() {
		return new xbean.__.TeamMember.Data();
	}

	public static xdb.util.BeanPool<Team> poolTeam = new xdb.util.BeanPool<Team>() {
		@Override
		protected Team newBean() {
			return new xbean.__.Team();
		}
	};

	public static Team newTeam() {
		return poolTeam.get();
	}

	public static void _reset_unsafe_add_(Team bean) {
		poolTeam._reset_unsafe_add_(bean);
	}

	public static void padd(Team bean) {
		xdb.Procedure.padd(bean, poolTeam);
	}

	public static Team newTeamData() {
		return new xbean.__.Team.Data();
	}

	public static xdb.util.BeanPool<SkillInfo> poolSkillInfo = new xdb.util.BeanPool<SkillInfo>() {
		@Override
		protected SkillInfo newBean() {
			return new xbean.__.SkillInfo();
		}
	};

	public static SkillInfo newSkillInfo() {
		return poolSkillInfo.get();
	}

	public static void _reset_unsafe_add_(SkillInfo bean) {
		poolSkillInfo._reset_unsafe_add_(bean);
	}

	public static void padd(SkillInfo bean) {
		xdb.Procedure.padd(bean, poolSkillInfo);
	}

	public static SkillInfo newSkillInfoData() {
		return new xbean.__.SkillInfo.Data();
	}

	public static xdb.util.BeanPool<RoleSkill> poolRoleSkill = new xdb.util.BeanPool<RoleSkill>() {
		@Override
		protected RoleSkill newBean() {
			return new xbean.__.RoleSkill();
		}
	};

	public static RoleSkill newRoleSkill() {
		return poolRoleSkill.get();
	}

	public static void _reset_unsafe_add_(RoleSkill bean) {
		poolRoleSkill._reset_unsafe_add_(bean);
	}

	public static void padd(RoleSkill bean) {
		xdb.Procedure.padd(bean, poolRoleSkill);
	}

	public static RoleSkill newRoleSkillData() {
		return new xbean.__.RoleSkill.Data();
	}

	public static xdb.util.BeanPool<Title> poolTitle = new xdb.util.BeanPool<Title>() {
		@Override
		protected Title newBean() {
			return new xbean.__.Title();
		}
	};

	public static Title newTitle() {
		return poolTitle.get();
	}

	public static void _reset_unsafe_add_(Title bean) {
		poolTitle._reset_unsafe_add_(bean);
	}

	public static void padd(Title bean) {
		xdb.Procedure.padd(bean, poolTitle);
	}

	public static Title newTitleData() {
		return new xbean.__.Title.Data();
	}

	public static xdb.util.BeanPool<GroupTitle> poolGroupTitle = new xdb.util.BeanPool<GroupTitle>() {
		@Override
		protected GroupTitle newBean() {
			return new xbean.__.GroupTitle();
		}
	};

	public static GroupTitle newGroupTitle() {
		return poolGroupTitle.get();
	}

	public static void _reset_unsafe_add_(GroupTitle bean) {
		poolGroupTitle._reset_unsafe_add_(bean);
	}

	public static void padd(GroupTitle bean) {
		xdb.Procedure.padd(bean, poolGroupTitle);
	}

	public static GroupTitle newGroupTitleData() {
		return new xbean.__.GroupTitle.Data();
	}

	public static xdb.util.BeanPool<RoleTitle> poolRoleTitle = new xdb.util.BeanPool<RoleTitle>() {
		@Override
		protected RoleTitle newBean() {
			return new xbean.__.RoleTitle();
		}
	};

	public static RoleTitle newRoleTitle() {
		return poolRoleTitle.get();
	}

	public static void _reset_unsafe_add_(RoleTitle bean) {
		poolRoleTitle._reset_unsafe_add_(bean);
	}

	public static void padd(RoleTitle bean) {
		xdb.Procedure.padd(bean, poolRoleTitle);
	}

	public static RoleTitle newRoleTitleData() {
		return new xbean.__.RoleTitle.Data();
	}

	public static xdb.util.BeanPool<CounterSet> poolCounterSet = new xdb.util.BeanPool<CounterSet>() {
		@Override
		protected CounterSet newBean() {
			return new xbean.__.CounterSet();
		}
	};

	public static CounterSet newCounterSet() {
		return poolCounterSet.get();
	}

	public static void _reset_unsafe_add_(CounterSet bean) {
		poolCounterSet._reset_unsafe_add_(bean);
	}

	public static void padd(CounterSet bean) {
		xdb.Procedure.padd(bean, poolCounterSet);
	}

	public static CounterSet newCounterSetData() {
		return new xbean.__.CounterSet.Data();
	}

	public static xdb.util.BeanPool<RoleAchievement> poolRoleAchievement = new xdb.util.BeanPool<RoleAchievement>() {
		@Override
		protected RoleAchievement newBean() {
			return new xbean.__.RoleAchievement();
		}
	};

	public static RoleAchievement newRoleAchievement() {
		return poolRoleAchievement.get();
	}

	public static void _reset_unsafe_add_(RoleAchievement bean) {
		poolRoleAchievement._reset_unsafe_add_(bean);
	}

	public static void padd(RoleAchievement bean) {
		xdb.Procedure.padd(bean, poolRoleAchievement);
	}

	public static RoleAchievement newRoleAchievementData() {
		return new xbean.__.RoleAchievement.Data();
	}

	public static xdb.util.BeanPool<FamilyMember> poolFamilyMember = new xdb.util.BeanPool<FamilyMember>() {
		@Override
		protected FamilyMember newBean() {
			return new xbean.__.FamilyMember();
		}
	};

	public static FamilyMember newFamilyMember() {
		return poolFamilyMember.get();
	}

	public static void _reset_unsafe_add_(FamilyMember bean) {
		poolFamilyMember._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyMember bean) {
		xdb.Procedure.padd(bean, poolFamilyMember);
	}

	public static FamilyMember newFamilyMemberData() {
		return new xbean.__.FamilyMember.Data();
	}

	public static xdb.util.BeanPool<RoleFamily> poolRoleFamily = new xdb.util.BeanPool<RoleFamily>() {
		@Override
		protected RoleFamily newBean() {
			return new xbean.__.RoleFamily();
		}
	};

	public static RoleFamily newRoleFamily() {
		return poolRoleFamily.get();
	}

	public static void _reset_unsafe_add_(RoleFamily bean) {
		poolRoleFamily._reset_unsafe_add_(bean);
	}

	public static void padd(RoleFamily bean) {
		xdb.Procedure.padd(bean, poolRoleFamily);
	}

	public static RoleFamily newRoleFamilyData() {
		return new xbean.__.RoleFamily.Data();
	}

	public static xdb.util.BeanPool<FamilyLogReport> poolFamilyLogReport = new xdb.util.BeanPool<FamilyLogReport>() {
		@Override
		protected FamilyLogReport newBean() {
			return new xbean.__.FamilyLogReport();
		}
	};

	public static FamilyLogReport newFamilyLogReport() {
		return poolFamilyLogReport.get();
	}

	public static void _reset_unsafe_add_(FamilyLogReport bean) {
		poolFamilyLogReport._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyLogReport bean) {
		xdb.Procedure.padd(bean, poolFamilyLogReport);
	}

	public static FamilyLogReport newFamilyLogReportData() {
		return new xbean.__.FamilyLogReport.Data();
	}

	public static xdb.util.BeanPool<FamilyJobStaffList> poolFamilyJobStaffList = new xdb.util.BeanPool<FamilyJobStaffList>() {
		@Override
		protected FamilyJobStaffList newBean() {
			return new xbean.__.FamilyJobStaffList();
		}
	};

	public static FamilyJobStaffList newFamilyJobStaffList() {
		return poolFamilyJobStaffList.get();
	}

	public static void _reset_unsafe_add_(FamilyJobStaffList bean) {
		poolFamilyJobStaffList._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyJobStaffList bean) {
		xdb.Procedure.padd(bean, poolFamilyJobStaffList);
	}

	public static FamilyJobStaffList newFamilyJobStaffListData() {
		return new xbean.__.FamilyJobStaffList.Data();
	}

	public static xdb.util.BeanPool<FamilySkill> poolFamilySkill = new xdb.util.BeanPool<FamilySkill>() {
		@Override
		protected FamilySkill newBean() {
			return new xbean.__.FamilySkill();
		}
	};

	public static FamilySkill newFamilySkill() {
		return poolFamilySkill.get();
	}

	public static void _reset_unsafe_add_(FamilySkill bean) {
		poolFamilySkill._reset_unsafe_add_(bean);
	}

	public static void padd(FamilySkill bean) {
		xdb.Procedure.padd(bean, poolFamilySkill);
	}

	public static FamilySkill newFamilySkillData() {
		return new xbean.__.FamilySkill.Data();
	}

	public static xdb.util.BeanPool<FamilyWelfare> poolFamilyWelfare = new xdb.util.BeanPool<FamilyWelfare>() {
		@Override
		protected FamilyWelfare newBean() {
			return new xbean.__.FamilyWelfare();
		}
	};

	public static FamilyWelfare newFamilyWelfare() {
		return poolFamilyWelfare.get();
	}

	public static void _reset_unsafe_add_(FamilyWelfare bean) {
		poolFamilyWelfare._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyWelfare bean) {
		xdb.Procedure.padd(bean, poolFamilyWelfare);
	}

	public static FamilyWelfare newFamilyWelfareData() {
		return new xbean.__.FamilyWelfare.Data();
	}

	public static xdb.util.BeanPool<FamilyGodAnimal> poolFamilyGodAnimal = new xdb.util.BeanPool<FamilyGodAnimal>() {
		@Override
		protected FamilyGodAnimal newBean() {
			return new xbean.__.FamilyGodAnimal();
		}
	};

	public static FamilyGodAnimal newFamilyGodAnimal() {
		return poolFamilyGodAnimal.get();
	}

	public static void _reset_unsafe_add_(FamilyGodAnimal bean) {
		poolFamilyGodAnimal._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyGodAnimal bean) {
		xdb.Procedure.padd(bean, poolFamilyGodAnimal);
	}

	public static FamilyGodAnimal newFamilyGodAnimalData() {
		return new xbean.__.FamilyGodAnimal.Data();
	}

	public static xdb.util.BeanPool<FamilyActivity> poolFamilyActivity = new xdb.util.BeanPool<FamilyActivity>() {
		@Override
		protected FamilyActivity newBean() {
			return new xbean.__.FamilyActivity();
		}
	};

	public static FamilyActivity newFamilyActivity() {
		return poolFamilyActivity.get();
	}

	public static void _reset_unsafe_add_(FamilyActivity bean) {
		poolFamilyActivity._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyActivity bean) {
		xdb.Procedure.padd(bean, poolFamilyActivity);
	}

	public static FamilyActivity newFamilyActivityData() {
		return new xbean.__.FamilyActivity.Data();
	}

	public static xdb.util.BeanPool<FamilyGodAnimalAct> poolFamilyGodAnimalAct = new xdb.util.BeanPool<FamilyGodAnimalAct>() {
		@Override
		protected FamilyGodAnimalAct newBean() {
			return new xbean.__.FamilyGodAnimalAct();
		}
	};

	public static FamilyGodAnimalAct newFamilyGodAnimalAct() {
		return poolFamilyGodAnimalAct.get();
	}

	public static void _reset_unsafe_add_(FamilyGodAnimalAct bean) {
		poolFamilyGodAnimalAct._reset_unsafe_add_(bean);
	}

	public static void padd(FamilyGodAnimalAct bean) {
		xdb.Procedure.padd(bean, poolFamilyGodAnimalAct);
	}

	public static FamilyGodAnimalAct newFamilyGodAnimalActData() {
		return new xbean.__.FamilyGodAnimalAct.Data();
	}

	public static xdb.util.BeanPool<Family> poolFamily = new xdb.util.BeanPool<Family>() {
		@Override
		protected Family newBean() {
			return new xbean.__.Family();
		}
	};

	public static Family newFamily() {
		return poolFamily.get();
	}

	public static void _reset_unsafe_add_(Family bean) {
		poolFamily._reset_unsafe_add_(bean);
	}

	public static void padd(Family bean) {
		xdb.Procedure.padd(bean, poolFamily);
	}

	public static Family newFamilyData() {
		return new xbean.__.Family.Data();
	}

	public static xdb.util.BeanPool<IdList> poolIdList = new xdb.util.BeanPool<IdList>() {
		@Override
		protected IdList newBean() {
			return new xbean.__.IdList();
		}
	};

	public static IdList newIdList() {
		return poolIdList.get();
	}

	public static void _reset_unsafe_add_(IdList bean) {
		poolIdList._reset_unsafe_add_(bean);
	}

	public static void padd(IdList bean) {
		xdb.Procedure.padd(bean, poolIdList);
	}

	public static IdList newIdListData() {
		return new xbean.__.IdList.Data();
	}

	public static xdb.util.BeanPool<FNameToID> poolFNameToID = new xdb.util.BeanPool<FNameToID>() {
		@Override
		protected FNameToID newBean() {
			return new xbean.__.FNameToID();
		}
	};

	public static FNameToID newFNameToID() {
		return poolFNameToID.get();
	}

	public static void _reset_unsafe_add_(FNameToID bean) {
		poolFNameToID._reset_unsafe_add_(bean);
	}

	public static void padd(FNameToID bean) {
		xdb.Procedure.padd(bean, poolFNameToID);
	}

	public static FNameToID newFNameToIDData() {
		return new xbean.__.FNameToID.Data();
	}

	public static xdb.util.BeanPool<HuiWuChampion> poolHuiWuChampion = new xdb.util.BeanPool<HuiWuChampion>() {
		@Override
		protected HuiWuChampion newBean() {
			return new xbean.__.HuiWuChampion();
		}
	};

	public static HuiWuChampion newHuiWuChampion() {
		return poolHuiWuChampion.get();
	}

	public static void _reset_unsafe_add_(HuiWuChampion bean) {
		poolHuiWuChampion._reset_unsafe_add_(bean);
	}

	public static void padd(HuiWuChampion bean) {
		xdb.Procedure.padd(bean, poolHuiWuChampion);
	}

	public static HuiWuChampion newHuiWuChampionData() {
		return new xbean.__.HuiWuChampion.Data();
	}

	public static xdb.util.BeanPool<HuiWuPriviousTerm> poolHuiWuPriviousTerm = new xdb.util.BeanPool<HuiWuPriviousTerm>() {
		@Override
		protected HuiWuPriviousTerm newBean() {
			return new xbean.__.HuiWuPriviousTerm();
		}
	};

	public static HuiWuPriviousTerm newHuiWuPriviousTerm() {
		return poolHuiWuPriviousTerm.get();
	}

	public static void _reset_unsafe_add_(HuiWuPriviousTerm bean) {
		poolHuiWuPriviousTerm._reset_unsafe_add_(bean);
	}

	public static void padd(HuiWuPriviousTerm bean) {
		xdb.Procedure.padd(bean, poolHuiWuPriviousTerm);
	}

	public static HuiWuPriviousTerm newHuiWuPriviousTermData() {
		return new xbean.__.HuiWuPriviousTerm.Data();
	}

	public static xdb.util.BeanPool<HuiWuBattle> poolHuiWuBattle = new xdb.util.BeanPool<HuiWuBattle>() {
		@Override
		protected HuiWuBattle newBean() {
			return new xbean.__.HuiWuBattle();
		}
	};

	public static HuiWuBattle newHuiWuBattle() {
		return poolHuiWuBattle.get();
	}

	public static void _reset_unsafe_add_(HuiWuBattle bean) {
		poolHuiWuBattle._reset_unsafe_add_(bean);
	}

	public static void padd(HuiWuBattle bean) {
		xdb.Procedure.padd(bean, poolHuiWuBattle);
	}

	public static HuiWuBattle newHuiWuBattleData() {
		return new xbean.__.HuiWuBattle.Data();
	}

	public static xdb.util.BeanPool<HuiWuRound> poolHuiWuRound = new xdb.util.BeanPool<HuiWuRound>() {
		@Override
		protected HuiWuRound newBean() {
			return new xbean.__.HuiWuRound();
		}
	};

	public static HuiWuRound newHuiWuRound() {
		return poolHuiWuRound.get();
	}

	public static void _reset_unsafe_add_(HuiWuRound bean) {
		poolHuiWuRound._reset_unsafe_add_(bean);
	}

	public static void padd(HuiWuRound bean) {
		xdb.Procedure.padd(bean, poolHuiWuRound);
	}

	public static HuiWuRound newHuiWuRoundData() {
		return new xbean.__.HuiWuRound.Data();
	}

	public static xdb.util.BeanPool<HuiWuProfessionTerm> poolHuiWuProfessionTerm = new xdb.util.BeanPool<HuiWuProfessionTerm>() {
		@Override
		protected HuiWuProfessionTerm newBean() {
			return new xbean.__.HuiWuProfessionTerm();
		}
	};

	public static HuiWuProfessionTerm newHuiWuProfessionTerm() {
		return poolHuiWuProfessionTerm.get();
	}

	public static void _reset_unsafe_add_(HuiWuProfessionTerm bean) {
		poolHuiWuProfessionTerm._reset_unsafe_add_(bean);
	}

	public static void padd(HuiWuProfessionTerm bean) {
		xdb.Procedure.padd(bean, poolHuiWuProfessionTerm);
	}

	public static HuiWuProfessionTerm newHuiWuProfessionTermData() {
		return new xbean.__.HuiWuProfessionTerm.Data();
	}

	public static xdb.util.BeanPool<HuiWuCurTerm> poolHuiWuCurTerm = new xdb.util.BeanPool<HuiWuCurTerm>() {
		@Override
		protected HuiWuCurTerm newBean() {
			return new xbean.__.HuiWuCurTerm();
		}
	};

	public static HuiWuCurTerm newHuiWuCurTerm() {
		return poolHuiWuCurTerm.get();
	}

	public static void _reset_unsafe_add_(HuiWuCurTerm bean) {
		poolHuiWuCurTerm._reset_unsafe_add_(bean);
	}

	public static void padd(HuiWuCurTerm bean) {
		xdb.Procedure.padd(bean, poolHuiWuCurTerm);
	}

	public static HuiWuCurTerm newHuiWuCurTermData() {
		return new xbean.__.HuiWuCurTerm.Data();
	}

	public static xdb.util.BeanPool<StoryNoteChapter> poolStoryNoteChapter = new xdb.util.BeanPool<StoryNoteChapter>() {
		@Override
		protected StoryNoteChapter newBean() {
			return new xbean.__.StoryNoteChapter();
		}
	};

	public static StoryNoteChapter newStoryNoteChapter() {
		return poolStoryNoteChapter.get();
	}

	public static void _reset_unsafe_add_(StoryNoteChapter bean) {
		poolStoryNoteChapter._reset_unsafe_add_(bean);
	}

	public static void padd(StoryNoteChapter bean) {
		xdb.Procedure.padd(bean, poolStoryNoteChapter);
	}

	public static StoryNoteChapter newStoryNoteChapterData() {
		return new xbean.__.StoryNoteChapter.Data();
	}

	public static xdb.util.BeanPool<RoleStoryNote> poolRoleStoryNote = new xdb.util.BeanPool<RoleStoryNote>() {
		@Override
		protected RoleStoryNote newBean() {
			return new xbean.__.RoleStoryNote();
		}
	};

	public static RoleStoryNote newRoleStoryNote() {
		return poolRoleStoryNote.get();
	}

	public static void _reset_unsafe_add_(RoleStoryNote bean) {
		poolRoleStoryNote._reset_unsafe_add_(bean);
	}

	public static void padd(RoleStoryNote bean) {
		xdb.Procedure.padd(bean, poolRoleStoryNote);
	}

	public static RoleStoryNote newRoleStoryNoteData() {
		return new xbean.__.RoleStoryNote.Data();
	}

	public static xdb.util.BeanPool<System> poolSystem = new xdb.util.BeanPool<System>() {
		@Override
		protected System newBean() {
			return new xbean.__.System();
		}
	};

	public static System newSystem() {
		return poolSystem.get();
	}

	public static void _reset_unsafe_add_(System bean) {
		poolSystem._reset_unsafe_add_(bean);
	}

	public static void padd(System bean) {
		xdb.Procedure.padd(bean, poolSystem);
	}

	public static System newSystemData() {
		return new xbean.__.System.Data();
	}

	public static xdb.util.BeanPool<GMSenseword> poolGMSenseword = new xdb.util.BeanPool<GMSenseword>() {
		@Override
		protected GMSenseword newBean() {
			return new xbean.__.GMSenseword();
		}
	};

	public static GMSenseword newGMSenseword() {
		return poolGMSenseword.get();
	}

	public static void _reset_unsafe_add_(GMSenseword bean) {
		poolGMSenseword._reset_unsafe_add_(bean);
	}

	public static void padd(GMSenseword bean) {
		xdb.Procedure.padd(bean, poolGMSenseword);
	}

	public static GMSenseword newGMSensewordData() {
		return new xbean.__.GMSenseword.Data();
	}

	public static xdb.util.BeanPool<ActiveCode> poolActiveCode = new xdb.util.BeanPool<ActiveCode>() {
		@Override
		protected ActiveCode newBean() {
			return new xbean.__.ActiveCode();
		}
	};

	public static ActiveCode newActiveCode() {
		return poolActiveCode.get();
	}

	public static void _reset_unsafe_add_(ActiveCode bean) {
		poolActiveCode._reset_unsafe_add_(bean);
	}

	public static void padd(ActiveCode bean) {
		xdb.Procedure.padd(bean, poolActiveCode);
	}

	public static ActiveCode newActiveCodeData() {
		return new xbean.__.ActiveCode.Data();
	}

	public static xdb.util.BeanPool<RoleActiveCode> poolRoleActiveCode = new xdb.util.BeanPool<RoleActiveCode>() {
		@Override
		protected RoleActiveCode newBean() {
			return new xbean.__.RoleActiveCode();
		}
	};

	public static RoleActiveCode newRoleActiveCode() {
		return poolRoleActiveCode.get();
	}

	public static void _reset_unsafe_add_(RoleActiveCode bean) {
		poolRoleActiveCode._reset_unsafe_add_(bean);
	}

	public static void padd(RoleActiveCode bean) {
		xdb.Procedure.padd(bean, poolRoleActiveCode);
	}

	public static RoleActiveCode newRoleActiveCodeData() {
		return new xbean.__.RoleActiveCode.Data();
	}

}
