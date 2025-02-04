
// read
import aoc_2024_src.aocFunctions;

import java.io.File;
import java.nio.file.*;

// regular expr
//import java.util.regex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// list
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class aoc2024_d2 {

    // safe check
    private static int SafeCheck (List<Integer> i_incdiscList){
        int safe = 0;
        for (int i = 0; i < i_incdiscList.size(); i++){
            if(Math.abs(i_incdiscList.get(i))>3||Math.abs(i_incdiscList.get(i))<1){
                safe = 0;
                break;
            }
            else{
                if (i == 0) 
                    continue;
                else{
                    if((i_incdiscList.get(i)>0 && (i_incdiscList.get(i-1)>0))||(i_incdiscList.get(i)<0 && (i_incdiscList.get(i-1)<0))){
                        safe = 1;
                    }
                    else {
                        safe = 0;
                        break;
                    }
                }
            }
        }
        return safe;
    }


    public static void main(String[] args) {

        String importPath = System.getProperty("user.dir");
        File file = new File(importPath);
        String puzzelInput = "2024_d2.txt";
        String puzzelInputPath = "";
        puzzelInputPath = aocFunctions.FindFileByName(file, puzzelInput, puzzelInputPath);
        List<String> exportStringList = new ArrayList();
        exportStringList = aocFunctions.readFile(puzzelInputPath, exportStringList); 
        for (String line: exportStringList){
            System.out.println(line);
        }

        ////2024_d1_step_1
        // lineEleList
        List<List<Integer>> lineEleList = new ArrayList<>();
        for (String line: exportStringList){
            List<Integer> IntList = new ArrayList();
            IntList.clear();
            String[] stringNumbers = line.split(" ");
            for (String intValue: stringNumbers){
                IntList.add(Integer.parseInt(intValue));
            }
            lineEleList.add(IntList);
        }

        // range of increase/discrease
        List<Integer> safeList = new ArrayList();
        List<List<Integer>> incdiscList  = new ArrayList();
        for (List<Integer> IntList: lineEleList){
            List<Integer> incdiscListEle = new ArrayList<>();
            incdiscListEle.clear();
            for (int i = 0; i<IntList.size();i++){
                if(i<=0)continue;
                int incdisc = IntList.get(i-1)-IntList.get(i);
                incdiscListEle.add(incdisc);
            }
            incdiscList.add(incdiscListEle);
        }

        int j = 0;
        for (List<Integer> elem: incdiscList){
            int safe = 0;
            //for (int i = 0; i < elem.size(); i++){
            //    if(Math.abs(elem.get(i))>3||Math.abs(elem.get(i))<1){
            //        safe = 0;
            //        System.out.println("incdiscList unsafe: " + (j+1));
            //        break;
            //    }
            //    else{
            //        if (i == 0) 
            //            continue;
            //        else{
            //            if((elem.get(i)>0 && (elem.get(i-1)>0))||(elem.get(i)<0 && (elem.get(i-1)<0))){
            //                safe = 1;
            //            }
            //            else {
            //                safe = 0;
            //                System.out.println("incdiscList unsafe: " + (j + 1));
            //                break;
            //            }
            //        }
            //    }
            //}
            safe = SafeCheck(elem);
            safeList.add(safe);
        }

        int sum = 0;
        j = 0;
        for (int num : safeList) {
            if (num == 0){
                System.out.println("safeList unsafe: "+ (j + 1)+ " " + incdiscList.get(j)+ " " + lineEleList.get(j));
            }
            sum += num; // Add each number to sum
            j++;
        }

        System.out.println(sum);

        //// 2024_d1_step_2
        j = 0;
        safeList.clear();
        for (int k = 0; k < incdiscList.size(); k++) {
            int safe = 0;
            int safeDam = 0;
            for (int i = 0; i < incdiscList.get(k).size(); i++) {
                if (Math.abs(incdiscList.get(k).get(i)) > 3 || Math.abs(incdiscList.get(k).get(i)) < 1) {
                    safe = 0;
                    List<Integer> dampenerline_1 = new ArrayList<Integer>(); 
                    for (int n = 0; n< lineEleList.get(k).size();n++){                   
                        dampenerline_1 = new ArrayList<Integer>(lineEleList.get(k));
                        dampenerline_1.remove(n);
                        List<Integer> incdiscDampenerList = new ArrayList<Integer>();
                        for( int m  = 0; m < dampenerline_1.size(); m++){
                            if (m ==0)continue;
                            else{
                                int incdiscDampener = dampenerline_1.get(m - 1) - dampenerline_1.get(m);
                                incdiscDampenerList.add(incdiscDampener);
                            }
                        }
                        safeDam = SafeCheck(incdiscDampenerList);
                        // left lvl
                        if (safeDam == 1) break;
                    }
                    if (safeDam == 1) break;
                    break;
                } 
                else {
                    if (i == 0)
                        continue;
                    else {
                        if ((incdiscList.get(k).get(i) > 0 && (incdiscList.get(k).get(i - 1) > 0)) || (incdiscList.get(k).get(i) < 0 && (incdiscList
                                .get(k).get(i - 1) < 0))) {
                            safe = 1;
                        } 
                        else {
                            safe = 0;
                            List<Integer> dampenerline_3 = new ArrayList<Integer>();
                            for (int n = 0; n< lineEleList.get(k).size();n++){ 
                                dampenerline_3 = new ArrayList<Integer>(lineEleList.get(k));
                                dampenerline_3.remove(n);
                                List<Integer> incdiscDampenerList_3 = new ArrayList<Integer>();
                                for (int m = 0; m < dampenerline_3.size(); m++) {
                                    if (m == 0)
                                        continue;
                                    else {
                                        int incdiscDampener_3 = dampenerline_3.get(m - 1) - dampenerline_3.get(m);
                                        incdiscDampenerList_3.add(incdiscDampener_3);
                                    }
                                }
                                safeDam = SafeCheck(incdiscDampenerList_3);
                                // left lvl
                                if (safeDam == 1) break;
                            }
                            if (safeDam == 1) break;
                            safe = 0;
                            break;
                        }
                    }
                }
            }
            safeList.add(safe + safeDam);
            System.out.println("safeList un/safe: " + (j + 1) + " " + incdiscList.get(j) + " " + lineEleList.get(j)+" "+ safe);
            j++;
        }

        sum = 0;
        j = 0;
        for (int num : safeList) {
            if (num == 0) {
                System.out.println("safeList unsafe: " + (j + 1) + " " + incdiscList.get(j) + " " + lineEleList.get(j)
                        + " " + num);
            }
            sum += num; // Add each number to sum
            j++;
        }
        System.out.println(sum);

    }
}
