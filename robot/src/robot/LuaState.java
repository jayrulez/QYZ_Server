package robot;

import com.goldhuman.Common.Octets;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.DebugLib;
import org.luaj.vm2.lib.jse.JsePlatform;

public class LuaState {
    public final static LuaState Ins = new LuaState();

    private final Globals g;
    private LuaValue main;
    private LuaValue secondUpdate;
    private LuaValue update;
    private LuaValue onRecv;

    private LuaState() {
        this.g = JsePlatform.standardGlobals();
        this.g.load(new DebugLib());
        this.g.load(new MarshalLib());
        //LuaJC.install(g);
    }

    public void init(String scriptsRoot, String configDataRoot) {
        addSearchPath(scriptsRoot);

        main = this.g.load("return require 'main'").call();//  g.loadfile(argv[1] + "/main.lua").call();
        main.get("init").invoke(LuaValue.valueOf(configDataRoot));

        secondUpdate = main.get("second_update");
        update = main.get("update");
        onRecv = main.get("onrecv");
    }

    public void start() {
        LinkClient.taskQueue.scheduleAtFixedRate(() -> {
            try {
                secondUpdate.invoke(LuaValue.valueOf(System.currentTimeMillis()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 1000, 1000);

        LinkClient.taskQueue.scheduleAtFixedRate(() -> {
            try {
                update.invoke(LuaValue.valueOf(System.currentTimeMillis()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 100, 100);
    }

    public void onUserProtocol(String username, int type, Octets data) {
        LinkClient.taskQueue.add(() -> {
            try {
                onRecv.invoke(LuaValue.valueOf(username), LuaValue.valueOf(type), LuaValue.valueOf(data.array()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void addSearchPath(String searchPath) {
        LuaValue pkg = g.get("package");
        pkg.set("path", String.format("%s;%s/?.lua", pkg.get("path").tostring(), searchPath));
    }

}
