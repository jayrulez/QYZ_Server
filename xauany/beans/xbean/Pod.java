
package xbean;

/**
 * bean factory
 */
public final class Pod {
	public static xdb.util.BeanPool<UserInfo> poolUserInfo = new xdb.util.BeanPool<UserInfo>() {
		@Override
		protected UserInfo newBean() {
			return new xbean.__.UserInfo();
		}
	};

	public static UserInfo newUserInfo() {
		return poolUserInfo.get();
	}

	public static void _reset_unsafe_add_(UserInfo bean) {
		poolUserInfo._reset_unsafe_add_(bean);
	}

	public static void padd(UserInfo bean) {
		xdb.Procedure.padd(bean, poolUserInfo);
	}

	public static UserInfo newUserInfoData() {
		return new xbean.__.UserInfo.Data();
	}

	public static xdb.util.BeanPool<UserPayInfo> poolUserPayInfo = new xdb.util.BeanPool<UserPayInfo>() {
		@Override
		protected UserPayInfo newBean() {
			return new xbean.__.UserPayInfo();
		}
	};

	public static UserPayInfo newUserPayInfo() {
		return poolUserPayInfo.get();
	}

	public static void _reset_unsafe_add_(UserPayInfo bean) {
		poolUserPayInfo._reset_unsafe_add_(bean);
	}

	public static void padd(UserPayInfo bean) {
		xdb.Procedure.padd(bean, poolUserPayInfo);
	}

	public static UserPayInfo newUserPayInfoData() {
		return new xbean.__.UserPayInfo.Data();
	}

	public static xdb.util.BeanPool<UncompletedOrderInfo> poolUncompletedOrderInfo = new xdb.util.BeanPool<UncompletedOrderInfo>() {
		@Override
		protected UncompletedOrderInfo newBean() {
			return new xbean.__.UncompletedOrderInfo();
		}
	};

	public static UncompletedOrderInfo newUncompletedOrderInfo() {
		return poolUncompletedOrderInfo.get();
	}

	public static void _reset_unsafe_add_(UncompletedOrderInfo bean) {
		poolUncompletedOrderInfo._reset_unsafe_add_(bean);
	}

	public static void padd(UncompletedOrderInfo bean) {
		xdb.Procedure.padd(bean, poolUncompletedOrderInfo);
	}

	public static UncompletedOrderInfo newUncompletedOrderInfoData() {
		return new xbean.__.UncompletedOrderInfo.Data();
	}

	public static xdb.util.BeanPool<GsdErrorOrderInfo> poolGsdErrorOrderInfo = new xdb.util.BeanPool<GsdErrorOrderInfo>() {
		@Override
		protected GsdErrorOrderInfo newBean() {
			return new xbean.__.GsdErrorOrderInfo();
		}
	};

	public static GsdErrorOrderInfo newGsdErrorOrderInfo() {
		return poolGsdErrorOrderInfo.get();
	}

	public static void _reset_unsafe_add_(GsdErrorOrderInfo bean) {
		poolGsdErrorOrderInfo._reset_unsafe_add_(bean);
	}

	public static void padd(GsdErrorOrderInfo bean) {
		xdb.Procedure.padd(bean, poolGsdErrorOrderInfo);
	}

	public static GsdErrorOrderInfo newGsdErrorOrderInfoData() {
		return new xbean.__.GsdErrorOrderInfo.Data();
	}

	public static xdb.util.BeanPool<TestUserInfo> poolTestUserInfo = new xdb.util.BeanPool<TestUserInfo>() {
		@Override
		protected TestUserInfo newBean() {
			return new xbean.__.TestUserInfo();
		}
	};

	public static TestUserInfo newTestUserInfo() {
		return poolTestUserInfo.get();
	}

	public static void _reset_unsafe_add_(TestUserInfo bean) {
		poolTestUserInfo._reset_unsafe_add_(bean);
	}

	public static void padd(TestUserInfo bean) {
		xdb.Procedure.padd(bean, poolTestUserInfo);
	}

	public static TestUserInfo newTestUserInfoData() {
		return new xbean.__.TestUserInfo.Data();
	}

	public static xdb.util.BeanPool<TestOrderInfo> poolTestOrderInfo = new xdb.util.BeanPool<TestOrderInfo>() {
		@Override
		protected TestOrderInfo newBean() {
			return new xbean.__.TestOrderInfo();
		}
	};

	public static TestOrderInfo newTestOrderInfo() {
		return poolTestOrderInfo.get();
	}

	public static void _reset_unsafe_add_(TestOrderInfo bean) {
		poolTestOrderInfo._reset_unsafe_add_(bean);
	}

	public static void padd(TestOrderInfo bean) {
		xdb.Procedure.padd(bean, poolTestOrderInfo);
	}

	public static TestOrderInfo newTestOrderInfoData() {
		return new xbean.__.TestOrderInfo.Data();
	}

	public static xdb.util.BeanPool<OnesdkUserInfo> poolOnesdkUserInfo = new xdb.util.BeanPool<OnesdkUserInfo>() {
		@Override
		protected OnesdkUserInfo newBean() {
			return new xbean.__.OnesdkUserInfo();
		}
	};

	public static OnesdkUserInfo newOnesdkUserInfo() {
		return poolOnesdkUserInfo.get();
	}

	public static void _reset_unsafe_add_(OnesdkUserInfo bean) {
		poolOnesdkUserInfo._reset_unsafe_add_(bean);
	}

	public static void padd(OnesdkUserInfo bean) {
		xdb.Procedure.padd(bean, poolOnesdkUserInfo);
	}

	public static OnesdkUserInfo newOnesdkUserInfoData() {
		return new xbean.__.OnesdkUserInfo.Data();
	}

	public static xdb.util.BeanPool<OnesdkOrderInfo> poolOnesdkOrderInfo = new xdb.util.BeanPool<OnesdkOrderInfo>() {
		@Override
		protected OnesdkOrderInfo newBean() {
			return new xbean.__.OnesdkOrderInfo();
		}
	};

	public static OnesdkOrderInfo newOnesdkOrderInfo() {
		return poolOnesdkOrderInfo.get();
	}

	public static void _reset_unsafe_add_(OnesdkOrderInfo bean) {
		poolOnesdkOrderInfo._reset_unsafe_add_(bean);
	}

	public static void padd(OnesdkOrderInfo bean) {
		xdb.Procedure.padd(bean, poolOnesdkOrderInfo);
	}

	public static OnesdkOrderInfo newOnesdkOrderInfoData() {
		return new xbean.__.OnesdkOrderInfo.Data();
	}

	public static xdb.util.BeanPool<ApnsDevice> poolApnsDevice = new xdb.util.BeanPool<ApnsDevice>() {
		@Override
		protected ApnsDevice newBean() {
			return new xbean.__.ApnsDevice();
		}
	};

	public static ApnsDevice newApnsDevice() {
		return poolApnsDevice.get();
	}

	public static void _reset_unsafe_add_(ApnsDevice bean) {
		poolApnsDevice._reset_unsafe_add_(bean);
	}

	public static void padd(ApnsDevice bean) {
		xdb.Procedure.padd(bean, poolApnsDevice);
	}

	public static ApnsDevice newApnsDeviceData() {
		return new xbean.__.ApnsDevice.Data();
	}

	public static xdb.util.BeanPool<RobotId> poolRobotId = new xdb.util.BeanPool<RobotId>() {
		@Override
		protected RobotId newBean() {
			return new xbean.__.RobotId();
		}
	};

	public static RobotId newRobotId() {
		return poolRobotId.get();
	}

	public static void _reset_unsafe_add_(RobotId bean) {
		poolRobotId._reset_unsafe_add_(bean);
	}

	public static void padd(RobotId bean) {
		xdb.Procedure.padd(bean, poolRobotId);
	}

	public static RobotId newRobotIdData() {
		return new xbean.__.RobotId.Data();
	}

	public static xdb.util.BeanPool<GlobalActivationCode> poolGlobalActivationCode = new xdb.util.BeanPool<GlobalActivationCode>() {
		@Override
		protected GlobalActivationCode newBean() {
			return new xbean.__.GlobalActivationCode();
		}
	};

	public static GlobalActivationCode newGlobalActivationCode() {
		return poolGlobalActivationCode.get();
	}

	public static void _reset_unsafe_add_(GlobalActivationCode bean) {
		poolGlobalActivationCode._reset_unsafe_add_(bean);
	}

	public static void padd(GlobalActivationCode bean) {
		xdb.Procedure.padd(bean, poolGlobalActivationCode);
	}

	public static GlobalActivationCode newGlobalActivationCodeData() {
		return new xbean.__.GlobalActivationCode.Data();
	}

	public static xdb.util.BeanPool<ActivationCodeSet> poolActivationCodeSet = new xdb.util.BeanPool<ActivationCodeSet>() {
		@Override
		protected ActivationCodeSet newBean() {
			return new xbean.__.ActivationCodeSet();
		}
	};

	public static ActivationCodeSet newActivationCodeSet() {
		return poolActivationCodeSet.get();
	}

	public static void _reset_unsafe_add_(ActivationCodeSet bean) {
		poolActivationCodeSet._reset_unsafe_add_(bean);
	}

	public static void padd(ActivationCodeSet bean) {
		xdb.Procedure.padd(bean, poolActivationCodeSet);
	}

	public static ActivationCodeSet newActivationCodeSetData() {
		return new xbean.__.ActivationCodeSet.Data();
	}

	public static xdb.util.BeanPool<ActivationCode> poolActivationCode = new xdb.util.BeanPool<ActivationCode>() {
		@Override
		protected ActivationCode newBean() {
			return new xbean.__.ActivationCode();
		}
	};

	public static ActivationCode newActivationCode() {
		return poolActivationCode.get();
	}

	public static void _reset_unsafe_add_(ActivationCode bean) {
		poolActivationCode._reset_unsafe_add_(bean);
	}

	public static void padd(ActivationCode bean) {
		xdb.Procedure.padd(bean, poolActivationCode);
	}

	public static ActivationCode newActivationCodeData() {
		return new xbean.__.ActivationCode.Data();
	}

	public static xdb.util.BeanPool<UserActivationCode> poolUserActivationCode = new xdb.util.BeanPool<UserActivationCode>() {
		@Override
		protected UserActivationCode newBean() {
			return new xbean.__.UserActivationCode();
		}
	};

	public static UserActivationCode newUserActivationCode() {
		return poolUserActivationCode.get();
	}

	public static void _reset_unsafe_add_(UserActivationCode bean) {
		poolUserActivationCode._reset_unsafe_add_(bean);
	}

	public static void padd(UserActivationCode bean) {
		xdb.Procedure.padd(bean, poolUserActivationCode);
	}

	public static UserActivationCode newUserActivationCodeData() {
		return new xbean.__.UserActivationCode.Data();
	}

}
