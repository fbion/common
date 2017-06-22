package utils.file.interfaces.impl;

import utils.file.interfaces.IOperator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * 描述：遍历 <br>
 * 创建时间: 2017/6/229:02 <br>
 *
 * @author 周志辉
 */
public class InsertInnerClass implements IOperator{
    private List<String> classNames = new ArrayList<>();


    private Random random = new Random();

    private FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            return pathname.getName().endsWith(".java");
        }
    };


    public InsertInnerClass(Collection<String> classNames) {
        this.classNames.addAll(classNames);
    }


    public InsertInnerClass(String[] classNames) {
        this(Arrays.asList(classNames));
    }


    @Override
    public void operator(File file) throws IOException {
        //过滤非java文件
        if(!filter.accept(file)) {
            return;
        }
        System.out.print(String.format("%-60s", file.getAbsoluteFile()) + "\t\t");
        File desFile = new File(file.getAbsolutePath() + System.currentTimeMillis());
        try(BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(desFile))) {
            Stack<String> readStack = new Stack();
            String line = null;
            //读取目标文件内容到读取栈里
            while((line = br.readLine()) != null) {
                readStack.push(line);
            }
            Stack<String> writeStatck = new Stack<>();
            boolean flag =  false;
            //弹出读取栈并压入写入栈，并在最后一个右大括号前加入内部类代码
            while(!readStack.empty()) {
                line = readStack.pop();
                writeStatck.push(line);
                if(!flag && line.indexOf("}") >= 0) {
                    writeStatck.push("private class " + classNames.get(random.nextInt(classNames.size())) + "{}");
                    flag = true;
                    System.out.print("success" + "\t\t");
                }
            }
            while(!writeStatck.empty()) {
                line = writeStatck.pop();
                bw.write(line + "\n");
                bw.flush();
            }
        }
        System.out.print(file.delete() + "\t\t");
        System.out.println(desFile.renameTo(file));
    }
}
