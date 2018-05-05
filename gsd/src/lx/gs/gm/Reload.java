package lx.gs.gm;

import gm.annotation.Cmd;
import gm.annotation.Module;
import gs.ClassReloader;

import java.io.*;
import java.lang.instrument.UnmodifiableClassException;
import java.util.HashMap;

/**
 * @author Jin Shuai
 */
@Module(comment="热加载")
public class Reload {

    @Cmd(comment = "加载reload文件夹中全部的class, class文件名改为全路径，例如：Test.class改为lx.gs.xx.xx.Test.class")
    public Object reloadDir() throws ClassNotFoundException, UnmodifiableClassException, IOException {
        HashMap<String, String> ret = new HashMap<>();
        for (File file : new File("reload").listFiles()) {
            try {
                String name = file.getName();
                if(!name.endsWith(".class")){
                    continue;
                }
                Class<?> clazz = Class.forName(name.replace(".class", ""));
                if(clazz != null){
                    ret.put(name, ClassReloader.reload(clazz, file));
                }
            } catch (Exception e) {

            }
        }
        return ret;
    }

    /**
     * 备用方法
     * 在文件中填写类的全路径
     * @return
     * @throws FileNotFoundException
     */
    private HashMap<String, String> getReloadClassMap() throws FileNotFoundException {
        HashMap<String, String> ret = new HashMap<>();
        LineNumberReader reader = null;
        try {
            File file = new File("reload/reloadlist");
            reader = new LineNumberReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null){
                if(!line.startsWith("#")){
                    try {
                        ret.put(line.substring(line.lastIndexOf(".") + 1) + ".class", line);
                    }catch (Exception e){}
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
