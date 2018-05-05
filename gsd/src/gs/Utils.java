package gs;

import lx.gs.SError;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

public class Utils {

	public static int getServerId() {
		return Config.getInstance().getServerid();
	}

	public static String makeFullName(String name, int serverid) {
		return name + "@" + serverid;
	}

	public static boolean isNull(Collection collection){
		return collection == null || collection.isEmpty();
	}

	public static boolean isNull(Map map){
		return map == null || map.isEmpty();
	}

	public static boolean isNotNull(Collection collection){
		return !isNull(collection);
	}

	public static boolean isNotNull(Map map){
		return !isNull(map);
	}

	public static void call(xio.Protocol msg) {
		final Class<?> cls = msg.getClass();
		try {
			Method method = cls.getDeclaredMethod("process");
			method.setAccessible(true);
			method.invoke(msg);
		} catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] argv) {
		call(new SError());
	}
}
