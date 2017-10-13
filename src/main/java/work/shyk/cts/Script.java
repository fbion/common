package work.shyk.cts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description： <br>
 * createTime: 2017/10/1110:44 <br>
 *
 * @author zzh
 */
public class Script {

    public static void test() {
        String separator = "\t";
        String srcFile = "D:\\work\\项目\\CTS\\数据库\\tables.txt";
        String desFile = "D:\\work\\项目\\CTS\\数据库\\tables.csv";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"));
             BufferedWriter bw = new BufferedWriter(new FileWriter(desFile))) {
            String line = null;
            int status = 0;
            String[] temp = null;
            StringBuilder writeLine = new StringBuilder("");
            while ((line = br.readLine()) != null) {
                if (status == 0) {
                    if (line.startsWith("cts_")) {
                        status = 1;
                    }
                    writeLine.append(line);
                } else if (status == 1) {
                    if ("".equals(line.replaceAll(",+", ""))) {
                        status = 0;
                    } else {
                        temp = line.replaceAll("not\\s*null", "").
                                replaceAll("',", "").
                                replaceAll("'", "").
                                replaceAll("comment", "").
                                replaceAll("^\\s*", "").
                                replaceAll("\\s*$", "").
                                split("\\s+");
                        if (temp.length >= 3) {
                            String s = temp[1].replaceAll("(\\D*)(\\d+)(\\D*)", "$2");
                            if (!s.matches("\\d+")) {
                                s = "";
                            }
                            writeLine.append(temp[0]).
                                    append(separator).
                                    append(temp[1]).
                                    append(separator).
                                    append(s).
                                    append(separator).
                                    append(temp[2]);
                        }
                    }
                }
                bw.write(writeLine.toString() + "\n");
                writeLine.delete(0, writeLine.length());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void test1() {
        String separator = "\t";
        String srcFile = "D:\\work\\项目\\CTS\\数据库\\tables.txt";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), "UTF-8"))) {
            String line = null;
            int status = 0;
            String[] temp = null;
            while ((line = br.readLine()) != null) {
                if (status == 0) {
                    if (line.startsWith("cts_")) {
                        status = 1;
                        System.out.println(line);
                    }
                } else if (status == 1) {
                    if ("".equals(line.replaceAll(",+", ""))) {
                        status = 0;
                    } else {
                        String s = line.replaceAll("([^']+)'([^']*)'([^']+)", "$2");
                        if (s.indexOf(" ") >= 0) {
                            temp = line.replaceAll("not\\s*null", "").
                                    replaceAll("',", "").
                                    replaceAll("'", "").
                                    replaceAll("comment", "").
                                    replaceAll("^\\s*", "").
                                    replaceAll("\\s*$", "").
                                    split("\\s+");
                            System.out.println("\t\t\t" + temp[0] + "\t\t\t\t" + s);
                        }

                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        test1();
    }
}
