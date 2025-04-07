// read
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
// collection
import java.util.Collections;

import aoc_2024_src.aocFunctions;

// regular expr
//import java.util.regex;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

public class aoc2024_d5 {

    static class PageComparator implements Comparator<Integer> {
        private final Map<Integer, Set<Integer>> map;

        public PageComparator(Map<Integer, Set<Integer>> map) {
            this.map = map;
        }

        // @Override
        public int compare(Integer i1, Integer i2) {
            if (map.get(i1).contains(i2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    private static final Set<Integer> emptySet = new LinkedHashSet<>();
    private static boolean valid(Map<Integer, Set<Integer>> map, List<Integer> pages) {
        for (int i = 0; i < pages.size() - 1; i++) {
            if (!map.getOrDefault(pages.get(i), emptySet).contains(pages.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static int partOneMiddle(Map<Integer, Set<Integer>> map, List<Integer> pages) {
        if (valid(map, pages)) {
            return pages.get(pages.size() / 2);
        } else {
            return 0;
        }
    }

    public static int partTwoMiddle(Map<Integer, Set<Integer>> map, List<Integer> pages) {
        if (valid(map, pages)) {
            return 0;
        } else {
            pages.sort(new PageComparator(map));
            return pages.get(pages.size() / 2);
        }
    }    
    public static void main(String[] args) {
        String importPath = System.getProperty("user.dir");
        File file = new File(importPath);
        String puzzelInput = "2024_d5.txt";
        // String puzzelInput = "2024_d5_tst.txt";
        String puzzelInputPath = "";
        puzzelInputPath = aocFunctions.FindFileByName(file, puzzelInput, puzzelInputPath);
        List<String> exportStringList = new ArrayList();
        exportStringList = aocFunctions.readFile(puzzelInputPath, exportStringList);

        String frontRules = "\\d*";
        List<String> ruleList = new ArrayList();
        ruleList = aocFunctions.regularExp(frontRules, exportStringList);
        System.out.println();

        String input = "";
        for (String line : exportStringList) {
            if (input == ""){
                input = line;
            }
            else{
                input = input + '\n' + line;                
            }
        }

        String[] section = input.split("\n\n");
        String[] ruleSection = section[0].split("\n");
        String[] pageSection = section[1].split("\n");

        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (String rule : ruleSection) {
            // split symbol seperated into 2 part
            String[] R = rule.split("\\|");
            // trans into integer
            int from = Integer.parseInt(R[0]);
            int to = Integer.parseInt(R[1]);
            // according to mapkey to search element and save them to set
            Set<Integer> set = map.getOrDefault(from, new LinkedHashSet<>());
            set.add(to);
            map.put(from, set);
        }
        System.out.println("Map value: " + map);

        int partOneSum = 0;
        int partTwoSum = 0;
        for (String pageString : pageSection) {
            List<Integer> pages = Arrays.stream(pageString.split(",")).mapToInt(Integer::parseInt).boxed()
                    .collect(Collectors.toList());
            partOneSum += partOneMiddle(map, pages);
            partTwoSum += partTwoMiddle(map, pages);
        }
        System.out.println("Day 5 part 1: " + partOneSum);
        System.out.println("Day 5 part 2: " + partTwoSum);
    }  
}
