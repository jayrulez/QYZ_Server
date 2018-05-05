package xauany.code;

import gm.GmCmdResult;

import java.io.File;
import java.util.*;

public class CodeUtils {
	
	public static long decode(String code) {
		if(code.length() > 12)
		    code = code.substring(0, 12);
        return Long.parseLong(code, 36);
	}
	
	public static boolean isValid(String code){
		return !code.isEmpty() && code.length() < 20;
	}

	public static void walk(String dir) {
        File folder = new File(dir);
        Map<String, GmCmdResult> result = new LinkedHashMap<>();
        for (File file : folder.listFiles()) {
            String filename = file.getPath();
//            if(filename.startsWith("."))
 //               continue;
            if(file.isDirectory()) {
                System.out.println(filename);
                walk(filename);
            } else  {
                System.out.println(filename);
            }
        }
    }

	public static void main(String[] argv) {
	    walk("src/xauany");
    }
}
