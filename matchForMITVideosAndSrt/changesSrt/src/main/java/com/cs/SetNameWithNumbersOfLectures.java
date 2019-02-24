package com.cs;

import java.io.File;

public class SetNameWithNumbersOfLectures {
    public static void main(String[] args) {
        //从文件夹依据文件夹pattern提取文件并重命名
        //given a path and choose its grandsons to find out the specific files and rename it!
        String folderPathStr = "G:\\youtube\\algo design\\6-046j-spring-2015\\ex2";
        String regex = "-";             //some patterns you want to choose
        String initial = "R";           //start word that file you will get
        String end = ".pdf";            //end
        String containsPick = ".pdf";   // should contains
        String fobiddenPick = ".xml";   //should not contains
        int index = 1;                  //where it is as an array.
        File folderPath = new File(folderPathStr);
        File[] files = folderPath.listFiles();
        changingFileNames(folderPathStr, regex, initial, end, containsPick, fobiddenPick, files);

    }

    private static void changingFileNames(String folderPathStr, String regex, String initial, String end, String containsPick, String fobiddenPick, File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                String[] split = file.getName().split(regex);
                String goal = split[1];
                File[] subFiles = file.listFiles();
                for (File subFile : subFiles) {
                    String subFileName = subFile.getName();
                    if (subFileName.contains(containsPick) && !subFileName.contains(fobiddenPick)) {
                        File newFile = new File(folderPathStr + "\\" + initial + goal + end);
                        subFile.renameTo(newFile);
                        System.out.println("文件成功命名为:   " + newFile.getName());
                    }
                }
            }
        }
    }
}
