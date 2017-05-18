/**
 * 
 */
package thinking.chapter14.toys;

interface HasBatteries {}

interface Waterproof {}

interface Shoots {}

class Toy {
	Toy() {}
	Toy(int i) {}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
	FancyToy(int i) {super(1);}
}

/**
 * @author zzh
 *
 */
public class ToyTest {

	static void printInfo(Class cc) {
		System.out.println("class name: " + cc.getName() +
				"Is interface:[" + cc.isInterface() + "]");
		System.out.println("Simple name: " + cc.getSimpleName());
		System.out.println("Canonical name: " + cc.getCanonicalName());
	}
	
	public static void main(String[] args) {
		Class c = null;
		try{
			c = Class.forName("thinking.chapter14.toys.FancyToy");
		}catch(ClassNotFoundException e){
			System.out.println("Can not find FancyToy");
			System.exit(1);
		}
		printInfo(c);
		for (Class face : c.getInterfaces()) {
			printInfo(face);
		}
		Class up = c.getSuperclass();
		Object obj = null;
		try{
			obj = up.newInstance();
		}catch(InstantiationException e){
			System.out.println("can not instantiate");
		}catch (IllegalAccessException e) {
			System.out.println("can not access");
		}
		printInfo(obj.getClass());
	}
}
