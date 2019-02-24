package com.cs;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RenameSrtOrPDFs {
    //autoRename
    public static void main(String[] args) {
        String path = "G:\\youtube\\algo design\\6-046j-spring-2015\\ex";
        String regex = "_lec0|(\\.)|_rec0|Recitation|_lec";             // divide mode 分割模式
        int indexSourceName = 0;                                        // 视频或者目标文件名 --得到标识符
        int indexChangedName = 1;                                       // 被改的文件的 唯一标识符位置
        String shouldBeChangedContains = ".pdf";                        // 包含的内容
        String sourceNameFileContains = ".srt";                         // 目标文件名包含的内容(特别的,一般为类型)
        int charsOfSourceAfterDot = 3;                                  // 源文件后缀名长度
        String withAndAfterDot = "pdf";                                 // the kind of Files should Be changed
        String shouldBeChangedMustNOTContains = "(.)+(orig|alt|written|(\\.xml))(.)*";;   // exclude names that the should Be changed file not contains
        String initialSource = "";                                      //
        String initalShouldBeChanged="R";                               // 被改的文件需要开头加上的内容 --例如pdf为rec09,就需要补上个'R'
        String patternRegexShouldBeChanged = "^MIT(.)*";                // 满足条件的才去改变 防止已经改变的再度改变


        File pathFile = new File(path);
        Map<String, String> mapForIdentifierAndVideo = new HashMap<>();

        //build a map and put the identifier and name into the map
        File[] files = pathFile.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (fileName.contains(sourceNameFileContains)){
                String[] split = fileName.split(regex);
                String goal = initialSource + split[indexSourceName];
                mapForIdentifierAndVideo.put(goal, fileName.substring(0,fileName.length()-charsOfSourceAfterDot));
            }
        }

        //change!!
        for (File file : files) {
            if (Pattern.matches(patternRegexShouldBeChanged,file.getName()) && !Pattern.matches(shouldBeChangedMustNOTContains,file.getName())){
                if (file.getName().contains(shouldBeChangedContains) ){
                    String[] split = file.getName().split(regex);
                    String goal =initalShouldBeChanged + split[indexChangedName];
                    if (mapForIdentifierAndVideo.get(goal)!= null){
                        System.out.println(file.getName());
                        System.out.print(goal);
                        File newFile = new File(path + "\\" + mapForIdentifierAndVideo.get(goal) + withAndAfterDot);
                        file.renameTo(newFile);
                        System.out.println("   succeed! changedTo:   " + newFile.getName());
                    }
                }
            }

        }



    }
}
