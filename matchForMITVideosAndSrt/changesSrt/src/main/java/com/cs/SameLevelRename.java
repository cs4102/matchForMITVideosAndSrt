package com.cs;

import java.io.File;
import java.util.regex.Pattern;

public class SameLevelRename {
    public static void main(String[] args) {
        String shouldBeChangedContains = "(.)+(orig|alt|written|(\\.xml))(.)*";
        File file = new File("G:\\youtube\\algo design\\6-046j-spring-2015\\ex");
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (Pattern.matches(shouldBeChangedContains,file1.getName())) {
                System.out.println(file1.getName());
            }
        }

    }
}
