package work.qizhi.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/30.
 */
public class TestSerial {

    public static List<Integer>  list;

    public static Map<Integer, Integer> map = new HashMap<>();

    static {
        recover();
    }

    public static void storeList() throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("F:\\12345.out"));
        out.writeObject(list);
    }

    public static void  recoverList(){
        List<Integer> temp = null;
        ObjectInputStream in = null;
        try {
            if(new File("F:\\12345.out").exists()){
                in = new ObjectInputStream(new FileInputStream("F:\\12345.out"));
                temp = (List<Integer>)in.readObject();
                list = temp;
            }else
            {
                list = new ArrayList<>();
            }

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            list = new ArrayList<>();
        }
    }


    public static <T extends Serializable> void storeObjectToFile(T[] t, String[] fileName) throws IOException {
        ObjectOutputStream out = null;
        for (int i = 0; i < t.length; i++) {
            String fullFileName = System.getProperty("user.dir") + "/" + fileName[i];
            out = new ObjectOutputStream(new FileOutputStream(fullFileName));
            out.writeObject(t[i]);
            out.close();
        }
    }

    public static <T extends Serializable> void recoverObjectFromFile(T[] t, String[] fileName) {
        ObjectInputStream in = null;
        for (int i = 0; i < t.length; i++) {
            String fullFileName = System.getProperty("user.dir") + "/" + fileName[i];
            try {
                if(new File(fullFileName).exists()){
                    in = new ObjectInputStream(new FileInputStream(fullFileName));
                    t[i] = (T)in.readObject();
                    in.close();
                }
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void recover(){
        Serializable[] temp = new Serializable[2];
        recoverObjectFromFile(temp, new String[]{"list.out", "map.out"});
        System.out.println(Arrays.deepToString(temp));
        list = (ArrayList)temp[0];
        map = (HashMap)temp[1];
        if(list == null)
        {
            list = new ArrayList<>();
        }
        if(map == null)
        {
            map = new HashMap<>();
        }
    }

    public static void store() throws IOException {
        storeObjectToFile(new Serializable[]{(Serializable)list, (Serializable)map}, new String[]{"list.out", "map.out"});
    }
}
