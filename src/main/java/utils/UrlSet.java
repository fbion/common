package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UrlSet extends HashSet {

    @Override
    public String toString() {
        return super.toString().replaceAll("^\\[",",").replaceAll("\\]$",",").replaceAll(", ", ",");
    }


    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new FileReader("D:\\work\\项目\\CTS\\Test\\record.csv"))) {
            String line  = null;
            while((line = br.readLine()) != null) {
                if(!set.add(line.replaceAll("^/+", "").replaceAll("/+$", "").replaceAll("/+", "/"))) {
                    if(!"".equals(line) && !"project/createIssue".equals(line)) {
                        System.out.println(line);
                        System.out.println();
                    }
                };
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = 0;
        try(BufferedReader br = new BufferedReader(new FileReader("D:\\work\\项目\\CTS\\Test\\total.csv"))) {
            String line  = null;
            while((line = br.readLine()) != null) {
                if(!set.contains(line.replaceAll("^/+", "").replaceAll("/+$", "").replaceAll("/+", "/"))) {
                    count++;
                    System.out.println(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }
}