package com.mxw.doraemon.utils;

import jodd.io.FileUtil;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RenamePackage {

    public static final String filePath = "/Users/alanma/git/ksc/crypto-ex/ksc-crypto-ex-server-spotmatch";
    //需要修改的文件类型
    public static final String[] fileType = {".java"};
    //指定修改文本替换 example
    private static String[][] replace ={ {"liaoxuefeng", "ksc"}, {"com.itranswarp.crypto", "com.ksc.crypto"} };

    public static void main(String[] args) {
        File filesBoot = new File(filePath);
        processDir(filesBoot);
        processReplace(filesBoot);
    }

    public static void processDir(File fileDir) {
        if (!fileDir.isDirectory()) {
           return;
        }

        if("itranswarp".equals(fileDir.getName()) &&
                fileDir.getParent().indexOf("main/java/com") > 0){
            fileDir.renameTo(new File(fileDir.getParent().concat(File.separator).concat("ksc")));
        } else {
            File[] files = fileDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return !pathname.getName().startsWith(".");
                }
            });
            Arrays.stream(files).forEach(file -> processDir(file));
        }

    }

    private static void processReplace(File fileDir) {
        if (!fileDir.isDirectory()) {
            String filename = fileDir.getName();
            Arrays.stream(fileType).forEach(item -> {
                if (filename.endsWith(item)) {
                    processFile(fileDir);
                }
            });

        } else {
            File[] files = fileDir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return !pathname.getName().startsWith(".");
                }
            });
            Arrays.stream(files).forEach(file -> processReplace(file));
        }
    }

    public static void processFile(File file) {
        try {
            System.out.println(file.getAbsolutePath());
            Reader reader = new FileReader(file);
            BufferedReader bReader = new BufferedReader(reader);
            File tmpFile = new File(System.getProperty("java.io.tmpdir")+File.separator+file.getName());
            Writer writer = new FileWriter(tmpFile);
            BufferedWriter bWriter = new BufferedWriter(writer);
            for (;;) {
                String str = bReader.readLine();
                if (str != null) {
                    List<String> target = Arrays.stream(replace).map(strings -> strings[0]).collect(Collectors.toList());
                    List<String> desc = Arrays.stream(replace).map(strings -> strings[1]).collect(Collectors.toList());
                    if (target.size() == desc.size()) {
                        for (int i = 0; i < target.size(); i++) {
                            str = str.replace(target.get(i), desc.get(i));
                        }
                        bWriter.append(str);
                        bWriter.append("\n");
                    }
                } else {
                    break;
                }
            }
            bReader.close();
            bWriter.flush();
            bWriter.close();
            FileUtil.copy(tmpFile, file);

            tmpFile.deleteOnExit();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
