package robot;

import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.VarArgFunction;

import java.nio.charset.StandardCharsets;

/**
 * Created by HuangQiang on 2016/6/25.
 */
public class MarshalLib extends TwoArgFunction {
    public static MarshalLib INST = null;
    public MarshalLib() {
        INST = this;
    }

    @Override
    public LuaValue call(LuaValue luaValue, LuaValue env) {
        LuaTable lib = new LuaTable(0, 4);
        lib.set("float2int", new Float2Int());
        lib.set("int2float", new Int2Float());
        lib.set("double2intint", new DoubleToIntInt());
        lib.set("intint2double", new IntInt2Double());
        lib.set("utf8toutf16", new Utf8ToUtf16());
        lib.set("utf16toutf8", new Utf16ToUtf8());

        env.set("marshal", lib);
        env.get("package").get("loaded").set("math", lib);
        return lib;
    }

    public static class Float2Int extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            return LuaValue.valueOf(Float.floatToIntBits(luaValue.tofloat()));
        }
    }

    public static class Int2Float extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            return LuaValue.valueOf(Float.intBitsToFloat(luaValue.toint()));
        }
    }

    public static class DoubleToIntInt extends VarArgFunction {

        @Override
        public Varargs invoke(Varargs luaValue) {
            long x = Double.doubleToRawLongBits(luaValue.arg1().todouble());
            return LuaValue.varargsOf(new LuaValue[] {LuaValue.valueOf((int)x), LuaValue.valueOf((int)(x >> 32))});
        }
    }

    public static class IntInt2Double extends VarArgFunction {
        @Override
        public Varargs invoke(Varargs luaValue) {
            int lowx = luaValue.checkint(1);
            int highx = luaValue.checkint(2);
            double x = Double.longBitsToDouble(((long)(highx) << 32) |lowx);
            return LuaValue.valueOf(x);
        }
    }

    public static class Utf16ToUtf8 extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            LuaString x = luaValue.checkstring();
            final String s = new String(x.m_bytes, x.m_offset, x.m_length, StandardCharsets.UTF_16LE);
            return LuaString.valueOf(s.getBytes(StandardCharsets.UTF_8));
        }
    }

    public static class Utf8ToUtf16 extends OneArgFunction {
        @Override
        public LuaValue call(LuaValue luaValue) {
            LuaString x = luaValue.checkstring();
            final String s = new String(x.m_bytes, x.m_offset, x.m_length, StandardCharsets.UTF_8);
            return LuaString.valueOf(s.getBytes(StandardCharsets.UTF_16LE));
        }
    }
}
