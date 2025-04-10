
// read
import aoc_2024_src.aocFunctions;
import java.io.File;

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

    public static void main(String[] args) {

        String importPath = System.getProperty("user.dir");
        File file = new File(importPath);
        String puzzelInput = "2024_d1.txt";
        String puzzelInputPath = "";
        puzzelInputPath = aocFunctions.FindFileByName(file, puzzelInput, puzzelInputPath);
        List<String> exportStringList = new ArrayList();
        exportStringList = aocFunctions.readFile(puzzelInputPath, exportStringList);

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

        //// d1 step_1
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

        ////d1 step_2
        //cout appearence times
        // Loop through each string in list2 and count its occurrences in list1
        List <Integer> appearanceList = new ArrayList<>();
        for (String str : listLeft) {
            int count = 0;
            for (String s : listRight) {
                if (s.equals(str)) {
                    count++;
                }
            }
            appearanceList.add(count);
        }

        // muliti
        List<Integer> appearMutilist = new ArrayList();
        for (int i = 0; i < listLeft.size(); i++) {
            int leftvalue = Integer.parseInt(listLeft.get(i));
            int appearMutilistvalue = leftvalue*(appearanceList.get(i));
            appearMutilist.add(appearMutilistvalue);
        }


        // sum
        sum = 0;
        for (int num : appearMutilist) {
            sum += num; // Add each number to sum
        }

        System.out.println(sum);

    }
}
