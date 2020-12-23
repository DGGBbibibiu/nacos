package com.example.nacos.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符查找和替换
 */
public class PatternTest {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Karter");
        Matcher matcher = pattern.matcher("Karter yi Karter er and Karter san");

        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (matcher.find()){
            i++;
            matcher.appendReplacement(sb, "Kart");
            System.out.println("第" + i + "次匹配后sb的结果是:" + sb);
        }
        matcher.appendTail(sb);
        System.out.println("调用matcher.appendTail(sb)后最终的结果是:" + sb);
    }
}
