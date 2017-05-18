package test.recent;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2015/12/8.
 */
public class TestProperties
{
    public static void test() {
        Properties p = new Properties();

        try(InputStream ins = TestProperties.class
                .getResourceAsStream("test.properties"))
        {
            p.load(ins);
            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void test1() {
        try(BufferedReader br = new BufferedReader(new FileReader(new File("F:\\idea-projects\\test\\src\\test\\recent\\test.properties")))) {
            String line = null;
            while((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        Properties p = new Properties();

        try(InputStream ins = new FileInputStream(new File("C:\\\\test.properties")))
        {
            p.load(ins);
            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void test3() {
        Properties p = new Properties();
        File file = new File("F:\\idea-projects\\test\\src\\test\\recent\\test.properties");
        File file1 = new File("F:\\idea-projects\\test\\src\\test\\recent\\test1.properties");
        try(FileInputStream ins = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file))
        {
            p.load(ins);
            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            p.setProperty("c", "5");
            p.setProperty("d", "4");
            p.store(fos, "12345");
            fos.flush();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        file.delete();
        file1.renameTo(file);
    }

    public static void test4() {
        Properties p = new Properties();
        try
        {
//            File file = new File("C:\\test.properties");
            File file = new File("F:\\idea-projects\\test\\src\\test\\recent\\test.properties");
            FileInputStream ins = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(file);
            p.load(ins);
            for (Map.Entry<Object, Object> entry : p.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            p.setProperty("c", "5");
            p.setProperty("d", "4");
            p.store(fos, "12345");
            fos.flush();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test4();
    }
}
