package xtable;

import com.goldhuman.Common.Marshal.OctetsStream;
import com.goldhuman.Common.Marshal.MarshalException;

public class _Tables_ extends xdb.Tables {
	static volatile boolean isExplicitLockCheck = false;

	public static void startExplicitLockCheck() {
		isExplicitLockCheck = true;
	}

	public static _Tables_ getInstance() {
		return (_Tables_)xdb.Xdb.getInstance().getTables();
	}

	public _Tables_() {
		add(gsderrororderinfos);
		add(testusers);
		add(robotids);
		add(testordergs2plat);
		add(globalactivationcodes);
		add(users);
		add(uncompletedorderinfos);
		add(onesdkordergs2plat);
		add(onesdkorderinfos);
		add(useractivationcodes);
		add(testorderinfos);
		add(activationcodesets);
		add(apnsdevices);
		add(userpays);
		add(onesdkusers);
		add(activationcodes);
	}

	// visible in package
	xdb.TTable<String, xbean.GsdErrorOrderInfo> gsderrororderinfos = new xdb.TTable<String, xbean.GsdErrorOrderInfo>() {
		@Override
		public String getName() {
			return "gsderrororderinfos";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.GsdErrorOrderInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public xbean.GsdErrorOrderInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.GsdErrorOrderInfo value = xbean.Pod.newGsdErrorOrderInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.GsdErrorOrderInfo newValue() {
			xbean.GsdErrorOrderInfo value = xbean.Pod.newGsdErrorOrderInfo();
			return value;
		}

	};

	xdb.TTable<String, xbean.TestUserInfo> testusers = new xdb.TTable<String, xbean.TestUserInfo>() {
		@Override
		public String getName() {
			return "testusers";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.TestUserInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public xbean.TestUserInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.TestUserInfo value = xbean.Pod.newTestUserInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.TestUserInfo newValue() {
			xbean.TestUserInfo value = xbean.Pod.newTestUserInfo();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.RobotId> robotids = new xdb.TTable<Integer, xbean.RobotId>() {
		@Override
		public String getName() {
			return "robotids";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.RobotId value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.RobotId unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.RobotId value = xbean.Pod.newRobotId();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.RobotId newValue() {
			xbean.RobotId value = xbean.Pod.newRobotId();
			return value;
		}

	};

	xdb.TTable<String, String> testordergs2plat = new xdb.TTable<String, String>() {
		@Override
		public String getName() {
			return "testordergs2plat";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(String value) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(value, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public String unmarshalValue(OctetsStream _os_) throws MarshalException {
			String value = "";
			value = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return value;
		}

		@Override
		public String newValue() {
			String value = "";
			return value;
		}

	};

	xdb.TTable<Integer, xbean.GlobalActivationCode> globalactivationcodes = new xdb.TTable<Integer, xbean.GlobalActivationCode>() {
		@Override
		public String getName() {
			return "globalactivationcodes";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.GlobalActivationCode value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.GlobalActivationCode unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.GlobalActivationCode value = xbean.Pod.newGlobalActivationCode();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.GlobalActivationCode newValue() {
			xbean.GlobalActivationCode value = xbean.Pod.newGlobalActivationCode();
			return value;
		}

	};

	xdb.TTable<Long, xbean.UserInfo> users = new xdb.TTable<Long, xbean.UserInfo>() {
		@Override
		public String getName() {
			return "users";
		}

		@Override
		protected xdb.util.AutoKey<Long> bindAutoKey() {
			return getInstance().getTableSys().getAutoKeys().getAutoKeyLong(getName());
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.UserInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.UserInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.UserInfo value = xbean.Pod.newUserInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.UserInfo newValue() {
			xbean.UserInfo value = xbean.Pod.newUserInfo();
			return value;
		}

	};

	xdb.TTable<String, xbean.UncompletedOrderInfo> uncompletedorderinfos = new xdb.TTable<String, xbean.UncompletedOrderInfo>() {
		@Override
		public String getName() {
			return "uncompletedorderinfos";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.UncompletedOrderInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public xbean.UncompletedOrderInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.UncompletedOrderInfo value = xbean.Pod.newUncompletedOrderInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.UncompletedOrderInfo newValue() {
			xbean.UncompletedOrderInfo value = xbean.Pod.newUncompletedOrderInfo();
			return value;
		}

	};

	xdb.TTable<String, String> onesdkordergs2plat = new xdb.TTable<String, String>() {
		@Override
		public String getName() {
			return "onesdkordergs2plat";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(String value) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(value, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public String unmarshalValue(OctetsStream _os_) throws MarshalException {
			String value = "";
			value = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return value;
		}

		@Override
		public String newValue() {
			String value = "";
			return value;
		}

	};

	xdb.TTable<String, xbean.OnesdkOrderInfo> onesdkorderinfos = new xdb.TTable<String, xbean.OnesdkOrderInfo>() {
		@Override
		public String getName() {
			return "onesdkorderinfos";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.OnesdkOrderInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public xbean.OnesdkOrderInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.OnesdkOrderInfo value = xbean.Pod.newOnesdkOrderInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.OnesdkOrderInfo newValue() {
			xbean.OnesdkOrderInfo value = xbean.Pod.newOnesdkOrderInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.UserActivationCode> useractivationcodes = new xdb.TTable<Long, xbean.UserActivationCode>() {
		@Override
		public String getName() {
			return "useractivationcodes";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.UserActivationCode value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.UserActivationCode unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.UserActivationCode value = xbean.Pod.newUserActivationCode();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.UserActivationCode newValue() {
			xbean.UserActivationCode value = xbean.Pod.newUserActivationCode();
			return value;
		}

	};

	xdb.TTable<String, xbean.TestOrderInfo> testorderinfos = new xdb.TTable<String, xbean.TestOrderInfo>() {
		@Override
		public String getName() {
			return "testorderinfos";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.TestOrderInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public xbean.TestOrderInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.TestOrderInfo value = xbean.Pod.newTestOrderInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.TestOrderInfo newValue() {
			xbean.TestOrderInfo value = xbean.Pod.newTestOrderInfo();
			return value;
		}

	};

	xdb.TTable<Integer, xbean.ActivationCodeSet> activationcodesets = new xdb.TTable<Integer, xbean.ActivationCodeSet>() {
		@Override
		public String getName() {
			return "activationcodesets";
		}

		@Override
		public OctetsStream marshalKey(Integer key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.ActivationCodeSet value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Integer unmarshalKey(OctetsStream _os_) throws MarshalException {
			int key = 0;
			key = _os_.unmarshal_int();
			return key;
		}

		@Override
		public xbean.ActivationCodeSet unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.ActivationCodeSet value = xbean.Pod.newActivationCodeSet();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.ActivationCodeSet newValue() {
			xbean.ActivationCodeSet value = xbean.Pod.newActivationCodeSet();
			return value;
		}

	};

	xdb.TTable<Long, xbean.ApnsDevice> apnsdevices = new xdb.TTable<Long, xbean.ApnsDevice>() {
		@Override
		public String getName() {
			return "apnsdevices";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.ApnsDevice value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.ApnsDevice unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.ApnsDevice value = xbean.Pod.newApnsDevice();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.ApnsDevice newValue() {
			xbean.ApnsDevice value = xbean.Pod.newApnsDevice();
			return value;
		}

	};

	xdb.TTable<Long, xbean.UserPayInfo> userpays = new xdb.TTable<Long, xbean.UserPayInfo>() {
		@Override
		public String getName() {
			return "userpays";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.UserPayInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.UserPayInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.UserPayInfo value = xbean.Pod.newUserPayInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.UserPayInfo newValue() {
			xbean.UserPayInfo value = xbean.Pod.newUserPayInfo();
			return value;
		}

	};

	xdb.TTable<String, xbean.OnesdkUserInfo> onesdkusers = new xdb.TTable<String, xbean.OnesdkUserInfo>() {
		@Override
		public String getName() {
			return "onesdkusers";
		}

		@Override
		public OctetsStream marshalKey(String key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key, xdb.Const.IO_CHARSET);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.OnesdkUserInfo value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public String unmarshalKey(OctetsStream _os_) throws MarshalException {
			String key = "";
			key = _os_.unmarshal_String(xdb.Const.IO_CHARSET);
			return key;
		}

		@Override
		public xbean.OnesdkUserInfo unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.OnesdkUserInfo value = xbean.Pod.newOnesdkUserInfo();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.OnesdkUserInfo newValue() {
			xbean.OnesdkUserInfo value = xbean.Pod.newOnesdkUserInfo();
			return value;
		}

	};

	xdb.TTable<Long, xbean.ActivationCode> activationcodes = new xdb.TTable<Long, xbean.ActivationCode>() {
		@Override
		public String getName() {
			return "activationcodes";
		}

		@Override
		public OctetsStream marshalKey(Long key) {
			OctetsStream _os_ = new OctetsStream();
			_os_.marshal(key);
			return _os_;
		}

		@Override
		public OctetsStream marshalValue(xbean.ActivationCode value) {
			OctetsStream _os_ = new OctetsStream();
			value.marshal(_os_);
			return _os_;
		}

		@Override
		public Long unmarshalKey(OctetsStream _os_) throws MarshalException {
			long key = 0;
			key = _os_.unmarshal_long();
			return key;
		}

		@Override
		public xbean.ActivationCode unmarshalValue(OctetsStream _os_) throws MarshalException {
			xbean.ActivationCode value = xbean.Pod.newActivationCode();
			value.unmarshal(_os_);
			return value;
		}

		@Override
		public xbean.ActivationCode newValue() {
			xbean.ActivationCode value = xbean.Pod.newActivationCode();
			return value;
		}

	};


}
