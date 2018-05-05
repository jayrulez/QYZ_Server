package lx.gs.gm;

import gm.GmLauncher;
import gm.server.GmHttpServer;
import gnet.link.Onlines;
import gs.Module;
import xio.Manager;
import xio.Protocol.Coder;
import xio.Protocol.Stub;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum GmModule implements Module {
	INSTANCE;
	
	@Override
	public void start() {
		
	}

	public static void startWithPort(int gmport, Integer csport, Integer httpport, String gmPasswd, boolean isTest) throws IOException{
		new GmLauncher()
			.test(isTest) //是否在测试模式下启动GM攻击，false表示正式环境，需要登陆GM账号才能执行GM命令，登录方式[admin login gmaccount, gmpassword]
			
			.gmport(gmport) // gm socket端口，telnet控制台，!=0 启动socket监听
			.csport(csport == null ? 0 : csport.intValue()) //客服服务端口，!=0 启动客服服务socket监听
			
			.optional(() -> httpport != null && httpport != 0, 
				self -> {
					self.httpport(httpport) // gm http端口，!=0 提供以http的方式执行GM命令
						.httpContext("gm")
						.httpExecutor(GmHttpServer.defaultExecutor());
			})
			
			.autoScanGmPackage(GmModule.class.getPackage().getName()) //扫描package下带有gm.annotation.Module注解的class并自动注册到GM命令中
			
			//batch 命令模块
			.onlineRolesSupplier(() -> { // batch onlinerolesexec 命令使用，查询在线角色列表
				gnet.link.Role[] roles = Onlines.getInstance().roles();
				List<Long> roleList = new ArrayList<>(roles.length);
				for (gnet.link.Role role : roles) {
					roleList.add(role.getRoleid());
				}
				return roleList;
			})
			
			//protocol 命令模块
			.protocolPackagePrefixName("lx.gs") // 协议package前缀
			.showProtocolPredicate(protcolFullName -> {// 协议过滤规则
				int index = protcolFullName.lastIndexOf('.');
				String cmdName = protcolFullName.substring(index + 1);
				return cmdName.startsWith("C"); // 协议名以C开始，表示该协议是客户端发送给服务器的，具体规则根据各自项的规则自行修改
			})
			.protocolStubSupplier(() -> {// 协议容器
				Manager.Coder coder = Onlines.getInstance().getCoder();
				if(coder instanceof Coder){
					Coder pCoder = (Coder)coder;
					try {
						Field f = Coder.class.getDeclaredField("stubs");
						f.setAccessible(true);
						
						@SuppressWarnings("unchecked")
						Map<Integer, Stub> stubs = (Map<Integer, Stub>)f.get(pCoder);
						return stubs;
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}
				return null;
			})
			
			//protocollog 命令模块
			.roleFinder(protocol -> { // 通过协议查询发送协议的角色
				gnet.link.Role role = Onlines.getInstance().find(protocol);
				return role == null ? 0L : role.getRoleid();
			})
			
			//admin 命令模块
			.withGmAccount("admin", gmPasswd)
			
			//启动GM
			.start();
	}
}
