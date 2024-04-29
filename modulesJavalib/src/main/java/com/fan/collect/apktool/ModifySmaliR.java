package com.fan.collect.apktool;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModifySmaliR {


    public static void main(String[] args) {
//        mofifyR("D:\\rereplace\\sjztcn", "Lcom/arkgames/sjztcn", "Lcom1/a1rkgames/sjztcn1");
        mofifyR("D:\\work\\android\\decompile\\apktool\\app\\decompiled-app_tapmb\\smali\\com\\arkgames\\sjztcntap", "Lcom/arkgames/sjztcntap", "Lcom/tds/common");


    }

    public static void mofifyR(String smaliFloder, String oldChar, String newChar) {
        try (Stream<Path> list = Files.list(Paths.get(smaliFloder))) {
            String newFloder = smaliFloder + "\\new";
            File file = new File(newFloder);
            if (!file.exists()) {
                file.mkdir();
            }
            list.filter(path -> path.toString().endsWith("smali")).forEach(path -> {
                Path fileName = path.getFileName();
                String des = newFloder + File.separator + fileName;
                System.out.println("replace,src:" + path + " des:" + des);
                try {
                    modifyOneFile(path.toString(), des, oldChar, newChar);
                } catch (IOException e) {
                    System.out.println("replace error");
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void modifyOneFile(String src, String des, String oldChar, String newChar) throws IOException {
        List<String> content = Files.readAllLines(Paths.get(src), StandardCharsets.UTF_8);
        List<String> newcontent = content.stream().map(s -> s.replaceAll(oldChar, newChar)).collect(Collectors.toList());
        Files.write(Paths.get(des), newcontent);
    }


}
