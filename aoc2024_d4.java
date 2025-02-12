
// read
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// collection
import java.util.Collections;

import aoc_2024_src.aocFunctions;

// regular expr
//import java.util.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aoc2024_d4 {

    // regular expr
    private static List<String> regularExp(String i_regex, List<String> i_StringList) {
        Pattern pattern = Pattern.compile(i_regex);
        List<String> o_mulList = new ArrayList<String>();
        for (String line : i_StringList) {
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                o_mulList.add(matcher.group());
                // System.out.println(matcher.group());
            }
        }
        return o_mulList;
    }

    private static List<Integer> transToInt(String i_regex, List<String> i_StringList) {
        List<Integer> resultLftList = new ArrayList<Integer>();
        for (String eleStr : i_StringList) {
            Pattern pattern = Pattern.compile(i_regex);
            Matcher matcher = pattern.matcher(eleStr);
            while (matcher.find()) {
                String line = matcher.group();
                line = line.replaceAll(",", "");
                resultLftList.add(Integer.parseInt(line));
            }
        }
        return resultLftList;
    }

    private static List<Integer> regularExpPos (String i_regex, String i_String) {
        Pattern pattern = Pattern.compile(i_regex);
        List<Integer> o_mulList = new ArrayList<Integer>();
        Matcher matcher = pattern.matcher(i_String);
        while (matcher.find()) {
            o_mulList.add(matcher.end());
            // System.out.println(matcher.group());
        }
        return o_mulList;
    }

    public static void main(String[] args) {
        String importPath = System.getProperty("user.dir");
        File file = new File(importPath);
        // String puzzelInput = "2024_d4.txt";
        String puzzelInput = "2024_d4_tst.txt";
        String puzzelInputPath = "";
        puzzelInputPath = aocFunctions.FindFileByName(file, puzzelInput, puzzelInputPath);
        List<String> exportStringList = new ArrayList();
        exportStringList = aocFunctions.readFile(puzzelInputPath, exportStringList);

        //// 2024_d4_step_1
        //

    }
}
