
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

    private static int horizonalXmas(List<String> i_List, String i_xmas){
        int horizonalXmas = 0;
        for (String Line: i_List){
            Pattern pattern = Pattern.compile(i_xmas);
            Matcher matcher = pattern.matcher(Line);
            while (matcher.find()) {
                horizonalXmas++;
            }
        }  
        return horizonalXmas;
    }


    public static void main(String[] args) {
        String importPath = System.getProperty("user.dir");
        File file = new File(importPath);
        // String puzzelInput = "2024_d4.txt";
        String puzzelInput = "2024_d4.txt";
        String puzzelInputPath = "";
        puzzelInputPath = aocFunctions.FindFileByName(file, puzzelInput, puzzelInputPath);
        List<String> exportStringList = new ArrayList();
        exportStringList = aocFunctions.readFile(puzzelInputPath, exportStringList);

        //// 2024_d4_step_1
        for (int i = 0; i < exportStringList.size(); i++){
            for (int j = 0; j < exportStringList.get(i).length(); j++){
                // System.out.println("matrix: " + exportStringList.get(i).charAt(j));
            }
        }

        String i_xmas = "XMAS";
        String i_samx = "SAMX";

        int horizonXmas = horizonalXmas(exportStringList, i_xmas);
        int horizonSamx = horizonalXmas(exportStringList, i_samx);

        System.out.println("\n"+"horizonXmas: " + (horizonXmas+horizonSamx) );
        
        // Find the maximum length of the strings to know how many columns we need
        int maxLength = exportStringList.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);

        // Create a new list to hold the transposed result
        List<String> transposedList = new ArrayList<>();

        // Loop through each column index
        for (int col = 0; col < maxLength; col++) {
            StringBuilder sb = new StringBuilder();

            // Loop through each row (string) and get the character at the current column
            // index
            for (String str : exportStringList) {
                if (col < str.length()) {
                    sb.append(str.charAt(col)); // Append the character at the column index
                }
            }

            // Add the result to the transposed list
            transposedList.add(sb.toString());
        }

        // Output the transposed list
        System.out.println(transposedList);

        int vertXmas = horizonalXmas(transposedList, i_xmas);
        int vertSamx = horizonalXmas(transposedList, i_samx);

        System.out.println("\n" + "vertXmas: " + (vertXmas + vertSamx));

        // diagonal list
        int k  = 0;
        List<String> diagonalList = new ArrayList<>();
        for (int i = 0; i < exportStringList.size(); i++){
            StringBuilder diagonEle = new StringBuilder();
            int diagonalInt = i;
            for (String str : exportStringList){
                if (diagonalInt >= str.length()) break;
                diagonEle.append(str.charAt(diagonalInt));
                diagonalInt++;    
            }
            diagonalList.add(diagonEle.toString());
            diagonEle = new StringBuilder();
            if (i-1<0) continue;
            for ( int j = i; j < exportStringList.get(i).length(); j++){
                diagonEle.append(exportStringList.get(j).charAt(j-k-1));
            }
            diagonalList.add(diagonEle.toString());
            k++;
        }

        int j = 0;
        for (int i = exportStringList.size()-1; i > -1; i--) {
            StringBuilder diagonEle = new StringBuilder();
            int diagonalInt = i;
            for (String str : exportStringList) {
                if (diagonalInt < 0)
                    break;
                diagonEle.append(str.charAt(diagonalInt));
                diagonalInt--;
            }
            diagonalList.add(diagonEle.toString());
            diagonEle = new StringBuilder();
            diagonalInt = exportStringList.get(i).length();
            if (diagonalInt  == exportStringList.get(i).length()-1)
                continue;
            for (String str : exportStringList) {
                if (diagonalInt-1 >= i ){
                    diagonalInt--;}
                else{
                diagonEle.append(str.charAt(diagonalInt+j));
                diagonalInt--;}
            }
            diagonalList.add(diagonEle.toString());
            j++;
        }



        System.out.println(diagonalList);
        int diagonXmas = horizonalXmas(diagonalList, i_xmas);
        int diagonSamx = horizonalXmas(diagonalList, i_samx);

        System.out.println("\n" + "diagonXmas: " + (diagonXmas + diagonSamx));
        System.out.println("\n" + "Samt: " + (horizonXmas + horizonSamx + vertXmas + vertSamx + diagonXmas + diagonSamx));
    }
}
