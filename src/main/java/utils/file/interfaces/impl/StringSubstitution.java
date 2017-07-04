package utils.file.interfaces.impl;

import utils.file.interfaces.IOperator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;

/**
 * 描述： <br>
 * 创建时间: 2017/6/2211:16 <br>
 *
 * @author 周志辉
 */
public class StringSubstitution implements IOperator {

    private List<String> innerClassNames = new ArrayList<>();

    private FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            String filenName = pathname.getName();
            return filenName.endsWith(".class") && (filenName.indexOf("$") >= 0);
        }
    };


    public StringSubstitution(Collection<String> classNames) {
        this.innerClassNames.addAll(classNames);
    }


    public StringSubstitution(String[] classNames) {
        this(Arrays.asList(classNames));
    }


    @Override
    public void operator(File file) {
        //过滤非class文件
        if (!filter.accept(file)) {
            return;
        }

        System.out.println(String.format("%-60s", file.getAbsoluteFile()) + "\t\t");
        File desFile = new File(file.getAbsolutePath() + System.currentTimeMillis());
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
             BufferedWriter bw = new BufferedWriter(new FileWriter(desFile))) {
            String line = null;
            String className = file.getName().substring(0, file.getName().indexOf("."));
            Matcher m = null;
            while ((line = br.readLine()) != null) {
                if(line.indexOf(className) >= 0) {
                    int index = className.indexOf("$");
                    className = className.substring(0, index) + "\\$" + className.substring(index + 1);
                    String replacement = className.substring(0, index) + "\\$" + className.substring(index + 3);
                    line = line.replaceAll(className, replacement);
                }
                bw.write(line + "\n");
                bw.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(file.delete() + "\t\t");
        System.out.println(desFile.renameTo(file));
    }
}