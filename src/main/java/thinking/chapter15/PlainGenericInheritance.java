package thinking.chapter15;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PlainGenericInheritance {
    public static void main(String[] args) {
        Base base = new Base();
        Derived derived = new Derived();
        DerivedSetter ds = new DerivedSetter();
        ds.set(derived);
        ds.set(base);
    }
}

class OrdinerySetter{
    void set(Base base){
        System.out.println("OrdineryGetter.set(base)");
    }
}

class DerivedSetter extends OrdinerySetter {
    void set(Derived derived) {
        System.out.println("DerivedSetter.set(derived)");
    }
}