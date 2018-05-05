package xauany.gm;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import org.omg.PortableInterceptor.InvalidSlot;
import xauany.code.CodeUtils;
import xauany.code.FCode;
import gm.GmCmdResult;
import gm.annotation.Cmd;
import gm.annotation.Module;
import gm.annotation.Param;
import xdb.Trace;

@Module (comment="批量执行GM命令")
public class Code {
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private String toDateString(long time){
		if(time == 0){
			return "";
		}
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(time);
		
		return df.format(c.getTime());
	}

    private long toDateMills(String datestring) throws ParseException{
        long timeMills = 0;
        if(!"0".equals(datestring)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(datestring);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 1);

            timeMills = c.getTimeInMillis();
        }

        return timeMills;
    }

    private Object toCodeSetString(int codetype) {
        Map<Object, Object> map = new LinkedHashMap<>();
        map.put("codetype", codetype);
        xbean.ActivationCodeSet codeset = xtable.Activationcodesets.get(codetype);
        if(codeset == null){
            map.put("errmsg", "该类型还没有生成激活码");
        }
        else{
            map.put("count", codeset.getValues().size());
            map.put("platform", codeset.getPlatformset());
            map.put("createtime", toDateString(codeset.getCreatetime()));
            map.put("opentime", toDateString(codeset.getOpentime()));
            map.put("expirate", toDateString(codeset.getExpiratetime()));
            map.put("islogin", codeset.getIslogin());
        }

        return map;
    }
	
	@Cmd(comment="显示当前已经有的激活码类型")
	public Object showall(){
		List<Object> lst = new ArrayList<>();
		xbean.GlobalActivationCode globalActivationCode = FCode.getGlobalActivationCode();
		globalActivationCode.getAlltypes().forEach(codetype -> {
		    lst.add(toCodeSetString(codetype));
		});
		
		return lst;
	}
	
	@Cmd(comment="显示激活码类型")
	public Object show(@Param(name="codetype", comment="激活码类型") int codetype){
		return toCodeSetString(codetype);
	}

	@Cmd(comment = "修改码参数")
    public Object modify(@Param(name="codetype", comment = "码类型")int codetype,
                        @Param(name="activatelogin",comment = "是否为登陆激活码 0|1") int activatelogin,
                         @Param(name="infinite", comment = "是否不限使用次数 0|1") int isShare,
                         @Param(name="opentime", comment = "开启时间,格式yyyy-MM-dd，凌晨过期, 填0表示立即") String opentime,
                         @Param(name="expiretime", comment = "过期时间，格式yyyy-MM-dd，凌晨过期, 填0表示永不过期") String expiretime,
                         @Param(name="platforms", comment = "限制平台,如果多个,以:划分,例如 342:123,0或-1表示无限制") String plats

    ) {
	    try {
            xbean.ActivationCodeSet newcodeset = xtable.Activationcodesets.get(codetype);
            if (newcodeset != null) {
                final boolean isLogin = activatelogin == 1;
                final long openTime = toDateMills(opentime);
                final long expireTime = toDateMills(expiretime);
                final List<Integer> platforms = Arrays.asList(plats.split(":")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
                if (platforms.size() == 1 && (platforms.contains(-1) || platforms.contains(0)))
                    platforms.clear();

                newcodeset.setCreatetime(System.currentTimeMillis());
                newcodeset.setOpentime(openTime);
                newcodeset.setExpiratetime(expireTime);
                newcodeset.setIsshared(isShare);
                newcodeset.setIslogin(isLogin);
                newcodeset.getPlatformset().addAll(platforms);
            } else {
                return GmCmdResult.error("该类型不存在");
            }
        } catch (Exception e) {
            return GmCmdResult.exception(e);
        }
	    return "succ";
    }


    @Cmd(comment="手动添加一条激活码")
    public Object addone(
            @Param(name="codetype", comment="激活码类型") int codetype,
            @Param(name="code", comment="激活码") String code
    ){
        xbean.ActivationCodeSet codeset = xtable.Activationcodesets.get(codetype);
        long codevalue = CodeUtils.decode(code);
        xbean.ActivationCode activationcode = xtable.Activationcodes.get(codevalue);
        if(activationcode != null){
            return GmCmdResult.error("激活码已被占用");
        }
        codeset.getValues().add(codevalue);
        activationcode = xbean.Pod.newActivationCode();
        activationcode.setType(codetype);
        xtable.Activationcodes.insert(codevalue, activationcode);

        return " 添加成功 ： " + code;
    }

    @Cmd(comment = "从文件导入一批激活码,可以是已存在的码类型,但其他参数必须一致,而且不能导入已存在的码,码文件格式:\n" +
        "codetype(激活码类型, >0)\n"+
        "islogin(是否为登陆激活码,0|1)\n"+
        "infinite(是否为通码,即不限使用次数,0|1)\n" +
        "opentime(开启时间，格式yyyy-MM-dd，凌晨过期, 填0表示立即)\n" +
        "expiretime(过期时间，格式yyyy-MM-dd，凌晨过期, 填0表示永不过期)\n" +
        "platforms(限制平台,如果多个,以:划分,例如 342:123,空表示无限制)\n" +
        "code1\n" + "...\n" + "codeN\n"
    )
    public GmCmdResult importFromFile(@Param(name="codefile", comment = "激活码文件")String codefile) throws Exception {
//        try {
            Trace.info("importFromFile:{} start", codefile);
            final List<String> lines = Files.readAllLines(new File(codefile).toPath(), StandardCharsets.UTF_8).stream()
                    .map(s -> s.trim()).filter(s -> !s.isEmpty() && !s.startsWith("#")).collect(Collectors.toList());
            if(lines.size() < 6)
                return GmCmdResult.error("激活码文件格式不合法!");
            int index = 0;
            final int codeType = Integer.parseInt(lines.get(index++));
            final boolean isLogin = lines.get(index++).equals("1");
            final int isShare = Integer.parseInt(lines.get(index++));
            final long openTime = toDateMills(lines.get(index++));
            final long expireTime = toDateMills(lines.get(index++));
            final List<Integer> platforms = Arrays.asList(lines.get(index++).split(":")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
            if(platforms.size() == 1 && (platforms.contains(-1) || platforms.contains(0)))
                platforms.clear();

            xbean.GlobalActivationCode globalActivationCode = FCode.getGlobalActivationCode();
            List<String> newCodeSet = lines.subList(index, lines.size());

            xbean.ActivationCodeSet newcodeset = xtable.Activationcodesets.get(codeType);
            if(newcodeset == null) {
                newcodeset = xbean.Pod.newActivationCodeSet();
                globalActivationCode.getAlltypes().add(codeType);
                xtable.Activationcodesets.insert(codeType, newcodeset);

                newcodeset.setType(codeType);
                newcodeset.setCreatetime(System.currentTimeMillis());
                newcodeset.setOpentime(openTime);
                newcodeset.setExpiratetime(expireTime);
                newcodeset.setIsshared(isShare);
                newcodeset.setIslogin(isLogin);
                newcodeset.getPlatformset().addAll(platforms);
            } else {
                if(newcodeset.getOpentime() != openTime)
                    return GmCmdResult.error("已存在此类型激活码.但开启时间 cur:" + newcodeset.getOpentime() + " != new:" + openTime);
                if(newcodeset.getIslogin() != isLogin) {
                    return GmCmdResult.error("已存在此类型激活码.但登陆类型 cur:" + newcodeset.getIslogin() + " != new:" + isLogin);
                }
                if(newcodeset.getExpiratetime() != expireTime)
                    return GmCmdResult.error("已存在此类型激活码.但过期时间 cur:" + newcodeset.getExpiratetime() + " != new:" + expireTime);
                if(newcodeset.getIsshared() != isShare)
                    return GmCmdResult.error("已存在此类型激活码.但通码类型 cur:" + newcodeset.getIsshared() + " != new:" + isShare);

                final Set<Integer> curPlatforms = newcodeset.getPlatformset();
                if(curPlatforms.size() != platforms.size() || !curPlatforms.containsAll(platforms))
                    return GmCmdResult.error("已存在此类型激活码.但平台类型 cur:" + curPlatforms + " != new:" + platforms);
            }

            final Set<Long> codes = newcodeset.getValues();
            for(String codeStr : newCodeSet) {
                final long code = CodeUtils.decode(codeStr);
                if(xtable.Activationcodes.get(code) != null)
                    return GmCmdResult.error("code:" + codeStr + " 已经存在!");
                codes.add(code);
                xbean.ActivationCode activationcode = xbean.Pod.newActivationCode();
                activationcode.setType(codeType);
                xtable.Activationcodes.insert(code, activationcode);
            }
            Trace.info("importFromFile:{} end", codefile);
            return GmCmdResult.success("import succ");
//        } catch (IOException e) {
//            Trace.error("open codefile:{} fail", codefile);
//            return GmCmdResult.exception(e);
//        } catch (Exception e) {
//            return GmCmdResult.exception(e);
//        }
    }

	
	@Cmd(comment="加载目录中所有码,文件名称必须是*.txt")
	public Object importFromDirectory(@Param(name="codeFolder", comment="激活码所在文件夹") String codeFolder) throws Exception {
		File folder = new File(codeFolder);
		Map<String, Object> result = new LinkedHashMap<>();
		for (File file : folder.listFiles()) {
			String filename = file.getPath();
            if(file.isDirectory()) {
                result.put(filename, importFromDirectory(filename));
            } else if (filename.endsWith(".txt")) {
                result.put(filename,  importFromFile(filename));
			}
		}
		
		return result;
	}

	@Cmd(comment="删除某一类型的所有激活码")
	public Object removeCodeSet(
			@Param(name="codetype", comment="激活码类型") int codetype) throws ParseException{
		xbean.GlobalActivationCode globalActivationCode = FCode.getGlobalActivationCode();
		if(globalActivationCode == null || !globalActivationCode.getAlltypes().contains(codetype)){
			return GmCmdResult.error("没有当前的激活码类型");
		}
		if(globalActivationCode != null){
			globalActivationCode.getAlltypes().remove(codetype);
		}
		xbean.ActivationCodeSet codeset = xtable.Activationcodesets.get(codetype);
		xtable.Activationcodesets.remove(codetype);
		codeset.getValues().forEach(code -> {
			xtable.Activationcodes.remove(code);
		});
		globalActivationCode.getAlltypes().remove(codetype);
		
		return codeset;
	}

	@Cmd(comment = "清空所有激活码")
    public Object removeAll() {
	    xtable.Globalactivationcodes.delete(0);
        final List<Integer> types = new ArrayList<>();
        xtable.Activationcodesets.getTable().walk((type, s) -> {
            types.add(type);
            return true;
        });
        for(int type : types) {
            xtable.Activationcodesets.remove(type);
        }
        final List<Long> codes = new ArrayList<>(5000000);
        xtable.Activationcodes.getTable().walk((c, d) -> {
            codes.add(c);
            return true;
        });
        for(long code : codes) {
            xtable.Activationcodes.remove(code);
        }
	    return String.format("types:%s codes:%s", types.size(), codes.size());
    }
}