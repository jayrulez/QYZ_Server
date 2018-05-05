package lx.gs.idgen;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.*;

/**
 * Created by HuangQiang on 2016/5/25.
 */
public class IdGen {
    public static void main(String[] argv) {
        if(argv.length < 4) {
            System.out.print("usage:   xx.jar randomSeed codeWidth codeStart codeNum1 codeNum2 ...");
            System.exit(1);
        }

        System.out.println("argv:" + Arrays.asList(argv));

        final long seed = Long.parseLong(argv[0]);
        final int codeWidth = Integer.parseInt(argv[1]);
        final int codeStart = Integer.parseInt(argv[2]);
        final List<Integer> codeNums = new ArrayList<>();
        for(int i = 3 ; i < argv.length ; i++) {
            codeNums.add(Integer.parseInt(argv[i]));
        }

        final HashSet<Long> existCodes = new HashSet<>();
        final Random random = new Random(seed);
        int codeEnd = codeStart;
        for(int i = 0 ; i < codeNums.size() ; i++) {
            final int curCodeStart = codeEnd;
            codeEnd += codeNums.get(i);
            List<String> lines = new ArrayList<>();
            String fileName = String.format("seed.%s.width.%s.range%s-%s.txt", seed, codeWidth, curCodeStart, codeEnd);
            for(; existCodes.size() < codeEnd ; ) {
                String codeStr = "";
                long code = 0;
                for(int j = 0 ; j < codeWidth ; j++) {
                    final int x = random.nextInt(36);
                    code = code * 36 + x;
                    codeStr += Character.forDigit(x, 36);
                }
                if(!existCodes.add(code))
                    continue;
                if(existCodes.size() >= codeStart)
                    lines.add(codeStr.toUpperCase());
            }
            try {
                Files.write(new File(fileName).toPath(), lines, StandardCharsets.UTF_8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
