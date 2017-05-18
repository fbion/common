package thinking.chapter18;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Created by Administrator on 2016/3/22.
 */
public class TextFile extends ArrayList<String> {
    static final String path = "F:\\idea-projects\\test\\src\\thinking\\chapter17\\";
    public static String read(String fileName)
    {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(fileName))))
        {
            String s;
            while((s = br.readLine()) != null)
            {
                sb.append(s);
                sb.append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void write(String fileName, String text)
    {
        try(PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile()))
        {
            out.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public TextFile(String fileName, String splitter)
    {
        super(Arrays.asList(read(fileName).split(splitter)));
        if(get(0).equals(""))
        {
            remove(0);
        }
    }

    public TextFile(String fileName)
    {
        this(fileName, "\n");
    }

    public void write(String fileName)
    {
        try(PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile()))
        {
            for (String s : this) {
                out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String file = path + "TextFile.java";
        write(path + "test.txt", file);
        TextFile tf = new TextFile(path + "test.txt");
        tf.write(path + "test2.txt");
        TreeSet<String> words = new TreeSet<>(new TextFile(path + "TextFile.java", "\\W+"));
        System.out.println(words.headSet("a"));
    }
}
