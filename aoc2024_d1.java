
// read
import java.nio.file.*;
import java.io.File;
import java.io.IOException;
// import java.io.File;

// regular expr
//import java.util.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// list
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class aoc2024_d1 {
    
    private static List<String> readFile (String i_path, List<String> o_StringList){
        Path filePath = Paths.get(i_path);
        try{
        o_StringList = Files.readAllLines(filePath);}
        catch (IOException e) {
        e.printStackTrace();
        }
        return o_StringList;
    }

    public static void main(String[] args) {
        String importPath = "C:\\sw\\zli\\javaTrains\\aoc_2024\\2024_d1.txt";
        List<String> exportStringList = new ArrayList();
        exportStringList = readFile(importPath, exportStringList);

        List<String> listLeft = new ArrayList();
        List<String> listRight = new ArrayList();
        for (String line : exportStringList) {
            String regex = "\\d+\\s";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(line);
            String testTest;
            listLeft.add(line.replaceAll("\\s\\s\\s\\d+", ""));
            listRight.add(line.replaceAll("\\d+\\s\\s\\s", ""));
        }

        // sort
        Collections.sort(listLeft, Comparator.reverseOrder());
        Collections.sort(listRight, Comparator.reverseOrder());

        // distance list
        List<Integer> distancelist = new ArrayList();
        for (int i = 0; i< listLeft.size();i++){
            int leftvalue = Integer.parseInt(listLeft.get(i));
            int rightvalue = Integer.parseInt(listRight.get(i));
            distancelist.add(Math.abs(leftvalue - rightvalue));
        }

        // sum
        int sum = 0;
        for (int num : distancelist) {
            sum += num; // Add each number to sum
        }

        System.out.println(sum);


    }
}
