package xauany.gm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import gm.GmLauncher;

public class GmModule {

	public static void startWithPort(int gmport, Integer csport, boolean isTest) throws IOException{
		new GmLauncher()
			.gmport(gmport) // gm socket端口，telnet控制台，!=0 启动socket监听
			.csport(csport == null ? 0 : csport.intValue()) //客服服务端口，!=0 启动客服服务socket监听
			.test(isTest) //是否在测试模式下启动GM攻击，false表示正式环境，需要登陆GM账号才能执行GM命令，登录方式[admin login gmaccount, gmpassword]
			.httpport(0) // gm http端口，!=0 提供以http的方式执行GM命令
			
			
			.autoScanGmPackage(GmModule.class.getPackage().getName()) //扫描package下带有gm.annotation.Module注解的class并自动注册到GM命令中
			
			//batch 命令模块
			.onlineRolesSupplier(() -> { // batch onlinerolesexec 命令使用，查询在线角色列表
				return new ArrayList<>();
			})
			.allRolesSupplier(() -> { // batch allrolesexec 命令使用，查询所有角色列表
				return new LinkedList<>();
			})
			
			//protocol 命令模块
			.protocolPackagePrefixName("xauany") // 协议package前缀
			.showProtocolPredicate((protcolFullName) -> {// 协议过滤规则
				int index = protcolFullName.lastIndexOf('.');
				String cmdName = protcolFullName.substring(index + 1);
				return cmdName.startsWith("C"); // 协议名以C开始，表示该协议是客户端发送给服务器的，具体规则根据各自项的规则自行修改
			})
			.protocolStubSupplier(() -> {// 协议容器
				return null;
			})
			
			//protocollog 命令模块
			.roleFinder((protocol) -> { // 通过协议查询发送协议的角色
				return 0L;
			})
			.protocolLogPredicate((protocol) -> { // 协议记录过滤规则
				return true;
			})
			
			//admin 命令模块
			.withGmAccount("admin", xauany.Config.getInstance().getGmPassword())
			
			//启动GM
			.start();
	}
}
