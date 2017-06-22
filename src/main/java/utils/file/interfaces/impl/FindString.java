package utils.file.interfaces.impl;

import utils.file.interfaces.IOperator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 描述： <br>
 * 创建时间: 2017/6/229:08 <br>
 *
 * @author 周志辉
 */
public class FindString implements IOperator {
    private List<String> strs = new ArrayList<>();


    public FindString(Collection<String> classNames) {
        this.strs.addAll(classNames);
    }


    public FindString(String[] classNames) {
        this(Arrays.asList(classNames));
    }

    @Override
    public void operator(File file) {
//        System.out.println("\t\tIn operator : " + basePath + relativePath);
        if(file.getAbsolutePath()
                .contains("views\\")) {
//            System.out.println("false");
            return;
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            String line = null;
            while((line = br.readLine()) != null) {
                for(String s : strs) {
                    if(!s.trim().startsWith("//") && line.contains(s)) {
                        System.out.println(String.format("%-30s",s) + file.getAbsolutePath());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
