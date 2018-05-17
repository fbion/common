package ebook.thinking.chapter18.serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class RecoverCADState {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectInputStream in  = new ObjectInputStream(new FileInputStream("F:\\CADState.out"));
        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>) in.readObject();
        Line.deserializeStaticState(in);
        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}
