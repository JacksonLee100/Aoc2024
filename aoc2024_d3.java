
// read
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import aoc_2024_src.aocFunctions;

// regular expr
//import java.util.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aoc2024_d3 {

    // regular expr
    private static List<String> regularExp (String i_regex, List<String> i_StringList){
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

    private static List<Integer> transToInt (String i_regex, List<String> i_StringList) {
        List<Integer> resultLftList = new ArrayList<Integer>();
        for(String eleStr: i_StringList){
           Pattern pattern = Pattern.compile(i_regex);
           Matcher matcher = pattern.matcher(eleStr);
           while(matcher.find()){
                String line = matcher.group();
                line = line.replaceAll(",","");
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
        // String puzzelInput = "2024_d3.txt";
        String puzzelInput = "2024_d3_tst.txt";
        String puzzelInputPath = "";
        puzzelInputPath = aocFunctions.FindFileByName(file, puzzelInput, puzzelInputPath);
        List<String> exportStringList = new ArrayList();
        exportStringList = aocFunctions.readFile(puzzelInputPath, exportStringList);

        //// 2024_d3_step_1
        /// filter by regx
        String regex = "mul\\([0-9]+\\,[0-9]+\\)";
        List<String> mulList = new ArrayList<String>();
        mulList = regularExp (regex, exportStringList);
        
        System.out.println(mulList);
        List<Integer> resultLftList = new ArrayList<Integer>();
        List<Integer> resultRhtList = new ArrayList<Integer>();
        List<Integer> resultList = new ArrayList<Integer>();

        regex = "[0-9]+\\,";
        resultLftList = transToInt(regex, mulList);

        regex = "\\,[0-9]+";
        resultRhtList = transToInt(regex, mulList);
        
        // multipy
        for(int i = 0; i < resultLftList.size(); i++){
            resultList.add(resultLftList.get(i) * resultRhtList.get(i));
        }

        int sum = 0;
        for (int num : resultList) {
            sum += num; // Add each number to sum
        }
        System.out.println(sum);

        //// 2024_d3_step_2
        String regexDo = "do\\(\\)";
        String regexDont = "don't\\(\\)";
        List<Integer> doList = new ArrayList<Integer>();
        List<Integer> dontList = new ArrayList<Integer>();
        String result = String.join("", exportStringList);

        doList = regularExpPos(regexDo, result);
        dontList = regularExpPos(regexDont, result);
        System.out.println(doList);
        System.out.println(dontList);

        regex = "mul\\([0-9]+\\,[0-9]+\\)";
        List<Integer> muListPos = new ArrayList<Integer>();
        muListPos = regularExpPos(regex, result);

        System.out.println(muListPos);
        int validPos = 1;
        List<Integer> validList  = new ArrayList<Integer>();        
        for(int j = 0; j < muListPos.size(); j++){
            if (j == 0) {
                validList.add(validPos);
                continue;
            }
            else{
                for (int i = 0; i < dontList.size(); i++){
                    if (muListPos.get(j)>dontList.size() ){

                    }
                }
            }
        }


        
    }
}
