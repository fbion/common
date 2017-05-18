package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;

/**
 * Created by Administrator on 2016/9/20.
 */
import java.io.File;
import java.io.FileWriter;
import java.util.*;
public class test22 {
    public static void test1() {
        if(System.out.printf("a") == null) {
            System.out.println("a");
        } else {
            System.out.println("b");
        }
    }

    public static void test2() {
        if(new Object(){
            public boolean test() {
                System.out.print("a");
                return false;
            }
        }.test()){
            System.out.println("a");
        } else {
            System.out.println("b");
        }
    }

    public static void test3() {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File("f:\\test1234.txt")))) {
            for (int i = 0; i < 500000; i++) {
                bw.write(i + "\r\n");
            }
            bw.flush();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test4() {
        try(BufferedReader br = new BufferedReader(new java.io.FileReader(new File("f:\\test1234.txt")))) {
            String line = null;
            while((line = br.readLine()) != null)
                System.out.println(line);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args){
//        test4();
//        System.out.println("Over");
        new Object(){
            class MyTest{}
        };
    }
}