package aoc_2024_src;

import java.io.File;
import java.io.IOException;
import java.util.List;

import java.nio.file.*;

import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class aocFunctions {
    public static List<String> readFile(String i_path, List<String> o_StringList) {
        Path filePath = Paths.get(i_path);
        try {
            o_StringList = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return o_StringList;
    }

    public static String FindFileByName (File i_file, String i_puzzleFile, String o_puzzelInputPath) {
        File[] fileArray = i_file.listFiles();
        for (File f : fileArray) {
            // System.out.println(file.getPath());
            if (f.isDirectory()) {
                o_puzzelInputPath = FindFileByName(f, i_puzzleFile, o_puzzelInputPath);
                if(o_puzzelInputPath !="") break;
            }
            if (f.isFile()) {
                if (f.getName().contains(i_puzzleFile)) {
                    System.out.println("Found " + f.getName());
                    // System.out.println(f.getPath());
                    o_puzzelInputPath = f.getPath();
                    break;
                }
            }
        }
        return o_puzzelInputPath;
    }
}
