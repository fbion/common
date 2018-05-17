package ebook.thinking.chapter18.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/25.
 */
public class SerialCtl implements Serializable {
    private String a;
    private transient String b;

    public SerialCtl(String a, String b) {
        this.a = "Not transient: " + a;
        this.b = "transient: " + b;
    }
    public String toString()
    {
        return a + "\n" + b;
    }
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeObject(b);
    }
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        b = (String)stream.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialCtl sc = new SerialCtl("TestMethodSwiching", "Test2");
        System.out.println("Before:\n" + sc);
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(buf);
        out.writeObject(sc);
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
        SerialCtl sc2 = (SerialCtl) in.readObject();
        System.out.println("After:\n" + sc2);
    }
}
