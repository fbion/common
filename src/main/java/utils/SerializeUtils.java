package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/27.
 */
public class SerializeUtils implements Serializable{
    TestSerializable t = new TestSerializable();
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
            }
        }
    }

    public static void main(String[] args) {
        storeObjectToFile(new Serializable[]{ new SerializeUtils()}, new String[]{"SerializeUtils.txt"});
    }
}

class TestSerializable implements Serializable{
    private void writeObject(ObjectOutputStream stream) throws IOException {
        throw new UnsupportedOperationException();
    }
}