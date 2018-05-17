/**
 *
 */
package ebook.thinking.chapter14;

class Building {}

class House extends Building {}

/**
 * @author zzh
 *
 */
public class ClassCasts {

	public static void main(String[] args) {
		Building b = new House();
		Class<House> c = House.class;
		House h = c.cast(b);
		h = (House)b;
	}
}
