package advance.IO;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/9.
 */
public class TestSerial {
    /**
     * 序列化
     * @param t
     * @param fileName
     * @param <T>
     */
    private static <T extends Serializable> void storeObjectToFile(T[] t, String[] fileName) {
        ObjectOutputStream out = null;
        for (int i = 0; i < t.length; i++) {
            String fullFileName = System.getProperty("user.dir") + "/" + fileName[i];
            try {
                out = new ObjectOutputStream(new FileOutputStream(fullFileName));
                out.writeObject(t[i]);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 反序列化
     * @param t
     * @param fileName
     * @param <T>
     */
    private static <T extends Serializable> void recoverObjectFromFile(T[] t, String[] fileName) {
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
                System.out.println("Exception : " + fileName[i]);
            }
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        MySerial my = new MySerial();
//        my.add(10);
        Class clazz = MySerial.class;
//        storeObjectToFile(new Serializable[]{my}, new String[]{"bak1.dat"});
        Field f = clazz.getDeclaredField("serialVersionUID");
        f.setAccessible(true);
        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
        f.set(null, 2l);
//        storeObjectToFile(new Serializable[]{my}, new String[]{"bak2.dat"});

        Serializable[] temp = new Serializable[1];
        recoverObjectFromFile(temp, new String[]{ "bak2.dat"});
        System.out.println(((MySerial)temp[0]).get(0));
    }
}

class MySerial extends ArrayList implements Serializable {
    private static final long serialVersionUID = 1L;
}
